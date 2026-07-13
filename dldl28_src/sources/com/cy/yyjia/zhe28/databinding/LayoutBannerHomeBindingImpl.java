package com.cy.yyjia.zhe28.databinding;

import android.text.SpannableString;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BannerBean;
import com.cy.yyjia.zhe28.domain.BtnBean;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class LayoutBannerHomeBindingImpl extends LayoutBannerHomeBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private OnClickListenerImpl mDataLinkOnClickAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView11;
    private final TextView mboundView12;
    private final ShapeTextView mboundView13;
    private final ImageView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final ShapeLinearLayout mboundView6;
    private final TextView mboundView7;
    private final ShapeLinearLayout mboundView8;
    private final TextView mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.ll, 15);
        sparseIntArray.put(R.id.name, 16);
    }

    public LayoutBannerHomeBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds));
    }

    private LayoutBannerHomeBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ShapeLinearLayout) bindings[15], (LinearLayout) bindings[5], (LinearLayout) bindings[16], (ShapeLinearLayout) bindings[10], (ShapeTextView) bindings[14]);
        this.mDirtyFlags = -1L;
        this.llDiscount.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[11];
        this.mboundView11 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[12];
        this.mboundView12 = textView2;
        textView2.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[13];
        this.mboundView13 = shapeTextView;
        shapeTextView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[2];
        this.mboundView2 = imageView2;
        imageView2.setTag(null);
        TextView textView3 = (TextView) bindings[3];
        this.mboundView3 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[4];
        this.mboundView4 = textView4;
        textView4.setTag(null);
        ShapeLinearLayout shapeLinearLayout = (ShapeLinearLayout) bindings[6];
        this.mboundView6 = shapeLinearLayout;
        shapeLinearLayout.setTag(null);
        TextView textView5 = (TextView) bindings[7];
        this.mboundView7 = textView5;
        textView5.setTag(null);
        ShapeLinearLayout shapeLinearLayout2 = (ShapeLinearLayout) bindings[8];
        this.mboundView8 = shapeLinearLayout2;
        shapeLinearLayout2.setTag(null);
        TextView textView6 = (TextView) bindings[9];
        this.mboundView9 = textView6;
        textView6.setTag(null);
        this.other.setTag(null);
        this.tag.setTag(null);
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
        if (23 != variableId) {
            return false;
        }
        setData((BannerBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.LayoutBannerHomeBinding
    public void setData(BannerBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeDataGame((GameBean) object, fieldId);
    }

    private boolean onChangeDataGame(GameBean DataGame, int fieldId) {
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
        String pic_url;
        String str;
        String str2;
        OnClickListenerImpl value;
        String t;
        GameBean game;
        boolean zEquals;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        String str3;
        SpannableString spannableString;
        String str4;
        String str5;
        String str6;
        boolean z5;
        boolean z6;
        String continueRechargeDiscount;
        String str7;
        String free_tip;
        SpannableString bannerStr;
        String score;
        Integer isGm;
        String showName;
        String icon;
        boolean zCanShowDiscount;
        BtnBean btnBean;
        BannerBean.Tag tag;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        BannerBean bannerBean = this.mData;
        long j2 = j & 7;
        if (j2 != 0) {
            game = bannerBean != null ? bannerBean.getGame() : null;
            updateRegistration(0, game);
            if (game != null) {
                continueRechargeDiscount = game.getContinueRechargeDiscount();
                String firstRechargeDiscount = game.getFirstRechargeDiscount();
                free_tip = game.getFree_tip();
                bannerStr = game.getBannerStr();
                score = game.getScore();
                isGm = game.getIsGm();
                showName = game.getShowName();
                icon = game.getIcon();
                zCanShowDiscount = game.canShowDiscount();
                str7 = firstRechargeDiscount;
            } else {
                continueRechargeDiscount = null;
                str7 = null;
                free_tip = null;
                bannerStr = null;
                score = null;
                isGm = null;
                showName = null;
                icon = null;
                zCanShowDiscount = false;
            }
            str = continueRechargeDiscount + "折";
            str2 = str7 + "折";
            int iSafeUnbox = ViewDataBinding.safeUnbox(isGm);
            z4 = !zCanShowDiscount;
            if (j2 != 0) {
                j = !zCanShowDiscount ? j | 16 : j | 8;
            }
            zEquals = str7 != null ? str7.equals(continueRechargeDiscount) : false;
            z = iSafeUnbox == 1;
            z2 = !zEquals;
            if ((j & 7) != 0) {
                j = z ? j | 64 : j | 32;
            }
            if ((j & 6) != 0) {
                if (bannerBean != null) {
                    tag = bannerBean.getTag();
                    BtnBean link = bannerBean.getLink();
                    pic_url = bannerBean.getPic_url();
                    btnBean = link;
                } else {
                    pic_url = null;
                    btnBean = null;
                    tag = null;
                }
                t = tag != null ? tag.getT() : null;
                if (btnBean != null) {
                    OnClickListenerImpl onClickListenerImpl = this.mDataLinkOnClickAndroidViewViewOnClickListener;
                    if (onClickListenerImpl == null) {
                        onClickListenerImpl = new OnClickListenerImpl();
                        this.mDataLinkOnClickAndroidViewViewOnClickListener = onClickListenerImpl;
                    }
                    value = onClickListenerImpl.setValue(btnBean);
                } else {
                    value = null;
                }
                z3 = (t != null ? t.length() : 0) == 0;
                str3 = free_tip;
                spannableString = bannerStr;
                str4 = score;
                str5 = showName;
                str6 = icon;
            } else {
                str3 = free_tip;
                spannableString = bannerStr;
                str4 = score;
                str5 = showName;
                str6 = icon;
                pic_url = null;
                value = null;
                t = null;
                z3 = false;
            }
        } else {
            pic_url = null;
            str = null;
            str2 = null;
            value = null;
            t = null;
            game = null;
            zEquals = false;
            z = false;
            z2 = false;
            z3 = false;
            z4 = false;
            str3 = null;
            spannableString = null;
            str4 = null;
            str5 = null;
            str6 = null;
        }
        if ((j & 40) != 0) {
            int iSafeUnbox2 = ViewDataBinding.safeUnbox(game != null ? game.getIsFree() : null);
            z6 = (8 & j) != 0 && iSafeUnbox2 == 1;
            z5 = (j & 32) != 0 && iSafeUnbox2 == 0;
        } else {
            z5 = false;
            z6 = false;
        }
        long j3 = j & 7;
        if (j3 != 0) {
            if (z4) {
                z6 = true;
            }
            if (z) {
                z5 = true;
            }
        } else {
            z5 = false;
            z6 = false;
        }
        if (j3 != 0) {
            DataBindingHelper.setViewGone(this.llDiscount, z6);
            TextViewBindingAdapter.setText(this.mboundView11, str);
            DataBindingHelper.setViewGone(this.mboundView12, z5);
            TextViewBindingAdapter.setText(this.mboundView12, str3);
            TextViewBindingAdapter.setText(this.mboundView13, str4);
            DataBindingHelper.setGameIcon(this.mboundView2, str6);
            TextViewBindingAdapter.setText(this.mboundView3, str5);
            TextViewBindingAdapter.setText(this.mboundView4, spannableString);
            DataBindingHelper.setViewGone(this.mboundView6, z2);
            TextViewBindingAdapter.setText(this.mboundView7, str2);
            DataBindingHelper.setViewGone(this.mboundView8, zEquals);
            TextViewBindingAdapter.setText(this.mboundView9, str2);
            DataBindingHelper.setViewGone(this.other, zEquals);
        }
        if ((6 & j) != 0) {
            this.mboundView1.setOnClickListener(value);
            DataBindingHelper.setImg(this.mboundView1, pic_url, null);
            DataBindingHelper.setViewGone(this.tag, z3);
            TextViewBindingAdapter.setText(this.tag, t);
        }
        if ((j & 4) != 0) {
            DataBindingHelper.setSelected(this.mboundView3, true);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private BtnBean value;

        public OnClickListenerImpl setValue(BtnBean value) {
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
