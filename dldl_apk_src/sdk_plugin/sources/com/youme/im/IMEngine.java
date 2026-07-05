package com.youme.im;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;
import com.youme.im.CommonConst;
import com.youme.lbs.GeographyLocationManager;
import com.youme.voice.VoiceManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class IMEngine {
    private static int ENGINE_NOT_INIT = 1;
    private static String m_appKey;
    private static String m_appSecret;
    public static boolean m_init;
    private static boolean m_loadedLibrary;
    private static int m_serverZone;
    private static BroadcastReceiver s_netStatusReceiver;

    private static boolean loadLibrary() {
        if (!m_loadedLibrary) {
            try {
                System.loadLibrary("yim");
            } catch (Exception e) {
                Log.d("YIM_NATIVE", "loadLibrary error: " + e.toString());
            } catch (UnsatisfiedLinkError e2) {
                Log.d("YIM_NATIVE", "UnsatisfiedLinkError: " + e2.toString());
            } catch (Throwable th) {
                Log.d("YIM_NATIVE", "loadLibrary error: " + th.toString());
            }
            m_loadedLibrary = true;
        }
        return true;
    }

    private static void initIMEngine(Context context) {
        if (m_init) {
            return;
        }
        try {
            AppPara.initPara(context);
            VoiceManager.Instance().Init(context);
            GeographyLocationManager.Instance().Init(context);
            if (s_netStatusReceiver == null) {
                s_netStatusReceiver = new NetworkStatusReceiver2();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                context.registerReceiver(s_netStatusReceiver, intentFilter);
            }
            m_init = true;
        } catch (Throwable th) {
            Log.d("YIM_NATIVE", "initIMEngine error: " + th.toString());
        }
    }

    public static int init(Context context) {
        if (!loadLibrary()) {
            return -1;
        }
        initIMEngine(context);
        return 0;
    }

    public static void WriteLog(CommonConst.LogLevel logLevel, String str) {
        NativeEngine.WriteLog(logLevel.ordinal(), str);
    }

    public static int IM_Init(Context context, String str, String str2, int i) {
        m_appKey = str;
        m_appSecret = str2;
        m_serverZone = i;
        if (!loadLibrary()) {
            return -1;
        }
        initIMEngine(context);
        NativeEngine.SetServerZone(i);
        return NativeEngine.Init(str, str2);
    }

    public static int IM_LoadLibray(Context context, String str) {
        Log.i("YIM_NATIVE", "load library path:" + str);
        if (!m_loadedLibrary) {
            try {
                System.load(str);
                m_loadedLibrary = true;
            } catch (Exception e) {
                Log.d("YIM_NATIVE", "loadLibrary error: " + e.toString());
                return -1;
            } catch (UnsatisfiedLinkError e2) {
                Log.d("YIM_NATIVE", "UnsatisfiedLinkError: " + e2.toString());
                return -1;
            } catch (Throwable th) {
                Log.d("YIM_NATIVE", "loadLibrary error: " + th.toString());
                return -1;
            }
        }
        initIMEngine(context);
        try {
            NativeEngine.SetServerZone(m_serverZone);
            if (m_appKey != null && m_appKey.length() > 0 && m_appSecret != null && m_appSecret.length() > 0) {
                return NativeEngine.Init(m_appKey, m_appSecret);
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
        return -1;
    }

    public static boolean IsInit() {
        return m_init;
    }

    public static void IM_Uninit() {
        if (m_init) {
            NativeEngine.Uninit();
        }
    }

    public static void setUpdateReadStatusCallbackFlag(boolean z) {
        if (m_init) {
            NativeEngine.setUpdateReadStatusCallbackFlag(z);
        }
    }

    public static int IM_Login(String str, String str2, String str3) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.Login(str, str2, str3);
    }

    public static int IM_Logout() {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.Logout();
    }

    public static int IM_SendTextMessage(String str, int i, String str2, String str3, MessageRequestId messageRequestId) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.SendTextMessage(str, i, str2, str3, messageRequestId);
    }

    public static int IM_SendCustomMessage(String str, int i, byte[] bArr, int i2, MessageRequestId messageRequestId) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.SendCustomMessage(str, i, bArr, i2, messageRequestId);
    }

    public static int IM_SendFile(String str, int i, String str2, String str3, int i2, MessageRequestId messageRequestId) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.SendFile(str, i, str2, str3, i2, messageRequestId);
    }

    public static int IM_SendAudioMessage(String str, int i, MessageRequestId messageRequestId) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.SendAudioMessage(str, i, messageRequestId);
    }

    public static int IM_SendOnlyAudioMessage(String str, int i, MessageRequestId messageRequestId) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.SendOnlyAudioMessage(str, i, messageRequestId);
    }

    public static int IM_StopAudioMessage(String str) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.StopAudioMessage(str);
    }

    public static int IM_CancleAudioMessage() {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.CancleAudioMessage();
    }

    public static int IM_DownloadAudioFile(long j, String str) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.DownloadAudioFile(j, str);
    }

    public static int IM_JoinChatRoom(String str) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.JoinChatRoom(str);
    }

    public static int IM_LeaveChatRoom(String str) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.LeaveChatRoom(str);
    }

    public static int IM_LeaveAllChatRooms() {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.LeaveAllChatRooms();
    }

    public static int IM_GetRoomMemberCount(String str) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.GetRoomMemberCount(str);
    }

    public static byte[] IM_GetMessage() {
        if (m_init) {
            return NativeEngine.GetMessage();
        }
        return null;
    }

    public static void IM_PopMessage() {
        if (m_init) {
            NativeEngine.PopMessage();
        }
    }

    public static int IM_GetSDKVer() {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.GetSDKVer();
    }

    public static void IM_SetServerZone(int i) {
        if (m_init) {
            NativeEngine.SetServerZone(i);
        }
    }

    public static void IM_SetMode(int i) {
        if (m_init) {
            NativeEngine.SetMode(i);
        }
    }

    public static String IM_GetFilterText(String str, IntegerVal integerVal) {
        return !m_init ? "" : str == null ? str : NativeEngine.GetFilterText(str, integerVal);
    }

    public static void IM_OnPause(boolean z) {
        if (m_init) {
            NativeEngine.OnPause(z);
        }
    }

    public static void IM_OnResume() {
        if (m_init) {
            NativeEngine.OnResume();
        }
    }

    public static int IM_SendGift(String str, String str2, int i, int i2, String str3, MessageRequestId messageRequestId) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.SendGift(str, str2, i, i2, str3, messageRequestId);
    }

    public static int IM_MultiSendTextMessage(String str, String str2) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        if (str == null || "".equals(str)) {
            return 3;
        }
        return NativeEngine.MultiSendTextMessage(str, str2);
    }

    public static int IM_GetHistoryContact() {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.GetHistoryContact();
    }

    public static int IM_StartAudioSpeech(MessageRequestId messageRequestId, boolean z) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.StartAudioSpeech(messageRequestId, z);
    }

    public static int IM_StopAudioSpeech() {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.StopAudioSpeech();
    }

    public static int IM_ConvertAMRToWav(String str, String str2) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.ConvertAMRToWav(str, str2);
    }

    public static int IM_QueryHistoryMessage(String str, int i, long j, int i2, int i3) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.QueryHistoryMessage(str, i, j, i2, i3);
    }

    public static int IM_DeleteHistoryMessage(int i, long j) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.DeleteHistoryMessage(i, j);
    }

    public static int IM_DeleteHistoryMessageByID(long j) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.DeleteHistoryMessageByID(j);
    }

    public static int IM_DeleteSpecifiedHistoryMessage(String str, int i, long[] jArr) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.DeleteSpecifiedHistoryMessage(str, i, jArr);
    }

    public static int IM_DeleteHistoryMessageByTarget(String str, int i, long j, int i2) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.DeleteHistoryMessageByTarget(str, i, j, i2);
    }

    public static int IM_QueryRoomHistoryMessageFromServer(String str, int i, int i2) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        if (str == null || "".equals(str)) {
            return 3;
        }
        return NativeEngine.QueryRoomHistoryMessageFromServer(str, i, i2);
    }

    public static int IM_SetDownloadAudioMessageSwitch(boolean z) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.SetDownloadAudioMessageSwitch(z);
    }

    public static int IM_SetReceiveMessageSwitch(String str, boolean z) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        if (str == null || "".equals(str)) {
            return 3;
        }
        return NativeEngine.SetReceiveMessageSwitch(str, z);
    }

    public static int IM_GetNewMessage(String str) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.GetNewMessage(str);
    }

    public static void IM_SendMessageReadStatus(String str, int i, long j) {
        if (m_init) {
            NativeEngine.SendMessageReadStatus(str, i, j);
        }
    }

    public static int IM_SetUserInfo(String str) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        if (str == null || "".equals(str)) {
            return 3;
        }
        return NativeEngine.SetUserInfo(str);
    }

    public static int IM_GetUserInfo(String str) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        if (str == null || "".equals(str)) {
            return 3;
        }
        return NativeEngine.GetUserInfo(str);
    }

    public static int IM_SetRoomHistoryMessageSwitch(String str, boolean z) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        if (str == null || "".equals(str)) {
            return 3;
        }
        return NativeEngine.SetRoomHistoryMessageSwitch(str, z);
    }

    public static int IM_StartPlayAudio(String str) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        if (str == null || "".equals(str)) {
            return 3;
        }
        return NativeEngine.StartPlayAudio(str);
    }

    public static int IM_StopPlayAudio() {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.StopPlayAudio();
    }

    public static boolean IM_IsPlaying() {
        if (m_init) {
            return NativeEngine.IsPlaying();
        }
        return false;
    }

    public static void IM_SetVolume(float f) {
        if (m_init) {
            NativeEngine.SetVolume(f);
        }
    }

    public static String IM_GetAudioCachePath() {
        return NativeEngine.GetAudioCachePath();
    }

    public static boolean IM_ClearAudioCachePath() {
        return NativeEngine.ClearAudioCachePath();
    }

    public static int IM_QueryUserStatus(String str) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        if (str == null || "".equals(str)) {
            return 3;
        }
        return NativeEngine.QueryUserStatus(str);
    }

    public static int IM_TranslateText(IntegerVal integerVal, String str, int i, int i2) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.TranslateText(integerVal, str, i, i2);
    }

    public static int IM_GetCurrentLocation() {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.GetCurrentLocation();
    }

    public static int IM_GetNearbyObjects(int i, String str, int i2, boolean z) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.GetNearbyObjects(i, str, i2, z);
    }

    public static int IM_GetDistance(String str) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.GetDistance(str);
    }

    public static void IM_SetUpdateInterval(int i) {
        if (m_init) {
            NativeEngine.SetUpdateInterval(i);
        }
    }

    public static int IM_DownloadFileByURL(String str, String str2, int i) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.DownloadFileByURL(str, str2, i);
    }

    public static void IM_GetMicrophoneStatus() {
        if (m_init) {
            NativeEngine.GetMicrophoneStatus();
        }
    }

    public static int IM_SetSpeechRecognizeLanguage(int i) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.SetSpeechRecognizeLanguage(i);
    }

    public static int IM_SetOnlyRecognizeSpeechText(boolean z) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.SetOnlyRecognizeSpeechText(z);
    }

    public static int IM_Accusation(String str, int i, int i2, String str2, String str3) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.Accusation(str, i, i2, str2, str3);
    }

    public static int IM_QueryNotice() {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.QueryNotice();
    }

    public static int IM_GetForbiddenSpeakInfo() {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.GetForbiddenSpeakInfo();
    }

    public static int IM_BlockUser(String str, boolean z) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.BlockUser(str, z);
    }

    public static int IM_UnBlockAllUser() {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.UnBlockAllUser();
    }

    public static int IM_GetBlockUsers() {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.GetBlockUsers();
    }

    public static int IM_SetMessageRead(long j, boolean z) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.SetMessageRead(j, z);
    }

    public static int IM_SetAllMessageRead(String str, boolean z) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.SetAllMessageRead(str, z);
    }

    public static int IM_SetVoiceMsgPlayed(long j, boolean z) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.SetVoiceMsgPlayed(j, z);
    }

    public static int IM_SetDownloadDir(String str) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.SetDownloadDir(str);
    }

    public static int IM_SetUserProfileInfo(String str) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.SetUserProfileInfo(str);
    }

    public static int IM_SetUserProfilePhoto(String str) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.SetUserProfilePhoto(str);
    }

    public static int IM_GetUserProfileInfo(String str) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.GetUserProfileInfo(str);
    }

    public static int IM_SwitchUserStatus(String str, int i) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.SwitchUserStatus(str, i);
    }

    public static int IM_SetAddPermission(boolean z, int i) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.SetAddPermission(z, i);
    }

    public static int IM_FindUser(int i, String str) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.FindUser(i, str);
    }

    public static int IM_RequestAddFriend(String str, String str2) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.RequestAddFriend(str, str2);
    }

    public static int IM_DealBeRequestAddFriend(String str, int i) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.DealBeRequestAddFriend(str, i);
    }

    public static int IM_DeleteFriend(String str, int i) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.DeleteFriend(str, i);
    }

    public static int IM_BlackFriend(int i, String str) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.BlackFriend(i, str);
    }

    public static int IM_QueryFriends(int i, int i2, int i3) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.QueryFriends(i, i2, i3);
    }

    public static int IM_QueryFriendRequestList(int i, int i2) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.QueryFriendRequestList(i, i2);
    }

    public static int IM_SwitchMsgTransType(int i) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.SwitchMsgTransType(i);
    }

    public static int IM_SetShortConnectionMode() {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.SetShortConnectionMode();
    }

    public static int IM_SetLoginAddress(String str, int i) {
        if (!m_init) {
            return ENGINE_NOT_INIT;
        }
        return NativeEngine.SetLoginAddress(str, i);
    }

    public class MessageRequestId {
        private long requestId;

        public MessageRequestId() {
        }

        public void setId(long j) {
            this.requestId = j;
        }

        public long getId() {
            return this.requestId;
        }
    }

    public class IntegerVal {
        private int iValue = 0;

        public IntegerVal() {
        }

        public void setValue(int i) {
            this.iValue = i;
        }

        public int getValue() {
            return this.iValue;
        }
    }
}
