package com.cy.yyjia.zhe28.databinding;

import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.RecordBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemBillBindingImpl extends ItemBillBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final ShapeTextView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemBillBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private ItemBillBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0);
        this.mDirtyFlags = -1L;
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[1];
        this.mboundView1 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[3];
        this.mboundView3 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[4];
        this.mboundView4 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[5];
        this.mboundView5 = textView4;
        textView4.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
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
        setData((RecordBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemBillBinding
    public void setData(RecordBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean zIsEmpty;
        String showMoneyText;
        String dateline;
        String str;
        String str2;
        String gameName;
        String btnText;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        RecordBean recordBean = this.mData;
        long j2 = j & 3;
        String orderId = null;
        if (j2 != 0) {
            if (recordBean != null) {
                showMoneyText = recordBean.getShowMoneyText();
                dateline = recordBean.getDateline();
                orderId = recordBean.getOrderId();
                gameName = recordBean.getGameName();
                btnText = recordBean.getBtnText();
            } else {
                showMoneyText = null;
                dateline = null;
                gameName = null;
                btnText = null;
            }
            String str3 = "订单编号：" + orderId;
            str2 = "消费渠道：" + gameName;
            zIsEmpty = TextUtils.isEmpty(gameName);
            orderId = btnText;
            str = str3;
        } else {
            zIsEmpty = false;
            showMoneyText = null;
            dateline = null;
            str = null;
            str2 = null;
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, orderId);
            TextViewBindingAdapter.setText(this.mboundView2, showMoneyText);
            TextViewBindingAdapter.setText(this.mboundView3, dateline);
            TextViewBindingAdapter.setText(this.mboundView4, str);
            DataBindingHelper.setViewGone(this.mboundView5, zIsEmpty);
            TextViewBindingAdapter.setText(this.mboundView5, str2);
        }
    }
}
