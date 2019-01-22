package ge.mgl.utils.pagination;

import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.List;

public class PaginationAndFullSearchQueryResult<T> {

    private static DozerBeanMapper mapper = new DozerBeanMapper();

    private List<T> results;
    private T result;
    private Long maxPages;
    private Long total;
    private boolean success;
    private int code;
    private String message;

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public Long getMaxPages() {
        return maxPages;
    }

    public void setMaxPages(Long maxPages) {
        this.maxPages = maxPages;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public <S> PaginationAndFullSearchQueryResult<S> transform(Class<S> clazz) {
        PaginationAndFullSearchQueryResult<S> result = new PaginationAndFullSearchQueryResult<>();

        List<S> results = new ArrayList<>();

        for (T item : this.results) {
            results.add(mapper.map(item, clazz));
        }

        if (this.result != null) {
            result.result = mapper.map(this.result, clazz);
        }

        result.setResults(results);
        result.setCode(0);
        result.setMessage(null);
        result.setSuccess(true);
        result.setMaxPages(this.getMaxPages());
        result.setTotal(this.getTotal());
        return result;
    }

}