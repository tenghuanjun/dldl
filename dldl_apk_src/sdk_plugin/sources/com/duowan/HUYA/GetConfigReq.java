package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class GetConfigReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static Map<String, String> cache_mpVariable;
    static ArrayList<String> cache_sNamespace;
    static UserId cache_tId;
    public UserId tId = null;
    public Map<String, String> mpVariable = null;
    public String sBizId = "";
    public ArrayList<String> sNamespace = null;

    public String className() {
        return "HUYA.GetConfigReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetConfigReq";
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public Map<String, String> getMpVariable() {
        return this.mpVariable;
    }

    public void setMpVariable(Map<String, String> map) {
        this.mpVariable = map;
    }

    public String getSBizId() {
        return this.sBizId;
    }

    public void setSBizId(String str) {
        this.sBizId = str;
    }

    public ArrayList<String> getSNamespace() {
        return this.sNamespace;
    }

    public void setSNamespace(ArrayList<String> arrayList) {
        this.sNamespace = arrayList;
    }

    public GetConfigReq() {
        setTId(null);
        setMpVariable(this.mpVariable);
        setSBizId(this.sBizId);
        setSNamespace(this.sNamespace);
    }

    public GetConfigReq(UserId userId, Map<String, String> map, String str, ArrayList<String> arrayList) {
        setTId(userId);
        setMpVariable(map);
        setSBizId(str);
        setSNamespace(arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetConfigReq getConfigReq = (GetConfigReq) obj;
        return JceUtil.equals(this.tId, getConfigReq.tId) && JceUtil.equals(this.mpVariable, getConfigReq.mpVariable) && JceUtil.equals(this.sBizId, getConfigReq.sBizId) && JceUtil.equals(this.sNamespace, getConfigReq.sNamespace);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.tId), JceUtil.hashCode(this.mpVariable), JceUtil.hashCode(this.sBizId), JceUtil.hashCode(this.sNamespace)});
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
        UserId userId = this.tId;
        if (userId != null) {
            jceOutputStream.write((JceStruct) userId, 0);
        }
        Map<String, String> map = this.mpVariable;
        if (map != null) {
            jceOutputStream.write((Map) map, 1);
        }
        String str = this.sBizId;
        if (str != null) {
            jceOutputStream.write(str, 2);
        }
        ArrayList<String> arrayList = this.sNamespace;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 3);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 0, false));
        if (cache_mpVariable == null) {
            HashMap map = new HashMap();
            cache_mpVariable = map;
            map.put("", "");
        }
        setMpVariable((Map) jceInputStream.read(cache_mpVariable, 1, false));
        setSBizId(jceInputStream.readString(2, false));
        if (cache_sNamespace == null) {
            ArrayList<String> arrayList = new ArrayList<>();
            cache_sNamespace = arrayList;
            arrayList.add("");
        }
        setSNamespace((ArrayList) jceInputStream.read(cache_sNamespace, 3, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display((Map) this.mpVariable, "mpVariable");
        jceDisplayer.display(this.sBizId, "sBizId");
        jceDisplayer.display((Collection) this.sNamespace, "sNamespace");
    }
}
