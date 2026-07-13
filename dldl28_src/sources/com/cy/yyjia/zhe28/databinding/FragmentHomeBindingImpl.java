package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GroupBuyBean;
import com.cy.yyjia.zhe28.domain.HomeBean;
import com.cy.yyjia.zhe28.view.RecyclerViewScrollBar;
import com.google.android.material.appbar.AppBarLayout;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.layout.ShapeRelativeLayout;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentHomeBindingImpl extends FragmentHomeBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final CoordinatorLayout mboundView0;
    private final ShapeRelativeLayout mboundView10;
    private final TextView mboundView11;
    private final TextView mboundView12;
    private final ShapeRelativeLayout mboundView14;
    private final RecyclerViewScrollBar mboundView6;
    private final LinearLayout mboundView7;
    private final TextView mboundView8;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.abl, 17);
        sparseIntArray.put(R.id.ll_search, 18);
        sparseIntArray.put(R.id.tv_search, 19);
        sparseIntArray.put(R.id.vf, 20);
        sparseIntArray.put(R.id.tv_message, 21);
        sparseIntArray.put(R.id.tv_qiandao, 22);
        sparseIntArray.put(R.id.nsv, 23);
        sparseIntArray.put(R.id.rv_3, 24);
        sparseIntArray.put(R.id.rv_game, 25);
    }

    public FragmentHomeBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 26, sIncludes, sViewsWithIds));
    }

    private FragmentHomeBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (AppBarLayout) bindings[17], (ConvenientBanner) bindings[3], (LinearLayout) bindings[18], (NestedScrollView) bindings[23], (RecyclerView) bindings[1], (RecyclerView) bindings[9], (RecyclerView) bindings[24], (RecyclerView) bindings[5], (RecyclerView) bindings[25], (RecyclerView) bindings[15], (RecyclerView) bindings[13], (SmartRefreshLayout) bindings[2], (SmartRefreshLayout) bindings[16], (TextView) bindings[21], (TextView) bindings[4], (ImageView) bindings[22], (ShapeLinearLayout) bindings[19], (AdapterViewFlipper) bindings[20]);
        this.mDirtyFlags = -1L;
        this.banner.setTag(null);
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) bindings[0];
        this.mboundView0 = coordinatorLayout;
        coordinatorLayout.setTag(null);
        ShapeRelativeLayout shapeRelativeLayout = (ShapeRelativeLayout) bindings[10];
        this.mboundView10 = shapeRelativeLayout;
        shapeRelativeLayout.setTag(null);
        TextView textView = (TextView) bindings[11];
        this.mboundView11 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[12];
        this.mboundView12 = textView2;
        textView2.setTag(null);
        ShapeRelativeLayout shapeRelativeLayout2 = (ShapeRelativeLayout) bindings[14];
        this.mboundView14 = shapeRelativeLayout2;
        shapeRelativeLayout2.setTag(null);
        RecyclerViewScrollBar recyclerViewScrollBar = (RecyclerViewScrollBar) bindings[6];
        this.mboundView6 = recyclerViewScrollBar;
        recyclerViewScrollBar.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[7];
        this.mboundView7 = linearLayout;
        linearLayout.setTag(null);
        TextView textView3 = (TextView) bindings[8];
        this.mboundView8 = textView3;
        textView3.setTag(null);
        this.rv.setTag(null);
        this.rv1.setTag(null);
        this.rvFun.setTag(null);
        this.rvMore.setTag(null);
        this.rvTry.setTag(null);
        this.srl.setTag(null);
        this.srl2.setTag(null);
        this.tvMonthCard.setTag(null);
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
        if (37 == variableId) {
            setGroup((GroupBuyBean) variable);
        } else if (23 == variableId) {
            setData((HomeBean) variable);
        } else {
            if (43 != variableId) {
                return false;
            }
            setIndex(((Boolean) variable).booleanValue());
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentHomeBinding
    public void setGroup(GroupBuyBean Group) {
        this.mGroup = Group;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentHomeBinding
    public void setData(HomeBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentHomeBinding
    public void setIndex(boolean Index) {
        this.mIndex = Index;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(43);
        super.requestRebind();
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0128  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 561
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.FragmentHomeBindingImpl.executeBindings():void");
    }
}
