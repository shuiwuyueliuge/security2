package com.security.core.validatecode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.security.core.exception.SendCodeException;

public interface ValidateCodeGenerator {

	String generateAndSend(String key, HttpServletRequest request, HttpServletResponse response) throws SendCodeException;

    boolean check(String key, String value);
    
    String getLoginUri();
    
    String getUsername();
    
    String getCodeType();
    
    boolean isUsernameOnly();
}
