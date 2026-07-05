package com.igexin.sdk;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.bytedance.sdk.openadsdk.mediation.MediationConstant;
import com.getui.gtc.api.GtcManager;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.util.CommonUtil;
import com.igexin.assist.sdk.AssistPushConsts;
import com.igexin.push.core.b;
import com.igexin.push.core.i;
import com.igexin.push.core.p;
import com.igexin.push.f.c;
import com.igexin.push.f.d;
import com.igexin.push.f.e;
import com.igexin.push.f.o;
import com.igexin.sdk.message.BindAliasCmdMessage;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.SetTagCmdMessage;
import com.igexin.sdk.message.UnBindAliasCmdMessage;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.security.MessageDigest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;
import javax.crypto.KeyGenerator;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class PushManager {
    private static final String TAG = "PushManager";
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    private i callback;
    private String intentService;
    private byte[] keyBytes;
    private long lastOpAliasTime;
    private long lastSendMessageTime;
    private long lastSetTagTime;
    private String localCid;
    private String safeCode;
    private String uActivty;
    private Class uService;

    static class a {
        private static final PushManager a = new PushManager();

        private a() {
        }
    }

    private PushManager() {
    }

    private static void checkContext(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        }
    }

    private int getAssistAction(int i, String str) {
        return (TextUtils.isEmpty(str) || !str.contains("_")) ? i : (i == 60001 || i == 60002) ? str.startsWith(AssistPushConsts.HW_PREFIX) ? i + 18 : str.startsWith(AssistPushConsts.XM_PREFIX) ? i + 48 : str.startsWith(AssistPushConsts.OPPO_PREFIX) ? i + 28 : str.startsWith(AssistPushConsts.VIVO_PREFIX) ? i + 38 : str.startsWith(AssistPushConsts.MZ_PREFIX) ? i + 58 : i : i;
    }

    public static PushManager getInstance() {
        return a.a;
    }

    private String getMD5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            byte[] bArrDigest = messageDigest.digest();
            StringBuilder sb = new StringBuilder("");
            int length = bArrDigest.length;
            for (int i = 0; i < length; i++) {
                int i2 = bArrDigest[i];
                if (i2 < 0) {
                    i2 += 256;
                }
                if (i2 < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i2));
            }
            return sb.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Application getMainApplication(Context context) {
        if (context == null || !isMainProcess(context)) {
            return null;
        }
        return context instanceof Application ? (Application) context : (Application) context.getApplicationContext();
    }

    private Class getUserPushService(Context context) {
        checkContext(context);
        Class cls = this.uService;
        return cls != null ? cls : p.a.a.a(context);
    }

    private boolean isMainProcess(Context context) {
        try {
            GtcProvider.setContext(context);
            return CommonUtil.isMainProcess();
        } catch (Throwable unused) {
            return false;
        }
    }

    private void registerCallback(final Context context) {
        if (this.callback != null || Build.VERSION.SDK_INT < 14) {
            return;
        }
        p.b = context.getApplicationContext();
        executor.execute(new Runnable() { // from class: com.igexin.sdk.PushManager.1
            @Override // java.lang.Runnable
            public final void run() {
                Application mainApplication;
                try {
                    mainApplication = PushManager.this.getMainApplication(context);
                } catch (Throwable unused) {
                    mainApplication = null;
                }
                if (mainApplication == null) {
                    return;
                }
                try {
                    System.currentTimeMillis();
                    GtcManager.getInstance().initialize(context, null);
                    System.currentTimeMillis();
                } catch (Throwable unused2) {
                }
                try {
                    synchronized (PushManager.class) {
                        if (PushManager.this.callback == null && mainApplication != null) {
                            PushManager.this.callback = new i();
                            mainApplication.registerActivityLifecycleCallbacks(PushManager.this.callback);
                        }
                    }
                } catch (Throwable unused3) {
                }
            }
        });
    }

    private <T extends Activity> void registerPushActivity(Context context, Class<T> cls) {
        String name;
        checkContext(context);
        try {
            if (cls != null) {
                try {
                    Class.forName(cls.getName());
                    if (!c.a(context, cls)) {
                        return;
                    } else {
                        name = cls.getName();
                    }
                } catch (Exception e) {
                    com.igexin.b.a.c.a.c.a().a("[PushManager] can't load activity = " + e.toString());
                    com.igexin.b.a.c.a.a("PushManager|registerPushActiviy|" + e.toString(), new Object[0]);
                    return;
                }
            } else {
                com.igexin.b.a.c.a.c.a().a("[PushManager] call -> registerPushActiviy, parameter [activity] is null");
                name = "";
            }
            this.uActivty = name;
            if (this.uService != null) {
                Intent intent = new Intent(context.getApplicationContext(), (Class<?>) this.uService);
                intent.putExtra(o.c, this.uActivty);
                startService(context, intent);
            }
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a("PushManager|registerPushActiviy|" + th.toString(), new Object[0]);
        }
    }

    private void sendBindAliasResult(Context context, String str, String str2) {
        sendResult(context, new BindAliasCmdMessage(str, str2, 10010));
    }

    private void sendResult(Context context, GTCmdMessage gTCmdMessage) {
        try {
            Class clsB = p.a.a.b(context);
            if (clsB == null || context == null) {
                return;
            }
            Intent intent = new Intent(context, (Class<?>) clsB);
            Bundle bundle = new Bundle();
            bundle.putInt("action", 10010);
            bundle.putSerializable(PushConsts.KEY_CMD_MSG, gTCmdMessage);
            intent.putExtras(bundle);
            context.startService(intent);
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a("PushManager|" + th.toString(), new Object[0]);
        }
    }

    private void sendSetTagResult(Context context, String str, String str2) {
        sendResult(context, new SetTagCmdMessage(str, str2, 10009));
    }

    private void sendUnBindAliasResult(Context context, String str, String str2) {
        sendResult(context, new UnBindAliasCmdMessage(str, str2, 10011));
    }

    private boolean startService(Context context, Intent intent) {
        try {
            if (TextUtils.isEmpty(this.safeCode)) {
                this.safeCode = o.b(context, o.d, "").toString();
                if (TextUtils.isEmpty(this.safeCode)) {
                    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                    keyGenerator.init(128);
                    this.safeCode = e.a(keyGenerator.generateKey().getEncoded());
                    o.a(context, o.d, this.safeCode);
                }
            }
            intent.putExtra(o.d, this.safeCode);
        } catch (Exception unused) {
        }
        return p.a.a.a(context, intent);
    }

    private void unRegisterCallback(Context context) {
        if (this.callback == null || Build.VERSION.SDK_INT < 14) {
            return;
        }
        try {
            Application mainApplication = getMainApplication(context);
            if (mainApplication == null) {
                return;
            }
            mainApplication.unregisterActivityLifecycleCallbacks(this.callback);
            this.callback = null;
        } catch (Throwable unused) {
        }
    }

    public boolean areNotificationsEnabled(Context context) {
        return c.b(context);
    }

    public boolean bindAlias(Context context, String str) {
        checkContext(context);
        return bindAlias(context, str, "bindAlias_" + System.currentTimeMillis());
    }

    public boolean bindAlias(Context context, String str, String str2) {
        com.igexin.b.a.c.a.c.a().a("PushManager|call bindAlias");
        com.igexin.b.a.c.a.a("PushManager|call bindAlias", new Object[0]);
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.lastOpAliasTime < 1000) {
            com.igexin.b.a.c.a.c.a().a("[PushManager] call - > bindAlias failed, it be called too frequently");
            sendBindAliasResult(context, str2, "30001");
            return false;
        }
        this.lastOpAliasTime = jCurrentTimeMillis;
        Bundle bundle = new Bundle();
        bundle.putString("action", "bindAlias");
        bundle.putString("alias", str);
        bundle.putString("sn", str2);
        Intent intent = new Intent(context.getApplicationContext(), (Class<?>) getUserPushService(context));
        intent.putExtra("action", PushConsts.ACTION_BROADCAST_PUSHMANAGER);
        intent.putExtra("bundle", bundle);
        return startService(context, intent);
    }

    public void checkManifest(Context context) throws GetuiPushException {
        if (isMainProcess(context)) {
            c.c(context);
        }
    }

    public synchronized String getClientid(Context context) {
        checkContext(context);
        this.localCid = null;
        if (this.keyBytes == null) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (applicationInfo != null && applicationInfo.metaData != null) {
                    String string = applicationInfo.metaData.getString(b.a);
                    if (TextUtils.isEmpty(string)) {
                        string = applicationInfo.metaData.getString("GETUI_APPID");
                    }
                    if (string != null) {
                        string = string.trim();
                    }
                    if (!TextUtils.isEmpty(string)) {
                        String md5 = getMD5(string + context.getPackageName());
                        if (md5 != null) {
                            this.keyBytes = md5.getBytes();
                        }
                    }
                }
            } catch (Exception e) {
                com.igexin.b.a.c.a.a("PushManager|" + e.toString(), new Object[0]);
            }
        }
        if (this.keyBytes != null) {
            p.b = context.getApplicationContext();
            if (TextUtils.isEmpty(com.igexin.push.core.d.c.a().a(com.igexin.push.core.d.c.a))) {
                return this.localCid;
            }
            byte[] bArrDecode = Base64.decode(com.igexin.push.core.d.c.a().a(com.igexin.push.core.d.c.a), 0);
            if (bArrDecode != null && this.keyBytes.length == bArrDecode.length) {
                byte[] bArr = new byte[bArrDecode.length];
                for (int i = 0; i < bArr.length; i++) {
                    bArr[i] = (byte) (this.keyBytes[i] ^ bArrDecode[i]);
                }
                if (Pattern.matches("[a-zA-Z0-9]+", new String(bArr))) {
                    this.localCid = new String(bArr);
                }
            }
        }
        return this.localCid;
    }

    public String getVersion(Context context) {
        return "3.2.4.0";
    }

    public void initialize(Context context) {
        Class cls;
        try {
            checkContext(context);
            if (TextUtils.isEmpty(this.intentService) && (cls = (Class) d.a(context, GTIntentService.class).second) != null) {
                this.intentService = cls.getName();
            }
            if (this.uService == null) {
                this.uService = (Class) d.a(context, PushService.class).second;
            }
            initialize(context, this.uService);
        } catch (Throwable th) {
            com.igexin.b.a.c.a.c.a().a("[PushManager] initialize sdk error = " + th.toString());
            com.igexin.b.a.c.a.a("PushManager|initialize|" + th.toString(), new Object[0]);
        }
    }

    @Deprecated
    public <T extends Service> void initialize(Context context, Class<T> cls) {
        try {
            checkContext(context);
            if (cls == null || b.ak.equals(cls.getName())) {
                cls = PushService.class;
            }
            Intent intent = new Intent(context.getApplicationContext(), (Class<?>) cls);
            intent.putExtra("action", PushConsts.ACTION_SERVICE_INITIALIZE);
            intent.putExtra(o.a, cls.getName());
            if (this.intentService != null) {
                intent.putExtra(o.b, this.intentService);
            }
            if (this.uActivty != null) {
                intent.putExtra(o.c, this.uActivty);
            }
            if (startService(context, intent)) {
                this.uService = cls;
            }
            registerCallback(context);
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a("PushManager|initialize|" + th.toString(), new Object[0]);
        }
    }

    public boolean isPushTurnedOn(Context context) {
        checkContext(context);
        p.b = context.getApplicationContext();
        return com.igexin.push.core.d.c.a().a(com.igexin.push.core.d.c.c, new boolean[0]);
    }

    public void openNotification(Context context) {
        String str;
        int i;
        try {
            Intent intent = new Intent();
            if (Build.VERSION.SDK_INT >= 26) {
                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                intent.putExtra("android.provider.extra.APP_PACKAGE", context.getPackageName());
                str = "android.provider.extra.CHANNEL_ID";
                i = context.getApplicationInfo().uid;
            } else {
                if (Build.VERSION.SDK_INT < 21) {
                    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.setData(Uri.fromParts("package", context.getPackageName(), null));
                    intent.setFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
                    context.startActivity(intent);
                }
                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                intent.putExtra("app_package", context.getPackageName());
                str = "app_uid";
                i = context.getApplicationInfo().uid;
            }
            intent.putExtra(str, i);
            intent.setFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            context.startActivity(intent);
        } catch (Throwable unused) {
        }
    }

    @Deprecated
    public <T extends GTIntentService> void registerPushIntentService(Context context, Class<T> cls) {
        String name;
        com.igexin.b.a.c.a.a("PushManager|call registerPushIntentService", new Object[0]);
        try {
            if (cls != null) {
                try {
                    Class.forName(cls.getName());
                    if (!c.a(new Intent(context, (Class<?>) cls), context)) {
                        Log.e(TAG, "call - > registerPushIntentService, parameter [userIntentService] is set, but didn't find class \"" + cls.getName() + "\", please check your AndroidManifest");
                        return;
                    }
                    name = cls.getName();
                } catch (Exception e) {
                    Log.e(TAG, "can't load IntentService = " + e.toString());
                    com.igexin.b.a.c.a.a("PushManager|registerPushIntentService|" + e.toString(), new Object[0]);
                    return;
                }
            } else {
                Log.d(TAG, "call -> registerPushIntentService, parameter [userIntentService] is null, use default Receiver");
                name = "";
            }
            this.intentService = name;
            if (this.uService != null) {
                Intent intent = new Intent(context.getApplicationContext(), (Class<?>) this.uService);
                intent.putExtra(o.b, this.intentService);
                startService(context, intent);
            }
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a("PushManager|registerPushIntentService|" + th.toString(), new Object[0]);
        }
    }

    public boolean sendApplinkFeedback(Context context, String str) {
        checkContext(context);
        if (TextUtils.isEmpty(str)) {
            com.igexin.b.a.c.a.c.a().a("[PushManager] call - > sendApplinkFeedback failed, parameter is illegal");
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("action", "sendApplinkFeedback");
        bundle.putString("url", str);
        Intent intent = new Intent(context.getApplicationContext(), (Class<?>) getUserPushService(context));
        intent.putExtra("action", PushConsts.ACTION_BROADCAST_PUSHMANAGER);
        intent.putExtra("bundle", bundle);
        return startService(context, intent);
    }

    public boolean sendFeedbackMessage(Context context, String str, String str2, int i) {
        boolean z = (i >= 60001 && i <= 60999) || (i >= 90001 && i <= 90999);
        if (str == null || str2 == null || !z) {
            com.igexin.b.a.c.a.c.a().a("[PushManager] call - > sendFeedbackMessage failed, parameter is illegal");
            return false;
        }
        int assistAction = getAssistAction(i, str2);
        Bundle bundle = new Bundle();
        bundle.putString("action", "sendFeedbackMessage");
        bundle.putString("taskid", str);
        bundle.putString("messageid", str2);
        bundle.putString("actionid", String.valueOf(assistAction));
        Intent intent = new Intent(context.getApplicationContext(), (Class<?>) getUserPushService(context));
        intent.putExtra("action", PushConsts.ACTION_BROADCAST_PUSHMANAGER);
        intent.putExtra("bundle", bundle);
        return startService(context, intent);
    }

    public boolean sendMessage(Context context, String str, byte[] bArr) {
        checkContext(context);
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (str == null || bArr == null || bArr.length > 4096 || jCurrentTimeMillis - this.lastSendMessageTime < 1000) {
            com.igexin.b.a.c.a.c.a().a("[PushManager] call - > sendMessage failed, parameter is illegal or it be called too frequently");
            return false;
        }
        this.lastSendMessageTime = jCurrentTimeMillis;
        Bundle bundle = new Bundle();
        bundle.putString("action", "sendMessage");
        bundle.putString("taskid", str);
        bundle.putByteArray("extraData", bArr);
        Intent intent = new Intent(context.getApplicationContext(), (Class<?>) getUserPushService(context));
        intent.putExtra("action", PushConsts.ACTION_BROADCAST_PUSHMANAGER);
        intent.putExtra("bundle", bundle);
        return startService(context, intent);
    }

    public void setDebugLogger(Context context, IUserLoggerInterface iUserLoggerInterface) {
        if (context == null || iUserLoggerInterface == null) {
            throw new IllegalArgumentException("context or loggerInterface can not be null");
        }
        try {
            if (!c.a(context)) {
                iUserLoggerInterface.log("only run in debug mode");
                return;
            }
            if (!isMainProcess(context)) {
                iUserLoggerInterface.log("Must be called in main process!");
                return;
            }
            try {
                checkManifest(context);
            } catch (GetuiPushException e) {
                iUserLoggerInterface.log(e.toString());
            }
            com.igexin.b.a.c.a.c cVarA = com.igexin.b.a.c.a.c.a();
            if (iUserLoggerInterface == null) {
                Log.i("LogController", "register parameter can not be null!");
                return;
            }
            Context applicationContext = context.getApplicationContext();
            cVarA.a(applicationContext);
            cVarA.b.a(iUserLoggerInterface);
            cVarA.b.a();
            cVarA.a("[LogController] Sdk version = " + getInstance().getVersion(applicationContext));
        } catch (Throwable unused) {
        }
    }

    public boolean setHeartbeatInterval(Context context, int i) {
        checkContext(context);
        if (i < 0) {
            com.igexin.b.a.c.a.c.a().a("[PushManager] call -> setHeartbeatInterval failed, parameter [interval] < 0, illegal");
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("action", "setHeartbeatInterval");
        bundle.putInt("interval", i);
        Intent intent = new Intent(context.getApplicationContext(), (Class<?>) getUserPushService(context));
        intent.putExtra("action", PushConsts.ACTION_BROADCAST_PUSHMANAGER);
        intent.putExtra("bundle", bundle);
        return startService(context, intent);
    }

    public boolean setHwBadgeNum(Context context, int i) {
        com.igexin.b.a.c.a.c.a().a("[PushManager] call - > setHwBadgeNum");
        try {
            Bundle bundle = new Bundle();
            bundle.putString("action", "setHwBadgeNum");
            bundle.putInt("badgeNum", i);
            Intent intent = new Intent(context.getApplicationContext(), (Class<?>) getUserPushService(context));
            intent.putExtra("action", PushConsts.ACTION_BROADCAST_PUSHMANAGER);
            intent.putExtra("bundle", bundle);
            return startService(context, intent);
        } catch (Throwable unused) {
            return false;
        }
    }

    public boolean setOPPOBadgeNum(int i) {
        return d.b(i);
    }

    public boolean setSilentTime(Context context, int i, int i2) {
        checkContext(context);
        if (i < 0 || i >= 24 || i2 < 0 || i2 > 23) {
            com.igexin.b.a.c.a.c.a().a("[PushManager] call - > setSilentTime failed, parameter [beginHour] or [duration] value exceeding");
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("action", "setSilentTime");
        bundle.putInt("beginHour", i);
        bundle.putInt(MediationConstant.EXTRA_DURATION, i2);
        Intent intent = new Intent(context.getApplicationContext(), (Class<?>) getUserPushService(context));
        intent.putExtra("action", PushConsts.ACTION_BROADCAST_PUSHMANAGER);
        intent.putExtra("bundle", bundle);
        return startService(context, intent);
    }

    public boolean setSocketTimeout(Context context, int i) {
        checkContext(context);
        if (i < 0) {
            com.igexin.b.a.c.a.c.a().a("[PushManager] call - > setSocketTimeout failed, parameter [timeout] < 0, illegal");
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("action", "setSocketTimeout");
        bundle.putInt("timeout", i);
        Intent intent = new Intent(context.getApplicationContext(), (Class<?>) getUserPushService(context));
        intent.putExtra("action", PushConsts.ACTION_BROADCAST_PUSHMANAGER);
        intent.putExtra("bundle", bundle);
        return startService(context, intent);
    }

    public int setTag(Context context, Tag[] tagArr, String str) {
        if (tagArr == null) {
            com.igexin.b.a.c.a.c.a().a("[PushManager] call -> setTag failed, parameter [tags] is null");
            com.igexin.b.a.c.a.a("PushManager|tags is null", new Object[0]);
        } else {
            if (str == null) {
                com.igexin.b.a.c.a.c.a().a("[PushManager] call -> setTag failed, parameter [sn] is null");
                sendSetTagResult(context, str, "20007");
                return PushConsts.SETTAG_SN_NULL;
            }
            if (tagArr.length > 200) {
                com.igexin.b.a.c.a.c.a().a("[PushManager] call -> setTag failed, parameter [tags] len > 200 is exceeds");
                sendSetTagResult(context, str, PushConsts.SEND_MESSAGE_ERROR_GENERAL);
                return PushConsts.SETTAG_ERROR_COUNT;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - this.lastSetTagTime < 1000) {
                com.igexin.b.a.c.a.c.a().a("[PushManager] call - > setTag failed, it be called too frequently");
                sendSetTagResult(context, str, PushConsts.SEND_MESSAGE_ERROR_TIME_OUT);
                return PushConsts.SETTAG_ERROR_FREQUENCY;
            }
            StringBuilder sb = new StringBuilder();
            for (Tag tag : tagArr) {
                if (tag != null && tag.getName() != null) {
                    if (tag.getName().contains(" ") || tag.getName().contains(b.aj)) {
                        com.igexin.b.a.c.a.c.a().a("[PushManager] call -> setTag failed, the tag [" + tag.getName() + "] is not illegal");
                        sendSetTagResult(context, str, "20011");
                        return PushConsts.SETTAG_TAG_ILLEGAL;
                    }
                    sb.append(tag.getName());
                    sb.append(b.aj);
                }
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
                com.igexin.b.a.c.a.c.a().a("[PushManager] call setTag");
                Bundle bundle = new Bundle();
                bundle.putString("action", "setTag");
                bundle.putString("tags", sb.toString());
                bundle.putString("sn", str);
                this.lastSetTagTime = jCurrentTimeMillis;
                Intent intent = new Intent(context.getApplicationContext(), (Class<?>) getUserPushService(context));
                intent.putExtra("action", PushConsts.ACTION_BROADCAST_PUSHMANAGER);
                intent.putExtra("bundle", bundle);
                startService(context, intent);
                return 0;
            }
        }
        sendSetTagResult(context, str, "20006");
        return PushConsts.SETTAG_ERROR_NULL;
    }

    public boolean setVivoAppBadgeNum(int i) {
        return d.a(i);
    }

    public void turnOffPush(Context context) {
        com.igexin.b.a.c.a.c.a().a("PushManager|call turnOffPush");
        Bundle bundle = new Bundle();
        bundle.putString("action", "turnOffPush");
        Intent intent = new Intent(context.getApplicationContext(), (Class<?>) getUserPushService(context));
        intent.putExtra("action", PushConsts.ACTION_BROADCAST_PUSHMANAGER);
        intent.putExtra("bundle", bundle);
        startService(context, intent);
        unRegisterCallback(context);
    }

    public void turnOnPush(Context context) {
        com.igexin.b.a.c.a.c.a().a("PushManager|call turnOnPush");
        Intent intent = new Intent(context.getApplicationContext(), (Class<?>) getUserPushService(context));
        intent.putExtra("action", PushConsts.ACTION_SERVICE_INITIALIZE_SLAVE);
        intent.putExtra("op_app", context.getApplicationContext().getPackageName());
        intent.putExtra("isSlave", true);
        startService(context, intent);
        registerCallback(context);
    }

    public boolean unBindAlias(Context context, String str, boolean z) {
        return unBindAlias(context, str, z, "unBindAlias_" + System.currentTimeMillis());
    }

    public boolean unBindAlias(Context context, String str, boolean z, String str2) {
        com.igexin.b.a.c.a.c.a().a("PushManager|call unBindAlias");
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.lastOpAliasTime < 1000) {
            com.igexin.b.a.c.a.c.a().a("[PushManager] call - > unBindAlias failed, it be called too frequently");
            sendUnBindAliasResult(context, str2, "30001");
            return false;
        }
        this.lastOpAliasTime = jCurrentTimeMillis;
        Bundle bundle = new Bundle();
        bundle.putString("action", "unbindAlias");
        bundle.putString("alias", str);
        bundle.putBoolean("isSeft", z);
        bundle.putString("sn", str2);
        Intent intent = new Intent(context.getApplicationContext(), (Class<?>) getUserPushService(context));
        intent.putExtra("action", PushConsts.ACTION_BROADCAST_PUSHMANAGER);
        intent.putExtra("bundle", bundle);
        return startService(context, intent);
    }
}
