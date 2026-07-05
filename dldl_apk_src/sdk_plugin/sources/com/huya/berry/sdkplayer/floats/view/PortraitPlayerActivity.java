package com.huya.berry.sdkplayer.floats.view;

import android.os.Bundle;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.sqwan.liveshow.huya.SqR;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PortraitPlayerActivity extends PlayerActivity {
    @Override // com.huya.berry.sdkplayer.floats.view.PlayerActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.layoutId = ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_activity_player_portrait);
        SdkProperties.isVideoLandScape.set(false);
        super.onCreate(bundle);
    }
}
