package com.huya.hysignal.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WSVerifyHuyaTokenReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tId;
    public UserId tId = null;
    public int bAutoRegisterUid = 0;
    public String sAppSrc = "";

    public String className() {
        return "HUYA.WSVerifyHuyaTokenReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WSVerifyHuyaTokenReq";
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public int getBAutoRegisterUid() {
        return this.bAutoRegisterUid;
    }

    public void setBAutoRegisterUid(int i) {
        this.bAutoRegisterUid = i;
    }

    public String getSAppSrc() {
        return this.sAppSrc;
    }

    public void setSAppSrc(String str) {
        this.sAppSrc = str;
    }

    @Override // com.duowan.taf.jce.JceStruct
    public String toString() {
        return "WSVerifyHuyaTokenReq{tId=" + this.tId + ", bAutoRegisterUid=" + this.bAutoRegisterUid + ", sAppSrc='" + this.sAppSrc + '\'' + AbstractJsonLexerKt.END_OBJ;
    }

    public WSVerifyHuyaTokenReq() {
        setTId(null);
        setBAutoRegisterUid(this.bAutoRegisterUid);
        setSAppSrc(this.sAppSrc);
    }

    public WSVerifyHuyaTokenReq(UserId userId, int i, String str) {
        setTId(userId);
        setBAutoRegisterUid(i);
        setSAppSrc(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WSVerifyHuyaTokenReq wSVerifyHuyaTokenReq = (WSVerifyHuyaTokenReq) obj;
        return JceUtil.equals(this.tId, wSVerifyHuyaTokenReq.tId) && JceUtil.equals(this.bAutoRegisterUid, wSVerifyHuyaTokenReq.bAutoRegisterUid) && JceUtil.equals(this.sAppSrc, wSVerifyHuyaTokenReq.sAppSrc);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.tId), JceUtil.hashCode(this.bAutoRegisterUid), JceUtil.hashCode(this.sAppSrc)});
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
        jceOutputStream.write(this.bAutoRegisterUid, 1);
        String str = this.sAppSrc;
        if (str != null) {
            jceOutputStream.write(str, 2);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 0, false));
        setBAutoRegisterUid(jceInputStream.read(this.bAutoRegisterUid, 1, false));
        setSAppSrc(jceInputStream.readString(2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display(this.bAutoRegisterUid, "bAutoRegisterUid");
        jceDisplayer.display(this.sAppSrc, "sAppSrc");
    }
}
