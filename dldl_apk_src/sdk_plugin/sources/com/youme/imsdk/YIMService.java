package com.youme.imsdk;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.youme.im.IMEngine;
import com.youme.im.IMEngine.IntegerVal;
import com.youme.im.IMEngine.MessageRequestId;
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
import com.youme.imsdk.internal.NewMessageNotifyObj;
import com.youme.imsdk.internal.NoticeCancelInfo;
import com.youme.imsdk.internal.NoticeInfo;
import com.youme.imsdk.internal.OnPlayCompleteNotify;
import com.youme.imsdk.internal.PhotoUrlInfo;
import com.youme.imsdk.internal.RecognizeSpeechText;
import com.youme.imsdk.internal.ReconnectResult;
import com.youme.imsdk.internal.RecvMessage;
import com.youme.imsdk.internal.RelativeLocation;
import com.youme.imsdk.internal.RelativeLocationInfo;
import com.youme.imsdk.internal.RoomHistoryMessage;
import com.youme.imsdk.internal.RoomInfo;
import com.youme.imsdk.internal.SendMessage;
import com.youme.imsdk.internal.SendVoiceMsgInfo;
import com.youme.imsdk.internal.SpeechMessageInfo;
import com.youme.imsdk.internal.TranlateTextInfo;
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
import com.youme.imsdk.internal.YIMFriendRequestInfo;
import com.youme.imsdk.internal.YIMFriendRequestInfoList;
import com.youme.imsdk.internal.YIMFriendUserID;
import com.youme.imsdk.internal.YIMUserBriefInfo;
import com.youme.imsdk.internal.YIMUserBriefInfoList;
import com.youme.imsdk.internal.YouMeIMJsonResponse;
import com.youme.voice.VoiceManager;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class YIMService {
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
    private static final int CMD_USER_ENTER_ROOM = 20014;
    private static final int CMD_USER_LEAVE_ROOM = 20015;
    private static final int NOTIFY_LOGIN = 10001;
    private static final int NOTIFY_MAX_ID = 19999;
    private static final int NOTIFY_PRIVATE_MSG = 10002;
    private static final int NOTIFY_PRIVATE_MSG_V2 = 10012;
    private static final int NOTIFY_ROOM_GENERAL = 10004;
    private static final int NOTIFY_ROOM_MSG = 10003;
    private static final int NOTIFY_ROOM_MSG_V2 = 10013;
    private static final int NOTIFY_UPDATE_CONFIG = 10008;
    private static YIMService mInstance;
    private Context m_context;
    private static final String TAG = YIMService.class.getSimpleName();
    private static boolean DEBUG = false;
    private boolean mIsExit = true;
    private LoginListener mLoginListener = null;
    private MessageListener mMessageListener = null;
    private ChatRoomListener mChatRoomListener = null;
    private ContactListener mContactListener = null;
    private AudioPlayListener mAudioPlayListener = null;
    private LocationListen mLocationListen = null;
    private NoticeListener mNoticeListener = null;
    private ReconnectListener mReconnectListener = null;
    private UserProfileListener mUserProfileListener = null;
    private FriendListener mFriendListener = null;
    private Thread mThread = null;
    private Handler mHandler = null;
    private Runnable paresMessage = new Runnable() { // from class: com.youme.imsdk.YIMService.1
        @Override // java.lang.Runnable
        public void run() {
            String str = null;
            while (true) {
                try {
                    str = new String(IMEngine.IM_GetMessage(), "UTF-8");
                } catch (UnsupportedEncodingException unused) {
                    Log.e(YIMService.TAG, "Couldn't convert the jbyteArray to jstring");
                }
                if (str != null) {
                    if (YIMService.this.mIsExit) {
                        YIMService.this.mThread = null;
                        return;
                    }
                    if (YIMService.DEBUG) {
                        Log.d(YIMService.TAG, "IM_GetMessage :" + str);
                    }
                    YIMService.this.handlerMessage(str);
                    IMEngine.IM_PopMessage();
                }
            }
        }
    };

    public static final class AccusationDealResult {
        public static final int ACCUSATIONRESULT_FROBIDDEN_SPEAK = 2;
        public static final int ACCUSATIONRESULT_IGNORE = 0;
        public static final int ACCUSATIONRESULT_WARNING = 1;
    }

    public static final class AudioDeviceStatus {
        public static final int STATUS_AVAILABLE = 0;
        public static final int STATUS_MUTE = 2;
        public static final int STATUS_NO_AUTHORITY = 1;
        public static final int STATUS_UNAVAILABLE = 3;
    }

    public interface AudioPlayListener {
        void onGetMicrophoneStatus(Integer num);

        void onPlayCompletion(Integer num, String str);
    }

    public interface ChatRoomListener {
        void OnGetRoomMemberCount(Integer num, String str, Integer num2);

        void OnLeaveAllChatRooms(Integer num);

        void onJoinChatRoom(Integer num, String str);

        void onLeaveChatRoom(Integer num, String str);

        void onUserJoinChatRoom(String str, String str2);

        void onUserLeaveChatRoom(String str, String str2);
    }

    public static final class ChatType {
        public static final int PrivateChat = 1;
        public static final int RoomChat = 2;
        public static final int Unknow = 0;
    }

    public interface ContactListener {
        void onGetContact(ArrayList<ContactsSessionInfo> arrayList);

        void onGetUserInfo(Integer num, YIMExtraUserInfo yIMExtraUserInfo);

        void onQueryUserStatus(Integer num, UserStatusInfo userStatusInfo);
    }

    public static final class DistrictLevel {
        public static final int DISTRICT_CITY = 3;
        public static final int DISTRICT_COUNTRY = 1;
        public static final int DISTRICT_COUNTY = 4;
        public static final int DISTRICT_PROVINCE = 2;
        public static final int DISTRICT_STREET = 5;
        public static final int DISTRICT_UNKNOW = 0;
    }

    public static final class Errorcode {
        public static final int ALREADYFRIENDS = 1000;
        public static final int AdvertisementMessage = 42;
        public static final int AlreadyBlock = 43;
        public static final int AlreadyFriend = 61;
        public static final int AlreadyLogin = 7;
        public static final int AudioDriver = 33;
        public static final int BeRefuse = 59;
        public static final int ContentInvalid = 28;
        public static final int CreateDirectoryFailed = 49;
        public static final int CreateFileFailed = 24;
        public static final int DeviceStatusInvalid = 34;
        public static final int Disconnect = 51;
        public static final int EngineNotInit = 1;
        public static final int Fail = 10000;
        public static final int FileNotExist = 12;
        public static final int ForbiddenSpeak = 23;
        public static final int HasIllegalText = 41;
        public static final int HasNotRegisterUserInfo = 60;
        public static final int InitFailed = 50;
        public static final int InvalidAppKey = 3004;
        public static final int InvalidAppkey = 22;
        public static final int InvalidChatType = 19;
        public static final int InvalidContext = 3005;
        public static final int InvalidPassword = 3002;
        public static final int InvalidReceiver = 20;
        public static final int InvalidRoomId = 3001;
        public static final int InvalidStoragaePath = 3003;
        public static final int InvalidUserId = 3000;
        public static final int InvaliddSecretKey = 3006;
        public static final int LocationTimeout = 46;
        public static final int LoginInvalid = 1001;
        public static final int LoginSessionError = 10;
        public static final int LoginTokenInvalid = 48;
        public static final int MessageBlocked = 45;
        public static final int MessageTooLong = 17;
        public static final int NeedFriendVerify = 58;
        public static final int NetError = 9;
        public static final int NickNameTooLong = 56;
        public static final int NoAudioDevice = 32;
        public static final int NoLangCode = 37;
        public static final int NoLocationAuthrize = 29;
        public static final int NotBlack = 63;
        public static final int NotBlock = 44;
        public static final int NotFriend = 62;
        public static final int NotJoinRoom = 47;
        public static final int NotLogin = 2;
        public static final int NotStartUp = 11;
        public static final int PTT_Authorize = 2019;
        public static final int PTT_CancelPlay = 2015;
        public static final int PTT_ConvertFileFailed = 2025;
        public static final int PTT_DeviceStatusError = 2006;
        public static final int PTT_DownloadFail = 2002;
        public static final int PTT_Fail = 2001;
        public static final int PTT_FileNotExist = 2008;
        public static final int PTT_GetUploadTokenFail = 2003;
        public static final int PTT_InitFailed = 2018;
        public static final int PTT_IsPlaying = 2013;
        public static final int PTT_IsSpeeching = 2007;
        public static final int PTT_NoAudioDevice = 2026;
        public static final int PTT_NoDriver = 2027;
        public static final int PTT_NotInit = 2017;
        public static final int PTT_NotSpeech = 2005;
        public static final int PTT_NotStartPlay = 2014;
        public static final int PTT_NotStartRecord = 2016;
        public static final int PTT_ReachMaxDuration = 2009;
        public static final int PTT_ReadWriteFileError = 2024;
        public static final int PTT_ResolveFileError = 2023;
        public static final int PTT_SpeechTimeout = 2012;
        public static final int PTT_SpeechTooShort = 2010;
        public static final int PTT_Start = 2000;
        public static final int PTT_StartAudioRecordFailed = 2011;
        public static final int PTT_StartPlayFailed = 2028;
        public static final int PTT_StartRecordFailed = 2020;
        public static final int PTT_StopPlayFailed = 2029;
        public static final int PTT_StopRecordFailed = 2021;
        public static final int PTT_UnsupprtFormat = 2022;
        public static final int PTT_UploadFail = 2004;
        public static final int ParamInvalid = 3;
        public static final int PhotoSizeTooLarge = 65;
        public static final int PhotoUrlTooLong = 64;
        public static final int QueryUserInfoFail = 53;
        public static final int ReadWriteFileError = 36;
        public static final int ReceiverEmpty = 26;
        public static final int ReceiverTooLong = 18;
        public static final int ResolveFileError = 35;
        public static final int RoomIDTooLong = 27;
        public static final int SDKInvalid = 6;
        public static final int SendFileError = 13;
        public static final int ServerError = 8;
        public static final int SetUserInfoFail = 54;
        public static final int SignatureTooLong = 57;
        public static final int SpeechAccentInvalid = 39;
        public static final int SpeechLanguageInvalid = 40;
        public static final int StatusError = 5;
        public static final int Success = 0;
        public static final int TheSameParam = 52;
        public static final int TimeOut = 4;
        public static final int TranslateUnable = 38;
        public static final int UnknowError = 21;
        public static final int UnknowLocation = 30;
        public static final int Unsupport = 31;
        public static final int UnsupportFormat = 25;
        public static final int UpdateUserOnlineStateFail = 55;
        public static final int UploadFailed = 14;
        public static final int UserStatError = 16;
        public static final int UsernamePasswordError = 15;
    }

    public static final class FileType {
        public static final int FileType_Audio = 1;
        public static final int FileType_Image = 2;
        public static final int FileType_Other = 0;
        public static final int FileType_Video = 3;
    }

    public static final class ForbidSpeakReason {
        public static final int AD = 1;
        public static final int Insult = 2;
        public static final int Other = 7;
        public static final int Politics = 3;
        public static final int Reaction = 5;
        public static final int Sexy = 6;
        public static final int Terrorism = 4;
        public static final int Unkown = 0;
    }

    public interface FriendListener {
        void OnBeAddFriendNotify(String str, String str2);

        void OnBeDeleteFriendNotify(String str);

        void OnBeRequestAddFriendNotify(String str, String str2);

        void OnBlackFriend(Integer num, Integer num2, String str);

        void OnDealBeRequestAddFriend(Integer num, String str, String str2, Integer num2);

        void OnDeleteFriend(Integer num, String str);

        void OnFindUser(Integer num, ArrayList<YIMUserBriefInfo> arrayList);

        void OnQueryFriendRequestList(Integer num, Integer num2, ArrayList<YIMFriendRequestInfo> arrayList);

        void OnQueryFriends(Integer num, Integer num2, Integer num3, ArrayList<YIMUserBriefInfo> arrayList);

        void OnRequestAddFriend(Integer num, String str);

        void OnRequestAddFriendResultNotify(String str, String str2, Integer num);
    }

    public enum LanguageCode {
        LANG_AUTO,
        LANG_AF,
        LANG_AM,
        LANG_AR,
        LANG_AR_AE,
        LANG_AR_BH,
        LANG_AR_DZ,
        LANG_AR_KW,
        LANG_AR_LB,
        LANG_AR_OM,
        LANG_AR_SA,
        LANG_AR_SD,
        LANG_AR_TN,
        LANG_AZ,
        LANG_BE,
        LANG_BG,
        LANG_BN,
        LANG_BS,
        LANG_CA,
        LANG_CA_ES,
        LANG_CO,
        LANG_CS,
        LANG_CY,
        LANG_DA,
        LANG_DE,
        LANG_DE_CH,
        LANG_DE_LU,
        LANG_EL,
        LANG_EN,
        LANG_EN_CA,
        LANG_EN_IE,
        LANG_EN_ZA,
        LANG_EO,
        LANG_ES,
        LANG_ES_BO,
        LANG_ES_AR,
        LANG_ES_CO,
        LANG_ES_CR,
        LANG_ES_ES,
        LANG_ET,
        LANG_ES_PA,
        LANG_ES_SV,
        LANG_ES_VE,
        LANG_ET_EE,
        LANG_EU,
        LANG_FA,
        LANG_FI,
        LANG_FR,
        LANG_FR_BE,
        LANG_FR_CA,
        LANG_FR_CH,
        LANG_FR_LU,
        LANG_FY,
        LANG_GA,
        LANG_GD,
        LANG_GL,
        LANG_GU,
        LANG_HA,
        LANG_HI,
        LANG_HR,
        LANG_HT,
        LANG_HU,
        LANG_HY,
        LANG_ID,
        LANG_IG,
        LANG_IS,
        LANG_IT,
        LANG_IT_CH,
        LANG_JA,
        LANG_KA,
        LANG_KK,
        LANG_KN,
        LANG_KM,
        LANG_KO,
        LANG_KO_KR,
        LANG_KU,
        LANG_KY,
        LANG_LA,
        LANG_LB,
        LANG_LO,
        LANG_LT,
        LANG_LV,
        LANG_MG,
        LANG_MI,
        LANG_MK,
        LANG_ML,
        LANG_MN,
        LANG_MR,
        LANG_MS,
        LANG_MT,
        LANG_MY,
        LANG_NL,
        LANG_NL_BE,
        LANG_NE,
        LANG_NO,
        LANG_NY,
        LANG_PL,
        LANG_PS,
        LANG_PT,
        LANG_PT_BR,
        LANG_RO,
        LANG_RU,
        LANG_SD,
        LANG_SI,
        LANG_SK,
        LANG_SL,
        LANG_SM,
        LANG_SN,
        LANG_SO,
        LANG_SQ,
        LANG_SR,
        LANG_ST,
        LANG_SU,
        LANG_SV,
        LANG_SV_SE,
        LANG_SW,
        LANG_TA,
        LANG_TE,
        LANG_TG,
        LANG_TH,
        LANG_TL,
        LANG_TR,
        LANG_UK,
        LANG_UR,
        LANG_UZ,
        LANG_VI,
        LANG_XH,
        LANG_YID,
        LANG_YO,
        LANG_ZH,
        LANG_ZH_TW,
        LANG_ZU
    }

    public interface LocationListen {
        void OnGetDistance(Integer num, String str, Integer num2);

        void OnGetNearbyObjects(Integer num, ArrayList<RelativeLocation> arrayList, Integer num2, Integer num3);

        void OnUpdateLocation(Integer num, GeographyLocation geographyLocation);
    }

    public interface LoginListener {
        void onKickOff();

        void onLogin(String str, Integer num);

        void onLogout();
    }

    public static final class MessageBodyType {
        public static final int CustomMesssage = 2;
        public static final int Emoji = 3;
        public static final int File = 7;
        public static final int Gift = 8;
        public static final int Image = 4;
        public static final int TXT = 1;
        public static final int Unknow = 0;
        public static final int Video = 6;
        public static final int Voice = 5;
    }

    public interface MessageListener {
        void OnBlockUser(Integer num, String str, Boolean bool);

        void OnGetBlockUsers(Integer num, ArrayList<String> arrayList);

        void OnGetForbiddenSpeakInfo(Integer num, ArrayList<YIMForbiddenSpeakInfo> arrayList);

        void OnGetRecognizeSpeechText(Integer num, Long l, String str);

        void OnQueryHistoryMessage(Integer num, YIMHistoryMessage yIMHistoryMessage);

        void OnQueryRoomHistoryMessageFromServer(Integer num, String str, Integer num2, ArrayList<YIMMessage> arrayList);

        void OnRecordVolume(Float f);

        void OnRecvNewMessage(Integer num, String str);

        void OnStopAudioSpeechStatus(Integer num, Long l, String str, Integer num2, Integer num3, String str2, String str3);

        void OnTranslateTextComplete(Integer num, Integer num2, String str, Integer num3, Integer num4);

        void OnUnBlockAllUser(Integer num);

        void onAccusationResultNotify(Integer num, String str, Integer num2);

        void onDownload(Integer num, YIMMessage yIMMessage, String str);

        void onDownloadByUrl(Integer num, String str, String str2, Integer num2);

        void onRecvMessage(YIMMessage yIMMessage);

        void onSendAudioMessageStatus(Long l, Integer num, String str, String str2, Integer num2, Integer num3, Boolean bool, Integer num4, Long l2, Long l3);

        void onSendMessageStatus(Long l, Integer num, Integer num2, Boolean bool, Integer num3, Long l2, Long l3);

        void onStartSendAudioMessage(Long l, Integer num, String str, String str2, Integer num2);
    }

    public interface NoticeListener {
        void onCancelNotice(Integer num, String str);

        void onRecvNotice(NoticeInfo noticeInfo);
    }

    public static final class ReconnectDealResult {
        public static final int RECONNECTRESULT_FAIL = 2;
        public static final int RECONNECTRESULT_FAIL_AGAIN = 1;
        public static final int RECONNECTRESULT_SUCCESS = 0;
    }

    public interface ReconnectListener {
        void OnRecvReconnectResult(Integer num);

        void OnStartReconnect();
    }

    public static final class RequestId {
        public long id;
    }

    public static final class SampleRateType {
        public static final int SAMPLERATE_16 = 1;
        public static final int SAMPLERATE_32 = 2;
        public static final int SAMPLERATE_44_1 = 3;
        public static final int SAMPLERATE_48 = 4;
        public static final int SAMPLERATE_8 = 0;
    }

    public static final class ServerZone {
        public static final int America = 2;
        public static final int Australia = 5;
        public static final int Brazil = 7;
        public static final int China = 0;
        public static final int Deutschland = 6;
        public static final int HongKong = 3;
        public static final int India = 8;
        public static final int Ireland = 10;
        public static final int Japan = 9;
        public static final int Korea = 4;
        public static final int Singapore = 1;
    }

    public static final class SpeechRecognizeLanguage {
        public static final int SPEECHLANG_ENGLISH = 4;
        public static final int SPEECHLANG_HENAN = 3;
        public static final int SPEECHLANG_MANDARIN = 0;
        public static final int SPEECHLANG_SICHUAN = 2;
        public static final int SPEECHLANG_TRADITIONAL = 5;
        public static final int SPEECHLANG_YUEYU = 1;
    }

    public static final class UserBeAddPermission {
        public static final int NEED_VALIDATE = 1;
        public static final int NOT_ALLOW_ADD = 0;
        public static final int NO_ADD_PERMISSION = 2;
    }

    public static final class UserFoundPermission {
        public static final int CAN_BE_FOUND = 0;
        public static final int CAN_NOT_BE_FOUND = 1;
    }

    public static final class UserOnlineState {
        public static final int USER_STATUS_INVISIBLE = 2;
        public static final int USER_STATUS_OFFLINE = 1;
        public static final int USER_STATUS_ONLINE = 0;
    }

    public interface UserProfileListener {
        void OnQueryUserInfo(Integer num, UserProfileInfo userProfileInfo);

        void OnSetPhotoUrl(Integer num, String str);

        void OnSetUserInfo(Integer num);

        void OnSwitchUserOnlineState(Integer num);

        void OnUserInfoChangeNotify(String str);
    }

    public static final class UserSex {
        public static final int SEX_FEMALE = 2;
        public static final int SEX_MALE = 1;
        public static final int SEX_UNKNOWN = 0;
    }

    public enum UserStatus {
        ON_LINE(0),
        OFF_LINE(1);

        private int index;

        UserStatus(int i) {
            this.index = i;
        }

        public int getIndex() {
            return this.index;
        }
    }

    private YIMService() {
    }

    public static void SetDebug(boolean z) {
        DEBUG = z;
    }

    public static YIMService getInstance() {
        if (mInstance == null) {
            mInstance = new YIMService();
        }
        return mInstance;
    }

    public void registerLoginListener(LoginListener loginListener) {
        this.mLoginListener = loginListener;
    }

    public void unregisterLoginListener() {
        this.mLoginListener = null;
    }

    public void registerMessageListener(MessageListener messageListener) {
        this.mMessageListener = messageListener;
    }

    public void unregisterMessageListener() {
        this.mMessageListener = null;
    }

    public void registerChatRoomListenr(ChatRoomListener chatRoomListener) {
        this.mChatRoomListener = chatRoomListener;
    }

    public void unregisterChatRoomListener() {
        this.mChatRoomListener = null;
    }

    public void registerContactListener(ContactListener contactListener) {
        this.mContactListener = contactListener;
    }

    public void unregisterContactListener() {
        this.mContactListener = null;
    }

    public void registerAudioPlayListener(AudioPlayListener audioPlayListener) {
        this.mAudioPlayListener = audioPlayListener;
    }

    public void unregisterAudioPlayListener() {
        this.mAudioPlayListener = null;
    }

    public void registerLocationListen(LocationListen locationListen) {
        this.mLocationListen = locationListen;
    }

    public void unregisterLocationListen() {
        this.mLocationListen = null;
    }

    public void registerNoticeListener(NoticeListener noticeListener) {
        this.mNoticeListener = noticeListener;
    }

    public void unregisterNoticeListener() {
        this.mNoticeListener = null;
    }

    public void registerReconnectListener(ReconnectListener reconnectListener) {
        this.mReconnectListener = reconnectListener;
    }

    public void unregisterReconnectListener() {
        this.mReconnectListener = null;
    }

    public void registerUserProfileListener(UserProfileListener userProfileListener) {
        this.mUserProfileListener = userProfileListener;
    }

    public void unregisterUserProfileListener() {
        this.mUserProfileListener = null;
    }

    public void registerFriendListener(FriendListener friendListener) {
        this.mFriendListener = friendListener;
    }

    public void unregisterFriendListener() {
        this.mFriendListener = null;
    }

    public void setAudioCachePath(String str) {
        VoiceManager.SetAudioRecordCacheDir(str);
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

    public void setMode(int i) {
        IMEngine.IM_SetMode(i);
    }

    public int setLoginAddress(String str, int i) {
        return IMEngine.IM_SetLoginAddress(str, i);
    }

    public int login(String str, String str2, String str3) {
        if (!checkString("UserId", str)) {
            return 3000;
        }
        if (!checkString("Password", str2)) {
            return 3002;
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
        if (iIM_Login != 0) {
            return iIM_Login;
        }
        this.mIsExit = false;
        if (this.mHandler == null) {
            this.mHandler = new Handler();
        }
        if (this.mThread != null) {
            return iIM_Login;
        }
        Thread thread = new Thread(this.paresMessage);
        this.mThread = thread;
        thread.start();
        return iIM_Login;
    }

    public int logout() {
        return IMEngine.IM_Logout();
    }

    public int joinChatRoom(String str) {
        if (checkString("RoomID", str)) {
            return IMEngine.IM_JoinChatRoom(str);
        }
        return 3001;
    }

    public int leaveChatRoom(String str) {
        if (checkString("RoomID", str)) {
            return IMEngine.IM_LeaveChatRoom(str);
        }
        return 3001;
    }

    public int leaveAllChatRooms() {
        return IMEngine.IM_LeaveAllChatRooms();
    }

    public int getRoomMemberCount(String str) {
        return IMEngine.IM_GetRoomMemberCount(str);
    }

    public MessageSendStatus sendTextMessage(String str, int i, String str2, String str3) {
        MessageSendStatus messageSendStatus = new MessageSendStatus();
        if (!checkString("ReceiverID", str)) {
            messageSendStatus.setMessageId(0L);
            messageSendStatus.setErrorCode(20);
        } else {
            IMEngine iMEngine = new IMEngine();
            iMEngine.getClass();
            IMEngine.MessageRequestId messageRequestId = iMEngine.new MessageRequestId();
            int iIM_SendTextMessage = IMEngine.IM_SendTextMessage(str, i, str2, str3, messageRequestId);
            messageSendStatus.setMessageId(messageRequestId.getId());
            messageSendStatus.setErrorCode(iIM_SendTextMessage);
        }
        return messageSendStatus;
    }

    public MessageSendStatus startRecordAudioMessage(String str, int i) {
        MessageSendStatus messageSendStatus = new MessageSendStatus();
        if (!checkString("ReceiverID", str)) {
            messageSendStatus.setMessageId(0L);
            messageSendStatus.setErrorCode(20);
        } else {
            IMEngine iMEngine = new IMEngine();
            iMEngine.getClass();
            IMEngine.MessageRequestId messageRequestId = iMEngine.new MessageRequestId();
            int iIM_SendAudioMessage = IMEngine.IM_SendAudioMessage(str, i, messageRequestId);
            messageSendStatus.setMessageId(messageRequestId.getId());
            messageSendStatus.setErrorCode(iIM_SendAudioMessage);
        }
        return messageSendStatus;
    }

    public MessageSendStatus startOnlyRecordAudioMessage(String str, int i) {
        MessageSendStatus messageSendStatus = new MessageSendStatus();
        if (!checkString("ReceiverID", str)) {
            messageSendStatus.setMessageId(0L);
            messageSendStatus.setErrorCode(20);
        } else {
            IMEngine iMEngine = new IMEngine();
            iMEngine.getClass();
            IMEngine.MessageRequestId messageRequestId = iMEngine.new MessageRequestId();
            int iIM_SendOnlyAudioMessage = IMEngine.IM_SendOnlyAudioMessage(str, i, messageRequestId);
            messageSendStatus.setMessageId(messageRequestId.getId());
            messageSendStatus.setErrorCode(iIM_SendOnlyAudioMessage);
        }
        return messageSendStatus;
    }

    public int stopAndSendAudioMessage(String str) {
        return IMEngine.IM_StopAudioMessage(str);
    }

    public int cancleAudioMessage() {
        return IMEngine.IM_CancleAudioMessage();
    }

    public int downloadAudioMessage(long j, String str) {
        return IMEngine.IM_DownloadAudioFile(j, str);
    }

    public int downloadFile(long j, String str) {
        return IMEngine.IM_DownloadAudioFile(j, str);
    }

    public int downloadFileByUrl(String str, String str2, int i) {
        return IMEngine.IM_DownloadFileByURL(str, str2, i);
    }

    public int setDownloadDir(String str) {
        return IMEngine.IM_SetDownloadDir(str);
    }

    public MessageSendStatus sendCustomMessage(String str, int i, byte[] bArr, int i2) {
        MessageSendStatus messageSendStatus = new MessageSendStatus();
        if (!checkString("ReceiverID", str)) {
            messageSendStatus.setMessageId(0L);
            messageSendStatus.setErrorCode(20);
        } else {
            IMEngine iMEngine = new IMEngine();
            iMEngine.getClass();
            IMEngine.MessageRequestId messageRequestId = iMEngine.new MessageRequestId();
            int iIM_SendCustomMessage = IMEngine.IM_SendCustomMessage(str, i, bArr, i2, messageRequestId);
            messageSendStatus.setMessageId(messageRequestId.getId());
            messageSendStatus.setErrorCode(iIM_SendCustomMessage);
        }
        return messageSendStatus;
    }

    public MessageSendStatus sendFile(String str, int i, String str2, String str3, int i2) {
        MessageSendStatus messageSendStatus = new MessageSendStatus();
        if (!checkString("ReceiverID", str)) {
            messageSendStatus.setMessageId(0L);
            messageSendStatus.setErrorCode(20);
        } else {
            IMEngine iMEngine = new IMEngine();
            iMEngine.getClass();
            IMEngine.MessageRequestId messageRequestId = iMEngine.new MessageRequestId();
            int iIM_SendFile = IMEngine.IM_SendFile(str, i, str2, str3, i2, messageRequestId);
            messageSendStatus.setMessageId(messageRequestId.getId());
            messageSendStatus.setErrorCode(iIM_SendFile);
        }
        return messageSendStatus;
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

    public int queryRoomHistoryMessageFromServer(String str, int i, int i2) {
        return IMEngine.IM_QueryRoomHistoryMessageFromServer(str, i, i2);
    }

    public MessageSendStatus sendGift(String str, String str2, int i, int i2, YIMExtraGifParam yIMExtraGifParam) {
        MessageSendStatus messageSendStatus = new MessageSendStatus();
        if (!checkString("Receive strChannel ", str2)) {
            messageSendStatus.setMessageId(0L);
            messageSendStatus.setErrorCode(20);
        } else {
            IMEngine iMEngine = new IMEngine();
            iMEngine.getClass();
            IMEngine.MessageRequestId messageRequestId = iMEngine.new MessageRequestId();
            Gson gsonCreate = new GsonBuilder().create();
            if (DEBUG) {
                Log.d(TAG, "gift extparam:" + gsonCreate.toJson(yIMExtraGifParam));
            }
            int iIM_SendGift = IMEngine.IM_SendGift(str, str2, i, i2, gsonCreate.toJson(yIMExtraGifParam), messageRequestId);
            messageSendStatus.setMessageId(messageRequestId.getId());
            messageSendStatus.setErrorCode(iIM_SendGift);
        }
        return messageSendStatus;
    }

    public int multiSendTextMessage(List<String> list, String str) {
        return IMEngine.IM_MultiSendTextMessage(new JSONArray((Collection) list).toString(), str);
    }

    public int getHistoryContact() {
        return IMEngine.IM_GetHistoryContact();
    }

    public MessageSendStatus startAudioSpeech(boolean z) {
        MessageSendStatus messageSendStatus = new MessageSendStatus();
        IMEngine iMEngine = new IMEngine();
        iMEngine.getClass();
        IMEngine.MessageRequestId messageRequestId = iMEngine.new MessageRequestId();
        int iIM_StartAudioSpeech = IMEngine.IM_StartAudioSpeech(messageRequestId, z);
        messageSendStatus.setMessageId(messageRequestId.getId());
        messageSendStatus.setErrorCode(iIM_StartAudioSpeech);
        return messageSendStatus;
    }

    public int stopAudioSpeech() {
        return IMEngine.IM_StopAudioSpeech();
    }

    public int convertAMRToWav(String str, String str2) {
        return IMEngine.IM_ConvertAMRToWav(str, str2);
    }

    public int queryHistoryMessage(String str, int i, long j, int i2, int i3) {
        return IMEngine.IM_QueryHistoryMessage(str, i, j, i2, i3);
    }

    public int deleteHistoryMessageByID(long j) {
        return IMEngine.IM_DeleteHistoryMessageByID(j);
    }

    public int deleteHistoryMessageBeforeTime(int i, long j) {
        return IMEngine.IM_DeleteHistoryMessage(i, j);
    }

    public int deleteSpecifiedHistoryMessage(String str, int i, long[] jArr) {
        return IMEngine.IM_DeleteSpecifiedHistoryMessage(str, i, jArr);
    }

    public int deleteHistoryMessageByTarget(String str, int i, long j, int i2) {
        return IMEngine.IM_DeleteHistoryMessageByTarget(str, i, j, i2);
    }

    public int SetUserInfo(YIMExtraUserInfo yIMExtraUserInfo) {
        if (yIMExtraUserInfo == null) {
            return 3;
        }
        return IMEngine.IM_SetUserInfo(new GsonBuilder().create().toJson(yIMExtraUserInfo));
    }

    public int getUserInfo(String str) {
        return IMEngine.IM_GetUserInfo(str);
    }

    public int SetRoomHistoryMessageSwitch(List<String> list, boolean z) {
        return IMEngine.IM_SetRoomHistoryMessageSwitch(new JSONArray((Collection) list).toString(), z);
    }

    public int StartPlayAudio(String str) {
        return IMEngine.IM_StartPlayAudio(str);
    }

    public int StopPlayAudio() {
        return IMEngine.IM_StopPlayAudio();
    }

    public boolean IsPlaying() {
        return IMEngine.IM_IsPlaying();
    }

    public void SetVolume(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > 1.0f) {
            f = 1.0f;
        }
        IMEngine.IM_SetVolume(f);
    }

    public String GetAudioCachePath() {
        return IMEngine.IM_GetAudioCachePath();
    }

    public boolean ClearAudioCachePath() {
        return IMEngine.IM_ClearAudioCachePath();
    }

    public int QueryUserStatus(String str) {
        return IMEngine.IM_QueryUserStatus(str);
    }

    public TranslateMsgStatus translateText(String str, int i, int i2) {
        TranslateMsgStatus translateMsgStatus = new TranslateMsgStatus();
        IMEngine iMEngine = new IMEngine();
        iMEngine.getClass();
        translateMsgStatus.setErrorCode(IMEngine.IM_TranslateText(iMEngine.new IntegerVal(), str, i, i2));
        translateMsgStatus.setMessageId(r1.getValue());
        return translateMsgStatus;
    }

    public int blockUser(String str, boolean z) {
        return IMEngine.IM_BlockUser(str, z);
    }

    public int unBlockAllUser() {
        return IMEngine.IM_UnBlockAllUser();
    }

    public int getBlockUsers() {
        return IMEngine.IM_GetBlockUsers();
    }

    public int getCurrentLocation() {
        return IMEngine.IM_GetCurrentLocation();
    }

    public int getNearbyObjects(int i, String str, int i2, boolean z) {
        return IMEngine.IM_GetNearbyObjects(i, str, i2, z);
    }

    public int getDistance(String str) {
        return IMEngine.IM_GetDistance(str);
    }

    public void setUpdateInterval(int i) {
        IMEngine.IM_SetUpdateInterval(i);
    }

    public void getMicrophoneStatus() {
        IMEngine.IM_GetMicrophoneStatus();
    }

    public int setSpeechRecognizeLanguage(int i) {
        return IMEngine.IM_SetSpeechRecognizeLanguage(i);
    }

    public int setOnlyRecognizeSpeechText(boolean z) {
        return IMEngine.IM_SetOnlyRecognizeSpeechText(z);
    }

    public int accusation(String str, int i, int i2, String str2, String str3) {
        return IMEngine.IM_Accusation(str, i, i2, str2, str3);
    }

    public int queryNotice() {
        return IMEngine.IM_QueryNotice();
    }

    public int getForbiddenSpeakInfo() {
        return IMEngine.IM_GetForbiddenSpeakInfo();
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

    public int setUserProfileInfo(YIMUserSettingInfo yIMUserSettingInfo) {
        if (yIMUserSettingInfo == null) {
            return 3;
        }
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
        return IMEngine.IM_SetUserProfileInfo(jSONObject.toString());
    }

    public int setUserProfilePhoto(String str) {
        return IMEngine.IM_SetUserProfilePhoto(str);
    }

    public int getUserProfileInfo(String str) {
        return IMEngine.IM_GetUserProfileInfo(str);
    }

    public int switchUserStatus(String str, int i) {
        return IMEngine.IM_SwitchUserStatus(str, i);
    }

    public int setAddPermission(boolean z, int i) {
        return IMEngine.IM_SetAddPermission(z, i);
    }

    public int findUser(int i, String str) {
        return IMEngine.IM_FindUser(i, str);
    }

    public int requestAddFriend(List<String> list, String str) {
        return IMEngine.IM_RequestAddFriend(new JSONArray((Collection) list).toString(), str);
    }

    public int dealBeRequestAddFriend(String str, int i) {
        return IMEngine.IM_DealBeRequestAddFriend(str, i);
    }

    public int deleteFriend(List<String> list, int i) {
        return IMEngine.IM_DeleteFriend(new JSONArray((Collection) list).toString(), i);
    }

    public int blackFriend(int i, List<String> list) {
        return IMEngine.IM_BlackFriend(i, new JSONArray((Collection) list).toString());
    }

    public int queryFriends(int i, int i2, int i3) {
        return IMEngine.IM_QueryFriends(i, i2, i3);
    }

    public int queryFriendRequestList(int i, int i2) {
        return IMEngine.IM_QueryFriendRequestList(i, i2);
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

    /* JADX INFO: Access modifiers changed from: private */
    public void handlerMessage(String str) {
        Gson gson;
        IYIMMessageBodyBase yIMMessageBodyGift;
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
                Login login = (Login) gson.fromJson(str, Login.class);
                try {
                    Log.d(TAG, "mLoginListener class:" + this.mLoginListener.getClass().getName());
                    this.mHandler.post(new YIMCallBackProtocol(this.mLoginListener, "onLogin", login.getYoumeId(), youMeIMJsonResponse.errcode));
                    return;
                } catch (NullPointerException e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 7) {
                SendVoiceMsgInfo sendVoiceMsgInfo = (SendVoiceMsgInfo) gson.fromJson(str, SendVoiceMsgInfo.class);
                try {
                    this.mHandler.post(new YIMCallBackProtocol(this.mMessageListener, "onSendAudioMessageStatus", Long.valueOf(sendVoiceMsgInfo.getRequestId()), youMeIMJsonResponse.errcode, sendVoiceMsgInfo.getText(), sendVoiceMsgInfo.getLocalPath(), Integer.valueOf(sendVoiceMsgInfo.getDuration()), Integer.valueOf(sendVoiceMsgInfo.getSendTime()), Boolean.valueOf(sendVoiceMsgInfo.getIsForbidRoom()), Integer.valueOf(sendVoiceMsgInfo.getReasonType()), Long.valueOf(sendVoiceMsgInfo.getEndTime()), Long.valueOf(sendVoiceMsgInfo.getMessageID())));
                    return;
                } catch (NullPointerException e3) {
                    e3.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 11) {
                try {
                    this.mHandler.post(new YIMCallBackProtocol(this.mLoginListener, "onKickOff", new Object[0]));
                    return;
                } catch (NullPointerException e4) {
                    e4.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 31) {
                Log.d("CMD_GET_PEOPLE_NEARBY", "msg = " + str);
                try {
                    this.mHandler.post(new YIMCallBackProtocol(this.mMessageListener, "OnGetForbiddenSpeakInfo", youMeIMJsonResponse.errcode, ((YIMForbiddenSpeakList) gson.fromJson(str, YIMForbiddenSpeakList.class)).forbiddenList));
                    return;
                } catch (Exception e5) {
                    e5.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 40) {
                try {
                    this.mHandler.post(new YIMCallBackProtocol(this.mFriendListener, "OnFindUser", youMeIMJsonResponse.errcode, ((YIMUserBriefInfoList) gson.fromJson(str, YIMUserBriefInfoList.class)).userList));
                    return;
                } catch (NullPointerException e6) {
                    e6.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 42) {
                try {
                    this.mHandler.post(new YIMCallBackProtocol(this.mFriendListener, "OnRequestAddFriend", youMeIMJsonResponse.errcode, ((YIMFriendUserID) gson.fromJson(str, YIMFriendUserID.class)).userID));
                    return;
                } catch (NullPointerException e7) {
                    e7.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 51) {
                try {
                    YIMFriendRequestInfoList yIMFriendRequestInfoList = (YIMFriendRequestInfoList) gson.fromJson(str, YIMFriendRequestInfoList.class);
                    ArrayList<YIMFriendRequestInfo> arrayList = yIMFriendRequestInfoList.userList;
                    this.mHandler.post(new YIMCallBackProtocol(this.mFriendListener, "OnQueryFriendRequestList", youMeIMJsonResponse.errcode, Integer.valueOf(yIMFriendRequestInfoList.startIndex), yIMFriendRequestInfoList.userList));
                    return;
                } catch (NullPointerException e8) {
                    e8.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 74) {
                try {
                    this.mHandler.post(new YIMCallBackProtocol(this.mUserProfileListener, "OnUserInfoChangeNotify", ((YIMFriendUserID) gson.fromJson(str, YIMFriendUserID.class)).userID));
                    return;
                } catch (NullPointerException e9) {
                    e9.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 3) {
                try {
                    this.mHandler.post(new YIMCallBackProtocol(this.mLoginListener, "onLogout", new Object[0]));
                    return;
                } catch (NullPointerException e10) {
                    e10.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 4) {
                try {
                    this.mHandler.post(new YIMCallBackProtocol(this.mChatRoomListener, "onJoinChatRoom", youMeIMJsonResponse.errcode, ((ChatRoom) gson.fromJson(str, ChatRoom.class)).groupId));
                    return;
                } catch (NullPointerException e11) {
                    e11.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 5) {
                try {
                    this.mHandler.post(new YIMCallBackProtocol(this.mChatRoomListener, "onLeaveChatRoom", youMeIMJsonResponse.errcode, ((ChatRoom) gson.fromJson(str, ChatRoom.class)).groupId));
                    return;
                } catch (NullPointerException e12) {
                    e12.printStackTrace();
                    return;
                }
            }
            IYIMMessageBodyBase yIMMessageBodyFile = null;
            if (iIntValue == 16) {
                RoomHistoryMessage roomHistoryMessage = (RoomHistoryMessage) gson.fromJson(str, RoomHistoryMessage.class);
                ArrayList arrayList2 = new ArrayList();
                if (roomHistoryMessage.messageList != null && roomHistoryMessage.messageList.size() > 0) {
                    for (RecvMessage recvMessage : roomHistoryMessage.messageList) {
                        YIMMessage yIMMessage = new YIMMessage();
                        yIMMessage.setChatType(recvMessage.chatType.intValue());
                        yIMMessage.setReceiveID(recvMessage.receiveId);
                        yIMMessage.setSenderID(recvMessage.senderId);
                        yIMMessage.setMeesageID(recvMessage.serial.longValue());
                        yIMMessage.setMessageType(recvMessage.msgType.intValue());
                        yIMMessage.setCreateTime(recvMessage.createTime.intValue());
                        yIMMessage.setDistance(recvMessage.distance.intValue());
                        yIMMessage.setRead(recvMessage.getIsRead());
                        if (recvMessage.msgType.intValue() == 1) {
                            yIMMessageBodyGift = new YIMMessageBodyText();
                            ((YIMMessageBodyText) yIMMessageBodyGift).setMessageContent(recvMessage.content);
                            ((YIMMessageBodyText) yIMMessageBodyGift).setAttachParam(recvMessage.attachParam);
                        } else if (recvMessage.msgType.intValue() == 2) {
                            yIMMessageBodyGift = new YIMMessageBodyCustom();
                            ((YIMMessageBodyCustom) yIMMessageBodyGift).setMessageContent(recvMessage.content);
                        } else if (recvMessage.msgType.intValue() == 5) {
                            yIMMessageBodyGift = new YIMMessageBodyAudio();
                            ((YIMMessageBodyAudio) yIMMessageBodyGift).setAudioTime(recvMessage.duration.intValue());
                            ((YIMMessageBodyAudio) yIMMessageBodyGift).setParam(recvMessage.param);
                            ((YIMMessageBodyAudio) yIMMessageBodyGift).setText(recvMessage.extraText);
                        } else if (recvMessage.msgType.intValue() == 7) {
                            yIMMessageBodyGift = new YIMMessageBodyFile();
                            ((YIMMessageBodyFile) yIMMessageBodyGift).setFileType(recvMessage.fileType.intValue());
                            ((YIMMessageBodyFile) yIMMessageBodyGift).setFileSize(recvMessage.fileSize.intValue());
                            ((YIMMessageBodyFile) yIMMessageBodyGift).setFileExtension(recvMessage.fileExtension);
                            ((YIMMessageBodyFile) yIMMessageBodyGift).setExtParam(recvMessage.extraParam);
                            ((YIMMessageBodyFile) yIMMessageBodyGift).setFileName(recvMessage.fileName);
                        } else if (recvMessage.msgType.intValue() == 8) {
                            yIMMessageBodyGift = new YIMMessageBodyGift();
                            ((YIMMessageBodyGift) yIMMessageBodyGift).setAnchor(recvMessage.anchor);
                            ((YIMMessageBodyGift) yIMMessageBodyGift).setGiftCount(recvMessage.giftCount);
                            ((YIMMessageBodyGift) yIMMessageBodyGift).setGiftID(recvMessage.giftID);
                            try {
                                ((YIMMessageBodyGift) yIMMessageBodyGift).setExtParam((YIMExtraGifParam) gson.fromJson(recvMessage.param, YIMExtraGifParam.class));
                            } catch (Exception e13) {
                                e13.printStackTrace();
                                return;
                            }
                        } else {
                            yIMMessageBodyGift = null;
                        }
                        yIMMessage.setMessageBody(yIMMessageBodyGift);
                        arrayList2.add(yIMMessage);
                    }
                }
                try {
                    this.mHandler.post(new YIMCallBackProtocol(this.mMessageListener, "OnQueryRoomHistoryMessageFromServer", youMeIMJsonResponse.errcode, roomHistoryMessage.roomID, roomHistoryMessage.remain, arrayList2));
                    return;
                } catch (NullPointerException e14) {
                    e14.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 17) {
                UserInfoString userInfoString = (UserInfoString) gson.fromJson(str, UserInfoString.class);
                try {
                    YIMExtraUserInfo yIMExtraUserInfo = (YIMExtraUserInfo) gson.fromJson(userInfoString.userInfoString, YIMExtraUserInfo.class);
                    yIMExtraUserInfo.setUserID(userInfoString.userID);
                    this.mHandler.post(new YIMCallBackProtocol(this.mContactListener, "onGetUserInfo", youMeIMJsonResponse.errcode, yIMExtraUserInfo));
                    return;
                } catch (Exception e15) {
                    e15.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 45) {
                try {
                    this.mHandler.post(new YIMCallBackProtocol(this.mFriendListener, "OnDeleteFriend", youMeIMJsonResponse.errcode, ((YIMFriendUserID) gson.fromJson(str, YIMFriendUserID.class)).userID));
                    return;
                } catch (NullPointerException e16) {
                    e16.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 46) {
                try {
                    YIMBlackFriendInfo yIMBlackFriendInfo = (YIMBlackFriendInfo) gson.fromJson(str, YIMBlackFriendInfo.class);
                    this.mHandler.post(new YIMCallBackProtocol(this.mFriendListener, "OnBlackFriend", youMeIMJsonResponse.errcode, Integer.valueOf(yIMBlackFriendInfo.type), yIMBlackFriendInfo.userID));
                    return;
                } catch (NullPointerException e17) {
                    e17.printStackTrace();
                    return;
                }
            }
            if (iIntValue == 48) {
                try {
                    YIMFriendDealResult yIMFriendDealResult = (YIMFriendDealResult) gson.fromJson(str, YIMFriendDealResult.class);
                    this.mHandler.post(new YIMCallBackProtocol(this.mFriendListener, "OnDealBeRequestAddFriend", youMeIMJsonResponse.errcode, yIMFriendDealResult.userID, yIMFriendDealResult.comments, Integer.valueOf(yIMFriendDealResult.dealResult)));
                    return;
                } catch (NullPointerException e18) {
                    e18.printStackTrace();
                    return;
                }
            }
            if (iIntValue != 49) {
                switch (iIntValue) {
                    case 20:
                        AccusationResult accusationResult = (AccusationResult) gson.fromJson(str, AccusationResult.class);
                        try {
                            this.mHandler.post(new YIMCallBackProtocol(this.mMessageListener, "onAccusationResultNotify", Integer.valueOf(accusationResult.iResult), accusationResult.strUserID, Integer.valueOf(accusationResult.iAccusationTime)));
                            return;
                        } catch (NullPointerException e19) {
                            e19.printStackTrace();
                            return;
                        }
                    case 21:
                        Log.d("CMD_GET_DISTRICT", "msg = " + str);
                        try {
                            this.mHandler.post(new YIMCallBackProtocol(this.mLocationListen, "OnUpdateLocation", youMeIMJsonResponse.errcode, (GeographyLocation) gson.fromJson(str, GeographyLocation.class)));
                            return;
                        } catch (Exception e20) {
                            e20.printStackTrace();
                            return;
                        }
                    case 22:
                        Log.d("CMD_GET_PEOPLE_NEARBY", "msg = " + str);
                        RelativeLocationInfo relativeLocationInfo = (RelativeLocationInfo) gson.fromJson(str, RelativeLocationInfo.class);
                        try {
                            this.mHandler.post(new YIMCallBackProtocol(this.mLocationListen, "OnGetNearbyObjects", youMeIMJsonResponse.errcode, relativeLocationInfo.relativeLocations, Integer.valueOf(relativeLocationInfo.startDistance), Integer.valueOf(relativeLocationInfo.endDistance)));
                            return;
                        } catch (Exception e21) {
                            e21.printStackTrace();
                            return;
                        }
                    default:
                        switch (iIntValue) {
                            case 24:
                                try {
                                    BlockUserInfo blockUserInfo = (BlockUserInfo) gson.fromJson(str, BlockUserInfo.class);
                                    this.mHandler.post(new YIMCallBackProtocol(this.mMessageListener, "OnBlockUser", youMeIMJsonResponse.errcode, blockUserInfo.getUserID(), Boolean.valueOf(blockUserInfo.getBlock())));
                                    return;
                                } catch (NullPointerException e22) {
                                    e22.printStackTrace();
                                    return;
                                }
                            case 25:
                                try {
                                    this.mHandler.post(new YIMCallBackProtocol(this.mMessageListener, "OnGetBlockUsers", youMeIMJsonResponse.errcode, ((BlockUserList) gson.fromJson(str, BlockUserList.class)).userList));
                                    return;
                                } catch (NullPointerException e23) {
                                    e23.printStackTrace();
                                    return;
                                }
                            case 26:
                                try {
                                    this.mHandler.post(new YIMCallBackProtocol(this.mMessageListener, "OnUnBlockAllUser", youMeIMJsonResponse.errcode));
                                    return;
                                } catch (NullPointerException e24) {
                                    e24.printStackTrace();
                                    return;
                                }
                            case 27:
                                try {
                                    RoomInfo roomInfo = (RoomInfo) gson.fromJson(str, RoomInfo.class);
                                    this.mHandler.post(new YIMCallBackProtocol(this.mChatRoomListener, "OnGetRoomMemberCount", youMeIMJsonResponse.errcode, roomInfo.roomID, Integer.valueOf(roomInfo.memberCount)));
                                    return;
                                } catch (NullPointerException e25) {
                                    e25.printStackTrace();
                                    return;
                                }
                            case 28:
                                try {
                                    this.mHandler.post(new YIMCallBackProtocol(this.mChatRoomListener, "OnLeaveAllChatRooms", youMeIMJsonResponse.errcode));
                                    return;
                                } catch (NullPointerException e26) {
                                    e26.printStackTrace();
                                    return;
                                }
                            default:
                                switch (iIntValue) {
                                    case 20001:
                                        Download download = (Download) gson.fromJson(str, Download.class);
                                        YIMMessage yIMMessage2 = new YIMMessage();
                                        RecvMessage recvMessage2 = (RecvMessage) gson.fromJson(str, RecvMessage.class);
                                        yIMMessage2.setChatType(recvMessage2.chatType.intValue());
                                        yIMMessage2.setReceiveID(recvMessage2.receiveId);
                                        yIMMessage2.setSenderID(recvMessage2.senderId);
                                        yIMMessage2.setMeesageID(recvMessage2.serial.longValue());
                                        yIMMessage2.setMessageType(recvMessage2.msgType.intValue());
                                        yIMMessage2.setCreateTime(recvMessage2.createTime.intValue());
                                        yIMMessage2.setDistance(recvMessage2.distance.intValue());
                                        yIMMessage2.setRead(recvMessage2.getIsRead());
                                        if (recvMessage2.msgType.intValue() == 5) {
                                            yIMMessageBodyFile = new YIMMessageBodyAudio();
                                            ((YIMMessageBodyAudio) yIMMessageBodyFile).setAudioTime(recvMessage2.duration.intValue());
                                            ((YIMMessageBodyAudio) yIMMessageBodyFile).setParam(recvMessage2.param);
                                            ((YIMMessageBodyAudio) yIMMessageBodyFile).setText(recvMessage2.extraText);
                                            ((YIMMessageBodyAudio) yIMMessageBodyFile).setLocalPath(download.savePath);
                                        } else if (recvMessage2.msgType.intValue() == 7) {
                                            yIMMessageBodyFile = new YIMMessageBodyFile();
                                            ((YIMMessageBodyFile) yIMMessageBodyFile).setFileType(recvMessage2.fileType.intValue());
                                            ((YIMMessageBodyFile) yIMMessageBodyFile).setFileSize(recvMessage2.fileSize.intValue());
                                            ((YIMMessageBodyFile) yIMMessageBodyFile).setFileExtension(recvMessage2.fileExtension);
                                            ((YIMMessageBodyFile) yIMMessageBodyFile).setExtParam(recvMessage2.extraParam);
                                            ((YIMMessageBodyFile) yIMMessageBodyFile).setFileName(recvMessage2.fileName);
                                            ((YIMMessageBodyFile) yIMMessageBodyFile).setLocalPath(download.savePath);
                                        }
                                        yIMMessage2.setMessageBody(yIMMessageBodyFile);
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mMessageListener, "onDownload", youMeIMJsonResponse.errcode, yIMMessage2, download.savePath));
                                            return;
                                        } catch (NullPointerException e27) {
                                            e27.printStackTrace();
                                            return;
                                        }
                                    case CMD_SEND_MESSAGE_STATUS /* 20002 */:
                                        SendMessage sendMessage = (SendMessage) gson.fromJson(str, SendMessage.class);
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mMessageListener, "onSendMessageStatus", Long.valueOf(sendMessage.getRequestId()), youMeIMJsonResponse.errcode, Integer.valueOf(sendMessage.getSendTime()), Boolean.valueOf(sendMessage.getIsForbidRoom()), Integer.valueOf(sendMessage.getReasonType()), Long.valueOf(sendMessage.getEndTime()), Long.valueOf(sendMessage.getMessageID())));
                                            return;
                                        } catch (NullPointerException e28) {
                                            e28.printStackTrace();
                                            return;
                                        }
                                    case CMD_RECV_MESSAGE /* 20003 */:
                                        YIMMessage yIMMessage3 = new YIMMessage();
                                        RecvMessage recvMessage3 = (RecvMessage) gson.fromJson(str, RecvMessage.class);
                                        yIMMessage3.setChatType(recvMessage3.chatType.intValue());
                                        yIMMessage3.setReceiveID(recvMessage3.receiveId);
                                        yIMMessage3.setSenderID(recvMessage3.senderId);
                                        yIMMessage3.setMeesageID(recvMessage3.serial.longValue());
                                        yIMMessage3.setMessageType(recvMessage3.msgType.intValue());
                                        yIMMessage3.setCreateTime(recvMessage3.createTime.intValue());
                                        yIMMessage3.setDistance(recvMessage3.distance.intValue());
                                        yIMMessage3.setRead(recvMessage3.getIsRead());
                                        if (recvMessage3.msgType.intValue() == 1) {
                                            yIMMessageBodyFile = new YIMMessageBodyText();
                                            ((YIMMessageBodyText) yIMMessageBodyFile).setMessageContent(recvMessage3.content);
                                            ((YIMMessageBodyText) yIMMessageBodyFile).setAttachParam(recvMessage3.attachParam);
                                        } else if (recvMessage3.msgType.intValue() == 2) {
                                            yIMMessageBodyFile = new YIMMessageBodyCustom();
                                            ((YIMMessageBodyCustom) yIMMessageBodyFile).setMessageContent(recvMessage3.content);
                                        } else if (recvMessage3.msgType.intValue() == 5) {
                                            yIMMessageBodyFile = new YIMMessageBodyAudio();
                                            ((YIMMessageBodyAudio) yIMMessageBodyFile).setAudioTime(recvMessage3.duration.intValue());
                                            ((YIMMessageBodyAudio) yIMMessageBodyFile).setParam(recvMessage3.param);
                                            ((YIMMessageBodyAudio) yIMMessageBodyFile).setText(recvMessage3.extraText);
                                        } else if (recvMessage3.msgType.intValue() == 7) {
                                            yIMMessageBodyFile = new YIMMessageBodyFile();
                                            ((YIMMessageBodyFile) yIMMessageBodyFile).setFileType(recvMessage3.fileType.intValue());
                                            ((YIMMessageBodyFile) yIMMessageBodyFile).setFileSize(recvMessage3.fileSize.intValue());
                                            ((YIMMessageBodyFile) yIMMessageBodyFile).setFileExtension(recvMessage3.fileExtension);
                                            ((YIMMessageBodyFile) yIMMessageBodyFile).setExtParam(recvMessage3.extraParam);
                                            ((YIMMessageBodyFile) yIMMessageBodyFile).setFileName(recvMessage3.fileName);
                                        } else if (recvMessage3.msgType.intValue() == 8) {
                                            yIMMessageBodyFile = new YIMMessageBodyGift();
                                            ((YIMMessageBodyGift) yIMMessageBodyFile).setAnchor(recvMessage3.anchor);
                                            ((YIMMessageBodyGift) yIMMessageBodyFile).setGiftCount(recvMessage3.giftCount);
                                            ((YIMMessageBodyGift) yIMMessageBodyFile).setGiftID(recvMessage3.giftID);
                                            try {
                                                YIMExtraGifParam yIMExtraGifParam = (YIMExtraGifParam) gson.fromJson(recvMessage3.param, YIMExtraGifParam.class);
                                                if (DEBUG) {
                                                    Log.d(TAG, "recv giftExtParam:" + yIMExtraGifParam.getNickName());
                                                }
                                                ((YIMMessageBodyGift) yIMMessageBodyFile).setExtParam(yIMExtraGifParam);
                                            } catch (Exception e29) {
                                                e29.printStackTrace();
                                                return;
                                            }
                                        }
                                        yIMMessage3.setMessageBody(yIMMessageBodyFile);
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mMessageListener, "onRecvMessage", yIMMessage3));
                                            return;
                                        } catch (NullPointerException e30) {
                                            e30.printStackTrace();
                                            return;
                                        }
                                    case CMD_STOP_AUDIOSPEECH /* 20004 */:
                                        SpeechMessageInfo speechMessageInfo = (SpeechMessageInfo) gson.fromJson(str, SpeechMessageInfo.class);
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mMessageListener, "OnStopAudioSpeechStatus", youMeIMJsonResponse.errcode, Long.valueOf(speechMessageInfo.getRequestID()), speechMessageInfo.getDownloadURL(), Integer.valueOf(speechMessageInfo.getDuration()), Integer.valueOf(speechMessageInfo.getFileSize()), speechMessageInfo.getLocalPath(), speechMessageInfo.getText()));
                                            return;
                                        } catch (NullPointerException e31) {
                                            e31.printStackTrace();
                                            return;
                                        }
                                    case CMD_QUERY_HISTORY_MESSAGE /* 20005 */:
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mMessageListener, "OnQueryHistoryMessage", youMeIMJsonResponse.errcode, (YIMHistoryMessage) gson.fromJson(str, YIMHistoryMessage.class)));
                                            return;
                                        } catch (Exception e32) {
                                            e32.printStackTrace();
                                            return;
                                        }
                                    case CMD_GET_RENCENT_CONTACTS /* 20006 */:
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mContactListener, "onGetContact", ((Contacts) gson.fromJson(str, Contacts.class)).contacts));
                                            return;
                                        } catch (NullPointerException e33) {
                                            e33.printStackTrace();
                                            return;
                                        }
                                    case CMD_RECEIVE_MESSAGE_NITIFY /* 20007 */:
                                        try {
                                            NewMessageNotifyObj newMessageNotifyObj = (NewMessageNotifyObj) gson.fromJson(str, NewMessageNotifyObj.class);
                                            this.mHandler.post(new YIMCallBackProtocol(this.mMessageListener, "OnRecvNewMessage", newMessageNotifyObj.chatType, newMessageNotifyObj.targetID));
                                            return;
                                        } catch (NullPointerException e34) {
                                            e34.printStackTrace();
                                            return;
                                        }
                                    case CMD_QUERY_USER_STATUS /* 20008 */:
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mContactListener, "onQueryUserStatus", youMeIMJsonResponse.errcode, (UserStatusInfo) gson.fromJson(str, UserStatusInfo.class)));
                                            return;
                                        } catch (Exception e35) {
                                            e35.printStackTrace();
                                            return;
                                        }
                                    case CMD_AUDIO_PLAY_COMPLETE /* 20009 */:
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mAudioPlayListener, "onPlayCompletion", youMeIMJsonResponse.errcode, ((OnPlayCompleteNotify) gson.fromJson(str, OnPlayCompleteNotify.class)).audioPath));
                                            return;
                                        } catch (Exception e36) {
                                            e36.printStackTrace();
                                            return;
                                        }
                                    case CMD_STOP_SEND_AUDIO /* 20010 */:
                                        VoiceInfo voiceInfo = (VoiceInfo) gson.fromJson(str, VoiceInfo.class);
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mMessageListener, "onStartSendAudioMessage", voiceInfo.requestId, youMeIMJsonResponse.errcode, voiceInfo.extraText, voiceInfo.localPath, voiceInfo.duration));
                                            return;
                                        } catch (NullPointerException e37) {
                                            e37.printStackTrace();
                                            return;
                                        }
                                    case CMD_TRANSLATE_COMPLETE /* 20011 */:
                                        Log.d("CMD_TRANSLATE_COMPLETE", "msg = " + str);
                                        TranlateTextInfo tranlateTextInfo = (TranlateTextInfo) gson.fromJson(str, TranlateTextInfo.class);
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mMessageListener, "OnTranslateTextComplete", youMeIMJsonResponse.errcode, Integer.valueOf(tranlateTextInfo.requestID), tranlateTextInfo.text, Integer.valueOf(tranlateTextInfo.srcLangCode), Integer.valueOf(tranlateTextInfo.destLangCode)));
                                            return;
                                        } catch (NullPointerException e38) {
                                            e38.printStackTrace();
                                            return;
                                        }
                                    case CMD_DOWNLOAD_URL /* 20012 */:
                                        DownloadUrl downloadUrl = (DownloadUrl) gson.fromJson(str, DownloadUrl.class);
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mMessageListener, "onDownloadByUrl", youMeIMJsonResponse.errcode, downloadUrl.fromUrl, downloadUrl.savePath, Integer.valueOf(downloadUrl.audioTime)));
                                            return;
                                        } catch (NullPointerException e39) {
                                            e39.printStackTrace();
                                            return;
                                        }
                                    case CMD_GET_MICROPHONE_STATUS /* 20013 */:
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mAudioPlayListener, "onGetMicrophoneStatus", Integer.valueOf(((MicrophoneStatus) gson.fromJson(str, MicrophoneStatus.class)).status)));
                                            return;
                                        } catch (NullPointerException e40) {
                                            e40.printStackTrace();
                                            return;
                                        }
                                    case CMD_USER_ENTER_ROOM /* 20014 */:
                                        UserChatRoom userChatRoom = (UserChatRoom) gson.fromJson(str, UserChatRoom.class);
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mChatRoomListener, "onUserJoinChatRoom", userChatRoom.channelID, userChatRoom.userID));
                                            return;
                                        } catch (NullPointerException e41) {
                                            e41.printStackTrace();
                                            return;
                                        }
                                    case CMD_USER_LEAVE_ROOM /* 20015 */:
                                        UserChatRoom userChatRoom2 = (UserChatRoom) gson.fromJson(str, UserChatRoom.class);
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mChatRoomListener, "onUserLeaveChatRoom", userChatRoom2.channelID, userChatRoom2.userID));
                                            return;
                                        } catch (NullPointerException e42) {
                                            e42.printStackTrace();
                                            return;
                                        }
                                    case CMD_RECV_NOTICE /* 20016 */:
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mNoticeListener, "onRecvNotice", (NoticeInfo) gson.fromJson(str, NoticeInfo.class)));
                                            return;
                                        } catch (NullPointerException e43) {
                                            e43.printStackTrace();
                                            return;
                                        }
                                    case CMD_CANCEL_NOTICE /* 20017 */:
                                        NoticeCancelInfo noticeCancelInfo = (NoticeCancelInfo) gson.fromJson(str, NoticeCancelInfo.class);
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mNoticeListener, "onCancelNotice", Long.valueOf(noticeCancelInfo.iNoticeID), noticeCancelInfo.strChannelID));
                                            return;
                                        } catch (NullPointerException e44) {
                                            e44.printStackTrace();
                                            return;
                                        }
                                    case CMD_GET_SPEECH_TEXT /* 20018 */:
                                        try {
                                            RecognizeSpeechText recognizeSpeechText = (RecognizeSpeechText) gson.fromJson(str, RecognizeSpeechText.class);
                                            this.mHandler.post(new YIMCallBackProtocol(this.mMessageListener, "OnGetRecognizeSpeechText", youMeIMJsonResponse.errcode, recognizeSpeechText.requestId, recognizeSpeechText.text));
                                            return;
                                        } catch (NullPointerException e45) {
                                            e45.printStackTrace();
                                            return;
                                        }
                                    case CMD_GET_RECONNECT_RESULT /* 20019 */:
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mReconnectListener, "OnRecvReconnectResult", Integer.valueOf(((ReconnectResult) gson.fromJson(str, ReconnectResult.class)).iResult)));
                                            return;
                                        } catch (NullPointerException e46) {
                                            e46.printStackTrace();
                                            return;
                                        }
                                    case CMD_START_RECONNECT /* 20020 */:
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mReconnectListener, "OnStartReconnect", new Object[0]));
                                            return;
                                        } catch (NullPointerException e47) {
                                            e47.printStackTrace();
                                            return;
                                        }
                                    case CMD_RECORD_VOLUME /* 20021 */:
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mMessageListener, "OnRecordVolume", Float.valueOf(((VolumeInfo) gson.fromJson(str, VolumeInfo.class)).iVolume)));
                                            return;
                                        } catch (NullPointerException e48) {
                                            e48.printStackTrace();
                                            return;
                                        }
                                    case CMD_GET_DISTANCE /* 20022 */:
                                        UserDistanceInfo userDistanceInfo = (UserDistanceInfo) gson.fromJson(str, UserDistanceInfo.class);
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mLocationListen, "OnGetDistance", youMeIMJsonResponse.errcode, userDistanceInfo.userID, Integer.valueOf(userDistanceInfo.distance)));
                                            return;
                                        } catch (Exception e49) {
                                            e49.printStackTrace();
                                            return;
                                        }
                                    case CMD_REQUEST_ADD_FRIEND_NOTIFY /* 20023 */:
                                        try {
                                            YIMFriendCommon yIMFriendCommon = (YIMFriendCommon) gson.fromJson(str, YIMFriendCommon.class);
                                            this.mHandler.post(new YIMCallBackProtocol(this.mFriendListener, "OnBeRequestAddFriendNotify", yIMFriendCommon.userID, yIMFriendCommon.comments));
                                            return;
                                        } catch (NullPointerException e50) {
                                            e50.printStackTrace();
                                            return;
                                        }
                                    case CMD_ADD_FRIENT_RESULT_NOTIFY /* 20024 */:
                                        try {
                                            YIMFriendDealResult yIMFriendDealResult2 = (YIMFriendDealResult) gson.fromJson(str, YIMFriendDealResult.class);
                                            this.mHandler.post(new YIMCallBackProtocol(this.mFriendListener, "OnRequestAddFriendResultNotify", yIMFriendDealResult2.userID, yIMFriendDealResult2.comments, Integer.valueOf(yIMFriendDealResult2.dealResult)));
                                            return;
                                        } catch (NullPointerException e51) {
                                            e51.printStackTrace();
                                            return;
                                        }
                                    case CMD_BE_ADD_FRIENT /* 20025 */:
                                        try {
                                            YIMFriendCommon yIMFriendCommon2 = (YIMFriendCommon) gson.fromJson(str, YIMFriendCommon.class);
                                            this.mHandler.post(new YIMCallBackProtocol(this.mFriendListener, "OnBeAddFriendNotify", yIMFriendCommon2.userID, yIMFriendCommon2.comments));
                                            return;
                                        } catch (NullPointerException e52) {
                                            e52.printStackTrace();
                                            return;
                                        }
                                    case CMD_BE_DELETE_FRIEND_NOTIFY /* 20026 */:
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mFriendListener, "OnBeDeleteFriendNotify", ((YIMFriendUserID) gson.fromJson(str, YIMFriendUserID.class)).userID));
                                            return;
                                        } catch (NullPointerException e53) {
                                            e53.printStackTrace();
                                            return;
                                        }
                                    case CMD_BE_BLACK_FRIEND_NOTIFY /* 20027 */:
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mFriendListener, "OnBeBlackNotify", ((YIMFriendUserID) gson.fromJson(str, YIMFriendUserID.class)).userID));
                                            return;
                                        } catch (NullPointerException e54) {
                                            e54.printStackTrace();
                                            return;
                                        }
                                    case CMD_GET_USER_PROFILE /* 20028 */:
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mUserProfileListener, "OnQueryUserInfo", youMeIMJsonResponse.errcode, (UserProfileInfo) gson.fromJson(str, UserProfileInfo.class)));
                                            return;
                                        } catch (NullPointerException e55) {
                                            e55.printStackTrace();
                                            return;
                                        }
                                    case CMD_SET_USER_PROFILE /* 20029 */:
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mUserProfileListener, "OnSetUserInfo", youMeIMJsonResponse.errcode));
                                            return;
                                        } catch (NullPointerException e56) {
                                            e56.printStackTrace();
                                            return;
                                        }
                                    case CMD_SET_USER_PHOTO /* 20030 */:
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mUserProfileListener, "OnSetPhotoUrl", youMeIMJsonResponse.errcode, ((PhotoUrlInfo) gson.fromJson(str, PhotoUrlInfo.class)).photoUrl));
                                            return;
                                        } catch (NullPointerException e57) {
                                            e57.printStackTrace();
                                            return;
                                        }
                                    case CMD_SWITCH_USER_STATE /* 20031 */:
                                        try {
                                            this.mHandler.post(new YIMCallBackProtocol(this.mUserProfileListener, "OnSwitchUserOnlineState", youMeIMJsonResponse.errcode));
                                            return;
                                        } catch (NullPointerException e58) {
                                            e58.printStackTrace();
                                            return;
                                        }
                                    default:
                                        return;
                                }
                        }
                }
            }
            try {
                YIMFriendListInfo yIMFriendListInfo = (YIMFriendListInfo) gson.fromJson(str, YIMFriendListInfo.class);
                this.mHandler.post(new YIMCallBackProtocol(this.mFriendListener, "OnQueryFriends", youMeIMJsonResponse.errcode, Integer.valueOf(yIMFriendListInfo.type), Integer.valueOf(yIMFriendListInfo.startIndex), yIMFriendListInfo.userList));
                return;
            } catch (NullPointerException e59) {
                e59.printStackTrace();
                return;
            }
            e.printStackTrace();
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
}
