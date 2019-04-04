package com.cloud.ftl.ftltest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.cloud")
public class FtlTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FtlTestApplication.class, args);
	}

}
