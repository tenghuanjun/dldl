package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.EventBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;
import kotlin.UByte;

/* JADX INFO: loaded from: classes2.dex */
public class ItemGameEventBindingImpl extends ItemGameEventBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final ShapeTextView mboundView2;
    private final TextView mboundView3;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemGameEventBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private ItemGameEventBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ShapeTextView) bindings[5], (ShapeTextView) bindings[1], (TextView) bindings[4]);
        this.mDirtyFlags = -1L;
        this.btn.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[2];
        this.mboundView2 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView = (TextView) bindings[3];
        this.mboundView3 = textView;
        textView.setTag(null);
        this.tvTag.setTag(null);
        this.tvTag1.setTag(null);
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
        if (23 != variableId) {
            return false;
        }
        setData((EventBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemGameEventBinding
    public void setData(EventBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        String str2;
        String str3;
        boolean z;
        String date_md_text;
        EventBean.Category category;
        String title;
        String sapplyType;
        String applyTypeText;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        EventBean eventBean = this.mData;
        long j2 = j & 3;
        byte b = 0;
        if (j2 != 0) {
            if (eventBean != null) {
                category = eventBean.getCategory();
                title = eventBean.getTitle();
                sapplyType = eventBean.getSapplyType();
                applyTypeText = eventBean.getApplyTypeText();
                date_md_text = eventBean.getDate_md_text();
            } else {
                date_md_text = null;
                category = null;
                title = null;
                sapplyType = null;
                applyTypeText = null;
            }
            name = category != null ? category.getName() : null;
            boolean zEquals = "2".equals(sapplyType);
            String str4 = "发布时间：" + date_md_text;
            if (j2 != 0) {
                j |= zEquals ? 8L : 4L;
            }
            boolean zEquals2 = name != null ? name.equals("游戏活动") : 0;
            str3 = title;
            str2 = name;
            name = applyTypeText;
            byte b2 = zEquals ? UByte.MAX_VALUE : (byte) -13108;
            str = str4;
            z = zEquals2 ^ 1 ? 1 : 0;
            b = b2;
        } else {
            str = null;
            str2 = null;
            str3 = null;
            z = false;
        }
        if ((j & 3) != 0) {
            TextViewBindingAdapter.setText(this.btn, name);
            this.btn.setTextColor(b);
            DataBindingHelper.setViewGone(this.mboundView2, z);
            TextViewBindingAdapter.setText(this.mboundView3, str);
            TextViewBindingAdapter.setText(this.tvTag, str2);
            TextViewBindingAdapter.setText(this.tvTag1, str3);
        }
    }
}
