package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LiveAttributeInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static byte[] cache_vData;
    public byte[] vData = null;
    public long lTimespan = 0;
    public long lExpTimeMs = 0;

    public String className() {
        return "HUYA.LiveAttributeInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.LiveAttributeInfo";
    }

    public byte[] getVData() {
        return this.vData;
    }

    public void setVData(byte[] bArr) {
        this.vData = bArr;
    }

    public long getLTimespan() {
        return this.lTimespan;
    }

    public void setLTimespan(long j) {
        this.lTimespan = j;
    }

    public long getLExpTimeMs() {
        return this.lExpTimeMs;
    }

    public void setLExpTimeMs(long j) {
        this.lExpTimeMs = j;
    }

    public LiveAttributeInfo() {
        setVData(null);
        setLTimespan(this.lTimespan);
        setLExpTimeMs(this.lExpTimeMs);
    }

    public LiveAttributeInfo(byte[] bArr, long j, long j2) {
        setVData(bArr);
        setLTimespan(j);
        setLExpTimeMs(j2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LiveAttributeInfo liveAttributeInfo = (LiveAttributeInfo) obj;
        return JceUtil.equals(this.vData, liveAttributeInfo.vData) && JceUtil.equals(this.lTimespan, liveAttributeInfo.lTimespan) && JceUtil.equals(this.lExpTimeMs, liveAttributeInfo.lExpTimeMs);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.vData), JceUtil.hashCode(this.lTimespan), JceUtil.hashCode(this.lExpTimeMs)});
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
        byte[] bArr = this.vData;
        if (bArr != null) {
            jceOutputStream.write(bArr, 0);
        }
        jceOutputStream.write(this.lTimespan, 1);
        jceOutputStream.write(this.lExpTimeMs, 2);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_vData == null) {
            cache_vData = new byte[]{0};
        }
        setVData(jceInputStream.read(cache_vData, 0, false));
        setLTimespan(jceInputStream.read(this.lTimespan, 1, false));
        setLExpTimeMs(jceInputStream.read(this.lExpTimeMs, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.vData, "vData");
        jceDisplayer.display(this.lTimespan, "lTimespan");
        jceDisplayer.display(this.lExpTimeMs, "lExpTimeMs");
    }
}
