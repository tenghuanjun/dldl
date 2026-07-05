package com.sqwan.liveshow;

import com.sqwan.liveshow.common.ILiveshowEventWrapper;
import com.youme.voiceengine.MemberChange;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveshowEventWrapperDispatcher {
    public List<ILiveshowEventWrapper> iLiveshowEventWrappers = new CopyOnWriteArrayList();

    public void register(ILiveshowEventWrapper iLiveshowEventWrapper) {
        if (iLiveshowEventWrapper == null || this.iLiveshowEventWrappers.contains(iLiveshowEventWrapper)) {
            return;
        }
        this.iLiveshowEventWrappers.add(iLiveshowEventWrapper);
    }

    public void unregister(ILiveshowEventWrapper iLiveshowEventWrapper) {
        if (iLiveshowEventWrapper != null) {
            this.iLiveshowEventWrappers.remove(iLiveshowEventWrapper);
        }
    }

    public void release() {
        this.iLiveshowEventWrappers.clear();
    }

    public void dispatchInit(boolean z) {
        Iterator<ILiveshowEventWrapper> it = this.iLiveshowEventWrappers.iterator();
        while (it.hasNext()) {
            it.next().initCallback(z);
        }
    }

    public void dispatchJoinRoom(boolean z) {
        Iterator<ILiveshowEventWrapper> it = this.iLiveshowEventWrappers.iterator();
        while (it.hasNext()) {
            it.next().joinRoomCallback(z);
        }
    }

    public void dispatchLeaveRoom(boolean z) {
        Iterator<ILiveshowEventWrapper> it = this.iLiveshowEventWrappers.iterator();
        while (it.hasNext()) {
            it.next().leaveRoomCallback(z);
        }
    }

    public void dispatchChannelChange(boolean z) {
        Iterator<ILiveshowEventWrapper> it = this.iLiveshowEventWrappers.iterator();
        while (it.hasNext()) {
            it.next().channelChange(z);
        }
    }

    public void dispatcheOthersMicChange(boolean z, String str) {
        Iterator<ILiveshowEventWrapper> it = this.iLiveshowEventWrappers.iterator();
        while (it.hasNext()) {
            it.next().othersMicChange(z, str);
        }
    }

    public void dispatchMemberChange(String str, MemberChange[] memberChangeArr, boolean z) {
        Iterator<ILiveshowEventWrapper> it = this.iLiveshowEventWrappers.iterator();
        while (it.hasNext()) {
            it.next().onMemberChange(str, memberChangeArr, z);
        }
    }

    public void dispatchMemberChange(int i) {
        Iterator<ILiveshowEventWrapper> it = this.iLiveshowEventWrappers.iterator();
        while (it.hasNext()) {
            it.next().onMemberChange(i);
        }
    }

    public void dispatchRepeatClickLiveshowIcon() {
        Iterator<ILiveshowEventWrapper> it = this.iLiveshowEventWrappers.iterator();
        while (it.hasNext()) {
            it.next().onRepeatClickLiveshowIcon();
        }
    }
}
