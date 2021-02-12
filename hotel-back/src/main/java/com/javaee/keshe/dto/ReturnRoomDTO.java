package com.javaee.keshe.dto;

import com.javaee.keshe.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

/**
 * @author：TuoJun
 * @date：2021/01/01 13:52
 * Description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReturnRoomDTO {

    private Integer id;
    private String number;
    private Integer maxPeople;
    private Map<String, String> introduces;
    private Type type;
}
