package com.kaishu.eduservice.controller;

import com.kaishu.eduservice.entity.EduTeacher;
import com.kaishu.eduservice.service.impl.EduTeacherServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 讲师信息表 前端控制器
 * </p>
 *
 * @author zhangyafei
 * @since 2020-06-19
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    //查询讲师表所有信息

    //1、注入service
    @Autowired
    private EduTeacherServiceImpl eduTeacherService; //service类中中自动注入了baseMapper，所以service也具有了mapper的功能。使用service也可以查询数据库

    //rest风格
//    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public List<EduTeacher> findAll() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return list;
    }

    /**
     * 删除操作，id值需要传递
     */
//    @ApiOperation(value = "删除指定讲师")
//    @DeleteMapping("{id}")
//    public R deleteTeacher(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable String id) {
//
//        boolean flag = eduTeacherService.removeById(id);
//        if (flag) {
//            return R.ok();
//        } else {
//            return R.error();
//        }
//    }

}

