package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.DealParamBean;
import com.hjq.shape.view.ShapeEditText;

/* JADX INFO: loaded from: classes2.dex */
public class DialogDealFilterBindingImpl extends DialogDealFilterBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mOnClickOnClickAndroidViewViewOnClickListener;
    private final LinearLayout mboundView0;
    private final ShapeEditText mboundView2;
    private InverseBindingListener mboundView2androidTextAttrChanged;
    private final ShapeEditText mboundView3;
    private InverseBindingListener mboundView3androidTextAttrChanged;
    private final ShapeEditText mboundView4;
    private InverseBindingListener mboundView4androidTextAttrChanged;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.rv_type, 7);
    }

    public DialogDealFilterBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private DialogDealFilterBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (TextView) bindings[5], (TextView) bindings[6], (ImageView) bindings[1], (RecyclerView) bindings[7]);
        this.mboundView2androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.DialogDealFilterBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(DialogDealFilterBindingImpl.this.mboundView2);
                DealParamBean dealParamBean = DialogDealFilterBindingImpl.this.mData;
                if (dealParamBean != null) {
                    dealParamBean.setServer(textString);
                }
            }
        };
        this.mboundView3androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.DialogDealFilterBindingImpl.2
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(DialogDealFilterBindingImpl.this.mboundView3);
                DealParamBean dealParamBean = DialogDealFilterBindingImpl.this.mData;
                if (dealParamBean != null) {
                    dealParamBean.setMin(textString);
                }
            }
        };
        this.mboundView4androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.DialogDealFilterBindingImpl.3
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(DialogDealFilterBindingImpl.this.mboundView4);
                DealParamBean dealParamBean = DialogDealFilterBindingImpl.this.mData;
                if (dealParamBean != null) {
                    dealParamBean.setMax(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.btn1.setTag(null);
        this.btn2.setTag(null);
        this.ivClose.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ShapeEditText shapeEditText = (ShapeEditText) bindings[2];
        this.mboundView2 = shapeEditText;
        shapeEditText.setTag(null);
        ShapeEditText shapeEditText2 = (ShapeEditText) bindings[3];
        this.mboundView3 = shapeEditText2;
        shapeEditText2.setTag(null);
        ShapeEditText shapeEditText3 = (ShapeEditText) bindings[4];
        this.mboundView4 = shapeEditText3;
        shapeEditText3.setTag(null);
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
        if (68 == variableId) {
            setOnClick((View.OnClickListener) variable);
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((DealParamBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogDealFilterBinding
    public void setOnClick(View.OnClickListener OnClick) {
        this.mOnClick = OnClick;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(68);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogDealFilterBinding
    public void setData(DealParamBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        OnClickListenerImpl value;
        String min;
        String server;
        String max;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        View.OnClickListener onClickListener = this.mOnClick;
        DealParamBean dealParamBean = this.mData;
        long j2 = 5 & j;
        if (j2 == 0 || onClickListener == null) {
            value = null;
        } else {
            OnClickListenerImpl onClickListenerImpl = this.mOnClickOnClickAndroidViewViewOnClickListener;
            if (onClickListenerImpl == null) {
                onClickListenerImpl = new OnClickListenerImpl();
                this.mOnClickOnClickAndroidViewViewOnClickListener = onClickListenerImpl;
            }
            value = onClickListenerImpl.setValue(onClickListener);
        }
        long j3 = 6 & j;
        if (j3 == 0 || dealParamBean == null) {
            min = null;
            server = null;
            max = null;
        } else {
            server = dealParamBean.getServer();
            max = dealParamBean.getMax();
            min = dealParamBean.getMin();
        }
        if (j2 != 0) {
            this.btn1.setOnClickListener(value);
            this.btn2.setOnClickListener(value);
            this.ivClose.setOnClickListener(value);
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, server);
            TextViewBindingAdapter.setText(this.mboundView3, min);
            TextViewBindingAdapter.setText(this.mboundView4, max);
        }
        if ((j & 4) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.mboundView2, null, null, null, this.mboundView2androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView3, null, null, null, this.mboundView3androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView4, null, null, null, this.mboundView4androidTextAttrChanged);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private View.OnClickListener value;

        public OnClickListenerImpl setValue(View.OnClickListener value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.onClick(arg0);
        }
    }
}
