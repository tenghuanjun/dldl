package com.sy37sdk.account;

import android.content.Context;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import com.sqwan.common.mod.track.TrackModManager2;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.util.SQContextWrapper;
import com.sqwan.msdk.BaseSQwanCore;
import com.sy37sdk.account.binding.GameBindingManager;
import com.sy37sdk.account.device.DevicesInfo;
import com.sy37sdk.account.trackaction.TrackNoticeLog;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LoginTractionManager {
    public static final String TRACK_ACCOUNT_TYPE = "account_type";
    public static final String TRACK_ACCOUNT_TYPE_ACCOUNT = "account";
    public static final String TRACK_ACCOUNT_TYPE_ACCOUNT_NUM = "1";
    public static final String TRACK_ACCOUNT_TYPE_PHONE = "phone";
    public static final String TRACK_ACCOUNT_TYPE_PHONE_NUM = "2";
    public static final String TRACK_ACCOUNT_TYPE_WECHAT_NUM = "3";
    public static final String TRACK_LOGIN_TYPE = "login_type";
    public static final String TRACK_LOGIN_TYPE_LOGIN = "login";
    public static final String TRACK_LOGIN_TYPE_REGISTER = "register";
    public static final String login_way_account = "1";
    public static final String login_way_ali_fast = "3";
    public static final String login_way_auto = "4";
    public static final String login_way_history = "6";
    public static final String login_way_phone_code = "2";
    public static final String login_way_phone_pwd = "5";
    public static final String login_way_quick_start = "7";
    public static final String login_way_wechat = "8";
    public static final String register_way_account = "1";
    public static final String register_way_ali_fast = "3";
    public static final String register_way_phone_code = "2";
    public static final String register_way_quick = "4";
    public static final String register_way_wechat = "5";

    public static void trackInvoke(String str, String str2) {
        HashMap map = new HashMap();
        map.put("login_type", str);
        map.put("login_way", str2);
        map.put(SqTrackKey.is_game_binding, GameBindingManager.getInstance().isGameBinding() ? "1" : "0");
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.login_invoke, map);
        SqTrackActionManager2.getInstance().flush();
    }

    public static void track(String str, Map<String, String> map) {
        TrackNoticeLog.noticeLog(SQContextWrapper.getActivity());
        if (map == null || map.isEmpty()) {
            return;
        }
        TrackModManager2.setUserId(map.get(BaseSQwanCore.LOGIN_KEY_USERID));
        String str2 = map.get(TRACK_ACCOUNT_TYPE);
        String str3 = map.get("login_type");
        if (!TextUtils.isEmpty(str3) && TRACK_LOGIN_TYPE_REGISTER.equals(str3)) {
            byte b = -1;
            int iHashCode = str.hashCode();
            if (iHashCode != 50) {
                if (iHashCode != 51) {
                    if (iHashCode != 55) {
                        if (iHashCode == 56 && str.equals("8")) {
                            b = 2;
                        }
                    } else if (str.equals("7")) {
                        b = 3;
                    }
                } else if (str.equals("3")) {
                    b = 0;
                }
            } else if (str.equals("2")) {
                b = 1;
            }
            String str4 = b != 0 ? b != 1 ? b != 2 ? b != 3 ? "1" : "4" : "5" : "2" : "3";
            HashMap map2 = new HashMap();
            map2.put("uid", map.get(BaseSQwanCore.LOGIN_KEY_USERID));
            map2.put(TRACK_ACCOUNT_TYPE, str2);
            map2.put("register_way", str4);
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.register_succ, map2);
        }
        HashMap map3 = new HashMap();
        map3.put("uid", map.get(BaseSQwanCore.LOGIN_KEY_USERID));
        map3.put("login_type", str2);
        map3.put("login_way", str);
        map3.put("notification_status", areNotificationsEnabled() ? "1" : "0");
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.login_succ, map3);
        DevicesInfo.doDeviceCollect(1);
    }

    public static void trackFail(String str, String str2, String str3, String str4) {
        HashMap map = new HashMap();
        map.put("login_type", str);
        map.put("login_way", str2);
        map.put(SqTrackKey.fail_code, str3);
        map.put(SqTrackKey.reason_fail, str4);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.login_fail, map);
        DevicesInfo.doDeviceCollect(2);
    }

    public static void trackWechatSuccess(String str) {
        HashMap map = new HashMap();
        map.put("wx_auth_code", str);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.login_wechat_succ, map);
    }

    public static void trackWechatFail(String str) {
        HashMap map = new HashMap();
        map.put("msg", str);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.login_wechat_fail, map);
    }

    public static String parseAccountType(String str) {
        if (TextUtils.isEmpty(str)) {
            return "1";
        }
        byte b = -1;
        int iHashCode = str.hashCode();
        if (iHashCode != 50) {
            if (iHashCode == 51 && str.equals("3")) {
                b = 1;
            }
        } else if (str.equals("2")) {
            b = 0;
        }
        return b != 0 ? b != 1 ? "1" : "3" : "2";
    }

    private static boolean areNotificationsEnabled() {
        Context applicationContext = SQContextWrapper.getApplicationContext();
        if (applicationContext == null) {
            return false;
        }
        return NotificationManagerCompat.from(applicationContext).areNotificationsEnabled();
    }

    public static void trackCheckAccountListSucc(String[] strArr, String str) {
        HashMap map = new HashMap();
        int i = 0;
        while (i < strArr.length) {
            StringBuilder sb = new StringBuilder();
            sb.append("uid");
            int i2 = i + 1;
            sb.append(i2);
            map.put(sb.toString(), strArr[i]);
            i = i2;
        }
        map.put("okUid", str);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.CHECK_ACCOUNT_LIST_SUCC, map);
    }

    public static void trackCheckAccountListFail(String str) {
        HashMap map = new HashMap();
        map.put(SqTrackKey.reason_fail, str);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.CHECK_ACCOUNT_LIST_FAIL, map);
    }
}
