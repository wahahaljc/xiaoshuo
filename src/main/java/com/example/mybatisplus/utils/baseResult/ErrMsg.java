package com.example.mybatisplus.utils.baseResult;

/**
 * 错误码
 *
 * @author oyp.
 * @date 2019-09-17
 */
public interface ErrMsg {

    /**
     * 默认成功CODE
     */
    int DEFAULT_SUCCESS_CODE = 0;

    /**
     * 默认成功MESSAGE
     */
    String DEFAULT_SUCCESS_MESSAGE = "成功";

    /**
     * 错误码
     *
     * @return 错误码
     */
    int getCode();

    /**
     * 错误信息
     *
     * @return 错误信息
     */
    String getMessage();
}
