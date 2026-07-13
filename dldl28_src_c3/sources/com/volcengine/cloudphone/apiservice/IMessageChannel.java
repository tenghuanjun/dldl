package com.volcengine.cloudphone.apiservice;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface IMessageChannel {

    public interface IChannelBinaryMessage {
        String getDestChannelUid();

        String getMid();

        byte[] getPayload();

        @Deprecated
        String getSendUserId();

        String getSrcChannelUid();

        long getTimeMillis();
    }

    public interface IChannelMessage {
        String getDestChannelUid();

        String getMid();

        String getPayload();

        @Deprecated
        String getSendUserId();

        String getSrcChannelUid();

        long getTimeMillis();
    }

    public interface IMessageReceiver {
        void onError(int i, String str);

        void onReceiveBinaryMessage(IChannelBinaryMessage iChannelBinaryMessage);

        void onReceiveMessage(IChannelMessage iChannelMessage);

        void onRemoteOffline(String str);

        void onRemoteOnline(String str);

        void onSentResult(boolean z, String str);

        @Deprecated
        void ready();
    }

    IChannelBinaryMessage sendBinaryMessage(byte[] bArr, long j);

    IChannelBinaryMessage sendBinaryMessage(byte[] bArr, long j, String str);

    IChannelBinaryMessage sendBinaryMessage(byte[] bArr, boolean z);

    IChannelBinaryMessage sendBinaryMessage(byte[] bArr, boolean z, String str);

    IChannelMessage sendMessage(String str, long j);

    IChannelMessage sendMessage(String str, long j, String str2);

    IChannelMessage sendMessage(String str, boolean z);

    IChannelMessage sendMessage(String str, boolean z, String str2);

    void setMessageListener(IMessageReceiver iMessageReceiver);
}
