package xalead.springmvc.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyExceptionrResolver implements HandlerExceptionResolver {
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {

                e.printStackTrace();
                MyException m = null;
                if(e instanceof MyException){
                    m = (MyException) e;
                }else{
                    m = new MyException("ERROR!");
                }
                ModelAndView mv= new ModelAndView();
                mv.addObject("error",e.getMessage());
                mv.setViewName("error");    //也会被视图解析器处理
                return null;
    }
}
