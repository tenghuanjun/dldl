package com.sy37sdk.account.policy;

import android.content.Context;
import com.sq.tool.logger.SQLog;
import com.sq.tools.Logger;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.account.IAccountMod;
import com.sqwan.common.mod.account.IAuthResultListener;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SpUtils;
import com.sqwan.common.util.task.Task;
import com.sqwan.common.webview.SQWebViewDialog;
import com.sqwan.msdk.api.SQResultListener;
import com.sy37sdk.account.AccountCache;
import com.sy37sdk.account.auth.AntiManager;
import com.sy37sdk.account.auth.AuthConfigCache;
import com.sy37sdk.account.auth.floatview.AuthCountDownManager;
import com.sy37sdk.account.face.FaceVerifyManager;
import com.sy37sdk.account.policy.view.GuaranteeDialog;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PolicyManager {
    private static final String SP_KEY = "adult_uids_key";
    private static final String SP_NAME = "adult_uids";
    private static final PolicyManager instance = new PolicyManager();

    public static PolicyManager getInstance() {
        return instance;
    }

    private PolicyManager() {
    }

    public void showPolicyDialog(final Context context, String str, boolean z) {
        LogUtil.i("显示弹窗，focus: " + z + ", url:" + str);
        if (z) {
            LogUtil.i("处理弹窗并踢下线");
            final IAuthResultListener authResultListener = ((IAccountMod) ModHelper.get(IAccountMod.class)).getAuthResultListener();
            if (authResultListener != null) {
                Task.post(new Runnable() { // from class: com.sy37sdk.account.policy.PolicyManager.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ((IAccountMod) ModHelper.get(IAccountMod.class)).setSubmitRole(false);
                        AntiManager.getInstance(context).reset();
                        AuthCountDownManager.getInstance().stopReportAuth();
                        authResultListener.onAuthResult(false);
                        LogUtil.i("踢下线 onAuthResult false");
                    }
                });
            }
        }
        SQWebViewDialog sQWebViewDialog = new SQWebViewDialog(context);
        sQWebViewDialog.setUrl(AppUtils.constructWebUrlParam(context, str));
        sQWebViewDialog.setCancelable(false);
        sQWebViewDialog.show();
    }

    public void handleTimeLimit(Context context, long j, boolean z) {
        int age = AuthConfigCache.getAge();
        if (age == -1 || 18 - age <= 0) {
            return;
        }
        if (AuthConfigCache.isPlayDate() && AuthConfigCache.isPlayTimeRange(j)) {
            return;
        }
        SQLog.w("不在时间段内,弹时间段限制弹窗,并踢下线");
        showPolicyDialog(context, AuthConfigCache.getTimeRangeLimitUrl(), z);
    }

    public static void saveAuthAdult(Context context) {
        String userid = AccountCache.getUserid(context);
        ArrayList<String> authAdult = getAuthAdult(context);
        if (authAdult.contains(userid)) {
            return;
        }
        JSONArray jSONArray = new JSONArray();
        try {
            authAdult.add(userid);
            Iterator<String> it = authAdult.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.i("缓存实名的uid出错");
        }
        LogUtil.i("缓存实名的uid " + jSONArray.toString());
        SpUtils.get(context, SP_NAME).put(SP_KEY, jSONArray.toString());
    }

    public static ArrayList<String> getAuthAdult(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            JSONArray jSONArray = new JSONArray(SpUtils.get(context, SP_NAME).getString(SP_KEY, ""));
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.optString(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public void handlePCheckGuarantee(Context context, SQResultListener sQResultListener) {
        LogUtil.i("处理pcheck弱网");
        String userid = AccountCache.getUserid(context);
        ArrayList<String> authAdult = getAuthAdult(context);
        LogUtil.i("当前uid " + userid);
        LogUtil.i("缓存uid " + authAdult.toString());
        if (authAdult.contains(userid)) {
            LogUtil.i("该uid是实名成年用户，可继续游戏");
            AntiManager.getInstance(context).report();
            Logger.info("pcheck弱网，且是实名已成年，判断是否需要人脸识别", new Object[0]);
            FaceVerifyManager.getInstance(context).handleFaceVerify(sQResultListener);
            return;
        }
        LogUtil.i("该uid不是实名成年用户，弹窗");
        showGuaranteeDialog(context, "当前网络不稳定，请检查您的网络或稍后重试~");
    }

    public void handleReportGuarantee(Context context) {
        LogUtil.i("心跳接口弱网判断是否在时间段内");
        handleTimeLimit(context, System.currentTimeMillis(), true);
    }

    public void showGuaranteeDialog(Context context, String str) {
        GuaranteeDialog guaranteeDialog = new GuaranteeDialog(context);
        guaranteeDialog.setTip(str);
        guaranteeDialog.setCancelable(false);
        guaranteeDialog.show();
    }
}
