package com.example.mybatisplus;

import com.example.mybatisplus.Parse.BqgParse;
import com.example.mybatisplus.service.INovelContentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

@SpringBootTest
class MybatisplusApplicationTests {

    @Autowired
    private INovelContentService iNovelContentService;


    @Test
    public void test() throws IOException {
        try {
            File file = new File("C:\\Users\\ljc\\Desktop\\xiao.txt");
            file.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            LinkedHashMap<String, String> map = new LinkedHashMap<>();
            map = BqgParse.gettest("https://www.jlxsw.com/45_45764/28679076.html", map);
            for(String k:map.keySet()){
                out.write(k+"\r\n");
                out.write(map.get(k)+"\r\n");
            }
            out.flush();
            out.close();
            System.out.println("下载完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
