package com.hoho.entity;

import com.hoho.util.EnvironmentEnum;
import com.hoho.util.StatusEnum;
import com.hoho.util.TypeEnum;

import java.util.Date;

/**
 * Created by Silence on 2016/9/12.
 */
public class Config {
    private long id;
    private String itemKey;
    private String itemValue;
    private TypeEnum type;
    private EnvironmentEnum environment;
    private StatusEnum status;
    private String description;
    private Date createTime;
    private String creator;
    private Date updateTime;
    private String updater;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public EnvironmentEnum getEnvironment() {
        return environment;
    }

    public void setEnvironment(EnvironmentEnum environment) {
        this.environment = environment;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Config(String itemKey, String itemValue, String type, String environment, String status, String description, Date createTime, String creator, Date updateTime, String updater) {
        this.itemKey = itemKey;
        this.itemValue = itemValue;
        this.type = TypeEnum.valueOf(type);
        this.environment = EnvironmentEnum.valueOf(environment);
        this.status = StatusEnum.valueOf(status);
        this.description = description;
        this.createTime = createTime;
        this.creator = creator;
        this.updateTime = updateTime;
        this.updater = updater;
    }

    public Config(String itemKey, String itemValue, String type, String environment, String status, String description) {
        this.itemKey = itemKey;
        this.itemValue = itemValue;
        this.type = TypeEnum.valueOf(type);
        this.environment = EnvironmentEnum.valueOf(environment);
        this.status = StatusEnum.valueOf(status);
        this.description = description;
    }

    public Config(long id, String itemKey, String itemValue, String type, String environment, String status, String description) {
        this.id = id;
        this.itemKey = itemKey;
        this.itemValue = itemValue;
        this.type = TypeEnum.valueOf(type);
        this.environment = EnvironmentEnum.valueOf(environment);
        this.status = StatusEnum.valueOf(status);
        this.description = description;
    }

    public Config() {
    }
}
