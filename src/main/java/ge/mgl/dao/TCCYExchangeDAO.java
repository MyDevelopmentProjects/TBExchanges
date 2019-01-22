package ge.mgl.dao;

import ge.mgl.entities.TCCYExchange;
import ge.mgl.utils.MGLStringUtils;
import ge.mgl.utils.pagination.PaginationAndFullSearchQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository public
class TCCYExchangeDAO extends PaginationAndFullSearchQuery<TCCYExchange> {
    
    public TCCYExchangeDAO() {
        super(TCCYExchange.class);
    }

    @Override
    public <T> Predicate getPDQExpression(Class<T> resultClass, Object... objects) {

        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TCCYExchange> criteriaQuery = criteriaBuilder.createQuery(TCCYExchange.class);
        Root<TCCYExchange> returnClassRoot = criteriaQuery.from(TCCYExchange.class);
        returnClassRoot.alias(TABLE_ALIAS);

        List<Predicate> exprs = new ArrayList<>();

        if (objects != null) {
            if (objects.length > 0 && objects[0] instanceof Date && objects.length > 1 && objects[1] instanceof Date) {
                Date start = formatDate(((Date) objects[0]), true);
                Date end = formatDate(((Date) objects[1]), false);
                exprs.add(criteriaBuilder.between(returnClassRoot.get("dateCreated"), start, end));
            }
            if (objects.length > 2 && objects[2] instanceof Long) {
                exprs.add(criteriaBuilder.equal(returnClassRoot.get("user").get("id"), objects[2]));
            }
            if (objects.length > 3 && objects[3] instanceof String) {
                exprs.add(criteriaBuilder.equal(returnClassRoot.get("customer").get("pid"), objects[3]));
            }
            if (objects.length > 4 && objects[4] instanceof Long) {
                exprs.add(criteriaBuilder.equal(returnClassRoot.get("ccyFrom").get("id"), objects[4]));
            }
            if (objects.length > 5 && objects[5] instanceof Long) {
                exprs.add(criteriaBuilder.equal(returnClassRoot.get("ccyTo").get("id"), objects[5]));
            }
        }

        if (exprs.size() > 0) {
            return criteriaBuilder.and(exprs.toArray(new Predicate[exprs.size()]));
        }

        return null;
    }

    private Date formatDate(Date start, boolean low) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(start);
        instance.set(Calendar.HOUR_OF_DAY, low ? 0 : 23);
        instance.set(Calendar.MINUTE, low ? 0 : 59);
        instance.set(Calendar.SECOND, low ? 0 : 59);
        return instance.getTime();
    }
    
    @Override
    public <T> List<String> getFieldsAvailableForFullTextSearch(Class<T> resultClass) {
        if (resultClass == TCCYExchange.class) {
            List<String> list = new ArrayList<>();
            list.add("title");
            return list;
        }
        return null;
    }
}