package com.cy.yyjia.zhe28.databinding;

import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.databinding.adapters.ViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.CardInfoBean;
import com.cy.yyjia.zhe28.domain.CardModuleBean;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;
import java.util.List;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityMonthCardBindingImpl extends ActivityMonthCardBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView10;
    private final TextView mboundView11;
    private final TextView mboundView12;
    private final ImageView mboundView2;
    private final ShapeTextView mboundView4;
    private final TextView mboundView5;
    private final LinearLayout mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.rl_title, 13);
        sparseIntArray.put(R.id.iv_back, 14);
        sparseIntArray.put(R.id.tv_record, 15);
        sparseIntArray.put(R.id.tv_buy, 16);
        sparseIntArray.put(R.id.tv_game, 17);
    }

    public ActivityMonthCardBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds));
    }

    private ActivityMonthCardBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (ImageView) bindings[14], (RelativeLayout) bindings[13], (RecyclerView) bindings[8], (RecyclerView) bindings[7], (LinearLayout) bindings[16], (ShapeTextView) bindings[6], (TextView) bindings[17], (TextView) bindings[3], (TextView) bindings[15]);
        this.mDirtyFlags = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[10];
        this.mboundView10 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[11];
        this.mboundView11 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[12];
        this.mboundView12 = textView4;
        textView4.setTag(null);
        ImageView imageView = (ImageView) bindings[2];
        this.mboundView2 = imageView;
        imageView.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[4];
        this.mboundView4 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView5 = (TextView) bindings[5];
        this.mboundView5 = textView5;
        textView5.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[9];
        this.mboundView9 = linearLayout;
        linearLayout.setTag(null);
        this.rv.setTag(null);
        this.rvTitle.setTag(null);
        this.tvGain.setTag(null);
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
        } else {
            if (119 != variableId) {
                return false;
            }
            setUser((UserBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityMonthCardBinding
    public void setData(CardModuleBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityMonthCardBinding
    public void setUser(UserBean User) {
        this.mUser = User;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(119);
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
        if (fieldId == 91) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        if (fieldId != 92) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
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
        String str;
        CardInfoBean selectedModule;
        String str2;
        List<CardInfoBean.Price> list;
        List<CardInfoBean> modules;
        String desc;
        String publicize;
        String desc2;
        String str3;
        boolean z;
        boolean z2;
        boolean zIsEmpty;
        boolean z3;
        int selectPricePosition;
        boolean z4;
        String str4;
        String str5;
        String nickName;
        Drawable drawable;
        Drawable drawable2;
        String str6;
        boolean z5;
        long j2;
        boolean z6;
        boolean z7;
        String cardTimeStr;
        boolean z8;
        CardInfoBean.UserCard userCard;
        int iIsRreceive;
        long j3;
        String str7;
        CardInfoBean.Config config;
        CardInfoBean.UserCard userCard2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        CardModuleBean cardModuleBean = this.mData;
        UserBean userBean = this.mUser;
        Drawable drawable3 = null;
        if ((111 & j) != 0) {
            if ((107 & j) != 0) {
                selectedModule = cardModuleBean != null ? cardModuleBean.getSelectedModule() : null;
                updateRegistration(0, selectedModule);
                long j4 = j & 73;
                if (j4 != 0) {
                    if (selectedModule != null) {
                        list = selectedModule.getList();
                        config = selectedModule.getConfig();
                        userCard2 = selectedModule.getUserCard();
                    } else {
                        list = null;
                        config = null;
                        userCard2 = null;
                    }
                    desc2 = config != null ? config.getDesc() : null;
                    cardTimeStr = userCard2 != null ? userCard2.getCardTimeStr() : null;
                    z8 = userCard2 == null;
                    if (j4 != 0) {
                        j |= z8 ? 65792L : 32896L;
                    }
                    zIsEmpty = TextUtils.isEmpty(desc2);
                    if ((j & 73) != 0) {
                        j = zIsEmpty ? j | 262144 : j | IjkMediaMeta.AV_CH_TOP_BACK_RIGHT;
                    }
                } else {
                    list = null;
                    desc2 = null;
                    cardTimeStr = null;
                    zIsEmpty = false;
                    z8 = false;
                }
                long j5 = j & 105;
                if (j5 != 0) {
                    selectPricePosition = selectedModule != null ? selectedModule.getSelectPricePosition() : 0;
                    z4 = selectPricePosition == 2;
                    if (j5 != 0) {
                        j = z4 ? j | 4096 : j | 2048;
                    }
                    j3 = 75;
                } else {
                    selectPricePosition = 0;
                    j3 = 75;
                    z4 = false;
                }
                if ((j & j3) != 0) {
                    CardInfoBean.Price selectPrice = selectedModule != null ? selectedModule.getSelectPrice() : null;
                    updateRegistration(1, selectPrice);
                    if (selectPrice != null) {
                        publicize = selectPrice.getPublicize();
                        String price = selectPrice.getPrice();
                        desc = selectPrice.getDesc();
                        str7 = price;
                    } else {
                        str7 = null;
                        desc = null;
                        publicize = null;
                    }
                    str2 = ("￥" + str7) + " 确定购买";
                } else {
                    str2 = null;
                    desc = null;
                    publicize = null;
                }
            } else {
                selectedModule = null;
                str2 = null;
                list = null;
                desc = null;
                publicize = null;
                desc2 = null;
                cardTimeStr = null;
                zIsEmpty = false;
                selectPricePosition = 0;
                z8 = false;
                z4 = false;
            }
            modules = ((j & 72) == 0 || cardModuleBean == null) ? null : cardModuleBean.getModules();
            long j6 = j & 76;
            if (j6 != 0) {
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
                if (j6 != 0) {
                    j |= z2 ? 16384L : IjkMediaMeta.AV_CH_TOP_FRONT_CENTER;
                }
                str = z2 ? "已领取" : "全部领取";
                z3 = z8;
                str3 = cardTimeStr;
            } else {
                str = null;
                z3 = z8;
                str3 = cardTimeStr;
                z = false;
                z2 = false;
            }
        } else {
            str = null;
            selectedModule = null;
            str2 = null;
            list = null;
            modules = null;
            desc = null;
            publicize = null;
            desc2 = null;
            str3 = null;
            z = false;
            z2 = false;
            zIsEmpty = false;
            z3 = false;
            selectPricePosition = 0;
            z4 = false;
        }
        if ((j & 80) == 0 || userBean == null) {
            str4 = str;
            str5 = null;
            nickName = null;
        } else {
            String user_avatar = userBean.getUser_avatar();
            nickName = userBean.getNickName();
            str4 = str;
            str5 = user_avatar;
        }
        String tags = ((j & 128) == 0 || selectedModule == null) ? null : selectedModule.getTags();
        CharSequence charSequenceFromHtml = (j & IjkMediaMeta.AV_CH_TOP_BACK_RIGHT) != 0 ? Html.fromHtml(desc2) : null;
        boolean zIsEmpty2 = (j & IjkMediaMeta.AV_CH_TOP_BACK_LEFT) != 0 ? TextUtils.isEmpty(str3) : false;
        long j7 = j & 2048;
        String str8 = tags;
        if (j7 != 0) {
            boolean z9 = selectPricePosition == 1;
            if (j7 != 0) {
                j |= z9 ? 1024L : 512L;
            }
            drawable = z9 ? AppCompatResources.getDrawable(this.mboundView9.getContext(), R.drawable.bg_card_intro2) : AppCompatResources.getDrawable(this.mboundView9.getContext(), R.drawable.bg_card_intro1);
        } else {
            drawable = null;
        }
        long j8 = j & 73;
        if (j8 != 0) {
            if (z3) {
                str8 = "未开通";
            }
            boolean z10 = z3 ? true : zIsEmpty2;
            if (zIsEmpty) {
                charSequenceFromHtml = "";
            }
            z5 = z10;
            j2 = 105;
            drawable2 = drawable;
            str6 = str8;
        } else {
            drawable2 = drawable;
            str6 = null;
            charSequenceFromHtml = null;
            z5 = false;
            j2 = 105;
        }
        long j9 = j & j2;
        if (j9 == 0) {
            z6 = z;
            z7 = z2;
        } else if (z4) {
            z7 = z2;
            z6 = z;
            drawable3 = AppCompatResources.getDrawable(this.mboundView9.getContext(), R.drawable.bg_card_intro3);
        } else {
            z6 = z;
            z7 = z2;
            drawable3 = drawable2;
        }
        Drawable drawable4 = drawable3;
        if ((j & 75) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, str2);
            TextViewBindingAdapter.setText(this.mboundView10, desc);
            TextViewBindingAdapter.setText(this.mboundView11, publicize);
        }
        if (j8 != 0) {
            TextViewBindingAdapter.setText(this.mboundView12, charSequenceFromHtml);
            DataBindingHelper.setSelected(this.mboundView4, z3);
            TextViewBindingAdapter.setText(this.mboundView4, str6);
            DataBindingHelper.setViewGone(this.mboundView5, z5);
            TextViewBindingAdapter.setText(this.mboundView5, str3);
            DataBindingHelper.setRvData(this.rv, list);
        }
        if ((j & 80) != 0) {
            DataBindingHelper.setUserIcon(this.mboundView2, str5);
            TextViewBindingAdapter.setText(this.tvNickname, nickName);
        }
        if (j9 != 0) {
            ViewBindingAdapter.setBackground(this.mboundView9, drawable4);
        }
        if ((j & 72) != 0) {
            DataBindingHelper.setRvData(this.rvTitle, modules);
        }
        if ((j & 76) != 0) {
            DataBindingHelper.setViewGone(this.tvGain, z6);
            DataBindingHelper.setSelected(this.tvGain, z7);
            TextViewBindingAdapter.setText(this.tvGain, str4);
        }
    }
}
