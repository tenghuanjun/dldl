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
import com.cy.yyjia.zhe28.domain.ItemTradeRecordBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemItemSellOrderBindingImpl extends ItemItemSellOrderBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final ShapeTextView mboundView10;
    private final TextView mboundView11;
    private final TextView mboundView12;
    private final ShapeTextView mboundView13;
    private final TextView mboundView14;
    private final TextView mboundView15;
    private final LinearLayout mboundView16;
    private final ShapeTextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView7;
    private final TextView mboundView8;
    private final TextView mboundView9;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.ll_role, 17);
        sparseIntArray.put(R.id.ll_role_id, 18);
        sparseIntArray.put(R.id.tv_cancel, 19);
        sparseIntArray.put(R.id.tv_go, 20);
    }

    public ItemItemSellOrderBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 21, sIncludes, sViewsWithIds));
    }

    private ItemItemSellOrderBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (LinearLayout) bindings[17], (LinearLayout) bindings[18], (ShapeTextView) bindings[19], (ShapeTextView) bindings[20]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[10];
        this.mboundView10 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView = (TextView) bindings[11];
        this.mboundView11 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[12];
        this.mboundView12 = textView2;
        textView2.setTag(null);
        ShapeTextView shapeTextView2 = (ShapeTextView) bindings[13];
        this.mboundView13 = shapeTextView2;
        shapeTextView2.setTag(null);
        TextView textView3 = (TextView) bindings[14];
        this.mboundView14 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[15];
        this.mboundView15 = textView4;
        textView4.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[16];
        this.mboundView16 = linearLayout2;
        linearLayout2.setTag(null);
        ShapeTextView shapeTextView3 = (ShapeTextView) bindings[2];
        this.mboundView2 = shapeTextView3;
        shapeTextView3.setTag(null);
        TextView textView5 = (TextView) bindings[3];
        this.mboundView3 = textView5;
        textView5.setTag(null);
        TextView textView6 = (TextView) bindings[4];
        this.mboundView4 = textView6;
        textView6.setTag(null);
        TextView textView7 = (TextView) bindings[5];
        this.mboundView5 = textView7;
        textView7.setTag(null);
        TextView textView8 = (TextView) bindings[6];
        this.mboundView6 = textView8;
        textView8.setTag(null);
        TextView textView9 = (TextView) bindings[7];
        this.mboundView7 = textView9;
        textView9.setTag(null);
        TextView textView10 = (TextView) bindings[8];
        this.mboundView8 = textView10;
        textView10.setTag(null);
        TextView textView11 = (TextView) bindings[9];
        this.mboundView9 = textView11;
        textView11.setTag(null);
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

    @Override // com.cy.yyjia.zhe28.databinding.ItemItemSellOrderBinding
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
        String str;
        String statusName;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String name;
        String str7;
        boolean z;
        boolean z2;
        boolean z3;
        String roleId;
        ItemTradeRecordBean.Game game;
        String money;
        String roleName;
        String serviceCode;
        String payTime;
        String deliverTime;
        ItemTradeRecordBean.Asset asset;
        int num;
        int status;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        ItemTradeRecordBean itemTradeRecordBean = this.mData;
        long j2 = j & 3;
        String pic = null;
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
                roleId = itemTradeRecordBean.getRoleId();
            } else {
                roleId = null;
                game = null;
                statusName = null;
                money = null;
                roleName = null;
                serviceCode = null;
                payTime = null;
                deliverTime = null;
                asset = null;
                num = 0;
                status = 0;
            }
            String name2 = game != null ? game.getName() : null;
            String str8 = "待发货数量：" + num;
            str3 = "￥" + money;
            String str9 = "付款时间：" + payTime;
            boolean z4 = status != 2;
            boolean z5 = status != 1;
            z3 = status == 2;
            str6 = "发货时间：" + deliverTime;
            if (asset != null) {
                pic = asset.getPic();
                name = asset.getName();
            } else {
                name = null;
            }
            str5 = (str8 + "丨") + serviceCode;
            str2 = str9;
            str7 = roleName;
            z2 = z4;
            str4 = name2;
            str = roleId;
            z = z5;
        } else {
            str = null;
            statusName = null;
            str2 = null;
            str3 = null;
            str4 = null;
            str5 = null;
            str6 = null;
            name = null;
            str7 = null;
            z = false;
            z2 = false;
            z3 = false;
        }
        if (j2 != 0) {
            DataBindingHelper.setGameIcon(this.mboundView1, pic);
            this.mboundView10.setEnabled(z2);
            TextViewBindingAdapter.setText(this.mboundView11, str);
            this.mboundView11.setEnabled(z2);
            DataBindingHelper.setViewGone(this.mboundView12, z3);
            this.mboundView13.setEnabled(z2);
            TextViewBindingAdapter.setText(this.mboundView13, statusName);
            DataBindingHelper.setViewGone(this.mboundView14, z);
            TextViewBindingAdapter.setText(this.mboundView14, str2);
            DataBindingHelper.setViewGone(this.mboundView15, z2);
            TextViewBindingAdapter.setText(this.mboundView15, str6);
            DataBindingHelper.setViewGone(this.mboundView16, z);
            this.mboundView2.setEnabled(z2);
            TextViewBindingAdapter.setText(this.mboundView2, statusName);
            TextViewBindingAdapter.setText(this.mboundView3, name);
            TextViewBindingAdapter.setText(this.mboundView4, str3);
            TextViewBindingAdapter.setText(this.mboundView5, str4);
            TextViewBindingAdapter.setText(this.mboundView6, str5);
            this.mboundView7.setEnabled(z2);
            TextViewBindingAdapter.setText(this.mboundView8, str7);
            this.mboundView8.setEnabled(z2);
            DataBindingHelper.setViewGone(this.mboundView9, z3);
        }
    }
}
