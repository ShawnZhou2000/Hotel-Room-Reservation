package com.javaee.keshe.dto;

import com.javaee.keshe.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author：TuoJun
 * @date：2021/01/07 1:25
 * Description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReturnCommentDTO {

    private ReturnUserDTO user;
    private Comment comment;
}
