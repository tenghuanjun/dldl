package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.NoviceGameBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemNoviceGameGiftBinding extends ViewDataBinding {
    public final TextView btn;

    @Bindable
    protected NoviceGameBean.GiftBean mData;

    public abstract void setData(NoviceGameBean.GiftBean data);

    protected ItemNoviceGameGiftBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
    }

    public NoviceGameBean.GiftBean getData() {
        return this.mData;
    }

    public static ItemNoviceGameGiftBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemNoviceGameGiftBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemNoviceGameGiftBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_novice_game_gift, root, attachToRoot, component);
    }

    public static ItemNoviceGameGiftBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemNoviceGameGiftBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemNoviceGameGiftBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_novice_game_gift, null, false, component);
    }

    public static ItemNoviceGameGiftBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemNoviceGameGiftBinding bind(View view, Object component) {
        return (ItemNoviceGameGiftBinding) bind(component, view, R.layout.item_novice_game_gift);
    }
}
