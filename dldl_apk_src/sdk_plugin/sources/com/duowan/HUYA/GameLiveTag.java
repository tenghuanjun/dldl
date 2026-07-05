package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GameLiveTag extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iTagId = 0;
    public String sTagName = "";
    public boolean bIsShow = true;

    public String className() {
        return "HUYA.GameLiveTag";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GameLiveTag";
    }

    public int getITagId() {
        return this.iTagId;
    }

    public void setITagId(int i) {
        this.iTagId = i;
    }

    public String getSTagName() {
        return this.sTagName;
    }

    public void setSTagName(String str) {
        this.sTagName = str;
    }

    public boolean getBIsShow() {
        return this.bIsShow;
    }

    public void setBIsShow(boolean z) {
        this.bIsShow = z;
    }

    public GameLiveTag() {
        setITagId(0);
        setSTagName(this.sTagName);
        setBIsShow(this.bIsShow);
    }

    public GameLiveTag(int i, String str, boolean z) {
        setITagId(i);
        setSTagName(str);
        setBIsShow(z);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        GameLiveTag gameLiveTag = (GameLiveTag) obj;
        return JceUtil.equals(this.iTagId, gameLiveTag.iTagId) && JceUtil.equals(this.sTagName, gameLiveTag.sTagName) && JceUtil.equals(this.bIsShow, gameLiveTag.bIsShow);
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
        jceOutputStream.write(this.iTagId, 0);
        String str = this.sTagName;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        jceOutputStream.write(this.bIsShow, 2);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setITagId(jceInputStream.read(this.iTagId, 0, false));
        setSTagName(jceInputStream.readString(1, false));
        setBIsShow(jceInputStream.read(this.bIsShow, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iTagId, "iTagId");
        jceDisplayer.display(this.sTagName, "sTagName");
        jceDisplayer.display(this.bIsShow, "bIsShow");
    }
}
