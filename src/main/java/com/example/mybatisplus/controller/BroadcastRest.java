package com.example.mybatisplus.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.mybatisplus.entity.RequestMessage;
import com.example.mybatisplus.entity.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicInteger;
@Controller
public class BroadcastRest {

    private Logger logger = LoggerFactory.getLogger(getClass());



    private AtomicInteger count = new AtomicInteger(0);



    @RequestMapping(value = "/broadcast/index")
    public String boradcastIndex(HttpServletRequest request) {

        logger.info("登录人IP:{}", request.getRemoteHost());

        return "test";

    }

    @RequestMapping(value = "/broadcast/index1")
    public String boradcastIndex1(HttpServletRequest request) {

        return "test1";

    }



    @MessageMapping("receive")

    @SendTo("/topic/getResponse")

    public ResponseMessage broadcast(RequestMessage requestMessage) {

        logger.info("接收到的消息：第{}条：", count.incrementAndGet(), JSONObject.toJSONString(requestMessage));

        ResponseMessage responseMessage = new ResponseMessage();

        responseMessage.setResponseMessage(requestMessage.getMsg());

        return responseMessage;

    }
}
