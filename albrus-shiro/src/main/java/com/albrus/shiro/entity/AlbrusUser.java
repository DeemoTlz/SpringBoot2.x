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

    private String password;

    private LocalDateTime beginTime;

    private LocalDateTime endTime;

    private LocalDateTime updatePwdTime;

    private Integer predefined;

    /**
     * 密码安全策略
     */
    private Integer pwdPolicy;

    private String descript;

    private Integer deleted;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    public Integer getPwdPolicy() {
        return pwdPolicy;
    }

    public void setPwdPolicy(Integer pwdPolicy) {
        this.pwdPolicy = pwdPolicy;
    }
    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
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
        return "AlbrusUser{" +
        "username=" + username +
        ", password=" + password +
        ", beginTime=" + beginTime +
        ", endTime=" + endTime +
        ", updatePwdTime=" + updatePwdTime +
        ", predefined=" + predefined +
        ", pwdPolicy=" + pwdPolicy +
        ", descript=" + descript +
        ", deleted=" + deleted +
        "}";
    }
}
