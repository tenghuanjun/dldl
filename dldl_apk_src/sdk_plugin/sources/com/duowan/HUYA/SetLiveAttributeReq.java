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
public class SetLiveAttributeReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static Map<String, LiveAttributeInfo> cache_mpAttribute;
    static UserId cache_tId;
    public long lLiveId = 0;
    public Map<String, LiveAttributeInfo> mpAttribute = null;
    public UserId tId = null;

    public String className() {
        return "HUYA.SetLiveAttributeReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.SetLiveAttributeReq";
    }

    public long getLLiveId() {
        return this.lLiveId;
    }

    public void setLLiveId(long j) {
        this.lLiveId = j;
    }

    public Map<String, LiveAttributeInfo> getMpAttribute() {
        return this.mpAttribute;
    }

    public void setMpAttribute(Map<String, LiveAttributeInfo> map) {
        this.mpAttribute = map;
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public SetLiveAttributeReq() {
        setLLiveId(0L);
        setMpAttribute(this.mpAttribute);
        setTId(this.tId);
    }

    public SetLiveAttributeReq(long j, Map<String, LiveAttributeInfo> map, UserId userId) {
        setLLiveId(j);
        setMpAttribute(map);
        setTId(userId);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SetLiveAttributeReq setLiveAttributeReq = (SetLiveAttributeReq) obj;
        return JceUtil.equals(this.lLiveId, setLiveAttributeReq.lLiveId) && JceUtil.equals(this.mpAttribute, setLiveAttributeReq.mpAttribute) && JceUtil.equals(this.tId, setLiveAttributeReq.tId);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.lLiveId), JceUtil.hashCode(this.mpAttribute), JceUtil.hashCode(this.tId)});
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
        jceOutputStream.write(this.lLiveId, 0);
        Map<String, LiveAttributeInfo> map = this.mpAttribute;
        if (map != null) {
            jceOutputStream.write((Map) map, 1);
        }
        UserId userId = this.tId;
        if (userId != null) {
            jceOutputStream.write((JceStruct) userId, 2);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLLiveId(jceInputStream.read(this.lLiveId, 0, false));
        if (cache_mpAttribute == null) {
            cache_mpAttribute = new HashMap();
            cache_mpAttribute.put("", new LiveAttributeInfo());
        }
        setMpAttribute((Map) jceInputStream.read(cache_mpAttribute, 1, false));
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lLiveId, "lLiveId");
        jceDisplayer.display((Map) this.mpAttribute, "mpAttribute");
        jceDisplayer.display((JceStruct) this.tId, "tId");
    }
}
