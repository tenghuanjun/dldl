package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BbsDetailBean;
import com.cy.yyjia.zhe28.domain.GameBean;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityBbsEditBindingImpl extends ActivityBbsEditBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener etandroidTextAttrChanged;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final EditText mboundView1;
    private InverseBindingListener mboundView1androidTextAttrChanged;
    private final ImageView mboundView3;
    private final TextView mboundView4;
    private final ImageView mboundView6;
    private final TextView mboundView7;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.rv_cate, 8);
        sparseIntArray.put(R.id.rv_reply, 9);
        sparseIntArray.put(R.id.rv_pic, 10);
        sparseIntArray.put(R.id.ll_game, 11);
        sparseIntArray.put(R.id.btn, 12);
    }

    public ActivityBbsEditBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }

    private ActivityBbsEditBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (TextView) bindings[12], (EditText) bindings[2], (ImageView) bindings[5], (LinearLayout) bindings[11], (RecyclerView) bindings[8], (RecyclerView) bindings[10], (RecyclerView) bindings[9]);
        this.etandroidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityBbsEditBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityBbsEditBindingImpl.this.et);
                String str = ActivityBbsEditBindingImpl.this.mText;
                ActivityBbsEditBindingImpl activityBbsEditBindingImpl = ActivityBbsEditBindingImpl.this;
                if (activityBbsEditBindingImpl != null) {
                    activityBbsEditBindingImpl.setText(textString);
                }
            }
        };
        this.mboundView1androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityBbsEditBindingImpl.2
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityBbsEditBindingImpl.this.mboundView1);
                String str = ActivityBbsEditBindingImpl.this.mTitle;
                ActivityBbsEditBindingImpl activityBbsEditBindingImpl = ActivityBbsEditBindingImpl.this;
                if (activityBbsEditBindingImpl != null) {
                    activityBbsEditBindingImpl.setTitle(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.et.setTag(null);
        this.ivGame.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        EditText editText = (EditText) bindings[1];
        this.mboundView1 = editText;
        editText.setTag(null);
        ImageView imageView = (ImageView) bindings[3];
        this.mboundView3 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[4];
        this.mboundView4 = textView;
        textView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[6];
        this.mboundView6 = imageView2;
        imageView2.setTag(null);
        TextView textView2 = (TextView) bindings[7];
        this.mboundView7 = textView2;
        textView2.setTag(null);
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
        if (74 == variableId) {
            setPosition(((Integer) variable).intValue());
        } else if (23 == variableId) {
            setData((BbsDetailBean) variable);
        } else if (111 == variableId) {
            setText((String) variable);
        } else {
            if (114 != variableId) {
                return false;
            }
            setTitle((String) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityBbsEditBinding
    public void setPosition(int Position) {
        this.mPosition = Position;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityBbsEditBinding
    public void setData(BbsDetailBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityBbsEditBinding
    public void setText(String Text) {
        this.mText = Text;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(111);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityBbsEditBinding
    public void setTitle(String Title) {
        this.mTitle = Title;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(114);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeDataGame((GameBean) object, fieldId);
    }

    private boolean onChangeDataGame(GameBean DataGame, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0050  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            r20 = this;
            r1 = r20
            monitor-enter(r20)
            long r2 = r1.mDirtyFlags     // Catch: java.lang.Throwable -> Lb8
            r4 = 0
            r1.mDirtyFlags = r4     // Catch: java.lang.Throwable -> Lb8
            monitor-exit(r20)     // Catch: java.lang.Throwable -> Lb8
            com.cy.yyjia.zhe28.domain.BbsDetailBean r0 = r1.mData
            java.lang.String r6 = r1.mText
            java.lang.String r7 = r1.mTitle
            r8 = 37
            long r8 = r8 & r2
            r10 = 36
            r12 = 0
            r13 = 0
            int r14 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r14 == 0) goto L53
            if (r0 == 0) goto L22
            com.cy.yyjia.zhe28.domain.GameBean r8 = r0.getGame()
            goto L23
        L22:
            r8 = r13
        L23:
            r1.updateRegistration(r12, r8)
            if (r8 != 0) goto L2a
            r9 = 1
            r12 = 1
        L2a:
            if (r8 == 0) goto L35
            java.lang.String r9 = r8.getName()
            java.lang.String r8 = r8.getIcon()
            goto L37
        L35:
            r8 = r13
            r9 = r8
        L37:
            long r15 = r2 & r10
            int r17 = (r15 > r4 ? 1 : (r15 == r4 ? 0 : -1))
            if (r17 == 0) goto L50
            if (r0 == 0) goto L44
            com.cy.yyjia.zhe28.domain.BbsDetailBean$Info r0 = r0.getInfo()
            goto L45
        L44:
            r0 = r13
        L45:
            if (r0 == 0) goto L50
            java.lang.String r15 = r0.getName()
            java.lang.String r0 = r0.getIcon()
            goto L57
        L50:
            r0 = r13
            r15 = r0
            goto L57
        L53:
            r0 = r13
            r8 = r0
            r9 = r8
            r15 = r9
        L57:
            r16 = 40
            long r16 = r2 & r16
            int r18 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            r16 = 48
            long r16 = r2 & r16
            int r19 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            if (r18 == 0) goto L6a
            android.widget.EditText r10 = r1.et
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r10, r6)
        L6a:
            r10 = 32
            long r10 = r10 & r2
            int r6 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r6 == 0) goto L88
            android.widget.EditText r6 = r1.et
            r10 = r13
            androidx.databinding.adapters.TextViewBindingAdapter$BeforeTextChanged r10 = (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged) r10
            r10 = r13
            androidx.databinding.adapters.TextViewBindingAdapter$OnTextChanged r10 = (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged) r10
            r10 = r13
            androidx.databinding.adapters.TextViewBindingAdapter$AfterTextChanged r10 = (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged) r10
            androidx.databinding.InverseBindingListener r10 = r1.etandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r6, r13, r13, r13, r10)
            android.widget.EditText r6 = r1.mboundView1
            androidx.databinding.InverseBindingListener r10 = r1.mboundView1androidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r6, r13, r13, r13, r10)
        L88:
            if (r14 == 0) goto L9c
            android.widget.ImageView r6 = r1.ivGame
            com.cy.yyjia.zhe28.util.DataBindingHelper.setViewGone(r6, r12)
            android.widget.ImageView r6 = r1.mboundView3
            r10 = r13
            android.graphics.drawable.Drawable r10 = (android.graphics.drawable.Drawable) r10
            com.cy.yyjia.zhe28.util.DataBindingHelper.setImg(r6, r8, r13)
            android.widget.TextView r6 = r1.mboundView4
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r6, r9)
        L9c:
            if (r19 == 0) goto La3
            android.widget.EditText r6 = r1.mboundView1
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r6, r7)
        La3:
            r6 = 36
            long r2 = r2 & r6
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 == 0) goto Lb7
            android.widget.ImageView r2 = r1.mboundView6
            r3 = r13
            android.graphics.drawable.Drawable r3 = (android.graphics.drawable.Drawable) r3
            com.cy.yyjia.zhe28.util.DataBindingHelper.setImg(r2, r0, r13)
            android.widget.TextView r0 = r1.mboundView7
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r15)
        Lb7:
            return
        Lb8:
            r0 = move-exception
            monitor-exit(r20)     // Catch: java.lang.Throwable -> Lb8
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ActivityBbsEditBindingImpl.executeBindings():void");
    }
}
