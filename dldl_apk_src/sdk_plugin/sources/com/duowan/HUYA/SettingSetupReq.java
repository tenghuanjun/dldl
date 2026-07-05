package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class SettingSetupReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tId;
    static ArrayList<UserSettingItem> cache_vItems;
    public UserId tId = null;
    public ArrayList<UserSettingItem> vItems = null;

    public String className() {
        return "HUYA.SettingSetupReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.SettingSetupReq";
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public ArrayList<UserSettingItem> getVItems() {
        return this.vItems;
    }

    public void setVItems(ArrayList<UserSettingItem> arrayList) {
        this.vItems = arrayList;
    }

    public SettingSetupReq() {
        setTId(null);
        setVItems(this.vItems);
    }

    public SettingSetupReq(UserId userId, ArrayList<UserSettingItem> arrayList) {
        setTId(userId);
        setVItems(arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SettingSetupReq settingSetupReq = (SettingSetupReq) obj;
        return JceUtil.equals(this.tId, settingSetupReq.tId) && JceUtil.equals(this.vItems, settingSetupReq.vItems);
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
        ArrayList<UserSettingItem> arrayList = this.vItems;
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
        if (cache_vItems == null) {
            cache_vItems = new ArrayList<>();
            cache_vItems.add(new UserSettingItem());
        }
        setVItems((ArrayList) jceInputStream.read(cache_vItems, 1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display((Collection) this.vItems, "vItems");
    }
}
