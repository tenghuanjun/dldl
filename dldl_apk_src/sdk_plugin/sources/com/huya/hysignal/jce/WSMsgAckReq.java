package com.huya.hysignal.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WSMsgAckReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<Long> cache_vMsgId;
    public ArrayList<Long> vMsgId = null;

    public String className() {
        return "HUYA.WSMsgAckReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WSMsgAckReq";
    }

    public ArrayList<Long> getVMsgId() {
        return this.vMsgId;
    }

    public void setVMsgId(ArrayList<Long> arrayList) {
        this.vMsgId = arrayList;
    }

    public WSMsgAckReq() {
        setVMsgId(null);
    }

    public WSMsgAckReq(ArrayList<Long> arrayList) {
        setVMsgId(arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return JceUtil.equals(this.vMsgId, ((WSMsgAckReq) obj).vMsgId);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.vMsgId)});
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
        ArrayList<Long> arrayList = this.vMsgId;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 0);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_vMsgId == null) {
            cache_vMsgId = new ArrayList<>();
            cache_vMsgId.add(0L);
        }
        setVMsgId((ArrayList) jceInputStream.read(cache_vMsgId, 0, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display((Collection) this.vMsgId, "vMsgId");
    }
}
