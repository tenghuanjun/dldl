package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class UserRecItem extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<CornerMark> cache_vCornerMarks;
    static ArrayList<FilterTag> cache_vInterestTags;
    static ArrayList<SimpleStreamInfo> cache_vStreamInfo;
    static ArrayList<FilterTag> cache_vTags;
    public int iContentType;
    public int iViewType;
    public String sAction;
    public String sAvatar;
    public String sCoverUrl;
    public String sId;
    public String sNickName;
    public String sPreviewUrl;
    public String sSubTitle;
    public String sTitle;
    public String sTraceId;
    public ArrayList<CornerMark> vCornerMarks;
    public ArrayList<FilterTag> vInterestTags;
    public ArrayList<SimpleStreamInfo> vStreamInfo;
    public ArrayList<FilterTag> vTags;

    public String className() {
        return "HUYA.UserRecItem";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.UserRecItem";
    }

    public UserRecItem() {
        this.iContentType = 0;
        this.sAction = "";
        this.sCoverUrl = "";
        this.sTitle = "";
        this.sSubTitle = "";
        this.vCornerMarks = null;
        this.iViewType = 0;
        this.sAvatar = "";
        this.sPreviewUrl = "";
        this.sTraceId = "";
        this.vTags = null;
        this.sId = "";
        this.vInterestTags = null;
        this.sNickName = "";
        this.vStreamInfo = null;
    }

    public UserRecItem(int i, String str, String str2, String str3, String str4, ArrayList<CornerMark> arrayList, int i2, String str5, String str6, String str7, ArrayList<FilterTag> arrayList2, String str8, ArrayList<FilterTag> arrayList3, String str9, ArrayList<SimpleStreamInfo> arrayList4) {
        this.iContentType = 0;
        this.sAction = "";
        this.sCoverUrl = "";
        this.sTitle = "";
        this.sSubTitle = "";
        this.vCornerMarks = null;
        this.iViewType = 0;
        this.sAvatar = "";
        this.sPreviewUrl = "";
        this.sTraceId = "";
        this.vTags = null;
        this.sId = "";
        this.vInterestTags = null;
        this.sNickName = "";
        this.vStreamInfo = null;
        this.iContentType = i;
        this.sAction = str;
        this.sCoverUrl = str2;
        this.sTitle = str3;
        this.sSubTitle = str4;
        this.vCornerMarks = arrayList;
        this.iViewType = i2;
        this.sAvatar = str5;
        this.sPreviewUrl = str6;
        this.sTraceId = str7;
        this.vTags = arrayList2;
        this.sId = str8;
        this.vInterestTags = arrayList3;
        this.sNickName = str9;
        this.vStreamInfo = arrayList4;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserRecItem userRecItem = (UserRecItem) obj;
        return JceUtil.equals(this.iContentType, userRecItem.iContentType) && JceUtil.equals(this.sAction, userRecItem.sAction) && JceUtil.equals(this.sCoverUrl, userRecItem.sCoverUrl) && JceUtil.equals(this.sTitle, userRecItem.sTitle) && JceUtil.equals(this.sSubTitle, userRecItem.sSubTitle) && JceUtil.equals(this.vCornerMarks, userRecItem.vCornerMarks) && JceUtil.equals(this.iViewType, userRecItem.iViewType) && JceUtil.equals(this.sAvatar, userRecItem.sAvatar) && JceUtil.equals(this.sPreviewUrl, userRecItem.sPreviewUrl) && JceUtil.equals(this.sTraceId, userRecItem.sTraceId) && JceUtil.equals(this.vTags, userRecItem.vTags) && JceUtil.equals(this.sId, userRecItem.sId) && JceUtil.equals(this.vInterestTags, userRecItem.vInterestTags) && JceUtil.equals(this.sNickName, userRecItem.sNickName) && JceUtil.equals(this.vStreamInfo, userRecItem.vStreamInfo);
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
        jceOutputStream.write(this.iContentType, 0);
        String str = this.sAction;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        String str2 = this.sCoverUrl;
        if (str2 != null) {
            jceOutputStream.write(str2, 2);
        }
        String str3 = this.sTitle;
        if (str3 != null) {
            jceOutputStream.write(str3, 3);
        }
        String str4 = this.sSubTitle;
        if (str4 != null) {
            jceOutputStream.write(str4, 4);
        }
        ArrayList<CornerMark> arrayList = this.vCornerMarks;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 5);
        }
        jceOutputStream.write(this.iViewType, 6);
        String str5 = this.sAvatar;
        if (str5 != null) {
            jceOutputStream.write(str5, 7);
        }
        String str6 = this.sPreviewUrl;
        if (str6 != null) {
            jceOutputStream.write(str6, 8);
        }
        String str7 = this.sTraceId;
        if (str7 != null) {
            jceOutputStream.write(str7, 9);
        }
        ArrayList<FilterTag> arrayList2 = this.vTags;
        if (arrayList2 != null) {
            jceOutputStream.write((Collection) arrayList2, 10);
        }
        String str8 = this.sId;
        if (str8 != null) {
            jceOutputStream.write(str8, 11);
        }
        ArrayList<FilterTag> arrayList3 = this.vInterestTags;
        if (arrayList3 != null) {
            jceOutputStream.write((Collection) arrayList3, 12);
        }
        String str9 = this.sNickName;
        if (str9 != null) {
            jceOutputStream.write(str9, 13);
        }
        ArrayList<SimpleStreamInfo> arrayList4 = this.vStreamInfo;
        if (arrayList4 != null) {
            jceOutputStream.write((Collection) arrayList4, 15);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        this.iContentType = jceInputStream.read(this.iContentType, 0, false);
        this.sAction = jceInputStream.readString(1, false);
        this.sCoverUrl = jceInputStream.readString(2, false);
        this.sTitle = jceInputStream.readString(3, false);
        this.sSubTitle = jceInputStream.readString(4, false);
        if (cache_vCornerMarks == null) {
            cache_vCornerMarks = new ArrayList<>();
            cache_vCornerMarks.add(new CornerMark());
        }
        this.vCornerMarks = (ArrayList) jceInputStream.read(cache_vCornerMarks, 5, false);
        this.iViewType = jceInputStream.read(this.iViewType, 6, false);
        this.sAvatar = jceInputStream.readString(7, false);
        this.sPreviewUrl = jceInputStream.readString(8, false);
        this.sTraceId = jceInputStream.readString(9, false);
        if (cache_vTags == null) {
            cache_vTags = new ArrayList<>();
            cache_vTags.add(new FilterTag());
        }
        this.vTags = (ArrayList) jceInputStream.read(cache_vTags, 10, false);
        this.sId = jceInputStream.readString(11, false);
        if (cache_vInterestTags == null) {
            cache_vInterestTags = new ArrayList<>();
            cache_vInterestTags.add(new FilterTag());
        }
        this.vInterestTags = (ArrayList) jceInputStream.read(cache_vInterestTags, 12, false);
        this.sNickName = jceInputStream.readString(13, false);
        if (cache_vStreamInfo == null) {
            cache_vStreamInfo = new ArrayList<>();
            cache_vStreamInfo.add(new SimpleStreamInfo());
        }
        this.vStreamInfo = (ArrayList) jceInputStream.read(cache_vStreamInfo, 15, false);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iContentType, "iContentType");
        jceDisplayer.display(this.sAction, "sAction");
        jceDisplayer.display(this.sCoverUrl, "sCoverUrl");
        jceDisplayer.display(this.sTitle, "sTitle");
        jceDisplayer.display(this.sSubTitle, "sSubTitle");
        jceDisplayer.display((Collection) this.vCornerMarks, "vCornerMarks");
        jceDisplayer.display(this.iViewType, "iViewType");
        jceDisplayer.display(this.sAvatar, "sAvatar");
        jceDisplayer.display(this.sPreviewUrl, "sPreviewUrl");
        jceDisplayer.display(this.sTraceId, "sTraceId");
        jceDisplayer.display((Collection) this.vTags, "vTags");
        jceDisplayer.display(this.sId, "sId");
        jceDisplayer.display((Collection) this.vInterestTags, "vInterestTags");
        jceDisplayer.display(this.sNickName, "sNickName");
        jceDisplayer.display((Collection) this.vStreamInfo, "vStreamInfo");
    }
}
