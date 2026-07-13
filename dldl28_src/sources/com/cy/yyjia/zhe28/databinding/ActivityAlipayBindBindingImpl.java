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
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.view.CountdownView;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityAlipayBindBindingImpl extends ActivityAlipayBindBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
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
        sparseIntArray.put(R.id.btn_code, 5);
        sparseIntArray.put(R.id.tv_go, 6);
    }

    public ActivityAlipayBindBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private ActivityAlipayBindBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (CountdownView) bindings[5], (ShapeTextView) bindings[6]);
        this.mboundView2androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityAlipayBindBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityAlipayBindBindingImpl.this.mboundView2);
                String str = ActivityAlipayBindBindingImpl.this.mCode;
                ActivityAlipayBindBindingImpl activityAlipayBindBindingImpl = ActivityAlipayBindBindingImpl.this;
                if (activityAlipayBindBindingImpl != null) {
                    activityAlipayBindBindingImpl.setCode(textString);
                }
            }
        };
        this.mboundView3androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityAlipayBindBindingImpl.2
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityAlipayBindBindingImpl.this.mboundView3);
                String str = ActivityAlipayBindBindingImpl.this.mAccount;
                ActivityAlipayBindBindingImpl activityAlipayBindBindingImpl = ActivityAlipayBindBindingImpl.this;
                if (activityAlipayBindBindingImpl != null) {
                    activityAlipayBindBindingImpl.setAccount(textString);
                }
            }
        };
        this.mboundView4androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityAlipayBindBindingImpl.3
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityAlipayBindBindingImpl.this.mboundView4);
                String str = ActivityAlipayBindBindingImpl.this.mName;
                ActivityAlipayBindBindingImpl activityAlipayBindBindingImpl = ActivityAlipayBindBindingImpl.this;
                if (activityAlipayBindBindingImpl != null) {
                    activityAlipayBindBindingImpl.setName(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        EditText editText = (EditText) bindings[2];
        this.mboundView2 = editText;
        editText.setTag(null);
        EditText editText2 = (EditText) bindings[3];
        this.mboundView3 = editText2;
        editText2.setTag(null);
        EditText editText3 = (EditText) bindings[4];
        this.mboundView4 = editText3;
        editText3.setTag(null);
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
        if (61 == variableId) {
            setName((String) variable);
            return true;
        }
        if (14 == variableId) {
            setCode((String) variable);
            return true;
        }
        if (1 == variableId) {
            setAccount((String) variable);
            return true;
        }
        if (23 != variableId) {
            return false;
        }
        setData((UserBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityAlipayBindBinding
    public void setName(String Name) {
        this.mName = Name;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(61);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityAlipayBindBinding
    public void setCode(String Code) {
        this.mCode = Code;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(14);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityAlipayBindBinding
    public void setAccount(String Account) {
        this.mAccount = Account;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(1);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityAlipayBindBinding
    public void setData(UserBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str = this.mName;
        String str2 = this.mCode;
        String str3 = this.mAccount;
        UserBean userBean = this.mData;
        long j2 = 17 & j;
        long j3 = 18 & j;
        long j4 = 20 & j;
        long j5 = 24 & j;
        String telphone = (j5 == 0 || userBean == null) ? null : userBean.getTelphone();
        if (j5 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, telphone);
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, str2);
        }
        if ((j & 16) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.mboundView2, null, null, null, this.mboundView2androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView3, null, null, null, this.mboundView3androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView4, null, null, null, this.mboundView4androidTextAttrChanged);
        }
        if (j4 != 0) {
            TextViewBindingAdapter.setText(this.mboundView3, str3);
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView4, str);
        }
    }
}
