package com.kbstar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableScheduling  //스케쥴링 어노테이션
@SpringBootApplication
public class adminApplication {

	public static void main(String[] args) {
		SpringApplication.run(adminApplication.class, args);
	}

	@Bean
	public InternalResourceViewResolver setupViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}  // JSP Setting  어느 폴더에 존재하는지 셋팅 & resources 안에 폴더생성함
}
