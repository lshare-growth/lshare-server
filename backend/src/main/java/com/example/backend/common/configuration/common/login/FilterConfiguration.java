package com.example.backend.common.configuration.common.login;

import com.example.backend.common.log.filter.MDCFilter;
import com.example.backend.common.log.model.logstatus.MDCLogTrace;
import com.example.backend.common.login.filter.CorsFilter;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FilterConfiguration implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<XssEscapeServletFilter> xssEscapeServletFilter() {
        FilterRegistrationBean<XssEscapeServletFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new XssEscapeServletFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> getFilterRegistrationBean() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CorsFilter());
        registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<MDCFilter> mdcFilterFilterRegistrationBean() {
        FilterRegistrationBean<MDCFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MDCFilter(new MDCLogTrace()));
        registrationBean.setOrder(2);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
