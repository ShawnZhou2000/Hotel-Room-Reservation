package com.javaee.keshe.controller.admin;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaee.keshe.common.CommonResult;
import com.javaee.keshe.common.StatusCode;
import com.javaee.keshe.domain.Admin;
import com.javaee.keshe.dto.AdminLoginDTO;
import com.javaee.keshe.service.AdminService;
import com.javaee.keshe.util.WebUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author：TuoJun
 * @date：2021/01/01 19:31
 * Description：
 */
@RestController("adminAdminController")
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @PostMapping(value = "/login")
    public CommonResult<String> login(@RequestBody AdminLoginDTO adminLoginDTO) {
        CommonResult<String> commonResult = new CommonResult<>();
        QueryWrapper queryWrapper = new QueryWrapper();

        queryWrapper.eq("admin_name", adminLoginDTO.getUserName());
        String md5Password = SecureUtil.md5(adminLoginDTO.getPassword());
        queryWrapper.eq("password", md5Password);
        Admin admin = adminService.getOne(queryWrapper);
        // System.out.println(admin);

        if (null != admin) {
            WebUtils.getSession().setAttribute("loginAdmin", admin);

            commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
            commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());
            commonResult.setData("登录成功");
        } else {
            commonResult.setCode(StatusCode.COMMON_FAIL.getCode());
            commonResult.setMessage(StatusCode.COMMON_FAIL.getMessage());
            commonResult.setData("账号密码错误，请重试");
        }

        return commonResult;
    }

    @GetMapping("/logout")
    public CommonResult<String> logout(){
        CommonResult<String> commonResult = new CommonResult<>();

        WebUtils.getSession().removeAttribute("loginAdmin");

        commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
        commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());
        commonResult.setData("登出成功!");

        return commonResult;
    }
}
