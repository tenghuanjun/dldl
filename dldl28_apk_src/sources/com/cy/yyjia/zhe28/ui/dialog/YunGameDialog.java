package com.cy.yyjia.zhe28.ui.dialog;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.databinding.DialogYunGameBinding;
import com.cy.yyjia.zhe28.databinding.ItemYunGameBinding;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.ui.activity.YunPlayActivity;
import com.cy.yyjia.zhe28.util.Repository;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: YunGameDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u0011R'\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015¨\u0006\u001d"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/YunGameDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogYunGameBinding;", "activity", "Landroidx/fragment/app/FragmentActivity;", "disable", "", "(Landroidx/fragment/app/FragmentActivity;Z)V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemYunGameBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "deviceId", "", "getDeviceId", "()I", "setDeviceId", "(I)V", "page", "getPage", "setPage", "getData", "", "toYun", "p", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class YunGameDialog extends BaseDataBindingDialog<DialogYunGameBinding, YunGameDialog> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;
    private int deviceId;
    private int page;

    public /* synthetic */ YunGameDialog(FragmentActivity fragmentActivity, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fragmentActivity, (i & 2) != 0 ? false : z);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public YunGameDialog(FragmentActivity activity, final boolean z) {
        super(activity, R.layout.dialog_yun_game);
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.page = 1;
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<GameBean, ItemYunGameBinding>>() { // from class: com.cy.yyjia.zhe28.ui.dialog.YunGameDialog$adapter$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<GameBean, ItemYunGameBinding> invoke() {
                final boolean z2 = z;
                return new BaseAdapter<>(R.layout.item_yun_game, new Function3<BaseDataBindingHolder<ItemYunGameBinding>, Integer, GameBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.YunGameDialog$adapter$2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemYunGameBinding> baseDataBindingHolder, Integer num, GameBean gameBean) {
                        invoke(baseDataBindingHolder, num.intValue(), gameBean);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(BaseDataBindingHolder<ItemYunGameBinding> h, int i, GameBean gameBean) {
                        Intrinsics.checkNotNullParameter(h, "h");
                        h.setGone(R.id.tv_go, z2);
                    }
                });
            }
        });
        this.page = 1;
        ((DialogYunGameBinding) this.mBinding).setGame("");
        ((DialogYunGameBinding) this.mBinding).rv.setAdapter(getAdapter());
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.YunGameDialog$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                YunGameDialog._init_$lambda$0(this.f$0);
            }
        });
        getAdapter().addChildClickViewIds(R.id.tv_go);
        getAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.YunGameDialog$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                YunGameDialog._init_$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        });
        ((DialogYunGameBinding) this.mBinding).tvSearch.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.YunGameDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                YunGameDialog._init_$lambda$2(this.f$0, view);
            }
        });
        ((DialogYunGameBinding) this.mBinding).et.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.YunGameDialog$$ExternalSyntheticLambda3
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return YunGameDialog._init_$lambda$3(this.f$0, textView, i, keyEvent);
            }
        });
        ((DialogYunGameBinding) this.mBinding).ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.YunGameDialog$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                YunGameDialog._init_$lambda$4(this.f$0, view);
            }
        });
        getData();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    public final BaseAdapter<GameBean, ItemYunGameBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    public final int getDeviceId() {
        return this.deviceId;
    }

    public final void setDeviceId(int i) {
        this.deviceId = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(YunGameDialog this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$1(final YunGameDialog this$0, BaseQuickAdapter baseQuickAdapter, View view, final int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        if (Intrinsics.areEqual(this$0.getAdapter().getItem(i).getStatus(), "1")) {
            return;
        }
        if (this$0.deviceId != 0) {
            Repository.INSTANCE.bindYunGame(this$0.deviceId, this$0.getAdapter().getItem(i).getId(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.YunGameDialog$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    this.this$0.toYun(i);
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.YunGameDialog$2$2
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
                    this.this$0.netFail(it);
                }
            });
        } else {
            this$0.toYun(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$2(YunGameDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.page = 1;
        this$0.getAdapter().setNewInstance(null);
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean _init_$lambda$3(YunGameDialog this$0, TextView textView, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i != 3) {
            return true;
        }
        ((DialogYunGameBinding) this$0.mBinding).tvSearch.performClick();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$4(YunGameDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public final void toYun(int p) {
        Intent intent = new Intent(getContext(), (Class<?>) YunPlayActivity.class);
        intent.putExtra("gid", getAdapter().getItem(p).getId());
        intent.putExtra("landscape", Intrinsics.areEqual(getAdapter().getItem(p).getScreen(), "horizontal"));
        intent.putExtra("myDeviceId", this.deviceId);
        getContext().startActivity(intent);
        dismiss();
    }

    public final void getData() {
        Repository repository = Repository.INSTANCE;
        int i = this.page;
        String game = ((DialogYunGameBinding) this.mBinding).getGame();
        Intrinsics.checkNotNull(game);
        repository.getYunGameList(i, game, new Function1<PageBean<GameBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.YunGameDialog.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<GameBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<GameBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (YunGameDialog.this.getPage() == 1) {
                    YunGameDialog.this.getAdapter().setNewInstance(it.getList());
                } else {
                    YunGameDialog.this.getAdapter().addData(it.getList());
                }
                YunGameDialog yunGameDialog = YunGameDialog.this;
                yunGameDialog.setPage(yunGameDialog.getPage() + 1);
                yunGameDialog.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(YunGameDialog.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    YunGameDialog.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.YunGameDialog.getData.2
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
                YunGameDialog.this.getAdapter().getLoadMoreModule().loadMoreFail();
                YunGameDialog.this.netFail(it);
            }
        });
    }
}
