package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class UserRecListReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tId;
    static LocationPos cache_tLocation;
    static byte[] cache_vContext;
    public int iContentType;
    public int iFreeFlowFlag;
    public int iGameId;
    public String sFilterTagId;
    public String sSource;
    public UserId tId;
    public LocationPos tLocation;
    public byte[] vContext;

    public String className() {
        return "HUYA.UserRecListReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.UserRecListReq";
    }

    public UserRecListReq() {
        this.tId = null;
        this.vContext = null;
        this.iContentType = 0;
        this.iGameId = 0;
        this.tLocation = null;
        this.sFilterTagId = "";
        this.sSource = "";
        this.iFreeFlowFlag = 0;
    }

    public UserRecListReq(UserId userId, byte[] bArr, int i, int i2, LocationPos locationPos, String str, String str2, int i3) {
        this.tId = null;
        this.vContext = null;
        this.iContentType = 0;
        this.iGameId = 0;
        this.tLocation = null;
        this.sFilterTagId = "";
        this.sSource = "";
        this.iFreeFlowFlag = 0;
        this.tId = userId;
        this.vContext = bArr;
        this.iContentType = i;
        this.iGameId = i2;
        this.tLocation = locationPos;
        this.sFilterTagId = str;
        this.sSource = str2;
        this.iFreeFlowFlag = i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserRecListReq userRecListReq = (UserRecListReq) obj;
        return JceUtil.equals(this.tId, userRecListReq.tId) && JceUtil.equals(this.vContext, userRecListReq.vContext) && JceUtil.equals(this.iContentType, userRecListReq.iContentType) && JceUtil.equals(this.iGameId, userRecListReq.iGameId) && JceUtil.equals(this.tLocation, userRecListReq.tLocation) && JceUtil.equals(this.sFilterTagId, userRecListReq.sFilterTagId) && JceUtil.equals(this.sSource, userRecListReq.sSource) && JceUtil.equals(this.iFreeFlowFlag, userRecListReq.iFreeFlowFlag);
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
        UserId userId = this.tId;
        if (userId != null) {
            jceOutputStream.write((JceStruct) userId, 0);
        }
        byte[] bArr = this.vContext;
        if (bArr != null) {
            jceOutputStream.write(bArr, 1);
        }
        jceOutputStream.write(this.iContentType, 2);
        jceOutputStream.write(this.iGameId, 3);
        LocationPos locationPos = this.tLocation;
        if (locationPos != null) {
            jceOutputStream.write((JceStruct) locationPos, 4);
        }
        String str = this.sFilterTagId;
        if (str != null) {
            jceOutputStream.write(str, 5);
        }
        String str2 = this.sSource;
        if (str2 != null) {
            jceOutputStream.write(str2, 6);
        }
        jceOutputStream.write(this.iFreeFlowFlag, 7);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        this.tId = (UserId) jceInputStream.read((JceStruct) cache_tId, 0, false);
        if (cache_vContext == null) {
            cache_vContext = new byte[]{0};
        }
        this.vContext = jceInputStream.read(cache_vContext, 1, false);
        this.iContentType = jceInputStream.read(this.iContentType, 2, false);
        this.iGameId = jceInputStream.read(this.iGameId, 3, false);
        if (cache_tLocation == null) {
            cache_tLocation = new LocationPos();
        }
        this.tLocation = (LocationPos) jceInputStream.read((JceStruct) cache_tLocation, 4, false);
        this.sFilterTagId = jceInputStream.readString(5, false);
        this.sSource = jceInputStream.readString(6, false);
        this.iFreeFlowFlag = jceInputStream.read(this.iFreeFlowFlag, 7, false);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display(this.vContext, "vContext");
        jceDisplayer.display(this.iContentType, "iContentType");
        jceDisplayer.display(this.iGameId, "iGameId");
        jceDisplayer.display((JceStruct) this.tLocation, "tLocation");
        jceDisplayer.display(this.sFilterTagId, "sFilterTagId");
        jceDisplayer.display(this.sSource, "sSource");
        jceDisplayer.display(this.iFreeFlowFlag, "iFreeFlowFlag");
    }
}
