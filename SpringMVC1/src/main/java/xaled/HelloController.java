package xaled;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public ModelAndView hello(){
        ModelAndView mv = new ModelAndView();
        System.out.println("hello Controller");
        mv.addObject("name","aaa");
        mv.addObject("pwd","123");
        mv.setViewName("/WEB-INF/view.jsp");
        return mv;
    }
}
