package cn.thinkingdata.android;

import android.content.Context;
import cn.thinkingdata.android.ThinkingAnalyticsSDK;
import cn.thinkingdata.android.encrypt.TDSecreteKey;
import cn.thinkingdata.android.utils.TDLog;
import cn.thinkingdata.android.utils.r;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class TDConfig {
    private static final String TAG = "ThinkingAnalytics.TDConfig";
    public static final String VERSION = "2.8.3.5";
    private static final Map<Context, Map<String, TDConfig>> sInstances = new HashMap();
    private volatile boolean mAllowedDebug;
    private final cn.thinkingdata.android.q.d mConfigStoragePlugin;
    private final String mConfigUrl;
    final Context mContext;
    private final m mContextConfig;
    private final String mDebugUrl;
    private TimeZone mDefaultTimeZone;
    private boolean mEnableMutiprocess;
    private SSLSocketFactory mSSLSocketFactory;
    private final String mServerUrl;
    final String mToken;
    private volatile String name;
    private final Set<String> mDisabledEvents = new HashSet();
    private final ReadWriteLock mDisabledEventsLock = new ReentrantReadWriteLock();
    private volatile ModeEnum mMode = ModeEnum.NORMAL;
    private int mNetworkType = 255;
    private volatile boolean mTrackOldData = true;
    private TDSecreteKey secreteKey = null;
    boolean mEnableEncrypt = false;

    public enum ModeEnum {
        NORMAL,
        DEBUG,
        DEBUG_ONLY
    }

    public final class NetworkType {
        public static final int TYPE_2G = 1;
        public static final int TYPE_3G = 2;
        public static final int TYPE_4G = 4;
        public static final int TYPE_5G = 16;
        public static final int TYPE_ALL = 255;
        public static final int TYPE_WIFI = 8;

        public NetworkType() {
        }
    }

    class a implements Runnable {
        a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:110:0x0279  */
        /* JADX WARN: Removed duplicated region for block: B:114:0x026f A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:134:? A[SYNTHETIC] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 637
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.thinkingdata.android.TDConfig.a.run():void");
        }
    }

    static /* synthetic */ class b {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ThinkingAnalyticsSDK.ThinkingdataNetworkType.values().length];
            a = iArr;
            try {
                iArr[ThinkingAnalyticsSDK.ThinkingdataNetworkType.NETWORKTYPE_WIFI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ThinkingAnalyticsSDK.ThinkingdataNetworkType.NETWORKTYPE_DEFAULT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ThinkingAnalyticsSDK.ThinkingdataNetworkType.NETWORKTYPE_ALL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private TDConfig(Context context, String str, String str2) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mContextConfig = m.a(applicationContext);
        this.mToken = str;
        this.mServerUrl = str2 + "/sync";
        this.mDebugUrl = str2 + "/data_debug";
        this.mConfigUrl = str2 + "/config?appid=" + str;
        this.mConfigStoragePlugin = new cn.thinkingdata.android.q.d(this.mContext, str);
        this.mEnableMutiprocess = false;
    }

    static TDConfig getInstance(Context context, String str) {
        try {
            return getInstance(context, str, "");
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static TDConfig getInstance(Context context, String str, String str2) {
        return getInstance(context, str, str2, str);
    }

    public static TDConfig getInstance(Context context, String str, String str2, String str3) {
        TDConfig tDConfig;
        String str4;
        Context applicationContext = context.getApplicationContext();
        synchronized (sInstances) {
            Map<String, TDConfig> map = sInstances.get(applicationContext);
            if (map == null) {
                map = new HashMap<>();
                sInstances.put(applicationContext, map);
            }
            String strReplace = str.replace(" ", "");
            String strReplace2 = str3.replace(" ", "");
            tDConfig = map.get(strReplace2);
            if (tDConfig == null) {
                try {
                    URL url = new URL(str2);
                    StringBuilder sb = new StringBuilder();
                    sb.append(url.getProtocol());
                    sb.append("://");
                    sb.append(url.getHost());
                    if (url.getPort() > 0) {
                        str4 = ":" + url.getPort();
                    } else {
                        str4 = "";
                    }
                    sb.append(str4);
                    TDConfig tDConfig2 = new TDConfig(applicationContext, strReplace, sb.toString());
                    tDConfig2.setName(strReplace2);
                    map.put(strReplace2, tDConfig2);
                    tDConfig2.getRemoteConfig();
                    tDConfig = tDConfig2;
                } catch (MalformedURLException e) {
                    TDLog.e(TAG, "Invalid server URL: " + str2);
                    throw new IllegalArgumentException(e);
                }
            }
        }
        return tDConfig;
    }

    private void getRemoteConfig() {
        new Thread(new a()).start();
    }

    private void setName(String str) {
        this.name = str;
    }

    public TDConfig enableEncrypt(boolean z) {
        this.mEnableEncrypt = z;
        return this;
    }

    String getDebugUrl() {
        return this.mDebugUrl;
    }

    public synchronized TimeZone getDefaultTimeZone() {
        TimeZone timeZone;
        timeZone = this.mDefaultTimeZone;
        if (timeZone == null) {
            timeZone = TimeZone.getDefault();
        }
        return timeZone;
    }

    int getFlushBulkSize() {
        return ((Integer) this.mConfigStoragePlugin.a(cn.thinkingdata.android.q.g.FLUSH_SIZE)).intValue();
    }

    int getFlushInterval() {
        return ((Integer) this.mConfigStoragePlugin.a(cn.thinkingdata.android.q.g.FLUSH_INTERVAL)).intValue();
    }

    String getMainProcessName() {
        return this.mContextConfig.b();
    }

    public ModeEnum getMode() {
        return this.mMode;
    }

    public String getName() {
        return this.name;
    }

    public synchronized SSLSocketFactory getSSLSocketFactory() {
        return this.mSSLSocketFactory;
    }

    public TDSecreteKey getSecreteKey() {
        return this.secreteKey;
    }

    String getServerUrl() {
        return this.mServerUrl;
    }

    Map<String, TDConfig> getTDConfigMap() {
        return sInstances.get(this.mContext);
    }

    boolean isDebug() {
        return ModeEnum.DEBUG.equals(this.mMode);
    }

    boolean isDebugOnly() {
        return ModeEnum.DEBUG_ONLY.equals(this.mMode);
    }

    boolean isDisabledEvent(String str) {
        this.mDisabledEventsLock.readLock().lock();
        try {
            return this.mDisabledEvents.contains(str);
        } finally {
            this.mDisabledEventsLock.readLock().unlock();
        }
    }

    public boolean isEnableMutiprocess() {
        return this.mEnableMutiprocess;
    }

    boolean isNormal() {
        return ModeEnum.NORMAL.equals(this.mMode);
    }

    synchronized boolean isShouldFlush(String str) {
        return (r.a(str) & this.mNetworkType) != 0;
    }

    void setAllowDebug() {
        this.mAllowedDebug = true;
    }

    public synchronized TDConfig setDefaultTimeZone(TimeZone timeZone) {
        this.mDefaultTimeZone = timeZone;
        return this;
    }

    public TDConfig setMode(ModeEnum modeEnum) {
        this.mMode = modeEnum;
        return this;
    }

    public TDConfig setMutiprocess(boolean z) {
        this.mEnableMutiprocess = z;
        return this;
    }

    synchronized void setNetworkType(ThinkingAnalyticsSDK.ThinkingdataNetworkType thinkingdataNetworkType) {
        int i = b.a[thinkingdataNetworkType.ordinal()];
        if (i == 1) {
            this.mNetworkType = 8;
        } else if (i == 2 || i == 3) {
            this.mNetworkType = 31;
        }
    }

    public synchronized TDConfig setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        if (sSLSocketFactory != null) {
            this.mSSLSocketFactory = sSLSocketFactory;
            getRemoteConfig();
        }
        return this;
    }

    public TDConfig setSecretKey(TDSecreteKey tDSecreteKey) {
        if (this.secreteKey == null) {
            this.secreteKey = tDSecreteKey;
        }
        return this;
    }

    public TDConfig setTrackOldData(boolean z) {
        this.mTrackOldData = z;
        return this;
    }

    boolean shouldThrowException() {
        return false;
    }

    public boolean trackOldData() {
        return this.mTrackOldData;
    }
}
