package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeEditText;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityAuthBindingImpl extends ActivityAuthBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ShapeEditText mboundView1;
    private InverseBindingListener mboundView1androidTextAttrChanged;
    private final ShapeEditText mboundView2;
    private InverseBindingListener mboundView2androidTextAttrChanged;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.re_tag, 3);
        sparseIntArray.put(R.id.tv_tag1, 4);
        sparseIntArray.put(R.id.lin_tag1, 5);
        sparseIntArray.put(R.id.lin_tag2, 6);
        sparseIntArray.put(R.id.tv_tag2, 7);
        sparseIntArray.put(R.id.re_tag1, 8);
        sparseIntArray.put(R.id.tv_go, 9);
    }

    public ActivityAuthBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private ActivityAuthBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ShapeLinearLayout) bindings[5], (ShapeLinearLayout) bindings[6], (Navigation) bindings[3], (RelativeLayout) bindings[8], (ShapeTextView) bindings[9], (TextView) bindings[4], (TextView) bindings[7]);
        this.mboundView1androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityAuthBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityAuthBindingImpl.this.mboundView1);
                String str = ActivityAuthBindingImpl.this.mName;
                ActivityAuthBindingImpl activityAuthBindingImpl = ActivityAuthBindingImpl.this;
                if (activityAuthBindingImpl != null) {
                    activityAuthBindingImpl.setName(textString);
                }
            }
        };
        this.mboundView2androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityAuthBindingImpl.2
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityAuthBindingImpl.this.mboundView2);
                String str = ActivityAuthBindingImpl.this.mCode;
                ActivityAuthBindingImpl activityAuthBindingImpl = ActivityAuthBindingImpl.this;
                if (activityAuthBindingImpl != null) {
                    activityAuthBindingImpl.setCode(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        ShapeEditText shapeEditText = (ShapeEditText) bindings[1];
        this.mboundView1 = shapeEditText;
        shapeEditText.setTag(null);
        ShapeEditText shapeEditText2 = (ShapeEditText) bindings[2];
        this.mboundView2 = shapeEditText2;
        shapeEditText2.setTag(null);
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
        if (61 == variableId) {
            setName((String) variable);
        } else {
            if (14 != variableId) {
                return false;
            }
            setCode((String) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityAuthBinding
    public void setName(String Name) {
        this.mName = Name;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(61);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityAuthBinding
    public void setCode(String Code) {
        this.mCode = Code;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(14);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str = this.mName;
        String str2 = this.mCode;
        long j2 = 6 & j;
        if ((5 & j) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, str);
        }
        if ((j & 4) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.mboundView1, null, null, null, this.mboundView1androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView2, null, null, null, this.mboundView2androidTextAttrChanged);
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, str2);
        }
    }
}
