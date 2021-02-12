package com.javaee.keshe.controller.admin;

import com.javaee.keshe.common.CommonResult;
import com.javaee.keshe.common.StatusCode;
import com.javaee.keshe.domain.Comment;
import com.javaee.keshe.domain.User;
import com.javaee.keshe.dto.ReturnCommentDTO;
import com.javaee.keshe.dto.ReturnUserDTO;
import com.javaee.keshe.service.CommentService;
import com.javaee.keshe.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author：TuoJun
 * @date：2021/01/07 1:23
 * Description：
 */
@RestController("adminCommentController")
@RequestMapping("/admin")
public class CommentController {

    @Resource
    private CommentService commentService;
    @Resource
    private UserService userService;

    @GetMapping("/listComment")
    public CommonResult<List<ReturnCommentDTO>> listComment() {
        CommonResult<List<ReturnCommentDTO>> commonResult = new CommonResult<>();
        List<ReturnCommentDTO> returnCommentList = new ArrayList<>();

        List<Comment> commentList = commentService.list();

        for (Comment comment : commentList) {
            ReturnCommentDTO commentDTO = new ReturnCommentDTO();
            User user = userService.getById(comment.getUserId());
            ReturnUserDTO returnUserDTO = new ReturnUserDTO();
            BeanUtils.copyProperties(user, returnUserDTO);

            commentDTO.setComment(comment);
            commentDTO.setUser(returnUserDTO);

            returnCommentList.add(commentDTO);
        }

        commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
        commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());
        commonResult.setData(returnCommentList);

        return commonResult;
    }
}
