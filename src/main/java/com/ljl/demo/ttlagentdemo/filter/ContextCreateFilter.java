package com.ljl.demo.ttlagentdemo.filter;

import com.ljl.demo.ttlagentdemo.context.TraceContext;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;


public class ContextCreateFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String traceId = UUID.randomUUID().toString();

        //重置traceId
        TraceContext.getContext().setTraceId(traceId);
        System.out.println("");
        System.out.println("****************************reset context******************************");
        System.out.println("");
        filterChain.doFilter(servletRequest, servletResponse);
        TraceContext.removeContext();
    }
}
