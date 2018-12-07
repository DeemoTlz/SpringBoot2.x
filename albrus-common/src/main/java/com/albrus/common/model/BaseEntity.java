package com.albrus.common.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体父类基类，定义一些公共的属性
 * 
 * 所有的数据模型的父类
 * @ClassName: BaseModel 
 * @Description: TODO
 * @author ouy
 * @param <T>
 * @date 2017年7月8日 下午3:26:26 
 *
 */
@SuppressWarnings({"rawtypes", "serial" })
public abstract class BaseEntity<T extends BaseEntity> extends Model implements Serializable {
	
    @TableId(type= IdType.AUTO)
	protected Long id;

	/**
	 * 创建者
	 * */
	@TableField(value = "generate_by",fill = FieldFill.INSERT)
	protected String generateBy;

	/**
	 * 创建时间
	 * */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@TableField(value = "generate_time",fill = FieldFill.INSERT)
	protected Date generateTime;

	/**
	 * 修改者
	 */
	@TableField(value = "update_by",fill = FieldFill.INSERT_UPDATE)
	protected String updateBy;

	/**
	 * 修改时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
	protected Date updateTime;

    public Long getId() {
        return id;
    }

    public String getGenerateBy() {
        return generateBy;
    }

    public Date getGenerateTime() {
        return generateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGenerateBy(String generateBy) {
        this.generateBy = generateBy;
    }

    public void setGenerateTime(Date generateTime) {
        this.generateTime = generateTime;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}