package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityPtbDetailBinding;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Util;
import java.util.LinkedHashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: PtbDetailActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\n\u001a\u00020\u000bJ\b\u0010\f\u001a\u00020\u000bH\u0016R\u001d\u0010\u0004\u001a\u0004\u0018\u00010\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/PtbDetailActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityPtbDetailBinding;", "()V", "id", "", "getId", "()Ljava/lang/String;", "id$delegate", "Lkotlin/Lazy;", "getData", "", "init", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class PtbDetailActivity extends BaseActivity<ActivityPtbDetailBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: id$delegate, reason: from kotlin metadata */
    private final Lazy id;

    public PtbDetailActivity() {
        super(R.layout.activity_ptb_detail, 0, 2, null);
        this.id = LazyKt.lazy(new Function0<String>() { // from class: com.cy.yyjia.zhe28.ui.activity.PtbDetailActivity$id$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return this.this$0.getIntent().getStringExtra("id");
            }
        });
    }

    public final String getId() {
        return (String) this.id.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().navigation.setMoreClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.PtbDetailActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PtbDetailActivity.init$lambda$0(this.f$0, view);
            }
        });
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(PtbDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Util.toService(this$0.getMContext());
    }

    public final void getData() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String id = getId();
        Intrinsics.checkNotNull(id);
        linkedHashMap.put("orderId", id);
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new PtbDetailActivity$getData$$inlined$get$1("home/wallet/detail", linkedHashMap, null, this, this), 3, null);
    }
}
