package com.volcengine.cloudphone.apiservice;

import java.util.List;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface GamePodControlService {

    public interface ForwardStreamListener {
        void onForwardStreamState(List<StateInfo> list);
    }

    public interface SessionModeListener {
        void onResult(int i, boolean z);
    }

    public static class StateInfo {
        public final int error;
        public final String roomId;
        public final int state;

        public StateInfo(String str, int i, int i2) {
            this.roomId = str;
            this.state = i;
            this.error = i2;
        }

        public String toString() {
            return "StateInfo{roomId='" + this.roomId + "', state=" + this.state + ", error=" + this.error + '}';
        }
    }

    int getSessionMode();

    void setForwardStreamListener(ForwardStreamListener forwardStreamListener);

    void setListener(SessionModeListener sessionModeListener);

    int setSessionMode(int i);
}
