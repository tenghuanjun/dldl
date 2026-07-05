package com.huya.statistics.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class SDKReport extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static DataInfo cache_tHeader;
    static ArrayList<DataInfo> cache_vBody;
    public DataInfo tHeader = null;
    public ArrayList<DataInfo> vBody = null;

    public String className() {
        return "HYSDK.SDKReport";
    }

    public String fullClassName() {
        return "SDKReport";
    }

    public DataInfo getTHeader() {
        return this.tHeader;
    }

    public void setTHeader(DataInfo dataInfo) {
        this.tHeader = dataInfo;
    }

    public ArrayList<DataInfo> getVBody() {
        return this.vBody;
    }

    public void setVBody(ArrayList<DataInfo> arrayList) {
        this.vBody = arrayList;
    }

    public SDKReport() {
        setTHeader(null);
        setVBody(this.vBody);
    }

    public SDKReport(DataInfo dataInfo, ArrayList<DataInfo> arrayList) {
        setTHeader(dataInfo);
        setVBody(arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SDKReport sDKReport = (SDKReport) obj;
        return JceUtil.equals(this.tHeader, sDKReport.tHeader) && JceUtil.equals(this.vBody, sDKReport.vBody);
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
        jceOutputStream.write((JceStruct) this.tHeader, 0);
        jceOutputStream.write((Collection) this.vBody, 1);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tHeader == null) {
            cache_tHeader = new DataInfo();
        }
        setTHeader((DataInfo) jceInputStream.read((JceStruct) cache_tHeader, 0, true));
        if (cache_vBody == null) {
            cache_vBody = new ArrayList<>();
            cache_vBody.add(new DataInfo());
        }
        setVBody((ArrayList) jceInputStream.read(cache_vBody, 1, true));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tHeader, "tHeader");
        jceDisplayer.display((Collection) this.vBody, "vBody");
    }
}
