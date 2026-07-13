package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentHallBindingImpl extends FragmentHallBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mOnClickOnClickAndroidViewViewOnClickListener;
    private final RelativeLayout mboundView0;
    private final TextView mboundView11;
    private final View mboundView12;
    private final TextView mboundView13;
    private final TextView mboundView15;
    private final View mboundView16;
    private final TextView mboundView17;
    private final TextView mboundView3;
    private final View mboundView4;
    private final TextView mboundView5;
    private final TextView mboundView7;
    private final View mboundView8;
    private final TextView mboundView9;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.vp, 19);
    }

    public FragmentHallBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 20, sIncludes, sViewsWithIds));
    }

    private FragmentHallBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (LinearLayout) bindings[2], (LinearLayout) bindings[6], (LinearLayout) bindings[10], (LinearLayout) bindings[14], (LinearLayout) bindings[1], (ImageView) bindings[18], (ViewPager2) bindings[19]);
        this.mDirtyFlags = -1L;
        this.btn1.setTag(null);
        this.btn2.setTag(null);
        this.btn3.setTag(null);
        this.btn4.setTag(null);
        this.ll.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        TextView textView = (TextView) bindings[11];
        this.mboundView11 = textView;
        textView.setTag(null);
        View view = (View) bindings[12];
        this.mboundView12 = view;
        view.setTag(null);
        TextView textView2 = (TextView) bindings[13];
        this.mboundView13 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[15];
        this.mboundView15 = textView3;
        textView3.setTag(null);
        View view2 = (View) bindings[16];
        this.mboundView16 = view2;
        view2.setTag(null);
        TextView textView4 = (TextView) bindings[17];
        this.mboundView17 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[3];
        this.mboundView3 = textView5;
        textView5.setTag(null);
        View view3 = (View) bindings[4];
        this.mboundView4 = view3;
        view3.setTag(null);
        TextView textView6 = (TextView) bindings[5];
        this.mboundView5 = textView6;
        textView6.setTag(null);
        TextView textView7 = (TextView) bindings[7];
        this.mboundView7 = textView7;
        textView7.setTag(null);
        View view4 = (View) bindings[8];
        this.mboundView8 = view4;
        view4.setTag(null);
        TextView textView8 = (TextView) bindings[9];
        this.mboundView9 = textView8;
        textView8.setTag(null);
        this.tvSearch.setTag(null);
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
        if (74 == variableId) {
            setPosition(((Integer) variable).intValue());
        } else {
            if (68 != variableId) {
                return false;
            }
            setOnClick((View.OnClickListener) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentHallBinding
    public void setPosition(int Position) {
        this.mPosition = Position;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(74);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentHallBinding
    public void setOnClick(View.OnClickListener OnClick) {
        this.mOnClick = OnClick;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(68);
        super.requestRebind();
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
        boolean z7;
        OnClickListenerImpl value;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int i = this.mPosition;
        View.OnClickListener onClickListener = this.mOnClick;
        long j2 = 5 & j;
        if (j2 != 0) {
            boolean z8 = i != 2;
            z4 = i != 0;
            z5 = i == 0;
            z2 = i == 2;
            z7 = i != 3;
            boolean z9 = i != 1;
            boolean z10 = i == 1;
            z6 = i == 3;
            z = z9;
            z = z8;
            z3 = z10;
        } else {
            z = false;
            z2 = false;
            z3 = false;
            z4 = false;
            z5 = false;
            z6 = false;
            z7 = false;
        }
        long j3 = j & 6;
        if (j3 == 0 || onClickListener == null) {
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
            this.btn1.setOnClickListener(value);
            this.btn2.setOnClickListener(value);
            this.btn3.setOnClickListener(value);
            this.btn4.setOnClickListener(value);
            this.tvSearch.setOnClickListener(value);
        }
        if ((j & 4) != 0) {
            DataBindingHelper.setFitWindow(this.ll, true);
        }
        if (j2 != 0) {
            DataBindingHelper.setViewGone(this.mboundView11, z);
            DataBindingHelper.setViewGone(this.mboundView12, z);
            DataBindingHelper.setViewGone(this.mboundView13, z2);
            DataBindingHelper.setViewGone(this.mboundView15, z7);
            DataBindingHelper.setViewGone(this.mboundView16, z7);
            DataBindingHelper.setViewGone(this.mboundView17, z6);
            DataBindingHelper.setViewGone(this.mboundView3, z4);
            DataBindingHelper.setViewGone(this.mboundView4, z4);
            DataBindingHelper.setViewGone(this.mboundView5, z5);
            DataBindingHelper.setViewGone(this.mboundView7, z);
            DataBindingHelper.setViewGone(this.mboundView8, z);
            DataBindingHelper.setViewGone(this.mboundView9, z3);
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
