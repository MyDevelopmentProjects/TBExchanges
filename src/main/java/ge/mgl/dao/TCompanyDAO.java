package ge.mgl.dao;

import ge.mgl.entities.TCompany;
import ge.mgl.utils.pagination.PaginationAndFullSearchQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TCompanyDAO extends PaginationAndFullSearchQuery<TCompany> {
    public TCompanyDAO() {
        super(TCompany.class);
    }

    @Override
    public <T> List<String> getFieldsAvailableForFullTextSearch(Class<T> resultClass) {
        if (resultClass == TCompany.class) {
        }
        return null;
    }
}