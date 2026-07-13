package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeEditText;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityTrumpetBindingImpl extends ActivityTrumpetBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener etandroidTextAttrChanged;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView2;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.navigation, 3);
        sparseIntArray.put(R.id.rv, 4);
    }

    public ActivityTrumpetBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private ActivityTrumpetBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ShapeEditText) bindings[1], (Navigation) bindings[3], (RecyclerView) bindings[4]);
        this.etandroidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityTrumpetBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityTrumpetBindingImpl.this.et);
                String str = ActivityTrumpetBindingImpl.this.mGame;
                ActivityTrumpetBindingImpl activityTrumpetBindingImpl = ActivityTrumpetBindingImpl.this;
                if (activityTrumpetBindingImpl != null) {
                    activityTrumpetBindingImpl.setGame(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.et.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
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
        if (54 == variableId) {
            setManager(((Boolean) variable).booleanValue());
        } else {
            if (35 != variableId) {
                return false;
            }
            setGame((String) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityTrumpetBinding
    public void setManager(boolean Manager) {
        this.mManager = Manager;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(54);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityTrumpetBinding
    public void setGame(String Game) {
        this.mGame = Game;
        synchronized (this) {
            this.mDirtyFlags |= 2;
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
        boolean z = this.mManager;
        String str = this.mGame;
        long j2 = 5 & j;
        boolean z2 = j2 != 0 ? !z : false;
        if ((6 & j) != 0) {
            TextViewBindingAdapter.setText(this.et, str);
        }
        if ((j & 4) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.et, null, null, null, this.etandroidTextAttrChanged);
        }
        if (j2 != 0) {
            DataBindingHelper.setViewGone(this.mboundView2, z2);
        }
    }
}
