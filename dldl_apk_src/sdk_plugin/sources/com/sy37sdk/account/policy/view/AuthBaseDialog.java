package com.sy37sdk.account.policy.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.duowan.live.login.LoginReportConstants;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.webview.SQWebViewDialog;
import com.sy37sdk.account.activebefore.ActiveBeforeManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public abstract class AuthBaseDialog extends Dialog {
    private ClickCallback clickCallback;
    protected Context mContext;
    protected String privacyprotol;
    protected TextView tvCancel;
    protected TextView tvOk;
    protected TextView tvTitle;
    protected String userprotol;

    public interface ClickCallback {
        void onClickCancel();

        void onClickOk();
    }

    public static class ClickCallbackAdapter implements ClickCallback {
        @Override // com.sy37sdk.account.policy.view.AuthBaseDialog.ClickCallback
        public void onClickCancel() {
        }

        @Override // com.sy37sdk.account.policy.view.AuthBaseDialog.ClickCallback
        public void onClickOk() {
        }
    }

    protected abstract void doEngine();

    protected abstract String getContainerLayout();

    protected abstract String getTitle();

    protected void onClickCancel() {
    }

    protected void onClickOk() {
    }

    public ClickCallback getClickCallback() {
        return this.clickCallback;
    }

    public void setClickCallback(ClickCallback clickCallback) {
        this.clickCallback = clickCallback;
    }

    public static class URLSpanNoUnderline extends URLSpan {
        private String mColorStr;
        private final Context mContext;

        public URLSpanNoUnderline(String str, Context context) {
            super(str);
            this.mContext = context;
        }

        public void setTextColor(String str) {
            this.mColorStr = str;
        }

        @Override // android.text.style.URLSpan, android.text.style.ClickableSpan
        public void onClick(View view) {
            String url = getURL();
            LogUtil.i("url " + url);
            if (TextUtils.isEmpty(url)) {
                return;
            }
            SQWebViewDialog sQWebViewDialog = new SQWebViewDialog(this.mContext);
            sQWebViewDialog.setCancelable(true);
            sQWebViewDialog.setUrl(url);
            sQWebViewDialog.show();
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setUnderlineText(false);
            if (TextUtils.isEmpty(this.mColorStr)) {
                textPaint.setColor(Color.parseColor("#f59a23"));
            } else {
                textPaint.setColor(Color.parseColor(this.mColorStr));
            }
        }
    }

    public AuthBaseDialog(Context context) {
        super(context, SqResUtils.getStyleId(context, "CustomDialog"));
        this.userprotol = ActiveBeforeManager.getInstance().userProtocolInfo.userprotol;
        this.privacyprotol = ActiveBeforeManager.getInstance().userProtocolInfo.privacyprotol;
        this.mContext = context;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        boolean z = getContext().getResources().getConfiguration().orientation == 2;
        setContentView(SqResUtils.getLayoutId(this.mContext, "sy37_dialog_permission_preview"));
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        doBase();
        doEngine();
        Display defaultDisplay = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        if (z) {
            attributes.width = (defaultDisplay.getWidth() / 4) * 3;
            attributes.gravity = 81;
        } else {
            attributes.gravity = 83;
            attributes.width = defaultDisplay.getWidth();
        }
        getWindow().setAttributes(attributes);
    }

    protected int findId(String str) {
        return SqResUtils.getId(this.mContext, str);
    }

    protected int findLayout(String str) {
        return SqResUtils.getLayoutId(this.mContext, str);
    }

    protected void doBase() {
        ((ViewGroup) findViewById(findId("rl_container"))).addView(View.inflate(this.mContext, findLayout(getContainerLayout()), null), new ViewGroup.LayoutParams(-1, -2));
        this.tvOk = (TextView) findViewById(findId("tv_ok"));
        TextView textView = (TextView) findViewById(findId("tv_title"));
        this.tvTitle = textView;
        textView.setText(getTitle());
        this.tvCancel = (TextView) findViewById(findId("tv_cancel"));
        this.tvOk.setSelected(true);
        this.tvCancel.setSelected(true);
        this.tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.policy.view.AuthBaseDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AuthBaseDialog.this.clickCallback != null) {
                    AuthBaseDialog.this.clickCallback.onClickCancel();
                }
                AuthBaseDialog.this.onClickCancel();
            }
        });
        this.tvOk.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.policy.view.AuthBaseDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AuthBaseDialog.this.clickCallback != null) {
                    AuthBaseDialog.this.clickCallback.onClickOk();
                }
                AuthBaseDialog.this.onClickOk();
            }
        });
    }

    protected void exit() {
        Context context = this.mContext;
        if (context instanceof Activity) {
            ((Activity) context).finish();
            System.exit(0);
        }
    }

    @Override // android.app.Dialog
    public void show() {
        Context context = this.mContext;
        if ((context instanceof Activity) && ((Activity) context).isFinishing()) {
            return;
        }
        super.show();
    }

    protected SpannableString setLink(TextView textView, String str) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new URLSpanNoUnderline(this.userprotol, this.mContext), str.indexOf("《用户协议》"), str.indexOf("《用户协议》") + 6, 33);
        spannableString.setSpan(new URLSpanNoUnderline(this.privacyprotol, this.mContext), str.indexOf(LoginReportConstants.UserGuide), str.indexOf(LoginReportConstants.UserGuide) + 6, 33);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setHighlightColor(0);
        return spannableString;
    }
}
