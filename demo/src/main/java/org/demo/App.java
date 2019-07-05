package org.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.security.core.enable.EnableValidateCode;
import com.security.core.validatecode.ValidateCodeGenerator;
import com.security.core.validatecode.VaildateCodeFailureHandler;
import com.security.core.validatecode.ValidateCodeManager;
import com.security.core.validatecode.simple.SimpleImgValidateCodeGenerator;
import com.security.core.validatecode.simple.SimpleValidateCodeManager;
import com.security.core.validatecode.ValidateCodeTypeEnum;

@SpringBootApplication
@EnableValidateCode
public class App {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Bean
	public ValidateCodeGenerator img2() {
		return new SimpleImgValidateCodeGenerator(new SimpleValidateCodeManager(), "/login", "img3");
	}
	
	@Bean
	public VaildateCodeFailureHandler codeFailureHandler() {
		return (request, response, exception) -> {
			response.getWriter().write("Vaildate Code Failure");
		};
	}
	
	@Bean
	public ValidateCodeGenerator s() {
		return new A(new SimpleValidateCodeManager(), "/login/sms", ValidateCodeTypeEnum.SMS.getType());
	}
}
