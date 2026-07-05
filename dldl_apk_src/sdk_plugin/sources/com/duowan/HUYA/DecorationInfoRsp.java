package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class DecorationInfoRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static BulletFormat cache_tBulletFormat;
    static ContentFormat cache_tFormat;
    static SenderInfo cache_tUserInfo;
    static ArrayList<DecorationInfo> cache_vBulletPrefix;
    static ArrayList<DecorationInfo> cache_vDecorationPrefix;
    static ArrayList<DecorationInfo> cache_vDecorationSuffix;
    static ArrayList<ChannelPair1> cache_vForwardChannels;
    public ArrayList<DecorationInfo> vDecorationPrefix = null;
    public ArrayList<DecorationInfo> vDecorationSuffix = null;
    public ContentFormat tFormat = null;
    public BulletFormat tBulletFormat = null;
    public ArrayList<ChannelPair1> vForwardChannels = null;
    public int iModifyMask = 0;
    public ArrayList<DecorationInfo> vBulletPrefix = null;
    public SenderInfo tUserInfo = null;

    public String className() {
        return "HUYA.DecorationInfoRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.DecorationInfoRsp";
    }

    public ArrayList<DecorationInfo> getVDecorationPrefix() {
        return this.vDecorationPrefix;
    }

    public void setVDecorationPrefix(ArrayList<DecorationInfo> arrayList) {
        this.vDecorationPrefix = arrayList;
    }

    public ArrayList<DecorationInfo> getVDecorationSuffix() {
        return this.vDecorationSuffix;
    }

    public void setVDecorationSuffix(ArrayList<DecorationInfo> arrayList) {
        this.vDecorationSuffix = arrayList;
    }

    public ContentFormat getTFormat() {
        return this.tFormat;
    }

    public void setTFormat(ContentFormat contentFormat) {
        this.tFormat = contentFormat;
    }

    public BulletFormat getTBulletFormat() {
        return this.tBulletFormat;
    }

    public void setTBulletFormat(BulletFormat bulletFormat) {
        this.tBulletFormat = bulletFormat;
    }

    public ArrayList<ChannelPair1> getVForwardChannels() {
        return this.vForwardChannels;
    }

    public void setVForwardChannels(ArrayList<ChannelPair1> arrayList) {
        this.vForwardChannels = arrayList;
    }

    public int getIModifyMask() {
        return this.iModifyMask;
    }

    public void setIModifyMask(int i) {
        this.iModifyMask = i;
    }

    public ArrayList<DecorationInfo> getVBulletPrefix() {
        return this.vBulletPrefix;
    }

    public void setVBulletPrefix(ArrayList<DecorationInfo> arrayList) {
        this.vBulletPrefix = arrayList;
    }

    public SenderInfo getTUserInfo() {
        return this.tUserInfo;
    }

    public void setTUserInfo(SenderInfo senderInfo) {
        this.tUserInfo = senderInfo;
    }

    public DecorationInfoRsp() {
        setVDecorationPrefix(null);
        setVDecorationSuffix(this.vDecorationSuffix);
        setTFormat(this.tFormat);
        setTBulletFormat(this.tBulletFormat);
        setVForwardChannels(this.vForwardChannels);
        setIModifyMask(this.iModifyMask);
        setVBulletPrefix(this.vBulletPrefix);
        setTUserInfo(this.tUserInfo);
    }

    public DecorationInfoRsp(ArrayList<DecorationInfo> arrayList, ArrayList<DecorationInfo> arrayList2, ContentFormat contentFormat, BulletFormat bulletFormat, ArrayList<ChannelPair1> arrayList3, int i, ArrayList<DecorationInfo> arrayList4, SenderInfo senderInfo) {
        setVDecorationPrefix(arrayList);
        setVDecorationSuffix(arrayList2);
        setTFormat(contentFormat);
        setTBulletFormat(bulletFormat);
        setVForwardChannels(arrayList3);
        setIModifyMask(i);
        setVBulletPrefix(arrayList4);
        setTUserInfo(senderInfo);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DecorationInfoRsp decorationInfoRsp = (DecorationInfoRsp) obj;
        return JceUtil.equals(this.vDecorationPrefix, decorationInfoRsp.vDecorationPrefix) && JceUtil.equals(this.vDecorationSuffix, decorationInfoRsp.vDecorationSuffix) && JceUtil.equals(this.tFormat, decorationInfoRsp.tFormat) && JceUtil.equals(this.tBulletFormat, decorationInfoRsp.tBulletFormat) && JceUtil.equals(this.vForwardChannels, decorationInfoRsp.vForwardChannels) && JceUtil.equals(this.iModifyMask, decorationInfoRsp.iModifyMask) && JceUtil.equals(this.vBulletPrefix, decorationInfoRsp.vBulletPrefix) && JceUtil.equals(this.tUserInfo, decorationInfoRsp.tUserInfo);
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
        ArrayList<DecorationInfo> arrayList = this.vDecorationPrefix;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 0);
        }
        ArrayList<DecorationInfo> arrayList2 = this.vDecorationSuffix;
        if (arrayList2 != null) {
            jceOutputStream.write((Collection) arrayList2, 1);
        }
        ContentFormat contentFormat = this.tFormat;
        if (contentFormat != null) {
            jceOutputStream.write((JceStruct) contentFormat, 2);
        }
        BulletFormat bulletFormat = this.tBulletFormat;
        if (bulletFormat != null) {
            jceOutputStream.write((JceStruct) bulletFormat, 3);
        }
        ArrayList<ChannelPair1> arrayList3 = this.vForwardChannels;
        if (arrayList3 != null) {
            jceOutputStream.write((Collection) arrayList3, 4);
        }
        jceOutputStream.write(this.iModifyMask, 5);
        ArrayList<DecorationInfo> arrayList4 = this.vBulletPrefix;
        if (arrayList4 != null) {
            jceOutputStream.write((Collection) arrayList4, 6);
        }
        SenderInfo senderInfo = this.tUserInfo;
        if (senderInfo != null) {
            jceOutputStream.write((JceStruct) senderInfo, 7);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_vDecorationPrefix == null) {
            cache_vDecorationPrefix = new ArrayList<>();
            cache_vDecorationPrefix.add(new DecorationInfo());
        }
        setVDecorationPrefix((ArrayList) jceInputStream.read(cache_vDecorationPrefix, 0, false));
        if (cache_vDecorationSuffix == null) {
            cache_vDecorationSuffix = new ArrayList<>();
            cache_vDecorationSuffix.add(new DecorationInfo());
        }
        setVDecorationSuffix((ArrayList) jceInputStream.read(cache_vDecorationSuffix, 1, false));
        if (cache_tFormat == null) {
            cache_tFormat = new ContentFormat();
        }
        setTFormat((ContentFormat) jceInputStream.read((JceStruct) cache_tFormat, 2, false));
        if (cache_tBulletFormat == null) {
            cache_tBulletFormat = new BulletFormat();
        }
        setTBulletFormat((BulletFormat) jceInputStream.read((JceStruct) cache_tBulletFormat, 3, false));
        if (cache_vForwardChannels == null) {
            cache_vForwardChannels = new ArrayList<>();
            cache_vForwardChannels.add(new ChannelPair1());
        }
        setVForwardChannels((ArrayList) jceInputStream.read(cache_vForwardChannels, 4, false));
        setIModifyMask(jceInputStream.read(this.iModifyMask, 5, false));
        if (cache_vBulletPrefix == null) {
            cache_vBulletPrefix = new ArrayList<>();
            cache_vBulletPrefix.add(new DecorationInfo());
        }
        setVBulletPrefix((ArrayList) jceInputStream.read(cache_vBulletPrefix, 6, false));
        if (cache_tUserInfo == null) {
            cache_tUserInfo = new SenderInfo();
        }
        setTUserInfo((SenderInfo) jceInputStream.read((JceStruct) cache_tUserInfo, 7, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((Collection) this.vDecorationPrefix, "vDecorationPrefix");
        jceDisplayer.display((Collection) this.vDecorationSuffix, "vDecorationSuffix");
        jceDisplayer.display((JceStruct) this.tFormat, "tFormat");
        jceDisplayer.display((JceStruct) this.tBulletFormat, "tBulletFormat");
        jceDisplayer.display((Collection) this.vForwardChannels, "vForwardChannels");
        jceDisplayer.display(this.iModifyMask, "iModifyMask");
        jceDisplayer.display((Collection) this.vBulletPrefix, "vBulletPrefix");
        jceDisplayer.display((JceStruct) this.tUserInfo, "tUserInfo");
    }
}
