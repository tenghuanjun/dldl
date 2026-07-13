package com.cy.yyjia.zhe28.ui.fragment;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.databinding.FragmentHallBinding;
import com.cy.yyjia.zhe28.domain.MainViewModel;
import com.cy.yyjia.zhe28.ui.activity.SearchActivity;
import com.donkingliang.imageselector.utils.ImageSelector;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HallFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0012\u0010\u0010\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u000e\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0015R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/HallFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentHallBinding;", "Landroid/view/View$OnClickListener;", "()V", "vm", "Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "vm$delegate", "Lkotlin/Lazy;", "init", "", "onClick", "v", "Landroid/view/View;", "onViewStateRestored", "savedInstanceState", "Landroid/os/Bundle;", "select", ImageSelector.POSITION, "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class HallFragment extends BaseFragment<FragmentHallBinding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;

    public HallFragment() {
        super(R.layout.fragment_hall);
        this.vm = LazyKt.lazy(new Function0<MainViewModel>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HallFragment$vm$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MainViewModel invoke() {
                return (MainViewModel) new ViewModelProvider(this.this$0.getMContext()).get(MainViewModel.class);
            }
        });
    }

    public final MainViewModel getVm() {
        return (MainViewModel) this.vm.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        getMBinding().setPosition(0);
        getMBinding().setOnClick(this);
        getMBinding().vp.setAdapter(new FragmentStateAdapter(this) { // from class: com.cy.yyjia.zhe28.ui.fragment.HallFragment.init.1
            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return 4;
            }

            {
                super(this);
            }

            @Override // androidx.viewpager2.adapter.FragmentStateAdapter
            public Fragment createFragment(int position) {
                if (position == 1) {
                    return NewGameFragment.INSTANCE.newInstance(0);
                }
                if (position == 2) {
                    return new HomeScheduleFragment();
                }
                if (position == 3) {
                    return NewGameFragment.INSTANCE.newInstance(1);
                }
                return new HallGameFragment();
            }
        });
        getMBinding().vp.setUserInputEnabled(false);
        HallFragment hallFragment = this;
        getVm().getHomeFun().observe(hallFragment, new HallFragment$sam$androidx_lifecycle_Observer$0(new Function1<String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HallFragment.init.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String str) {
                if (Intrinsics.areEqual(str, "新游首发")) {
                    HallFragment.this.select(0);
                }
            }
        }));
        getVm().getCategory().observe(hallFragment, new HallFragment$sam$androidx_lifecycle_Observer$0(new Function1<Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HallFragment.init.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke2(num);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Integer num) {
                HallFragment.this.select(0);
            }
        }));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        if (id == R.id.btn1) {
            select(0);
            return;
        }
        if (id != R.id.tv_search) {
            switch (id) {
                case R.id.btn2 /* 2131361942 */:
                    select(1);
                    break;
                case R.id.btn3 /* 2131361943 */:
                    select(2);
                    break;
                case R.id.btn4 /* 2131361944 */:
                    select(3);
                    break;
            }
            return;
        }
        startActivity(SearchActivity.class);
    }

    public final void select(int position) {
        getMBinding().setPosition(position);
        getMBinding().vp.setCurrentItem(position, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        getMBinding().vp.setCurrentItem(0, false);
    }
}
