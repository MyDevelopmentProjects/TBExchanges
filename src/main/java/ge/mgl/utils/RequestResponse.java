package ge.mgl.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestResponse {

    private Boolean success = false;
    private String message;
    private List<String> errors = new ArrayList<>();
    private Integer code;
    private Long objectId;

    public RequestResponse() {
    }

    public RequestResponse(Boolean success) {
        this.success = success;
    }

    public RequestResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public RequestResponse(Boolean success, String message, Integer code) {
        this.success = success;
        this.message = message;
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }
}