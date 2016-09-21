package com.hoho.util;

/**
 * Created by Silence on 2016/9/19.
 */
public enum TypeEnum {
    PUBLIC(1,"PUBLIC","公有"),PRIVATE(2,"PRIVATE","私有");

    private int index;
    private String nameEn;
    private String name;

    private TypeEnum(int index, String nameEn, String name){
        this.index = index ;
        this.nameEn = nameEn ;
        this.name = name ;
    }

    public static String getName(int index){
        for(TypeEnum status : TypeEnum.values()){
            if (status.getIndex() == index){
                return status.getName();
            }
        }
        return null;
    }

    public static String getNameEn(int index){
        for(TypeEnum status : TypeEnum.values()){
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
