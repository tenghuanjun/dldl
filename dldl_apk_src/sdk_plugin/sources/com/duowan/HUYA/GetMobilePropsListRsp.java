package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GetMobilePropsListRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<MobilePropsItem> cache_vPropsItemList;
    public ArrayList<MobilePropsItem> vPropsItemList = null;
    public String sMd5 = "";
    public short iMirrorRoomShowNum = 0;
    public short iGameRoomShowNum = 0;

    public String className() {
        return "HUYA.GetMobilePropsListRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetMobilePropsListRsp";
    }

    public ArrayList<MobilePropsItem> getVPropsItemList() {
        return this.vPropsItemList;
    }

    public void setVPropsItemList(ArrayList<MobilePropsItem> arrayList) {
        this.vPropsItemList = arrayList;
    }

    public String getSMd5() {
        return this.sMd5;
    }

    public void setSMd5(String str) {
        this.sMd5 = str;
    }

    public short getIMirrorRoomShowNum() {
        return this.iMirrorRoomShowNum;
    }

    public void setIMirrorRoomShowNum(short s) {
        this.iMirrorRoomShowNum = s;
    }

    public short getIGameRoomShowNum() {
        return this.iGameRoomShowNum;
    }

    public void setIGameRoomShowNum(short s) {
        this.iGameRoomShowNum = s;
    }

    public GetMobilePropsListRsp() {
        setVPropsItemList(null);
        setSMd5(this.sMd5);
        setIMirrorRoomShowNum(this.iMirrorRoomShowNum);
        setIGameRoomShowNum(this.iGameRoomShowNum);
    }

    public GetMobilePropsListRsp(ArrayList<MobilePropsItem> arrayList, String str, short s, short s2) {
        setVPropsItemList(arrayList);
        setSMd5(str);
        setIMirrorRoomShowNum(s);
        setIGameRoomShowNum(s2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetMobilePropsListRsp getMobilePropsListRsp = (GetMobilePropsListRsp) obj;
        return JceUtil.equals(this.vPropsItemList, getMobilePropsListRsp.vPropsItemList) && JceUtil.equals(this.sMd5, getMobilePropsListRsp.sMd5) && JceUtil.equals(this.iMirrorRoomShowNum, getMobilePropsListRsp.iMirrorRoomShowNum) && JceUtil.equals(this.iGameRoomShowNum, getMobilePropsListRsp.iGameRoomShowNum);
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
        ArrayList<MobilePropsItem> arrayList = this.vPropsItemList;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 1);
        }
        String str = this.sMd5;
        if (str != null) {
            jceOutputStream.write(str, 2);
        }
        jceOutputStream.write(this.iMirrorRoomShowNum, 3);
        jceOutputStream.write(this.iGameRoomShowNum, 4);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_vPropsItemList == null) {
            cache_vPropsItemList = new ArrayList<>();
            cache_vPropsItemList.add(new MobilePropsItem());
        }
        setVPropsItemList((ArrayList) jceInputStream.read(cache_vPropsItemList, 1, false));
        setSMd5(jceInputStream.readString(2, false));
        setIMirrorRoomShowNum(jceInputStream.read(this.iMirrorRoomShowNum, 3, false));
        setIGameRoomShowNum(jceInputStream.read(this.iGameRoomShowNum, 4, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((Collection) this.vPropsItemList, "vPropsItemList");
        jceDisplayer.display(this.sMd5, "sMd5");
        jceDisplayer.display(this.iMirrorRoomShowNum, "iMirrorRoomShowNum");
        jceDisplayer.display(this.iGameRoomShowNum, "iGameRoomShowNum");
    }
}
