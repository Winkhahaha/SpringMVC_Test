package xalead.exception;

public class MyException extends RuntimeException{
    public MyException(){
        super("这是自己的异常!");
    }

    public MyException(String mesg){
        super(mesg);
    }
}
