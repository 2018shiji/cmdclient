package com.module.cmd.ping;

import com.module.cmd.ping.executor.PingCommand;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
class PingApplicationTests {

    @Test
    void contextLoads() {
    }

    Logger logger = LoggerFactory.getLogger(PingApplicationTests.class);
    @Test
    void log(){
        MDC.put("mock-message-id", "mock-message-id");
        logger.info("11111111222222222333333333");
        MDC.remove("mock-message-id");
    }

    @Test
    void testPingCommand(){
        new Thread(() -> {
            String cmd = "10.28.56.121";
            PingCommand.doPingCommand(cmd);
            String cmd1 = "10.28.56.122";
            PingCommand.doPingCommand(cmd1);
            String cmd2 = "10.28.56.123";
            PingCommand.doPingCommand(cmd2);
            String cmd3 = "10.28.56.124";
            PingCommand.doPingCommand(cmd3);
            String cmd4 = "10.28.56.125";
            PingCommand.doPingCommand(cmd4);
            String cmd5 = "10.28.56.126";
            PingCommand.doPingCommand(cmd5);

            String cmd6 = "10.28.56.127";
            PingCommand.doPingCommand(cmd6);
            String cmd7 = "10.28.56.128";
            PingCommand.doPingCommand(cmd7);
            String cmd8 = "10.28.56.129";
            PingCommand.doPingCommand(cmd8);
            String cmd9 = "10.28.56.130";
            PingCommand.doPingCommand(cmd9);
            String cmd10 = "10.28.56.131";
            PingCommand.doPingCommand(cmd10);
            String cmd11 = "10.28.56.132";
            PingCommand.doPingCommand(cmd11);
            String cmd12 = "10.28.56.133";
            PingCommand.doPingCommand(cmd12);
            String cmd13 = "10.28.56.134";
            PingCommand.doPingCommand(cmd13);
            String cmd14 = "10.28.56.135";
            PingCommand.doPingCommand(cmd14);
            String cmd15 = "10.28.56.136";
            PingCommand.doPingCommand(cmd15);
            String cmd16 = "10.28.56.137";
            PingCommand.doPingCommand(cmd16);
            String cmd17 = "10.28.56.138";
            PingCommand.doPingCommand(cmd17);
        }).start();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        try{
            countDownLatch.await();
        }catch (InterruptedException e){e.printStackTrace();}
    }


}
