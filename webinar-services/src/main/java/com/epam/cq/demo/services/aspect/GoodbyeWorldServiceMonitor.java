package com.epam.cq.demo.services.aspect;

import com.epam.cq.demo.services.GoodbyeWorldService;

public class GoodbyeWorldServiceMonitor implements GoodbyeWorldService {

    private volatile GoodbyeWorldService m_manageable;

    @Override
    public String getMessage(String name) {
        return m_manageable.getMessage("aspect intercept");
    }
}
