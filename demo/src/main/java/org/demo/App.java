package org.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

@SpringBootApplication
public class App {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
//	@Bean
//	public SessionInformationExpiredStrategy a() {
//		return (event) -> {
//			event.getResponse().getWriter().write("12312a");
//			System.out.println("wwww");
//		};
//	}
}
