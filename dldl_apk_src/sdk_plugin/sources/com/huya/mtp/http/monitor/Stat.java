package com.huya.mtp.http.monitor;

import java.util.TreeMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Stat {
    public static final int DECODE_RESPONSE_BEGIN = 105;
    public static final String DECODE_RESPONSE_BEGIN_KEY = "decode_response_begin";
    public static final int DECODE_RESPONSE_END = 109;
    public static final String DECODE_RESPONSE_END_KEY = "decode_response_end";
    public static final int DISPATCHER_THREAD_EXECUTE_TIME = 104;
    public static final String DISPATCHER_THREAD_EXECUTE_TIME_KEY = "dispatcher_thread_execute_time";
    public static final int EXECUTE = 100;
    public static final int EXECUTE_END = 112;
    public static final String EXECUTE_END_KEY = "execute_end";
    public static final String EXECUTE_KEY = "execute";
    public static final int HYSIGNAL_ENQUEUE = 101;
    public static final String HYSIGNAL_ENQUEUE_KEY = "hysignal_enqueue";
    public static final int HYSIGNAL_RESPONSE = 102;
    public static final String HYSIGNAL_RESPONSE_KEY = "hysignal_response";
    public static final int JOIN_DISPATCHER_THREAD = 103;
    public static final String JOIN_DISPATCHER_THREAD_KEY = "join_dispatcher_thread";
    public static final String UNIPACKET_BEGIN_KEY = "unipacket_decode_begin";
    public static final int UNIPACKET_DECODE_BEGIN = 106;
    public static final int UNIPACKET_DECODE_END = 107;
    public static final int UNIPACKET_ENCODE_BEGIN = 113;
    public static final String UNIPACKET_ENCODE_BEGIN_KEY = "unipacket_encode_begin";
    public static final int UNIPACKET_ENCODE_END = 114;
    public static final String UNIPACKET_ENCODE_END_KEY = "unipacket_encode_end";
    public static final String UNIPACKET_END_KEY = "unipacket_decode_end";
    public static final int UNIPACKET_READ_RSP_END = 108;
    public static final String UNIPACKET_READ_RSP_END_KEY = "unipacket_read_rsp_end";
    public static final int WRITE_CACHE_BEGIN = 110;
    public static final String WRITE_CACHE_BEGIN_KEY = "write_cache_begin";
    public static final int WRITE_CACHE_END = 111;
    public static final String WRITE_CACHE_END_KEY = "write_cache_end";
    private TreeMap<Integer, Long> statMap = new TreeMap<>();

    public String getStatKey(int i) {
        switch (i) {
            case 100:
                return EXECUTE_KEY;
            case 101:
                return HYSIGNAL_ENQUEUE_KEY;
            case 102:
                return HYSIGNAL_RESPONSE_KEY;
            case 103:
                return JOIN_DISPATCHER_THREAD_KEY;
            case 104:
                return DISPATCHER_THREAD_EXECUTE_TIME_KEY;
            case 105:
                return DECODE_RESPONSE_BEGIN_KEY;
            case 106:
                return UNIPACKET_BEGIN_KEY;
            case 107:
                return UNIPACKET_END_KEY;
            case 108:
                return UNIPACKET_READ_RSP_END_KEY;
            case 109:
                return DECODE_RESPONSE_END_KEY;
            case 110:
                return WRITE_CACHE_BEGIN_KEY;
            case 111:
                return WRITE_CACHE_END_KEY;
            case 112:
                return EXECUTE_END_KEY;
            case 113:
                return UNIPACKET_ENCODE_BEGIN_KEY;
            case 114:
                return UNIPACKET_ENCODE_END_KEY;
            default:
                return "";
        }
    }

    public void updateState(int i) {
        switch (i) {
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.statMap.put(Integer.valueOf(i), Long.valueOf(System.currentTimeMillis()));
                break;
        }
    }

    public TreeMap<Integer, Long> getStatMap() {
        return this.statMap;
    }
}
