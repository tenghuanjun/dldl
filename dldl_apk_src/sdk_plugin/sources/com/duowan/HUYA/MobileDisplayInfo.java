package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class MobileDisplayInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<Integer> cache_vAllVideoMax;
    static ArrayList<Integer> cache_vAllVideoMin;
    static ArrayList<Integer> cache_vCurrentVideoMax;
    static ArrayList<Integer> cache_vCurrentVideoMin;
    public int iMarqueeScopeMin = 0;
    public int iMarqueeScopeMax = 0;
    public ArrayList<Integer> vCurrentVideoMin = null;
    public ArrayList<Integer> vCurrentVideoMax = null;
    public ArrayList<Integer> vAllVideoMin = null;
    public ArrayList<Integer> vAllVideoMax = null;

    public String className() {
        return "HUYA.MobileDisplayInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.MobileDisplayInfo";
    }

    public int getIMarqueeScopeMin() {
        return this.iMarqueeScopeMin;
    }

    public void setIMarqueeScopeMin(int i) {
        this.iMarqueeScopeMin = i;
    }

    public int getIMarqueeScopeMax() {
        return this.iMarqueeScopeMax;
    }

    public void setIMarqueeScopeMax(int i) {
        this.iMarqueeScopeMax = i;
    }

    public ArrayList<Integer> getVCurrentVideoMin() {
        return this.vCurrentVideoMin;
    }

    public void setVCurrentVideoMin(ArrayList<Integer> arrayList) {
        this.vCurrentVideoMin = arrayList;
    }

    public ArrayList<Integer> getVCurrentVideoMax() {
        return this.vCurrentVideoMax;
    }

    public void setVCurrentVideoMax(ArrayList<Integer> arrayList) {
        this.vCurrentVideoMax = arrayList;
    }

    public ArrayList<Integer> getVAllVideoMin() {
        return this.vAllVideoMin;
    }

    public void setVAllVideoMin(ArrayList<Integer> arrayList) {
        this.vAllVideoMin = arrayList;
    }

    public ArrayList<Integer> getVAllVideoMax() {
        return this.vAllVideoMax;
    }

    public void setVAllVideoMax(ArrayList<Integer> arrayList) {
        this.vAllVideoMax = arrayList;
    }

    public MobileDisplayInfo() {
        setIMarqueeScopeMin(0);
        setIMarqueeScopeMax(this.iMarqueeScopeMax);
        setVCurrentVideoMin(this.vCurrentVideoMin);
        setVCurrentVideoMax(this.vCurrentVideoMax);
        setVAllVideoMin(this.vAllVideoMin);
        setVAllVideoMax(this.vAllVideoMax);
    }

    public MobileDisplayInfo(int i, int i2, ArrayList<Integer> arrayList, ArrayList<Integer> arrayList2, ArrayList<Integer> arrayList3, ArrayList<Integer> arrayList4) {
        setIMarqueeScopeMin(i);
        setIMarqueeScopeMax(i2);
        setVCurrentVideoMin(arrayList);
        setVCurrentVideoMax(arrayList2);
        setVAllVideoMin(arrayList3);
        setVAllVideoMax(arrayList4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MobileDisplayInfo mobileDisplayInfo = (MobileDisplayInfo) obj;
        return JceUtil.equals(this.iMarqueeScopeMin, mobileDisplayInfo.iMarqueeScopeMin) && JceUtil.equals(this.iMarqueeScopeMax, mobileDisplayInfo.iMarqueeScopeMax) && JceUtil.equals(this.vCurrentVideoMin, mobileDisplayInfo.vCurrentVideoMin) && JceUtil.equals(this.vCurrentVideoMax, mobileDisplayInfo.vCurrentVideoMax) && JceUtil.equals(this.vAllVideoMin, mobileDisplayInfo.vAllVideoMin) && JceUtil.equals(this.vAllVideoMax, mobileDisplayInfo.vAllVideoMax);
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
        jceOutputStream.write(this.iMarqueeScopeMin, 1);
        jceOutputStream.write(this.iMarqueeScopeMax, 2);
        ArrayList<Integer> arrayList = this.vCurrentVideoMin;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 3);
        }
        ArrayList<Integer> arrayList2 = this.vCurrentVideoMax;
        if (arrayList2 != null) {
            jceOutputStream.write((Collection) arrayList2, 4);
        }
        ArrayList<Integer> arrayList3 = this.vAllVideoMin;
        if (arrayList3 != null) {
            jceOutputStream.write((Collection) arrayList3, 5);
        }
        ArrayList<Integer> arrayList4 = this.vAllVideoMax;
        if (arrayList4 != null) {
            jceOutputStream.write((Collection) arrayList4, 6);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIMarqueeScopeMin(jceInputStream.read(this.iMarqueeScopeMin, 1, false));
        setIMarqueeScopeMax(jceInputStream.read(this.iMarqueeScopeMax, 2, false));
        if (cache_vCurrentVideoMin == null) {
            cache_vCurrentVideoMin = new ArrayList<>();
            cache_vCurrentVideoMin.add(0);
        }
        setVCurrentVideoMin((ArrayList) jceInputStream.read(cache_vCurrentVideoMin, 3, false));
        if (cache_vCurrentVideoMax == null) {
            cache_vCurrentVideoMax = new ArrayList<>();
            cache_vCurrentVideoMax.add(0);
        }
        setVCurrentVideoMax((ArrayList) jceInputStream.read(cache_vCurrentVideoMax, 4, false));
        if (cache_vAllVideoMin == null) {
            cache_vAllVideoMin = new ArrayList<>();
            cache_vAllVideoMin.add(0);
        }
        setVAllVideoMin((ArrayList) jceInputStream.read(cache_vAllVideoMin, 5, false));
        if (cache_vAllVideoMax == null) {
            cache_vAllVideoMax = new ArrayList<>();
            cache_vAllVideoMax.add(0);
        }
        setVAllVideoMax((ArrayList) jceInputStream.read(cache_vAllVideoMax, 6, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iMarqueeScopeMin, "iMarqueeScopeMin");
        jceDisplayer.display(this.iMarqueeScopeMax, "iMarqueeScopeMax");
        jceDisplayer.display((Collection) this.vCurrentVideoMin, "vCurrentVideoMin");
        jceDisplayer.display((Collection) this.vCurrentVideoMax, "vCurrentVideoMax");
        jceDisplayer.display((Collection) this.vAllVideoMin, "vAllVideoMin");
        jceDisplayer.display((Collection) this.vAllVideoMax, "vAllVideoMax");
    }
}
