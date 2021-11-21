package org.example.model;

public class StoreResponse {
    private Integer code;
    private String type;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public StoreResponse(Integer code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }
}
