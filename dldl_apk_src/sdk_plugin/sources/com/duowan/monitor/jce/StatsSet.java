package com.duowan.monitor.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class StatsSet extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public double fSum = 0.0d;
    public double fMaxValue = 0.0d;
    public double fMinValue = 0.0d;
    public long lSampleCnt = 0;

    public String className() {
        return "monitor.jce.StatsSet";
    }

    public String fullClassName() {
        return "com.duowan.monitor.jce.StatsSet";
    }

    public double getFSum() {
        return this.fSum;
    }

    public void setFSum(double d) {
        this.fSum = d;
    }

    public double getFMaxValue() {
        return this.fMaxValue;
    }

    public void setFMaxValue(double d) {
        this.fMaxValue = d;
    }

    public double getFMinValue() {
        return this.fMinValue;
    }

    public void setFMinValue(double d) {
        this.fMinValue = d;
    }

    public long getLSampleCnt() {
        return this.lSampleCnt;
    }

    public void setLSampleCnt(long j) {
        this.lSampleCnt = j;
    }

    public StatsSet() {
        setFSum(0.0d);
        setFMaxValue(this.fMaxValue);
        setFMinValue(this.fMinValue);
        setLSampleCnt(this.lSampleCnt);
    }

    public StatsSet(double d, double d2, double d3, long j) {
        setFSum(d);
        setFMaxValue(d2);
        setFMinValue(d3);
        setLSampleCnt(j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StatsSet statsSet = (StatsSet) obj;
        return JceUtil.equals(this.fSum, statsSet.fSum) && JceUtil.equals(this.fMaxValue, statsSet.fMaxValue) && JceUtil.equals(this.fMinValue, statsSet.fMinValue) && JceUtil.equals(this.lSampleCnt, statsSet.lSampleCnt);
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
        jceOutputStream.write(this.fSum, 0);
        jceOutputStream.write(this.fMaxValue, 1);
        jceOutputStream.write(this.fMinValue, 2);
        jceOutputStream.write(this.lSampleCnt, 3);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setFSum(jceInputStream.read(this.fSum, 0, false));
        setFMaxValue(jceInputStream.read(this.fMaxValue, 1, false));
        setFMinValue(jceInputStream.read(this.fMinValue, 2, false));
        setLSampleCnt(jceInputStream.read(this.lSampleCnt, 3, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.fSum, "fSum");
        jceDisplayer.display(this.fMaxValue, "fMaxValue");
        jceDisplayer.display(this.fMinValue, "fMinValue");
        jceDisplayer.display(this.lSampleCnt, "lSampleCnt");
    }
}
