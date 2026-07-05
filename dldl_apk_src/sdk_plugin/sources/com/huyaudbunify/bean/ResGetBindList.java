package com.huyaudbunify.bean;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ResGetBindList {
    int bindState;
    List<ThirdBindInfo> bind_vec = new ArrayList();
    ResponseHeander header;
    String phoneMask;

    public ResponseHeander getHeader() {
        return this.header;
    }

    public void setHeader(ResponseHeander responseHeander) {
        this.header = responseHeander;
    }

    public List<ThirdBindInfo> getBind_vec() {
        return this.bind_vec;
    }

    public void setBind_vec(List<ThirdBindInfo> list) {
        this.bind_vec = list;
    }

    public String getPhoneMask() {
        return this.phoneMask;
    }

    public void setPhoneMask(String str) {
        this.phoneMask = str;
    }

    public int getBindState() {
        return this.bindState;
    }

    public void setBindState(int i) {
        this.bindState = i;
    }
}
