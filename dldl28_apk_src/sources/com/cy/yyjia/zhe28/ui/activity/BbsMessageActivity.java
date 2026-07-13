package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import androidx.databinding.ViewDataBinding;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.QuickDialog;
import com.cy.yyjia.zhe28.databinding.ActivityRvBinding;
import com.cy.yyjia.zhe28.domain.BbsBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Repository;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import java.util.LinkedHashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: BbsMessageActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0015\u001a\u00020\u0016J\b\u0010\u0017\u001a\u00020\u0016H\u0016J\u000e\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\rJ\u0016\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u001dR)\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\b\u0001\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0012\u001a\u00020\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u000b\u001a\u0004\b\u0013\u0010\u000f¨\u0006\u001e"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/BbsMessageActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityRvBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/BbsBean;", "Landroidx/databinding/ViewDataBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "page", "", "getPage", "()I", "setPage", "(I)V", "type", "getType", "type$delegate", "getData", "", "init", "read", ImageSelector.POSITION, "reply", "id", "text", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class BbsMessageActivity extends BaseActivity<ActivityRvBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;
    private int page;

    /* JADX INFO: renamed from: type$delegate, reason: from kotlin metadata */
    private final Lazy type;

    public BbsMessageActivity() {
        super(R.layout.activity_rv, 0, 2, null);
        this.type = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsMessageActivity$type$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("type", 0));
            }
        });
        this.page = 1;
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<BbsBean, ? extends ViewDataBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsMessageActivity$adapter$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<BbsBean, ? extends ViewDataBinding> invoke() {
                if (this.this$0.getType() == 0) {
                    return new BaseAdapter<>(R.layout.item_bbs_message_official, null, 2, null);
                }
                return new BaseAdapter<>(R.layout.item_bbs_message_reply, null, 2, null);
            }
        });
    }

    public final int getType() {
        return ((Number) this.type.getValue()).intValue();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    public final BaseAdapter<BbsBean, ? extends ViewDataBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().navigation.setTitle(getIntent().getStringExtra("name"));
        getMBinding().rv.setAdapter(getAdapter());
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsMessageActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                BbsMessageActivity.init$lambda$0(this.f$0);
            }
        });
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsMessageActivity$$ExternalSyntheticLambda1
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                BbsMessageActivity.init$lambda$1(this.f$0, refreshLayout);
            }
        });
        if (getType() == 1) {
            getAdapter().addChildClickViewIds(R.id.tv_reply);
            getAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsMessageActivity$$ExternalSyntheticLambda2
                @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
                public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    BbsMessageActivity.init$lambda$3(this.f$0, baseQuickAdapter, view, i);
                }
            });
        }
        getAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsMessageActivity$$ExternalSyntheticLambda3
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                BbsMessageActivity.init$lambda$4(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(BbsMessageActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(BbsMessageActivity this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.page = 1;
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$3(final BbsMessageActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, final int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.read(i);
        new QuickDialog(this$0, R.layout.dialog_bbs_reply).setData(this$0.getAdapter().getItem(i)).setOnClickListener(R.id.btn, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsMessageActivity$$ExternalSyntheticLambda4
            @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
            public final void onClick(BaseDialog baseDialog, View view2) {
                BbsMessageActivity.init$lambda$3$lambda$2(this.f$0, i, baseDialog, view2);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$3$lambda$2(BbsMessageActivity this$0, int i, BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        EditText editText = (EditText) baseDialog.findViewById(R.id.et);
        Intrinsics.checkNotNull(editText);
        String string = editText.getText().toString();
        if (TextUtils.isEmpty(string)) {
            this$0.toast("请先输入回复内容");
            return;
        }
        this$0.hideSoftKeyboard();
        baseDialog.dismiss();
        this$0.reply(this$0.getAdapter().getItem(i).getId(), string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$4(BbsMessageActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "view");
        this$0.read(i);
        if (this$0.getType() == 0) {
            this$0.getAdapter().getItem(i).go(view);
            return;
        }
        boolean z = this$0.getAdapter().getItem(i).isReply() == 1;
        BbsMessageActivity bbsMessageActivity = this$0;
        Intent intent = new Intent(bbsMessageActivity, (Class<?>) BbsDetailActivity.class);
        intent.putExtra("id", this$0.getAdapter().getItem(i).getPostId());
        if (!z) {
            intent.putExtra("targetId", this$0.getAdapter().getItem(i).getId());
        }
        this$0.startActivity(intent);
        if (this$0.getAdapter().getItem(i).isReply() == 1) {
            Intent intent2 = new Intent(bbsMessageActivity, (Class<?>) BbsDetailActivity.class);
            intent2.putExtra("id", this$0.getAdapter().getItem(i).getPid());
            intent2.putExtra("child", true);
            intent2.putExtra("targetId", this$0.getAdapter().getItem(i).getId());
            this$0.startActivity(intent2);
        }
    }

    public final void getData() {
        String str;
        if (getType() == 0) {
            str = "bbs/siteMessage";
        } else {
            str = "bbs/myMessage";
        }
        String str2 = str;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("page", String.valueOf(this.page));
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new BbsMessageActivity$getData$$inlined$get$1(str2, linkedHashMap, null, this, this), 3, null);
    }

    public final void reply(int id, String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (TextUtils.isEmpty(text)) {
            toast("请先输入回复内容");
        } else {
            hideSoftKeyboard();
            Repository.INSTANCE.replyBbs(id, text, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsMessageActivity.reply.1
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
                    BbsMessageActivity.this.toast(it.getMsg());
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsMessageActivity.reply.2
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
                    BbsMessageActivity.this.netFail(it);
                }
            });
        }
    }

    public final void read(final int position) {
        if (getAdapter().getItem(position).getRead() == 1) {
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("id", String.valueOf(getAdapter().getItem(position).getId()));
        NetUtil.post2$default(NetUtil.INSTANCE, "bbs/replayRead", linkedHashMap, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsMessageActivity.read.1
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
                BbsMessageActivity.this.log(it.getMsg());
                BbsMessageActivity.this.getAdapter().getItem(position).setRead(1);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsMessageActivity.read.2
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
                BbsMessageActivity bbsMessageActivity = BbsMessageActivity.this;
                String localizedMessage = it.getLocalizedMessage();
                Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                bbsMessageActivity.log(localizedMessage);
            }
        }, null, 16, null);
    }
}
