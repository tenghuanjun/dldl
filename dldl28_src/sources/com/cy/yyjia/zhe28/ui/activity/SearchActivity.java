package com.cy.yyjia.zhe28.ui.activity;

import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.room.Room;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivitySearchBinding;
import com.cy.yyjia.zhe28.databinding.ItemGameSearchBinding;
import com.cy.yyjia.zhe28.databinding.ItemHomeGameBinding;
import com.cy.yyjia.zhe28.databinding.ItemSearchHistoryBinding;
import com.cy.yyjia.zhe28.databinding.ItemSearchHotBinding;
import com.cy.yyjia.zhe28.db.SearchHistoryDao;
import com.cy.yyjia.zhe28.db.SearchHistoryDatabase;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.SearchHistory;
import com.cy.yyjia.zhe28.domain.SearchHotBean;
import com.cy.yyjia.zhe28.domain.SearchResult;
import com.cy.yyjia.zhe28.domain.TypeBean;
import com.cy.yyjia.zhe28.ui.activity.SearchActivity;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.volcengine.cloudcore.common.mode.KeyBoardKey;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: SearchActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u00018B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010/\u001a\u000200J\u0006\u00101\u001a\u000200J\b\u00102\u001a\u000200H\u0016J\u0006\u00103\u001a\u000200J\u0010\u00104\u001a\u0002002\u0006\u00105\u001a\u000206H\u0016J\u0006\u00107\u001a\u000200R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R'\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\n\u001a\u0004\b\u0015\u0010\u0016R'\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\n\u001a\u0004\b\u001b\u0010\u0016R'\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001e0\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b \u0010\n\u001a\u0004\b\u001f\u0010\u0016R\u001a\u0010!\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010)\"\u0004\b*\u0010+R\u001a\u0010,\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010$\"\u0004\b.\u0010&¨\u00069"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/SearchActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivitySearchBinding;", "Landroid/view/View$OnClickListener;", "()V", "dao", "Lcom/cy/yyjia/zhe28/db/SearchHistoryDao;", "getDao", "()Lcom/cy/yyjia/zhe28/db/SearchHistoryDao;", "dao$delegate", "Lkotlin/Lazy;", "game", "", "getGame", "()Ljava/lang/String;", "setGame", "(Ljava/lang/String;)V", "gameAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemGameSearchBinding;", "getGameAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "gameAdapter$delegate", "historyAdapter", "Lcom/cy/yyjia/zhe28/domain/SearchHistory;", "Lcom/cy/yyjia/zhe28/databinding/ItemSearchHistoryBinding;", "getHistoryAdapter", "historyAdapter$delegate", "hotAdapter", "Lcom/cy/yyjia/zhe28/databinding/ItemHomeGameBinding;", "getHotAdapter", "hotAdapter$delegate", "hotPage", "", "getHotPage", "()I", "setHotPage", "(I)V", "isHistory", "", "()Z", "setHistory", "(Z)V", "page", "getPage", "setPage", "getHot", "", "getWord", "init", "initHistory", "onClick", "v", "Landroid/view/View;", "search", "HotAdapter", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SearchActivity extends BaseActivity<ActivitySearchBinding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: dao$delegate, reason: from kotlin metadata */
    private final Lazy dao;
    private String game;

    /* JADX INFO: renamed from: gameAdapter$delegate, reason: from kotlin metadata */
    private final Lazy gameAdapter;

    /* JADX INFO: renamed from: historyAdapter$delegate, reason: from kotlin metadata */
    private final Lazy historyAdapter;

    /* JADX INFO: renamed from: hotAdapter$delegate, reason: from kotlin metadata */
    private final Lazy hotAdapter;
    private int hotPage;
    private boolean isHistory;
    private int page;

    public SearchActivity() {
        super(R.layout.activity_search, 1);
        this.hotAdapter = LazyKt.lazy(new Function0<BaseAdapter<GameBean, ItemHomeGameBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.SearchActivity$hotAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<GameBean, ItemHomeGameBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_home_game, new Function3<BaseDataBindingHolder<ItemHomeGameBinding>, Integer, GameBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SearchActivity$hotAdapter$2.1
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemHomeGameBinding> baseDataBindingHolder, Integer num, GameBean gameBean) {
                        invoke(baseDataBindingHolder, num.intValue(), gameBean);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(BaseDataBindingHolder<ItemHomeGameBinding> h, int i, GameBean gameBean) {
                        Intrinsics.checkNotNullParameter(h, "h");
                        ViewDataBinding dataBinding = h.getDataBinding();
                        Intrinsics.checkNotNull(dataBinding);
                        ((ItemHomeGameBinding) dataBinding).setPosition(h.getLayoutPosition());
                    }
                });
            }
        });
        this.gameAdapter = LazyKt.lazy(new Function0<BaseAdapter<GameBean, ItemGameSearchBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.SearchActivity$gameAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<GameBean, ItemGameSearchBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_game_search, null, 2, null);
            }
        });
        this.dao = LazyKt.lazy(new Function0<SearchHistoryDao>() { // from class: com.cy.yyjia.zhe28.ui.activity.SearchActivity$dao$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final SearchHistoryDao invoke() {
                return ((SearchHistoryDatabase) Room.databaseBuilder(this.this$0, SearchHistoryDatabase.class, "SearchHistory").allowMainThreadQueries().build()).SearchHistoryDao();
            }
        });
        this.historyAdapter = LazyKt.lazy(new Function0<BaseAdapter<SearchHistory, ItemSearchHistoryBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.SearchActivity$historyAdapter$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<SearchHistory, ItemSearchHistoryBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_search_history, this.this$0.getDao().getAll());
            }
        });
        this.page = 1;
        this.hotPage = 1;
        this.game = "";
    }

    public static final /* synthetic */ ActivitySearchBinding access$getMBinding(SearchActivity searchActivity) {
        return searchActivity.getMBinding();
    }

    public final BaseAdapter<GameBean, ItemHomeGameBinding> getHotAdapter() {
        return (BaseAdapter) this.hotAdapter.getValue();
    }

    public final BaseAdapter<GameBean, ItemGameSearchBinding> getGameAdapter() {
        return (BaseAdapter) this.gameAdapter.getValue();
    }

    public final SearchHistoryDao getDao() {
        return (SearchHistoryDao) this.dao.getValue();
    }

    public final BaseAdapter<SearchHistory, ItemSearchHistoryBinding> getHistoryAdapter() {
        return (BaseAdapter) this.historyAdapter.getValue();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    public final int getHotPage() {
        return this.hotPage;
    }

    public final void setHotPage(int i) {
        this.hotPage = i;
    }

    public final String getGame() {
        return this.game;
    }

    public final void setGame(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.game = str;
    }

    /* JADX INFO: renamed from: isHistory, reason: from getter */
    public final boolean getIsHistory() {
        return this.isHistory;
    }

    public final void setHistory(boolean z) {
        this.isHistory = z;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        EditText et = getMBinding().et;
        Intrinsics.checkNotNullExpressionValue(et, "et");
        et.addTextChangedListener(new TextWatcher() { // from class: com.cy.yyjia.zhe28.ui.activity.SearchActivity$init$$inlined$addTextChangedListener$default$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                if (this.this$0.getIsHistory() || s == null || s.length() <= 0) {
                    return;
                }
                this.this$0.setPage(1);
                this.this$0.setGame(s.toString());
                this.this$0.search();
            }
        });
        initHistory();
        getMBinding().rv.setAdapter(getGameAdapter());
        getGameAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SearchActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                SearchActivity.init$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getGameAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SearchActivity$$ExternalSyntheticLambda3
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                SearchActivity.init$lambda$2(this.f$0);
            }
        });
        getMBinding().rvHot.setAdapter(getHotAdapter());
        getHot();
        getWord();
        String stringExtra = getIntent().getStringExtra("str");
        if (TextUtils.isEmpty(stringExtra)) {
            return;
        }
        getMBinding().et.setText(stringExtra);
        getMBinding().tvSearch.performClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(SearchActivity this$0, BaseQuickAdapter baseQuickAdapter, View v, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(v, "v");
        String showName = this$0.getGameAdapter().getItem(i).getShowName();
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0), null, null, new SearchActivity$init$2$1(this$0, showName, new SearchHistory(showName, System.currentTimeMillis()), null), 3, null);
        Repository.INSTANCE.clickGame(this$0.getGameAdapter().getItem(i).getId());
        this$0.getGameAdapter().getItem(i).gotoGame(v);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(SearchActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.search();
    }

    public final void initHistory() {
        getMBinding().rvHistory.setLayoutManager(new FlexboxLayoutManager(this));
        getMBinding().rvHistory.setAdapter(getHistoryAdapter());
        getHistoryAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SearchActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                SearchActivity.initHistory$lambda$3(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getMBinding().et.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SearchActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                SearchActivity.initHistory$lambda$4(this.f$0, view, z);
            }
        });
        if (getHistoryAdapter().getData().size() == 0) {
            getMBinding().rlHistory.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initHistory$lambda$3(SearchActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        SearchHistory searchHistory = new SearchHistory(this$0.getHistoryAdapter().getItem(i).getText(), System.currentTimeMillis());
        this$0.getDao().updateData(searchHistory);
        this$0.getHistoryAdapter().removeAt(i);
        this$0.getHistoryAdapter().addData(0, searchHistory);
        this$0.isHistory = true;
        this$0.getMBinding().et.setText(searchHistory.getText());
        this$0.getMBinding().tvSearch.performClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initHistory$lambda$4(SearchActivity this$0, View view, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z && this$0.getHistoryAdapter().getData().size() > 0) {
            this$0.getMBinding().rlHistory.setVisibility(0);
        } else {
            this$0.getMBinding().rlHistory.setVisibility(8);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        getMBinding().et.clearFocus();
        switch (v.getId()) {
            case R.id.iv_clear /* 2131362166 */:
                getMBinding().setSearch(false);
                this.isHistory = true;
                this.game = "";
                getMBinding().et.setText("");
                getMBinding().rv.setVisibility(8);
                break;
            case R.id.tv_apply /* 2131362658 */:
            case R.id.tv_empty /* 2131362695 */:
                Util.skipWithLogin(this, GameReportActivity2.class);
                break;
            case R.id.tv_clean /* 2131362670 */:
                getDao().deleteAll();
                getHistoryAdapter().setNewInstance(null);
                getMBinding().rlHistory.setVisibility(8);
                break;
            case R.id.tv_search /* 2131362768 */:
                if (!checkClick(1)) {
                    this.isHistory = false;
                    hideSoftKeyboard();
                    this.page = 1;
                    this.game = getMBinding().et.getText().toString();
                    SearchHistory searchHistory = new SearchHistory(this.game, System.currentTimeMillis());
                    BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C10701(searchHistory, null), 3, null);
                    Iterator<SearchHistory> it = getHistoryAdapter().getData().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            SearchHistory next = it.next();
                            if (Intrinsics.areEqual(next.getText(), this.game)) {
                                getHistoryAdapter().remove(next);
                            }
                        }
                    }
                    getHistoryAdapter().addData(0, searchHistory);
                    getMBinding().rlHistory.setVisibility(8);
                    search();
                    break;
                }
                break;
        }
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.SearchActivity$onClick$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SearchActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.cy.yyjia.zhe28.ui.activity.SearchActivity$onClick$1", f = "SearchActivity.kt", i = {}, l = {KeyBoardKey.KeyboardKeyOemFjTouroku}, m = "invokeSuspend", n = {}, s = {})
    static final class C10701 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ SearchHistory $history;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10701(SearchHistory searchHistory, Continuation<? super C10701> continuation) {
            super(2, continuation);
            this.$history = searchHistory;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SearchActivity.this.new C10701(this.$history, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C10701) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.SearchActivity$onClick$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SearchActivity.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.cy.yyjia.zhe28.ui.activity.SearchActivity$onClick$1$1", f = "SearchActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C02231 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ SearchHistory $history;
            int label;
            final /* synthetic */ SearchActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C02231(SearchActivity searchActivity, SearchHistory searchHistory, Continuation<? super C02231> continuation) {
                super(2, continuation);
                this.this$0 = searchActivity;
                this.$history = searchHistory;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C02231(this.this$0, this.$history, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C02231) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                if (this.this$0.getDao().check(this.this$0.getGame()) == null) {
                    this.this$0.getDao().add(this.$history);
                } else {
                    this.this$0.getDao().updateData(this.$history);
                }
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (BuildersKt.withContext(Dispatchers.getIO(), new C02231(SearchActivity.this, this.$history, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final void search() {
        getMBinding().setSearch(true);
        Repository.INSTANCE.searchGame(this.page, this.game, new Function1<SearchResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SearchActivity.search.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SearchResult searchResult) {
                invoke2(searchResult);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SearchResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (SearchActivity.this.getPage() == 1) {
                    SearchActivity.this.getGameAdapter().setNewInstance(it.getList().getList());
                    if (it.getList().getList().size() == 0) {
                        String str = "没有搜索到“" + SearchActivity.this.getGame() + "”游戏，为您推荐以下搜索结果。您也可以点击此处申请新游>";
                        SpannableString spannableString = new SpannableString(str);
                        spannableString.setSpan(new ForegroundColorSpan(SearchActivity.this.getResources().getColor(R.color.colorPrimary)), str.length() - 13, str.length(), 33);
                        SearchActivity.access$getMBinding(SearchActivity.this).tvEmpty.setText(spannableString);
                        SearchActivity.access$getMBinding(SearchActivity.this).rv.setVisibility(8);
                        SearchActivity.access$getMBinding(SearchActivity.this).llGone.setVisibility(0);
                        SearchActivity.access$getMBinding(SearchActivity.this).llEmpty.setVisibility(0);
                    } else {
                        SearchActivity.access$getMBinding(SearchActivity.this).rv.setVisibility(0);
                        SearchActivity.access$getMBinding(SearchActivity.this).llGone.setVisibility(8);
                        SearchActivity.access$getMBinding(SearchActivity.this).llEmpty.setVisibility(8);
                    }
                } else {
                    SearchActivity.this.getGameAdapter().addData(it.getList().getList());
                }
                SearchActivity searchActivity = SearchActivity.this;
                searchActivity.setPage(searchActivity.getPage() + 1);
                searchActivity.getPage();
                if (it.getList().getCurrent_page() >= it.getList().getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(SearchActivity.this.getGameAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    SearchActivity.this.getGameAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SearchActivity.search.2
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
                SearchActivity.this.getGameAdapter().getLoadMoreModule().loadMoreFail();
                SearchActivity searchActivity = SearchActivity.this;
                String localizedMessage = it.getLocalizedMessage();
                Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                searchActivity.log(localizedMessage);
            }
        });
    }

    public final void getHot() {
        Repository.INSTANCE.getHotSearch(this.hotPage, new Function1<PageBean<GameBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SearchActivity.getHot.1
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
                SearchActivity.this.getHotAdapter().setNewInstance(it.getList());
                SearchActivity.access$getMBinding(SearchActivity.this).rvEmpty.setAdapter(new BaseAdapter(R.layout.item_home_game, it.getList()));
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SearchActivity.getHot.2
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
                SearchActivity.this.getHotAdapter().getLoadMoreModule().loadMoreFail();
                SearchActivity searchActivity = SearchActivity.this;
                String localizedMessage = it.getLocalizedMessage();
                Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                searchActivity.log(localizedMessage);
            }
        });
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.SearchActivity$getWord$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SearchActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/cy/yyjia/zhe28/domain/SearchHotBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class C10681 extends Lambda implements Function1<SearchHotBean, Unit> {
        C10681() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(SearchHotBean searchHotBean) {
            invoke2(searchHotBean);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(SearchHotBean it) {
            Intrinsics.checkNotNullParameter(it, "it");
            SearchActivity.access$getMBinding(SearchActivity.this).rvType.setLayoutManager(new FlexboxLayoutManager(SearchActivity.this));
            final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_search_type, it.getCategory());
            SearchActivity.access$getMBinding(SearchActivity.this).rvType.setAdapter(baseAdapter);
            final SearchActivity searchActivity = SearchActivity.this;
            baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SearchActivity$getWord$1$$ExternalSyntheticLambda0
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    SearchActivity.C10681.invoke$lambda$0(searchActivity, baseAdapter, baseQuickAdapter, view, i);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final void invoke$lambda$0(SearchActivity this$0, BaseAdapter typeAdapter, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(typeAdapter, "$typeAdapter");
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
            this$0.setHistory(true);
            SearchActivity.access$getMBinding(this$0).et.setText(((TypeBean) typeAdapter.getItem(i)).getName());
            SearchActivity.access$getMBinding(this$0).tvSearch.performClick();
        }
    }

    public final void getWord() {
        Repository.INSTANCE.getHotWord(new C10681(), new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SearchActivity.getWord.2
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
                SearchActivity.this.netFail(it);
            }
        });
    }

    /* JADX INFO: compiled from: SearchActivity.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/SearchActivity$HotAdapter;", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemSearchHotBinding;", "data", "", "(Ljava/util/List;)V", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class HotAdapter extends BaseAdapter<GameBean, ItemSearchHotBinding> {
        public static final int $stable = 0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public HotAdapter(List<GameBean> data) {
            super(R.layout.item_search_hot, data);
            Intrinsics.checkNotNullParameter(data, "data");
            setMFun(new Function3<BaseDataBindingHolder<ItemSearchHotBinding>, Integer, GameBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SearchActivity.HotAdapter.4
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemSearchHotBinding> baseDataBindingHolder, Integer num, GameBean gameBean) {
                    invoke(baseDataBindingHolder, num.intValue(), gameBean);
                    return Unit.INSTANCE;
                }

                public final void invoke(BaseDataBindingHolder<ItemSearchHotBinding> h, int i, GameBean gameBean) {
                    Intrinsics.checkNotNullParameter(h, "h");
                    ItemSearchHotBinding itemSearchHotBinding = (ItemSearchHotBinding) h.getDataBinding();
                    if (itemSearchHotBinding == null) {
                        return;
                    }
                    itemSearchHotBinding.setPosition(i);
                }
            });
        }
    }
}
