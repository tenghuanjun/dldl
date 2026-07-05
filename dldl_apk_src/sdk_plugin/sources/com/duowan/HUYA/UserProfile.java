package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class UserProfile extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static PresenterBase cache_tPresenterBase;
    static GameLiveInfo cache_tRecentLive;
    static UserBase cache_tUserBase;
    public UserBase tUserBase = null;
    public PresenterBase tPresenterBase = null;
    public GameLiveInfo tRecentLive = null;

    public String className() {
        return "HUYA.UserProfile";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.UserProfile";
    }

    public UserBase getTUserBase() {
        return this.tUserBase;
    }

    public void setTUserBase(UserBase userBase) {
        this.tUserBase = userBase;
    }

    public PresenterBase getTPresenterBase() {
        return this.tPresenterBase;
    }

    public void setTPresenterBase(PresenterBase presenterBase) {
        this.tPresenterBase = presenterBase;
    }

    public GameLiveInfo getTRecentLive() {
        return this.tRecentLive;
    }

    public void setTRecentLive(GameLiveInfo gameLiveInfo) {
        this.tRecentLive = gameLiveInfo;
    }

    public UserProfile() {
        setTUserBase(null);
        setTPresenterBase(this.tPresenterBase);
        setTRecentLive(this.tRecentLive);
    }

    public UserProfile(UserBase userBase, PresenterBase presenterBase, GameLiveInfo gameLiveInfo) {
        setTUserBase(userBase);
        setTPresenterBase(presenterBase);
        setTRecentLive(gameLiveInfo);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserProfile userProfile = (UserProfile) obj;
        return JceUtil.equals(this.tUserBase, userProfile.tUserBase) && JceUtil.equals(this.tPresenterBase, userProfile.tPresenterBase) && JceUtil.equals(this.tRecentLive, userProfile.tRecentLive);
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
        UserBase userBase = this.tUserBase;
        if (userBase != null) {
            jceOutputStream.write((JceStruct) userBase, 0);
        }
        PresenterBase presenterBase = this.tPresenterBase;
        if (presenterBase != null) {
            jceOutputStream.write((JceStruct) presenterBase, 1);
        }
        GameLiveInfo gameLiveInfo = this.tRecentLive;
        if (gameLiveInfo != null) {
            jceOutputStream.write((JceStruct) gameLiveInfo, 2);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tUserBase == null) {
            cache_tUserBase = new UserBase();
        }
        setTUserBase((UserBase) jceInputStream.read((JceStruct) cache_tUserBase, 0, false));
        if (cache_tPresenterBase == null) {
            cache_tPresenterBase = new PresenterBase();
        }
        setTPresenterBase((PresenterBase) jceInputStream.read((JceStruct) cache_tPresenterBase, 1, false));
        if (cache_tRecentLive == null) {
            cache_tRecentLive = new GameLiveInfo();
        }
        setTRecentLive((GameLiveInfo) jceInputStream.read((JceStruct) cache_tRecentLive, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tUserBase, "tUserBase");
        jceDisplayer.display((JceStruct) this.tPresenterBase, "tPresenterBase");
        jceDisplayer.display((JceStruct) this.tRecentLive, "tRecentLive");
    }
}
