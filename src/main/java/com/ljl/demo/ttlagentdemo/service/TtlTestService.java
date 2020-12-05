package com.ljl.demo.ttlagentdemo.service;

import com.alibaba.ttl.threadpool.TtlExecutors;
import com.ljl.demo.ttlagentdemo.context.TraceContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@Service
public class TtlTestService {

    private ExecutorService executorService = Executors.newFixedThreadPool(1);
    private ExecutorService ttlExecutorService = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(1));

    /**
     * 模拟线程复用 上下文不透传
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Map<String, String> acrossThread() throws ExecutionException, InterruptedException {
        Map<String,String> result=new HashMap<>(2);
        System.out.println("");
        System.out.println("============================================================main acrossThread:" + TraceContext.getContext().getTraceId());
        result.put("main acrossThread",TraceContext.getContext().getTraceId());
        //System.out.println(Thread.currentThread().getName());

        Future<String> childTraceIdFuture = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("============================================================child acrossThread:" + TraceContext.getContext().getTraceId());
                return TraceContext.getContext().getTraceId();
                //System.out.println(Thread.currentThread().getName());
            }
        });
        result.put("child acrossThread",childTraceIdFuture.get());
        return result;
    }

    /**
     * 模拟线程复用 上下文通过api透传
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Map<String, String> ttlApi() throws ExecutionException, InterruptedException {
        Map<String,String> result=new HashMap<>(2);
        System.out.println("");
        System.out.println("============================================================main ttlApi:" + TraceContext.getContext().getTraceId());
        result.put("main ttlApi",TraceContext.getContext().getTraceId());
        //System.out.println(Thread.currentThread().getName());

        Future<String> childTraceIdFuture = ttlExecutorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("============================================================child ttlApi:" + TraceContext.getContext().getTraceId());
                return TraceContext.getContext().getTraceId();
                //System.out.println(Thread.currentThread().getName());
            }
        });
        result.put("child ttlApi",childTraceIdFuture.get());
        return result;
    }

    /**
     * 模拟线程复用 上下文通过agent透传
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Map<String, String> ttlAgent() throws ExecutionException, InterruptedException {
        Map<String,String> result=new HashMap<>(2);
        System.out.println("");
        System.out.println("============================================================main ttlAgent:" + TraceContext.getContext().getTraceId());
        result.put("main ttlAgent",TraceContext.getContext().getTraceId());
        //System.out.println(Thread.currentThread().getName());

        Future<String> childTraceIdFuture = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("============================================================child ttlAgent:" + TraceContext.getContext().getTraceId());
                return TraceContext.getContext().getTraceId();
                //System.out.println(Thread.currentThread().getName());
            }
        });
        result.put("child ttlAgent",childTraceIdFuture.get());
        return result;
    }
}
