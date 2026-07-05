package com.taptap.sdk.kit.internal.extensions;

import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import com.sqwan.liveshow.huya.SqR;
import com.taptap.sdk.kit.internal.TapTapKit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UIExt.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0006\u0010\u0000\u001a\u00020\u0001\u001a\f\u0010\u0002\u001a\u00020\u0003*\u0004\u0018\u00010\u0004\u001a\f\u0010\u0005\u001a\u00020\u0003*\u0004\u0018\u00010\u0004\u001a\f\u0010\u0006\u001a\u00020\u0007*\u0004\u0018\u00010\u0004\u001a(\u0010\b\u001a\u00020\u0003*\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00030\f\u001a\n\u0010\r\u001a\u00020\u000e*\u00020\u000e\u001a\n\u0010\u000f\u001a\u00020\u0010*\u00020\u000e\u001a\f\u0010\u0011\u001a\u00020\u0003*\u0004\u0018\u00010\u0004¨\u0006\u0012"}, d2 = {"getThemeText", "", "gone", "", "Landroid/view/View;", "invisible", "isVisible", "", "setDebounceClickListener", "debounceInterval", "", "listenerBlock", "Lkotlin/Function1;", "toDp", "", "toPx", "", "visible", "tap-common_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class UIExtKt {
    public static final void visible(View view) {
        if (view == null) {
            return;
        }
        view.setVisibility(0);
    }

    public static final void gone(View view) {
        if (view == null) {
            return;
        }
        view.setVisibility(8);
    }

    public static final void invisible(View view) {
        if (view == null) {
            return;
        }
        view.setVisibility(4);
    }

    public static final boolean isVisible(View view) {
        return view != null && view.getVisibility() == 0;
    }

    public static final int toPx(float f) {
        return (int) TypedValue.applyDimension(1, f, Resources.getSystem().getDisplayMetrics());
    }

    public static final float toDp(float f) {
        return TypedValue.applyDimension(0, f, Resources.getSystem().getDisplayMetrics());
    }

    public static /* synthetic */ void setDebounceClickListener$default(View view, long j, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 500;
        }
        setDebounceClickListener(view, j, function1);
    }

    public static final void setDebounceClickListener(View view, long j, Function1<? super View, Unit> listenerBlock) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(listenerBlock, "listenerBlock");
        view.setOnClickListener(new DebounceOnClickListener(j, listenerBlock));
    }

    public static final String getThemeText() {
        return (TapTapKit.INSTANCE.getContext().getResources().getConfiguration().uiMode & 48) == 32 ? SqR.color.dark : "light";
    }
}
