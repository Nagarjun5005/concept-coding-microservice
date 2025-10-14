package com.order;

public class ProductServiceException extends RuntimeException{

    private int status;

    public ProductServiceException(String message, int status) {
        super(message);
        this.status = status;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
