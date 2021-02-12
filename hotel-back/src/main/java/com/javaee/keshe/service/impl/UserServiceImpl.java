package com.javaee.keshe.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaee.keshe.domain.User;
import com.javaee.keshe.mapper.UserMapper;
import com.javaee.keshe.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author：TuoJun
 * @date：2021/01/01 12:04
 * Description：
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


}

