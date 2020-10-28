package com.azure.spring.sample.aad.b2c.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(exception instanceof OAuth2AuthenticationException){
            OAuth2AuthenticationException auth2AuthenticationException = (OAuth2AuthenticationException)exception;
            super.setDefaultFailureUrl("/errorPage?errorMsg=" + auth2AuthenticationException.getError().getErrorCode());
        }
        super.onAuthenticationFailure(request, response, exception);
    }
}
