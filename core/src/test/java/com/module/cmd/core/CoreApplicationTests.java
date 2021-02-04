package com.module.cmd.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CoreApplicationTests {

    @Test
    void contextLoads() {
        List<String> test = new ArrayList<>();
        test.add("123");
        test.add("一二三");

        System.out.println(test.contains("123"));
    }

}
