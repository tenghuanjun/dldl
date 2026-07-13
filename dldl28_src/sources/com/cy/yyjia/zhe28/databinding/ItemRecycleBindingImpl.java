package com.cy.yyjia.zhe28.databinding;

import android.text.SpannableString;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.RecycleListBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ItemRecycleBindingImpl extends ItemRecycleBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final ImageView mboundView10;
    private final ImageView mboundView11;
    private final TextView mboundView12;
    private final View mboundView13;
    private final TextView mboundView14;
    private final ShapeTextView mboundView2;
    private final TextView mboundView3;
    private final LinearLayout mboundView4;
    private final TextView mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView7;
    private final ImageView mboundView9;

    public ItemRecycleBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds));
    }

    private ItemRecycleBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (LinearLayout) bindings[8], (RecyclerView) bindings[15], (TextView) bindings[16]);
        this.mDirtyFlags = -1L;
        this.llBox.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[10];
        this.mboundView10 = imageView2;
        imageView2.setTag(null);
        ImageView imageView3 = (ImageView) bindings[11];
        this.mboundView11 = imageView3;
        imageView3.setTag(null);
        TextView textView = (TextView) bindings[12];
        this.mboundView12 = textView;
        textView.setTag(null);
        View view = (View) bindings[13];
        this.mboundView13 = view;
        view.setTag(null);
        TextView textView2 = (TextView) bindings[14];
        this.mboundView14 = textView2;
        textView2.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[2];
        this.mboundView2 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView3 = (TextView) bindings[3];
        this.mboundView3 = textView3;
        textView3.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[4];
        this.mboundView4 = linearLayout2;
        linearLayout2.setTag(null);
        TextView textView4 = (TextView) bindings[5];
        this.mboundView5 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[6];
        this.mboundView6 = textView5;
        textView5.setTag(null);
        TextView textView6 = (TextView) bindings[7];
        this.mboundView7 = textView6;
        textView6.setTag(null);
        ImageView imageView4 = (ImageView) bindings[9];
        this.mboundView9 = imageView4;
        imageView4.setTag(null);
        this.rv.setTag(null);
        this.tvShow.setTag(null);
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
        if (58 == variableId) {
            setMode(((Integer) variable).intValue());
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((RecycleListBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemRecycleBinding
    public void setMode(int Mode) {
        this.mMode = Mode;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(58);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemRecycleBinding
    public void setData(RecycleListBean Data) {
        updateRegistration(1, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId == 0) {
            return onChangeDataGame((GameBean) object, fieldId);
        }
        if (localFieldId != 1) {
            return false;
        }
        return onChangeData((RecycleListBean) object, fieldId);
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

    private boolean onChangeData(RecycleListBean Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        if (fieldId == 93) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        if (fieldId != 94) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        boolean z2;
        String str;
        String icon;
        String str2;
        List<RecycleListBean.Account> list;
        SpannableString spannableString;
        SpannableString spannableString2;
        SpannableString spannableString3;
        String str3;
        long j2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean selected;
        boolean z7;
        boolean z8;
        boolean z9;
        boolean z10;
        long j3;
        String name_suffix;
        String showName;
        boolean zIsEmpty;
        String str4;
        List<RecycleListBean.Account> list2;
        SpannableString text3;
        SpannableString text2;
        SpannableString text1;
        boolean z11;
        boolean z12;
        boolean z13;
        String str5;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int i = this.mMode;
        RecycleListBean recycleListBean = this.mData;
        long j4 = j & 54;
        if (j4 != 0) {
            z2 = i == 0;
            if (j4 != 0) {
                j = z2 ? j | 128 : j | 64;
            }
            z = ((j & 36) == 0 || i == 0) ? false : true;
        } else {
            z = false;
            z2 = false;
        }
        String str6 = null;
        List<RecycleListBean.Account> list3 = null;
        String str7 = null;
        if ((j & 59) != 0) {
            if ((j & 35) != 0) {
                GameBean game = recycleListBean != null ? recycleListBean.getGame() : null;
                updateRegistration(0, game);
                if (game != null) {
                    name_suffix = game.getName_suffix();
                    showName = game.getShowName();
                    icon = game.getIcon();
                } else {
                    icon = null;
                    name_suffix = null;
                    showName = null;
                }
                zIsEmpty = TextUtils.isEmpty(name_suffix);
            } else {
                icon = null;
                name_suffix = null;
                showName = null;
                zIsEmpty = false;
            }
            if ((j & 34) != 0) {
                if (recycleListBean != null) {
                    String recycleMoney = recycleListBean.getRecycleMoney();
                    List<RecycleListBean.Account> accounts = recycleListBean.getAccounts();
                    text3 = recycleListBean.getText3();
                    text2 = recycleListBean.getText2();
                    text1 = recycleListBean.getText1();
                    str5 = recycleMoney;
                    list3 = accounts;
                } else {
                    str5 = null;
                    text3 = null;
                    text2 = null;
                    text1 = null;
                }
                str4 = (list3 != null ? list3.size() : 0) + "个小号";
                list2 = list3;
                str7 = str5;
            } else {
                str4 = null;
                list2 = null;
                text3 = null;
                text2 = null;
                text1 = null;
            }
            if ((j & 42) != 0) {
                int selectType = recycleListBean != null ? recycleListBean.getSelectType() : 0;
                z13 = selectType != 0;
                z12 = selectType != 2;
                z11 = selectType != 1;
            } else {
                z11 = false;
                z12 = false;
                z13 = false;
            }
            if ((j & 50) == 0 || recycleListBean == null) {
                str3 = str4;
                z6 = z11;
                z5 = z12;
                str = name_suffix;
                z4 = zIsEmpty;
                z3 = z13;
                spannableString = text3;
                spannableString2 = text2;
                spannableString3 = text1;
                selected = false;
                j2 = 128;
            } else {
                str3 = str4;
                z6 = z11;
                str = name_suffix;
                z3 = z13;
                j2 = 128;
                selected = recycleListBean.getSelected();
                z5 = z12;
                z4 = zIsEmpty;
                spannableString = text3;
                spannableString2 = text2;
                spannableString3 = text1;
            }
            List<RecycleListBean.Account> list4 = list2;
            str2 = str7;
            str6 = showName;
            list = list4;
        } else {
            str = null;
            icon = null;
            str2 = null;
            list = null;
            spannableString = null;
            spannableString2 = null;
            spannableString3 = null;
            str3 = null;
            j2 = 128;
            z3 = false;
            z4 = false;
            z5 = false;
            z6 = false;
            selected = false;
        }
        if ((j & j2) != 0) {
            if (recycleListBean != null) {
                selected = recycleListBean.getSelected();
            }
            z8 = !selected;
            z7 = selected;
        } else {
            z7 = selected;
            z8 = false;
        }
        long j5 = j & 54;
        if (j5 != 0) {
            if (!z2) {
                z8 = false;
            }
            j3 = 36;
            boolean z14 = z8;
            z9 = z7;
            z10 = z14;
        } else {
            z9 = z7;
            z10 = false;
            j3 = 36;
        }
        if ((j & j3) != 0) {
            DataBindingHelper.setViewGone(this.llBox, z);
            DataBindingHelper.setViewGone(this.mboundView12, z2);
            DataBindingHelper.setViewGone(this.mboundView13, z2);
            DataBindingHelper.setViewGone(this.mboundView14, z2);
            DataBindingHelper.setViewGone(this.mboundView4, z);
            DataBindingHelper.setViewGone(this.mboundView7, z);
            DataBindingHelper.setViewGone(this.tvShow, z);
        }
        if ((j & 35) != 0) {
            DataBindingHelper.setGameIcon(this.mboundView1, icon);
            DataBindingHelper.setViewGone(this.mboundView2, z4);
            TextViewBindingAdapter.setText(this.mboundView2, str);
            TextViewBindingAdapter.setText(this.mboundView3, str6);
        }
        if ((j & 42) != 0) {
            DataBindingHelper.setViewGone(this.mboundView10, z6);
            DataBindingHelper.setViewGone(this.mboundView11, z5);
            DataBindingHelper.setViewGone(this.mboundView9, z3);
        }
        if ((j & 34) != 0) {
            TextViewBindingAdapter.setText(this.mboundView12, str2);
            TextViewBindingAdapter.setText(this.mboundView5, spannableString3);
            TextViewBindingAdapter.setText(this.mboundView6, spannableString2);
            TextViewBindingAdapter.setText(this.mboundView7, spannableString);
            DataBindingHelper.setRvData(this.rv, list);
            TextViewBindingAdapter.setText(this.tvShow, str3);
        }
        if ((32 & j) != 0) {
            DataBindingHelper.setSelected(this.mboundView3, true);
        }
        if (j5 != 0) {
            DataBindingHelper.setViewGone(this.rv, z10);
        }
        if ((j & 50) != 0) {
            DataBindingHelper.setSelected(this.tvShow, z9);
        }
    }
}
