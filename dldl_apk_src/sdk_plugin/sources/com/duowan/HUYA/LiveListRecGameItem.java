package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class LiveListRecGameItem extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iGameId;
    public String sAction;
    public String sGameName;
    public String sImageUrl;

    public String className() {
        return "HUYA.LiveListRecGameItem";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.LiveListRecGameItem";
    }

    public LiveListRecGameItem() {
        this.iGameId = 0;
        this.sAction = "";
        this.sImageUrl = "";
        this.sGameName = "";
    }

    public LiveListRecGameItem(int i, String str, String str2, String str3) {
        this.iGameId = 0;
        this.sAction = "";
        this.sImageUrl = "";
        this.sGameName = "";
        this.iGameId = i;
        this.sAction = str;
        this.sImageUrl = str2;
        this.sGameName = str3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LiveListRecGameItem liveListRecGameItem = (LiveListRecGameItem) obj;
        return JceUtil.equals(this.iGameId, liveListRecGameItem.iGameId) && JceUtil.equals(this.sAction, liveListRecGameItem.sAction) && JceUtil.equals(this.sImageUrl, liveListRecGameItem.sImageUrl) && JceUtil.equals(this.sGameName, liveListRecGameItem.sGameName);
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
        jceOutputStream.write(this.iGameId, 0);
        String str = this.sAction;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        String str2 = this.sImageUrl;
        if (str2 != null) {
            jceOutputStream.write(str2, 2);
        }
        String str3 = this.sGameName;
        if (str3 != null) {
            jceOutputStream.write(str3, 3);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        this.iGameId = jceInputStream.read(this.iGameId, 0, false);
        this.sAction = jceInputStream.readString(1, false);
        this.sImageUrl = jceInputStream.readString(2, false);
        this.sGameName = jceInputStream.readString(3, false);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iGameId, "iGameId");
        jceDisplayer.display(this.sAction, "sAction");
        jceDisplayer.display(this.sImageUrl, "sImageUrl");
        jceDisplayer.display(this.sGameName, "sGameName");
    }
}
