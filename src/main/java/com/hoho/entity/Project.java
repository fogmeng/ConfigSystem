package com.hoho.entity;

import com.hoho.util.StatusEnum;

/**
 * Created by Silence on 2016/9/19.
 */
public class Project {
    private long id;
    private String nameEn;
    private String name;
    private String description;
    private StatusEnum status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Project(String nameEn, String name, String description, String status) {
        this.nameEn = nameEn;
        this.name = name;
        this.description = description;
        this.status = StatusEnum.valueOf(status);
    }

    public Project(long id, String nameEn, String name, String description, String status) {
        this.id = id;
        this.nameEn = nameEn;
        this.name = name;
        this.description = description;
        this.status = StatusEnum.valueOf(status);
    }

    public Project() {
    }
}
