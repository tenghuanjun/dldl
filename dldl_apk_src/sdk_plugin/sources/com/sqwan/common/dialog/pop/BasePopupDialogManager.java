package com.sqwan.common.dialog.pop;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.parameters.bean.WebDialogBean;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.download.IDownloadMod;
import com.sqwan.common.net.risk.RiskWebActivity;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.track.SqTrackUtil;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.UrlUtils;
import com.sqwan.common.web.SY37PortraitWebPage;
import com.sqwan.common.web.SY37web;
import com.sqwan.common.webview.SQWebViewDialog;
import com.sqwan.msdk.api.SQResultListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class BasePopupDialogManager {
    protected Context mContext;
    protected List<PopupDialogBean> popupDialogBeans;
    private int index = 0;
    private int DOWNLOAD_DIALOG = 99;

    public abstract String getDesc();

    public abstract void requestPopup();

    public void handlePopup(Context context) {
        this.mContext = context;
        requestPopup();
    }

    public void setPopupData(String str) {
        try {
            if (this.popupDialogBeans == null) {
                this.popupDialogBeans = new ArrayList();
            }
            this.popupDialogBeans.clear();
            JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("urls");
            if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
                return;
            }
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                PopupDialogBean popupDialogBeanDecodeFromJson = PopupDialogBean.decodeFromJson(jSONArrayOptJSONArray.getJSONObject(i));
                if (!TextUtils.isEmpty(popupDialogBeanDecodeFromJson.getUrl())) {
                    this.popupDialogBeans.add(popupDialogBeanDecodeFromJson);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<PopupDialogBean> getPopupDialogs() {
        return this.popupDialogBeans;
    }

    public boolean needShowPopup() {
        List<PopupDialogBean> list = this.popupDialogBeans;
        return list != null && list.size() > 0;
    }

    public void showPopupDialog(final Context context, final OnDialogFinishListener onDialogFinishListener) {
        if (needShowPopup()) {
            showPopupDialog(context, this.index, new DialogInterface.OnDismissListener() { // from class: com.sqwan.common.dialog.pop.BasePopupDialogManager.1
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    BasePopupDialogManager.this.index++;
                    if (BasePopupDialogManager.this.index < BasePopupDialogManager.this.popupDialogBeans.size()) {
                        BasePopupDialogManager basePopupDialogManager = BasePopupDialogManager.this;
                        basePopupDialogManager.showPopupDialog(context, basePopupDialogManager.index, this);
                    } else {
                        OnDialogFinishListener onDialogFinishListener2 = onDialogFinishListener;
                        if (onDialogFinishListener2 != null) {
                            onDialogFinishListener2.onFinishPopup();
                        }
                        BasePopupDialogManager.this.index = 0;
                    }
                }
            });
        }
    }

    public void showPopupDialog(OnDialogFinishListener onDialogFinishListener) {
        showPopupDialog(this.mContext, onDialogFinishListener);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void showPopupDialog(Context context, int i, DialogInterface.OnDismissListener onDismissListener) {
        List<PopupDialogBean> list = this.popupDialogBeans;
        if (list == null || i >= list.size()) {
            return;
        }
        PopupDialogBean popupDialogBean = this.popupDialogBeans.get(i);
        WebDialogBean webDialogBean = new WebDialogBean();
        webDialogBean.setUrl(popupDialogBean.getUrl());
        if ((context instanceof Activity) && !((Activity) context).isFinishing()) {
            String queryParameter = Uri.parse(popupDialogBean.getUrl()).getQueryParameter("forceOrientation");
            if (popupDialogBean.getAction_id() == this.DOWNLOAD_DIALOG) {
                ((IDownloadMod) ModHelper.get(IDownloadMod.class)).installApk(getActionTemplateIdFromUrl(popupDialogBean.getUrl()), new SQResultListener() { // from class: com.sqwan.common.dialog.pop.BasePopupDialogManager.2
                    public void onSuccess(Bundle bundle) {
                        LogUtil.i("下载弹窗成功显示");
                    }

                    public void onFailture(int i2, String str) {
                        LogUtil.i("下载弹窗失败");
                    }
                });
                return;
            }
            if (TextUtils.equals(queryParameter, "1") && !SqTrackUtil.isScreenOriatationPortrait(context)) {
                boolean zIsSupportPlugin = isSupportPlugin();
                Intent intent = new Intent();
                intent.setFlags(268435456);
                intent.putExtra("url", popupDialogBean.getUrl());
                intent.setClass(context, zIsSupportPlugin ? SY37web.class : SY37PortraitWebPage.class);
                intent.putExtra(RiskWebActivity.INTENT_KEY_IN_SCREEN_ORIENTATION, "portrait");
                context.startActivity(intent);
            } else {
                SQWebViewDialog sQWebViewDialog = new SQWebViewDialog(context);
                sQWebViewDialog.setUrl(webDialogBean.getUrl());
                sQWebViewDialog.setShowWebBar(webDialogBean.isShowToolBar());
                sQWebViewDialog.setAllowJumpURL(false);
                sQWebViewDialog.setOnDismissListener(onDismissListener);
                sQWebViewDialog.setCancelable(!popupDialogBean.isForce());
                sQWebViewDialog.show();
            }
            HashMap map = new HashMap();
            map.put(SqTrackKey.push_id, popupDialogBean.getId());
            map.put(SqTrackKey.push_link, popupDialogBean.getUrl());
            map.put(SqTrackKey.push_scene_id, getDesc());
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.sdk_push_show, map);
            return;
        }
        LogUtil.i("context is not an Activity or Activity is finishing ");
    }

    public String getActionTemplateIdFromUrl(String str) {
        String valueFromUrlStrByParamName;
        return (TextUtils.isEmpty(str) || (valueFromUrlStrByParamName = UrlUtils.readValueFromUrlStrByParamName(str, "action_template_id")) == null) ? "" : valueFromUrlStrByParamName;
    }

    private static boolean isSupportPlugin() {
        try {
            Class<?> cls = Class.forName("com.sqwan.msdk.SQwanCore");
            boolean zBooleanValue = ((Boolean) cls.getMethod("isSupportPlugin", new Class[0]).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), new Object[0])).booleanValue();
            LogUtil.i("isSupportPlugin() 返回值：" + zBooleanValue);
            return zBooleanValue;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            LogUtil.e("反射 isSupportPlugin 方法失败", e);
            return false;
        }
    }
}
