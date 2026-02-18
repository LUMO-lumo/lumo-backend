package Lumo.lumo_backend.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean (name = "mailExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(15);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(100); // 큐가 꽉 차면 거절
        executor.setThreadNamePrefix("MailExecutor-");
        executor.setWaitForTasksToCompleteOnShutdown(true); // Graceful Shutdown set
        executor.setAwaitTerminationSeconds(5); // Graceful Shutdown set
        executor.initialize();
        return executor;
    }

    @Bean(name = "alarmTaskExecutor")
    public Executor alarmTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("alarm-async-");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(5);
        executor.initialize();
        return executor;
    }

}