package com.sy37sdk.account.floatview;

import android.text.TextUtils;
import com.sqwan.common.util.SQContextWrapper;
import com.sqwan.common.util.SpUtils;
import com.sy37sdk.account.AccountCache;
import com.sy37sdk.account.floatview.data.RedDot;
import com.sy37sdk.account.floatview.request.bean.FloatUserInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FloatViewDataCacheHelper {
    private static final String SP_KEY_FLOAT_AVATAR_LIST = "sp_key_float_avatar_list";
    private static final String SP_KEY_FLOAT_RED_DOT = "sp_key_float_red_dot";
    private static final String SP_KEY_FLOAT_USER_INFO = "sp_key_float_user_info_list";

    public static void saveFloatUserInfo(FloatUserInfo floatUserInfo) {
        ArrayList<FloatUserInfo> floatUserInfos = getFloatUserInfos();
        int i = 0;
        while (true) {
            if (i >= floatUserInfos.size()) {
                i = -1;
                break;
            } else if (floatUserInfos.get(i).getUid().equals(floatUserInfo.getUid())) {
                break;
            } else {
                i++;
            }
        }
        if (i != -1) {
            floatUserInfos.set(i, floatUserInfo);
        } else {
            floatUserInfos.add(floatUserInfo);
        }
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<FloatUserInfo> it = floatUserInfos.iterator();
            while (it.hasNext()) {
                jSONArray.put(FloatUserInfo.objectToJson(it.next()));
            }
            SpUtils.get(SQContextWrapper.getApplicationContext()).put(SP_KEY_FLOAT_USER_INFO, jSONArray.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<FloatUserInfo> getFloatUserInfos() {
        ArrayList<FloatUserInfo> arrayList = new ArrayList<>();
        String string = SpUtils.get(SQContextWrapper.getApplicationContext()).getString(SP_KEY_FLOAT_USER_INFO);
        if (TextUtils.isEmpty(string)) {
            return arrayList;
        }
        try {
            JSONArray jSONArray = new JSONArray(string);
            if (jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(FloatUserInfo.jsonToObject((String) jSONArray.get(i)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static FloatUserInfo getFloatUserInfo() {
        return getFloatUserInfo(AccountCache.getUserid(SQContextWrapper.getApplicationContext()));
    }

    public static FloatUserInfo getFloatUserInfo(String str) {
        FloatUserInfo floatUserInfo = new FloatUserInfo();
        ArrayList<FloatUserInfo> floatUserInfos = getFloatUserInfos();
        if (floatUserInfos.size() <= 0) {
            return floatUserInfo;
        }
        for (FloatUserInfo floatUserInfo2 : floatUserInfos) {
            if (floatUserInfo2.getUid().equals(str)) {
                return floatUserInfo2;
            }
        }
        return floatUserInfo;
    }

    public static void saveAvatars(String str) {
        SpUtils.get(SQContextWrapper.getApplicationContext()).put(SP_KEY_FLOAT_AVATAR_LIST, str);
    }

    public static ArrayList<String> getAvatars() {
        String string = SpUtils.get(SQContextWrapper.getApplicationContext()).getString(SP_KEY_FLOAT_AVATAR_LIST);
        ArrayList<String> arrayList = new ArrayList<>();
        if (!TextUtils.isEmpty(string)) {
            try {
                JSONArray jSONArray = new JSONArray(string);
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add((String) jSONArray.get(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public static List<RedDot> getRedDotCache() {
        String string = SpUtils.get(SQContextWrapper.getApplicationContext()).getString(SP_KEY_FLOAT_RED_DOT);
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(string)) {
            try {
                JSONArray jSONArray = new JSONArray(string);
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(RedDot.parse(jSONArray.optString(i)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public static void clearRedDotCache() {
        SpUtils.get(SQContextWrapper.getApplicationContext()).put(SP_KEY_FLOAT_RED_DOT, "");
    }

    public static void saveRedDotCache(List<RedDot> list) {
        if (list == null || list.size() < 1) {
            return;
        }
        JSONArray jSONArray = new JSONArray();
        Iterator<RedDot> it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(RedDot.objectToJson(it.next()));
        }
        SpUtils.get(SQContextWrapper.getApplicationContext()).put(SP_KEY_FLOAT_RED_DOT, jSONArray.toString());
    }
}
