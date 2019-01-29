package com.albrus.shiro.password;

import com.albrus.shiro.model.MenuBO;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

public class JSONTest {

    @Test
    public void stringToJson() {
        Object parse = JSONObject.parse("[{\"parent\": \"abc\", \"children\": [\"a\", \"b\", \"c\"]}, {\"parent\": \"def\", \"children\": [\"d\", \"e\", \"f\"]}]");
        System.out.println(parse);
    }

    @Test
    public void listTest() {
        List<MenuBO> menus = Lists.newLinkedList();

        MenuBO menu = new MenuBO();
        menus.add(menu);
        menu.setName("1111111111");

        MenuBO menu2 = new MenuBO();
        menus.add(menu2);
        menu2.setName("222222222222");

        MenuBO menu3 = new MenuBO();
        menus.add(menu3);
        menu3.setName("33333333333333");

        MenuBO menu4 = new MenuBO();
        menus.add(menu4);
        menu4.setName("444444444444");

        for (MenuBO e : menus) {
            System.out.println(e.getName());
            menus.remove(e);
        }
    }
}
