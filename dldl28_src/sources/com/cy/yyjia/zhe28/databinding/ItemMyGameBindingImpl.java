package com.cy.yyjia.zhe28.databinding;

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
import com.hjq.shape.view.ShapeTextView;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ItemMyGameBindingImpl extends ItemMyGameBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final RelativeLayout mboundView1;
    private final TextView mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView7;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(12);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"layout_game_icon"}, new int[]{8}, new int[]{R.layout.layout_game_icon});
        includedLayouts.setIncludes(1, new String[]{"layout_game_name"}, new int[]{9}, new int[]{R.layout.layout_game_name});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.desc, 10);
        sparseIntArray.put(R.id.iv_delete, 11);
    }

    public ItemMyGameBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    private ItemMyGameBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (LinearLayout) bindings[10], (ShapeTextView) bindings[4], (LayoutGameIconBinding) bindings[8], (ImageView) bindings[11], (LayoutGameNameBinding) bindings[9], (LinearLayout) bindings[2], (LinearLayout) bindings[3]);
        this.mDirtyFlags = -1L;
        this.discount.setTag(null);
        setContainedBinding(this.icon);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[1];
        this.mboundView1 = relativeLayout;
        relativeLayout.setTag(null);
        TextView textView = (TextView) bindings[5];
        this.mboundView5 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[6];
        this.mboundView6 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[7];
        this.mboundView7 = textView3;
        textView3.setTag(null);
        setContainedBinding(this.name);
        this.tag.setTag(null);
        this.tag2.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
        }
        this.icon.invalidateAll();
        this.name.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.icon.hasPendingBindings() || this.name.hasPendingBindings();
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

    @Override // com.cy.yyjia.zhe28.databinding.ItemMyGameBinding
    public void setData(GameBean Data) {
        updateRegistration(2, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.icon.setLifecycleOwner(lifecycleOwner);
        this.name.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId == 0) {
            return onChangeIcon((LayoutGameIconBinding) object, fieldId);
        }
        if (localFieldId == 1) {
            return onChangeName((LayoutGameNameBinding) object, fieldId);
        }
        if (localFieldId != 2) {
            return false;
        }
        return onChangeData((GameBean) object, fieldId);
    }

    private boolean onChangeIcon(LayoutGameIconBinding Icon, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeName(LayoutGameNameBinding Name, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeData(GameBean Data, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String onlineText;
        List<GameBean.Tag> list;
        String str;
        List<String> list2;
        boolean z;
        String btnStr;
        List<String> category_list;
        int playNum;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        GameBean gameBean = this.mData;
        long j2 = j & 12;
        boolean zIsOnline = false;
        String str2 = null;
        List<GameBean.Tag> tags = null;
        if (j2 != 0) {
            if (gameBean != null) {
                tags = gameBean.getTags();
                btnStr = gameBean.getBtnStr();
                zIsOnline = gameBean.isOnline();
                onlineText = gameBean.getOnlineText();
                category_list = gameBean.getCategory_list();
                playNum = gameBean.getPlayNum();
            } else {
                btnStr = null;
                onlineText = null;
                category_list = null;
                playNum = 0;
            }
            str = playNum + "人在玩";
            List<GameBean.Tag> list3 = tags;
            str2 = btnStr;
            z = zIsOnline;
            zIsOnline = !zIsOnline;
            list2 = category_list;
            list = list3;
        } else {
            onlineText = null;
            list = null;
            str = null;
            list2 = null;
            z = false;
        }
        if (j2 != 0) {
            DataBindingHelper.setViewGone(this.discount, zIsOnline);
            TextViewBindingAdapter.setText(this.discount, str2);
            this.icon.setData(gameBean);
            TextViewBindingAdapter.setText(this.mboundView5, str);
            DataBindingHelper.setViewGone(this.mboundView6, z);
            DataBindingHelper.setViewGone(this.mboundView7, z);
            TextViewBindingAdapter.setText(this.mboundView7, onlineText);
            this.name.setData(gameBean);
            DataBindingHelper.setTags(this.tag, list);
            DataBindingHelper.setTags2(this.tag2, list2);
        }
        executeBindingsOn(this.icon);
        executeBindingsOn(this.name);
    }
}
