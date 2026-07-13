package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.AppInfo;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;
import com.lzy.okgo.model.Progress;

/* JADX INFO: loaded from: classes2.dex */
public class ItemDownloadBindingImpl extends ItemDownloadBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final ProgressBar mboundView5;
    private final ImageView mboundView7;

    public ItemDownloadBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private ItemDownloadBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ShapeTextView) bindings[6]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[3];
        this.mboundView3 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[4];
        this.mboundView4 = textView3;
        textView3.setTag(null);
        ProgressBar progressBar = (ProgressBar) bindings[5];
        this.mboundView5 = progressBar;
        progressBar.setTag(null);
        ImageView imageView2 = (ImageView) bindings[7];
        this.mboundView7 = imageView2;
        imageView2.setTag(null);
        this.f463tv.setTag(null);
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
        if (54 == variableId) {
            setManager(((Boolean) variable).booleanValue());
        } else if (79 == variableId) {
            setProgress((Progress) variable);
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((AppInfo) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemDownloadBinding
    public void setManager(boolean Manager) {
        this.mManager = Manager;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(54);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemDownloadBinding
    public void setProgress(Progress Progress) {
        this.mProgress = Progress;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(79);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemDownloadBinding
    public void setData(AppInfo Data) {
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
        return onChangeData((AppInfo) object, fieldId);
    }

    private boolean onChangeData(AppInfo Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        if (fieldId == 22) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        if (fieldId == 79) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        if (fieldId != 94) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        String str2;
        String str3;
        String str4;
        int progress;
        boolean selected;
        String str5;
        String version;
        String icon;
        String totalSize;
        String currentSize;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        boolean z = this.mManager;
        Progress progress2 = this.mProgress;
        AppInfo appInfo = this.mData;
        long j2 = 66 & j;
        boolean z2 = j2 != 0 ? !z : false;
        long j3 = 68 & j;
        String name = null;
        if ((121 & j) != 0) {
            if ((j & 73) != 0) {
                if (appInfo != null) {
                    totalSize = appInfo.getTotalSize();
                    currentSize = appInfo.getCurrentSize();
                } else {
                    totalSize = null;
                    currentSize = null;
                }
                str5 = (currentSize + "/") + totalSize;
            } else {
                str5 = null;
            }
            selected = ((j & 97) == 0 || appInfo == null) ? false : appInfo.getSelected();
            if ((j & 65) == 0 || appInfo == null) {
                version = null;
                icon = null;
            } else {
                name = appInfo.getName();
                version = appInfo.getVersion();
                icon = appInfo.getIcon();
            }
            if ((j & 81) == 0 || appInfo == null) {
                str4 = str5;
                str3 = version;
                str2 = name;
                str = icon;
                progress = 0;
            } else {
                str4 = str5;
                str3 = version;
                str2 = name;
                progress = appInfo.getProgress();
                str = icon;
            }
        } else {
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
            progress = 0;
            selected = false;
        }
        if ((j & 65) != 0) {
            DataBindingHelper.setGameIcon(this.mboundView1, str);
            TextViewBindingAdapter.setText(this.mboundView2, str2);
            TextViewBindingAdapter.setText(this.mboundView3, str3);
        }
        if ((j & 73) != 0) {
            TextViewBindingAdapter.setText(this.mboundView4, str4);
        }
        if (j2 != 0) {
            DataBindingHelper.setViewGone(this.mboundView5, z);
            DataBindingHelper.setViewGone(this.mboundView7, z2);
            DataBindingHelper.setViewGone(this.f463tv, z);
        }
        if ((81 & j) != 0) {
            this.mboundView5.setProgress(progress);
        }
        if ((j & 97) != 0) {
            DataBindingHelper.setSelected(this.mboundView7, selected);
        }
        if (j3 != 0) {
            DataBindingHelper.setDownloadText(this.f463tv, progress2);
        }
    }
}
