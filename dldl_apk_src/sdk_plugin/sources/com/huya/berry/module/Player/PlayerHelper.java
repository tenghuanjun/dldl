package com.huya.berry.module.Player;

import android.app.Activity;
import com.duowan.HUYA.CornerMark;
import com.duowan.HUYA.ScreenType;
import com.duowan.HUYA.UserRecItem;
import com.duowan.auk.util.L;
import com.duowan.live.one.module.report.Report;
import com.duowan.networkmars.hysignal.HySignalProxy;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.berry.module.Player.SMObject;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.sqwan.bugless.util.FileUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class PlayerHelper {
    public static final int DEFAULT_BITRATE = 2500;
    public static final String TAG = "PlayerHelper";
    public static String audienceCount = null;
    public static int bitRate = 0;
    public static int defaultBitRate = 0;
    public static String disPlayName = "超清";
    public static boolean floatPlay;
    public static String gameName;
    public static String lastInputText;
    public static int line;
    public static String liveAnchorAvatar;
    public static String liveAnchorName;
    public static Activity mActivity;
    public static ScreenType mScreenType;
    public static UserRecItem mUserRecItem;
    public static long presenterUid;
    public static long roomId;
    public static long sid;
    public static SMObject.SingleStreamInfo singleStreamInfo;
    public static String streamName;
    public static long subSid;
    public static String title;
    public static Map<Integer, String> FlvUrl = new HashMap();
    public static Map<Integer, String> HlsUrl = new HashMap();
    public static Map<Integer, String> P2PUrl = new HashMap();
    public static Map<Integer, String> streamNames = new HashMap();
    public static boolean isOpenDanmu = true;
    public static boolean isOpenMute = true;

    public static void openFloat(Activity activity, UserRecItem userRecItem) {
        String str;
        String str2;
        mUserRecItem = userRecItem;
        String[] strArrSplit = userRecItem.sAction.split("&");
        if (strArrSplit.length == 0) {
            L.info(TAG, "item.sAction split == 0");
            return;
        }
        String str3 = "";
        lastInputText = "";
        long jLongValue = 0;
        if (presenterUid > 0) {
            HySignalProxy.getInstance().unRegisterLiveGroups(presenterUid);
        }
        int length = strArrSplit.length;
        long jLongValue2 = 0;
        long jLongValue3 = 0;
        int i = 0;
        int iIntValue = 1;
        int iIntValue2 = 0;
        while (i < length) {
            String str4 = strArrSplit[i];
            String[] strArr = strArrSplit;
            if (str4.contains("screentype")) {
                iIntValue = Integer.valueOf(str4.substring(str4.indexOf(SimpleComparison.EQUAL_TO_OPERATION) + 1, str4.length())).intValue();
            }
            if (str4.contains("sourcetype")) {
                iIntValue2 = Integer.valueOf(str4.substring(str4.indexOf(SimpleComparison.EQUAL_TO_OPERATION) + 1, str4.length())).intValue();
            }
            if (str4.contains("channelid")) {
                jLongValue2 = Long.valueOf(str4.substring(str4.indexOf(SimpleComparison.EQUAL_TO_OPERATION) + 1, str4.length())).longValue();
            }
            if (str4.contains("subid")) {
                jLongValue3 = Long.valueOf(str4.substring(str4.indexOf(SimpleComparison.EQUAL_TO_OPERATION) + 1, str4.length())).longValue();
            }
            if (str4.contains("liveuid")) {
                jLongValue = Long.valueOf(str4.substring(str4.indexOf(SimpleComparison.EQUAL_TO_OPERATION) + 1, str4.length())).longValue();
            }
            i++;
            strArrSplit = strArr;
        }
        liveAnchorName = userRecItem.sNickName;
        liveAnchorAvatar = userRecItem.sAvatar;
        presenterUid = jLongValue;
        sid = jLongValue2;
        subSid = jLongValue3;
        title = userRecItem.sTitle;
        Iterator<CornerMark> it = userRecItem.vCornerMarks.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            CornerMark next = it.next();
            if (next.iPos == 4) {
                audienceCount = next.sText;
                break;
            }
        }
        ScreenType screenType = ScreenType.ST_Horizonal;
        if ((iIntValue == 0 && (iIntValue2 == 6 || iIntValue2 == 2)) || iIntValue2 == 11) {
            screenType = ScreenType.ST_Vertical;
        }
        if (userRecItem.vStreamInfo == null || userRecItem.vStreamInfo.size() <= 0) {
            str = "";
            str2 = str;
        } else {
            str3 = userRecItem.vStreamInfo.get(0).sFlvUrl;
            str = userRecItem.vStreamInfo.get(0).sFlvUrlSuffix;
            str2 = userRecItem.vStreamInfo.get(0).sFlvAntiCode;
            line = userRecItem.vStreamInfo.get(0).iLineIndex;
            streamName = userRecItem.vStreamInfo.get(0).sStreamName;
        }
        mScreenType = screenType;
        mActivity = activity;
        FlvUrl.put(Integer.valueOf(line), str3 + "/" + streamName + FileUtil.FILE_EXTENSION_SEPARATOR + str + "?uid=0&uuid=0&" + str2);
        Report.event(SdkReportConst.LIVE_PREVIEW_NATIVE);
    }

    public static boolean isShown() {
        return SdkProperties.isPLayerFloating.get().booleanValue();
    }
}
