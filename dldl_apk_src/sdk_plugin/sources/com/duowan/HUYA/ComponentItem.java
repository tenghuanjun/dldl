package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ComponentItem extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int heavy = 0;
    public String sTitle = "";
    public String sUrl = "";
    public boolean bRedIcon = true;
    public int iLoginStatus = 0;
    public String sIconUrl = "";
    public int iComID = 0;

    public String className() {
        return "HUYA.ComponentItem";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.ComponentItem";
    }

    public int getHeavy() {
        return this.heavy;
    }

    public void setHeavy(int i) {
        this.heavy = i;
    }

    public String getSTitle() {
        return this.sTitle;
    }

    public void setSTitle(String str) {
        this.sTitle = str;
    }

    public String getSUrl() {
        return this.sUrl;
    }

    public void setSUrl(String str) {
        this.sUrl = str;
    }

    public boolean getBRedIcon() {
        return this.bRedIcon;
    }

    public void setBRedIcon(boolean z) {
        this.bRedIcon = z;
    }

    public int getILoginStatus() {
        return this.iLoginStatus;
    }

    public void setILoginStatus(int i) {
        this.iLoginStatus = i;
    }

    public String getSIconUrl() {
        return this.sIconUrl;
    }

    public void setSIconUrl(String str) {
        this.sIconUrl = str;
    }

    public int getIComID() {
        return this.iComID;
    }

    public void setIComID(int i) {
        this.iComID = i;
    }

    public ComponentItem() {
        setHeavy(0);
        setSTitle(this.sTitle);
        setSUrl(this.sUrl);
        setBRedIcon(this.bRedIcon);
        setILoginStatus(this.iLoginStatus);
        setSIconUrl(this.sIconUrl);
        setIComID(this.iComID);
    }

    public ComponentItem(int i, String str, String str2, boolean z, int i2, String str3, int i3) {
        setHeavy(i);
        setSTitle(str);
        setSUrl(str2);
        setBRedIcon(z);
        setILoginStatus(i2);
        setSIconUrl(str3);
        setIComID(i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ComponentItem componentItem = (ComponentItem) obj;
        return JceUtil.equals(this.heavy, componentItem.heavy) && JceUtil.equals(this.sTitle, componentItem.sTitle) && JceUtil.equals(this.sUrl, componentItem.sUrl) && JceUtil.equals(this.bRedIcon, componentItem.bRedIcon) && JceUtil.equals(this.iLoginStatus, componentItem.iLoginStatus) && JceUtil.equals(this.sIconUrl, componentItem.sIconUrl) && JceUtil.equals(this.iComID, componentItem.iComID);
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
        jceOutputStream.write(this.heavy, 0);
        String str = this.sTitle;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        String str2 = this.sUrl;
        if (str2 != null) {
            jceOutputStream.write(str2, 2);
        }
        jceOutputStream.write(this.bRedIcon, 3);
        jceOutputStream.write(this.iLoginStatus, 4);
        String str3 = this.sIconUrl;
        if (str3 != null) {
            jceOutputStream.write(str3, 5);
        }
        jceOutputStream.write(this.iComID, 6);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setHeavy(jceInputStream.read(this.heavy, 0, false));
        setSTitle(jceInputStream.readString(1, false));
        setSUrl(jceInputStream.readString(2, false));
        setBRedIcon(jceInputStream.read(this.bRedIcon, 3, false));
        setILoginStatus(jceInputStream.read(this.iLoginStatus, 4, false));
        setSIconUrl(jceInputStream.readString(5, false));
        setIComID(jceInputStream.read(this.iComID, 6, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.heavy, "heavy");
        jceDisplayer.display(this.sTitle, "sTitle");
        jceDisplayer.display(this.sUrl, "sUrl");
        jceDisplayer.display(this.bRedIcon, "bRedIcon");
        jceDisplayer.display(this.iLoginStatus, "iLoginStatus");
        jceDisplayer.display(this.sIconUrl, "sIconUrl");
        jceDisplayer.display(this.iComID, "iComID");
    }
}
