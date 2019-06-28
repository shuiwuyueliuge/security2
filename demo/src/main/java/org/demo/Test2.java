package org.demo;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

@Component("/code/result")
public class Test2 extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println(model.get("sendResult"));
		System.out.println(model.get("success"));
		if (!(boolean)model.get("success")) {
			response.getWriter().write("error");
		}
	}
}
