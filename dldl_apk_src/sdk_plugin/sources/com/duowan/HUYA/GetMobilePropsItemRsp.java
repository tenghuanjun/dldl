package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GetMobilePropsItemRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static MobilePropsItem cache_tPropsItem;
    public MobilePropsItem tPropsItem = null;

    public String className() {
        return "HUYA.GetMobilePropsItemRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetMobilePropsItemRsp";
    }

    public MobilePropsItem getTPropsItem() {
        return this.tPropsItem;
    }

    public void setTPropsItem(MobilePropsItem mobilePropsItem) {
        this.tPropsItem = mobilePropsItem;
    }

    public GetMobilePropsItemRsp() {
        setTPropsItem(null);
    }

    public GetMobilePropsItemRsp(MobilePropsItem mobilePropsItem) {
        setTPropsItem(mobilePropsItem);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return JceUtil.equals(this.tPropsItem, ((GetMobilePropsItemRsp) obj).tPropsItem);
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
        MobilePropsItem mobilePropsItem = this.tPropsItem;
        if (mobilePropsItem != null) {
            jceOutputStream.write((JceStruct) mobilePropsItem, 0);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tPropsItem == null) {
            cache_tPropsItem = new MobilePropsItem();
        }
        setTPropsItem((MobilePropsItem) jceInputStream.read((JceStruct) cache_tPropsItem, 0, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display((JceStruct) this.tPropsItem, "tPropsItem");
    }
}
