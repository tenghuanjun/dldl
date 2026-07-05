package com.huya.berry.module.data;

import com.huya.berry.gamesdk.base.BaseCallback;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LiveListTagInfo extends BaseCallback {
    public String id;
    public String name;
    public String parentTagId;

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        stringBuffer.append(this.id);
        stringBuffer.append(",");
        stringBuffer.append(this.name);
        stringBuffer.append(",");
        stringBuffer.append(this.parentTagId);
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
