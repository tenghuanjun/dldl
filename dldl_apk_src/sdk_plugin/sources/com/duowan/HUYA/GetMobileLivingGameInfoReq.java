package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class GetMobileLivingGameInfoReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tId;
    public UserId tId = null;
    public String sDataMd5 = "";

    public String className() {
        return "HUYA.GetMobileLivingGameInfoReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetMobileLivingGameInfoReq";
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public String getSDataMd5() {
        return this.sDataMd5;
    }

    public void setSDataMd5(String str) {
        this.sDataMd5 = str;
    }

    public GetMobileLivingGameInfoReq() {
        setTId(null);
        setSDataMd5(this.sDataMd5);
    }

    public GetMobileLivingGameInfoReq(UserId userId, String str) {
        setTId(userId);
        setSDataMd5(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetMobileLivingGameInfoReq getMobileLivingGameInfoReq = (GetMobileLivingGameInfoReq) obj;
        return JceUtil.equals(this.tId, getMobileLivingGameInfoReq.tId) && JceUtil.equals(this.sDataMd5, getMobileLivingGameInfoReq.sDataMd5);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.tId), JceUtil.hashCode(this.sDataMd5)});
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
        String str = this.sDataMd5;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 0, false));
        setSDataMd5(jceInputStream.readString(1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display(this.sDataMd5, "sDataMd5");
    }
}
