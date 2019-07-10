package org.demo;

import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestC {
	
	@RequestMapping("/test")
	public Object test(HttpServletRequest r) {
		//System.out.println(new AntPathRequestMatcher("/test").matches(r));
		//System.out.println(new AntPathRequestMatcher("/test/**").matches(r));
		//System.out.println(new AntPathRequestMatcher("/login/**").matches(r));
		return new Date().toLocaleString();
	}
}
