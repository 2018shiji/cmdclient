package com.module.cmd.ping.executorn;

import com.module.cmd.core.cmdpumpern.ICmdPumpStreamHandlerN;
import com.module.cmd.core.cmdpumpern.RealTimePumpSHandlerN;
import com.module.cmd.ping.pojo.PingJobDetail;
import com.module.cmd.ping.response.PingResponse;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class PingExecutorN {
    private DefaultExecutor executor = new DefaultExecutor();
    private LinkedBlockingDeque<PingResponse> responseList = new LinkedBlockingDeque<PingResponse>();
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public PingExecutorN withCountDown(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
        return this;
    }

    public void doPingTask(PingJobDetail pingJobDetail) {
        final CommandLine cmdLine = CommandLine.parse(pingJobDetail.getCommand());
        try {
//            ICmdPumpStreamHandlerN<PingResponse> pumpHandler = new PingPumpStreamHandlerN(pingJobDetail);
//            RealTimePumpSHandlerN pumpStreamHandler = new RealTimePumpSHandlerN(pumpHandler, responseList);
            PingPumpStreamHandler pumpStreamHandler = new PingPumpStreamHandler(pingJobDetail);
            executor.setExitValue(1);
            executor.setStreamHandler(pumpStreamHandler);
            executor.execute(cmdLine, new PingResultHandler(countDownLatch));

        } catch (Exception e) {e.printStackTrace();}

    }

    public Map<String, PingResponse> getPingResponses(){
        try {
            countDownLatch.await(8, TimeUnit.SECONDS);
        } catch (InterruptedException e){e.printStackTrace();}

        Map<String, PingResponse> responseMap = new HashMap<>();
        responseList.stream().forEach(response -> responseMap.put(response.getIpAddress(), response));

        return responseMap;

    }
}
