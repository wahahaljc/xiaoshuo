package com.example.mybatisplus.parse;

import com.example.mybatisplus.utils.HttpRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedHashMap;

public class WebsiteTemplates extends Throwable{

    //https://www.jlxsw.com递归获得数据
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
        Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
        //采取html标签中的内容
        Elements elements = doc.select("#book");
        Elements title = elements.select(".content").select("h1"); //标题
        Elements content = elements.select(".content").select("#content"); //内容
        Elements nextchapter = elements.select(".content").select("ul").select("li").eq(2).select("a"); //下一章
        String next=nextchapter.attr("href");
        map.put(title.text(),content.text().replace("\n","\r\n"));
        System.out.println(title.text().substring(1,7)+"完成");
        if(next.indexOf(".html")>0) {
            jlxswUrl(url.substring(0,url.indexOf("com")+3)+next, map);
        }
        return map;
    }

    //https://www.luoqiuzw.com递归获得数据
    public static LinkedHashMap<String,String> luoqiuzwUrl(String url,LinkedHashMap<String,String> map) throws IOException {
        String html = null;
        try {
            html = HttpRequest.getRawHtml(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //采用Jsoup解析
        Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
        //采取html标签中的内容
        Elements elements = doc.select("#main");
        Elements title = elements.select(".bookname").select("h1"); //标题
        Elements content = elements.select("#content").select(".content_detail"); //内容
        Elements nextchapter = elements.select(".box_con").select(".bottem2").select("a").eq(3).select("a"); //下一章
        String next=nextchapter.attr("href");
        map.put(title.text(),content.html().replace("\n","\r\n"));
        System.out.println(title.text().substring(1,7)+"完成");
        if(next.indexOf(".html")>0) {
            luoqiuzwUrl("https://www.luoqiuzw.com"+next, map);
        }
        return map;
    }

    //https://www.pyp5.com递归获得数据
    public static LinkedHashMap<String,String> pyp5Url(String url,LinkedHashMap<String,String> map) throws IOException {
        String html = null;
        try {
            html = HttpRequest.getRawHtml(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //采用Jsoup解析
        Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
        //采取html标签中的内容
        Elements elements = doc.select("#wrapper");
        Elements title = elements.select(".bookname").select("h1"); //标题
        Elements content = elements.select("#content").select("p:not(:first-child)"); //内容
        Elements nextchapter = elements.select(".bottem1").select("a").eq(2).select("a"); //下一章
        String next=nextchapter.attr("href");
        map.put(title.text().substring(title.text().indexOf("第")),content.text().replace("\n","\r\n"));
        System.out.println(title.text().substring(title.text().indexOf("第"),title.text().indexOf("章")+1)+"完成");
        if(next.indexOf(".html")>0) {
            pyp5Url("https://www.pyp5.com"+next, map);
        }
        return map;
    }

}
