package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class StreamerNode extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public short iGiftLevel = 0;
    public short iStreamerLevel = 0;
    public short iMaterialType = 0;

    public String className() {
        return "HUYA.StreamerNode";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.StreamerNode";
    }

    public short getIGiftLevel() {
        return this.iGiftLevel;
    }

    public void setIGiftLevel(short s) {
        this.iGiftLevel = s;
    }

    public short getIStreamerLevel() {
        return this.iStreamerLevel;
    }

    public void setIStreamerLevel(short s) {
        this.iStreamerLevel = s;
    }

    public short getIMaterialType() {
        return this.iMaterialType;
    }

    public void setIMaterialType(short s) {
        this.iMaterialType = s;
    }

    public StreamerNode() {
        setIGiftLevel((short) 0);
        setIStreamerLevel(this.iStreamerLevel);
        setIMaterialType(this.iMaterialType);
    }

    public StreamerNode(short s, short s2, short s3) {
        setIGiftLevel(s);
        setIStreamerLevel(s2);
        setIMaterialType(s3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StreamerNode streamerNode = (StreamerNode) obj;
        return JceUtil.equals(this.iGiftLevel, streamerNode.iGiftLevel) && JceUtil.equals(this.iStreamerLevel, streamerNode.iStreamerLevel) && JceUtil.equals(this.iMaterialType, streamerNode.iMaterialType);
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
        jceOutputStream.write(this.iGiftLevel, 0);
        jceOutputStream.write(this.iStreamerLevel, 1);
        jceOutputStream.write(this.iMaterialType, 2);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIGiftLevel(jceInputStream.read(this.iGiftLevel, 0, false));
        setIStreamerLevel(jceInputStream.read(this.iStreamerLevel, 1, false));
        setIMaterialType(jceInputStream.read(this.iMaterialType, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iGiftLevel, "iGiftLevel");
        jceDisplayer.display(this.iStreamerLevel, "iStreamerLevel");
        jceDisplayer.display(this.iMaterialType, "iMaterialType");
    }
}
