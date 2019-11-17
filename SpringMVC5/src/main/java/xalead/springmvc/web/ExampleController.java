package xalead.springmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xalead.springmvc.entity.Channel;
import xalead.springmvc.entity.JSONObj;
import xalead.springmvc.entity.User;
import xalead.springmvc.exception.MyException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@Controller
//@RequestMapping("/aaa")
//@RestController
public class ExampleController {

    @RequestMapping("/test1")
    //可以在地址栏 ?参数&参数,也可以使用form.jsp传参
    public String testServletApi(String name, HttpServletRequest request){
        String password = request.getParameter("password");
        System.out.println(name+":"+password);
        request.setAttribute("name",name);
        request.setAttribute("password",password);
        return "test";      //和视图解析器的前后缀拼接
    }

    @RequestMapping("/test2")
    public String testModelMap(String name, String password,ModelMap map){
        System.out.println(name+":"+password);
        map.put("name",name);
        map.put("password",password);
        return "test";
    }

    @RequestMapping("/test3")
    public String testRequestParam(@RequestParam(name = "name") String n, @RequestParam(name="password") String p, ModelMap map){
        System.out.println(n + ":" + p);
        map.put("name",n);
        map.put("password",p);
        return "test";
    }

    @RequestMapping("/test4")
    public String testTypeConvert(String name, Date cur){
        System.out.println(name + ":" + cur);
        return "test";
    }

    @RequestMapping("/test5")
    public String testTime(Channel c, Model m){
        //   @DateTimeFormat(pattern = "yyyy年MM月dd日"):注解将指定的字符串格式转为日期格式
        //   private Date date;
        System.out.println(c.getCid()+":"+c.getCname()+":"+c.getDate());
        m.addAttribute("c",c);
        return "test";
    }


    @RequestMapping("/test7")
    public String testTimeConvert(Date d){
        //  使用时间格式转化器StringToDateConverter将字符串转为日期
        System.out.println(d);
        return "test";
    }

    @RequestMapping("/test8")
    public String arrayConvert(String[] args,HttpServletRequest request){
        System.out.println(Arrays.asList(request.getParameter("args")));
        System.out.println(Arrays.asList(args));
        return "test";
    }

    @RequestMapping("/test9")
    public String arrayConvert2(Integer[] args){    //会自动做类型转换String-->Integer
        System.out.println(Arrays.asList(args));
        return "test";
    }

    @RequestMapping("/test10")
    public String arrayConvert3(@RequestParam("channels") ArrayList<Channel> channels){
        //  将接收的字符串经过转换器转换为对象
        System.out.println(channels);
        return "test";
    }

    @RequestMapping("/test11")
    public String arrayConvert4(User user){
        System.out.println(user);
        return "test";
    }

    @GetMapping("/test12")
    public String test3(String name,int age){
        System.out.println(name+":"+age);
        return "test";
    }

    @PostMapping("/test13")
    public String test4(String name,int age){
        System.out.println(name+":"+age);
        return "test";

    }

    @PostMapping("/test14")
    public String test4(String name,String password,Model model,HttpServletRequest request){
        System.out.println(name+":"+password);
        model.addAttribute("name",name);
        //model.addAttribute("password", password);
        request.getSession().setAttribute("password",password);
        return "redirect:test15.action";    //会跟窄化请求绑定:aaa/bbb/test15.action
    }

    @GetMapping("/test15")
    public void testRedirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // redirect转发后model会消失,可以使用session存储
        //  forward转发model不会消失
        request.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(request,response);
    }

    @PostMapping("/test16")
    public String test5(String name,String password,Model model,HttpServletRequest request){
        System.out.println(name+":"+password);
        model.addAttribute("name",name);
        model.addAttribute("password", password);
        //request.getSession().setAttribute("password",password);
        /*
        forward转发时,post必须对应post(必须跟当前request请求方法一致)
         */
        return "forward:test17.action";    //会跟窄化请求绑定:aaa/bbb/test15.action
    }

    @PostMapping("/test17")
    public void testForward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // redirect转发后model会消失,可以使用session存储
        //  forward转发model不会消失
        request.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(request,response);
    }

    @PostMapping("/test18")
    public String test6(String name,String password,Model model){
        if(1==1){
            throw new MyException("异常测试!");
        }
        System.out.println(name+":"+password);
        model.addAttribute("name",name);
        model.addAttribute("password", password);
        //request.getSession().setAttribute("password",password);
        /*
        forward转发时,post必须对应post(必须跟当前request请求方法一致)
         */
        return "forward:test17.action";    //会跟窄化请求绑定:aaa/bbb/test15.action
    }

    @RequestMapping("/upload")
    public String upload(MultipartFile photo,HttpServletRequest request) throws IOException {
            //取出文件原名
        String fileName=photo.getOriginalFilename();
        System.out.println("原文件名:"+fileName);
            //起新名字
        String newFileName = UUID.randomUUID()+fileName.substring(fileName.lastIndexOf("."));   //截取后缀
        System.out.println("新文件名:"+newFileName);
            //上传文件,绝对路径
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        System.out.println("文件当前的绝对路径:"+rootPath);
        //创建上传文件要保存的完整路径
        String fullPath = rootPath+"upload/"+newFileName;
        System.out.println("文件要保存到的完整路径:"+fullPath);
        File file = new File(fullPath);
        //递归建目录
        if(!file.exists()){
            file.mkdirs();
        }
        //向磁盘写这个文件
        photo.transferTo(file);

        return "success";   // //转换到success.jsp,事先已配好视图解析器
    }

    @RequestMapping("/test20")
    @ResponseBody
    public JSONObj test20(){
       JSONObj jsonObj = new JSONObj();
       jsonObj.setAge(1);
       jsonObj.setName("aaa");
       jsonObj.setSex("男");
       //   后台在返回json数据的时候，用自身的序列化机制会把时间变成一段很长的数字
       //   必须在json的实体类中的Date上面使用@JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss",timezone = "GMT+8")
        //  才能得到标准的日期格式
       jsonObj.setDate(new Date());
        return jsonObj;
    }

    // http://localhost:8080/test21/zhangsan/22
    @RequestMapping("/test21/{name}/{age}")
    @ResponseBody
    public JSONObj test21(@PathVariable("name") String name,@PathVariable("age") int age){
        JSONObj jsonObj = new JSONObj();
        jsonObj.setAge(age);
        jsonObj.setName(name);
        jsonObj.setSex("男");
        //   后台在返回json数据的时候，用自身的序列化机制会把时间变成一段很长的数字
        //   必须在json的实体类中的Date上面使用@JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss",timezone = "GMT+8")
        //  才能得到标准的日期格式
        jsonObj.setDate(new Date());
        return jsonObj;
    }
}
