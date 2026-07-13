package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.layout.ShapeLinearLayout;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityDealSellBindingImpl extends ActivityDealSellBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener etandroidTextAttrChanged;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.re_tag, 2);
        sparseIntArray.put(R.id.lin_tag1, 3);
        sparseIntArray.put(R.id.lin_tag2, 4);
        sparseIntArray.put(R.id.iv_record, 5);
        sparseIntArray.put(R.id.iv_intro, 6);
        sparseIntArray.put(R.id.rv, 7);
    }

    public ActivityDealSellBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private ActivityDealSellBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (EditText) bindings[1], (ImageView) bindings[6], (ImageView) bindings[5], (ShapeLinearLayout) bindings[3], (ShapeLinearLayout) bindings[4], (Navigation) bindings[2], (RecyclerView) bindings[7]);
        this.etandroidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityDealSellBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityDealSellBindingImpl.this.et);
                String str = ActivityDealSellBindingImpl.this.mGame;
                ActivityDealSellBindingImpl activityDealSellBindingImpl = ActivityDealSellBindingImpl.this;
                if (activityDealSellBindingImpl != null) {
                    activityDealSellBindingImpl.setGame(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.et.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
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
        if (35 != variableId) {
            return false;
        }
        setGame((String) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityDealSellBinding
    public void setGame(String Game) {
        this.mGame = Game;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(35);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str = this.mGame;
        if ((3 & j) != 0) {
            TextViewBindingAdapter.setText(this.et, str);
        }
        if ((j & 2) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.et, null, null, null, this.etandroidTextAttrChanged);
        }
    }
}
