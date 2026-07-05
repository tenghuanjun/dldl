package com.huya.statistics.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class DataInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static Map<String, String> cache_mPair;
    public Map<String, String> mPair = null;

    public String className() {
        return "HYSDK.DataInfo";
    }

    public String fullClassName() {
        return "com.duowan.HYSDK.DataInfo";
    }

    public Map<String, String> getMPair() {
        return this.mPair;
    }

    public void setMPair(Map<String, String> map) {
        this.mPair = map;
    }

    public DataInfo() {
        setMPair(null);
    }

    public DataInfo(Map<String, String> map) {
        setMPair(map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return JceUtil.equals(this.mPair, ((DataInfo) obj).mPair);
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
        jceOutputStream.write((Map) this.mPair, 0);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_mPair == null) {
            HashMap map = new HashMap();
            cache_mPair = map;
            map.put("", "");
        }
        setMPair((Map) jceInputStream.read(cache_mPair, 0, true));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display((Map) this.mPair, "mPair");
    }
}
