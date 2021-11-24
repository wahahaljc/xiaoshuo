package com.example.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.entity.NovelContent;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ljc
 * @since 2021-04-03
 */
public interface INovelContentService extends IService<NovelContent> {

    public boolean insertNovelContent(String url,Integer nid);
}
