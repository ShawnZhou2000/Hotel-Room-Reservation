package com.javaee.keshe.dto;

import com.javaee.keshe.domain.Room;
import com.javaee.keshe.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author：TuoJun
 * @date：2021/01/07 10:20
 * Description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminReturnRoomDTO {

    private Room room;
    private Type type;
}
