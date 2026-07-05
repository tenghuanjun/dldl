package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GetUserProfileRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserProfile cache_tUserProfile;
    public UserProfile tUserProfile = null;

    public String className() {
        return "HUYA.GetUserProfileRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetUserProfileRsp";
    }

    public UserProfile getTUserProfile() {
        return this.tUserProfile;
    }

    public void setTUserProfile(UserProfile userProfile) {
        this.tUserProfile = userProfile;
    }

    public GetUserProfileRsp() {
        setTUserProfile(null);
    }

    public GetUserProfileRsp(UserProfile userProfile) {
        setTUserProfile(userProfile);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return JceUtil.equals(this.tUserProfile, ((GetUserProfileRsp) obj).tUserProfile);
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
        UserProfile userProfile = this.tUserProfile;
        if (userProfile != null) {
            jceOutputStream.write((JceStruct) userProfile, 0);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tUserProfile == null) {
            cache_tUserProfile = new UserProfile();
        }
        setTUserProfile((UserProfile) jceInputStream.read((JceStruct) cache_tUserProfile, 0, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display((JceStruct) this.tUserProfile, "tUserProfile");
    }
}
