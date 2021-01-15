package com.module.cmd.iperf;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
class IPerfApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testScreenCommand(){
//        ScreenCommand.doScreenCommand(null);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try{
            countDownLatch.await();
        }catch (InterruptedException e){e.printStackTrace();}
    }

}
