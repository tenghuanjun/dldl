package androidx.core.content.res;

import android.content.res.Resources;
import android.os.Build;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes5.dex */
public final class ConfigurationHelper {
    private ConfigurationHelper() {
    }

    public static int getDensityDpi(@NonNull Resources resources) {
        if (Build.VERSION.SDK_INT >= 17) {
            return resources.getConfiguration().densityDpi;
        }
        return resources.getDisplayMetrics().densityDpi;
    }
}
