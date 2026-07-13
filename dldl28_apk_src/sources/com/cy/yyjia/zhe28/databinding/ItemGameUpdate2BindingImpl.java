package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameHistoryBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemGameUpdate2BindingImpl extends ItemGameUpdate2Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ShapeView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemGameUpdate2BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private ItemGameUpdate2BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ShapeView shapeView = (ShapeView) bindings[1];
        this.mboundView1 = shapeView;
        shapeView.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[3];
        this.mboundView3 = textView2;
        textView2.setTag(null);
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
        if (116 == variableId) {
            setTop(((Boolean) variable).booleanValue());
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((GameHistoryBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemGameUpdate2Binding
    public void setTop(boolean Top) {
        this.mTop = Top;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(116);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemGameUpdate2Binding
    public void setData(GameHistoryBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        int colorFromResource;
        String context;
        String time;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        boolean z = this.mTop;
        GameHistoryBean gameHistoryBean = this.mData;
        long j2 = j & 5;
        if (j2 != 0) {
            if (j2 != 0) {
                j |= z ? 16L : 8L;
            }
            colorFromResource = getColorFromResource(this.mboundView2, z ? R.color.colorPrimary : R.color.color_text_3);
        } else {
            colorFromResource = 0;
        }
        long j3 = 6 & j;
        if (j3 == 0 || gameHistoryBean == null) {
            context = null;
            time = null;
        } else {
            context = gameHistoryBean.getContext();
            time = gameHistoryBean.getTime();
        }
        if ((j & 5) != 0) {
            DataBindingHelper.setSelected(this.mboundView1, z);
            this.mboundView2.setTextColor(colorFromResource);
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, time);
            TextViewBindingAdapter.setText(this.mboundView3, context);
        }
    }
}
