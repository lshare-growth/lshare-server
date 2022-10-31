package com.example.backend.common.log;

import com.example.backend.common.log.model.MDCTaskDecorator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfiguration implements AsyncConfigurer {

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setTaskDecorator(new MDCTaskDecorator());
        taskExecutor.setCorePoolSize(40);
        taskExecutor.setMaxPoolSize(100);
        taskExecutor.setQueueCapacity(200);
        return taskExecutor;
    }
}
