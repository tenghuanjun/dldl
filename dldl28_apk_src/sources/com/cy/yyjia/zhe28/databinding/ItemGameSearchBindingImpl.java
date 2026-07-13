package com.cy.yyjia.zhe28.databinding;

import android.text.SpannableString;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ItemGameSearchBindingImpl extends ItemGameSearchBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LayoutGameIconBinding mboundView0;
    private final LinearLayout mboundView01;
    private final RelativeLayout mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(10);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"layout_game_icon", "layout_discount"}, new int[]{6, 8}, new int[]{R.layout.layout_game_icon, R.layout.layout_discount});
        includedLayouts.setIncludes(1, new String[]{"layout_game_name"}, new int[]{7}, new int[]{R.layout.layout_game_name});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.desc, 9);
    }

    public ItemGameSearchBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private ItemGameSearchBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (LinearLayout) bindings[9], (LayoutDiscountBinding) bindings[8], (LayoutGameNameBinding) bindings[7], (LinearLayout) bindings[5]);
        this.mDirtyFlags = -1L;
        setContainedBinding(this.discount);
        LayoutGameIconBinding layoutGameIconBinding = (LayoutGameIconBinding) bindings[6];
        this.mboundView0 = layoutGameIconBinding;
        setContainedBinding(layoutGameIconBinding);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView01 = linearLayout;
        linearLayout.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[1];
        this.mboundView1 = relativeLayout;
        relativeLayout.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[3];
        this.mboundView3 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[4];
        this.mboundView4 = textView3;
        textView3.setTag(null);
        setContainedBinding(this.name);
        this.tag.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
        }
        this.mboundView0.invalidateAll();
        this.name.invalidateAll();
        this.discount.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.mboundView0.hasPendingBindings() || this.name.hasPendingBindings() || this.discount.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (23 != variableId) {
            return false;
        }
        setData((GameBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemGameSearchBinding
    public void setData(GameBean Data) {
        updateRegistration(2, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.mboundView0.setLifecycleOwner(lifecycleOwner);
        this.name.setLifecycleOwner(lifecycleOwner);
        this.discount.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId == 0) {
            return onChangeDiscount((LayoutDiscountBinding) object, fieldId);
        }
        if (localFieldId == 1) {
            return onChangeName((LayoutGameNameBinding) object, fieldId);
        }
        if (localFieldId != 2) {
            return false;
        }
        return onChangeData((GameBean) object, fieldId);
    }

    private boolean onChangeDiscount(LayoutDiscountBinding Discount, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeName(LayoutGameNameBinding Name, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeData(GameBean Data, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean zIsEmpty;
        String str;
        List<GameBean.Tag> list;
        SpannableString descNew;
        List<GameBean.Tag> tags;
        String str2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        GameBean gameBean = this.mData;
        long j2 = 12 & j;
        String str3 = null;
        if (j2 != 0) {
            if (gameBean != null) {
                tags = gameBean.getTags();
                String service_tip = gameBean.getService_tip();
                String score = gameBean.getScore();
                descNew = gameBean.getDescNew();
                str3 = service_tip;
                str2 = score;
            } else {
                tags = null;
                str2 = null;
                descNew = null;
            }
            zIsEmpty = TextUtils.isEmpty(str3);
            String str4 = str2;
            list = tags;
            str = str3;
            str3 = str4;
        } else {
            zIsEmpty = false;
            str = null;
            list = null;
            descNew = null;
        }
        if (j2 != 0) {
            this.discount.setData(gameBean);
            this.mboundView0.setData(gameBean);
            TextViewBindingAdapter.setText(this.mboundView2, str3);
            TextViewBindingAdapter.setText(this.mboundView3, descNew);
            TextViewBindingAdapter.setText(this.mboundView4, str);
            DataBindingHelper.setViewGone(this.mboundView4, zIsEmpty);
            this.name.setData(gameBean);
            DataBindingHelper.setTags(this.tag, list);
        }
        if ((j & 8) != 0) {
            this.name.setSearch(true);
        }
        executeBindingsOn(this.mboundView0);
        executeBindingsOn(this.name);
        executeBindingsOn(this.discount);
    }
}
