package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityRecycleBinding;
import com.cy.yyjia.zhe28.ui.adapter.RecycleAdapter;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecycleConfirmActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eR\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/RecycleConfirmActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityRecycleBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/ui/adapter/RecycleAdapter;", "getAdapter", "()Lcom/cy/yyjia/zhe28/ui/adapter/RecycleAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "init", "", "onClick", "v", "Landroid/view/View;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class RecycleConfirmActivity extends BaseActivity<ActivityRecycleBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    public RecycleConfirmActivity() {
        super(R.layout.activity_recycle, 0, 2, null);
        this.adapter = LazyKt.lazy(new Function0<RecycleAdapter>() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleConfirmActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RecycleAdapter invoke() {
                return new RecycleAdapter(1);
            }
        });
    }

    public final RecycleAdapter getAdapter() {
        return (RecycleAdapter) this.adapter.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().rv.setAdapter(getAdapter());
    }

    public final void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (v.getId() == R.id.iv_record) {
            startActivity(RecycleRecordActivity.class);
        }
    }
}
