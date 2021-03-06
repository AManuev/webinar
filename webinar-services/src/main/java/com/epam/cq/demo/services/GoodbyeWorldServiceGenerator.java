package com.epam.cq.demo.services;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.osgi.framework.Constants;

@Component(immediate = true, metatype = true)
@Service(GoodbyeWorldServiceGenerator.class)
@Properties({ @Property(name = Constants.SERVICE_VENDOR, value = "EPAM Systems"),

@Property(name = Constants.SERVICE_DESCRIPTION, value = "Provides a string to Goodbye service.") })
public class GoodbyeWorldServiceGenerator {

    public String getMessage() {
        return "Goodbye string from provider %s!";
    }
}
