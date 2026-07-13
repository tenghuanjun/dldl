package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BtnBean;
import com.cy.yyjia.zhe28.domain.CollectionBean;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.GroupBuyBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.layout.ShapeRelativeLayout;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ItemHomeGroupBindingImpl extends ItemHomeGroupBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private OnClickListenerImpl mDataGroupbuyLinkOnClickAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final ShapeRelativeLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.rv_group_user, 6);
    }

    public ItemHomeGroupBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private ItemHomeGroupBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (RecyclerView) bindings[5], (RecyclerView) bindings[6]);
        this.mDirtyFlags = -1L;
        ShapeRelativeLayout shapeRelativeLayout = (ShapeRelativeLayout) bindings[0];
        this.mboundView0 = shapeRelativeLayout;
        shapeRelativeLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[2];
        this.mboundView2 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[3];
        this.mboundView3 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[4];
        this.mboundView4 = textView4;
        textView4.setTag(null);
        this.rvGroupGame.setTag(null);
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
        setData((CollectionBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemHomeGroupBinding
    public void setData(CollectionBean Data) {
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
        List<GameBean> games;
        String desc;
        String title;
        String discount;
        String nums;
        GroupBuyBean.Next next;
        BtnBean link;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        CollectionBean collectionBean = this.mData;
        long j2 = j & 3;
        OnClickListenerImpl value = null;
        if (j2 != 0) {
            GroupBuyBean groupbuy = collectionBean != null ? collectionBean.getGroupbuy() : null;
            if (groupbuy != null) {
                desc = groupbuy.getDesc();
                title = groupbuy.getTitle();
                next = groupbuy.getNext();
                link = groupbuy.getLink();
                games = groupbuy.getGames();
            } else {
                games = null;
                desc = null;
                title = null;
                next = null;
                link = null;
            }
            if (next != null) {
                nums = next.getNums();
                discount = next.getDiscount();
            } else {
                discount = null;
                nums = null;
            }
            if (link != null) {
                OnClickListenerImpl onClickListenerImpl = this.mDataGroupbuyLinkOnClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mDataGroupbuyLinkOnClickAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                value = onClickListenerImpl.setValue(link);
            }
        } else {
            games = null;
            desc = null;
            title = null;
            discount = null;
            nums = null;
        }
        if (j2 != 0) {
            this.mboundView0.setOnClickListener(value);
            TextViewBindingAdapter.setText(this.mboundView1, title);
            TextViewBindingAdapter.setText(this.mboundView2, desc);
            TextViewBindingAdapter.setText(this.mboundView3, nums);
            TextViewBindingAdapter.setText(this.mboundView4, discount);
            DataBindingHelper.setRvData(this.rvGroupGame, games);
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
