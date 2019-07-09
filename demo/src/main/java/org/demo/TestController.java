package org.demo;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

@RestController
public class TestController {

	@Autowired(required = false)
	private ProviderSignInUtils providerSignInUtils;
	
	@GetMapping("/social/user")
	public void getSocialUserInfo(HttpServletRequest request, HttpServletResponse response) {
	    Connection<?> conn = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
	    providerSignInUtils.doPostSignUp("1234", new ServletWebRequest(request));
	    try {
	    	request.getSession().setAttribute("name", conn.getDisplayName());
			response.sendRedirect("/index.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/regist")
	public String regist(HttpServletRequest request) {
		String name = request.getParameter("name");
		String psw = request.getParameter("psw");
		Integer id = 1;
		if (id != null) {
			providerSignInUtils.doPostSignUp(id.toString(), new ServletWebRequest(request));
			return "success";
		}
	    
	    return "fail";
	}
}
