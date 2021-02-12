package com.javaee.keshe.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author：TuoJun
 * @date：2021/01/01 11:55
 * Description：
 */
@TableName(value = "admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin {

    @TableId(value = "id")
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @TableField(value = "create_time")
    private Date createTime = new Date();
    @TableField(value = "email")
    private String email;
    @TableField(value = "admin_name")
    private String admin_name;
    @TableField(value = "password")
    private String password;
    @TableField(value = "phone")
    private String phone;
}
