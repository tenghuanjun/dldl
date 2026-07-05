package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class IsMutedRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public boolean bMuted = false;
    public int iMutedTime = 0;
    public long lAutoUnmutedTime = 0;
    public int iMutedType = 0;

    public String className() {
        return "HUYA.IsMutedRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.IsMutedRsp";
    }

    public boolean getBMuted() {
        return this.bMuted;
    }

    public void setBMuted(boolean z) {
        this.bMuted = z;
    }

    public int getIMutedTime() {
        return this.iMutedTime;
    }

    public void setIMutedTime(int i) {
        this.iMutedTime = i;
    }

    public long getLAutoUnmutedTime() {
        return this.lAutoUnmutedTime;
    }

    public void setLAutoUnmutedTime(long j) {
        this.lAutoUnmutedTime = j;
    }

    public int getIMutedType() {
        return this.iMutedType;
    }

    public void setIMutedType(int i) {
        this.iMutedType = i;
    }

    public IsMutedRsp() {
        setBMuted(false);
        setIMutedTime(this.iMutedTime);
        setLAutoUnmutedTime(this.lAutoUnmutedTime);
        setIMutedType(this.iMutedType);
    }

    public IsMutedRsp(boolean z, int i, long j, int i2) {
        setBMuted(z);
        setIMutedTime(i);
        setLAutoUnmutedTime(j);
        setIMutedType(i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        IsMutedRsp isMutedRsp = (IsMutedRsp) obj;
        return JceUtil.equals(this.bMuted, isMutedRsp.bMuted) && JceUtil.equals(this.iMutedTime, isMutedRsp.iMutedTime) && JceUtil.equals(this.lAutoUnmutedTime, isMutedRsp.lAutoUnmutedTime) && JceUtil.equals(this.iMutedType, isMutedRsp.iMutedType);
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
        jceOutputStream.write(this.bMuted, 0);
        jceOutputStream.write(this.iMutedTime, 1);
        jceOutputStream.write(this.lAutoUnmutedTime, 2);
        jceOutputStream.write(this.iMutedType, 3);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setBMuted(jceInputStream.read(this.bMuted, 0, false));
        setIMutedTime(jceInputStream.read(this.iMutedTime, 1, false));
        setLAutoUnmutedTime(jceInputStream.read(this.lAutoUnmutedTime, 2, false));
        setIMutedType(jceInputStream.read(this.iMutedType, 3, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.bMuted, "bMuted");
        jceDisplayer.display(this.iMutedTime, "iMutedTime");
        jceDisplayer.display(this.lAutoUnmutedTime, "lAutoUnmutedTime");
        jceDisplayer.display(this.iMutedType, "iMutedType");
    }
}
