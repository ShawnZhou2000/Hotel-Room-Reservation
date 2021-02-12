package com.javaee.keshe.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author：TuoJun
 * @date：2021/01/01 11:57
 * Description：
 */
@TableName(value = "comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {

    @TableId(value = "id")
    private Integer id;
    @TableField(value = "user_id")
    private Integer userId;
    @TableField(value = "information")
    private String information;
    @TableField(value = "type")
    private Integer type;

}
