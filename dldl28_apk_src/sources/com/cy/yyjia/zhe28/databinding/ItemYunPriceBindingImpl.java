package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.YunPrice;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemYunPriceBindingImpl extends ItemYunPriceBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final ShapeTextView mboundView4;

    public ItemYunPriceBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private ItemYunPriceBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[2];
        this.mboundView2 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[3];
        this.mboundView3 = textView3;
        textView3.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[4];
        this.mboundView4 = shapeTextView;
        shapeTextView.setTag(null);
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
        setData((YunPrice) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemYunPriceBinding
    public void setData(YunPrice Data) {
        updateRegistration(0, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeData((YunPrice) object, fieldId);
    }

    private boolean onChangeData(YunPrice Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        if (fieldId != 94) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        String priceStr;
        int colorFromResource;
        int colorFromResource2;
        int colorFromResource3;
        String name;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        YunPrice yunPrice = this.mData;
        long j2 = j & 7;
        String desc = null;
        if (j2 != 0) {
            if ((j & 5) == 0 || yunPrice == null) {
                priceStr = null;
                name = null;
            } else {
                desc = yunPrice.getDesc();
                priceStr = yunPrice.getPriceStr();
                name = yunPrice.getName();
            }
            selected = yunPrice != null ? yunPrice.getSelected() : false;
            if (j2 != 0) {
                j |= selected ? 336L : 168L;
            }
            TextView textView = this.mboundView3;
            colorFromResource3 = selected ? getColorFromResource(textView, R.color.colorWhite) : getColorFromResource(textView, R.color.color_text_2);
            TextView textView2 = this.mboundView1;
            colorFromResource2 = selected ? getColorFromResource(textView2, R.color.colorWhite) : getColorFromResource(textView2, R.color.color_text_2);
            String str2 = name;
            colorFromResource = selected ? getColorFromResource(this.mboundView2, R.color.colorWhite) : getColorFromResource(this.mboundView2, R.color.color_text_2);
            str = desc;
            desc = str2;
        } else {
            str = null;
            priceStr = null;
            colorFromResource = 0;
            colorFromResource2 = 0;
            colorFromResource3 = 0;
        }
        if ((7 & j) != 0) {
            DataBindingHelper.setSelected(this.mboundView0, selected);
            this.mboundView1.setTextColor(colorFromResource2);
            this.mboundView2.setTextColor(colorFromResource);
            this.mboundView3.setTextColor(colorFromResource3);
        }
        if ((j & 5) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, desc);
            TextViewBindingAdapter.setText(this.mboundView3, priceStr);
            TextViewBindingAdapter.setText(this.mboundView4, str);
        }
    }
}
