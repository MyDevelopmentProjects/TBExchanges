package ge.mgl.service;

import ge.mgl.dao.FUserDAO;
import ge.mgl.entities.*;
import ge.mgl.pojos.UserRegistrationPOJO;
import ge.mgl.utils.RequestResponse;
import ge.mgl.utils.RequestResponseWithSource;
import ge.mgl.utils.pagination.PaginationAndFullSearchQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FUserService {

    @Autowired
    private FUserDAO userDAO;

    @Transactional(readOnly = true)
    public PaginationAndFullSearchQueryResult<FUser> getList(String searchExpression, String sortField, boolean isAscending, Integer pageNumber, int pageSize) {
        return userDAO.getPaginatedList(FUser.class,
                searchExpression, sortField, isAscending, pageNumber, pageSize);
    }

    @Transactional
    public FUser findByUserName(String username) {
        return userDAO.findByUserName(username);
    }

    @Transactional
    public FUser loadUserByUsername(String username) {
        return userDAO.findByUserName(username);
    }

    @Transactional
    public RequestResponseWithSource<FUser> register(UserRegistrationPOJO user) {
        RequestResponseWithSource<FUser> usr = userDAO.register(user);
        if (usr.getSuccess()) {
        }
        return usr;
    }

    @Transactional
    public FUser save(FUser user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
        if (user.getId() != null) {
            if (user.getPassword().length() != 60) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            return userDAO.update(user);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDAO.create(user);
    }

    @Transactional(readOnly = true)
    public FUser findById(Long id) {
        return userDAO.find(id);
    }

    @Transactional
    public boolean delete(Long id) {
        return userDAO.delete(id);
    }

}
