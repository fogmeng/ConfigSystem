package com.hoho.util;

/**
 * Created by Silence on 2016/9/19.
 */
public enum StatusEnum {
    ON(1,"ON","启用"),OFF(2,"OFF","禁用");

    private int index;
    private String nameEn;
    private String name;

    private StatusEnum(int index, String nameEn, String name){
        this.index = index ;
        this.nameEn = nameEn ;
        this.name = name ;
    }

    public static String getName(int index){
        for(StatusEnum status : StatusEnum.values()){
            if (status.getIndex() == index){
                return status.getName();
            }
        }
        return null;
    }

    public static String getNameEn(int index){
        for(StatusEnum status : StatusEnum.values()){
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
