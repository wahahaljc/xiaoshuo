package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.entity.UserRole;
import com.example.mybatisplus.mapper.UserRoleMapper;
import com.example.mybatisplus.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ljc
 * @since 2021-04-03
 */
@Service
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
