package com.epam.cq.demo.services.interceptors;

import static org.osgi.framework.ServiceEvent.REGISTERED;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Properties;

import org.osgi.framework.*;
import org.osgi.framework.hooks.service.EventHook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetricsHooks implements EventHook {

    public static final Logger LOGGER = LoggerFactory.getLogger(MetricsHooks.class);

    private static final String IS_PROXY = "isProxy";
    private BundleContext bundleContext;

    public MetricsHooks(BundleContext bundleContext) {
        this.bundleContext = bundleContext;
    }

    @Override
    public void event(ServiceEvent serviceEvent, Collection collection) {
        final ServiceReference serviceReference = serviceEvent.getServiceReference();

        if (serviceReference.getProperty(IS_PROXY) == null) {
            Bundle bundle = serviceReference.getBundle();

            if (REGISTERED == serviceEvent.getType()) {

                String[] propertyKeys = serviceReference.getPropertyKeys();
                Properties properties = buildProps(propertyKeys, serviceEvent);
                String[] interfaces = (String[]) serviceReference.getProperty("objectClass");

                Class<?>[] toClass = toClass(interfaces, bundle);
                proxyService(bundle, toClass, properties, this.getClass().getClassLoader(), new MetricsProxy(
                        bundleContext, serviceReference));
            }
        }
    }

    private Properties buildProps(String[] propertyKeys, ServiceEvent event) {
        Properties properties = new Properties();
        for (String string : propertyKeys) {
            properties.put(string, event.getServiceReference().getProperty(string));
        }
        return properties;
    }

    private static ServiceRegistration proxyService(Bundle bundleSource, Class<?>[] interfaces, Properties prop,
            ClassLoader cl, InvocationHandler proxy) {

        prop.put(IS_PROXY, true);
        Object loggerProxy = Proxy.newProxyInstance(cl, interfaces, proxy);

        LOGGER.info("Register proxy under interfaces: ");
        for (Class<?> anInterface : interfaces) {
            LOGGER.info(" -> {}", anInterface.getName());
        }

        return bundleSource.getBundleContext().registerService(toString(interfaces), loggerProxy, prop);

    }

    private static String[] toString(Class<?>[] interfaces) {
        String[] names = new String[interfaces.length];
        int i = 0;
        for (Class clazz : interfaces) {
            names[i++] = clazz.getName();
        }
        return names;
    }

    private static Class<?>[] toClass(String[] interfaces, Bundle bl) {
        Class<?>[] names = new Class<?>[interfaces.length];
        int i = 0;
        for (String clazz : interfaces) {
            try {
                names[i++] = bl.loadClass(clazz);
            } catch (ClassNotFoundException ex) {
                LOGGER.error("Class not found: ", ex);
            }
        }
        return names;
    }

}
