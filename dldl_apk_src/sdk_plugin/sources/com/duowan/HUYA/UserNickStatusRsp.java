package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class UserNickStatusRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iModifyTimes = 0;
    public int iGoldBeanPrice = 0;
    public int iSilverBeanPrice = 0;
    public String sRulerDesc = "";
    public int iPhoneState = 0;
    public long lGoldBeanBalance = 0;
    public long lSilverBeanBalance = 0;
    public int iPiecePrice = 0;
    public long lPieceBalance = 0;
    public String sPieceDateLine = "";

    public String className() {
        return "HUYA.UserNickStatusRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.UserNickStatusRsp";
    }

    public int getIModifyTimes() {
        return this.iModifyTimes;
    }

    public void setIModifyTimes(int i) {
        this.iModifyTimes = i;
    }

    public int getIGoldBeanPrice() {
        return this.iGoldBeanPrice;
    }

    public void setIGoldBeanPrice(int i) {
        this.iGoldBeanPrice = i;
    }

    public int getISilverBeanPrice() {
        return this.iSilverBeanPrice;
    }

    public void setISilverBeanPrice(int i) {
        this.iSilverBeanPrice = i;
    }

    public String getSRulerDesc() {
        return this.sRulerDesc;
    }

    public void setSRulerDesc(String str) {
        this.sRulerDesc = str;
    }

    public int getIPhoneState() {
        return this.iPhoneState;
    }

    public void setIPhoneState(int i) {
        this.iPhoneState = i;
    }

    public long getLGoldBeanBalance() {
        return this.lGoldBeanBalance;
    }

    public void setLGoldBeanBalance(long j) {
        this.lGoldBeanBalance = j;
    }

    public long getLSilverBeanBalance() {
        return this.lSilverBeanBalance;
    }

    public void setLSilverBeanBalance(long j) {
        this.lSilverBeanBalance = j;
    }

    public int getIPiecePrice() {
        return this.iPiecePrice;
    }

    public void setIPiecePrice(int i) {
        this.iPiecePrice = i;
    }

    public long getLPieceBalance() {
        return this.lPieceBalance;
    }

    public void setLPieceBalance(long j) {
        this.lPieceBalance = j;
    }

    public String getSPieceDateLine() {
        return this.sPieceDateLine;
    }

    public void setSPieceDateLine(String str) {
        this.sPieceDateLine = str;
    }

    public UserNickStatusRsp() {
        setIModifyTimes(0);
        setIGoldBeanPrice(this.iGoldBeanPrice);
        setISilverBeanPrice(this.iSilverBeanPrice);
        setSRulerDesc(this.sRulerDesc);
        setIPhoneState(this.iPhoneState);
        setLGoldBeanBalance(this.lGoldBeanBalance);
        setLSilverBeanBalance(this.lSilverBeanBalance);
        setIPiecePrice(this.iPiecePrice);
        setLPieceBalance(this.lPieceBalance);
        setSPieceDateLine(this.sPieceDateLine);
    }

    public UserNickStatusRsp(int i, int i2, int i3, String str, int i4, long j, long j2, int i5, long j3, String str2) {
        setIModifyTimes(i);
        setIGoldBeanPrice(i2);
        setISilverBeanPrice(i3);
        setSRulerDesc(str);
        setIPhoneState(i4);
        setLGoldBeanBalance(j);
        setLSilverBeanBalance(j2);
        setIPiecePrice(i5);
        setLPieceBalance(j3);
        setSPieceDateLine(str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserNickStatusRsp userNickStatusRsp = (UserNickStatusRsp) obj;
        return JceUtil.equals(this.iModifyTimes, userNickStatusRsp.iModifyTimes) && JceUtil.equals(this.iGoldBeanPrice, userNickStatusRsp.iGoldBeanPrice) && JceUtil.equals(this.iSilverBeanPrice, userNickStatusRsp.iSilverBeanPrice) && JceUtil.equals(this.sRulerDesc, userNickStatusRsp.sRulerDesc) && JceUtil.equals(this.iPhoneState, userNickStatusRsp.iPhoneState) && JceUtil.equals(this.lGoldBeanBalance, userNickStatusRsp.lGoldBeanBalance) && JceUtil.equals(this.lSilverBeanBalance, userNickStatusRsp.lSilverBeanBalance) && JceUtil.equals(this.iPiecePrice, userNickStatusRsp.iPiecePrice) && JceUtil.equals(this.lPieceBalance, userNickStatusRsp.lPieceBalance) && JceUtil.equals(this.sPieceDateLine, userNickStatusRsp.sPieceDateLine);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.iModifyTimes), JceUtil.hashCode(this.iGoldBeanPrice), JceUtil.hashCode(this.iSilverBeanPrice), JceUtil.hashCode(this.sRulerDesc), JceUtil.hashCode(this.iPhoneState), JceUtil.hashCode(this.lGoldBeanBalance), JceUtil.hashCode(this.lSilverBeanBalance), JceUtil.hashCode(this.iPiecePrice), JceUtil.hashCode(this.lPieceBalance), JceUtil.hashCode(this.sPieceDateLine)});
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
        jceOutputStream.write(this.iModifyTimes, 0);
        jceOutputStream.write(this.iGoldBeanPrice, 1);
        jceOutputStream.write(this.iSilverBeanPrice, 2);
        String str = this.sRulerDesc;
        if (str != null) {
            jceOutputStream.write(str, 3);
        }
        jceOutputStream.write(this.iPhoneState, 4);
        jceOutputStream.write(this.lGoldBeanBalance, 5);
        jceOutputStream.write(this.lSilverBeanBalance, 6);
        jceOutputStream.write(this.iPiecePrice, 7);
        jceOutputStream.write(this.lPieceBalance, 8);
        String str2 = this.sPieceDateLine;
        if (str2 != null) {
            jceOutputStream.write(str2, 9);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIModifyTimes(jceInputStream.read(this.iModifyTimes, 0, false));
        setIGoldBeanPrice(jceInputStream.read(this.iGoldBeanPrice, 1, false));
        setISilverBeanPrice(jceInputStream.read(this.iSilverBeanPrice, 2, false));
        setSRulerDesc(jceInputStream.readString(3, false));
        setIPhoneState(jceInputStream.read(this.iPhoneState, 4, false));
        setLGoldBeanBalance(jceInputStream.read(this.lGoldBeanBalance, 5, false));
        setLSilverBeanBalance(jceInputStream.read(this.lSilverBeanBalance, 6, false));
        setIPiecePrice(jceInputStream.read(this.iPiecePrice, 7, false));
        setLPieceBalance(jceInputStream.read(this.lPieceBalance, 8, false));
        setSPieceDateLine(jceInputStream.readString(9, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iModifyTimes, "iModifyTimes");
        jceDisplayer.display(this.iGoldBeanPrice, "iGoldBeanPrice");
        jceDisplayer.display(this.iSilverBeanPrice, "iSilverBeanPrice");
        jceDisplayer.display(this.sRulerDesc, "sRulerDesc");
        jceDisplayer.display(this.iPhoneState, "iPhoneState");
        jceDisplayer.display(this.lGoldBeanBalance, "lGoldBeanBalance");
        jceDisplayer.display(this.lSilverBeanBalance, "lSilverBeanBalance");
        jceDisplayer.display(this.iPiecePrice, "iPiecePrice");
        jceDisplayer.display(this.lPieceBalance, "lPieceBalance");
        jceDisplayer.display(this.sPieceDateLine, "sPieceDateLine");
    }
}
