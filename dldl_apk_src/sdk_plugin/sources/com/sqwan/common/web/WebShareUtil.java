package com.sqwan.common.web;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.parameters.bean.SystemShareBean;
import com.parameters.share.IShareInfo;
import com.parameters.share.ShareImageInfo;
import com.parameters.share.ShareMessage;
import com.parameters.share.ShareTextInfo;
import com.parameters.share.ShareWebInfo;
import com.parameters.share.WebShare;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.share.IShareMod;
import com.sqwan.common.mod.share.IShareResultListener;
import com.sqwan.common.util.AsyncImageLoader;
import com.sqwan.common.util.PermissionSimpleHelper;
import com.sy37sdk.order.third.union.UnionPayWay;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class WebShareUtil {
    public static void parseJsShare(Context context, String str) {
        parseJsShare(context, str, null);
    }

    public static void parseJsShare(Context context, String str, IShareResultListener iShareResultListener) {
        parseJsShare(context, str, null, iShareResultListener);
    }

    public static void parseJsShare(Context context, String str, Bitmap bitmap, final IShareResultListener iShareResultListener) {
        final WebShare fromJson = WebShare.parseFromJson(str);
        final Runnable runnable = new Runnable() { // from class: com.sqwan.common.web.-$$Lambda$WebShareUtil$evDDauVecocD0qBQp40u2nDWIXM
            @Override // java.lang.Runnable
            public final void run() {
                WebShareUtil.lambda$parseJsShare$0(fromJson, iShareResultListener);
            }
        };
        if (bitmap != null) {
            fromJson.setBitmap(bitmap);
            runnable.run();
        } else {
            new AsyncImageLoader(context).loadDrawable(fromJson.getImg(), null, new AsyncImageLoader.ImageCallback() { // from class: com.sqwan.common.web.-$$Lambda$WebShareUtil$dcPzAHAxF0l2MrkBeoEiJfvZ_zo
                @Override // com.sqwan.common.util.AsyncImageLoader.ImageCallback
                public final void imageLoaded(Bitmap bitmap2, ImageView imageView, String str2) {
                    WebShareUtil.lambda$parseJsShare$1(fromJson, runnable, bitmap2, imageView, str2);
                }
            });
        }
    }

    static /* synthetic */ void lambda$parseJsShare$0(WebShare webShare, final IShareResultListener iShareResultListener) {
        final ShareMessage shareMessage = new ShareMessage();
        shareMessage.setSkipPreview(true);
        shareMessage.setPlatform(parsePlatform(webShare));
        shareMessage.setShareMessage(parseShareMessage(webShare));
        final IShareMod iShareMod = (IShareMod) ModHelper.get(IShareMod.class);
        if (iShareMod == null) {
            if (iShareResultListener != null) {
                iShareResultListener.onFailture(UnionPayWay.ERROR_INVALID_RESULT, "分享内部错误，请尝试重启一下应用");
            }
        } else if (!(shareMessage.getShareMessage() instanceof ShareImageInfo)) {
            iShareMod.share(shareMessage, iShareResultListener);
        } else {
            PermissionSimpleHelper.requestPermission(PermissionSimpleHelper.STORAGE_PERMISSION, PermissionSimpleHelper.STORAGE_PERMISSION_NAME, PermissionSimpleHelper.STORAGE_PERMISSION_BY_SHARE, new PermissionSimpleHelper.OnPermissionCallback() { // from class: com.sqwan.common.web.WebShareUtil.1
                @Override // com.sqwan.common.util.PermissionSimpleHelper.OnPermissionCallback
                public void onGranted() {
                    iShareMod.share(shareMessage, iShareResultListener);
                }

                @Override // com.sqwan.common.util.PermissionSimpleHelper.OnPermissionCallback
                public void onDenied() {
                    IShareResultListener iShareResultListener2 = iShareResultListener;
                    if (iShareResultListener2 == null) {
                        return;
                    }
                    iShareResultListener2.onFailture(1002, "分享图片失败，没有存储权限");
                }
            });
        }
    }

    static /* synthetic */ void lambda$parseJsShare$1(WebShare webShare, Runnable runnable, Bitmap bitmap, ImageView imageView, String str) {
        webShare.setBitmap(bitmap);
        runnable.run();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int parsePlatform(com.parameters.share.WebShare r5) {
        /*
            r0 = -1
            if (r5 == 0) goto L51
            java.lang.String r1 = r5.getWay()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto Le
            goto L51
        Le:
            java.lang.String r5 = r5.getWay()
            int r1 = r5.hashCode()
            r2 = -791770330(0xffffffffd0ce8b26, float:-2.7721806E10)
            r3 = 2
            r4 = 1
            if (r1 == r2) goto L3b
            r2 = 3616(0xe20, float:5.067E-42)
            if (r1 == r2) goto L31
            r2 = 1235271283(0x49a0be73, float:1316814.4)
            if (r1 == r2) goto L27
            goto L45
        L27:
            java.lang.String r1 = "moments"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L45
            r5 = 1
            goto L46
        L31:
            java.lang.String r1 = "qq"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L45
            r5 = 2
            goto L46
        L3b:
            java.lang.String r1 = "wechat"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L45
            r5 = 0
            goto L46
        L45:
            r5 = -1
        L46:
            if (r5 == 0) goto L50
            if (r5 == r4) goto L4f
            if (r5 == r3) goto L4d
            return r0
        L4d:
            r5 = 3
            return r5
        L4f:
            return r3
        L50:
            return r4
        L51:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sqwan.common.web.WebShareUtil.parsePlatform(com.parameters.share.WebShare):int");
    }

    public static IShareInfo parseShareMessage(WebShare webShare) {
        int type = webShare.getType();
        if (type == 1) {
            ShareImageInfo shareImageInfo = new ShareImageInfo();
            shareImageInfo.setBitmap(webShare.getBitmap());
            return shareImageInfo;
        }
        if (type != 2) {
            return null;
        }
        ShareWebInfo shareWebInfo = new ShareWebInfo();
        shareWebInfo.setThumbBmp(webShare.getBitmap());
        shareWebInfo.setDesc(webShare.getDesc());
        shareWebInfo.setTitle(webShare.getTitle());
        shareWebInfo.setPageUrl(webShare.getLandingPageUrl());
        return shareWebInfo;
    }

    private static IShareInfo parseShareMessage(SystemShareBean systemShareBean) {
        int i = systemShareBean.shareType;
        if (i == 1) {
            ShareImageInfo shareImageInfo = new ShareImageInfo();
            shareImageInfo.setBitmap(systemShareBean.bitmap);
            return shareImageInfo;
        }
        if (i != 2) {
            if (i != 3) {
                return null;
            }
            ShareTextInfo shareTextInfo = new ShareTextInfo();
            shareTextInfo.setText(systemShareBean.shareText);
            return shareTextInfo;
        }
        ShareWebInfo shareWebInfo = new ShareWebInfo();
        shareWebInfo.setThumbBmp(systemShareBean.bitmap);
        shareWebInfo.setTitle(systemShareBean.shareTitle);
        shareWebInfo.setPageUrl(systemShareBean.shareLinkUrl);
        return shareWebInfo;
    }
}
