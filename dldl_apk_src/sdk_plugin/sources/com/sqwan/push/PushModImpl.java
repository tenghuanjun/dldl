package com.sqwan.push;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.sq.push.service.IPush;
import com.sq.push.service.PushLog;
import com.sq.push.service.SqPushService;
import com.sq.tool.logger.SQLog;
import com.sqwan.common.mod.push.IPushMod;
import com.sqwan.msdk.api.SQAppConfig;
import com.sqwan.msdk.config.ConfigManager;
import com.sqwan.msdk.utils.PushHttpRequester;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PushModImpl implements IPushMod {
    @Override // com.sqwan.common.mod.push.IPushMod
    public void onCreate(Activity activity, Bundle bundle) {
        try {
            SqPushService.getInstance().onCreate(activity, bundle);
        } catch (Throwable unused) {
        }
    }

    @Override // com.sqwan.common.mod.push.IPushMod
    public void onNewIntent(Activity activity, Intent intent) {
        try {
            SqPushService.getInstance().onNewIntent(activity, intent);
        } catch (Throwable unused) {
        }
    }

    @Override // com.sqwan.common.mod.push.IPushMod
    public void init(Context context) {
        try {
            PushLog.setTag("sqsdk");
            PushLog.setLogger(new PushLog.Logger() { // from class: com.sqwan.push.-$$Lambda$k9A1BDaFlcy5qbvkDG9l-wtg8lc
                public final void log(int i, String str, String str2, Throwable th) {
                    SQLog.log(i, str, str2, th);
                }
            });
            SQAppConfig sQAppConfig = ConfigManager.getInstance(context).getSQAppConfig();
            SqPushService.getInstance().init(context, new SqPushService.GameConfig(sQAppConfig.getPartner(), sQAppConfig.getGameid()), new PushHttpRequester());
        } catch (Throwable unused) {
            SQLog.w("【Push】不支持推送");
        }
    }

    @Override // com.sqwan.common.mod.push.IPushMod
    public void setUserInfo(String str, String str2) {
        try {
            SqPushService.getInstance().setUserInfo(str, str2);
        } catch (Throwable unused) {
        }
    }

    @Override // com.sqwan.common.mod.push.IPushMod
    public void setTransmitMessageListener(final IPushMod.TransmitMessageListenerInternal transmitMessageListenerInternal) {
        try {
            SqPushService.getInstance().setTransmitMessageListener(new IPush.TransmitMessageListener() { // from class: com.sqwan.push.-$$Lambda$PushModImpl$ULAATP4D1ILOTCN5bX7Rj_y9_GI
                public final void onReceiveTransmitMessage(String str) {
                    PushModImpl.lambda$setTransmitMessageListener$0(transmitMessageListenerInternal, str);
                }
            });
        } catch (Throwable unused) {
        }
    }

    static /* synthetic */ void lambda$setTransmitMessageListener$0(IPushMod.TransmitMessageListenerInternal transmitMessageListenerInternal, String str) {
        if (transmitMessageListenerInternal != null) {
            transmitMessageListenerInternal.onReceiveTransmitMessage(str);
        }
    }

    @Override // com.sqwan.common.mod.push.IPushMod
    public void sendFeedback(Context context, Map<String, String> map) {
        try {
            SqPushService.getInstance().sendFeedback(context, map);
        } catch (Throwable unused) {
        }
    }
}
