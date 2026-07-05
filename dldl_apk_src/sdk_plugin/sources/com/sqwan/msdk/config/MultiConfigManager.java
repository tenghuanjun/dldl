package com.sqwan.msdk.config;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.util.LogUtil;
import java.util.HashMap;
import java.util.Properties;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class MultiConfigManager {
    private static final String MULTI_SDK = "multiconfig";
    private static final String PRO_KEY_ORIENTATION = "isLandScape";
    private static final String PRO_KEY_SKIN = "skin";
    private static final String PRO_KEY_SQ_UNION = "isSqUnion";
    private static final String PRO_KEY_WECHAT = "isWechat";
    private static final String TAG = "MultiConfigManager";
    private static volatile MultiConfigManager instance;
    private String orientation;
    private String skin;
    private String sqUnion;
    private String wechat;

    private MultiConfigManager() {
    }

    public static MultiConfigManager getInstance() {
        if (instance == null) {
            synchronized (MultiConfigManager.class) {
                if (instance == null) {
                    instance = new MultiConfigManager();
                }
            }
        }
        return instance;
    }

    public void initMultiConfig(Context context) throws Throwable {
        Properties properties = readProperties(context);
        this.sqUnion = properties.getProperty(PRO_KEY_SQ_UNION);
        this.orientation = properties.getProperty(PRO_KEY_ORIENTATION);
        this.wechat = properties.getProperty(PRO_KEY_WECHAT);
        this.skin = properties.getProperty(PRO_KEY_SKIN);
        LogUtil.i(TAG, "initMultiConfig");
        LogUtil.e(TAG, "multiconfig: " + this.sqUnion + " orientation:" + this.orientation + " wechat:" + this.wechat + " skin:" + this.skin);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0072 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.Properties readProperties(android.content.Context r8) throws java.lang.Throwable {
        /*
            r7 = this;
            java.lang.String r0 = "readProperties finally: "
            java.lang.String r1 = "MultiConfigManager"
            r2 = 0
            android.content.res.Resources r8 = r8.getResources()     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45
            android.content.res.AssetManager r8 = r8.getAssets()     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45
            java.lang.String r3 = "multiconfig"
            java.io.InputStream r8 = r8.open(r3)     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45
            java.util.Properties r3 = new java.util.Properties     // Catch: java.io.IOException -> L3b java.lang.Throwable -> L6f
            r3.<init>()     // Catch: java.io.IOException -> L3b java.lang.Throwable -> L6f
            r3.load(r8)     // Catch: java.io.IOException -> L39 java.lang.Throwable -> L6f
            if (r8 == 0) goto L6e
            r8.close()     // Catch: java.io.IOException -> L21
            goto L6e
        L21:
            r8 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
        L27:
            r2.append(r0)
            java.lang.String r8 = r8.getMessage()
            r2.append(r8)
            java.lang.String r8 = r2.toString()
            android.util.Log.e(r1, r8)
            goto L6e
        L39:
            r2 = move-exception
            goto L49
        L3b:
            r3 = move-exception
            r6 = r3
            r3 = r2
            r2 = r6
            goto L49
        L40:
            r8 = move-exception
            r6 = r2
            r2 = r8
            r8 = r6
            goto L70
        L45:
            r8 = move-exception
            r3 = r2
            r2 = r8
            r8 = r3
        L49:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6f
            r4.<init>()     // Catch: java.lang.Throwable -> L6f
            java.lang.String r5 = "readProperties catch: "
            r4.append(r5)     // Catch: java.lang.Throwable -> L6f
            java.lang.String r2 = r2.getMessage()     // Catch: java.lang.Throwable -> L6f
            r4.append(r2)     // Catch: java.lang.Throwable -> L6f
            java.lang.String r2 = r4.toString()     // Catch: java.lang.Throwable -> L6f
            android.util.Log.e(r1, r2)     // Catch: java.lang.Throwable -> L6f
            if (r8 == 0) goto L6e
            r8.close()     // Catch: java.io.IOException -> L67
            goto L6e
        L67:
            r8 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            goto L27
        L6e:
            return r3
        L6f:
            r2 = move-exception
        L70:
            if (r8 == 0) goto L8d
            r8.close()     // Catch: java.io.IOException -> L76
            goto L8d
        L76:
            r8 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r8 = r8.getMessage()
            r3.append(r8)
            java.lang.String r8 = r3.toString()
            android.util.Log.e(r1, r8)
        L8d:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sqwan.msdk.config.MultiConfigManager.readProperties(android.content.Context):java.util.Properties");
    }

    public boolean isSqUnion() {
        return !TextUtils.isEmpty(this.sqUnion) && this.sqUnion.equals("1");
    }

    public boolean isLandscape() {
        return !TextUtils.isEmpty(this.orientation) && this.orientation.equals("1");
    }

    public boolean isWechat() {
        return !TextUtils.isEmpty(this.wechat) && this.wechat.equals("1");
    }

    public String getSkinType() {
        return this.skin;
    }

    public boolean supportWx(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            String string = applicationInfo.metaData.getString("wx_appid");
            String string2 = applicationInfo.metaData.getString("wx_appkey");
            if (TextUtils.isEmpty(string)) {
                return false;
            }
            return !TextUtils.isEmpty(string2);
        } catch (Exception e) {
            HashMap map = new HashMap();
            map.put("error", e.getMessage());
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.SDK_COMMON_ERROR, map);
            e.printStackTrace();
            LogUtil.e(TAG, "未找到微信参数");
            return false;
        }
    }
}
