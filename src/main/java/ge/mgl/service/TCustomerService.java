package ge.mgl.service;

import ge.mgl.dao.TCustomerDAO;
import ge.mgl.entities.*;
import ge.mgl.utils.pagination.PaginationAndFullSearchQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TCustomerService {
    @Autowired
    private TCustomerDAO dao;

    @Transactional(readOnly = true)
    public PaginationAndFullSearchQueryResult<TCustomer> getList(String searchExpression, String sortField, boolean isAscending, Integer pageNumber, int pageSize) {
        return dao.getPaginatedList(TCustomer.class, searchExpression, sortField, isAscending, pageNumber, pageSize);
    }

    @Transactional
    public TCustomer save(TCustomer customer) {
        if (customer.getId() != null) return dao.update(customer);
        return dao.create(customer);
    }

    @Transactional(readOnly = true)
    public TCustomer findById(Long id) {
        return dao.find(id);
    }

    @Transactional
    public boolean delete(Long id) {
        return dao.delete(id);
    }
}