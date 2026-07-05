package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class AppIdentity extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public String sAndroid = "";
    public String sIphone = "";
    public String sIpad = "";
    public String sBasicColor = "";
    public String sNickColor = "";
    public String sNumberColor = "";
    public String sAndriodEffect = "";
    public String sIphoneEffect = "";
    public String sIpadEffect = "";
    public int sWitch = 0;
    public String scornerMark = "";
    public int iPropViewId = 0;
    public String sAndroidSign = "";
    public String sIphoneSign = "";
    public String sIpadSign = "";
    public String sAndroidFaceU = "";
    public String sIphoneFaceU = "";
    public String sPadFaceU = "";

    public String className() {
        return "HUYA.AppIdentity";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.AppIdentity";
    }

    public String getSAndroid() {
        return this.sAndroid;
    }

    public void setSAndroid(String str) {
        this.sAndroid = str;
    }

    public String getSIphone() {
        return this.sIphone;
    }

    public void setSIphone(String str) {
        this.sIphone = str;
    }

    public String getSIpad() {
        return this.sIpad;
    }

    public void setSIpad(String str) {
        this.sIpad = str;
    }

    public String getSBasicColor() {
        return this.sBasicColor;
    }

    public void setSBasicColor(String str) {
        this.sBasicColor = str;
    }

    public String getSNickColor() {
        return this.sNickColor;
    }

    public void setSNickColor(String str) {
        this.sNickColor = str;
    }

    public String getSNumberColor() {
        return this.sNumberColor;
    }

    public void setSNumberColor(String str) {
        this.sNumberColor = str;
    }

    public String getSAndriodEffect() {
        return this.sAndriodEffect;
    }

    public void setSAndriodEffect(String str) {
        this.sAndriodEffect = str;
    }

    public String getSIphoneEffect() {
        return this.sIphoneEffect;
    }

    public void setSIphoneEffect(String str) {
        this.sIphoneEffect = str;
    }

    public String getSIpadEffect() {
        return this.sIpadEffect;
    }

    public void setSIpadEffect(String str) {
        this.sIpadEffect = str;
    }

    public int getSWitch() {
        return this.sWitch;
    }

    public void setSWitch(int i) {
        this.sWitch = i;
    }

    public String getScornerMark() {
        return this.scornerMark;
    }

    public void setScornerMark(String str) {
        this.scornerMark = str;
    }

    public int getIPropViewId() {
        return this.iPropViewId;
    }

    public void setIPropViewId(int i) {
        this.iPropViewId = i;
    }

    public String getSAndroidSign() {
        return this.sAndroidSign;
    }

    public void setSAndroidSign(String str) {
        this.sAndroidSign = str;
    }

    public String getSIphoneSign() {
        return this.sIphoneSign;
    }

    public void setSIphoneSign(String str) {
        this.sIphoneSign = str;
    }

    public String getSIpadSign() {
        return this.sIpadSign;
    }

    public void setSIpadSign(String str) {
        this.sIpadSign = str;
    }

    public String getSAndroidFaceU() {
        return this.sAndroidFaceU;
    }

    public void setSAndroidFaceU(String str) {
        this.sAndroidFaceU = str;
    }

    public String getSIphoneFaceU() {
        return this.sIphoneFaceU;
    }

    public void setSIphoneFaceU(String str) {
        this.sIphoneFaceU = str;
    }

    public String getSPadFaceU() {
        return this.sPadFaceU;
    }

    public void setSPadFaceU(String str) {
        this.sPadFaceU = str;
    }

    public AppIdentity() {
        setSAndroid("");
        setSIphone(this.sIphone);
        setSIpad(this.sIpad);
        setSBasicColor(this.sBasicColor);
        setSNickColor(this.sNickColor);
        setSNumberColor(this.sNumberColor);
        setSAndriodEffect(this.sAndriodEffect);
        setSIphoneEffect(this.sIphoneEffect);
        setSIpadEffect(this.sIpadEffect);
        setSWitch(this.sWitch);
        setScornerMark(this.scornerMark);
        setIPropViewId(this.iPropViewId);
        setSAndroidSign(this.sAndroidSign);
        setSIphoneSign(this.sIphoneSign);
        setSIpadSign(this.sIpadSign);
        setSAndroidFaceU(this.sAndroidFaceU);
        setSIphoneFaceU(this.sIphoneFaceU);
        setSPadFaceU(this.sPadFaceU);
    }

    public AppIdentity(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i, String str10, int i2, String str11, String str12, String str13, String str14, String str15, String str16) {
        setSAndroid(str);
        setSIphone(str2);
        setSIpad(str3);
        setSBasicColor(str4);
        setSNickColor(str5);
        setSNumberColor(str6);
        setSAndriodEffect(str7);
        setSIphoneEffect(str8);
        setSIpadEffect(str9);
        setSWitch(i);
        setScornerMark(str10);
        setIPropViewId(i2);
        setSAndroidSign(str11);
        setSIphoneSign(str12);
        setSIpadSign(str13);
        setSAndroidFaceU(str14);
        setSIphoneFaceU(str15);
        setSPadFaceU(str16);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AppIdentity appIdentity = (AppIdentity) obj;
        return JceUtil.equals(this.sAndroid, appIdentity.sAndroid) && JceUtil.equals(this.sIphone, appIdentity.sIphone) && JceUtil.equals(this.sIpad, appIdentity.sIpad) && JceUtil.equals(this.sBasicColor, appIdentity.sBasicColor) && JceUtil.equals(this.sNickColor, appIdentity.sNickColor) && JceUtil.equals(this.sNumberColor, appIdentity.sNumberColor) && JceUtil.equals(this.sAndriodEffect, appIdentity.sAndriodEffect) && JceUtil.equals(this.sIphoneEffect, appIdentity.sIphoneEffect) && JceUtil.equals(this.sIpadEffect, appIdentity.sIpadEffect) && JceUtil.equals(this.sWitch, appIdentity.sWitch) && JceUtil.equals(this.scornerMark, appIdentity.scornerMark) && JceUtil.equals(this.iPropViewId, appIdentity.iPropViewId) && JceUtil.equals(this.sAndroidSign, appIdentity.sAndroidSign) && JceUtil.equals(this.sIphoneSign, appIdentity.sIphoneSign) && JceUtil.equals(this.sIpadSign, appIdentity.sIpadSign) && JceUtil.equals(this.sAndroidFaceU, appIdentity.sAndroidFaceU) && JceUtil.equals(this.sIphoneFaceU, appIdentity.sIphoneFaceU) && JceUtil.equals(this.sPadFaceU, appIdentity.sPadFaceU);
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
        String str = this.sAndroid;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        String str2 = this.sIphone;
        if (str2 != null) {
            jceOutputStream.write(str2, 2);
        }
        String str3 = this.sIpad;
        if (str3 != null) {
            jceOutputStream.write(str3, 3);
        }
        String str4 = this.sBasicColor;
        if (str4 != null) {
            jceOutputStream.write(str4, 4);
        }
        String str5 = this.sNickColor;
        if (str5 != null) {
            jceOutputStream.write(str5, 5);
        }
        String str6 = this.sNumberColor;
        if (str6 != null) {
            jceOutputStream.write(str6, 6);
        }
        String str7 = this.sAndriodEffect;
        if (str7 != null) {
            jceOutputStream.write(str7, 7);
        }
        String str8 = this.sIphoneEffect;
        if (str8 != null) {
            jceOutputStream.write(str8, 8);
        }
        String str9 = this.sIpadEffect;
        if (str9 != null) {
            jceOutputStream.write(str9, 9);
        }
        jceOutputStream.write(this.sWitch, 10);
        String str10 = this.scornerMark;
        if (str10 != null) {
            jceOutputStream.write(str10, 11);
        }
        jceOutputStream.write(this.iPropViewId, 12);
        String str11 = this.sAndroidSign;
        if (str11 != null) {
            jceOutputStream.write(str11, 14);
        }
        String str12 = this.sIphoneSign;
        if (str12 != null) {
            jceOutputStream.write(str12, 15);
        }
        String str13 = this.sIpadSign;
        if (str13 != null) {
            jceOutputStream.write(str13, 16);
        }
        String str14 = this.sAndroidFaceU;
        if (str14 != null) {
            jceOutputStream.write(str14, 17);
        }
        String str15 = this.sIphoneFaceU;
        if (str15 != null) {
            jceOutputStream.write(str15, 18);
        }
        String str16 = this.sPadFaceU;
        if (str16 != null) {
            jceOutputStream.write(str16, 19);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSAndroid(jceInputStream.readString(1, false));
        setSIphone(jceInputStream.readString(2, false));
        setSIpad(jceInputStream.readString(3, false));
        setSBasicColor(jceInputStream.readString(4, false));
        setSNickColor(jceInputStream.readString(5, false));
        setSNumberColor(jceInputStream.readString(6, false));
        setSAndriodEffect(jceInputStream.readString(7, false));
        setSIphoneEffect(jceInputStream.readString(8, false));
        setSIpadEffect(jceInputStream.readString(9, false));
        setSWitch(jceInputStream.read(this.sWitch, 10, false));
        setScornerMark(jceInputStream.readString(11, false));
        setIPropViewId(jceInputStream.read(this.iPropViewId, 12, false));
        setSAndroidSign(jceInputStream.readString(14, false));
        setSIphoneSign(jceInputStream.readString(15, false));
        setSIpadSign(jceInputStream.readString(16, false));
        setSAndroidFaceU(jceInputStream.readString(17, false));
        setSIphoneFaceU(jceInputStream.readString(18, false));
        setSPadFaceU(jceInputStream.readString(19, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sAndroid, "sAndroid");
        jceDisplayer.display(this.sIphone, "sIphone");
        jceDisplayer.display(this.sIpad, "sIpad");
        jceDisplayer.display(this.sBasicColor, "sBasicColor");
        jceDisplayer.display(this.sNickColor, "sNickColor");
        jceDisplayer.display(this.sNumberColor, "sNumberColor");
        jceDisplayer.display(this.sAndriodEffect, "sAndriodEffect");
        jceDisplayer.display(this.sIphoneEffect, "sIphoneEffect");
        jceDisplayer.display(this.sIpadEffect, "sIpadEffect");
        jceDisplayer.display(this.sWitch, "sWitch");
        jceDisplayer.display(this.scornerMark, "scornerMark");
        jceDisplayer.display(this.iPropViewId, "iPropViewId");
        jceDisplayer.display(this.sAndroidSign, "sAndroidSign");
        jceDisplayer.display(this.sIphoneSign, "sIphoneSign");
        jceDisplayer.display(this.sIpadSign, "sIpadSign");
        jceDisplayer.display(this.sAndroidFaceU, "sAndroidFaceU");
        jceDisplayer.display(this.sIphoneFaceU, "sIphoneFaceU");
        jceDisplayer.display(this.sPadFaceU, "sPadFaceU");
    }
}
