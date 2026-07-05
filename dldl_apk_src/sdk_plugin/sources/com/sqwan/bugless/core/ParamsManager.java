package com.sqwan.bugless.core;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.sqwan.bugless.model.AppExtension;
import com.sqwan.bugless.model.UserInfo;
import com.sqwan.bugless.util.ChannelUtil;
import com.sqwan.bugless.util.LogUtil;
import com.sqwan.bugless.util.MacUtil;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ParamsManager {
    public static final String CONFIG_FILE_NAME = "37wan_config.xml";
    public static final String CONFIG_NODE_GAMEID = "gameid";
    public static final String CONFIG_NODE_PARTNER = "partner";
    public static final String CONFIG_NODE_REFER = "referer";
    private static ParamsManager instance;
    private Map<String, String> deviceInfo = new HashMap();
    private AppExtension extension;
    private boolean isDeviceParmasInit;
    private String mAPPId;
    private String mAppSecret;
    private Context mContext;
    private UserInfo mUserInfo;
    private long startTime;

    private ParamsManager() {
    }

    public static ParamsManager getInstance() {
        if (instance == null) {
            instance = new ParamsManager();
        }
        return instance;
    }

    public void init(Context context) {
        init(context, null);
    }

    public void init(Context context, AppExtension extension) {
        this.mContext = context;
        initAppSecret();
        initStartTime();
        if (extension == null) {
            initStatInfo();
        } else {
            initExtension(extension);
        }
    }

    private void initStatInfo() {
        AssetManager assets = this.mContext.getAssets();
        if (assets != null) {
            AppExtension appExtension = new AppExtension();
            try {
                InputStream inputStreamOpen = assets.open(CONFIG_FILE_NAME);
                if (inputStreamOpen == null) {
                    LogUtil.e("37wan_config.xml文件不存在");
                    return;
                }
                XmlPullParser xmlPullParserNewPullParser = XmlPullParserFactory.newInstance().newPullParser();
                xmlPullParserNewPullParser.setInput(inputStreamOpen, "utf-8");
                for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                    if (eventType == 2) {
                        if (xmlPullParserNewPullParser.getName().equals(CONFIG_NODE_GAMEID)) {
                            appExtension.setGid(xmlPullParserNewPullParser.nextText().trim());
                        }
                        if (xmlPullParserNewPullParser.getName().equals(CONFIG_NODE_PARTNER)) {
                            appExtension.setPid(xmlPullParserNewPullParser.nextText().trim());
                        }
                        if (xmlPullParserNewPullParser.getName().equals("referer")) {
                            appExtension.setRefer(xmlPullParserNewPullParser.nextText().trim());
                        }
                    }
                }
                initExtension(getChannelInfoFromAPK(appExtension));
            } catch (Exception unused) {
                LogUtil.e("读取 apk 渠道信息失败！");
            }
        }
    }

    private AppExtension getChannelInfoFromAPK(AppExtension extension) {
        if (extension.getPid() != null && !"1".equals(extension.getPid())) {
            LogUtil.w("pid is not 1");
            return extension;
        }
        try {
            String channelFromAssets = ChannelUtil.getChannelFromAssets(this.mContext);
            LogUtil.d("channel info is " + channelFromAssets);
            if (!"".equals(channelFromAssets)) {
                String[] strArrSplit = channelFromAssets.split("-");
                if (strArrSplit.length == 3) {
                    extension.setGid(strArrSplit[0]);
                    extension.setPid(strArrSplit[1]);
                    extension.setRefer(strArrSplit[2]);
                }
            }
        } catch (Exception unused) {
            LogUtil.e("读取 apk 渠道信息失败！");
        }
        return extension;
    }

    private void initDeviceParams(Boolean isHasPermission) {
        String string;
        if (this.mContext == null) {
            LogUtil.e("bugly未初始化");
            return;
        }
        LogUtil.d("初始化设备参数");
        this.isDeviceParmasInit = true;
        try {
            PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 1);
            if (packageInfo != null) {
                this.deviceInfo.put(Constant.PKG_VERSION_NAME, packageInfo.versionName);
                this.deviceInfo.put(Constant.PKG_VERSION_CODE, packageInfo.versionCode + "");
                this.deviceInfo.put(Constant.PKG_NAME, packageInfo.packageName);
                this.deviceInfo.put(Constant.APP_NAME, ((Object) this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 128).loadLabel(this.mContext.getPackageManager())) + "");
            }
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.e("an error occured when collect package info");
        }
        this.deviceInfo.put(Constant.DEV_SYS_VERSION, Build.VERSION.RELEASE);
        this.deviceInfo.put(Constant.DEV_MODEL, Build.MODEL);
        this.deviceInfo.put("brand", Build.BRAND);
        this.deviceInfo.put("os", "Android");
        WindowManager windowManager = (WindowManager) this.mContext.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            this.deviceInfo.put("wpi", displayMetrics.widthPixels + "");
            this.deviceInfo.put("hpi", displayMetrics.heightPixels + "");
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            this.deviceInfo.put(Constant.DEV_NETWORK, "none");
        } else {
            int type = activeNetworkInfo.getType();
            if (type == 0) {
                this.deviceInfo.put(Constant.DEV_NETWORK, "mobile");
            } else if (type == 1) {
                this.deviceInfo.put(Constant.DEV_NETWORK, "wifi");
            }
        }
        if (Build.VERSION.SDK_INT < 21) {
            string = Build.CPU_ABI;
        } else {
            String[] strArr = Build.SUPPORTED_ABIS;
            StringBuilder sb = new StringBuilder();
            for (String str : strArr) {
                sb.append(str + ";");
            }
            string = sb.toString();
        }
        this.deviceInfo.put("cpu", string);
        this.deviceInfo.put("mac", isHasPermission.booleanValue() ? MacUtil.getMac(this.mContext) : "");
        this.deviceInfo.put(Constant.PROCESS_ID, this.mContext.getApplicationContext().getPackageName());
    }

    private void initStartTime() {
        this.startTime = System.currentTimeMillis();
    }

    public void initUserInfo(UserInfo userInfo) {
        this.mUserInfo = userInfo;
        LogUtil.i("初始化用户信息");
        LogUtil.i("uid : " + userInfo.getId());
        LogUtil.i("uname : " + userInfo.getName());
    }

    public void initExtension(AppExtension extension) {
        this.extension = extension;
        LogUtil.i("初始化应用扩展信息 pid: " + extension.getPid() + ", gid: " + extension.getGid() + ", refer: " + extension.getRefer());
    }

    private void initAppSecret() {
        try {
            ApplicationInfo applicationInfo = this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                String string = applicationInfo.metaData.getString(Constant.BUGLESS_APP_SECRET);
                String string2 = applicationInfo.metaData.getString(Constant.BUGLESS_APP_ID);
                LogUtil.i("init app secret : " + string);
                this.mAppSecret = string;
                this.mAPPId = string2;
                return;
            }
            LogUtil.e("meta data in manifest is null, return");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getAppSecret() {
        return this.mAppSecret;
    }

    public String getAPPId() {
        return this.mAPPId;
    }

    public AppExtension getExtension() {
        return this.extension;
    }

    public UserInfo getUserInfo() {
        return this.mUserInfo;
    }

    public Map<String, String> getDeviceInfo(Boolean isHasPermission) {
        if (!this.isDeviceParmasInit) {
            initDeviceParams(isHasPermission);
        }
        return this.deviceInfo;
    }

    public long getDuration() {
        return System.currentTimeMillis() - this.startTime;
    }
}
