package com.fkq.project.model;

/**
 * Created by Administrator on 2018/12/5.
 */

public class JsonData {

    /**
     * 返回编码 0：正常返回
     */
    private int code;
    private Object data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
