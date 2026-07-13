package com.cy.yyjia.zhe28.ui.fragment;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.domain.ScheduleTimeBean;
import com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: HomeNewFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/cy/yyjia/zhe28/domain/ScheduleTimeBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
final class HomeNewFragment$ChildFragment$init$4 extends Lambda implements Function1<ScheduleTimeBean, Unit> {
    final /* synthetic */ HomeNewFragment.ChildFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HomeNewFragment$ChildFragment$init$4(HomeNewFragment.ChildFragment childFragment) {
        super(1);
        this.this$0 = childFragment;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(ScheduleTimeBean scheduleTimeBean) {
        invoke2(scheduleTimeBean);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(ScheduleTimeBean it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (this.this$0.getType() == 0) {
            it.getDay().get(6).setSelected(true);
            this.this$0.setTime(it.getDay().get(6).getDate());
        } else {
            it.getDay().get(0).setSelected(true);
            this.this$0.setTime(it.getDay().get(0).getDate());
        }
        final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_schedule_time, it.getDay());
        this.this$0.getMBinding().rvTime.setAdapter(baseAdapter);
        final HomeNewFragment.ChildFragment childFragment = this.this$0;
        baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$ChildFragment$init$4$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                HomeNewFragment$ChildFragment$init$4.invoke$lambda$0(baseAdapter, childFragment, baseQuickAdapter, view, i);
            }
        });
        this.this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void invoke$lambda$0(BaseAdapter timeAdapter, HomeNewFragment.ChildFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(timeAdapter, "$timeAdapter");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        for (T t : timeAdapter.getData()) {
            t.setSelected(Intrinsics.areEqual(t.getDay(), ((ScheduleTimeBean.Day) timeAdapter.getItem(i)).getDay()));
        }
        this$0.setTime(((ScheduleTimeBean.Day) timeAdapter.getItem(i)).getDate());
        this$0.getNewGame();
    }
}
