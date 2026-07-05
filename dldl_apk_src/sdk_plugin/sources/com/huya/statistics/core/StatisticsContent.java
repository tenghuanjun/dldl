package com.huya.statistics.core;

import com.huya.mtp.api.MTPApi;
import com.huya.statistics.log.SLog;
import com.huya.statistics.util.Util;
import com.hy.HyDeviceProxy;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class StatisticsContent {
    public static final String ACT = "act";
    public static final String APPID = "app";
    public static final String APPKEY = "appkey";
    public static final String CHA = "cha";
    public static final String CHANNEL = "chn";
    public static final String CUTC = "cutc";
    public static final String EVENT_DESC = "eid_desc";
    public static final String EVENT_LABEL = "label";
    public static final String EVENT_PROP = "prop";
    public static final String EVENT_id = "eid";
    public static final String FROM = "from";
    public static final String GUID = "guid";
    public static final String IMEI = "imei";
    public static final String LLA = "local_language";
    public static final String MAC = "mac";
    public static final String MBOS = "mbos";
    public static final String MID = "mid";
    public static final String NET = "net";
    public static final String NTM = "ntm";
    public static final String OS = "os";
    public static final String PRO = "pro";
    public static final String SCO = "sco";
    public static final String SDKVER = "sdkver";
    public static final String SJM = "sjm";
    public static final String SJP = "sjp";
    public static final String SRE = "sre";
    public static final String SUUID = "uuid";
    public static final String SYS = "sys";
    public static final String TIME = "time";
    public static final String VER = "ver";
    private TreeMap<String, String> raw;
    private UUID uuid;
    private static final String PRO_UUID = UUID.randomUUID().toString();
    private static AtomicLong mRepCnt = new AtomicLong(1);
    private static AtomicLong mRepTimes = new AtomicLong(1);

    public StatisticsContent() {
        TreeMap<String, String> treeMap = new TreeMap<>();
        this.raw = treeMap;
        treeMap.put(CUTC, String.valueOf(System.currentTimeMillis()));
    }

    public String put(String str, int i) {
        return put(str, String.valueOf(i));
    }

    public String put(String str, long j) {
        return put(str, String.valueOf(j));
    }

    public String put(String str, double d) {
        return put(str, String.valueOf(d));
    }

    public String get(String str) {
        return this.raw.get(str);
    }

    public synchronized void putAll(Map<String, String> map) {
        if (map != null) {
            if (map.size() > 0) {
                this.raw.putAll(map);
            }
        }
    }

    public boolean containsKey(String str) {
        return this.raw.containsKey(str);
    }

    public synchronized String put(String str, String str2) {
        String strPut;
        if (Util.empty(str)) {
            SLog.error(StatisticsContent.class, "key is invalid for value %s", str2);
            strPut = null;
        } else {
            strPut = this.raw.put(str, Util.asEmptyOnNull(str2));
        }
        return strPut;
    }

    public boolean isEmpty() {
        return this.raw.isEmpty();
    }

    public UUID getUUId() {
        return this.uuid;
    }

    public void createUUID() {
        UUID uuidRandomUUID = UUID.randomUUID();
        this.uuid = uuidRandomUUID;
        this.raw.put("uuid", uuidRandomUUID.toString());
    }

    public void addCommonFields() {
        this.raw.put("sdid", HyDeviceProxy.instance().fastGetSDID());
        this.raw.put("pro_uuid", PRO_UUID);
        this.raw.put("rep_cnt", mRepCnt.getAndIncrement() + "");
        this.raw.put("rep_times", mRepTimes.get() + "");
        String oaid = MTPApi.DID.getOaid();
        this.raw.put("oaid", oaid != null ? oaid : "");
    }

    static void addRepTimes() {
        mRepTimes.getAndIncrement();
    }

    public TreeMap<String, String> getRaw() {
        return this.raw;
    }
}
