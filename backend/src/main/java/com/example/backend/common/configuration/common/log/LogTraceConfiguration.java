package com.example.backend.common.configuration.common.log;

import com.example.backend.common.log.LogTrace;
import com.example.backend.common.log.model.logstatus.MDCLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfiguration {

    @Bean
    public LogTrace logTrace() {
        return new MDCLogTrace();
    }
}
