package com.sq.push.service;

import android.content.Context;
import com.sq.push.service.IHttpRequester;
import com.sqwan.common.route.FunctionRouter;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class HttpManager {
    private static final String URL_GET_CONFIG = "http://app-liefer.37.com.cn/app/push/param";
    private static final String URL_PUSH_TOKEN = "http://app-liefer.37.com.cn/app/push/alias";
    private final String gid;
    private final IHttpRequester mRequester;
    private final String pid;
    private final String pkg;

    interface PushConfigCallback {
        void onFailed();

        void onSuccess(boolean enable);
    }

    HttpManager(Context context, IHttpRequester requester, String pid, String gid) {
        this.mRequester = requester;
        this.pkg = context.getPackageName();
        this.pid = pid;
        this.gid = gid;
    }

    void requestConfig(final PushConfigCallback callback) {
        HashMap map = new HashMap();
        map.put("apk_name", this.pkg);
        map.put("pid", this.pid);
        map.put("gid", this.gid);
        this.mRequester.get(URL_GET_CONFIG, null, map, new IHttpRequester.IRequestCallback() { // from class: com.sq.push.service.HttpManager.1
            @Override // com.sq.push.service.IHttpRequester.IRequestCallback
            public void onSuccess(int statusCode, String jsonStr) {
                try {
                    JSONObject jSONObject = new JSONObject(jsonStr);
                    if (jSONObject.optInt("code") == 0) {
                        callback.onSuccess(jSONObject.optJSONObject(FunctionRouter.KEY_DATA) != null);
                    } else {
                        callback.onSuccess(false);
                    }
                } catch (Exception unused) {
                    callback.onSuccess(false);
                }
            }

            @Override // com.sq.push.service.IHttpRequester.IRequestCallback
            public void onFailure(int code, String message) {
                callback.onFailed();
            }
        });
    }

    void pushToken(String token, String uid, String roleId) {
        HashMap map = new HashMap();
        map.put("apk_name", this.pkg);
        map.put("pid", this.pid);
        map.put("gid", this.gid);
        map.put("cid", token);
        if (uid == null) {
            uid = "";
        }
        map.put("uid", uid);
        if (roleId == null) {
            roleId = "";
        }
        map.put("rid", roleId);
        this.mRequester.postJson(URL_PUSH_TOKEN, null, map, new IHttpRequester.IRequestCallback() { // from class: com.sq.push.service.HttpManager.2
            @Override // com.sq.push.service.IHttpRequester.IRequestCallback
            public void onFailure(int code, String message) {
            }

            @Override // com.sq.push.service.IHttpRequester.IRequestCallback
            public void onSuccess(int statusCode, String jsonStr) {
            }
        });
    }
}
