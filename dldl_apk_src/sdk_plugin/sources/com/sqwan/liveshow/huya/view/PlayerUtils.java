package com.sqwan.liveshow.huya.view;

import android.os.Build;
import android.text.TextUtils;
import com.huya.berry.client.customui.model.BitRateInfo;
import com.huya.berry.client.customui.model.LiveInfo;
import com.huya.berry.gamesdk.wup.WupHelper;
import com.nbvideo.VideoInfo;
import com.sqwan.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PlayerUtils {
    private static final String TAG = "PlayerUtils";
    private static List<String> disPlayNames = Arrays.asList("蓝光", "蓝光4M", "蓝光5M", "蓝光6M", "蓝光6M", "蓝光8M", "蓝光9M", "蓝光10M", "超清", "高清", "流畅");
    private static List<String> disPlayNamesLowVersion = Arrays.asList("流畅", "高清", "超清", "蓝光", "蓝光4M", "蓝光8M", "蓝光5M", "蓝光6M", "蓝光6M", "蓝光8M", "蓝光9M", "蓝光10M");
    private static String testUrl = "https://txdirect.hls.huya.com/huyalive/1704199086-1704199086-7319479340243091456-3408521628-10057-A-0-1.m3u8?wsSecret=1698b58c2a40f4bfafb765b34982e877&wsTime=60fbb7e6&u=0&seqid=16270229498540&ctype=huya_adr_game_sdk&txyp=o%3Ac8%3B&fs=bgct&ratio=2000";

    public static List<String> getLines(LiveInfo liveInfo) {
        ArrayList arrayList = new ArrayList();
        for (Integer num : liveInfo.getLines()) {
            LogUtil.i(TAG, "line:" + num);
            Iterator<BitRateInfo> it = liveInfo.getBitRateList(num.intValue()).iterator();
            while (it.hasNext()) {
                String str = it.next().disPlayName;
                if (!arrayList.contains(str)) {
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }

    public static VideoInfo matchLine(LiveInfo liveInfo, String str) {
        List<String> arrayList;
        if (str != null) {
            arrayList = new ArrayList();
            arrayList.add(str);
        } else if (isLowVideoViewSdkVersion()) {
            arrayList = disPlayNamesLowVersion;
        } else {
            arrayList = disPlayNames;
        }
        for (String str2 : arrayList) {
            LogUtil.i(TAG, "disPlayName:" + str2);
            for (Integer num : liveInfo.getLines()) {
                LogUtil.i(TAG, "line:" + num);
                for (BitRateInfo bitRateInfo : liveInfo.getBitRateList(num.intValue())) {
                    LogUtil.i(TAG, "bitRateInfo:" + bitRateInfo.toString());
                    if (TextUtils.equals(str2, bitRateInfo.disPlayName)) {
                        int i = bitRateInfo.bitRate;
                        LogUtil.i(TAG, "bitRate:" + i);
                        if (i != 0) {
                            String playUrlByLineAndBitrate = liveInfo.getPlayUrlByLineAndBitrate(false, num.intValue(), i);
                            LogUtil.i(TAG, "url:" + playUrlByLineAndBitrate);
                            if (!TextUtils.isEmpty(playUrlByLineAndBitrate)) {
                                HashMap map = new HashMap();
                                String sHuYaUA = WupHelper.getSHuYaUA();
                                LogUtil.i(TAG, "userAgent:" + sHuYaUA);
                                map.put("User-Agent", sHuYaUA);
                                return new VideoInfo(str2, playUrlByLineAndBitrate, map);
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        }
        return null;
    }

    public static VideoInfo matchLine(LiveInfo liveInfo, String str, ArrayList<String> arrayList) {
        List<String> arrayList2;
        if (str != null) {
            arrayList2 = new ArrayList();
            arrayList2.add(str);
        } else if (isLowVideoViewSdkVersion()) {
            arrayList2 = disPlayNamesLowVersion;
        } else {
            arrayList2 = disPlayNames;
        }
        for (String str2 : arrayList2) {
            LogUtil.i(TAG, "disPlayName:" + str2);
            for (Integer num : liveInfo.getLines()) {
                LogUtil.i(TAG, "line:" + num);
                for (BitRateInfo bitRateInfo : liveInfo.getBitRateList(num.intValue())) {
                    LogUtil.i(TAG, "bitRateInfo:" + bitRateInfo.toString());
                    if (TextUtils.equals(str2, bitRateInfo.disPlayName)) {
                        int i = bitRateInfo.bitRate;
                        LogUtil.i(TAG, "bitRate:" + i);
                        if (i != 0) {
                            String playUrlByLineAndBitrate = liveInfo.getPlayUrlByLineAndBitrate(false, num.intValue(), i);
                            LogUtil.i(TAG, "url:" + playUrlByLineAndBitrate);
                            if (arrayList == null || arrayList.size() <= 0 || !arrayList.contains(playUrlByLineAndBitrate)) {
                                if (!TextUtils.isEmpty(playUrlByLineAndBitrate)) {
                                    HashMap map = new HashMap();
                                    String sHuYaUA = WupHelper.getSHuYaUA();
                                    LogUtil.i(TAG, "userAgent:" + sHuYaUA);
                                    map.put("User-Agent", sHuYaUA);
                                    return new VideoInfo(str2, playUrlByLineAndBitrate, map);
                                }
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        }
        return null;
    }

    private static boolean isLowVideoViewSdkVersion() {
        return Build.VERSION.SDK_INT <= 23;
    }
}
