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
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityRecycleBindingImpl extends ActivityRecycleBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final LinearLayout mboundView2;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.iv_record, 5);
        sparseIntArray.put(R.id.rv, 6);
    }

    public ActivityRecycleBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private ActivityRecycleBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[5], (Navigation) bindings[1], (RecyclerView) bindings[6], (TextView) bindings[4], (ShapeTextView) bindings[3]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[2];
        this.mboundView2 = linearLayout2;
        linearLayout2.setTag(null);
        this.navigation.setTag(null);
        this.tvRecycle.setTag(null);
        this.tvSearch.setTag(null);
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
            if (103 != variableId) {
                return false;
            }
            setStep(((Integer) variable).intValue());
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityRecycleBinding
    public void setName(String Name) {
        this.mName = Name;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(61);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityRecycleBinding
    public void setStep(int Step) {
        this.mStep = Step;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(103);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        String str2;
        String str3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str4 = this.mName;
        int i = this.mStep;
        long j2 = j & 6;
        if (j2 != 0) {
            boolean z = i != 0;
            z = i == 0;
            if (j2 != 0) {
                j |= z ? 336L : 168L;
            }
            str = z ? "回收" : "回收确认";
            str2 = z ? "规则" : "";
            String str5 = z ? "回收" : "下一步";
            z = z;
            str3 = str5;
        } else {
            str = null;
            str2 = null;
            str3 = null;
        }
        if ((6 & j) != 0) {
            DataBindingHelper.setViewGone(this.mboundView2, z);
            this.navigation.setMoreText(str2);
            this.navigation.setTitle(str);
            TextViewBindingAdapter.setText(this.tvRecycle, str3);
        }
        if ((j & 5) != 0) {
            TextViewBindingAdapter.setText(this.tvSearch, str4);
        }
    }
}
