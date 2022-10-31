package com.example.backend.common.log.filter;

import com.example.backend.common.configuration.common.log.model.logstatus.TraceStatus;
import com.example.backend.common.log.LogTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MDCFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(MDCFilter.class);
    private static final String NULL_STRING = "";
    private final LogTrace logTrace;

    public MDCFilter(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        TraceStatus status = logTrace.begin(NULL_STRING);
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        log.info("RequestURI: {}", servletRequest.getRequestURI());
        chain.doFilter(request, response);
        logTrace.end(status, NULL_STRING);
    }
}
