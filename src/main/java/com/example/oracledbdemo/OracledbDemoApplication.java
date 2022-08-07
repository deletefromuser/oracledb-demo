package com.example.oracledbdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.oracledbdemo.dao.mapper")
public class OracledbDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OracledbDemoApplication.class, args);
    }

}
