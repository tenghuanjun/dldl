package com.sy37sdk.account.view.base.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sqwan.base.L;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.AsyncImageLoader;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.util.UrlUtils;
import com.sqwan.common.util.VersionUtil;
import com.sqwan.msdk.api.SQAppConfig;
import com.sqwan.msdk.config.ConfigManager;
import com.sy37sdk.account.UrlConstant;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AccountLoginAttachView extends RelativeLayout {
    private ImageView ivCustomer;
    private Context mContext;
    private View rootView;
    private TextView tvVersion;

    public AccountLoginAttachView(Context context) {
        this(context, null);
    }

    public AccountLoginAttachView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AccountLoginAttachView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        LogUtil.i("AccountLoginAttachView mcontext:" + this.mContext);
        this.rootView = View.inflate(context, SqResUtils.getLayoutId(this.mContext, "sysq_account_login_dialog_attach"), this);
        initView();
        initEvent();
    }

    private void initView() {
        this.tvVersion = (TextView) this.rootView.findViewById(SqResUtils.getId(this.mContext, "tv_sversion"));
        this.ivCustomer = (ImageView) this.rootView.findViewById(SqResUtils.getId(this.mContext, "iv_customer"));
        LogUtil.i("客服页面scut:" + ConfigManager.getInstance(this.mContext).getLoginCode());
        if (isSimplifiedSDK()) {
            this.ivCustomer.setVisibility(8);
        }
        this.tvVersion.setText("v" + VersionUtil.getVersionStr(this.mContext));
        if (!TextUtils.isEmpty(UrlConstant.KEFU_ICON_URL)) {
            new AsyncImageLoader(getContext()).loadDrawable(UrlConstant.KEFU_ICON_URL, this.ivCustomer, new AsyncImageLoader.ImageCallback() { // from class: com.sy37sdk.account.view.base.view.-$$Lambda$AccountLoginAttachView$Q7Y_rVb4KS5gASl9EkPSnlnnxqU
                @Override // com.sqwan.common.util.AsyncImageLoader.ImageCallback
                public final void imageLoaded(Bitmap bitmap, ImageView imageView, String str) {
                    this.f$0.lambda$initView$0$AccountLoginAttachView(bitmap, imageView, str);
                }
            });
        } else {
            this.ivCustomer.setBackgroundResource(SqResUtils.getDrawableId(this.mContext, "sysq_icon_customer"));
        }
    }

    public /* synthetic */ void lambda$initView$0$AccountLoginAttachView(Bitmap bitmap, ImageView imageView, String str) {
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setBackgroundResource(SqResUtils.getDrawableId(this.mContext, "sysq_icon_customer"));
        }
    }

    private void initEvent() {
        if (isSimplifiedSDK()) {
            return;
        }
        this.ivCustomer.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.base.view.AccountLoginAttachView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AccountLoginAttachView.this.showKefuPage();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showKefuPage() {
        if (TextUtils.isEmpty(UrlConstant.KEFU_GUIDE_URL)) {
            ToastUtil.showToast("功能升级中");
            return;
        }
        Activity activity = L.getActivity();
        SQAppConfig sQAppConfig = ConfigManager.getInstance(activity).getSQAppConfig();
        HashMap map = new HashMap();
        map.put("pid", sQAppConfig.getPartner());
        map.put("gid", sQAppConfig.getGameid());
        AppUtils.toSQWebUrlNoAppend(activity, UrlUtils.appendUrlParams(UrlConstant.KEFU_GUIDE_URL, map), "", false);
    }

    private boolean isSimplifiedSDK() {
        return ConfigManager.getInstance(this.mContext).isSimplifiedSDK();
    }
}
