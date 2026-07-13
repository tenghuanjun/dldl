package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.FilterBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeEditText;
import com.hjq.shape.view.ShapeTextView;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityDealListBindingImpl extends ActivityDealListBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener etandroidTextAttrChanged;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ShapeEditText mboundView10;
    private InverseBindingListener mboundView10androidTextAttrChanged;
    private final ShapeEditText mboundView11;
    private InverseBindingListener mboundView11androidTextAttrChanged;
    private final ShapeEditText mboundView8;
    private InverseBindingListener mboundView8androidTextAttrChanged;
    private final LinearLayout mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.f438tv, 12);
        sparseIntArray.put(R.id.cl_jianlou, 13);
        sparseIntArray.put(R.id.iv_tag, 14);
        sparseIntArray.put(R.id.srl, 15);
        sparseIntArray.put(R.id.rv, 16);
        sparseIntArray.put(R.id.tv_reset, 17);
        sparseIntArray.put(R.id.tv_search, 18);
    }

    public ActivityDealListBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds));
    }

    private ActivityDealListBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ConstraintLayout) bindings[13], (EditText) bindings[2], (FrameLayout) bindings[7], (ImageView) bindings[14], (Navigation) bindings[1], (RecyclerView) bindings[16], (RecyclerView) bindings[6], (SmartRefreshLayout) bindings[15], (ShapeLinearLayout) bindings[12], (TextView) bindings[3], (TextView) bindings[5], (ShapeTextView) bindings[17], (ShapeTextView) bindings[18], (TextView) bindings[4]);
        this.etandroidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityDealListBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityDealListBindingImpl.this.et);
                String str = ActivityDealListBindingImpl.this.mKeyword;
                ActivityDealListBindingImpl activityDealListBindingImpl = ActivityDealListBindingImpl.this;
                if (activityDealListBindingImpl != null) {
                    activityDealListBindingImpl.setKeyword(textString);
                }
            }
        };
        this.mboundView10androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityDealListBindingImpl.2
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityDealListBindingImpl.this.mboundView10);
                String str = ActivityDealListBindingImpl.this.mMin;
                ActivityDealListBindingImpl activityDealListBindingImpl = ActivityDealListBindingImpl.this;
                if (activityDealListBindingImpl != null) {
                    activityDealListBindingImpl.setMin(textString);
                }
            }
        };
        this.mboundView11androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityDealListBindingImpl.3
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityDealListBindingImpl.this.mboundView11);
                String str = ActivityDealListBindingImpl.this.mMax;
                ActivityDealListBindingImpl activityDealListBindingImpl = ActivityDealListBindingImpl.this;
                if (activityDealListBindingImpl != null) {
                    activityDealListBindingImpl.setMax(textString);
                }
            }
        };
        this.mboundView8androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityDealListBindingImpl.4
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityDealListBindingImpl.this.mboundView8);
                String str = ActivityDealListBindingImpl.this.mServer;
                ActivityDealListBindingImpl activityDealListBindingImpl = ActivityDealListBindingImpl.this;
                if (activityDealListBindingImpl != null) {
                    activityDealListBindingImpl.setServer(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.et.setTag(null);
        this.flFilter3.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ShapeEditText shapeEditText = (ShapeEditText) bindings[10];
        this.mboundView10 = shapeEditText;
        shapeEditText.setTag(null);
        ShapeEditText shapeEditText2 = (ShapeEditText) bindings[11];
        this.mboundView11 = shapeEditText2;
        shapeEditText2.setTag(null);
        ShapeEditText shapeEditText3 = (ShapeEditText) bindings[8];
        this.mboundView8 = shapeEditText3;
        shapeEditText3.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[9];
        this.mboundView9 = linearLayout2;
        linearLayout2.setTag(null);
        this.navigation.setTag(null);
        this.rvFilter.setTag(null);
        this.tvOrder.setTag(null);
        this.tvPrice.setTag(null);
        this.tvServer.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 128L;
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
        if (55 == variableId) {
            setMax((String) variable);
        } else if (96 == variableId) {
            setServer((String) variable);
        } else if (51 == variableId) {
            setKeyword((String) variable);
        } else if (57 == variableId) {
            setMin((String) variable);
        } else if (32 == variableId) {
            setFilter(((Integer) variable).intValue());
        } else if (50 == variableId) {
            setJianlou(((Integer) variable).intValue());
        } else {
            if (69 != variableId) {
                return false;
            }
            setOrder((FilterBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityDealListBinding
    public void setMax(String Max) {
        this.mMax = Max;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(55);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityDealListBinding
    public void setServer(String Server) {
        this.mServer = Server;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(96);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityDealListBinding
    public void setKeyword(String Keyword) {
        this.mKeyword = Keyword;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(51);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityDealListBinding
    public void setMin(String Min) {
        this.mMin = Min;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(57);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityDealListBinding
    public void setFilter(int Filter) {
        this.mFilter = Filter;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(32);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityDealListBinding
    public void setJianlou(int Jianlou) {
        this.mJianlou = Jianlou;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(50);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityDealListBinding
    public void setOrder(FilterBean Order) {
        updateRegistration(0, Order);
        this.mOrder = Order;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(69);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeOrder((FilterBean) object, fieldId);
    }

    private boolean onChangeOrder(FilterBean Order, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        String str;
        boolean z7;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str2 = this.mMax;
        String str3 = this.mServer;
        String str4 = this.mKeyword;
        String str5 = this.mMin;
        int i = this.mFilter;
        int i2 = this.mJianlou;
        FilterBean filterBean = this.mOrder;
        long j2 = j & 160;
        if (j2 != 0) {
            z4 = i == 2;
            z3 = i != 2;
            boolean z8 = i == 3;
            boolean z9 = i == 1;
            boolean z10 = i != 1;
            z = i != 3;
            if (j2 != 0) {
                j |= z ? 2048L : 1024L;
            }
            z2 = z8;
            z5 = z9;
            z6 = z10;
        } else {
            z = false;
            z2 = false;
            z3 = false;
            z4 = false;
            z5 = false;
            z6 = false;
        }
        long j3 = j & 192;
        if (j3 != 0) {
            boolean z11 = i2 == 1;
            if (j3 != 0) {
                j |= z11 ? 512L : 256L;
            }
            str = z11 ? "官方捡漏" : "购买小号";
        } else {
            str = null;
        }
        long j4 = j & 129;
        String name = (j4 == 0 || filterBean == null) ? null : filterBean.getName();
        long j5 = j & 160;
        if (j5 != 0) {
            z7 = z ? z3 : false;
        } else {
            z7 = false;
        }
        if ((j & 136) != 0) {
            TextViewBindingAdapter.setText(this.et, str4);
        }
        if ((128 & j) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.et, null, null, null, this.etandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView10, null, null, null, this.mboundView10androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView11, null, null, null, this.mboundView11androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView8, null, null, null, this.mboundView8androidTextAttrChanged);
        }
        if (j5 != 0) {
            DataBindingHelper.setViewGone(this.flFilter3, z7);
            DataBindingHelper.setViewGone(this.mboundView8, z3);
            DataBindingHelper.setViewGone(this.mboundView9, z);
            DataBindingHelper.setViewGone(this.rvFilter, z6);
            DataBindingHelper.setSelected(this.tvOrder, z5);
            DataBindingHelper.setSelected(this.tvPrice, z2);
            DataBindingHelper.setSelected(this.tvServer, z4);
        }
        if ((144 & j) != 0) {
            TextViewBindingAdapter.setText(this.mboundView10, str5);
        }
        if ((130 & j) != 0) {
            TextViewBindingAdapter.setText(this.mboundView11, str2);
        }
        if ((132 & j) != 0) {
            TextViewBindingAdapter.setText(this.mboundView8, str3);
        }
        if ((j & 192) != 0) {
            this.navigation.setTitle(str);
        }
        if (j4 != 0) {
            TextViewBindingAdapter.setText(this.tvOrder, name);
        }
    }
}
