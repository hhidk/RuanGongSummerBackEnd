package com.diamond;

import com.alibaba.fastjson.JSONObject;
import com.diamond.utils.DiyUUID;
import com.diamond.utils.FormatHandler;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DocsApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(FormatHandler.AlterTimeFormat("2020-08-19 09:30:56.870"));
    }

}
