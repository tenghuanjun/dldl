package com.cy.yyjia.zhe28.ui.dialog;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BasePopupWindow;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RvPopup.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0007\u001a\u00020\u00002\u000e\u0010\b\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/RvPopup;", "Lcom/cy/yyjia/zhe28/base/BasePopupWindow$Builder;", "c", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getC", "()Landroid/content/Context;", "setAdapter", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class RvPopup extends BasePopupWindow.Builder<RvPopup> {
    public static final int $stable = 8;
    private final Context c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RvPopup(Context c) {
        super(c);
        Intrinsics.checkNotNullParameter(c, "c");
        this.c = c;
        setContentView(R.layout.popup_rv);
    }

    public final Context getC() {
        return this.c;
    }

    public final RvPopup setAdapter(BaseAdapter<?, ?> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        ((RecyclerView) findViewById(R.id.rv)).setAdapter(adapter);
        return this;
    }
}
