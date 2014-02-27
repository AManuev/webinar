<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/libs/foundation/global.jsp"%>


<%@ taglib prefix="slice" uri="http://cognifide.com/jsp/slice"%>
<slice:lookup var="model" type="<%=com.epam.cq.demo.services.ui.WeatherBean.class%>"/>

<%-- Try to use DATA attribute in HTML tags. Put any values and get it in JS. --%>

<div id="global-weather-ajax" class="global-weather-ajax" data-search-url='${model.servletUrl}'>

    <span>This example show how to use AJAX and JS in components.
        Example: See parameter dependency in client Library folder.</span>

    <div class="global-weather-ajax__info">
    </div>
</div><%--/.global-weather-ajax--%>



