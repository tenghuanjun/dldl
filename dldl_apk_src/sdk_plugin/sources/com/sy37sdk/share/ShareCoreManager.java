package com.sy37sdk.share;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import com.parameters.share.DefaultShareInfo;
import com.parameters.share.IShareInfo;
import com.parameters.share.ShareImageInfo;
import com.parameters.share.ShareMessage;
import com.parameters.share.ShareTextInfo;
import com.parameters.share.ShareWebInfo;
import com.social.sdk.platform.PlatformType;
import com.sqwan.common.mod.share.IShareResultListener;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.util.LogUtil;
import com.sy37sdk.share.bean.ShareBean;
import com.sy37sdk.share.track.ShareTrack;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class ShareCoreManager {
    private static volatile ShareCoreManager instance;
    private Context context;
    private Activity mActivity;

    private ShareCoreManager(Context context) {
        if (context instanceof Activity) {
            this.mActivity = (Activity) context;
        }
        this.context = context.getApplicationContext();
    }

    public static ShareCoreManager getInstance(Context context) {
        if (instance == null) {
            synchronized (AccountManager.class) {
                if (instance == null) {
                    instance = new ShareCoreManager(context);
                }
            }
        }
        return instance;
    }

    public void share(ShareMessage shareMessage, IShareResultListener iShareResultListener) {
        HashMap map = new HashMap();
        map.put("platform", getPlatformName(shareMessage));
        map.put(ShareTrack.ShareTrackKey.classify, getClassifyType(shareMessage));
        String str = "";
        if (shareMessage != null) {
            str = shareMessage.isSkipPreview() + "";
        }
        map.put(ShareTrack.ShareTrackKey.skipPreview, str);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_invoke, map);
        if (shareMessage == null) {
            LogUtil.e("分享失败，ShareMessage 为空");
            if (iShareResultListener != null) {
                iShareResultListener.onFailture(203, "分享失败，ShareMessage 为空");
            }
            HashMap map2 = new HashMap();
            map2.put("platform", getPlatformName(shareMessage));
            map2.put(ShareTrack.ShareTrackKey.classify, getClassifyType(shareMessage));
            map2.put(SqTrackKey.reason_fail, "分享失败，ShareMessage 为空");
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_fail, map2);
            return;
        }
        ShareTextInfo shareMessage2 = shareMessage.getShareMessage();
        if (shareMessage2 == null) {
            LogUtil.i("分享失败，ShareInfo 为空");
            if (iShareResultListener != null) {
                iShareResultListener.onFailture(203, "分享失败，ShareInfo 为空");
            }
            HashMap map3 = new HashMap();
            map3.put("platform", getPlatformName(shareMessage));
            map3.put(ShareTrack.ShareTrackKey.classify, getClassifyType(shareMessage));
            map3.put(SqTrackKey.reason_fail, "分享失败，ShareInfo 为空");
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_fail, map3);
            return;
        }
        LogUtil.i("classify=" + getClassifyType(shareMessage));
        int iClassify = shareMessage2.classify();
        if (iClassify == 0) {
            DefaultShareInfo defaultShareInfo = (DefaultShareInfo) shareMessage2;
            ShareManager.getInstance(this.mActivity).getShareSources(defaultShareInfo.getInviteCode(), defaultShareInfo.getImgId(), iShareResultListener);
            return;
        }
        if (iClassify == 1) {
            ShareBean shareBean = new ShareBean();
            shareBean.setBitmap(((ShareImageInfo) shareMessage2).getBitmap());
            shareBranch(shareMessage, shareBean, iShareResultListener);
            return;
        }
        if (iClassify == 2) {
            ShareWebInfo shareWebInfo = (ShareWebInfo) shareMessage2;
            ShareBean shareBean2 = new ShareBean();
            shareBean2.setType(2);
            shareBean2.setBitmap(shareWebInfo.getThumbBmp());
            shareBean2.setDesc(shareWebInfo.getDesc());
            shareBean2.setTitle(shareWebInfo.getTitle());
            shareBean2.setLandingPageUrl(shareWebInfo.getPageUrl());
            shareBranch(shareMessage, shareBean2, iShareResultListener);
            return;
        }
        if (iClassify == 3) {
            ShareBean shareBean3 = new ShareBean();
            shareBean3.setType(3);
            shareBean3.setText(shareMessage2.getText());
            shareBranch(shareMessage, shareBean3, iShareResultListener);
            return;
        }
        LogUtil.e("分享失败，不支持的分享内容类型");
        if (iShareResultListener != null) {
            iShareResultListener.onFailture(203, "分享失败，不支持的分享内容类型");
        }
        HashMap map4 = new HashMap();
        map4.put("platform", getPlatformName(shareMessage));
        map4.put(ShareTrack.ShareTrackKey.classify, getClassifyType(shareMessage));
        map4.put(SqTrackKey.reason_fail, "分享失败，不支持的分享内容类型");
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_fail, map4);
    }

    private void shareBranch(ShareMessage shareMessage, ShareBean shareBean, IShareResultListener iShareResultListener) {
        if (shareMessage.isSkipPreview()) {
            ShareManager.getInstance(this.mActivity).shareDirectly(shareMessage.getPlatform(), shareBean, iShareResultListener);
        } else {
            ShareManager.getInstance(this.mActivity).share(shareBean, true, iShareResultListener);
        }
    }

    private String getPlatformName(ShareMessage shareMessage) {
        if (shareMessage == null) {
            return "";
        }
        int platform = shareMessage.getPlatform();
        if (platform == 1) {
            return PlatformType.WECHAT.name();
        }
        if (platform == 2) {
            return PlatformType.WECHAT_CIRCLE.name();
        }
        if (platform != 3) {
            return platform != 4 ? "" : "SYSTEM";
        }
        return PlatformType.QQ.name();
    }

    private String getClassifyType(ShareMessage shareMessage) {
        IShareInfo shareMessage2;
        return (shareMessage == null || (shareMessage2 = shareMessage.getShareMessage()) == null) ? "" : String.valueOf(shareMessage2.classify());
    }
}
