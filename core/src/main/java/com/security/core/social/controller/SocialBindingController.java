package com.security.core.social.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.AbstractView;

public class SocialBindingController extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (model.get("connection") == null) {
			 response.getWriter().write("unbinding success");
		} else {
			 response.getWriter().write("binding success");
		}
	}
}
