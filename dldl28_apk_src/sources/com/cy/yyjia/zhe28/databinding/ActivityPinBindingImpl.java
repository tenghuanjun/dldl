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
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityPinBindingImpl extends ActivityPinBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final ShapeTextView mboundView3;
    private final ShapeTextView mboundView4;
    private final ShapeTextView mboundView5;
    private final ShapeTextView mboundView6;
    private final EditText mboundView7;
    private InverseBindingListener mboundView7androidTextAttrChanged;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.navigation, 9);
    }

    public ActivityPinBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private ActivityPinBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (Navigation) bindings[9], (ShapeTextView) bindings[8]);
        this.mboundView7androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityPinBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityPinBindingImpl.this.mboundView7);
                String str = ActivityPinBindingImpl.this.mPassword;
                ActivityPinBindingImpl activityPinBindingImpl = ActivityPinBindingImpl.this;
                if (activityPinBindingImpl != null) {
                    activityPinBindingImpl.setPassword(textString);
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
        TextView textView2 = (TextView) bindings[2];
        this.mboundView2 = textView2;
        textView2.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[3];
        this.mboundView3 = shapeTextView;
        shapeTextView.setTag(null);
        ShapeTextView shapeTextView2 = (ShapeTextView) bindings[4];
        this.mboundView4 = shapeTextView2;
        shapeTextView2.setTag(null);
        ShapeTextView shapeTextView3 = (ShapeTextView) bindings[5];
        this.mboundView5 = shapeTextView3;
        shapeTextView3.setTag(null);
        ShapeTextView shapeTextView4 = (ShapeTextView) bindings[6];
        this.mboundView6 = shapeTextView4;
        shapeTextView4.setTag(null);
        EditText editText = (EditText) bindings[7];
        this.mboundView7 = editText;
        editText.setTag(null);
        this.tvGo.setTag(null);
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
        if (113 == variableId) {
            setTip((String) variable);
        } else if (39 == variableId) {
            setHint((String) variable);
        } else if (70 == variableId) {
            setPassword((String) variable);
        } else {
            if (9 != variableId) {
                return false;
            }
            setBtn((String) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityPinBinding
    public void setTip(String Tip) {
        this.mTip = Tip;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(113);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityPinBinding
    public void setHint(String Hint) {
        this.mHint = Hint;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(39);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityPinBinding
    public void setPassword(String Password) {
        this.mPassword = Password;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(70);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityPinBinding
    public void setBtn(String Btn) {
        this.mBtn = Btn;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(9);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        String str;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str2 = this.mTip;
        String str3 = this.mHint;
        String str4 = this.mPassword;
        String str5 = this.mBtn;
        long j2 = j & 20;
        if (j2 != 0) {
            int length = str4 != null ? str4.length() : 0;
            z2 = length > 1;
            z3 = length > 3;
            z4 = length > 0;
            z = length > 2;
            if (j2 != 0) {
                j = z2 ? j | 4096 : j | 2048;
            }
            if ((j & 20) != 0) {
                j = z3 ? j | 64 : j | 32;
            }
            if ((j & 20) != 0) {
                j = z4 ? j | 1024 : j | 512;
            }
            if ((j & 20) != 0) {
                j = z ? j | 256 : j | 128;
            }
        } else {
            z = false;
            z2 = false;
            z3 = false;
            z4 = false;
        }
        long j3 = j & 24;
        String strSubstring = ((j & 256) == 0 || str4 == null) ? null : str4.substring(2, 3);
        String strSubstring2 = ((j & 4096) == 0 || str4 == null) ? null : str4.substring(1, 2);
        String strSubstring3 = ((j & 64) == 0 || str4 == null) ? null : str4.substring(3, 4);
        String strSubstring4 = ((j & 1024) == 0 || str4 == null) ? null : str4.substring(0, 1);
        long j4 = j & 20;
        if (j4 != 0) {
            if (!z3) {
                strSubstring3 = "";
            }
            if (!z) {
                strSubstring = "";
            }
            if (!z4) {
                strSubstring4 = "";
            }
            if (!z2) {
                strSubstring2 = "";
            }
            str = strSubstring;
        } else {
            strSubstring3 = null;
            str = null;
            strSubstring4 = null;
            strSubstring2 = null;
        }
        if ((j & 17) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, str2);
        }
        if ((j & 18) != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, str3);
        }
        if (j4 != 0) {
            TextViewBindingAdapter.setText(this.mboundView3, strSubstring4);
            TextViewBindingAdapter.setText(this.mboundView4, strSubstring2);
            TextViewBindingAdapter.setText(this.mboundView5, str);
            TextViewBindingAdapter.setText(this.mboundView6, strSubstring3);
            TextViewBindingAdapter.setText(this.mboundView7, str4);
        }
        if ((j & 16) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.mboundView7, null, null, null, this.mboundView7androidTextAttrChanged);
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.tvGo, str5);
        }
    }
}
