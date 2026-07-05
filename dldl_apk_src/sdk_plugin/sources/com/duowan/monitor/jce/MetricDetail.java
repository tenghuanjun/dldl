package com.duowan.monitor.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class MetricDetail extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<Dimension> cache_vDimension;
    static ArrayList<Dimension> cache_vExLog;
    static ArrayList<Field> cache_vFiled;
    public String sMetricName = "";
    public long iTS = 0;
    public ArrayList<Dimension> vDimension = null;
    public ArrayList<Field> vFiled = null;
    public ArrayList<Dimension> vExLog = null;

    public String className() {
        return "monitor.jce.MetricDetail";
    }

    public String fullClassName() {
        return "com.duowan.monitor.jce.MetricDetail";
    }

    public String getSMetricName() {
        return this.sMetricName;
    }

    public void setSMetricName(String str) {
        this.sMetricName = str;
    }

    public long getITS() {
        return this.iTS;
    }

    public void setITS(long j) {
        this.iTS = j;
    }

    public ArrayList<Dimension> getVDimension() {
        return this.vDimension;
    }

    public void setVDimension(ArrayList<Dimension> arrayList) {
        this.vDimension = arrayList;
    }

    public ArrayList<Field> getVFiled() {
        return this.vFiled;
    }

    public void setVFiled(ArrayList<Field> arrayList) {
        this.vFiled = arrayList;
    }

    public ArrayList<Dimension> getVExLog() {
        return this.vExLog;
    }

    public void setVExLog(ArrayList<Dimension> arrayList) {
        this.vExLog = arrayList;
    }

    public MetricDetail() {
        setSMetricName("");
        setITS(this.iTS);
        setVDimension(this.vDimension);
        setVFiled(this.vFiled);
        setVExLog(this.vExLog);
    }

    public MetricDetail(String str, long j, ArrayList<Dimension> arrayList, ArrayList<Field> arrayList2, ArrayList<Dimension> arrayList3) {
        setSMetricName(str);
        setITS(j);
        setVDimension(arrayList);
        setVFiled(arrayList2);
        setVExLog(arrayList3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MetricDetail metricDetail = (MetricDetail) obj;
        return JceUtil.equals(this.sMetricName, metricDetail.sMetricName) && JceUtil.equals(this.iTS, metricDetail.iTS) && JceUtil.equals(this.vDimension, metricDetail.vDimension) && JceUtil.equals(this.vFiled, metricDetail.vFiled) && JceUtil.equals(this.vExLog, metricDetail.vExLog);
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
        jceOutputStream.write(this.iTS, 1);
        ArrayList<Dimension> arrayList = this.vDimension;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 2);
        }
        ArrayList<Field> arrayList2 = this.vFiled;
        if (arrayList2 != null) {
            jceOutputStream.write((Collection) arrayList2, 3);
        }
        ArrayList<Dimension> arrayList3 = this.vExLog;
        if (arrayList3 != null) {
            jceOutputStream.write((Collection) arrayList3, 4);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSMetricName(jceInputStream.readString(0, true));
        setITS(jceInputStream.read(this.iTS, 1, false));
        if (cache_vDimension == null) {
            cache_vDimension = new ArrayList<>();
            cache_vDimension.add(new Dimension());
        }
        setVDimension((ArrayList) jceInputStream.read(cache_vDimension, 2, false));
        if (cache_vFiled == null) {
            cache_vFiled = new ArrayList<>();
            cache_vFiled.add(new Field());
        }
        setVFiled((ArrayList) jceInputStream.read(cache_vFiled, 3, false));
        if (cache_vExLog == null) {
            cache_vExLog = new ArrayList<>();
            cache_vExLog.add(new Dimension());
        }
        setVExLog((ArrayList) jceInputStream.read(cache_vExLog, 4, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sMetricName, "sMetricName");
        jceDisplayer.display(this.iTS, "iTS");
        jceDisplayer.display((Collection) this.vDimension, "vDimension");
        jceDisplayer.display((Collection) this.vFiled, "vFiled");
        jceDisplayer.display((Collection) this.vExLog, "vExLog");
    }
}
