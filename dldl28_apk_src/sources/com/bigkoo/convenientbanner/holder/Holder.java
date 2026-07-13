package com.bigkoo.convenientbanner.holder;

import android.content.Context;
import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public interface Holder<T> {
    void UpdateUI(Context context, int i, T t);

    View createView(Context context);
}
