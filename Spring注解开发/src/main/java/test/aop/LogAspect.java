package test.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect //告诉Spring当前是一个切面类
public class LogAspect {

    //抽取公共的切入点表达式
    //1.本类引用
    //2.其他的切面引用
    @Pointcut("execution (public int test.aop.MathCalculator.div(int,int))")
    public void pointCut() {
    }


    // 在目标方法之前切入:切入点表达式(指定在哪个方法切入)
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println("除法运行..."+joinPoint.getSignature().getName()+"运行:"+ Arrays.asList(joinPoint.getArgs()));
    }

    @After("test.aop.LogAspect.pointCut()")
    public void logEnd() {
        System.out.println("除法结束...");
    }

    @AfterReturning(value="pointCut()",returning = "result")
    public void logReturn(Object result) {
        System.out.println("返回计算结果:"+result);
    }

    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void logException(Exception exception) {
        System.out.println("除法异常:"+exception);
    }
}
