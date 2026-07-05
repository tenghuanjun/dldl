package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class GetMobileLivingGameInfoRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<MobileLivingGameInfo> cache_vGameData;
    static ArrayList<MobileLivingGameGroup> cache_vGameGroup;
    public String sDataMd5 = "";
    public ArrayList<MobileLivingGameInfo> vGameData = null;
    public ArrayList<MobileLivingGameGroup> vGameGroup = null;

    public String className() {
        return "HUYA.GetMobileLivingGameInfoRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetMobileLivingGameInfoRsp";
    }

    public String getSDataMd5() {
        return this.sDataMd5;
    }

    public void setSDataMd5(String str) {
        this.sDataMd5 = str;
    }

    public ArrayList<MobileLivingGameInfo> getVGameData() {
        return this.vGameData;
    }

    public void setVGameData(ArrayList<MobileLivingGameInfo> arrayList) {
        this.vGameData = arrayList;
    }

    public ArrayList<MobileLivingGameGroup> getVGameGroup() {
        return this.vGameGroup;
    }

    public void setVGameGroup(ArrayList<MobileLivingGameGroup> arrayList) {
        this.vGameGroup = arrayList;
    }

    public GetMobileLivingGameInfoRsp() {
        setSDataMd5("");
        setVGameData(this.vGameData);
        setVGameGroup(this.vGameGroup);
    }

    public GetMobileLivingGameInfoRsp(String str, ArrayList<MobileLivingGameInfo> arrayList, ArrayList<MobileLivingGameGroup> arrayList2) {
        setSDataMd5(str);
        setVGameData(arrayList);
        setVGameGroup(arrayList2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetMobileLivingGameInfoRsp getMobileLivingGameInfoRsp = (GetMobileLivingGameInfoRsp) obj;
        return JceUtil.equals(this.sDataMd5, getMobileLivingGameInfoRsp.sDataMd5) && JceUtil.equals(this.vGameData, getMobileLivingGameInfoRsp.vGameData) && JceUtil.equals(this.vGameGroup, getMobileLivingGameInfoRsp.vGameGroup);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.sDataMd5), JceUtil.hashCode(this.vGameData), JceUtil.hashCode(this.vGameGroup)});
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
        String str = this.sDataMd5;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        ArrayList<MobileLivingGameInfo> arrayList = this.vGameData;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 1);
        }
        ArrayList<MobileLivingGameGroup> arrayList2 = this.vGameGroup;
        if (arrayList2 != null) {
            jceOutputStream.write((Collection) arrayList2, 2);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSDataMd5(jceInputStream.readString(0, false));
        if (cache_vGameData == null) {
            cache_vGameData = new ArrayList<>();
            cache_vGameData.add(new MobileLivingGameInfo());
        }
        setVGameData((ArrayList) jceInputStream.read(cache_vGameData, 1, false));
        if (cache_vGameGroup == null) {
            cache_vGameGroup = new ArrayList<>();
            cache_vGameGroup.add(new MobileLivingGameGroup());
        }
        setVGameGroup((ArrayList) jceInputStream.read(cache_vGameGroup, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sDataMd5, "sDataMd5");
        jceDisplayer.display((Collection) this.vGameData, "vGameData");
        jceDisplayer.display((Collection) this.vGameGroup, "vGameGroup");
    }
}
