package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GiftBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ItemSanbao648GiftBindingImpl extends ItemSanbao648GiftBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.btn, 6);
    }

    public ItemSanbao648GiftBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private ItemSanbao648GiftBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (TextView) bindings[6]);
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
        TextView textView4 = (TextView) bindings[4];
        this.mboundView4 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[5];
        this.mboundView5 = textView5;
        textView5.setTag(null);
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
        setData((GiftBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemSanbao648GiftBinding
    public void setData(GiftBean Data) {
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
        return onChangeData((GiftBean) object, fieldId);
    }

    private boolean onChangeData(GiftBean Data, int fieldId) {
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
        String str2;
        String str3;
        String content_text;
        boolean z;
        String gift_name;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        GiftBean giftBean = this.mData;
        long j2 = j & 7;
        String str4 = null;
        if (j2 != 0) {
            selected = giftBean != null ? giftBean.getSelected() : false;
            if (j2 != 0) {
                j |= selected ? 16L : 8L;
            }
            z = !selected;
            str3 = selected ? "收起" : "详情";
            if ((j & 5) != 0) {
                if (giftBean != null) {
                    String times = giftBean.getTimes();
                    String gift_code = giftBean.getGift_code();
                    content_text = giftBean.getContent_text();
                    gift_name = giftBean.getGift_name();
                    str = times;
                    str4 = gift_code;
                } else {
                    str = null;
                    gift_name = null;
                    content_text = null;
                }
                str2 = "礼包码 " + str4;
                str4 = gift_name;
            } else {
                str = null;
                str2 = null;
                content_text = null;
            }
        } else {
            str = null;
            str2 = null;
            str3 = null;
            content_text = null;
            z = false;
        }
        if ((5 & j) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, str4);
            TextViewBindingAdapter.setText(this.mboundView2, str);
            TextViewBindingAdapter.setText(this.mboundView3, str2);
            TextViewBindingAdapter.setText(this.mboundView5, content_text);
        }
        if ((j & 7) != 0) {
            DataBindingHelper.setSelected(this.mboundView4, selected);
            TextViewBindingAdapter.setText(this.mboundView4, str3);
            DataBindingHelper.setViewGone(this.mboundView5, z);
        }
    }
}
