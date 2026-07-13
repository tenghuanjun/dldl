package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.domain.WelfareBean3;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentWelfare4BindingImpl extends FragmentWelfare4Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mOnClickOnClickAndroidViewViewOnClickListener;
    private final FrameLayout mboundView0;
    private final TextView mboundView3;
    private final ImageView mboundView5;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.rv1, 8);
    }

    public FragmentWelfare4BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private FragmentWelfare4BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ConstraintLayout) bindings[1], (ImageView) bindings[2], (ProgressBar) bindings[7], (RecyclerView) bindings[8], (TextView) bindings[6], (TextView) bindings[4]);
        this.mDirtyFlags = -1L;
        this.clUser.setTag(null);
        this.ivUser.setTag(null);
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        TextView textView = (TextView) bindings[3];
        this.mboundView3 = textView;
        textView.setTag(null);
        ImageView imageView = (ImageView) bindings[5];
        this.mboundView5 = imageView;
        imageView.setTag(null);
        this.pb.setTag(null);
        this.tvExp.setTag(null);
        this.tvName.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
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
        if (23 == variableId) {
            setData((WelfareBean3) variable);
        } else if (119 == variableId) {
            setUser((UserBean) variable);
        } else {
            if (68 != variableId) {
                return false;
            }
            setOnClick((View.OnClickListener) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentWelfare4Binding
    public void setData(WelfareBean3 Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentWelfare4Binding
    public void setUser(UserBean User) {
        this.mUser = User;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(119);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentWelfare4Binding
    public void setOnClick(View.OnClickListener OnClick) {
        this.mOnClick = OnClick;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(68);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String pic;
        String text;
        int progress;
        int max;
        String nickName;
        String user_avatar;
        boolean z;
        OnClickListenerImpl value;
        int uid;
        WelfareBean3.Vip vip;
        WelfareBean3.User user;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        WelfareBean3 welfareBean3 = this.mData;
        UserBean userBean = this.mUser;
        View.OnClickListener onClickListener = this.mOnClick;
        long j2 = 9 & j;
        if (j2 != 0) {
            if (welfareBean3 != null) {
                user = welfareBean3.getUser();
                vip = welfareBean3.getVip();
            } else {
                vip = null;
                user = null;
            }
            if (user != null) {
                progress = user.getProgress();
                max = user.getMax();
                text = user.getText();
            } else {
                text = null;
                progress = 0;
                max = 0;
            }
            WelfareBean3.Vip.Detail detail = vip != null ? vip.getDetail() : null;
            pic = detail != null ? detail.getPic() : null;
        } else {
            pic = null;
            text = null;
            progress = 0;
            max = 0;
        }
        long j3 = 10 & j;
        if (j3 != 0) {
            if (userBean != null) {
                nickName = userBean.getNickName();
                user_avatar = userBean.getUser_avatar();
                uid = userBean.getUid();
            } else {
                nickName = null;
                user_avatar = null;
                uid = 0;
            }
            boolean z2 = uid != 0;
            z = uid == 0;
            z = z2;
        } else {
            nickName = null;
            user_avatar = null;
            z = false;
        }
        long j4 = j & 12;
        if (j4 == 0 || onClickListener == null) {
            value = null;
        } else {
            OnClickListenerImpl onClickListenerImpl = this.mOnClickOnClickAndroidViewViewOnClickListener;
            if (onClickListenerImpl == null) {
                onClickListenerImpl = new OnClickListenerImpl();
                this.mOnClickOnClickAndroidViewViewOnClickListener = onClickListenerImpl;
            }
            value = onClickListenerImpl.setValue(onClickListener);
        }
        if (j4 != 0) {
            this.clUser.setOnClickListener(value);
        }
        if (j3 != 0) {
            DataBindingHelper.setUserIcon(this.ivUser, user_avatar);
            DataBindingHelper.setViewGone(this.mboundView3, z);
            DataBindingHelper.setViewGone(this.mboundView5, z);
            DataBindingHelper.setViewGone(this.pb, z);
            DataBindingHelper.setViewGone(this.tvExp, z);
            DataBindingHelper.setViewGone(this.tvName, z);
            TextViewBindingAdapter.setText(this.tvName, nickName);
        }
        if (j2 != 0) {
            DataBindingHelper.setImg(this.mboundView5, pic, null);
            this.pb.setMax(max);
            this.pb.setProgress(progress);
            TextViewBindingAdapter.setText(this.tvExp, text);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private View.OnClickListener value;

        public OnClickListenerImpl setValue(View.OnClickListener value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.onClick(arg0);
        }
    }
}
