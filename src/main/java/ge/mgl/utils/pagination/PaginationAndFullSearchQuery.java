package ge.mgl.utils.pagination;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public abstract class PaginationAndFullSearchQuery<T> {

    protected final String TABLE_ALIAS = "PAGINATION_AND_FULL_SEARCH_QUERY";

    @PersistenceContext
    private EntityManager em;

    private final Class<T> clazz;

    public PaginationAndFullSearchQuery(Class<T> clazz){
        this.clazz = clazz;
    }

    public T first(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> rootEntry = cq.from(clazz);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = em.createQuery(all);
        List<T> resultList = allQuery.getResultList();
        return (!resultList.isEmpty())? resultList.get(0):null;
    }

    public List<T> lastX(int number) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> rootEntry = cq.from(clazz);
        CriteriaQuery<T> all = cq.select(rootEntry);
        cq.orderBy(cb.desc(rootEntry.get("dateCreated")));
        TypedQuery<T> allQuery = em.createQuery(all);
        return  allQuery.setFirstResult(0).setMaxResults(number).getResultList();
    }

    public List<T> all(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> rootEntry = cq.from(clazz);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = em.createQuery(all);
        List<T> resultList = allQuery.getResultList();
        return  (!resultList.isEmpty())? resultList:new ArrayList<>();
    }

    public Long total() {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(clazz)));
        return em.createQuery(cq).getSingleResult();
    }

    public T find(Long id) {
        return em.find(clazz, id);
    }

    public T create(T t) {
        em.persist(t);
        return t;
    }

    public T update(T t) {
        return em.merge(t);
    }

    public boolean delete(Long id) {
        T obj = em.find(clazz, id);
        if (obj == null) {
            return false;
        }
        em.remove(obj);
        return true;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public abstract <T> List<String> getFieldsAvailableForFullTextSearch(Class<T> resultClass);

    public <T> Predicate getPDQExpression(Class<T> resultClass, Object... objects) {
        return null;
    }

    public <T> PaginationAndFullSearchQueryResult<T> getPaginatedList(Class<T> resultClass,
                                                                      String searchExpression,
                                                                      String sortField,
                                                                      boolean isAscending,
                                                                      int pageNumber,
                                                                      int pageSize,
                                                                      Object... objects) {
        if (pageNumber < 0) {
            pageNumber = 0;
        }
        if (pageSize > 0 && (pageSize < 6 || pageSize > 100)) {
            pageSize = 6;
        } else if (pageSize == -255) //CUSTOM VALUE FOR EXCEL REPORTING...
        {
            pageSize = 5000;
        }
        Predicate pdqExpression = getPDQExpression(resultClass, objects);
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(resultClass);
        Root<T> returnClassRoot = criteriaQuery.from(resultClass);
        returnClassRoot.alias(TABLE_ALIAS);


        if (searchExpression != null && searchExpression.length() > 0 && getFieldsAvailableForFullTextSearch(resultClass) != null) {
            List<String> fields = getFieldsAvailableForFullTextSearch(clazz);

            Predicate predicate = null;

            for (String field : fields) {
                Predicate tmpPredicate = criteriaBuilder.like(criteriaBuilder.lower(getField(returnClassRoot, field).as(String.class)),searchExpression.toLowerCase() + "%");
                predicate = predicate == null ? tmpPredicate : criteriaBuilder.or(predicate, tmpPredicate);
            }

            if (pdqExpression != null) {
                predicate = criteriaBuilder.and(predicate, pdqExpression);
            }

            criteriaQuery.where(predicate);
        } else {
            if (pdqExpression != null) {
                criteriaQuery.where(pdqExpression);
            }
        }

        PaginationAndFullSearchQueryResult<T> result = new PaginationAndFullSearchQueryResult<T>();

        CriteriaQuery<Long> countQuery = createCountQuery(criteriaQuery, resultClass);
        if (countQuery != null) {
            TypedQuery<Long> query = em.createQuery(countQuery);
            Long rowCount = query.getSingleResult();

            result.setMaxPages((rowCount - 1) / pageSize + 1);
            result.setTotal(rowCount);
        }

        if (result.getMaxPages() > pageNumber) {
            // if sort field is not empty, then create order by expression
            if (sortField != null && sortField.length() > 0) {
                criteriaQuery.orderBy(
                        isAscending ? criteriaBuilder.asc(getField(returnClassRoot, sortField)) : criteriaBuilder.desc(getField(returnClassRoot, sortField))
                );
            }

            TypedQuery<T> query = em.createQuery(criteriaQuery);
            query.setFirstResult(pageNumber * pageSize);
            query.setMaxResults(pageSize);

            result.setResults(query.getResultList());
        } else {
            result.setResults(new ArrayList<T>());
            result.setTotal(0L);
        }
        result.setCode(200);
        result.setSuccess(true);
        return result;
    }

    private <T> Path<String> getField(Root<T> root, String fieldName) {
        String[] fields = fieldName.split("\\.");
        String alias = TABLE_ALIAS;
        if (fields.length > 1) {
            Join<String, String> join = root.join(fields[0], JoinType.LEFT);
            alias += "X" + fields[0];
            join.alias(alias);
            for (int i = 1; i < fields.length - 1; i++) {
                alias += "X" + fields[i];
                join = join.join(fields[i], JoinType.LEFT);
                join.alias(alias);
            }
            return join.get(fields[fields.length - 1]);
        } else {
            return root.get(fields[fields.length - 1]);
        }
    }

    public <T> CriteriaQuery<Long> createCountQuery(CriteriaQuery<T> criteriaQuery, Class<T> clazz) {
        CriteriaQuery<Long> countQuery = em.getCriteriaBuilder().createQuery(Long.class);
        for (Root<?> root : criteriaQuery.getRoots()) {
            Root<?> dest = countQuery.from(root.getJavaType());
            dest.alias(root.getAlias());
            copyJoins(root, dest);
        }
        if (criteriaQuery.getRestriction() != null) {
            countQuery.where(criteriaQuery.getRestriction());
        }
        Root<T> root = null;
        for (Root<?> r : criteriaQuery.getRoots()) {
            if (clazz.equals(r.getJavaType())) {
                root = (Root<T>) r.as(clazz);
            }
        }
        Expression<Long> countExpression = em.getCriteriaBuilder().count(root);
        return countQuery.select(countExpression);
    }

    private void copyJoins(From<?, ?> from, From<?, ?> to) {
        for (Join<?, ?> j : from.getJoins()) {
            Join<?, ?> toJoin = to.join(j.getAttribute().getName(), j.getJoinType());
            toJoin.alias(j.getAlias());
            copyJoins(j, toJoin);
        }
    }
}
