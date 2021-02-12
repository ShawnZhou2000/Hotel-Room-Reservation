package com.javaee.keshe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author：TuoJun
 * @date：2021/01/01 12:17
 * Description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginDTO {

    private String email;
    private String password;
}

