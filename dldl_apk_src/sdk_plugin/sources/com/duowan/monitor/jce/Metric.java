package com.duowan.monitor.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class Metric extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static int cache_eUnit;
    static StatsSet cache_tStatsSet;
    static ArrayList<Dimension> cache_vDimension;
    static ArrayList<Dimension> cache_vExLog;
    public String sMetricName = "";
    public ArrayList<Dimension> vDimension = null;
    public long iTS = 0;
    public int iSuccess = 0;
    public int iRetCode = 0;
    public double fValue = 0.0d;
    public int eUnit = 0;
    public StatsSet tStatsSet = null;
    public String sExtDesc = "";
    public ArrayList<Dimension> vExLog = null;

    public String className() {
        return "monitor.jce.Metric";
    }

    public String fullClassName() {
        return "com.duowan.monitor.jce.Metric";
    }

    public String getSMetricName() {
        return this.sMetricName;
    }

    public void setSMetricName(String str) {
        this.sMetricName = str;
    }

    public ArrayList<Dimension> getVDimension() {
        return this.vDimension;
    }

    public void setVDimension(ArrayList<Dimension> arrayList) {
        this.vDimension = arrayList;
    }

    public long getITS() {
        return this.iTS;
    }

    public void setITS(long j) {
        this.iTS = j;
    }

    public int getISuccess() {
        return this.iSuccess;
    }

    public void setISuccess(int i) {
        this.iSuccess = i;
    }

    public int getIRetCode() {
        return this.iRetCode;
    }

    public void setIRetCode(int i) {
        this.iRetCode = i;
    }

    public double getFValue() {
        return this.fValue;
    }

    public void setFValue(double d) {
        this.fValue = d;
    }

    public int getEUnit() {
        return this.eUnit;
    }

    public void setEUnit(int i) {
        this.eUnit = i;
    }

    public StatsSet getTStatsSet() {
        return this.tStatsSet;
    }

    public void setTStatsSet(StatsSet statsSet) {
        this.tStatsSet = statsSet;
    }

    public String getSExtDesc() {
        return this.sExtDesc;
    }

    public void setSExtDesc(String str) {
        this.sExtDesc = str;
    }

    public ArrayList<Dimension> getVExLog() {
        return this.vExLog;
    }

    public void setVExLog(ArrayList<Dimension> arrayList) {
        this.vExLog = arrayList;
    }

    public Metric() {
        setSMetricName("");
        setVDimension(this.vDimension);
        setITS(this.iTS);
        setISuccess(this.iSuccess);
        setIRetCode(this.iRetCode);
        setFValue(this.fValue);
        setEUnit(this.eUnit);
        setTStatsSet(this.tStatsSet);
        setSExtDesc(this.sExtDesc);
        setVExLog(this.vExLog);
    }

    public Metric(String str, ArrayList<Dimension> arrayList, long j, int i, int i2, double d, int i3, StatsSet statsSet, String str2, ArrayList<Dimension> arrayList2) {
        setSMetricName(str);
        setVDimension(arrayList);
        setITS(j);
        setISuccess(i);
        setIRetCode(i2);
        setFValue(d);
        setEUnit(i3);
        setTStatsSet(statsSet);
        setSExtDesc(str2);
        setVExLog(arrayList2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Metric metric = (Metric) obj;
        return JceUtil.equals(this.sMetricName, metric.sMetricName) && JceUtil.equals(this.vDimension, metric.vDimension) && JceUtil.equals(this.iTS, metric.iTS) && JceUtil.equals(this.iSuccess, metric.iSuccess) && JceUtil.equals(this.iRetCode, metric.iRetCode) && JceUtil.equals(this.fValue, metric.fValue) && JceUtil.equals(this.eUnit, metric.eUnit) && JceUtil.equals(this.tStatsSet, metric.tStatsSet) && JceUtil.equals(this.sExtDesc, metric.sExtDesc) && JceUtil.equals(this.vExLog, metric.vExLog);
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
        jceOutputStream.write(this.sMetricName, 0);
        ArrayList<Dimension> arrayList = this.vDimension;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 1);
        }
        jceOutputStream.write(this.iTS, 2);
        jceOutputStream.write(this.iSuccess, 3);
        jceOutputStream.write(this.iRetCode, 4);
        jceOutputStream.write(this.fValue, 5);
        jceOutputStream.write(this.eUnit, 6);
        StatsSet statsSet = this.tStatsSet;
        if (statsSet != null) {
            jceOutputStream.write((JceStruct) statsSet, 7);
        }
        String str = this.sExtDesc;
        if (str != null) {
            jceOutputStream.write(str, 8);
        }
        ArrayList<Dimension> arrayList2 = this.vExLog;
        if (arrayList2 != null) {
            jceOutputStream.write((Collection) arrayList2, 9);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSMetricName(jceInputStream.readString(0, true));
        if (cache_vDimension == null) {
            cache_vDimension = new ArrayList<>();
            cache_vDimension.add(new Dimension());
        }
        setVDimension((ArrayList) jceInputStream.read(cache_vDimension, 1, false));
        setITS(jceInputStream.read(this.iTS, 2, false));
        setISuccess(jceInputStream.read(this.iSuccess, 3, false));
        setIRetCode(jceInputStream.read(this.iRetCode, 4, false));
        setFValue(jceInputStream.read(this.fValue, 5, false));
        setEUnit(jceInputStream.read(this.eUnit, 6, false));
        if (cache_tStatsSet == null) {
            cache_tStatsSet = new StatsSet();
        }
        setTStatsSet((StatsSet) jceInputStream.read((JceStruct) cache_tStatsSet, 7, false));
        setSExtDesc(jceInputStream.readString(8, false));
        if (cache_vExLog == null) {
            cache_vExLog = new ArrayList<>();
            cache_vExLog.add(new Dimension());
        }
        setVExLog((ArrayList) jceInputStream.read(cache_vExLog, 9, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sMetricName, "sMetricName");
        jceDisplayer.display((Collection) this.vDimension, "vDimension");
        jceDisplayer.display(this.iTS, "iTS");
        jceDisplayer.display(this.iSuccess, "iSuccess");
        jceDisplayer.display(this.iRetCode, "iRetCode");
        jceDisplayer.display(this.fValue, "fValue");
        jceDisplayer.display(this.eUnit, "eUnit");
        jceDisplayer.display((JceStruct) this.tStatsSet, "tStatsSet");
        jceDisplayer.display(this.sExtDesc, "sExtDesc");
        jceDisplayer.display((Collection) this.vExLog, "vExLog");
    }
}
