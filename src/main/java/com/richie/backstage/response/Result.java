package com.richie.backstage.response;

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
        USER_NOT_EXIST(107, "用户不存在"),

        CREATE_STORE_FAILED(200, "创建店铺失败"),
        UPDATE_STORE_FAILED(201, "更新店铺失败"),
        QUERY_STORE_FAILED(202, "查询不到店铺"),

        CREATE_CATEGORY_FAILED(300, "创建分类失败"),
        UPDATE_CATEGORY_FAILED(301, "更新分类失败"),
        DELETE_CATEGORY_FAILED(302, "删除分类失败"),
        QUERY_CATEGORY_FAILED(304, "查询不到分类"),

        CREATE_GOODS_FAILED(400, "创建商品失败"),
        UPDATE_GOODS_FAILED(401, "更新商品失败"),
        DELETE_GOODS_FAILED(402, "删除商品失败"),
        QUERY_GOODS_FAILED(403, "查询不到商品"),
        INCREASE_GOODS_FAILED(404, "加库存失败"),
        CHANGE_SALE_GOODS_FAILED(405, "操作失败"),

        CREATE_MEMBER_FAILED(500, "创建会员失败"),
        UPDATE_MEMBER_FAILED(501, "更新会员失败"),
        DELETE_MEMBER_FAILED(502, "删除会员失败"),
        QUERY_MEMBER_FAILED(503, "查询不到会员"),

        UPLOAD_IMAGE_FAILED(900, "文件上传失败"),
        UPLOAD_IMAGE_EMPTY(901, "文件不能为空");

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
