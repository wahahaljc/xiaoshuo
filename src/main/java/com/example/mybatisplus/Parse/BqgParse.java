package com.example.mybatisplus.Parse;

import com.example.mybatisplus.entity.NovelContent;
import com.example.mybatisplus.utils.HttpRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class BqgParse {

    public static List<NovelContent> getData(String url, Integer nid) {
        //获取的数据，存放在集合中
        List<NovelContent> novelContents = new ArrayList<NovelContent>();
        String html = null;
        while (true) {
            NovelContent novelContent = new NovelContent();
            try {
                html = HttpRequest.getRawHtml(url);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //采用Jsoup解析
            Document doc = Jsoup.parse(html);
            //采取html标签中的内容
            Elements elements = doc.select("div[id=wrapper]");
            Elements title = elements.select("div.bookname").select("h1"); //标题
            Elements content = elements.select("div#content"); //内容
            Elements zjlist = elements.select("div.bottem1").select("a.back"); //章节列表
            Elements nextchapter = elements.select("div.bottem1").select("a.next"); //下一章
            if (title != null || title.equals("")) {
                for (Element element : title) {
                    novelContent.setChaptersandsections(element.text());
                }
            }

            if (content != null || content.equals("")) {
                for (Element element : content) {
                    novelContent.setContent(element.text());
                }
            }

            novelContent.setnId(nid);
            novelContents.add(novelContent);
            if (zjlist.attr("href").equals(nextchapter.attr("href"))) {
                break;
            } else {
                url = "https://www.biqugeu.net" + nextchapter.attr("href");
            }
        }
        return novelContents;
    }

    public static LinkedHashMap<String,String> gettest(String url,LinkedHashMap<String,String> map) throws IOException {
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
        System.out.println(title.text().substring(1,7));
        if(next.indexOf(".html")>0) {
            gettest("https://www.jlxsw.com"+next, map);
        }
        return map;
    }
}
