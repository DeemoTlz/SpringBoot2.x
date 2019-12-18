package com.albrus.consume.entity;

import com.albrus.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author albrus
 * @since 2019-12-18
 */
@TableName("albrus_consume_type")
public class ConsumeType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 消费种类
     */
    private String name;

    /**
     * 上级ID
     */
    private Integer parentId;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 描述
     */
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ConsumeType{" +
        "name=" + name +
        ", parentId=" + parentId +
        ", level=" + level +
        ", desc=" + desc +
        "}";
    }
}
