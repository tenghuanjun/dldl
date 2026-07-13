package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.FudaiIndexBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.view.Navigation;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityFudaiIndexBindingImpl extends ActivityFudaiIndexBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final NestedScrollView mboundView0;
    private final TextView mboundView2;
    private final ImageView mboundView3;
    private final TextView mboundView5;
    private final TextView mboundView6;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.ll_record, 8);
        sparseIntArray.put(R.id.f438tv, 9);
        sparseIntArray.put(R.id.iv_add, 10);
        sparseIntArray.put(R.id.tv_time, 11);
    }

    public ActivityFudaiIndexBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    private ActivityFudaiIndexBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[10], (LinearLayout) bindings[8], (Navigation) bindings[1], (RecyclerView) bindings[7], (TextView) bindings[9], (TextView) bindings[4], (TextView) bindings[11]);
        this.mDirtyFlags = -1L;
        NestedScrollView nestedScrollView = (NestedScrollView) bindings[0];
        this.mboundView0 = nestedScrollView;
        nestedScrollView.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        ImageView imageView = (ImageView) bindings[3];
        this.mboundView3 = imageView;
        imageView.setTag(null);
        TextView textView2 = (TextView) bindings[5];
        this.mboundView5 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[6];
        this.mboundView6 = textView3;
        textView3.setTag(null);
        this.navigation.setTag(null);
        this.rv.setTag(null);
        this.tvCountdown.setTag(null);
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
        if (23 != variableId) {
            return false;
        }
        setData((FudaiIndexBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityFudaiIndexBinding
    public void setData(FudaiIndexBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        String str2;
        List<FudaiIndexBean.List> list;
        String maxPrizeMsg;
        boolean z;
        boolean z2;
        int total;
        int iIsAdd;
        int isfull;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        FudaiIndexBean fudaiIndexBean = this.mData;
        long j2 = j & 3;
        String desc = null;
        if (j2 != 0) {
            if (fudaiIndexBean != null) {
                desc = fudaiIndexBean.getDesc();
                total = fudaiIndexBean.getTotal();
                list = fudaiIndexBean.getList();
                iIsAdd = fudaiIndexBean.isAdd();
                maxPrizeMsg = fudaiIndexBean.getMaxPrizeMsg();
                isfull = fudaiIndexBean.getIsfull();
            } else {
                list = null;
                maxPrizeMsg = null;
                total = 0;
                iIsAdd = 0;
                isfull = 0;
            }
            boolean z3 = fudaiIndexBean == null;
            if (j2 != 0) {
                j |= z3 ? 8L : 4L;
            }
            String str3 = "共" + total;
            z = iIsAdd != 1;
            z2 = isfull != 1;
            str = z3 ? "" : "规则";
            str2 = str3 + "份";
        } else {
            str = null;
            str2 = null;
            list = null;
            maxPrizeMsg = null;
            z = false;
            z2 = false;
        }
        if ((j & 3) != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, desc);
            DataBindingHelper.setViewGone(this.mboundView3, z2);
            TextViewBindingAdapter.setText(this.mboundView5, str2);
            TextViewBindingAdapter.setText(this.mboundView6, maxPrizeMsg);
            this.navigation.setMoreText(str);
            DataBindingHelper.setRvData(this.rv, list);
            DataBindingHelper.setViewGone(this.tvCountdown, z);
        }
    }
}
