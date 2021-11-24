package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplus.Parse.BqgParse;
import com.example.mybatisplus.entity.NovelContent;
import com.example.mybatisplus.mapper.NovelContentMapper;
import com.example.mybatisplus.service.INovelContentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ljc
 * @since 2021-04-03
 */
@Service
public class NovelContentService extends ServiceImpl<NovelContentMapper, NovelContent> implements INovelContentService {

    public boolean insertNovelContent(String url,Integer nid) {

        //输入爬取的总页数
//        int sumpagenumber = number;


        List<NovelContent> dataslist = BqgParse.getData(url, nid);

        //循环输出抓取的数据

        return this.saveBatch(dataslist);
    }
}
