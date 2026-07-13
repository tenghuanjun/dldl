package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityRvBinding;
import com.cy.yyjia.zhe28.domain.YunTipBean;
import com.cy.yyjia.zhe28.ui.activity.YunTipsActivity;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.view.Navigation;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: YunTipsActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\b\u0010\u0006\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/YunTipsActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityRvBinding;", "()V", "getData", "", "init", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class YunTipsActivity extends BaseActivity<ActivityRvBinding> {
    public static final int $stable = 0;

    public YunTipsActivity() {
        super(R.layout.activity_rv, 0, 2, null);
    }

    public static final /* synthetic */ ActivityRvBinding access$getMBinding(YunTipsActivity yunTipsActivity) {
        return yunTipsActivity.getMBinding();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        Navigation navigation = getMBinding().navigation;
        navigation.setTitle("云游戏帮助");
        navigation.setMoreText("问题反馈");
        navigation.setMoreTextColor(getColor(R.color.colorPrimary));
        navigation.setMoreClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.YunTipsActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                YunTipsActivity.init$lambda$1$lambda$0(this.f$0, view);
            }
        });
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1$lambda$0(YunTipsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(FeedbackActivity.class);
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.YunTipsActivity$getData$1, reason: invalid class name */
    /* JADX INFO: compiled from: YunTipsActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "Lcom/cy/yyjia/zhe28/domain/YunTipBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class AnonymousClass1 extends Lambda implements Function1<List<YunTipBean>, Unit> {
        AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<YunTipBean> list) {
            invoke2(list);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(List<YunTipBean> it) {
            Intrinsics.checkNotNullParameter(it, "it");
            final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_yun_tip, it);
            YunTipsActivity.access$getMBinding(YunTipsActivity.this).rv.setAdapter(baseAdapter);
            baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.YunTipsActivity$getData$1$$ExternalSyntheticLambda0
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    YunTipsActivity.AnonymousClass1.invoke$lambda$0(baseAdapter, baseQuickAdapter, view, i);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final void invoke$lambda$0(BaseAdapter adapter, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(adapter, "$adapter");
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
            ((YunTipBean) adapter.getItem(i)).setSelected(!((YunTipBean) adapter.getItem(i)).getSelected());
        }
    }

    public final void getData() {
        Repository.INSTANCE.getYunTip(new AnonymousClass1(), new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunTipsActivity.getData.2
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
                YunTipsActivity.this.netFail(it);
            }
        });
    }
}
