package com.tencent.open.c;

import android.content.Context;
import android.os.Build;
import android.view.DisplayCutout;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.core.util.HalfKt$;
import com.bun.miitmdid.x$;
import java.util.List;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class c extends FrameLayout {
    public c(Context context) {
        super(context);
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        a(windowInsets);
        return super.onApplyWindowInsets(windowInsets);
    }

    private void a(WindowInsets windowInsets) {
        DisplayCutout displayCutoutM;
        List listM;
        if (Build.VERSION.SDK_INT < 28 || windowInsets == null || (displayCutoutM = HalfKt$.ExternalSyntheticApiModelOutline0.m(windowInsets)) == null || (listM = x$.ExternalSyntheticApiModelOutline0.m(displayCutoutM)) == null || listM.isEmpty()) {
            return;
        }
        setPadding(Math.max(x$.ExternalSyntheticApiModelOutline0.m$1(displayCutoutM), 0), Math.max(x$.ExternalSyntheticApiModelOutline0.m(displayCutoutM), 0), Math.max(x$.ExternalSyntheticApiModelOutline0.m$2(displayCutoutM), 0), Math.max(displayCutoutM.getSafeInsetBottom(), 0));
    }
}
