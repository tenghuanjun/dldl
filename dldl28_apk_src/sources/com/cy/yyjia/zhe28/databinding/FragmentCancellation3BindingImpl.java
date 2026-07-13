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
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentCancellation3BindingImpl extends FragmentCancellation3Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    private final EditText mboundView2;
    private InverseBindingListener mboundView2androidTextAttrChanged;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.btn_code, 4);
    }

    public FragmentCancellation3BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private FragmentCancellation3BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (CountdownView) bindings[4], (ShapeTextView) bindings[3]);
        this.mboundView2androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.FragmentCancellation3BindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(FragmentCancellation3BindingImpl.this.mboundView2);
                String str = FragmentCancellation3BindingImpl.this.mCode;
                FragmentCancellation3BindingImpl fragmentCancellation3BindingImpl = FragmentCancellation3BindingImpl.this;
                if (fragmentCancellation3BindingImpl != null) {
                    fragmentCancellation3BindingImpl.setCode(textString);
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
        this.tvGo.setTag(null);
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
        if (14 == variableId) {
            setCode((String) variable);
        } else {
            if (72 != variableId) {
                return false;
            }
            setPhone((String) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentCancellation3Binding
    public void setCode(String Code) {
        this.mCode = Code;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(14);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentCancellation3Binding
    public void setPhone(String Phone) {
        this.mPhone = Phone;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(72);
        super.requestRebind();
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0049  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            r17 = this;
            r1 = r17
            monitor-enter(r17)
            long r2 = r1.mDirtyFlags     // Catch: java.lang.Throwable -> L87
            r4 = 0
            r1.mDirtyFlags = r4     // Catch: java.lang.Throwable -> L87
            monitor-exit(r17)     // Catch: java.lang.Throwable -> L87
            java.lang.String r0 = r1.mCode
            java.lang.String r6 = r1.mPhone
            r7 = 7
            long r9 = r2 & r7
            r11 = 8
            r13 = 1
            r14 = 0
            int r15 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r15 == 0) goto L32
            if (r0 == 0) goto L21
            int r9 = r0.length()
            goto L22
        L21:
            r9 = 0
        L22:
            r10 = 6
            if (r9 == r10) goto L27
            r9 = 1
            goto L28
        L27:
            r9 = 0
        L28:
            if (r15 == 0) goto L33
            if (r9 == 0) goto L30
            r15 = 16
            long r2 = r2 | r15
            goto L33
        L30:
            long r2 = r2 | r11
            goto L33
        L32:
            r9 = 0
        L33:
            r15 = 6
            long r15 = r15 & r2
            int r10 = (r15 > r4 ? 1 : (r15 == r4 ? 0 : -1))
            long r11 = r11 & r2
            int r15 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r15 == 0) goto L49
            if (r6 == 0) goto L44
            int r11 = r6.length()
            goto L45
        L44:
            r11 = 0
        L45:
            if (r11 != 0) goto L49
            r11 = 1
            goto L4a
        L49:
            r11 = 0
        L4a:
            long r7 = r7 & r2
            int r12 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r12 == 0) goto L54
            if (r9 == 0) goto L52
            goto L53
        L52:
            r13 = r11
        L53:
            r14 = r13
        L54:
            if (r10 == 0) goto L5b
            android.widget.TextView r7 = r1.mboundView1
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r7, r6)
        L5b:
            r6 = 5
            long r6 = r6 & r2
            int r8 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r8 == 0) goto L67
            android.widget.EditText r6 = r1.mboundView2
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r6, r0)
        L67:
            r6 = 4
            long r2 = r2 & r6
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L7f
            android.widget.EditText r0 = r1.mboundView2
            r2 = 0
            r3 = r2
            androidx.databinding.adapters.TextViewBindingAdapter$BeforeTextChanged r3 = (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged) r3
            r3 = r2
            androidx.databinding.adapters.TextViewBindingAdapter$OnTextChanged r3 = (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged) r3
            r3 = r2
            androidx.databinding.adapters.TextViewBindingAdapter$AfterTextChanged r3 = (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged) r3
            androidx.databinding.InverseBindingListener r3 = r1.mboundView2androidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r0, r2, r2, r2, r3)
        L7f:
            if (r12 == 0) goto L86
            com.hjq.shape.view.ShapeTextView r0 = r1.tvGo
            com.cy.yyjia.zhe28.util.DataBindingHelper.setSelected(r0, r14)
        L86:
            return
        L87:
            r0 = move-exception
            monitor-exit(r17)     // Catch: java.lang.Throwable -> L87
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.FragmentCancellation3BindingImpl.executeBindings():void");
    }
}
