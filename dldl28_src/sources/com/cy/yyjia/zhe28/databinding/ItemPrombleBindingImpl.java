package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.FeedbackRecordBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;
import com.hjq.shape.view.ShapeView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemPrombleBindingImpl extends ItemPrombleBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ShapeView mboundView1;
    private final TextView mboundView2;
    private final ShapeTextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.iv_tag1, 6);
        sparseIntArray.put(R.id.rv, 7);
        sparseIntArray.put(R.id.tv_delete, 8);
    }

    public ItemPrombleBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private ItemPrombleBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[6], (RecyclerView) bindings[7], (TextView) bindings[8]);
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
        ShapeTextView shapeTextView = (ShapeTextView) bindings[3];
        this.mboundView3 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView2 = (TextView) bindings[4];
        this.mboundView4 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[5];
        this.mboundView5 = textView3;
        textView3.setTag(null);
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
        setData((FeedbackRecordBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemPrombleBinding
    public void setData(FeedbackRecordBean Data) {
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
        String str;
        String str2;
        String desc;
        String timeStr;
        String typeText;
        int status;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        FeedbackRecordBean feedbackRecordBean = this.mData;
        long j2 = j & 3;
        String statusText = null;
        if (j2 != 0) {
            if (feedbackRecordBean != null) {
                statusText = feedbackRecordBean.getStatusText();
                timeStr = feedbackRecordBean.getTimeStr();
                typeText = feedbackRecordBean.getTypeText();
                status = feedbackRecordBean.getStatus();
                desc = feedbackRecordBean.getDesc();
            } else {
                timeStr = null;
                typeText = null;
                desc = null;
                status = 0;
            }
            z = status == 1;
            String str3 = typeText;
            str2 = timeStr;
            str = statusText;
            statusText = str3;
        } else {
            str = null;
            str2 = null;
            desc = null;
        }
        if (j2 != 0) {
            DataBindingHelper.setViewGone(this.mboundView1, z);
            TextViewBindingAdapter.setText(this.mboundView2, statusText);
            DataBindingHelper.setSelected(this.mboundView3, z);
            TextViewBindingAdapter.setText(this.mboundView3, str);
            TextViewBindingAdapter.setText(this.mboundView4, desc);
            TextViewBindingAdapter.setText(this.mboundView5, str2);
        }
    }
}
