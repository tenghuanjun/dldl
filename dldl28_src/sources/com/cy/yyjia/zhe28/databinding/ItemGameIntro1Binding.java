package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBannerBean;
import com.cy.yyjia.zhe28.view.WancmsStandardPlayer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGameIntro1Binding extends ViewDataBinding {
    public final CardView cv;

    @Bindable
    protected GameBannerBean mData;
    public final WancmsStandardPlayer player;

    public abstract void setData(GameBannerBean data);

    protected ItemGameIntro1Binding(Object _bindingComponent, View _root, int _localFieldCount, CardView cv, WancmsStandardPlayer player) {
        super(_bindingComponent, _root, _localFieldCount);
        this.cv = cv;
        this.player = player;
    }

    public GameBannerBean getData() {
        return this.mData;
    }

    public static ItemGameIntro1Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameIntro1Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGameIntro1Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_intro1, root, attachToRoot, component);
    }

    public static ItemGameIntro1Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameIntro1Binding inflate(LayoutInflater inflater, Object component) {
        return (ItemGameIntro1Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_intro1, null, false, component);
    }

    public static ItemGameIntro1Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameIntro1Binding bind(View view, Object component) {
        return (ItemGameIntro1Binding) bind(component, view, R.layout.item_game_intro1);
    }
}
