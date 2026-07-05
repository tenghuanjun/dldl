package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class SettingSetupRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iLevel = 0;
    public String sMessage = "";

    public String className() {
        return "HUYA.SettingSetupRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.SettingSetupRsp";
    }

    public int getILevel() {
        return this.iLevel;
    }

    public void setILevel(int i) {
        this.iLevel = i;
    }

    public String getSMessage() {
        return this.sMessage;
    }

    public void setSMessage(String str) {
        this.sMessage = str;
    }

    public SettingSetupRsp() {
        setILevel(0);
        setSMessage(this.sMessage);
    }

    public SettingSetupRsp(int i, String str) {
        setILevel(i);
        setSMessage(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SettingSetupRsp settingSetupRsp = (SettingSetupRsp) obj;
        return JceUtil.equals(this.iLevel, settingSetupRsp.iLevel) && JceUtil.equals(this.sMessage, settingSetupRsp.sMessage);
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
        jceOutputStream.write(this.iLevel, 0);
        String str = this.sMessage;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setILevel(jceInputStream.read(this.iLevel, 0, false));
        setSMessage(jceInputStream.readString(1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iLevel, "iLevel");
        jceDisplayer.display(this.sMessage, "sMessage");
    }
}
