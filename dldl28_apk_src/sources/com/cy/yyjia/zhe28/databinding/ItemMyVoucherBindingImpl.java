package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.VoucherBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ItemMyVoucherBindingImpl extends ItemMyVoucherBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;

    public ItemMyVoucherBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private ItemMyVoucherBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[3];
        this.mboundView3 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[4];
        this.mboundView4 = textView3;
        textView3.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (23 != variableId) {
            return false;
        }
        setData((VoucherBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemMyVoucherBinding
    public void setData(VoucherBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeDataGame((GameBean) object, fieldId);
    }

    private boolean onChangeDataGame(GameBean DataGame, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        String name;
        boolean z;
        int colorFromResource;
        int colorFromResource2;
        String icon;
        int status;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        VoucherBean voucherBean = this.mData;
        String coupon = null;
        if ((j & 7) != 0) {
            GameBean game = voucherBean != null ? voucherBean.getGame() : null;
            updateRegistration(0, game);
            if (game != null) {
                name = game.getName();
                icon = game.getIcon();
            } else {
                icon = null;
                name = null;
            }
            long j2 = j & 6;
            if (j2 != 0) {
                if (voucherBean != null) {
                    coupon = voucherBean.getCoupon();
                    status = voucherBean.getStatus();
                } else {
                    status = 0;
                }
                i = status == 0 ? 1 : 0;
                if (j2 != 0) {
                    j |= i != 0 ? 336L : 168L;
                }
                TextView textView = this.mboundView3;
                colorFromResource2 = i != 0 ? getColorFromResource(textView, R.color.color_text_3) : getColorFromResource(textView, R.color.color_text_1);
                int colorFromResource3 = i != 0 ? getColorFromResource(this.mboundView2, R.color.color_text_3) : getColorFromResource(this.mboundView2, R.color.color_text_1);
                TextView textView2 = this.mboundView4;
                colorFromResource = i != 0 ? getColorFromResource(textView2, R.color.color_text_3) : getColorFromResource(textView2, R.color.colorPrimary);
                int i = i;
                i = colorFromResource3;
                str = coupon;
                coupon = icon;
                z = i;
            } else {
                str = null;
                colorFromResource = 0;
                colorFromResource2 = 0;
                coupon = icon;
                z = 0;
            }
        } else {
            str = null;
            name = null;
            z = 0;
            colorFromResource = 0;
            colorFromResource2 = 0;
        }
        if ((7 & j) != 0) {
            DataBindingHelper.setGameIcon(this.mboundView1, coupon);
            TextViewBindingAdapter.setText(this.mboundView2, name);
        }
        if ((j & 6) != 0) {
            this.mboundView2.setTextColor(i);
            DataBindingHelper.setSelected(this.mboundView3, z);
            this.mboundView3.setTextColor(colorFromResource2);
            TextViewBindingAdapter.setText(this.mboundView4, str);
            this.mboundView4.setTextColor(colorFromResource);
        }
    }
}
