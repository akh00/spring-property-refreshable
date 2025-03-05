package com.example.app;

import com.example.refreshable.config.PropertyRefreshConfig;
import com.example.service.SomeService;
import com.example.service.config.ServiceConfig;
import com.example.service.config.SomeProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAutoConfiguration
@EnableScheduling
@Import({ PropertyRefreshConfig.class, ServiceConfig.class, SomeProperties.class})
public class PropertyRefreshableApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(PropertyRefreshableApplication.class, args);
		SomeService service = context.getBean("someService", SomeService.class);
		while (true) {
			service.doSomething();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.exit(0);
			}
		}
	}

}
