package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class GetActiveEventInfoRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<ActiveEventInfo> cache_vActiveEventInfo;
    public ArrayList<ActiveEventInfo> vActiveEventInfo;

    public String className() {
        return "HUYA.GetActiveEventInfoRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetActiveEventInfoRsp";
    }

    public GetActiveEventInfoRsp() {
        this.vActiveEventInfo = null;
    }

    public GetActiveEventInfoRsp(ArrayList<ActiveEventInfo> arrayList) {
        this.vActiveEventInfo = null;
        this.vActiveEventInfo = arrayList;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return JceUtil.equals(this.vActiveEventInfo, ((GetActiveEventInfoRsp) obj).vActiveEventInfo);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.vActiveEventInfo)});
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
        ArrayList<ActiveEventInfo> arrayList = this.vActiveEventInfo;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 0);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_vActiveEventInfo == null) {
            cache_vActiveEventInfo = new ArrayList<>();
            cache_vActiveEventInfo.add(new ActiveEventInfo());
        }
        this.vActiveEventInfo = (ArrayList) jceInputStream.read(cache_vActiveEventInfo, 0, false);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display((Collection) this.vActiveEventInfo, "vActiveEventInfo");
    }
}
