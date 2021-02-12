package com.javaee.keshe.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaee.keshe.common.CommonResult;
import com.javaee.keshe.common.StatusCode;
import com.javaee.keshe.domain.Order;
import com.javaee.keshe.domain.User;
import com.javaee.keshe.dto.ReturnOrderDTO;
import com.javaee.keshe.dto.ReturnRoomDTO;
import com.javaee.keshe.dto.ReturnUserDTO;
import com.javaee.keshe.service.OrderService;
import com.javaee.keshe.service.RoomService;
import com.javaee.keshe.service.UserService;
import com.javaee.keshe.util.WebUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author：TuoJun
 * @date：2021/01/02 21:59
 * Description：
 */
@RestController
@RequestMapping("/user")
public class OrderController {

    @Resource
    private OrderService orderService;
    @Resource
    private RoomService roomService;
    @Resource
    private UserService userService;

    @GetMapping("/historyOrder")
    public CommonResult<List<ReturnOrderDTO>> historyOrder() {
        CommonResult<List<ReturnOrderDTO>> commonResult = new CommonResult<>();
        QueryWrapper queryWrapper = new QueryWrapper();

        // User user1 = new User();
        // user1.setId(6);
        // WebUtils.getSession().setAttribute("loginUser", user1);

        User user = (User) WebUtils.getSession().getAttribute("loginUser");

        queryWrapper.eq("user_id", user.getId());
        List<Order> list = orderService.list(queryWrapper);

        List<ReturnOrderDTO> orderDTOList = new ArrayList<>();
        if (0 != list.size()) {
            for (Order order : list) {
                ReturnOrderDTO orderDTO = new ReturnOrderDTO();
                ReturnRoomDTO roomDTO = roomService.roomDetail(order.getRoomId());

                orderDTO.setOrder(order);
                orderDTO.setRoom(roomDTO);

                orderDTOList.add(orderDTO);
            }
        }

        commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
        commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());
        commonResult.setData(orderDTOList);

        return commonResult;
    }

    @PostMapping("/detailOrder")
    public CommonResult<ReturnOrderDTO> detailOrder(@RequestParam("orderId") Integer orderId) {
        CommonResult<ReturnOrderDTO> commonResult = new CommonResult<>();
        ReturnOrderDTO returnOrder = new ReturnOrderDTO();
        ReturnUserDTO userDTO = new ReturnUserDTO();

        Order order = orderService.getById(orderId);
        User user = userService.getById(order.getUserId());
        BeanUtils.copyProperties(user, userDTO);
        ReturnRoomDTO returnRoomDTO = roomService.roomDetail(order.getRoomId());

        returnOrder.setOrder(order);
        returnOrder.setUser(userDTO);
        returnOrder.setRoom(returnRoomDTO);

        commonResult.setCode(StatusCode.COMMON_SUCCESS.getCode());
        commonResult.setMessage(StatusCode.COMMON_SUCCESS.getMessage());
        commonResult.setData(returnOrder);

        return commonResult;
    }

}
