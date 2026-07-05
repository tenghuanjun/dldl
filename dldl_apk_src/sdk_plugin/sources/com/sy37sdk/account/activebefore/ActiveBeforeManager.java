package com.sy37sdk.account.activebefore;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.sq.tool.network.SqHttpCallback;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.base.BaseEnginHandler;
import com.sqwan.common.util.LogUtil;
import com.sy37sdk.account.uagree.UAgreeManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ActiveBeforeManager extends BaseEnginHandler {
    private static final ActiveBeforeManager ourInstance = new ActiveBeforeManager();
    private ActiveBeforeRequestManager activeBeforeRequestManager;
    private Handler handler;
    private Handler loopHandler;
    private long sTime;
    private UpdateDataCallback updateDataCallback;
    private int messageWhat = 0;
    private int messageCount = 0;
    private List<ActiveBeforeBaseInfo> baseInfos = new ArrayList();
    public PermissionInfo permissionInfo = new PermissionInfo();
    public UserProtocolInfo userProtocolInfo = new UserProtocolInfo();

    public interface UpdateDataCallback {
        void invoke();
    }

    public static ActiveBeforeManager getInstance() {
        return ourInstance;
    }

    private ActiveBeforeManager() {
    }

    @Override // com.sqwan.base.BaseEnginHandler
    public void init(Context context) {
        super.init(context);
        if (this.context != null) {
            this.activeBeforeRequestManager = new ActiveBeforeRequestManager();
            this.handler = new Handler(this.context.getMainLooper()) { // from class: com.sy37sdk.account.activebefore.ActiveBeforeManager.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    ActiveBeforeManager.this.messageCount++;
                    if (ActiveBeforeManager.this.messageCount == ActiveBeforeManager.this.baseInfos.size()) {
                        ActiveBeforeManager.this.invoke();
                    }
                }
            };
        }
        this.messageCount = 0;
        this.handler.removeMessages(this.messageWhat);
        this.baseInfos.clear();
        this.baseInfos.add(this.permissionInfo);
        this.baseInfos.add(this.userProtocolInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invoke() {
        if (this.updateDataCallback != null) {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.sTime;
            LogUtil.i(this.TAG, "dTime:" + jCurrentTimeMillis);
            for (ActiveBeforeBaseInfo activeBeforeBaseInfo : this.baseInfos) {
                LogUtil.i(this.TAG, "baseInfo:" + activeBeforeBaseInfo);
            }
            this.updateDataCallback.invoke();
        }
    }

    public void updateRsp() {
        Handler handler = this.handler;
        if (handler != null) {
            handler.sendEmptyMessage(this.messageWhat);
        }
    }

    private abstract class Callback extends SqHttpCallback<JSONObject> {
        abstract void onData(String str);

        private Callback() {
        }

        @Override // com.sq.tool.network.SqHttpCallback
        public void onSuccess(JSONObject jSONObject) {
            onData(jSONObject.toString());
            ActiveBeforeManager.this.updateRsp();
        }

        @Override // com.sq.tool.network.SqHttpCallback
        public void onFailure(int i, String str, VolleyError volleyError) {
            ActiveBeforeManager.this.updateRsp();
        }

        @Override // com.sdk.sq.net.SqRequestCallback
        public void onResponseStateError(int i, int i2, String str, String str2) {
            ActiveBeforeManager.this.updateRsp();
        }
    }

    private void updateUserprotocolInfo() {
        ActiveBeforeRequestManager activeBeforeRequestManager = this.activeBeforeRequestManager;
        if (activeBeforeRequestManager != null) {
            activeBeforeRequestManager.reqUserProtocol(new Callback() { // from class: com.sy37sdk.account.activebefore.ActiveBeforeManager.2
                @Override // com.sy37sdk.account.activebefore.ActiveBeforeManager.Callback
                void onData(String str) {
                    if (ActiveBeforeManager.this.checkValid() != null) {
                        UAgreeManager.getInstance().initConfig(str);
                    }
                    ActiveBeforeManager.this.userProtocolInfo.parse(str);
                }
            });
        }
    }

    private void updatePermissionInfo() {
        ActiveBeforeRequestManager activeBeforeRequestManager = this.activeBeforeRequestManager;
        if (activeBeforeRequestManager != null) {
            activeBeforeRequestManager.reqGetpermission(new Callback() { // from class: com.sy37sdk.account.activebefore.ActiveBeforeManager.3
                @Override // com.sy37sdk.account.activebefore.ActiveBeforeManager.Callback
                void onData(String str) {
                    ActiveBeforeManager.this.permissionInfo.parse(str);
                }
            });
        }
    }

    public void updateData(UpdateDataCallback updateDataCallback) {
        this.sTime = System.currentTimeMillis();
        this.updateDataCallback = updateDataCallback;
        updatePermissionInfo();
        updateUserprotocolInfo();
    }

    public void requestGameUrlList() {
        ActiveBeforeRequestManager activeBeforeRequestManager = this.activeBeforeRequestManager;
        if (activeBeforeRequestManager == null) {
            return;
        }
        activeBeforeRequestManager.getGameUrlList(new SqHttpCallback.SimpleSqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.activebefore.ActiveBeforeManager.4
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                int iOptInt = jSONObject.optInt("max_req_num");
                if (iOptInt == 0) {
                    return;
                }
                int iOptInt2 = jSONObject.optInt("req_frequency");
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("url_list");
                if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() == 0) {
                    return;
                }
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    String strOptString = jSONArrayOptJSONArray.optString(i);
                    if (!TextUtils.isEmpty(strOptString)) {
                        ActiveBeforeManager.this.loopRequestDomain(strOptString, iOptInt2, iOptInt);
                    }
                }
            }
        });
    }

    public void loopRequestDomain(final String str, final long j, final long j2) {
        if (this.activeBeforeRequestManager == null || j2 == 0) {
            return;
        }
        if (this.loopHandler == null) {
            this.loopHandler = new Handler();
        }
        this.activeBeforeRequestManager.requestCustomDomain(str);
        this.loopHandler.postDelayed(new Runnable() { // from class: com.sy37sdk.account.activebefore.-$$Lambda$ActiveBeforeManager$_etsJ2qa2i9rAmQkxELgk5jxQMU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$loopRequestDomain$0$ActiveBeforeManager(j2, str, j);
            }
        }, j * 1000);
    }

    public /* synthetic */ void lambda$loopRequestDomain$0$ActiveBeforeManager(long j, String str, long j2) {
        if (j == 1) {
            return;
        }
        loopRequestDomain(str, j2, j - 1);
    }
}
