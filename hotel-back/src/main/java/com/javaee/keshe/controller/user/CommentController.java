package com.javaee.keshe.controller.user;

import com.javaee.keshe.common.CommonResult;
import com.javaee.keshe.common.StatusCode;
import com.javaee.keshe.domain.Comment;
import com.javaee.keshe.domain.User;
import com.javaee.keshe.dto.CommentDTO;
import com.javaee.keshe.service.CommentService;
import com.javaee.keshe.util.WebUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author：TuoJun
 * @date：2021/01/01 14:14
 * Description：
 */
@RestController
@RequestMapping("/user")
public class CommentController {

    @Resource
    private CommentService commentService;

    @PostMapping("/publishComment")
    public CommonResult<String> publishComment(@RequestBody CommentDTO commentDTO) {
        CommonResult<String> commonResult = new CommonResult<>();

        // User user1 = new User();
        // user1.setId(10);
        // user1.setCreateTime(new Date());
        // user1.setEmail("1066261401@qq.com");
        // user1.setUserName("水墨清尘");
        // user1.setPassword("e10adc3949ba59abbe56e057f20f883e");
        // user1.setSex(0);
        // user1.setPhone("19861407837");
        // user1.setState(1);
        // WebUtils.getSession().setAttribute("loginUser", user1);

        User user = (User) WebUtils.getSession().getAttribute("loginUser");

        if (1 != user.getState()) {
            commonResult.setCode(StatusCode.COMMON_FAIL.getCode());
            commonResult.setMessage(StatusCode.COMMON_FAIL.getMessage());
            commonResult.setData("评价失败，您还没在该店消费过!");
        }
        else {
            Comment comment = new Comment();
            BeanUtils.copyProperties(commentDTO, comment);
            comment.setUserId(user.getId());

            commentService.save(comment);

            commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
            commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());
            commonResult.setData("评价成功!");
        }

        return commonResult;
    }

}
