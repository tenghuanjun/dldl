package com.youme.imsdk;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.youme.im.IMEngine;
import com.youme.im.IMEngine.IntegerVal;
import com.youme.im.IMEngine.MessageRequestId;
import com.youme.imsdk.YIMConstInfo;
import com.youme.imsdk.callback.YIMEventCallback;
import com.youme.imsdk.internal.AccusationResult;
import com.youme.imsdk.internal.BlockUserInfo;
import com.youme.imsdk.internal.BlockUserList;
import com.youme.imsdk.internal.ChatRoom;
import com.youme.imsdk.internal.Contacts;
import com.youme.imsdk.internal.Download;
import com.youme.imsdk.internal.DownloadUrl;
import com.youme.imsdk.internal.GeographyLocation;
import com.youme.imsdk.internal.Login;
import com.youme.imsdk.internal.MicrophoneStatus;
import com.youme.imsdk.internal.MsgReadStatus;
import com.youme.imsdk.internal.NewMessageNotifyObj;
import com.youme.imsdk.internal.NoticeCancelInfo;
import com.youme.imsdk.internal.NoticeInfo;
import com.youme.imsdk.internal.OnPlayCompleteNotify;
import com.youme.imsdk.internal.PhotoUrlInfo;
import com.youme.imsdk.internal.RecognizeSpeechText;
import com.youme.imsdk.internal.ReconnectResult;
import com.youme.imsdk.internal.RecvMessage;
import com.youme.imsdk.internal.RelativeLocationInfo;
import com.youme.imsdk.internal.RoomHistoryMessage;
import com.youme.imsdk.internal.RoomHistoryMsgInfo;
import com.youme.imsdk.internal.RoomInfo;
import com.youme.imsdk.internal.SendMessage;
import com.youme.imsdk.internal.SendVoiceMsgInfo;
import com.youme.imsdk.internal.SpeechMessageInfo;
import com.youme.imsdk.internal.TranlateTextInfo;
import com.youme.imsdk.internal.UploadProgress;
import com.youme.imsdk.internal.UserChatRoom;
import com.youme.imsdk.internal.UserDistanceInfo;
import com.youme.imsdk.internal.UserInfoString;
import com.youme.imsdk.internal.UserProfileInfo;
import com.youme.imsdk.internal.UserStatusInfo;
import com.youme.imsdk.internal.VoiceInfo;
import com.youme.imsdk.internal.VolumeInfo;
import com.youme.imsdk.internal.YIMBlackFriendInfo;
import com.youme.imsdk.internal.YIMFriendCommon;
import com.youme.imsdk.internal.YIMFriendDealResult;
import com.youme.imsdk.internal.YIMFriendListInfo;
import com.youme.imsdk.internal.YIMFriendRequestInfoList;
import com.youme.imsdk.internal.YIMFriendUserID;
import com.youme.imsdk.internal.YIMUserBriefInfo;
import com.youme.imsdk.internal.YIMUserBriefInfoList;
import com.youme.imsdk.internal.YouMeIMJsonResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class YIMClient {
    private static final int CMD_ADD_FRIENT_RESULT_NOTIFY = 20024;
    private static final int CMD_AUDIO_PLAY_COMPLETE = 20009;
    private static final int CMD_BE_ADD_FRIENT = 20025;
    private static final int CMD_BE_BLACK_FRIEND_NOTIFY = 20027;
    private static final int CMD_BE_DELETE_FRIEND_NOTIFY = 20026;
    private static final int CMD_BLACK_FRIEND = 46;
    private static final int CMD_CANCEL_NOTICE = 20017;
    private static final int CMD_CHECK_ONLINE = 14;
    private static final int CMD_CLEAN_MASK_USER_MSG = 26;
    private static final int CMD_DEAL_ADD_FRIEND = 48;
    private static final int CMD_DELETE_FRIEND = 45;
    private static final int CMD_DOWNLOAD = 20001;
    private static final int CMD_DOWNLOAD_URL = 20012;
    private static final int CMD_ENTER_ROOM = 4;
    private static final int CMD_FIND_FRIEND_BY_ID = 40;
    private static final int CMD_FIND_FRIEND_BY_NICKNAME = 41;
    private static final int CMD_FRIND_NOTIFY = 44;
    private static final int CMD_GET_DISTANCE = 20022;
    private static final int CMD_GET_DISTRICT = 21;
    private static final int CMD_GET_FORBID_RECORD = 31;
    private static final int CMD_GET_MASK_USER_MSG = 25;
    private static final int CMD_GET_MICROPHONE_STATUS = 20013;
    private static final int CMD_GET_MSG = 9;
    private static final int CMD_GET_PEOPLE_NEARBY = 22;
    private static final int CMD_GET_RECONNECT_RESULT = 20019;
    private static final int CMD_GET_RENCENT_CONTACTS = 20006;
    private static final int CMD_GET_ROOM_HISTORY_MSG = 16;
    private static final int CMD_GET_ROOM_INFO = 27;
    private static final int CMD_GET_SESSION_HISTORY_MSG = 30;
    private static final int CMD_GET_SPEECH_TEXT = 20018;
    private static final int CMD_GET_TIPOFF_MSG = 20;
    private static final int CMD_GET_UPLOAD_TOKEN = 10;
    private static final int CMD_GET_USER_PROFILE = 20028;
    private static final int CMD_GET_USR_INFO = 17;
    private static final int CMD_HEARTBEAT = 2;
    private static final int CMD_HXR_USER_INFO_CHANGE_NOTIFY = 74;
    private static final int CMD_KICK_OFF = 11;
    private static final int CMD_LEAVE_ALL_ROOM = 28;
    private static final int CMD_LEAVE_ROOM = 5;
    private static final int CMD_LOGIN = 1;
    private static final int CMD_LOGOUT = 3;
    private static final int CMD_QUERY_BLACK_FRIEND_LIST = 50;
    private static final int CMD_QUERY_FRIEND_LIST = 49;
    private static final int CMD_QUERY_FRIEND_REQUEST_LIST = 51;
    private static final int CMD_QUERY_HISTORY_MESSAGE = 20005;
    private static final int CMD_QUERY_NOTICE = 23;
    private static final int CMD_QUERY_USER_PROFILE = 37;
    private static final int CMD_QUERY_USER_STATUS = 20008;
    private static final int CMD_RECEIVE_MESSAGE_NITIFY = 20007;
    private static final int CMD_RECORD_VOLUME = 20021;
    private static final int CMD_RECV_MESSAGE = 20003;
    private static final int CMD_RECV_NOTICE = 20016;
    private static final int CMD_RELATION_CHAIN_HEARTBEAT = 53;
    private static final int CMD_RELOGIN = 13;
    private static final int CMD_REQUEST_ADD_FRIEND = 42;
    private static final int CMD_REQUEST_ADD_FRIEND_NOTIFY = 20023;
    private static final int CMD_SEND_MESSAGE_STATUS = 20002;
    private static final int CMD_SET_MASK_USER_MSG = 24;
    private static final int CMD_SET_READ_STATUS = 76;
    private static final int CMD_SET_USER_PHOTO = 20030;
    private static final int CMD_SET_USER_PROFILE = 20029;
    private static final int CMD_SND_BIN_MSG = 12;
    private static final int CMD_SND_FILE_MSG = 8;
    private static final int CMD_SND_GIFT_MSG = 15;
    private static final int CMD_SND_TEXT_MSG = 6;
    private static final int CMD_SND_TIPOFF_MSG = 19;
    private static final int CMD_SND_VOICE_MSG = 7;
    private static final int CMD_START_RECONNECT = 20020;
    private static final int CMD_STOP_AUDIOSPEECH = 20004;
    private static final int CMD_STOP_SEND_AUDIO = 20010;
    private static final int CMD_SWITCH_USER_STATE = 20031;
    private static final int CMD_TRANSLATE_COMPLETE = 20011;
    private static final int CMD_UNBLACK_FRIEND = 47;
    private static final int CMD_UNKNOW = 0;
    private static final int CMD_UPDATE_FRIEND_RQUEST_STATUS = 52;
    private static final int CMD_UPDATE_ONLINE_STATUS = 39;
    private static final int CMD_UPDATE_USER_INFO = 18;
    private static final int CMD_UPDATE_USER_PROFILE = 38;
    private static final int CMD_UPLOAD_PROGRESS = 75;
    private static final int CMD_USER_ENTER_ROOM = 20014;
    private static final int CMD_USER_LEAVE_ROOM = 20015;
    private static boolean DEBUG = false;
    private static final int NOTIFY_LOGIN = 10001;
    private static final int NOTIFY_PRIVATE_MSG = 10002;
    private static final int NOTIFY_ROOM_MSG = 10003;
    private static final String TAG = YIMService.class.getSimpleName();
    private static YIMClient mInstance;
    private boolean mIsExit = true;
    private Thread mThread = null;
    private Handler mHandler = null;
    private YIMEventCallback.ResultCallback<String> m_loginCallback = null;
    private YIMEventCallback.OperationCallback m_logoutCallback = null;
    private YIMEventCallback.ResultCallback<RoomInfo> m_roomInfoCallback = null;
    private YIMEventCallback.OperationCallback m_leaveAllCallback = null;
    private YIMEventCallback.OperationCallback m_setprofileCallback = null;
    private YIMEventCallback.OperationCallback m_switchStatusCallback = null;
    private YIMEventCallback.OperationCallback m_setPermissionCallback = null;
    private YIMEventCallback.UserJoinLeaveChannelCallback m_userJoinLeaveCallback = null;
    private YIMEventCallback.AutoDownloadVoiceCallback m_autoDownloadCallback = null;
    private YIMEventCallback.KickOffCallback m_kickOffCallback = null;
    private YIMEventCallback.MessageEventCallback m_msgEventCallback = null;
    private YIMEventCallback.UpdateReadStatusCallback m_updateReadStatusCallback = null;
    private YIMEventCallback.ResultCallback<RoomHistoryMsgInfo> m_roomMsgCallback = null;
    private YIMEventCallback.ResultCallback<ArrayList<ContactsSessionInfo>> m_historyContactCallback = null;
    private YIMEventCallback.ResultCallback<YIMHistoryMessage> m_localHistoryMsgCallback = null;
    private YIMEventCallback.ResultCallback<YIMExtraUserInfo> m_userInfoCallback = null;
    private YIMEventCallback.ResultCallback<String> m_playCompleteCallback = null;
    private YIMEventCallback.ResultCallback<UserStatusInfo> m_userStatusCallback = null;
    private YIMEventCallback.ResultCallback<TranlateTextInfo> m_translateTextCallback = null;
    private YIMEventCallback.ResultCallback<BlockUserInfo> m_blockUserCallback = null;
    private YIMEventCallback.OperationCallback m_unBlockUserCallback = null;
    private YIMEventCallback.ResultCallback<ArrayList<String>> m_getBlockUsersCallback = null;
    private YIMEventCallback.GetLocationCallback m_getLocationCallback = null;
    private YIMEventCallback.ResultCallback<RelativeLocationInfo> m_getNearbyObjCallback = null;
    private YIMEventCallback.ResultCallback<UserDistanceInfo> m_getDistanceCallback = null;
    private YIMEventCallback.GetMicStatusCallback m_getMicStatusCallback = null;
    private YIMEventCallback.AccusationResultCallback m_accusationCallback = null;
    private YIMEventCallback.NoticeCallback m_noticeCallback = null;
    private YIMEventCallback.ReconnectCallback m_reconnectCallback = null;
    private YIMEventCallback.ResultCallback<ArrayList<YIMForbiddenSpeakInfo>> m_forbidSpeakInfoCallback = null;
    private YIMEventCallback.UserProfileChangeCallback m_userProfileChangeCallback = null;
    private YIMEventCallback.FriendNotifyCallback m_friendNotifyCallback = null;
    private YIMEventCallback.ResultCallback<UserProfileInfo> m_userProfileCallback = null;
    private YIMEventCallback.ResultCallback<String> m_photoUrlCallback = null;
    private YIMEventCallback.ResultCallback<ArrayList<YIMUserBriefInfo>> m_findUserCallback = null;
    private YIMEventCallback.ResultCallback<String> m_requestAddCallback = null;
    private YIMEventCallback.ResultCallback<YIMFriendDealResult> m_dealAddCallback = null;
    private YIMEventCallback.ResultCallback<String> m_deleteFriendCallback = null;
    private YIMEventCallback.ResultCallback<YIMBlackFriendInfo> m_blackFriendCallback = null;
    private YIMEventCallback.ResultCallback<YIMFriendListInfo> m_queryFriendsCallback = null;
    private YIMEventCallback.ResultCallback<YIMFriendRequestInfoList> m_requestListCallback = null;
    private Map<String, YIMEventCallback.ResultCallback<ChatRoom>> joinChannelCallbackQueue = new HashMap();
    private Map<String, YIMEventCallback.ResultCallback<ChatRoom>> leaveChannelCallbackQueue = new HashMap();
    private Map<Long, MessageCallbackObject> messageCallbackQueue = new HashMap();
    private Map<Long, YIMEventCallback.ShowUploadFileProgressCallback> uploadProgressCallbackQueue = new HashMap();
    private YIMMessage lastRecordMsg = null;
    private Map<Long, YIMEventCallback.DownloadFileCallback> downloadMsgCallbackQueue = new HashMap();
    private Map<Long, YIMEventCallback.SpeechEventCallback> uploadSpeechCallbackQueue = new HashMap();
    private SpeechMessageInfo lastSpeechMsg = null;
    private Map<String, YIMEventCallback.DownloadByUrlCallback> downloadByUrlCallbackQueue = new HashMap();
    private String m_currentUserID = "";
    private String m_downloadDir = "";
    private Runnable paresMessage = new Runnable() { // from class: com.youme.imsdk.YIMClient.1
        @Override // java.lang.Runnable
        public void run() {
            String str = null;
            while (true) {
                try {
                    str = new String(IMEngine.IM_GetMessage(), "UTF-8");
                } catch (UnsupportedEncodingException unused) {
                    Log.e(YIMClient.TAG, "Couldn't convert the jbyteArray to jstring");
                }
                if (str != null) {
                    if (YIMClient.this.mIsExit) {
                        YIMClient.this.mThread = null;
                        return;
                    }
                    if (YIMClient.DEBUG) {
                        Log.d(YIMClient.TAG, "IM_GetMessage :" + str);
                    }
                    YIMClient.this.handlerMessage(str);
                    IMEngine.IM_PopMessage();
                }
            }
        }
    };

    public enum ChannelEventType {
        UserJoinRoom,
        UserLeaveRoom
    }

    public static final class RequestId {
        public long id;
    }

    public static YIMClient getInstance() {
        if (mInstance == null) {
            mInstance = new YIMClient();
        }
        return mInstance;
    }

    public void registerUserJoinLeaveCallback(YIMEventCallback.UserJoinLeaveChannelCallback userJoinLeaveChannelCallback) {
        this.m_userJoinLeaveCallback = userJoinLeaveChannelCallback;
    }

    public void unRegisterUserJoinLeaveCallback() {
        this.m_userJoinLeaveCallback = null;
    }

    public void registerUpdateReadStatusCallback(YIMEventCallback.UpdateReadStatusCallback updateReadStatusCallback) {
        this.m_updateReadStatusCallback = updateReadStatusCallback;
        IMEngine.setUpdateReadStatusCallbackFlag(true);
    }

    public void unRegisterUpdateReadStatusCallback() {
        this.m_updateReadStatusCallback = null;
    }

    public void registerKickOffCallback(YIMEventCallback.KickOffCallback kickOffCallback) {
        this.m_kickOffCallback = kickOffCallback;
    }

    public void unRegisterKickOffCallback() {
        this.m_kickOffCallback = null;
    }

    public void registerMsgEventCallback(YIMEventCallback.MessageEventCallback messageEventCallback) {
        this.m_msgEventCallback = messageEventCallback;
    }

    public void unRegisterMsgEventCallback() {
        this.m_msgEventCallback = null;
    }

    public void registerAutoDownloadCallback(YIMEventCallback.AutoDownloadVoiceCallback autoDownloadVoiceCallback) {
        this.m_autoDownloadCallback = autoDownloadVoiceCallback;
    }

    public void unRegisterAutoDownloadCallback() {
        this.m_autoDownloadCallback = null;
    }

    public void registerGetLocationCallback(YIMEventCallback.GetLocationCallback getLocationCallback) {
        this.m_getLocationCallback = getLocationCallback;
    }

    public void unregisterGetLocationCallback() {
        this.m_getLocationCallback = null;
    }

    public void registerAccusationCallback(YIMEventCallback.AccusationResultCallback accusationResultCallback) {
        this.m_accusationCallback = accusationResultCallback;
    }

    public void unRegisterAccusationCallback() {
        this.m_accusationCallback = null;
    }

    public void registerNoticeCallback(YIMEventCallback.NoticeCallback noticeCallback) {
        this.m_noticeCallback = noticeCallback;
    }

    public void unRegisterNoticeCallback() {
        this.m_noticeCallback = null;
    }

    public void registerReconnectCallback(YIMEventCallback.ReconnectCallback reconnectCallback) {
        this.m_reconnectCallback = reconnectCallback;
    }

    public void unRegisterReconnectCallback() {
        this.m_reconnectCallback = null;
    }

    public void registerUserProfileChangeCallback(YIMEventCallback.UserProfileChangeCallback userProfileChangeCallback) {
        this.m_userProfileChangeCallback = userProfileChangeCallback;
    }

    public void unRegisterUserProfileChangeCallback() {
        this.m_userProfileChangeCallback = null;
    }

    public void registerFriendNotifyCallback(YIMEventCallback.FriendNotifyCallback friendNotifyCallback) {
        this.m_friendNotifyCallback = friendNotifyCallback;
    }

    public void unRegisterFriendNotifyCallback() {
        this.m_friendNotifyCallback = null;
    }

    public int init(Context context, String str, String str2, int i) {
        if (context == null) {
            return 3005;
        }
        if (!checkString("AppKey", str)) {
            return 3004;
        }
        if (checkString("SecrectKey", str2)) {
            return IMEngine.IM_Init(context, str, str2, i) == -1 ? 50 : 0;
        }
        return 3006;
    }

    private static void initEngine(Context context) {
        IMEngine.init(context);
    }

    public void login(String str, String str2, String str3, YIMEventCallback.ResultCallback<String> resultCallback) {
        this.m_loginCallback = resultCallback;
        if (!checkString("UserId", str)) {
            resultCallback.onFailed(3000, str);
            return;
        }
        if (!checkString("Password", str2)) {
            resultCallback.onFailed(3002, str);
            return;
        }
        int iIM_Login = IMEngine.IM_Login(str, str2, str3);
        if (DEBUG) {
            String str4 = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("errcode:");
            sb.append(iIM_Login);
            sb.append(", mThread is null");
            sb.append(this.mThread == null);
            Log.d(str4, sb.toString());
        }
        if (iIM_Login == 0) {
            this.mIsExit = false;
            if (this.mHandler == null) {
                this.mHandler = new Handler();
            }
            if (this.mThread == null) {
                Thread thread = new Thread(this.paresMessage);
                this.mThread = thread;
                thread.start();
                return;
            }
            return;
        }
        resultCallback.onFailed(iIM_Login, str);
    }

    public void logout(YIMEventCallback.OperationCallback operationCallback) {
        this.m_logoutCallback = operationCallback;
        int iIM_Logout = IMEngine.IM_Logout();
        if (iIM_Logout == 0 || operationCallback == null) {
            return;
        }
        operationCallback.onFailed(iIM_Logout);
    }

    public void joinChatRoom(String str, YIMEventCallback.ResultCallback<ChatRoom> resultCallback) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.groupId = str;
        if (!checkString("RoomID", str)) {
            resultCallback.onFailed(3001, chatRoom);
            return;
        }
        int iIM_JoinChatRoom = IMEngine.IM_JoinChatRoom(str);
        if (iIM_JoinChatRoom != 0) {
            if (resultCallback != null) {
                resultCallback.onFailed(iIM_JoinChatRoom, chatRoom);
            }
        } else {
            if (resultCallback == null || addJoinChannelCallbackObj(str, resultCallback)) {
                return;
            }
            resultCallback.onFailed(YIMConstInfo.Errorcode.IsWaitingJoin, chatRoom);
        }
    }

    public void leaveChatRoom(String str, YIMEventCallback.ResultCallback<ChatRoom> resultCallback) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.groupId = str;
        if (!checkString("RoomID", str)) {
            resultCallback.onFailed(3001, chatRoom);
            return;
        }
        int iIM_LeaveChatRoom = IMEngine.IM_LeaveChatRoom(str);
        if (iIM_LeaveChatRoom != 0) {
            if (resultCallback != null) {
                resultCallback.onFailed(iIM_LeaveChatRoom, chatRoom);
            }
        } else {
            if (resultCallback == null || addLeaveChannelCallbackObj(str, resultCallback)) {
                return;
            }
            resultCallback.onFailed(YIMConstInfo.Errorcode.IsWaitingLeave, chatRoom);
        }
    }

    public void leaveAllChatRooms(YIMEventCallback.OperationCallback operationCallback) {
        this.m_leaveAllCallback = operationCallback;
        int iIM_LeaveAllChatRooms = IMEngine.IM_LeaveAllChatRooms();
        if (iIM_LeaveAllChatRooms == 0 || operationCallback == null) {
            return;
        }
        operationCallback.onFailed(iIM_LeaveAllChatRooms);
    }

    public void getRoomMemberCount(String str, YIMEventCallback.ResultCallback<RoomInfo> resultCallback) {
        this.m_roomInfoCallback = resultCallback;
        RoomInfo roomInfo = new RoomInfo();
        roomInfo.roomID = str;
        roomInfo.memberCount = 0;
        int iIM_GetRoomMemberCount = IMEngine.IM_GetRoomMemberCount(str);
        if (iIM_GetRoomMemberCount == 0 || resultCallback == null) {
            return;
        }
        resultCallback.onFailed(iIM_GetRoomMemberCount, roomInfo);
    }

    public void sendTextMessage(String str, int i, String str2, String str3, YIMEventCallback.ResultCallback<SendMessage> resultCallback) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setSendTime(0);
        sendMessage.setIsForbidRoom(false);
        sendMessage.setEndTime(0L);
        if (!checkString("ReceiverID", str)) {
            sendMessage.setRequestId(0L);
            resultCallback.onFailed(20, sendMessage);
            return;
        }
        IMEngine iMEngine = new IMEngine();
        iMEngine.getClass();
        IMEngine.MessageRequestId messageRequestId = iMEngine.new MessageRequestId();
        int iIM_SendTextMessage = IMEngine.IM_SendTextMessage(str, i, str2, str3, messageRequestId);
        if (iIM_SendTextMessage != 0) {
            if (resultCallback != null) {
                sendMessage.setRequestId(messageRequestId.getId());
                resultCallback.onFailed(iIM_SendTextMessage, sendMessage);
                return;
            }
            return;
        }
        YIMMessage yIMMessage = new YIMMessage();
        yIMMessage.setChatType(i);
        yIMMessage.setMeesageID(messageRequestId.getId());
        yIMMessage.setSenderID(this.m_currentUserID);
        yIMMessage.setReceiveID(str);
        yIMMessage.setMessageType(1);
        YIMMessageBodyText yIMMessageBodyText = new YIMMessageBodyText();
        yIMMessageBodyText.setMessageContent(str2);
        yIMMessageBodyText.setAttachParam(str3);
        yIMMessage.setMessageBody(yIMMessageBodyText);
        if (resultCallback != null) {
            if (addMessageCallbackObj(messageRequestId.getId(), new MessageCallbackObject(1, yIMMessage, resultCallback))) {
                return;
            }
            resultCallback.onFailed(YIMConstInfo.Errorcode.IsWaitingSend, sendMessage);
        }
    }

    public void sendMessageReadStatus(String str, int i, long j) {
        if (!checkString("SendId", str)) {
            Log.d(TAG, "sendMessageReadStatus: recvId is empty");
        } else {
            Log.d(TAG, "sendMessageReadStatus: bruce >>> 确认消息已读");
            IMEngine.IM_SendMessageReadStatus(str, i, j);
        }
    }

    public MessageSendStatus startRecordAudioMessage(String str, int i, String str2, boolean z, boolean z2) {
        int iIM_SendOnlyAudioMessage;
        MessageSendStatus messageSendStatus = new MessageSendStatus();
        if (!checkString("ReceiverID", str)) {
            messageSendStatus.setMessageId(0L);
            messageSendStatus.setErrorCode(20);
        } else {
            IMEngine iMEngine = new IMEngine();
            iMEngine.getClass();
            IMEngine.MessageRequestId messageRequestId = iMEngine.new MessageRequestId();
            if (z) {
                IMEngine.IM_SetOnlyRecognizeSpeechText(z2);
                iIM_SendOnlyAudioMessage = IMEngine.IM_SendAudioMessage(str, i, messageRequestId);
            } else {
                iIM_SendOnlyAudioMessage = IMEngine.IM_SendOnlyAudioMessage(str, i, messageRequestId);
            }
            messageSendStatus.setMessageId(messageRequestId.getId());
            messageSendStatus.setErrorCode(iIM_SendOnlyAudioMessage);
            if (iIM_SendOnlyAudioMessage == 0) {
                YIMMessage yIMMessage = new YIMMessage();
                this.lastRecordMsg = yIMMessage;
                yIMMessage.setSenderID(this.m_currentUserID);
                this.lastRecordMsg.setReceiveID(str);
                this.lastRecordMsg.setChatType(i);
                YIMMessageBodyAudio yIMMessageBodyAudio = new YIMMessageBodyAudio();
                yIMMessageBodyAudio.setParam(str2);
                this.lastRecordMsg.setMessageBody(yIMMessageBodyAudio);
                this.lastRecordMsg.setMeesageID(messageRequestId.getId());
            }
        }
        return messageSendStatus;
    }

    public void stopAndSendAudioMessage(YIMEventCallback.AudioMsgEventCallback audioMsgEventCallback) {
        SendVoiceMsgInfo sendVoiceMsgInfo = new SendVoiceMsgInfo();
        sendVoiceMsgInfo.setRequestId(0L);
        sendVoiceMsgInfo.setText("");
        sendVoiceMsgInfo.setLocalPath("");
        sendVoiceMsgInfo.setDuration(0);
        sendVoiceMsgInfo.setSendTime(0);
        sendVoiceMsgInfo.setIsForbidRoom(false);
        sendVoiceMsgInfo.setReasonType(-1);
        sendVoiceMsgInfo.setEndTime(0L);
        YIMMessage yIMMessage = this.lastRecordMsg;
        if (yIMMessage != null) {
            YIMMessageBodyAudio yIMMessageBodyAudio = (YIMMessageBodyAudio) yIMMessage.getMessageBody();
            int iIM_StopAudioMessage = IMEngine.IM_StopAudioMessage(yIMMessageBodyAudio.getParam());
            sendVoiceMsgInfo.setRequestId(this.lastRecordMsg.getMessageID());
            if (iIM_StopAudioMessage == 0) {
                YIMMessage yIMMessage2 = new YIMMessage();
                yIMMessage2.setChatType(this.lastRecordMsg.getChatType());
                yIMMessage2.setMeesageID(this.lastRecordMsg.getMessageID());
                yIMMessage2.setSenderID(this.m_currentUserID);
                yIMMessage2.setReceiveID(this.lastRecordMsg.getReceiveID());
                yIMMessage2.setMessageType(5);
                YIMMessageBodyAudio yIMMessageBodyAudio2 = new YIMMessageBodyAudio();
                yIMMessageBodyAudio2.setParam(yIMMessageBodyAudio.getParam());
                yIMMessage2.setMessageBody(yIMMessageBodyAudio2);
                if (audioMsgEventCallback != null) {
                    if (!addMessageCallbackObj(this.lastRecordMsg.getMessageID(), new MessageCallbackObject(5, yIMMessage2, audioMsgEventCallback))) {
                        audioMsgEventCallback.onSendAudioMessageStatus(YIMConstInfo.Errorcode.IsWaitingSend, sendVoiceMsgInfo);
                    }
                }
            } else if (audioMsgEventCallback != null) {
                audioMsgEventCallback.onSendAudioMessageStatus(iIM_StopAudioMessage, sendVoiceMsgInfo);
            }
            this.lastRecordMsg = null;
            return;
        }
        Log.e("YouMeIM", "Has no start record!");
        if (audioMsgEventCallback != null) {
            audioMsgEventCallback.onSendAudioMessageStatus(2016, sendVoiceMsgInfo);
        }
    }

    public int cancleAudioMessage() {
        return IMEngine.IM_CancleAudioMessage();
    }

    public void downloadAudioMessage(long j, String str, YIMEventCallback.DownloadFileCallback downloadFileCallback) {
        int iIM_DownloadAudioFile;
        if (this.m_downloadDir != "" && (str == "" || str == null)) {
            iIM_DownloadAudioFile = IMEngine.IM_DownloadAudioFile(j, this.m_downloadDir);
        } else {
            iIM_DownloadAudioFile = (str == "" || str == null) ? 3 : IMEngine.IM_DownloadAudioFile(j, str);
        }
        YIMMessage yIMMessage = new YIMMessage();
        yIMMessage.setMeesageID(j);
        if (iIM_DownloadAudioFile != 0) {
            if (downloadFileCallback != null) {
                downloadFileCallback.onDownload(iIM_DownloadAudioFile, yIMMessage, str);
            }
        } else {
            if (downloadFileCallback == null || addDownloadMsgCallbackObj(j, downloadFileCallback)) {
                return;
            }
            downloadFileCallback.onDownload(YIMConstInfo.Errorcode.IsWaitingDownload, yIMMessage, str);
        }
    }

    public void downloadFile(long j, String str, YIMEventCallback.DownloadFileCallback downloadFileCallback) {
        int iIM_DownloadAudioFile = IMEngine.IM_DownloadAudioFile(j, str);
        YIMMessage yIMMessage = new YIMMessage();
        yIMMessage.setMeesageID(j);
        if (iIM_DownloadAudioFile != 0) {
            if (downloadFileCallback != null) {
                downloadFileCallback.onDownload(iIM_DownloadAudioFile, yIMMessage, str);
            }
        } else {
            if (downloadFileCallback == null || addDownloadMsgCallbackObj(j, downloadFileCallback)) {
                return;
            }
            downloadFileCallback.onDownload(YIMConstInfo.Errorcode.IsWaitingDownload, yIMMessage, str);
        }
    }

    public void downloadFileByUrl(String str, String str2, int i, YIMEventCallback.DownloadByUrlCallback downloadByUrlCallback) {
        int iIM_DownloadFileByURL = IMEngine.IM_DownloadFileByURL(str, str2, i);
        if (iIM_DownloadFileByURL != 0) {
            if (downloadByUrlCallback != null) {
                downloadByUrlCallback.onDownloadByUrl(iIM_DownloadFileByURL, str, str2, 0);
            }
        } else {
            if (downloadByUrlCallback == null || addUrlDownloadCallbackObj(str, downloadByUrlCallback)) {
                return;
            }
            downloadByUrlCallback.onDownloadByUrl(YIMConstInfo.Errorcode.IsWaitingDownload, str, str2, 0);
        }
    }

    public int setDownloadDir(String str) {
        this.m_downloadDir = str;
        return IMEngine.IM_SetDownloadDir(str);
    }

    public void sendCustomMessage(String str, int i, byte[] bArr, int i2, YIMEventCallback.ResultCallback<SendMessage> resultCallback) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setSendTime(0);
        sendMessage.setIsForbidRoom(false);
        sendMessage.setEndTime(0L);
        if (!checkString("ReceiverID", str)) {
            sendMessage.setRequestId(0L);
            resultCallback.onFailed(20, sendMessage);
            return;
        }
        IMEngine iMEngine = new IMEngine();
        iMEngine.getClass();
        IMEngine.MessageRequestId messageRequestId = iMEngine.new MessageRequestId();
        int iIM_SendCustomMessage = IMEngine.IM_SendCustomMessage(str, i, bArr, i2, messageRequestId);
        if (iIM_SendCustomMessage != 0) {
            if (resultCallback != null) {
                sendMessage.setRequestId(messageRequestId.getId());
                resultCallback.onFailed(iIM_SendCustomMessage, sendMessage);
                return;
            }
            return;
        }
        YIMMessage yIMMessage = new YIMMessage();
        yIMMessage.setChatType(i);
        yIMMessage.setMeesageID(messageRequestId.getId());
        yIMMessage.setSenderID(this.m_currentUserID);
        yIMMessage.setReceiveID(str);
        yIMMessage.setMessageType(1);
        YIMMessageBodyCustom yIMMessageBodyCustom = new YIMMessageBodyCustom();
        yIMMessageBodyCustom.setMessageContent(Base64.encodeToString(bArr, 0));
        yIMMessage.setMessageBody(yIMMessageBodyCustom);
        if (addMessageCallbackObj(messageRequestId.getId(), new MessageCallbackObject(2, yIMMessage, resultCallback)) || resultCallback == null) {
            return;
        }
        resultCallback.onFailed(YIMConstInfo.Errorcode.IsWaitingSend, sendMessage);
    }

    public void sendFile(String str, int i, String str2, String str3, int i2, YIMEventCallback.ResultCallback<SendMessage> resultCallback) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setSendTime(0);
        sendMessage.setIsForbidRoom(false);
        sendMessage.setEndTime(0L);
        if (!checkString("ReceiverID", str)) {
            sendMessage.setRequestId(0L);
            resultCallback.onFailed(20, sendMessage);
            return;
        }
        IMEngine iMEngine = new IMEngine();
        iMEngine.getClass();
        IMEngine.MessageRequestId messageRequestId = iMEngine.new MessageRequestId();
        int iIM_SendFile = IMEngine.IM_SendFile(str, i, str2, str3, i2, messageRequestId);
        if (iIM_SendFile != 0) {
            if (resultCallback != null) {
                sendMessage.setRequestId(messageRequestId.getId());
                resultCallback.onFailed(iIM_SendFile, sendMessage);
                return;
            }
            return;
        }
        YIMMessage yIMMessage = new YIMMessage();
        yIMMessage.setChatType(i);
        yIMMessage.setMeesageID(messageRequestId.getId());
        yIMMessage.setSenderID(this.m_currentUserID);
        yIMMessage.setReceiveID(str);
        yIMMessage.setMessageType(1);
        YIMMessageBodyFile yIMMessageBodyFile = new YIMMessageBodyFile();
        yIMMessageBodyFile.setFileType(i2);
        yIMMessageBodyFile.setExtParam(str3);
        yIMMessage.setMessageBody(yIMMessageBodyFile);
        if (resultCallback != null) {
            if (addMessageCallbackObj(messageRequestId.getId(), new MessageCallbackObject(7, yIMMessage, resultCallback))) {
                return;
            }
            resultCallback.onFailed(YIMConstInfo.Errorcode.IsWaitingSend, sendMessage);
        }
    }

    public void sendFileWithProgress(String str, int i, String str2, String str3, int i2, YIMEventCallback.ResultCallback<SendMessage> resultCallback, YIMEventCallback.ShowUploadFileProgressCallback showUploadFileProgressCallback) {
        if (!checkString("ReceiverID", str)) {
            resultCallback.onFailed(20, null);
            return;
        }
        IMEngine iMEngine = new IMEngine();
        iMEngine.getClass();
        IMEngine.MessageRequestId messageRequestId = iMEngine.new MessageRequestId();
        int iIM_SendFile = IMEngine.IM_SendFile(str, i, str2, str3, i2, messageRequestId);
        setProgressCallback(messageRequestId.getId(), showUploadFileProgressCallback);
        if (iIM_SendFile != 0) {
            if (resultCallback != null) {
                resultCallback.onFailed(iIM_SendFile, null);
                return;
            }
            return;
        }
        YIMMessage yIMMessage = new YIMMessage();
        yIMMessage.setChatType(i);
        yIMMessage.setMeesageID(messageRequestId.getId());
        yIMMessage.setSenderID(this.m_currentUserID);
        yIMMessage.setReceiveID(str);
        yIMMessage.setMessageType(1);
        YIMMessageBodyFile yIMMessageBodyFile = new YIMMessageBodyFile();
        yIMMessageBodyFile.setFileType(i2);
        yIMMessageBodyFile.setExtParam(str3);
        yIMMessage.setMessageBody(yIMMessageBodyFile);
        if (resultCallback != null) {
            MessageCallbackObject messageCallbackObject = new MessageCallbackObject(7, yIMMessage, resultCallback);
            setProgressCallback(messageRequestId.getId(), showUploadFileProgressCallback);
            if (addMessageCallbackObj(messageRequestId.getId(), messageCallbackObject)) {
                return;
            }
            resultCallback.onFailed(YIMConstInfo.Errorcode.IsWaitingSend, null);
        }
    }

    public void setProgressCallback(long j, YIMEventCallback.ShowUploadFileProgressCallback showUploadFileProgressCallback) {
        if (this.uploadProgressCallbackQueue.containsKey(Long.valueOf(j))) {
            return;
        }
        this.uploadProgressCallbackQueue.put(Long.valueOf(j), showUploadFileProgressCallback);
    }

    public String getFilterText(String str, IMEngine.IntegerVal integerVal) {
        return IMEngine.IM_GetFilterText(str, integerVal);
    }

    public void onPause(boolean z) {
        IMEngine.IM_OnPause(z);
    }

    public void onResume() {
        IMEngine.IM_OnResume();
    }

    public int getNewMessage(List<String> list) {
        return IMEngine.IM_GetNewMessage(new JSONArray((Collection) list).toString());
    }

    public int setDownloadAudioMessageSwitch(boolean z) {
        return IMEngine.IM_SetDownloadAudioMessageSwitch(z);
    }

    public int setReceiveMessageSwitch(List<String> list, boolean z) {
        return IMEngine.IM_SetReceiveMessageSwitch(new JSONArray((Collection) list).toString(), z);
    }

    public void queryRoomHistoryMessageFromServer(String str, int i, int i2, YIMEventCallback.ResultCallback<RoomHistoryMsgInfo> resultCallback) {
        this.m_roomMsgCallback = resultCallback;
        int iIM_QueryRoomHistoryMessageFromServer = IMEngine.IM_QueryRoomHistoryMessageFromServer(str, i, i2);
        if (iIM_QueryRoomHistoryMessageFromServer == 0 || resultCallback == null) {
            return;
        }
        resultCallback.onFailed(iIM_QueryRoomHistoryMessageFromServer, new RoomHistoryMsgInfo(str, 0, null));
    }

    public void sendGift(String str, String str2, int i, int i2, YIMExtraGifParam yIMExtraGifParam, YIMEventCallback.ResultCallback<SendMessage> resultCallback) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setSendTime(0);
        sendMessage.setIsForbidRoom(false);
        sendMessage.setEndTime(0L);
        if (!checkString("Receive strChannel ", str2)) {
            sendMessage.setRequestId(0L);
            resultCallback.onFailed(20, sendMessage);
            return;
        }
        IMEngine iMEngine = new IMEngine();
        iMEngine.getClass();
        IMEngine.MessageRequestId messageRequestId = iMEngine.new MessageRequestId();
        Gson gsonCreate = new GsonBuilder().create();
        if (DEBUG) {
            Log.d(TAG, "gift extparam:" + gsonCreate.toJson(yIMExtraGifParam));
        }
        int iIM_SendGift = IMEngine.IM_SendGift(str, str2, i, i2, gsonCreate.toJson(yIMExtraGifParam), messageRequestId);
        if (iIM_SendGift != 0) {
            if (resultCallback != null) {
                sendMessage.setRequestId(messageRequestId.getId());
                resultCallback.onFailed(iIM_SendGift, sendMessage);
                return;
            }
            return;
        }
        YIMMessage yIMMessage = new YIMMessage();
        yIMMessage.setChatType(2);
        yIMMessage.setMeesageID(messageRequestId.getId());
        yIMMessage.setSenderID(this.m_currentUserID);
        yIMMessage.setReceiveID(str);
        yIMMessage.setMessageType(8);
        YIMMessageBodyGift yIMMessageBodyGift = new YIMMessageBodyGift();
        yIMMessageBodyGift.setAnchor(str);
        yIMMessageBodyGift.setGiftID(Integer.valueOf(i));
        yIMMessageBodyGift.setGiftCount(Integer.valueOf(i2));
        yIMMessageBodyGift.setExtParam(yIMExtraGifParam);
        yIMMessage.setMessageBody(yIMMessageBodyGift);
        if (resultCallback != null) {
            if (addMessageCallbackObj(messageRequestId.getId(), new MessageCallbackObject(8, yIMMessage, resultCallback))) {
                return;
            }
            resultCallback.onFailed(YIMConstInfo.Errorcode.IsWaitingSend, sendMessage);
        }
    }

    public int multiSendTextMessage(List<String> list, String str) {
        return IMEngine.IM_MultiSendTextMessage(new JSONArray((Collection) list).toString(), str);
    }

    public void getHistoryContact(YIMEventCallback.ResultCallback<ArrayList<ContactsSessionInfo>> resultCallback) {
        this.m_historyContactCallback = resultCallback;
        int iIM_GetHistoryContact = IMEngine.IM_GetHistoryContact();
        if (iIM_GetHistoryContact == 0 || resultCallback == null) {
            return;
        }
        resultCallback.onFailed(iIM_GetHistoryContact, null);
    }

    public MessageSendStatus startAudioSpeech(boolean z) {
        MessageSendStatus messageSendStatus = new MessageSendStatus();
        IMEngine iMEngine = new IMEngine();
        iMEngine.getClass();
        IMEngine.MessageRequestId messageRequestId = iMEngine.new MessageRequestId();
        int iIM_StartAudioSpeech = IMEngine.IM_StartAudioSpeech(messageRequestId, z);
        if (iIM_StartAudioSpeech == 0) {
            SpeechMessageInfo speechMessageInfo = new SpeechMessageInfo();
            this.lastSpeechMsg = speechMessageInfo;
            speechMessageInfo.setRequestID(messageRequestId.getId());
        }
        messageSendStatus.setMessageId(messageRequestId.getId());
        messageSendStatus.setErrorCode(iIM_StartAudioSpeech);
        return messageSendStatus;
    }

    public void stopAudioSpeech(YIMEventCallback.SpeechEventCallback speechEventCallback) {
        SpeechMessageInfo speechMessageInfo = new SpeechMessageInfo();
        speechMessageInfo.setRequestID(0L);
        speechMessageInfo.setDownloadURL("");
        speechMessageInfo.setDuration(0);
        speechMessageInfo.setFileSize(0);
        speechMessageInfo.setLocalPath("");
        speechMessageInfo.setText("");
        if (this.lastSpeechMsg != null) {
            int iIM_StopAudioSpeech = IMEngine.IM_StopAudioSpeech();
            speechMessageInfo.setRequestID(this.lastSpeechMsg.getRequestID());
            if (iIM_StopAudioSpeech == 0) {
                if (speechEventCallback != null && !addUploadSpeechCallbackObj(this.lastSpeechMsg.getRequestID(), speechEventCallback)) {
                    speechEventCallback.onStopAudioSpeechStatus(YIMConstInfo.Errorcode.IsWaitingUpload, speechMessageInfo);
                }
            } else if (speechEventCallback != null) {
                speechEventCallback.onStopAudioSpeechStatus(iIM_StopAudioSpeech, speechMessageInfo);
            }
            this.lastSpeechMsg = null;
            return;
        }
        Log.e("YouMeIM", "Has no start record!");
        if (speechEventCallback != null) {
            speechEventCallback.onStopAudioSpeechStatus(2016, speechMessageInfo);
        }
    }

    public int convertAMRToWav(String str, String str2) {
        return IMEngine.IM_ConvertAMRToWav(str, str2);
    }

    public void queryHistoryMessage(String str, int i, long j, int i2, int i3, YIMEventCallback.ResultCallback<YIMHistoryMessage> resultCallback) {
        this.m_localHistoryMsgCallback = resultCallback;
        int iIM_QueryHistoryMessage = IMEngine.IM_QueryHistoryMessage(str, i, j, i2, i3);
        if (iIM_QueryHistoryMessage == 0 || resultCallback == null) {
            return;
        }
        resultCallback.onFailed(iIM_QueryHistoryMessage, null);
    }

    public int deleteHistoryMessageByID(long j) {
        return IMEngine.IM_DeleteHistoryMessageByID(j);
    }

    public int deleteHistoryMessageBeforeTime(int i, long j) {
        return IMEngine.IM_DeleteHistoryMessage(i, j);
    }

    public int switchMsgTransType(int i) {
        return IMEngine.IM_SwitchMsgTransType(i);
    }

    public int deleteSpecifiedHistoryMessage(String str, int i, long[] jArr) {
        return IMEngine.IM_DeleteSpecifiedHistoryMessage(str, i, jArr);
    }

    public int deleteHistoryMessageByTarget(String str, int i, long j, int i2) {
        return IMEngine.IM_DeleteHistoryMessageByTarget(str, i, j, i2);
    }

    public int setUserInfo(YIMExtraUserInfo yIMExtraUserInfo) {
        if (yIMExtraUserInfo == null) {
            return 3;
        }
        return IMEngine.IM_SetUserInfo(new GsonBuilder().create().toJson(yIMExtraUserInfo));
    }

    public void getUserInfo(String str, YIMEventCallback.ResultCallback<YIMExtraUserInfo> resultCallback) {
        this.m_userInfoCallback = resultCallback;
        int iIM_GetUserInfo = IMEngine.IM_GetUserInfo(str);
        if (iIM_GetUserInfo == 0 || resultCallback == null) {
            return;
        }
        resultCallback.onFailed(iIM_GetUserInfo, null);
    }

    public int setRoomHistoryMessageSwitch(List<String> list, boolean z) {
        return IMEngine.IM_SetRoomHistoryMessageSwitch(new JSONArray((Collection) list).toString(), z);
    }

    public void startPlayAudio(String str, YIMEventCallback.ResultCallback<String> resultCallback) {
        this.m_playCompleteCallback = resultCallback;
        if (isPlaying() && stopPlayAudio() != 0 && resultCallback != null) {
            this.m_playCompleteCallback = null;
            resultCallback.onFailed(YIMConstInfo.Errorcode.StopPlayFailBeforeStart, str);
        }
        int iIM_StartPlayAudio = IMEngine.IM_StartPlayAudio(str);
        if (iIM_StartPlayAudio == 0 || resultCallback == null) {
            return;
        }
        this.m_playCompleteCallback = null;
        resultCallback.onFailed(iIM_StartPlayAudio, str);
    }

    public int stopPlayAudio() {
        return IMEngine.IM_StopPlayAudio();
    }

    private boolean isPlaying() {
        return IMEngine.IM_IsPlaying();
    }

    public void setVolume(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > 1.0f) {
            f = 1.0f;
        }
        IMEngine.IM_SetVolume(f);
    }

    public String getAudioCachePath() {
        return IMEngine.IM_GetAudioCachePath();
    }

    public boolean clearAudioCachePath() {
        return IMEngine.IM_ClearAudioCachePath();
    }

    public void queryUserStatus(String str, YIMEventCallback.ResultCallback<UserStatusInfo> resultCallback) {
        this.m_userStatusCallback = resultCallback;
        int iIM_QueryUserStatus = IMEngine.IM_QueryUserStatus(str);
        if (iIM_QueryUserStatus == 0 || resultCallback == null) {
            return;
        }
        UserStatusInfo userStatusInfo = new UserStatusInfo();
        userStatusInfo.status = 2;
        userStatusInfo.userID = str;
        resultCallback.onFailed(iIM_QueryUserStatus, userStatusInfo);
        this.m_userStatusCallback = null;
    }

    public void translateText(String str, int i, int i2, YIMEventCallback.ResultCallback<TranlateTextInfo> resultCallback) {
        this.m_translateTextCallback = resultCallback;
        IMEngine iMEngine = new IMEngine();
        iMEngine.getClass();
        IMEngine.IntegerVal integerVal = iMEngine.new IntegerVal();
        int iIM_TranslateText = IMEngine.IM_TranslateText(integerVal, str, i, i2);
        if (iIM_TranslateText == 0 || resultCallback == null) {
            return;
        }
        TranlateTextInfo tranlateTextInfo = new TranlateTextInfo();
        tranlateTextInfo.requestID = integerVal.getValue();
        tranlateTextInfo.text = str;
        tranlateTextInfo.srcLangCode = i2;
        tranlateTextInfo.destLangCode = i;
        resultCallback.onFailed(iIM_TranslateText, tranlateTextInfo);
    }

    public void blockUser(String str, boolean z, YIMEventCallback.ResultCallback<BlockUserInfo> resultCallback) {
        this.m_blockUserCallback = resultCallback;
        int iIM_BlockUser = IMEngine.IM_BlockUser(str, z);
        if (iIM_BlockUser == 0 || resultCallback == null) {
            return;
        }
        BlockUserInfo blockUserInfo = new BlockUserInfo();
        blockUserInfo.setUserID(str);
        blockUserInfo.setBlock(false);
        resultCallback.onFailed(iIM_BlockUser, blockUserInfo);
    }

    public void unBlockAllUser(YIMEventCallback.OperationCallback operationCallback) {
        this.m_unBlockUserCallback = operationCallback;
        int iIM_UnBlockAllUser = IMEngine.IM_UnBlockAllUser();
        if (iIM_UnBlockAllUser == 0 || operationCallback == null) {
            return;
        }
        operationCallback.onFailed(iIM_UnBlockAllUser);
    }

    public void getBlockUsers(YIMEventCallback.ResultCallback<ArrayList<String>> resultCallback) {
        this.m_getBlockUsersCallback = resultCallback;
        int iIM_GetBlockUsers = IMEngine.IM_GetBlockUsers();
        if (iIM_GetBlockUsers == 0 || resultCallback == null) {
            return;
        }
        resultCallback.onFailed(iIM_GetBlockUsers, null);
    }

    public int getCurrentLocation() {
        return IMEngine.IM_GetCurrentLocation();
    }

    public void getNearbyObjects(int i, String str, int i2, boolean z, YIMEventCallback.ResultCallback<RelativeLocationInfo> resultCallback) {
        this.m_getNearbyObjCallback = resultCallback;
        int iIM_GetNearbyObjects = IMEngine.IM_GetNearbyObjects(i, str, i2, z);
        if (iIM_GetNearbyObjects == 0 || resultCallback == null) {
            return;
        }
        RelativeLocationInfo relativeLocationInfo = new RelativeLocationInfo();
        relativeLocationInfo.startDistance = 0;
        relativeLocationInfo.endDistance = 0;
        relativeLocationInfo.relativeLocations = null;
        resultCallback.onFailed(iIM_GetNearbyObjects, relativeLocationInfo);
    }

    public void getDistance(String str, YIMEventCallback.ResultCallback<UserDistanceInfo> resultCallback) {
        this.m_getDistanceCallback = resultCallback;
        int iIM_GetDistance = IMEngine.IM_GetDistance(str);
        if (iIM_GetDistance == 0 || resultCallback == null) {
            return;
        }
        UserDistanceInfo userDistanceInfo = new UserDistanceInfo();
        userDistanceInfo.userID = str;
        userDistanceInfo.distance = -1;
        resultCallback.onFailed(iIM_GetDistance, userDistanceInfo);
    }

    public void setUpdateInterval(int i) {
        IMEngine.IM_SetUpdateInterval(i);
    }

    public void getMicrophoneStatus(YIMEventCallback.GetMicStatusCallback getMicStatusCallback) {
        this.m_getMicStatusCallback = getMicStatusCallback;
        IMEngine.IM_GetMicrophoneStatus();
    }

    public int setSpeechRecognizeLanguage(int i) {
        return IMEngine.IM_SetSpeechRecognizeLanguage(i);
    }

    private int setOnlyRecognizeSpeechText(boolean z) {
        return IMEngine.IM_SetOnlyRecognizeSpeechText(z);
    }

    public int accusation(String str, int i, int i2, String str2, String str3) {
        return IMEngine.IM_Accusation(str, i, i2, str2, str3);
    }

    public int queryNotice() {
        return IMEngine.IM_QueryNotice();
    }

    public void getForbiddenSpeakInfo(YIMEventCallback.ResultCallback<ArrayList<YIMForbiddenSpeakInfo>> resultCallback) {
        this.m_forbidSpeakInfoCallback = resultCallback;
        int iIM_GetForbiddenSpeakInfo = IMEngine.IM_GetForbiddenSpeakInfo();
        if (iIM_GetForbiddenSpeakInfo == 0 || resultCallback == null) {
            return;
        }
        resultCallback.onFailed(iIM_GetForbiddenSpeakInfo, null);
    }

    public int setMessageRead(long j, boolean z) {
        return IMEngine.IM_SetMessageRead(j, z);
    }

    public int setAllMessageRead(String str, boolean z) {
        return IMEngine.IM_SetAllMessageRead(str, z);
    }

    public int setVoiceMsgPlayed(long j, boolean z) {
        return IMEngine.IM_SetVoiceMsgPlayed(j, z);
    }

    public void setUserProfileInfo(YIMUserSettingInfo yIMUserSettingInfo, YIMEventCallback.OperationCallback operationCallback) {
        if (yIMUserSettingInfo == null) {
            if (operationCallback != null) {
                operationCallback.onFailed(3);
                return;
            }
            return;
        }
        this.m_setprofileCallback = operationCallback;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("NickName", yIMUserSettingInfo.getNickName());
            jSONObject.put("Sex", String.valueOf(yIMUserSettingInfo.getSex()));
            jSONObject.put("Signature", yIMUserSettingInfo.getSignature());
            jSONObject.put("Country", yIMUserSettingInfo.getCountry());
            jSONObject.put("Province", yIMUserSettingInfo.getProvince());
            jSONObject.put("City", yIMUserSettingInfo.getCity());
            jSONObject.put("ExtraInfo", yIMUserSettingInfo.getExtraInfo());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int iIM_SetUserProfileInfo = IMEngine.IM_SetUserProfileInfo(jSONObject.toString());
        if (iIM_SetUserProfileInfo == 0 || operationCallback == null) {
            return;
        }
        operationCallback.onFailed(iIM_SetUserProfileInfo);
    }

    public void setUserProfilePhoto(String str, YIMEventCallback.ResultCallback<String> resultCallback) {
        this.m_photoUrlCallback = resultCallback;
        int iIM_SetUserProfilePhoto = IMEngine.IM_SetUserProfilePhoto(str);
        if (iIM_SetUserProfilePhoto == 0 || resultCallback == null) {
            return;
        }
        resultCallback.onFailed(iIM_SetUserProfilePhoto, "");
    }

    public void getUserProfileInfo(String str, YIMEventCallback.ResultCallback<UserProfileInfo> resultCallback) {
        this.m_userProfileCallback = resultCallback;
        int iIM_GetUserProfileInfo = IMEngine.IM_GetUserProfileInfo(str);
        if (iIM_GetUserProfileInfo == 0 || resultCallback == null) {
            return;
        }
        resultCallback.onFailed(iIM_GetUserProfileInfo, null);
    }

    public void switchUserStatus(String str, int i, YIMEventCallback.OperationCallback operationCallback) {
        YIMEventCallback.OperationCallback operationCallback2;
        this.m_switchStatusCallback = operationCallback;
        int iIM_SwitchUserStatus = IMEngine.IM_SwitchUserStatus(str, i);
        if (iIM_SwitchUserStatus == 0 || (operationCallback2 = this.m_switchStatusCallback) == null) {
            return;
        }
        operationCallback2.onFailed(iIM_SwitchUserStatus);
    }

    public void setAddPermission(boolean z, int i, YIMEventCallback.OperationCallback operationCallback) {
        this.m_setPermissionCallback = operationCallback;
        int iIM_SetAddPermission = IMEngine.IM_SetAddPermission(z, i);
        if (iIM_SetAddPermission == 0 || operationCallback == null) {
            return;
        }
        operationCallback.onFailed(iIM_SetAddPermission);
    }

    public void findUser(int i, String str, YIMEventCallback.ResultCallback<ArrayList<YIMUserBriefInfo>> resultCallback) {
        this.m_findUserCallback = resultCallback;
        int iIM_FindUser = IMEngine.IM_FindUser(i, str);
        if (iIM_FindUser == 0 || resultCallback == null) {
            return;
        }
        resultCallback.onFailed(iIM_FindUser, null);
    }

    public void requestAddFriend(List<String> list, String str, YIMEventCallback.ResultCallback<String> resultCallback) {
        this.m_requestAddCallback = resultCallback;
        int iIM_RequestAddFriend = IMEngine.IM_RequestAddFriend(new JSONArray((Collection) list).toString(), str);
        if (iIM_RequestAddFriend == 0 || resultCallback == null) {
            return;
        }
        resultCallback.onFailed(iIM_RequestAddFriend, "");
    }

    public void dealBeRequestAddFriend(String str, int i, YIMEventCallback.ResultCallback<YIMFriendDealResult> resultCallback) {
        this.m_dealAddCallback = resultCallback;
        int iIM_DealBeRequestAddFriend = IMEngine.IM_DealBeRequestAddFriend(str, i);
        if (iIM_DealBeRequestAddFriend == 0 || resultCallback == null) {
            return;
        }
        YIMFriendDealResult yIMFriendDealResult = new YIMFriendDealResult();
        yIMFriendDealResult.userID = str;
        yIMFriendDealResult.comments = "";
        yIMFriendDealResult.dealResult = -1;
        resultCallback.onFailed(iIM_DealBeRequestAddFriend, yIMFriendDealResult);
    }

    public void deleteFriend(List<String> list, int i, YIMEventCallback.ResultCallback<String> resultCallback) {
        this.m_deleteFriendCallback = resultCallback;
        int iIM_DeleteFriend = IMEngine.IM_DeleteFriend(new JSONArray((Collection) list).toString(), i);
        if (iIM_DeleteFriend == 0 || resultCallback == null) {
            return;
        }
        resultCallback.onFailed(iIM_DeleteFriend, "");
    }

    public void blackFriend(int i, List<String> list, YIMEventCallback.ResultCallback<YIMBlackFriendInfo> resultCallback) {
        this.m_blackFriendCallback = resultCallback;
        int iIM_BlackFriend = IMEngine.IM_BlackFriend(i, new JSONArray((Collection) list).toString());
        if (iIM_BlackFriend == 0 || resultCallback == null) {
            return;
        }
        YIMBlackFriendInfo yIMBlackFriendInfo = new YIMBlackFriendInfo();
        yIMBlackFriendInfo.userID = "";
        yIMBlackFriendInfo.type = i;
        resultCallback.onFailed(iIM_BlackFriend, yIMBlackFriendInfo);
    }

    public void queryFriends(int i, int i2, int i3, YIMEventCallback.ResultCallback<YIMFriendListInfo> resultCallback) {
        this.m_queryFriendsCallback = resultCallback;
        int iIM_QueryFriends = IMEngine.IM_QueryFriends(i, i2, i3);
        if (iIM_QueryFriends == 0 || resultCallback == null) {
            return;
        }
        YIMFriendListInfo yIMFriendListInfo = new YIMFriendListInfo();
        yIMFriendListInfo.startIndex = i2;
        yIMFriendListInfo.type = i;
        yIMFriendListInfo.userList = null;
        resultCallback.onFailed(iIM_QueryFriends, yIMFriendListInfo);
    }

    public void queryFriendRequestList(int i, int i2, YIMEventCallback.ResultCallback<YIMFriendRequestInfoList> resultCallback) {
        this.m_requestListCallback = resultCallback;
        int iIM_QueryFriendRequestList = IMEngine.IM_QueryFriendRequestList(i, i2);
        if (iIM_QueryFriendRequestList == 0 || resultCallback == null) {
            return;
        }
        YIMFriendRequestInfoList yIMFriendRequestInfoList = new YIMFriendRequestInfoList();
        yIMFriendRequestInfoList.startIndex = i;
        yIMFriendRequestInfoList.userList = null;
        resultCallback.onFailed(iIM_QueryFriendRequestList, yIMFriendRequestInfoList);
    }

    public void setShortConnectionMode() {
        IMEngine.IM_SetShortConnectionMode();
    }

    private boolean addJoinChannelCallbackObj(String str, YIMEventCallback.ResultCallback<ChatRoom> resultCallback) {
        if (!this.joinChannelCallbackQueue.containsKey(str)) {
            this.joinChannelCallbackQueue.put(str, resultCallback);
            return true;
        }
        Log.e("YouMeIM", "channel id is already in joining queue.");
        return false;
    }

    private boolean addLeaveChannelCallbackObj(String str, YIMEventCallback.ResultCallback<ChatRoom> resultCallback) {
        if (!this.leaveChannelCallbackQueue.containsKey(str)) {
            this.leaveChannelCallbackQueue.put(str, resultCallback);
            return true;
        }
        Log.e("YouMeIM", "channel id is already in leaving queue.");
        return false;
    }

    public static final class MessageCallbackObject<T> {
        public T callback;
        public YIMMessage message;
        public int msgType;

        public MessageCallbackObject(int i, YIMMessage yIMMessage, T t) {
            this.msgType = i;
            this.message = yIMMessage;
            this.callback = t;
        }
    }

    private boolean addMessageCallbackObj(long j, MessageCallbackObject messageCallbackObject) {
        if (!this.messageCallbackQueue.containsKey(Long.valueOf(j))) {
            this.messageCallbackQueue.put(Long.valueOf(j), messageCallbackObject);
            return true;
        }
        Log.e("YouMeIM", "message id is already in sending queue.");
        return false;
    }

    private boolean addDownloadMsgCallbackObj(long j, YIMEventCallback.DownloadFileCallback downloadFileCallback) {
        if (!this.downloadMsgCallbackQueue.containsKey(Long.valueOf(j))) {
            this.downloadMsgCallbackQueue.put(Long.valueOf(j), downloadFileCallback);
            return true;
        }
        Log.e("YouMeIM", "message id is already in downloading queue.");
        return false;
    }

    private boolean addUrlDownloadCallbackObj(String str, YIMEventCallback.DownloadByUrlCallback downloadByUrlCallback) {
        if (!this.downloadByUrlCallbackQueue.containsKey(str)) {
            this.downloadByUrlCallbackQueue.put(str, downloadByUrlCallback);
            return true;
        }
        Log.e("YouMeIM", "message id is already in downloading queue.");
        return false;
    }

    private boolean addUploadSpeechCallbackObj(long j, YIMEventCallback.SpeechEventCallback speechEventCallback) {
        if (!this.uploadSpeechCallbackQueue.containsKey(Long.valueOf(j))) {
            this.uploadSpeechCallbackQueue.put(Long.valueOf(j), speechEventCallback);
            return true;
        }
        Log.e("YouMeIM", "message id is already in uploading queue.");
        return false;
    }

    public static final class MessageSendStatus {
        private int errCode;
        private long msgId;

        public MessageSendStatus() {
        }

        public MessageSendStatus(int i, long j) {
            this.errCode = i;
            this.msgId = j;
        }

        public void setErrorCode(int i) {
            this.errCode = i;
        }

        public void setMessageId(long j) {
            this.msgId = j;
        }

        public int getErrorCode() {
            return this.errCode;
        }

        public long getMessageId() {
            return this.msgId;
        }
    }

    public static final class TranslateMsgStatus {
        private int errCode;
        private long msgId;

        public void setErrorCode(int i) {
            this.errCode = i;
        }

        public void setMessageId(long j) {
            this.msgId = j;
        }

        public int getErrorCode() {
            return this.errCode;
        }

        public long getMessageId() {
            return this.msgId;
        }
    }

    private boolean checkString(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!TextUtils.isEmpty(str2)) {
            return true;
        }
        debugInfo(str + " is null");
        return false;
    }

    private void debugInfo(String str) {
        if (DEBUG) {
            Log.e(TAG, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlerMessage(String str) {
        Gson gson;
        IYIMMessageBodyBase yIMMessageBodyGift;
        MessageCallbackObject messageCallbackObject;
        if (str == null || str.equals("")) {
            return;
        }
        try {
            gson = new Gson();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (str != null) {
            YouMeIMJsonResponse youMeIMJsonResponse = (YouMeIMJsonResponse) gson.fromJson(str, YouMeIMJsonResponse.class);
            int iIntValue = youMeIMJsonResponse.command.intValue();
            if (iIntValue == 1) {
                try {
                    Login login = (Login) gson.fromJson(str, Login.class);
                    if (youMeIMJsonResponse.errcode.intValue() == 0) {
                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_loginCallback, "onSuccess", login.getYoumeId()));
                        this.m_currentUserID = login.getYoumeId();
                    } else {
                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_loginCallback, "onFailed", youMeIMJsonResponse.errcode, login.getYoumeId()));
                    }
                    return;
                } catch (NullPointerException e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 7) {
                SendVoiceMsgInfo sendVoiceMsgInfo = (SendVoiceMsgInfo) gson.fromJson(str, SendVoiceMsgInfo.class);
                if (this.messageCallbackQueue.containsKey(Long.valueOf(sendVoiceMsgInfo.getRequestId()))) {
                    MessageCallbackObject messageCallbackObject2 = this.messageCallbackQueue.get(Long.valueOf(sendVoiceMsgInfo.getRequestId()));
                    if (messageCallbackObject2 != null && messageCallbackObject2.callback != 0) {
                        try {
                            this.mHandler.post(new YIMCallBackProtocolV2((YIMEventCallback.AudioMsgEventCallback) messageCallbackObject2.callback, "onSendAudioMessageStatus", youMeIMJsonResponse.errcode, sendVoiceMsgInfo));
                        } catch (NullPointerException e3) {
                            e3.printStackTrace();
                        }
                    }
                    this.messageCallbackQueue.remove(Long.valueOf(sendVoiceMsgInfo.getRequestId()));
                    return;
                }
                return;
            }
            if (iIntValue == 11) {
                try {
                    this.mHandler.post(new YIMCallBackProtocolV2(this.m_kickOffCallback, "onKickOff", new Object[0]));
                    return;
                } catch (NullPointerException e4) {
                    e4.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 31) {
                Log.d("CMD_GET_PEOPLE_NEARBY", "msg = " + str);
                YIMForbiddenSpeakList yIMForbiddenSpeakList = (YIMForbiddenSpeakList) gson.fromJson(str, YIMForbiddenSpeakList.class);
                try {
                    if (youMeIMJsonResponse.errcode.intValue() == 0) {
                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_forbidSpeakInfoCallback, "onSuccess", yIMForbiddenSpeakList.forbiddenList));
                    } else {
                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_forbidSpeakInfoCallback, "onFailed", youMeIMJsonResponse.errcode, yIMForbiddenSpeakList.forbiddenList));
                    }
                    return;
                } catch (NullPointerException e5) {
                    e5.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 40) {
                YIMUserBriefInfoList yIMUserBriefInfoList = (YIMUserBriefInfoList) gson.fromJson(str, YIMUserBriefInfoList.class);
                try {
                    if (youMeIMJsonResponse.errcode.intValue() == 0) {
                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_findUserCallback, "onSuccess", yIMUserBriefInfoList.userList));
                    } else {
                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_findUserCallback, "onFailed", youMeIMJsonResponse.errcode, yIMUserBriefInfoList.userList));
                    }
                    return;
                } catch (NullPointerException e6) {
                    e6.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 42) {
                YIMFriendUserID yIMFriendUserID = (YIMFriendUserID) gson.fromJson(str, YIMFriendUserID.class);
                try {
                    if (youMeIMJsonResponse.errcode.intValue() == 0) {
                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_requestAddCallback, "onSuccess", yIMFriendUserID.userID));
                    } else {
                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_requestAddCallback, "onFailed", youMeIMJsonResponse.errcode, yIMFriendUserID.userID));
                    }
                    return;
                } catch (NullPointerException e7) {
                    e7.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 51) {
                YIMFriendRequestInfoList yIMFriendRequestInfoList = (YIMFriendRequestInfoList) gson.fromJson(str, YIMFriendRequestInfoList.class);
                try {
                    if (youMeIMJsonResponse.errcode.intValue() == 0) {
                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_requestListCallback, "onSuccess", yIMFriendRequestInfoList));
                    } else {
                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_requestListCallback, "onFailed", youMeIMJsonResponse.errcode, yIMFriendRequestInfoList));
                    }
                    return;
                } catch (NullPointerException e8) {
                    e8.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 3) {
                try {
                    if (youMeIMJsonResponse.errcode.intValue() == 0) {
                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_logoutCallback, "onSuccess", new Object[0]));
                    } else {
                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_logoutCallback, "onFailed", youMeIMJsonResponse.errcode));
                    }
                    return;
                } catch (NullPointerException e9) {
                    e9.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 4) {
                ChatRoom chatRoom = (ChatRoom) gson.fromJson(str, ChatRoom.class);
                if (this.joinChannelCallbackQueue.containsKey(chatRoom.groupId)) {
                    YIMEventCallback.ResultCallback<ChatRoom> resultCallback = this.joinChannelCallbackQueue.get(chatRoom.groupId);
                    try {
                        if (youMeIMJsonResponse.errcode.intValue() == 0) {
                            this.mHandler.post(new YIMCallBackProtocolV2(resultCallback, "onSuccess", chatRoom));
                        } else {
                            this.mHandler.post(new YIMCallBackProtocolV2(resultCallback, "onFailed", youMeIMJsonResponse.errcode, chatRoom));
                        }
                    } catch (NullPointerException e10) {
                        e10.printStackTrace();
                    }
                    this.joinChannelCallbackQueue.remove(chatRoom.groupId);
                    return;
                }
                return;
            }
            if (iIntValue == 5) {
                ChatRoom chatRoom2 = (ChatRoom) gson.fromJson(str, ChatRoom.class);
                if (this.leaveChannelCallbackQueue.containsKey(chatRoom2.groupId)) {
                    YIMEventCallback.ResultCallback<ChatRoom> resultCallback2 = this.leaveChannelCallbackQueue.get(chatRoom2.groupId);
                    try {
                        if (youMeIMJsonResponse.errcode.intValue() == 0) {
                            this.mHandler.post(new YIMCallBackProtocolV2(resultCallback2, "onSuccess", chatRoom2));
                        } else {
                            this.mHandler.post(new YIMCallBackProtocolV2(resultCallback2, "onFailed", youMeIMJsonResponse.errcode, chatRoom2));
                        }
                    } catch (NullPointerException e11) {
                        e11.printStackTrace();
                    }
                    this.leaveChannelCallbackQueue.remove(chatRoom2.groupId);
                    return;
                }
                return;
            }
            IYIMMessageBodyBase yIMMessageBodyFile = null;
            if (iIntValue == 16) {
                RoomHistoryMessage roomHistoryMessage = (RoomHistoryMessage) gson.fromJson(str, RoomHistoryMessage.class);
                ArrayList arrayList = new ArrayList();
                if (roomHistoryMessage.messageList != null && roomHistoryMessage.messageList.size() > 0) {
                    Iterator<RecvMessage> it = roomHistoryMessage.messageList.iterator();
                    while (it.hasNext()) {
                        YIMMessage yIMMessage = new YIMMessage();
                        RecvMessage next = it.next();
                        yIMMessage.setChatType(next.chatType.intValue());
                        yIMMessage.setReceiveID(next.receiveId);
                        yIMMessage.setSenderID(next.senderId);
                        Iterator<RecvMessage> it2 = it;
                        yIMMessage.setMeesageID(next.serial.longValue());
                        yIMMessage.setMessageType(next.msgType.intValue());
                        yIMMessage.setCreateTime(next.createTime.intValue());
                        yIMMessage.setDistance(next.distance.intValue());
                        yIMMessage.setRead(next.getIsRead());
                        if (next.msgType.intValue() == 1) {
                            yIMMessageBodyGift = new YIMMessageBodyText();
                            ((YIMMessageBodyText) yIMMessageBodyGift).setMessageContent(next.content);
                            ((YIMMessageBodyText) yIMMessageBodyGift).setAttachParam(next.attachParam);
                        } else if (next.msgType.intValue() == 2) {
                            yIMMessageBodyGift = new YIMMessageBodyCustom();
                            ((YIMMessageBodyCustom) yIMMessageBodyGift).setMessageContent(next.content);
                        } else if (next.msgType.intValue() == 5) {
                            yIMMessageBodyGift = new YIMMessageBodyAudio();
                            ((YIMMessageBodyAudio) yIMMessageBodyGift).setAudioTime(next.duration.intValue());
                            ((YIMMessageBodyAudio) yIMMessageBodyGift).setParam(next.param);
                            ((YIMMessageBodyAudio) yIMMessageBodyGift).setText(next.extraText);
                        } else if (next.msgType.intValue() == 7) {
                            yIMMessageBodyGift = new YIMMessageBodyFile();
                            ((YIMMessageBodyFile) yIMMessageBodyGift).setFileType(next.fileType.intValue());
                            ((YIMMessageBodyFile) yIMMessageBodyGift).setFileSize(next.fileSize.intValue());
                            ((YIMMessageBodyFile) yIMMessageBodyGift).setFileExtension(next.fileExtension);
                            ((YIMMessageBodyFile) yIMMessageBodyGift).setExtParam(next.extraParam);
                            ((YIMMessageBodyFile) yIMMessageBodyGift).setFileName(next.fileName);
                        } else if (next.msgType.intValue() == 8) {
                            yIMMessageBodyGift = new YIMMessageBodyGift();
                            ((YIMMessageBodyGift) yIMMessageBodyGift).setAnchor(next.anchor);
                            ((YIMMessageBodyGift) yIMMessageBodyGift).setGiftCount(next.giftCount);
                            ((YIMMessageBodyGift) yIMMessageBodyGift).setGiftID(next.giftID);
                            try {
                                ((YIMMessageBodyGift) yIMMessageBodyGift).setExtParam((YIMExtraGifParam) gson.fromJson(next.param, YIMExtraGifParam.class));
                            } catch (Exception e12) {
                                e12.printStackTrace();
                                return;
                            }
                        } else {
                            yIMMessageBodyGift = null;
                        }
                        yIMMessage.setMessageBody(yIMMessageBodyGift);
                        arrayList.add(yIMMessage);
                        it = it2;
                    }
                }
                RoomHistoryMsgInfo roomHistoryMsgInfo = new RoomHistoryMsgInfo(roomHistoryMessage.roomID, roomHistoryMessage.remain.intValue(), arrayList);
                try {
                    if (youMeIMJsonResponse.errcode.intValue() == 0) {
                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_roomMsgCallback, "onSuccess", roomHistoryMsgInfo));
                    } else {
                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_roomMsgCallback, "onFailed", youMeIMJsonResponse.errcode, roomHistoryMsgInfo));
                    }
                    return;
                } catch (NullPointerException e13) {
                    e13.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 17) {
                UserInfoString userInfoString = (UserInfoString) gson.fromJson(str, UserInfoString.class);
                YIMExtraUserInfo yIMExtraUserInfo = (YIMExtraUserInfo) gson.fromJson(userInfoString.userInfoString, YIMExtraUserInfo.class);
                yIMExtraUserInfo.setUserID(userInfoString.userID);
                try {
                    if (youMeIMJsonResponse.errcode.intValue() == 0) {
                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_userInfoCallback, "onSuccess", yIMExtraUserInfo));
                    } else {
                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_userInfoCallback, "onFailed", youMeIMJsonResponse.errcode, yIMExtraUserInfo));
                    }
                    return;
                } catch (NullPointerException e14) {
                    e14.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 45) {
                YIMFriendUserID yIMFriendUserID2 = (YIMFriendUserID) gson.fromJson(str, YIMFriendUserID.class);
                try {
                    if (youMeIMJsonResponse.errcode.intValue() == 0) {
                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_deleteFriendCallback, "onSuccess", yIMFriendUserID2.userID));
                    } else {
                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_deleteFriendCallback, "onFailed", youMeIMJsonResponse.errcode, yIMFriendUserID2.userID));
                    }
                    return;
                } catch (NullPointerException e15) {
                    e15.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 46) {
                YIMBlackFriendInfo yIMBlackFriendInfo = (YIMBlackFriendInfo) gson.fromJson(str, YIMBlackFriendInfo.class);
                try {
                    if (youMeIMJsonResponse.errcode.intValue() == 0) {
                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_blackFriendCallback, "onSuccess", yIMBlackFriendInfo));
                    } else {
                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_blackFriendCallback, "onFailed", youMeIMJsonResponse.errcode, yIMBlackFriendInfo));
                    }
                    return;
                } catch (NullPointerException e16) {
                    e16.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 48) {
                YIMFriendDealResult yIMFriendDealResult = (YIMFriendDealResult) gson.fromJson(str, YIMFriendDealResult.class);
                try {
                    if (youMeIMJsonResponse.errcode.intValue() == 0) {
                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_dealAddCallback, "onSuccess", yIMFriendDealResult));
                    } else {
                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_dealAddCallback, "onFailed", youMeIMJsonResponse.errcode, yIMFriendDealResult));
                    }
                    return;
                } catch (NullPointerException e17) {
                    e17.printStackTrace();
                    return;
                }
            }
            if (iIntValue != 49) {
                switch (iIntValue) {
                    case 20:
                        AccusationResult accusationResult = (AccusationResult) gson.fromJson(str, AccusationResult.class);
                        try {
                            this.mHandler.post(new YIMCallBackProtocolV2(this.m_accusationCallback, "onAccusationResultNotify", Integer.valueOf(accusationResult.iResult), accusationResult.strUserID, Integer.valueOf(accusationResult.iAccusationTime)));
                            return;
                        } catch (NullPointerException e18) {
                            e18.printStackTrace();
                            return;
                        }
                    case 21:
                        Log.d("CMD_GET_DISTRICT", "msg = " + str);
                        try {
                            this.mHandler.post(new YIMCallBackProtocolV2(this.m_getLocationCallback, "onUpdateLocation", youMeIMJsonResponse.errcode, (GeographyLocation) gson.fromJson(str, GeographyLocation.class)));
                            return;
                        } catch (NullPointerException e19) {
                            e19.printStackTrace();
                            return;
                        }
                    case 22:
                        Log.d("CMD_GET_PEOPLE_NEARBY", "msg = " + str);
                        RelativeLocationInfo relativeLocationInfo = (RelativeLocationInfo) gson.fromJson(str, RelativeLocationInfo.class);
                        try {
                            if (youMeIMJsonResponse.errcode.intValue() == 0) {
                                this.mHandler.post(new YIMCallBackProtocolV2(this.m_getNearbyObjCallback, "onSuccess", relativeLocationInfo));
                            } else {
                                this.mHandler.post(new YIMCallBackProtocolV2(this.m_getNearbyObjCallback, "onFailed", youMeIMJsonResponse.errcode, relativeLocationInfo));
                            }
                            return;
                        } catch (NullPointerException e20) {
                            e20.printStackTrace();
                            return;
                        }
                    default:
                        switch (iIntValue) {
                            case 24:
                                BlockUserInfo blockUserInfo = (BlockUserInfo) gson.fromJson(str, BlockUserInfo.class);
                                try {
                                    if (youMeIMJsonResponse.errcode.intValue() == 0) {
                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_blockUserCallback, "onSuccess", blockUserInfo));
                                    } else {
                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_blockUserCallback, "onFailed", youMeIMJsonResponse.errcode, blockUserInfo));
                                    }
                                    return;
                                } catch (NullPointerException e21) {
                                    e21.printStackTrace();
                                    return;
                                }
                            case 25:
                                BlockUserList blockUserList = (BlockUserList) gson.fromJson(str, BlockUserList.class);
                                try {
                                    if (youMeIMJsonResponse.errcode.intValue() == 0) {
                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_getBlockUsersCallback, "onSuccess", blockUserList.userList));
                                    } else {
                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_getBlockUsersCallback, "onFailed", youMeIMJsonResponse.errcode, blockUserList.userList));
                                    }
                                    return;
                                } catch (NullPointerException e22) {
                                    e22.printStackTrace();
                                    return;
                                }
                            case 26:
                                try {
                                    if (youMeIMJsonResponse.errcode.intValue() == 0) {
                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_unBlockUserCallback, "onSuccess", new Object[0]));
                                    } else {
                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_unBlockUserCallback, "onFailed", youMeIMJsonResponse.errcode));
                                    }
                                    return;
                                } catch (NullPointerException e23) {
                                    e23.printStackTrace();
                                    return;
                                }
                            case 27:
                                try {
                                    RoomInfo roomInfo = (RoomInfo) gson.fromJson(str, RoomInfo.class);
                                    if (youMeIMJsonResponse.errcode.intValue() == 0) {
                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_roomInfoCallback, "onSuccess", roomInfo));
                                    } else {
                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_roomInfoCallback, "onFailed", youMeIMJsonResponse.errcode, roomInfo));
                                    }
                                    return;
                                } catch (NullPointerException e24) {
                                    e24.printStackTrace();
                                    return;
                                }
                            case 28:
                                try {
                                    if (youMeIMJsonResponse.errcode.intValue() == 0) {
                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_leaveAllCallback, "onSuccess", new Object[0]));
                                    } else {
                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_leaveAllCallback, "onFailed", youMeIMJsonResponse.errcode));
                                    }
                                    return;
                                } catch (NullPointerException e25) {
                                    e25.printStackTrace();
                                    return;
                                }
                            default:
                                switch (iIntValue) {
                                    case 74:
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocolV2(this.m_userProfileChangeCallback, "onUserInfoChangeNotify", ((YIMFriendUserID) gson.fromJson(str, YIMFriendUserID.class)).userID));
                                            return;
                                        } catch (NullPointerException e26) {
                                            e26.printStackTrace();
                                            return;
                                        }
                                    case 75:
                                        UploadProgress uploadProgress = (UploadProgress) gson.fromJson(str, UploadProgress.class);
                                        if (this.uploadProgressCallbackQueue.containsKey(Long.valueOf(uploadProgress.getRequestId()))) {
                                            YIMEventCallback.ShowUploadFileProgressCallback showUploadFileProgressCallback = this.uploadProgressCallbackQueue.get(Long.valueOf(uploadProgress.getRequestId()));
                                            if (showUploadFileProgressCallback != null && youMeIMJsonResponse.errcode.intValue() == 0) {
                                                showUploadFileProgressCallback.onShowUploadFileProgress(uploadProgress.getPercent());
                                            }
                                            if (uploadProgress.getPercent() >= 100.0d) {
                                                this.uploadProgressCallbackQueue.remove(Long.valueOf(uploadProgress.getRequestId()));
                                                return;
                                            }
                                            return;
                                        }
                                        return;
                                    case 76:
                                        MsgReadStatus msgReadStatus = (MsgReadStatus) gson.fromJson(str, MsgReadStatus.class);
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocolV2(this.m_updateReadStatusCallback, "onRead", msgReadStatus.getRecvId(), Integer.valueOf(msgReadStatus.getChatType()), Long.valueOf(msgReadStatus.getMsgId())));
                                            return;
                                        } catch (NullPointerException e27) {
                                            e27.printStackTrace();
                                            return;
                                        }
                                    default:
                                        switch (iIntValue) {
                                            case 20001:
                                                Download download = (Download) gson.fromJson(str, Download.class);
                                                YIMMessage yIMMessage2 = new YIMMessage();
                                                RecvMessage recvMessage = (RecvMessage) gson.fromJson(str, RecvMessage.class);
                                                yIMMessage2.setChatType(recvMessage.chatType.intValue());
                                                yIMMessage2.setReceiveID(recvMessage.receiveId);
                                                yIMMessage2.setSenderID(recvMessage.senderId);
                                                yIMMessage2.setMeesageID(recvMessage.serial.longValue());
                                                yIMMessage2.setMessageType(recvMessage.msgType.intValue());
                                                yIMMessage2.setCreateTime(recvMessage.createTime.intValue());
                                                yIMMessage2.setDistance(recvMessage.distance.intValue());
                                                yIMMessage2.setRead(recvMessage.getIsRead());
                                                if (recvMessage.msgType.intValue() == 5) {
                                                    yIMMessageBodyFile = new YIMMessageBodyAudio();
                                                    ((YIMMessageBodyAudio) yIMMessageBodyFile).setAudioTime(recvMessage.duration.intValue());
                                                    ((YIMMessageBodyAudio) yIMMessageBodyFile).setParam(recvMessage.param);
                                                    ((YIMMessageBodyAudio) yIMMessageBodyFile).setText(recvMessage.extraText);
                                                } else if (recvMessage.msgType.intValue() == 7) {
                                                    yIMMessageBodyFile = new YIMMessageBodyFile();
                                                    ((YIMMessageBodyFile) yIMMessageBodyFile).setFileType(recvMessage.fileType.intValue());
                                                    ((YIMMessageBodyFile) yIMMessageBodyFile).setFileSize(recvMessage.fileSize.intValue());
                                                    ((YIMMessageBodyFile) yIMMessageBodyFile).setFileExtension(recvMessage.fileExtension);
                                                    ((YIMMessageBodyFile) yIMMessageBodyFile).setExtParam(recvMessage.extraParam);
                                                    ((YIMMessageBodyFile) yIMMessageBodyFile).setFileName(recvMessage.fileName);
                                                }
                                                yIMMessage2.setMessageBody(yIMMessageBodyFile);
                                                if (this.downloadMsgCallbackQueue.containsKey(Long.valueOf(yIMMessage2.getMessageID()))) {
                                                    try {
                                                        this.mHandler.post(new YIMCallBackProtocolV2(this.downloadMsgCallbackQueue.get(Long.valueOf(yIMMessage2.getMessageID())), "onDownload", youMeIMJsonResponse.errcode, yIMMessage2, download.savePath));
                                                    } catch (NullPointerException e28) {
                                                        e28.printStackTrace();
                                                    }
                                                    this.downloadMsgCallbackQueue.remove(Long.valueOf(yIMMessage2.getMessageID()));
                                                    break;
                                                }
                                                if (this.m_autoDownloadCallback != null) {
                                                    this.m_autoDownloadCallback.onAutoDownload(youMeIMJsonResponse.errcode.intValue(), yIMMessage2, download.savePath);
                                                    return;
                                                }
                                                return;
                                            case CMD_SEND_MESSAGE_STATUS /* 20002 */:
                                                SendMessage sendMessage = (SendMessage) gson.fromJson(str, SendMessage.class);
                                                if (this.messageCallbackQueue.containsKey(Long.valueOf(sendMessage.getRequestId()))) {
                                                    MessageCallbackObject messageCallbackObject3 = this.messageCallbackQueue.get(Long.valueOf(sendMessage.getRequestId()));
                                                    if (messageCallbackObject3 != null && messageCallbackObject3.callback != 0) {
                                                        try {
                                                            if (youMeIMJsonResponse.errcode.intValue() == 0) {
                                                                this.mHandler.post(new YIMCallBackProtocolV2((YIMEventCallback.ResultCallback) messageCallbackObject3.callback, "onSuccess", sendMessage));
                                                            } else {
                                                                this.mHandler.post(new YIMCallBackProtocolV2((YIMEventCallback.ResultCallback) messageCallbackObject3.callback, "onFailed", youMeIMJsonResponse.errcode, sendMessage));
                                                            }
                                                        } catch (NullPointerException e29) {
                                                            e29.printStackTrace();
                                                        }
                                                        break;
                                                    }
                                                    this.messageCallbackQueue.remove(Long.valueOf(sendMessage.getRequestId()));
                                                    return;
                                                }
                                                return;
                                            case CMD_RECV_MESSAGE /* 20003 */:
                                                YIMMessage yIMMessage3 = new YIMMessage();
                                                RecvMessage recvMessage2 = (RecvMessage) gson.fromJson(str, RecvMessage.class);
                                                yIMMessage3.setChatType(recvMessage2.chatType.intValue());
                                                yIMMessage3.setReceiveID(recvMessage2.receiveId);
                                                yIMMessage3.setSenderID(recvMessage2.senderId);
                                                yIMMessage3.setMeesageID(recvMessage2.serial.longValue());
                                                yIMMessage3.setMessageType(recvMessage2.msgType.intValue());
                                                yIMMessage3.setCreateTime(recvMessage2.createTime.intValue());
                                                yIMMessage3.setDistance(recvMessage2.distance.intValue());
                                                yIMMessage3.setRead(recvMessage2.getIsRead());
                                                if (recvMessage2.msgType.intValue() == 1) {
                                                    yIMMessageBodyFile = new YIMMessageBodyText();
                                                    ((YIMMessageBodyText) yIMMessageBodyFile).setMessageContent(recvMessage2.content);
                                                    ((YIMMessageBodyText) yIMMessageBodyFile).setAttachParam(recvMessage2.attachParam);
                                                } else if (recvMessage2.msgType.intValue() == 2) {
                                                    yIMMessageBodyFile = new YIMMessageBodyCustom();
                                                    ((YIMMessageBodyCustom) yIMMessageBodyFile).setMessageContent(recvMessage2.content);
                                                } else if (recvMessage2.msgType.intValue() == 5) {
                                                    yIMMessageBodyFile = new YIMMessageBodyAudio();
                                                    ((YIMMessageBodyAudio) yIMMessageBodyFile).setAudioTime(recvMessage2.duration.intValue());
                                                    ((YIMMessageBodyAudio) yIMMessageBodyFile).setParam(recvMessage2.param);
                                                    ((YIMMessageBodyAudio) yIMMessageBodyFile).setText(recvMessage2.extraText);
                                                } else if (recvMessage2.msgType.intValue() == 7) {
                                                    yIMMessageBodyFile = new YIMMessageBodyFile();
                                                    ((YIMMessageBodyFile) yIMMessageBodyFile).setFileType(recvMessage2.fileType.intValue());
                                                    ((YIMMessageBodyFile) yIMMessageBodyFile).setFileSize(recvMessage2.fileSize.intValue());
                                                    ((YIMMessageBodyFile) yIMMessageBodyFile).setFileExtension(recvMessage2.fileExtension);
                                                    ((YIMMessageBodyFile) yIMMessageBodyFile).setExtParam(recvMessage2.extraParam);
                                                    ((YIMMessageBodyFile) yIMMessageBodyFile).setFileName(recvMessage2.fileName);
                                                } else if (recvMessage2.msgType.intValue() == 8) {
                                                    yIMMessageBodyFile = new YIMMessageBodyGift();
                                                    ((YIMMessageBodyGift) yIMMessageBodyFile).setAnchor(recvMessage2.anchor);
                                                    ((YIMMessageBodyGift) yIMMessageBodyFile).setGiftCount(recvMessage2.giftCount);
                                                    ((YIMMessageBodyGift) yIMMessageBodyFile).setGiftID(recvMessage2.giftID);
                                                    try {
                                                        YIMExtraGifParam yIMExtraGifParam = (YIMExtraGifParam) gson.fromJson(recvMessage2.param, YIMExtraGifParam.class);
                                                        if (DEBUG) {
                                                            Log.d(TAG, "recv giftExtParam:" + yIMExtraGifParam.getNickName());
                                                        }
                                                        ((YIMMessageBodyGift) yIMMessageBodyFile).setExtParam(yIMExtraGifParam);
                                                    } catch (Exception e30) {
                                                        e30.printStackTrace();
                                                        return;
                                                    }
                                                }
                                                yIMMessage3.setMessageBody(yIMMessageBodyFile);
                                                try {
                                                    this.mHandler.post(new YIMCallBackProtocolV2(this.m_msgEventCallback, "onRecvMessage", yIMMessage3));
                                                    return;
                                                } catch (NullPointerException e31) {
                                                    e31.printStackTrace();
                                                    return;
                                                }
                                            case CMD_STOP_AUDIOSPEECH /* 20004 */:
                                                SpeechMessageInfo speechMessageInfo = (SpeechMessageInfo) gson.fromJson(str, SpeechMessageInfo.class);
                                                if (this.uploadSpeechCallbackQueue.containsKey(Long.valueOf(speechMessageInfo.getRequestID()))) {
                                                    try {
                                                        this.mHandler.post(new YIMCallBackProtocolV2(this.uploadSpeechCallbackQueue.get(Long.valueOf(speechMessageInfo.getRequestID())), "onStopAudioSpeechStatus", youMeIMJsonResponse.errcode, speechMessageInfo));
                                                        break;
                                                    } catch (NullPointerException e32) {
                                                        e32.printStackTrace();
                                                    }
                                                    this.messageCallbackQueue.remove(Long.valueOf(speechMessageInfo.getRequestID()));
                                                    return;
                                                }
                                                return;
                                            case CMD_QUERY_HISTORY_MESSAGE /* 20005 */:
                                                try {
                                                    YIMHistoryMessage yIMHistoryMessage = (YIMHistoryMessage) gson.fromJson(str, YIMHistoryMessage.class);
                                                    if (youMeIMJsonResponse.errcode.intValue() == 0) {
                                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_localHistoryMsgCallback, "onSuccess", yIMHistoryMessage));
                                                    } else {
                                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_localHistoryMsgCallback, "onFailed", youMeIMJsonResponse.errcode, yIMHistoryMessage));
                                                    }
                                                    return;
                                                } catch (NullPointerException e33) {
                                                    e33.printStackTrace();
                                                    return;
                                                }
                                            case CMD_GET_RENCENT_CONTACTS /* 20006 */:
                                                try {
                                                    Contacts contacts = (Contacts) gson.fromJson(str, Contacts.class);
                                                    if (youMeIMJsonResponse.errcode.intValue() == 0) {
                                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_historyContactCallback, "onSuccess", contacts.contacts));
                                                    } else {
                                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_historyContactCallback, "onFailed", youMeIMJsonResponse.errcode, contacts.contacts));
                                                    }
                                                    return;
                                                } catch (NullPointerException e34) {
                                                    e34.printStackTrace();
                                                    return;
                                                }
                                            case CMD_RECEIVE_MESSAGE_NITIFY /* 20007 */:
                                                try {
                                                    NewMessageNotifyObj newMessageNotifyObj = (NewMessageNotifyObj) gson.fromJson(str, NewMessageNotifyObj.class);
                                                    this.mHandler.post(new YIMCallBackProtocolV2(this.m_msgEventCallback, "onRecvNewMessage", newMessageNotifyObj.chatType, newMessageNotifyObj.targetID));
                                                    return;
                                                } catch (NullPointerException e35) {
                                                    e35.printStackTrace();
                                                    return;
                                                }
                                            case CMD_QUERY_USER_STATUS /* 20008 */:
                                                UserStatusInfo userStatusInfo = (UserStatusInfo) gson.fromJson(str, UserStatusInfo.class);
                                                try {
                                                    if (this.m_userStatusCallback != null) {
                                                        if (youMeIMJsonResponse.errcode.intValue() == 0) {
                                                            this.mHandler.post(new YIMCallBackProtocolV2(this.m_userStatusCallback, "onSuccess", userStatusInfo));
                                                        } else {
                                                            this.mHandler.post(new YIMCallBackProtocolV2(this.m_userStatusCallback, "onFailed", youMeIMJsonResponse.errcode, userStatusInfo));
                                                        }
                                                    }
                                                    this.m_userStatusCallback = null;
                                                    return;
                                                } catch (NullPointerException e36) {
                                                    e36.printStackTrace();
                                                    return;
                                                }
                                            case CMD_AUDIO_PLAY_COMPLETE /* 20009 */:
                                                OnPlayCompleteNotify onPlayCompleteNotify = (OnPlayCompleteNotify) gson.fromJson(str, OnPlayCompleteNotify.class);
                                                try {
                                                    if (this.m_playCompleteCallback != null) {
                                                        if (youMeIMJsonResponse.errcode.intValue() == 0) {
                                                            this.mHandler.post(new YIMCallBackProtocolV2(this.m_playCompleteCallback, "onSuccess", onPlayCompleteNotify.audioPath));
                                                        } else {
                                                            this.mHandler.post(new YIMCallBackProtocolV2(this.m_playCompleteCallback, "onFailed", youMeIMJsonResponse.errcode, onPlayCompleteNotify.audioPath));
                                                        }
                                                        this.m_playCompleteCallback = null;
                                                        return;
                                                    }
                                                    return;
                                                } catch (NullPointerException e37) {
                                                    e37.printStackTrace();
                                                    return;
                                                }
                                            case CMD_STOP_SEND_AUDIO /* 20010 */:
                                                VoiceInfo voiceInfo = (VoiceInfo) gson.fromJson(str, VoiceInfo.class);
                                                if (!this.messageCallbackQueue.containsKey(voiceInfo.requestId) || (messageCallbackObject = this.messageCallbackQueue.get(voiceInfo.requestId)) == null || messageCallbackObject.callback == 0) {
                                                    return;
                                                }
                                                try {
                                                    this.mHandler.post(new YIMCallBackProtocolV2((YIMEventCallback.AudioMsgEventCallback) messageCallbackObject.callback, "onStartSendAudioMessage", voiceInfo.requestId, youMeIMJsonResponse.errcode, voiceInfo.extraText, voiceInfo.localPath, voiceInfo.duration));
                                                    return;
                                                } catch (NullPointerException e38) {
                                                    e38.printStackTrace();
                                                    return;
                                                }
                                            case CMD_TRANSLATE_COMPLETE /* 20011 */:
                                                Log.d("CMD_TRANSLATE_COMPLETE", "msg = " + str);
                                                TranlateTextInfo tranlateTextInfo = (TranlateTextInfo) gson.fromJson(str, TranlateTextInfo.class);
                                                try {
                                                    if (youMeIMJsonResponse.errcode.intValue() == 0) {
                                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_translateTextCallback, "onSuccess", tranlateTextInfo));
                                                    } else {
                                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_translateTextCallback, "onFailed", youMeIMJsonResponse.errcode, tranlateTextInfo));
                                                    }
                                                    return;
                                                } catch (NullPointerException e39) {
                                                    e39.printStackTrace();
                                                    return;
                                                }
                                            case CMD_DOWNLOAD_URL /* 20012 */:
                                                DownloadUrl downloadUrl = (DownloadUrl) gson.fromJson(str, DownloadUrl.class);
                                                if (this.downloadByUrlCallbackQueue.containsKey(downloadUrl.fromUrl)) {
                                                    try {
                                                        this.mHandler.post(new YIMCallBackProtocolV2(this.downloadByUrlCallbackQueue.get(downloadUrl.fromUrl), "onDownloadByUrl", youMeIMJsonResponse.errcode, downloadUrl.fromUrl, downloadUrl.savePath, Integer.valueOf(downloadUrl.audioTime)));
                                                        break;
                                                    } catch (NullPointerException e40) {
                                                        e40.printStackTrace();
                                                    }
                                                    this.downloadByUrlCallbackQueue.remove(downloadUrl.fromUrl);
                                                    return;
                                                }
                                                return;
                                            case CMD_GET_MICROPHONE_STATUS /* 20013 */:
                                                try {
                                                    this.mHandler.post(new YIMCallBackProtocolV2(this.m_getMicStatusCallback, "onGetMicrophoneStatus", Integer.valueOf(((MicrophoneStatus) gson.fromJson(str, MicrophoneStatus.class)).status)));
                                                    return;
                                                } catch (NullPointerException e41) {
                                                    e41.printStackTrace();
                                                    return;
                                                }
                                            case CMD_USER_ENTER_ROOM /* 20014 */:
                                                try {
                                                    this.mHandler.post(new YIMCallBackProtocolV2(this.m_userJoinLeaveCallback, "joinLeaveNotify", ChannelEventType.UserJoinRoom, (UserChatRoom) gson.fromJson(str, UserChatRoom.class)));
                                                    return;
                                                } catch (NullPointerException e42) {
                                                    e42.printStackTrace();
                                                    return;
                                                }
                                            case CMD_USER_LEAVE_ROOM /* 20015 */:
                                                try {
                                                    this.mHandler.post(new YIMCallBackProtocolV2(this.m_userJoinLeaveCallback, "joinLeaveNotify", ChannelEventType.UserLeaveRoom, (UserChatRoom) gson.fromJson(str, UserChatRoom.class)));
                                                    return;
                                                } catch (NullPointerException e43) {
                                                    e43.printStackTrace();
                                                    return;
                                                }
                                            case CMD_RECV_NOTICE /* 20016 */:
                                                try {
                                                    this.mHandler.post(new YIMCallBackProtocolV2(this.m_noticeCallback, "onRecvNotice", (NoticeInfo) gson.fromJson(str, NoticeInfo.class)));
                                                    return;
                                                } catch (NullPointerException e44) {
                                                    e44.printStackTrace();
                                                    return;
                                                }
                                            case CMD_CANCEL_NOTICE /* 20017 */:
                                                NoticeCancelInfo noticeCancelInfo = (NoticeCancelInfo) gson.fromJson(str, NoticeCancelInfo.class);
                                                try {
                                                    this.mHandler.post(new YIMCallBackProtocolV2(this.m_noticeCallback, "onCancelNotice", Long.valueOf(noticeCancelInfo.iNoticeID), noticeCancelInfo.strChannelID));
                                                    return;
                                                } catch (NullPointerException e45) {
                                                    e45.printStackTrace();
                                                    return;
                                                }
                                            case CMD_GET_SPEECH_TEXT /* 20018 */:
                                                RecognizeSpeechText recognizeSpeechText = (RecognizeSpeechText) gson.fromJson(str, RecognizeSpeechText.class);
                                                try {
                                                    this.mHandler.post(new YIMCallBackProtocolV2(this.m_msgEventCallback, "onGetRecognizeSpeechText", youMeIMJsonResponse.errcode, recognizeSpeechText.requestId, recognizeSpeechText.text));
                                                    return;
                                                } catch (NullPointerException e46) {
                                                    e46.printStackTrace();
                                                    return;
                                                }
                                            case CMD_GET_RECONNECT_RESULT /* 20019 */:
                                                try {
                                                    this.mHandler.post(new YIMCallBackProtocolV2(this.m_reconnectCallback, "onRecvReconnectResult", Integer.valueOf(((ReconnectResult) gson.fromJson(str, ReconnectResult.class)).iResult)));
                                                    return;
                                                } catch (NullPointerException e47) {
                                                    e47.printStackTrace();
                                                    return;
                                                }
                                            case CMD_START_RECONNECT /* 20020 */:
                                                try {
                                                    this.mHandler.post(new YIMCallBackProtocolV2(this.m_reconnectCallback, "onStartReconnect", new Object[0]));
                                                    return;
                                                } catch (NullPointerException e48) {
                                                    e48.printStackTrace();
                                                    return;
                                                }
                                            case CMD_RECORD_VOLUME /* 20021 */:
                                                try {
                                                    this.mHandler.post(new YIMCallBackProtocolV2(this.m_msgEventCallback, "onRecordVolume", Float.valueOf(((VolumeInfo) gson.fromJson(str, VolumeInfo.class)).iVolume)));
                                                    return;
                                                } catch (NullPointerException e49) {
                                                    e49.printStackTrace();
                                                    return;
                                                }
                                            case CMD_GET_DISTANCE /* 20022 */:
                                                UserDistanceInfo userDistanceInfo = (UserDistanceInfo) gson.fromJson(str, UserDistanceInfo.class);
                                                try {
                                                    if (youMeIMJsonResponse.errcode.intValue() == 0) {
                                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_getDistanceCallback, "onSuccess", userDistanceInfo));
                                                    } else {
                                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_getDistanceCallback, "onFailed", youMeIMJsonResponse.errcode, userDistanceInfo));
                                                    }
                                                    return;
                                                } catch (NullPointerException e50) {
                                                    e50.printStackTrace();
                                                    return;
                                                }
                                            case CMD_REQUEST_ADD_FRIEND_NOTIFY /* 20023 */:
                                                try {
                                                    YIMFriendCommon yIMFriendCommon = (YIMFriendCommon) gson.fromJson(str, YIMFriendCommon.class);
                                                    this.mHandler.post(new YIMCallBackProtocolV2(this.m_friendNotifyCallback, "onBeRequestAddFriendNotify", yIMFriendCommon.userID, yIMFriendCommon.comments));
                                                    return;
                                                } catch (NullPointerException e51) {
                                                    e51.printStackTrace();
                                                    return;
                                                }
                                            case CMD_ADD_FRIENT_RESULT_NOTIFY /* 20024 */:
                                                YIMFriendDealResult yIMFriendDealResult2 = (YIMFriendDealResult) gson.fromJson(str, YIMFriendDealResult.class);
                                                try {
                                                    this.mHandler.post(new YIMCallBackProtocolV2(this.m_friendNotifyCallback, "onRequestAddFriendResultNotify", yIMFriendDealResult2.userID, yIMFriendDealResult2.comments, Integer.valueOf(yIMFriendDealResult2.dealResult)));
                                                    return;
                                                } catch (NullPointerException e52) {
                                                    e52.printStackTrace();
                                                    return;
                                                }
                                            case CMD_BE_ADD_FRIENT /* 20025 */:
                                                try {
                                                    YIMFriendCommon yIMFriendCommon2 = (YIMFriendCommon) gson.fromJson(str, YIMFriendCommon.class);
                                                    this.mHandler.post(new YIMCallBackProtocolV2(this.m_friendNotifyCallback, "onBeAddFriendNotify", yIMFriendCommon2.userID, yIMFriendCommon2.comments));
                                                    return;
                                                } catch (NullPointerException e53) {
                                                    e53.printStackTrace();
                                                    return;
                                                }
                                            case CMD_BE_DELETE_FRIEND_NOTIFY /* 20026 */:
                                                try {
                                                    this.mHandler.post(new YIMCallBackProtocolV2(this.m_friendNotifyCallback, "onBeDeleteFriendNotify", ((YIMFriendUserID) gson.fromJson(str, YIMFriendUserID.class)).userID));
                                                    return;
                                                } catch (NullPointerException e54) {
                                                    e54.printStackTrace();
                                                    return;
                                                }
                                            case CMD_BE_BLACK_FRIEND_NOTIFY /* 20027 */:
                                                try {
                                                    this.mHandler.post(new YIMCallBackProtocolV2(this.m_friendNotifyCallback, "onBeBlackNotify", ((YIMFriendUserID) gson.fromJson(str, YIMFriendUserID.class)).userID));
                                                    return;
                                                } catch (NullPointerException e55) {
                                                    e55.printStackTrace();
                                                    return;
                                                }
                                            case CMD_GET_USER_PROFILE /* 20028 */:
                                                UserProfileInfo userProfileInfo = (UserProfileInfo) gson.fromJson(str, UserProfileInfo.class);
                                                try {
                                                    if (youMeIMJsonResponse.errcode.intValue() == 0) {
                                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_userProfileCallback, "onSuccess", userProfileInfo));
                                                    } else {
                                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_userProfileCallback, "onFailed", youMeIMJsonResponse.errcode, userProfileInfo));
                                                    }
                                                    return;
                                                } catch (NullPointerException e56) {
                                                    e56.printStackTrace();
                                                    return;
                                                }
                                            case CMD_SET_USER_PROFILE /* 20029 */:
                                                try {
                                                    if (youMeIMJsonResponse.errcode.intValue() == 0) {
                                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_setprofileCallback, "onSuccess", new Object[0]));
                                                    } else {
                                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_setprofileCallback, "onFailed", youMeIMJsonResponse.errcode));
                                                    }
                                                    return;
                                                } catch (NullPointerException e57) {
                                                    e57.printStackTrace();
                                                    return;
                                                }
                                            case CMD_SET_USER_PHOTO /* 20030 */:
                                                PhotoUrlInfo photoUrlInfo = (PhotoUrlInfo) gson.fromJson(str, PhotoUrlInfo.class);
                                                try {
                                                    if (youMeIMJsonResponse.errcode.intValue() == 0) {
                                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_photoUrlCallback, "onSuccess", photoUrlInfo.photoUrl));
                                                    } else {
                                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_photoUrlCallback, "onFailed", youMeIMJsonResponse.errcode, photoUrlInfo.photoUrl));
                                                    }
                                                    return;
                                                } catch (NullPointerException e58) {
                                                    e58.printStackTrace();
                                                    return;
                                                }
                                            case CMD_SWITCH_USER_STATE /* 20031 */:
                                                try {
                                                    if (youMeIMJsonResponse.errcode.intValue() == 0) {
                                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_switchStatusCallback, "onSuccess", new Object[0]));
                                                    } else {
                                                        this.mHandler.post(new YIMCallBackProtocolV2(this.m_switchStatusCallback, "onFailed", youMeIMJsonResponse.errcode));
                                                    }
                                                    return;
                                                } catch (NullPointerException e59) {
                                                    e59.printStackTrace();
                                                    return;
                                                }
                                            default:
                                                return;
                                        }
                                }
                        }
                }
            }
            YIMFriendListInfo yIMFriendListInfo = (YIMFriendListInfo) gson.fromJson(str, YIMFriendListInfo.class);
            try {
                if (youMeIMJsonResponse.errcode.intValue() == 0) {
                    this.mHandler.post(new YIMCallBackProtocolV2(this.m_queryFriendsCallback, "onSuccess", yIMFriendListInfo));
                } else {
                    this.mHandler.post(new YIMCallBackProtocolV2(this.m_queryFriendsCallback, "onFailed", youMeIMJsonResponse.errcode, yIMFriendListInfo));
                }
                return;
            } catch (NullPointerException e60) {
                e60.printStackTrace();
                return;
            }
            e.printStackTrace();
        }
    }
}
