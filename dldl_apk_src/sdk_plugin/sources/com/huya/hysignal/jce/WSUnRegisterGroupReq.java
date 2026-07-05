package com.huya.hysignal.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class WSUnRegisterGroupReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<String> cache_vGroupId;
    public ArrayList<String> vGroupId = null;

    public String className() {
        return "HUYA.WSUnRegisterGroupReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WSUnRegisterGroupReq";
    }

    public ArrayList<String> getVGroupId() {
        return this.vGroupId;
    }

    public void setVGroupId(ArrayList<String> arrayList) {
        this.vGroupId = arrayList;
    }

    public WSUnRegisterGroupReq() {
        setVGroupId(null);
    }

    public WSUnRegisterGroupReq(ArrayList<String> arrayList) {
        setVGroupId(arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return JceUtil.equals(this.vGroupId, ((WSUnRegisterGroupReq) obj).vGroupId);
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
        ArrayList<String> arrayList = this.vGroupId;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 0);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_vGroupId == null) {
            ArrayList<String> arrayList = new ArrayList<>();
            cache_vGroupId = arrayList;
            arrayList.add("");
        }
        setVGroupId((ArrayList) jceInputStream.read(cache_vGroupId, 0, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display((Collection) this.vGroupId, "vGroupId");
    }
}
