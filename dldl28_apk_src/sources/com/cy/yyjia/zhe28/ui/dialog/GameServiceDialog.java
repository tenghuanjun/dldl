package com.cy.yyjia.zhe28.ui.dialog;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.domain.GameDetailBean;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GameServiceDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0006R\u00020\u00070\u0005¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/GameServiceDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDialog$Builder;", "activity", "Landroid/content/Context;", "mutableList", "", "Lcom/cy/yyjia/zhe28/domain/GameDetailBean$ServiceBean;", "Lcom/cy/yyjia/zhe28/domain/GameDetailBean;", "(Landroid/content/Context;Ljava/util/List;)V", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GameServiceDialog extends BaseDialog.Builder<GameServiceDialog> {
    public static final int $stable = 0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GameServiceDialog(Context activity, List<GameDetailBean.ServiceBean> mutableList) {
        super(activity);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(mutableList, "mutableList");
        setContentView(R.layout.dialog_game_service);
        ((RecyclerView) findViewById(R.id.rv)).setAdapter(new BaseAdapter(R.layout.item_game_service, mutableList));
    }
}
