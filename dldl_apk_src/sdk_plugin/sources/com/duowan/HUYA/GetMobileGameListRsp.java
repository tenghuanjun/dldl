package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GetMobileGameListRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<MobileGameList> cache_vMobileGameList;
    static ArrayList<MobileGameList> cache_vNewMGameList;
    static ArrayList<MobileGameList> cache_vOtherMGameList;
    public ArrayList<MobileGameList> vMobileGameList = null;
    public ArrayList<MobileGameList> vNewMGameList = null;
    public ArrayList<MobileGameList> vOtherMGameList = null;

    public String className() {
        return "HUYA.GetMobileGameListRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetMobileGameListRsp";
    }

    public ArrayList<MobileGameList> getVMobileGameList() {
        return this.vMobileGameList;
    }

    public void setVMobileGameList(ArrayList<MobileGameList> arrayList) {
        this.vMobileGameList = arrayList;
    }

    public ArrayList<MobileGameList> getVNewMGameList() {
        return this.vNewMGameList;
    }

    public void setVNewMGameList(ArrayList<MobileGameList> arrayList) {
        this.vNewMGameList = arrayList;
    }

    public ArrayList<MobileGameList> getVOtherMGameList() {
        return this.vOtherMGameList;
    }

    public void setVOtherMGameList(ArrayList<MobileGameList> arrayList) {
        this.vOtherMGameList = arrayList;
    }

    public GetMobileGameListRsp() {
        setVMobileGameList(null);
        setVNewMGameList(this.vNewMGameList);
        setVOtherMGameList(this.vOtherMGameList);
    }

    public GetMobileGameListRsp(ArrayList<MobileGameList> arrayList, ArrayList<MobileGameList> arrayList2, ArrayList<MobileGameList> arrayList3) {
        setVMobileGameList(arrayList);
        setVNewMGameList(arrayList2);
        setVOtherMGameList(arrayList3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetMobileGameListRsp getMobileGameListRsp = (GetMobileGameListRsp) obj;
        return JceUtil.equals(this.vMobileGameList, getMobileGameListRsp.vMobileGameList) && JceUtil.equals(this.vNewMGameList, getMobileGameListRsp.vNewMGameList) && JceUtil.equals(this.vOtherMGameList, getMobileGameListRsp.vOtherMGameList);
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
        ArrayList<MobileGameList> arrayList = this.vMobileGameList;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 0);
        }
        ArrayList<MobileGameList> arrayList2 = this.vNewMGameList;
        if (arrayList2 != null) {
            jceOutputStream.write((Collection) arrayList2, 1);
        }
        ArrayList<MobileGameList> arrayList3 = this.vOtherMGameList;
        if (arrayList3 != null) {
            jceOutputStream.write((Collection) arrayList3, 2);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_vMobileGameList == null) {
            cache_vMobileGameList = new ArrayList<>();
            cache_vMobileGameList.add(new MobileGameList());
        }
        setVMobileGameList((ArrayList) jceInputStream.read(cache_vMobileGameList, 0, false));
        if (cache_vNewMGameList == null) {
            cache_vNewMGameList = new ArrayList<>();
            cache_vNewMGameList.add(new MobileGameList());
        }
        setVNewMGameList((ArrayList) jceInputStream.read(cache_vNewMGameList, 1, false));
        if (cache_vOtherMGameList == null) {
            cache_vOtherMGameList = new ArrayList<>();
            cache_vOtherMGameList.add(new MobileGameList());
        }
        setVOtherMGameList((ArrayList) jceInputStream.read(cache_vOtherMGameList, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((Collection) this.vMobileGameList, "vMobileGameList");
        jceDisplayer.display((Collection) this.vNewMGameList, "vNewMGameList");
        jceDisplayer.display((Collection) this.vOtherMGameList, "vOtherMGameList");
    }
}
