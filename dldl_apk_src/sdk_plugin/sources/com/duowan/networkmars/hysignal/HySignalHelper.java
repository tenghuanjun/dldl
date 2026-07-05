package com.duowan.networkmars.hysignal;

import com.duowan.auk.util.L;
import com.duowan.networkmars.data.MarsProperties;
import com.huya.mtp.hyns.api.NSRegisterApi;
import com.huya.mtp.utils.FP;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HySignalHelper {
    private static final String TAG = "HySignalHelper";

    public static Set<Long> getCareHistoryMsgUriSet() {
        HashSet hashSet = new HashSet();
        if (!FP.empty(MarsProperties.careHistoryMsgUri.get())) {
            String[] strArrSplit = MarsProperties.careHistoryMsgUri.get().split("\\|");
            for (int i = 0; i < strArrSplit.length; i++) {
                if (!FP.empty(strArrSplit[i])) {
                    try {
                        hashSet.add(Long.valueOf(Long.parseLong(strArrSplit[i])));
                    } catch (Exception unused) {
                    }
                }
            }
        }
        return hashSet;
    }

    public static void closePush() {
        HySignalProxy.getInstance().setPushMessageListenter(null);
    }

    public static void setCareHistoryMsgUriSet() {
        HySignalProxy.getInstance().updateRegisterMsgUriSet(getCareHistoryMsgUriSet());
    }

    public static void updateIpList(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        HySignalSDK.getInstance().updateIpList(arrayList, arrayList2);
    }

    public static void logout() {
        HySignalProxy.getInstance().clearLoginInfo();
    }

    public void setLoginInfo(long j) {
        HySignalProxy.getInstance().setLoginInfo(j);
        HySignalSDK.getInstance().setUid(j);
    }

    public void joinLiveRoom(NSRegisterApi.RegisterPushMsgListener registerPushMsgListener) {
        HySignalProxy.getInstance().registerGroupLive(registerPushMsgListener);
    }

    public void leaveLiveRoom() {
        L.info(TAG, "leaveLiveRoom...");
        HySignalProxy.getInstance().unRegisterGroupLive();
    }

    public void joinLink(long j, long j2, NSRegisterApi.RegisterPushMsgListener registerPushMsgListener) {
        L.info(TAG, "joinLink:,groupId:" + j);
        HySignalProxy.getInstance().registerGroupLink(j, registerPushMsgListener);
    }

    public static void leaveLink(long j) {
        HySignalProxy.getInstance().unRegisterGroupLink(j);
    }

    public static void joinLiveGroups(String str, NSRegisterApi.RegisterPushMsgListener registerPushMsgListener) {
        HySignalProxy.getInstance().registerLiveGroups(str, registerPushMsgListener);
    }

    public static void leaveLiveGroups(String str) {
        HySignalProxy.getInstance().unRegisterLiveGroups(str);
    }
}
