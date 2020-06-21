package com.kaishu.servicebase.exceptionhandler;


import com.kaishu.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class) //异常只要是属于Exception异常类，就执行此方法
    @ResponseBody //为了返回数据 【这不是在controller中，因此需要加这个注解】
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("指定了全局异常处理...");

    }

}
