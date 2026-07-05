package androidx.activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes5.dex */
public interface OnBackPressedDispatcherOwner extends LifecycleOwner {
    @NonNull
    OnBackPressedDispatcher getOnBackPressedDispatcher();
}
