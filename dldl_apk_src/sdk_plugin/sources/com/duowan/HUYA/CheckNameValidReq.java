package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class CheckNameValidReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tUserId;
    public UserId tUserId = null;
    public String sType = "";
    public String sName = "";

    public String className() {
        return "HUYA.CheckNameValidReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.CheckNameValidReq";
    }

    public UserId getTUserId() {
        return this.tUserId;
    }

    public void setTUserId(UserId userId) {
        this.tUserId = userId;
    }

    public String getSType() {
        return this.sType;
    }

    public void setSType(String str) {
        this.sType = str;
    }

    public String getSName() {
        return this.sName;
    }

    public void setSName(String str) {
        this.sName = str;
    }

    public CheckNameValidReq() {
        setTUserId(null);
        setSType(this.sType);
        setSName(this.sName);
    }

    public CheckNameValidReq(UserId userId, String str, String str2) {
        setTUserId(userId);
        setSType(str);
        setSName(str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CheckNameValidReq checkNameValidReq = (CheckNameValidReq) obj;
        return JceUtil.equals(this.tUserId, checkNameValidReq.tUserId) && JceUtil.equals(this.sType, checkNameValidReq.sType) && JceUtil.equals(this.sName, checkNameValidReq.sName);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.tUserId), JceUtil.hashCode(this.sType), JceUtil.hashCode(this.sName)});
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
        UserId userId = this.tUserId;
        if (userId != null) {
            jceOutputStream.write((JceStruct) userId, 0);
        }
        String str = this.sType;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        String str2 = this.sName;
        if (str2 != null) {
            jceOutputStream.write(str2, 2);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tUserId == null) {
            cache_tUserId = new UserId();
        }
        setTUserId((UserId) jceInputStream.read((JceStruct) cache_tUserId, 0, false));
        setSType(jceInputStream.readString(1, false));
        setSName(jceInputStream.readString(2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tUserId, "tUserId");
        jceDisplayer.display(this.sType, "sType");
        jceDisplayer.display(this.sName, "sName");
    }
}
