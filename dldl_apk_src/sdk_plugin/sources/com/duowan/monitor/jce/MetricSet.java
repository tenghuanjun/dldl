package com.duowan.monitor.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class MetricSet extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tId;
    static ArrayList<Metric> cache_vMetric;
    public UserId tId = null;
    public ArrayList<Metric> vMetric = null;

    public String className() {
        return "monitor.jce.MetricSet";
    }

    public String fullClassName() {
        return "com.duowan.monitor.jce.MetricSet";
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public ArrayList<Metric> getVMetric() {
        return this.vMetric;
    }

    public void setVMetric(ArrayList<Metric> arrayList) {
        this.vMetric = arrayList;
    }

    public MetricSet() {
        setTId(null);
        setVMetric(this.vMetric);
    }

    public MetricSet(UserId userId, ArrayList<Metric> arrayList) {
        setTId(userId);
        setVMetric(arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MetricSet metricSet = (MetricSet) obj;
        return JceUtil.equals(this.tId, metricSet.tId) && JceUtil.equals(this.vMetric, metricSet.vMetric);
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
        jceOutputStream.write((JceStruct) this.tId, 0);
        jceOutputStream.write((Collection) this.vMetric, 1);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 0, true));
        if (cache_vMetric == null) {
            cache_vMetric = new ArrayList<>();
            cache_vMetric.add(new Metric());
        }
        setVMetric((ArrayList) jceInputStream.read(cache_vMetric, 1, true));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display((Collection) this.vMetric, "vMetric");
    }
}
