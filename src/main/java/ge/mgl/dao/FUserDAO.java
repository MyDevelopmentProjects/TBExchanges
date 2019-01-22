package ge.mgl.dao;

import ge.mgl.entities.FUser;
import ge.mgl.pojos.UserRegistrationPOJO;
import ge.mgl.utils.RequestResponseWithSource;
import ge.mgl.utils.pagination.PaginationAndFullSearchQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FUserDAO extends PaginationAndFullSearchQuery<FUser> {

    public FUserDAO() {
        super(FUser.class);
    }

    public FUser findByUserName(String username) {
        List<FUser> users;
        users = getEntityManager().createQuery("from FUser where LOWER(username)=LOWER(:username)")
                .setParameter("username", username)
                .getResultList();
        if (users != null && users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    public FUser findUserByUsernamePassword(String username, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
        String hashedPassword = passwordEncoder.encode(password);

        List<FUser> user = getEntityManager().createQuery("FROM User where LOWER(username)=LOWER(:username) and password=:password")
                .setParameter("username", username)
                .setParameter("password", hashedPassword)
                .getResultList();
        if (user != null && user.size() > 0) {
            return user.get(0);
        }
        return null;
    }

    public RequestResponseWithSource<FUser> register(UserRegistrationPOJO user) {

        if (findByUserName(user.getUsername()) != null) {
            return new RequestResponseWithSource<>(false, "User already exists");
        }

        FUser newUser = new FUser(user);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        FUser created = this.create(newUser);
        RequestResponseWithSource<FUser> resp = new RequestResponseWithSource<>(true);
        resp.setSource(created);
        return resp;
    }

    @Override
    public <T> List<String> getFieldsAvailableForFullTextSearch(Class<T> resultClass) {
        List<String> fieldList = new ArrayList<>();
        if (resultClass == FUser.class) {
            fieldList.add("username");
            fieldList.add("fullName");
            fieldList.add("pid");
        }
        return fieldList;
    }

}
