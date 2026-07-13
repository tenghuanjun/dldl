package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.domain.CardInfoBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemCardPriceBindingImpl extends ItemCardPriceBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final ShapeTextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final ImageView mboundView5;
    private final ShapeTextView mboundView6;
    private final ShapeTextView mboundView7;

    public ItemCardPriceBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private ItemCardPriceBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (LinearLayout) bindings[1]);
        this.mDirtyFlags = -1L;
        this.ll1.setTag(null);
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[2];
        this.mboundView2 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView = (TextView) bindings[3];
        this.mboundView3 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[4];
        this.mboundView4 = textView2;
        textView2.setTag(null);
        ImageView imageView = (ImageView) bindings[5];
        this.mboundView5 = imageView;
        imageView.setTag(null);
        ShapeTextView shapeTextView2 = (ShapeTextView) bindings[6];
        this.mboundView6 = shapeTextView2;
        shapeTextView2.setTag(null);
        ShapeTextView shapeTextView3 = (ShapeTextView) bindings[7];
        this.mboundView7 = shapeTextView3;
        shapeTextView3.setTag(null);
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
        setData((CardInfoBean.Price) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemCardPriceBinding
    public void setData(CardInfoBean.Price Data) {
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
        return onChangeData((CardInfoBean.Price) object, fieldId);
    }

    private boolean onChangeData(CardInfoBean.Price Data, int fieldId) {
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

    /* JADX WARN: Removed duplicated region for block: B:35:0x0097  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ItemCardPriceBindingImpl.executeBindings():void");
    }
}
