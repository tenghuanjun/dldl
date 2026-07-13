package com.cy.yyjia.zhe28.databinding;

import android.text.Editable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public class ActivitySearchBindingImpl extends ActivitySearchBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener etandroidTextAttrChanged;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final TextView mboundView2;
    private final TextView mboundView4;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.navigation, 6);
        sparseIntArray.put(R.id.ll_input, 7);
        sparseIntArray.put(R.id.et, 8);
        sparseIntArray.put(R.id.tv_search, 9);
        sparseIntArray.put(R.id.rl_history, 10);
        sparseIntArray.put(R.id.tv_clean, 11);
        sparseIntArray.put(R.id.rv_history, 12);
        sparseIntArray.put(R.id.rv, 13);
        sparseIntArray.put(R.id.ll_gone, 14);
        sparseIntArray.put(R.id.tv_empty, 15);
        sparseIntArray.put(R.id.ll_empty, 16);
        sparseIntArray.put(R.id.rv_empty, 17);
        sparseIntArray.put(R.id.tv_apply, 18);
    }

    public ActivitySearchBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds));
    }

    private ActivitySearchBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (EditText) bindings[8], (ImageView) bindings[1], (LinearLayout) bindings[16], (LinearLayout) bindings[14], (LinearLayout) bindings[7], (Navigation) bindings[6], (RelativeLayout) bindings[10], (RecyclerView) bindings[13], (RecyclerView) bindings[17], (RecyclerView) bindings[12], (RecyclerView) bindings[5], (RecyclerView) bindings[3], (TextView) bindings[18], (TextView) bindings[11], (TextView) bindings[15], (TextView) bindings[9]);
        this.etandroidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivitySearchBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                synchronized (ActivitySearchBindingImpl.this) {
                    ActivitySearchBindingImpl.this.mDirtyFlags |= 2;
                }
                ActivitySearchBindingImpl.this.requestRebind();
            }
        };
        this.mDirtyFlags = -1L;
        this.ivClear.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[4];
        this.mboundView4 = textView2;
        textView2.setTag(null);
        this.rvHot.setTag(null);
        this.rvType.setTag(null);
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
        if (89 != variableId) {
            return false;
        }
        setSearch(((Boolean) variable).booleanValue());
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivitySearchBinding
    public void setSearch(boolean Search) {
        this.mSearch = Search;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(89);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        boolean z = this.mSearch;
        long j2 = 5 & j;
        long j3 = 6 & j;
        boolean z2 = false;
        if (j3 != 0) {
            Editable text = this.et.getText();
            if ((text != null ? text.length() : 0) == 0) {
                z2 = true;
            }
        }
        if ((j & 4) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.et, null, null, null, this.etandroidTextAttrChanged);
        }
        if (j3 != 0) {
            DataBindingHelper.setViewGone(this.ivClear, z2);
        }
        if (j2 != 0) {
            DataBindingHelper.setViewGone(this.mboundView2, z);
            DataBindingHelper.setViewGone(this.mboundView4, z);
            DataBindingHelper.setViewGone(this.rvHot, z);
            DataBindingHelper.setViewGone(this.rvType, z);
        }
    }
}
