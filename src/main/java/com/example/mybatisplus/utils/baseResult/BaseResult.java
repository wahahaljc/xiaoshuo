package com.example.mybatisplus.utils.baseResult;

import com.example.mybatisplus.exception.GlobalException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * 统一返回结果类
 *
 * @author oyp
 */
public class BaseResult<E> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码：0成功，其他为失败
     */
    public int code;
    /**
     * 成功为success，其他为失败原因
     */
    public String msg;
    /**
     * 数据结果集
     */
    public E data;

    public BaseResult() {
        this.code = ErrMsg.DEFAULT_SUCCESS_CODE;
        this.msg = ErrMsg.DEFAULT_SUCCESS_MESSAGE;
    }

    public BaseResult(E data) {
        this();
        this.data = data;
    }

    public BaseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResult(int code, String message, E data) {
        this(code, message);
        this.data = data;
    }

    public BaseResult(BaseResult result) {
        this.code = result.getCode();
        this.msg = result.getMsg();
        this.data = null;
    }

    public BaseResult(BaseResult result, E data) {
        this.code = result.getCode();
        this.msg = result.getMsg();
        this.data = data;
    }

    public BaseResult(ErrMsg errMsg) {
        this.code = errMsg.getCode();
        this.msg = errMsg.getMessage();
    }

    public BaseResult(ErrMsg errMsg, String message) {
        this.code = errMsg.getCode();
        this.msg = message;
    }

    public BaseResult(ErrMsg errMsg, int count) {
        if (count <= 0) {
            this.code = errMsg.getCode();
            this.msg = errMsg.getMessage();
        } else {
            this.code = ErrMsg.DEFAULT_SUCCESS_CODE;
            this.msg = ErrMsg.DEFAULT_SUCCESS_MESSAGE;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public E getData() {
        return data;
    }

    @JsonIgnore
    public E getCheckedData() {
        if (isError()) {
            throw new GlobalException("CODE"+code+"message"+msg);
        }
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return code == ErrMsg.DEFAULT_SUCCESS_CODE;
    }

    @JsonIgnore
    public boolean isError() {
        return !isSuccess();
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "code=" + code +
                ", message='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
