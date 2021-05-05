package com.epam.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LocalizationFilter implements Filter {

    private static final String LOCALE_SESSION = "javax.servlet.jsp.jstl.fmt.locale.session";
    private static final String LANGUAGE = "lang";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        if (httpServletRequest.getParameter(LOCALE_SESSION) != null) {

            httpServletRequest.getSession().setAttribute(LANGUAGE,
                    httpServletRequest.getParameter(LOCALE_SESSION));
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
