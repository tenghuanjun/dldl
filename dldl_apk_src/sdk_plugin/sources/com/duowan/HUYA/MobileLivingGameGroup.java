package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class MobileLivingGameGroup extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<MobileLivingGameSubGroup> cache_vSubGroup;
    public int iId = 0;
    public String sName = "";
    public ArrayList<MobileLivingGameSubGroup> vSubGroup = null;
    public int iIsEntertainment = 0;

    public String className() {
        return "HUYA.MobileLivingGameGroup";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.MobileLivingGameGroup";
    }

    public int getIId() {
        return this.iId;
    }

    public void setIId(int i) {
        this.iId = i;
    }

    public String getSName() {
        return this.sName;
    }

    public void setSName(String str) {
        this.sName = str;
    }

    public ArrayList<MobileLivingGameSubGroup> getVSubGroup() {
        return this.vSubGroup;
    }

    public void setVSubGroup(ArrayList<MobileLivingGameSubGroup> arrayList) {
        this.vSubGroup = arrayList;
    }

    public int getIIsEntertainment() {
        return this.iIsEntertainment;
    }

    public void setIIsEntertainment(int i) {
        this.iIsEntertainment = i;
    }

    public MobileLivingGameGroup() {
        setIId(0);
        setSName(this.sName);
        setVSubGroup(this.vSubGroup);
        setIIsEntertainment(this.iIsEntertainment);
    }

    public MobileLivingGameGroup(int i, String str, ArrayList<MobileLivingGameSubGroup> arrayList, int i2) {
        setIId(i);
        setSName(str);
        setVSubGroup(arrayList);
        setIIsEntertainment(i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MobileLivingGameGroup mobileLivingGameGroup = (MobileLivingGameGroup) obj;
        return JceUtil.equals(this.iId, mobileLivingGameGroup.iId) && JceUtil.equals(this.sName, mobileLivingGameGroup.sName) && JceUtil.equals(this.vSubGroup, mobileLivingGameGroup.vSubGroup) && JceUtil.equals(this.iIsEntertainment, mobileLivingGameGroup.iIsEntertainment);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.iId), JceUtil.hashCode(this.sName), JceUtil.hashCode(this.vSubGroup), JceUtil.hashCode(this.iIsEntertainment)});
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
        String str = this.sName;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        ArrayList<MobileLivingGameSubGroup> arrayList = this.vSubGroup;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 2);
        }
        jceOutputStream.write(this.iIsEntertainment, 3);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIId(jceInputStream.read(this.iId, 0, false));
        setSName(jceInputStream.readString(1, false));
        if (cache_vSubGroup == null) {
            cache_vSubGroup = new ArrayList<>();
            cache_vSubGroup.add(new MobileLivingGameSubGroup());
        }
        setVSubGroup((ArrayList) jceInputStream.read(cache_vSubGroup, 2, false));
        setIIsEntertainment(jceInputStream.read(this.iIsEntertainment, 3, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iId, "iId");
        jceDisplayer.display(this.sName, "sName");
        jceDisplayer.display((Collection) this.vSubGroup, "vSubGroup");
        jceDisplayer.display(this.iIsEntertainment, "iIsEntertainment");
    }
}
