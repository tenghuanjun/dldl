package com.sy37sdk.account;

import android.text.TextUtils;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sq.tools.Logger;
import com.sqwan.common.request.CommonParamsV3;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.TimeTools;
import com.sqwan.common.util.task.Task;
import com.sy37sdk.account.db.LoginTrigger;
import com.sy37sdk.account.db.LoginTriggerDBManager;
import com.sy37sdk.account.db.LoginTriggerTable;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LoginTriggerManager {
    private static LoginTriggerManager loginTriggerManager;
    private Task queryTask = Task.create();
    private boolean isLoop = false;

    private LoginTriggerManager() {
    }

    public static LoginTriggerManager getInstance() {
        if (loginTriggerManager == null) {
            synchronized (LoginTriggerManager.class) {
                if (loginTriggerManager == null) {
                    loginTriggerManager = new LoginTriggerManager();
                }
            }
        }
        return loginTriggerManager;
    }

    public void startQueryLoginTrigger() {
        if (this.isLoop) {
            return;
        }
        Logger.info("启动查询假登录信息", new Object[0]);
        this.queryTask.repeat(0L, 300000L, new Task.TaskFunc() { // from class: com.sy37sdk.account.LoginTriggerManager.1
            @Override // com.sqwan.common.util.task.Task.TaskFunc
            public Task.Result exec() {
                StringBuilder sb = new StringBuilder();
                sb.append("queryLoginTrigger 当前时间：");
                sb.append(TimeTools.stampToDate(System.currentTimeMillis() + ""));
                Logger.info(sb.toString(), new Object[0]);
                LoginTriggerManager.this.uploadLoginTriggerData();
                return null;
            }
        });
        this.isLoop = true;
    }

    public void stopQueryLoginTrigger() {
        Logger.info("停止假登录信息查询", new Object[0]);
        if (this.isLoop) {
            this.queryTask.stop();
            this.isLoop = false;
        }
    }

    public void uploadLoginTriggerData() {
        LogUtil.i("查询假登录信息计时到了 uploadLoginTriggerData");
        List<LoginTrigger> listQuery = LoginTriggerDBManager.getInstance().query();
        if (listQuery == null || listQuery.isEmpty()) {
            LogUtil.i("查询假登录信息计时到了 no loginTriggerData");
            stopQueryLoginTrigger();
        } else {
            LogUtil.i("查询假登录信息计时到了 开始上报");
            reportLogin(listQuery);
        }
    }

    private void reportLogin(final List<LoginTrigger> list) {
        String string;
        if (list == null || list.isEmpty()) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray();
            for (LoginTrigger loginTrigger : list) {
                if (loginTrigger != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("uid", loginTrigger.getUid());
                    jSONObject.put("uname", loginTrigger.getUname());
                    jSONObject.put("token", loginTrigger.getToken());
                    jSONObject.put(LoginTriggerTable.TRIGGER_TIME, loginTrigger.getTriggerTime());
                    jSONObject.put("login_type", loginTrigger.getLoginType());
                    jSONArray.put(jSONObject);
                }
            }
            string = jSONArray.toString();
        } catch (Exception e) {
            e.printStackTrace();
            string = "";
        }
        LogUtil.i("开始上报假登录：" + string);
        if (TextUtils.isEmpty(string)) {
            return;
        }
        SqRequest.of(UrlConstant.URL_LOGIN_REPORT).signV3().addParam("users", string).addParamsTransformer(new CommonParamsV3()).post(new SqHttpCallback.SimpleSqHttpCallback<Void>() { // from class: com.sy37sdk.account.LoginTriggerManager.2
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(Void r2) {
                LoginTriggerDBManager.getInstance().delete(list);
            }
        }, Void.class);
    }
}
