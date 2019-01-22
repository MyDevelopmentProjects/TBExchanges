package ge.mgl.service;

import ge.mgl.dao.TCCYDAO;
import ge.mgl.entities.*;
import ge.mgl.utils.pagination.PaginationAndFullSearchQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TCCYService {
    @Autowired
    private TCCYDAO dao;

    @Transactional(readOnly = true)
    public PaginationAndFullSearchQueryResult<TCCY> getList(String searchExpression, String sortField, boolean isAscending, Integer pageNumber, int pageSize) {
        return dao.getPaginatedList(TCCY.class, searchExpression, sortField, isAscending, pageNumber, pageSize);
    }

    @Transactional
    public TCCY save(TCCY ccy) {
        if (ccy.getId() != null) {
            return dao.update(ccy);
        }
        return dao.create(ccy);
    }

    @Transactional(readOnly = true)
    public TCCY findById(Long id) {
        return dao.find(id);
    }

    @Transactional
    public boolean delete(Long id) {
        return dao.delete(id);
    }
}