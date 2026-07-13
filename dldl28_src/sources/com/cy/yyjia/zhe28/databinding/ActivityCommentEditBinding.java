package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.CommentConfig;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityCommentEditBinding extends ViewDataBinding {

    @Bindable
    protected String mContent;

    @Bindable
    protected CommentConfig mData;

    @Bindable
    protected String mIcon;

    @Bindable
    protected String mName;

    @Bindable
    protected CommentConfig.Tag mTag;

    @Bindable
    protected String mTitle;
    public final Navigation navigation;
    public final AppCompatRatingBar ratingbar;
    public final RecyclerView rv;
    public final RecyclerView rvContent;
    public final RecyclerView rvTag;
    public final ShapeTextView tvGo;

    public abstract void setContent(String content);

    public abstract void setData(CommentConfig data);

    public abstract void setIcon(String icon);

    public abstract void setName(String name);

    public abstract void setTag(CommentConfig.Tag tag);

    public abstract void setTitle(String title);

    protected ActivityCommentEditBinding(Object _bindingComponent, View _root, int _localFieldCount, Navigation navigation, AppCompatRatingBar ratingbar, RecyclerView rv, RecyclerView rvContent, RecyclerView rvTag, ShapeTextView tvGo) {
        super(_bindingComponent, _root, _localFieldCount);
        this.navigation = navigation;
        this.ratingbar = ratingbar;
        this.rv = rv;
        this.rvContent = rvContent;
        this.rvTag = rvTag;
        this.tvGo = tvGo;
    }

    public String getContent() {
        return this.mContent;
    }

    public String getIcon() {
        return this.mIcon;
    }

    public String getName() {
        return this.mName;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public CommentConfig getData() {
        return this.mData;
    }

    public CommentConfig.Tag getTag() {
        return this.mTag;
    }

    public static ActivityCommentEditBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityCommentEditBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityCommentEditBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_comment_edit, root, attachToRoot, component);
    }

    public static ActivityCommentEditBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityCommentEditBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityCommentEditBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_comment_edit, null, false, component);
    }

    public static ActivityCommentEditBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityCommentEditBinding bind(View view, Object component) {
        return (ActivityCommentEditBinding) bind(component, view, R.layout.activity_comment_edit);
    }
}
