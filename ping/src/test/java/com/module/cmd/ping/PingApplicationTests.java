package com.module.cmd.ping;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.test.context.SpringBootTest;

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

}
