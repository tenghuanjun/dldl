package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ZhuShouLiveingGameListRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<ZhuShouGameNameInfo> cache_vGameInfo;
    public ArrayList<ZhuShouGameNameInfo> vGameInfo = null;
    public String sMd5 = "";

    public String className() {
        return "HUYA.ZhuShouLiveingGameListRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.ZhuShouLiveingGameListRsp";
    }

    public ArrayList<ZhuShouGameNameInfo> getVGameInfo() {
        return this.vGameInfo;
    }

    public void setVGameInfo(ArrayList<ZhuShouGameNameInfo> arrayList) {
        this.vGameInfo = arrayList;
    }

    public String getSMd5() {
        return this.sMd5;
    }

    public void setSMd5(String str) {
        this.sMd5 = str;
    }

    public ZhuShouLiveingGameListRsp() {
        setVGameInfo(null);
        setSMd5(this.sMd5);
    }

    public ZhuShouLiveingGameListRsp(ArrayList<ZhuShouGameNameInfo> arrayList, String str) {
        setVGameInfo(arrayList);
        setSMd5(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ZhuShouLiveingGameListRsp zhuShouLiveingGameListRsp = (ZhuShouLiveingGameListRsp) obj;
        return JceUtil.equals(this.vGameInfo, zhuShouLiveingGameListRsp.vGameInfo) && JceUtil.equals(this.sMd5, zhuShouLiveingGameListRsp.sMd5);
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
        ArrayList<ZhuShouGameNameInfo> arrayList = this.vGameInfo;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 0);
        }
        String str = this.sMd5;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_vGameInfo == null) {
            cache_vGameInfo = new ArrayList<>();
            cache_vGameInfo.add(new ZhuShouGameNameInfo());
        }
        setVGameInfo((ArrayList) jceInputStream.read(cache_vGameInfo, 0, false));
        setSMd5(jceInputStream.readString(1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((Collection) this.vGameInfo, "vGameInfo");
        jceDisplayer.display(this.sMd5, "sMd5");
    }
}
