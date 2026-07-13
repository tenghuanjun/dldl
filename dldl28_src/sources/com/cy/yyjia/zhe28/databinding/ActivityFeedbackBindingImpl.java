package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.layout.ShapeRelativeLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityFeedbackBindingImpl extends ActivityFeedbackBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final EditText mboundView6;
    private InverseBindingListener mboundView6androidTextAttrChanged;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.navigation, 7);
        sparseIntArray.put(R.id.rv_type, 8);
        sparseIntArray.put(R.id.re_tag3, 9);
        sparseIntArray.put(R.id.rv_pic, 10);
    }

    public ActivityFeedbackBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    private ActivityFeedbackBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (Navigation) bindings[7], (ShapeRelativeLayout) bindings[9], (RecyclerView) bindings[10], (GridLayout) bindings[8], (ShapeTextView) bindings[1], (ShapeTextView) bindings[2], (ShapeTextView) bindings[3], (ShapeTextView) bindings[4], (ShapeTextView) bindings[5]);
        this.mboundView6androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityFeedbackBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityFeedbackBindingImpl.this.mboundView6);
                String str = ActivityFeedbackBindingImpl.this.mDesc;
                ActivityFeedbackBindingImpl activityFeedbackBindingImpl = ActivityFeedbackBindingImpl.this;
                if (activityFeedbackBindingImpl != null) {
                    activityFeedbackBindingImpl.setDesc(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        EditText editText = (EditText) bindings[6];
        this.mboundView6 = editText;
        editText.setTag(null);
        this.tv1.setTag(null);
        this.tv2.setTag(null);
        this.tv3.setTag(null);
        this.tv4.setTag(null);
        this.tv5.setTag(null);
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
        if (25 == variableId) {
            setDesc((String) variable);
        } else {
            if (118 != variableId) {
                return false;
            }
            setType(((Integer) variable).intValue());
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityFeedbackBinding
    public void setDesc(String Desc) {
        this.mDesc = Desc;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityFeedbackBinding
    public void setType(int Type) {
        this.mType = Type;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(118);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str = this.mDesc;
        int i = this.mType;
        long j2 = 5 & j;
        long j3 = 6 & j;
        if (j3 != 0) {
            z2 = i == 3;
            z3 = i == 4;
            z4 = i == 5;
            boolean z5 = i == 1;
            z = i == 2;
            z = z5;
        } else {
            z = false;
            z2 = false;
            z3 = false;
            z4 = false;
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView6, str);
        }
        if ((j & 4) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.mboundView6, null, null, null, this.mboundView6androidTextAttrChanged);
        }
        if (j3 != 0) {
            DataBindingHelper.setSelected(this.tv1, z);
            DataBindingHelper.setSelected(this.tv2, z);
            DataBindingHelper.setSelected(this.tv3, z2);
            DataBindingHelper.setSelected(this.tv4, z3);
            DataBindingHelper.setSelected(this.tv5, z4);
        }
    }
}
