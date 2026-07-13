package com.cy.yyjia.zhe28.databinding;

import android.text.Spanned;
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
import com.cy.yyjia.zhe28.domain.WelfareEventDetailBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityWelfareEventDetailBindingImpl extends ActivityWelfareEventDetailBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener etandroidTextAttrChanged;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final ImageView mboundView3;
    private final TextView mboundView4;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.navigation, 6);
        sparseIntArray.put(R.id.rv, 7);
        sparseIntArray.put(R.id.btn_send, 8);
    }

    public ActivityWelfareEventDetailBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private ActivityWelfareEventDetailBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (TextView) bindings[8], (EditText) bindings[5], (Navigation) bindings[6], (RecyclerView) bindings[7]);
        this.etandroidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityWelfareEventDetailBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityWelfareEventDetailBindingImpl.this.et);
                String str = ActivityWelfareEventDetailBindingImpl.this.mText;
                ActivityWelfareEventDetailBindingImpl activityWelfareEventDetailBindingImpl = ActivityWelfareEventDetailBindingImpl.this;
                if (activityWelfareEventDetailBindingImpl != null) {
                    activityWelfareEventDetailBindingImpl.setText(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.et.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[2];
        this.mboundView2 = textView2;
        textView2.setTag(null);
        ImageView imageView = (ImageView) bindings[3];
        this.mboundView3 = imageView;
        imageView.setTag(null);
        TextView textView3 = (TextView) bindings[4];
        this.mboundView4 = textView3;
        textView3.setTag(null);
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
        if (23 == variableId) {
            setData((WelfareEventDetailBean) variable);
        } else {
            if (111 != variableId) {
                return false;
            }
            setText((String) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityWelfareEventDetailBinding
    public void setData(WelfareEventDetailBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityWelfareEventDetailBinding
    public void setText(String Text) {
        this.mText = Text;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(111);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String start_time;
        String title;
        String pic;
        Spanned mainText;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        WelfareEventDetailBean welfareEventDetailBean = this.mData;
        String str = this.mText;
        long j2 = 5 & j;
        if (j2 == 0 || welfareEventDetailBean == null) {
            start_time = null;
            title = null;
            pic = null;
            mainText = null;
        } else {
            title = welfareEventDetailBean.getTitle();
            pic = welfareEventDetailBean.getPic();
            mainText = welfareEventDetailBean.getMainText();
            start_time = welfareEventDetailBean.getStart_time();
        }
        if ((6 & j) != 0) {
            TextViewBindingAdapter.setText(this.et, str);
        }
        if ((j & 4) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.et, null, null, null, this.etandroidTextAttrChanged);
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, title);
            TextViewBindingAdapter.setText(this.mboundView2, start_time);
            DataBindingHelper.setImg(this.mboundView3, pic, null);
            TextViewBindingAdapter.setText(this.mboundView4, mainText);
        }
    }
}
