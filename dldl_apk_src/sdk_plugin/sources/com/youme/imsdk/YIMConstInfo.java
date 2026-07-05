package com.youme.imsdk;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class YIMConstInfo {

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

    public static final class ChatType {
        public static final int PrivateChat = 1;
        public static final int RoomChat = 2;
        public static final int Unknow = 0;
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
        public static final int AlreadyLogin = 7;
        public static final int AudioDriver = 33;
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
        public static final int IsWaitingDownload = 4001;
        public static final int IsWaitingJoin = 4004;
        public static final int IsWaitingLeave = 4005;
        public static final int IsWaitingSend = 4000;
        public static final int IsWaitingUpload = 4002;
        public static final int LocationTimeout = 46;
        public static final int LoginInvalid = 1001;
        public static final int LoginSessionError = 10;
        public static final int LoginTokenInvalid = 48;
        public static final int MessageBlocked = 45;
        public static final int MessageTooLong = 17;
        public static final int NetError = 9;
        public static final int NoAudioDevice = 32;
        public static final int NoLangCode = 37;
        public static final int NoLocationAuthrize = 29;
        public static final int NotBlock = 44;
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
        public static final int PTT_RecognizeFailed = 2030;
        public static final int PTT_ResolveFileError = 2023;
        public static final int PTT_ShortConnectionMode = 2031;
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
        public static final int ReadWriteFileError = 36;
        public static final int ReceiverEmpty = 26;
        public static final int ReceiverTooLong = 18;
        public static final int ResolveFileError = 35;
        public static final int RoomIDTooLong = 27;
        public static final int SDKInvalid = 6;
        public static final int SendFileError = 13;
        public static final int ServerError = 8;
        public static final int SpeechAccentInvalid = 39;
        public static final int SpeechLanguageInvalid = 40;
        public static final int StatusError = 5;
        public static final int StopPlayFailBeforeStart = 4003;
        public static final int Success = 0;
        public static final int TimeOut = 4;
        public static final int TranslateUnable = 38;
        public static final int UnknowError = 21;
        public static final int UnknowLocation = 30;
        public static final int Unsupport = 31;
        public static final int UnsupportFormat = 25;
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

    public static final class ReconnectDealResult {
        public static final int RECONNECTRESULT_FAIL = 2;
        public static final int RECONNECTRESULT_FAIL_AGAIN = 1;
        public static final int RECONNECTRESULT_SUCCESS = 0;
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
}
