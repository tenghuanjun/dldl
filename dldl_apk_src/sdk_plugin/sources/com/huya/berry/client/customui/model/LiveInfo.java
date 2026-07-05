package com.huya.berry.client.customui.model;

import android.text.TextUtils;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.base.BaseCallback;
import com.huya.berry.module.Player.PlayerHelper;
import com.huya.berry.module.Player.SMObject;
import java.util.Vector;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LiveInfo extends BaseCallback {
    public String audienceCount;
    public String gameName;
    public int landType;
    public long roomId;
    public long uid;
    public String title = "";
    public String nickName = "";
    public String avatar = "";

    public Vector<Integer> getLines() {
        if (PlayerHelper.singleStreamInfo == null) {
            return null;
        }
        return PlayerHelper.singleStreamInfo.getLineIndexs();
    }

    public Vector<BitRateInfo> getBitRateList(int i) {
        SMObject.LineInfo lineInfoByIndex;
        if (PlayerHelper.singleStreamInfo == null || (lineInfoByIndex = PlayerHelper.singleStreamInfo.getLineInfoByIndex(i)) == null) {
            return null;
        }
        Vector<SMObject.BitRateInfo> bitRateList = lineInfoByIndex.getBitRateList();
        Vector<BitRateInfo> vector = new Vector<>();
        for (int i2 = 0; i2 < bitRateList.size(); i2++) {
            SMObject.BitRateInfo bitRateInfo = bitRateList.get(i2);
            int i3 = bitRateInfo.H264BitRate > 0 ? bitRateInfo.H264BitRate : bitRateInfo.H265BitRate;
            if (SdkProperties.maxPlayBitrate.get().intValue() <= 0 || i3 <= SdkProperties.maxPlayBitrate.get().intValue()) {
                BitRateInfo bitRateInfo2 = new BitRateInfo();
                bitRateInfo2.bitRate = i3;
                bitRateInfo2.disPlayName = bitRateInfo.disPlayName;
                vector.add(bitRateInfo2);
            }
        }
        if (vector.size() == 0 && bitRateList.size() > 0) {
            int i4 = bitRateList.get(0).H264BitRate > 0 ? bitRateList.get(0).H264BitRate : bitRateList.get(0).H265BitRate;
            BitRateInfo bitRateInfo3 = new BitRateInfo();
            bitRateInfo3.bitRate = i4;
            bitRateInfo3.disPlayName = bitRateList.get(0).disPlayName;
            vector.add(bitRateInfo3);
        }
        return vector;
    }

    public String getPlayUrlByLineAndBitrate(boolean z, int i, int i2) {
        String string;
        String str;
        if (SdkProperties.maxPlayBitrate.get().intValue() > 0 && i2 > SdkProperties.maxPlayBitrate.get().intValue()) {
            return null;
        }
        if (i2 == -1) {
            i2 = 0;
        }
        String str2 = PlayerHelper.FlvUrl.get(Integer.valueOf(i));
        if (z) {
            if (TextUtils.isEmpty(str2)) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            if (i2 == 0) {
                str = "";
            } else {
                str = "&ratio=" + i2;
            }
            sb.append(str);
            string = sb.toString();
        } else {
            String str3 = PlayerHelper.HlsUrl.get(Integer.valueOf(i));
            if (TextUtils.isEmpty(str3)) {
                return null;
            }
            int iLastIndexOf = str3.lastIndexOf(".m3u8");
            String strSubstring = str3.substring(iLastIndexOf, str3.length());
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str3.substring(0, iLastIndexOf));
            if (i2 != 0) {
                strSubstring = "_" + i2 + strSubstring;
            }
            sb2.append(strSubstring);
            string = sb2.toString();
        }
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return string;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        stringBuffer.append(this.title);
        stringBuffer.append(",");
        stringBuffer.append(this.nickName);
        stringBuffer.append(",");
        stringBuffer.append(this.roomId);
        stringBuffer.append(",");
        stringBuffer.append(this.audienceCount);
        stringBuffer.append(",");
        stringBuffer.append(this.gameName);
        stringBuffer.append(",");
        stringBuffer.append(this.landType);
        stringBuffer.append(",");
        stringBuffer.append(this.avatar);
        stringBuffer.append(",");
        stringBuffer.append(this.uid);
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
