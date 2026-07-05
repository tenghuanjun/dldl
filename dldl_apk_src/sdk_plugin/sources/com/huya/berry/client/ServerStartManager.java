package com.huya.berry.client;

import android.os.Bundle;
import com.duowan.auk.Ark;
import com.duowan.auk.ArkValue;
import com.duowan.live.login.api.ILoginService;
import com.duowan.live.one.module.uploadLog.FeedBackConstants;
import com.duowan.live.one.module.uploadLog.FeedBackModule;
import com.huya.berry.LoginSdkService;
import com.huya.berry.endlive.EndLiveService;
import com.huya.berry.endlive.api.IEndLiveService;
import com.huya.berry.endlive.api.ISdkPlayerService;
import com.huya.berry.forcelive.ForceLiveService;
import com.huya.berry.forcelive.api.IForceLiveService;
import com.huya.berry.gamesdk.module.CommonService;
import com.huya.berry.gamesdk.module.ICommonService;
import com.huya.berry.modifytitle.ModifyTitleService;
import com.huya.berry.modifytitle.api.IModifyTitleService;
import com.huya.berry.module.HysignalPushModule;
import com.huya.berry.module.live.ISdkLiveService;
import com.huya.berry.module.live.SdkLiveService;
import com.huya.berry.sdkcamera.SdkCameraService;
import com.huya.berry.sdkcamera.api.ISdkCameraService;
import com.huya.berry.sdklive.LiveService;
import com.huya.berry.sdklive.api.ILiveService;
import com.huya.berry.sdklivelist.SdkLiveListService;
import com.huya.berry.sdklivelist.api.ISdkLiveListService;
import com.huya.berry.sdkplayer.SdkPlayerService;
import com.huya.component.login.api.ILoginModule;
import com.huya.component.login.module.LoginModule;
import com.huya.component.user.api.IUserService;
import com.huya.component.user.module.UserService;
import com.huya.live.service.ServiceCenter;
import com.huya.live.service.ServiceHelper;
import com.huya.mtp.utils.ResourceUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ServerStartManager {
    public void startModules() {
        Bundle bundle = new Bundle();
        bundle.putString(FeedBackConstants.KEY_FB_APPID, ResourceUtils.getMetaValue(ArkValue.gContext, "FB_APPID", ""));
        Ark.startModule(FeedBackModule.class, bundle);
        Ark.startModule(HysignalPushModule.class);
        ServiceHelper.createService(ILoginService.class, LoginSdkService.class);
        ServiceHelper.createService(ILoginModule.class, LoginModule.class);
        ServiceHelper.createService(IUserService.class, UserService.class);
        ServiceHelper.createService(ISdkCameraService.class, SdkCameraService.class);
        ServiceHelper.createService(ISdkLiveListService.class, SdkLiveListService.class);
        ServiceHelper.createService(IModifyTitleService.class, ModifyTitleService.class);
        ServiceHelper.createService(IEndLiveService.class, EndLiveService.class);
        ServiceHelper.createService(ISdkPlayerService.class, SdkPlayerService.class);
        ServiceHelper.createService(ICommonService.class, CommonService.class);
        ServiceHelper.createService(ISdkLiveService.class, SdkLiveService.class);
        ServiceHelper.createService(ILiveService.class, LiveService.class);
        ServiceHelper.createService(IForceLiveService.class, ForceLiveService.class);
        ServiceCenter.instance().getService(ILoginModule.class);
        ISdkPlayerService iSdkPlayerService = (ISdkPlayerService) ServiceCenter.instance().getService(ISdkPlayerService.class);
        if (iSdkPlayerService != null) {
            iSdkPlayerService.init();
        }
    }
}
