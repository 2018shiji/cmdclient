package com.module.cmd;

import com.module.cmd.ping.DeviceMonitor;
import org.apache.commons.exec.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.concurrent.CountDownLatch;

@SpringBootTest
class CmdClientApplicationTests {
    @Autowired
    DeviceMonitor deviceMonitor;

    @Test
    void contextLoads() {
    }

    @Test
    void testDeviceMonitor(){
        deviceMonitor.organizePingTasks(null);

        CountDownLatch countDownLatch = new CountDownLatch(1);
        try{
            countDownLatch.await();
        }catch (InterruptedException e){e.printStackTrace();}
    }

    @Autowired
    Navigator navigator;

    @Test
    void testServerResp(){
        navigator.getServerResp();
    }

    @Test
    void testRFIDCameraResp(){
        navigator.getRFIDCameraResp();
    }

    @Test
    void testFieldBridgeResp(){
        navigator.getFieldBridgeResp();
    }

}
