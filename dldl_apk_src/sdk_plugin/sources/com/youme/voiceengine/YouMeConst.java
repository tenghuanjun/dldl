package com.youme.voiceengine;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class YouMeConst {

    public class ChannelState {
        public static final int CHANNEL_STATE_JOINED = 1;
        public static final int CHANNEL_STATE_JOINIING = 0;
        public static final int CHANNEL_STATE_LEAVED = 4;
        public static final int CHANNEL_STATE_LEAVING_ALL = 3;
        public static final int CHANNEL_STATE_LEAVING_ONE = 2;

        public ChannelState() {
        }
    }

    public class ChannelAudioMode {
        public static final int CHANNEL_AUDIO_MODE_CALL = 0;
        public static final int CHANNEL_AUDIO_MODE_HQ_MUSIC = 1;

        public ChannelAudioMode() {
        }
    }

    public class YouMeUserRole {
        public static final int YOUME_USER_COMMANDER = 4;
        public static final int YOUME_USER_GUSET = 6;
        public static final int YOUME_USER_HOST = 5;
        public static final int YOUME_USER_LISTENER = 3;
        public static final int YOUME_USER_NONE = 0;
        public static final int YOUME_USER_TALKER_FREE = 1;
        public static final int YOUME_USER_TALKER_ON_DEMAND = 2;

        public YouMeUserRole() {
        }
    }

    public class YouMeEvent {
        public static final int YOUME_EVENT_BGM_FAILED = 14;
        public static final int YOUME_EVENT_BGM_STOPPED = 13;
        public static final int YOUME_EVENT_CHECK_DEVICE_MUTE_RESULT = 76;
        public static final int YOUME_EVENT_EFFECT_PLAY_COMPLETE = 71;
        public static final int YOUME_EVENT_EOF = 1000;
        public static final int YOUME_EVENT_FAREND_VOICE_LEVEL = 66;
        public static final int YOUME_EVENT_GRABMIC_ENDMIC = 42;
        public static final int YOUME_EVENT_GRABMIC_NOTIFY_HASMIC = 45;
        public static final int YOUME_EVENT_GRABMIC_NOTIFY_NOMIC = 46;
        public static final int YOUME_EVENT_GRABMIC_NOTIFY_START = 43;
        public static final int YOUME_EVENT_GRABMIC_NOTIFY_STOP = 44;
        public static final int YOUME_EVENT_GRABMIC_RELEASE_FAILED = 41;
        public static final int YOUME_EVENT_GRABMIC_RELEASE_OK = 40;
        public static final int YOUME_EVENT_GRABMIC_REQUEST_FAILED = 38;
        public static final int YOUME_EVENT_GRABMIC_REQUEST_OK = 37;
        public static final int YOUME_EVENT_GRABMIC_REQUEST_WAIT = 39;
        public static final int YOUME_EVENT_GRABMIC_START_FAILED = 34;
        public static final int YOUME_EVENT_GRABMIC_START_OK = 33;
        public static final int YOUME_EVENT_GRABMIC_STOP_FAILED = 36;
        public static final int YOUME_EVENT_GRABMIC_STOP_OK = 35;
        public static final int YOUME_EVENT_IDENTITY_MODIFY = 77;
        public static final int YOUME_EVENT_INIT_FAILED = 1;
        public static final int YOUME_EVENT_INIT_OK = 0;
        public static final int YOUME_EVENT_INVITEMIC_CANNOT_TALK = 56;
        public static final int YOUME_EVENT_INVITEMIC_CAN_TALK = 55;
        public static final int YOUME_EVENT_INVITEMIC_NOTIFY_ANSWER = 58;
        public static final int YOUME_EVENT_INVITEMIC_NOTIFY_CALL = 57;
        public static final int YOUME_EVENT_INVITEMIC_NOTIFY_CANCEL = 59;
        public static final int YOUME_EVENT_INVITEMIC_REQUEST_FAILED = 50;
        public static final int YOUME_EVENT_INVITEMIC_REQUEST_OK = 49;
        public static final int YOUME_EVENT_INVITEMIC_RESPONSE_FAILED = 52;
        public static final int YOUME_EVENT_INVITEMIC_RESPONSE_OK = 51;
        public static final int YOUME_EVENT_INVITEMIC_SETOPT_FAILED = 48;
        public static final int YOUME_EVENT_INVITEMIC_SETOPT_OK = 47;
        public static final int YOUME_EVENT_INVITEMIC_STOP_FAILED = 54;
        public static final int YOUME_EVENT_INVITEMIC_STOP_OK = 53;
        public static final int YOUME_EVENT_JOIN_FAILED = 3;
        public static final int YOUME_EVENT_JOIN_OK = 2;
        public static final int YOUME_EVENT_KICK_NOTIFY = 65;
        public static final int YOUME_EVENT_KICK_RESULT = 64;
        public static final int YOUME_EVENT_LEAVED_ALL = 5;
        public static final int YOUME_EVENT_LEAVED_ONE = 4;
        public static final int YOUME_EVENT_LISTEN_OTHER_OFF = 28;
        public static final int YOUME_EVENT_LISTEN_OTHER_ON = 27;
        public static final int YOUME_EVENT_LOCAL_MIC_OFF = 30;
        public static final int YOUME_EVENT_LOCAL_MIC_ON = 29;
        public static final int YOUME_EVENT_LOCAL_SPEAKER_OFF = 32;
        public static final int YOUME_EVENT_LOCAL_SPEAKER_ON = 31;
        public static final int YOUME_EVENT_MESSAGE_NOTIFY = 61;
        public static final int YOUME_EVENT_MIC_CTR_OFF = 24;
        public static final int YOUME_EVENT_MIC_CTR_ON = 23;
        public static final int YOUME_EVENT_MY_MIC_LEVEL = 22;
        public static final int YOUME_EVENT_OTHERS_BE_KICKED = 67;
        public static final int YOUME_EVENT_OTHERS_MIC_OFF = 17;
        public static final int YOUME_EVENT_OTHERS_MIC_ON = 16;
        public static final int YOUME_EVENT_OTHERS_SPEAKER_OFF = 19;
        public static final int YOUME_EVENT_OTHERS_SPEAKER_ON = 18;
        public static final int YOUME_EVENT_OTHERS_VOICE_OFF = 21;
        public static final int YOUME_EVENT_OTHERS_VOICE_ON = 20;
        public static final int YOUME_EVENT_OTHER_PLAY_BACKGRAOUND_MUSIC = 226;
        public static final int YOUME_EVENT_OTHER_STOP_BACKGRAOUND_MUSIC = 227;
        public static final int YOUME_EVENT_PAUSED = 6;
        public static final int YOUME_EVENT_RECONNECTED = 11;
        public static final int YOUME_EVENT_RECONNECTING = 10;
        public static final int YOUME_EVENT_REC_PERMISSION_STATUS = 12;
        public static final int YOUME_EVENT_REPORT_AUDIO = 78;
        public static final int YOUME_EVENT_RESUMED = 7;
        public static final int YOUME_EVENT_SEND_MESSAGE_RESULT = 60;
        public static final int YOUME_EVENT_SET_WHITE_USER_LIST_FAILED = 63;
        public static final int YOUME_EVENT_SET_WHITE_USER_LIST_OK = 62;
        public static final int YOUME_EVENT_SPEAKER_CTR_OFF = 26;
        public static final int YOUME_EVENT_SPEAKER_CTR_ON = 25;
        public static final int YOUME_EVENT_SPEAK_FAILED = 9;
        public static final int YOUME_EVENT_SPEAK_RECORD_OFF = 69;
        public static final int YOUME_EVENT_SPEAK_RECORD_ON = 68;
        public static final int YOUME_EVENT_SPEAK_RECORD_RESET = 70;
        public static final int YOUME_EVENT_SPEAK_SUCCESS = 8;
        public static final int YOUME_EVENT_SWITCH_OUTPUT = 75;

        public YouMeEvent() {
        }
    }

    public class YouMeErrorCode {
        public static final int YOUME_ERROR_ALREADY_INIT = -3;
        public static final int YOUME_ERROR_API_NOT_SUPPORTED = -1;
        public static final int YOUME_ERROR_BE_KICK = -13;
        public static final int YOUME_ERROR_CHANNEL_EXIST = -5;
        public static final int YOUME_ERROR_CHANNEL_NOT_EXIST = -6;
        public static final int YOUME_ERROR_GRABMIC_FULL = -301;
        public static final int YOUME_ERROR_GRABMIC_HASEND = -302;
        public static final int YOUME_ERROR_ILLEGAL_SDK = -103;
        public static final int YOUME_ERROR_INVALID_PARAM = -2;
        public static final int YOUME_ERROR_INVITEMIC_NOUSER = -401;
        public static final int YOUME_ERROR_INVITEMIC_OFFLINE = -402;
        public static final int YOUME_ERROR_INVITEMIC_REJECT = -403;
        public static final int YOUME_ERROR_INVITEMIC_TIMEOUT = -404;
        public static final int YOUME_ERROR_MEMORY_OUT = -100;
        public static final int YOUME_ERROR_NETWORK_ERROR = -105;
        public static final int YOUME_ERROR_NOT_ALLOWED_MOBILE_NETWROK = -8;
        public static final int YOUME_ERROR_NOT_INIT = -4;
        public static final int YOUME_ERROR_NOT_IN_CHANNEL = -12;
        public static final int YOUME_ERROR_QUERY_RESTAPI_FAIL = -107;
        public static final int YOUME_ERROR_REC_INIT_FAILED = -201;
        public static final int YOUME_ERROR_REC_NO_DATA = -203;
        public static final int YOUME_ERROR_REC_NO_PERMISSION = -202;
        public static final int YOUME_ERROR_REC_OTHERS = -204;
        public static final int YOUME_ERROR_REC_PERMISSION_UNDEFINED = -205;
        public static final int YOUME_ERROR_SEND_MESSAGE_FAIL = -109;
        public static final int YOUME_ERROR_SERVER_INTER_ERROR = -106;
        public static final int YOUME_ERROR_SERVER_INVALID = -104;
        public static final int YOUME_ERROR_START_FAILED = -101;
        public static final int YOUME_ERROR_STOP_FAILED = -102;
        public static final int YOUME_ERROR_TOKEN_ERROR = -11;
        public static final int YOUME_ERROR_TOO_MANY_CHANNELS = -10;
        public static final int YOUME_ERROR_UNKNOWN = -1000;
        public static final int YOUME_ERROR_USER_ABORT = -108;
        public static final int YOUME_ERROR_WHITE_SOMEUSER_ABNORMAL = -501;
        public static final int YOUME_ERROR_WRONG_CHANNEL_MODE = -9;
        public static final int YOUME_ERROR_WRONG_STATE = -7;
        public static final int YOUME_SUCCESS = 0;

        public YouMeErrorCode() {
        }
    }

    public class YouMeKickReason {
        public static final int YOUME_KICK_ADMIN = 1;
        public static final int YOUME_KICK_RELOGIN = 2;

        public YouMeKickReason() {
        }
    }

    public class YouMeBroadcast {
        public static final int YOUME_BROADCAST_GRABMIC_BROADCAST_FREEMIC = 2;
        public static final int YOUME_BROADCAST_GRABMIC_BROADCAST_GETMIC = 1;
        public static final int YOUME_BROADCAST_INVITEMIC_BROADCAST_CONNECT = 3;
        public static final int YOUME_BROADCAST_INVITEMIC_BROADCAST_DISCONNECT = 4;
        public static final int YOUME_BROADCAST_NONE = 0;

        public YouMeBroadcast() {
        }
    }

    public class YOUME_RTC_SERVER_REGION {
        public static final int RTC_AU_SERVER = 5;
        public static final int RTC_BR_SERVER = 7;
        public static final int RTC_CA_SERVER = 13;
        public static final int RTC_CN_SERVER = 0;
        public static final int RTC_DEFAULT_SERVER = 10001;
        public static final int RTC_DE_SERVER = 6;
        public static final int RTC_DXB_SERVER = 16;
        public static final int RTC_EXT_SERVER = 10000;
        public static final int RTC_FRA_SERVER = 15;
        public static final int RTC_HK_SERVER = 1;
        public static final int RTC_IE_SERVER = 10;
        public static final int RTC_IN_SERVER = 8;
        public static final int RTC_JP_SERVER = 9;
        public static final int RTC_KR_SERVER = 4;
        public static final int RTC_LON_SERVER = 14;
        public static final int RTC_SG_SERVER = 3;
        public static final int RTC_USM_SERVER = 12;
        public static final int RTC_USW_SERVER = 11;
        public static final int RTC_US_SERVER = 2;

        public YOUME_RTC_SERVER_REGION() {
        }
    }

    public class YouMePcmCallBackFlag {
        public static final int PcmCallbackFlag_Mix = 4;
        public static final int PcmCallbackFlag_Record = 2;
        public static final int PcmCallbackFlag_Remote = 1;

        public YouMePcmCallBackFlag() {
        }
    }
}
