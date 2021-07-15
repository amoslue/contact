package com.bupt.proj.model.enums;

public enum ContactStatusEnum {
    NORMAL(0),
    DELETE(1),
    RECOMMENDED(2),
    ;
    private int value;
    ContactStatusEnum(int value){this.value=value;}
    public int getValue(){return value;}




}
