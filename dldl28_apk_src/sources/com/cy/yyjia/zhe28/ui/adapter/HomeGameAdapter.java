package com.cy.yyjia.zhe28.ui.adapter;

import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseProvider;
import com.cy.yyjia.zhe28.databinding.ItemHomeNewBinding;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.donkingliang.imageselector.utils.ImageSelector;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HomeGameAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\fJ\u001e\u0010\u000e\u001a\u00020\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0006H\u0014R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/adapter/HomeGameAdapter;", "Lcom/chad/library/adapter/base/BaseProviderMultiAdapter;", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "Lcom/chad/library/adapter/base/module/LoadMoreModule;", "()V", "type", "", "getType", "()I", "setType", "(I)V", "formatData", "", "list", "getItemType", "data", "", ImageSelector.POSITION, "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class HomeGameAdapter extends BaseProviderMultiAdapter<GameBean> implements LoadMoreModule {
    public static final int $stable = 8;
    private int type;

    @Override // com.chad.library.adapter.base.module.LoadMoreModule
    public /* synthetic */ BaseLoadMoreModule addLoadMoreModule(BaseQuickAdapter baseQuickAdapter) {
        return LoadMoreModule.CC.$default$addLoadMoreModule(this, baseQuickAdapter);
    }

    public HomeGameAdapter() {
        super(null, 1, null);
        addItemProvider(new BaseProvider(1, R.layout.item_home_game_head));
        addItemProvider(new BaseProvider(0, R.layout.item_home_new, new Function4<BaseViewHolder, ItemHomeNewBinding, Integer, GameBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.adapter.HomeGameAdapter.1
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, ItemHomeNewBinding itemHomeNewBinding, Integer num, GameBean gameBean) {
                invoke(baseViewHolder, itemHomeNewBinding, num.intValue(), gameBean);
                return Unit.INSTANCE;
            }

            public final void invoke(BaseViewHolder h, ItemHomeNewBinding b, int i, GameBean gameBean) {
                Intrinsics.checkNotNullParameter(h, "h");
                Intrinsics.checkNotNullParameter(b, "b");
                b.setOrder(HomeGameAdapter.this.getType() != 0);
            }
        }));
    }

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }

    @Override // com.chad.library.adapter.base.BaseProviderMultiAdapter
    protected int getItemType(List<? extends GameBean> data, int position) {
        Intrinsics.checkNotNullParameter(data, "data");
        Integer groupType = data.get(position).getGroupType();
        Intrinsics.checkNotNull(groupType);
        return groupType.intValue();
    }

    public final List<GameBean> formatData(List<GameBean> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            GameBean gameBean = list.get(i);
            if (i == 0 && getData().size() > 0 && !Intrinsics.areEqual(list.get(0).getStartTimeStr(), getData().get(getData().size() - 1).getStartTimeStr())) {
                GameBean gameBean2 = new GameBean(gameBean.getStartTimeStr());
                gameBean2.setGroupType(1);
                arrayList.add(gameBean2);
            }
            if (i != 0 && !Intrinsics.areEqual(list.get(i).getStartTimeStr(), list.get(i - 1).getStartTimeStr())) {
                GameBean gameBean3 = new GameBean(gameBean.getStartTimeStr());
                gameBean3.setGroupType(1);
                arrayList.add(gameBean3);
            }
            arrayList.add(gameBean);
        }
        return arrayList;
    }
}
