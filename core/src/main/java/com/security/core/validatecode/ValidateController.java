package com.security.core.validatecode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.security.core.config.ValidateCodeProperties;
import com.security.core.exception.SendCodeException;

@Controller
public class ValidateController {
	
	private final Log logger = LogFactory.getLog(getClass());
	
	private static final String RESULT_VIEW = "/code/result";

	@Autowired
	private ValidateCodeGeneratorHolder holder;
	
	@Autowired(required = false)
	private ValidateCodeProperties validateCodeProperties;
	
	@RequestMapping("/code/{type}")
	public String validateCode(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable("type") String type) {
		ValidateCodeGenerator generator = holder.getGeneratorByType(type);
		logger.debug("发送验证码类型[" + type + "]");
		String key = request.getParameter(getKeyParameter());
		if (generator != null && key != null && !key.trim().equals("")) {
			try {
				model.addAttribute("sendResult", generator.generateAndSend(key, request, response));
				model.addAttribute("success", Boolean.TRUE);
			} catch (SendCodeException e) {
				model.addAttribute("success", Boolean.FALSE);
			}
		} else {
			model.addAttribute("success", Boolean.FALSE);
		}
		
		return createView();
	}
	
	private String getKeyParameter() {
		return validateCodeProperties != null ? validateCodeProperties.getKeyParameter() : "key";
	}
	
	private String createView() {
		return RESULT_VIEW;
	}
}
