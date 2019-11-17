package xaled.springmvc;

import com.sun.net.httpserver.HttpsServer;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xaled.exception.SystemException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpContext;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
//@RequestMapping("/aaa/bbb")
public class HelloCon {
    @RequestMapping("/hello")
    public String hello(Model m,Student s){
        //ModelAndView mv = new ModelAndView();
        m.addAttribute("mess","helloCon");
        m.addAttribute("stu",s);
        //mv.setViewName("/WEB-INF/view.jsp");
        //mv.setViewName("view");
        //return "forward:view";
        return "view";
    }

    @RequestMapping("/forward")
    public String forwardTest(Model m,Person p){
        m.addAttribute("per",p);
        return "forward:hello.action";//将stu转发到另一个服务器
        //间接请求转发,数据不共享
        //return "redirect:hello.action";
    }

    @RequestMapping(value = "/sessionTest",method = RequestMethod.GET)
    public String sessionTest(Model m, HttpSession session){
    if(1==1){
        throw new SystemException("异常测试");
    }
        return "exception";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("afile") MultipartFile afile, HttpServletRequest request) throws IOException {
        String srcname=afile.getOriginalFilename();//原始文件名
        String tname= UUID.randomUUID().toString()+"_"+srcname;
        String dir=request.getServletContext().getRealPath("/")+"upload";//存储的目录名
        String filePath = dir+tname;
        File f = new File(filePath);
        if(!f.exists()){
            f.mkdir();
        }
        afile.transferTo(f);
        return "upload_success";
    }

    @GetMapping("/ajax")
    public void ajax(int a, int b, HttpServletResponse response){
        try {
            response.getWriter().println(a+b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/json")
    @ResponseBody
    public void json( Student stu){
        //s.setId(666);
        //s.setName("tom");
        System.out.println(stu);
    }


}
