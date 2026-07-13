package com.cy.yyjia.zhe28.databinding;

import android.text.Html;
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
import com.cy.yyjia.zhe28.domain.CardInfoBean;
import com.cy.yyjia.zhe28.domain.CardModuleBean;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;
import java.util.List;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentSubscribeBindingImpl extends FragmentSubscribeBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private OnClickListenerImpl mOnClickOnClickAndroidViewViewOnClickListener;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView11;
    private final TextView mboundView13;
    private final ShapeTextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView8;
    private final TextView mboundView9;

    public FragmentSubscribeBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }

    private FragmentSubscribeBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (RecyclerView) bindings[7], (RecyclerView) bindings[6], (LinearLayout) bindings[12], (ShapeTextView) bindings[5], (TextView) bindings[10], (TextView) bindings[2]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[11];
        this.mboundView11 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[13];
        this.mboundView13 = textView2;
        textView2.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[3];
        this.mboundView3 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView3 = (TextView) bindings[4];
        this.mboundView4 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[8];
        this.mboundView8 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[9];
        this.mboundView9 = textView5;
        textView5.setTag(null);
        this.rv.setTag(null);
        this.rvTitle.setTag(null);
        this.tvBuy.setTag(null);
        this.tvGain.setTag(null);
        this.tvGame.setTag(null);
        this.tvNickname.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 64L;
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
            setData((CardModuleBean) variable);
        } else if (119 == variableId) {
            setUser((UserBean) variable);
        } else {
            if (68 != variableId) {
                return false;
            }
            setOnClick((View.OnClickListener) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentSubscribeBinding
    public void setData(CardModuleBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentSubscribeBinding
    public void setUser(UserBean User) {
        this.mUser = User;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(119);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentSubscribeBinding
    public void setOnClick(View.OnClickListener OnClick) {
        this.mOnClick = OnClick;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(68);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId == 0) {
            return onChangeDataSelectedModule((CardInfoBean) object, fieldId);
        }
        if (localFieldId == 1) {
            return onChangeDataSelectedModuleSelectPrice((CardInfoBean.Price) object, fieldId);
        }
        if (localFieldId != 2) {
            return false;
        }
        return onChangeDataGetSelectedModule((CardInfoBean) object, fieldId);
    }

    private boolean onChangeDataSelectedModule(CardInfoBean DataSelectedModule, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        if (fieldId != 91) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeDataSelectedModuleSelectPrice(CardInfoBean.Price DataSelectedModuleSelectPrice, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeDataGetSelectedModule(CardInfoBean DataGetSelectedModule, int fieldId) {
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
        CardInfoBean selectedModule;
        String str;
        List<CardInfoBean> modules;
        String str2;
        String desc;
        List<CardInfoBean.Price> list;
        String str3;
        String desc2;
        String str4;
        boolean z;
        boolean zIsEmpty;
        boolean z2;
        boolean z3;
        String nickName;
        String str5;
        OnClickListenerImpl value;
        boolean z4;
        String str6;
        boolean z5;
        String str7;
        List<CardInfoBean.Price> list2;
        String cardTimeStr;
        String publicize;
        boolean z6;
        CardInfoBean.UserCard userCard;
        int iIsRreceive;
        String str8;
        CardInfoBean.Config config;
        CardInfoBean.UserCard userCard2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        CardModuleBean cardModuleBean = this.mData;
        UserBean userBean = this.mUser;
        View.OnClickListener onClickListener = this.mOnClick;
        CharSequence charSequence = null;
        if ((79 & j) != 0) {
            if ((j & 75) != 0) {
                selectedModule = cardModuleBean != null ? cardModuleBean.getSelectedModule() : null;
                updateRegistration(0, selectedModule);
                long j2 = j & 73;
                if (j2 != 0) {
                    if (selectedModule != null) {
                        list2 = selectedModule.getList();
                        config = selectedModule.getConfig();
                        userCard2 = selectedModule.getUserCard();
                    } else {
                        list2 = null;
                        config = null;
                        userCard2 = null;
                    }
                    desc2 = config != null ? config.getDesc() : null;
                    cardTimeStr = userCard2 != null ? userCard2.getCardTimeStr() : null;
                    z6 = userCard2 == null;
                    if (j2 != 0) {
                        j |= z6 ? 4352L : 2176L;
                    }
                    zIsEmpty = TextUtils.isEmpty(desc2);
                    if ((j & 73) != 0) {
                        j = zIsEmpty ? j | 16384 : j | IjkMediaMeta.AV_CH_TOP_FRONT_CENTER;
                    }
                } else {
                    list2 = null;
                    desc2 = null;
                    cardTimeStr = null;
                    zIsEmpty = false;
                    z6 = false;
                }
                CardInfoBean.Price selectPrice = selectedModule != null ? selectedModule.getSelectPrice() : null;
                updateRegistration(1, selectPrice);
                if (selectPrice != null) {
                    publicize = selectPrice.getPublicize();
                    String price = selectPrice.getPrice();
                    desc = selectPrice.getDesc();
                    str8 = price;
                } else {
                    str8 = null;
                    desc = null;
                    publicize = null;
                }
                str = ("￥" + str8) + " 确定购买";
            } else {
                selectedModule = null;
                str = null;
                desc = null;
                list2 = null;
                desc2 = null;
                cardTimeStr = null;
                publicize = null;
                zIsEmpty = false;
                z6 = false;
            }
            modules = ((j & 72) == 0 || cardModuleBean == null) ? null : cardModuleBean.getModules();
            long j3 = j & 76;
            if (j3 != 0) {
                CardInfoBean selectedModule2 = cardModuleBean != null ? cardModuleBean.getSelectedModule() : null;
                updateRegistration(2, selectedModule2);
                if (selectedModule2 != null) {
                    iIsRreceive = selectedModule2.isRreceive();
                    userCard = selectedModule2.getUserCard();
                } else {
                    userCard = null;
                    iIsRreceive = 0;
                }
                z2 = iIsRreceive == 1;
                z = userCard == null;
                if (j3 != 0) {
                    j |= z2 ? 1024L : 512L;
                }
                str2 = z2 ? "已领取" : "全部领取";
                list = list2;
                z3 = z6;
                str3 = cardTimeStr;
                str4 = publicize;
            } else {
                str2 = null;
                list = list2;
                z3 = z6;
                str3 = cardTimeStr;
                str4 = publicize;
                z = false;
                z2 = false;
            }
        } else {
            selectedModule = null;
            str = null;
            modules = null;
            str2 = null;
            desc = null;
            list = null;
            str3 = null;
            desc2 = null;
            str4 = null;
            z = false;
            zIsEmpty = false;
            z2 = false;
            z3 = false;
        }
        long j4 = j & 80;
        if (j4 == 0 || userBean == null) {
            nickName = null;
            str5 = null;
        } else {
            String user_avatar = userBean.getUser_avatar();
            nickName = userBean.getNickName();
            str5 = user_avatar;
        }
        long j5 = j & 96;
        if (j5 == 0 || onClickListener == null) {
            value = null;
        } else {
            OnClickListenerImpl onClickListenerImpl = this.mOnClickOnClickAndroidViewViewOnClickListener;
            if (onClickListenerImpl == null) {
                onClickListenerImpl = new OnClickListenerImpl();
                this.mOnClickOnClickAndroidViewViewOnClickListener = onClickListenerImpl;
            }
            value = onClickListenerImpl.setValue(onClickListener);
        }
        String tags = ((j & 128) == 0 || selectedModule == null) ? null : selectedModule.getTags();
        CharSequence charSequenceFromHtml = (IjkMediaMeta.AV_CH_TOP_FRONT_CENTER & j) != 0 ? Html.fromHtml(desc2) : null;
        boolean zIsEmpty2 = (j & 2048) != 0 ? TextUtils.isEmpty(str3) : false;
        long j6 = j & 73;
        if (j6 != 0) {
            String str9 = tags;
            z4 = z3;
            if (z4) {
                str9 = "未开通";
            }
            String str10 = str9;
            boolean z7 = z4 ? true : zIsEmpty2;
            if (zIsEmpty) {
                charSequenceFromHtml = "";
            }
            str6 = str10;
            charSequence = charSequenceFromHtml;
            z5 = z7;
        } else {
            z4 = z3;
            str6 = null;
            z5 = false;
        }
        if (j4 != 0) {
            str7 = str2;
            DataBindingHelper.setUserIcon(this.mboundView1, str5);
            TextViewBindingAdapter.setText(this.tvNickname, nickName);
        } else {
            str7 = str2;
        }
        if (j6 != 0) {
            TextViewBindingAdapter.setText(this.mboundView11, charSequence);
            DataBindingHelper.setSelected(this.mboundView3, z4);
            TextViewBindingAdapter.setText(this.mboundView3, str6);
            DataBindingHelper.setViewGone(this.mboundView4, z5);
            TextViewBindingAdapter.setText(this.mboundView4, str3);
            DataBindingHelper.setRvData(this.rv, list);
        }
        if ((j & 75) != 0) {
            TextViewBindingAdapter.setText(this.mboundView13, str);
            TextViewBindingAdapter.setText(this.mboundView8, desc);
            TextViewBindingAdapter.setText(this.mboundView9, str4);
        }
        if ((72 & j) != 0) {
            DataBindingHelper.setRvData(this.rvTitle, modules);
        }
        if (j5 != 0) {
            this.tvBuy.setOnClickListener(value);
            this.tvGain.setOnClickListener(value);
            this.tvGame.setOnClickListener(value);
        }
        if ((j & 76) != 0) {
            DataBindingHelper.setViewGone(this.tvGain, z);
            DataBindingHelper.setSelected(this.tvGain, z2);
            TextViewBindingAdapter.setText(this.tvGain, str7);
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
