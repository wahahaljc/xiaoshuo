package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.service.ClimbService;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;

@Service
public class ClimbServiceImpl implements ClimbService {

    public LinkedHashMap<String, String> urlClimbText(String url,String urlName) throws ClassNotFoundException {
        Class menu = Class.forName("com.example.mybatisplus.parse.WebsiteTemplates");
        LinkedHashMap<String, String> result_4 = null;
        try {
            //通过newinstance实例化
            Object obj = menu.newInstance();
            Class[] cArg = new Class[2];
            cArg[0] = String.class;
            cArg[1] = LinkedHashMap.class;
            //取得setName方法，方法中有一个String类型的形参
            Method myname = menu.getDeclaredMethod(urlName, cArg);
            //通过invoke执行obj的myname方法，并传入String实参"小明"
  /*          myname.invoke(obj, url);*/

            //得到并执行obj的toString（）方法
            //Method msg = menu.getDeclaredMethod("getTitle");

            Object[] args = new Object[]{url, new LinkedHashMap<>()};
            //有返回值
            result_4 = (LinkedHashMap<String, String>) myname.invoke(obj, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result_4;
    }
}
