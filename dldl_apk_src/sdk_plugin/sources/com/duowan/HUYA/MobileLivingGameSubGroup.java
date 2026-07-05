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
public class MobileLivingGameSubGroup extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<MobileLivingGameInfo> cache_vGameData;
    public int iId = 0;
    public String sName = "";
    public ArrayList<MobileLivingGameInfo> vGameData = null;

    public String className() {
        return "HUYA.MobileLivingGameSubGroup";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.MobileLivingGameSubGroup";
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

    public ArrayList<MobileLivingGameInfo> getVGameData() {
        return this.vGameData;
    }

    public void setVGameData(ArrayList<MobileLivingGameInfo> arrayList) {
        this.vGameData = arrayList;
    }

    public MobileLivingGameSubGroup() {
        setIId(0);
        setSName(this.sName);
        setVGameData(this.vGameData);
    }

    public MobileLivingGameSubGroup(int i, String str, ArrayList<MobileLivingGameInfo> arrayList) {
        setIId(i);
        setSName(str);
        setVGameData(arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MobileLivingGameSubGroup mobileLivingGameSubGroup = (MobileLivingGameSubGroup) obj;
        return JceUtil.equals(this.iId, mobileLivingGameSubGroup.iId) && JceUtil.equals(this.sName, mobileLivingGameSubGroup.sName) && JceUtil.equals(this.vGameData, mobileLivingGameSubGroup.vGameData);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.iId), JceUtil.hashCode(this.sName), JceUtil.hashCode(this.vGameData)});
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
        ArrayList<MobileLivingGameInfo> arrayList = this.vGameData;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 3);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIId(jceInputStream.read(this.iId, 0, false));
        setSName(jceInputStream.readString(1, false));
        if (cache_vGameData == null) {
            cache_vGameData = new ArrayList<>();
            cache_vGameData.add(new MobileLivingGameInfo());
        }
        setVGameData((ArrayList) jceInputStream.read(cache_vGameData, 3, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iId, "iId");
        jceDisplayer.display(this.sName, "sName");
        jceDisplayer.display((Collection) this.vGameData, "vGameData");
    }
}
