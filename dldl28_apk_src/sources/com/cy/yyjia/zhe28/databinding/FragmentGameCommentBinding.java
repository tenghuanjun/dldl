package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameScoreBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentGameCommentBinding extends ViewDataBinding {
    public final ImageView btnTop;

    @Bindable
    protected GameScoreBean mData;
    public final RecyclerView rv;
    public final RecyclerView rvCategory;
    public final TextView tvCommentNum;
    public final TextView tvScore;
    public final TextView tvSort;

    public abstract void setData(GameScoreBean data);

    protected FragmentGameCommentBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView btnTop, RecyclerView rv, RecyclerView rvCategory, TextView tvCommentNum, TextView tvScore, TextView tvSort) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnTop = btnTop;
        this.rv = rv;
        this.rvCategory = rvCategory;
        this.tvCommentNum = tvCommentNum;
        this.tvScore = tvScore;
        this.tvSort = tvSort;
    }

    public GameScoreBean getData() {
        return this.mData;
    }

    public static FragmentGameCommentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentGameCommentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentGameCommentBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_game_comment, root, attachToRoot, component);
    }

    public static FragmentGameCommentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentGameCommentBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentGameCommentBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_game_comment, null, false, component);
    }

    public static FragmentGameCommentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentGameCommentBinding bind(View view, Object component) {
        return (FragmentGameCommentBinding) bind(component, view, R.layout.fragment_game_comment);
    }
}
