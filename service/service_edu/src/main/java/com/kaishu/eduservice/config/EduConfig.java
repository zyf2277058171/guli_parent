package com.kaishu.eduservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.kaishu.eduservice.mapper")
public class EduConfig {
}
