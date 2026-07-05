package com.huya.hysignal.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WSRedirect extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<String> cache_vRemoveIps;
    public ArrayList<String> vRemoveIps = null;
    public String sRedirectIp = "";

    public String className() {
        return "HUYA.WSRedirect";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WSRedirect";
    }

    public ArrayList<String> getVRemoveIps() {
        return this.vRemoveIps;
    }

    public void setVRemoveIps(ArrayList<String> arrayList) {
        this.vRemoveIps = arrayList;
    }

    public String getSRedirectIp() {
        return this.sRedirectIp;
    }

    public void setSRedirectIp(String str) {
        this.sRedirectIp = str;
    }

    public WSRedirect() {
        setVRemoveIps(null);
        setSRedirectIp(this.sRedirectIp);
    }

    public WSRedirect(ArrayList<String> arrayList, String str) {
        setVRemoveIps(arrayList);
        setSRedirectIp(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WSRedirect wSRedirect = (WSRedirect) obj;
        return JceUtil.equals(this.vRemoveIps, wSRedirect.vRemoveIps) && JceUtil.equals(this.sRedirectIp, wSRedirect.sRedirectIp);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.vRemoveIps), JceUtil.hashCode(this.sRedirectIp)});
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
        ArrayList<String> arrayList = this.vRemoveIps;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 0);
        }
        String str = this.sRedirectIp;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_vRemoveIps == null) {
            ArrayList<String> arrayList = new ArrayList<>();
            cache_vRemoveIps = arrayList;
            arrayList.add("");
        }
        setVRemoveIps((ArrayList) jceInputStream.read(cache_vRemoveIps, 0, false));
        setSRedirectIp(jceInputStream.readString(1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((Collection) this.vRemoveIps, "vRemoveIps");
        jceDisplayer.display(this.sRedirectIp, "sRedirectIp");
    }
}
