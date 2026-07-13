package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.DealIndexBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityWithdrewBindingImpl extends ActivityWithdrewBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener etandroidTextAttrChanged;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final ImageView mboundView4;
    private final ImageView mboundView5;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.navigation, 6);
        sparseIntArray.put(R.id.tv_all, 7);
        sparseIntArray.put(R.id.ll_ptb, 8);
        sparseIntArray.put(R.id.f438tv, 9);
        sparseIntArray.put(R.id.ll_zfb, 10);
        sparseIntArray.put(R.id.tv_alipay, 11);
        sparseIntArray.put(R.id.btn, 12);
    }

    public ActivityWithdrewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }

    private ActivityWithdrewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ShapeTextView) bindings[12], (EditText) bindings[3], (LinearLayout) bindings[8], (LinearLayout) bindings[10], (Navigation) bindings[6], (TextView) bindings[9], (TextView) bindings[11], (TextView) bindings[7]);
        this.etandroidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityWithdrewBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityWithdrewBindingImpl.this.et);
                String str = ActivityWithdrewBindingImpl.this.mNumber;
                ActivityWithdrewBindingImpl activityWithdrewBindingImpl = ActivityWithdrewBindingImpl.this;
                if (activityWithdrewBindingImpl != null) {
                    activityWithdrewBindingImpl.setNumber(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.et.setTag(null);
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[2];
        this.mboundView2 = textView2;
        textView2.setTag(null);
        ImageView imageView = (ImageView) bindings[4];
        this.mboundView4 = imageView;
        imageView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[5];
        this.mboundView5 = imageView2;
        imageView2.setTag(null);
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
        if (64 == variableId) {
            setNumber((String) variable);
        } else if (23 == variableId) {
            setData((DealIndexBean) variable);
        } else {
            if (81 != variableId) {
                return false;
            }
            setPtb(((Boolean) variable).booleanValue());
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityWithdrewBinding
    public void setNumber(String Number) {
        this.mNumber = Number;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(64);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityWithdrewBinding
    public void setData(DealIndexBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityWithdrewBinding
    public void setPtb(boolean Ptb) {
        this.mPtb = Ptb;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(81);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String usable_money;
        String freeze_money;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str = this.mNumber;
        DealIndexBean dealIndexBean = this.mData;
        boolean z = this.mPtb;
        long j2 = 9 & j;
        long j3 = 10 & j;
        if (j3 == 0 || dealIndexBean == null) {
            usable_money = null;
            freeze_money = null;
        } else {
            freeze_money = dealIndexBean.getFreeze_money();
            usable_money = dealIndexBean.getUsable_money();
        }
        long j4 = 12 & j;
        boolean z2 = j4 != 0 ? !z : false;
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.et, str);
        }
        if ((j & 8) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.et, null, null, null, this.etandroidTextAttrChanged);
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, usable_money);
            TextViewBindingAdapter.setText(this.mboundView2, freeze_money);
        }
        if (j4 != 0) {
            DataBindingHelper.setSelected(this.mboundView4, z);
            DataBindingHelper.setSelected(this.mboundView5, z2);
        }
    }
}
