package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityMessageBinding;
import com.cy.yyjia.zhe28.databinding.ItemMessageDickerBinding;
import com.cy.yyjia.zhe28.databinding.ItemMessageOfficialBinding;
import com.cy.yyjia.zhe28.databinding.ItemMessageUserBinding;
import com.cy.yyjia.zhe28.domain.CommentMessageBean;
import com.cy.yyjia.zhe28.domain.DickerMessageBean;
import com.cy.yyjia.zhe28.domain.MessageBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.UnreadBean;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.volcengine.common.contant.CommonConstants;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MessageActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\u001eJ\u0006\u0010 \u001a\u00020\u001eJ\u0006\u0010!\u001a\u00020\u001eJ\b\u0010\"\u001a\u00020\u001eH\u0016J\u0010\u0010#\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020%H\u0016J\b\u0010&\u001a\u00020\u001eH\u0014R'\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR'\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u0010\u0010\nR'\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\f\u001a\u0004\b\u0015\u0010\nR\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006'"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/MessageActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityMessageBinding;", "Landroid/view/View$OnClickListener;", "()V", "adapter1", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/MessageBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemMessageOfficialBinding;", "getAdapter1", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter1$delegate", "Lkotlin/Lazy;", "adapter2", "Lcom/cy/yyjia/zhe28/domain/CommentMessageBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemMessageUserBinding;", "getAdapter2", "adapter2$delegate", "adapter3", "Lcom/cy/yyjia/zhe28/domain/DickerMessageBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemMessageDickerBinding;", "getAdapter3", "adapter3$delegate", "page", "", "getPage", "()I", "setPage", "(I)V", "getData", "", "getData2", "getData3", "getUnreadNumber", "init", "onClick", "v", "Landroid/view/View;", "onResume", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class MessageActivity extends BaseActivity<ActivityMessageBinding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter1$delegate, reason: from kotlin metadata */
    private final Lazy adapter1;

    /* JADX INFO: renamed from: adapter2$delegate, reason: from kotlin metadata */
    private final Lazy adapter2;

    /* JADX INFO: renamed from: adapter3$delegate, reason: from kotlin metadata */
    private final Lazy adapter3;
    private int page;

    public MessageActivity() {
        super(R.layout.activity_message, 0, 2, null);
        this.adapter1 = LazyKt.lazy(new Function0<BaseAdapter<MessageBean, ItemMessageOfficialBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity$adapter1$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<MessageBean, ItemMessageOfficialBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_message_official, null, 2, null);
            }
        });
        this.adapter2 = LazyKt.lazy(new Function0<BaseAdapter<CommentMessageBean, ItemMessageUserBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity$adapter2$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<CommentMessageBean, ItemMessageUserBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_message_user, null, 2, null);
            }
        });
        this.adapter3 = LazyKt.lazy(new Function0<BaseAdapter<DickerMessageBean, ItemMessageDickerBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity$adapter3$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<DickerMessageBean, ItemMessageDickerBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_message_dicker, null, 2, null);
            }
        });
        this.page = 1;
    }

    public static final /* synthetic */ ActivityMessageBinding access$getMBinding(MessageActivity messageActivity) {
        return messageActivity.getMBinding();
    }

    public final BaseAdapter<MessageBean, ItemMessageOfficialBinding> getAdapter1() {
        return (BaseAdapter) this.adapter1.getValue();
    }

    public final BaseAdapter<CommentMessageBean, ItemMessageUserBinding> getAdapter2() {
        return (BaseAdapter) this.adapter2.getValue();
    }

    public final BaseAdapter<DickerMessageBean, ItemMessageDickerBinding> getAdapter3() {
        return (BaseAdapter) this.adapter3.getValue();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().setType(1);
        getMBinding().rv.setAdapter(getAdapter1());
        getAdapter1().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                MessageActivity.init$lambda$0(this.f$0);
            }
        });
        getAdapter1().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MessageActivity.init$lambda$2(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getAdapter2().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                MessageActivity.init$lambda$3(this.f$0);
            }
        });
        getAdapter2().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity$$ExternalSyntheticLambda3
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MessageActivity.init$lambda$5(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getAdapter3().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity$$ExternalSyntheticLambda4
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                MessageActivity.init$lambda$6(this.f$0);
            }
        });
        getAdapter3().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity$$ExternalSyntheticLambda5
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MessageActivity.init$lambda$7(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getData();
        getUnreadNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(MessageActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(final MessageActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        if (!this$0.getAdapter1().getItem(i).isRead()) {
            this$0.getAdapter1().getItem(i).setRead(true);
            this$0.getAdapter1().notifyItemChanged(i);
            UnreadBean data = this$0.getMBinding().getData();
            if (data != null) {
                Repository.INSTANCE.readMessage(this$0.getAdapter1().getItem(i).getId(), CommonConstants.KEY_MESSAGE, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity$init$2$1$1
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Result it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Result result) {
                        invoke2(result);
                        return Unit.INSTANCE;
                    }
                }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity$init$2$1$2
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
                        MessageActivity messageActivity = this.this$0;
                        String localizedMessage = it.getLocalizedMessage();
                        Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                        messageActivity.log(localizedMessage);
                    }
                });
                data.setMsgNum(data.getMsgNum() - 1);
            }
        }
        Intent intent = new Intent(this$0, (Class<?>) WebActivity.class);
        intent.putExtra("messageId", this$0.getAdapter1().getItem(i).getId());
        this$0.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$3(MessageActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData2();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$5(final MessageActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        if (!this$0.getAdapter2().getItem(i).isRead()) {
            Repository.INSTANCE.readMessage(this$0.getAdapter1().getItem(i).getId(), "comment", new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity$init$4$1
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Result it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Result result) {
                    invoke2(result);
                    return Unit.INSTANCE;
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity$init$4$2
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
                    MessageActivity messageActivity = this.this$0;
                    String localizedMessage = it.getLocalizedMessage();
                    Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                    messageActivity.log(localizedMessage);
                }
            });
            UnreadBean data = this$0.getMBinding().getData();
            if (data != null) {
                data.setCommentNum(data.getCommentNum() - 1);
            }
        }
        Util.gotoGame(this$0, this$0.getAdapter2().getItem(i).getGameId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$6(MessageActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData3();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$7(final MessageActivity this$0, BaseQuickAdapter baseQuickAdapter, View v, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(v, "v");
        if (this$0.getAdapter3().getItem(i).getIsread() != 1) {
            this$0.getAdapter3().getItem(i).setIsread(1);
            this$0.getAdapter3().notifyItemChanged(i);
            this$0.getMBinding().setDicker(r2.getDicker() - 1);
            Repository.INSTANCE.readDicker(this$0.getAdapter3().getItem(i).getId(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity$init$6$1
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Result it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Result result) {
                    invoke2(result);
                    return Unit.INSTANCE;
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity$init$6$2
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
        }
        Intent intent = new Intent(this$0, (Class<?>) DealRecordActivity.class);
        intent.putExtra(ImageSelector.POSITION, 8);
        this$0.startActivity(intent);
    }

    public final void getData() {
        Repository.INSTANCE.getMessageList(this.page, new Function1<PageBean<MessageBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<MessageBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<MessageBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (MessageActivity.this.getPage() == 1) {
                    MessageActivity.this.getAdapter1().setNewInstance(it.getList());
                } else {
                    MessageActivity.this.getAdapter1().addData(it.getList());
                }
                MessageActivity messageActivity = MessageActivity.this;
                messageActivity.setPage(messageActivity.getPage() + 1);
                messageActivity.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(MessageActivity.this.getAdapter1().getLoadMoreModule(), false, 1, null);
                } else {
                    MessageActivity.this.getAdapter1().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity.getData.2
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
                MessageActivity.this.netFail(it);
                MessageActivity.this.getAdapter1().getLoadMoreModule().loadMoreFail();
            }
        });
    }

    public final void getData2() {
        Repository.INSTANCE.getMessageList2(this.page, new Function1<PageBean<CommentMessageBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity.getData2.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<CommentMessageBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<CommentMessageBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (MessageActivity.this.getPage() == 1) {
                    MessageActivity.this.getAdapter2().setNewInstance(it.getList());
                } else {
                    MessageActivity.this.getAdapter2().addData(it.getList());
                }
                MessageActivity messageActivity = MessageActivity.this;
                messageActivity.setPage(messageActivity.getPage() + 1);
                messageActivity.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(MessageActivity.this.getAdapter2().getLoadMoreModule(), false, 1, null);
                } else {
                    MessageActivity.this.getAdapter2().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity.getData2.2
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
                MessageActivity.this.netFail(it);
                MessageActivity.this.getAdapter2().getLoadMoreModule().loadMoreFail();
            }
        });
    }

    public final void getData3() {
        Repository.INSTANCE.getMessageList3(this.page, new Function1<PageBean<DickerMessageBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity.getData3.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<DickerMessageBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<DickerMessageBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (MessageActivity.this.getPage() == 1) {
                    MessageActivity.this.getAdapter3().setNewInstance(it.getList());
                } else {
                    MessageActivity.this.getAdapter3().addData(it.getList());
                }
                MessageActivity messageActivity = MessageActivity.this;
                messageActivity.setPage(messageActivity.getPage() + 1);
                messageActivity.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(MessageActivity.this.getAdapter3().getLoadMoreModule(), false, 1, null);
                } else {
                    MessageActivity.this.getAdapter3().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity.getData3.2
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
                MessageActivity.this.netFail(it);
                MessageActivity.this.getAdapter3().getLoadMoreModule().loadMoreFail();
            }
        });
    }

    public final void getUnreadNumber() {
        if (Constant.INSTANCE.getLogged()) {
            Repository.INSTANCE.getUnreadNumber(new Function1<UnreadBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity.getUnreadNumber.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(UnreadBean unreadBean) {
                    invoke2(unreadBean);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(UnreadBean it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    MessageActivity.access$getMBinding(MessageActivity.this).setData(it);
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity.getUnreadNumber.2
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
                    MessageActivity.this.netFail(it);
                }
            });
            Repository.INSTANCE.getUnreadNumber2(new Function1<Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity.getUnreadNumber.3
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i) {
                    MessageActivity.access$getMBinding(MessageActivity.this).setDicker(i);
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity.getUnreadNumber.4
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
                    MessageActivity.this.netFail(it);
                }
            });
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        getUnreadNumber();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        if (id != R.id.iv_clean) {
            switch (id) {
                case R.id.tv_1 /* 2131362651 */:
                    getMBinding().rv.setAdapter(getAdapter1());
                    getMBinding().setType(1);
                    this.page = 1;
                    getAdapter1().setNewInstance(null);
                    getData();
                    break;
                case R.id.tv_2 /* 2131362652 */:
                    getMBinding().rv.setAdapter(getAdapter2());
                    getMBinding().setType(2);
                    this.page = 1;
                    getAdapter2().setNewInstance(null);
                    getData2();
                    break;
                case R.id.tv_3 /* 2131362653 */:
                    getMBinding().rv.setAdapter(getAdapter3());
                    getMBinding().setType(3);
                    this.page = 1;
                    getAdapter3().setNewInstance(null);
                    getData3();
                    break;
            }
        }
        Repository.INSTANCE.readAllMessage(getMBinding().getType() == 1 ? CommonConstants.KEY_MESSAGE : "comment", new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity.onClick.1
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
                MessageActivity.this.toast(it.getMsg());
                MessageActivity.this.getUnreadNumber();
                MessageActivity.this.setPage(1);
                if (MessageActivity.access$getMBinding(MessageActivity.this).getType() == 1) {
                    MessageActivity.this.getData();
                } else {
                    MessageActivity.this.getData2();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MessageActivity.onClick.2
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
                MessageActivity.this.netFail(it);
            }
        });
    }
}
