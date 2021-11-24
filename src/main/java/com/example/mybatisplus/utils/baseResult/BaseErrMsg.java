package com.example.mybatisplus.utils.baseResult;

/**
 * @author oyp.
 * @date 2019-09-19
 */
public enum BaseErrMsg implements ErrMsg {
    OK(0, "ok"),

    /**
     * 前端不弹窗显示，只打印错误日志
     */
    API_WARN(2, ""),
    API_ERROR(10000, "请求失败"),
    HTTP_ERROR_400(20400, "请求参数错误"),
    HTTP_ERROR_404(20404, "请求资源不存在"),
    HTTP_ERROR_405(20405, "请求方法不支持"),
    HTTP_ERROR_500(20500, "系统出错"),
    ILLEGAL_DATA_FORMAT(30000, "数据格式非法"),
    ILLEGAL_ARGUMENT(40000, "参数错误"),
    SERVER_INTERNAL_ERROR(5000, "系统内部错误"),
    DB_INSERT_FAILURE(6001, "插入失败"),
    DB_UPDATE_FAILURE(6002, "更新失败"),
    DB_DELETE_FAILURE(6003, "删除失败"),
    API_DEPRECATED(4403, "接口已经弃用"),
    API_EC_PHOTO_UPLOAD_FAIL(20101, "上传照片失败"),
    CUSTOMER_NOT_EXIST(10404, "客户代码不存在"),
    LOGIN_EXPIRED(10405, "登录已过期！请重新刷新"),
    DATA_ALREADY_EXISTS(10406,"数据已存在"),
    NO_UPDATED_CONTENT(10407,"没有更新内容");
    private int code;
    private String message;

    BaseErrMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
