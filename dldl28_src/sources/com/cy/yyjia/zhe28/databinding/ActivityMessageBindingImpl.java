package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.UnreadBean;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityMessageBindingImpl extends ActivityMessageBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ShapeTextView mboundView2;
    private final ShapeTextView mboundView4;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.rv, 7);
    }

    public ActivityMessageBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private ActivityMessageBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ImageView) bindings[6], (RecyclerView) bindings[7], (ShapeTextView) bindings[1], (ShapeTextView) bindings[3], (ShapeTextView) bindings[5]);
        this.mDirtyFlags = -1L;
        this.ivClean.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[2];
        this.mboundView2 = shapeTextView;
        shapeTextView.setTag(null);
        ShapeTextView shapeTextView2 = (ShapeTextView) bindings[4];
        this.mboundView4 = shapeTextView2;
        shapeTextView2.setTag(null);
        this.tv1.setTag(null);
        this.tv2.setTag(null);
        this.tv3.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32L;
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
        if (27 == variableId) {
            setDicker(((Integer) variable).intValue());
        } else if (23 == variableId) {
            setData((UnreadBean) variable);
        } else {
            if (118 != variableId) {
                return false;
            }
            setType(((Integer) variable).intValue());
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityMessageBinding
    public void setDicker(int Dicker) {
        this.mDicker = Dicker;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityMessageBinding
    public void setData(UnreadBean Data) {
        updateRegistration(0, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityMessageBinding
    public void setType(int Type) {
        this.mType = Type;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(118);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeData((UnreadBean) object, fieldId);
    }

    private boolean onChangeData(UnreadBean Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        if (fieldId == 60) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        if (fieldId != 15) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        String strValueOf;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        UnreadBean unreadBean = this.mData;
        int i = this.mType;
        String strValueOf2 = null;
        boolean z6 = true;
        if ((57 & j) != 0) {
            long j2 = j & 49;
            if (j2 != 0) {
                int commentNum = unreadBean != null ? unreadBean.getCommentNum() : 0;
                strValueOf = String.valueOf(commentNum);
                z2 = commentNum < 1;
                if (j2 != 0) {
                    j |= z2 ? 512L : 256L;
                }
            } else {
                strValueOf = null;
                z2 = false;
            }
            long j3 = j & 41;
            if (j3 != 0) {
                int msgNum = unreadBean != null ? unreadBean.getMsgNum() : 0;
                strValueOf2 = String.valueOf(msgNum);
                z = msgNum < 1;
                if (j3 != 0) {
                    j |= z ? 128L : 64L;
                }
            } else {
                z = false;
            }
            String str2 = strValueOf2;
            strValueOf2 = strValueOf;
            str = str2;
        } else {
            str = null;
            z = false;
            z2 = false;
        }
        long j4 = j & 32;
        boolean z7 = j4 != 0 && Constant.INSTANCE.getId() == 0;
        long j5 = j & 36;
        if (j5 != 0) {
            z5 = i == 2;
            z4 = i == 3;
            z3 = i == 1;
        } else {
            z3 = false;
            z4 = false;
            z5 = false;
        }
        if ((j & 320) != 0) {
            z7 = Constant.INSTANCE.getId() == 0;
        }
        boolean z8 = z7;
        long j6 = 41 & j;
        boolean z9 = j6 != 0 ? z ? true : z8 : false;
        long j7 = j & 49;
        if (j7 == 0) {
            z6 = false;
        } else if (!z2) {
            z6 = z8;
        }
        if (j4 != 0) {
            DataBindingHelper.setViewGone(this.ivClean, z8);
            DataBindingHelper.setViewGone(this.tv2, z8);
        }
        if (j6 != 0) {
            DataBindingHelper.setViewGone(this.mboundView2, z9);
            TextViewBindingAdapter.setText(this.mboundView2, str);
        }
        if (j7 != 0) {
            DataBindingHelper.setViewGone(this.mboundView4, z6);
            TextViewBindingAdapter.setText(this.mboundView4, strValueOf2);
        }
        if (j5 != 0) {
            DataBindingHelper.setBold(this.tv1, z3);
            DataBindingHelper.setSelected(this.tv1, z3);
            DataBindingHelper.setBold(this.tv2, z5);
            DataBindingHelper.setSelected(this.tv2, z5);
            DataBindingHelper.setBold(this.tv3, z4);
            DataBindingHelper.setSelected(this.tv3, z4);
        }
    }
}
