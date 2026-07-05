package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class AttendeeCountNotice extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iAttendeeCount = 0;

    public String className() {
        return "HUYA.AttendeeCountNotice";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.AttendeeCountNotice";
    }

    public int getIAttendeeCount() {
        return this.iAttendeeCount;
    }

    public void setIAttendeeCount(int i) {
        this.iAttendeeCount = i;
    }

    public AttendeeCountNotice() {
        setIAttendeeCount(0);
    }

    public AttendeeCountNotice(int i) {
        setIAttendeeCount(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return JceUtil.equals(this.iAttendeeCount, ((AttendeeCountNotice) obj).iAttendeeCount);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.iAttendeeCount)});
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
        jceOutputStream.write(this.iAttendeeCount, 0);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIAttendeeCount(jceInputStream.read(this.iAttendeeCount, 0, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display(this.iAttendeeCount, "iAttendeeCount");
    }
}
