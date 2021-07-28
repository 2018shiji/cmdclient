package com.module.cmd.ping;

import com.alibaba.fastjson.JSON;
import com.module.cmd.ping.executorn.PingExecutorN;
import com.module.cmd.ping.executorn.XxlJobHandler;
import com.module.cmd.ping.pojo.PingJobDetail;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@SpringBootTest
class PingApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("9999");
    }

    Logger logger = LoggerFactory.getLogger(PingApplicationTests.class);
    @Test
    void log(){
        MDC.put("mock-message-id", "mock-message-id");
        logger.info("11111111222222222333333333");
        MDC.remove("mock-message-id");
    }

    @Autowired
    XxlJobHandler xxlJobHandler;

    @Test
    @SneakyThrows
    void testXxlPingJob(){
        List<PingJobDetail> list = Arrays.asList(
                new PingJobDetail("192.168.1.103", "home"),
                new PingJobDetail("192.168.1.104", "home"),
                new PingJobDetail("192.168.1.105", "home"));
        String pingJobsStr = JSON.toJSONString(list);
        xxlJobHandler.RFIDJobHandler(pingJobsStr);

        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await();
    }


}
