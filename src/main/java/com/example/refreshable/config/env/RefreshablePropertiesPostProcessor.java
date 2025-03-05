package com.example.refreshable.config.env;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

@Order(Ordered.LOWEST_PRECEDENCE)
public class RefreshablePropertiesPostProcessor implements EnvironmentPostProcessor
{
    private static final String REFRESHABLE_PROPERTY_SOURCE_NAME = "refreshablePropertySource";

    private static final Logger LOGGER = LoggerFactory.getLogger(RefreshablePropertiesPostProcessor.class);
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        PropertySourceRefreshable source = new PropertySourceRefreshable(REFRESHABLE_PROPERTY_SOURCE_NAME);

        String refreshFilePathStr = environment.getProperty("property-provider.file.path");
        RefreshFilePropertyProvider filePropertyProvider = new RefreshFilePropertyProvider(
                refreshFilePathStr == null ? "config/refresh.properties" : refreshFilePathStr);
        try {
            source.refresh(filePropertyProvider.getProperties());
        } catch (IOException e) {
            //resume app if could not read properties.
            LOGGER.info("Can not load properties from file", e);
        }
        environment.getPropertySources()
                .addBefore(StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME, source);

        application.addInitializers(
                context ->  context.getBeanFactory().registerSingleton("localFilePropertyProvider",
                        filePropertyProvider),
                context ->context.getBeanFactory().registerSingleton(REFRESHABLE_PROPERTY_SOURCE_NAME, source));
    }
}
