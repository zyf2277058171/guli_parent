package com.kaishu.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class R implements Serializable {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String,Object> data = new HashMap<String,Object>();

    private R(){}

    //成功静态方法：成功时调用此方法，获取一个成功的对象，然后将结果封装进此对象
    public static R ok(){
        R r = new R();

        r.setCode(ResultCode.SUCCESS);
        r.setSuccess(true);
        r.setMessage("成功");

        return r;
    }

    //失败静态方法：失败时调用此方法，获取一个失败的对象，然后将结果封装进此对象
    public static R error(){
        R r = new R();

        r.setCode(ResultCode.ERROR);
        r.setSuccess(false);
        r.setMessage("失败");

        return r;
    }

    /**
     * 这些方法，是为了在设置完属性后，依然返回调用此方法的原对象【链式编程】
     */
    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R data(String key,Object value){
        this.data.put(key,value); //设置map的值：传k-v值时，要用put方法
        return this;
    }

    /**
     * 方法的重载，既可以传入map，也可以传k-v对
     * @param datas
     * @return
     */
    public R data(HashMap<String,Object> datas){
        this.setData(datas); //设置map的值：直接传map的时候，就用set方法即可
//        this.data(datas); //错误示范，导致栈溢出。
        return this;
    }

}
