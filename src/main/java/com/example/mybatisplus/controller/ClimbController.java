package com.example.mybatisplus.controller;

import com.example.mybatisplus.service.ClimbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.util.Map;

@Controller
public class ClimbController {

    @Autowired
    private ClimbService climbService;

    @GetMapping("test2")
    public String test2() {
        return "test2";
    }

    @GetMapping("asd")
    @ResponseBody
    public void urlClimbText(HttpServletResponse response, String url,String name) {
        StringBuffer text=new StringBuffer();

        try {
            Map<String,String> map=climbService.urlClimbText(url);
            for(Map.Entry<String,String> m:map.entrySet()){
                text.append(m.getKey()+"\r\n");
                text.append(m.getValue()+"\r\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        response.setCharacterEncoding("utf-8");
        //设置响应的内容类型
        response.setContentType("text/plain");
        //设置文件的名称和格式
        response.addHeader("Content-Disposition","attachment;filename="
                + genAttachmentFileName( name, "JSON_FOR_UCC_")//设置名称格式，没有这个中文名称无法显示
                + ".txt");
        BufferedOutputStream buff = null;
        ServletOutputStream outStr = null;
        try {
            outStr = response.getOutputStream();
            buff = new BufferedOutputStream(outStr);
            buff.write(text.toString().getBytes("UTF-8"));
            buff.flush();
            buff.close();
        } catch (Exception e) {
            //LOGGER.error("导出文件文件出错:{}",e);
        } finally {try {
            buff.close();
            outStr.close();
        } catch (Exception e) {
            //LOGGER.error("关闭流对象出错 e:{}",e);
        }
        }

    }
    //防止中文文件名显示出错

    public  String genAttachmentFileName(String cnName, String defaultName) {
        try {
            cnName = new String(cnName.getBytes("gb2312"), "ISO8859-1");
        } catch (Exception e) {
            cnName = defaultName;
        }
        return cnName;
    }
}