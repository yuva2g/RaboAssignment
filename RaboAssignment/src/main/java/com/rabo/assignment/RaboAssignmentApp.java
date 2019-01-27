package com.rabo.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.rabo.assignment.storage.StorageProperties;

@SpringBootApplication
@Configuration
@EnableConfigurationProperties(StorageProperties.class)
@EnableAutoConfiguration
public class RaboAssignmentApp {

	public static void main(String[] args) {
		SpringApplication.run(RaboAssignmentApp.class, args);
	}
}
