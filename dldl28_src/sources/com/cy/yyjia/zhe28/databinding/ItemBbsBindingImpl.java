package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.domain.BbsBean;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemBbsBindingImpl extends ItemBbsBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private OnClickListenerImpl mDataFoldAndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mDataPraiseAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView10;
    private final TextView mboundView11;
    private final TextView mboundView12;
    private final LinearLayout mboundView13;
    private final ImageView mboundView14;
    private final CardView mboundView15;
    private final ImageView mboundView16;
    private final CardView mboundView17;
    private final ImageView mboundView18;
    private final ShapeTextView mboundView19;
    private final TextView mboundView2;
    private final TextView mboundView20;
    private final TextView mboundView21;
    private final TextView mboundView22;
    private final ImageView mboundView3;
    private final ImageView mboundView4;
    private final ImageView mboundView5;
    private final TextView mboundView6;
    private final LinearLayout mboundView8;
    private final ShapeTextView mboundView9;

    public ItemBbsBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 25, sIncludes, sViewsWithIds));
    }

    private ItemBbsBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (ImageView) bindings[7], (RecyclerView) bindings[24], (TextView) bindings[23]);
        this.mDirtyFlags = -1L;
        this.ivDelete.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[10];
        this.mboundView10 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[11];
        this.mboundView11 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[12];
        this.mboundView12 = textView3;
        textView3.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[13];
        this.mboundView13 = linearLayout2;
        linearLayout2.setTag(null);
        ImageView imageView2 = (ImageView) bindings[14];
        this.mboundView14 = imageView2;
        imageView2.setTag(null);
        CardView cardView = (CardView) bindings[15];
        this.mboundView15 = cardView;
        cardView.setTag(null);
        ImageView imageView3 = (ImageView) bindings[16];
        this.mboundView16 = imageView3;
        imageView3.setTag(null);
        CardView cardView2 = (CardView) bindings[17];
        this.mboundView17 = cardView2;
        cardView2.setTag(null);
        ImageView imageView4 = (ImageView) bindings[18];
        this.mboundView18 = imageView4;
        imageView4.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[19];
        this.mboundView19 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView4 = (TextView) bindings[2];
        this.mboundView2 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[20];
        this.mboundView20 = textView5;
        textView5.setTag(null);
        TextView textView6 = (TextView) bindings[21];
        this.mboundView21 = textView6;
        textView6.setTag(null);
        TextView textView7 = (TextView) bindings[22];
        this.mboundView22 = textView7;
        textView7.setTag(null);
        ImageView imageView5 = (ImageView) bindings[3];
        this.mboundView3 = imageView5;
        imageView5.setTag(null);
        ImageView imageView6 = (ImageView) bindings[4];
        this.mboundView4 = imageView6;
        imageView6.setTag(null);
        ImageView imageView7 = (ImageView) bindings[5];
        this.mboundView5 = imageView7;
        imageView7.setTag(null);
        TextView textView8 = (TextView) bindings[6];
        this.mboundView6 = textView8;
        textView8.setTag(null);
        LinearLayout linearLayout3 = (LinearLayout) bindings[8];
        this.mboundView8 = linearLayout3;
        linearLayout3.setTag(null);
        ShapeTextView shapeTextView2 = (ShapeTextView) bindings[9];
        this.mboundView9 = shapeTextView2;
        shapeTextView2.setTag(null);
        this.rv.setTag(null);
        this.tvPraise.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 256L;
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
            setData((BbsBean) variable);
        } else {
            if (12 != variableId) {
                return false;
            }
            setChild(((Boolean) variable).booleanValue());
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemBbsBinding
    public void setData(BbsBean Data) {
        updateRegistration(1, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemBbsBinding
    public void setChild(boolean Child) {
        this.mChild = Child;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(12);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId == 0) {
            return onChangeDataGame((GameBean) object, fieldId);
        }
        if (localFieldId != 1) {
            return false;
        }
        return onChangeData((BbsBean) object, fieldId);
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

    private boolean onChangeData(BbsBean Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        if (fieldId == 106) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        if (fieldId == 98) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        if (fieldId == 94) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        if (fieldId == 76) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        if (fieldId != 75) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:155:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0449  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0454  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0462  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0479  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0480  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x048e  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0497  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x04c1  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x04d2  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x04dd  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x04e4  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x05a0  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x05ab  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x05b8  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x05c5  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x05da  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x05e3  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x05f3  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x0600  */
    /* JADX WARN: Removed duplicated region for block: B:288:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 1547
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ItemBbsBindingImpl.executeBindings():void");
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private BbsBean value;

        public OnClickListenerImpl setValue(BbsBean value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.fold(arg0);
        }
    }

    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private BbsBean value;

        public OnClickListenerImpl1 setValue(BbsBean value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.praise(arg0);
        }
    }
}
