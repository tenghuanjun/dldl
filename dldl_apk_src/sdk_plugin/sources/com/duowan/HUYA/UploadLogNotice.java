package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class UploadLogNotice extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iClientType = 0;
    public int iSampleRatio = 0;
    public int iUploadNetLevel = 0;
    public int iUploadBackupLog = 0;
    public String sContent = "";

    public String className() {
        return "HUYA.UploadLogNotice";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.UploadLogNotice";
    }

    public int getIClientType() {
        return this.iClientType;
    }

    public void setIClientType(int i) {
        this.iClientType = i;
    }

    public int getISampleRatio() {
        return this.iSampleRatio;
    }

    public void setISampleRatio(int i) {
        this.iSampleRatio = i;
    }

    public int getIUploadNetLevel() {
        return this.iUploadNetLevel;
    }

    public void setIUploadNetLevel(int i) {
        this.iUploadNetLevel = i;
    }

    public int getIUploadBackupLog() {
        return this.iUploadBackupLog;
    }

    public void setIUploadBackupLog(int i) {
        this.iUploadBackupLog = i;
    }

    public String getSContent() {
        return this.sContent;
    }

    public void setSContent(String str) {
        this.sContent = str;
    }

    public UploadLogNotice() {
        setIClientType(0);
        setISampleRatio(this.iSampleRatio);
        setIUploadNetLevel(this.iUploadNetLevel);
        setIUploadBackupLog(this.iUploadBackupLog);
        setSContent(this.sContent);
    }

    public UploadLogNotice(int i, int i2, int i3, int i4, String str) {
        setIClientType(i);
        setISampleRatio(i2);
        setIUploadNetLevel(i3);
        setIUploadBackupLog(i4);
        setSContent(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UploadLogNotice uploadLogNotice = (UploadLogNotice) obj;
        return JceUtil.equals(this.iClientType, uploadLogNotice.iClientType) && JceUtil.equals(this.iSampleRatio, uploadLogNotice.iSampleRatio) && JceUtil.equals(this.iUploadNetLevel, uploadLogNotice.iUploadNetLevel) && JceUtil.equals(this.iUploadBackupLog, uploadLogNotice.iUploadBackupLog) && JceUtil.equals(this.sContent, uploadLogNotice.sContent);
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
        jceOutputStream.write(this.iClientType, 0);
        jceOutputStream.write(this.iSampleRatio, 1);
        jceOutputStream.write(this.iUploadNetLevel, 2);
        jceOutputStream.write(this.iUploadBackupLog, 3);
        String str = this.sContent;
        if (str != null) {
            jceOutputStream.write(str, 4);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIClientType(jceInputStream.read(this.iClientType, 0, false));
        setISampleRatio(jceInputStream.read(this.iSampleRatio, 1, false));
        setIUploadNetLevel(jceInputStream.read(this.iUploadNetLevel, 2, false));
        setIUploadBackupLog(jceInputStream.read(this.iUploadBackupLog, 3, false));
        setSContent(jceInputStream.readString(4, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iClientType, "iClientType");
        jceDisplayer.display(this.iSampleRatio, "iSampleRatio");
        jceDisplayer.display(this.iUploadNetLevel, "iUploadNetLevel");
        jceDisplayer.display(this.iUploadBackupLog, "iUploadBackupLog");
        jceDisplayer.display(this.sContent, "sContent");
    }
}
