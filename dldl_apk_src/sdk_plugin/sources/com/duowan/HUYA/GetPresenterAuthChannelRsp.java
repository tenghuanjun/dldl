package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GetPresenterAuthChannelRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<AuthChannelInfo> cache_vAuthChannelInfo;
    public ArrayList<AuthChannelInfo> vAuthChannelInfo = null;

    public String className() {
        return "HUYA.GetPresenterAuthChannelRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetPresenterAuthChannelRsp";
    }

    public ArrayList<AuthChannelInfo> getVAuthChannelInfo() {
        return this.vAuthChannelInfo;
    }

    public void setVAuthChannelInfo(ArrayList<AuthChannelInfo> arrayList) {
        this.vAuthChannelInfo = arrayList;
    }

    public GetPresenterAuthChannelRsp() {
        setVAuthChannelInfo(null);
    }

    public GetPresenterAuthChannelRsp(ArrayList<AuthChannelInfo> arrayList) {
        setVAuthChannelInfo(arrayList);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return JceUtil.equals(this.vAuthChannelInfo, ((GetPresenterAuthChannelRsp) obj).vAuthChannelInfo);
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
        ArrayList<AuthChannelInfo> arrayList = this.vAuthChannelInfo;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 0);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_vAuthChannelInfo == null) {
            cache_vAuthChannelInfo = new ArrayList<>();
            cache_vAuthChannelInfo.add(new AuthChannelInfo());
        }
        setVAuthChannelInfo((ArrayList) jceInputStream.read(cache_vAuthChannelInfo, 0, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display((Collection) this.vAuthChannelInfo, "vAuthChannelInfo");
    }
}
