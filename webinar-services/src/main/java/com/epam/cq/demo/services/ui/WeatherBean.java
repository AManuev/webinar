package com.epam.cq.demo.services.ui;

import com.cognifide.slice.cq.qualifier.CurrentPage;
import com.cognifide.slice.mapper.annotation.JcrProperty;
import com.cognifide.slice.mapper.annotation.SliceResource;
import com.day.cq.wcm.api.Page;
import com.epam.cq.demo.services.servlet.MessageServlet;
import com.google.inject.Inject;

@SliceResource
public class WeatherBean {

    @JcrProperty
    private String title;

    @Inject
    @CurrentPage
    private Page currentPage;

    public String getTitle() {
        return title;
    }

    public String getServletUrl() {
        return MessageServlet.MAPPING_PATH;
    }

}
