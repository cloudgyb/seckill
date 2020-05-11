package com.gyb.seckill.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseResult {
    private int code;
    private String msg;
    private Object data;

    private ResponseResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseResult of(int code,String msg,Object data){
        return new ResponseResult(code,msg,data);
    }

    public static ResponseResult ok(){
        return of(200,"successful!",null);
    }

    public static ResponseResult ok(Object data){
        return of(200,"successful!",data);
    }
    public static ResponseResult ok(String msg ,Object data){
        return of(200,msg,data);
    }

    public static ResponseResult error(String msg){
        return of(500,msg,null);
    }
    public static ResponseResult error(Object data){
        return of(500,"failed!",data);
    }
    public static ResponseResult error(String msg, Object data){
        return of(500,msg,data);
    }
    public static ResponseResult error(Exception e){
        String msg;
        if(e.getMessage()!= null)
            msg = e.getMessage();
        else msg = e.toString();
        return of(500,msg,null);
    }
}
