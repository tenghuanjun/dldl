package com.sy37sdk.share.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.social.sdk.SocialApi;
import com.social.sdk.common.listener.ShareListener;
import com.social.sdk.platform.PlatformType;
import com.sq.sdk.tool.util.DisplayUtil;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.mod.share.IShareResultListener;
import com.sqwan.common.mvp.BaseDialog;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.track.SqTrackPage;
import com.sqwan.common.util.CutoutUtil;
import com.sqwan.common.util.ImageUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.MD5Util;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.StatusBarUtil;
import com.sqwan.common.util.ViewUtils;
import com.sy37sdk.share.ShareImageHandler;
import com.sy37sdk.share.ShareManager;
import com.sy37sdk.share.bean.ShareBean;
import com.sy37sdk.share.bean.ShareMediaParser;
import com.sy37sdk.share.track.ShareTrack;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class ShareDialog extends BaseDialog {
    private boolean ignoreShareWay;
    private Context mContext;
    private IShareResultListener mListener;
    private ShareListener mShareListener;
    private ShareBean shareBean;

    public ShareDialog(Context context) {
        super(context);
        this.mShareListener = new ShareListener() { // from class: com.sy37sdk.share.dialog.ShareDialog.6
            public void onSuccess(PlatformType platformType) {
                LogUtil.i(" share onSuccess");
                HashMap map = new HashMap();
                map.put("platform", platformType.name());
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_success, map);
                if (ShareDialog.this.mListener != null) {
                    ShareDialog.this.mListener.onSuccess(new Bundle());
                }
            }

            public void onCancel(PlatformType platformType) {
                LogUtil.i(" share onCancel");
                HashMap map = new HashMap();
                map.put("platform", platformType.name());
                map.put(SqTrackKey.reason_fail, ShareTrack.ShareTrackMsg.share_cancel);
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_fail, map);
                if (ShareDialog.this.mListener != null) {
                    ShareDialog.this.mListener.onFailture(203, ShareTrack.ShareTrackMsg.share_cancel);
                }
            }

            public void onFailure(PlatformType platformType, String str) {
                LogUtil.i(" share onFailure platform: " + platformType.name() + " message: " + str);
                HashMap map = new HashMap();
                map.put("platform", platformType.name());
                map.put(SqTrackKey.reason_fail, str);
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_fail, map);
                ViewUtils.showToast(ShareDialog.this.mContext, "分享失败");
                if (ShareDialog.this.mListener != null) {
                    ShareDialog.this.mListener.onFailture(203, "分享失败：" + str);
                }
            }
        };
        this.mContext = context;
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        int iDip2px;
        int i;
        RelativeLayout relativeLayout;
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        int i2 = this.mContext.getResources().getConfiguration().orientation;
        int screenHeight = DisplayUtil.getScreenHeight(this.mContext);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        if (i2 == 2) {
            iDip2px = screenHeight - DisplayUtil.dip2px(this.mContext, 120.0f);
            i = (iDip2px * 16) / 9;
            relativeLayout = (RelativeLayout) getLayoutInflater().inflate(getIdByName("sy37_share_dialog_landscape", "layout"), (ViewGroup) null, false);
            iArr[0] = i;
            iArr[1] = iDip2px;
        } else {
            iDip2px = screenHeight - DisplayUtil.dip2px(this.mContext, 180.0f);
            i = (iDip2px * 9) / 16;
            iArr2[0] = i;
            iArr2[1] = iDip2px;
            relativeLayout = (RelativeLayout) getLayoutInflater().inflate(getIdByName("sy37_share_dialog_portrait", "layout"), (ViewGroup) null, false);
        }
        setContentView(relativeLayout);
        HashMap map = new HashMap();
        map.put(SqTrackKey.view_id, SqTrackPage.SqTrackViewId.share_preview);
        map.put(SqTrackKey.view_name, SqTrackPage.SqTrackViewName.share_preview);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.sdk_view_show, map);
        int[] iArrHandleSize = ShareImageHandler.handleSize(this.mContext, this.shareBean.getBitmap(), iArr, iArr2);
        RelativeLayout relativeLayout2 = (RelativeLayout) relativeLayout.findViewById(getIdByName("rl_img", SqTrackCommonKey.id));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) relativeLayout2.getLayoutParams();
        layoutParams.width = i + (DisplayUtil.dip2px(this.mContext, 5.0f) * 2);
        layoutParams.height = iDip2px + (DisplayUtil.dip2px(this.mContext, 5.0f) * 2);
        relativeLayout2.setLayoutParams(layoutParams);
        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this.mContext);
        ImageView imageView = (ImageView) relativeLayout.findViewById(getIdByName("imageView", SqTrackCommonKey.id));
        imageView.setImageBitmap(this.shareBean.getBitmap());
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams2.width = iArrHandleSize[0];
        layoutParams2.height = iArrHandleSize[1];
        if (i2 == 1 && CutoutUtil.hasNotchScreen((Activity) this.mContext)) {
            layoutParams.topMargin = statusBarHeight;
            relativeLayout2.setLayoutParams(layoutParams);
        }
        imageView.setLayoutParams(layoutParams2);
        relativeLayout.findViewById(getIdByName("close", SqTrackCommonKey.id)).setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.share.dialog.ShareDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HashMap map2 = new HashMap();
                map2.put(SqTrackKey.reason_fail, ShareTrack.ShareTrackMsg.share_close);
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_fail, map2);
                if (ShareDialog.this.mListener != null) {
                    ShareDialog.this.mListener.onFailture(203, ShareTrack.ShareTrackMsg.share_cancel);
                }
                ShareDialog.this.dismiss();
            }
        });
        initView(relativeLayout);
    }

    private void initView(View view) {
        View viewFindViewById = view.findViewById(SqResUtils.getIdByName("ll_share_way", SqTrackCommonKey.id, this.mContext));
        List<Integer> shareWays = this.shareBean.getShareWays();
        if (!this.ignoreShareWay && (shareWays == null || shareWays.isEmpty())) {
            viewFindViewById.setVisibility(8);
            return;
        }
        View viewFindViewById2 = view.findViewById(SqResUtils.getIdByName("share_wx", SqTrackCommonKey.id, this.mContext));
        View viewFindViewById3 = view.findViewById(SqResUtils.getIdByName("share_wx_circle", SqTrackCommonKey.id, this.mContext));
        View viewFindViewById4 = view.findViewById(SqResUtils.getIdByName("share_qq", SqTrackCommonKey.id, this.mContext));
        View viewFindViewById5 = view.findViewById(SqResUtils.getIdByName("share_more", SqTrackCommonKey.id, this.mContext));
        if (this.ignoreShareWay) {
            viewFindViewById2.setVisibility(0);
            viewFindViewById3.setVisibility(0);
            viewFindViewById4.setVisibility(0);
            viewFindViewById5.setVisibility(0);
        } else {
            Iterator<Integer> it = shareWays.iterator();
            while (it.hasNext()) {
                int iIntValue = it.next().intValue();
                if (iIntValue == 0) {
                    viewFindViewById2.setVisibility(0);
                } else if (iIntValue == 1) {
                    viewFindViewById3.setVisibility(0);
                } else if (iIntValue == 2) {
                    viewFindViewById4.setVisibility(0);
                } else if (iIntValue == 3) {
                    viewFindViewById5.setVisibility(0);
                }
            }
        }
        viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.share.dialog.ShareDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.share_wechat, SqTrackBtn.SqTrackBtnExt.share_wechat);
                if (ShareDialog.this.checkInstallWX()) {
                    if (ShareDialog.this.supportWx()) {
                        ShareDialog.this.share(PlatformType.WECHAT);
                        ShareDialog.this.dismiss();
                        return;
                    } else {
                        ShareDialog.this.shareOriginal(0);
                        return;
                    }
                }
                HashMap map = new HashMap();
                map.put(SqTrackKey.reason_fail, "未安装客户端微信");
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_fail, map);
            }
        });
        viewFindViewById3.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.share.dialog.ShareDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.share_moment, SqTrackBtn.SqTrackBtnExt.share_moment);
                if (ShareDialog.this.checkInstallWX()) {
                    if (ShareDialog.this.supportWx()) {
                        ShareDialog.this.share(PlatformType.WECHAT_CIRCLE);
                        ShareDialog.this.dismiss();
                        return;
                    } else {
                        ShareDialog.this.shareOriginal(1);
                        return;
                    }
                }
                HashMap map = new HashMap();
                map.put(SqTrackKey.reason_fail, "未安装客户端微信");
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_fail, map);
            }
        });
        viewFindViewById4.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.share.dialog.ShareDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.share_qq, SqTrackBtn.SqTrackBtnExt.share_qq);
                if (ShareDialog.this.checkInstallQQ()) {
                    if (ShareDialog.this.supportQQ()) {
                        ShareDialog.this.share(PlatformType.QQ);
                        ShareDialog.this.dismiss();
                        return;
                    } else {
                        ShareDialog.this.shareOriginal(2);
                        return;
                    }
                }
                HashMap map = new HashMap();
                map.put(SqTrackKey.reason_fail, "未安装客户端qq");
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_fail, map);
            }
        });
        viewFindViewById5.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.share.dialog.ShareDialog.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.share_system, SqTrackBtn.SqTrackBtnExt.share_system);
                ShareManager.getInstance(ShareDialog.this.getContext()).shareToSystem(ShareDialog.this.shareBean, ShareDialog.this.mListener);
                ShareDialog.this.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shareOriginal(int i) {
        String strMd5;
        if (TextUtils.isEmpty(this.shareBean.getImg())) {
            strMd5 = MD5Util.Md5(ShareImageHandler.bitmapToBase64(this.shareBean.getBitmap())) + ".jpg";
            LogUtil.i("存储路径：" + strMd5);
        } else {
            strMd5 = MD5Util.Md5(this.shareBean.getImg());
        }
        if (ImageUtils.save(this.mContext, this.shareBean.getBitmap(), strMd5 + ".jpg") == null) {
            showTipDailog();
        } else {
            new ShareConfirmDialog(this.mContext).setShareBean(this.shareBean).setShareWay(i).setListener(this.mListener).show();
        }
    }

    private void showTipDailog() {
        new ShareTipDialog(this.mContext).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkInstallWX() {
        boolean zCheckInstall = checkInstall("com.tencent.mm");
        if (!zCheckInstall) {
            ViewUtils.showToast(this.mContext, "请先安装微信客户端");
        }
        return zCheckInstall;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean supportWx() {
        try {
            return !TextUtils.isEmpty(this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 128).metaData.getString("wx_appid"));
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("未找到微信参数");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkInstallQQ() {
        boolean zCheckInstall = checkInstall("com.tencent.mobileqq");
        if (!zCheckInstall) {
            ViewUtils.showToast(this.mContext, "请先安装qq客户端");
        }
        return zCheckInstall;
    }

    private boolean checkInstall(String str) {
        try {
            this.mContext.getPackageManager().getApplicationInfo(str, 8192);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean supportQQ() {
        try {
            return !TextUtils.isEmpty(this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 128).metaData.getString("qq_appid"));
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("未找到qq参数");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void share(PlatformType platformType) {
        SocialApi.getInstance().share((Activity) this.mContext, platformType, ShareMediaParser.parse(this.shareBean), this.mShareListener);
    }

    public ShareDialog setShareBean(ShareBean shareBean) {
        this.shareBean = shareBean;
        return this;
    }

    public ShareDialog ignoreShareWay(boolean z) {
        this.ignoreShareWay = z;
        return this;
    }

    public ShareDialog setListener(IShareResultListener iShareResultListener) {
        this.mListener = iShareResultListener;
        return this;
    }
}
