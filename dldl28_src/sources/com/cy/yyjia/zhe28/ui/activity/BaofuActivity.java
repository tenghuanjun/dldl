package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityBaofuBinding;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Util;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: BaofuActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0006B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/BaofuActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityBaofuBinding;", "()V", "init", "", "DataBean", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class BaofuActivity extends BaseActivity<ActivityBaofuBinding> {
    public static final int $stable = 0;

    public BaofuActivity() {
        super(R.layout.activity_baofu, 2);
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new BaofuActivity$init$$inlined$get$1("topic/clothing", MapsKt.emptyMap(), null, this, this), 3, null);
        getMBinding().tvService.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.BaofuActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaofuActivity.init$lambda$2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(BaofuActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Util.toService(this$0);
    }

    /* JADX INFO: compiled from: BaofuActivity.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/BaofuActivity$DataBean;", "", "gameList", "", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "more", "", "(Ljava/util/List;Ljava/lang/String;)V", "getGameList", "()Ljava/util/List;", "getMore", "()Ljava/lang/String;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class DataBean {
        public static final int $stable = 8;
        private final List<GameBean> gameList;
        private final String more;

        public DataBean(List<GameBean> gameList, String more) {
            Intrinsics.checkNotNullParameter(gameList, "gameList");
            Intrinsics.checkNotNullParameter(more, "more");
            this.gameList = gameList;
            this.more = more;
        }

        public final List<GameBean> getGameList() {
            return this.gameList;
        }

        public final String getMore() {
            return this.more;
        }
    }
}
