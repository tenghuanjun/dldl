package com.sy37sdk.account.floatview.redpacket;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.UrlUtils;
import com.sqwan.common.webview.SQWebViewDialog;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class RedPacketDialog extends SQWebViewDialog {
    private AnimatorSet animatorSet;
    private View floatView;
    private long mDuration;
    private RedPacketDialogCallback redPacketDialogCallback;
    private RedPacketInfo redPacketInfo;

    public interface AnimCallback {
        void onEnd();
    }

    public interface RedPacketDialogCallback {
        void onClose();

        void onOpenUrl();
    }

    public RedPacketDialog(Context context) {
        super(context);
        this.mDuration = 800L;
    }

    public RedPacketDialog(Context context, int i) {
        super(context, i);
        this.mDuration = 800L;
    }

    public void initData(View view, RedPacketInfo redPacketInfo, RedPacketDialogCallback redPacketDialogCallback) {
        this.redPacketInfo = redPacketInfo;
        this.floatView = view;
        this.redPacketDialogCallback = redPacketDialogCallback;
        String str = UrlUtils.parse(redPacketInfo.webViewConfig.pop_url).baseUrl;
        log("initData url " + str);
        setUrl(str);
    }

    @Override // com.sqwan.common.webview.SQWebViewDialog
    protected void jsOpenUrl(String str) {
        post(new Runnable() { // from class: com.sy37sdk.account.floatview.redpacket.RedPacketDialog.1
            @Override // java.lang.Runnable
            public void run() {
                if (RedPacketDialog.this.redPacketDialogCallback == null) {
                    return;
                }
                RedPacketDialog.this.redPacketDialogCallback.onOpenUrl();
            }
        });
    }

    /* JADX INFO: renamed from: com.sy37sdk.account.floatview.redpacket.RedPacketDialog$2, reason: invalid class name */
    class AnonymousClass2 implements Runnable {
        AnonymousClass2() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RedPacketDialog.this.handleAnim(new AnimCallback() { // from class: com.sy37sdk.account.floatview.redpacket.RedPacketDialog.2.1
                @Override // com.sy37sdk.account.floatview.redpacket.RedPacketDialog.AnimCallback
                public void onEnd() {
                    RedPacketDialog.this.dismiss();
                    if (RedPacketDialog.this.redPacketDialogCallback != null) {
                        RedPacketDialog.this.post(new Runnable() { // from class: com.sy37sdk.account.floatview.redpacket.RedPacketDialog.2.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                RedPacketDialog.this.redPacketDialogCallback.onClose();
                            }
                        });
                    }
                }
            });
        }
    }

    @Override // com.sqwan.common.webview.SQWebViewDialog
    protected void jsClose(String str, String str2) {
        post(new AnonymousClass2());
    }

    @Override // com.sqwan.common.webview.SQWebViewDialog, com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCanceledOnTouchOutside(false);
    }

    public void handleAnim(final AnimCallback animCallback) {
        if (this.mWebView == null || this.floatView == null) {
            if (animCallback != null) {
                animCallback.onEnd();
                return;
            }
            return;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        this.animatorSet = animatorSet;
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.sy37sdk.account.floatview.redpacket.RedPacketDialog.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                AnimCallback animCallback2 = animCallback;
                if (animCallback2 != null) {
                    animCallback2.onEnd();
                }
            }
        });
        int[] iArr = new int[2];
        this.floatView.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        int[] iArr2 = new int[2];
        this.mWebView.getLocationOnScreen(iArr2);
        int i3 = iArr2[0];
        int i4 = iArr2[1];
        int width = this.floatView.getWidth();
        int height = this.floatView.getHeight();
        int width2 = ((width / 2) + i) - ((this.mWebView.getWidth() / 2) + i3);
        int height2 = ((height / 2) + i2) - ((this.mWebView.getHeight() / 2) + i4);
        LogUtil.i(String.format("xFloatview:%d,yFloatview:%d,xWebview:%d,yWebview:%s,dx:%d,dy:%d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(width2), Integer.valueOf(height2)));
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.mWebView, "scaleY", 1.0f, 0.1f);
        this.animatorSet.playTogether(ObjectAnimator.ofFloat(this.mWebView, "translationX", 0.0f, width2), ObjectAnimator.ofFloat(this.mWebView, "translationY", 0.0f, height2), ObjectAnimator.ofFloat(this.mWebView, "scaleX", 1.0f, 0.1f), objectAnimatorOfFloat);
        this.animatorSet.setDuration(this.mDuration);
        this.animatorSet.setInterpolator(new AccelerateInterpolator());
        this.animatorSet.start();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AnimatorSet animatorSet = this.animatorSet;
        if (animatorSet != null) {
            animatorSet.removeAllListeners();
            this.animatorSet.end();
        }
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        jsClose("", "");
    }

    @Override // com.sqwan.common.webview.SQWebViewDialog
    public String getPopData() {
        RedPacketInfo redPacketInfo = this.redPacketInfo;
        if (redPacketInfo == null || redPacketInfo.webViewConfig == null || this.redPacketInfo.webViewConfig.pop_url == null) {
            return "";
        }
        String string = new JSONObject(UrlUtils.parse(this.redPacketInfo.webViewConfig.pop_url).params).toString();
        LogUtil.d("getPopData " + string);
        return string;
    }

    @Override // com.sqwan.common.webview.SQWebViewDialog
    protected void initView(View view) {
        super.initView(view);
        this.mWebView.getSettings().setUseWideViewPort(false);
        this.mWebView.getSettings().setTextZoom(100);
    }
}
