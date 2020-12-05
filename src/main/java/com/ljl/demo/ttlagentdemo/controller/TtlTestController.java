package com.ljl.demo.ttlagentdemo.controller;

import com.alibaba.fastjson.JSON;
import com.ljl.demo.ttlagentdemo.service.TtlTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("ttl")
public class TtlTestController {

    @Autowired
    private TtlTestService ttlTestService;

    /**
     * 模拟线程复用 上下文不透传
     *
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @RequestMapping("acrossThread")
    public String acrossThread() throws ExecutionException, InterruptedException {
        return JSON.toJSONString(ttlTestService.acrossThread());
    }

    /**
     * 模拟线程复用 上下文通过api透传
     *
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @RequestMapping("ttlApi")
    public String ttlApi() throws ExecutionException, InterruptedException {
        return JSON.toJSONString(ttlTestService.ttlApi());
    }

    /**
     * 模拟线程复用 上下文通过agent透传
     *
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @RequestMapping("ttlAgent")
    public String ttlAgent() throws ExecutionException, InterruptedException {
        return JSON.toJSONString(ttlTestService.ttlAgent());
    }
}
