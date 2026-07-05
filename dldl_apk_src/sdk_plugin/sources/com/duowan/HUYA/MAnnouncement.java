package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class MAnnouncement extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public boolean bEnableClose;
    public int iId;
    public int iValidTime;
    public String sAction;
    public String sContent;

    public String className() {
        return "HUYA.MAnnouncement";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.MAnnouncement";
    }

    public MAnnouncement() {
        this.iId = 0;
        this.sContent = "";
        this.sAction = "";
        this.bEnableClose = true;
        this.iValidTime = 0;
    }

    public MAnnouncement(int i, String str, String str2, boolean z, int i2) {
        this.iId = 0;
        this.sContent = "";
        this.sAction = "";
        this.bEnableClose = true;
        this.iValidTime = 0;
        this.iId = i;
        this.sContent = str;
        this.sAction = str2;
        this.bEnableClose = z;
        this.iValidTime = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MAnnouncement mAnnouncement = (MAnnouncement) obj;
        return JceUtil.equals(this.iId, mAnnouncement.iId) && JceUtil.equals(this.sContent, mAnnouncement.sContent) && JceUtil.equals(this.sAction, mAnnouncement.sAction) && JceUtil.equals(this.bEnableClose, mAnnouncement.bEnableClose) && JceUtil.equals(this.iValidTime, mAnnouncement.iValidTime);
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
        jceOutputStream.write(this.iId, 0);
        String str = this.sContent;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        String str2 = this.sAction;
        if (str2 != null) {
            jceOutputStream.write(str2, 2);
        }
        jceOutputStream.write(this.bEnableClose, 3);
        jceOutputStream.write(this.iValidTime, 4);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        this.iId = jceInputStream.read(this.iId, 0, false);
        this.sContent = jceInputStream.readString(1, false);
        this.sAction = jceInputStream.readString(2, false);
        this.bEnableClose = jceInputStream.read(this.bEnableClose, 3, false);
        this.iValidTime = jceInputStream.read(this.iValidTime, 4, false);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iId, "iId");
        jceDisplayer.display(this.sContent, "sContent");
        jceDisplayer.display(this.sAction, "sAction");
        jceDisplayer.display(this.bEnableClose, "bEnableClose");
        jceDisplayer.display(this.iValidTime, "iValidTime");
    }
}
