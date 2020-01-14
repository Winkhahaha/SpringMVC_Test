package test.aop;

public class MathCalculator {       //在业务逻辑运行的时候将日志进行打印,方法之前,运行,异常

    public int div(int i,int j){
        try {
            return i/j;
        } catch (Exception e) {
            System.out.println("除数不能为0!");
        }
        return -1;
    }
}
