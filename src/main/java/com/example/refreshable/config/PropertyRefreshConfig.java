package com.example.refreshable.config;

import java.util.List;

import com.example.refreshable.config.env.CronPropertiesRefresher;
import com.example.refreshable.config.env.PropertiesProvider;
import com.example.refreshable.config.env.PropertySourceRefreshable;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyRefreshConfig
{
    @Bean
    public CronPropertiesRefresher cronPropertiesRefresher(List<PropertiesProvider> propertyProviders,
                                           PropertySourceRefreshable propertySourceRefreshable,
                                                           RefreshScope beanRefresher) {
        return new CronPropertiesRefresher(propertySourceRefreshable, propertyProviders, beanRefresher);
    }
}
