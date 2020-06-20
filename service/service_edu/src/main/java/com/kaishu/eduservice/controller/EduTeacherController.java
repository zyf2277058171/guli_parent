package com.kaishu.eduservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaishu.commonutils.R;
import com.kaishu.eduservice.entity.EduTeacher;
import com.kaishu.eduservice.service.impl.EduTeacherServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    public R findAll() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("item",list);
    }

    /**
     * 删除操作，id值需要传递
     */
    @ApiOperation(value = "删除指定讲师")
    @DeleteMapping("{id}")
    public R deleteTeacher( @PathVariable String id) {  //@ApiParam(name = "id", value = "讲师id", required = true)

        boolean flag = eduTeacherService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 分页查询讲师
     * @param current 当前页
     * @param limit 每页显示的记录数
     * @return
     */
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit){
        //1、创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit); //当前页，个数

        //2、调用方法实现分页
        //调用page方法时，底层会进行封装：将分页的所有数据封装到pageTeacher对象里面
        eduTeacherService.page(pageTeacher,null);

        //3、获取数据
        long total = pageTeacher.getTotal(); //总记录数
        List<EduTeacher> records = pageTeacher.getRecords(); //数据集合

        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);
        return R.ok().data(map);

        //4、返回数据
//        return R.ok().data("total",total).data("rows",records);
    }




}













