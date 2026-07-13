package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.PtbDetailBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityPtbDetailBindingImpl extends ActivityPtbDetailBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView10;
    private final TextView mboundView11;
    private final ImageView mboundView2;
    private final TextView mboundView8;
    private final TextView mboundView9;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.navigation, 12);
    }

    public ActivityPtbDetailBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }

    private ActivityPtbDetailBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[1], (TextView) bindings[3], (Navigation) bindings[12], (TextView) bindings[4], (TextView) bindings[5], (TextView) bindings[7], (TextView) bindings[6]);
        this.mDirtyFlags = -1L;
        this.icon.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[10];
        this.mboundView10 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[11];
        this.mboundView11 = textView2;
        textView2.setTag(null);
        ImageView imageView = (ImageView) bindings[2];
        this.mboundView2 = imageView;
        imageView.setTag(null);
        TextView textView3 = (TextView) bindings[8];
        this.mboundView8 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[9];
        this.mboundView9 = textView4;
        textView4.setTag(null);
        this.name.setTag(null);
        this.number.setTag(null);
        this.orderId.setTag(null);
        this.orderPayType.setTag(null);
        this.orderTime.setTag(null);
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
        setData((PtbDetailBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityPtbDetailBinding
    public void setData(PtbDetailBean Data) {
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
        boolean z;
        String icon;
        String str3;
        String money;
        boolean z2;
        String str4;
        String str5;
        String str6;
        String str7;
        String type;
        PtbDetailBean.Game game;
        String str8;
        String orderId;
        String result;
        String goodsName;
        String name1;
        String discountedMoney;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        PtbDetailBean ptbDetailBean = this.mData;
        long j2 = j & 3;
        if (j2 != 0) {
            if (ptbDetailBean != null) {
                game = ptbDetailBean.getGame();
                money = ptbDetailBean.getMoney();
                result = ptbDetailBean.getResult();
                String showTime = ptbDetailBean.getShowTime();
                goodsName = ptbDetailBean.getGoodsName();
                name1 = ptbDetailBean.getName1();
                discountedMoney = ptbDetailBean.getDiscountedMoney();
                str8 = showTime;
                orderId = ptbDetailBean.getOrderId();
                type = ptbDetailBean.getType();
            } else {
                type = null;
                game = null;
                money = null;
                str8 = null;
                orderId = null;
                result = null;
                goodsName = null;
                name1 = null;
                discountedMoney = null;
            }
            icon = game != null ? game.getIcon() : null;
            str = "-" + result;
            str2 = "小计：￥" + result;
            String str9 = "下单时间：" + str8;
            String str10 = "订单号：" + orderId;
            boolean zEquals = type != null ? type.equals("game") : false;
            if (j2 != 0) {
                j = zEquals ? j | 8 : j | 4;
            }
            z = !zEquals;
            str4 = str10;
            z2 = zEquals;
            str5 = goodsName;
            str6 = name1;
            str7 = str9;
            str3 = discountedMoney;
        } else {
            str = null;
            str2 = null;
            z = false;
            icon = null;
            str3 = null;
            money = null;
            z2 = false;
            str4 = null;
            str5 = null;
            str6 = null;
            str7 = null;
        }
        String orderTypeText = ((4 & j) == 0 || ptbDetailBean == null) ? null : ptbDetailBean.getOrderTypeText();
        long j3 = j & 3;
        if (j3 == 0) {
            orderTypeText = null;
        } else if (z2) {
            orderTypeText = str5;
        }
        if (j3 != 0) {
            DataBindingHelper.setGameIcon(this.icon, icon);
            DataBindingHelper.setViewGone(this.icon, z);
            TextViewBindingAdapter.setText(this.mboundView10, str3);
            TextViewBindingAdapter.setText(this.mboundView11, str2);
            DataBindingHelper.setViewGone(this.mboundView2, z2);
            TextViewBindingAdapter.setText(this.mboundView8, str5);
            TextViewBindingAdapter.setText(this.mboundView9, money);
            TextViewBindingAdapter.setText(this.name, orderTypeText);
            TextViewBindingAdapter.setText(this.number, str);
            TextViewBindingAdapter.setText(this.orderId, str4);
            TextViewBindingAdapter.setText(this.orderPayType, str6);
            TextViewBindingAdapter.setText(this.orderTime, str7);
        }
    }
}
