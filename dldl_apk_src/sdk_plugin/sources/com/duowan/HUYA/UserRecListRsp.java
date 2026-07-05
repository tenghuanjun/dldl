package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class UserRecListRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<ActiveEventInfo> cache_vActiveEventInfo;
    static ArrayList<MAnnouncement> cache_vAnnouncements;
    static ArrayList<BannerItem> cache_vBanners;
    static ArrayList<FilterTag> cache_vChildFilterTags;
    static byte[] cache_vContext;
    static ArrayList<UserRecItem> cache_vItems;
    static ArrayList<LiveListRecGameItem> cache_vRecGame;
    public int iHasMore;
    public int iReset;
    public int iViewType;
    public ArrayList<ActiveEventInfo> vActiveEventInfo;
    public ArrayList<MAnnouncement> vAnnouncements;
    public ArrayList<BannerItem> vBanners;
    public ArrayList<FilterTag> vChildFilterTags;
    public byte[] vContext;
    public ArrayList<UserRecItem> vItems;
    public ArrayList<LiveListRecGameItem> vRecGame;

    public String className() {
        return "HUYA.UserRecListRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.UserRecListRsp";
    }

    public UserRecListRsp() {
        this.vContext = null;
        this.vItems = null;
        this.iReset = 0;
        this.vChildFilterTags = null;
        this.vBanners = null;
        this.iViewType = 0;
        this.vActiveEventInfo = null;
        this.vRecGame = null;
        this.vAnnouncements = null;
        this.iHasMore = 0;
    }

    public UserRecListRsp(byte[] bArr, ArrayList<UserRecItem> arrayList, int i, ArrayList<FilterTag> arrayList2, ArrayList<BannerItem> arrayList3, int i2, ArrayList<ActiveEventInfo> arrayList4, ArrayList<LiveListRecGameItem> arrayList5, ArrayList<MAnnouncement> arrayList6, int i3) {
        this.vContext = null;
        this.vItems = null;
        this.iReset = 0;
        this.vChildFilterTags = null;
        this.vBanners = null;
        this.iViewType = 0;
        this.vActiveEventInfo = null;
        this.vRecGame = null;
        this.vAnnouncements = null;
        this.iHasMore = 0;
        this.vContext = bArr;
        this.vItems = arrayList;
        this.iReset = i;
        this.vChildFilterTags = arrayList2;
        this.vBanners = arrayList3;
        this.iViewType = i2;
        this.vActiveEventInfo = arrayList4;
        this.vRecGame = arrayList5;
        this.vAnnouncements = arrayList6;
        this.iHasMore = i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserRecListRsp userRecListRsp = (UserRecListRsp) obj;
        return JceUtil.equals(this.vContext, userRecListRsp.vContext) && JceUtil.equals(this.vItems, userRecListRsp.vItems) && JceUtil.equals(this.iReset, userRecListRsp.iReset) && JceUtil.equals(this.vChildFilterTags, userRecListRsp.vChildFilterTags) && JceUtil.equals(this.vBanners, userRecListRsp.vBanners) && JceUtil.equals(this.iViewType, userRecListRsp.iViewType) && JceUtil.equals(this.vActiveEventInfo, userRecListRsp.vActiveEventInfo) && JceUtil.equals(this.vRecGame, userRecListRsp.vRecGame) && JceUtil.equals(this.vAnnouncements, userRecListRsp.vAnnouncements) && JceUtil.equals(this.iHasMore, userRecListRsp.iHasMore);
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
        byte[] bArr = this.vContext;
        if (bArr != null) {
            jceOutputStream.write(bArr, 0);
        }
        ArrayList<UserRecItem> arrayList = this.vItems;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 1);
        }
        jceOutputStream.write(this.iReset, 2);
        ArrayList<FilterTag> arrayList2 = this.vChildFilterTags;
        if (arrayList2 != null) {
            jceOutputStream.write((Collection) arrayList2, 3);
        }
        ArrayList<BannerItem> arrayList3 = this.vBanners;
        if (arrayList3 != null) {
            jceOutputStream.write((Collection) arrayList3, 4);
        }
        jceOutputStream.write(this.iViewType, 5);
        ArrayList<ActiveEventInfo> arrayList4 = this.vActiveEventInfo;
        if (arrayList4 != null) {
            jceOutputStream.write((Collection) arrayList4, 6);
        }
        ArrayList<LiveListRecGameItem> arrayList5 = this.vRecGame;
        if (arrayList5 != null) {
            jceOutputStream.write((Collection) arrayList5, 7);
        }
        ArrayList<MAnnouncement> arrayList6 = this.vAnnouncements;
        if (arrayList6 != null) {
            jceOutputStream.write((Collection) arrayList6, 8);
        }
        jceOutputStream.write(this.iHasMore, 9);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_vContext == null) {
            cache_vContext = new byte[]{0};
        }
        this.vContext = jceInputStream.read(cache_vContext, 0, false);
        if (cache_vItems == null) {
            cache_vItems = new ArrayList<>();
            cache_vItems.add(new UserRecItem());
        }
        this.vItems = (ArrayList) jceInputStream.read(cache_vItems, 1, false);
        this.iReset = jceInputStream.read(this.iReset, 2, false);
        if (cache_vChildFilterTags == null) {
            cache_vChildFilterTags = new ArrayList<>();
            cache_vChildFilterTags.add(new FilterTag());
        }
        this.vChildFilterTags = (ArrayList) jceInputStream.read(cache_vChildFilterTags, 3, false);
        if (cache_vBanners == null) {
            cache_vBanners = new ArrayList<>();
            cache_vBanners.add(new BannerItem());
        }
        this.vBanners = (ArrayList) jceInputStream.read(cache_vBanners, 4, false);
        this.iViewType = jceInputStream.read(this.iViewType, 5, false);
        if (cache_vActiveEventInfo == null) {
            cache_vActiveEventInfo = new ArrayList<>();
            cache_vActiveEventInfo.add(new ActiveEventInfo());
        }
        this.vActiveEventInfo = (ArrayList) jceInputStream.read(cache_vActiveEventInfo, 6, false);
        if (cache_vRecGame == null) {
            cache_vRecGame = new ArrayList<>();
            cache_vRecGame.add(new LiveListRecGameItem());
        }
        this.vRecGame = (ArrayList) jceInputStream.read(cache_vRecGame, 7, false);
        if (cache_vAnnouncements == null) {
            cache_vAnnouncements = new ArrayList<>();
            cache_vAnnouncements.add(new MAnnouncement());
        }
        this.vAnnouncements = (ArrayList) jceInputStream.read(cache_vAnnouncements, 8, false);
        this.iHasMore = jceInputStream.read(this.iHasMore, 9, false);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.vContext, "vContext");
        jceDisplayer.display((Collection) this.vItems, "vItems");
        jceDisplayer.display(this.iReset, "iReset");
        jceDisplayer.display((Collection) this.vChildFilterTags, "vChildFilterTags");
        jceDisplayer.display((Collection) this.vBanners, "vBanners");
        jceDisplayer.display(this.iViewType, "iViewType");
        jceDisplayer.display((Collection) this.vActiveEventInfo, "vActiveEventInfo");
        jceDisplayer.display((Collection) this.vRecGame, "vRecGame");
        jceDisplayer.display((Collection) this.vAnnouncements, "vAnnouncements");
        jceDisplayer.display(this.iHasMore, "iHasMore");
    }
}
