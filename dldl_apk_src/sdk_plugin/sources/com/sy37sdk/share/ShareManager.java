package com.sy37sdk.share;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.ImageView;
import com.social.sdk.SocialApi;
import com.social.sdk.common.listener.ShareListener;
import com.social.sdk.platform.PlatformType;
import com.sq.tool.network.SqHttpCallback;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.common.dialog.LoadingDialog;
import com.sqwan.common.mod.share.IShareResultListener;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.track.SqTrackUtil;
import com.sqwan.common.util.AsyncImageLoader;
import com.sqwan.common.util.ImageUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.util.ViewUtils;
import com.sy37sdk.order.third.union.UnionPayWay;
import com.sy37sdk.share.bean.ShareBean;
import com.sy37sdk.share.bean.ShareMediaParser;
import com.sy37sdk.share.dialog.ShareDialog;
import com.sy37sdk.share.track.ShareTrack;
import java.util.HashMap;
import java.util.UUID;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class ShareManager {
    private static final long DELAY_MILLIS = 10000;
    private static volatile ShareManager instance;
    private Context context;
    private boolean isBreak = false;
    private Activity mActivity;
    private ShareRequestManager requestManager;
    private LoadingDialog waitDialog;

    private ShareManager(Context context) {
        if (context instanceof Activity) {
            this.mActivity = (Activity) context;
        }
        this.context = context.getApplicationContext();
        this.requestManager = new ShareRequestManager();
    }

    public static ShareManager getInstance(Context context) {
        if (instance == null) {
            synchronized (AccountManager.class) {
                if (instance == null) {
                    instance = new ShareManager(context);
                }
            }
        }
        return instance;
    }

    private void showLoading() {
        if (this.waitDialog == null) {
            LoadingDialog loadingDialog = new LoadingDialog(this.mActivity);
            this.waitDialog = loadingDialog;
            loadingDialog.setCancelable(false);
        }
        this.waitDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideLoading() {
        LoadingDialog loadingDialog = this.waitDialog;
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    public void getShareSources(String str, String str2, final IShareResultListener iShareResultListener) {
        showLoading();
        this.isBreak = false;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() { // from class: com.sy37sdk.share.ShareManager.1
            @Override // java.lang.Runnable
            public void run() {
                ShareManager.this.isBreak = true;
                ViewUtils.showToast(ShareManager.this.context, "网络异常，请稍后重试");
                ShareManager.this.handleRequestErro("分享失败", iShareResultListener);
            }
        }, DELAY_MILLIS);
        this.requestManager.getShareSources(str, str2, new SqHttpCallback<String>() { // from class: com.sy37sdk.share.ShareManager.2
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str3, String str4) {
                if (ShareManager.this.isBreak) {
                    return;
                }
                HashMap map = new HashMap();
                map.put(SqTrackKey.reason_fail, str3);
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_fail, map);
                handler.removeCallbacksAndMessages(null);
                ViewUtils.showToast(ShareManager.this.context, "分享失败");
                LogUtil.e("请求分享物料失败,msg:  " + str3);
                ShareManager.this.handleRequestErro("分享失败:  " + str3, iShareResultListener);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(String str3) {
                if (ShareManager.this.isBreak) {
                    return;
                }
                ShareManager.this.handleRequestShareSuccess(str3, handler, iShareResultListener);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str3, VolleyError volleyError) {
                if (ShareManager.this.isBreak) {
                    return;
                }
                HashMap map = new HashMap();
                map.put(SqTrackKey.reason_fail, str3);
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_fail, map);
                handler.removeCallbacksAndMessages(null);
                ViewUtils.showToast(ShareManager.this.context, "网络异常，请稍后重试");
                ShareManager.this.handleRequestErro("分享失败:" + str3, iShareResultListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleRequestShareSuccess(String str, final Handler handler, final IShareResultListener iShareResultListener) {
        final ShareBean shareBeanHandleShareData = handleShareData(str);
        if (shareBeanHandleShareData.getShareWays() == null || shareBeanHandleShareData.getShareWays().isEmpty()) {
            if (this.isBreak) {
                return;
            }
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            hideLoading();
            LogUtil.i("没有配置分享平台");
            ToastUtil.showToast(this.mActivity, "未配置分享平台");
            return;
        }
        new AsyncImageLoader(this.context).loadDrawable(shareBeanHandleShareData.getImg(), null, new AsyncImageLoader.ImageCallback() { // from class: com.sy37sdk.share.ShareManager.3
            @Override // com.sqwan.common.util.AsyncImageLoader.ImageCallback
            public void imageLoaded(Bitmap bitmap, ImageView imageView, String str2) {
                if (ShareManager.this.isBreak) {
                    return;
                }
                Handler handler2 = handler;
                if (handler2 != null) {
                    handler2.removeCallbacksAndMessages(null);
                }
                ShareManager.this.hideLoading();
                shareBeanHandleShareData.setBitmap(bitmap);
                ShareManager.this.share(shareBeanHandleShareData, iShareResultListener);
            }
        });
    }

    public void share(ShareBean shareBean, IShareResultListener iShareResultListener) {
        share(shareBean, false, iShareResultListener);
    }

    public void share(ShareBean shareBean, boolean z, IShareResultListener iShareResultListener) {
        ShareDialog shareDialog = new ShareDialog(this.mActivity);
        shareDialog.setShareBean(shareBean).setListener(iShareResultListener).ignoreShareWay(z);
        shareDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleRequestErro(String str, IShareResultListener iShareResultListener) {
        hideLoading();
        if (iShareResultListener != null) {
            iShareResultListener.onFailture(203, str);
        }
    }

    private ShareBean handleShareData(String str) {
        ShareBean shareBean = new ShareBean();
        shareBean.parse(str);
        return shareBean;
    }

    public void shareDirectly(int i, ShareBean shareBean, IShareResultListener iShareResultListener) {
        if (i == 1) {
            share("com.tencent.mm", "wx_appid", PlatformType.WECHAT, shareBean, iShareResultListener);
            return;
        }
        if (i == 2) {
            share("com.tencent.mm", "wx_appid", PlatformType.WECHAT_CIRCLE, shareBean, iShareResultListener);
            return;
        }
        if (i == 3) {
            share("com.tencent.mobileqq", "qq_appid", PlatformType.QQ, shareBean, iShareResultListener);
            return;
        }
        if (i == 4) {
            shareToSystem(shareBean, iShareResultListener);
            return;
        }
        LogUtil.i("未设置分享平台或设置了不支持的分享平台");
        if (iShareResultListener != null) {
            iShareResultListener.onFailture(203, "未设置分享平台或设置了不支持的分享平台");
        }
        HashMap map = new HashMap();
        map.put(ShareTrack.ShareTrackKey.classify, getClassifyType(shareBean));
        map.put(SqTrackKey.reason_fail, "未设置分享平台或设置了不支持的分享平台");
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_fail, map);
    }

    private void share(String str, String str2, final PlatformType platformType, final ShareBean shareBean, final IShareResultListener iShareResultListener) {
        String str3;
        if (!SqTrackUtil.checkAppInstalled(this.context, str)) {
            if ("com.tencent.mm".equals(str)) {
                str3 = "分享失败，请先安装微信客户端";
            } else {
                str3 = "com.tencent.mobileqq".equals(str) ? "分享失败，请先安装 QQ 客户端" : "分享失败，请先安装对应客户端";
            }
            LogUtil.i(str3);
            if (iShareResultListener != null) {
                iShareResultListener.onFailture(UnionPayWay.ERROR_INVALID_RESULT, str3);
            }
            HashMap map = new HashMap();
            map.put("platform", platformType.name());
            map.put(ShareTrack.ShareTrackKey.classify, getClassifyType(shareBean));
            map.put(SqTrackKey.reason_fail, ShareTrack.ShareTrackMsg.share_not_install);
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_fail, map);
            return;
        }
        if (supportSocialApp(str2)) {
            SocialApi.getInstance().share(this.mActivity, platformType, ShareMediaParser.parse(shareBean), new ShareListener() { // from class: com.sy37sdk.share.ShareManager.4
                public void onSuccess(PlatformType platformType2) {
                    LogUtil.i("分享成功");
                    IShareResultListener iShareResultListener2 = iShareResultListener;
                    if (iShareResultListener2 != null) {
                        iShareResultListener2.onSuccess(new Bundle());
                    }
                    HashMap map2 = new HashMap();
                    map2.put("platform", platformType.name());
                    map2.put(ShareTrack.ShareTrackKey.classify, ShareManager.this.getClassifyType(shareBean));
                    SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_success, map2);
                }

                public void onCancel(PlatformType platformType2) {
                    LogUtil.i("取消分享");
                    IShareResultListener iShareResultListener2 = iShareResultListener;
                    if (iShareResultListener2 != null) {
                        iShareResultListener2.onFailture(203, "取消分享");
                    }
                    HashMap map2 = new HashMap();
                    map2.put("platform", platformType.name());
                    map2.put(ShareTrack.ShareTrackKey.classify, ShareManager.this.getClassifyType(shareBean));
                    map2.put(SqTrackKey.reason_fail, ShareTrack.ShareTrackMsg.share_cancel);
                    SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_fail, map2);
                }

                public void onFailure(PlatformType platformType2, String str4) {
                    LogUtil.i(" 分享失败: " + str4);
                    IShareResultListener iShareResultListener2 = iShareResultListener;
                    if (iShareResultListener2 != null) {
                        iShareResultListener2.onFailture(203, str4);
                    }
                    HashMap map2 = new HashMap();
                    map2.put("platform", platformType.name());
                    map2.put(ShareTrack.ShareTrackKey.classify, ShareManager.this.getClassifyType(shareBean));
                    map2.put(SqTrackKey.reason_fail, str4);
                    SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_fail, map2);
                }
            });
            return;
        }
        if (shareBean.getType() == 2) {
            String landingPageUrl = shareBean.getLandingPageUrl();
            try {
                ClipboardManager clipboardManager = (ClipboardManager) this.context.getSystemService("clipboard");
                if (clipboardManager != null) {
                    clipboardManager.setPrimaryClip(ClipData.newPlainText("url", landingPageUrl.trim()));
                }
                ToastUtil.showToast(this.context, "分享链接已复制到粘贴板");
                if (iShareResultListener != null) {
                    iShareResultListener.onSuccess(new Bundle());
                }
                HashMap map2 = new HashMap();
                map2.put("platform", platformType.name());
                map2.put(ShareTrack.ShareTrackKey.classify, getClassifyType(shareBean));
                map2.put("message", "分享链接已复制到粘贴板");
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_success, map2);
                return;
            } catch (Exception e) {
                LogUtil.e("分享失败，无法复制链接到粘贴板", e);
                if (iShareResultListener != null) {
                    iShareResultListener.onFailture(203, "分享失败，无法复制链接到粘贴板");
                }
                HashMap map3 = new HashMap();
                map3.put("platform", platformType.name());
                map3.put(ShareTrack.ShareTrackKey.classify, getClassifyType(shareBean));
                map3.put(SqTrackKey.reason_fail, "分享失败，无法复制链接到粘贴板");
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_fail, map3);
                return;
            }
        }
        if (shareBean.getBitmap() != null) {
            ImageUtils.save(this.context, shareBean.getBitmap(), String.format("share_image_%s", UUID.randomUUID().toString()));
        }
        try {
            Intent launchIntentForPackage = this.context.getPackageManager().getLaunchIntentForPackage(str);
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.addFlags(268435456);
            intent.setComponent(launchIntentForPackage.getComponent());
            intent.addFlags(268435456);
            this.context.startActivity(intent);
            LogUtil.i("直接启动客户端");
            if (iShareResultListener != null) {
                iShareResultListener.onSuccess(new Bundle());
            }
            HashMap map4 = new HashMap();
            map4.put("platform", platformType.name());
            map4.put(ShareTrack.ShareTrackKey.classify, getClassifyType(shareBean));
            map4.put("message", "直接启动客户端");
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_success, map4);
        } catch (Exception e2) {
            e2.printStackTrace();
            LogUtil.e("打开第三方平台失败", e2);
            if (iShareResultListener != null) {
                iShareResultListener.onFailture(505, "打开第三方平台失败");
            }
            HashMap map5 = new HashMap();
            map5.put("platform", platformType.name());
            map5.put(ShareTrack.ShareTrackKey.classify, getClassifyType(shareBean));
            map5.put(SqTrackKey.reason_fail, "打开第三方平台失败");
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_fail, map5);
        }
    }

    public void shareToSystem(ShareBean shareBean, IShareResultListener iShareResultListener) {
        Intent intent;
        int type = shareBean.getType();
        if (type == 1) {
            Uri uriSave = ImageUtils.save(this.context, shareBean.getBitmap(), String.format("share_image_%s", Long.valueOf(System.currentTimeMillis())) + ".png");
            if (uriSave == null) {
                if (iShareResultListener != null) {
                    iShareResultListener.onFailture(203, "分享失败，图片文件保存失败");
                }
                HashMap map = new HashMap();
                map.put("platform", "SYSTEM");
                map.put(ShareTrack.ShareTrackKey.classify, getClassifyType(shareBean));
                map.put(SqTrackKey.reason_fail, "分享失败，图片文件保存失败");
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_fail, map);
                return;
            }
            Intent intent2 = new Intent("android.intent.action.SEND");
            intent2.addFlags(3);
            intent2.putExtra("android.intent.extra.STREAM", uriSave);
            intent2.setType("image/*");
            intent = intent2;
        } else if (type == 2) {
            intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.TEXT", shareBean.getTitle() + ": " + shareBean.getLandingPageUrl());
        } else if (type != 3) {
            intent = null;
        } else {
            intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.TEXT", shareBean.getText());
        }
        if (intent == null) {
            LogUtil.i("分享失败，分享类型不被支持");
            if (iShareResultListener != null) {
                iShareResultListener.onFailture(203, "分享失败，分享类型不被支持");
            }
            HashMap map2 = new HashMap();
            map2.put("platform", "SYSTEM");
            map2.put(ShareTrack.ShareTrackKey.classify, getClassifyType(shareBean));
            map2.put(SqTrackKey.reason_fail, "分享失败，分享类型不被支持");
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_fail, map2);
            return;
        }
        if (this.context.getPackageManager().queryIntentActivities(intent, 65536).isEmpty()) {
            LogUtil.i("分享失败，找不到对应的系统程序");
            if (iShareResultListener != null) {
                iShareResultListener.onFailture(203, "分享失败，找不到对应的系统程序");
            }
            HashMap map3 = new HashMap();
            map3.put("platform", "SYSTEM");
            map3.put(ShareTrack.ShareTrackKey.classify, getClassifyType(shareBean));
            map3.put(SqTrackKey.reason_fail, "分享失败，找不到对应的系统程序");
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_fail, map3);
            return;
        }
        Intent intentCreateChooser = Intent.createChooser(intent, "");
        intentCreateChooser.addFlags(268435456);
        try {
            this.context.startActivity(intentCreateChooser);
            if (iShareResultListener != null) {
                iShareResultListener.onSuccess(new Bundle());
            }
            LogUtil.i("调起系统分享成功");
            HashMap map4 = new HashMap();
            map4.put("platform", "SYSTEM");
            map4.put(ShareTrack.ShareTrackKey.classify, getClassifyType(shareBean));
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_success, map4);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("分享失败，调起系统分享失败", e);
            if (iShareResultListener != null) {
                iShareResultListener.onFailture(203, "分享失败，调起系统分享失败");
            }
            HashMap map5 = new HashMap();
            map5.put("platform", "SYSTEM");
            map5.put(ShareTrack.ShareTrackKey.classify, getClassifyType(shareBean));
            map5.put(SqTrackKey.reason_fail, "分享失败，调起系统分享失败");
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_fail, map5);
        }
    }

    private boolean supportSocialApp(String str) {
        try {
            return !TextUtils.isEmpty(this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 128).metaData.getString(str));
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("未找到第三方的配置参数");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getClassifyType(ShareBean shareBean) {
        return shareBean == null ? "" : String.valueOf(shareBean.getType());
    }
}
