package com.albrus.shiro.password;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class JSONTest {

    @Test
    public void stringToJson() {
        Object parse = JSONObject.parse("[{\"parent\": \"abc\", \"children\": [\"a\", \"b\", \"c\"]}, {\"parent\": \"def\", \"children\": [\"d\", \"e\", \"f\"]}]");
        System.out.println(parse);
    }
}
