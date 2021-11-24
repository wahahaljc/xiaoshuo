package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.entity.Menu;
import com.example.mybatisplus.mapper.MenuMapper;
import com.example.mybatisplus.service.IMenuService;
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
public class MenuService extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
