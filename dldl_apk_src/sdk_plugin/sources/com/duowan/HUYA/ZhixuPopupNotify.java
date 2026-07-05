package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ZhixuPopupNotify extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<PresenterPopData> cache_vData;
    public ArrayList<PresenterPopData> vData = null;

    public String className() {
        return "HUYA.ZhixuPopupNotify";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.ZhixuPopupNotify";
    }

    public ArrayList<PresenterPopData> getVData() {
        return this.vData;
    }

    public void setVData(ArrayList<PresenterPopData> arrayList) {
        this.vData = arrayList;
    }

    public ZhixuPopupNotify() {
        setVData(null);
    }

    public ZhixuPopupNotify(ArrayList<PresenterPopData> arrayList) {
        setVData(arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return JceUtil.equals(this.vData, ((ZhixuPopupNotify) obj).vData);
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
        ArrayList<PresenterPopData> arrayList = this.vData;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 0);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_vData == null) {
            cache_vData = new ArrayList<>();
            cache_vData.add(new PresenterPopData());
        }
        setVData((ArrayList) jceInputStream.read(cache_vData, 0, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display((Collection) this.vData, "vData");
    }
}
