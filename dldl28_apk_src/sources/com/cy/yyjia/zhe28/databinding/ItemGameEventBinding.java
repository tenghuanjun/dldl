package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.EventBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGameEventBinding extends ViewDataBinding {
    public final ShapeTextView btn;

    @Bindable
    protected EventBean mData;
    public final ShapeTextView tvTag;
    public final TextView tvTag1;

    public abstract void setData(EventBean data);

    protected ItemGameEventBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView btn, ShapeTextView tvTag, TextView tvTag1) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.tvTag = tvTag;
        this.tvTag1 = tvTag1;
    }

    public EventBean getData() {
        return this.mData;
    }

    public static ItemGameEventBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameEventBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGameEventBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_event, root, attachToRoot, component);
    }

    public static ItemGameEventBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameEventBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemGameEventBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_event, null, false, component);
    }

    public static ItemGameEventBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameEventBinding bind(View view, Object component) {
        return (ItemGameEventBinding) bind(component, view, R.layout.item_game_event);
    }
}
