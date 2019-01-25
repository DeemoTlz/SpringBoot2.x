package com.albrus.shiro.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.albrus.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.io.Serializable;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author albrus
 * @since 2018-12-17
 */
@TableName("albrus_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否系统预定义
     */
    private Integer predefined;

    /**
     * 删除标志
     */
    @TableLogic
    private Integer deleted;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getPredefined() {
        return predefined;
    }

    public void setPredefined(Integer predefined) {
        this.predefined = predefined;
    }
    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Role{" +
        "roleName=" + roleName +
        ", description=" + description +
        ", predefined=" + predefined +
        ", deleted=" + deleted +
        "}";
    }
}
