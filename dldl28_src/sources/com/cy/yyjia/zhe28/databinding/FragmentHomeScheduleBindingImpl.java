package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentHomeScheduleBindingImpl extends FragmentHomeScheduleBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.ll, 2);
        sparseIntArray.put(R.id.f438tv, 3);
        sparseIntArray.put(R.id.rv_time, 4);
        sparseIntArray.put(R.id.srl, 5);
        sparseIntArray.put(R.id.rv, 6);
    }

    public FragmentHomeScheduleBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private FragmentHomeScheduleBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (LinearLayout) bindings[2], (RecyclerView) bindings[6], (RecyclerView) bindings[4], (SmartRefreshLayout) bindings[5], (TextView) bindings[3]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
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
        if (68 == variableId) {
            setOnClick((View.OnClickListener) variable);
        } else {
            if (112 != variableId) {
                return false;
            }
            setTime((String) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentHomeScheduleBinding
    public void setOnClick(View.OnClickListener OnClick) {
        this.mOnClick = OnClick;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentHomeScheduleBinding
    public void setTime(String Time) {
        this.mTime = Time;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(112);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str = this.mTime;
        if ((j & 6) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, str);
        }
    }
}
