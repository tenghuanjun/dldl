package com.youme.imsdk.callback;

import com.youme.imsdk.YIMClient;
import com.youme.imsdk.YIMMessage;
import com.youme.imsdk.internal.GeographyLocation;
import com.youme.imsdk.internal.NoticeInfo;
import com.youme.imsdk.internal.SendVoiceMsgInfo;
import com.youme.imsdk.internal.SpeechMessageInfo;
import com.youme.imsdk.internal.UserChatRoom;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class YIMEventCallback {

    public interface AccusationResultCallback {
        void onAccusationResultNotify(int i, String str, int i2);
    }

    public interface AudioMsgEventCallback {
        void onSendAudioMessageStatus(int i, SendVoiceMsgInfo sendVoiceMsgInfo);

        void onStartSendAudioMessage(long j, int i, String str, String str2, int i2);
    }

    public interface AutoDownloadVoiceCallback {
        void onAutoDownload(int i, YIMMessage yIMMessage, String str);
    }

    public interface DownloadByUrlCallback {
        void onDownloadByUrl(int i, String str, String str2, int i2);
    }

    public interface DownloadFileCallback {
        void onDownload(int i, YIMMessage yIMMessage, String str);
    }

    public interface FriendNotifyCallback {
        void onBeAddFriendNotify(String str, String str2);

        void onBeDeleteFriendNotify(String str);

        void onBeRequestAddFriendNotify(String str, String str2);

        void onRequestAddFriendResultNotify(String str, String str2, int i);
    }

    public interface GetLocationCallback {
        void onUpdateLocation(int i, GeographyLocation geographyLocation);
    }

    public interface GetMicStatusCallback {
        void onGetMicrophoneStatus(int i);
    }

    public interface KickOffCallback {
        void onKickOff();
    }

    public interface MessageEventCallback {
        void onGetRecognizeSpeechText(int i, long j, String str);

        void onRecordVolume(float f);

        void onRecvMessage(YIMMessage yIMMessage);

        void onRecvNewMessage(int i, String str);
    }

    public interface NoticeCallback {
        void onCancelNotice(long j, String str);

        void onRecvNotice(NoticeInfo noticeInfo);
    }

    public interface OperationCallback {
        void onFailed(int i);

        void onSuccess();
    }

    public interface ReconnectCallback {
        void onRecvReconnectResult(int i);

        void onStartReconnect();
    }

    public interface ResultCallback<T> {
        void onFailed(int i, T t);

        void onSuccess(T t);
    }

    public interface ShowUploadFileProgressCallback {
        void onShowUploadFileProgress(float f);
    }

    public interface SpeechEventCallback {
        void onStopAudioSpeechStatus(int i, SpeechMessageInfo speechMessageInfo);
    }

    public interface UpdateReadStatusCallback {
        void onRead(String str, int i, long j);
    }

    public interface UserJoinLeaveChannelCallback {
        void joinLeaveNotify(YIMClient.ChannelEventType channelEventType, UserChatRoom userChatRoom);
    }

    public interface UserProfileChangeCallback {
        void onUserInfoChangeNotify(String str);
    }
}
