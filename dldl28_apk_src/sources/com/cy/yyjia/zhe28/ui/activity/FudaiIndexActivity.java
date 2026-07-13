package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import androidx.lifecycle.LifecycleOwnerKt;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityFudaiIndexBinding;
import com.cy.yyjia.zhe28.databinding.ItemFudaiIndexBinding;
import com.cy.yyjia.zhe28.domain.FudaiIndexBean;
import com.cy.yyjia.zhe28.domain.FudaiRecordBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: FudaiIndexActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013J\b\u0010\u0015\u001a\u00020\u0013H\u0016J\b\u0010\u0016\u001a\u00020\u0013H\u0014J\b\u0010\u0017\u001a\u00020\u0013H\u0014R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0018"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/FudaiIndexActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityFudaiIndexBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/FudaiIndexBean$List;", "Lcom/cy/yyjia/zhe28/databinding/ItemFudaiIndexBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "checkJob", "Lkotlinx/coroutines/Job;", "getCheckJob", "()Lkotlinx/coroutines/Job;", "setCheckJob", "(Lkotlinx/coroutines/Job;)V", "getData", "", "go", "init", "onPause", "onResume", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class FudaiIndexActivity extends BaseActivity<ActivityFudaiIndexBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;
    private Job checkJob;

    public static final /* synthetic */ ActivityFudaiIndexBinding access$getMBinding(FudaiIndexActivity fudaiIndexActivity) {
        return fudaiIndexActivity.getMBinding();
    }

    public FudaiIndexActivity() {
        super(R.layout.activity_fudai_index, 1);
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<FudaiIndexBean.List, ItemFudaiIndexBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.FudaiIndexActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<FudaiIndexBean.List, ItemFudaiIndexBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_fudai_index, null, 2, null);
            }
        });
    }

    public final BaseAdapter<FudaiIndexBean.List, ItemFudaiIndexBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().navigation.setMoreClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.FudaiIndexActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FudaiIndexActivity.init$lambda$0(this.f$0, view);
            }
        });
        getMBinding().rv.setAdapter(getAdapter());
        BaseAdapter.setMyEmptyView$default(getAdapter(), null, 1, null);
        getMBinding().llRecord.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.FudaiIndexActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FudaiIndexActivity.init$lambda$1(this.f$0, view);
            }
        });
        getMBinding().ivAdd.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.FudaiIndexActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FudaiIndexActivity.init$lambda$2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(FudaiIndexActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Util.openWeb(this$0, "规则", "https://www.28zhe.com/dist/game-notice-detail?id=139913&gid=1", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(FudaiIndexActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(FudaiRecordActivity.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(FudaiIndexActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.go();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        getData();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        Job job = this.checkJob;
        if (job == null || job.isCancelled() || job.isCompleted()) {
            return;
        }
        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        log("cancel job");
    }

    public final Job getCheckJob() {
        return this.checkJob;
    }

    public final void setCheckJob(Job job) {
        this.checkJob = job;
    }

    public final void getData() {
        Repository.INSTANCE.getFudaiIndex(new Function1<FudaiIndexBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.FudaiIndexActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(FudaiIndexBean fudaiIndexBean) {
                invoke2(fudaiIndexBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(FudaiIndexBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                FudaiIndexActivity.access$getMBinding(FudaiIndexActivity.this).setData(it);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm分ss秒");
                Calendar calendar = Calendar.getInstance();
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (calendar.get(12) >= 36) {
                    FudaiIndexActivity.access$getMBinding(FudaiIndexActivity.this).tvTime.setText("");
                    if (it.isAdd() == 1) {
                        FudaiIndexActivity.access$getMBinding(FudaiIndexActivity.this).tvCountdown.setText("已开奖");
                    }
                } else {
                    calendar.set(12, 36);
                }
                calendar.set(13, 5);
                Ref.LongRef longRef = new Ref.LongRef();
                longRef.element = calendar.getTimeInMillis() - jCurrentTimeMillis;
                if (longRef.element > 0) {
                    FudaiIndexActivity fudaiIndexActivity = FudaiIndexActivity.this;
                    fudaiIndexActivity.setCheckJob(BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(fudaiIndexActivity), null, null, new C01791(longRef, FudaiIndexActivity.this, simpleDateFormat, it, null), 3, null));
                } else {
                    Job checkJob = FudaiIndexActivity.this.getCheckJob();
                    if (checkJob != null) {
                        Job.DefaultImpls.cancel$default(checkJob, (CancellationException) null, 1, (Object) null);
                    }
                    FudaiIndexActivity.this.setCheckJob(null);
                }
            }

            /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.FudaiIndexActivity$getData$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: FudaiIndexActivity.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
            @DebugMetadata(c = "com.cy.yyjia.zhe28.ui.activity.FudaiIndexActivity$getData$1$1", f = "FudaiIndexActivity.kt", i = {}, l = {75}, m = "invokeSuspend", n = {}, s = {})
            static final class C01791 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ FudaiIndexBean $it;
                final /* synthetic */ SimpleDateFormat $sdf;
                final /* synthetic */ Ref.LongRef $time;
                int label;
                final /* synthetic */ FudaiIndexActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C01791(Ref.LongRef longRef, FudaiIndexActivity fudaiIndexActivity, SimpleDateFormat simpleDateFormat, FudaiIndexBean fudaiIndexBean, Continuation<? super C01791> continuation) {
                    super(2, continuation);
                    this.$time = longRef;
                    this.this$0 = fudaiIndexActivity;
                    this.$sdf = simpleDateFormat;
                    this.$it = fudaiIndexBean;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C01791(this.$time, this.this$0, this.$sdf, this.$it, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C01791) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Removed duplicated region for block: B:11:0x0025  */
                /* JADX WARN: Removed duplicated region for block: B:19:0x00c6  */
                /* JADX WARN: Removed duplicated region for block: B:20:0x00cd  */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x00bb -> B:17:0x00be). Please report as a decompilation issue!!! */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r10) {
                    /*
                        Method dump skipped, instruction units count: 208
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.ui.activity.FudaiIndexActivity.AnonymousClass1.C01791.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.FudaiIndexActivity.getData.2
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
                FudaiIndexActivity.this.netFail(it);
            }
        });
        Repository.INSTANCE.getFudaiRecord(new Function1<FudaiRecordBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.FudaiIndexActivity.getData.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(FudaiRecordBean fudaiRecordBean) {
                invoke2(fudaiRecordBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(FudaiRecordBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                FudaiIndexActivity.access$getMBinding(FudaiIndexActivity.this).f441tv.setText("未中奖");
                if (it.getList().size() == 0 || Intrinsics.areEqual(it.getList().get(0).getMoney(), "0")) {
                    return;
                }
                FudaiIndexActivity.access$getMBinding(FudaiIndexActivity.this).f441tv.setText("+" + it.getList().get(0).getMoney() + "福利币");
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.FudaiIndexActivity.getData.4
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
                FudaiIndexActivity.this.netFail(it);
            }
        });
    }

    public final void go() {
        Repository.INSTANCE.joinFudai(new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.FudaiIndexActivity.go.1
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
                FudaiIndexActivity.this.toast(it.getMsg());
                FudaiIndexActivity.this.getData();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.FudaiIndexActivity.go.2
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
                FudaiIndexActivity.this.netFail(it);
            }
        });
    }
}
