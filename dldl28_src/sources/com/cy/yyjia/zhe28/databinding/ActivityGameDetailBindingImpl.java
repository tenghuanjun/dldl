package com.cy.yyjia.zhe28.databinding;

import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.viewpager.widget.ViewPager;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.AppInfo;
import com.cy.yyjia.zhe28.domain.GameDetailBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.util.Util;
import com.cy.yyjia.zhe28.view.AutoHeightViewPager;
import com.cy.yyjia.zhe28.view.Navigation;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;
import com.lzy.okgo.model.Progress;
import eightbitlab.com.blurview.BlurView;
import net.lucode.hackware.magicindicator.MagicIndicator;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityGameDetailBindingImpl extends ActivityGameDetailBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final TextView mboundView14;
    private final TextView mboundView15;
    private final TextView mboundView6;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.app_bar, 18);
        sparseIntArray.put(R.id.bv, 19);
        sparseIntArray.put(R.id.ll2, 20);
        sparseIntArray.put(R.id.vp2, 21);
        sparseIntArray.put(R.id.indicator, 22);
        sparseIntArray.put(R.id.toolbar, 23);
        sparseIntArray.put(R.id.tab, 24);
        sparseIntArray.put(R.id.vp, 25);
        sparseIntArray.put(R.id.tv_open, 26);
    }

    public ActivityGameDetailBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 27, sIncludes, sViewsWithIds));
    }

    private ActivityGameDetailBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (AppBarLayout) bindings[18], (ImageView) bindings[1], (ImageView) bindings[17], (BlurView) bindings[19], (ConstraintLayout) bindings[0], (MagicIndicator) bindings[22], (LinearLayout) bindings[8], (LinearLayout) bindings[20], (Navigation) bindings[7], (ProgressBar) bindings[13], (RelativeLayout) bindings[12], (TabLayout) bindings[24], (Toolbar) bindings[23], (ShapeTextView) bindings[2], (ShapeTextView) bindings[3], (ShapeTextView) bindings[4], (ShapeTextView) bindings[5], (ShapeLinearLayout) bindings[9], (ShapeTextView) bindings[26], (ShapeTextView) bindings[16], (ShapeLinearLayout) bindings[10], (ShapeLinearLayout) bindings[11], (ViewPager) bindings[25], (AutoHeightViewPager) bindings[21]);
        this.mDirtyFlags = -1L;
        this.bg.setTag(null);
        this.btnComment.setTag(null);
        this.cl.setTag(null);
        this.ll.setTag(null);
        TextView textView = (TextView) bindings[14];
        this.mboundView14 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[15];
        this.mboundView15 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[6];
        this.mboundView6 = textView3;
        textView3.setTag(null);
        this.navigation.setTag(null);
        this.pb.setTag(null);
        this.rlDownload.setTag(null);
        this.tv0.setTag(null);
        this.tv1.setTag(null);
        this.tv2.setTag(null);
        this.tv3.setTag(null);
        this.tvGm.setTag(null);
        this.tvOrder.setTag(null);
        this.tvPlay.setTag(null);
        this.tvYun.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 64L;
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
        if (33 == variableId) {
            setFold(((Boolean) variable).booleanValue());
        } else if (23 == variableId) {
            setData((GameDetailBean) variable);
        } else if (74 == variableId) {
            setPosition(((Integer) variable).intValue());
        } else {
            if (79 != variableId) {
                return false;
            }
            setProgress((Progress) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGameDetailBinding
    public void setFold(boolean Fold) {
        this.mFold = Fold;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(33);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGameDetailBinding
    public void setData(GameDetailBean Data) {
        updateRegistration(0, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGameDetailBinding
    public void setPosition(int Position) {
        this.mPosition = Position;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(74);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGameDetailBinding
    public void setProgress(Progress Progress) {
        this.mProgress = Progress;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(79);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId == 0) {
            return onChangeData((GameDetailBean) object, fieldId);
        }
        if (localFieldId != 1) {
            return false;
        }
        return onChangeDataAppInfo((AppInfo) object, fieldId);
    }

    private boolean onChangeData(GameDetailBean Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        if (fieldId != 48) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeDataAppInfo(AppInfo DataAppInfo, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        int colorFromResource;
        boolean zEquals;
        String str;
        boolean z;
        boolean z2;
        boolean zHideYun;
        boolean z3;
        boolean z4;
        boolean zHidePlay;
        String str2;
        boolean z5;
        boolean z6;
        String str3;
        int i;
        boolean z7;
        boolean z8;
        boolean z9;
        boolean z10;
        boolean z11;
        boolean z12;
        int iF2i;
        boolean z13;
        boolean z14;
        boolean z15;
        Progress progress;
        String str4;
        boolean z16;
        long j2;
        AppInfo appInfo;
        int i2;
        boolean z17;
        String background;
        boolean z18;
        String showCommentCount;
        boolean zIsEmpty;
        boolean z19;
        String str5;
        Integer numIsGm;
        int isTool;
        String server_img;
        int isDown;
        Navigation navigation;
        int i3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        boolean z20 = this.mFold;
        GameDetailBean gameDetailBean = this.mData;
        int i4 = this.mPosition;
        Progress progress2 = this.mProgress;
        long j3 = j & 68;
        if (j3 != 0) {
            if (j3 != 0) {
                j |= z20 ? 1024L : 512L;
            }
            if (z20) {
                navigation = this.navigation;
                i3 = R.color.colorPrimary;
            } else {
                navigation = this.navigation;
                i3 = R.color.colorWhite;
            }
            colorFromResource = getColorFromResource(navigation, i3);
        } else {
            colorFromResource = 0;
        }
        if ((99 & j) != 0) {
            long j4 = j & 65;
            if (j4 != 0) {
                if (gameDetailBean != null) {
                    numIsGm = gameDetailBean.getIsGm();
                    zHideYun = gameDetailBean.hideYun();
                    background = gameDetailBean.getBackground();
                    isTool = gameDetailBean.getIsTool();
                    showCommentCount = gameDetailBean.getShowCommentCount();
                    server_img = gameDetailBean.getServer_img();
                    isDown = gameDetailBean.getIsDown();
                    zHidePlay = gameDetailBean.hidePlay();
                } else {
                    numIsGm = null;
                    zHideYun = false;
                    background = null;
                    isTool = 0;
                    showCommentCount = null;
                    server_img = null;
                    isDown = 0;
                    zHidePlay = false;
                }
                if (j4 != 0) {
                    j = zHidePlay ? j | 256 : j | 128;
                }
                z3 = gameDetailBean == null;
                int iSafeUnbox = ViewDataBinding.safeUnbox(numIsGm);
                z18 = isTool == 0;
                zIsEmpty = TextUtils.isEmpty(server_img);
                z19 = isDown == 0;
                z2 = iSafeUnbox != 1;
            } else {
                z2 = false;
                zHideYun = false;
                z3 = false;
                background = null;
                z18 = false;
                showCommentCount = null;
                zIsEmpty = false;
                z19 = false;
                zHidePlay = false;
            }
            long j5 = j & 97;
            if (j5 != 0) {
                z4 = (gameDetailBean != null ? gameDetailBean.getIsreservation() : 0) == 1;
                if (j5 != 0) {
                    j |= z4 ? 16384L : IjkMediaMeta.AV_CH_TOP_FRONT_CENTER;
                }
                str5 = z4 ? "已预约" : "预约";
            } else {
                z4 = false;
                str5 = null;
            }
            long j6 = j & 67;
            if (j6 != 0) {
                zEquals = "gameweb".equals(gameDetailBean != null ? gameDetailBean.getType() : null);
                if (j6 != 0) {
                    j = zEquals ? j | 4096 : j | 2048;
                }
                str = background;
                z = z18;
                str2 = showCommentCount;
                z5 = zIsEmpty;
                z6 = z19;
                str3 = str5;
            } else {
                str = background;
                z = z18;
                str2 = showCommentCount;
                z5 = zIsEmpty;
                z6 = z19;
                str3 = str5;
                zEquals = false;
            }
        } else {
            zEquals = false;
            str = null;
            z = false;
            z2 = false;
            zHideYun = false;
            z3 = false;
            z4 = false;
            zHidePlay = false;
            str2 = null;
            z5 = false;
            z6 = false;
            str3 = null;
        }
        long j7 = j & 72;
        if (j7 != 0) {
            boolean z21 = i4 == 0;
            boolean z22 = i4 != 1;
            boolean z23 = i4 == 3;
            if (i4 == 2) {
                i2 = 1;
                z17 = true;
            } else {
                i2 = 1;
                z17 = false;
            }
            z11 = i4 == i2;
            z9 = z23;
            i = colorFromResource;
            z8 = z22;
            z7 = z4;
            z10 = z21;
            z12 = z17;
        } else {
            i = colorFromResource;
            z7 = z4;
            z8 = false;
            z9 = false;
            z10 = false;
            z11 = false;
            z12 = false;
        }
        long j8 = j & 80;
        if (j8 != 0) {
            iF2i = Util.f2i(progress2);
            z13 = progress2 != null;
            z14 = progress2 == null;
        } else {
            iF2i = 0;
            z13 = false;
            z14 = false;
        }
        boolean zCanShowOrder = ((j & 128) == 0 || gameDetailBean == null) ? false : gameDetailBean.canShowOrder();
        if ((j & 2048) != 0) {
            if (gameDetailBean != null) {
                appInfo = gameDetailBean.getAppInfo();
                progress = progress2;
            } else {
                progress = progress2;
                appInfo = null;
            }
            updateRegistration(1, appInfo);
            z15 = z9;
            str4 = ("下载（" + (appInfo != null ? appInfo.getSize() : null)) + "MB）";
        } else {
            z15 = z9;
            progress = progress2;
            str4 = null;
        }
        long j9 = j & 65;
        if (j9 != 0) {
            if (zHidePlay) {
                zCanShowOrder = true;
            }
            z16 = zCanShowOrder;
        } else {
            z16 = false;
        }
        long j10 = j & 67;
        if (j10 == 0) {
            str4 = null;
        } else if (zEquals) {
            str4 = "下载微端";
        }
        if (j9 != 0) {
            j2 = j;
            DataBindingHelper.setImg(this.bg, str, null);
            DataBindingHelper.setViewGone(this.ll, z3);
            TextViewBindingAdapter.setText(this.mboundView6, str2);
            DataBindingHelper.setViewGone(this.rlDownload, z6);
            DataBindingHelper.setViewGone(this.tv2, z);
            DataBindingHelper.setViewGone(this.tv3, z5);
            DataBindingHelper.setViewGone(this.tvGm, z2);
            DataBindingHelper.setViewGone(this.tvPlay, z16);
            DataBindingHelper.setViewGone(this.tvYun, zHideYun);
        } else {
            j2 = j;
        }
        if (j7 != 0) {
            DataBindingHelper.setViewGone(this.btnComment, z8);
            DataBindingHelper.setBold(this.tv0, z10);
            DataBindingHelper.setSelected(this.tv0, z10);
            DataBindingHelper.setBold(this.tv1, z11);
            DataBindingHelper.setSelected(this.tv1, z11);
            DataBindingHelper.setBold(this.tv2, z12);
            DataBindingHelper.setSelected(this.tv2, z12);
            boolean z24 = z15;
            DataBindingHelper.setBold(this.tv3, z24);
            DataBindingHelper.setSelected(this.tv3, z24);
        }
        if (j8 != 0) {
            DataBindingHelper.setDownloadText(this.mboundView14, progress);
            DataBindingHelper.setViewGone(this.mboundView14, z14);
            DataBindingHelper.setViewGone(this.mboundView15, z13);
            this.pb.setProgress(iF2i);
        }
        if (j10 != 0) {
            TextViewBindingAdapter.setText(this.mboundView15, str4);
        }
        if ((j2 & 68) != 0) {
            this.navigation.setIconTint(i);
        }
        if ((j2 & 97) != 0) {
            DataBindingHelper.setSelected(this.tvOrder, z7);
            TextViewBindingAdapter.setText(this.tvOrder, str3);
        }
    }
}
