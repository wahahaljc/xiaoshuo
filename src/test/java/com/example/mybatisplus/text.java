package com.example.mybatisplus;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class text {

    @Test
    public void test1() {
       int[][] arr=new int[5][5];
       int sum=1;
       for (int i=0;i<arr.length;i++){
           for (int j=i,k=0;j>=0;j--){
               arr[j][k++]=sum++;
           }
       }
       for (int[] arrs:arr){
           for (int i:arrs){
               if(i!=0){
                   System.out.print(i+"\t");
               }
           }
           System.out.println();
       }
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
