package com.javaee.keshe.dto;

import com.javaee.keshe.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author：TuoJun
 * @date：2021/01/03 12:46
 * Description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReturnOrderDTO {

    private Order order;
    private ReturnUserDTO user;
    private ReturnRoomDTO room;
}
