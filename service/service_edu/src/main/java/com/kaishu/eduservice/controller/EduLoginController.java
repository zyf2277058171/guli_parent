package com.kaishu.eduservice.controller;

import com.kaishu.commonutils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {

    //login
    @PostMapping("login")
    public R login(){

        return R.ok().data("token","admin");
    }

    //info
    public R info(){

        return R.ok().data("roles","admin");
    }


}
