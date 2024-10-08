package io.github.opensabe.spring.cloud.parent.common.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.springframework.cloud.bootstrap.BootstrapApplicationListener.BOOTSTRAP_PROPERTY_SOURCE_NAME;

public abstract class OnlyOnceApplicationListener<T extends ApplicationEvent> implements ApplicationListener<T> {
    private final AtomicBoolean INITIALIZED = new AtomicBoolean(false);

    @Override
    public void onApplicationEvent(T event) {
        if (isBootstrapContext(event)) {
            return;
        }
        //由于spring-cloud的org.springframework.cloud.context.restart.RestartListener导致同一个context触发多次
        //以及 ApplicationContext 的 refresh 也会触发，虽然我们基本不用，为了保证全局只加载一次，使用这个
        synchronized (INITIALIZED) {
            if (INITIALIZED.get()) {
                return;
            }
            onlyOnce(event);
            INITIALIZED.set(true);
        }
    }

    protected boolean isBootstrapContext(T applicationEvent) {
        if (applicationEvent instanceof ApplicationContextEvent) {
            ApplicationContext applicationContext = ((ApplicationContextEvent) applicationEvent).getApplicationContext();
            if (applicationContext instanceof ConfigurableApplicationContext) {
                ConfigurableApplicationContext context = (ConfigurableApplicationContext) applicationContext;
                return context.getEnvironment().getPropertySources().contains(BOOTSTRAP_PROPERTY_SOURCE_NAME);
            }
        }
        if (applicationEvent instanceof ApplicationReadyEvent) {
            return ((ApplicationReadyEvent) applicationEvent).getApplicationContext()
                    .getEnvironment().getPropertySources().contains(BOOTSTRAP_PROPERTY_SOURCE_NAME);
        }
        return false;
    }

    protected abstract void onlyOnce(T event);
}
