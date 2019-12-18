package com.albrus.consume.entity;

import com.albrus.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author albrus
 * @since 2019-12-18
 */
@TableName("albrus_consume_cost")
public class ConsumeCost extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 消费内容
     */
    private Integer consumeType;

    /**
     * 消费金额
     */
    private Float consumeMoney;

    /**
     * 消费时间
     */
    private LocalDateTime consumeDate;

    /**
     * 消费人
     */
    private Integer consumeUser;

    @TableLogic
    private Integer deleted;

    public Integer getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(Integer consumeType) {
        this.consumeType = consumeType;
    }
    public Float getConsumeMoney() {
        return consumeMoney;
    }

    public void setConsumeMoney(Float consumeMoney) {
        this.consumeMoney = consumeMoney;
    }
    public LocalDateTime getConsumeDate() {
        return consumeDate;
    }

    public void setConsumeDate(LocalDateTime consumeDate) {
        this.consumeDate = consumeDate;
    }
    public Integer getConsumeUser() {
        return consumeUser;
    }

    public void setConsumeUser(Integer consumeUser) {
        this.consumeUser = consumeUser;
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
        return "ConsumeCost{" +
        "consumeType=" + consumeType +
        ", consumeMoney=" + consumeMoney +
        ", consumeDate=" + consumeDate +
        ", consumeUser=" + consumeUser +
        ", deleted=" + deleted +
        "}";
    }
}
