package com.richie.backstage.response;

import java.util.List;

/**
 * @author richie on 2018.06.27
 */
public class ListResult<T> {
    private int count;
    private List<T> list;
    private int status;
    private String message;

    public ListResult() {
    }

    public ListResult(int count, List<T> list, int status, String message) {
        this.count = count;
        this.list = list;
        this.status = status;
        this.message = message;
    }

    public static <T> ListResult<T> createOk(List<T> list, int count) {
        return new ListResult<>(count, list, Result.STATUS_YES, null);
    }

    public static <T> ListResult<T> createNo(String message) {
        return new ListResult<>(0, null, Result.STATUS_NO, message);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ListResult{" +
                "count=" + count +
                ", list=" + list +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
    /*
    * {
    "count": 4,
    "list": [
        {
            "catId": 6,
            "name": "油盐酱醋",
            "createdAt": 1530085165000,
            "sequence": 4
        },
        {
            "catId": 5,
            "name": "电子产品",
            "createdAt": 1530076842000,
            "sequence": 3
        },
        {
            "catId": 2,
            "name": "好吃的",
            "createdAt": 1529982950000,
            "sequence": 1
        },
        {
            "catId": 3,
            "name": "日常洗护",
            "createdAt": 1529983006000,
            "sequence": 0
        }
    ],
    "status": 1
}
    * */
}
