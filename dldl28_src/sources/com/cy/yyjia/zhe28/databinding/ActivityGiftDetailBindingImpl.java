package com.cy.yyjia.zhe28.databinding;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.GiftDetailBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityGiftDetailBindingImpl extends ActivityGiftDetailBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView2;
    private final TextView mboundView4;
    private final TextView mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView7;

    public ActivityGiftDetailBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private ActivityGiftDetailBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ImageView) bindings[1], (LinearLayout) bindings[3]);
        this.mDirtyFlags = -1L;
        this.gameIcon.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[4];
        this.mboundView4 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[5];
        this.mboundView5 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[6];
        this.mboundView6 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[7];
        this.mboundView7 = textView5;
        textView5.setTag(null);
        this.tag.setTag(null);
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
        if (23 != variableId) {
            return false;
        }
        setData((GiftDetailBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGiftDetailBinding
    public void setData(GiftDetailBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
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
        String type;
        List<GameBean.Tag> list;
        String str;
        String str2;
        String content;
        String howToUser;
        boolean zIsEmpty;
        String str3;
        long j2;
        CharSequence charSequence;
        String icon;
        List<GameBean.Tag> tags;
        String name;
        String availableTime;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        GiftDetailBean giftDetailBean = this.mData;
        boolean z = false;
        if ((j & 7) != 0) {
            GameBean game = giftDetailBean != null ? giftDetailBean.getGame() : null;
            updateRegistration(0, game);
            if (game != null) {
                tags = game.getTags();
                name = game.getName();
                icon = game.getIcon();
            } else {
                icon = null;
                tags = null;
                name = null;
            }
            long j3 = j & 6;
            if (j3 != 0) {
                if (giftDetailBean != null) {
                    availableTime = giftDetailBean.getAvailableTime();
                    content = giftDetailBean.getContent();
                    howToUser = giftDetailBean.getHowToUser();
                    type = giftDetailBean.getType();
                } else {
                    type = null;
                    availableTime = null;
                    content = null;
                    howToUser = null;
                }
                boolean zIsEmpty2 = TextUtils.isEmpty(content);
                zIsEmpty = TextUtils.isEmpty(howToUser);
                if (j3 != 0) {
                    j = zIsEmpty2 ? j | 16 : j | 8;
                }
                if ((j & 6) != 0) {
                    j = zIsEmpty ? j | 64 : j | 32;
                }
                list = tags;
                str3 = name;
                str2 = icon;
                str = availableTime;
                z = zIsEmpty2;
            } else {
                list = tags;
                str3 = name;
                type = null;
                content = null;
                howToUser = null;
                zIsEmpty = false;
                str2 = icon;
                str = null;
            }
        } else {
            type = null;
            list = null;
            str = null;
            str2 = null;
            content = null;
            howToUser = null;
            zIsEmpty = false;
            str3 = null;
        }
        Spanned spannedFromHtml = (8 & j) != 0 ? Html.fromHtml(content) : null;
        CharSequence charSequenceFromHtml = (32 & j) != 0 ? Html.fromHtml(howToUser) : null;
        long j4 = j & 6;
        if (j4 != 0) {
            CharSequence charSequence2 = z ? "" : spannedFromHtml;
            if (zIsEmpty) {
                charSequenceFromHtml = "";
            }
            charSequence = charSequence2;
            j2 = 7;
        } else {
            charSequenceFromHtml = null;
            j2 = 7;
            charSequence = null;
        }
        if ((j & j2) != 0) {
            DataBindingHelper.setGameIcon(this.gameIcon, str2);
            TextViewBindingAdapter.setText(this.mboundView2, str3);
            DataBindingHelper.setTags(this.tag, list);
        }
        if (j4 != 0) {
            TextViewBindingAdapter.setText(this.mboundView4, type);
            TextViewBindingAdapter.setText(this.mboundView5, charSequence);
            TextViewBindingAdapter.setText(this.mboundView6, charSequenceFromHtml);
            TextViewBindingAdapter.setText(this.mboundView7, str);
        }
    }
}
