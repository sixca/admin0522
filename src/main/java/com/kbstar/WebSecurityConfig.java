package com.kbstar;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//비밀번호 암호화 encoder
//다른 서버에서 접속하는 corsconfituration
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin(CorsConfiguration.ALL);
        configuration.addAllowedMethod(CorsConfiguration.ALL);
        configuration.addAllowedHeader(CorsConfiguration.ALL);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
        source.registerCorsConfiguration("/**, /ws, /chbot", configuration);  //  웹소캣 /ws, /cnbot 추가함

        http.httpBasic()
                .and().authorizeRequests()
                .anyRequest().permitAll()
                .and().cors().configurationSource(source)
                .and().csrf().disable();
    }
}
