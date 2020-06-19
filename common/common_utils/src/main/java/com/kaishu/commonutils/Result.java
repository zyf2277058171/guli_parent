package com.kaishu.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String,Object> data = new HashMap<String,Object>();

    private Result(){}

    //成功静态方法：成功时调用此方法，获取一个成功的对象，然后将结果封装进此对象
    public static Result ok(){
        Result result = new Result();

        result.setCode(ResultCode.SUCCESS);
        result.setSuccess(true);
        result.setMessage("成功");

        return result;
    }

    //失败静态方法：失败时调用此方法，获取一个失败的对象，然后将结果封装进此对象
    public static Result error(){
        Result result = new Result();

        result.setCode(ResultCode.ERROR);
        result.setSuccess(false);
        result.setMessage("失败");

        return result;
    }

}
