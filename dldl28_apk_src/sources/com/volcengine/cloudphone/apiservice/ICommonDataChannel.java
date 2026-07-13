package com.volcengine.cloudphone.apiservice;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface ICommonDataChannel {

    public interface IMessageListener {
        void onLoadPodMessage(String str);
    }

    void addMessageListener(IMessageListener iMessageListener);

    List<IMessageListener> getMessageListeners();

    void removeAllListener();

    void removeListener(IMessageListener iMessageListener);

    void sendMessage(String str);

    void sendMessage(String str, String str2);
}
