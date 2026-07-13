package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.view.ShapeTextView;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentHomeSchedule2BindingImpl extends FragmentHomeSchedule2Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mOnClickOnClickAndroidViewViewOnClickListener;
    private final LinearLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.rv_type, 4);
        sparseIntArray.put(R.id.srl, 5);
        sparseIntArray.put(R.id.rv, 6);
    }

    public FragmentHomeSchedule2BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private FragmentHomeSchedule2BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (RecyclerView) bindings[6], (RecyclerView) bindings[4], (SmartRefreshLayout) bindings[5], (ShapeTextView) bindings[3], (ShapeTextView) bindings[1], (ShapeTextView) bindings[2]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        this.tvScreen.setTag(null);
        this.tvTime.setTag(null);
        this.tvType.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
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
        if (74 == variableId) {
            setPosition(((Integer) variable).intValue());
        } else if (68 == variableId) {
            setOnClick((View.OnClickListener) variable);
        } else {
            if (112 != variableId) {
                return false;
            }
            setTime((String) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentHomeSchedule2Binding
    public void setPosition(int Position) {
        this.mPosition = Position;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentHomeSchedule2Binding
    public void setOnClick(View.OnClickListener OnClick) {
        this.mOnClick = OnClick;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(68);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentHomeSchedule2Binding
    public void setTime(String Time) {
        this.mTime = Time;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        OnClickListenerImpl value;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        View.OnClickListener onClickListener = this.mOnClick;
        long j2 = j & 10;
        if (j2 == 0 || onClickListener == null) {
            value = null;
        } else {
            OnClickListenerImpl onClickListenerImpl = this.mOnClickOnClickAndroidViewViewOnClickListener;
            if (onClickListenerImpl == null) {
                onClickListenerImpl = new OnClickListenerImpl();
                this.mOnClickOnClickAndroidViewViewOnClickListener = onClickListenerImpl;
            }
            value = onClickListenerImpl.setValue(onClickListener);
        }
        if (j2 != 0) {
            this.tvScreen.setOnClickListener(value);
            this.tvTime.setOnClickListener(value);
            this.tvType.setOnClickListener(value);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private View.OnClickListener value;

        public OnClickListenerImpl setValue(View.OnClickListener value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.onClick(arg0);
        }
    }
}
