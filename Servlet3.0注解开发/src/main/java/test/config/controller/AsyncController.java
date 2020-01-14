package test.config.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Callable;

@Controller
public class AsyncController {

    /**
     * 控制器返回Callable之后,mvc异步的将Callable提交到一个taskExecutor使用隔离的线程执行
     * DispatchServlet和所有的Filter退出web容器的线程,但是response保持打开状态
     * Callable返回结果,SpringMVC将请求重新派发给容器(preHandle...),恢复之前的处理
     * 根据Callable返回的结果,Springmvc将继续执行请求流程
     *
     * preHandle.../springmvc-annotation/async01
     * 		主线程开始...Thread[http-bio-8081-exec-3,5,main]==>1513932494700
     * 		主线程结束...Thread[http-bio-8081-exec-3,5,main]==>1513932494700
     * 		=========DispatcherServlet及所有的Filter退出线程============================
     *
     * 		================等待Callable执行==========
     * 		副线程开始...Thread[MvcAsync1,5,main]==>1513932494707
     * 		副线程开始...Thread[MvcAsync1,5,main]==>1513932496708
     * 		================Callable执行完成==========
     *
     * 		================再次收到之前重发过来的请求========
     * 		preHandle.../springmvc-annotation/async01
     * 		postHandle...（Callable的之前的返回值就是目标方法的返回值）
     * 		afterCompletion...
     *
     * 		异步的拦截器:
     * 			1）、原生API的AsyncListener
     * 			2）、SpringMVC：实现AsyncHandlerInterceptor；
     * @return
     */
    @ResponseBody
    @RequestMapping("/async01")
    public Callable<String> async01() {
        System.out.println("主线程开始->"+Thread.currentThread().getName());
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("副线程开始->"+Thread.currentThread().getName());
                Thread.sleep(2000);
                System.out.println("副线程开始->"+Thread.currentThread().getName());
                return "new Callable<String>()";
            }
        };
        System.out.println("主线程结束->"+Thread.currentThread().getName());
        return callable;
    }

}
