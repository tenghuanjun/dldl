package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityBbsSearchBindingImpl extends ActivityBbsSearchBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener etandroidTextAttrChanged;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.rv, 4);
    }

    public ActivityBbsSearchBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private ActivityBbsSearchBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (TextView) bindings[3], (EditText) bindings[1], (ImageView) bindings[2], (RecyclerView) bindings[4]);
        this.etandroidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityBbsSearchBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityBbsSearchBindingImpl.this.et);
                String str = ActivityBbsSearchBindingImpl.this.mText;
                ActivityBbsSearchBindingImpl activityBbsSearchBindingImpl = ActivityBbsSearchBindingImpl.this;
                if (activityBbsSearchBindingImpl != null) {
                    activityBbsSearchBindingImpl.setText(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.btn.setTag(null);
        this.et.setTag(null);
        this.iv.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
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
        if (111 != variableId) {
            return false;
        }
        setText((String) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityBbsSearchBinding
    public void setText(String Text) {
        this.mText = Text;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(111);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str = this.mText;
        long j2 = 3 & j;
        if (j2 != 0) {
            int length = str != null ? str.length() : 0;
            boolean z2 = length != 0;
            z = length == 0;
            z = z2;
        } else {
            z = false;
        }
        if (j2 != 0) {
            this.btn.setEnabled(z);
            TextViewBindingAdapter.setText(this.et, str);
            DataBindingHelper.setViewGone(this.iv, z);
        }
        if ((j & 2) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.et, null, null, null, this.etandroidTextAttrChanged);
        }
    }
}
