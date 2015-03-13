package com.epam.cq.demo.services;

import org.apache.felix.scr.annotations.*;

import org.osgi.framework.Constants;

/**
 * Example OSGi service using SCR annotations.
 */
@Component(immediate = true, metatype = true)
@Service(GoodbyeWorldService.class)
@Properties({
    @Property(name = Constants.SERVICE_VENDOR, value = "EPAM Systems"),
    @Property(name = Constants.SERVICE_DESCRIPTION, value = "Provides a friendly farewell.")
})
public class GoodbyeWorldServiceImpl implements GoodbyeWorldService {

    @Override
    public String getMessage(String name) {
        return String.format("Goodbye %s!", name);
    }

}
