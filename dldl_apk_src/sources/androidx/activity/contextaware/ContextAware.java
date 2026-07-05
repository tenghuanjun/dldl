package androidx.activity.contextaware;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes5.dex */
public interface ContextAware {
    void addOnContextAvailableListener(@NonNull OnContextAvailableListener onContextAvailableListener);

    @Nullable
    Context peekAvailableContext();

    void removeOnContextAvailableListener(@NonNull OnContextAvailableListener onContextAvailableListener);
}
