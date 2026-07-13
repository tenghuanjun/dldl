package com.cy.yyjia.zhe28.ui.activity;

import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityDevelopBinding;
import kotlin.Metadata;

/* JADX INFO: compiled from: DevelopActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/DevelopActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityDevelopBinding;", "()V", "init", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DevelopActivity extends BaseActivity<ActivityDevelopBinding> {
    public static final int $stable = 0;

    public DevelopActivity() {
        super(R.layout.activity_develop, 0, 2, null);
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().setTitle(getIntent().getStringExtra("title"));
    }
}
