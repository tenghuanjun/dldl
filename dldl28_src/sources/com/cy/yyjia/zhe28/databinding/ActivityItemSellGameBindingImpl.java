package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeEditText;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityItemSellGameBindingImpl extends ActivityItemSellGameBinding {
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
        sparseIntArray.put(R.id.navigation, 2);
        sparseIntArray.put(R.id.rv, 3);
    }

    public ActivityItemSellGameBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private ActivityItemSellGameBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ShapeEditText) bindings[1], (Navigation) bindings[2], (RecyclerView) bindings[3]);
        this.etandroidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityItemSellGameBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityItemSellGameBindingImpl.this.et);
                String str = ActivityItemSellGameBindingImpl.this.mText;
                ActivityItemSellGameBindingImpl activityItemSellGameBindingImpl = ActivityItemSellGameBindingImpl.this;
                if (activityItemSellGameBindingImpl != null) {
                    activityItemSellGameBindingImpl.setText(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.et.setTag(null);
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

    @Override // com.cy.yyjia.zhe28.databinding.ActivityItemSellGameBinding
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
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str = this.mText;
        if ((3 & j) != 0) {
            TextViewBindingAdapter.setText(this.et, str);
        }
        if ((j & 2) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.et, null, null, null, this.etandroidTextAttrChanged);
        }
    }
}
