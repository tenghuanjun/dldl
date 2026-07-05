package com.sy37sdk.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.sqwan.common.util.AsyncImageLoader;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.StatusBarUtil;
import com.sy37sdk.utils.BitmapUtils;
import com.sy37sdk.utils.Util;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class SplashDialog extends Dialog {
    private Bitmap bitmap;
    private Context mContext;
    private String mSplashImgUrl;
    private SplashListener splashCallback;
    private int splashTime;

    public interface SplashListener {
        void afterSplash();
    }

    public SplashDialog(Context context, int i) {
        super(context, Util.getIdByName("Mdialog", "style", context.getPackageName(), context));
        this.splashTime = 3;
        this.mContext = context;
        this.splashTime = i;
    }

    public SplashDialog(Context context) {
        super(context, Util.getIdByName("Mdialog", "style", context.getPackageName(), context));
        this.splashTime = 3;
        this.mContext = context;
    }

    public void setSplashTime(int i) {
        this.splashTime = i;
    }

    public void setImgUrl(String str) {
        this.mSplashImgUrl = str;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.i("splash dialog onCreate!");
        ImageView imageView = new ImageView(this.mContext);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if (TextUtils.isEmpty(this.mSplashImgUrl)) {
            LogUtil.i("使用老的逻辑显示本地闪图");
            if (Util.isScreenOriatationPortrait(this.mContext)) {
                this.bitmap = BitmapUtils.decodeResource(getContext(), "sy37_splash_port");
            } else {
                this.bitmap = BitmapUtils.decodeResource(getContext(), "sy37_splash_land");
            }
            LogUtil.e("show splash dialog");
            imageView.setImageBitmap(this.bitmap);
            imageView.postDelayed(new Runnable() { // from class: com.sy37sdk.views.SplashDialog.1
                @Override // java.lang.Runnable
                public void run() {
                    SplashDialog.this.dismiss();
                    SplashDialog.this.bitmap.recycle();
                    SplashDialog.this.bitmap = null;
                    if (SplashDialog.this.splashCallback != null) {
                        SplashDialog.this.splashCallback.afterSplash();
                    }
                }
            }, this.splashTime * 1000);
        } else {
            LogUtil.i("加载后端配置的闪图：" + this.mSplashImgUrl);
            new AsyncImageLoader(this.mContext).loadDrawable(this.mSplashImgUrl, imageView, new AsyncImageLoader.ImageCallback() { // from class: com.sy37sdk.views.SplashDialog.2
                @Override // com.sqwan.common.util.AsyncImageLoader.ImageCallback
                public void imageLoaded(Bitmap bitmap, ImageView imageView2, String str) {
                    SplashDialog.this.bitmap = bitmap;
                    imageView2.setImageBitmap(bitmap);
                    imageView2.postDelayed(new Runnable() { // from class: com.sy37sdk.views.SplashDialog.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SplashDialog.this.dismiss();
                            if (SplashDialog.this.bitmap != null) {
                                SplashDialog.this.bitmap.recycle();
                                SplashDialog.this.bitmap = null;
                            }
                            if (SplashDialog.this.splashCallback != null) {
                                SplashDialog.this.splashCallback.afterSplash();
                            }
                        }
                    }, SplashDialog.this.splashTime * 1000);
                }
            });
        }
        setContentView(imageView);
        hideSystemView(getWindow());
    }

    private void hideSystemView(Window window) {
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            window.setAttributes(attributes);
            return;
        }
        hideMiuiNotch();
    }

    private void hideMiuiNotch() {
        try {
            Window.class.getMethod("addExtraFlags", Integer.TYPE).invoke(getWindow(), 1792);
        } catch (Exception unused) {
            LogUtil.i("addExtraFlags not found.");
        }
    }

    @Override // android.app.Dialog
    public void show() {
        Context context = this.mContext;
        if (context == null || ((Activity) context).isFinishing()) {
            return;
        }
        super.show();
        StatusBarUtil.hideSystemUI(getWindow());
    }

    public void setSplashListener(SplashListener splashListener) {
        this.splashCallback = splashListener;
    }
}
