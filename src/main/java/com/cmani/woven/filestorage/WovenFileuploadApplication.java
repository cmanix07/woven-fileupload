package com.cmani.woven.filestorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableAutoConfiguration
@SpringBootApplication
@EnableJpaRepositories
public class WovenFileuploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(WovenFileuploadApplication.class, args);
	}

}
