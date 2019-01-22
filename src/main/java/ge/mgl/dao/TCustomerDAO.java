package ge.mgl.dao;

import ge.mgl.entities.TCustomer;
import ge.mgl.utils.pagination.PaginationAndFullSearchQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TCustomerDAO extends PaginationAndFullSearchQuery<TCustomer> {
    public TCustomerDAO() {
        super(TCustomer.class);
    }

    @Override
    public <T> List<String> getFieldsAvailableForFullTextSearch(Class<T> resultClass) {
        if (resultClass == TCustomer.class) {
            List<String> list = new ArrayList<>();
            list.add("firstName");
            list.add("lastName");
            list.add("fullName");
            list.add("pid");
            return list;
        }
        return null;
    }
}