package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.CountdownView;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityRegisterBindingImpl extends ActivityRegisterBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final NestedScrollView mboundView0;
    private final EditText mboundView1;
    private InverseBindingListener mboundView1androidTextAttrChanged;
    private final EditText mboundView2;
    private InverseBindingListener mboundView2androidTextAttrChanged;
    private final EditText mboundView3;
    private InverseBindingListener mboundView3androidTextAttrChanged;
    private final EditText mboundView4;
    private InverseBindingListener mboundView4androidTextAttrChanged;
    private final EditText mboundView5;
    private InverseBindingListener mboundView5androidTextAttrChanged;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.btn_code, 6);
        sparseIntArray.put(R.id.tv_register, 7);
        sparseIntArray.put(R.id.tv_finish, 8);
    }

    public ActivityRegisterBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private ActivityRegisterBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (CountdownView) bindings[6], (TextView) bindings[8], (ShapeTextView) bindings[7]);
        this.mboundView1androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityRegisterBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityRegisterBindingImpl.this.mboundView1);
                String str = ActivityRegisterBindingImpl.this.mPhone;
                ActivityRegisterBindingImpl activityRegisterBindingImpl = ActivityRegisterBindingImpl.this;
                if (activityRegisterBindingImpl != null) {
                    activityRegisterBindingImpl.setPhone(textString);
                }
            }
        };
        this.mboundView2androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityRegisterBindingImpl.2
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityRegisterBindingImpl.this.mboundView2);
                String str = ActivityRegisterBindingImpl.this.mPassword;
                ActivityRegisterBindingImpl activityRegisterBindingImpl = ActivityRegisterBindingImpl.this;
                if (activityRegisterBindingImpl != null) {
                    activityRegisterBindingImpl.setPassword(textString);
                }
            }
        };
        this.mboundView3androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityRegisterBindingImpl.3
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityRegisterBindingImpl.this.mboundView3);
                String str = ActivityRegisterBindingImpl.this.mPassword2;
                ActivityRegisterBindingImpl activityRegisterBindingImpl = ActivityRegisterBindingImpl.this;
                if (activityRegisterBindingImpl != null) {
                    activityRegisterBindingImpl.setPassword2(textString);
                }
            }
        };
        this.mboundView4androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityRegisterBindingImpl.4
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityRegisterBindingImpl.this.mboundView4);
                String str = ActivityRegisterBindingImpl.this.mName;
                ActivityRegisterBindingImpl activityRegisterBindingImpl = ActivityRegisterBindingImpl.this;
                if (activityRegisterBindingImpl != null) {
                    activityRegisterBindingImpl.setName(textString);
                }
            }
        };
        this.mboundView5androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityRegisterBindingImpl.5
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityRegisterBindingImpl.this.mboundView5);
                String str = ActivityRegisterBindingImpl.this.mIdcard;
                ActivityRegisterBindingImpl activityRegisterBindingImpl = ActivityRegisterBindingImpl.this;
                if (activityRegisterBindingImpl != null) {
                    activityRegisterBindingImpl.setIdcard(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        NestedScrollView nestedScrollView = (NestedScrollView) bindings[0];
        this.mboundView0 = nestedScrollView;
        nestedScrollView.setTag(null);
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
        EditText editText5 = (EditText) bindings[5];
        this.mboundView5 = editText5;
        editText5.setTag(null);
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
        if (70 == variableId) {
            setPassword((String) variable);
        } else if (41 == variableId) {
            setIdcard((String) variable);
        } else if (61 == variableId) {
            setName((String) variable);
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

    @Override // com.cy.yyjia.zhe28.databinding.ActivityRegisterBinding
    public void setPassword(String Password) {
        this.mPassword = Password;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(70);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityRegisterBinding
    public void setIdcard(String Idcard) {
        this.mIdcard = Idcard;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(41);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityRegisterBinding
    public void setName(String Name) {
        this.mName = Name;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(61);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityRegisterBinding
    public void setCode(String Code) {
        this.mCode = Code;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityRegisterBinding
    public void setPhone(String Phone) {
        this.mPhone = Phone;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(72);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityRegisterBinding
    public void setPassword2(String Password2) {
        this.mPassword2 = Password2;
        synchronized (this) {
            this.mDirtyFlags |= 32;
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
        String str2 = this.mIdcard;
        String str3 = this.mName;
        String str4 = this.mPhone;
        String str5 = this.mPassword2;
        long j2 = 65 & j;
        long j3 = 66 & j;
        long j4 = 68 & j;
        long j5 = 96 & j;
        if ((80 & j) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, str4);
        }
        if ((j & 64) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.mboundView1, null, null, null, this.mboundView1androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView2, null, null, null, this.mboundView2androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView3, null, null, null, this.mboundView3androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView4, null, null, null, this.mboundView4androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView5, null, null, null, this.mboundView5androidTextAttrChanged);
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, str);
        }
        if (j5 != 0) {
            TextViewBindingAdapter.setText(this.mboundView3, str5);
        }
        if (j4 != 0) {
            TextViewBindingAdapter.setText(this.mboundView4, str3);
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.mboundView5, str2);
        }
    }
}
