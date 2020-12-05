package com.ljl.demo.ttlagentdemo.context;

import com.alibaba.ttl.TransmittableThreadLocal;

public class TraceContext {
    private static final ThreadLocal<TraceContext> LOCAL = new TransmittableThreadLocal<TraceContext>() {
        @Override
        protected TraceContext initialValue() {
            return new TraceContext();
        }
    };

    public static TraceContext getContext() {
        return LOCAL.get();
    }

    public static void removeContext() {
        LOCAL.remove();
    }

    private String traceId;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }
}
