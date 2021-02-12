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
 * @date：2021/01/01 11:42
 * Description：
 */
@TableName(value = "room")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Room {

    @TableId(value = "id")
    private Integer id;
    @TableField(value = "number")
    private String number;
    @TableField(value = "type")
    private Integer type;
    @TableField(value = "state")
    private Integer state = 0;
    @TableField(value = "max_people")
    private Integer maxPeople;
    @TableField(value = "introduce")
    private String introduce;
}
