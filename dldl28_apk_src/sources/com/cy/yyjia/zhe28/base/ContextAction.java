package com.cy.yyjia.zhe28.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes2.dex */
public interface ContextAction {
    int getColor(int id);

    Context getContext();

    Drawable getDrawable(int id);

    Resources getResources();

    String getString(int id);

    String getString(int id, Object... formatArgs);

    <S> S getSystemService(Class<S> serviceClass);

    void startActivity(Intent intent);

    void startActivity(Class<? extends Activity> clazz);

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.base.ContextAction$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$startActivity(ContextAction _this, Intent intent) {
            if (!(_this.getContext() instanceof Activity)) {
                intent.addFlags(268435456);
            }
            _this.getContext().startActivity(intent);
        }
    }
}
