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
public class ActivityChangePasswordBindingImpl extends ActivityChangePasswordBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final EditText mboundView1;
    private InverseBindingListener mboundView1androidTextAttrChanged;
    private final EditText mboundView2;
    private InverseBindingListener mboundView2androidTextAttrChanged;
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
        sparseIntArray.put(R.id.navigation, 5);
        sparseIntArray.put(R.id.tv_title, 6);
        sparseIntArray.put(R.id.btn_code, 7);
        sparseIntArray.put(R.id.tv_go, 8);
    }

    public ActivityChangePasswordBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private ActivityChangePasswordBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (CountdownView) bindings[7], (Navigation) bindings[5], (ShapeTextView) bindings[8], (TextView) bindings[6]);
        this.mboundView1androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityChangePasswordBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityChangePasswordBindingImpl.this.mboundView1);
                String str = ActivityChangePasswordBindingImpl.this.mPhone;
                ActivityChangePasswordBindingImpl activityChangePasswordBindingImpl = ActivityChangePasswordBindingImpl.this;
                if (activityChangePasswordBindingImpl != null) {
                    activityChangePasswordBindingImpl.setPhone(textString);
                }
            }
        };
        this.mboundView2androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityChangePasswordBindingImpl.2
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityChangePasswordBindingImpl.this.mboundView2);
                String str = ActivityChangePasswordBindingImpl.this.mCode;
                ActivityChangePasswordBindingImpl activityChangePasswordBindingImpl = ActivityChangePasswordBindingImpl.this;
                if (activityChangePasswordBindingImpl != null) {
                    activityChangePasswordBindingImpl.setCode(textString);
                }
            }
        };
        this.mboundView3androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityChangePasswordBindingImpl.3
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityChangePasswordBindingImpl.this.mboundView3);
                String str = ActivityChangePasswordBindingImpl.this.mPassword;
                ActivityChangePasswordBindingImpl activityChangePasswordBindingImpl = ActivityChangePasswordBindingImpl.this;
                if (activityChangePasswordBindingImpl != null) {
                    activityChangePasswordBindingImpl.setPassword(textString);
                }
            }
        };
        this.mboundView4androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityChangePasswordBindingImpl.4
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityChangePasswordBindingImpl.this.mboundView4);
                String str = ActivityChangePasswordBindingImpl.this.mPassword2;
                ActivityChangePasswordBindingImpl activityChangePasswordBindingImpl = ActivityChangePasswordBindingImpl.this;
                if (activityChangePasswordBindingImpl != null) {
                    activityChangePasswordBindingImpl.setPassword2(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        EditText editText = (EditText) bindings[1];
        this.mboundView1 = editText;
        editText.setTag(null);
        EditText editText2 = (EditText) bindings[2];
        this.mboundView2 = editText2;
        editText2.setTag(null);
        EditText editText3 = (EditText) bindings[3];
        this.mboundView3 = editText3;
        editText3.setTag(null);
        EditText editText4 = (EditText) bindings[4];
        this.mboundView4 = editText4;
        editText4.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
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
        if (70 == variableId) {
            setPassword((String) variable);
        } else if (14 == variableId) {
            setCode((String) variable);
        } else if (72 == variableId) {
            setPhone((String) variable);
        } else {
            if (71 != variableId) {
                return false;
            }
            setPassword2((String) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityChangePasswordBinding
    public void setPassword(String Password) {
        this.mPassword = Password;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(70);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityChangePasswordBinding
    public void setCode(String Code) {
        this.mCode = Code;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(14);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityChangePasswordBinding
    public void setPhone(String Phone) {
        this.mPhone = Phone;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(72);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityChangePasswordBinding
    public void setPassword2(String Password2) {
        this.mPassword2 = Password2;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(71);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str = this.mPassword;
        String str2 = this.mCode;
        String str3 = this.mPhone;
        String str4 = this.mPassword2;
        long j2 = 17 & j;
        long j3 = 18 & j;
        long j4 = 24 & j;
        if ((20 & j) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, str3);
        }
        if ((j & 16) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.mboundView1, null, null, null, this.mboundView1androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView2, null, null, null, this.mboundView2androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView3, null, null, null, this.mboundView3androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView4, null, null, null, this.mboundView4androidTextAttrChanged);
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, str2);
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView3, str);
        }
        if (j4 != 0) {
            TextViewBindingAdapter.setText(this.mboundView4, str4);
        }
    }
}
