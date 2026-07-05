package com.huya.hysignal.wrapper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TafConstants {

    public static class HistoryMsg {
        public static final int QUERY_MSG_STATUS_FAILED = 1;
        public static final int QUERY_MSG_STATUS_FINISH = 0;
        public static final int QUERY_MSG_STATUS_PULLING = 2;
        public static final int TYPE_GROUP_PUSH = 1;
        public static final int TYPE_UID_PUSH = 0;
    }

    public static class P2P {
        public static final int WS_P2P_CLOSE_NOTIFY = 1025308;
        public static final int WS_P2P_OPEN_NOTIFY = 1025307;
    }

    public static class TafETokenType {
        public static final int UDB_TOKEN = 0;
    }

    public static class TafRegPushMsgType {
        public static final int REG_PUSHMSG_ALL = 7;
        public static final int REG_PUSHMSG_CHANNEL = 2;
        public static final int REG_PUSHMSG_CHANNEL_AND_GROUP = 6;
        public static final int REG_PUSHMSG_GROUP = 4;
        public static final int REG_PUSHMSG_UID = 1;
        public static final int REG_PUSHMSG_UID_AND_CHANNEL = 3;
        public static final int REG_PUSHMSG_UID_AND_GROUP = 5;
    }

    public static class WebSocketCommandType {
        public static final int DEREGISTER_REQ = 8;
        public static final int ENTER_P2P = 27;
        public static final int EWSCmdC2S_MsgAckReq = 35;
        public static final int EWSCmdC2S_SyncGroupReq = 31;
        public static final int EWSCmdC2S_UpdateUserInfoReq = 33;
        public static final int EWSCmdS2C_MsgAckRsp = 36;
        public static final int EWSCmdS2C_SyncGroupRsp = 32;
        public static final int EWSCmdS2C_UpdateUserInfoRsp = 34;
        public static final int EXIT_P2P = 29;
        public static final int PULL_HISTORY_MSG_REQ = 25;
        public static final int REGISTER_GROUP_REQ = 16;
        public static final int REGISTER_REQ = 1;
        public static final int UNREGISTER_GROUP_REQ = 18;
        public static final int UN_VERIFY_TOKEN_REQ = 14;
        public static final int UPDATE_USER_EXPS_REQ = 23;
        public static final int VERIFY_HUYA_TOKEN_REQ = 12;
        public static final int WUP_REQ = 3;
    }
}
