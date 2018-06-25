package com.richie.backstage.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author richie on 2018.04.19
 * 数据格式
 */
public class Result<T> {

    public static final int STATUS_YES = 1;
    public static final int STATUS_NO = 0;
    private int status;
    private T data;
    private ErrorCode errorCode;

    public Result(int status) {
        this.status = status;
    }

    public Result(int status, T data) {
        this.status = status;
        this.data = data;
    }

    public Result(int status, ErrorCode errorCode) {
        this.status = status;
        this.errorCode = errorCode;
    }

    public static Result createYesResult() {
        return new Result(STATUS_YES);
    }

    public static <T> Result createYesResult(T data) {
        return new Result<>(STATUS_YES, data);
    }

    public static Result createNoResult(ErrorCode errorCode) {
        return new Result(STATUS_NO, errorCode);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", data=" + data +
                ", errorCode=" + errorCode +
                '}';
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum ErrorCode {
        UNKNOWN_ERROR(100, "未知错误"),
        LOGIN_FAILED(101, "密码错误"),
        REPEATED_USERNAME(102, "用户名已存在"),
        LOGOUT_FAILED(103, "注销失败"),
        NOT_LOGIN(104, "用户未登录"),
        REGISTER_FAILED(105, "注册失败"),
        REPEATED_PHONE(106, "手机号已注册"),
        USER_NOT_EXIST(107, "用户不存在");

        private int code;
        private String message;

        ErrorCode(int code, String message) {
            this.code = code;
            this.message = message;
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
    }

    /*
    *
    * {
    "status": 0,
    "errorCode": {
        "code": 101,
        "message": "用户名已存在"
      }
    }
    *
    * */

    /*
     *
     * {
    "status": 1,
    "data": {
        "userId": 1,
        "username": "richie",
        "lastVisit": 1524144501000,
        "email": "isuperqiang@gmail.com",
        "description": ""
    }
}
     *
     */
}
