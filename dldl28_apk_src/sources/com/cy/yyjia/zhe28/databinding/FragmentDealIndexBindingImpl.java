package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.google.android.material.appbar.AppBarLayout;
import com.hjq.shape.layout.ShapeLinearLayout;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentDealIndexBindingImpl extends FragmentDealIndexBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener etandroidTextAttrChanged;
    private long mDirtyFlags;
    private OnClickListenerImpl mOnClickOnClickAndroidViewViewOnClickListener;
    private final CoordinatorLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.abl, 6);
        sparseIntArray.put(R.id.tv_search, 7);
        sparseIntArray.put(R.id.rv, 8);
        sparseIntArray.put(R.id.vp2, 9);
    }

    public FragmentDealIndexBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private FragmentDealIndexBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (AppBarLayout) bindings[6], (EditText) bindings[2], (ImageView) bindings[5], (ImageView) bindings[4], (LinearLayout) bindings[1], (RecyclerView) bindings[8], (TextView) bindings[3], (ShapeLinearLayout) bindings[7], (ViewPager2) bindings[9]);
        this.etandroidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.FragmentDealIndexBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(FragmentDealIndexBindingImpl.this.et);
                String str = FragmentDealIndexBindingImpl.this.mKeyword;
                FragmentDealIndexBindingImpl fragmentDealIndexBindingImpl = FragmentDealIndexBindingImpl.this;
                if (fragmentDealIndexBindingImpl != null) {
                    fragmentDealIndexBindingImpl.setKeyword(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.et.setTag(null);
        this.ivRule.setTag(null);
        this.ivSearch.setTag(null);
        this.llSearch.setTag(null);
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) bindings[0];
        this.mboundView0 = coordinatorLayout;
        coordinatorLayout.setTag(null);
        this.tvFilter.setTag(null);
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
        if (74 == variableId) {
            setPosition(((Integer) variable).intValue());
        } else if (51 == variableId) {
            setKeyword((String) variable);
        } else if (34 == variableId) {
            setFolder(((Boolean) variable).booleanValue());
        } else {
            if (68 != variableId) {
                return false;
            }
            setOnClick((View.OnClickListener) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentDealIndexBinding
    public void setPosition(int Position) {
        this.mPosition = Position;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(74);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentDealIndexBinding
    public void setKeyword(String Keyword) {
        this.mKeyword = Keyword;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(51);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentDealIndexBinding
    public void setFolder(boolean Folder) {
        this.mFolder = Folder;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(34);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentDealIndexBinding
    public void setOnClick(View.OnClickListener OnClick) {
        this.mOnClick = OnClick;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(68);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        boolean z2;
        OnClickListenerImpl value;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int i = this.mPosition;
        String str = this.mKeyword;
        boolean z3 = this.mFolder;
        View.OnClickListener onClickListener = this.mOnClick;
        long j2 = j & 21;
        if (j2 != 0) {
            z2 = i > 1;
            if (j2 != 0) {
                j = z2 ? j | 64 : j | 32;
            }
            z = ((j & 17) == 0 || i == 0) ? false : true;
        } else {
            z = false;
            z2 = false;
        }
        long j3 = j & 18;
        long j4 = j & 24;
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
        boolean z4 = (j & 32) != 0 ? !z3 : false;
        long j5 = 21 & j;
        if (j5 == 0) {
            z4 = false;
        } else if (z2) {
            z4 = true;
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.et, str);
        }
        if ((16 & j) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.et, null, null, null, this.etandroidTextAttrChanged);
            DataBindingHelper.setFitWindow(this.mboundView0, true);
        }
        if (j4 != 0) {
            this.ivRule.setOnClickListener(value);
            this.ivSearch.setOnClickListener(value);
            this.tvFilter.setOnClickListener(value);
        }
        if (j5 != 0) {
            DataBindingHelper.setViewGone(this.ivSearch, z4);
        }
        if ((j & 17) != 0) {
            DataBindingHelper.setViewGone(this.llSearch, z2);
            DataBindingHelper.setViewGone(this.tvFilter, z);
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
