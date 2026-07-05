package com.duowan.monitor.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class MetricDetailSet extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tId;
    static ArrayList<MetricDetail> cache_vMetricDetail;
    public UserId tId = null;
    public ArrayList<MetricDetail> vMetricDetail = null;

    public String className() {
        return "monitor.jce.MetricDetailSet";
    }

    public String fullClassName() {
        return "com.duowan.monitor.jce.MetricDetailSet";
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public ArrayList<MetricDetail> getVMetricDetail() {
        return this.vMetricDetail;
    }

    public void setVMetricDetail(ArrayList<MetricDetail> arrayList) {
        this.vMetricDetail = arrayList;
    }

    public MetricDetailSet() {
        setTId(null);
        setVMetricDetail(this.vMetricDetail);
    }

    public MetricDetailSet(UserId userId, ArrayList<MetricDetail> arrayList) {
        setTId(userId);
        setVMetricDetail(arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MetricDetailSet metricDetailSet = (MetricDetailSet) obj;
        return JceUtil.equals(this.tId, metricDetailSet.tId) && JceUtil.equals(this.vMetricDetail, metricDetailSet.vMetricDetail);
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
        jceOutputStream.write((Collection) this.vMetricDetail, 1);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 0, true));
        if (cache_vMetricDetail == null) {
            cache_vMetricDetail = new ArrayList<>();
            cache_vMetricDetail.add(new MetricDetail());
        }
        setVMetricDetail((ArrayList) jceInputStream.read(cache_vMetricDetail, 1, true));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display((Collection) this.vMetricDetail, "vMetricDetail");
    }
}
