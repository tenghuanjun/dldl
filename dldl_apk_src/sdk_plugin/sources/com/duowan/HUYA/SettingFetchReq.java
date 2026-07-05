package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class SettingFetchReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tId;
    static ArrayList<String> cache_vKeys;
    public UserId tId = null;
    public ArrayList<String> vKeys = null;
    public boolean bEnableCached = true;

    public String className() {
        return "HUYA.SettingFetchReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.SettingFetchReq";
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public ArrayList<String> getVKeys() {
        return this.vKeys;
    }

    public void setVKeys(ArrayList<String> arrayList) {
        this.vKeys = arrayList;
    }

    public boolean getBEnableCached() {
        return this.bEnableCached;
    }

    public void setBEnableCached(boolean z) {
        this.bEnableCached = z;
    }

    public SettingFetchReq() {
        setTId(null);
        setVKeys(this.vKeys);
        setBEnableCached(this.bEnableCached);
    }

    public SettingFetchReq(UserId userId, ArrayList<String> arrayList, boolean z) {
        setTId(userId);
        setVKeys(arrayList);
        setBEnableCached(z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SettingFetchReq settingFetchReq = (SettingFetchReq) obj;
        return JceUtil.equals(this.tId, settingFetchReq.tId) && JceUtil.equals(this.vKeys, settingFetchReq.vKeys) && JceUtil.equals(this.bEnableCached, settingFetchReq.bEnableCached);
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
        ArrayList<String> arrayList = this.vKeys;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 1);
        }
        jceOutputStream.write(this.bEnableCached, 2);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 0, false));
        if (cache_vKeys == null) {
            ArrayList<String> arrayList = new ArrayList<>();
            cache_vKeys = arrayList;
            arrayList.add("");
        }
        setVKeys((ArrayList) jceInputStream.read(cache_vKeys, 1, false));
        setBEnableCached(jceInputStream.read(this.bEnableCached, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display((Collection) this.vKeys, "vKeys");
        jceDisplayer.display(this.bEnableCached, "bEnableCached");
    }
}
