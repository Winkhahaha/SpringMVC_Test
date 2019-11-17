package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public ModelAndView hello(){
        ModelAndView mv = new ModelAndView();
        System.out.println("方法响应!");
        mv.addObject("name","aaa");
        mv.addObject("pwd","123");
        mv.setViewName("/WEB-INF/jsp/test.jsp");
        return mv;
    }

    @RequestMapping("/test")
    public String test(Model model){
        System.out.println("spring mvc");
        model.addAttribute("name","aaa");

        return "test";  ///WEB-INF/jsp/test.jsp
    }
}
