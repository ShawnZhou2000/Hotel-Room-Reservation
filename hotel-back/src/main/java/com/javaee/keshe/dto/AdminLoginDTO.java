package com.javaee.keshe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author：TuoJun
 * @date：2021/01/04 14:05
 * Description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminLoginDTO {

    private String userName;
    private String password;
}
