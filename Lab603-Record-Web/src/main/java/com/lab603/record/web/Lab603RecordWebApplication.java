package com.lab603.record.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan("com.lab603.record.web")
@Configuration
@EnableAutoConfiguration
public class Lab603RecordWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lab603RecordWebApplication.class, args);
	}

}
