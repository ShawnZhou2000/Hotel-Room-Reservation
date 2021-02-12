package com.javaee.keshe.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaee.keshe.domain.Order;
import com.javaee.keshe.mapper.OrderMapper;
import com.javaee.keshe.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author：TuoJun
 * @date：2021/01/01 12:04
 * Description：
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {


}

