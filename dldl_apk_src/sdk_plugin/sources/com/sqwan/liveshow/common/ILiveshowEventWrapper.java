package com.sqwan.liveshow.common;

import com.youme.voiceengine.MemberChange;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface ILiveshowEventWrapper {
    void channelChange(boolean z);

    void initCallback(boolean z);

    void joinRoomCallback(boolean z);

    void leaveRoomCallback(boolean z);

    void onMemberChange(int i);

    void onMemberChange(String str, MemberChange[] memberChangeArr, boolean z);

    void onRepeatClickLiveshowIcon();

    void othersMicChange(boolean z, String str);
}
