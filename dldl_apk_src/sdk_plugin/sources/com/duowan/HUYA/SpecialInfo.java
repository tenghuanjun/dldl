package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class SpecialInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iFirstSingle = 0;
    public int iFirstGroup = 0;
    public String sFirstTips = "";
    public int iSecondSingle = 0;
    public int iSecondGroup = 0;
    public String sSecondTips = "";
    public int iThirdSingle = 0;
    public int iThirdGroup = 0;
    public String sThirdTips = "";
    public int iWorldSingle = 0;
    public int iWorldGroup = 0;

    public String className() {
        return "HUYA.SpecialInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.SpecialInfo";
    }

    public int getIFirstSingle() {
        return this.iFirstSingle;
    }

    public void setIFirstSingle(int i) {
        this.iFirstSingle = i;
    }

    public int getIFirstGroup() {
        return this.iFirstGroup;
    }

    public void setIFirstGroup(int i) {
        this.iFirstGroup = i;
    }

    public String getSFirstTips() {
        return this.sFirstTips;
    }

    public void setSFirstTips(String str) {
        this.sFirstTips = str;
    }

    public int getISecondSingle() {
        return this.iSecondSingle;
    }

    public void setISecondSingle(int i) {
        this.iSecondSingle = i;
    }

    public int getISecondGroup() {
        return this.iSecondGroup;
    }

    public void setISecondGroup(int i) {
        this.iSecondGroup = i;
    }

    public String getSSecondTips() {
        return this.sSecondTips;
    }

    public void setSSecondTips(String str) {
        this.sSecondTips = str;
    }

    public int getIThirdSingle() {
        return this.iThirdSingle;
    }

    public void setIThirdSingle(int i) {
        this.iThirdSingle = i;
    }

    public int getIThirdGroup() {
        return this.iThirdGroup;
    }

    public void setIThirdGroup(int i) {
        this.iThirdGroup = i;
    }

    public String getSThirdTips() {
        return this.sThirdTips;
    }

    public void setSThirdTips(String str) {
        this.sThirdTips = str;
    }

    public int getIWorldSingle() {
        return this.iWorldSingle;
    }

    public void setIWorldSingle(int i) {
        this.iWorldSingle = i;
    }

    public int getIWorldGroup() {
        return this.iWorldGroup;
    }

    public void setIWorldGroup(int i) {
        this.iWorldGroup = i;
    }

    public SpecialInfo() {
        setIFirstSingle(0);
        setIFirstGroup(this.iFirstGroup);
        setSFirstTips(this.sFirstTips);
        setISecondSingle(this.iSecondSingle);
        setISecondGroup(this.iSecondGroup);
        setSSecondTips(this.sSecondTips);
        setIThirdSingle(this.iThirdSingle);
        setIThirdGroup(this.iThirdGroup);
        setSThirdTips(this.sThirdTips);
        setIWorldSingle(this.iWorldSingle);
        setIWorldGroup(this.iWorldGroup);
    }

    public SpecialInfo(int i, int i2, String str, int i3, int i4, String str2, int i5, int i6, String str3, int i7, int i8) {
        setIFirstSingle(i);
        setIFirstGroup(i2);
        setSFirstTips(str);
        setISecondSingle(i3);
        setISecondGroup(i4);
        setSSecondTips(str2);
        setIThirdSingle(i5);
        setIThirdGroup(i6);
        setSThirdTips(str3);
        setIWorldSingle(i7);
        setIWorldGroup(i8);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SpecialInfo specialInfo = (SpecialInfo) obj;
        return JceUtil.equals(this.iFirstSingle, specialInfo.iFirstSingle) && JceUtil.equals(this.iFirstGroup, specialInfo.iFirstGroup) && JceUtil.equals(this.sFirstTips, specialInfo.sFirstTips) && JceUtil.equals(this.iSecondSingle, specialInfo.iSecondSingle) && JceUtil.equals(this.iSecondGroup, specialInfo.iSecondGroup) && JceUtil.equals(this.sSecondTips, specialInfo.sSecondTips) && JceUtil.equals(this.iThirdSingle, specialInfo.iThirdSingle) && JceUtil.equals(this.iThirdGroup, specialInfo.iThirdGroup) && JceUtil.equals(this.sThirdTips, specialInfo.sThirdTips) && JceUtil.equals(this.iWorldSingle, specialInfo.iWorldSingle) && JceUtil.equals(this.iWorldGroup, specialInfo.iWorldGroup);
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
        jceOutputStream.write(this.iFirstSingle, 1);
        jceOutputStream.write(this.iFirstGroup, 2);
        String str = this.sFirstTips;
        if (str != null) {
            jceOutputStream.write(str, 3);
        }
        jceOutputStream.write(this.iSecondSingle, 4);
        jceOutputStream.write(this.iSecondGroup, 5);
        String str2 = this.sSecondTips;
        if (str2 != null) {
            jceOutputStream.write(str2, 6);
        }
        jceOutputStream.write(this.iThirdSingle, 7);
        jceOutputStream.write(this.iThirdGroup, 8);
        String str3 = this.sThirdTips;
        if (str3 != null) {
            jceOutputStream.write(str3, 9);
        }
        jceOutputStream.write(this.iWorldSingle, 10);
        jceOutputStream.write(this.iWorldGroup, 11);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIFirstSingle(jceInputStream.read(this.iFirstSingle, 1, false));
        setIFirstGroup(jceInputStream.read(this.iFirstGroup, 2, false));
        setSFirstTips(jceInputStream.readString(3, false));
        setISecondSingle(jceInputStream.read(this.iSecondSingle, 4, false));
        setISecondGroup(jceInputStream.read(this.iSecondGroup, 5, false));
        setSSecondTips(jceInputStream.readString(6, false));
        setIThirdSingle(jceInputStream.read(this.iThirdSingle, 7, false));
        setIThirdGroup(jceInputStream.read(this.iThirdGroup, 8, false));
        setSThirdTips(jceInputStream.readString(9, false));
        setIWorldSingle(jceInputStream.read(this.iWorldSingle, 10, false));
        setIWorldGroup(jceInputStream.read(this.iWorldGroup, 11, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iFirstSingle, "iFirstSingle");
        jceDisplayer.display(this.iFirstGroup, "iFirstGroup");
        jceDisplayer.display(this.sFirstTips, "sFirstTips");
        jceDisplayer.display(this.iSecondSingle, "iSecondSingle");
        jceDisplayer.display(this.iSecondGroup, "iSecondGroup");
        jceDisplayer.display(this.sSecondTips, "sSecondTips");
        jceDisplayer.display(this.iThirdSingle, "iThirdSingle");
        jceDisplayer.display(this.iThirdGroup, "iThirdGroup");
        jceDisplayer.display(this.sThirdTips, "sThirdTips");
        jceDisplayer.display(this.iWorldSingle, "iWorldSingle");
        jceDisplayer.display(this.iWorldGroup, "iWorldGroup");
    }
}
