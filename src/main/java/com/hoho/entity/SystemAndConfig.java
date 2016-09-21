package com.hoho.entity;

/**
 * Created by Silence on 2016/9/12.
 */
public class SystemAndConfig {
    private long id;
    private long systemId;
    private long configId;

    public long getSystemId() {
        return systemId;
    }

    public void setSystemId(long systemId) {
        this.systemId = systemId;
    }

    public long getConfigId() {
        return configId;
    }

    public void setConfigId(long configId) {
        this.configId = configId;
    }

    public SystemAndConfig(long systemId, long configId) {
        this.systemId = systemId;
        this.configId = configId;
    }

    public SystemAndConfig() {
    }
}
