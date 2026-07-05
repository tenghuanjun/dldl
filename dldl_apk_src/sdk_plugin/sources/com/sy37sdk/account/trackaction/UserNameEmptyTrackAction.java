package com.sy37sdk.account.trackaction;

import android.text.TextUtils;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.util.LogUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class UserNameEmptyTrackAction {

    public enum ActionType {
        saveAccout,
        active,
        login,
        modifyPass
    }

    public static void report(ActionType actionType, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.e("UserNameEmptyTrackAction report");
            BuglessAction.reportCatchException(new Exception(actionType.toString()), actionType.toString(), str2, 24);
        }
    }
}
