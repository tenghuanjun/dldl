package com.cy.yyjia.zhe28.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.bigkoo.convenientbanner.holder.Holder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.databinding.LayoutBannerHomeBinding;
import com.cy.yyjia.zhe28.domain.BannerBean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BannerHolder.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\rH\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Lcom/cy/yyjia/zhe28/view/BannerHolder;", "Lcom/bigkoo/convenientbanner/holder/Holder;", "Lcom/cy/yyjia/zhe28/domain/BannerBean;", "()V", "mBinding", "Lcom/cy/yyjia/zhe28/databinding/LayoutBannerHomeBinding;", "getMBinding", "()Lcom/cy/yyjia/zhe28/databinding/LayoutBannerHomeBinding;", "setMBinding", "(Lcom/cy/yyjia/zhe28/databinding/LayoutBannerHomeBinding;)V", "UpdateUI", "", "context", "Landroid/content/Context;", "p1", "", "data", "createView", "Landroid/view/View;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class BannerHolder implements Holder<BannerBean> {
    public static final int $stable = 8;
    public LayoutBannerHomeBinding mBinding;

    public final LayoutBannerHomeBinding getMBinding() {
        LayoutBannerHomeBinding layoutBannerHomeBinding = this.mBinding;
        if (layoutBannerHomeBinding != null) {
            return layoutBannerHomeBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mBinding");
        return null;
    }

    public final void setMBinding(LayoutBannerHomeBinding layoutBannerHomeBinding) {
        Intrinsics.checkNotNullParameter(layoutBannerHomeBinding, "<set-?>");
        this.mBinding = layoutBannerHomeBinding;
    }

    @Override // com.bigkoo.convenientbanner.holder.Holder
    public View createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_banner_home, null, false);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(...)");
        setMBinding((LayoutBannerHomeBinding) viewDataBindingInflate);
        View root = getMBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // com.bigkoo.convenientbanner.holder.Holder
    public void UpdateUI(Context context, int p1, BannerBean data) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        getMBinding().setData(data);
        getMBinding().ll.getShapeDrawableBuilder().setSolidGradientColors(Color.parseColor("#00000000"), Color.parseColor(data.getColor()), Color.parseColor(data.getColor())).intoBackground();
        if (data.getTag().getC().length() > 0) {
            getMBinding().tag.getShapeDrawableBuilder().setSolidColor(Color.parseColor(data.getTag().getC())).intoBackground();
        }
    }
}
