package com.javaee.keshe.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaee.keshe.domain.Admin;
import com.javaee.keshe.mapper.AdminMapper;
import com.javaee.keshe.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author：TuoJun
 * @date：2021/01/01 12:04
 * Description：
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    
}

