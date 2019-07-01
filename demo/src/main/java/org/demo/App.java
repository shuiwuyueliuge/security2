package org.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;

import com.security.core.enable.EnableValidateCode;
//import com.security.core.validatecode.VaildateCodeFailureHandler;
//import com.security.core.validatecode.ValidateCodeGenerator;
//import com.security.core.validatecode.ValidateCodeManager;
//import com.security.core.validatecode.simple.SimpleImgValidateCodeGenerator;
//import com.security.core.validatecode.simple.SimpleValidateCodeManager;

@SpringBootApplication
@EnableValidateCode
public class App {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
//	@Bean
//	public ValidateCodeManager codeManager() {
//		return new SimpleValidateCodeManager();
//	}
//	
//	@Bean
//	public ValidateCodeGenerator img2(ValidateCodeManager manager) {
//		return new SimpleImgValidateCodeGenerator(manager);
//	}
//	
//	@Bean
//	public VaildateCodeFailureHandler codeFailureHandler() {
//		return (request, response, exception) -> {
//			response.getWriter().write("Vaildate Code Failure");
//		};
//	}
}
