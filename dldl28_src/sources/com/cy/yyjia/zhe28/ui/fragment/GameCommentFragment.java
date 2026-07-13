package com.cy.yyjia.zhe28.ui.fragment;

import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;
import com.bytedance.framwork.core.sdklib.MonitorCommonConstants;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.base.BasePopupWindow;
import com.cy.yyjia.zhe28.databinding.FragmentGameCommentBinding;
import com.cy.yyjia.zhe28.domain.CommentBean;
import com.cy.yyjia.zhe28.domain.CommentConfig;
import com.cy.yyjia.zhe28.domain.GameDetailBean;
import com.cy.yyjia.zhe28.domain.GameScoreBean;
import com.cy.yyjia.zhe28.domain.GameViewModel;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.ui.adapter.GameCommentAdapter2;
import com.cy.yyjia.zhe28.ui.fragment.GameCommentFragment;
import com.cy.yyjia.zhe28.util.Repository;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: GameCommentFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u001e\u001a\u00020\u001fJ\b\u0010 \u001a\u00020\u001fH\u0016R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001b\u0010\u0019\u001a\u00020\u001a8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\t\u001a\u0004\b\u001b\u0010\u001c¨\u0006!"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/GameCommentFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentGameCommentBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/ui/adapter/GameCommentAdapter2;", "getAdapter", "()Lcom/cy/yyjia/zhe28/ui/adapter/GameCommentAdapter2;", "adapter$delegate", "Lkotlin/Lazy;", "category", "", "getCategory", "()I", "setCategory", "(I)V", "page", "getPage", "setPage", "sort", "", "getSort", "()Ljava/lang/String;", "setSort", "(Ljava/lang/String;)V", "vm", "Lcom/cy/yyjia/zhe28/domain/GameViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/GameViewModel;", "vm$delegate", "getData", "", "init", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GameCommentFragment extends BaseFragment<FragmentGameCommentBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;
    private int category;
    private int page;
    private String sort;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;

    public static final /* synthetic */ FragmentGameCommentBinding access$getMBinding(GameCommentFragment gameCommentFragment) {
        return gameCommentFragment.getMBinding();
    }

    public GameCommentFragment() {
        super(R.layout.fragment_game_comment);
        this.vm = LazyKt.lazy(new Function0<GameViewModel>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameCommentFragment$vm$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final GameViewModel invoke() {
                return (GameViewModel) new ViewModelProvider(this.this$0.getMContext()).get(GameViewModel.class);
            }
        });
        this.adapter = LazyKt.lazy(new Function0<GameCommentAdapter2>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameCommentFragment$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final GameCommentAdapter2 invoke() {
                return new GameCommentAdapter2(null, 1, null);
            }
        });
        this.page = 1;
        this.sort = MonitorCommonConstants.DEFAULT_AID;
    }

    public final GameViewModel getVm() {
        return (GameViewModel) this.vm.getValue();
    }

    public final GameCommentAdapter2 getAdapter() {
        return (GameCommentAdapter2) this.adapter.getValue();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    public final String getSort() {
        return this.sort;
    }

    public final void setSort(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.sort = str;
    }

    public final int getCategory() {
        return this.category;
    }

    public final void setCategory(int i) {
        this.category = i;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        GameCommentFragment gameCommentFragment = this;
        getVm().getData().observe(gameCommentFragment, new GameCommentFragment$sam$androidx_lifecycle_Observer$0(new Function1<GameDetailBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameCommentFragment.init.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GameDetailBean gameDetailBean) {
                invoke2(gameDetailBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.fragment.GameCommentFragment$init$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: GameCommentFragment.kt */
            @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/cy/yyjia/zhe28/domain/PageBean;", "Lcom/cy/yyjia/zhe28/domain/CommentConfig$Tag;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
            static final class C02361 extends Lambda implements Function1<PageBean<CommentConfig.Tag>, Unit> {
                final /* synthetic */ GameCommentFragment this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C02361(GameCommentFragment gameCommentFragment) {
                    super(1);
                    this.this$0 = gameCommentFragment;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PageBean<CommentConfig.Tag> pageBean) {
                    invoke2(pageBean);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PageBean<CommentConfig.Tag> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    if (it.getList().size() > 0) {
                        it.getList().get(0).setSelected(true);
                    }
                    final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_comment_category, it.getList());
                    GameCommentFragment.access$getMBinding(this.this$0).rvCategory.setAdapter(baseAdapter);
                    final GameCommentFragment gameCommentFragment = this.this$0;
                    baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameCommentFragment$init$1$1$$ExternalSyntheticLambda0
                        @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                        public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                            GameCommentFragment.C11391.C02361.invoke$lambda$0(baseAdapter, gameCommentFragment, baseQuickAdapter, view, i);
                        }
                    });
                }

                /* JADX INFO: Access modifiers changed from: private */
                /* JADX WARN: Multi-variable type inference failed */
                public static final void invoke$lambda$0(BaseAdapter cateAdapter, GameCommentFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    Intrinsics.checkNotNullParameter(cateAdapter, "$cateAdapter");
                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                    Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
                    Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
                    Iterator it = cateAdapter.getData().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            CommentConfig.Tag tag = (CommentConfig.Tag) it.next();
                            tag.setSelected(tag.getId() == ((CommentConfig.Tag) cateAdapter.getItem(i)).getId());
                            if (tag.getSelected()) {
                                this$0.setCategory(tag.getId());
                            }
                        } else {
                            cateAdapter.notifyDataSetChanged();
                            this$0.setPage(1);
                            this$0.getAdapter().setNewInstance(null);
                            this$0.getData();
                            return;
                        }
                    }
                }
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GameDetailBean gameDetailBean) {
                GameCommentFragment.access$getMBinding(GameCommentFragment.this).tvCommentNum.setText("全部帖子（" + gameDetailBean.getCommentCount() + "）");
                Repository repository = Repository.INSTANCE;
                int id = gameDetailBean.getId();
                C02361 c02361 = new C02361(GameCommentFragment.this);
                final GameCommentFragment gameCommentFragment2 = GameCommentFragment.this;
                repository.getCommentCategory(id, c02361, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameCommentFragment.init.1.2
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
                        gameCommentFragment2.log(it.toString());
                    }
                });
                GameCommentFragment.this.getData();
            }
        }));
        getVm().getScore().observe(gameCommentFragment, new GameCommentFragment$sam$androidx_lifecycle_Observer$0(new Function1<GameScoreBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameCommentFragment.init.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GameScoreBean gameScoreBean) {
                invoke2(gameScoreBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GameScoreBean gameScoreBean) {
                GameCommentFragment.access$getMBinding(GameCommentFragment.this).setData(gameScoreBean);
            }
        }));
        getMBinding().rv.setAdapter(getAdapter());
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameCommentFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                GameCommentFragment.init$lambda$0(this.f$0);
            }
        });
        getAdapter().setEmptyView(R.layout.layout_empty);
        getMBinding().tvSort.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameCommentFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameCommentFragment.init$lambda$2(this.f$0, view);
            }
        });
        getMBinding().btnTop.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameCommentFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameCommentFragment.init$lambda$3(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(GameCommentFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(final GameCommentFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BasePopupWindow.OnClickListener onClickListener = new BasePopupWindow.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameCommentFragment$$ExternalSyntheticLambda3
            @Override // com.cy.yyjia.zhe28.base.BasePopupWindow.OnClickListener
            public final void onClick(BasePopupWindow basePopupWindow, View view2) {
                GameCommentFragment.init$lambda$2$lambda$1(this.f$0, basePopupWindow, (TextView) view2);
            }
        };
        new BasePopupWindow.Builder(this$0.getMContext()).setContentView(R.layout.popwindow_comment_rank).setOnClickListener(R.id.f438tv, onClickListener).setOnClickListener(R.id.tv_time, onClickListener).setOnClickListener(R.id.tv_number, onClickListener).setWidth(view.getWidth()).showAsDropDown(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2$lambda$1(GameCommentFragment this$0, BasePopupWindow basePopupWindow, TextView textView) {
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int id = textView.getId();
        if (id == R.id.tv_number) {
            str = "reply_num";
        } else if (id == R.id.tv_time) {
            str = "id";
        } else {
            str = MonitorCommonConstants.DEFAULT_AID;
        }
        this$0.sort = str;
        this$0.getMBinding().tvSort.setText(textView.getText());
        basePopupWindow.dismiss();
        this$0.page = 1;
        this$0.getAdapter().setNewInstance(null);
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$3(GameCommentFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getMBinding().rv.scrollToPosition(0);
    }

    public final void getData() {
        Repository repository = Repository.INSTANCE;
        int i = this.page;
        GameDetailBean value = getVm().getData().getValue();
        Intrinsics.checkNotNull(value);
        repository.getGameComments2(i, value.getId(), this.sort, this.category, new Function1<PageBean<CommentBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameCommentFragment.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<CommentBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<CommentBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (GameCommentFragment.this.getPage() == 1) {
                    GameCommentFragment.this.getAdapter().setNewInstance(it.getList());
                } else {
                    GameCommentFragment.this.getAdapter().addData((Collection) it.getList());
                }
                GameCommentFragment gameCommentFragment = GameCommentFragment.this;
                gameCommentFragment.setPage(gameCommentFragment.getPage() + 1);
                gameCommentFragment.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(GameCommentFragment.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    GameCommentFragment.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameCommentFragment.getData.2
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
                GameCommentFragment.this.getAdapter().getLoadMoreModule().loadMoreFail();
                GameCommentFragment.this.log(it.toString());
            }
        });
    }
}
