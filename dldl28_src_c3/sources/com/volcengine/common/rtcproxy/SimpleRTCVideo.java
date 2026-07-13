package com.volcengine.common.rtcproxy;

import android.view.View;
import com.volcengine.cloudcore.common.mode.StreamType;
import java.nio.ByteBuffer;
import org.json.JSONObject;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface SimpleRTCVideo {

    public interface SimpleRoomEventHandler {
        void onLeaveRoom();

        void onRemoteStreamStats(int i, int i2, int i3, int i4, int i5, int i6, int i7, float f, int i8, String str, int i9, int i10, long j);

        void onRoomBinaryMessageReceived(String str, ByteBuffer byteBuffer);

        void onRoomError(int i);

        void onRoomMessageReceived(String str, String str2);

        void onRoomMessageSendResult(long j, int i);

        void onRoomStateChange(String str, String str2, int i, String str3);

        void onRoomStats(int i, int i2);

        void onRoomWarning(int i);

        void onStreamSubscribed(int i, String str, boolean z, boolean z2, boolean z3, int i2);

        void onUserBinaryMessageReceived(String str, ByteBuffer byteBuffer);

        void onUserJoined(String str, int i);

        void onUserLeave(String str, int i);

        void onUserMessageReceived(String str, String str2);

        void onUserMessageSendResult(long j, int i);

        void onUserPublishScreen(String str, int i);

        void onUserPublishStream(String str, int i);

        void onUserUnPublishScreen(String str, int i, int i2);

        void onUserUnPublishStream(String str, int i, int i2);
    }

    public interface SimpleRtcVideoHandler {
        void onAudioPlaybackDeviceChanged(int i);

        void onConnectionStateChanged(int i, int i2);

        void onFirstRemoteAudioFrame(String str, String str2);

        void onFirstRemoteVideoFrameDecoded(String str, String str2, int i, int i2);

        void onFirstRemoteVideoFrameRendered(String str, String str2, int i, int i2);

        void onNetworkTypeChanged(int i);

        void onRemoteVideoSizeChanged(String str, String str2, int i, int i2, int i3);

        void onSEIMessageReceived(String str, String str2, ByteBuffer byteBuffer);

        void onVideoFramePlayStateChanged(String str, String str2, int i);
    }

    void closeEngine();

    String getSdkVersion();

    void init(String str, String str2, String str3, String str4, String str5, String str6, String str7, JSONObject jSONObject, boolean z, String str8, StreamType streamType, SimpleRtcVideoHandler simpleRtcVideoHandler, SimpleRoomEventHandler simpleRoomEventHandler);

    void pauseAll(int i);

    void resumeAll(int i);

    int sendBinaryMessage(String str, byte[] bArr, int i);

    int sendMessage(String str, String str2, int i);

    void setRemoteAudioPlaybackVolume(String str, int i);

    void setupVideoView(String str, View view);

    void setupVideoView(String str, View view, int i);

    int start();

    void subscribeStream(String str, int i);

    void unsubscribeStream(String str, int i);

    void updateVideoRenderMode(String str, String str2, int i, int i2);
}
