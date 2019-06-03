package com.monkey01.springbootstart.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

//get请求的串：http://localhost:8081/springboot/getUserByGet?userName=吴老师
//post 的请求：http://localhost:8081/springboot//getUserByPost  使用postman的form data方式进行提交，userName  吴老师
//post 的请求：http://localhost:8081/springboot/getUserByJson{ "username":"吴老师"}
//@RestController注解能够使项目支持Rest
@RestController
@SpringBootApplication
//表示该controller类下所有的方法都公用的一级上下文根
@RequestMapping(value = "/springboot")
public class UserController {
    //这里使用@RequestMapping注解表示该方法对应的二级上下文路径
    @RequestMapping(value = "/getUserByGet", method = RequestMethod.GET)
    String getUserByGet(@RequestParam(value = "userName") String userName){
        return "Hello " + userName;
    }

    //通过RequestMethod.POST表示请求需要时POST方式
    @RequestMapping(value = "/getUserByPost", method = RequestMethod.POST)
    String getUserByPost(@RequestParam(value = "userName") String userName){
        return "Hello " + userName;
    }

    //在入参设置@RequestBody注解表示接收整个报文体，这里主要用在接收整个POST请求中的json报文体，
    //目前主流的请求报文也都是JSON格式了，使用该注解就能够获取整个JSON报文体作为入参，使用JSON解析工具解析后获取具体参数
    @RequestMapping(value = "/getUserByJson",method = RequestMethod.POST)
    String getUserByJson(@RequestBody String data){
        JSONObject object= null;
        try {
            object =  object.parseObject(data);
            String userName =object.getString("username");
            object.clear();
            object.put("userdata", userName+" is a man!");
        }catch (Exception e){
            e.printStackTrace();
        }
        return object.toJSONString();
    }
}
