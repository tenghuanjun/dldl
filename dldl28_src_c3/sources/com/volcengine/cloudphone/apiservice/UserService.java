package com.volcengine.cloudphone.apiservice;

import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface UserService {

    public interface ControlListener {
        void onAllControlsResult(int i, List<ControlState> list, String str);

        void onControlStateChanged(ControlState controlState);

        void onEnableControlResult(int i, ControlState controlState, String str);

        void onHasControlResult(int i, ControlState controlState, String str);

        void onUserJoin(String str);

        void onUserLeave(String str);
    }

    public static class ControlState {
        public final boolean enable;
        public final String userId;

        public ControlState(String str, boolean z) {
            this.userId = str;
            this.enable = z;
        }

        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("userId", this.userId);
                jSONObject.put("enable", this.enable);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        public String toString() {
            return "UserControlState{userId='" + this.userId + ", enable=" + this.enable + '}';
        }
    }

    int enableControl(String str, boolean z);

    int getAllControls();

    int hasControl(String str);

    void setControlListener(ControlListener controlListener);
}
