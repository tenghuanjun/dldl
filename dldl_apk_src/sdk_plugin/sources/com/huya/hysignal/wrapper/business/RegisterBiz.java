package com.huya.hysignal.wrapper.business;

import com.huya.hysignal.wrapper.listener.RegisterLiveGroupListener;
import com.huya.hysignal.wrapper.listener.RegisterPushMsgListener;
import com.huya.hysignal.wrapper.listener.UnRegisterPushMsgListener;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface RegisterBiz {
    void reRegisterGroupsIfNeed();

    void registerGroup(ArrayList<String> arrayList, RegisterPushMsgListener registerPushMsgListener);

    @Deprecated
    void registerLiveGroup(long j, String str, RegisterLiveGroupListener registerLiveGroupListener);

    void registerPrivateGroup(String str, String str2);

    void unRegisterGroup(ArrayList<String> arrayList, UnRegisterPushMsgListener unRegisterPushMsgListener);

    void unRegisterLiveGroup(long j, UnRegisterPushMsgListener unRegisterPushMsgListener);
}
