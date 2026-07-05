package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ModifyUserNickRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public String sMessage = "";
    public String sVerifyUrl = "";

    public String className() {
        return "HUYA.ModifyUserNickRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.ModifyUserNickRsp";
    }

    public String getSMessage() {
        return this.sMessage;
    }

    public void setSMessage(String str) {
        this.sMessage = str;
    }

    public String getSVerifyUrl() {
        return this.sVerifyUrl;
    }

    public void setSVerifyUrl(String str) {
        this.sVerifyUrl = str;
    }

    public ModifyUserNickRsp() {
        setSMessage("");
        setSVerifyUrl(this.sVerifyUrl);
    }

    public ModifyUserNickRsp(String str, String str2) {
        setSMessage(str);
        setSVerifyUrl(str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ModifyUserNickRsp modifyUserNickRsp = (ModifyUserNickRsp) obj;
        return JceUtil.equals(this.sMessage, modifyUserNickRsp.sMessage) && JceUtil.equals(this.sVerifyUrl, modifyUserNickRsp.sVerifyUrl);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.sMessage), JceUtil.hashCode(this.sVerifyUrl)});
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
        String str = this.sMessage;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        String str2 = this.sVerifyUrl;
        if (str2 != null) {
            jceOutputStream.write(str2, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSMessage(jceInputStream.readString(0, false));
        setSVerifyUrl(jceInputStream.readString(1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sMessage, "sMessage");
        jceDisplayer.display(this.sVerifyUrl, "sVerifyUrl");
    }
}
