package com.duowan.auk.ui.toast;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
final class SafeToastContext extends ContextWrapper {
    private BadTokenListener badTokenListener;
    private Toast toast;

    SafeToastContext(Context context, Toast toast) {
        super(context);
        this.toast = toast;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Context getApplicationContext() {
        return new ApplicationContextWrapper(getBaseContext().getApplicationContext());
    }

    public void setBadTokenListener(BadTokenListener badTokenListener) {
        this.badTokenListener = badTokenListener;
    }

    private final class ApplicationContextWrapper extends ContextWrapper {
        private ApplicationContextWrapper(Context context) {
            super(context);
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public Object getSystemService(String str) {
            if ("window".equals(str)) {
                return new WindowManagerWrapper((WindowManager) getBaseContext().getSystemService(str));
            }
            return super.getSystemService(str);
        }
    }

    private final class WindowManagerWrapper implements WindowManager {
        private static final String TAG = "WindowManagerWrapper";
        private final WindowManager base;

        private WindowManagerWrapper(WindowManager windowManager) {
            this.base = windowManager;
        }

        @Override // android.view.WindowManager
        public Display getDefaultDisplay() {
            return this.base.getDefaultDisplay();
        }

        @Override // android.view.WindowManager
        public void removeViewImmediate(View view) {
            this.base.removeViewImmediate(view);
        }

        @Override // android.view.ViewManager
        public void addView(View view, ViewGroup.LayoutParams layoutParams) {
            try {
                Log.d(TAG, "WindowManager's addView(view, params) has been hooked.");
                this.base.addView(view, layoutParams);
            } catch (WindowManager.BadTokenException e) {
                Log.i(TAG, e.getMessage());
                if (SafeToastContext.this.badTokenListener != null) {
                    SafeToastContext.this.badTokenListener.onBadTokenCaught(SafeToastContext.this.toast);
                }
            } catch (Throwable th) {
                Log.e(TAG, "[addView]", th);
            }
        }

        @Override // android.view.ViewManager
        public void updateViewLayout(View view, ViewGroup.LayoutParams layoutParams) {
            this.base.updateViewLayout(view, layoutParams);
        }

        @Override // android.view.ViewManager
        public void removeView(View view) {
            this.base.removeView(view);
        }
    }
}
