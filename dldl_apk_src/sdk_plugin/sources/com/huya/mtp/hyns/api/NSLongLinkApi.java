package com.huya.mtp.hyns.api;

import com.huya.mtp.hyns.NSApi;
import com.huya.mtp.hyns.protocol.NSLongLinkProtocol;
import com.huya.mtp.utils.Utils;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@NSApi(NSLongLinkProtocol.class)
public interface NSLongLinkApi {

    public interface PushListener {
        public static final int STATUS_CONNECTED = 4;
        public static final int STATUS_CONNECTTING = 3;
        public static final int STATUS_GATEWAY_FAILED = 1;
        public static final int STATUS_NETWORK_UNAVAILABLE = 0;
        public static final int STATUS_NETWORK_UNKNOWN = -1;
        public static final int STATUS_SERVER_FAILED = 2;

        void onLinkStateChange(int i);

        void onPush(HySignalMessage hySignalMessage);
    }

    void addPushListener(PushListener pushListener);

    boolean addPushStatInfoListener(PushStatListener pushStatListener);

    int getLinkStatus();

    Call newCall(Request request);

    void removePushListener(PushListener pushListener);

    boolean removePushStatInfoListener(PushStatListener pushStatListener);

    void updateExperimentConfig(Map<String, String> map);

    public interface PushStatListener {
        void onPushStatInfo(PushMsgType pushMsgType, long j, long j2, String str, long j3, long j4);

        public enum PushMsgType {
            SIGNAL_SERVER_PUSH_TYPE(1),
            P2P_PUSH_TYPE(2),
            RETRY_PULL_TYPE(3);

            private int index;

            PushMsgType(int i) {
            }

            public static PushMsgType valueOf(int i) {
                if (i == 2) {
                    return P2P_PUSH_TYPE;
                }
                if (i == 3) {
                    return RETRY_PULL_TYPE;
                }
                return SIGNAL_SERVER_PUSH_TYPE;
            }

            public int value() {
                return this.index;
            }
        }
    }

    public static final class HySignalMessage {
        public int iUri = 0;
        public long msgId = 0;
        public byte[] sMsg = null;
        public String sGroupId = "";
        public boolean isFromQuery = false;
        public boolean isFromP2p = false;
        public String sourceLinkChannel = Utils.ChinaOperator.UNKNOWN;

        public String getSourceLongLinkChannel() {
            return this.sourceLinkChannel;
        }

        public void setSourceLongLinkChannel(String str) {
            this.sourceLinkChannel = str;
        }

        public int getIUri() {
            return this.iUri;
        }

        public void setIUri(int i) {
            this.iUri = i;
        }

        public long getMsgId() {
            return this.msgId;
        }

        public void setMsgId(long j) {
            this.msgId = j;
        }

        public byte[] getSMsg() {
            return this.sMsg;
        }

        public void setSMsg(byte[] bArr) {
            this.sMsg = bArr;
        }

        public String getSGroupId() {
            return this.sGroupId;
        }

        public void setSGroupId(String str) {
            this.sGroupId = str;
        }

        public boolean isFromQuery() {
            return this.isFromQuery;
        }

        public void setFromQuery(boolean z) {
            this.isFromQuery = z;
        }

        public boolean isFromP2p() {
            return this.isFromP2p;
        }

        public void setFromP2p(boolean z) {
            this.isFromP2p = z;
        }

        public HySignalMessage(int i, byte[] bArr, String str, long j, boolean z, String str2) {
            setIUri(i);
            setSMsg(bArr);
            setSGroupId(str);
            setMsgId(j);
            setFromQuery(z);
            setSourceLongLinkChannel(str2);
        }
    }
}
