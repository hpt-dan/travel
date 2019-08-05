package cn.itcast.travel.exceptions;

/**
 * 密码错误异常
 */
public class UserNameOrPasswordErrorException extends Exception {
    public UserNameOrPasswordErrorException(){

    }
    public UserNameOrPasswordErrorException(String errorMsg){
        super(errorMsg);
    }
}
