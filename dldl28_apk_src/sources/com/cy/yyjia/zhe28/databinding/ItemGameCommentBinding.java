package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.CommentBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGameCommentBinding extends ViewDataBinding {
    public final TextView content;

    @Bindable
    protected CommentBean mData;
    public final AppCompatRatingBar ratingbar;
    public final RecyclerView rv;
    public final RecyclerView rvPic;
    public final TextView tvFolder;
    public final ImageView tvOff;
    public final ShapeTextView tvPraise;
    public final ImageView userIcon;
    public final TextView userName;
    public final ImageView vip1;
    public final ImageView vip2;

    public abstract void setData(CommentBean data);

    protected ItemGameCommentBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView content, AppCompatRatingBar ratingbar, RecyclerView rv, RecyclerView rvPic, TextView tvFolder, ImageView tvOff, ShapeTextView tvPraise, ImageView userIcon, TextView userName, ImageView vip1, ImageView vip2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.content = content;
        this.ratingbar = ratingbar;
        this.rv = rv;
        this.rvPic = rvPic;
        this.tvFolder = tvFolder;
        this.tvOff = tvOff;
        this.tvPraise = tvPraise;
        this.userIcon = userIcon;
        this.userName = userName;
        this.vip1 = vip1;
        this.vip2 = vip2;
    }

    public CommentBean getData() {
        return this.mData;
    }

    public static ItemGameCommentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameCommentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGameCommentBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_comment, root, attachToRoot, component);
    }

    public static ItemGameCommentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameCommentBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemGameCommentBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_comment, null, false, component);
    }

    public static ItemGameCommentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameCommentBinding bind(View view, Object component) {
        return (ItemGameCommentBinding) bind(component, view, R.layout.item_game_comment);
    }
}
