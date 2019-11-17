package xalead.springmvc.web;

import xalead.springmvc.entity.Channel;
import xalead.springmvc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/addChannel")
    public String addChannel(Channel channel, Model m){
        userService.addUser(channel);
        m.addAttribute("c",channel);
        return "success";
    }

}
