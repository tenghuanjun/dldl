package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BbsDetailBean;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentBbsBindingImpl extends FragmentBbsBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final ImageView mboundView1;
    private final LinearLayout mboundView2;
    private final ImageView mboundView4;
    private final TextView mboundView5;
    private final LinearLayout mboundView6;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.abl, 15);
        sparseIntArray.put(R.id.banner, 16);
        sparseIntArray.put(R.id.rv, 17);
        sparseIntArray.put(R.id.rv_bbs, 18);
    }

    public FragmentBbsBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds));
    }

    private FragmentBbsBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (AppBarLayout) bindings[15], (ConvenientBanner) bindings[16], (ImageView) bindings[14], (ImageView) bindings[13], (RecyclerView) bindings[17], (RecyclerView) bindings[18], (RecyclerView) bindings[11], (TabLayout) bindings[12], (Toolbar) bindings[3], (TextView) bindings[7], (ImageView) bindings[10], (ImageView) bindings[9], (TextView) bindings[8]);
        this.mDirtyFlags = -1L;
        this.ivEdit.setTag(null);
        this.ivRefresh.setTag(null);
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[2];
        this.mboundView2 = linearLayout;
        linearLayout.setTag(null);
        ImageView imageView2 = (ImageView) bindings[4];
        this.mboundView4 = imageView2;
        imageView2.setTag(null);
        TextView textView = (TextView) bindings[5];
        this.mboundView5 = textView;
        textView.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[6];
        this.mboundView6 = linearLayout2;
        linearLayout2.setTag(null);
        this.rvTop.setTag(null);
        this.tab.setTag(null);
        this.toolbar.setTag(null);
        this.tvName.setTag(null);
        this.tvSearch.setTag(null);
        this.tvSign.setTag(null);
        this.tvViews.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
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
        if (33 == variableId) {
            setFold(((Float) variable).floatValue());
        } else if (74 == variableId) {
            setPosition(((Integer) variable).intValue());
        } else if (23 == variableId) {
            setData((BbsDetailBean) variable);
        } else {
            if (68 != variableId) {
                return false;
            }
            setOnClick((View.OnClickListener) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentBbsBinding
    public void setFold(float Fold) {
        this.mFold = Fold;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(33);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentBbsBinding
    public void setPosition(int Position) {
        this.mPosition = Position;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentBbsBinding
    public void setData(BbsDetailBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentBbsBinding
    public void setOnClick(View.OnClickListener OnClick) {
        this.mOnClick = OnClick;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(68);
        super.requestRebind();
    }

    /* JADX WARN: Removed duplicated region for block: B:101:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0188  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 405
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.FragmentBbsBindingImpl.executeBindings():void");
    }
}
