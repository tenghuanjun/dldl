package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.VipListBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.layout.ShapeFrameLayout;
import com.hjq.shape.view.ShapeTextView;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityVipBindingImpl extends ActivityVipBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final TextView mboundView1;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.vp, 3);
        sparseIntArray.put(R.id.ll_right, 4);
        sparseIntArray.put(R.id.btn1, 5);
        sparseIntArray.put(R.id.btn2, 6);
        sparseIntArray.put(R.id.btn3, 7);
        sparseIntArray.put(R.id.btn4, 8);
        sparseIntArray.put(R.id.btn6, 9);
        sparseIntArray.put(R.id.btn5, 10);
        sparseIntArray.put(R.id.tv_gift, 11);
        sparseIntArray.put(R.id.rv_gift, 12);
        sparseIntArray.put(R.id.rv_fun, 13);
        sparseIntArray.put(R.id.navigation, 14);
    }

    public ActivityVipBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }

    private ActivityVipBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ShapeFrameLayout) bindings[5], (ShapeFrameLayout) bindings[6], (ShapeFrameLayout) bindings[7], (ShapeFrameLayout) bindings[8], (ShapeFrameLayout) bindings[10], (ShapeFrameLayout) bindings[9], (LinearLayout) bindings[4], (Navigation) bindings[14], (RecyclerView) bindings[13], (RecyclerView) bindings[12], (RecyclerView) bindings[2], (ShapeTextView) bindings[11], (ViewPager2) bindings[3]);
        this.mDirtyFlags = -1L;
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        this.rvRight.setTag(null);
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
        } else if (86 == variableId) {
            setRight(((Boolean) variable).booleanValue());
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((VipListBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityVipBinding
    public void setPosition(int Position) {
        this.mPosition = Position;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(74);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityVipBinding
    public void setRight(boolean Right) {
        this.mRight = Right;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityVipBinding
    public void setData(VipListBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        List<VipListBean.RightBean> privilege_list;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int i = this.mPosition;
        VipListBean vipListBean = this.mData;
        long j2 = 13 & j;
        String str = null;
        if (j2 != 0) {
            String label = ((j & 12) == 0 || vipListBean == null) ? null : vipListBean.getLabel();
            List<VipListBean.ListBean> list = vipListBean != null ? vipListBean.getList() : null;
            VipListBean.ListBean listBean = list != null ? list.get(i) : null;
            privilege_list = listBean != null ? listBean.getPrivilege_list() : null;
            str = label;
        } else {
            privilege_list = null;
        }
        if ((j & 12) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, str);
        }
        if (j2 != 0) {
            DataBindingHelper.setRvData(this.rvRight, privilege_list);
        }
    }
}
