package com.duowan.networkmars.hysignal;

import android.content.Context;
import android.text.TextUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.Config;
import com.duowan.auk.util.L;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HySignalSDK {
    public static final String DEFAULT_URL;
    public static final String DEFAULT_URL_DEBUG = "https://test-websocket.va.huya.com:4434";
    public static final String DEFAULT_URL_RELEASE = "https://wup.huya.com/";
    private static final String KEY_LONG_IP_LIST = "longIpList";
    private static final String KEY_SHORT_IP_LIST = "shortIpList";
    private static final String TAG = "HySignalSDK";
    private static volatile HySignalSDK mInstance;
    private Config config;
    private Context mAppContext;
    private String mAppVersion;
    private String mChannel;
    private String mGuid;
    private String mHuyaUA;
    private boolean mIsTestModel;
    private boolean mNeedDoLaunch;
    private long mUid;
    private String mUrl;
    private final List<String> mAddress = new ArrayList();
    private boolean mIsUpdateList = false;

    static {
        DEFAULT_URL = ArkValue.debuggable() ? DEFAULT_URL_DEBUG : DEFAULT_URL_RELEASE;
    }

    private HySignalSDK() {
    }

    public static HySignalSDK getInstance() {
        if (mInstance == null) {
            synchronized (HySignalSDK.class) {
                if (mInstance == null) {
                    mInstance = new HySignalSDK();
                }
            }
        }
        return mInstance;
    }

    public void init(Context context, boolean z, String str) {
        this.mAppContext = context;
        this.mIsTestModel = z;
        this.config = Config.getInstance(context);
        this.mHuyaUA = str;
    }

    public void setUid(long j) {
        this.mUid = j;
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }

    public void updateIpList(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        if (this.mNeedDoLaunch) {
            if (arrayList.size() == 0 && arrayList2.size() == 0) {
                if (this.mIsUpdateList) {
                    return;
                }
                getShortIpList();
                getLongIpList();
            } else {
                setShortIpList(arrayList);
                setLongIpList(arrayList2);
            }
            this.mIsUpdateList = true;
        }
    }

    public Context getAppContext() {
        return this.mAppContext;
    }

    public void setAppVersion(String str) {
        this.mAppVersion = str;
    }

    public String getAppVersion() {
        return TextUtils.isEmpty(this.mAppVersion) ? "1.0.0" : this.mAppVersion;
    }

    public void setChannel(String str) {
        this.mChannel = str;
    }

    public String getChannel() {
        return TextUtils.isEmpty(this.mChannel) ? "official" : this.mChannel;
    }

    public long getUid() {
        long j = this.mUid;
        if (j == 0) {
            return -1L;
        }
        return j;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public boolean isTestModel() {
        return this.mIsTestModel;
    }

    private synchronized String randomUrl() {
        synchronized (this.mAddress) {
            if (this.mAddress.isEmpty()) {
                return DEFAULT_URL;
            }
            int iNextInt = new Random().nextInt(this.mAddress.size());
            if (this.mAddress.size() <= iNextInt) {
                return DEFAULT_URL;
            }
            String str = this.mAddress.get(iNextInt);
            L.info(TAG, String.format("randomUrl:%s", str));
            return str;
        }
    }

    public synchronized void removeUrl() {
        L.info(TAG, String.format("removeUrl:%s", this.mUrl));
        synchronized (this.mAddress) {
            Iterator<String> it = this.mAddress.iterator();
            while (it.hasNext()) {
                if (it.next().equals(this.mUrl)) {
                    it.remove();
                }
            }
        }
        setUrl(randomUrl());
    }

    public String getGUID() {
        return this.mGuid;
    }

    public void setShortIpList(List<String> list) {
        HashSet hashSet = new HashSet();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next());
        }
        this.config.setStringSet(KEY_SHORT_IP_LIST, hashSet);
    }

    public ArrayList<String> getShortIpList() {
        ArrayList<String> arrayList = new ArrayList<>();
        Set<String> stringSet = this.config.getStringSet(KEY_SHORT_IP_LIST, null);
        if (stringSet != null) {
            Iterator<String> it = stringSet.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
        }
        return arrayList;
    }

    public void setLongIpList(List<String> list) {
        HashSet hashSet = new HashSet();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next());
        }
        this.config.setStringSet(KEY_LONG_IP_LIST, hashSet);
    }

    public ArrayList<String> getLongIpList() {
        ArrayList<String> arrayList = new ArrayList<>();
        Set<String> stringSet = this.config.getStringSet(KEY_LONG_IP_LIST, null);
        if (stringSet != null) {
            Iterator<String> it = stringSet.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
        }
        return arrayList;
    }
}
