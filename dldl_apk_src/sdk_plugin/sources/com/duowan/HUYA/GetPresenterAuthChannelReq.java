package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GetPresenterAuthChannelReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tId;
    static ArrayList<Long> cache_vChannelId;
    public UserId tId = null;
    public ArrayList<Long> vChannelId = null;

    public String className() {
        return "HUYA.GetPresenterAuthChannelReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetPresenterAuthChannelReq";
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public ArrayList<Long> getVChannelId() {
        return this.vChannelId;
    }

    public void setVChannelId(ArrayList<Long> arrayList) {
        this.vChannelId = arrayList;
    }

    public GetPresenterAuthChannelReq() {
        setTId(null);
        setVChannelId(this.vChannelId);
    }

    public GetPresenterAuthChannelReq(UserId userId, ArrayList<Long> arrayList) {
        setTId(userId);
        setVChannelId(arrayList);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        GetPresenterAuthChannelReq getPresenterAuthChannelReq = (GetPresenterAuthChannelReq) obj;
        return JceUtil.equals(this.tId, getPresenterAuthChannelReq.tId) && JceUtil.equals(this.vChannelId, getPresenterAuthChannelReq.vChannelId);
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
        ArrayList<Long> arrayList = this.vChannelId;
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
        if (cache_vChannelId == null) {
            cache_vChannelId = new ArrayList<>();
            cache_vChannelId.add(0L);
        }
        setVChannelId((ArrayList) jceInputStream.read(cache_vChannelId, 1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display((Collection) this.vChannelId, "vChannelId");
    }
}
