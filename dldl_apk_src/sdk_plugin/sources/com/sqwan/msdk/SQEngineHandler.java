package com.sqwan.msdk;

import android.content.Context;
import com.sqwan.common.mod.ModManager;
import com.sqwan.common.mod.account.IAccountMod;
import com.sqwan.common.mod.advertise.IAdvertiseMod;
import com.sqwan.common.mod.comment.ICommentMod;
import com.sqwan.common.mod.config.IConfigMod;
import com.sqwan.common.mod.download.IDownloadMod;
import com.sqwan.common.mod.liveshow.IAudioLiveshowManager;
import com.sqwan.common.mod.liveshow.IAudioLiveshowTrackManager;
import com.sqwan.common.mod.liveshow.IHyLiveshowManager;
import com.sqwan.common.mod.liveshow.IHyLiveshowTrackManager;
import com.sqwan.common.mod.liveshow.LiveRadioEngine;
import com.sqwan.common.mod.liveshow.LiveshowEngine;
import com.sqwan.common.mod.order.IOrderMod;
import com.sqwan.common.mod.plugin.IPluginMod;
import com.sqwan.common.mod.push.IPushMod;
import com.sqwan.common.mod.share.IShareMod;
import com.sqwan.common.mod.track.ITrackMod;
import com.sqwan.common.mod.track.ITrackMod2;
import com.sqwan.common.util.PermissionHelper;
import com.sqwan.engine.CoreEngineHandler;
import com.sqwan.msdk.api.MultiSDKUtils;
import com.sy37sdk.account.activebefore.ActiveBeforeManager;
import com.sy37sdk.account.policy.AuthHandler;
import com.sy37sdk.account.uagree.UAgreeManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQEngineHandler {
    public static final String CONFIG_FILE = "multiconfig";
    private static final SQEngineHandler ourInstance = new SQEngineHandler();

    private boolean isLiveshowTypeAudio() {
        return true;
    }

    private boolean isLiveshowTypeHy() {
        return true;
    }

    public static SQEngineHandler getInstance() {
        return ourInstance;
    }

    private SQEngineHandler() {
    }

    public void init(Context context) {
        initMod(context);
        LiveshowEngine.getInstance().init(context);
        LiveRadioEngine.getInstance().init(context);
        UAgreeManager.getInstance().init(context);
        AuthHandler.getInstance().init(context);
        PermissionHelper.getInstance().init(context);
        CoreEngineHandler.getInstance().init(context);
        initConfigsBean(context);
        ActiveBeforeManager.getInstance().init(context);
    }

    private void initMod(Context context) {
        ModManager modManager = ModManager.getInstance();
        modManager.clearMod();
        modManager.putMod(IOrderMod.class, new ModManager.ModConfig("com.sy37sdk.order.OrderModImpl", true));
        modManager.putMod(IAccountMod.class, new ModManager.ModConfig("com.sy37sdk.account.AccountModImpl", true));
        modManager.putMod(IPluginMod.class, new ModManager.ModConfig("com.sy37sdk.plugin.PluginModImpl", true));
        modManager.putMod(IShareMod.class, new ModManager.ModConfig("com.sy37sdk.share.ShareModImpl", true));
        modManager.putMod(IDownloadMod.class, new ModManager.ModConfig("com.sqwan.afinal.download.DownloadModImpl", true));
        modManager.putMod(ITrackMod.class, new ModManager.ModConfig("com.sq.track.TrackModImpl", true));
        modManager.putMod(ITrackMod2.class, new ModManager.ModConfig("com.sq.track.TrackModImpl2", true));
        if (isLiveshowTypeAudio()) {
            modManager.putMod(IAudioLiveshowManager.class, new ModManager.ModConfig("com.sqwan.liveshow.LiveshowManager"));
            modManager.putMod(IAudioLiveshowTrackManager.class, new ModManager.ModConfig("com.sqwan.liveshow.trackaction.LiveshowTrackManager"));
        }
        if (isLiveshowTypeHy()) {
            modManager.putMod(IHyLiveshowManager.class, new ModManager.ModConfig("com.sqwan.liveshow.huya.engine.LiveshowManager"));
            modManager.putMod(IHyLiveshowTrackManager.class, new ModManager.ModConfig("com.sqwan.liveshow.huya.trackaction.LiveshowTrackManager"));
        }
        modManager.putMod(IConfigMod.class, new ModManager.ModConfig("com.sqwan.common.mod.config.ConfigModImpl"));
        modManager.putMod(IPushMod.class, new ModManager.ModConfig("com.sqwan.push.PushModImpl"));
        modManager.putMod(IAdvertiseMod.class, new ModManager.ModConfig("com.sy37sdk.advertise.AdvertiseImpl"));
        modManager.putMod(ICommentMod.class, new ModManager.ModConfig("com.sy37sdk.account.config.CommentModImpl"));
        modManager.loadMod(context);
    }

    private void initConfigsBean(Context context) {
        if (ConfigsBean.configsBean == null) {
            ConfigsBean.configsBean = ConfigsBean.init(MultiSDKUtils.readPropertites(context, "multiconfig"));
        }
    }
}
