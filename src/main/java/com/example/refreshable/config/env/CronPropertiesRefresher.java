package com.example.refreshable.config.env;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.scheduling.annotation.Scheduled;

public class CronPropertiesRefresher
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CronPropertiesRefresher.class);
    private final RefreshableSource source;
    private final  List<PropertiesProvider>  providers;
    private final RefreshScope refreshScopeBean;
    public CronPropertiesRefresher(RefreshableSource source, List<PropertiesProvider> providers, RefreshScope refreshScopeBean) {
        this.source = source;
        this.providers = providers;
        this.refreshScopeBean = refreshScopeBean;
    }

    @Scheduled(fixedDelayString = "${cron.properties.refresher.delay:10000}",
            initialDelayString = "${cron.properties.refresher.initialDelay:5000}")
    public void refreshPropertiesAndBeans() {
        List<String> updatedProperties = new ArrayList<>();
        providers.forEach(provider -> {
            try {
                updatedProperties.addAll(source.refresh(provider.getProperties()));
            } catch (IOException e) {
                LOGGER.info("Can not load properties from provider {}", provider, e);
            }
        });
        if(!updatedProperties.isEmpty()) {
            refreshScopeBean.refreshAll(); //theoretically here we can find only required beans to refresh
        }

    }

}
