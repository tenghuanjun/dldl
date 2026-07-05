package com.huya.mtp.hyns.api;

import com.huya.mtp.hyns.NSApi;
import com.huya.mtp.hyns.protocol.NSRegisterProtocol;
import java.util.ArrayList;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@NSApi(NSRegisterProtocol.class)
public interface NSRegisterApi {

    public interface JoinChannelListener {
        void onJoinFailed();

        void onJoinPasswordFailed();

        void onJoinPasswordSucceed();

        void onJoinSucceed();
    }

    public interface RegisterPushMsgListener {
        void onRegisterFailed(RegistResultInfo registResultInfo);

        void onRegisterSucceed(RegistResultInfo registResultInfo);
    }

    public interface UnRegisterPushMsgListener {
        void onUnRegisterFailed(RegistResultInfo registResultInfo);

        void onUnRegisterSucceed(RegistResultInfo registResultInfo);
    }

    void reRegisterGroupsIfNeed();

    void registerGroup(ArrayList<String> arrayList, RegisterPushMsgListener registerPushMsgListener);

    @Deprecated
    void registerLiveGroup(long j, String str, JoinChannelListener joinChannelListener);

    void unRegisterGroup(ArrayList<String> arrayList, UnRegisterPushMsgListener unRegisterPushMsgListener);

    void unRegisterLiveGroup(long j, UnRegisterPushMsgListener unRegisterPushMsgListener);

    public static class RegistResultInfo {
        String groupId;
        int status;

        public String getGroupId() {
            return this.groupId;
        }

        public void setGroupId(String str) {
            this.groupId = str;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        RegistResultInfo() {
            this.groupId = "";
            this.status = 0;
        }

        RegistResultInfo(int i) {
            this.groupId = "";
            this.status = 0;
            this.status = i;
        }

        public RegistResultInfo(String str, int i) {
            this.groupId = "";
            this.status = 0;
            this.groupId = str;
            this.status = i;
        }

        public String toString() {
            return "RegistResultInfo{groupId='" + this.groupId + "', status=" + this.status + AbstractJsonLexerKt.END_OBJ;
        }
    }
}
