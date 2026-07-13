package com.cy.yyjia.zhe28.ui.activity;

import android.text.TextUtils;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityWelfareEventDetailBinding;
import com.cy.yyjia.zhe28.databinding.ItemWelfareEventReplyBinding;
import com.cy.yyjia.zhe28.domain.CommentBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.WelfareEventDetailBean;
import com.cy.yyjia.zhe28.util.Repository;
import com.donkingliang.imageselector.utils.ImageSelector;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WelfareEventDetailActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0016\u001a\u00020\u0017J\b\u0010\u0018\u001a\u00020\u0017H\u0016J\u0010\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u000e\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u000eJ\u0006\u0010\u001e\u001a\u00020\u0017R'\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0015¨\u0006\u001f"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/WelfareEventDetailActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityWelfareEventDetailBinding;", "Landroid/view/View$OnClickListener;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/CommentBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemWelfareEventReplyBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "id", "", "getId", "()I", "id$delegate", "page", "getPage", "setPage", "(I)V", "getData", "", "init", "onClick", "v", "Landroid/view/View;", "praise", ImageSelector.POSITION, "submit", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class WelfareEventDetailActivity extends BaseActivity<ActivityWelfareEventDetailBinding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    /* JADX INFO: renamed from: id$delegate, reason: from kotlin metadata */
    private final Lazy id;
    private int page;

    public static final /* synthetic */ ActivityWelfareEventDetailBinding access$getMBinding(WelfareEventDetailActivity welfareEventDetailActivity) {
        return welfareEventDetailActivity.getMBinding();
    }

    public WelfareEventDetailActivity() {
        super(R.layout.activity_welfare_event_detail, 0, 2, null);
        this.id = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.WelfareEventDetailActivity$id$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("id", 0));
            }
        });
        this.page = 1;
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<CommentBean, ItemWelfareEventReplyBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.WelfareEventDetailActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<CommentBean, ItemWelfareEventReplyBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_welfare_event_reply, null, 2, null);
            }
        });
    }

    public final int getId() {
        return ((Number) this.id.getValue()).intValue();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    public final BaseAdapter<CommentBean, ItemWelfareEventReplyBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().rv.setAdapter(getAdapter());
        getAdapter().addChildClickViewIds(R.id.tv_praise);
        getAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.WelfareEventDetailActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                WelfareEventDetailActivity.init$lambda$0(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.WelfareEventDetailActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                WelfareEventDetailActivity.init$lambda$1(this.f$0);
            }
        });
        Repository.INSTANCE.getWelfareEventDetail(getId(), new Function1<WelfareEventDetailBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WelfareEventDetailActivity.init.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WelfareEventDetailBean welfareEventDetailBean) {
                invoke2(welfareEventDetailBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WelfareEventDetailBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                WelfareEventDetailActivity.access$getMBinding(WelfareEventDetailActivity.this).setData(it);
                WelfareEventDetailActivity.this.getData();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WelfareEventDetailActivity.init.4
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
                WelfareEventDetailActivity.this.netFail(it);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(WelfareEventDetailActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.praise(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(WelfareEventDetailActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    public final void getData() {
        Repository.INSTANCE.getGameComments(this.page, 0, 0, true, "", "", 0, new Function1<PageBean<CommentBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WelfareEventDetailActivity.getData.1
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
                if (WelfareEventDetailActivity.this.getPage() == 1) {
                    WelfareEventDetailActivity.this.getAdapter().setNewInstance(it.getList());
                } else {
                    WelfareEventDetailActivity.this.getAdapter().addData(it.getList());
                }
                WelfareEventDetailActivity welfareEventDetailActivity = WelfareEventDetailActivity.this;
                welfareEventDetailActivity.setPage(welfareEventDetailActivity.getPage() + 1);
                welfareEventDetailActivity.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(WelfareEventDetailActivity.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    WelfareEventDetailActivity.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WelfareEventDetailActivity.getData.2
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
                WelfareEventDetailActivity.this.netFail(it);
            }
        }, Integer.valueOf(getId()));
    }

    public final void praise(final int position) {
        Repository.INSTANCE.praiseComments(getAdapter().getItem(position).getId(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WelfareEventDetailActivity.praise.1
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
                int like_num;
                Intrinsics.checkNotNullParameter(it, "it");
                WelfareEventDetailActivity.this.toast(it.getMsg());
                if (it.getCode() == 200) {
                    WelfareEventDetailActivity.this.getAdapter().getItem(position).setIslikeNum(WelfareEventDetailActivity.this.getAdapter().getItem(position).getIslikeNum() == 1 ? 0 : 1);
                    CommentBean item = WelfareEventDetailActivity.this.getAdapter().getItem(position);
                    Object data = it.getData();
                    Intrinsics.checkNotNull(data, "null cannot be cast to non-null type kotlin.Boolean");
                    if (((Boolean) data).booleanValue()) {
                        like_num = WelfareEventDetailActivity.this.getAdapter().getItem(position).getLike_num() + 1;
                    } else {
                        like_num = WelfareEventDetailActivity.this.getAdapter().getItem(position).getLike_num() - 1;
                    }
                    item.setLike_num(like_num);
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WelfareEventDetailActivity.praise.2
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
                WelfareEventDetailActivity.this.netFail(it);
            }
        });
    }

    public final void submit() {
        Repository repository = Repository.INSTANCE;
        String text = getMBinding().getText();
        Intrinsics.checkNotNull(text);
        repository.submitComment(0, 5, (3072 & 4) != 0 ? 0 : 0, text, 0, 0, new ArrayList(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WelfareEventDetailActivity.submit.1
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
                WelfareEventDetailActivity.this.toast(it.getMsg());
                if (it.getCode() == 200) {
                    WelfareEventDetailActivity.access$getMBinding(WelfareEventDetailActivity.this).setText("");
                    WelfareEventDetailActivity.this.hideSoftKeyboard();
                    WelfareEventDetailActivity.access$getMBinding(WelfareEventDetailActivity.this).et.clearFocus();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WelfareEventDetailActivity.submit.2
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
                WelfareEventDetailActivity.this.netFail(it);
            }
        }, (3072 & 512) != 0 ? 0 : Integer.valueOf(getId()), (3072 & 1024) != 0 ? "" : null, (3072 & 2048) != 0 ? 0 : null);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (v.getId() != R.id.btn_send || TextUtils.isEmpty(getMBinding().getText()) || checkClick()) {
            return;
        }
        submit();
    }
}
