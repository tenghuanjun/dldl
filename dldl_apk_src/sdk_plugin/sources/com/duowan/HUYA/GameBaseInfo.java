package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GameBaseInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iCategory;
    public int iExeId;
    public int iId;
    public String sCategoryName;
    public String sFullName;
    public String sIcon;
    public String sShortName;

    public String className() {
        return "HUYA.GameBaseInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GameBaseInfo";
    }

    public GameBaseInfo() {
        this.iId = 0;
        this.sFullName = "";
        this.sShortName = "";
        this.sIcon = "";
        this.iCategory = 0;
        this.sCategoryName = "";
        this.iExeId = 0;
    }

    public GameBaseInfo(int i, String str, String str2, String str3, int i2, String str4, int i3) {
        this.iId = 0;
        this.sFullName = "";
        this.sShortName = "";
        this.sIcon = "";
        this.iCategory = 0;
        this.sCategoryName = "";
        this.iExeId = 0;
        this.iId = i;
        this.sFullName = str;
        this.sShortName = str2;
        this.sIcon = str3;
        this.iCategory = i2;
        this.sCategoryName = str4;
        this.iExeId = i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GameBaseInfo gameBaseInfo = (GameBaseInfo) obj;
        return JceUtil.equals(this.iId, gameBaseInfo.iId) && JceUtil.equals(this.sFullName, gameBaseInfo.sFullName) && JceUtil.equals(this.sShortName, gameBaseInfo.sShortName) && JceUtil.equals(this.sIcon, gameBaseInfo.sIcon) && JceUtil.equals(this.iCategory, gameBaseInfo.iCategory) && JceUtil.equals(this.sCategoryName, gameBaseInfo.sCategoryName) && JceUtil.equals(this.iExeId, gameBaseInfo.iExeId);
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
        jceOutputStream.write(this.iId, 0);
        String str = this.sFullName;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        String str2 = this.sShortName;
        if (str2 != null) {
            jceOutputStream.write(str2, 2);
        }
        String str3 = this.sIcon;
        if (str3 != null) {
            jceOutputStream.write(str3, 3);
        }
        jceOutputStream.write(this.iCategory, 4);
        String str4 = this.sCategoryName;
        if (str4 != null) {
            jceOutputStream.write(str4, 5);
        }
        jceOutputStream.write(this.iExeId, 6);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        this.iId = jceInputStream.read(this.iId, 0, false);
        this.sFullName = jceInputStream.readString(1, false);
        this.sShortName = jceInputStream.readString(2, false);
        this.sIcon = jceInputStream.readString(3, false);
        this.iCategory = jceInputStream.read(this.iCategory, 4, false);
        this.sCategoryName = jceInputStream.readString(5, false);
        this.iExeId = jceInputStream.read(this.iExeId, 6, false);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iId, "iId");
        jceDisplayer.display(this.sFullName, "sFullName");
        jceDisplayer.display(this.sShortName, "sShortName");
        jceDisplayer.display(this.sIcon, "sIcon");
        jceDisplayer.display(this.iCategory, "iCategory");
        jceDisplayer.display(this.sCategoryName, "sCategoryName");
        jceDisplayer.display(this.iExeId, "iExeId");
    }
}
