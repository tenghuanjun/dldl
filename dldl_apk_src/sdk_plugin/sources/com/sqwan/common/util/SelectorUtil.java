package com.sqwan.common.util;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.IOException;
import java.net.URL;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SelectorUtil {
    public static void addSelectorFromDrawable(Context context, int i, int i2, ImageView imageView) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable drawable = context.getResources().getDrawable(i);
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, context.getResources().getDrawable(i2));
        stateListDrawable.addState(new int[]{-16842919}, drawable);
        imageView.setBackgroundDrawable(stateListDrawable);
    }

    public static void addSelectorFromDrawable(Context context, int i, int i2, Button button) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable drawable = context.getResources().getDrawable(i);
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, context.getResources().getDrawable(i2));
        stateListDrawable.addState(new int[]{-16842919}, drawable);
        button.setBackgroundDrawable(stateListDrawable);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.sqwan.common.util.SelectorUtil$1] */
    public static void addSeletorFromNet(final Class cls, final String str, final String str2, final ImageView imageView) {
        new AsyncTask<Void, Void, Drawable>() { // from class: com.sqwan.common.util.SelectorUtil.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Drawable doInBackground(Void... voidArr) {
                StateListDrawable stateListDrawable = new StateListDrawable();
                Drawable drawableLoadImageFromNet = SelectorUtil.loadImageFromNet(cls, str);
                stateListDrawable.addState(new int[]{R.attr.state_pressed}, SelectorUtil.loadImageFromNet(cls, str2));
                stateListDrawable.addState(new int[]{-16842919}, drawableLoadImageFromNet);
                return stateListDrawable;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Drawable drawable) {
                super.onPostExecute(drawable);
                imageView.setBackgroundDrawable(drawable);
            }
        }.execute(new Void[0]);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.sqwan.common.util.SelectorUtil$2] */
    public static void addSeletorFromNet(final Class cls, final String str, final String str2, final Button button) {
        new AsyncTask<Void, Void, Drawable>() { // from class: com.sqwan.common.util.SelectorUtil.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Drawable doInBackground(Void... voidArr) {
                StateListDrawable stateListDrawable = new StateListDrawable();
                Drawable drawableLoadImageFromNet = SelectorUtil.loadImageFromNet(cls, str);
                stateListDrawable.addState(new int[]{R.attr.state_pressed}, SelectorUtil.loadImageFromNet(cls, str2));
                stateListDrawable.addState(new int[]{-16842919}, drawableLoadImageFromNet);
                return stateListDrawable;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Drawable drawable) {
                super.onPostExecute(drawable);
                button.setBackgroundDrawable(drawable);
            }
        }.execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Drawable loadImageFromNet(Class cls, String str) {
        try {
            return Drawable.createFromStream(new URL(str).openStream(), "netUrl.jpg");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void addSelectorFromDrawable(Context context, int i, int i2, View view) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable drawable = context.getResources().getDrawable(i);
        Drawable drawable2 = context.getResources().getDrawable(i2);
        stateListDrawable.addState(new int[]{R.attr.state_selected}, drawable);
        stateListDrawable.addState(new int[]{-16842913}, drawable2);
        view.setBackgroundDrawable(stateListDrawable);
    }

    public static void addSelectorFromDrawable(Context context, int[] iArr, int[] iArr2, View view) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        for (int i = 0; i < iArr.length; i++) {
            stateListDrawable.addState(new int[]{iArr[i]}, context.getResources().getDrawable(iArr2[i]));
        }
        view.setBackground(stateListDrawable);
    }

    public static void addSelectorFromDrawable(Context context, int[] iArr, Drawable[] drawableArr, View view) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        for (int i = 0; i < iArr.length; i++) {
            stateListDrawable.addState(new int[]{iArr[i]}, drawableArr[i]);
        }
        view.setBackground(stateListDrawable);
    }
}
