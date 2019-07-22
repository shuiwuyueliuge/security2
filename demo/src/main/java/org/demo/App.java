package org.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.security.core.enable.EnableSocial;
import com.security.core.enable.EnableValidateCode;
import com.security.core.social.SocialEnum;
import com.security.core.validatecode.ValidateCodeGenerator;
import com.security.core.validatecode.simple.SimpleImgValidateCodeGenerator;

@SpringBootApplication
//@EnableValidateCode
//@EnableSocial({ SocialEnum.QQ, SocialEnum.GITHUB })
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

//	@Bean
//	public ValidateCodeGenerator g() {
//		return new A("sms");
//	}
//
//	@Bean
//	public ValidateCodeGenerator g1() {
//		return new SimpleImgValidateCodeGenerator("img3");
//	}
//
//	@Bean
//	public AuthenticationSuccessHandler handler() {
//		return (request, response, authentication) -> {
//			response.sendRedirect("http://www.baidu.com");
//		};
//	}
}
