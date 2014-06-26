package com.epam.cq.demo.services.osgi;

import static org.osgi.framework.ServiceEvent.REGISTERED;

import java.util.Properties;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceProxyManager implements ServiceListener {

    public static final Logger LOGGER = LoggerFactory.getLogger(ServiceProxyManager.class);

    private static final String IS_PROXY = "isProxy";

    @Override
    public void serviceChanged(ServiceEvent serviceEvent) {
        final ServiceReference serviceReference = serviceEvent.getServiceReference();

        if (serviceReference.getProperty(IS_PROXY) == null) {
            Bundle bundle = serviceReference.getBundle();

            if (REGISTERED == serviceEvent.getType()) {

                String[] propertyKeys = serviceReference.getPropertyKeys();
                Properties properties = buildProps(propertyKeys, serviceEvent);
                String[] interfaces = (String[]) serviceReference.getProperty("objectClass");

                Class<?>[] toClass = toClass(interfaces, bundle);

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
