package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GetMobilePresenterChannelReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tId;
    static ArrayList<ChannelPair> cache_vChannelPair;
    public UserId tId = null;
    public ArrayList<ChannelPair> vChannelPair = null;

    public String className() {
        return "HUYA.GetMobilePresenterChannelReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetMobilePresenterChannelReq";
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public ArrayList<ChannelPair> getVChannelPair() {
        return this.vChannelPair;
    }

    public void setVChannelPair(ArrayList<ChannelPair> arrayList) {
        this.vChannelPair = arrayList;
    }

    public GetMobilePresenterChannelReq() {
        setTId(null);
        setVChannelPair(this.vChannelPair);
    }

    public GetMobilePresenterChannelReq(UserId userId, ArrayList<ChannelPair> arrayList) {
        setTId(userId);
        setVChannelPair(arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetMobilePresenterChannelReq getMobilePresenterChannelReq = (GetMobilePresenterChannelReq) obj;
        return JceUtil.equals(this.tId, getMobilePresenterChannelReq.tId) && JceUtil.equals(this.vChannelPair, getMobilePresenterChannelReq.vChannelPair);
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
        UserId userId = this.tId;
        if (userId != null) {
            jceOutputStream.write((JceStruct) userId, 0);
        }
        ArrayList<ChannelPair> arrayList = this.vChannelPair;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 0, false));
        if (cache_vChannelPair == null) {
            cache_vChannelPair = new ArrayList<>();
            cache_vChannelPair.add(new ChannelPair());
        }
        setVChannelPair((ArrayList) jceInputStream.read(cache_vChannelPair, 1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display((Collection) this.vChannelPair, "vChannelPair");
    }
}
