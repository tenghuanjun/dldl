package com.cy.yyjia.zhe28.ui.fragment;

import android.content.Intent;
import android.view.View;
import androidx.lifecycle.ViewModelProvider;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.databinding.FragmentGameServerBinding;
import com.cy.yyjia.zhe28.domain.GameDetailBean;
import com.cy.yyjia.zhe28.domain.GameViewModel;
import com.cy.yyjia.zhe28.ui.activity.ImageActivity;
import com.donkingliang.imageselector.utils.ImageSelector;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GameServerFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/GameServerFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentGameServerBinding;", "()V", "vm", "Lcom/cy/yyjia/zhe28/domain/GameViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/GameViewModel;", "vm$delegate", "Lkotlin/Lazy;", "init", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GameServerFragment extends BaseFragment<FragmentGameServerBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;

    public GameServerFragment() {
        super(R.layout.fragment_game_server);
        this.vm = LazyKt.lazy(new Function0<GameViewModel>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameServerFragment$vm$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final GameViewModel invoke() {
                return (GameViewModel) new ViewModelProvider(this.this$0.getMContext()).get(GameViewModel.class);
            }
        });
    }

    public static final /* synthetic */ FragmentGameServerBinding access$getMBinding(GameServerFragment gameServerFragment) {
        return gameServerFragment.getMBinding();
    }

    public final GameViewModel getVm() {
        return (GameViewModel) this.vm.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        getVm().getData().observe(this, new GameServerFragment$sam$androidx_lifecycle_Observer$0(new Function1<GameDetailBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameServerFragment.init.1
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
                GameServerFragment.access$getMBinding(GameServerFragment.this).setData(gameDetailBean.getServer_img());
            }
        }));
        getMBinding().iv.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameServerFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameServerFragment.init$lambda$0(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(GameServerFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ArrayList<String> arrayList = new ArrayList<>();
        String data = this$0.getMBinding().getData();
        Intrinsics.checkNotNull(data);
        arrayList.add(data);
        Intent intent = new Intent(this$0.getMContext(), (Class<?>) ImageActivity.class);
        intent.putStringArrayListExtra("images", arrayList);
        intent.putExtra(ImageSelector.POSITION, 0);
        this$0.startActivity(intent);
    }
}
