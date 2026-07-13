package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.ItemTradeRecordBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemItemTradeRecordBindingImpl extends ItemItemTradeRecordBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView10;
    private final ShapeTextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;
    private final TextView mboundView6;
    private final ShapeTextView mboundView7;
    private final TextView mboundView8;
    private final TextView mboundView9;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemItemTradeRecordBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    private ItemItemTradeRecordBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ShapeTextView) bindings[11]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[10];
        this.mboundView10 = textView;
        textView.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[2];
        this.mboundView2 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView2 = (TextView) bindings[3];
        this.mboundView3 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[4];
        this.mboundView4 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[5];
        this.mboundView5 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[6];
        this.mboundView6 = textView5;
        textView5.setTag(null);
        ShapeTextView shapeTextView2 = (ShapeTextView) bindings[7];
        this.mboundView7 = shapeTextView2;
        shapeTextView2.setTag(null);
        TextView textView6 = (TextView) bindings[8];
        this.mboundView8 = textView6;
        textView6.setTag(null);
        TextView textView7 = (TextView) bindings[9];
        this.mboundView9 = textView7;
        textView7.setTag(null);
        this.tvConfirm.setTag(null);
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
        setData((ItemTradeRecordBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemItemTradeRecordBinding
    public void setData(ItemTradeRecordBean Data) {
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
        boolean z;
        String str;
        String statusName;
        String str2;
        String str3;
        boolean z2;
        String str4;
        String str5;
        String str6;
        String str7;
        String name;
        boolean z3;
        String createTime;
        ItemTradeRecordBean.Game game;
        int num;
        String money;
        String roleName;
        String serviceCode;
        String payTime;
        int status;
        String deliverTime;
        ItemTradeRecordBean.Asset asset;
        String str8;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        ItemTradeRecordBean itemTradeRecordBean = this.mData;
        long j2 = j & 3;
        if (j2 != 0) {
            if (itemTradeRecordBean != null) {
                game = itemTradeRecordBean.getGame();
                statusName = itemTradeRecordBean.getStatusName();
                num = itemTradeRecordBean.getNum();
                money = itemTradeRecordBean.getMoney();
                roleName = itemTradeRecordBean.getRoleName();
                serviceCode = itemTradeRecordBean.getServiceCode();
                payTime = itemTradeRecordBean.getPayTime();
                status = itemTradeRecordBean.getStatus();
                deliverTime = itemTradeRecordBean.getDeliverTime();
                asset = itemTradeRecordBean.getAsset();
                createTime = itemTradeRecordBean.getCreateTime();
            } else {
                createTime = null;
                game = null;
                statusName = null;
                num = 0;
                money = null;
                roleName = null;
                serviceCode = null;
                payTime = null;
                status = 0;
                deliverTime = null;
                asset = null;
            }
            String name2 = game != null ? game.getName() : null;
            String str9 = "购买数量：" + num;
            str3 = "￥" + money;
            String str10 = "支付时间：" + payTime;
            boolean z4 = status != 2;
            boolean z5 = status != 0;
            z3 = status != 1;
            String str11 = "发货时间：" + deliverTime;
            String str12 = "上架时间：" + createTime;
            if (asset != null) {
                String pic = asset.getPic();
                name = asset.getName();
                str8 = pic;
            } else {
                name = null;
                str8 = null;
            }
            String str13 = (((str9 + "丨") + serviceCode) + "丨") + roleName;
            str6 = str12;
            str5 = str13;
            z = z5;
            str2 = name2;
            str = str11;
            str4 = str8;
            boolean z6 = z4;
            str7 = str10;
            z2 = z6;
        } else {
            z = false;
            str = null;
            statusName = null;
            str2 = null;
            str3 = null;
            z2 = false;
            str4 = null;
            str5 = null;
            str6 = null;
            str7 = null;
            name = null;
            z3 = false;
        }
        if (j2 != 0) {
            DataBindingHelper.setGameIcon(this.mboundView1, str4);
            DataBindingHelper.setViewGone(this.mboundView10, z2);
            TextViewBindingAdapter.setText(this.mboundView10, str);
            this.mboundView2.setEnabled(z2);
            TextViewBindingAdapter.setText(this.mboundView2, statusName);
            TextViewBindingAdapter.setText(this.mboundView3, name);
            TextViewBindingAdapter.setText(this.mboundView4, str3);
            TextViewBindingAdapter.setText(this.mboundView5, str2);
            TextViewBindingAdapter.setText(this.mboundView6, str5);
            this.mboundView7.setEnabled(z2);
            TextViewBindingAdapter.setText(this.mboundView7, statusName);
            DataBindingHelper.setViewGone(this.mboundView8, z);
            TextViewBindingAdapter.setText(this.mboundView8, str6);
            DataBindingHelper.setViewGone(this.mboundView9, z3);
            TextViewBindingAdapter.setText(this.mboundView9, str7);
            DataBindingHelper.setViewGone(this.tvConfirm, z2);
        }
    }
}
