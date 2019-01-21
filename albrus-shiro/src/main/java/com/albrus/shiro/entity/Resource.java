package com.albrus.shiro.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.albrus.common.entity.BaseEntity;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.io.Serializable;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author albrus
 * @since 2019-01-21
 */
@TableName("albrus_resource")
public class Resource extends BaseEntity<Resource> {

    private static final long serialVersionUID = 1L;

    /**
     * 父级编号
     */
    private Integer parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 链接
     */
    private String url;

    /**
     * 目标系统
     */
    private String systemId;

    /**
     * 0:目录 1:功能 2:Action
     */
    private Integer type;

    /**
     * 图标
     */
    private String ico;

    /**
     * 是否在菜单中显示
     */
    private String isShow;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 删除标记
     */
    @TableLogic
    private Integer deleted;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }
    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Resource{" +
        "parentId=" + parentId +
        ", name=" + name +
        ", sort=" + sort +
        ", url=" + url +
        ", systemId=" + systemId +
        ", type=" + type +
        ", ico=" + ico +
        ", isShow=" + isShow +
        ", permission=" + permission +
        ", remarks=" + remarks +
        ", deleted=" + deleted +
        "}";
    }
}
