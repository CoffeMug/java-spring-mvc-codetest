package com.example.demo;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
@EnableAsync
public class JavaSpringMvcApplication implements AsyncConfigurer {
	
	/**
	 * Sample async executor with 100 threads pool size
	 */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(100);
        scheduler.initialize();
        return scheduler;
    }

	public static void main(String[] args) {
		SpringApplication.run(JavaSpringMvcApplication.class, args);
	}

}
