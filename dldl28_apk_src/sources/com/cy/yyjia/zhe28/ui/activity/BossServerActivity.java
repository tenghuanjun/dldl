package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.QuickDialog;
import com.cy.yyjia.zhe28.databinding.ActivityBossServerBinding;
import com.cy.yyjia.zhe28.domain.BossServerBean;
import com.cy.yyjia.zhe28.util.NetUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: BossServerActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/BossServerActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityBossServerBinding;", "Landroid/view/View$OnClickListener;", "()V", "getData", "", "init", "onClick", "v", "Landroid/view/View;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class BossServerActivity extends BaseActivity<ActivityBossServerBinding> implements View.OnClickListener {
    public static final int $stable = 0;

    public BossServerActivity() {
        super(R.layout.activity_boss_server, 1);
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().rv.setAdapter(new BaseAdapter(R.layout.item_boss_server_game, null, 2, null));
        getData();
    }

    public final void getData() {
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new BossServerActivity$getData$$inlined$get$1("topic/bossTopic", MapsKt.emptyMap(), null, this, this), 3, null);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (v.getTag() != null) {
            List listMutableListOf = CollectionsKt.mutableListOf(Integer.valueOf(R.mipmap.ic_boss_server_p1), Integer.valueOf(R.mipmap.ic_boss_server_p2), Integer.valueOf(R.mipmap.ic_boss_server_p3), Integer.valueOf(R.mipmap.ic_boss_server_p4), Integer.valueOf(R.mipmap.ic_boss_server_p5), Integer.valueOf(R.mipmap.ic_boss_server_p6));
            int i = Integer.parseInt(v.getTag().toString()) - 1;
            QuickDialog quickDialog = new QuickDialog(this, R.layout.dialog_boss_server);
            BossServerBean data = getMBinding().getData();
            Intrinsics.checkNotNull(data);
            quickDialog.setData(data.getPrivilegeList().get(i)).setImageDrawable(R.id.iv, ((Number) listMutableListOf.get(i)).intValue()).show();
            return;
        }
        startActivity(BossServerGameActivity.class);
    }
}
