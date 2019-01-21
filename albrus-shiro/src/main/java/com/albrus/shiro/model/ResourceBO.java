package com.albrus.shiro.model;

import com.albrus.shiro.entity.Resource;

public class ResourceBO extends Resource {
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
