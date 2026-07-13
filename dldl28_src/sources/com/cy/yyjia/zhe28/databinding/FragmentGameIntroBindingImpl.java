package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BtnBean;
import com.cy.yyjia.zhe28.domain.GameDetailBean;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentGameIntroBindingImpl extends FragmentGameIntroBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private OnClickListenerImpl1 mDataFreeLotteryOnClickAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private OnClickListenerImpl mOnClickOnClickAndroidViewViewOnClickListener;
    private final TextView mboundView10;
    private final LinearLayout mboundView11;
    private final FrameLayout mboundView13;
    private final TextView mboundView14;
    private final TextView mboundView15;
    private final TextView mboundView16;
    private final LinearLayout mboundView2;
    private final TextView mboundView20;
    private final TextView mboundView21;
    private final TextView mboundView23;
    private final ImageView mboundView26;
    private final LinearLayout mboundView27;
    private final LinearLayout mboundView29;
    private final ImageView mboundView3;
    private final TextView mboundView30;
    private final LinearLayout mboundView33;
    private final ShapeTextView mboundView4;
    private final TextView mboundView40;
    private final TextView mboundView42;
    private final FrameLayout mboundView43;
    private final TextView mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView8;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(49);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(2, new String[]{"layout_discount"}, new int[]{47}, new int[]{R.layout.layout_discount});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.vf, 48);
    }

    public FragmentGameIntroBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 49, sIncludes, sViewsWithIds));
    }

    private FragmentGameIntroBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (ShapeLinearLayout) bindings[19], (ShapeTextView) bindings[35], (LinearLayout) bindings[22], (ShapeTextView) bindings[36], (ShapeTextView) bindings[34], (Button) bindings[17], (LayoutDiscountBinding) bindings[47], (ImageView) bindings[46], (ImageView) bindings[9], (ImageView) bindings[25], (ImageView) bindings[24], (NestedScrollView) bindings[0], (RecyclerView) bindings[1], (RecyclerView) bindings[41], (RecyclerView) bindings[44], (TextView) bindings[12], (TextView) bindings[32], (TextView) bindings[18], (TextView) bindings[28], (ShapeLinearLayout) bindings[7], (TextView) bindings[31], (ShapeTextView) bindings[37], (ShapeTextView) bindings[38], (ShapeTextView) bindings[39], (TextView) bindings[45], (AdapterViewFlipper) bindings[48]);
        this.mDirtyFlags = -1L;
        this.btn648.setTag(null);
        this.btnCoupon.setTag(null);
        this.btnEvent.setTag(null);
        this.btnGift.setTag(null);
        this.btnIntro.setTag(null);
        this.btnReserve.setTag(null);
        setContainedBinding(this.discount);
        this.ivBbs.setTag(null);
        this.ivFreeClose.setTag(null);
        this.ivTask.setTag(null);
        this.ivTopic.setTag(null);
        TextView textView = (TextView) bindings[10];
        this.mboundView10 = textView;
        textView.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[11];
        this.mboundView11 = linearLayout;
        linearLayout.setTag(null);
        FrameLayout frameLayout = (FrameLayout) bindings[13];
        this.mboundView13 = frameLayout;
        frameLayout.setTag(null);
        TextView textView2 = (TextView) bindings[14];
        this.mboundView14 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[15];
        this.mboundView15 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[16];
        this.mboundView16 = textView4;
        textView4.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[2];
        this.mboundView2 = linearLayout2;
        linearLayout2.setTag(null);
        TextView textView5 = (TextView) bindings[20];
        this.mboundView20 = textView5;
        textView5.setTag(null);
        TextView textView6 = (TextView) bindings[21];
        this.mboundView21 = textView6;
        textView6.setTag(null);
        TextView textView7 = (TextView) bindings[23];
        this.mboundView23 = textView7;
        textView7.setTag(null);
        ImageView imageView = (ImageView) bindings[26];
        this.mboundView26 = imageView;
        imageView.setTag(null);
        LinearLayout linearLayout3 = (LinearLayout) bindings[27];
        this.mboundView27 = linearLayout3;
        linearLayout3.setTag(null);
        LinearLayout linearLayout4 = (LinearLayout) bindings[29];
        this.mboundView29 = linearLayout4;
        linearLayout4.setTag(null);
        ImageView imageView2 = (ImageView) bindings[3];
        this.mboundView3 = imageView2;
        imageView2.setTag(null);
        TextView textView8 = (TextView) bindings[30];
        this.mboundView30 = textView8;
        textView8.setTag(null);
        LinearLayout linearLayout5 = (LinearLayout) bindings[33];
        this.mboundView33 = linearLayout5;
        linearLayout5.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[4];
        this.mboundView4 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView9 = (TextView) bindings[40];
        this.mboundView40 = textView9;
        textView9.setTag(null);
        TextView textView10 = (TextView) bindings[42];
        this.mboundView42 = textView10;
        textView10.setTag(null);
        FrameLayout frameLayout2 = (FrameLayout) bindings[43];
        this.mboundView43 = frameLayout2;
        frameLayout2.setTag(null);
        TextView textView11 = (TextView) bindings[5];
        this.mboundView5 = textView11;
        textView11.setTag(null);
        TextView textView12 = (TextView) bindings[6];
        this.mboundView6 = textView12;
        textView12.setTag(null);
        TextView textView13 = (TextView) bindings[8];
        this.mboundView8 = textView13;
        textView13.setTag(null);
        this.nsv.setTag(null);
        this.rv.setTag(null);
        this.rvTag.setTag(null);
        this.rvUpdate.setTag(null);
        this.tvEvent.setTag(null);
        this.tvHour.setTag(null);
        this.tvKaiju.setTag(null);
        this.tvLottery.setTag(null);
        this.tvService.setTag(null);
        this.tvTask.setTag(null);
        this.tvType1.setTag(null);
        this.tvType2.setTag(null);
        this.tvType3.setTag(null);
        this.tvUpdate.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 64L;
        }
        this.discount.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.discount.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (123 == variableId) {
            setVip(((Boolean) variable).booleanValue());
        } else if (100 == variableId) {
            setShowFree(((Boolean) variable).booleanValue());
        } else if (23 == variableId) {
            setData((GameDetailBean) variable);
        } else {
            if (68 != variableId) {
                return false;
            }
            setOnClick((View.OnClickListener) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentGameIntroBinding
    public void setVip(boolean Vip) {
        this.mVip = Vip;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentGameIntroBinding
    public void setShowFree(boolean ShowFree) {
        this.mShowFree = ShowFree;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentGameIntroBinding
    public void setData(GameDetailBean Data) {
        updateRegistration(0, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentGameIntroBinding
    public void setOnClick(View.OnClickListener OnClick) {
        this.mOnClick = OnClick;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(68);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.discount.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId == 0) {
            return onChangeData((GameDetailBean) object, fieldId);
        }
        if (localFieldId != 1) {
            return false;
        }
        return onChangeDiscount((LayoutDiscountBinding) object, fieldId);
    }

    private boolean onChangeData(GameDetailBean Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        if (fieldId != 48) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeDiscount(LayoutDiscountBinding Discount, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:140:0x039f  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x03b8  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x03d3  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 1555
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.FragmentGameIntroBindingImpl.executeBindings():void");
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

    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private BtnBean value;

        public OnClickListenerImpl1 setValue(BtnBean value) {
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
