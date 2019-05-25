package com.huiwan.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author:mjl
 * @Description:
 * @Date:Create in 15:02 2019/5/23
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    @Test
    public void Test(){
        String name = "aaa";
        String password = "bbb";
        log.info("name: {},password: {}" , name ,password);
    }

}
