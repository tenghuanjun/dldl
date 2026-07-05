package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GetMobilePropsItemReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tUserId;
    public UserId tUserId = null;
    public long lPropId = 0;
    public int iTemplateType = 0;

    public String className() {
        return "HUYA.GetMobilePropsItemReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetMobilePropsItemReq";
    }

    public UserId getTUserId() {
        return this.tUserId;
    }

    public void setTUserId(UserId userId) {
        this.tUserId = userId;
    }

    public long getLPropId() {
        return this.lPropId;
    }

    public void setLPropId(long j) {
        this.lPropId = j;
    }

    public int getITemplateType() {
        return this.iTemplateType;
    }

    public void setITemplateType(int i) {
        this.iTemplateType = i;
    }

    public GetMobilePropsItemReq() {
        setTUserId(null);
        setLPropId(this.lPropId);
        setITemplateType(this.iTemplateType);
    }

    public GetMobilePropsItemReq(UserId userId, long j, int i) {
        setTUserId(userId);
        setLPropId(j);
        setITemplateType(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetMobilePropsItemReq getMobilePropsItemReq = (GetMobilePropsItemReq) obj;
        return JceUtil.equals(this.tUserId, getMobilePropsItemReq.tUserId) && JceUtil.equals(this.lPropId, getMobilePropsItemReq.lPropId) && JceUtil.equals(this.iTemplateType, getMobilePropsItemReq.iTemplateType);
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
        UserId userId = this.tUserId;
        if (userId != null) {
            jceOutputStream.write((JceStruct) userId, 0);
        }
        jceOutputStream.write(this.lPropId, 1);
        jceOutputStream.write(this.iTemplateType, 2);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tUserId == null) {
            cache_tUserId = new UserId();
        }
        setTUserId((UserId) jceInputStream.read((JceStruct) cache_tUserId, 0, false));
        setLPropId(jceInputStream.read(this.lPropId, 1, false));
        setITemplateType(jceInputStream.read(this.iTemplateType, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tUserId, "tUserId");
        jceDisplayer.display(this.lPropId, "lPropId");
        jceDisplayer.display(this.iTemplateType, "iTemplateType");
    }
}
