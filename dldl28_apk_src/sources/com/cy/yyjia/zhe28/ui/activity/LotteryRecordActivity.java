package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityLotteryRecordBinding;
import com.cy.yyjia.zhe28.databinding.ItemLotteryRecordBinding;
import com.cy.yyjia.zhe28.domain.LotteryIdResult;
import com.cy.yyjia.zhe28.domain.LotteryRecordBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.util.Repository;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LotteryRecordActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u001dJ\b\u0010\u001f\u001a\u00020\u001dH\u0016J\u0010\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"H\u0016J\u000e\u0010#\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u0014R'\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001a\u0010\r\u001a\u00020\u000eX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018¨\u0006%"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/LotteryRecordActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityLotteryRecordBinding;", "Landroid/view/View$OnClickListener;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/LotteryRecordBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemLotteryRecordBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "id", "Lcom/cy/yyjia/zhe28/domain/LotteryIdResult;", "getId", "()Lcom/cy/yyjia/zhe28/domain/LotteryIdResult;", "setId", "(Lcom/cy/yyjia/zhe28/domain/LotteryIdResult;)V", "page", "", "getPage", "()I", "setPage", "(I)V", "typeId", "getTypeId", "setTypeId", "getBoxType", "", "getData", "init", "onClick", "v", "Landroid/view/View;", "setType", "i", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class LotteryRecordActivity extends BaseActivity<ActivityLotteryRecordBinding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;
    public LotteryIdResult id;
    private int page;
    private int typeId;

    public LotteryRecordActivity() {
        super(R.layout.activity_lottery_record, 2);
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<LotteryRecordBean, ItemLotteryRecordBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.LotteryRecordActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<LotteryRecordBean, ItemLotteryRecordBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_lottery_record, null, 2, null);
            }
        });
        this.page = 1;
    }

    public final BaseAdapter<LotteryRecordBean, ItemLotteryRecordBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    public final LotteryIdResult getId() {
        LotteryIdResult lotteryIdResult = this.id;
        if (lotteryIdResult != null) {
            return lotteryIdResult;
        }
        Intrinsics.throwUninitializedPropertyAccessException("id");
        return null;
    }

    public final void setId(LotteryIdResult lotteryIdResult) {
        Intrinsics.checkNotNullParameter(lotteryIdResult, "<set-?>");
        this.id = lotteryIdResult;
    }

    public final int getTypeId() {
        return this.typeId;
    }

    public final void setTypeId(int i) {
        this.typeId = i;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().rv.setAdapter(getAdapter());
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.LotteryRecordActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                LotteryRecordActivity.init$lambda$0(this.f$0);
            }
        });
        getBoxType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(LotteryRecordActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.tv1 /* 2131362645 */:
                setType(1);
                break;
            case R.id.tv2 /* 2131362646 */:
                setType(2);
                break;
        }
    }

    public final void getBoxType() {
        Repository.INSTANCE.getLotteryTypeId(new Function1<LotteryIdResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LotteryRecordActivity.getBoxType.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LotteryIdResult lotteryIdResult) {
                invoke2(lotteryIdResult);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LotteryIdResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                LotteryRecordActivity.this.setId(it);
                LotteryRecordActivity.this.setType(1);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LotteryRecordActivity.getBoxType.2
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
                LotteryRecordActivity.this.netFail(it);
            }
        });
    }

    public final void setType(int i) {
        this.page = 1;
        getAdapter().setNewInstance(null);
        this.typeId = i == 1 ? getId().getZhe() : getId().getCommon();
        getMBinding().setType(i);
        getData();
    }

    public final void getData() {
        Repository.INSTANCE.getLotteryRecord(this.typeId, new Function1<PageBean<LotteryRecordBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LotteryRecordActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<LotteryRecordBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<LotteryRecordBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (LotteryRecordActivity.this.getPage() == 1) {
                    LotteryRecordActivity.this.getAdapter().setNewInstance(it.getList());
                } else {
                    LotteryRecordActivity.this.getAdapter().addData(it.getList());
                }
                LotteryRecordActivity lotteryRecordActivity = LotteryRecordActivity.this;
                lotteryRecordActivity.setPage(lotteryRecordActivity.getPage() + 1);
                lotteryRecordActivity.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(LotteryRecordActivity.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    LotteryRecordActivity.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LotteryRecordActivity.getData.2
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
                LotteryRecordActivity.this.netFail(it);
                LotteryRecordActivity.this.getAdapter().getLoadMoreModule().loadMoreFail();
            }
        });
    }
}
