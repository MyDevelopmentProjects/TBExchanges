package ge.mgl.utils;

public class RequestResponseWithSource<T> extends RequestResponse {

    public RequestResponseWithSource(boolean success) {
        super(success);
    }

    public RequestResponseWithSource() {
        super();
    }

    public RequestResponseWithSource(boolean success, String message) {
        super(success, message);
    }

    public RequestResponseWithSource(Boolean success, String message, Integer code) {
        super(success, message, code);
    }

    private T source;

    public T getSource() {
        return source;
    }

    public void setSource(T source) {
        this.source = source;
    }

}

