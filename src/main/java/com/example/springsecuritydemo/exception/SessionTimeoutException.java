package com.example.springsecuritydemo.exception;

/**
 * @author Dennis.Chen
 * @Date
 */
public class SessionTimeoutException extends RuntimeException {

    String errorCode;
    String errorMsg;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
