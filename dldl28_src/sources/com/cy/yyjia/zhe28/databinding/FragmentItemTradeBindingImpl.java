package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentItemTradeBindingImpl extends FragmentItemTradeBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final ImageView mboundView2;
    private final TextView mboundView3;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.ll_filter, 6);
        sparseIntArray.put(R.id.srl, 7);
        sparseIntArray.put(R.id.rv, 8);
    }

    public FragmentItemTradeBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private FragmentItemTradeBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (LinearLayout) bindings[6], (RecyclerView) bindings[8], (SmartRefreshLayout) bindings[7], (TextView) bindings[5], (TextView) bindings[4]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[2];
        this.mboundView2 = imageView2;
        imageView2.setTag(null);
        TextView textView = (TextView) bindings[3];
        this.mboundView3 = textView;
        textView.setTag(null);
        this.tvServer.setTag(null);
        this.tvService.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
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
        if (96 == variableId) {
            setServer((String) variable);
        } else {
            if (35 != variableId) {
                return false;
            }
            setGame((GameBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentItemTradeBinding
    public void setServer(String Server) {
        this.mServer = Server;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(96);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentItemTradeBinding
    public void setGame(GameBean Game) {
        updateRegistration(0, Game);
        this.mGame = Game;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(35);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeGame((GameBean) object, fieldId);
    }

    private boolean onChangeGame(GameBean Game, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        String name;
        String icon;
        boolean z2;
        boolean z3;
        String str;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str2 = this.mServer;
        GameBean gameBean = this.mGame;
        long j2 = j & 6;
        boolean z4 = false;
        if (j2 != 0) {
            z = str2 == null;
            if (j2 != 0) {
                j |= z ? 256L : 128L;
            }
        } else {
            z = false;
        }
        long j3 = j & 5;
        String str3 = null;
        if (j3 != 0) {
            if (gameBean != null) {
                name = gameBean.getName();
                icon = gameBean.getIcon();
            } else {
                name = null;
                icon = null;
            }
            z2 = gameBean != null;
            boolean z5 = name == null;
            z3 = icon == null;
            if (j3 != 0) {
                j |= z5 ? 16L : 8L;
            }
            if ((j & 5) != 0) {
                j |= z3 ? 64L : 32L;
            }
            z4 = z5;
        } else {
            name = null;
            icon = null;
            z2 = false;
            z3 = false;
        }
        long j4 = 5 & j;
        if (j4 != 0) {
            if (z4) {
                name = "全部游戏";
            }
            if (z3) {
                icon = "";
            }
            str = icon;
        } else {
            str = null;
            name = null;
        }
        long j5 = j & 6;
        if (j5 != 0) {
            if (z) {
                str2 = "全部区服";
            }
            str3 = str2;
        }
        if (j4 != 0) {
            DataBindingHelper.setGameIcon(this.mboundView1, str);
            DataBindingHelper.setViewGone(this.mboundView2, z2);
            TextViewBindingAdapter.setText(this.mboundView3, name);
            DataBindingHelper.setViewGone(this.tvService, z2);
        }
        if (j5 != 0) {
            TextViewBindingAdapter.setText(this.tvServer, str3);
        }
    }
}
