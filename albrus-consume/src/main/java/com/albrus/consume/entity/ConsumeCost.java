package com.albrus.consume.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.albrus.common.entity.BaseEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author albrus
 * @since 2018-12-07
 */
@TableName("albrus_consume_cost")
public class ConsumeCost extends BaseEntity<ConsumeCost> {

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
        "}";
    }
}
