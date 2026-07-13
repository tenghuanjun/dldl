package com.cy.yyjia.zhe28.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.RecyclerView;
import com.bytedance.framwork.core.sdklib.DBHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cy.yyjia.zhe28.base.BaseDialog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QuickDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\b\u0017\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00000\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0006\u0010\u0010\u001a\u00020\u0011J*\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u0013\u001a\u00020\b2\u000e\u0010\u0014\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0015J\u001f\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u0017\u001a\u00028\u0000¢\u0006\u0002\u0010\u0018J\"\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u001cR\u001c\u0010\n\u001a\u00028\u0001X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u001d"}, d2 = {"Lcom/cy/yyjia/zhe28/base/QuickDialog;", ExifInterface.GPS_DIRECTION_TRUE, "DB", "Landroidx/databinding/ViewDataBinding;", "Lcom/cy/yyjia/zhe28/base/BaseDialog$Builder;", "activity", "Landroid/content/Context;", "layoutId", "", "(Landroid/content/Context;I)V", "mBinding", "getMBinding", "()Landroidx/databinding/ViewDataBinding;", "setMBinding", "(Landroidx/databinding/ViewDataBinding;)V", "Landroidx/databinding/ViewDataBinding;", "hide", "", "setAdapter", "rvId", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "setData", "data", "(Ljava/lang/Object;)Lcom/cy/yyjia/zhe28/base/QuickDialog;", "setVariable", "variableId", DBHelper.COL_VALUE, "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public class QuickDialog<T, DB extends ViewDataBinding> extends BaseDialog.Builder<QuickDialog<T, DB>> {
    public static final int $stable = 8;
    private DB mBinding;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QuickDialog(Context activity, int i) {
        super(activity);
        Intrinsics.checkNotNullParameter(activity, "activity");
        DB db = (DB) DataBindingUtil.inflate(LayoutInflater.from(getContext()), i, new FrameLayout(getContext()), false);
        Intrinsics.checkNotNullExpressionValue(db, "inflate(...)");
        this.mBinding = db;
        setContentView(db.getRoot());
    }

    public final DB getMBinding() {
        return this.mBinding;
    }

    public final void setMBinding(DB db) {
        Intrinsics.checkNotNullParameter(db, "<set-?>");
        this.mBinding = db;
    }

    public final QuickDialog<T, DB> setData(T data) {
        this.mBinding.setVariable(23, data);
        return this;
    }

    public final QuickDialog<T, DB> setVariable(int variableId, Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.mBinding.setVariable(variableId, value);
        return this;
    }

    public final QuickDialog<T, DB> setAdapter(int rvId, BaseQuickAdapter<?, ?> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        ((RecyclerView) findViewById(rvId)).setAdapter(adapter);
        return this;
    }

    public final void hide() {
        dismiss();
    }
}
