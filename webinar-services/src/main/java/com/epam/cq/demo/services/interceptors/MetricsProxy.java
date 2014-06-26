package com.epam.cq.demo.services.interceptors;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetricsProxy implements InvocationHandler, Serializable {

    public static final Logger LOGGER = LoggerFactory.getLogger(MetricsProxy.class);

    private ServiceReference serviceReference;
    private BundleContext bundleContext;

    public MetricsProxy(BundleContext bundleContext, ServiceReference serviceReference) {
        this.serviceReference = serviceReference;
        this.bundleContext = bundleContext;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LOGGER.info("Method name : " + method.getName());
        LOGGER.info("Parameters : ");
        for (Object object : args) {
            LOGGER.info(" " + object + " : ");
        }

        Object invoke = method.invoke(bundleContext.getService(serviceReference), args);
        LOGGER.info("Return : " + invoke);
        return invoke;
    }
}
