package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class GetConfigRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static Map<String, String> cache_mpConfig;
    static Map<String, String> cache_mpExperiment;
    public Map<String, String> mpConfig = null;
    public Map<String, String> mpExperiment = null;

    public String className() {
        return "HUYA.GetConfigRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetConfigRsp";
    }

    public Map<String, String> getMpConfig() {
        return this.mpConfig;
    }

    public void setMpConfig(Map<String, String> map) {
        this.mpConfig = map;
    }

    public Map<String, String> getMpExperiment() {
        return this.mpExperiment;
    }

    public void setMpExperiment(Map<String, String> map) {
        this.mpExperiment = map;
    }

    public GetConfigRsp() {
        setMpConfig(null);
        setMpExperiment(this.mpExperiment);
    }

    public GetConfigRsp(Map<String, String> map, Map<String, String> map2) {
        setMpConfig(map);
        setMpExperiment(map2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetConfigRsp getConfigRsp = (GetConfigRsp) obj;
        return JceUtil.equals(this.mpConfig, getConfigRsp.mpConfig) && JceUtil.equals(this.mpExperiment, getConfigRsp.mpExperiment);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.mpConfig), JceUtil.hashCode(this.mpExperiment)});
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
        Map<String, String> map = this.mpConfig;
        if (map != null) {
            jceOutputStream.write((Map) map, 0);
        }
        Map<String, String> map2 = this.mpExperiment;
        if (map2 != null) {
            jceOutputStream.write((Map) map2, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_mpConfig == null) {
            HashMap map = new HashMap();
            cache_mpConfig = map;
            map.put("", "");
        }
        setMpConfig((Map) jceInputStream.read(cache_mpConfig, 0, false));
        if (cache_mpExperiment == null) {
            HashMap map2 = new HashMap();
            cache_mpExperiment = map2;
            map2.put("", "");
        }
        setMpExperiment((Map) jceInputStream.read(cache_mpExperiment, 1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((Map) this.mpConfig, "mpConfig");
        jceDisplayer.display((Map) this.mpExperiment, "mpExperiment");
    }
}
