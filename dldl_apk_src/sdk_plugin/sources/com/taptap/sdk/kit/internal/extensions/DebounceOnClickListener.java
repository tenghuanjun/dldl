package com.taptap.sdk.kit.internal.extensions;

import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UIExt.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/taptap/sdk/kit/internal/extensions/DebounceOnClickListener;", "Landroid/view/View$OnClickListener;", "interval", "", "listenerBlock", "Lkotlin/Function1;", "Landroid/view/View;", "", "(JLkotlin/jvm/functions/Function1;)V", "lastClickTime", "onClick", "v", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class DebounceOnClickListener implements View.OnClickListener {
    private final long interval;
    private long lastClickTime;
    private final Function1<View, Unit> listenerBlock;

    /* JADX WARN: Multi-variable type inference failed */
    public DebounceOnClickListener(long j, Function1<? super View, Unit> listenerBlock) {
        Intrinsics.checkNotNullParameter(listenerBlock, "listenerBlock");
        this.interval = j;
        this.listenerBlock = listenerBlock;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.lastClickTime >= this.interval) {
            this.lastClickTime = jCurrentTimeMillis;
            this.listenerBlock.invoke(v);
        }
    }
}
