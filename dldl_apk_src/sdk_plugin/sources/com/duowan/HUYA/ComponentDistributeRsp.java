package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ComponentDistributeRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<ComponentItem> cache_vComList;
    public ArrayList<ComponentItem> vComList = null;

    public String className() {
        return "HUYA.ComponentDistributeRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.ComponentDistributeRsp";
    }

    public ArrayList<ComponentItem> getVComList() {
        return this.vComList;
    }

    public void setVComList(ArrayList<ComponentItem> arrayList) {
        this.vComList = arrayList;
    }

    public ComponentDistributeRsp() {
        setVComList(null);
    }

    public ComponentDistributeRsp(ArrayList<ComponentItem> arrayList) {
        setVComList(arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return JceUtil.equals(this.vComList, ((ComponentDistributeRsp) obj).vComList);
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
        ArrayList<ComponentItem> arrayList = this.vComList;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 0);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_vComList == null) {
            cache_vComList = new ArrayList<>();
            cache_vComList.add(new ComponentItem());
        }
        setVComList((ArrayList) jceInputStream.read(cache_vComList, 0, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display((Collection) this.vComList, "vComList");
    }
}
