package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameDetailBean;
import com.cy.yyjia.zhe28.domain.YunIndexBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.lzy.okgo.model.Progress;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityYunPlayBindingImpl extends ActivityYunPlayBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final LinearLayout mboundView1;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.body, 7);
        sparseIntArray.put(R.id.f438tv, 8);
        sparseIntArray.put(R.id.tv_fold, 9);
        sparseIntArray.put(R.id.tv_back, 10);
        sparseIntArray.put(R.id.tv_level, 11);
        sparseIntArray.put(R.id.tv_more, 12);
        sparseIntArray.put(R.id.tv_service, 13);
    }

    public ActivityYunPlayBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }

    private ActivityYunPlayBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (FrameLayout) bindings[7], (TextView) bindings[6], (TextView) bindings[8], (TextView) bindings[10], (TextView) bindings[5], (TextView) bindings[9], (TextView) bindings[11], (TextView) bindings[12], (TextView) bindings[3], (TextView) bindings[4], (TextView) bindings[13], (TextView) bindings[2]);
        this.mDirtyFlags = -1L;
        this.llFloat.setTag(null);
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[1];
        this.mboundView1 = linearLayout;
        linearLayout.setTag(null);
        this.tvDownload.setTag(null);
        this.tvPin.setTag(null);
        this.tvRestart.setTag(null);
        this.tvYunDevice.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 64L;
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
        if (101 == variableId) {
            setShowMenu(((Boolean) variable).booleanValue());
        } else if (79 == variableId) {
            setProgress((Progress) variable);
        } else if (73 == variableId) {
            setPin((String) variable);
        } else if (23 == variableId) {
            setData((GameDetailBean) variable);
        } else if (124 == variableId) {
            setVisitor(((Boolean) variable).booleanValue());
        } else {
            if (43 != variableId) {
                return false;
            }
            setIndex((YunIndexBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityYunPlayBinding
    public void setShowMenu(boolean ShowMenu) {
        this.mShowMenu = ShowMenu;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(101);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityYunPlayBinding
    public void setProgress(Progress Progress) {
        this.mProgress = Progress;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(79);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityYunPlayBinding
    public void setPin(String Pin) {
        this.mPin = Pin;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(73);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityYunPlayBinding
    public void setData(GameDetailBean Data) {
        this.mData = Data;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityYunPlayBinding
    public void setVisitor(boolean Visitor) {
        this.mVisitor = Visitor;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(124);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityYunPlayBinding
    public void setIndex(YunIndexBean Index) {
        this.mIndex = Index;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(43);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeData((GameDetailBean) object, fieldId);
    }

    private boolean onChangeData(GameDetailBean Data, int fieldId) {
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
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        boolean z = this.mShowMenu;
        Progress progress = this.mProgress;
        String str = this.mPin;
        boolean z2 = this.mVisitor;
        YunIndexBean yunIndexBean = this.mIndex;
        long j2 = 66 & j;
        boolean z3 = j2 != 0 ? !z : false;
        long j3 = 68 & j;
        long j4 = 72 & j;
        long j5 = 80 & j;
        boolean z4 = j5 != 0 ? !z2 : false;
        long j6 = j & 96;
        String name = null;
        if (j6 != 0) {
            YunIndexBean.Device selectedDevice = yunIndexBean != null ? yunIndexBean.getSelectedDevice() : null;
            if (selectedDevice != null) {
                name = selectedDevice.getName();
            }
        }
        if (j2 != 0) {
            DataBindingHelper.setViewGone(this.llFloat, z);
            DataBindingHelper.setViewGone(this.mboundView1, z3);
        }
        if (j4 != 0) {
            TextViewBindingAdapter.setText(this.llFloat, str);
            TextViewBindingAdapter.setText(this.tvPin, str);
        }
        if (j3 != 0) {
            DataBindingHelper.setDownloadText(this.tvDownload, progress);
        }
        if (j5 != 0) {
            DataBindingHelper.setViewGone(this.tvDownload, z4);
            DataBindingHelper.setViewGone(this.tvRestart, z2);
            DataBindingHelper.setViewGone(this.tvYunDevice, z2);
        }
        if (j6 != 0) {
            TextViewBindingAdapter.setText(this.tvYunDevice, name);
        }
    }
}
