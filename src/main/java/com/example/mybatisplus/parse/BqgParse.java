package com.example.mybatisplus.parse;

import com.example.mybatisplus.utils.HttpRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedHashMap;

public class BqgParse {

    //递归获得数据
    public static LinkedHashMap<String,String> jlxswUrl(String url,LinkedHashMap<String,String> map) throws IOException {
        String html = null;
        try {
            html = HttpRequest.getRawHtml(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //采用Jsoup解析
        Document doc = Jsoup.parse(new URL(url).openStream(), "GBK", url);
        //采取html标签中的内容
        Elements elements = doc.select("#book");
        Elements title = elements.select(".content").select("h1"); //标题
        Elements content = elements.select(".content").select("#content"); //标题
        Elements nextchapter = elements.select(".content").select("ul").select("li").eq(2).select("a"); //下一章
        String next=nextchapter.attr("href");
        map.put(title.text(),content.text());
        System.out.println(title.text().substring(1,7)+"完成");
        if(next.indexOf(".html")>0) {
            jlxswUrl("https://www.jlxsw.com"+next, map);
        }
        return map;
    }
}
