package com.cloud.ftl.ftlautocode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.cloud")
public class FtlAutocodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FtlAutocodeApplication.class, args);
	}

}
