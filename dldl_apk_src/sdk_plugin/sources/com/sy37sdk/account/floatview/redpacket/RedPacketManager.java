package com.sy37sdk.account.floatview.redpacket;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.sq.sdk.tool.download.DownloadListener;
import com.sq.sdk.tool.download.DownloadTask;
import com.sq.sdk.tool.util.MD5Util;
import com.sq.tool.network.SqHttpCallback;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SpUtils;
import com.sqwan.common.util.UrlUtils;
import com.sqwan.common.webview.SQWebViewDialog;
import com.sqwan.msdk.BaseSQwanCore;
import com.sy37sdk.account.AccountRequestManager;
import com.sy37sdk.account.floatview.SqFloatViewManager;
import com.sy37sdk.account.floatview.redpacket.RedPacketDialog;
import com.sy37sdk.account.floatview.redpacket.RedPacketInfo;
import java.io.File;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class RedPacketManager {
    public static final String SP_KEY_HASSHOWNREDPACKETPOP = "SP_KEY_HASSHOWNREDPACKETPOP";
    private static final String popImgUrlKey = "imgUrl";
    private static Map<String, String> roleInfos;
    private static RedPacketManager sInstance;
    private String TAG = "RedPacketManager";

    public interface DownloadGifCallback {
        void getLocalGifPath(String str, boolean z);
    }

    private void testRedPacketDialog(Activity activity) {
        RedPacketInfo redPacketInfo = new RedPacketInfo();
        RedPacketInfo.WebViewConfig webViewConfig = new RedPacketInfo.WebViewConfig();
        redPacketInfo.jumpLink = getCouponUrl(activity, "http://37.com.cn/huodong/20201207_gd_red_envelopes");
        webViewConfig.pop_url = "http://37.com.cn/huodong/20201207_gd_red_envelopes_popup";
        webViewConfig.height = 589;
        webViewConfig.width = 691;
        redPacketInfo.webViewConfig = webViewConfig;
        showRedPacketDialog(activity, redPacketInfo);
    }

    private void testGifUrl(final Activity activity) {
        final RedPacketInfo redPacketInfo = new RedPacketInfo();
        redPacketInfo.imgUrl = "https://s3.ax1x.com/2020/11/17/DVA1aT.gif";
        downloadFloatViewGifImg(activity, redPacketInfo, new DownloadGifCallback() { // from class: com.sy37sdk.account.floatview.redpacket.RedPacketManager.1
            @Override // com.sy37sdk.account.floatview.redpacket.RedPacketManager.DownloadGifCallback
            public void getLocalGifPath(String str, boolean z) {
                LogUtil.d(RedPacketManager.this.TAG, "getLocalGifPath localPath : " + str + " isCache : " + z);
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                redPacketInfo.imgLocalPath = str;
                activity.runOnUiThread(new Runnable() { // from class: com.sy37sdk.account.floatview.redpacket.RedPacketManager.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        SqFloatViewManager.getInstance().showRedPacketFloat(activity, redPacketInfo, false);
                        RedPacketManager.this.showRedPacketDialog(activity, redPacketInfo);
                    }
                });
            }
        });
    }

    public static RedPacketManager getInstance() {
        if (sInstance == null) {
            sInstance = new RedPacketManager();
        }
        return sInstance;
    }

    public void submitRoleInfos(Context context, Map<String, String> map) {
        roleInfos = map;
        getRedPacketFloatConfig((Activity) context, map.get(BaseSQwanCore.INFO_SERVERTIME));
    }

    public String getCouponUrl(Context context, String str) {
        return AppUtils.constructWebUrlParam(context, str) + "&dsid=" + roleInfos.get(BaseSQwanCore.INFO_SERVERID) + "&drid=" + roleInfos.get(BaseSQwanCore.INFO_ROLEID) + "&roleName=" + roleInfos.get(BaseSQwanCore.INFO_ROLENAME);
    }

    /* JADX INFO: renamed from: com.sy37sdk.account.floatview.redpacket.RedPacketManager$2, reason: invalid class name */
    class AnonymousClass2 extends SqHttpCallback.SimpleSqHttpCallback<JSONObject> {
        final /* synthetic */ Activity val$activity;

        AnonymousClass2(Activity activity) {
            this.val$activity = activity;
        }

        @Override // com.sq.tool.network.SqHttpCallback
        public void onSuccess(JSONObject jSONObject) {
            try {
                if (!jSONObject.optBoolean("show_red_float_window")) {
                    if (SqFloatViewManager.getInstance().isShowFloat()) {
                        SqFloatViewManager.getInstance().showFloatView(this.val$activity);
                        return;
                    }
                    return;
                }
                final RedPacketInfo redPacketInfo = new RedPacketInfo();
                String strOptString = jSONObject.optString("icon_url");
                final String strOptString2 = jSONObject.optString("act_url");
                int iOptInt = jSONObject.optInt("pop_width");
                int iOptInt2 = jSONObject.optInt("pop_height");
                String strOptString3 = jSONObject.optString("pop_url");
                final RedPacketInfo.WebViewConfig webViewConfig = new RedPacketInfo.WebViewConfig();
                webViewConfig.width = iOptInt;
                webViewConfig.height = iOptInt2;
                webViewConfig.pop_url = strOptString3;
                redPacketInfo.webViewConfig = webViewConfig;
                redPacketInfo.imgUrl = strOptString;
                LogUtil.i(RedPacketManager.this.TAG, "redPacketInfo:" + redPacketInfo);
                RedPacketManager.this.downloadFloatViewGifImg(this.val$activity, redPacketInfo, new DownloadGifCallback() { // from class: com.sy37sdk.account.floatview.redpacket.RedPacketManager.2.1
                    @Override // com.sy37sdk.account.floatview.redpacket.RedPacketManager.DownloadGifCallback
                    public void getLocalGifPath(String str, boolean z) {
                        LogUtil.d(RedPacketManager.this.TAG, "getLocalGifPath localPath : " + str + " isCache : " + z);
                        if (TextUtils.isEmpty(str)) {
                            return;
                        }
                        redPacketInfo.imgLocalPath = str;
                        redPacketInfo.jumpLink = RedPacketManager.this.getCouponUrl(AnonymousClass2.this.val$activity, strOptString2);
                        LogUtil.i(RedPacketManager.this.TAG, "redPacketInfo:" + redPacketInfo);
                        AnonymousClass2.this.val$activity.runOnUiThread(new Runnable() { // from class: com.sy37sdk.account.floatview.redpacket.RedPacketManager.2.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                boolean zCheckValidatePopImg = !TextUtils.isEmpty(webViewConfig.pop_url) ? RedPacketManager.this.checkValidatePopImg(webViewConfig.pop_url) : false;
                                if (RedPacketManager.this.hasShownRedpacketPop(AnonymousClass2.this.val$activity) || !zCheckValidatePopImg) {
                                    SqFloatViewManager.getInstance().showRedPacketFloat(AnonymousClass2.this.val$activity, redPacketInfo, true);
                                    return;
                                }
                                SqFloatViewManager.getInstance().showRedPacketFloat(AnonymousClass2.this.val$activity, redPacketInfo, false);
                                RedPacketManager.this.showRedPacketDialog(AnonymousClass2.this.val$activity, redPacketInfo);
                                RedPacketManager.this.setShownRedpacketPop(AnonymousClass2.this.val$activity, true);
                            }
                        });
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void getRedPacketFloatConfig(Activity activity, String str) {
        new AccountRequestManager(activity).getRedPacketSwitch(str, new AnonymousClass2(activity));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasShownRedpacketPop(Context context) {
        return SpUtils.get(context).getBoolean(SP_KEY_HASSHOWNREDPACKETPOP, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setShownRedpacketPop(Context context, boolean z) {
        SpUtils.get(context).put(SP_KEY_HASSHOWNREDPACKETPOP, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RedPacketDialog showRedPacketDialog(final Activity activity, final RedPacketInfo redPacketInfo) {
        final RedPacketDialog redPacketDialog = new RedPacketDialog(activity);
        redPacketDialog.initData(SqFloatViewManager.getInstance().floatView, redPacketInfo, new RedPacketDialog.RedPacketDialogCallback() { // from class: com.sy37sdk.account.floatview.redpacket.RedPacketManager.3
            @Override // com.sy37sdk.account.floatview.redpacket.RedPacketDialog.RedPacketDialogCallback
            public void onClose() {
                redPacketDialog.dismiss();
                SqFloatViewManager.getInstance().showRedPacktFloatView();
            }

            @Override // com.sy37sdk.account.floatview.redpacket.RedPacketDialog.RedPacketDialogCallback
            public void onOpenUrl() {
                redPacketDialog.dismiss();
                RedPacketManager.this.showTargetUrl(activity, redPacketInfo.jumpLink);
                SqFloatViewManager.getInstance().showRedPacktFloatView();
            }
        });
        redPacketDialog.show();
        return redPacketDialog;
    }

    public void downloadFloatViewGifImg(final Context context, RedPacketInfo redPacketInfo, final DownloadGifCallback downloadGifCallback) {
        String absolutePath = context.getExternalCacheDir().getAbsolutePath();
        String str = redPacketInfo.imgUrl;
        if (!TextUtils.isEmpty(str)) {
            String strEncode = MD5Util.encode(str);
            if (SpUtils.get(context).getString("localFloatGifNameKey", "").equals(strEncode)) {
                File file = new File(absolutePath, strEncode);
                if (file.exists() && file.length() > 0 && downloadGifCallback != null) {
                    downloadGifCallback.getLocalGifPath(file.getAbsolutePath(), true);
                    return;
                }
            }
            new DownloadTask(str, strEncode, absolutePath, new DownloadListener() { // from class: com.sy37sdk.account.floatview.redpacket.RedPacketManager.4
                @Override // com.sq.sdk.tool.download.DownloadListener
                public void onUpdate(long j, long j2) {
                }

                @Override // com.sq.sdk.tool.download.DownloadListener
                public void onSuccess(File file2) {
                    String absolutePath2 = file2.getAbsolutePath();
                    LogUtil.i(RedPacketManager.this.TAG, "onSuccess localFloatGifPath : " + absolutePath2);
                    SpUtils.get(context).put("localFloatGifNameKey", file2.getName());
                    DownloadGifCallback downloadGifCallback2 = downloadGifCallback;
                    if (downloadGifCallback2 != null) {
                        downloadGifCallback2.getLocalGifPath(absolutePath2, false);
                    }
                }

                @Override // com.sq.sdk.tool.download.DownloadListener
                public void onFailure(Throwable th, int i, String str2) {
                    LogUtil.e(RedPacketManager.this.TAG, "onFailure : " + str2);
                    downloadGifCallback.getLocalGifPath(null, false);
                }
            }).execute(new String[0]);
            return;
        }
        downloadGifCallback.getLocalGifPath(null, false);
    }

    public void showTargetUrl(Context context, RedPacketInfo redPacketInfo) {
        showTargetUrl(context, redPacketInfo.jumpLink);
    }

    public void showTargetUrl(Context context, String str) {
        LogUtil.i("showTargetUrl");
        SQWebViewDialog sQWebViewDialog = new SQWebViewDialog(context);
        sQWebViewDialog.setUrl(str);
        sQWebViewDialog.setAllowJumpURL(false);
        sQWebViewDialog.setCancelable(true);
        sQWebViewDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkValidatePopImg(String str) {
        try {
            return !TextUtils.isEmpty(UrlUtils.parse(str).params.get(popImgUrlKey));
        } catch (Exception unused) {
            return false;
        }
    }
}
