package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.TaskBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import org.apache.commons.lang3.BooleanUtils;

/* JADX INFO: loaded from: classes2.dex */
public class ItemTaskMonthlyBindingImpl extends ItemTaskMonthlyBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;

    public ItemTaskMonthlyBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private ItemTaskMonthlyBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (TextView) bindings[4]);
        this.mDirtyFlags = -1L;
        this.btn.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[2];
        this.mboundView2 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[3];
        this.mboundView3 = textView3;
        textView3.setTag(null);
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
        setData((TaskBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemTaskMonthlyBinding
    public void setData(TaskBean Data) {
        updateRegistration(0, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeData((TaskBean) object, fieldId);
    }

    private boolean onChangeData(TaskBean Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        if (fieldId != 28) {
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
        String target;
        String str;
        String str2;
        int i;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        TaskBean taskBean = this.mData;
        long j2 = j & 7;
        String str3 = null;
        if (j2 != 0) {
            String done = taskBean != null ? taskBean.getDone() : null;
            zEquals = done != null ? done.equals(BooleanUtils.YES) : false;
            if (j2 != 0) {
                j |= zEquals ? 16L : 8L;
            }
            boolean z = !zEquals;
            int colorFromResource = zEquals ? -3291465 : getColorFromResource(this.btn, R.color.color_text_1);
            if ((j & 5) == 0 || taskBean == null) {
                i = colorFromResource;
                str = null;
                str2 = null;
                zEquals = z;
                target = null;
            } else {
                String money = taskBean.getMoney();
                String experience = taskBean.getExperience();
                String btnText = taskBean.getBtnText();
                target = taskBean.getTarget();
                i = colorFromResource;
                zEquals = z;
                str2 = experience;
                str = money;
                str3 = btnText;
            }
        } else {
            target = null;
            str = null;
            str2 = null;
            i = 0;
        }
        if ((7 & j) != 0) {
            DataBindingHelper.setSelected(this.btn, zEquals);
            this.btn.setTextColor(i);
        }
        if ((j & 5) != 0) {
            TextViewBindingAdapter.setText(this.btn, str3);
            TextViewBindingAdapter.setText(this.mboundView1, target);
            TextViewBindingAdapter.setText(this.mboundView2, str);
            TextViewBindingAdapter.setText(this.mboundView3, str2);
        }
    }
}
