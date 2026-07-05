package com.duowan.live.one.module.uploadLog;

import com.duowan.auk.ArkUtils;
import com.duowan.auk.ArkValue;
import com.duowan.live.common.EmojiUtils;
import com.duowan.live.one.module.feedback.R;
import com.duowan.live.one.module.uploadLog.FeedBackInterface;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class FeedBackHelper {
    private static final String TAG = "FeedBackHelper";

    public static void sendFeedback(String str, String str2) {
        sendFeedback(str, str2, null);
    }

    public static void sendFeedback(String str, String str2, String str3) {
        sendFeedback(str, str2, str3, 0L);
    }

    public static void sendFeedback(String str, String str2, String str3, long j) {
        ArkUtils.send(new FeedBackInterface.AddFeedBack(str, EmojiUtils.emoji2Unicode(str2), str3, j));
    }

    public static void sendFeedback(String str) {
        sendFeedback(ArkValue.gContext.getResources().getString(R.string.feedback_prefix), str);
    }
}
