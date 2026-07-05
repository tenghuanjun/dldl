package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GetUserHDAvatarRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public String sHDAvatar = "";

    public String className() {
        return "HUYA.GetUserHDAvatarRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetUserHDAvatarRsp";
    }

    public String getSHDAvatar() {
        return this.sHDAvatar;
    }

    public void setSHDAvatar(String str) {
        this.sHDAvatar = str;
    }

    public GetUserHDAvatarRsp() {
        setSHDAvatar("");
    }

    public GetUserHDAvatarRsp(String str) {
        setSHDAvatar(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return JceUtil.equals(this.sHDAvatar, ((GetUserHDAvatarRsp) obj).sHDAvatar);
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
        String str = this.sHDAvatar;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSHDAvatar(jceInputStream.readString(0, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display(this.sHDAvatar, "sHDAvatar");
    }
}
