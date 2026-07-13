package com.cy.yyjia.zhe28.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ItemPicSelectBinding;
import com.cy.yyjia.zhe28.util.Util;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.hjq.permissions.OnPermissionCallback;
import com.mobile.auth.gatewayauth.Constant;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: PicSelectAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ \u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J \u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013R\u0014\u0010\t\u001a\u00020\u0007X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/adapter/PicSelectAdapter;", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "", "Lcom/cy/yyjia/zhe28/databinding/ItemPicSelectBinding;", "activity", "Landroid/app/Activity;", "max", "", "(Landroid/app/Activity;I)V", "REQUEST_CODE", "getREQUEST_CODE", "()I", "getActivity", "()Landroid/app/Activity;", "getMax", "getPic", "", Constant.LOGIN_ACTIVITY_REQUEST_CODE, "intentData", "Landroid/content/Intent;", "getPicAuto", "", "resultCode", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class PicSelectAdapter extends BaseAdapter<String, ItemPicSelectBinding> {
    public static final int $stable = 8;
    private final int REQUEST_CODE;
    private final Activity activity;
    private final int max;

    public /* synthetic */ PicSelectAdapter(Activity activity, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(activity, (i2 & 2) != 0 ? 9 : i);
    }

    public final Activity getActivity() {
        return this.activity;
    }

    public final int getMax() {
        return this.max;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PicSelectAdapter(Activity activity, int i) {
        super(R.layout.item_pic_select, null, 2, null);
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
        this.max = i;
        this.REQUEST_CODE = 11;
        if (getData().isEmpty()) {
            getData().add("");
        }
        addChildClickViewIds(R.id.iv_delete);
        setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.adapter.PicSelectAdapter$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
                PicSelectAdapter._init_$lambda$0(this.f$0, baseQuickAdapter, view, i2);
            }
        });
        setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.adapter.PicSelectAdapter$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
                PicSelectAdapter._init_$lambda$2(this.f$0, baseQuickAdapter, view, i2);
            }
        });
    }

    public final int getREQUEST_CODE() {
        return this.REQUEST_CODE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(PicSelectAdapter this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        if (i == this$0.max - 1) {
            this$0.addData("");
        }
        this$0.removeAt(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$2(final PicSelectAdapter this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        Util.checkReadPermission(this$0.activity, new OnPermissionCallback() { // from class: com.cy.yyjia.zhe28.ui.adapter.PicSelectAdapter$$ExternalSyntheticLambda0
            @Override // com.hjq.permissions.OnPermissionCallback
            public /* synthetic */ void onDenied(List list, boolean z) {
                OnPermissionCallback.CC.$default$onDenied(this, list, z);
            }

            @Override // com.hjq.permissions.OnPermissionCallback
            public final void onGranted(List list, boolean z) {
                PicSelectAdapter.lambda$2$lambda$1(this.f$0, list, z);
            }
        });
    }

    static final void lambda$2$lambda$1(PicSelectAdapter this$0, List list, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(list, "<anonymous parameter 0>");
        if (z) {
            ImageSelector.ImageSelectorBuilder maxSelectCount = ImageSelector.builder().useCamera(false).setSingle(false).setMaxSelectCount(this$0.max);
            List<String> data = this$0.getData();
            Intrinsics.checkNotNull(data, "null cannot be cast to non-null type java.util.ArrayList<kotlin.String?>{ kotlin.collections.TypeAliasesKt.ArrayList<kotlin.String?> }");
            maxSelectCount.setSelected((ArrayList) data).start(this$0.activity, this$0.REQUEST_CODE);
        }
    }

    public final void getPicAuto(int requestCode, int resultCode, Intent intentData) {
        if (requestCode != this.REQUEST_CODE || intentData == null) {
            return;
        }
        ArrayList<String> stringArrayListExtra = intentData.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
        if (stringArrayListExtra == null) {
            stringArrayListExtra = new ArrayList<>();
        }
        if (stringArrayListExtra.size() < this.max) {
            stringArrayListExtra.add("");
        }
        setNewInstance(TypeIntrinsics.asMutableList(stringArrayListExtra));
    }

    public final List<String> getPic(int requestCode, Intent intentData) {
        if (requestCode == this.REQUEST_CODE && intentData != null) {
            ArrayList<String> stringArrayListExtra = intentData.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
            Intrinsics.checkNotNull(stringArrayListExtra);
            if (stringArrayListExtra.size() < this.max) {
                stringArrayListExtra.add("");
            }
            return stringArrayListExtra;
        }
        List<String> data = getData();
        Intrinsics.checkNotNull(data, "null cannot be cast to non-null type kotlin.collections.MutableList<kotlin.String?>");
        return TypeIntrinsics.asMutableList(data);
    }
}
