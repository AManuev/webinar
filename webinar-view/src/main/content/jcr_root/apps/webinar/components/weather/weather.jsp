<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/libs/foundation/global.jsp"%>

<%@ taglib prefix="slice" uri="http://cognifide.com/jsp/slice"%>
<slice:lookup var="model" type="<%=com.epam.cq.demo.services.ui.WeatherBean.class%>"/>

<cq:setContentBundle/>

<div class="global-weather">

    <span class="global-weather__header">${model.title}</span>
    <br>
    <span>
        This is example show how to use SLICE bean in JSP.
        Also you can see there CSS class name convention example and i18n.

        <fmt:message key="info.weather.Info"/>

    </span>
</div><%--/.global-weather--%>


