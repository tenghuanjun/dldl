package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class UserSettingItem extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public String sKey = "";
    public String sValue = "";

    public String className() {
        return "HUYA.UserSettingItem";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.UserSettingItem";
    }

    public String getSKey() {
        return this.sKey;
    }

    public void setSKey(String str) {
        this.sKey = str;
    }

    public String getSValue() {
        return this.sValue;
    }

    public void setSValue(String str) {
        this.sValue = str;
    }

    public UserSettingItem() {
        setSKey("");
        setSValue(this.sValue);
    }

    public UserSettingItem(String str, String str2) {
        setSKey(str);
        setSValue(str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserSettingItem userSettingItem = (UserSettingItem) obj;
        return JceUtil.equals(this.sKey, userSettingItem.sKey) && JceUtil.equals(this.sValue, userSettingItem.sValue);
    }

    public int hashCode() {
        try {
            throw new Exception("Need define key first!");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void writeTo(JceOutputStream jceOutputStream) {
        String str = this.sKey;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        String str2 = this.sValue;
        if (str2 != null) {
            jceOutputStream.write(str2, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSKey(jceInputStream.readString(0, false));
        setSValue(jceInputStream.readString(1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sKey, "sKey");
        jceDisplayer.display(this.sValue, "sValue");
    }
}
