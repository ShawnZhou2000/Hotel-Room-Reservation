package com.javaee.keshe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author：TuoJun
 * @date：2021/01/03 13:05
 * Description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReturnUserDTO {

    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;
    private String email;
    private String userName;
    private Integer sex;
    private String phone;
    private Integer jifen = 0;
    private Integer state = 0;
}
