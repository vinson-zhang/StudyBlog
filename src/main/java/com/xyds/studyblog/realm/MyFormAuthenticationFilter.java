package com.xyds.studyblog.realm;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;

@Component("myFormAuthenticationFilter")
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
	
	
	
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		System.out.println("hello");
		SavedRequest savedRequest =  WebUtils.getAndClearSavedRequest(request);
		WebUtils.redirectToSavedRequest(request, response, savedRequest.getRequestUrl());
		return super.onLoginSuccess(token, subject, request, response);
	}

}
