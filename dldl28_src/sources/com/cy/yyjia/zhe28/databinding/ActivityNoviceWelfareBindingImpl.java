package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BtnBean;
import com.cy.yyjia.zhe28.domain.GameToolBean;
import com.cy.yyjia.zhe28.domain.NoviceDataBean;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityNoviceWelfareBindingImpl extends ActivityNoviceWelfareBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private OnClickListenerImpl mDataGiftLiveLinkOnClickAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final TextView mboundView2;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.bg, 6);
        sparseIntArray.put(R.id.iv_go, 7);
        sparseIntArray.put(R.id.tv_game, 8);
        sparseIntArray.put(R.id.navigation, 9);
    }

    public ActivityNoviceWelfareBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private ActivityNoviceWelfareBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ImageView) bindings[6], (ImageView) bindings[7], (Navigation) bindings[9], (TextView) bindings[8], (TextView) bindings[1], (TextView) bindings[4], (TextView) bindings[3], (TextView) bindings[5]);
        this.mDirtyFlags = -1L;
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        this.tvGo.setTag(null);
        this.tvRecord.setTag(null);
        this.tvRule.setTag(null);
        this.tvShow.setTag(null);
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
        if (23 != variableId) {
            return false;
        }
        setData((NoviceDataBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityNoviceWelfareBinding
    public void setData(NoviceDataBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeDataGiftLive((GameToolBean) object, fieldId);
    }

    private boolean onChangeDataGiftLive(GameToolBean DataGiftLive, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            r19 = this;
            r1 = r19
            monitor-enter(r19)
            long r2 = r1.mDirtyFlags     // Catch: java.lang.Throwable -> Laf
            r4 = 0
            r1.mDirtyFlags = r4     // Catch: java.lang.Throwable -> Laf
            monitor-exit(r19)     // Catch: java.lang.Throwable -> Laf
            com.cy.yyjia.zhe28.domain.NoviceDataBean r0 = r1.mData
            r6 = 7
            long r8 = r2 & r6
            r10 = 6
            r12 = 8
            r14 = 0
            r15 = 0
            int r16 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r16 == 0) goto L66
            if (r0 == 0) goto L21
            com.cy.yyjia.zhe28.domain.GameToolBean r8 = r0.getGift_live()
            goto L22
        L21:
            r8 = r15
        L22:
            r1.updateRegistration(r14, r8)
            if (r8 == 0) goto L30
            java.lang.String r9 = r8.getTitle()
            com.cy.yyjia.zhe28.domain.BtnBean r8 = r8.getLink()
            goto L32
        L30:
            r8 = r15
            r9 = r8
        L32:
            if (r8 == 0) goto L44
            com.cy.yyjia.zhe28.databinding.ActivityNoviceWelfareBindingImpl$OnClickListenerImpl r14 = r1.mDataGiftLiveLinkOnClickAndroidViewViewOnClickListener
            if (r14 != 0) goto L3f
            com.cy.yyjia.zhe28.databinding.ActivityNoviceWelfareBindingImpl$OnClickListenerImpl r14 = new com.cy.yyjia.zhe28.databinding.ActivityNoviceWelfareBindingImpl$OnClickListenerImpl
            r14.<init>()
            r1.mDataGiftLiveLinkOnClickAndroidViewViewOnClickListener = r14
        L3f:
            com.cy.yyjia.zhe28.databinding.ActivityNoviceWelfareBindingImpl$OnClickListenerImpl r8 = r14.setValue(r8)
            goto L45
        L44:
            r8 = r15
        L45:
            long r17 = r2 & r10
            int r14 = (r17 > r4 ? 1 : (r17 == r4 ? 0 : -1))
            if (r14 == 0) goto L64
            if (r0 == 0) goto L52
            java.lang.String r0 = r0.getReceiveRule()
            goto L53
        L52:
            r0 = r15
        L53:
            boolean r16 = android.text.TextUtils.isEmpty(r0)
            if (r14 == 0) goto L61
            if (r16 == 0) goto L60
            r17 = 16
            long r2 = r2 | r17
            goto L61
        L60:
            long r2 = r2 | r12
        L61:
            r14 = r16
            goto L6a
        L64:
            r0 = r15
            goto L69
        L66:
            r0 = r15
            r8 = r0
            r9 = r8
        L69:
            r14 = 0
        L6a:
            long r12 = r12 & r2
            int r16 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r16 == 0) goto L74
            android.text.Spanned r0 = android.text.Html.fromHtml(r0)
            goto L75
        L74:
            r0 = r15
        L75:
            long r10 = r10 & r2
            int r12 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r12 == 0) goto L7f
            if (r14 == 0) goto L7e
            java.lang.String r0 = ""
        L7e:
            r15 = r0
        L7f:
            if (r12 == 0) goto L88
            android.widget.TextView r0 = r1.mboundView2
            java.lang.CharSequence r15 = (java.lang.CharSequence) r15
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r15)
        L88:
            long r6 = r6 & r2
            int r0 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r0 == 0) goto L97
            android.widget.TextView r0 = r1.tvGo
            r0.setOnClickListener(r8)
            android.widget.TextView r0 = r1.tvGo
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r9)
        L97:
            r6 = 4
            long r2 = r2 & r6
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto Lae
            android.widget.TextView r0 = r1.tvRecord
            r2 = 1
            com.cy.yyjia.zhe28.util.DataBindingHelper.setFitWindow(r0, r2)
            android.widget.TextView r0 = r1.tvRule
            com.cy.yyjia.zhe28.util.DataBindingHelper.setFitWindow(r0, r2)
            android.widget.TextView r0 = r1.tvShow
            com.cy.yyjia.zhe28.util.DataBindingHelper.setFitWindow(r0, r2)
        Lae:
            return
        Laf:
            r0 = move-exception
            monitor-exit(r19)     // Catch: java.lang.Throwable -> Laf
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ActivityNoviceWelfareBindingImpl.executeBindings():void");
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private BtnBean value;

        public OnClickListenerImpl setValue(BtnBean value) {
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
