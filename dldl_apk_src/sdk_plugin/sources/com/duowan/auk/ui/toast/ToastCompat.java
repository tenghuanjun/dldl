package com.duowan.auk.ui.toast;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.View;
import android.widget.Toast;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ToastCompat extends Toast {
    private final Toast toast;

    private ToastCompat(Context context, Toast toast) {
        super(context);
        this.toast = toast;
    }

    public static ToastCompat makeText(Context context, CharSequence charSequence, int i) {
        Toast toastMakeText = Toast.makeText(context, charSequence, i);
        setContextCompat(toastMakeText.getView(), new SafeToastContext(context, toastMakeText));
        return new ToastCompat(context, toastMakeText);
    }

    public static Toast makeText(Context context, int i, int i2) throws Resources.NotFoundException {
        return makeText(context, context.getResources().getText(i), i2);
    }

    public ToastCompat setBadTokenListener(BadTokenListener badTokenListener) {
        Context context = getView().getContext();
        if (context instanceof SafeToastContext) {
            ((SafeToastContext) context).setBadTokenListener(badTokenListener);
        }
        return this;
    }

    @Override // android.widget.Toast
    public void show() {
        this.toast.show();
    }

    @Override // android.widget.Toast
    public void setDuration(int i) {
        this.toast.setDuration(i);
    }

    @Override // android.widget.Toast
    public void setGravity(int i, int i2, int i3) {
        this.toast.setGravity(i, i2, i3);
    }

    @Override // android.widget.Toast
    public void setMargin(float f, float f2) {
        this.toast.setMargin(f, f2);
    }

    @Override // android.widget.Toast
    public void setText(int i) {
        this.toast.setText(i);
    }

    @Override // android.widget.Toast
    public void setText(CharSequence charSequence) {
        this.toast.setText(charSequence);
    }

    @Override // android.widget.Toast
    public void setView(View view) {
        this.toast.setView(view);
        setContextCompat(view, new SafeToastContext(view.getContext(), this));
    }

    @Override // android.widget.Toast
    public float getHorizontalMargin() {
        return this.toast.getHorizontalMargin();
    }

    @Override // android.widget.Toast
    public float getVerticalMargin() {
        return this.toast.getVerticalMargin();
    }

    @Override // android.widget.Toast
    public int getDuration() {
        return this.toast.getDuration();
    }

    @Override // android.widget.Toast
    public int getGravity() {
        return this.toast.getGravity();
    }

    @Override // android.widget.Toast
    public int getXOffset() {
        return this.toast.getXOffset();
    }

    @Override // android.widget.Toast
    public int getYOffset() {
        return this.toast.getYOffset();
    }

    @Override // android.widget.Toast
    public View getView() {
        return this.toast.getView();
    }

    public Toast getBaseToast() {
        return this.toast;
    }

    private static void setContextCompat(View view, Context context) {
        if (Build.VERSION.SDK_INT == 25) {
            try {
                Field declaredField = View.class.getDeclaredField("mContext");
                declaredField.setAccessible(true);
                declaredField.set(view, context);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
