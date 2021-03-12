package com.essam.carstask.utls;

public class Resource <T>{

    private T data;
    private Status status;
    private String errorMsg;

    public T getData() {
        return data;
    }

    public Status getStatus() {
        return status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public enum Status {
        SUCCESS, ERROR
    }

    public Resource<T> success (T data){
        this.data = data;
        status = Status.SUCCESS;
        return this;
    }


    public Resource<T> error (Throwable throwable){
        status = Status.ERROR;
        errorMsg = "Check your internet connection";
        return this;
    }
}
