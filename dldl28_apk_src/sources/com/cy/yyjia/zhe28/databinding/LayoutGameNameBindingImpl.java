package com.cy.yyjia.zhe28.databinding;

import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class LayoutGameNameBindingImpl extends LayoutGameNameBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ShapeTextView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;

    public LayoutGameNameBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private LayoutGameNameBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[1];
        this.mboundView1 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[3];
        this.mboundView3 = textView2;
        textView2.setTag(null);
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
        if (89 == variableId) {
            setSearch(((Boolean) variable).booleanValue());
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((GameBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.LayoutGameNameBinding
    public void setSearch(boolean Search) {
        this.mSearch = Search;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(89);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.LayoutGameNameBinding
    public void setData(GameBean Data) {
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
        return onChangeData((GameBean) object, fieldId);
    }

    private boolean onChangeData(GameBean Data, int fieldId) {
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
        String str;
        String str2;
        boolean zIsEmpty;
        boolean zIsEmpty2;
        boolean z;
        String showName;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        boolean z2 = this.mSearch;
        GameBean gameBean = this.mData;
        long j2 = j & 7;
        String name_mid = null;
        if (j2 != 0) {
            String name_suffix = gameBean != null ? gameBean.getName_suffix() : null;
            zIsEmpty = TextUtils.isEmpty(name_suffix);
            if (j2 != 0) {
                j = zIsEmpty ? j | 16 : j | 8;
            }
            if ((j & 5) != 0) {
                if (gameBean != null) {
                    name_mid = gameBean.getName_mid();
                    showName = gameBean.getShowName();
                } else {
                    showName = null;
                }
                zIsEmpty2 = TextUtils.isEmpty(name_mid);
                String str3 = name_suffix;
                str2 = showName;
                str = name_mid;
                name_mid = str3;
            } else {
                str = null;
                zIsEmpty2 = false;
                name_mid = name_suffix;
                str2 = null;
            }
        } else {
            str = null;
            str2 = null;
            zIsEmpty = false;
            zIsEmpty2 = false;
        }
        boolean z3 = (8 & j) != 0 ? !z2 : false;
        long j3 = 7 & j;
        if (j3 != 0) {
            z = zIsEmpty ? true : z3;
        } else {
            z = false;
        }
        if (j3 != 0) {
            DataBindingHelper.setViewGone(this.mboundView1, z);
        }
        if ((5 & j) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, name_mid);
            TextViewBindingAdapter.setText(this.mboundView2, str2);
            DataBindingHelper.setViewGone(this.mboundView3, zIsEmpty2);
            TextViewBindingAdapter.setText(this.mboundView3, str);
        }
        if ((j & 4) != 0) {
            DataBindingHelper.setSelected(this.mboundView2, true);
        }
    }
}
