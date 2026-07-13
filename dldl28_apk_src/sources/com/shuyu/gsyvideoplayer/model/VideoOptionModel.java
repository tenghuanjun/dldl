package com.shuyu.gsyvideoplayer.model;

/* JADX INFO: loaded from: classes3.dex */
public class VideoOptionModel {
    public static final int VALUE_TYPE_INT = 0;
    public static final int VALUE_TYPE_STRING = 1;
    int category;
    String name;
    int valueInt;
    String valueString;
    int valueType = 0;

    public VideoOptionModel(int i, String str, int i2) {
        this.category = i;
        this.name = str;
        this.valueInt = i2;
    }

    public VideoOptionModel(int i, String str, String str2) {
        this.category = i;
        this.name = str;
        this.valueString = str2;
    }

    public int getValueType() {
        return this.valueType;
    }

    public void setValueType(int i) {
        this.valueType = i;
    }

    public int getCategory() {
        return this.category;
    }

    public void setCategory(int i) {
        this.category = i;
    }

    public int getValueInt() {
        return this.valueInt;
    }

    public void setValueInt(int i) {
        this.valueInt = i;
        this.valueType = 0;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getValueString() {
        return this.valueString;
    }

    public void setValueString(String str) {
        this.valueString = str;
        this.valueType = 1;
    }
}
