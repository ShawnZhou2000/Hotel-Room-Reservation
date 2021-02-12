package com.javaee.keshe.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javaee.keshe.domain.Room;
import com.javaee.keshe.dto.AdminReturnRoomDTO;
import com.javaee.keshe.dto.DateSectionDTO;
import com.javaee.keshe.dto.ReturnRoomDTO;

import java.util.List;

/**
 * @author：TuoJun
 * @date：2021/01/01 12:02
 * Description：
 */
public interface RoomService extends IService<Room> {

    Boolean bookRoom(Integer roomId);

    Boolean finishRoom(Integer roomId);

    List<ReturnRoomDTO> listRooms(DateSectionDTO dateSectionDTO);

    ReturnRoomDTO roomDetail(Integer roomId);

    AdminReturnRoomDTO adminRoomDetail(Integer roomId);
}
