package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.CountdownView;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityPhoneBindingImpl extends ActivityPhoneBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView2;
    private final EditText mboundView3;
    private InverseBindingListener mboundView3androidTextAttrChanged;
    private final EditText mboundView4;
    private InverseBindingListener mboundView4androidTextAttrChanged;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.btn_code, 6);
    }

    public ActivityPhoneBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private ActivityPhoneBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (CountdownView) bindings[6], (Navigation) bindings[1], (ShapeTextView) bindings[5]);
        this.mboundView3androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityPhoneBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityPhoneBindingImpl.this.mboundView3);
                String str = ActivityPhoneBindingImpl.this.mPhone;
                ActivityPhoneBindingImpl activityPhoneBindingImpl = ActivityPhoneBindingImpl.this;
                if (activityPhoneBindingImpl != null) {
                    activityPhoneBindingImpl.setPhone(textString);
                }
            }
        };
        this.mboundView4androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityPhoneBindingImpl.2
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityPhoneBindingImpl.this.mboundView4);
                String str = ActivityPhoneBindingImpl.this.mCode;
                ActivityPhoneBindingImpl activityPhoneBindingImpl = ActivityPhoneBindingImpl.this;
                if (activityPhoneBindingImpl != null) {
                    activityPhoneBindingImpl.setCode(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        EditText editText = (EditText) bindings[3];
        this.mboundView3 = editText;
        editText.setTag(null);
        EditText editText2 = (EditText) bindings[4];
        this.mboundView4 = editText2;
        editText2.setTag(null);
        this.navigation.setTag(null);
        this.tvGo.setTag(null);
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
        if (115 == variableId) {
            setTitle2((String) variable);
        } else if (14 == variableId) {
            setCode((String) variable);
        } else if (72 == variableId) {
            setPhone((String) variable);
        } else if (9 == variableId) {
            setBtn((String) variable);
        } else {
            if (114 != variableId) {
                return false;
            }
            setTitle((String) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityPhoneBinding
    public void setTitle2(String Title2) {
        this.mTitle2 = Title2;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(115);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityPhoneBinding
    public void setCode(String Code) {
        this.mCode = Code;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(14);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityPhoneBinding
    public void setPhone(String Phone) {
        this.mPhone = Phone;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(72);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityPhoneBinding
    public void setBtn(String Btn) {
        this.mBtn = Btn;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(9);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityPhoneBinding
    public void setTitle(String Title) {
        this.mTitle = Title;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(114);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str = this.mTitle2;
        String str2 = this.mCode;
        String str3 = this.mPhone;
        String str4 = this.mBtn;
        String str5 = this.mTitle;
        long j2 = 34 & j;
        long j3 = 36 & j;
        long j4 = 40 & j;
        long j5 = 48 & j;
        if ((33 & j) != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, str);
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.mboundView3, str3);
        }
        if ((j & 32) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.mboundView3, null, null, null, this.mboundView3androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView4, null, null, null, this.mboundView4androidTextAttrChanged);
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView4, str2);
        }
        if (j5 != 0) {
            this.navigation.setTitle(str5);
        }
        if (j4 != 0) {
            TextViewBindingAdapter.setText(this.tvGo, str4);
        }
    }
}
