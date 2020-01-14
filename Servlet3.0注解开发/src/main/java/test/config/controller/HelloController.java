package test.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import test.config.service.HelloService;

@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return helloService.sayHello("tomcat");
    }

    @RequestMapping("/suc")
    public String success(){
        return "success";       //返回到success.jsp
    }


}
