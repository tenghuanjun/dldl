package com.cy.yyjia.zhe28.ui.fragment;

import android.view.View;
import androidx.lifecycle.ViewModelProvider;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.databinding.FragmentRvBinding;
import com.cy.yyjia.zhe28.databinding.ItemGameToolBinding;
import com.cy.yyjia.zhe28.domain.GameDetailBean;
import com.cy.yyjia.zhe28.domain.GameToolBean;
import com.cy.yyjia.zhe28.domain.GameViewModel;
import com.cy.yyjia.zhe28.util.Repository;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GameToolFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0011\u001a\u00020\u0012H\u0016R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/GameToolFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentRvBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/GameToolBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemGameToolBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "vm", "Lcom/cy/yyjia/zhe28/domain/GameViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/GameViewModel;", "vm$delegate", "init", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GameToolFragment extends BaseFragment<FragmentRvBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;

    public GameToolFragment() {
        super(R.layout.fragment_rv);
        this.vm = LazyKt.lazy(new Function0<GameViewModel>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameToolFragment$vm$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final GameViewModel invoke() {
                return (GameViewModel) new ViewModelProvider(this.this$0.getMContext()).get(GameViewModel.class);
            }
        });
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<GameToolBean, ItemGameToolBinding>>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameToolFragment$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<GameToolBean, ItemGameToolBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_game_tool, null, 2, null);
            }
        });
    }

    public final GameViewModel getVm() {
        return (GameViewModel) this.vm.getValue();
    }

    public final BaseAdapter<GameToolBean, ItemGameToolBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        getMBinding().srl.setEnabled(false);
        getMBinding().rv.setAdapter(getAdapter());
        getAdapter().setEmptyView(R.layout.fragment_game_tool);
        getAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameToolFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                GameToolFragment.init$lambda$0(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getVm().getData().observe(this, new GameToolFragment$sam$androidx_lifecycle_Observer$0(new Function1<GameDetailBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameToolFragment.init.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GameDetailBean gameDetailBean) {
                invoke2(gameDetailBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GameDetailBean gameDetailBean) {
                Repository repository = Repository.INSTANCE;
                int id = gameDetailBean.getId();
                final GameToolFragment gameToolFragment = GameToolFragment.this;
                Function1<List<GameToolBean>, Unit> function1 = new Function1<List<GameToolBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameToolFragment.init.2.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(List<GameToolBean> list) {
                        invoke2(list);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(List<GameToolBean> it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        gameToolFragment.getAdapter().setNewInstance(it);
                    }
                };
                final GameToolFragment gameToolFragment2 = GameToolFragment.this;
                repository.getGameTool(id, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameToolFragment.init.2.2
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
                        gameToolFragment2.netFail(it);
                    }
                });
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(GameToolFragment this$0, BaseQuickAdapter baseQuickAdapter, View v, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(v, "v");
        this$0.getAdapter().getItem(i).setSelected(!this$0.getAdapter().getItem(i).getSelected());
        GSYVideoManager.releaseAllVideos();
    }
}
