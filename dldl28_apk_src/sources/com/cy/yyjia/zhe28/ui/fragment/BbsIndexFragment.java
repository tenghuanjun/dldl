package com.cy.yyjia.zhe28.ui.fragment;

import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.databinding.FragmentBbsIndexBinding;
import com.cy.yyjia.zhe28.domain.BbsBlockBean;
import com.cy.yyjia.zhe28.ui.activity.BbsMessageIndexActivity;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Repository;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: BbsIndexFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0007\u001a\u00020\u0005H\u0016¨\u0006\b"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/BbsIndexFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentBbsIndexBinding;", "()V", "getData", "", "init", "onResume", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class BbsIndexFragment extends BaseFragment<FragmentBbsIndexBinding> {
    public static final int $stable = 0;

    public BbsIndexFragment() {
        super(R.layout.fragment_bbs_index);
    }

    public static final /* synthetic */ FragmentBbsIndexBinding access$getMBinding(BbsIndexFragment bbsIndexFragment) {
        return bbsIndexFragment.getMBinding();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        getMBinding().setOnClick(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsIndexFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BbsIndexFragment.init$lambda$0(this.f$0, view);
            }
        });
        getMBinding().vp.setAdapter(new FragmentStateAdapter(this) { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsIndexFragment.init.2
            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return 2;
            }

            {
                super(this);
            }

            @Override // androidx.viewpager2.adapter.FragmentStateAdapter
            public Fragment createFragment(int position) {
                if (position == 0) {
                    return new BbsFragment();
                }
                return new BbsRecommendFragment();
            }
        });
        getMBinding().vp.setUserInputEnabled(false);
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(final BbsIndexFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (view.getId() == R.id.iv_message) {
            this$0.doWithLogin(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsIndexFragment$init$1$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.startActivity(BbsMessageIndexActivity.class);
                }
            });
            return;
        }
        int i = Integer.parseInt(view.getTag().toString());
        this$0.getMBinding().setPosition(i);
        this$0.getMBinding().vp.setCurrentItem(i, false);
    }

    public final void getData() {
        Repository.INSTANCE.getBbsIndex(new Function1<List<BbsBlockBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsIndexFragment.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<BbsBlockBean> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<BbsBlockBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                BbsIndexFragment.access$getMBinding(BbsIndexFragment.this).setData(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsIndexFragment.getData.2
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
                BbsIndexFragment.this.netFail(it);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (Constant.INSTANCE.getLogged()) {
            NetUtil netUtil = NetUtil.INSTANCE;
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new BbsIndexFragment$onResume$$inlined$get$1("bbs/bbsMessage", MapsKt.emptyMap(), null, this, this), 3, null);
        }
    }
}
