package com.huya.mtp.hyns.hysignal;

import com.huya.hal.Hal;
import com.huya.hysignal.wrapper.RegistResultInfo;
import com.huya.hysignal.wrapper.business.RegisterBiz;
import com.huya.hysignal.wrapper.listener.RegisterLiveGroupListener;
import com.huya.hysignal.wrapper.listener.RegisterPushMsgListener;
import com.huya.hysignal.wrapper.listener.UnRegisterPushMsgListener;
import com.huya.mtp.hyns.api.NSRegisterApi;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HyRegisterImpl implements NSRegisterApi {
    private RegisterBiz mRegisterBiz = Hal.getRegisterBiz();

    @Override // com.huya.mtp.hyns.api.NSRegisterApi
    public void registerGroup(ArrayList<String> arrayList, final NSRegisterApi.RegisterPushMsgListener registerPushMsgListener) {
        this.mRegisterBiz.registerGroup(arrayList, new RegisterPushMsgListener() { // from class: com.huya.mtp.hyns.hysignal.HyRegisterImpl.1
            @Override // com.huya.hysignal.wrapper.listener.RegisterPushMsgListener
            public void onRegisterSucceed(RegistResultInfo registResultInfo) {
                NSRegisterApi.RegisterPushMsgListener registerPushMsgListener2 = registerPushMsgListener;
                if (registerPushMsgListener2 == null) {
                    return;
                }
                if (registResultInfo != null) {
                    registerPushMsgListener.onRegisterSucceed(new NSRegisterApi.RegistResultInfo(registResultInfo.getGroupId(), registResultInfo.getStatus()));
                } else {
                    registerPushMsgListener2.onRegisterSucceed(null);
                }
            }

            @Override // com.huya.hysignal.wrapper.listener.RegisterPushMsgListener
            public void onRegisterFailed(RegistResultInfo registResultInfo) {
                if (registResultInfo != null) {
                    registerPushMsgListener.onRegisterFailed(new NSRegisterApi.RegistResultInfo(registResultInfo.getGroupId(), registResultInfo.getStatus()));
                } else {
                    registerPushMsgListener.onRegisterFailed(null);
                }
            }
        });
    }

    @Override // com.huya.mtp.hyns.api.NSRegisterApi
    public void registerLiveGroup(long j, String str, final NSRegisterApi.JoinChannelListener joinChannelListener) {
        this.mRegisterBiz.registerLiveGroup(j, str, new RegisterLiveGroupListener() { // from class: com.huya.mtp.hyns.hysignal.HyRegisterImpl.2
            @Override // com.huya.hysignal.wrapper.listener.RegisterLiveGroupListener
            public void onJoinSucceed() {
                NSRegisterApi.JoinChannelListener joinChannelListener2 = joinChannelListener;
                if (joinChannelListener2 != null) {
                    joinChannelListener2.onJoinSucceed();
                }
            }

            @Override // com.huya.hysignal.wrapper.listener.RegisterLiveGroupListener
            public void onJoinFailed() {
                NSRegisterApi.JoinChannelListener joinChannelListener2 = joinChannelListener;
                if (joinChannelListener2 != null) {
                    joinChannelListener2.onJoinFailed();
                }
            }

            @Override // com.huya.hysignal.wrapper.listener.RegisterLiveGroupListener
            public void onJoinPasswordSucceed() {
                NSRegisterApi.JoinChannelListener joinChannelListener2 = joinChannelListener;
                if (joinChannelListener2 != null) {
                    joinChannelListener2.onJoinPasswordSucceed();
                }
            }

            @Override // com.huya.hysignal.wrapper.listener.RegisterLiveGroupListener
            public void onJoinPasswordFailed() {
                NSRegisterApi.JoinChannelListener joinChannelListener2 = joinChannelListener;
                if (joinChannelListener2 != null) {
                    joinChannelListener2.onJoinPasswordFailed();
                }
            }
        });
    }

    @Override // com.huya.mtp.hyns.api.NSRegisterApi
    public void unRegisterGroup(ArrayList<String> arrayList, NSRegisterApi.UnRegisterPushMsgListener unRegisterPushMsgListener) {
        this.mRegisterBiz.unRegisterGroup(arrayList, getUnRegisterPushMsgListener(unRegisterPushMsgListener));
    }

    @Override // com.huya.mtp.hyns.api.NSRegisterApi
    public void unRegisterLiveGroup(long j, NSRegisterApi.UnRegisterPushMsgListener unRegisterPushMsgListener) {
        this.mRegisterBiz.unRegisterLiveGroup(j, getUnRegisterPushMsgListener(unRegisterPushMsgListener));
    }

    @Override // com.huya.mtp.hyns.api.NSRegisterApi
    public void reRegisterGroupsIfNeed() {
        this.mRegisterBiz.reRegisterGroupsIfNeed();
    }

    private UnRegisterPushMsgListener getUnRegisterPushMsgListener(final NSRegisterApi.UnRegisterPushMsgListener unRegisterPushMsgListener) {
        return new UnRegisterPushMsgListener() { // from class: com.huya.mtp.hyns.hysignal.HyRegisterImpl.3
            @Override // com.huya.hysignal.wrapper.listener.UnRegisterPushMsgListener
            public void onUnRegisterSucceed(RegistResultInfo registResultInfo) {
                NSRegisterApi.UnRegisterPushMsgListener unRegisterPushMsgListener2 = unRegisterPushMsgListener;
                if (unRegisterPushMsgListener2 != null) {
                    if (registResultInfo != null) {
                        unRegisterPushMsgListener2.onUnRegisterSucceed(new NSRegisterApi.RegistResultInfo(registResultInfo.getGroupId(), registResultInfo.getStatus()));
                    } else {
                        unRegisterPushMsgListener2.onUnRegisterSucceed(null);
                    }
                }
            }

            @Override // com.huya.hysignal.wrapper.listener.UnRegisterPushMsgListener
            public void onUnRegisterFailed(RegistResultInfo registResultInfo) {
                NSRegisterApi.UnRegisterPushMsgListener unRegisterPushMsgListener2 = unRegisterPushMsgListener;
                if (unRegisterPushMsgListener2 != null) {
                    if (registResultInfo != null) {
                        unRegisterPushMsgListener2.onUnRegisterFailed(new NSRegisterApi.RegistResultInfo(registResultInfo.getGroupId(), registResultInfo.getStatus()));
                    } else {
                        unRegisterPushMsgListener2.onUnRegisterFailed(null);
                    }
                }
            }
        };
    }
}
