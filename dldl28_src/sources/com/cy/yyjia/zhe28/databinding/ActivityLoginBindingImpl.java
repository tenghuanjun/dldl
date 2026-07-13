package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.view.CountdownView;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeEditText;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityLoginBindingImpl extends ActivityLoginBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ShapeEditText mboundView2;
    private InverseBindingListener mboundView2androidTextAttrChanged;
    private final ShapeEditText mboundView3;
    private InverseBindingListener mboundView3androidTextAttrChanged;
    private final FrameLayout mboundView4;
    private final ShapeEditText mboundView5;
    private InverseBindingListener mboundView5androidTextAttrChanged;
    private final ImageView mboundView6;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.navigation, 8);
        sparseIntArray.put(R.id.btn_code, 9);
        sparseIntArray.put(R.id.tv_forget, 10);
        sparseIntArray.put(R.id.tv_login, 11);
        sparseIntArray.put(R.id.ll_confirm, 12);
        sparseIntArray.put(R.id.tv_user, 13);
        sparseIntArray.put(R.id.tv_privacy, 14);
        sparseIntArray.put(R.id.f438tv, 15);
        sparseIntArray.put(R.id.tv_fast, 16);
        sparseIntArray.put(R.id.tv_wx, 17);
    }

    public ActivityLoginBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds));
    }

    private ActivityLoginBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (CountdownView) bindings[9], (LinearLayout) bindings[12], (Navigation) bindings[8], (TextView) bindings[15], (TextView) bindings[7], (TextView) bindings[16], (TextView) bindings[10], (TextView) bindings[11], (TextView) bindings[14], (TextView) bindings[1], (TextView) bindings[13], (TextView) bindings[17]);
        this.mboundView2androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityLoginBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityLoginBindingImpl.this.mboundView2);
                String str = ActivityLoginBindingImpl.this.mUsername;
                ActivityLoginBindingImpl activityLoginBindingImpl = ActivityLoginBindingImpl.this;
                if (activityLoginBindingImpl != null) {
                    activityLoginBindingImpl.setUsername(textString);
                }
            }
        };
        this.mboundView3androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityLoginBindingImpl.2
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityLoginBindingImpl.this.mboundView3);
                String str = ActivityLoginBindingImpl.this.mPassword;
                ActivityLoginBindingImpl activityLoginBindingImpl = ActivityLoginBindingImpl.this;
                if (activityLoginBindingImpl != null) {
                    activityLoginBindingImpl.setPassword(textString);
                }
            }
        };
        this.mboundView5androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityLoginBindingImpl.3
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityLoginBindingImpl.this.mboundView5);
                String str = ActivityLoginBindingImpl.this.mYzm;
                ActivityLoginBindingImpl activityLoginBindingImpl = ActivityLoginBindingImpl.this;
                if (activityLoginBindingImpl != null) {
                    activityLoginBindingImpl.setYzm(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        ShapeEditText shapeEditText = (ShapeEditText) bindings[2];
        this.mboundView2 = shapeEditText;
        shapeEditText.setTag(null);
        ShapeEditText shapeEditText2 = (ShapeEditText) bindings[3];
        this.mboundView3 = shapeEditText2;
        shapeEditText2.setTag(null);
        FrameLayout frameLayout = (FrameLayout) bindings[4];
        this.mboundView4 = frameLayout;
        frameLayout.setTag(null);
        ShapeEditText shapeEditText3 = (ShapeEditText) bindings[5];
        this.mboundView5 = shapeEditText3;
        shapeEditText3.setTag(null);
        ImageView imageView = (ImageView) bindings[6];
        this.mboundView6 = imageView;
        imageView.setTag(null);
        this.tvChange.setTag(null);
        this.tvTag.setTag(null);
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
        if (128 == variableId) {
            setYzm((String) variable);
        } else if (70 == variableId) {
            setPassword((String) variable);
        } else if (72 == variableId) {
            setPhone(((Boolean) variable).booleanValue());
        } else if (121 == variableId) {
            setUsername((String) variable);
        } else {
            if (11 != variableId) {
                return false;
            }
            setCheck(((Boolean) variable).booleanValue());
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityLoginBinding
    public void setYzm(String Yzm) {
        this.mYzm = Yzm;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(128);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityLoginBinding
    public void setPassword(String Password) {
        this.mPassword = Password;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(70);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityLoginBinding
    public void setPhone(boolean Phone) {
        this.mPhone = Phone;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(72);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityLoginBinding
    public void setUsername(String Username) {
        this.mUsername = Username;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(121);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityLoginBinding
    public void setCheck(boolean Check) {
        this.mCheck = Check;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(11);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        String str;
        String str2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str3 = this.mYzm;
        String str4 = this.mPassword;
        boolean z2 = this.mPhone;
        String str5 = this.mUsername;
        boolean z3 = this.mCheck;
        long j2 = j & 36;
        if (j2 != 0) {
            if (j2 != 0) {
                j |= z2 ? 640L : 320L;
            }
            str = z2 ? "账号密码" : "验证码登录";
            str2 = z2 ? "手机登录" : "登录";
            z = !z2;
        } else {
            z = false;
            str = null;
            str2 = null;
        }
        long j3 = j & 48;
        if ((j & 40) != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, str5);
        }
        if ((32 & j) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.mboundView2, null, null, null, this.mboundView2androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView3, null, null, null, this.mboundView3androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView5, null, null, null, this.mboundView5androidTextAttrChanged);
        }
        if ((36 & j) != 0) {
            DataBindingHelper.setViewGone(this.mboundView3, z2);
            DataBindingHelper.setViewGone(this.mboundView4, z);
            TextViewBindingAdapter.setText(this.tvChange, str);
            TextViewBindingAdapter.setText(this.tvTag, str2);
        }
        if ((34 & j) != 0) {
            TextViewBindingAdapter.setText(this.mboundView3, str4);
        }
        if ((j & 33) != 0) {
            TextViewBindingAdapter.setText(this.mboundView5, str3);
        }
        if (j3 != 0) {
            DataBindingHelper.setSelected(this.mboundView6, z3);
        }
    }
}
