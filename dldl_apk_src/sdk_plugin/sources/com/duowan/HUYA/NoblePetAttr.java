package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class NoblePetAttr extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iPetId = 0;
    public String sPetName = "";
    public String sPetAction = "";
    public int iFrame = 0;
    public int iBeginTime = 0;
    public int iEndTime = 0;

    public String className() {
        return "HUYA.NoblePetAttr";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.NoblePetAttr";
    }

    public int getIPetId() {
        return this.iPetId;
    }

    public void setIPetId(int i) {
        this.iPetId = i;
    }

    public String getSPetName() {
        return this.sPetName;
    }

    public void setSPetName(String str) {
        this.sPetName = str;
    }

    public String getSPetAction() {
        return this.sPetAction;
    }

    public void setSPetAction(String str) {
        this.sPetAction = str;
    }

    public int getIFrame() {
        return this.iFrame;
    }

    public void setIFrame(int i) {
        this.iFrame = i;
    }

    public int getIBeginTime() {
        return this.iBeginTime;
    }

    public void setIBeginTime(int i) {
        this.iBeginTime = i;
    }

    public int getIEndTime() {
        return this.iEndTime;
    }

    public void setIEndTime(int i) {
        this.iEndTime = i;
    }

    public NoblePetAttr() {
        setIPetId(0);
        setSPetName(this.sPetName);
        setSPetAction(this.sPetAction);
        setIFrame(this.iFrame);
        setIBeginTime(this.iBeginTime);
        setIEndTime(this.iEndTime);
    }

    public NoblePetAttr(int i, String str, String str2, int i2, int i3, int i4) {
        setIPetId(i);
        setSPetName(str);
        setSPetAction(str2);
        setIFrame(i2);
        setIBeginTime(i3);
        setIEndTime(i4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NoblePetAttr noblePetAttr = (NoblePetAttr) obj;
        return JceUtil.equals(this.iPetId, noblePetAttr.iPetId) && JceUtil.equals(this.sPetName, noblePetAttr.sPetName) && JceUtil.equals(this.sPetAction, noblePetAttr.sPetAction) && JceUtil.equals(this.iFrame, noblePetAttr.iFrame) && JceUtil.equals(this.iBeginTime, noblePetAttr.iBeginTime) && JceUtil.equals(this.iEndTime, noblePetAttr.iEndTime);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.iPetId), JceUtil.hashCode(this.sPetName), JceUtil.hashCode(this.sPetAction), JceUtil.hashCode(this.iFrame), JceUtil.hashCode(this.iBeginTime), JceUtil.hashCode(this.iEndTime)});
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
        jceOutputStream.write(this.iPetId, 0);
        String str = this.sPetName;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        String str2 = this.sPetAction;
        if (str2 != null) {
            jceOutputStream.write(str2, 2);
        }
        jceOutputStream.write(this.iFrame, 3);
        jceOutputStream.write(this.iBeginTime, 4);
        jceOutputStream.write(this.iEndTime, 5);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIPetId(jceInputStream.read(this.iPetId, 0, false));
        setSPetName(jceInputStream.readString(1, false));
        setSPetAction(jceInputStream.readString(2, false));
        setIFrame(jceInputStream.read(this.iFrame, 3, false));
        setIBeginTime(jceInputStream.read(this.iBeginTime, 4, false));
        setIEndTime(jceInputStream.read(this.iEndTime, 5, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iPetId, "iPetId");
        jceDisplayer.display(this.sPetName, "sPetName");
        jceDisplayer.display(this.sPetAction, "sPetAction");
        jceDisplayer.display(this.iFrame, "iFrame");
        jceDisplayer.display(this.iBeginTime, "iBeginTime");
        jceDisplayer.display(this.iEndTime, "iEndTime");
    }
}
