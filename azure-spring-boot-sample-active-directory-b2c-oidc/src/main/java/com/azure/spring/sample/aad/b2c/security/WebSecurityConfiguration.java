// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.spring.sample.aad.b2c.security;

import com.azure.spring.sample.aad.b2c.handler.MyAuthenticationFailHandler;
import com.microsoft.azure.spring.autoconfigure.b2c.AADB2COidcLoginConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AADB2COidcLoginConfigurer configurer;

    public WebSecurityConfiguration(AADB2COidcLoginConfigurer configurer) {
        this.configurer = configurer;
    }

    private String oauthLoginUrl = "/oauth_login";

    private String errorPageUrl = "/errorPage";

    @Autowired
    private MyAuthenticationFailHandler myAuthenticationFailHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(oauthLoginUrl,errorPageUrl).permitAll()
                .anyRequest()
                .authenticated()
                .and().oauth2Login().loginPage(oauthLoginUrl).failureHandler(myAuthenticationFailHandler).and()
                .apply(configurer);
    }
}
