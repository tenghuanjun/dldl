package com.sy37sdk.share.dialog;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.ViewUtils;
import com.sy37sdk.share.ShareImageHandler;
import com.sy37sdk.share.bean.ShareBean;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class ShareConfirmDialog extends BaseDialog {
    private Context mContext;
    private IShareResultListener mListener;
    private ShareBean shareBean;
    private int shareWay;

    public ShareConfirmDialog(Context context) {
        super(context);
        this.shareWay = 0;
        this.mContext = context;
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        View viewInflate;
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        if (this.mContext.getResources().getConfiguration().orientation == 1) {
            viewInflate = getLayoutInflater().inflate(getIdByName("share_confirm_dialog_portrait", "layout"), (ViewGroup) null, false);
        } else {
            viewInflate = getLayoutInflater().inflate(getIdByName("share_confirm_dialog_landscape", "layout"), (ViewGroup) null, false);
        }
        setContentView(viewInflate);
        HashMap map = new HashMap();
        map.put(SqTrackKey.view_id, SqTrackPage.SqTrackViewId.share_confirm);
        map.put(SqTrackKey.view_name, SqTrackPage.SqTrackViewName.share_confirm);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.sdk_view_show, map);
        ImageView imageView = (ImageView) viewInflate.findViewById(getIdByName("img_preview", SqTrackCommonKey.id));
        imageView.setImageBitmap(this.shareBean.getBitmap());
        int[] iArrHandleSize = ShareImageHandler.handleSize(this.mContext, this.shareBean.getBitmap(), new int[]{DisplayUtil.dip2px(this.mContext, 240.0f), DisplayUtil.dip2px(this.mContext, 135.0f)}, new int[]{DisplayUtil.dip2px(this.mContext, 225.0f), DisplayUtil.dip2px(this.mContext, 400.0f)});
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = iArrHandleSize[0];
        layoutParams.height = iArrHandleSize[1];
        imageView.setLayoutParams(layoutParams);
        initView(viewInflate);
    }

    public ShareConfirmDialog setShareBean(ShareBean shareBean) {
        this.shareBean = shareBean;
        return this;
    }

    public ShareConfirmDialog setShareWay(int i) {
        this.shareWay = i;
        return this;
    }

    public ShareConfirmDialog setListener(IShareResultListener iShareResultListener) {
        this.mListener = iShareResultListener;
        return this;
    }

    private void initView(View view) {
        view.findViewById(getIdByName("close", SqTrackCommonKey.id)).setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.share.dialog.ShareConfirmDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ShareConfirmDialog.this.dismiss();
            }
        });
        View viewFindViewById = view.findViewById(getIdByName("open_platform", SqTrackCommonKey.id));
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.share.dialog.ShareConfirmDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.share_open_platform, SqTrackBtn.SqTrackBtnExt.share_open_platform);
                PlatformType platformType = PlatformType.WECHAT;
                int i = ShareConfirmDialog.this.shareWay;
                if (i == 0) {
                    ShareConfirmDialog.this.startPlatform("com.tencent.mm", platformType);
                } else if (i == 1) {
                    ShareConfirmDialog.this.startPlatform("com.tencent.mm", PlatformType.WECHAT_CIRCLE);
                } else if (i == 2) {
                    ShareConfirmDialog.this.startPlatform("com.tencent.mobileqq", PlatformType.QQ);
                } else {
                    HashMap map = new HashMap();
                    map.put("platform", platformType.name());
                    map.put(SqTrackKey.reason_fail, "不支持的分享平台" + ShareConfirmDialog.this.shareWay);
                    SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_fail, map);
                }
                ShareConfirmDialog.this.dismiss();
            }
        });
        TextView textView = (TextView) view.findViewById(SqResUtils.getIdByName("tv_share_way", SqTrackCommonKey.id, this.mContext));
        int i = this.shareWay;
        if (i == 0 || i == 1) {
            textView.setText("去微信分享给好友");
            viewFindViewById.setBackgroundResource(SqResUtils.getIdByName("sy37_open_wx_bg", "drawable", this.mContext));
        } else {
            if (i != 2) {
                return;
            }
            textView.setText("去QQ分享给好友");
            viewFindViewById.setBackgroundResource(SqResUtils.getIdByName("sy37_open_qq_bg", "drawable", this.mContext));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPlatform(String str, PlatformType platformType) {
        try {
            Intent launchIntentForPackage = this.mContext.getPackageManager().getLaunchIntentForPackage(str);
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.addFlags(268435456);
            intent.setComponent(launchIntentForPackage.getComponent());
            this.mContext.startActivity(intent);
            HashMap map = new HashMap();
            map.put("platform", platformType.name());
            map.put("message", "直接启动客户端");
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_success, map);
            if (this.mListener != null) {
                this.mListener.onSuccess(new Bundle());
            }
        } catch (Exception e) {
            e.printStackTrace();
            ViewUtils.showToast(this.mContext, "打开客户端失败");
            HashMap map2 = new HashMap();
            map2.put("platform", platformType.name());
            map2.put(SqTrackKey.reason_fail, e.toString());
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.share_fail, map2);
        }
    }
}
