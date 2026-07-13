package com.cy.yyjia.zhe28.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.exifinterface.media.ExifInterface;
import com.bigkoo.convenientbanner.holder.Holder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseBannerHolder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B\u000f\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J%\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u00028\u0001X\u0086.¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/cy/yyjia/zhe28/base/BaseBannerHolder;", ExifInterface.GPS_DIRECTION_TRUE, "DB", "Landroidx/databinding/ViewDataBinding;", "Lcom/bigkoo/convenientbanner/holder/Holder;", "layoutId", "", "(I)V", "getLayoutId", "()I", "mBinding", "getMBinding", "()Landroidx/databinding/ViewDataBinding;", "setMBinding", "(Landroidx/databinding/ViewDataBinding;)V", "Landroidx/databinding/ViewDataBinding;", "UpdateUI", "", "context", "Landroid/content/Context;", "p1", "data", "(Landroid/content/Context;ILjava/lang/Object;)V", "createView", "Landroid/view/View;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public class BaseBannerHolder<T, DB extends ViewDataBinding> implements Holder<T> {
    public static final int $stable = 8;
    private final int layoutId;
    public DB mBinding;

    public BaseBannerHolder(int i) {
        this.layoutId = i;
    }

    public final int getLayoutId() {
        return this.layoutId;
    }

    public final DB getMBinding() {
        DB db = this.mBinding;
        if (db != null) {
            return db;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mBinding");
        return null;
    }

    public final void setMBinding(DB db) {
        Intrinsics.checkNotNullParameter(db, "<set-?>");
        this.mBinding = db;
    }

    @Override // com.bigkoo.convenientbanner.holder.Holder
    public View createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(LayoutInflater.from(context), this.layoutId, null, false);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(...)");
        setMBinding(viewDataBindingInflate);
        View root = getMBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // com.bigkoo.convenientbanner.holder.Holder
    public void UpdateUI(Context context, int p1, T data) {
        Intrinsics.checkNotNullParameter(context, "context");
        getMBinding().setVariable(23, data);
    }
}
