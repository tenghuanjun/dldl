package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class LiveProxyValue extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static int cache_eProxyType;
    static ArrayList<String> cache_sProxy;
    public int eProxyType = 0;
    public ArrayList<String> sProxy = null;

    public String className() {
        return "HUYA.LiveProxyValue";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.LiveProxyValue";
    }

    public int getEProxyType() {
        return this.eProxyType;
    }

    public void setEProxyType(int i) {
        this.eProxyType = i;
    }

    public ArrayList<String> getSProxy() {
        return this.sProxy;
    }

    public void setSProxy(ArrayList<String> arrayList) {
        this.sProxy = arrayList;
    }

    public LiveProxyValue() {
        setEProxyType(0);
        setSProxy(this.sProxy);
    }

    public LiveProxyValue(int i, ArrayList<String> arrayList) {
        setEProxyType(i);
        setSProxy(arrayList);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        LiveProxyValue liveProxyValue = (LiveProxyValue) obj;
        return JceUtil.equals(this.eProxyType, liveProxyValue.eProxyType) && JceUtil.equals(this.sProxy, liveProxyValue.sProxy);
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
        jceOutputStream.write(this.eProxyType, 0);
        ArrayList<String> arrayList = this.sProxy;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setEProxyType(jceInputStream.read(this.eProxyType, 0, false));
        if (cache_sProxy == null) {
            ArrayList<String> arrayList = new ArrayList<>();
            cache_sProxy = arrayList;
            arrayList.add("");
        }
        setSProxy((ArrayList) jceInputStream.read(cache_sProxy, 1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.eProxyType, "eProxyType");
        jceDisplayer.display((Collection) this.sProxy, "sProxy");
    }
}
