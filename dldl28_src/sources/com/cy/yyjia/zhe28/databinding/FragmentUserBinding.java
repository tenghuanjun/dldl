package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.domain.YunIndexBean;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;
import com.hjq.shape.view.ShapeView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentUserBinding extends ViewDataBinding {
    public final ImageView btnDownload;
    public final TextView btnService;
    public final ImageView btnSetting;
    public final ConstraintLayout cl;
    public final RelativeLayout clMonth;
    public final RelativeLayout clSqk;
    public final ImageView ivMessage;
    public final ImageView ivSubscribe;
    public final ShapeView ivTag2;
    public final ShapeView ivTag3;
    public final ImageView ivYunGo;
    public final ImageView ivYunQuestion;
    public final ShapeLinearLayout llCompany;
    public final LinearLayout llFlb;
    public final LinearLayout llPoint;
    public final LinearLayout llPtb;
    public final LinearLayout llVip;
    public final LinearLayout llVoucher;

    @Bindable
    protected boolean mCompany;

    @Bindable
    protected UserBean mData;

    @Bindable
    protected String mMessageNum;

    @Bindable
    protected View.OnClickListener mOnClick;

    @Bindable
    protected int mPosition;

    @Bindable
    protected YunIndexBean mYun;
    public final NestedScrollView nsv;
    public final RelativeLayout reUser;
    public final RecyclerView rvGame;
    public final RecyclerView rvYunBlock;
    public final TextView tv1;
    public final TextView tv2;
    public final TextView tvDeviceName;
    public final ShapeTextView tvGame1;
    public final ShapeTextView tvGame2;
    public final ShapeTextView tvGame3;
    public final ShapeTextView tvGame4;
    public final TextView tvLogin;
    public final TextView tvLogin2;
    public final TextView tvMore;
    public final TextView tvNickname;
    public final ImageView tvSqk;
    public final ImageView tvYk;
    public final ShapeTextView tvYunAdd;
    public final TextView tvYunBuy;
    public final TextView tvYunDevice;
    public final TextView tvYunExit;
    public final TextView tvYunGame;
    public final TextView tvYunGames;
    public final TextView tvYunLesson;
    public final TextView tvYunRefresh;
    public final TextView tvYunRenew;
    public final ImageView userIcon;
    public final ImageView userIconDe;

    public abstract void setCompany(boolean company);

    public abstract void setData(UserBean data);

    public abstract void setMessageNum(String messageNum);

    public abstract void setOnClick(View.OnClickListener onClick);

    public abstract void setPosition(int position);

    public abstract void setYun(YunIndexBean yun);

    protected FragmentUserBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView btnDownload, TextView btnService, ImageView btnSetting, ConstraintLayout cl, RelativeLayout clMonth, RelativeLayout clSqk, ImageView ivMessage, ImageView ivSubscribe, ShapeView ivTag2, ShapeView ivTag3, ImageView ivYunGo, ImageView ivYunQuestion, ShapeLinearLayout llCompany, LinearLayout llFlb, LinearLayout llPoint, LinearLayout llPtb, LinearLayout llVip, LinearLayout llVoucher, NestedScrollView nsv, RelativeLayout reUser, RecyclerView rvGame, RecyclerView rvYunBlock, TextView tv1, TextView tv2, TextView tvDeviceName, ShapeTextView tvGame1, ShapeTextView tvGame2, ShapeTextView tvGame3, ShapeTextView tvGame4, TextView tvLogin, TextView tvLogin2, TextView tvMore, TextView tvNickname, ImageView tvSqk, ImageView tvYk, ShapeTextView tvYunAdd, TextView tvYunBuy, TextView tvYunDevice, TextView tvYunExit, TextView tvYunGame, TextView tvYunGames, TextView tvYunLesson, TextView tvYunRefresh, TextView tvYunRenew, ImageView userIcon, ImageView userIconDe) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnDownload = btnDownload;
        this.btnService = btnService;
        this.btnSetting = btnSetting;
        this.cl = cl;
        this.clMonth = clMonth;
        this.clSqk = clSqk;
        this.ivMessage = ivMessage;
        this.ivSubscribe = ivSubscribe;
        this.ivTag2 = ivTag2;
        this.ivTag3 = ivTag3;
        this.ivYunGo = ivYunGo;
        this.ivYunQuestion = ivYunQuestion;
        this.llCompany = llCompany;
        this.llFlb = llFlb;
        this.llPoint = llPoint;
        this.llPtb = llPtb;
        this.llVip = llVip;
        this.llVoucher = llVoucher;
        this.nsv = nsv;
        this.reUser = reUser;
        this.rvGame = rvGame;
        this.rvYunBlock = rvYunBlock;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tvDeviceName = tvDeviceName;
        this.tvGame1 = tvGame1;
        this.tvGame2 = tvGame2;
        this.tvGame3 = tvGame3;
        this.tvGame4 = tvGame4;
        this.tvLogin = tvLogin;
        this.tvLogin2 = tvLogin2;
        this.tvMore = tvMore;
        this.tvNickname = tvNickname;
        this.tvSqk = tvSqk;
        this.tvYk = tvYk;
        this.tvYunAdd = tvYunAdd;
        this.tvYunBuy = tvYunBuy;
        this.tvYunDevice = tvYunDevice;
        this.tvYunExit = tvYunExit;
        this.tvYunGame = tvYunGame;
        this.tvYunGames = tvYunGames;
        this.tvYunLesson = tvYunLesson;
        this.tvYunRefresh = tvYunRefresh;
        this.tvYunRenew = tvYunRenew;
        this.userIcon = userIcon;
        this.userIconDe = userIconDe;
    }

    public UserBean getData() {
        return this.mData;
    }

    public YunIndexBean getYun() {
        return this.mYun;
    }

    public View.OnClickListener getOnClick() {
        return this.mOnClick;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public String getMessageNum() {
        return this.mMessageNum;
    }

    public boolean getCompany() {
        return this.mCompany;
    }

    public static FragmentUserBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentUserBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentUserBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_user, root, attachToRoot, component);
    }

    public static FragmentUserBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentUserBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentUserBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_user, null, false, component);
    }

    public static FragmentUserBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentUserBinding bind(View view, Object component) {
        return (FragmentUserBinding) bind(component, view, R.layout.fragment_user);
    }
}
