package com.cy.yyjia.zhe28.databinding;

import android.text.SpannableString;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ItemSanbaoGame2BindingImpl extends ItemSanbaoGame2Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private OnClickListenerImpl mDataGotoGameAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final RelativeLayout mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(9);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(2, new String[]{"layout_game_name"}, new int[]{6}, new int[]{R.layout.layout_game_name});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.desc, 7);
        sparseIntArray.put(R.id.tv_btn, 8);
    }

    public ItemSanbaoGame2BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private ItemSanbaoGame2BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (LinearLayout) bindings[7], (LayoutGameNameBinding) bindings[6], (LinearLayout) bindings[5], (TextView) bindings[8]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[2];
        this.mboundView2 = relativeLayout;
        relativeLayout.setTag(null);
        TextView textView = (TextView) bindings[3];
        this.mboundView3 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[4];
        this.mboundView4 = textView2;
        textView2.setTag(null);
        setContainedBinding(this.name);
        this.tag.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
        }
        this.name.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.name.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (23 != variableId) {
            return false;
        }
        setData((GameBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemSanbaoGame2Binding
    public void setData(GameBean Data) {
        updateRegistration(1, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.name.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId == 0) {
            return onChangeName((LayoutGameNameBinding) object, fieldId);
        }
        if (localFieldId != 1) {
            return false;
        }
        return onChangeData((GameBean) object, fieldId);
    }

    private boolean onChangeName(LayoutGameNameBinding Name, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeData(GameBean Data, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        OnClickListenerImpl value;
        List<GameBean.Tag> tags;
        String icon;
        String score;
        SpannableString descNew;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        GameBean gameBean = this.mData;
        long j2 = j & 6;
        if (j2 == 0 || gameBean == null) {
            value = null;
            tags = null;
            icon = null;
            score = null;
            descNew = null;
        } else {
            OnClickListenerImpl onClickListenerImpl = this.mDataGotoGameAndroidViewViewOnClickListener;
            if (onClickListenerImpl == null) {
                onClickListenerImpl = new OnClickListenerImpl();
                this.mDataGotoGameAndroidViewViewOnClickListener = onClickListenerImpl;
            }
            value = onClickListenerImpl.setValue(gameBean);
            tags = gameBean.getTags();
            icon = gameBean.getIcon();
            score = gameBean.getScore();
            descNew = gameBean.getDescNew();
        }
        if (j2 != 0) {
            this.mboundView0.setOnClickListener(value);
            DataBindingHelper.setImg(this.mboundView1, icon, null);
            TextViewBindingAdapter.setText(this.mboundView3, score);
            TextViewBindingAdapter.setText(this.mboundView4, descNew);
            this.name.setData(gameBean);
            DataBindingHelper.setTags(this.tag, tags);
        }
        executeBindingsOn(this.name);
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private GameBean value;

        public OnClickListenerImpl setValue(GameBean value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.gotoGame(arg0);
        }
    }
}
