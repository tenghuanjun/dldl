package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class PresenterLevelNotice extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static PresenterGrowInfo cache_tGrowInfo;
    static PresenterLevelBase cache_tLevelBase;
    public PresenterLevelBase tLevelBase = null;
    public long lCurrLevelExp = 0;
    public long lNextLevelExp = 0;
    public long lNext2LevelExp = 0;
    public PresenterGrowInfo tGrowInfo = null;
    public int iLightUp = 0;
    public int iLevelMax = 0;

    public String className() {
        return "HUYA.PresenterLevelNotice";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.PresenterLevelNotice";
    }

    public PresenterLevelBase getTLevelBase() {
        return this.tLevelBase;
    }

    public void setTLevelBase(PresenterLevelBase presenterLevelBase) {
        this.tLevelBase = presenterLevelBase;
    }

    public long getLCurrLevelExp() {
        return this.lCurrLevelExp;
    }

    public void setLCurrLevelExp(long j) {
        this.lCurrLevelExp = j;
    }

    public long getLNextLevelExp() {
        return this.lNextLevelExp;
    }

    public void setLNextLevelExp(long j) {
        this.lNextLevelExp = j;
    }

    public long getLNext2LevelExp() {
        return this.lNext2LevelExp;
    }

    public void setLNext2LevelExp(long j) {
        this.lNext2LevelExp = j;
    }

    public PresenterGrowInfo getTGrowInfo() {
        return this.tGrowInfo;
    }

    public void setTGrowInfo(PresenterGrowInfo presenterGrowInfo) {
        this.tGrowInfo = presenterGrowInfo;
    }

    public int getILightUp() {
        return this.iLightUp;
    }

    public void setILightUp(int i) {
        this.iLightUp = i;
    }

    public int getILevelMax() {
        return this.iLevelMax;
    }

    public void setILevelMax(int i) {
        this.iLevelMax = i;
    }

    public PresenterLevelNotice() {
        setTLevelBase(null);
        setLCurrLevelExp(this.lCurrLevelExp);
        setLNextLevelExp(this.lNextLevelExp);
        setLNext2LevelExp(this.lNext2LevelExp);
        setTGrowInfo(this.tGrowInfo);
        setILightUp(this.iLightUp);
        setILevelMax(this.iLevelMax);
    }

    public PresenterLevelNotice(PresenterLevelBase presenterLevelBase, long j, long j2, long j3, PresenterGrowInfo presenterGrowInfo, int i, int i2) {
        setTLevelBase(presenterLevelBase);
        setLCurrLevelExp(j);
        setLNextLevelExp(j2);
        setLNext2LevelExp(j3);
        setTGrowInfo(presenterGrowInfo);
        setILightUp(i);
        setILevelMax(i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PresenterLevelNotice presenterLevelNotice = (PresenterLevelNotice) obj;
        return JceUtil.equals(this.tLevelBase, presenterLevelNotice.tLevelBase) && JceUtil.equals(this.lCurrLevelExp, presenterLevelNotice.lCurrLevelExp) && JceUtil.equals(this.lNextLevelExp, presenterLevelNotice.lNextLevelExp) && JceUtil.equals(this.lNext2LevelExp, presenterLevelNotice.lNext2LevelExp) && JceUtil.equals(this.tGrowInfo, presenterLevelNotice.tGrowInfo) && JceUtil.equals(this.iLightUp, presenterLevelNotice.iLightUp) && JceUtil.equals(this.iLevelMax, presenterLevelNotice.iLevelMax);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.tLevelBase), JceUtil.hashCode(this.lCurrLevelExp), JceUtil.hashCode(this.lNextLevelExp), JceUtil.hashCode(this.lNext2LevelExp), JceUtil.hashCode(this.tGrowInfo), JceUtil.hashCode(this.iLightUp), JceUtil.hashCode(this.iLevelMax)});
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
        PresenterLevelBase presenterLevelBase = this.tLevelBase;
        if (presenterLevelBase != null) {
            jceOutputStream.write((JceStruct) presenterLevelBase, 0);
        }
        jceOutputStream.write(this.lCurrLevelExp, 1);
        jceOutputStream.write(this.lNextLevelExp, 2);
        jceOutputStream.write(this.lNext2LevelExp, 3);
        PresenterGrowInfo presenterGrowInfo = this.tGrowInfo;
        if (presenterGrowInfo != null) {
            jceOutputStream.write((JceStruct) presenterGrowInfo, 4);
        }
        jceOutputStream.write(this.iLightUp, 5);
        jceOutputStream.write(this.iLevelMax, 6);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tLevelBase == null) {
            cache_tLevelBase = new PresenterLevelBase();
        }
        setTLevelBase((PresenterLevelBase) jceInputStream.read((JceStruct) cache_tLevelBase, 0, false));
        setLCurrLevelExp(jceInputStream.read(this.lCurrLevelExp, 1, false));
        setLNextLevelExp(jceInputStream.read(this.lNextLevelExp, 2, false));
        setLNext2LevelExp(jceInputStream.read(this.lNext2LevelExp, 3, false));
        if (cache_tGrowInfo == null) {
            cache_tGrowInfo = new PresenterGrowInfo();
        }
        setTGrowInfo((PresenterGrowInfo) jceInputStream.read((JceStruct) cache_tGrowInfo, 4, false));
        setILightUp(jceInputStream.read(this.iLightUp, 5, false));
        setILevelMax(jceInputStream.read(this.iLevelMax, 6, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tLevelBase, "tLevelBase");
        jceDisplayer.display(this.lCurrLevelExp, "lCurrLevelExp");
        jceDisplayer.display(this.lNextLevelExp, "lNextLevelExp");
        jceDisplayer.display(this.lNext2LevelExp, "lNext2LevelExp");
        jceDisplayer.display((JceStruct) this.tGrowInfo, "tGrowInfo");
        jceDisplayer.display(this.iLightUp, "iLightUp");
        jceDisplayer.display(this.iLevelMax, "iLevelMax");
    }
}
