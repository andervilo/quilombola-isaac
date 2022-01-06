package br.org.quilombola.arquitetura.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class NotFoundInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(response.getStatus() == HttpStatus.NOT_FOUND.value() && 
				request.getMethod().equals(HttpMethod.GET.name()) &&
				!request.getRequestURI().contains("/api/")) {
			
			if(request.getContextPath().equals(null) || request.getContextPath().equals("")) {
				response.sendRedirect("/404");
			}
			response.sendRedirect(request.getContextPath()+"/404");
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
