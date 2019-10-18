package com.ymzstudio.emba.domain;

import com.ymzstudio.emba.StartUp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StartUp.class)
class SpringCacheTest {
    @Test
    void test() {
        SpringCache springCache2 = new SpringCache(2);
        SpringCache springCache4 = new SpringCache(4);
        log.info("{}", springCache2.getRoot());
        log.info("{}", springCache2.getRoot());
        log.info("{}", springCache4.getRoot());
    }
}