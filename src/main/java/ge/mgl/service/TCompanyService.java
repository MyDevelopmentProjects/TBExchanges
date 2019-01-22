package ge.mgl.service;

import ge.mgl.dao.TCompanyDAO;
import ge.mgl.entities.*;
import ge.mgl.utils.pagination.PaginationAndFullSearchQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TCompanyService {
    @Autowired
    private TCompanyDAO dao;

    @Transactional(readOnly = true)
    public PaginationAndFullSearchQueryResult<TCompany> getList(String searchExpression, String sortField, boolean isAscending, Integer pageNumber, int pageSize) {
        return dao.getPaginatedList(TCompany.class, searchExpression, sortField, isAscending, pageNumber, pageSize);
    }

    @Transactional
    public TCompany save(TCompany company) {
        if (company.getId() != null) return dao.update(company);
        return dao.create(company);
    }

    @Transactional(readOnly = true)
    public TCompany findById(Long id) {
        return dao.find(id);
    }

    @Transactional
    public boolean delete(Long id) {
        return dao.delete(id);
    }
}