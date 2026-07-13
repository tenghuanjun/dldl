package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentSanbao648BindingImpl extends FragmentSanbao648Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mOnClickOnClickAndroidViewViewOnClickListener;
    private final LinearLayout mboundView0;
    private final LinearLayout mboundView4;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tv_rule, 9);
        sparseIntArray.put(R.id.vf, 10);
        sparseIntArray.put(R.id.ll, 11);
    }

    public FragmentSanbao648BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    private FragmentSanbao648BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (LinearLayout) bindings[11], (RecyclerView) bindings[7], (RecyclerView) bindings[8], (RecyclerView) bindings[3], (TextView) bindings[1], (TextView) bindings[2], (ShapeTextView) bindings[5], (ShapeTextView) bindings[6], (ShapeTextView) bindings[9], (AdapterViewFlipper) bindings[10]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[4];
        this.mboundView4 = linearLayout2;
        linearLayout2.setTag(null);
        this.rvGame.setTag(null);
        this.rvMy.setTag(null);
        this.rvType.setTag(null);
        this.tv1.setTag(null);
        this.tv2.setTag(null);
        this.tvCoupon.setTag(null);
        this.tvGift.setTag(null);
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
        if (36 == variableId) {
            setGift(((Boolean) variable).booleanValue());
        } else if (74 == variableId) {
            setPosition(((Integer) variable).intValue());
        } else {
            if (68 != variableId) {
                return false;
            }
            setOnClick((View.OnClickListener) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentSanbao648Binding
    public void setGift(boolean Gift) {
        this.mGift = Gift;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(36);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentSanbao648Binding
    public void setPosition(int Position) {
        this.mPosition = Position;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(74);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentSanbao648Binding
    public void setOnClick(View.OnClickListener OnClick) {
        this.mOnClick = OnClick;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(68);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        OnClickListenerImpl value;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        boolean z2 = this.mGift;
        int i = this.mPosition;
        View.OnClickListener onClickListener = this.mOnClick;
        long j2 = 9 & j;
        boolean z3 = j2 != 0 ? !z2 : false;
        long j3 = 10 & j;
        if (j3 != 0) {
            boolean z4 = i != 1;
            z = i != 0;
            z = z4;
        } else {
            z = false;
        }
        long j4 = j & 12;
        if (j4 == 0 || onClickListener == null) {
            value = null;
        } else {
            OnClickListenerImpl onClickListenerImpl = this.mOnClickOnClickAndroidViewViewOnClickListener;
            if (onClickListenerImpl == null) {
                onClickListenerImpl = new OnClickListenerImpl();
                this.mOnClickOnClickAndroidViewViewOnClickListener = onClickListenerImpl;
            }
            value = onClickListenerImpl.setValue(onClickListener);
        }
        if (j3 != 0) {
            DataBindingHelper.setViewGone(this.mboundView4, z);
            DataBindingHelper.setViewGone(this.rvGame, z);
            DataBindingHelper.setViewGone(this.rvMy, z);
            DataBindingHelper.setViewGone(this.rvType, z);
        }
        if (j4 != 0) {
            this.tv1.setOnClickListener(value);
            this.tv2.setOnClickListener(value);
            this.tvCoupon.setOnClickListener(value);
            this.tvGift.setOnClickListener(value);
        }
        if (j2 != 0) {
            DataBindingHelper.setSelected(this.tvCoupon, z3);
            DataBindingHelper.setSelected(this.tvGift, z2);
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
