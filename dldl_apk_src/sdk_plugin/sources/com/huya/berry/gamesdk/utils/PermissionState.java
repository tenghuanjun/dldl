package com.huya.berry.gamesdk.utils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class PermissionState {
    private boolean isOn;
    private String label;
    private String name;

    public PermissionState(String str, String str2, boolean z) {
        this.name = str;
        this.label = str2;
        this.isOn = z;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public boolean isOn() {
        return this.isOn;
    }

    public void setIsOn(boolean z) {
        this.isOn = z;
    }
}
