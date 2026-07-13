package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.LotteryGiftBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogLotteryResultBinding extends ViewDataBinding {
    public final ImageView anim;
    public final Button btn;
    public final LinearLayout ll;

    @Bindable
    protected LotteryGiftBean mData;
    public final RecyclerView rv;
    public final ShapeTextView tvSkip;

    public abstract void setData(LotteryGiftBean data);

    protected DialogLotteryResultBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView anim, Button btn, LinearLayout ll, RecyclerView rv, ShapeTextView tvSkip) {
        super(_bindingComponent, _root, _localFieldCount);
        this.anim = anim;
        this.btn = btn;
        this.ll = ll;
        this.rv = rv;
        this.tvSkip = tvSkip;
    }

    public LotteryGiftBean getData() {
        return this.mData;
    }

    public static DialogLotteryResultBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogLotteryResultBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogLotteryResultBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_lottery_result, root, attachToRoot, component);
    }

    public static DialogLotteryResultBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogLotteryResultBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogLotteryResultBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_lottery_result, null, false, component);
    }

    public static DialogLotteryResultBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogLotteryResultBinding bind(View view, Object component) {
        return (DialogLotteryResultBinding) bind(component, view, R.layout.dialog_lottery_result);
    }
}
