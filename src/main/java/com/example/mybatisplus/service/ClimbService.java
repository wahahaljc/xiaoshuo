package com.example.mybatisplus.service;

import java.util.LinkedHashMap;

public interface ClimbService {
    public LinkedHashMap<String, String> urlClimbText(String url,String urlName) throws ClassNotFoundException;
}
