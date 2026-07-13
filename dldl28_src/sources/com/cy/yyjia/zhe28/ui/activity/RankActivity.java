package com.cy.yyjia.zhe28.ui.activity;

import androidx.lifecycle.ViewModelProvider;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityEmptyBinding;
import com.cy.yyjia.zhe28.domain.MainTabBean;
import com.cy.yyjia.zhe28.domain.MainViewModel;
import com.cy.yyjia.zhe28.ui.fragment.HomeGameFragment;
import com.cy.yyjia.zhe28.util.Repository;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RankActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\n\u001a\u00020\u000bJ\b\u0010\f\u001a\u00020\u000bH\u0016R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/RankActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityEmptyBinding;", "()V", "vm", "Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "vm$delegate", "Lkotlin/Lazy;", "getTab", "", "init", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class RankActivity extends BaseActivity<ActivityEmptyBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;

    public RankActivity() {
        super(R.layout.activity_empty, 0, 2, null);
        this.vm = LazyKt.lazy(new Function0<MainViewModel>() { // from class: com.cy.yyjia.zhe28.ui.activity.RankActivity$vm$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MainViewModel invoke() {
                return (MainViewModel) new ViewModelProvider(this.this$0).get(MainViewModel.class);
            }
        });
    }

    public final MainViewModel getVm() {
        return (MainViewModel) this.vm.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().ll.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        getMBinding().navigation.setTitle("排行榜");
        getTab();
    }

    public final void getTab() {
        Repository.INSTANCE.getMainTab(new Function1<List<MainTabBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.RankActivity.getTab.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<MainTabBean> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<MainTabBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.get(0).setSelected(true);
                RankActivity.this.getVm().getHomeNav().setValue(it);
                int size = it.size();
                int i = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    if (it.get(i2).getNav_template() == 4) {
                        i = i2;
                    }
                }
                RankActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.body, HomeGameFragment.INSTANCE.newInstance(i)).commit();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.RankActivity.getTab.2
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
                RankActivity.this.netFail(it);
            }
        });
    }
}
