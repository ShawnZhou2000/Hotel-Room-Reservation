package com.javaee.keshe.controller.user;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaee.keshe.common.CommonResult;
import com.javaee.keshe.common.StatusCode;
import com.javaee.keshe.domain.User;
import com.javaee.keshe.dto.LoginDTO;
import com.javaee.keshe.dto.PasswordDTO;
import com.javaee.keshe.dto.RegisterDTO;
import com.javaee.keshe.dto.ReturnUserDTO;
import com.javaee.keshe.service.UserService;
import com.javaee.keshe.util.WebUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author：TuoJun
 * @date：2021/01/01 12:08
 * Description：
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping(value = "/register")
    public CommonResult<String> register(@RequestBody RegisterDTO registerDTO) {
        CommonResult<String> commonResult = new CommonResult<>();

        User user = new User();
        BeanUtils.copyProperties(registerDTO, user);
        user.setPassword(SecureUtil.md5(registerDTO.getPassword()));
        // System.out.println(user);

        userService.save(user);

        commonResult.setData("注册成功");
        commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
        commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());
        return commonResult;
    }

    @PostMapping(value = "/login")
    public CommonResult<String> login(@RequestBody LoginDTO loginDTO) {
        CommonResult<String> commonResult = new CommonResult<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("email", loginDTO.getEmail());
        String md5Password = SecureUtil.md5(loginDTO.getPassword());
        queryWrapper.eq("password", md5Password);
        User user = userService.getBaseMapper().selectOne(queryWrapper);

        if (null != user) {

            WebUtils.getSession().setAttribute("loginUser", user);
//            System.out.println(WebUtils.getSession().getId());

            commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
            commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());
            commonResult.setData("登录成功");
        } else {
            commonResult.setCode(StatusCode.COMMON_FAIL.getCode());
            commonResult.setMessage(StatusCode.COMMON_FAIL.getMessage());
            commonResult.setData("账号密码错误，请重试");
        }

        System.out.println(commonResult);
        return commonResult;
    }

    @GetMapping("/logout")
    public CommonResult<String> logout(){
        CommonResult<String> commonResult = new CommonResult<>();

        WebUtils.getSession().removeAttribute("loginUser");

        commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
        commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());
        commonResult.setData("登出成功!");

        return commonResult;
    }

    @GetMapping("/userDetail")
    public CommonResult<ReturnUserDTO> userDetail() {
        CommonResult<ReturnUserDTO> commonResult = new CommonResult();
        ReturnUserDTO returnUser = new ReturnUserDTO();

        // User user1 = new User();
        // user1.setId(6);
        // // user1.setCreateTime(new Date());
        // // user1.setEmail("1066261401@qq.com");
        // // user1.setUserName("水墨清尘");
        // // user1.setPassword("e10adc3949ba59abbe56e057f20f883e");
        // // user1.setSex(0);
        // // user1.setPhone("19861407837");
        // WebUtils.getSession().setAttribute("loginUser", user1);

        User user2 = (User) WebUtils.getSession().getAttribute("loginUser");
//        System.out.println(WebUtils.getSession().getId());
        User user = userService.getById(user2.getId());
//        System.out.println(user);
        BeanUtils.copyProperties(user, returnUser);

        commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
        commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());
        commonResult.setData(returnUser);

        return commonResult;
    }

    @PostMapping("/updatePassword")
    public CommonResult<String> updatePassword(@RequestBody PasswordDTO passwordDTO) {
        CommonResult<String> commonResult = new CommonResult<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        System.out.println(passwordDTO);

        // User user1 = new User();
        // user1.setId(10);
        // // user1.setCreateTime(new Date());
        // // user1.setEmail("1066261401@qq.com");
        // // user1.setUserName("水墨清尘");
        // // user1.setPassword("e10adc3949ba59abbe56e057f20f883e");
        // // user1.setSex(0);
        // // user1.setPhone("19861407837");
        // WebUtils.getSession().setAttribute("loginUser", user1);

        User user2 = (User) WebUtils.getSession().getAttribute("loginUser");
        User user = userService.getById(user2.getId());

        String md5OldPassword = SecureUtil.md5(passwordDTO.getOldPassword());

        if (!user.getPassword().equals(md5OldPassword)) {
            commonResult.setCode(StatusCode.COMMON_FAIL.getCode());
            commonResult.setMessage(StatusCode.COMMON_FAIL.getMessage());
            commonResult.setData("密码错误");

            return commonResult;
        }

        String md5NewPassword = SecureUtil.md5(passwordDTO.getNewPassword());
        user.setPassword(md5NewPassword);
        userService.updateById(user);

        commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
        commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());
        commonResult.setData("修改密码成功");

        return commonResult;
    }

}
