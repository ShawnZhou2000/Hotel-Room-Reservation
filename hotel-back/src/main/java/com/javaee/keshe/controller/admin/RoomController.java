package com.javaee.keshe.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaee.keshe.common.CommonResult;
import com.javaee.keshe.common.StatusCode;
import com.javaee.keshe.domain.Room;
import com.javaee.keshe.dto.AdminReturnRoomDTO;
import com.javaee.keshe.service.OrderService;
import com.javaee.keshe.service.RoomService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author：TuoJun
 * @date：2021/01/01 13:08
 * Description：
 */
@RestController("adminRoomController")
@RequestMapping("/admin")
public class RoomController {

    @Resource
    private RoomService roomService;
    @Resource
    private OrderService orderService;

    @GetMapping("/listRooms")
    public CommonResult<List<Room>> listRooms() {
        CommonResult<List<Room>> commonResult = new CommonResult<>();

        List<Room> roomList = roomService.list();

        commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
        commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());
        commonResult.setData(roomList);

        return commonResult;
    }

    @PostMapping(value = "/roomDetail")
    public CommonResult<AdminReturnRoomDTO> roomDetail(@RequestParam("roomId") Integer roomId) {
        CommonResult<AdminReturnRoomDTO> commonResult = new CommonResult<>();

        AdminReturnRoomDTO returnRoomDTO = roomService.adminRoomDetail(roomId);

        commonResult.setData(returnRoomDTO);
        commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
        commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());
        return commonResult;
    }

    @PostMapping("/addRoom")
    public CommonResult<String> addRoom(@RequestBody Room room) {
        CommonResult<String> commonResult = new CommonResult<>();

        boolean result = roomService.save(room);

        if (result) {
            commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
            commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());
            commonResult.setData("添加房间成功");
        } else {
            commonResult.setCode(StatusCode.COMMON_FAIL.getCode());
            commonResult.setMessage(StatusCode.COMMON_FAIL.getMessage());
            commonResult.setData("添加房间失败");
        }

        return commonResult;
    }

    @PostMapping("/updateRoom")
    public CommonResult<String> updateRoom(@RequestBody Room room) {
        CommonResult<String> commonResult = new CommonResult<>();

        boolean result = roomService.updateById(room);

        if (result) {
            commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
            commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());
            commonResult.setData("修改房间信息成功");
        } else {
            commonResult.setCode(StatusCode.COMMON_FAIL.getCode());
            commonResult.setMessage(StatusCode.COMMON_FAIL.getMessage());
            commonResult.setData("修改房间信息失败");
        }

        return commonResult;
    }

    @PostMapping("/deleteRoom")
    public CommonResult<String> deleteRoom(@RequestParam("roomId") Integer roomId) {
        CommonResult<String> commonResult = new CommonResult<>();

        boolean result = roomService.removeById(roomId);

        if (result) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("room_id", roomId);
            orderService.remove(queryWrapper);

            commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
            commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());
            commonResult.setData("删除房间成功");
        } else {
            commonResult.setCode(StatusCode.COMMON_FAIL.getCode());
            commonResult.setMessage(StatusCode.COMMON_FAIL.getMessage());
            commonResult.setData("删除房间失败");
        }

        return commonResult;
    }

}
