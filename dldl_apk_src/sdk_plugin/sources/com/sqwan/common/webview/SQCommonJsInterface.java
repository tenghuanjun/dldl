package com.sqwan.common.webview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.ImageView;
import com.parameters.bean.MiniProgramBean;
import com.parameters.share.ShareImageInfo;
import com.parameters.share.ShareMessage;
import com.parameters.share.ShareTextInfo;
import com.parameters.share.ShareWebInfo;
import com.sq.diagnostic.assistant.DiagnosticAssistant;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.base.ActivityResultListener;
import com.sqwan.base.EventDispatcher;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.dialog.MessageDialog;
import com.sqwan.common.eventbus.OnActivityResultEvent;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.account.IAccountMod;
import com.sqwan.common.mod.account.IBindWxListener;
import com.sqwan.common.mod.comment.ICommentMod;
import com.sqwan.common.mod.download.IDownloadMod;
import com.sqwan.common.mod.share.IShareMod;
import com.sqwan.common.mod.share.IShareResultListener;
import com.sqwan.common.mvp.BaseDialog;
import com.sqwan.common.net.risk.RiskWebActivity;
import com.sqwan.common.net.sq.CommonUrlConstant;
import com.sqwan.common.request.CommonParamsV3;
import com.sqwan.common.route.FunctionRouter;
import com.sqwan.common.route.FunctionRouterManager;
import com.sqwan.common.track.SqTrackUtil;
import com.sqwan.common.util.AppInstallUtil;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.AsyncImageLoader;
import com.sqwan.common.util.ImageUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.PermissionHelper;
import com.sqwan.common.util.PermissionSimpleHelper;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.util.ViewUtils;
import com.sqwan.common.web.SY37BehindWebPage;
import com.sqwan.common.web.SY37LandscapeWebPage;
import com.sqwan.common.web.SY37PortraitWebPage;
import com.sqwan.common.web.SY37web;
import com.sqwan.common.web.WebShareUtil;
import com.sqwan.msdk.api.SQResultListener;
import com.sy37sdk.share.UrlConstant;
import com.taptap.sdk.db.biz.iap.lib2plus.BillingClientConstants;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQCommonJsInterface {
    public static final int FEATURE_STATUS_NO = 0;
    public static final int FEATURE_STATUS_OK = 1;
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    public static final int INSTALL_STATUS_NO = 0;
    public static final int INSTALL_STATUS_OK = 1;
    public static final String INTERFACE_NAME = "fee";
    public static final int JS_SHARE_TYPE_H5 = 2;
    public static final int JS_SHARE_TYPE_IMG = 1;
    public static final int JS_SHARE_TYPE_TEXT = 3;
    public static final int ORIENTATION_LANDSCAPE = 2;
    public static final int ORIENTATION_PORTRAIT = 1;
    private static final String TAG = "SQCommonJsInterface";
    private final WebView mWebView;

    @JavascriptInterface
    public int checkFeatureStatus(String str) {
        return 0;
    }

    @JavascriptInterface
    public void enClose() {
    }

    public SQCommonJsInterface(SQWebView sQWebView) {
        this.mWebView = sQWebView;
    }

    public WebView getWebView() {
        return this.mWebView;
    }

    public Context getContext() {
        return this.mWebView.getContext();
    }

    public Activity getActivity() {
        return ViewUtils.getActivity(this.mWebView);
    }

    public final void post(Runnable runnable) {
        HANDLER.post(runnable);
    }

    public void log(String str, String str2) {
        LogUtil.i("SQCommonJsInterface " + str + " " + str2);
    }

    @JavascriptInterface
    public void enToast(final String str) {
        log("enToast", "message = " + str);
        post(new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.1
            @Override // java.lang.Runnable
            public void run() {
                ToastUtil.showToast(SQCommonJsInterface.this.getContext(), str);
            }
        });
    }

    @JavascriptInterface
    public void showMultipleUrl(final int i, final String str, final String str2, final String str3) {
        enClose();
        post(new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.2
            @Override // java.lang.Runnable
            public void run() {
                ((IAccountMod) ModHelper.get(IAccountMod.class)).showFloatMenu();
                String str4 = str;
                String str5 = "backurl=" + str2;
                String str6 = str4.indexOf("?") > 0 ? str4 + "&" + str5 : str4 + "?" + str5;
                SQCommonJsInterface.this.log("showMultipleUrl", str6);
                if (!TextUtils.isEmpty(str3)) {
                    for (String str7 : str3.split(",")) {
                        ((IAccountMod) ModHelper.get(IAccountMod.class)).redDotCalled(str7);
                    }
                }
                if (i == 1) {
                    SQCommonJsInterface.this.showWebViewDialog(str6, true);
                } else {
                    AppUtils.toSQWebUrl(SQCommonJsInterface.this.getContext(), str6, "");
                }
            }
        });
    }

    @JavascriptInterface
    public void enAlert(final String str) {
        log("enAlert", "tips = " + str);
        post(new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.3
            @Override // java.lang.Runnable
            public void run() {
                ToastUtil.showToast(SQCommonJsInterface.this.getContext(), str);
            }
        });
    }

    @JavascriptInterface
    public void sqOpenUrl(final String str) {
        log("sqOpenUrl", "url = " + str);
        post(new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    AppUtils.toSdkUrl(SQCommonJsInterface.this.getContext(), str);
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtil.showToast(SQCommonJsInterface.this.getContext(), "sqOpenUrl 跳转失败：" + str);
                }
            }
        });
    }

    @JavascriptInterface
    public void share(String str) {
        log(UrlConstant.KEY_SHARE, "json = " + str);
        shareByBitmap(str, (Bitmap) null);
    }

    @JavascriptInterface
    public void share(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("json = ");
        sb.append(str);
        sb.append(", base64 = ");
        sb.append(str2 != null);
        log(UrlConstant.KEY_SHARE, sb.toString());
        shareByBitmap(str, base64ToBitmap(str2));
    }

    /* JADX INFO: renamed from: com.sqwan.common.webview.SQCommonJsInterface$5, reason: invalid class name */
    class AnonymousClass5 implements Runnable {
        final /* synthetic */ Bitmap val$bitmap;
        final /* synthetic */ String val$json;

        AnonymousClass5(String str, Bitmap bitmap) {
            this.val$json = str;
            this.val$bitmap = bitmap;
        }

        @Override // java.lang.Runnable
        public void run() {
            WebShareUtil.parseJsShare(SQCommonJsInterface.this.getContext(), this.val$json, this.val$bitmap, new IShareResultListener() { // from class: com.sqwan.common.webview.SQCommonJsInterface.5.1
                @Override // com.sqwan.common.mod.share.IShareResultListener
                public void onSuccess(Bundle bundle) {
                    SQCommonJsInterface.this.log(UrlConstant.KEY_SHARE, "分享成功");
                    SQCommonJsInterface.this.post(new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.5.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SQCommonJsInterface.this.mWebView.loadUrl("javascript:window.fee.shareSuccessCallback('" + new JSONObject() + "')");
                        }
                    });
                }

                @Override // com.sqwan.common.mod.share.IShareResultListener
                public void onFailture(int i, String str) {
                    SQCommonJsInterface.this.log(UrlConstant.KEY_SHARE, "分享失败 code = " + i + ", msg = " + str);
                    final HashMap map = new HashMap();
                    map.put("code", Integer.valueOf(i));
                    map.put("msg", str);
                    SQCommonJsInterface.this.post(new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.5.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            SQCommonJsInterface.this.mWebView.loadUrl("javascript:window.fee.shareFailureCallback('" + new JSONObject(map) + "')");
                        }
                    });
                }
            });
        }
    }

    private void shareByBitmap(String str, Bitmap bitmap) {
        post(new AnonymousClass5(str, bitmap));
    }

    @JavascriptInterface
    public void shareToSystem(String str) {
        log("shareToSystem", "json = " + str);
        shareToSystemByBitmap(str, (Bitmap) null);
    }

    @JavascriptInterface
    public void shareToSystem(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("json = ");
        sb.append(str);
        sb.append(", base64 = ");
        sb.append(str2 != null);
        log("shareToSystem", sb.toString());
        shareToSystemByBitmap(str, base64ToBitmap(str2));
    }

    private void shareToSystemByBitmap(final String str, final Bitmap bitmap) {
        post(new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    int iOptInt = jSONObject.optInt("shareType");
                    String strOptString = jSONObject.optString("shareTitle");
                    String strOptString2 = jSONObject.optString("shareLinkUrl");
                    String strOptString3 = jSONObject.optString("shareImageUrl");
                    jSONObject.optString("shareText");
                    if (iOptInt == 1) {
                        Bitmap bitmap2 = bitmap;
                        if (bitmap2 != null) {
                            SQCommonJsInterface.this.shareToSystemByBitmap(bitmap2);
                            return;
                        } else {
                            new AsyncImageLoader(SQCommonJsInterface.this.getContext()).loadDrawable(strOptString3, null, new AsyncImageLoader.ImageCallback() { // from class: com.sqwan.common.webview.SQCommonJsInterface.6.1
                                @Override // com.sqwan.common.util.AsyncImageLoader.ImageCallback
                                public void imageLoaded(Bitmap bitmap3, ImageView imageView, String str2) {
                                    SQCommonJsInterface.this.shareToSystemByBitmap(bitmap3);
                                }
                            });
                            return;
                        }
                    }
                    if (iOptInt == 2) {
                        ShareMessage shareMessage = new ShareMessage();
                        shareMessage.setSkipPreview(true);
                        ShareWebInfo shareWebInfo = new ShareWebInfo();
                        shareWebInfo.setTitle(strOptString);
                        shareWebInfo.setPageUrl(strOptString2);
                        shareMessage.setShareMessage(shareWebInfo);
                        SQCommonJsInterface.this.startSystemShareActivity(shareMessage);
                        return;
                    }
                    if (iOptInt == 3) {
                        ShareMessage shareMessage2 = new ShareMessage();
                        shareMessage2.setSkipPreview(true);
                        ShareTextInfo shareTextInfo = new ShareTextInfo();
                        shareTextInfo.setText(jSONObject.optString("shareText"));
                        shareMessage2.setShareMessage(shareTextInfo);
                        SQCommonJsInterface.this.startSystemShareActivity(shareMessage2);
                        return;
                    }
                    ToastUtil.showToast(SQCommonJsInterface.this.getContext(), "调起分享失败，不支持的分享类型");
                } catch (JSONException e) {
                    e.printStackTrace();
                    ToastUtil.showToast(SQCommonJsInterface.this.getContext(), "数据解析失败，无法调起分享");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shareToSystemByBitmap(Bitmap bitmap) {
        ShareMessage shareMessage = new ShareMessage();
        shareMessage.setSkipPreview(true);
        ShareImageInfo shareImageInfo = new ShareImageInfo();
        shareImageInfo.setBitmap(bitmap);
        shareMessage.setShareMessage(shareImageInfo);
        startSystemShareActivity(shareMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSystemShareActivity(final ShareMessage shareMessage) {
        if (shareMessage == null) {
            ToastUtil.showToast(getContext(), "分享失败，要分享的内容为空");
            return;
        }
        shareMessage.setPlatform(4);
        final Runnable runnable = new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.7
            @Override // java.lang.Runnable
            public void run() {
                ((IShareMod) ModHelper.get(IShareMod.class)).share(shareMessage, new IShareResultListener() { // from class: com.sqwan.common.webview.SQCommonJsInterface.7.1
                    @Override // com.sqwan.common.mod.share.IShareResultListener
                    public void onSuccess(Bundle bundle) {
                    }

                    @Override // com.sqwan.common.mod.share.IShareResultListener
                    public void onFailture(int i, String str) {
                        ToastUtil.showToast(SQCommonJsInterface.this.getContext(), str);
                    }
                });
            }
        };
        if (!(shareMessage.getShareMessage() instanceof ShareImageInfo)) {
            runnable.run();
        } else {
            PermissionSimpleHelper.requestPermission(PermissionSimpleHelper.STORAGE_PERMISSION, PermissionSimpleHelper.STORAGE_PERMISSION_NAME, PermissionSimpleHelper.STORAGE_PERMISSION_BY_SHARE, new PermissionSimpleHelper.OnPermissionCallback() { // from class: com.sqwan.common.webview.SQCommonJsInterface.8
                @Override // com.sqwan.common.util.PermissionSimpleHelper.OnPermissionCallback
                public void onGranted() {
                    runnable.run();
                }

                @Override // com.sqwan.common.util.PermissionSimpleHelper.OnPermissionCallback
                public void onDenied() {
                    ToastUtil.showToast(SQCommonJsInterface.this.getContext(), "分享图片失败，没有存储权限");
                }
            });
        }
    }

    @JavascriptInterface
    public void saveImageByBase64(final String str) {
        PermissionSimpleHelper.requestPermission(PermissionSimpleHelper.STORAGE_PERMISSION, PermissionSimpleHelper.STORAGE_PERMISSION_NAME, PermissionSimpleHelper.STORAGE_PERMISSION_BY_WEB, new PermissionSimpleHelper.OnPermissionCallback() { // from class: com.sqwan.common.webview.SQCommonJsInterface.9
            @Override // com.sqwan.common.util.PermissionSimpleHelper.OnPermissionCallback
            public void onGranted() {
                Bitmap bitmapBase64ToBitmap = SQCommonJsInterface.base64ToBitmap(str);
                if (ImageUtils.save(SQCommonJsInterface.this.getContext(), bitmapBase64ToBitmap, String.format("share_image_%s", Long.valueOf(System.currentTimeMillis())) + ".png") == null) {
                    callbackFailture(102, "保存图片失败");
                } else {
                    callbackSuccess();
                }
            }

            @Override // com.sqwan.common.util.PermissionSimpleHelper.OnPermissionCallback
            public void onDenied() {
                callbackFailture(102, "权限申请失败，无法保存图片");
            }

            private void callbackSuccess() {
                SQCommonJsInterface.this.post(new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.9.1
                    @Override // java.lang.Runnable
                    public void run() {
                        SQCommonJsInterface.this.mWebView.loadUrl("javascript:window.fee.saveImageByBase64SuccessCallback('" + new JSONObject() + "')");
                    }
                });
            }

            private void callbackFailture(final int i, final String str2) {
                SQCommonJsInterface.this.post(new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.9.2
                    @Override // java.lang.Runnable
                    public void run() {
                        HashMap map = new HashMap();
                        map.put("code", Integer.valueOf(i));
                        map.put("msg", str2);
                        SQCommonJsInterface.this.mWebView.loadUrl("javascript:window.fee.saveImageByBase64FailtureCallback('" + new JSONObject(map) + "')");
                    }
                });
            }
        });
    }

    @JavascriptInterface
    public void saveImageByUrl(final String str) {
        PermissionSimpleHelper.requestPermission(PermissionSimpleHelper.STORAGE_PERMISSION, PermissionSimpleHelper.STORAGE_PERMISSION_NAME, PermissionSimpleHelper.STORAGE_PERMISSION_BY_WEB, new PermissionSimpleHelper.OnPermissionCallback() { // from class: com.sqwan.common.webview.SQCommonJsInterface.10
            @Override // com.sqwan.common.util.PermissionSimpleHelper.OnPermissionCallback
            public void onGranted() {
                new AsyncImageLoader(SQCommonJsInterface.this.getContext()).loadDrawable(str, null, new AsyncImageLoader.ImageCallback() { // from class: com.sqwan.common.webview.SQCommonJsInterface.10.1
                    @Override // com.sqwan.common.util.AsyncImageLoader.ImageCallback
                    public void imageLoaded(Bitmap bitmap, ImageView imageView, String str2) {
                        if (ImageUtils.save(SQCommonJsInterface.this.getContext(), bitmap, String.format("share_image_%s", Long.valueOf(System.currentTimeMillis())) + ".png") == null) {
                            callbackFailture(101, "保存图片失败");
                        } else {
                            callbackSuccess();
                        }
                    }
                });
            }

            @Override // com.sqwan.common.util.PermissionSimpleHelper.OnPermissionCallback
            public void onDenied() {
                callbackFailture(102, "权限申请失败，无法保存图片");
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void callbackSuccess() {
                SQCommonJsInterface.this.post(new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.10.2
                    @Override // java.lang.Runnable
                    public void run() {
                        SQCommonJsInterface.this.mWebView.loadUrl("javascript:window.fee.saveImageByUrlSuccessCallback('" + new JSONObject() + "')");
                    }
                });
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void callbackFailture(final int i, final String str2) {
                SQCommonJsInterface.this.post(new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.10.3
                    @Override // java.lang.Runnable
                    public void run() {
                        HashMap map = new HashMap();
                        map.put("code", Integer.valueOf(i));
                        map.put("msg", str2);
                        SQCommonJsInterface.this.mWebView.loadUrl("javascript:window.fee.saveImageByUrlFailtureCallback('" + new JSONObject(map) + "')");
                    }
                });
            }
        });
    }

    @JavascriptInterface
    public void enLogin() {
        log("enLogin", "");
        post(new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.11
            @Override // java.lang.Runnable
            public void run() {
                ((IAccountMod) ModHelper.get(IAccountMod.class)).webEnLogin(false);
            }
        });
    }

    @JavascriptInterface
    public void modifyPass() {
        log("modifyPass", "");
        post(new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.12
            @Override // java.lang.Runnable
            public void run() {
                ((IAccountMod) ModHelper.get(IAccountMod.class)).modifyPassword();
                SQCommonJsInterface.this.enLogin();
            }
        });
    }

    @JavascriptInterface
    public void enPay() {
        log("enPay", "");
        post(new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.13
            @Override // java.lang.Runnable
            public void run() {
                if (TextUtils.isEmpty(((IAccountMod) ModHelper.get(IAccountMod.class)).getToken())) {
                    LogUtil.w("SQCommonJsInterface enPay 登录状态已过期");
                    ToastUtil.showToast(SQCommonJsInterface.this.getContext(), "您的登录状态已过期，请重新登录【20001】");
                } else {
                    BuglessAction.reportCatchException(new Exception("旧的enPay"), "旧的enPay", 999);
                }
            }
        });
    }

    @JavascriptInterface
    /* JADX INFO: renamed from: enUniversalAliPay, reason: merged with bridge method [inline-methods] */
    public void lambda$enUniversalAliPay$0$SQCommonJsInterface(final String str) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            post(new Runnable() { // from class: com.sqwan.common.webview.-$$Lambda$SQCommonJsInterface$6wV6nZ2icQOW9rX6n09Gr55eMi4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$enUniversalAliPay$0$SQCommonJsInterface(str);
                }
            });
            return;
        }
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(FunctionRouter.KEY_DATA, str);
        FunctionRouterManager.getInstance().lambda$call$0$FunctionRouterManager(activity, FunctionRouter.Func.FUNC_UNIVERSAL_ALI_PAY, bundle);
    }

    @JavascriptInterface
    public void packageName() {
        log(BillingClientConstants.PACKAGE_NAME, "");
        post(new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.14
            @Override // java.lang.Runnable
            public void run() {
                SQCommonJsInterface.this.mWebView.loadUrl("javascript:window.packageName('" + SQCommonJsInterface.this.getContext().getPackageName() + "')");
            }
        });
    }

    @JavascriptInterface
    public void jumpWechat() {
        log("jumpWechat", "");
        try {
            if (!SqTrackUtil.checkAppInstalled(getContext(), "com.tencent.mm")) {
                post(new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.15
                    @Override // java.lang.Runnable
                    public void run() {
                        ToastUtil.showToast(SQCommonJsInterface.this.getContext(), "请安装微信后重试");
                    }
                });
                return;
            }
            Intent launchIntentForPackage = getContext().getPackageManager().getLaunchIntentForPackage("com.tencent.mm");
            if (launchIntentForPackage == null) {
                LogUtil.w("SQCommonJsInterface jumpWechat 无法找到微信");
                ToastUtil.showToast(getContext(), "打开微信失败，无法找到微信");
            } else {
                launchIntentForPackage.addFlags(268435456);
                getContext().startActivity(launchIntentForPackage);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("SQCommonJsInterface jumpWechat 打开微信失败 " + e.getMessage());
            ToastUtil.showToast(getContext(), "打开微信失败");
        }
    }

    /* JADX INFO: renamed from: com.sqwan.common.webview.SQCommonJsInterface$16, reason: invalid class name */
    class AnonymousClass16 implements Runnable {
        AnonymousClass16() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ((IAccountMod) ModHelper.get(IAccountMod.class)).wxBind(new IBindWxListener() { // from class: com.sqwan.common.webview.SQCommonJsInterface.16.1
                @Override // com.sqwan.common.mod.account.IBindWxListener
                public void onSuccess(String str) {
                    final String str2 = "javascript:window.bindWXCallback('" + str + "')";
                    SQCommonJsInterface.this.log("bindWX", "绑定成功 --> " + str2);
                    SQCommonJsInterface.this.post(new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.16.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SQCommonJsInterface.this.mWebView.loadUrl(str2);
                        }
                    });
                }

                @Override // com.sqwan.common.mod.account.IBindWxListener
                public void onFailure(int i, String str) {
                    SQCommonJsInterface.this.log("bindWX", "绑定失败 code = " + i + ", msg = " + str);
                    final HashMap map = new HashMap();
                    map.put("code", Integer.valueOf(i));
                    map.put("msg", str);
                    SQCommonJsInterface.this.post(new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.16.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            SQCommonJsInterface.this.mWebView.loadUrl("javascript:window.fee.bindWXFailureCallback('" + new JSONObject(map) + "')");
                            SQCommonJsInterface.this.mWebView.loadUrl("javascript:window.bindWXCallback('')");
                        }
                    });
                }
            });
        }
    }

    @JavascriptInterface
    public void bindWX() {
        log("bindWX", "");
        post(new AnonymousClass16());
    }

    @JavascriptInterface
    public void jumpUrl(final String str) {
        log("jumpUrl", "url = " + str);
        post(new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.17
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setFlags(268435456);
                    intent.setData(Uri.parse(str));
                    SQCommonJsInterface.this.getContext().startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtil.showToast(SQCommonJsInterface.this.getContext(), " jumpUrl 跳转失败：" + str);
                }
            }
        });
    }

    @JavascriptInterface
    public void openfull(final String str, int i) {
        log("openfull", "url = " + str + ", orientation" + i);
        post(new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.18
            @Override // java.lang.Runnable
            public void run() {
                try {
                    AppUtils.toSQWebUrl(SQCommonJsInterface.this.getContext(), str, "");
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtil.showToast(SQCommonJsInterface.this.getContext(), " openfull 跳转失败：" + str);
                }
            }
        });
    }

    @JavascriptInterface
    public void openWebView(final String str) {
        log("openWebView", "json = " + str);
        post(new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.19
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Context context = SQCommonJsInterface.this.getContext();
                    if (context == null) {
                        ToastUtil.showToast(SQCommonJsInterface.this.getContext(), "WebView 上下文为空，无法进行下一步");
                        return;
                    }
                    JSONObject jSONObject = new JSONObject(str);
                    String strOptString = jSONObject.optString("url");
                    if (TextUtils.isEmpty(strOptString)) {
                        ToastUtil.showToast(context, "openWebView 链接为空，无法跳转");
                        return;
                    }
                    int iOptInt = jSONObject.optInt("orientation", 0);
                    boolean zOptBoolean = jSONObject.optBoolean("transparent", false);
                    boolean zIsSupportPlugin = SQCommonJsInterface.isSupportPlugin();
                    if (iOptInt == 0 && zOptBoolean) {
                        SQCommonJsInterface.this.showWebViewDialog(strOptString, false);
                        return;
                    }
                    Intent intent = new Intent();
                    intent.setFlags(268435456);
                    intent.putExtra("url", AppUtils.constructWebUrlParam(context, strOptString));
                    if (iOptInt == 1) {
                        intent.setClass(context, zIsSupportPlugin ? SY37web.class : SY37PortraitWebPage.class);
                        intent.putExtra(RiskWebActivity.INTENT_KEY_IN_SCREEN_ORIENTATION, "portrait");
                    } else if (iOptInt == 2) {
                        intent.setClass(context, zIsSupportPlugin ? SY37web.class : SY37LandscapeWebPage.class);
                        intent.putExtra(RiskWebActivity.INTENT_KEY_IN_SCREEN_ORIENTATION, "landscape");
                    } else {
                        intent.setClass(context, zIsSupportPlugin ? SY37web.class : SY37BehindWebPage.class);
                        intent.putExtra(RiskWebActivity.INTENT_KEY_IN_SCREEN_ORIENTATION, "behind");
                    }
                    context.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtil.showToast(SQCommonJsInterface.this.getContext(), " openWebView 跳转失败：" + e.getMessage());
                    LogUtil.e(SQCommonJsInterface.TAG, "openWebView 跳转失败", e);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showWebViewDialog(String str, boolean z) {
        Activity activity = getActivity();
        if (activity == null) {
            ToastUtil.showToast(getContext(), "WebView 没有绑定在 Activity 上面，无法打开对话框");
            return;
        }
        SQWebViewDialog sQWebViewDialog = new SQWebViewDialog(activity);
        sQWebViewDialog.setUrl(AppUtils.constructWebUrlParam(getContext(), str));
        if (z) {
            sQWebViewDialog.setPortraitHeightWeight(60);
        }
        sQWebViewDialog.show();
    }

    @JavascriptInterface
    public int getWindowOrientation() {
        log("getWindowOrientation", "");
        Activity activity = getActivity();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = activity != null ? activity.getWindowManager() : null;
        Display defaultDisplay = windowManager != null ? windowManager.getDefaultDisplay() : null;
        if (defaultDisplay != null) {
            defaultDisplay.getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            if (i2 > i) {
                return 1;
            }
            if (i > i2) {
                return 2;
            }
        }
        int i3 = getContext().getResources().getConfiguration().orientation;
        if (i3 != 1) {
            return i3 != 2 ? 0 : 2;
        }
        return 1;
    }

    @JavascriptInterface
    public void jumpToMiniProgram(String str) {
        log("jumpToMiniProgram", "json = " + str);
        try {
            SqRequest.of(CommonUrlConstant.SKIP_APPLET_INFO_URL).signV3().addParam("token", ((IAccountMod) ModHelper.get(IAccountMod.class)).getToken()).addParam("flag_id", new JSONObject(str).optString("flag")).addParam(SqConstants.DSID, SqTrackUtil.getServerid(getContext())).addParam(SqConstants.DSNAME, SqTrackUtil.getServerName(getContext())).addParam(SqConstants.DRID, SqTrackUtil.getRoleid(getContext())).addParam(SqConstants.DRNAME, SqTrackUtil.getRolename(getContext())).addParam(SqConstants.DRLEVEL, SqTrackUtil.getRolelevel(getContext())).addParamsTransformer(new CommonParamsV3()).get(new SqHttpCallback<String>() { // from class: com.sqwan.common.webview.SQCommonJsInterface.20
                @Override // com.sdk.sq.net.SqRequestCallback
                public void onResponseStateError(int i, int i2, String str2, String str3) {
                    ToastUtil.showToast(SQCommonJsInterface.this.getContext(), "无法跳转到微信小程序：" + str2);
                }

                @Override // com.sq.tool.network.SqHttpCallback
                public void onSuccess(String str2) {
                    if (!SqTrackUtil.checkAppInstalled(SQCommonJsInterface.this.getContext(), "com.tencent.mm")) {
                        ToastUtil.showToast(SQCommonJsInterface.this.getContext(), "检测到手机没有安装微信，请安装微信后重试");
                        return;
                    }
                    MiniProgramBean toObject = MiniProgramBean.parseToObject(str2);
                    int i = toObject.skipType;
                    if (i == 1) {
                        IWXAPI iwxapiCreateWXAPI = WXAPIFactory.createWXAPI(SQCommonJsInterface.this.getContext(), toObject.appId);
                        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
                        req.userName = toObject.miniProgramId;
                        req.path = toObject.miniProgramPath;
                        req.miniprogramType = 0;
                        iwxapiCreateWXAPI.sendReq(req);
                        return;
                    }
                    if (i == 2) {
                        try {
                            Intent intent = new Intent("android.intent.action.VIEW");
                            intent.addFlags(268435456);
                            intent.setData(Uri.parse(toObject.schemeUrl));
                            SQCommonJsInterface.this.getContext().startActivity(intent);
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            ToastUtil.showToast(SQCommonJsInterface.this.getContext(), "微信小程序跳转失败：" + toObject.schemeUrl);
                            return;
                        }
                    }
                    ToastUtil.showToast(SQCommonJsInterface.this.getContext(), "不支持该类型跳转到微信小程序：" + toObject.skipType);
                }

                @Override // com.sq.tool.network.SqHttpCallback
                public void onFailure(int i, String str2, VolleyError volleyError) {
                    ToastUtil.showToast(SQCommonJsInterface.this.getContext(), str2);
                }
            }, String.class);
        } catch (JSONException e) {
            e.printStackTrace();
            LogUtil.e("jumpToMiniProgram error");
        }
    }

    @JavascriptInterface
    public void openDiagnosticAssistant(String str) {
        log("openDiagnosticAssistant", "json = " + str);
        post(new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.21
            @Override // java.lang.Runnable
            public void run() {
                DiagnosticAssistant.show(SQCommonJsInterface.this.getContext());
            }
        });
    }

    @JavascriptInterface
    public void requestPermissions(final String str) {
        log("requestPermissions", "json = " + str);
        post(new Runnable() { // from class: com.sqwan.common.webview.-$$Lambda$SQCommonJsInterface$xEG72aMT0fat2A_xBRlCC4_OmkA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$requestPermissions$1$SQCommonJsInterface(str);
            }
        });
    }

    public /* synthetic */ void lambda$requestPermissions$1$SQCommonJsInterface(String str) {
        JSONArray jSONArrayOptJSONArray;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        boolean z = false;
        try {
            jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("requestPermissionList");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() != 0) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObject = jSONArrayOptJSONArray.getJSONObject(i);
                String strOptString = jSONObject.optString("permission");
                String strOptString2 = jSONObject.optString("permissionName");
                String strOptString3 = jSONObject.optString("permissionExplain");
                if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2) && !TextUtils.isEmpty(strOptString3)) {
                    arrayList.add(strOptString);
                    arrayList2.add(strOptString2);
                    arrayList3.add(strOptString3);
                }
            }
            if (arrayList.isEmpty()) {
                callRequestPermissionsFail();
                return;
            }
            Iterator<String> it = arrayList.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (ContextCompat.checkSelfPermission(this.mWebView.getContext(), it.next()) == -1) {
                        break;
                    }
                } else {
                    z = true;
                    break;
                }
            }
            if (z) {
                callRequestPermissionsSuccess();
                return;
            } else {
                startRequestPermissions(arrayList, arrayList2, arrayList3);
                return;
            }
        }
        callRequestPermissionsFail();
    }

    @JavascriptInterface
    public String getSocialSoftwareKeys() {
        String metaData = getMetaData("wx_appid");
        String metaData2 = getMetaData("wx_appkey");
        String metaData3 = getMetaData("qq_appid");
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(metaData)) {
            map.put("wxAppId", metaData);
        }
        if (!TextUtils.isEmpty(metaData2)) {
            map.put("wxAppKey", metaData2);
        }
        if (!TextUtils.isEmpty(metaData3)) {
            map.put("qqAppId", metaData3);
        }
        return new JSONObject(map).toString();
    }

    @JavascriptInterface
    public int queryAppInstallStateByPackageName(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.d("是否安装了" + str + "结果0");
            return 0;
        }
        boolean zCheckAppInstalled = checkAppInstalled(getContext(), str);
        LogUtil.d("是否安装了" + str + "结果" + (zCheckAppInstalled ? 1 : 0));
        return zCheckAppInstalled ? 1 : 0;
    }

    @JavascriptInterface
    public void installApp(String str) {
        LogUtil.d("webview调用原生installApp:" + str);
        try {
            ((IDownloadMod) ModHelper.get(IDownloadMod.class)).installApk(str, new SQResultListener() { // from class: com.sqwan.common.webview.SQCommonJsInterface.22
                public void onSuccess(Bundle bundle) {
                    LogUtil.i("原生回调结果给H5");
                    final HashMap map = new HashMap();
                    map.put("code", 0);
                    map.put("msg", "成功");
                    SQCommonJsInterface.this.post(new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.22.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SQCommonJsInterface.this.mWebView.loadUrl("javascript:window.fee.installAppCallback('" + new JSONObject(map) + "')");
                        }
                    });
                }

                public void onFailture(int i, String str2) {
                    final HashMap map = new HashMap();
                    map.put("code", Integer.valueOf(i));
                    map.put("msg", str2);
                    SQCommonJsInterface.this.post(new Runnable() { // from class: com.sqwan.common.webview.SQCommonJsInterface.22.2
                        @Override // java.lang.Runnable
                        public void run() {
                            SQCommonJsInterface.this.mWebView.loadUrl("javascript:window.fee.installAppCallback('" + new JSONObject(map) + "')");
                        }
                    });
                }
            });
        } catch (Exception e) {
            LogUtil.e(e.toString());
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public void jumpComment() {
        enClose();
        ((ICommentMod) ModHelper.get(ICommentMod.class)).jumpComment();
    }

    @JavascriptInterface
    public void cancelJumpComment() {
        enClose();
        ((ICommentMod) ModHelper.get(ICommentMod.class)).cancelJumpComment();
    }

    @JavascriptInterface
    public void closeComment() {
        enClose();
        ((ICommentMod) ModHelper.get(ICommentMod.class)).closeComment();
    }

    public static boolean checkAppInstalled(Context context, String str) {
        return AppInstallUtil.getAppInstallResult(context, str);
    }

    private String getMetaData(String str) {
        try {
            return getContext().getPackageManager().getApplicationInfo(getContext().getPackageName(), 128).metaData.getString(str);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private void startRequestPermissions(List<String> list, final List<String> list2, List<String> list3) {
        final Activity activity = ViewUtils.getActivity(this.mWebView);
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            callRequestPermissionsFail();
        } else {
            PermissionHelper.getInstance().requestPermissions(activity, (String[]) list.toArray(new String[0]), (String[]) list3.toArray(new String[0]), 1110, new PermissionHelper.PermissionCallback() { // from class: com.sqwan.common.webview.-$$Lambda$SQCommonJsInterface$F7Q_O7EJd2GwcNdctd9YNBA0Lno
                @Override // com.sqwan.common.util.PermissionHelper.PermissionCallback
                public final void onRequestPermissionsResult(String[] strArr, int[] iArr) {
                    this.f$0.lambda$startRequestPermissions$2$SQCommonJsInterface(activity, list2, strArr, iArr);
                }
            });
        }
    }

    public /* synthetic */ void lambda$startRequestPermissions$2$SQCommonJsInterface(Activity activity, List list, String[] strArr, int[] iArr) {
        int length = iArr.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = true;
                break;
            } else if (iArr[i] != -1) {
                break;
            } else {
                i++;
            }
        }
        if (!z) {
            callRequestPermissionsSuccess();
        } else {
            showPermissionGuideDialog(activity, list);
        }
    }

    private void showPermissionGuideDialog(final Activity activity, List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            if (sb.length() == 0) {
                sb.append(str);
            } else {
                sb.append("、");
                sb.append(str);
            }
        }
        MessageDialog dialogListener = new MessageDialog(activity).setDialogTitle("授权提醒").setDialogMessage("获取权限失败，请手动授予" + ((Object) sb)).setDialogCancel("").setDialogListener(new MessageDialog.OnListener() { // from class: com.sqwan.common.webview.-$$Lambda$SQCommonJsInterface$WWOBIKFM7gISMyKcO25wgYef328
            @Override // com.sqwan.common.dialog.MessageDialog.OnListener
            public /* synthetic */ void onCancel(BaseDialog baseDialog) {
                MessageDialog.OnListener.CC.$default$onCancel(this, baseDialog);
            }

            @Override // com.sqwan.common.dialog.MessageDialog.OnListener
            public final void onConfirm(BaseDialog baseDialog) {
                this.f$0.lambda$showPermissionGuideDialog$3$SQCommonJsInterface(activity, baseDialog);
            }
        });
        dialogListener.setCancelable(false);
        dialogListener.show();
    }

    public /* synthetic */ void lambda$showPermissionGuideDialog$3$SQCommonJsInterface(final Activity activity, BaseDialog baseDialog) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivityForResult(intent, 1024);
        EventDispatcher.getInstance().addActivityResultListener(new ActivityResultListener() { // from class: com.sqwan.common.webview.SQCommonJsInterface.23
            @Override // com.sqwan.base.ActivityResultListener
            public void onResult(OnActivityResultEvent onActivityResultEvent) {
                EventDispatcher.getInstance().removeActivityResultListener(this);
                if (ContextCompat.checkSelfPermission(activity, "android.permission.CAMERA") == 0) {
                    SQCommonJsInterface.this.callRequestPermissionsSuccess();
                } else {
                    SQCommonJsInterface.this.callRequestPermissionsFail();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callRequestPermissionsSuccess() {
        this.mWebView.loadUrl("javascript:window.fee.requestPermissionsSuccess('')");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callRequestPermissionsFail() {
        this.mWebView.loadUrl("javascript:window.fee.requestPermissionsFail('')");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isSupportPlugin() {
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

    /* JADX INFO: Access modifiers changed from: private */
    public static Bitmap base64ToBitmap(String str) {
        if (str != null && str.length() != 0) {
            try {
                byte[] bArrDecode = Base64.decode(str.substring(str.indexOf(44) + 1), 0);
                return BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.e("将 Base64 转成图片失败", e);
            }
        }
        return null;
    }
}
