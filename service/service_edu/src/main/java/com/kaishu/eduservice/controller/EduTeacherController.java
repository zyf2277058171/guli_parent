package com.kaishu.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaishu.commonutils.R;
import com.kaishu.eduservice.entity.EduTeacher;
import com.kaishu.eduservice.entity.vo.TeacherQuery;
import com.kaishu.eduservice.service.impl.EduTeacherServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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

    /**
     * rest风格
     *
     * @return
     */
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAll() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("item", list);
    }

    /**
     * 删除操作，id值需要传递
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除指定讲师")
    @DeleteMapping("{id}")
    public R deleteTeacher(@PathVariable String id) {  //@ApiParam(name = "id", value = "讲师id", required = true)

        boolean flag = eduTeacherService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 分页查询讲师
     *
     * @param current 当前页
     * @param limit   每页显示的记录数
     * @return
     */
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit) {
        //1、创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit); //当前页，个数

        //2、调用方法实现分页
        //调用page方法时，底层会进行封装：将分页的所有数据封装到pageTeacher对象里面
        eduTeacherService.page(pageTeacher, null);

        //3、获取数据
        long total = pageTeacher.getTotal(); //总记录数
        List<EduTeacher> records = pageTeacher.getRecords(); //数据集合

        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", records);
        return R.ok().data(map);

        //4、返回数据
//        return R.ok().data("total",total).data("rows",records);
    }

    /**
     * 分页+条件查询
     *
     * @param current
     * @param limit
     * @param teacherQuery
     * @return
     */
    @ApiOperation(value = "分页+条件查询")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondiiton(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery) { //条件查询，用对象做封装 【用json传递数据，把参数放入请求体中】
        //1 创建page对象
        Page<EduTeacher> page = new Page<>(current, limit);

        //2 构建wrapper
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        //mybatis：动态拼接sql 【如果条件有值，则拼接】
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (!StringUtils.isEmpty(name)) { //用spring工具类判断是否为空
            //构建条件
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin); //拼接sql的时候，用的字段名称是表中的字段
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }

        //3 调用page方法
        eduTeacherService.page(page, wrapper);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();

        return R.ok().data("total", total).data("rows", records);
    }

    /**
     * 添加讲师
     *
     * @param eduTeacher
     * @return
     */
    @PostMapping("/addTeacher")
    @ApiOperation(value = "添加讲师")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if (save = true) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 根据id查询讲师
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getTeacher/{id}", method = RequestMethod.GET)
    public R getTeacher(@PathVariable String id) {
        EduTeacher teacher = eduTeacherService.getById(id);

        int i = 10/0;

        return R.ok().data("teacher", teacher);
    }

    @RequestMapping(value = "updateTeacher", method = RequestMethod.POST)
    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }


}













