package com.yuequan.xiulvpai.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yuequan
 * @since 1.0
 */

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = CommonApplicationTest.class)
@SpringBootApplication
public class CommonApplicationTest {
    @Test
    public void contextLoads() {
    }

    public static void main(String[] args) {
        SpringApplication.run(CommonApplicationTest.class, args);
    }
}
