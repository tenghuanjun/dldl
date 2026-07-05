package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class FaithPresenter extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public long lPid = 0;
    public String sLogo = "";

    public String className() {
        return "HUYA.FaithPresenter";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.FaithPresenter";
    }

    public long getLPid() {
        return this.lPid;
    }

    public void setLPid(long j) {
        this.lPid = j;
    }

    public String getSLogo() {
        return this.sLogo;
    }

    public void setSLogo(String str) {
        this.sLogo = str;
    }

    public FaithPresenter() {
        setLPid(0L);
        setSLogo(this.sLogo);
    }

    public FaithPresenter(long j, String str) {
        setLPid(j);
        setSLogo(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FaithPresenter faithPresenter = (FaithPresenter) obj;
        return JceUtil.equals(this.lPid, faithPresenter.lPid) && JceUtil.equals(this.sLogo, faithPresenter.sLogo);
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
        jceOutputStream.write(this.lPid, 0);
        String str = this.sLogo;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLPid(jceInputStream.read(this.lPid, 0, false));
        setSLogo(jceInputStream.readString(1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lPid, "lPid");
        jceDisplayer.display(this.sLogo, "sLogo");
    }
}
