package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.SanbaoBean;

/* JADX INFO: loaded from: classes2.dex */
public class ItemSanbaoGameBindingImpl extends ItemSanbaoGameBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final RelativeLayout mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(9);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(2, new String[]{"layout_game_name"}, new int[]{7}, new int[]{R.layout.layout_game_name});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.desc, 8);
    }

    public ItemSanbaoGameBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private ItemSanbaoGameBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (LinearLayout) bindings[8], (LayoutGameNameBinding) bindings[7], (LinearLayout) bindings[5], (TextView) bindings[6]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[2];
        this.mboundView2 = relativeLayout;
        relativeLayout.setTag(null);
        TextView textView = (TextView) bindings[3];
        this.mboundView3 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[4];
        this.mboundView4 = textView2;
        textView2.setTag(null);
        setContainedBinding(this.name);
        this.tag.setTag(null);
        this.tvBtn.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
        }
        this.name.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.name.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (9 == variableId) {
            setBtn((String) variable);
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((SanbaoBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemSanbaoGameBinding
    public void setBtn(String Btn) {
        this.mBtn = Btn;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(9);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemSanbaoGameBinding
    public void setData(SanbaoBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.name.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId == 0) {
            return onChangeDataGame((GameBean) object, fieldId);
        }
        if (localFieldId != 1) {
            return false;
        }
        return onChangeName((LayoutGameNameBinding) object, fieldId);
    }

    private boolean onChangeDataGame(GameBean DataGame, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeName(LayoutGameNameBinding Name, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x008f  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            r17 = this;
            r1 = r17
            monitor-enter(r17)
            long r2 = r1.mDirtyFlags     // Catch: java.lang.Throwable -> Lab
            r4 = 0
            r1.mDirtyFlags = r4     // Catch: java.lang.Throwable -> Lab
            monitor-exit(r17)     // Catch: java.lang.Throwable -> Lab
            java.lang.String r0 = r1.mBtn
            com.cy.yyjia.zhe28.domain.SanbaoBean r6 = r1.mData
            r7 = 20
            long r9 = r2 & r7
            r11 = 0
            int r12 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r12 == 0) goto L3d
            if (r0 == 0) goto L20
            java.lang.String r9 = "已领取"
            boolean r9 = r0.equals(r9)
            goto L21
        L20:
            r9 = 0
        L21:
            if (r12 == 0) goto L2b
            if (r9 == 0) goto L28
            r12 = 64
            goto L2a
        L28:
            r12 = 32
        L2a:
            long r2 = r2 | r12
        L2b:
            if (r9 == 0) goto L33
            android.widget.TextView r9 = r1.tvBtn
            r10 = 2131099705(0x7f060039, float:1.781177E38)
            goto L38
        L33:
            android.widget.TextView r9 = r1.tvBtn
            r10 = 2131099700(0x7f060034, float:1.781176E38)
        L38:
            int r9 = getColorFromResource(r9, r10)
            goto L3e
        L3d:
            r9 = 0
        L3e:
            r12 = 25
            long r12 = r12 & r2
            r10 = 0
            int r14 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r14 == 0) goto L66
            if (r6 == 0) goto L4d
            com.cy.yyjia.zhe28.domain.GameBean r6 = r6.getGame()
            goto L4e
        L4d:
            r6 = r10
        L4e:
            r1.updateRegistration(r11, r6)
            if (r6 == 0) goto L64
            java.util.List r11 = r6.getTags()
            android.text.SpannableString r12 = r6.getDescNew()
            java.lang.String r13 = r6.getIcon()
            java.lang.String r15 = r6.getScore()
            goto L6b
        L64:
            r11 = r10
            goto L68
        L66:
            r6 = r10
            r11 = r6
        L68:
            r12 = r11
            r13 = r12
            r15 = r13
        L6b:
            if (r14 == 0) goto L8a
            android.widget.ImageView r14 = r1.mboundView1
            r16 = r10
            android.graphics.drawable.Drawable r16 = (android.graphics.drawable.Drawable) r16
            com.cy.yyjia.zhe28.util.DataBindingHelper.setImg(r14, r13, r10)
            android.widget.TextView r10 = r1.mboundView3
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r10, r15)
            android.widget.TextView r10 = r1.mboundView4
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r10, r12)
            com.cy.yyjia.zhe28.databinding.LayoutGameNameBinding r10 = r1.name
            r10.setData(r6)
            android.widget.LinearLayout r6 = r1.tag
            com.cy.yyjia.zhe28.util.DataBindingHelper.setTags(r6, r11)
        L8a:
            long r2 = r2 & r7
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 == 0) goto La5
            android.widget.TextView r2 = r1.tvBtn
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r2, r0)
            int r0 = getBuildSdkInt()
            r2 = 21
            if (r0 < r2) goto La5
            android.widget.TextView r0 = r1.tvBtn
            android.content.res.ColorStateList r2 = androidx.databinding.adapters.Converters.convertColorToColorStateList(r9)
            r0.setBackgroundTintList(r2)
        La5:
            com.cy.yyjia.zhe28.databinding.LayoutGameNameBinding r0 = r1.name
            executeBindingsOn(r0)
            return
        Lab:
            r0 = move-exception
            monitor-exit(r17)     // Catch: java.lang.Throwable -> Lab
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ItemSanbaoGameBindingImpl.executeBindings():void");
    }
}
