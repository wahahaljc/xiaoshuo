package com.example.mybatisplus.entity;

import java.util.HashMap;

public class Rest extends HashMap<String, Object> {

    private String code;

    private Object Date;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getDate() {
        return Date;
    }

    public void setDate(Object date) {
        Date = date;
    }

    public Rest(String code, Object date) {
        super.put("code",code);
        super.put("date",date);
    }

    public static Rest success(Object data){
        return new Rest("200",data);
    }
}
