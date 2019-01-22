package ge.mgl.dao;

import ge.mgl.entities.TCCY;
import ge.mgl.utils.pagination.PaginationAndFullSearchQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TCCYDAO extends PaginationAndFullSearchQuery<TCCY> {
    public TCCYDAO() {
        super(TCCY.class);
    }

    @Override
    public <T> List<String> getFieldsAvailableForFullTextSearch(Class<T> resultClass) {
        if (resultClass == TCCY.class) {
            List<String> list = new ArrayList<>();
            list.add("title");
            return list;
        }
        return null;
    }
}