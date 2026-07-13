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
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public class DialogBbsGameBindingImpl extends DialogBbsGameBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final EditText mboundView1;
    private InverseBindingListener mboundView1androidTextAttrChanged;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tv_title, 2);
        sparseIntArray.put(R.id.rv, 3);
        sparseIntArray.put(R.id.tv_close, 4);
    }

    public DialogBbsGameBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private DialogBbsGameBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (RecyclerView) bindings[3], (TextView) bindings[4], (TextView) bindings[2]);
        this.mboundView1androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.DialogBbsGameBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(DialogBbsGameBindingImpl.this.mboundView1);
                String str = DialogBbsGameBindingImpl.this.mGame;
                DialogBbsGameBindingImpl dialogBbsGameBindingImpl = DialogBbsGameBindingImpl.this;
                if (dialogBbsGameBindingImpl != null) {
                    dialogBbsGameBindingImpl.setGame(textString);
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
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
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
        if (35 != variableId) {
            return false;
        }
        setGame((String) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogBbsGameBinding
    public void setGame(String Game) {
        this.mGame = Game;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(35);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str = this.mGame;
        if ((3 & j) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, str);
        }
        if ((j & 2) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.mboundView1, null, null, null, this.mboundView1androidTextAttrChanged);
        }
    }
}
