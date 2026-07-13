package com.cy.yyjia.zhe28.databinding;

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
public class ItemHomeRankBindingImpl extends ItemHomeRankBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private OnClickListenerImpl mDataGotoGameAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final LayoutGameIconBinding mboundView0;
    private final TextView mboundView1;
    private final RelativeLayout mboundView2;
    private final TextView mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView7;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(12);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"layout_game_icon", "layout_discount"}, new int[]{8, 10}, new int[]{R.layout.layout_game_icon, R.layout.layout_discount});
        includedLayouts.setIncludes(2, new String[]{"layout_game_name"}, new int[]{9}, new int[]{R.layout.layout_game_name});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.desc, 11);
    }

    public ItemHomeRankBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    private ItemHomeRankBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (LinearLayout) bindings[11], (LayoutDiscountBinding) bindings[10], (LinearLayout) bindings[0], (LayoutGameNameBinding) bindings[9], (LinearLayout) bindings[3], (LinearLayout) bindings[4]);
        this.mDirtyFlags = -1L;
        setContainedBinding(this.discount);
        this.ll.setTag(null);
        LayoutGameIconBinding layoutGameIconBinding = (LayoutGameIconBinding) bindings[8];
        this.mboundView0 = layoutGameIconBinding;
        setContainedBinding(layoutGameIconBinding);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[2];
        this.mboundView2 = relativeLayout;
        relativeLayout.setTag(null);
        TextView textView2 = (TextView) bindings[5];
        this.mboundView5 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[6];
        this.mboundView6 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[7];
        this.mboundView7 = textView4;
        textView4.setTag(null);
        setContainedBinding(this.name);
        this.tag.setTag(null);
        this.tag2.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
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
        if (74 == variableId) {
            setPosition(((Integer) variable).intValue());
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((GameBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemHomeRankBinding
    public void setPosition(int Position) {
        this.mPosition = Position;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(74);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemHomeRankBinding
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
        String str;
        String str2;
        List<GameBean.Tag> list;
        List<String> category_list;
        OnClickListenerImpl value;
        List<GameBean.Tag> tags;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int i = this.mPosition;
        GameBean gameBean = this.mData;
        long j2 = 24 & j;
        OnClickListenerImpl onClickListenerImpl = null;
        String str3 = null;
        String strValueOf = j2 != 0 ? String.valueOf(i + 1) : null;
        long j3 = j & 20;
        boolean z = false;
        int playNum = 0;
        if (j3 != 0) {
            if (gameBean != null) {
                OnClickListenerImpl onClickListenerImpl2 = this.mDataGotoGameAndroidViewViewOnClickListener;
                if (onClickListenerImpl2 == null) {
                    onClickListenerImpl2 = new OnClickListenerImpl();
                    this.mDataGotoGameAndroidViewViewOnClickListener = onClickListenerImpl2;
                }
                value = onClickListenerImpl2.setValue(gameBean);
                tags = gameBean.getTags();
                String service_tip = gameBean.getService_tip();
                category_list = gameBean.getCategory_list();
                playNum = gameBean.getPlayNum();
                str3 = service_tip;
            } else {
                value = null;
                tags = null;
                category_list = null;
            }
            boolean zIsEmpty = TextUtils.isEmpty(str3);
            String str4 = str3;
            onClickListenerImpl = value;
            str = playNum + "人在玩";
            z = zIsEmpty;
            list = tags;
            str2 = str4;
        } else {
            str = null;
            str2 = null;
            list = null;
            category_list = null;
        }
        if (j3 != 0) {
            this.discount.setData(gameBean);
            this.ll.setOnClickListener(onClickListenerImpl);
            this.mboundView0.setData(gameBean);
            TextViewBindingAdapter.setText(this.mboundView5, str);
            DataBindingHelper.setViewGone(this.mboundView6, z);
            DataBindingHelper.setViewGone(this.mboundView7, z);
            TextViewBindingAdapter.setText(this.mboundView7, str2);
            this.name.setData(gameBean);
            DataBindingHelper.setTags(this.tag, list);
            DataBindingHelper.setTags2(this.tag2, category_list);
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, strValueOf);
        }
        executeBindingsOn(this.mboundView0);
        executeBindingsOn(this.name);
        executeBindingsOn(this.discount);
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private GameBean value;

        public OnClickListenerImpl setValue(GameBean value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.gotoGame(arg0);
        }
    }
}
