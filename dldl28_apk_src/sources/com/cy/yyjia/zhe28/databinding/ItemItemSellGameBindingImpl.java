package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemItemSellGameBindingImpl extends ItemItemSellGameBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LayoutGameIconBinding mboundView0;
    private final LinearLayout mboundView01;
    private final ShapeTextView mboundView1;
    private final TextView mboundView2;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(5);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"layout_game_icon"}, new int[]{3}, new int[]{R.layout.layout_game_icon});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.name, 4);
    }

    public ItemItemSellGameBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private ItemItemSellGameBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (LinearLayout) bindings[4]);
        this.mDirtyFlags = -1L;
        LayoutGameIconBinding layoutGameIconBinding = (LayoutGameIconBinding) bindings[3];
        this.mboundView0 = layoutGameIconBinding;
        setContainedBinding(layoutGameIconBinding);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView01 = linearLayout;
        linearLayout.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[1];
        this.mboundView1 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
        }
        this.mboundView0.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.mboundView0.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (23 != variableId) {
            return false;
        }
        setData((GameBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemItemSellGameBinding
    public void setData(GameBean Data) {
        updateRegistration(0, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.mboundView0.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeData((GameBean) object, fieldId);
    }

    private boolean onChangeData(GameBean Data, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String name_suffix;
        String showName;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        GameBean gameBean = this.mData;
        long j2 = 3 & j;
        if (j2 == 0 || gameBean == null) {
            name_suffix = null;
            showName = null;
        } else {
            name_suffix = gameBean.getName_suffix();
            showName = gameBean.getShowName();
        }
        if (j2 != 0) {
            this.mboundView0.setData(gameBean);
            TextViewBindingAdapter.setText(this.mboundView1, name_suffix);
            TextViewBindingAdapter.setText(this.mboundView2, showName);
        }
        if ((j & 2) != 0) {
            DataBindingHelper.setSelected(this.mboundView2, true);
        }
        executeBindingsOn(this.mboundView0);
    }
}
