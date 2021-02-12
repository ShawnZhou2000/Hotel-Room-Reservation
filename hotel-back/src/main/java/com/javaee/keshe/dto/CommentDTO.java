package com.javaee.keshe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author：TuoJun
 * @date：2021/01/01 15:33
 * Description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentDTO {

    private String information;
    private Integer type;
}
