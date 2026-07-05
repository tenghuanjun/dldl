package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ModifyUserNickReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tId;
    public UserId tId = null;
    public String sNick = "";
    public String sVerifyCode = "";
    public int iPayType = 0;

    public String className() {
        return "HUYA.ModifyUserNickReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.ModifyUserNickReq";
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public String getSNick() {
        return this.sNick;
    }

    public void setSNick(String str) {
        this.sNick = str;
    }

    public String getSVerifyCode() {
        return this.sVerifyCode;
    }

    public void setSVerifyCode(String str) {
        this.sVerifyCode = str;
    }

    public int getIPayType() {
        return this.iPayType;
    }

    public void setIPayType(int i) {
        this.iPayType = i;
    }

    public ModifyUserNickReq() {
        setTId(null);
        setSNick(this.sNick);
        setSVerifyCode(this.sVerifyCode);
        setIPayType(this.iPayType);
    }

    public ModifyUserNickReq(UserId userId, String str, String str2, int i) {
        setTId(userId);
        setSNick(str);
        setSVerifyCode(str2);
        setIPayType(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ModifyUserNickReq modifyUserNickReq = (ModifyUserNickReq) obj;
        return JceUtil.equals(this.tId, modifyUserNickReq.tId) && JceUtil.equals(this.sNick, modifyUserNickReq.sNick) && JceUtil.equals(this.sVerifyCode, modifyUserNickReq.sVerifyCode) && JceUtil.equals(this.iPayType, modifyUserNickReq.iPayType);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.tId), JceUtil.hashCode(this.sNick), JceUtil.hashCode(this.sVerifyCode), JceUtil.hashCode(this.iPayType)});
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
        String str = this.sNick;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        String str2 = this.sVerifyCode;
        if (str2 != null) {
            jceOutputStream.write(str2, 2);
        }
        jceOutputStream.write(this.iPayType, 3);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 0, false));
        setSNick(jceInputStream.readString(1, false));
        setSVerifyCode(jceInputStream.readString(2, false));
        setIPayType(jceInputStream.read(this.iPayType, 3, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display(this.sNick, "sNick");
        jceDisplayer.display(this.sVerifyCode, "sVerifyCode");
        jceDisplayer.display(this.iPayType, "iPayType");
    }
}
