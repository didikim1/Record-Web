package com.lab603.record.web.framework.config.interceptor;

import java.util.Map.Entry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lab603.record.web.framework.beans.FrameworkBeans;
import com.lab603.record.web.framework.utils.FrameworkUtils;

public class AuthenticationInterceptor implements HandlerInterceptor
{
	String[] UN_CKECKURLS = new String[]{"/index", "/loginProc", "/error/error500"};


    @Override
    public boolean preHandle(HttpServletRequest _httpServletRequest, HttpServletResponse _httpServletResponse, Object _handler) throws Exception
    {
    	String 	strNewLine = "\r\n";

        String url = _httpServletRequest.getAttribute("org.springframework.web.servlet.HandlerMapping.pathWithinHandlerMapping").toString();
        _httpServletRequest.setAttribute("MAPPING_URL", url);

        StringBuffer sbReqMessage = new StringBuffer();


        FrameworkBeans.setHttpServletBean(_httpServletRequest, _httpServletResponse);

        sbReqMessage.append(strNewLine+"==========================LAB603=================================="+strNewLine);
        sbReqMessage.append(" Request URL=" + url + strNewLine);
        sbReqMessage.append(" Connetion IPAddr=" + _httpServletRequest.getRemoteAddr() + strNewLine);
        sbReqMessage.append(" Connetion X-Forwarded-For=" + _httpServletRequest.getHeader("x-forwarded-for") + strNewLine);
        for (Entry<Object, Object> elem : FrameworkBeans.findHttpServletBean().findClientRequestParameter().entrySet())
        {
            String mapKey = (String) elem.getKey();
            String mapVal = (String) elem.getValue();

            sbReqMessage.append("\t " + mapKey + " = " + mapVal + strNewLine);
        }

        sbReqMessage.append("==========================//LAB603==================================" + strNewLine);

        Logger.info( sbReqMessage.toString() );

        if( 	    url.indexOf("/login/") >= 0
        		|| 	url.indexOf("/login/index.do") >= 0
        		|| 	url.indexOf("/login/SelectOneData.do") >= 0
        		|| 	url.indexOf("/login/DeleteData.do") >= 0
        	)
        {
        	return true;
        }
        else
        {
        	if( FrameworkUtils.isNull( FrameworkBeans.findSessionBean().mberId ))
        	{
        		FrameworkBeans.findHttpServletBean().getHttpServletResponse().sendRedirect("/login/index.do");
        		return false;
        	}
        	else
        	{
        		return true;
        	}
        }
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {

    }

    public static final Logger Logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);
}