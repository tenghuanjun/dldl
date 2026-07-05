package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class PresenterLevelBase extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public long lPid = 0;
    public int iLevel = 0;
    public long lExp = 0;

    public String className() {
        return "HUYA.PresenterLevelBase";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.PresenterLevelBase";
    }

    public long getLPid() {
        return this.lPid;
    }

    public void setLPid(long j) {
        this.lPid = j;
    }

    public int getILevel() {
        return this.iLevel;
    }

    public void setILevel(int i) {
        this.iLevel = i;
    }

    public long getLExp() {
        return this.lExp;
    }

    public void setLExp(long j) {
        this.lExp = j;
    }

    public PresenterLevelBase() {
        setLPid(0L);
        setILevel(this.iLevel);
        setLExp(this.lExp);
    }

    public PresenterLevelBase(long j, int i, long j2) {
        setLPid(j);
        setILevel(i);
        setLExp(j2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PresenterLevelBase presenterLevelBase = (PresenterLevelBase) obj;
        return JceUtil.equals(this.lPid, presenterLevelBase.lPid) && JceUtil.equals(this.iLevel, presenterLevelBase.iLevel) && JceUtil.equals(this.lExp, presenterLevelBase.lExp);
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
        jceOutputStream.write(this.lPid, 0);
        jceOutputStream.write(this.iLevel, 1);
        jceOutputStream.write(this.lExp, 2);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLPid(jceInputStream.read(this.lPid, 0, false));
        setILevel(jceInputStream.read(this.iLevel, 1, false));
        setLExp(jceInputStream.read(this.lExp, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lPid, "lPid");
        jceDisplayer.display(this.iLevel, "iLevel");
        jceDisplayer.display(this.lExp, "lExp");
    }
}
