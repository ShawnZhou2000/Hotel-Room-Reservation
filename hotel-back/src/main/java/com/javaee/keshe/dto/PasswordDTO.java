package com.javaee.keshe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author：TuoJun
 * @date：2021/01/03 17:17
 * Description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PasswordDTO {

    private String oldPassword;
    private String newPassword;
}
