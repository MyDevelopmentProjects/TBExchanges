package ge.mgl.service;

import ge.mgl.dao.TTransfersDAO;
import ge.mgl.entities.*;
import ge.mgl.security.UserUtils;
import ge.mgl.utils.pagination.PaginationAndFullSearchQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TTransfersService {
    @Autowired
    private TTransfersDAO dao;

    @Transactional(readOnly = true)
    public PaginationAndFullSearchQueryResult<TTransfers> getList(String searchExpression, String sortField, boolean isAscending, Integer pageNumber, int pageSize, Date startDate, Date endDate, Long userId, String clientId, Long ccyFrom, Long ccyTo, Long companyId, String tid) {
        return dao.getPaginatedList(TTransfers.class, searchExpression, sortField, isAscending, pageNumber, pageSize,
                startDate,
                endDate,
                userId,
                clientId,
                ccyFrom,
                ccyTo,
                companyId,
                tid);
    }

    @Transactional
    public TTransfers save(TTransfers transfers) {
        transfers.setUser(UserUtils.currentUser());
        if (transfers.getId() != null) return dao.update(transfers);
        return dao.create(transfers);
    }

    @Transactional(readOnly = true)
    public TTransfers findById(Long id) {
        return dao.find(id);
    }

    @Transactional
    public boolean delete(Long id) {
        return dao.delete(id);
    }
}