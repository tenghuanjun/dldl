package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class BannerItem extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public String sContent;
    public String sExtval1;
    public String sImage;
    public String sMarketing;
    public String sSource;
    public String sSubject;
    public String sTraceId;
    public String sUrl;

    public String className() {
        return "HUYA.BannerItem";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.BannerItem";
    }

    public BannerItem() {
        this.sExtval1 = "";
        this.sImage = "";
        this.sMarketing = "";
        this.sSubject = "";
        this.sUrl = "";
        this.sContent = "";
        this.sSource = "";
        this.sTraceId = "";
    }

    public BannerItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.sExtval1 = "";
        this.sImage = "";
        this.sMarketing = "";
        this.sSubject = "";
        this.sUrl = "";
        this.sContent = "";
        this.sSource = "";
        this.sTraceId = "";
        this.sExtval1 = str;
        this.sImage = str2;
        this.sMarketing = str3;
        this.sSubject = str4;
        this.sUrl = str5;
        this.sContent = str6;
        this.sSource = str7;
        this.sTraceId = str8;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BannerItem bannerItem = (BannerItem) obj;
        return JceUtil.equals(this.sExtval1, bannerItem.sExtval1) && JceUtil.equals(this.sImage, bannerItem.sImage) && JceUtil.equals(this.sMarketing, bannerItem.sMarketing) && JceUtil.equals(this.sSubject, bannerItem.sSubject) && JceUtil.equals(this.sUrl, bannerItem.sUrl) && JceUtil.equals(this.sContent, bannerItem.sContent) && JceUtil.equals(this.sSource, bannerItem.sSource) && JceUtil.equals(this.sTraceId, bannerItem.sTraceId);
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
        String str = this.sExtval1;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        String str2 = this.sImage;
        if (str2 != null) {
            jceOutputStream.write(str2, 1);
        }
        String str3 = this.sMarketing;
        if (str3 != null) {
            jceOutputStream.write(str3, 2);
        }
        String str4 = this.sSubject;
        if (str4 != null) {
            jceOutputStream.write(str4, 3);
        }
        String str5 = this.sUrl;
        if (str5 != null) {
            jceOutputStream.write(str5, 4);
        }
        String str6 = this.sContent;
        if (str6 != null) {
            jceOutputStream.write(str6, 5);
        }
        String str7 = this.sSource;
        if (str7 != null) {
            jceOutputStream.write(str7, 6);
        }
        String str8 = this.sTraceId;
        if (str8 != null) {
            jceOutputStream.write(str8, 7);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        this.sExtval1 = jceInputStream.readString(0, false);
        this.sImage = jceInputStream.readString(1, false);
        this.sMarketing = jceInputStream.readString(2, false);
        this.sSubject = jceInputStream.readString(3, false);
        this.sUrl = jceInputStream.readString(4, false);
        this.sContent = jceInputStream.readString(5, false);
        this.sSource = jceInputStream.readString(6, false);
        this.sTraceId = jceInputStream.readString(7, false);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sExtval1, "sExtval1");
        jceDisplayer.display(this.sImage, "sImage");
        jceDisplayer.display(this.sMarketing, "sMarketing");
        jceDisplayer.display(this.sSubject, "sSubject");
        jceDisplayer.display(this.sUrl, "sUrl");
        jceDisplayer.display(this.sContent, "sContent");
        jceDisplayer.display(this.sSource, "sSource");
        jceDisplayer.display(this.sTraceId, "sTraceId");
    }
}
