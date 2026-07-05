package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class PresenterBase extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<GameBaseInfo> cache_vPresentedGames;
    public int iIsPresenter = 0;
    public String sPresenterName = "";
    public long lSignedChannel = 0;
    public String sPrivateHost = "";
    public int iRecType = 0;
    public int iFreeze = 0;
    public int iPresenterLevel = 0;
    public long lPresenterExp = 0;
    public ArrayList<GameBaseInfo> vPresentedGames = null;
    public int iCertified = 0;
    public int iRoomId = 0;

    public String className() {
        return "HUYA.PresenterBase";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.PresenterBase";
    }

    public int getIIsPresenter() {
        return this.iIsPresenter;
    }

    public void setIIsPresenter(int i) {
        this.iIsPresenter = i;
    }

    public String getSPresenterName() {
        return this.sPresenterName;
    }

    public void setSPresenterName(String str) {
        this.sPresenterName = str;
    }

    public long getLSignedChannel() {
        return this.lSignedChannel;
    }

    public void setLSignedChannel(long j) {
        this.lSignedChannel = j;
    }

    public String getSPrivateHost() {
        return this.sPrivateHost;
    }

    public void setSPrivateHost(String str) {
        this.sPrivateHost = str;
    }

    public int getIRecType() {
        return this.iRecType;
    }

    public void setIRecType(int i) {
        this.iRecType = i;
    }

    public int getIFreeze() {
        return this.iFreeze;
    }

    public void setIFreeze(int i) {
        this.iFreeze = i;
    }

    public int getIPresenterLevel() {
        return this.iPresenterLevel;
    }

    public void setIPresenterLevel(int i) {
        this.iPresenterLevel = i;
    }

    public long getLPresenterExp() {
        return this.lPresenterExp;
    }

    public void setLPresenterExp(long j) {
        this.lPresenterExp = j;
    }

    public ArrayList<GameBaseInfo> getVPresentedGames() {
        return this.vPresentedGames;
    }

    public void setVPresentedGames(ArrayList<GameBaseInfo> arrayList) {
        this.vPresentedGames = arrayList;
    }

    public int getICertified() {
        return this.iCertified;
    }

    public void setICertified(int i) {
        this.iCertified = i;
    }

    public int getIRoomId() {
        return this.iRoomId;
    }

    public void setIRoomId(int i) {
        this.iRoomId = i;
    }

    public PresenterBase() {
        setIIsPresenter(0);
        setSPresenterName(this.sPresenterName);
        setLSignedChannel(this.lSignedChannel);
        setSPrivateHost(this.sPrivateHost);
        setIRecType(this.iRecType);
        setIFreeze(this.iFreeze);
        setIPresenterLevel(this.iPresenterLevel);
        setLPresenterExp(this.lPresenterExp);
        setVPresentedGames(this.vPresentedGames);
        setICertified(this.iCertified);
        setIRoomId(this.iRoomId);
    }

    public PresenterBase(int i, String str, long j, String str2, int i2, int i3, int i4, long j2, ArrayList<GameBaseInfo> arrayList, int i5, int i6) {
        setIIsPresenter(i);
        setSPresenterName(str);
        setLSignedChannel(j);
        setSPrivateHost(str2);
        setIRecType(i2);
        setIFreeze(i3);
        setIPresenterLevel(i4);
        setLPresenterExp(j2);
        setVPresentedGames(arrayList);
        setICertified(i5);
        setIRoomId(i6);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PresenterBase presenterBase = (PresenterBase) obj;
        return JceUtil.equals(this.iIsPresenter, presenterBase.iIsPresenter) && JceUtil.equals(this.sPresenterName, presenterBase.sPresenterName) && JceUtil.equals(this.lSignedChannel, presenterBase.lSignedChannel) && JceUtil.equals(this.sPrivateHost, presenterBase.sPrivateHost) && JceUtil.equals(this.iRecType, presenterBase.iRecType) && JceUtil.equals(this.iFreeze, presenterBase.iFreeze) && JceUtil.equals(this.iPresenterLevel, presenterBase.iPresenterLevel) && JceUtil.equals(this.lPresenterExp, presenterBase.lPresenterExp) && JceUtil.equals(this.vPresentedGames, presenterBase.vPresentedGames) && JceUtil.equals(this.iCertified, presenterBase.iCertified) && JceUtil.equals(this.iRoomId, presenterBase.iRoomId);
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
        jceOutputStream.write(this.iIsPresenter, 0);
        String str = this.sPresenterName;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        jceOutputStream.write(this.lSignedChannel, 2);
        String str2 = this.sPrivateHost;
        if (str2 != null) {
            jceOutputStream.write(str2, 3);
        }
        jceOutputStream.write(this.iRecType, 4);
        jceOutputStream.write(this.iFreeze, 5);
        jceOutputStream.write(this.iPresenterLevel, 6);
        jceOutputStream.write(this.lPresenterExp, 7);
        ArrayList<GameBaseInfo> arrayList = this.vPresentedGames;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 8);
        }
        jceOutputStream.write(this.iCertified, 9);
        jceOutputStream.write(this.iRoomId, 10);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIIsPresenter(jceInputStream.read(this.iIsPresenter, 0, false));
        setSPresenterName(jceInputStream.readString(1, false));
        setLSignedChannel(jceInputStream.read(this.lSignedChannel, 2, false));
        setSPrivateHost(jceInputStream.readString(3, false));
        setIRecType(jceInputStream.read(this.iRecType, 4, false));
        setIFreeze(jceInputStream.read(this.iFreeze, 5, false));
        setIPresenterLevel(jceInputStream.read(this.iPresenterLevel, 6, false));
        setLPresenterExp(jceInputStream.read(this.lPresenterExp, 7, false));
        if (cache_vPresentedGames == null) {
            cache_vPresentedGames = new ArrayList<>();
            cache_vPresentedGames.add(new GameBaseInfo());
        }
        setVPresentedGames((ArrayList) jceInputStream.read(cache_vPresentedGames, 8, false));
        setICertified(jceInputStream.read(this.iCertified, 9, false));
        setIRoomId(jceInputStream.read(this.iRoomId, 10, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iIsPresenter, "iIsPresenter");
        jceDisplayer.display(this.sPresenterName, "sPresenterName");
        jceDisplayer.display(this.lSignedChannel, "lSignedChannel");
        jceDisplayer.display(this.sPrivateHost, "sPrivateHost");
        jceDisplayer.display(this.iRecType, "iRecType");
        jceDisplayer.display(this.iFreeze, "iFreeze");
        jceDisplayer.display(this.iPresenterLevel, "iPresenterLevel");
        jceDisplayer.display(this.lPresenterExp, "lPresenterExp");
        jceDisplayer.display((Collection) this.vPresentedGames, "vPresentedGames");
        jceDisplayer.display(this.iCertified, "iCertified");
        jceDisplayer.display(this.iRoomId, "iRoomId");
    }
}
