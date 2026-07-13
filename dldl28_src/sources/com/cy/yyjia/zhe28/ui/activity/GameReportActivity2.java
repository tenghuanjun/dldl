package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityGameReport2Binding;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.util.NetUtil;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GameReportActivity2.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/GameReportActivity2;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityGameReport2Binding;", "()V", "init", "", "submit", "v", "Landroid/view/View;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GameReportActivity2 extends BaseActivity<ActivityGameReport2Binding> {
    public static final int $stable = 0;

    public GameReportActivity2() {
        super(R.layout.activity_game_report2, 0, 2, null);
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().setGame("");
    }

    public final void submit(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        String game = getMBinding().getGame();
        Intrinsics.checkNotNull(game);
        if (game.length() == 0) {
            toast("请输入游戏名");
            return;
        }
        if (checkClick()) {
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String game2 = getMBinding().getGame();
        Intrinsics.checkNotNull(game2);
        linkedHashMap.put("name", game2);
        NetUtil.post2$default(NetUtil.INSTANCE, "game/apply", linkedHashMap, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameReportActivity2.submit.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Result result) {
                invoke2(result);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Result it) {
                Intrinsics.checkNotNullParameter(it, "it");
                GameReportActivity2.this.toast(it.getMsg());
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameReportActivity2.submit.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
                GameReportActivity2.this.netFail(it);
            }
        }, null, 16, null);
    }
}
