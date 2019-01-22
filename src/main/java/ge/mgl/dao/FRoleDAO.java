package ge.mgl.dao;

import ge.mgl.entities.FRole;
import ge.mgl.utils.pagination.PaginationAndFullSearchQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FRoleDAO extends PaginationAndFullSearchQuery<FRole> {

    public FRoleDAO() {
        super(FRole.class);
    }

    @Override
    public <T> List<String> getFieldsAvailableForFullTextSearch(Class<T> resultClass) {
        return null;
    }
}
