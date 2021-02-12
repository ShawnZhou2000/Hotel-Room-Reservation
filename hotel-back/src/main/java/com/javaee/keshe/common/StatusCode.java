package com.javaee.keshe.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author：TuoJun
 * @date：2021/01/01 12:10
 * Description：
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum StatusCode {

    COMMON_SUCCESS(ConstantCode.SUCCESS, "成功。"),
    COMMON_FAIL(ConstantCode.FAIL, "失败")
    ;


    private Integer code;
    private String message;
}
