package com.sqwan.msdk.views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.StatusBarUtil;
import com.sqwan.msdk.api.MultiSDKUtils;
import com.sqwan.msdk.utils.BitmapUtils;
import com.sy37sdk.utils.Util;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQSplashDialog extends Dialog {
    private Bitmap bitmap;
    private Context mcontext;
    private SplashListener splashCallback;
    private int splashTime;

    public interface SplashListener {
        void afterSplash();
    }

    public SQSplashDialog(Context context, int i) {
        super(context, Util.getIdByName("Mdialog", "style", context.getPackageName(), context));
        this.splashTime = 3;
        this.mcontext = context;
        this.splashTime = i;
    }

    public SQSplashDialog(Context context) {
        super(context, Util.getIdByName("Mdialog", "style", context.getPackageName(), context));
        this.splashTime = 3;
        this.mcontext = context;
    }

    public void setSplashTime(int i) {
        this.splashTime = i;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (MultiSDKUtils.isScreenOriatationPortrait(this.mcontext)) {
            this.bitmap = BitmapUtils.decodeResource(this.mcontext, "sy37_splash_port");
        } else {
            this.bitmap = BitmapUtils.decodeResource(this.mcontext, "sy37_splash_land");
        }
        ImageView imageView = new ImageView(this.mcontext);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageBitmap(this.bitmap);
        setContentView(imageView);
        imageView.postDelayed(new Runnable() { // from class: com.sqwan.msdk.views.SQSplashDialog.1
            @Override // java.lang.Runnable
            public void run() {
                SQSplashDialog.this.dismiss();
                SQSplashDialog.this.bitmap.recycle();
                SQSplashDialog.this.bitmap = null;
                if (SQSplashDialog.this.splashCallback != null) {
                    SQSplashDialog.this.splashCallback.afterSplash();
                }
            }
        }, this.splashTime * 1000);
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            getWindow().setAttributes(attributes);
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
        super.show();
        StatusBarUtil.hideSystemUI(getWindow());
    }

    public void setSplashListener(SplashListener splashListener) {
        this.splashCallback = splashListener;
    }
}
