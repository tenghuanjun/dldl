package com.cy.yyjia.zhe28.databinding;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameCouponBean;
import com.cy.yyjia.zhe28.domain.TabBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityGameCouponBindingImpl extends ActivityGameCouponBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView10;
    private final TextView mboundView11;
    private final ImageView mboundView12;
    private final ImageView mboundView2;
    private final ImageView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;
    private final ImageView mboundView6;
    private final ImageView mboundView8;
    private final ImageView mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tv_account, 13);
        sparseIntArray.put(R.id.btn_change, 14);
        sparseIntArray.put(R.id.btn_all, 15);
        sparseIntArray.put(R.id.rv, 16);
    }

    public ActivityGameCouponBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds));
    }

    private ActivityGameCouponBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (ShapeTextView) bindings[15], (TextView) bindings[14], (FrameLayout) bindings[7], (FrameLayout) bindings[1], (RecyclerView) bindings[16], (TextView) bindings[13]);
        this.mDirtyFlags = -1L;
        this.btnMonth.setTag(null);
        this.btnSqk.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[10];
        this.mboundView10 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[11];
        this.mboundView11 = textView2;
        textView2.setTag(null);
        ImageView imageView = (ImageView) bindings[12];
        this.mboundView12 = imageView;
        imageView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[2];
        this.mboundView2 = imageView2;
        imageView2.setTag(null);
        ImageView imageView3 = (ImageView) bindings[3];
        this.mboundView3 = imageView3;
        imageView3.setTag(null);
        TextView textView3 = (TextView) bindings[4];
        this.mboundView4 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[5];
        this.mboundView5 = textView4;
        textView4.setTag(null);
        ImageView imageView4 = (ImageView) bindings[6];
        this.mboundView6 = imageView4;
        imageView4.setTag(null);
        ImageView imageView5 = (ImageView) bindings[8];
        this.mboundView8 = imageView5;
        imageView5.setTag(null);
        ImageView imageView6 = (ImageView) bindings[9];
        this.mboundView9 = imageView6;
        imageView6.setTag(null);
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
        if (23 != variableId) {
            return false;
        }
        setData((GameCouponBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGameCouponBinding
    public void setData(GameCouponBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId == 0) {
            return onChangeDataCardListGetInt0((TabBean) object, fieldId);
        }
        if (localFieldId != 1) {
            return false;
        }
        return onChangeDataCardListGetInt1((TabBean) object, fieldId);
    }

    private boolean onChangeDataCardListGetInt0(TabBean DataCardListGetInt0, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeDataCardListGetInt1(TabBean DataCardListGetInt1, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        long j2;
        int isShowMonth;
        String name;
        String name2;
        boolean z;
        boolean z2;
        String str;
        String str2;
        boolean z3;
        String str3;
        String str4;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        Drawable drawable;
        String desc;
        String icon;
        boolean zIsEmpty;
        boolean z8;
        String desc2;
        String icon2;
        boolean zIsEmpty2;
        boolean z9;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        GameCouponBean gameCouponBean = this.mData;
        int i = 0;
        if ((15 & j) != 0) {
            List<TabBean> cardList = gameCouponBean != null ? gameCouponBean.getCardList() : null;
            if ((j & 13) != 0) {
                TabBean tabBean = cardList != null ? cardList.get(0) : null;
                updateRegistration(0, tabBean);
                if (tabBean != null) {
                    desc = tabBean.getDesc();
                    icon = tabBean.getIcon();
                    name2 = tabBean.getName();
                } else {
                    name2 = null;
                    desc = null;
                    icon = null;
                }
                zIsEmpty = TextUtils.isEmpty(icon);
                z8 = !zIsEmpty;
            } else {
                name2 = null;
                desc = null;
                icon = null;
                zIsEmpty = false;
                z8 = false;
            }
            if ((j & 14) != 0) {
                TabBean tabBean2 = cardList != null ? cardList.get(1) : null;
                updateRegistration(1, tabBean2);
                if (tabBean2 != null) {
                    desc2 = tabBean2.getDesc();
                    icon2 = tabBean2.getIcon();
                    name = tabBean2.getName();
                } else {
                    name = null;
                    desc2 = null;
                    icon2 = null;
                }
                zIsEmpty2 = TextUtils.isEmpty(icon2);
                z9 = !zIsEmpty2;
            } else {
                name = null;
                desc2 = null;
                icon2 = null;
                zIsEmpty2 = false;
                z9 = false;
            }
            long j3 = j & 12;
            if (j3 != 0) {
                if (gameCouponBean != null) {
                    int isShowSaving = gameCouponBean.getIsShowSaving();
                    isShowMonth = gameCouponBean.getIsShowMonth();
                    i = isShowSaving;
                } else {
                    isShowMonth = 0;
                }
                boolean z10 = i == 0;
                z3 = i == 1;
                boolean z11 = isShowMonth == 0;
                if (j3 == 0) {
                    j2 = 32;
                } else if (z3) {
                    j2 = 32;
                    j |= 32;
                } else {
                    j2 = 32;
                    j |= 16;
                }
                str3 = desc;
                str4 = icon;
                z4 = zIsEmpty;
                z5 = z8;
                str = desc2;
                str2 = icon2;
                z6 = zIsEmpty2;
                z7 = z9;
                z = z10;
                z2 = z11;
            } else {
                j2 = 32;
                str3 = desc;
                str4 = icon;
                z4 = zIsEmpty;
                z5 = z8;
                str = desc2;
                str2 = icon2;
                z6 = zIsEmpty2;
                z7 = z9;
                isShowMonth = 0;
                z = false;
                z2 = false;
                z3 = false;
            }
        } else {
            j2 = 32;
            isShowMonth = 0;
            name = null;
            name2 = null;
            z = false;
            z2 = false;
            str = null;
            str2 = null;
            z3 = false;
            str3 = null;
            str4 = null;
            z4 = false;
            z5 = false;
            z6 = false;
            z7 = false;
        }
        long j4 = 12 & j;
        boolean z12 = (j4 == 0 || !z3) ? false : (j & j2) != 0 && isShowMonth == 1;
        if (j4 != 0) {
            DataBindingHelper.setViewGone(this.btnMonth, z2);
            DataBindingHelper.setViewGone(this.btnSqk, z);
            DataBindingHelper.setViewGone(this.mboundView12, z12);
            DataBindingHelper.setViewGone(this.mboundView6, z12);
        }
        if ((14 & j) != 0) {
            TextViewBindingAdapter.setText(this.mboundView10, name);
            TextViewBindingAdapter.setText(this.mboundView11, str);
            DataBindingHelper.setViewGone(this.mboundView8, z7);
            DataBindingHelper.setViewGone(this.mboundView9, z6);
            drawable = null;
            DataBindingHelper.setImg(this.mboundView9, str2, null);
        } else {
            drawable = null;
        }
        if ((j & 13) != 0) {
            DataBindingHelper.setViewGone(this.mboundView2, z5);
            DataBindingHelper.setViewGone(this.mboundView3, z4);
            DataBindingHelper.setImg(this.mboundView3, str4, drawable);
            TextViewBindingAdapter.setText(this.mboundView4, name2);
            TextViewBindingAdapter.setText(this.mboundView5, str3);
        }
    }
}
