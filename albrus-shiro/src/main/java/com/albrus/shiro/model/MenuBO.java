package com.albrus.shiro.model;

import com.albrus.shiro.entity.Resource;

import java.util.List;

public class MenuBO extends Resource {
    private List<MenuBO> childs;

    public List<MenuBO> getChilds() {
        return childs;
    }

    public void setChilds(List<MenuBO> childs) {
        this.childs = childs;
    }
}
