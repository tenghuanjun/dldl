package com.huya.berry.module.help;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.duowan.HUYA.UserId;
import com.duowan.HUYA.UserRecItem;
import com.duowan.auk.ArkValue;
import com.duowan.auk.ui.widget.ArkToast;
import com.duowan.auk.util.L;
import com.duowan.live.one.module.report.Report;
import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.berry.gamesdk.wup.WupHelper;
import com.huya.berry.module.data.LiveListTagInfo;
import com.huya.component.login.LoginProperties;
import com.huya.component.user.api.UserApi;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LiveHelper {
    public static final int MOBILE_HUYA = 2;
    private static final String TAG = "LiveHelper";
    public static final int USER_IN = 1;
    public static final int USER_OUT = 2;
    public static ArrayList<LiveListTagInfo> list;

    public static boolean startApp(Activity activity, UserRecItem userRecItem, String str, int i) {
        String[] strArrSplit = userRecItem.sAction.split("&");
        if (strArrSplit.length == 0) {
            L.info(TAG, "item.sAction split == 0");
            return false;
        }
        long jLongValue = 0;
        long jLongValue2 = 0;
        long jLongValue3 = 0;
        for (String str2 : strArrSplit) {
            if (str2.contains("channelid")) {
                jLongValue = Long.valueOf(str2.substring(str2.indexOf(SimpleComparison.EQUAL_TO_OPERATION) + 1, str2.length())).longValue();
            } else if (str2.contains("subid")) {
                jLongValue2 = Long.valueOf(str2.substring(str2.indexOf(SimpleComparison.EQUAL_TO_OPERATION) + 1, str2.length())).longValue();
            } else if (str2.contains("liveuid")) {
                jLongValue3 = Long.valueOf(str2.substring(str2.indexOf(SimpleComparison.EQUAL_TO_OPERATION) + 1, str2.length())).longValue();
            }
        }
        String str3 = strArrSplit[0];
        String strSubstring = str3.substring(str3.lastIndexOf("/") + 1, str3.lastIndexOf("?"));
        if (checkPackInfo(ArkValue.gContext, "com.duowan.kiwi")) {
            Intent intent = new Intent();
            intent.setFlags(268435456);
            try {
                intent.setData(Uri.parse("yykiwi://openurl?banneraction=" + URLEncoder.encode("https://hd.huya.com/h5/app_update?hyaction=live&channelid=" + jLongValue + "&subid=" + jLongValue2 + "&liveuid=" + jLongValue3, "UTF-8")));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            ArkValue.gContext.startActivity(intent);
            Report.event(SdkReportConst.STATUS_HOME_LIVEPREVIEW_TOAPP);
            ArkToast.show("正在打开虎牙App");
            return true;
        }
        goToH5Live(activity, strSubstring, str, i);
        return false;
    }

    private static boolean checkPackInfo(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        return packageInfo != null;
    }

    private static void goToH5Live(Activity activity, String str, String str2, int i) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://m.huya.com/" + str + "?from=" + str2 + "&gameid=" + i));
        intent.setFlags(268435456);
        try {
            activity.startActivity(intent);
            Report.event(SdkReportConst.STATUS_HOME_LIVEPREVIEW_TOH5);
        } catch (Exception e) {
            ArkToast.show("无法打开浏览器");
            e.printStackTrace();
        }
    }

    public static UserId getUserId() {
        return getUserId(true);
    }

    public static UserId getUserId(boolean z) {
        UserId userId = new UserId();
        UserId apmUserId = UserApi.getApmUserId();
        userId.setLUid(LoginProperties.uid.get().longValue());
        userId.setSGuid(apmUserId.sGuid);
        userId.setSHuYaUA(WupHelper.getSHuYaUA());
        if (z) {
            userId.setSToken(apmUserId.sToken);
            userId.setITokenType(apmUserId.iTokenType);
        }
        return userId;
    }

    public static UserId getSpecialUserId() {
        UserId userId = new UserId();
        UserId apmUserId = UserApi.getApmUserId();
        userId.setLUid(LoginProperties.uid.get().longValue());
        userId.setSGuid(apmUserId.sGuid);
        userId.setSHuYaUA("web");
        userId.setSToken(apmUserId.sToken);
        userId.setITokenType(apmUserId.iTokenType);
        return userId;
    }
}
