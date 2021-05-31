package com.epam.web.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private static final String UTF_8 = "UTF-8";
    private static final String BASIC_CONTENT_TYPE = "text/html; charset=" + UTF_8;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest.getCharacterEncoding() == null) {
            servletRequest.setCharacterEncoding(UTF_8);
        }
        servletResponse.setCharacterEncoding(UTF_8);
        servletResponse.setContentType(BASIC_CONTENT_TYPE);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
