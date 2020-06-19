package com.kaishu.eduservice.controller;

import com.kaishu.eduservice.entity.EduTeacher;
import com.kaishu.eduservice.service.impl.EduTeacherServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/eduservice/teacher")
@Api(description = "讲师管理模块")
public class EduTeacherController {

    //查询讲师表所有信息

    //1、注入service
    @Autowired
    private EduTeacherServiceImpl eduTeacherService; //service类中中自动注入了baseMapper，所以service也具有了mapper的功能。使用service也可以查询数据库

    //rest风格
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public List<EduTeacher> findAll() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return list;
    }

    /**
     * 删除操作，id值需要传递
     */
    @ApiOperation(value = "删除指定讲师")
    @DeleteMapping("{id}")
    public boolean deleteTeacher( @PathVariable String id) {  //@ApiParam(name = "id", value = "讲师id", required = true)

        boolean flag = eduTeacherService.removeById(id);
//        if (flag) {
//            return R.ok();
//        } else {
//            return R.error();
//        }
        return flag;
    }

}

