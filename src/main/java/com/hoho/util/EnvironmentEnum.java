package com.hoho.util;

/**
 * Created by Silence on 2016/9/19.
 */
public enum EnvironmentEnum {
    DEV(1,"DEV","开发环境"),STABLE(2,"STABLE","测试环境"),PROD(3,"PROD","生产环境");

    private int index;
    private String nameEn;
    private String name;

    private EnvironmentEnum(int index, String nameEn, String name){
        this.index = index ;
        this.nameEn = nameEn ;
        this.name = name ;
    }

    public static String getName(int index){
        for(EnvironmentEnum status : EnvironmentEnum.values()){
            if (status.getIndex() == index){
                return status.getName();
            }
        }
        return null;
    }

    public static String getNameEn(int index){
        for(EnvironmentEnum status : EnvironmentEnum.values()){
            if (status.getIndex() == index){
                return status.getNameEn();
            }
        }
        return null;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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
}
