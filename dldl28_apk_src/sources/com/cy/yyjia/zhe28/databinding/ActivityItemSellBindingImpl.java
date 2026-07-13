package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.ItemTradeBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityItemSellBindingImpl extends ActivityItemSellBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener etandroidTextAttrChanged;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final EditText mboundView4;
    private InverseBindingListener mboundView4androidTextAttrChanged;
    private final EditText mboundView5;
    private InverseBindingListener mboundView5androidTextAttrChanged;
    private final EditText mboundView7;
    private InverseBindingListener mboundView7androidTextAttrChanged;
    private final EditText mboundView8;
    private InverseBindingListener mboundView8androidTextAttrChanged;
    private final TextView mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.navigation, 10);
        sparseIntArray.put(R.id.ll_game, 11);
        sparseIntArray.put(R.id.ll_server, 12);
        sparseIntArray.put(R.id.iv, 13);
        sparseIntArray.put(R.id.btn, 14);
    }

    public ActivityItemSellBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }

    private ActivityItemSellBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ShapeTextView) bindings[14], (EditText) bindings[6], (ImageView) bindings[13], (LinearLayout) bindings[11], (LinearLayout) bindings[12], (Navigation) bindings[10]);
        this.etandroidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityItemSellBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityItemSellBindingImpl.this.et);
                ItemTradeBean itemTradeBean = ActivityItemSellBindingImpl.this.mData;
                if (itemTradeBean != null) {
                    itemTradeBean.setPrice(textString);
                }
            }
        };
        this.mboundView4androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityItemSellBindingImpl.2
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityItemSellBindingImpl.this.mboundView4);
                ItemTradeBean itemTradeBean = ActivityItemSellBindingImpl.this.mData;
                if (itemTradeBean != null) {
                    itemTradeBean.setServiceCode(textString);
                }
            }
        };
        this.mboundView5androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityItemSellBindingImpl.3
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityItemSellBindingImpl.this.mboundView5);
                ItemTradeBean itemTradeBean = ActivityItemSellBindingImpl.this.mData;
                if (itemTradeBean != null) {
                    itemTradeBean.setInputNum(textString);
                }
            }
        };
        this.mboundView7androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityItemSellBindingImpl.4
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityItemSellBindingImpl.this.mboundView7);
                ItemTradeBean itemTradeBean = ActivityItemSellBindingImpl.this.mData;
                if (itemTradeBean != null) {
                    itemTradeBean.setName(textString);
                }
            }
        };
        this.mboundView8androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityItemSellBindingImpl.5
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityItemSellBindingImpl.this.mboundView8);
                String str = ActivityItemSellBindingImpl.this.mDesc;
                ActivityItemSellBindingImpl activityItemSellBindingImpl = ActivityItemSellBindingImpl.this;
                if (activityItemSellBindingImpl != null) {
                    activityItemSellBindingImpl.setDesc(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.et.setTag(null);
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[3];
        this.mboundView3 = textView2;
        textView2.setTag(null);
        EditText editText = (EditText) bindings[4];
        this.mboundView4 = editText;
        editText.setTag(null);
        EditText editText2 = (EditText) bindings[5];
        this.mboundView5 = editText2;
        editText2.setTag(null);
        EditText editText3 = (EditText) bindings[7];
        this.mboundView7 = editText3;
        editText3.setTag(null);
        EditText editText4 = (EditText) bindings[8];
        this.mboundView8 = editText4;
        editText4.setTag(null);
        TextView textView3 = (TextView) bindings[9];
        this.mboundView9 = textView3;
        textView3.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
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
        if (25 == variableId) {
            setDesc((String) variable);
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((ItemTradeBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityItemSellBinding
    public void setDesc(String Desc) {
        this.mDesc = Desc;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityItemSellBinding
    public void setData(ItemTradeBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(23);
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

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        String name;
        String str2;
        String str3;
        String serviceCode;
        String str4;
        boolean z;
        String str5;
        String str6;
        String icon;
        String name2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str7 = this.mDesc;
        ItemTradeBean itemTradeBean = this.mData;
        if ((j & 10) != 0) {
            str = (str7 != null ? str7.length() : 0) + "/100";
        } else {
            str = null;
        }
        long j2 = j & 13;
        if (j2 != 0) {
            GameBean game = itemTradeBean != null ? itemTradeBean.getGame() : null;
            updateRegistration(0, game);
            boolean z2 = game == null;
            if (game != null) {
                name2 = game.getName();
                icon = game.getIcon();
            } else {
                icon = null;
                name2 = null;
            }
            z = name2 == null;
            if (j2 != 0) {
                j |= z ? 32L : 16L;
            }
            if ((j & 12) == 0 || itemTradeBean == null) {
                str2 = icon;
                z = z2;
                str5 = name2;
                name = null;
                str3 = null;
                serviceCode = null;
                str4 = null;
            } else {
                serviceCode = itemTradeBean.getServiceCode();
                String price = itemTradeBean.getPrice();
                String inputNum = itemTradeBean.getInputNum();
                name = itemTradeBean.getName();
                str2 = icon;
                str3 = price;
                z = z2;
                str5 = name2;
                str4 = inputNum;
            }
        } else {
            name = null;
            str2 = null;
            str3 = null;
            serviceCode = null;
            str4 = null;
            z = false;
            str5 = null;
        }
        long j3 = 13 & j;
        if (j3 != 0) {
            if (z) {
                str5 = "请选择游戏";
            }
            str6 = str5;
        } else {
            str6 = null;
        }
        if ((j & 12) != 0) {
            TextViewBindingAdapter.setText(this.et, str3);
            TextViewBindingAdapter.setText(this.mboundView4, serviceCode);
            TextViewBindingAdapter.setText(this.mboundView5, str4);
            TextViewBindingAdapter.setText(this.mboundView7, name);
        }
        if ((8 & j) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.et, null, null, null, this.etandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView4, null, null, null, this.mboundView4androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView5, null, null, null, this.mboundView5androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView7, null, null, null, this.mboundView7androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView8, null, null, null, this.mboundView8androidTextAttrChanged);
        }
        if (j3 != 0) {
            DataBindingHelper.setGameIcon(this.mboundView1, str2);
            TextViewBindingAdapter.setText(this.mboundView2, str6);
            DataBindingHelper.setViewGone(this.mboundView3, z);
        }
        if ((j & 10) != 0) {
            TextViewBindingAdapter.setText(this.mboundView8, str7);
            TextViewBindingAdapter.setText(this.mboundView9, str);
        }
    }
}
