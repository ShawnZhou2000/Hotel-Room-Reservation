package com.javaee.keshe.common;

/**
 * @author：TuoJun
 * @date：2021/01/01 12:10
 * Description：
 */
public interface ConstantCode {
    /**
     * 状态码
     */
    // 成功
    public static final Integer SUCCESS = 200;
    // 失败
    public static final Integer FAIL = 400;
    // 未认证（签名错误）
    public static final Integer UNAUTHORIZED = 401;
    // 接口不存在
    public static final Integer NOT_FOUND = 404;
    // 服务器内部错误
    public static final Integer INTERNAL_SERVER_ERROR = 500;
}
