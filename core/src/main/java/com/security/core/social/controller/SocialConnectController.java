package com.security.core.social.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.social.connect.Connection;
import org.springframework.web.servlet.view.AbstractView;

public class SocialConnectController extends AbstractView {

	@Override
	@SuppressWarnings("unchecked")
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, List<Connection<?>>> map = (Map<String, List<Connection<?>>>) model.get("connectionMap");
	    response.getWriter().write(map.toString());
	}
}
