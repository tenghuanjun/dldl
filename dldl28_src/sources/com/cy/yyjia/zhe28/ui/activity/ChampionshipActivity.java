package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityChampionshipBinding;
import com.cy.yyjia.zhe28.databinding.ItemChampionshipRewardBinding;
import com.cy.yyjia.zhe28.databinding.ItemChampionshipTaskBinding;
import com.cy.yyjia.zhe28.domain.ChampionshipBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Repository;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ChampionshipActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018J\b\u0010\u0019\u001a\u00020\u0015H\u0016J\u0006\u0010\u001a\u001a\u00020\u0015R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u001b\u0010\u000e\u001a\u00020\u000f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001b"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/ChampionshipActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityChampionshipBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/ChampionshipBean$Prize;", "Lcom/cy/yyjia/zhe28/databinding/ItemChampionshipRewardBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter2", "Lcom/cy/yyjia/zhe28/domain/ChampionshipBean$Task;", "Lcom/cy/yyjia/zhe28/databinding/ItemChampionshipTaskBinding;", "getAdapter2", "id", "", "getId", "()I", "id$delegate", "Lkotlin/Lazy;", "getData", "", "getTimeDiff", "timestamp", "", "init", "submission", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ChampionshipActivity extends BaseActivity<ActivityChampionshipBinding> {
    public static final int $stable = 8;
    private final BaseAdapter<ChampionshipBean.Prize, ItemChampionshipRewardBinding> adapter;
    private final BaseAdapter<ChampionshipBean.Task, ItemChampionshipTaskBinding> adapter2;

    /* JADX INFO: renamed from: id$delegate, reason: from kotlin metadata */
    private final Lazy id;

    public static final /* synthetic */ ActivityChampionshipBinding access$getMBinding(ChampionshipActivity championshipActivity) {
        return championshipActivity.getMBinding();
    }

    public ChampionshipActivity() {
        super(R.layout.activity_championship, 0, 2, null);
        this.id = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.ChampionshipActivity$id$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("id", 0));
            }
        });
        this.adapter = new BaseAdapter<>(R.layout.item_championship_reward, null, 2, null);
        this.adapter2 = new BaseAdapter<>(R.layout.item_championship_task, null, 2, null);
    }

    public final int getId() {
        return ((Number) this.id.getValue()).intValue();
    }

    public final BaseAdapter<ChampionshipBean.Prize, ItemChampionshipRewardBinding> getAdapter() {
        return this.adapter;
    }

    public final BaseAdapter<ChampionshipBean.Task, ItemChampionshipTaskBinding> getAdapter2() {
        return this.adapter2;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().rv.setAdapter(this.adapter);
        getMBinding().rvTask.setAdapter(this.adapter2);
        getMBinding().btn.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ChampionshipActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChampionshipActivity.init$lambda$0(this.f$0, view);
            }
        });
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(ChampionshipActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.submission();
    }

    public final void getTimeDiff(long timestamp) {
        log("timestamp:" + timestamp);
        long jCurrentTimeMillis = System.currentTimeMillis() - timestamp;
        if (jCurrentTimeMillis > 0) {
            return;
        }
        long jAbs = Math.abs(jCurrentTimeMillis) / ((long) 60000);
        long j = 60;
        long j2 = jAbs / j;
        long j3 = 24;
        long j4 = j2 / j3;
        long j5 = j2 % j3;
        long j6 = jAbs % j;
        if (j4 > 0) {
            getMBinding().tv1.setText(String.valueOf(j4));
        }
        if (j5 > 0) {
            getMBinding().tv2.setText(String.valueOf(j5));
        }
        if (j6 > 0 || (j4 == 0 && j5 == 0)) {
            getMBinding().tv3.setText(String.valueOf(j6));
        }
    }

    public final void getData() {
        Repository.INSTANCE.getBbsActivity(getId(), new Function1<ChampionshipBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ChampionshipActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ChampionshipBean championshipBean) {
                invoke2(championshipBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ChampionshipBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                ChampionshipActivity.access$getMBinding(ChampionshipActivity.this).setData(it);
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(it.getActivity_info().getDraw_time());
                ChampionshipActivity championshipActivity = ChampionshipActivity.this;
                Intrinsics.checkNotNull(date);
                championshipActivity.getTimeDiff(date.getTime());
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ChampionshipActivity.getData.2
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
                ChampionshipActivity.this.netFail(it);
            }
        });
    }

    public final void submission() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(ConstantsAPI.WXWebPage.KEY_ACTIVITY_ID, String.valueOf(getId()));
        NetUtil.post2$default(NetUtil.INSTANCE, "community_activity/draw", linkedHashMap, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ChampionshipActivity.submission.1
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
                ChampionshipActivity.this.toast(it.getMsg());
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ChampionshipActivity.submission.2
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
                ChampionshipActivity.this.netFail(it);
            }
        }, null, 16, null);
    }
}
