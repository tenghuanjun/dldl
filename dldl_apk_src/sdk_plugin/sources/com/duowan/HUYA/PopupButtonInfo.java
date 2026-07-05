package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class PopupButtonInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public String sTitle = "";
    public String sActionUrl = "";
    public int iLoginStatus = 0;

    public String className() {
        return "HUYA.PopupButtonInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.PopupButtonInfo";
    }

    public String getSTitle() {
        return this.sTitle;
    }

    public void setSTitle(String str) {
        this.sTitle = str;
    }

    public String getSActionUrl() {
        return this.sActionUrl;
    }

    public void setSActionUrl(String str) {
        this.sActionUrl = str;
    }

    public int getILoginStatus() {
        return this.iLoginStatus;
    }

    public void setILoginStatus(int i) {
        this.iLoginStatus = i;
    }

    public PopupButtonInfo() {
        setSTitle("");
        setSActionUrl(this.sActionUrl);
        setILoginStatus(this.iLoginStatus);
    }

    public PopupButtonInfo(String str, String str2, int i) {
        setSTitle(str);
        setSActionUrl(str2);
        setILoginStatus(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PopupButtonInfo popupButtonInfo = (PopupButtonInfo) obj;
        return JceUtil.equals(this.sTitle, popupButtonInfo.sTitle) && JceUtil.equals(this.sActionUrl, popupButtonInfo.sActionUrl) && JceUtil.equals(this.iLoginStatus, popupButtonInfo.iLoginStatus);
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
        String str = this.sTitle;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        String str2 = this.sActionUrl;
        if (str2 != null) {
            jceOutputStream.write(str2, 1);
        }
        jceOutputStream.write(this.iLoginStatus, 2);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSTitle(jceInputStream.readString(0, false));
        setSActionUrl(jceInputStream.readString(1, false));
        setILoginStatus(jceInputStream.read(this.iLoginStatus, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sTitle, "sTitle");
        jceDisplayer.display(this.sActionUrl, "sActionUrl");
        jceDisplayer.display(this.iLoginStatus, "iLoginStatus");
    }
}
