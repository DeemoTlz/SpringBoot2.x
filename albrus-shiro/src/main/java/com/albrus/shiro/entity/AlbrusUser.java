package com.albrus.shiro.entity;

import com.albrus.common.model.BaseEntity;

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
public class AlbrusUser extends BaseEntity<AlbrusUser> {

    private static final long serialVersionUID = 1L;

    private String username;

    private String desc;

    private String password;

    private String logonIp1;

    private String logonIp2;

    private String logonIp3;

    private String logMac1;

    private String logMac2;

    private String logMac3;

    private Integer block;

    private LocalDateTime blockTime;

    private LocalDateTime beginTime;

    private LocalDateTime endTime;

    private LocalDateTime updatePwdTime;

    private Integer predefined;

    private Integer deleted;

    /**
     * 密码安全策略
     */
    private Integer pwdPolicy;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getLogonIp1() {
        return logonIp1;
    }

    public void setLogonIp1(String logonIp1) {
        this.logonIp1 = logonIp1;
    }
    public String getLogonIp2() {
        return logonIp2;
    }

    public void setLogonIp2(String logonIp2) {
        this.logonIp2 = logonIp2;
    }
    public String getLogonIp3() {
        return logonIp3;
    }

    public void setLogonIp3(String logonIp3) {
        this.logonIp3 = logonIp3;
    }
    public String getLogMac1() {
        return logMac1;
    }

    public void setLogMac1(String logMac1) {
        this.logMac1 = logMac1;
    }
    public String getLogMac2() {
        return logMac2;
    }

    public void setLogMac2(String logMac2) {
        this.logMac2 = logMac2;
    }
    public String getLogMac3() {
        return logMac3;
    }

    public void setLogMac3(String logMac3) {
        this.logMac3 = logMac3;
    }
    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }
    public LocalDateTime getBlockTime() {
        return blockTime;
    }

    public void setBlockTime(LocalDateTime blockTime) {
        this.blockTime = blockTime;
    }
    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    public LocalDateTime getUpdatePwdTime() {
        return updatePwdTime;
    }

    public void setUpdatePwdTime(LocalDateTime updatePwdTime) {
        this.updatePwdTime = updatePwdTime;
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
    public Integer getPwdPolicy() {
        return pwdPolicy;
    }

    public void setPwdPolicy(Integer pwdPolicy) {
        this.pwdPolicy = pwdPolicy;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AlbrusUser{" +
        "username=" + username +
        ", desc=" + desc +
        ", password=" + password +
        ", logonIp1=" + logonIp1 +
        ", logonIp2=" + logonIp2 +
        ", logonIp3=" + logonIp3 +
        ", logMac1=" + logMac1 +
        ", logMac2=" + logMac2 +
        ", logMac3=" + logMac3 +
        ", block=" + block +
        ", blockTime=" + blockTime +
        ", beginTime=" + beginTime +
        ", endTime=" + endTime +
        ", updatePwdTime=" + updatePwdTime +
        ", predefined=" + predefined +
        ", deleted=" + deleted +
        ", pwdPolicy=" + pwdPolicy +
        "}";
    }
}
