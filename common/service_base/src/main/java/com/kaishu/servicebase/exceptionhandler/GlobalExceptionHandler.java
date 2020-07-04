package com.kaishu.servicebase.exceptionhandler;


import com.kaishu.commonutils.R;
import com.kaishu.servicebase.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j  //输出异常信息到文件
public class GlobalExceptionHandler {

    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class) //异常只要是属于Exception异常类，就执行此方法
    @ResponseBody //为了返回数据 【这不是在controller中，因此需要加这个注解】
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("指定了全局异常处理...");

    }

    //特定异常处理
    @ExceptionHandler(ArithmeticException.class) //异常只要是属于ArithmeticException异常类，就执行此方法
    @ResponseBody //为了返回数据
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("指定了ArithmeticException异常处理...");

    }

    //自定义异常
    @ExceptionHandler(GuliException.class) //异常只要是属于ArithmeticException异常类，就执行此方法
    @ResponseBody //为了返回数据
    public R error(GuliException e){
        log.error(ExceptionUtil.getMessage(e));  //调用
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }






}
