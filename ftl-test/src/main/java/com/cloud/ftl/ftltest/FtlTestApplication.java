package com.cloud.ftl.ftltest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.cloud")
@MapperScan("com.cloud.**.dao")
public class FtlTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FtlTestApplication.class, args);
	}

}
