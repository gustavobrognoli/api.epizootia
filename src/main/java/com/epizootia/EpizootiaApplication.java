package com.epizootia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class EpizootiaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EpizootiaApplication.class, args);
	}
}
