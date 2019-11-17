package xaled.exception;

public class SystemException extends RuntimeException {
    public SystemException(){
        super("本系统自己的异常！");
    }

    public SystemException(String mesg){
        super(mesg);
    }
}
