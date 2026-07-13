package com.cy.yyjia.zhe28.ui.dialog;

import androidx.fragment.app.FragmentActivity;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.databinding.DialogTopicRecordBinding;
import com.cy.yyjia.zhe28.databinding.ItemTopicRecordBinding;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.TopicBroadcastBean;
import com.cy.yyjia.zhe28.domain.TopicDetailBean;
import com.cy.yyjia.zhe28.util.Repository;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TopicRecordDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0018R'\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0019"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/TopicRecordDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogTopicRecordBinding;", "activity", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/TopicBroadcastBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemTopicRecordBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "page", "", "getPage", "()I", "setPage", "(I)V", "getData", "", "setData", "data", "Lcom/cy/yyjia/zhe28/domain/TopicDetailBean;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class TopicRecordDialog extends BaseDataBindingDialog<DialogTopicRecordBinding, TopicRecordDialog> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;
    private int page;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TopicRecordDialog(FragmentActivity activity) {
        super(activity, R.layout.dialog_topic_record);
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.page = 1;
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<TopicBroadcastBean, ItemTopicRecordBinding>>() { // from class: com.cy.yyjia.zhe28.ui.dialog.TopicRecordDialog$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<TopicBroadcastBean, ItemTopicRecordBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_topic_record, null, 2, null);
            }
        });
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    public final BaseAdapter<TopicBroadcastBean, ItemTopicRecordBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    public final TopicRecordDialog setData(TopicDetailBean data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ((DialogTopicRecordBinding) this.mBinding).setData(data);
        ((DialogTopicRecordBinding) this.mBinding).rv.setAdapter(getAdapter());
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.TopicRecordDialog$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                TopicRecordDialog.setData$lambda$0(this.f$0);
            }
        });
        getData();
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setData$lambda$0(TopicRecordDialog this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    public final void getData() {
        Repository repository = Repository.INSTANCE;
        TopicDetailBean data = ((DialogTopicRecordBinding) this.mBinding).getData();
        Intrinsics.checkNotNull(data);
        repository.getTopicRecord(data.getId(), this.page, new Function1<PageBean<TopicBroadcastBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.TopicRecordDialog.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<TopicBroadcastBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<TopicBroadcastBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (TopicRecordDialog.this.getPage() == 1) {
                    TopicRecordDialog.this.getAdapter().setNewInstance(it.getList());
                } else {
                    TopicRecordDialog.this.getAdapter().addData(it.getList());
                }
                TopicRecordDialog topicRecordDialog = TopicRecordDialog.this;
                topicRecordDialog.setPage(topicRecordDialog.getPage() + 1);
                topicRecordDialog.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(TopicRecordDialog.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    TopicRecordDialog.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.TopicRecordDialog.getData.2
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
                TopicRecordDialog.this.getAdapter().getLoadMoreModule().loadMoreFail();
            }
        });
    }
}
