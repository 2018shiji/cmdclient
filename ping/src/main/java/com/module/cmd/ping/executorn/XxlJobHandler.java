package com.module.cmd.ping.executorn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.module.cmd.ping.pojo.PingJobDetail;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@Component
@Getter
public class XxlJobHandler {
    private PingExecutorN rfidExecutor = new PingExecutorN();
    private PingExecutorN testExecutor = new PingExecutorN();

    @XxlJob("demoHandler")
    public ReturnT<String> demoJobHandler(String param) throws Exception {
        System.out.println("Here is xxl job demo handler ");
        return ReturnT.SUCCESS;
    }

    @XxlJob("PingHandler")
    public ReturnT<String> pingJobHandler(String param) throws Exception{
        System.out.println("Here is ping job handler");
        return ReturnT.SUCCESS;
    }

//    @XxlJob("RFIDJobHandler")
    public ReturnT<String> RFIDJobHandler(String param) {
        List<PingJobDetail> pingJobDetails = JSON.parseArray(param, PingJobDetail.class);
        PingExecutorN pingExecutorN = rfidExecutor.withCountDown(new CountDownLatch(pingJobDetails.size()));
        pingJobDetails.forEach(item -> pingExecutorN.doPingTask(item));
        return ReturnT.SUCCESS;
    }

}
