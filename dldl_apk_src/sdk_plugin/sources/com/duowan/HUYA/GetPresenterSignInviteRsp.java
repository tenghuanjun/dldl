package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GetPresenterSignInviteRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public String sMsg = "";
    public String sJumpUrl = "";
    public int iHaveInvite = 0;

    public String className() {
        return "HUYA.GetPresenterSignInviteRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetPresenterSignInviteRsp";
    }

    public String getSMsg() {
        return this.sMsg;
    }

    public void setSMsg(String str) {
        this.sMsg = str;
    }

    public String getSJumpUrl() {
        return this.sJumpUrl;
    }

    public void setSJumpUrl(String str) {
        this.sJumpUrl = str;
    }

    public int getIHaveInvite() {
        return this.iHaveInvite;
    }

    public void setIHaveInvite(int i) {
        this.iHaveInvite = i;
    }

    public GetPresenterSignInviteRsp() {
        setSMsg("");
        setSJumpUrl(this.sJumpUrl);
        setIHaveInvite(this.iHaveInvite);
    }

    public GetPresenterSignInviteRsp(String str, String str2, int i) {
        setSMsg(str);
        setSJumpUrl(str2);
        setIHaveInvite(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetPresenterSignInviteRsp getPresenterSignInviteRsp = (GetPresenterSignInviteRsp) obj;
        return JceUtil.equals(this.sMsg, getPresenterSignInviteRsp.sMsg) && JceUtil.equals(this.sJumpUrl, getPresenterSignInviteRsp.sJumpUrl) && JceUtil.equals(this.iHaveInvite, getPresenterSignInviteRsp.iHaveInvite);
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
        String str = this.sMsg;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        String str2 = this.sJumpUrl;
        if (str2 != null) {
            jceOutputStream.write(str2, 1);
        }
        jceOutputStream.write(this.iHaveInvite, 2);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSMsg(jceInputStream.readString(0, false));
        setSJumpUrl(jceInputStream.readString(1, false));
        setIHaveInvite(jceInputStream.read(this.iHaveInvite, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sMsg, "sMsg");
        jceDisplayer.display(this.sJumpUrl, "sJumpUrl");
        jceDisplayer.display(this.iHaveInvite, "iHaveInvite");
    }
}
