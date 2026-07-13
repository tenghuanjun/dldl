package com.cy.yyjia.zhe28.ui.adapter;

import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseProvider;
import com.cy.yyjia.zhe28.domain.NewGameBean;
import com.donkingliang.imageselector.utils.ImageSelector;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScheduleAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0014J\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b¨\u0006\r"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/adapter/ScheduleAdapter;", "Lcom/chad/library/adapter/base/BaseProviderMultiAdapter;", "Lcom/cy/yyjia/zhe28/domain/NewGameBean;", "Lcom/chad/library/adapter/base/module/LoadMoreModule;", "()V", "getItemType", "", "data", "", ImageSelector.POSITION, "groupData", "", "list", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ScheduleAdapter extends BaseProviderMultiAdapter<NewGameBean> implements LoadMoreModule {
    public static final int $stable = 0;

    @Override // com.chad.library.adapter.base.module.LoadMoreModule
    public /* synthetic */ BaseLoadMoreModule addLoadMoreModule(BaseQuickAdapter baseQuickAdapter) {
        return LoadMoreModule.CC.$default$addLoadMoreModule(this, baseQuickAdapter);
    }

    public ScheduleAdapter() {
        super(null, 1, null);
        addItemProvider(new BaseProvider(0, R.layout.item_home_schedule_head));
        addItemProvider(new BaseProvider(1, R.layout.item_home_schedule));
    }

    @Override // com.chad.library.adapter.base.BaseProviderMultiAdapter
    protected int getItemType(List<? extends NewGameBean> data, int position) {
        Intrinsics.checkNotNullParameter(data, "data");
        return data.get(position).getId() == -1 ? 0 : 1;
    }

    public final List<NewGameBean> groupData(List<NewGameBean> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            NewGameBean newGameBean = list.get(i);
            if (i == 0 && getData().size() > 0 && !Intrinsics.areEqual(list.get(0).getNew_service_date(), getData().get(getData().size() - 1).getNew_service_date())) {
                arrayList.add(new NewGameBean(newGameBean.getNew_service_date()));
            }
            if (i != 0 && !Intrinsics.areEqual(list.get(i).getNew_service_date(), list.get(i - 1).getNew_service_date())) {
                arrayList.add(new NewGameBean(newGameBean.getNew_service_date()));
            }
            arrayList.add(newGameBean);
        }
        return arrayList;
    }
}
