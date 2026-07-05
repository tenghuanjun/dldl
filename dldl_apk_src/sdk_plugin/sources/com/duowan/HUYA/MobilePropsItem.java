package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class MobilePropsItem extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static AppIdentity cache_tAppIdentity;
    static MobileDisplayInfo cache_tDisplayInfo;
    static SpecialInfo cache_tSpecialInfo;
    static ArrayList<AppIdentity> cache_vAppIdentity;
    static ArrayList<Long> cache_vPresenterUid;
    static ArrayList<PropView> cache_vPropView;
    static ArrayList<Long> cache_vPropsChannel;
    static ArrayList<String> cache_vPropsNum;
    public int iPropsId = 0;
    public String sPropsName = "";
    public float fPropsYb = 0.0f;
    public float fPropsGreenBean = 0.0f;
    public float fPropsWhiteBean = 0.0f;
    public ArrayList<String> vPropsNum = null;
    public int iPropsMaxNum = 0;
    public int iPropsWeights = 0;
    public ArrayList<Long> vPropsChannel = null;
    public AppIdentity tAppIdentity = null;
    public MobileDisplayInfo tDisplayInfo = null;
    public int iTemplateType = 0;
    public short iBanned = 0;
    public int iPropsGrade = 0;
    public int iPropsGroupNum = 0;
    public int iShelfStatus = 0;
    public ArrayList<Long> vPresenterUid = null;
    public String sPropFaceu = "";
    public ArrayList<AppIdentity> vAppIdentity = null;
    public ArrayList<PropView> vPropView = null;
    public String sPropsToolTip = "";
    public SpecialInfo tSpecialInfo = null;
    public short iFaceUSwitch = 0;
    public short iDisplayCd = 0;
    public short iCount = 0;
    public int iVbCount = 0;

    public String className() {
        return "HUYA.MobilePropsItem";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.MobilePropsItem";
    }

    public int getIPropsId() {
        return this.iPropsId;
    }

    public void setIPropsId(int i) {
        this.iPropsId = i;
    }

    public String getSPropsName() {
        return this.sPropsName;
    }

    public void setSPropsName(String str) {
        this.sPropsName = str;
    }

    public float getFPropsYb() {
        return this.fPropsYb;
    }

    public void setFPropsYb(float f) {
        this.fPropsYb = f;
    }

    public float getFPropsGreenBean() {
        return this.fPropsGreenBean;
    }

    public void setFPropsGreenBean(float f) {
        this.fPropsGreenBean = f;
    }

    public float getFPropsWhiteBean() {
        return this.fPropsWhiteBean;
    }

    public void setFPropsWhiteBean(float f) {
        this.fPropsWhiteBean = f;
    }

    public ArrayList<String> getVPropsNum() {
        return this.vPropsNum;
    }

    public void setVPropsNum(ArrayList<String> arrayList) {
        this.vPropsNum = arrayList;
    }

    public int getIPropsMaxNum() {
        return this.iPropsMaxNum;
    }

    public void setIPropsMaxNum(int i) {
        this.iPropsMaxNum = i;
    }

    public int getIPropsWeights() {
        return this.iPropsWeights;
    }

    public void setIPropsWeights(int i) {
        this.iPropsWeights = i;
    }

    public ArrayList<Long> getVPropsChannel() {
        return this.vPropsChannel;
    }

    public void setVPropsChannel(ArrayList<Long> arrayList) {
        this.vPropsChannel = arrayList;
    }

    public AppIdentity getTAppIdentity() {
        return this.tAppIdentity;
    }

    public void setTAppIdentity(AppIdentity appIdentity) {
        this.tAppIdentity = appIdentity;
    }

    public MobileDisplayInfo getTDisplayInfo() {
        return this.tDisplayInfo;
    }

    public void setTDisplayInfo(MobileDisplayInfo mobileDisplayInfo) {
        this.tDisplayInfo = mobileDisplayInfo;
    }

    public int getITemplateType() {
        return this.iTemplateType;
    }

    public void setITemplateType(int i) {
        this.iTemplateType = i;
    }

    public short getIBanned() {
        return this.iBanned;
    }

    public void setIBanned(short s) {
        this.iBanned = s;
    }

    public int getIPropsGrade() {
        return this.iPropsGrade;
    }

    public void setIPropsGrade(int i) {
        this.iPropsGrade = i;
    }

    public int getIPropsGroupNum() {
        return this.iPropsGroupNum;
    }

    public void setIPropsGroupNum(int i) {
        this.iPropsGroupNum = i;
    }

    public int getIShelfStatus() {
        return this.iShelfStatus;
    }

    public void setIShelfStatus(int i) {
        this.iShelfStatus = i;
    }

    public ArrayList<Long> getVPresenterUid() {
        return this.vPresenterUid;
    }

    public void setVPresenterUid(ArrayList<Long> arrayList) {
        this.vPresenterUid = arrayList;
    }

    public String getSPropFaceu() {
        return this.sPropFaceu;
    }

    public void setSPropFaceu(String str) {
        this.sPropFaceu = str;
    }

    public ArrayList<AppIdentity> getVAppIdentity() {
        return this.vAppIdentity;
    }

    public void setVAppIdentity(ArrayList<AppIdentity> arrayList) {
        this.vAppIdentity = arrayList;
    }

    public ArrayList<PropView> getVPropView() {
        return this.vPropView;
    }

    public void setVPropView(ArrayList<PropView> arrayList) {
        this.vPropView = arrayList;
    }

    public String getSPropsToolTip() {
        return this.sPropsToolTip;
    }

    public void setSPropsToolTip(String str) {
        this.sPropsToolTip = str;
    }

    public SpecialInfo getTSpecialInfo() {
        return this.tSpecialInfo;
    }

    public void setTSpecialInfo(SpecialInfo specialInfo) {
        this.tSpecialInfo = specialInfo;
    }

    public short getIFaceUSwitch() {
        return this.iFaceUSwitch;
    }

    public void setIFaceUSwitch(short s) {
        this.iFaceUSwitch = s;
    }

    public short getIDisplayCd() {
        return this.iDisplayCd;
    }

    public void setIDisplayCd(short s) {
        this.iDisplayCd = s;
    }

    public short getICount() {
        return this.iCount;
    }

    public void setICount(short s) {
        this.iCount = s;
    }

    public int getIVbCount() {
        return this.iVbCount;
    }

    public void setIVbCount(int i) {
        this.iVbCount = i;
    }

    public MobilePropsItem() {
        setIPropsId(0);
        setSPropsName(this.sPropsName);
        setFPropsYb(this.fPropsYb);
        setFPropsGreenBean(this.fPropsGreenBean);
        setFPropsWhiteBean(this.fPropsWhiteBean);
        setVPropsNum(this.vPropsNum);
        setIPropsMaxNum(this.iPropsMaxNum);
        setIPropsWeights(this.iPropsWeights);
        setVPropsChannel(this.vPropsChannel);
        setTAppIdentity(this.tAppIdentity);
        setTDisplayInfo(this.tDisplayInfo);
        setITemplateType(this.iTemplateType);
        setIBanned(this.iBanned);
        setIPropsGrade(this.iPropsGrade);
        setIPropsGroupNum(this.iPropsGroupNum);
        setIShelfStatus(this.iShelfStatus);
        setVPresenterUid(this.vPresenterUid);
        setSPropFaceu(this.sPropFaceu);
        setVAppIdentity(this.vAppIdentity);
        setVPropView(this.vPropView);
        setSPropsToolTip(this.sPropsToolTip);
        setTSpecialInfo(this.tSpecialInfo);
        setIFaceUSwitch(this.iFaceUSwitch);
        setIDisplayCd(this.iDisplayCd);
        setICount(this.iCount);
        setIVbCount(this.iVbCount);
    }

    public MobilePropsItem(int i, String str, float f, float f2, float f3, ArrayList<String> arrayList, int i2, int i3, ArrayList<Long> arrayList2, AppIdentity appIdentity, MobileDisplayInfo mobileDisplayInfo, int i4, short s, int i5, int i6, int i7, ArrayList<Long> arrayList3, String str2, ArrayList<AppIdentity> arrayList4, ArrayList<PropView> arrayList5, String str3, SpecialInfo specialInfo, short s2, short s3, short s4, int i8) {
        setIPropsId(i);
        setSPropsName(str);
        setFPropsYb(f);
        setFPropsGreenBean(f2);
        setFPropsWhiteBean(f3);
        setVPropsNum(arrayList);
        setIPropsMaxNum(i2);
        setIPropsWeights(i3);
        setVPropsChannel(arrayList2);
        setTAppIdentity(appIdentity);
        setTDisplayInfo(mobileDisplayInfo);
        setITemplateType(i4);
        setIBanned(s);
        setIPropsGrade(i5);
        setIPropsGroupNum(i6);
        setIShelfStatus(i7);
        setVPresenterUid(arrayList3);
        setSPropFaceu(str2);
        setVAppIdentity(arrayList4);
        setVPropView(arrayList5);
        setSPropsToolTip(str3);
        setTSpecialInfo(specialInfo);
        setIFaceUSwitch(s2);
        setIDisplayCd(s3);
        setICount(s4);
        setIVbCount(i8);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MobilePropsItem mobilePropsItem = (MobilePropsItem) obj;
        return JceUtil.equals(this.iPropsId, mobilePropsItem.iPropsId) && JceUtil.equals(this.sPropsName, mobilePropsItem.sPropsName) && JceUtil.equals(this.fPropsYb, mobilePropsItem.fPropsYb) && JceUtil.equals(this.fPropsGreenBean, mobilePropsItem.fPropsGreenBean) && JceUtil.equals(this.fPropsWhiteBean, mobilePropsItem.fPropsWhiteBean) && JceUtil.equals(this.vPropsNum, mobilePropsItem.vPropsNum) && JceUtil.equals(this.iPropsMaxNum, mobilePropsItem.iPropsMaxNum) && JceUtil.equals(this.iPropsWeights, mobilePropsItem.iPropsWeights) && JceUtil.equals(this.vPropsChannel, mobilePropsItem.vPropsChannel) && JceUtil.equals(this.tAppIdentity, mobilePropsItem.tAppIdentity) && JceUtil.equals(this.tDisplayInfo, mobilePropsItem.tDisplayInfo) && JceUtil.equals(this.iTemplateType, mobilePropsItem.iTemplateType) && JceUtil.equals(this.iBanned, mobilePropsItem.iBanned) && JceUtil.equals(this.iPropsGrade, mobilePropsItem.iPropsGrade) && JceUtil.equals(this.iPropsGroupNum, mobilePropsItem.iPropsGroupNum) && JceUtil.equals(this.iShelfStatus, mobilePropsItem.iShelfStatus) && JceUtil.equals(this.vPresenterUid, mobilePropsItem.vPresenterUid) && JceUtil.equals(this.sPropFaceu, mobilePropsItem.sPropFaceu) && JceUtil.equals(this.vAppIdentity, mobilePropsItem.vAppIdentity) && JceUtil.equals(this.vPropView, mobilePropsItem.vPropView) && JceUtil.equals(this.sPropsToolTip, mobilePropsItem.sPropsToolTip) && JceUtil.equals(this.tSpecialInfo, mobilePropsItem.tSpecialInfo) && JceUtil.equals(this.iFaceUSwitch, mobilePropsItem.iFaceUSwitch) && JceUtil.equals(this.iDisplayCd, mobilePropsItem.iDisplayCd) && JceUtil.equals(this.iCount, mobilePropsItem.iCount) && JceUtil.equals(this.iVbCount, mobilePropsItem.iVbCount);
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
        jceOutputStream.write(this.iPropsId, 1);
        String str = this.sPropsName;
        if (str != null) {
            jceOutputStream.write(str, 2);
        }
        jceOutputStream.write(this.fPropsYb, 3);
        jceOutputStream.write(this.fPropsGreenBean, 4);
        jceOutputStream.write(this.fPropsWhiteBean, 5);
        ArrayList<String> arrayList = this.vPropsNum;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 6);
        }
        jceOutputStream.write(this.iPropsMaxNum, 7);
        jceOutputStream.write(this.iPropsWeights, 8);
        ArrayList<Long> arrayList2 = this.vPropsChannel;
        if (arrayList2 != null) {
            jceOutputStream.write((Collection) arrayList2, 9);
        }
        AppIdentity appIdentity = this.tAppIdentity;
        if (appIdentity != null) {
            jceOutputStream.write((JceStruct) appIdentity, 10);
        }
        MobileDisplayInfo mobileDisplayInfo = this.tDisplayInfo;
        if (mobileDisplayInfo != null) {
            jceOutputStream.write((JceStruct) mobileDisplayInfo, 11);
        }
        jceOutputStream.write(this.iTemplateType, 12);
        jceOutputStream.write(this.iBanned, 13);
        jceOutputStream.write(this.iPropsGrade, 14);
        jceOutputStream.write(this.iPropsGroupNum, 15);
        jceOutputStream.write(this.iShelfStatus, 16);
        ArrayList<Long> arrayList3 = this.vPresenterUid;
        if (arrayList3 != null) {
            jceOutputStream.write((Collection) arrayList3, 17);
        }
        String str2 = this.sPropFaceu;
        if (str2 != null) {
            jceOutputStream.write(str2, 18);
        }
        ArrayList<AppIdentity> arrayList4 = this.vAppIdentity;
        if (arrayList4 != null) {
            jceOutputStream.write((Collection) arrayList4, 19);
        }
        ArrayList<PropView> arrayList5 = this.vPropView;
        if (arrayList5 != null) {
            jceOutputStream.write((Collection) arrayList5, 20);
        }
        String str3 = this.sPropsToolTip;
        if (str3 != null) {
            jceOutputStream.write(str3, 21);
        }
        SpecialInfo specialInfo = this.tSpecialInfo;
        if (specialInfo != null) {
            jceOutputStream.write((JceStruct) specialInfo, 22);
        }
        jceOutputStream.write(this.iFaceUSwitch, 23);
        jceOutputStream.write(this.iDisplayCd, 24);
        jceOutputStream.write(this.iCount, 25);
        jceOutputStream.write(this.iVbCount, 26);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIPropsId(jceInputStream.read(this.iPropsId, 1, false));
        setSPropsName(jceInputStream.readString(2, false));
        setFPropsYb(jceInputStream.read(this.fPropsYb, 3, false));
        setFPropsGreenBean(jceInputStream.read(this.fPropsGreenBean, 4, false));
        setFPropsWhiteBean(jceInputStream.read(this.fPropsWhiteBean, 5, false));
        if (cache_vPropsNum == null) {
            ArrayList<String> arrayList = new ArrayList<>();
            cache_vPropsNum = arrayList;
            arrayList.add("");
        }
        setVPropsNum((ArrayList) jceInputStream.read(cache_vPropsNum, 6, false));
        setIPropsMaxNum(jceInputStream.read(this.iPropsMaxNum, 7, false));
        setIPropsWeights(jceInputStream.read(this.iPropsWeights, 8, false));
        if (cache_vPropsChannel == null) {
            cache_vPropsChannel = new ArrayList<>();
            cache_vPropsChannel.add(0L);
        }
        setVPropsChannel((ArrayList) jceInputStream.read(cache_vPropsChannel, 9, false));
        if (cache_tAppIdentity == null) {
            cache_tAppIdentity = new AppIdentity();
        }
        setTAppIdentity((AppIdentity) jceInputStream.read((JceStruct) cache_tAppIdentity, 10, false));
        if (cache_tDisplayInfo == null) {
            cache_tDisplayInfo = new MobileDisplayInfo();
        }
        setTDisplayInfo((MobileDisplayInfo) jceInputStream.read((JceStruct) cache_tDisplayInfo, 11, false));
        setITemplateType(jceInputStream.read(this.iTemplateType, 12, false));
        setIBanned(jceInputStream.read(this.iBanned, 13, false));
        setIPropsGrade(jceInputStream.read(this.iPropsGrade, 14, false));
        setIPropsGroupNum(jceInputStream.read(this.iPropsGroupNum, 15, false));
        setIShelfStatus(jceInputStream.read(this.iShelfStatus, 16, false));
        if (cache_vPresenterUid == null) {
            cache_vPresenterUid = new ArrayList<>();
            cache_vPresenterUid.add(0L);
        }
        setVPresenterUid((ArrayList) jceInputStream.read(cache_vPresenterUid, 17, false));
        setSPropFaceu(jceInputStream.readString(18, false));
        if (cache_vAppIdentity == null) {
            cache_vAppIdentity = new ArrayList<>();
            cache_vAppIdentity.add(new AppIdentity());
        }
        setVAppIdentity((ArrayList) jceInputStream.read(cache_vAppIdentity, 19, false));
        if (cache_vPropView == null) {
            cache_vPropView = new ArrayList<>();
            cache_vPropView.add(new PropView());
        }
        setVPropView((ArrayList) jceInputStream.read(cache_vPropView, 20, false));
        setSPropsToolTip(jceInputStream.readString(21, false));
        if (cache_tSpecialInfo == null) {
            cache_tSpecialInfo = new SpecialInfo();
        }
        setTSpecialInfo((SpecialInfo) jceInputStream.read((JceStruct) cache_tSpecialInfo, 22, false));
        setIFaceUSwitch(jceInputStream.read(this.iFaceUSwitch, 23, false));
        setIDisplayCd(jceInputStream.read(this.iDisplayCd, 24, false));
        setICount(jceInputStream.read(this.iCount, 25, false));
        setIVbCount(jceInputStream.read(this.iVbCount, 26, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iPropsId, "iPropsId");
        jceDisplayer.display(this.sPropsName, "sPropsName");
        jceDisplayer.display(this.fPropsYb, "fPropsYb");
        jceDisplayer.display(this.fPropsGreenBean, "fPropsGreenBean");
        jceDisplayer.display(this.fPropsWhiteBean, "fPropsWhiteBean");
        jceDisplayer.display((Collection) this.vPropsNum, "vPropsNum");
        jceDisplayer.display(this.iPropsMaxNum, "iPropsMaxNum");
        jceDisplayer.display(this.iPropsWeights, "iPropsWeights");
        jceDisplayer.display((Collection) this.vPropsChannel, "vPropsChannel");
        jceDisplayer.display((JceStruct) this.tAppIdentity, "tAppIdentity");
        jceDisplayer.display((JceStruct) this.tDisplayInfo, "tDisplayInfo");
        jceDisplayer.display(this.iTemplateType, "iTemplateType");
        jceDisplayer.display(this.iBanned, "iBanned");
        jceDisplayer.display(this.iPropsGrade, "iPropsGrade");
        jceDisplayer.display(this.iPropsGroupNum, "iPropsGroupNum");
        jceDisplayer.display(this.iShelfStatus, "iShelfStatus");
        jceDisplayer.display((Collection) this.vPresenterUid, "vPresenterUid");
        jceDisplayer.display(this.sPropFaceu, "sPropFaceu");
        jceDisplayer.display((Collection) this.vAppIdentity, "vAppIdentity");
        jceDisplayer.display((Collection) this.vPropView, "vPropView");
        jceDisplayer.display(this.sPropsToolTip, "sPropsToolTip");
        jceDisplayer.display((JceStruct) this.tSpecialInfo, "tSpecialInfo");
        jceDisplayer.display(this.iFaceUSwitch, "iFaceUSwitch");
        jceDisplayer.display(this.iDisplayCd, "iDisplayCd");
        jceDisplayer.display(this.iCount, "iCount");
        jceDisplayer.display(this.iVbCount, "iVbCount");
    }
}
