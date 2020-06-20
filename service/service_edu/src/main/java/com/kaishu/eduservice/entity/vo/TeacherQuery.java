package com.kaishu.eduservice.entity.vo;  //value objec

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TeacherQuery implements Serializable {

    //序列化id


    @ApiModelProperty(value = "讲师名称，模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔：1=高级讲师，2=首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间",example = "2020-03-03 11:11:11")
    private String begin;  //这里使用的是string类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间",example = "2020-03-03 11:11:11")
    private String end;
}
