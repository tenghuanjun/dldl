package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.CommentConfig;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityCommentEditBindingImpl extends ActivityCommentEditBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    private final ShapeLinearLayout mboundView2;
    private final ItemCommentTagBinding mboundView21;
    private final EditText mboundView3;
    private InverseBindingListener mboundView3androidTextAttrChanged;
    private final EditText mboundView4;
    private InverseBindingListener mboundView4androidTextAttrChanged;
    private final LinearLayout mboundView6;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(13);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(2, new String[]{"item_comment_tag"}, new int[]{8}, new int[]{R.layout.item_comment_tag});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.navigation, 9);
        sparseIntArray.put(R.id.ratingbar, 10);
        sparseIntArray.put(R.id.rv, 11);
        sparseIntArray.put(R.id.tv_go, 12);
    }

    public ActivityCommentEditBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }

    private ActivityCommentEditBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (Navigation) bindings[9], (AppCompatRatingBar) bindings[10], (RecyclerView) bindings[11], (RecyclerView) bindings[7], (RecyclerView) bindings[5], (ShapeTextView) bindings[12]);
        this.mboundView3androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityCommentEditBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityCommentEditBindingImpl.this.mboundView3);
                String str = ActivityCommentEditBindingImpl.this.mTitle;
                ActivityCommentEditBindingImpl activityCommentEditBindingImpl = ActivityCommentEditBindingImpl.this;
                if (activityCommentEditBindingImpl != null) {
                    activityCommentEditBindingImpl.setTitle(textString);
                }
            }
        };
        this.mboundView4androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityCommentEditBindingImpl.2
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityCommentEditBindingImpl.this.mboundView4);
                String str = ActivityCommentEditBindingImpl.this.mContent;
                ActivityCommentEditBindingImpl activityCommentEditBindingImpl = ActivityCommentEditBindingImpl.this;
                if (activityCommentEditBindingImpl != null) {
                    activityCommentEditBindingImpl.setContent(textString);
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
        ShapeLinearLayout shapeLinearLayout = (ShapeLinearLayout) bindings[2];
        this.mboundView2 = shapeLinearLayout;
        shapeLinearLayout.setTag(null);
        ItemCommentTagBinding itemCommentTagBinding = (ItemCommentTagBinding) bindings[8];
        this.mboundView21 = itemCommentTagBinding;
        setContainedBinding(itemCommentTagBinding);
        EditText editText = (EditText) bindings[3];
        this.mboundView3 = editText;
        editText.setTag(null);
        EditText editText2 = (EditText) bindings[4];
        this.mboundView4 = editText2;
        editText2.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[6];
        this.mboundView6 = linearLayout2;
        linearLayout2.setTag(null);
        this.rvContent.setTag(null);
        this.rvTag.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 64L;
        }
        this.mboundView21.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.mboundView21.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (18 == variableId) {
            setContent((String) variable);
        } else if (61 == variableId) {
            setName((String) variable);
        } else if (23 == variableId) {
            setData((CommentConfig) variable);
        } else if (40 == variableId) {
            setIcon((String) variable);
        } else if (114 == variableId) {
            setTitle((String) variable);
        } else {
            if (105 != variableId) {
                return false;
            }
            setTag((CommentConfig.Tag) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityCommentEditBinding
    public void setContent(String Content) {
        this.mContent = Content;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(18);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityCommentEditBinding
    public void setName(String Name) {
        this.mName = Name;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityCommentEditBinding
    public void setData(CommentConfig Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityCommentEditBinding
    public void setIcon(String Icon) {
        this.mIcon = Icon;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityCommentEditBinding
    public void setTitle(String Title) {
        this.mTitle = Title;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(114);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityCommentEditBinding
    public void setTag(CommentConfig.Tag Tag) {
        this.mTag = Tag;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(105);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.mboundView21.setLifecycleOwner(lifecycleOwner);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0072  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 237
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ActivityCommentEditBindingImpl.executeBindings():void");
    }
}
