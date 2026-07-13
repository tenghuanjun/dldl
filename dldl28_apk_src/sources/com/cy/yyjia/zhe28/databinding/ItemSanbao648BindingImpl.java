package com.cy.yyjia.zhe28.databinding;

import android.text.SpannableString;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.Converters;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.NoviceGameBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ItemSanbao648BindingImpl extends ItemSanbao648Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private OnClickListenerImpl mDataGotoGameAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final LayoutGameIconBinding mboundView0;
    private final LinearLayout mboundView01;
    private final RelativeLayout mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final ShapeTextView mboundView4;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(10);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"layout_game_icon"}, new int[]{7}, new int[]{R.layout.layout_game_icon});
        includedLayouts.setIncludes(1, new String[]{"layout_game_name"}, new int[]{8}, new int[]{R.layout.layout_game_name});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.desc, 9);
    }

    public ItemSanbao648BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private ItemSanbao648BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (TextView) bindings[6], (LinearLayout) bindings[9], (LayoutGameNameBinding) bindings[8], (LinearLayout) bindings[5]);
        this.mDirtyFlags = -1L;
        this.btn.setTag(null);
        LayoutGameIconBinding layoutGameIconBinding = (LayoutGameIconBinding) bindings[7];
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
        ShapeTextView shapeTextView = (ShapeTextView) bindings[4];
        this.mboundView4 = shapeTextView;
        shapeTextView.setTag(null);
        setContainedBinding(this.name);
        this.tag.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
        }
        this.mboundView0.invalidateAll();
        this.name.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.mboundView0.hasPendingBindings() || this.name.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (23 != variableId) {
            return false;
        }
        setData((NoviceGameBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemSanbao648Binding
    public void setData(NoviceGameBean Data) {
        updateRegistration(1, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.mboundView0.setLifecycleOwner(lifecycleOwner);
        this.name.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId == 0) {
            return onChangeName((LayoutGameNameBinding) object, fieldId);
        }
        if (localFieldId != 1) {
            return false;
        }
        return onChangeData((NoviceGameBean) object, fieldId);
    }

    private boolean onChangeName(LayoutGameNameBinding Name, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeData(NoviceGameBean Data, int fieldId) {
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
        Integer num;
        OnClickListenerImpl value;
        List<GameBean.Tag> tags;
        boolean z;
        String str;
        boolean z2;
        boolean z3;
        String score;
        SpannableString descNew;
        int colorFromResource;
        String str2;
        String str3;
        String firstRechargeDiscount;
        Integer unclaimed;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        NoviceGameBean noviceGameBean = this.mData;
        long j2 = j & 6;
        if (j2 != 0) {
            if (noviceGameBean != null) {
                OnClickListenerImpl onClickListenerImpl = this.mDataGotoGameAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mDataGotoGameAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                value = onClickListenerImpl.setValue(noviceGameBean);
                tags = noviceGameBean.getTags();
                firstRechargeDiscount = noviceGameBean.getFirstRechargeDiscount();
                score = noviceGameBean.getScore();
                descNew = noviceGameBean.getDescNew();
                unclaimed = noviceGameBean.getUnclaimed();
            } else {
                value = null;
                tags = null;
                firstRechargeDiscount = null;
                score = null;
                descNew = null;
                unclaimed = null;
            }
            String str4 = firstRechargeDiscount + "折";
            boolean zEquals = "0".equals(firstRechargeDiscount);
            int iSafeUnbox = ViewDataBinding.safeUnbox(unclaimed);
            boolean z4 = iSafeUnbox == 0;
            boolean z5 = iSafeUnbox != 0;
            if (j2 != 0) {
                j |= z4 ? 80L : 40L;
            }
            num = unclaimed;
            z3 = z5;
            colorFromResource = z4 ? -1052689 : getColorFromResource(this.btn, R.color.colorPrimary);
            z2 = zEquals;
            str = str4;
            z = z4;
        } else {
            num = null;
            value = null;
            tags = null;
            z = false;
            str = null;
            z2 = false;
            z3 = false;
            score = null;
            descNew = null;
            colorFromResource = 0;
        }
        if ((j & 8) != 0) {
            str2 = num + "个待领";
        } else {
            str2 = null;
        }
        long j3 = j & 6;
        if (j3 != 0) {
            str3 = z ? "已领取" : str2;
        } else {
            str3 = null;
        }
        if (j3 != 0) {
            this.btn.setEnabled(z3);
            TextViewBindingAdapter.setText(this.btn, str3);
            this.mboundView0.setData(noviceGameBean);
            this.mboundView01.setOnClickListener(value);
            TextViewBindingAdapter.setText(this.mboundView2, score);
            TextViewBindingAdapter.setText(this.mboundView3, descNew);
            DataBindingHelper.setViewGone(this.mboundView4, z2);
            TextViewBindingAdapter.setText(this.mboundView4, str);
            this.name.setData(noviceGameBean);
            DataBindingHelper.setTags(this.tag, tags);
            if (getBuildSdkInt() >= 21) {
                this.btn.setBackgroundTintList(Converters.convertColorToColorStateList(colorFromResource));
            }
        }
        executeBindingsOn(this.mboundView0);
        executeBindingsOn(this.name);
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private NoviceGameBean value;

        public OnClickListenerImpl setValue(NoviceGameBean value) {
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
