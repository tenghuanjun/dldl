package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class SettingFetchRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<UserSettingItem> cache_vItems;
    public ArrayList<UserSettingItem> vItems = null;

    public String className() {
        return "HUYA.SettingFetchRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.SettingFetchRsp";
    }

    public ArrayList<UserSettingItem> getVItems() {
        return this.vItems;
    }

    public void setVItems(ArrayList<UserSettingItem> arrayList) {
        this.vItems = arrayList;
    }

    public SettingFetchRsp() {
        setVItems(null);
    }

    public SettingFetchRsp(ArrayList<UserSettingItem> arrayList) {
        setVItems(arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return JceUtil.equals(this.vItems, ((SettingFetchRsp) obj).vItems);
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
        ArrayList<UserSettingItem> arrayList = this.vItems;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 0);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_vItems == null) {
            cache_vItems = new ArrayList<>();
            cache_vItems.add(new UserSettingItem());
        }
        setVItems((ArrayList) jceInputStream.read(cache_vItems, 0, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display((Collection) this.vItems, "vItems");
    }
}
