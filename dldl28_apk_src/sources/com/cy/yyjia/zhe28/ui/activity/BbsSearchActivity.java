package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityBbsSearchBinding;
import com.cy.yyjia.zhe28.databinding.ItemBbsSearchBinding;
import com.cy.yyjia.zhe28.domain.BbsBean;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.view.ExpandableTextView;
import java.util.LinkedHashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: BbsSearchActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u001a\u001a\u00020\u001bJ\b\u0010\u001c\u001a\u00020\u001bH\u0016R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\u0004\u0018\u00010\u000b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0010\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\u001d"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/BbsSearchActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityBbsSearchBinding;", "()V", "bbsAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/BbsBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemBbsSearchBinding;", "getBbsAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "bbsId", "", "getBbsId", "()Ljava/lang/String;", "bbsId$delegate", "Lkotlin/Lazy;", "keyword", "getKeyword", "setKeyword", "(Ljava/lang/String;)V", "page", "", "getPage", "()I", "setPage", "(I)V", "getBbs", "", "init", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class BbsSearchActivity extends BaseActivity<ActivityBbsSearchBinding> {
    public static final int $stable = 8;
    private final BaseAdapter<BbsBean, ItemBbsSearchBinding> bbsAdapter;

    /* JADX INFO: renamed from: bbsId$delegate, reason: from kotlin metadata */
    private final Lazy bbsId;
    private String keyword;
    private int page;

    public BbsSearchActivity() {
        super(R.layout.activity_bbs_search, 0, 2, null);
        this.bbsId = LazyKt.lazy(new Function0<String>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsSearchActivity$bbsId$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return this.this$0.getIntent().getStringExtra("bbsId");
            }
        });
        this.page = 1;
        this.bbsAdapter = new BaseAdapter<>(R.layout.item_bbs_search, new Function3<BaseDataBindingHolder<ItemBbsSearchBinding>, Integer, BbsBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsSearchActivity$bbsAdapter$1
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemBbsSearchBinding> baseDataBindingHolder, Integer num, BbsBean bbsBean) {
                invoke(baseDataBindingHolder, num.intValue(), bbsBean);
                return Unit.INSTANCE;
            }

            public final void invoke(BaseDataBindingHolder<ItemBbsSearchBinding> h, int i, BbsBean bbsBean) {
                Intrinsics.checkNotNullParameter(h, "h");
                Intrinsics.checkNotNull(bbsBean);
                BbsSearchActivity bbsSearchActivity = this.this$0;
                BbsSearchActivity bbsSearchActivity2 = bbsSearchActivity;
                String text = bbsSearchActivity.getMBinding().getText();
                Intrinsics.checkNotNull(text);
                h.setText(R.id.tv_title, bbsBean.searchContent(bbsSearchActivity2, text, bbsBean.getTitle()));
                ItemBbsSearchBinding itemBbsSearchBinding = (ItemBbsSearchBinding) h.getDataBinding();
                ExpandableTextView expandableTextView = itemBbsSearchBinding != null ? itemBbsSearchBinding.f462tv : null;
                Intrinsics.checkNotNull(expandableTextView);
                BbsSearchActivity bbsSearchActivity3 = this.this$0;
                BbsSearchActivity bbsSearchActivity4 = bbsSearchActivity3;
                String text2 = bbsSearchActivity3.getMBinding().getText();
                Intrinsics.checkNotNull(text2);
                expandableTextView.setExpandableTextContent(bbsBean.searchContent(bbsSearchActivity4, text2, bbsBean.getMessage()));
            }
        });
        this.keyword = "";
    }

    public final String getBbsId() {
        return (String) this.bbsId.getValue();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    public final BaseAdapter<BbsBean, ItemBbsSearchBinding> getBbsAdapter() {
        return this.bbsAdapter;
    }

    public final String getKeyword() {
        return this.keyword;
    }

    public final void setKeyword(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.keyword = str;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().setText("");
        getMBinding().rv.setAdapter(this.bbsAdapter);
        this.bbsAdapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsSearchActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                BbsSearchActivity.init$lambda$0(this.f$0);
            }
        });
        getMBinding().btn.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsSearchActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BbsSearchActivity.init$lambda$1(this.f$0, view);
            }
        });
        getMBinding().iv.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsSearchActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BbsSearchActivity.init$lambda$2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(BbsSearchActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBbs();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(BbsSearchActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String text = this$0.getMBinding().getText();
        Intrinsics.checkNotNull(text);
        this$0.keyword = text;
        this$0.getBbs();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(BbsSearchActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getMBinding().setText("");
        this$0.bbsAdapter.setNewInstance(null);
    }

    public final void getBbs() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String bbsId = getBbsId();
        if (bbsId == null) {
            bbsId = "";
        }
        linkedHashMap.put("id", bbsId);
        linkedHashMap.put("cate", "");
        linkedHashMap.put("page", String.valueOf(this.page));
        linkedHashMap.put("keyword", this.keyword);
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new BbsSearchActivity$getBbs$$inlined$get$1("bbs/listById", linkedHashMap, null, this, this), 3, null);
    }
}
