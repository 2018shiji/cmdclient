package com.module.cmd.ping.executorn;

import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteResultHandler;

import java.util.concurrent.CountDownLatch;

public class PingResultHandler implements ExecuteResultHandler {
    private CountDownLatch countDownLatch;

    public PingResultHandler(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void onProcessComplete(int exitValue) {
        countDownLatch.countDown();
    }

    @Override
    public void onProcessFailed(ExecuteException e) {
        countDownLatch.countDown();
    }

}
