package com.sy37sdk.account.screenshot;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.sqwan.common.mod.account.IScreenshotListener;
import com.sqwan.common.util.AssetsUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.ToastUtil;
import java.lang.ref.WeakReference;
import java.util.Properties;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ScreenshotManager {
    private static ScreenshotManager mInstance;
    private Context mContext;
    private IScreenshotListener mScreenshotListener;
    private MyHandler myHandler;

    private ScreenshotManager(Context context) {
        this.mContext = context;
        this.myHandler = new MyHandler(context, Looper.getMainLooper());
    }

    public static ScreenshotManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (ScreenshotManager.class) {
                mInstance = new ScreenshotManager(context);
            }
        }
        return mInstance;
    }

    public void screenShot() {
        if (this.mScreenshotListener != null) {
            ToastUtil.showToast(this.mContext, "点击截图");
            new Thread() { // from class: com.sy37sdk.account.screenshot.ScreenshotManager.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    ScreenshotManager.this.myHandler.obtainMessage(1, ScreenshotManager.this.mScreenshotListener.createScreenshot()).sendToTarget();
                }
            }.start();
        } else {
            LogUtil.e("游戏未接入截图功能");
            ToastUtil.showToast(this.mContext, "游戏未接入截图功能！");
        }
    }

    public void setScreenshotListener(IScreenshotListener iScreenshotListener) {
        this.mScreenshotListener = iScreenshotListener;
    }

    public boolean isOpenScreenShot() {
        Properties properties = AssetsUtils.readProperties(this.mContext, "multiconfig");
        if (properties == null) {
            return false;
        }
        String property = properties.getProperty(AssetsUtils.PRO_SHOW_SCREENSHOT);
        return !TextUtils.isEmpty(property) && "1".equals(property);
    }

    private static class MyHandler extends Handler {
        private WeakReference<Context> contextWeakReference;

        MyHandler(Context context, Looper looper) {
            super(looper);
            this.contextWeakReference = new WeakReference<>(context);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            LogUtil.d("ScreenshotTest", "开始截屏显示");
            if (message.obj == null) {
                LogUtil.i("截图失败");
            } else {
                LogUtil.i("截图成功");
            }
            if (this.contextWeakReference.get() == null) {
                LogUtil.i("显示截图失败，context 为空");
            } else {
                new ShowScreenshotDialog(this.contextWeakReference.get(), (Bitmap) message.obj).show();
            }
        }
    }
}
