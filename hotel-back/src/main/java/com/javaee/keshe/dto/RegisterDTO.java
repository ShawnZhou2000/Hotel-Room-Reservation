package com.javaee.keshe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author：TuoJun
 * @date：2021/01/01 12:19
 * Description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterDTO {

    private String email;
    private String userName;
    private String password;
    private Integer sex;
    private String phone;
}
