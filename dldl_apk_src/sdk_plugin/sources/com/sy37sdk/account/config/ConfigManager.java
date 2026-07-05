package com.sy37sdk.account.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.sq.webview.net.IRequest;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.msdk.api.SQResultListener;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ConfigManager {
    private static volatile ConfigManager sInstance;
    private String JumpUrl;
    private Context mContext;
    private SQResultListener commentListener = null;
    private SQResultListener policyListener = null;
    private String SQ_PREFS = "sq_prefs";

    public static ConfigManager getInstance() {
        if (sInstance == null) {
            synchronized (ConfigManager.class) {
                if (sInstance == null) {
                    sInstance = new ConfigManager();
                }
            }
        }
        return sInstance;
    }

    public void initConfigInfo(final Context context) {
        setContext(context);
        ConfigRequest.getConfigInfo(context, new IRequest.RequestCallback<JSONObject>() { // from class: com.sy37sdk.account.config.ConfigManager.1
            @Override // com.sq.webview.net.IRequest.RequestCallback
            public void onSuccess(JSONObject jSONObject) {
                try {
                    LogUtil.i("initCommentInfo请求成功，jsonObject：" + jSONObject);
                    if (!jSONObject.isNull("good_review")) {
                        ConfigManager.this.setCommentInfo(context, jSONObject.getJSONObject("good_review").toString());
                    }
                    if (!jSONObject.isNull("double_list")) {
                        ConfigManager.this.setPolicyInfo(context, jSONObject.getJSONObject("double_list").toString());
                    }
                    ConfigManager.this.initInfo(context);
                } catch (JSONException e) {
                    e.printStackTrace();
                    LogUtil.i("获取哆啦A梦后台sdk配置信息异常，e：" + e);
                }
            }

            @Override // com.sq.webview.net.IRequest.RequestCallback
            public void onError(int i, String str) {
                LogUtil.i("initCommentInfo请求失败，errorMsg：" + str);
            }
        });
    }

    public void jumpComment() {
        JSONObject jSONObject;
        String strOptString;
        SQResultListener commentListener = getInstance().getCommentListener();
        String jumpUrl = getInstance().getJumpUrl();
        if (jumpUrl.isEmpty()) {
            commentListener.onFailture(3, "参数异常，没有跳转参数");
            return;
        }
        try {
            jSONObject = new JSONObject(jumpUrl);
        } catch (Exception e) {
            e = e;
            jSONObject = null;
        }
        try {
            strOptString = jSONObject.optString("market");
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            strOptString = "";
        }
        byte b = -1;
        int iHashCode = strOptString.hashCode();
        if (iHashCode != -1944913515) {
            if (iHashCode == -1797116640 && strOptString.equals("TapTap")) {
                b = 1;
            }
        } else if (strOptString.equals("HaoYouKuaiBao")) {
            b = 0;
        }
        if (b == 0) {
            if (OpenReview.toHaoYouKuaiBaoMarketDetails(this.mContext, jSONObject.optString("gameId"))) {
                commentListener.onSuccess((Bundle) null);
                return;
            }
            commentListener.onFailture(3, "跳转好游快爆应用商店失败");
            ToastUtil.showToast(this.mContext, "请检查是否有安装好游快爆应用商店");
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.COMMENT_ERROR);
            return;
        }
        if (b == 1) {
            if (OpenReview.toTapTapMarketDetails()) {
                commentListener.onSuccess((Bundle) null);
                return;
            }
            commentListener.onFailture(3, "跳转TapTap应用商店失败");
            ToastUtil.showToast(this.mContext, "请检查是否有安装TapTap应用商店");
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.COMMENT_ERROR);
            return;
        }
        commentListener.onFailture(3, "请检查跳转应用商店参数");
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.COMMENT_ERROR);
    }

    public void initInfo(Context context) {
        OpenReview.getTapTapInfo(context);
    }

    public void handlePolicySuccess(Bundle bundle, SqTrackAction2 sqTrackAction2, SQResultListener sQResultListener) {
        sQResultListener.onSuccess(bundle);
        SqTrackActionManager2.getInstance().trackAction(sqTrackAction2);
        LogUtil.i("handlePolicySuccess: " + sqTrackAction2.getName());
    }

    public void handlePolicyFailure(int i, String str, SqTrackAction2 sqTrackAction2, SQResultListener sQResultListener) {
        sQResultListener.onFailture(i, str);
        HashMap map = new HashMap();
        map.put(SqTrackKey.reason_fail, str);
        map.put(SqTrackKey.fail_code, String.valueOf(i));
        SqTrackActionManager2.getInstance().trackAction(sqTrackAction2, map);
        LogUtil.i("handlePolicyFailure,code:" + i + " ,msg:" + str);
    }

    public void setCommentInfo(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(this.SQ_PREFS, 0).edit();
        editorEdit.putString("commentInfo", str);
        editorEdit.commit();
    }

    public String getCommentInfo(Context context) {
        return context.getSharedPreferences(this.SQ_PREFS, 0).getString("commentInfo", "");
    }

    public void setPolicyInfo(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(this.SQ_PREFS, 0).edit();
        editorEdit.putString("policyInfo", str);
        editorEdit.commit();
    }

    public String getPolicyInfo(Context context) {
        return context.getSharedPreferences(this.SQ_PREFS, 0).getString("policyInfo", "");
    }

    public void setCommentListener(SQResultListener sQResultListener) {
        this.commentListener = sQResultListener;
    }

    public SQResultListener getCommentListener() {
        return this.commentListener;
    }

    public void setPolicyListener(SQResultListener sQResultListener) {
        this.policyListener = sQResultListener;
    }

    public SQResultListener getPolicyListener() {
        return this.policyListener;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public Context getContext() {
        return this.mContext;
    }

    public void setJumpUrl(String str) {
        this.JumpUrl = str;
    }

    public String getJumpUrl() {
        return this.JumpUrl;
    }
}
