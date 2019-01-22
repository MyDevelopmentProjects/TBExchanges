package ge.mgl.service;

import ge.mgl.dao.TCCYExchangeDAO;
import ge.mgl.entities.*;
import ge.mgl.security.UserUtils;
import ge.mgl.utils.pagination.PaginationAndFullSearchQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TCCYExchangeService {
    @Autowired
    private TCCYExchangeDAO dao;

    @Transactional(readOnly = true)
    public PaginationAndFullSearchQueryResult<TCCYExchange> getList(String searchExpression, String sortField, boolean isAscending, Integer pageNumber, int pageSize, Date startDate, Date endDate, Long userId, String clientId, Long ccyFrom, Long ccyTo) {
        return dao.getPaginatedList(TCCYExchange.class, searchExpression, sortField, isAscending, pageNumber, pageSize,
                startDate,
                endDate,
                userId,
                clientId,
                ccyFrom,
                ccyTo);
    }

    @Transactional
    public TCCYExchange save(TCCYExchange ccyexchange) {
        ccyexchange.setUser(UserUtils.currentUser());
        if (ccyexchange.getId() != null) {
            return dao.update(ccyexchange);
        }
        return dao.create(ccyexchange);
    }

    @Transactional(readOnly = true)
    public TCCYExchange findById(Long id) {
        return dao.find(id);
    }

    @Transactional
    public boolean delete(Long id) {
        return dao.delete(id);
    }
}