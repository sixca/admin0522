//웹어플리케이션에서 사용할 수 있는 폴더들을 지정
//웹에서 127.0.0.1/logs/ 이렇게 폴더에 접근하는 것임
package com.kbstar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Value("${imgdir}")
	String imgdir;
	@Value("${logdir}")
	String logdir;
	//위 두개의 디렉토리를

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uimg/**").addResourceLocations(imgdir);
		registry.addResourceHandler("/logs/**").addResourceLocations(logdir);
	}
	//웹어플리케이션에서 사용할 수 있게 다른 디렉토리를 등록
}
