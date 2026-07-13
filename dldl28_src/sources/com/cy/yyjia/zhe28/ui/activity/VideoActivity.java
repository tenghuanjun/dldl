package com.cy.yyjia.zhe28.ui.activity;

import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityVideoBinding;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import kotlin.Metadata;

/* JADX INFO: compiled from: VideoActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0014J\b\u0010\u0007\u001a\u00020\u0005H\u0014¨\u0006\b"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/VideoActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityVideoBinding;", "()V", "init", "", "onDestroy", "onPause", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class VideoActivity extends BaseActivity<ActivityVideoBinding> {
    public static final int $stable = 0;

    public VideoActivity() {
        super(R.layout.activity_video, 0, 2, null);
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().navigation.setTitle(getIntent().getStringExtra("title"));
        getMBinding().setData(getIntent().getStringExtra("videoUrl"));
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }
}
