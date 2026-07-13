package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
public abstract class ActivityCommentDetailBinding extends ViewDataBinding {
    public final Button btn;
    public final TextView content;
    public final EditText et;

    @Bindable
    protected CommentBean mData;

    @Bindable
    protected String mText;
    public final AppCompatRatingBar ratingbar;
    public final RecyclerView rv;
    public final RecyclerView rvEdit;
    public final RecyclerView rvPic;
    public final TextView tvFolder;
    public final ImageView tvOff;
    public final ShapeTextView tvPraise;
    public final ImageView userIcon;
    public final TextView userName;
    public final ImageView vip1;
    public final ImageView vip2;

    public abstract void setData(CommentBean data);

    public abstract void setText(String text);

    protected ActivityCommentDetailBinding(Object _bindingComponent, View _root, int _localFieldCount, Button btn, TextView content, EditText et, AppCompatRatingBar ratingbar, RecyclerView rv, RecyclerView rvEdit, RecyclerView rvPic, TextView tvFolder, ImageView tvOff, ShapeTextView tvPraise, ImageView userIcon, TextView userName, ImageView vip1, ImageView vip2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.content = content;
        this.et = et;
        this.ratingbar = ratingbar;
        this.rv = rv;
        this.rvEdit = rvEdit;
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

    public String getText() {
        return this.mText;
    }

    public static ActivityCommentDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityCommentDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityCommentDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_comment_detail, root, attachToRoot, component);
    }

    public static ActivityCommentDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityCommentDetailBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityCommentDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_comment_detail, null, false, component);
    }

    public static ActivityCommentDetailBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityCommentDetailBinding bind(View view, Object component) {
        return (ActivityCommentDetailBinding) bind(component, view, R.layout.activity_comment_detail);
    }
}
