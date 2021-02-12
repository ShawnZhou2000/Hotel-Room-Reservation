package com.javaee.keshe.component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaee.keshe.domain.Order;
import com.javaee.keshe.service.OrderService;
import com.javaee.keshe.service.RoomService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author：TuoJun
 * @date：2021/01/04 0:25
 * Description：
 */
@Component
public class TimedTask {

    @Resource
    private OrderService orderService;
    @Resource
    private RoomService roomService;

    // @Scheduled(cron = "*/5 * * * * ?")
    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduled() {
        // System.out.println("执行定时任务");
        QueryWrapper queryWrapper = new QueryWrapper();
        Date nowDate = new Date();
        queryWrapper.lt("leave_time", nowDate);
        queryWrapper.eq("flag", 1);

        try{
            List<Order> orderList = orderService.list(queryWrapper);
            if (0 != orderList.size()) {
                for (Order order : orderList) {
                    order.setFlag(3);
                    roomService.finishRoom(order.getRoomId());
                }
                orderService.updateBatchById(orderList);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
