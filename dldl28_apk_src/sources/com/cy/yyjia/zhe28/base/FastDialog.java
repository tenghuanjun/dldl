package com.cy.yyjia.zhe28.base;

import android.content.Context;
import com.cy.yyjia.zhe28.base.BaseDialog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FastDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/cy/yyjia/zhe28/base/FastDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDialog$Builder;", "activity", "Landroid/content/Context;", "(Landroid/content/Context;)V", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class FastDialog extends BaseDialog.Builder<FastDialog> {
    public static final int $stable = 0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FastDialog(Context activity) {
        super(activity);
        Intrinsics.checkNotNullParameter(activity, "activity");
    }
}
