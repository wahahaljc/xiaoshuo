package com.example.mybatisplus;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.*;

@SpringBootTest
public class text {

    @Test
    public void test1() {

      try {
          Class menu=Class.forName("com.example.mybatisplus.entity.Menu");
          try {
              //通过newinstance实例化
              Object obj=menu.newInstance();
              //取得setName方法，方法中有一个String类型的形参
              Method myname=menu.getDeclaredMethod("setTitle", String.class);
              //通过invoke执行obj的myname方法，并传入String实参"小明"
              myname.invoke(obj, "小明");

              //得到并执行obj的toString（）方法
              Method msg=menu.getDeclaredMethod("getTitle");
              System.out.println(msg.invoke(obj));

          } catch (Exception e) {
              e.printStackTrace();
          }
      }catch (Exception e){

      }
    }

    @Test
    public void test2() throws IOException {
        Map<String,String> a= new LinkedHashMap<>();
        a.put("1","1");
        a.put("2","2");
        for(Map.Entry<String,String> m:a.entrySet()){

        }
        System.out.println(a.toString());
    }


    @Test
    public void a() throws IOException {
        String filePath = "C:/Users/Administrator/Desktop/test.txt";
        FileInputStream fin = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        Map<String, List<Map<String, Object>>> json = new HashMap<>();
        String strTmp = "";
        int i = 0;   //计数{
        String name = "";
        String sonName = "";
        String  widname="";
        String ipName="";
        while ((strTmp = buffReader.readLine()) != null) {
            if (strTmp.equals("") || strTmp.indexOf("#")>-1) continue;
            if (strTmp.indexOf("{") > -1) i += 1;
            if (strTmp.indexOf("}") > -1) i -= 1;
            if (i == 1 && strTmp.indexOf("wideip a") > 0) {
                name = "wideip";
                widname=strTmp.substring(strTmp.indexOf(" a ")+3, strTmp.indexOf("{"));
                json.put(widname, new ArrayList<>());
            }
            if (i == 1 && strTmp.indexOf("pool a ") > 0) {
                name = "pool";
                widname=strTmp.substring(strTmp.indexOf(" a ")+3, strTmp.indexOf("{")).trim();
               for (String key:json.keySet()){
                   List<Map<String,Object>> s=json.get(key);
                   for(Map lmap:s){
                       if(lmap.get("pool").toString().equals(widname)){
                           lmap.put("server",new HashMap<>());
                       }
                   }
               }
            }
            if (i == 1 && strTmp.indexOf(" server ") > 0) {
                name = "server";
                ipName=strTmp.substring(strTmp.indexOf(" server ")+8,strTmp.indexOf("{")).trim();
            }
            if (name.equals("wideip")) {
                if (sonName.equals("pools") && strTmp.indexOf("{") > -1) {
                    List<Map<String, Object>> list=json.get(widname);
                    Map sonMap=new HashMap();
                    sonMap.put("pool",strTmp.substring(0,strTmp.indexOf("{")).trim());
                    list.add(sonMap);

                }
                if (strTmp.indexOf("pools {") > -1) {
                    sonName = "pools";
                }
            } else if (name.equals("pool")) {
                if (sonName.equals("members") && strTmp.indexOf(":") > -1 && strTmp.indexOf("{") > -1) {
                    String serName=strTmp.substring(strTmp.indexOf(":")+1,strTmp.indexOf("{")).trim();
                    for (String key:json.keySet()){
                        List<Map<String,Object>> s=json.get(key);
                        for(Map lmap:s){
                            if(lmap.get("pool").toString().equals(widname)){
                                Map<String,String> ser=new ObjectMapper().convertValue(lmap.get("server"), Map.class);
                                ser.put(serName,"");
                                lmap.put("server",ser);
                            }
                        }
                    }

                }
                if (strTmp.indexOf("members {") > -1) {
                    sonName = "members";
                }
            } else if (name.equals("server")) {
                if (sonName.equals("addresses") && strTmp.indexOf("{") > -1 && strTmp.indexOf("}") > -1) {
                    String ip=strTmp.substring(0,strTmp.indexOf("{")).trim();
                    for (String key:json.keySet()){
                        List<Map<String,Object>> s=json.get(key);
                        for(Map lmap:s){
                            if(lmap.get("pool").toString().equals(widname)){
                                Map<String,String> ser=new ObjectMapper().convertValue(lmap.get("server"), Map.class);
                                ser.put(ipName,ip);
                                lmap.put("server",ser);
                            }
                        }
                    }

                }
                if (!sonName.equals("addresses") && strTmp.indexOf("addresses {") > -1) {
                    sonName = "addresses";
                }
            }
        }
        System.out.println(new JSONObject(json));
        buffReader.close();
    }

    abstract class asd{
        private int i;

    }
    public double test(double index) {
        if (index == 0) {
            return 0;
        }
        if (index == 1) {
            return 1;
        }
        if (index == 2) {
            return 1;
        }
        return test(index - 1) + test(index - 2);
    }
}
