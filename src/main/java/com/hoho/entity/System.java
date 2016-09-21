package com.hoho.entity;

/**
 * Created by Silence on 2016/9/12.
 */
public class System {
    private long id;
    private String nameEn;
    private String name;
    private String description;
    private long projectId;

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

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public System(String nameEn, String name, String description, long projectId) {
        this.nameEn = nameEn;
        this.name = name;
        this.description = description;
        this.projectId = projectId;
    }

    public System(String nameEn, String name, String description) {
        this.nameEn = nameEn;
        this.name = name;
        this.description = description;
    }

    public System(long id, String nameEn, String name, String description) {
        this.id = id;
        this.nameEn = nameEn;
        this.name = name;
        this.description = description;
    }

    public System(long id, String nameEn, String name, String description, long projectId) {
        this.id = id;
        this.nameEn = nameEn;
        this.name = name;
        this.description = description;
        this.projectId = projectId;
    }

    public System() {
    }
}
