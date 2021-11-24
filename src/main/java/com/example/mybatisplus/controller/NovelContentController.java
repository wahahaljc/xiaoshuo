package com.example.mybatisplus.controller;


import com.example.mybatisplus.service.impl.NovelContentService;
import com.example.mybatisplus.utils.baseResult.BaseErrMsg;
import com.example.mybatisplus.utils.baseResult.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ljc
 * @since 2021-04-03
 */
@Controller
@RequestMapping("/novelContent")
public class NovelContentController {

    @Autowired
    private NovelContentService novelContentService;

    @GetMapping("getNovelContent")
    @ResponseBody
    public BaseResult getNovelContent(String url,Integer nid){
        if (!novelContentService.insertNovelContent(url,nid)){
            return new BaseResult(500,"抓取成功！！！");
        }
        return new BaseResult(BaseErrMsg.DEFAULT_SUCCESS_MESSAGE);
    }

    /*
     * 下载
     * */
    @GetMapping("examinePlanDownExcel")
    @ResponseBody
    public void familysDownExcel(HttpServletResponse response) throws IOException {
       /* List<zebxt> zebxtList=new ArrayList<>();
        for(int i=1;i<136;i++){
            zebxtList.addAll(BqgParse.gettest("https://zsbxt.cqnu.edu.cn/index_zsjh.php?pg="+i,i));
        }
        ExportExcel.uploadExportExcel("应聘审核表",zebxt.class,zebxtList,response);*/
    }

}

