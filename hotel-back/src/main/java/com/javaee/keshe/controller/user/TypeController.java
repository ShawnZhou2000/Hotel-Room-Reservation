package com.javaee.keshe.controller.user;

import com.javaee.keshe.common.CommonResult;
import com.javaee.keshe.common.StatusCode;
import com.javaee.keshe.domain.Type;
import com.javaee.keshe.service.TypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author：TuoJun
 * @date：2021/01/01 18:47
 * Description：
 */
@RestController
@RequestMapping("/user")
public class TypeController {

    @Resource
    private TypeService typeService;

    @GetMapping("/listTypes")
    public CommonResult<List<Type>> listTypes() {
        CommonResult<List<Type>> commonResult = new CommonResult<>();

        List<Type> list = typeService.list();

        commonResult.setData(list);
        commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
        commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());
        return commonResult;
    }
}
