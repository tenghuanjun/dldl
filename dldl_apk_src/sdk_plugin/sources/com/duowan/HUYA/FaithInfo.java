package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class FaithInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<FaithPresenter> cache_vPresenter;
    public String sFaithName = "";
    public ArrayList<FaithPresenter> vPresenter = null;

    public String className() {
        return "HUYA.FaithInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.FaithInfo";
    }

    public String getSFaithName() {
        return this.sFaithName;
    }

    public void setSFaithName(String str) {
        this.sFaithName = str;
    }

    public ArrayList<FaithPresenter> getVPresenter() {
        return this.vPresenter;
    }

    public void setVPresenter(ArrayList<FaithPresenter> arrayList) {
        this.vPresenter = arrayList;
    }

    public FaithInfo() {
        setSFaithName("");
        setVPresenter(this.vPresenter);
    }

    public FaithInfo(String str, ArrayList<FaithPresenter> arrayList) {
        setSFaithName(str);
        setVPresenter(arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FaithInfo faithInfo = (FaithInfo) obj;
        return JceUtil.equals(this.sFaithName, faithInfo.sFaithName) && JceUtil.equals(this.vPresenter, faithInfo.vPresenter);
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
        String str = this.sFaithName;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        ArrayList<FaithPresenter> arrayList = this.vPresenter;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSFaithName(jceInputStream.readString(0, false));
        if (cache_vPresenter == null) {
            cache_vPresenter = new ArrayList<>();
            cache_vPresenter.add(new FaithPresenter());
        }
        setVPresenter((ArrayList) jceInputStream.read(cache_vPresenter, 1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sFaithName, "sFaithName");
        jceDisplayer.display((Collection) this.vPresenter, "vPresenter");
    }
}
