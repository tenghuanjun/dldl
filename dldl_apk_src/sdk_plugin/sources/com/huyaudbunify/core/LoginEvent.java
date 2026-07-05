package com.huyaudbunify.core;

import android.util.SparseArray;
import android.util.SparseIntArray;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LoginEvent {

    public static class LoginMessage {
        public static final int onAddSList = 10017;
        public static final int onAnonymLoginRes = 10030;
        public static final int onDCChanged = 10007;
        public static final int onDbgStatus = 11000;
        public static final int onGetChannelVpInfo = 110011;
        public static final int onIMUInfo = 10015;
        public static final int onKickoff = 10019;
        public static final int onLeaveGuildRes = 10023;
        public static final int onLoginLinkConnErr = 10021;
        public static final int onLoginNGRes = 110004;
        public static final int onLogout = 10002;
        public static final int onMultiReqChannelInfoRes = 10022;
        public static final int onMyChanList = 10031;
        public static final int onMyInfo = 10011;
        public static final int onMyInfoAnonym = 10029;
        public static final int onNewLogin = 110013;
        public static final int onPingSdkWithDataBinRes = 110005;
        public static final int onRemoveSList = 10018;
        public static final int onReportApRtt = 110006;
        public static final int onSessRequestOperRes = 110012;
        public static final int onSvcData = 10008;
        public static final int onSyncList = 10012;
        public static final int onTransmitData = 10020;
        public static final int onUInfo = 10010;
        public static final int onUInfoLogo = 10013;
        public static final int onUInfoMod = 10016;
        public static final int onUserDCChanged = 10009;
    }

    public static class evtType {
        public static final int ETLOGIN_ADD_SLIST_RES = 17;
        public static final int ETLOGIN_ANONYM_LOGIN_RES = 30;
        public static final int ETLOGIN_DBG_STATUS = 1000;
        public static final int ETLOGIN_DC_CHANGED = 7;
        public static final int ETLOGIN_GET_VP_INFO_RES = 10011;
        public static final int ETLOGIN_HTTPDATA_SEND = 5;
        public static final int ETLOGIN_IMUINFO = 15;
        public static final int ETLOGIN_KICK_OFF_NTF = 19;
        public static final int ETLOGIN_LEAVE_GUILD_RES = 23;
        public static final int ETLOGIN_LINK_CONN_ERR = 20;
        public static final int ETLOGIN_LIST = 12;
        public static final int ETLOGIN_MULTI_CHANNEL_INFO = 22;
        public static final int ETLOGIN_MYCHAN_LIST = 31;
        public static final int ETLOGIN_MYINFO = 11;
        public static final int ETLOGIN_MYINFO_ANONYM = 29;
        public static final int ETLOGIN_NEW_LOGIN = 24;
        public static final int ETLOGIN_PICCODE = 14;
        public static final int ETLOGIN_PING_SDK_RES = 10005;
        public static final int ETLOGIN_REMOVE_SLIST_RES = 18;
        public static final int ETLOGIN_REPORT_AP_RTT = 10006;
        public static final int ETLOGIN_REQUEST_OPERATE_RES = 10012;
        public static final int ETLOGIN_RES_NG = 40;
        public static final int ETLOGIN_SERVICE_ACK = 8;
        public static final int ETLOGIN_SESS_RELOGIN = 4;
        public static final int ETLOGIN_SPECAP_TYPE = 10004;
        public static final int ETLOGIN_TRANSMIT_DATA = 10002;
        public static final int ETLOGIN_UINFO_KEYVAL = 10;
        public static final int ETLOGIN_UINFO_LOGO = 13;
        public static final int ETLOGIN_UINFO_MOD_RES = 16;
        public static final int ETLOGIN_USERINFO = 9;
        public static final int ETLOGIN_WAN_IPINFO = 999;
        public static final int ETLOGIN_WRITE_LOG = 6;
        public static final int EVENT_LOGIN_RES = 1;
        public static final int EVENT_LOGOUT = 2;
        public static final int EVENT_UAUTH_UPDATE = 3;
    }

    public class ProtoModType {
        public static final int MODE_TYPE_UNKNOW = 10001;
        public static final int MOD_TYPE_LOGIN = 0;
        public static final int MOD_TYPE_MEDIA = 2;
        public static final int MOD_TYPE_REPORT = 3;
        public static final int MOD_TYPE_SESSION = 1;
        public static final int MOD_TYPE_SVC = 4;

        public ProtoModType() {
        }
    }

    public static class LoginBaseEvent {
        String mContext;
        int mEvtType;

        public int modType() {
            return 0;
        }

        public void unmarshall(byte[] bArr) {
        }

        public int eventType() {
            return this.mEvtType;
        }

        public String getCtx() {
            return this.mContext;
        }
    }

    public static class LoginResNGEvent extends LoginBaseEvent {
        public static final int APKICKOFF = 1;
        public static final int LOGIN_SUCESS = 200;
        public static final int NET_BROKEN = 0;
        public static final int RES_UAUTH = 4;
        public static final int TIMEOUT = 2;
        public byte[] strAuthData;
        public int uAuthType;
        public int uMyIp;
        public int uSrvResCode;

        public LoginResNGEvent() {
            this.mEvtType = 40;
        }

        @Override // com.huyaudbunify.core.LoginEvent.LoginBaseEvent
        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
        }
    }

    public static class ETLoginKickoff extends LoginBaseEvent {
        public byte[] strReason;
        public int uReason;

        public ETLoginKickoff() {
            this.mEvtType = 19;
        }

        @Override // com.huyaudbunify.core.LoginEvent.LoginBaseEvent
        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
        }
    }

    public static class ETNewLogin extends LoginBaseEvent {
        public byte[] strReason;
        public int uReason;
        public long uid;

        public ETNewLogin() {
            this.mEvtType = 24;
        }

        @Override // com.huyaudbunify.core.LoginEvent.LoginBaseEvent
        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
        }
    }

    public static class UInfoKeyVal {
        public static final int UINFO_COOKIE = 103;
        public static final int UINFO_EXT = 105;
        public static final String UINFO_INVALID_STR = "";
        public static final int UINFO_INVALID_VALUE = -1;
        public static final int UINFO_IP = 4;
        public static final int UINFO_NICK = 100;
        public static final int UINFO_PASSPORT = 104;
        public static final int UINFO_PASSWORD = 108;
        public static final int UINFO_PORT = 5;
        public static final int UINFO_ROLE = 106;
        public static final int UINFO_SEX = 3;
        public static final int UINFO_SIGN = 101;
        public static final int UINFO_UDB = 102;
        public static final int UINFO_UID = 1;
        public static final int UINFO_VIP = 107;
        public static final int UINFO_YYID = 2;
        public SparseIntArray intVal;
        public SparseArray<byte[]> strVal;

        public long getUid() {
            return 0L;
        }

        public int getIntVal(int i) {
            return this.intVal.get(i, -1);
        }

        public byte[] getStrVal(int i) {
            return this.strVal.get(i, "".getBytes());
        }
    }

    public static class ETMyInfoAnonym extends LoginBaseEvent {
        public UInfoKeyVal uinfo;

        @Override // com.huyaudbunify.core.LoginEvent.LoginBaseEvent
        public void unmarshall(byte[] bArr) {
        }

        public ETMyInfoAnonym() {
            this.mEvtType = 29;
        }
    }
}
