package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class NobleLevelAttr extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iAttrType = 0;
    public long lValidDate = 0;
    public int iAttrStatus = 0;
    public int iProgress = 0;
    public int iTask = 0;
    public int iRemainDays = 0;

    public String className() {
        return "HUYA.NobleLevelAttr";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.NobleLevelAttr";
    }

    public int getIAttrType() {
        return this.iAttrType;
    }

    public void setIAttrType(int i) {
        this.iAttrType = i;
    }

    public long getLValidDate() {
        return this.lValidDate;
    }

    public void setLValidDate(long j) {
        this.lValidDate = j;
    }

    public int getIAttrStatus() {
        return this.iAttrStatus;
    }

    public void setIAttrStatus(int i) {
        this.iAttrStatus = i;
    }

    public int getIProgress() {
        return this.iProgress;
    }

    public void setIProgress(int i) {
        this.iProgress = i;
    }

    public int getITask() {
        return this.iTask;
    }

    public void setITask(int i) {
        this.iTask = i;
    }

    public int getIRemainDays() {
        return this.iRemainDays;
    }

    public void setIRemainDays(int i) {
        this.iRemainDays = i;
    }

    public NobleLevelAttr() {
        setIAttrType(0);
        setLValidDate(this.lValidDate);
        setIAttrStatus(this.iAttrStatus);
        setIProgress(this.iProgress);
        setITask(this.iTask);
        setIRemainDays(this.iRemainDays);
    }

    public NobleLevelAttr(int i, long j, int i2, int i3, int i4, int i5) {
        setIAttrType(i);
        setLValidDate(j);
        setIAttrStatus(i2);
        setIProgress(i3);
        setITask(i4);
        setIRemainDays(i5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NobleLevelAttr nobleLevelAttr = (NobleLevelAttr) obj;
        return JceUtil.equals(this.iAttrType, nobleLevelAttr.iAttrType) && JceUtil.equals(this.lValidDate, nobleLevelAttr.lValidDate) && JceUtil.equals(this.iAttrStatus, nobleLevelAttr.iAttrStatus) && JceUtil.equals(this.iProgress, nobleLevelAttr.iProgress) && JceUtil.equals(this.iTask, nobleLevelAttr.iTask) && JceUtil.equals(this.iRemainDays, nobleLevelAttr.iRemainDays);
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
        jceOutputStream.write(this.iAttrType, 0);
        jceOutputStream.write(this.lValidDate, 1);
        jceOutputStream.write(this.iAttrStatus, 2);
        jceOutputStream.write(this.iProgress, 3);
        jceOutputStream.write(this.iTask, 4);
        jceOutputStream.write(this.iRemainDays, 5);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIAttrType(jceInputStream.read(this.iAttrType, 0, false));
        setLValidDate(jceInputStream.read(this.lValidDate, 1, false));
        setIAttrStatus(jceInputStream.read(this.iAttrStatus, 2, false));
        setIProgress(jceInputStream.read(this.iProgress, 3, false));
        setITask(jceInputStream.read(this.iTask, 4, false));
        setIRemainDays(jceInputStream.read(this.iRemainDays, 5, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iAttrType, "iAttrType");
        jceDisplayer.display(this.lValidDate, "lValidDate");
        jceDisplayer.display(this.iAttrStatus, "iAttrStatus");
        jceDisplayer.display(this.iProgress, "iProgress");
        jceDisplayer.display(this.iTask, "iTask");
        jceDisplayer.display(this.iRemainDays, "iRemainDays");
    }
}
