package com.huya.mtp.hyns;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Constants {
    public static final String CHINA_NEW_REQUEST_LONG_HOST = "wswup.cdn.huya.com";
    public static final String CHINA_QUIC_HOST = "wsquic.va.huya.com";
    public static final String CHINA_TEST_HOST = "testws.va.huya.com";
    public static final int CLIENT_VERSION = 100;
    public static final String DYNAMIC_ENABLE_STRING = "1";
    public static final int EBoth = 3;
    public static final int EBothWithoutQuic = 6;
    public static final int ECustomWs = 7;
    public static final int ELong = 2;
    public static final String ENABLE_KEEP_ALIVE = "hysignal_enable_keep_alive";
    public static final String ENABLE_SHORT_MONITOR = "hysignal_enable_short_monitor";
    public static final String ENABLE_TLS_1_3_KEY = "hysignal_enable_tls_1_3";
    public static final int EPushChannel = 5;
    public static final int EQuic = 4;
    public static final int EShort = 1;
    public static final int ETASK_PRIORITY_0 = 0;
    public static final int ETASK_PRIORITY_1 = 1;
    public static final int ETASK_PRIORITY_2 = 2;
    public static final int ETASK_PRIORITY_3 = 3;
    public static final int ETASK_PRIORITY_4 = 4;
    public static final int ETASK_PRIORITY_5 = 5;
    public static final int ETASK_PRIORITY_HIGHEST = 0;
    public static final int ETASK_PRIORITY_LOWEST = 5;
    public static final int ETASK_PRIORITY_NORMAL = 3;
    public static final String KEEP_ALIVE_MAX_COUNT = "hysignal_keep_alive_max_count";
    public static final int MSG_PUSH_REQ = 7;
    public static final int MSG_PUSH_REQ_V2 = 22;
    public static final String NATIONAL_LONG_LINK_HOST = "cdnws.api.huya.com";
    public static final String NATIONAL_SHORT_LINK_HOST = "cdn.wup.huya.com";
    public static final String NS_ENABLE_MUTLI_CONN = "ns.hysignal_mutli_conn";
    public static final String NS_ENABLE_STRICT_IDLE = "ns.hysignal_enable_strict_idle";
    public static final String NS_IP_SORT_STRATEGY = "ns.hysignal_ip_sort_strategy";
    public static final String NS_PUSH_ACK_DELAY_MILLISECONDS = "ns.hysignal_push_ack_wait_mill";
    public static final String NS_QUIC_ENABLE = "ns.hysignal_quic_enable";
    public static final String NS_QUIC_PUSH_ENABLE = "ns.hysignal_quic_push_enable";
    public static final String OPEN_SYN_REGISTER = "ns.open_syn_register";
    public static final String OVERSEA_HOST = "wsapi.master.live";
    public static final String OVERSEA_TEST_HOST = "testws.master.live";
    public static final String PUSH_REPORT_ENABLE = "ns.need_push_report_msgid";
    public static final String QUIC_ENABLE = "hysignal_quic_enable";
    public static final String QUIC_PUSH_ENABLE = "hysignal_quic_push_enable";
    public static final String REFINE_PARA_ENABLE = "hysignal_refine_para_enable";

    public static class HySignalQuicStatus {
        public static final int STATUS_CONNECTED = 4;
        public static final int STATUS_CONNECTING = 2;
        public static final int STATUS_CONNECT_FAILED = 6;
        public static final int STATUS_CONNECT_IDLE = 0;
        public static final int STATUS_DISCONNECTED = 5;
        public static final int STATUS_DNS = 1;
        public static final int STATUS_HAND_SHAKE = 3;
    }

    public static class HySignalWsStatus {
        public static final int STATUS_CONNECTED = 4;
        public static final int STATUS_CONNECTTING = 3;
        public static final int STATUS_GATEWAY_FAILED = 1;
        public static final int STATUS_NETWORK_UNAVAILABLE = 0;
        public static final int STATUS_NETWORK_UNKNOWN = -1;
        public static final int STATUS_SERVER_FAILED = 2;
    }
}
