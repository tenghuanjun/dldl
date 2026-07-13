package com.cy.yyjia.zhe28.ui.activity;

import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityRvBinding;
import com.cy.yyjia.zhe28.databinding.ItemPermissionListBinding;
import com.cy.yyjia.zhe28.domain.PermissionListBean;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PermissionListActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0016J\u0006\u0010\u000e\u001a\u00020\rJ\u001c\u0010\u000f\u001a\u00020\u00062\b\b\u0001\u0010\u0010\u001a\u00020\u00112\b\b\u0001\u0010\u0012\u001a\u00020\u0011H\u0002R&\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0013"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/PermissionListActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityRvBinding;", "()V", "listAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/PermissionListBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemPermissionListBinding;", "getListAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "setListAdapter", "(Lcom/cy/yyjia/zhe28/base/BaseAdapter;)V", "init", "", "initData", "newPermission", "name", "", SocialConstants.PARAM_APP_DESC, "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class PermissionListActivity extends BaseActivity<ActivityRvBinding> {
    public static final int $stable = 8;
    private BaseAdapter<PermissionListBean, ItemPermissionListBinding> listAdapter;

    public PermissionListActivity() {
        super(R.layout.activity_rv, 0, 2, null);
        this.listAdapter = new BaseAdapter<>(R.layout.item_permission_list, null, 2, null);
    }

    public final BaseAdapter<PermissionListBean, ItemPermissionListBinding> getListAdapter() {
        return this.listAdapter;
    }

    public final void setListAdapter(BaseAdapter<PermissionListBean, ItemPermissionListBinding> baseAdapter) {
        Intrinsics.checkNotNullParameter(baseAdapter, "<set-?>");
        this.listAdapter = baseAdapter;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().navigation.setTitle(R.string.setting_text5);
        getMBinding().rv.setAdapter(this.listAdapter);
        initData();
    }

    public final void initData() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(newPermission(R.string.setting_text12, R.string.setting_text6));
        arrayList.add(newPermission(R.string.setting_text13, R.string.setting_text7));
        arrayList.add(newPermission(R.string.setting_text14, R.string.setting_text8));
        arrayList.add(newPermission(R.string.setting_text15, R.string.setting_text9));
        arrayList.add(newPermission(R.string.setting_text16, R.string.setting_text10));
        arrayList.add(newPermission(R.string.setting_text17, R.string.setting_text11));
        arrayList.add(newPermission(R.string.setting_text18, R.string.setting_text19));
        this.listAdapter.setNewInstance(arrayList);
    }

    private final PermissionListBean newPermission(int name, int desc) {
        String string = getString(name);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = getString(desc);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        return new PermissionListBean(string, string2);
    }
}
