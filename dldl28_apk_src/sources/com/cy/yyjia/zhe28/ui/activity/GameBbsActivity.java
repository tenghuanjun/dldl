package com.cy.yyjia.zhe28.ui.activity;

import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityFragmentBinding;
import com.cy.yyjia.zhe28.ui.fragment.BbsFragment2;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: GameBbsActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/GameBbsActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityFragmentBinding;", "()V", "bbsId", "", "getBbsId", "()I", "bbsId$delegate", "Lkotlin/Lazy;", "init", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GameBbsActivity extends BaseActivity<ActivityFragmentBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: bbsId$delegate, reason: from kotlin metadata */
    private final Lazy bbsId;

    public GameBbsActivity() {
        super(R.layout.activity_fragment, 2);
        this.bbsId = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameBbsActivity$bbsId$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("bbsId", 1));
            }
        });
    }

    public final int getBbsId() {
        return ((Number) this.bbsId.getValue()).intValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getSupportFragmentManager().beginTransaction().replace(R.id.body, new BbsFragment2(getBbsId())).commit();
    }
}
