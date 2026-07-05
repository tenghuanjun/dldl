package com.youme.im;

import com.youme.im.IMEngine;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class NativeEngine {
    public static native int Accusation(String str, int i, int i2, String str2, String str3);

    public static native int BlackFriend(int i, String str);

    public static native int BlockUser(String str, boolean z);

    public static native int CancleAudioMessage();

    public static native boolean ClearAudioCachePath();

    public static native int ConvertAMRToWav(String str, String str2);

    public static native int DealBeRequestAddFriend(String str, int i);

    public static native int DeleteFriend(String str, int i);

    public static native int DeleteHistoryMessage(int i, long j);

    public static native int DeleteHistoryMessageByID(long j);

    public static native int DeleteHistoryMessageByTarget(String str, int i, long j, int i2);

    public static native int DeleteSpecifiedHistoryMessage(String str, int i, long[] jArr);

    public static native int DownloadAudioFile(long j, String str);

    public static native int DownloadFileByURL(String str, String str2, int i);

    public static native int FindUser(int i, String str);

    public static native String GetAudioCachePath();

    public static native int GetBlockUsers();

    public static native int GetCurrentLocation();

    public static native int GetDistance(String str);

    public static native String GetFilterText(String str, IMEngine.IntegerVal integerVal);

    public static native int GetForbiddenSpeakInfo();

    public static native int GetHistoryContact();

    public static native byte[] GetMessage();

    public static native void GetMicrophoneStatus();

    public static native int GetNearbyObjects(int i, String str, int i2, boolean z);

    public static native int GetNewMessage(String str);

    public static native int GetRoomMemberCount(String str);

    public static native int GetSDKVer();

    public static native int GetUserInfo(String str);

    public static native int GetUserProfileInfo(String str);

    public static native int Init(String str, String str2);

    public static native boolean IsPlaying();

    public static native int JoinChatRoom(String str);

    public static native int LeaveAllChatRooms();

    public static native int LeaveChatRoom(String str);

    public static native int Login(String str, String str2, String str3);

    public static native int Logout();

    public static native int MultiSendTextMessage(String str, String str2);

    public static native void NotifyVolumeChange(int i, int i2);

    public static native void OnPause(boolean z);

    public static native void OnPlayData(byte[] bArr, int i);

    public static native void OnPlayFinish(int i, String str);

    public static native byte[] OnRecordData(byte[] bArr, int i, boolean z);

    public static native void OnRecordFinish(int i, String str, long j, String str2);

    public static native void OnResume();

    public static native void OnUpdateLocation(int i, double d, double d2);

    public static native void PopMessage();

    public static native int QueryFriendRequestList(int i, int i2);

    public static native int QueryFriends(int i, int i2, int i3);

    public static native int QueryHistoryMessage(String str, int i, long j, int i2, int i3);

    public static native int QueryNotice();

    public static native int QueryRoomHistoryMessageFromServer(String str, int i, int i2);

    public static native int QueryUserStatus(String str);

    public static native int RequestAddFriend(String str, String str2);

    public static native int SendAudioMessage(String str, int i, IMEngine.MessageRequestId messageRequestId);

    public static native int SendCustomMessage(String str, int i, byte[] bArr, int i2, IMEngine.MessageRequestId messageRequestId);

    public static native int SendFile(String str, int i, String str2, String str3, int i2, IMEngine.MessageRequestId messageRequestId);

    public static native int SendGift(String str, String str2, int i, int i2, String str3, IMEngine.MessageRequestId messageRequestId);

    public static native int SendMessageReadStatus(String str, int i, long j);

    public static native int SendOnlyAudioMessage(String str, int i, IMEngine.MessageRequestId messageRequestId);

    public static native int SendTextMessage(String str, int i, String str2, String str3, IMEngine.MessageRequestId messageRequestId);

    public static native int SetAddPermission(boolean z, int i);

    public static native int SetAllMessageRead(String str, boolean z);

    public static native int SetDownloadAudioMessageSwitch(boolean z);

    public static native int SetDownloadDir(String str);

    public static native int SetLoginAddress(String str, int i);

    public static native int SetMessageRead(long j, boolean z);

    public static native void SetMode(int i);

    public static native int SetOnlyRecognizeSpeechText(boolean z);

    public static native int SetReceiveMessageSwitch(String str, boolean z);

    public static native int SetRoomHistoryMessageSwitch(String str, boolean z);

    public static native void SetServerZone(int i);

    public static native int SetShortConnectionMode();

    public static native int SetSpeechRecognizeLanguage(int i);

    public static native void SetUpdateInterval(int i);

    public static native int SetUserInfo(String str);

    public static native int SetUserProfileInfo(String str);

    public static native int SetUserProfilePhoto(String str);

    public static native int SetVoiceMsgPlayed(long j, boolean z);

    public static native void SetVolume(float f);

    public static native int StartAudioSpeech(IMEngine.MessageRequestId messageRequestId, boolean z);

    public static native int StartPlayAudio(String str);

    public static native int StopAudioMessage(String str);

    public static native int StopAudioSpeech();

    public static native int StopPlayAudio();

    public static native int SwitchMsgTransType(int i);

    public static native int SwitchUserStatus(String str, int i);

    public static native int TranslateText(IMEngine.IntegerVal integerVal, String str, int i, int i2);

    public static native int UnBlockAllUser();

    public static native void Uninit();

    public static native void WriteLog(int i, String str);

    public static native void onNetWorkChanged(int i);

    public static native void setBrand(String str);

    public static native void setCPUArch(String str);

    public static native void setCPUChip(String str);

    public static native void setCachePath(String str);

    public static native void setDeviceIMEI(String str);

    public static native void setDocumentPath(String str);

    public static native void setModel(String str);

    public static native void setPackageName(String str);

    public static native void setSysVersion(String str);

    public static native void setUpdateReadStatusCallbackFlag(boolean z);
}
