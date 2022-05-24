package com.example.ssbeproject.rest;

import java.util.HashMap;
import java.util.Map;

public class RestResponse {
    private String message;
    private int code;
    private boolean status;
    private Object data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setData(String key, Object data) {
        Map<String, Object> val = new HashMap<>();
        val.put(key, data);
        this.data = val;
    }
}
