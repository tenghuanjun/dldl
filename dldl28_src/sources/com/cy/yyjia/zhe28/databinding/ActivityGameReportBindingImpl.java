package com.cy.yyjia.zhe28.databinding;

import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.domain.GameReportBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityGameReportBindingImpl extends ActivityGameReportBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final EditText mboundView1;
    private InverseBindingListener mboundView1androidTextAttrChanged;
    private final TextView mboundView2;
    private final EditText mboundView3;
    private InverseBindingListener mboundView3androidTextAttrChanged;
    private final TextView mboundView4;
    private final EditText mboundView5;
    private InverseBindingListener mboundView5androidTextAttrChanged;

    public ActivityGameReportBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private ActivityGameReportBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ImageView) bindings[8], (ImageView) bindings[7], (RecyclerView) bindings[6]);
        this.mboundView1androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityGameReportBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityGameReportBindingImpl.this.mboundView1);
                GameReportBean gameReportBean = ActivityGameReportBindingImpl.this.mData;
                if (gameReportBean != null) {
                    gameReportBean.setGame(textString);
                }
            }
        };
        this.mboundView3androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityGameReportBindingImpl.2
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityGameReportBindingImpl.this.mboundView3);
                GameReportBean gameReportBean = ActivityGameReportBindingImpl.this.mData;
                if (gameReportBean != null) {
                    gameReportBean.setStr(textString);
                }
            }
        };
        this.mboundView5androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityGameReportBindingImpl.3
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityGameReportBindingImpl.this.mboundView5);
                GameReportBean gameReportBean = ActivityGameReportBindingImpl.this.mData;
                if (gameReportBean != null) {
                    gameReportBean.setReason(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.ivDelete.setTag(null);
        this.ivVideo.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        EditText editText = (EditText) bindings[1];
        this.mboundView1 = editText;
        editText.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        EditText editText2 = (EditText) bindings[3];
        this.mboundView3 = editText2;
        editText2.setTag(null);
        TextView textView2 = (TextView) bindings[4];
        this.mboundView4 = textView2;
        textView2.setTag(null);
        EditText editText3 = (EditText) bindings[5];
        this.mboundView5 = editText3;
        editText3.setTag(null);
        this.rvPic.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32L;
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
        if (23 != variableId) {
            return false;
        }
        setData((GameReportBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGameReportBinding
    public void setData(GameReportBean Data) {
        updateRegistration(0, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeData((GameReportBean) object, fieldId);
    }

    private boolean onChangeData(GameReportBean Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        if (fieldId == 35) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        if (fieldId == 104) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        if (fieldId == 84) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        if (fieldId != 122) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        String game;
        List<String> pic;
        String str2;
        String video;
        String str3;
        String str4;
        boolean z;
        boolean zIsEmpty;
        String reason;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        GameReportBean gameReportBean = this.mData;
        if ((63 & j) != 0) {
            game = ((j & 35) == 0 || gameReportBean == null) ? null : gameReportBean.getGame();
            pic = ((j & 33) == 0 || gameReportBean == null) ? null : gameReportBean.getPic();
            if ((j & 49) != 0) {
                video = gameReportBean != null ? gameReportBean.getVideo() : null;
                zIsEmpty = TextUtils.isEmpty(video);
            } else {
                video = null;
                zIsEmpty = false;
            }
            if ((j & 41) != 0) {
                reason = gameReportBean != null ? gameReportBean.getReason() : null;
                str3 = (reason != null ? reason.length() : 0) + "/100";
            } else {
                str3 = null;
                reason = null;
            }
            if ((j & 37) != 0) {
                str = gameReportBean != null ? gameReportBean.getStr() : null;
                str2 = (str != null ? str.length() : 0) + "/100";
            } else {
                str = null;
                str2 = null;
            }
            z = zIsEmpty;
            str4 = reason;
        } else {
            str = null;
            game = null;
            pic = null;
            str2 = null;
            video = null;
            str3 = null;
            str4 = null;
            z = false;
        }
        if ((j & 49) != 0) {
            DataBindingHelper.setViewGone(this.ivDelete, z);
            DataBindingHelper.setImg(this.ivVideo, video, null);
        }
        if ((j & 35) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, game);
        }
        if ((32 & j) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.mboundView1, null, null, null, this.mboundView1androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView3, null, null, null, this.mboundView3androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView5, null, null, null, this.mboundView5androidTextAttrChanged);
        }
        if ((37 & j) != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, str2);
            TextViewBindingAdapter.setText(this.mboundView3, str);
        }
        if ((41 & j) != 0) {
            TextViewBindingAdapter.setText(this.mboundView4, str3);
            TextViewBindingAdapter.setText(this.mboundView5, str4);
        }
        if ((j & 33) != 0) {
            DataBindingHelper.setRvData(this.rvPic, pic);
        }
    }
}
