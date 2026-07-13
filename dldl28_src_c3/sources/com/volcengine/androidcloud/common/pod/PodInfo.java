package com.volcengine.androidcloud.common.pod;

import android.text.TextUtils;
import com.volcengine.cloudcore.common.mode.MediaProvider;
import com.volcengine.cloudphone.base.CloudConfig;
import com.volcengine.cloudphone.base.VeDisplay;
import com.volcengine.j.c;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class PodInfo {
    private static final String DEFAULT_STREAM_ID = "vesdk_internal_stream";
    public static final String GAME_TYPE_ANDROID = "android";
    public static final String GAME_TYPE_PC = "pc";
    private String eip;
    private String expected_rtc_idc;
    private Map<String, String> extraMap;
    private String gameId;
    private String gameType;
    private String lms_ip;
    private int lms_port;
    private transient Map<String, MediaInfo> mAvailableMediaMapStreamId;
    private transient Map<String, MediaInfo> mAvailableMediaMapUid;
    private transient MediaInfo mMainScreen;
    private String media_app_id;
    private List<MediaInfo> media_list;
    private String media_provider;
    private String media_token;
    private String message_channel;
    private String pod_id;
    private String pod_user_id;
    private int port;
    private String reservedId;
    private String room_id;
    private Rotation rotation;
    private String round_id;
    private String rtc_app_id;
    private String rtc_business_id;
    private String skey;
    private int stream_height;
    private int stream_width;
    private String token;
    private int video_stream_profile_id;

    public static final class MediaInfo {
        public String client_user_id;
        public int height;
        public String media_app_id;
        public String media_provider;
        public String pod_user_id;
        public String room_id;
        public int rotation;
        public String stream_id;
        public String token;
        public int video_stream_profile_id;
        public int width;

        public MediaInfo() {
        }

        public MediaInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, int i2, int i3, int i4) {
            this.stream_id = str;
            this.media_app_id = str2;
            this.media_provider = str3;
            this.client_user_id = str4;
            this.pod_user_id = str5;
            this.room_id = str6;
            this.token = str7;
            this.width = i;
            this.height = i2;
            this.rotation = i3;
            this.video_stream_profile_id = i4;
        }

        public String toString() {
            return "MediaInfo{stream_id='" + this.stream_id + "', media_app_id='" + this.media_app_id + "', media_provider='" + this.media_provider + "', client_user_id='" + this.client_user_id + "', pod_user_id='" + this.pod_user_id + "', room_id='" + this.room_id + "', token='" + this.token + "', width=" + this.width + ", height=" + this.height + ", rotation=" + this.rotation + ", video_stream_profile_id=" + this.video_stream_profile_id + '}';
        }
    }

    public PodInfo() {
    }

    public PodInfo(String str, String str2, String str3, String str4, String str5, int i, int i2, Rotation rotation, int i3, String str6, Map<String, String> map, String str7, String str8, String str9, String str10, String str11, String str12, String str13, int i4, String str14, String str15, String str16, List<MediaInfo> list, String str17, String str18, int i5) {
        this.pod_id = str;
        this.rtc_app_id = str2;
        this.pod_user_id = str3;
        this.room_id = str4;
        this.token = str5;
        this.stream_width = i;
        this.stream_height = i2;
        this.rotation = rotation;
        this.video_stream_profile_id = i3;
        this.round_id = str6;
        this.extraMap = map;
        this.gameId = str7;
        this.message_channel = str8;
        this.reservedId = str9;
        this.media_provider = str10;
        this.media_app_id = str11;
        this.media_token = str12;
        this.eip = str13;
        this.port = i4;
        this.gameType = str14;
        this.expected_rtc_idc = str15;
        this.skey = str16;
        this.rtc_business_id = str17;
        this.media_list = list;
        this.lms_ip = str18;
        this.lms_port = i5;
    }

    private MediaInfo extractMediaInfo(String str) {
        return TextUtils.equals(this.media_provider, MediaProvider.LLAMA) ? new MediaInfo(DEFAULT_STREAM_ID, this.media_app_id, this.media_provider, str, this.pod_user_id, this.room_id, this.media_token, this.stream_width, this.stream_height, this.rotation.toRotation(), this.video_stream_profile_id) : new MediaInfo(DEFAULT_STREAM_ID, c.a(this.rtc_app_id, this.media_app_id), this.media_provider, str, this.pod_user_id, this.room_id, this.token, this.stream_width, this.stream_height, this.rotation.toRotation(), this.video_stream_profile_id);
    }

    public MediaInfo fetchMediaInfoByPodUid(String str) {
        Map<String, MediaInfo> map = this.mAvailableMediaMapUid;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public MediaInfo fetchMediaInfoByStreamId(String str) {
        Map<String, MediaInfo> map = this.mAvailableMediaMapStreamId;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public Collection<MediaInfo> getAvailableMediaInfo() {
        Map<String, MediaInfo> map = this.mAvailableMediaMapUid;
        return map == null ? Collections.emptyList() : map.values();
    }

    public String getEip() {
        return this.eip;
    }

    public String getExpected_rtc_idc() {
        return this.expected_rtc_idc;
    }

    public Map<String, String> getExtraMap() {
        return this.extraMap;
    }

    public String getGameId() {
        return this.gameId;
    }

    public String getGameType() {
        return this.gameType;
    }

    public String getLmsIP() {
        return this.lms_ip;
    }

    public int getLmsPort() {
        return this.lms_port;
    }

    public MediaInfo getMainScreen() {
        return this.mMainScreen;
    }

    public String getMedia_app_id() {
        MediaInfo mediaInfo = this.mMainScreen;
        return mediaInfo != null ? mediaInfo.media_app_id : this.media_app_id;
    }

    public List<MediaInfo> getMedia_list() {
        return this.media_list;
    }

    public String getMedia_provider() {
        MediaInfo mediaInfo = this.mMainScreen;
        return mediaInfo != null ? mediaInfo.media_provider : this.media_provider;
    }

    public String getMedia_token() {
        return this.media_token;
    }

    public String getMessage_channel() {
        return this.message_channel;
    }

    public String getPod_id() {
        return this.pod_id;
    }

    public String getPod_user_id() {
        MediaInfo mediaInfo = this.mMainScreen;
        return mediaInfo != null ? mediaInfo.pod_user_id : this.pod_user_id;
    }

    public int getPort() {
        return this.port;
    }

    public String getReservedId() {
        return this.reservedId;
    }

    public String getRoom_id() {
        MediaInfo mediaInfo = this.mMainScreen;
        return mediaInfo != null ? mediaInfo.room_id : this.room_id;
    }

    public Rotation getRotation() {
        MediaInfo mediaInfo = this.mMainScreen;
        return mediaInfo != null ? Rotation.from(mediaInfo.rotation) : this.rotation;
    }

    public String getRound_id() {
        return this.round_id;
    }

    public String getRtc_app_id() {
        MediaInfo mediaInfo = this.mMainScreen;
        return mediaInfo != null ? mediaInfo.media_app_id : this.rtc_app_id;
    }

    public String getRtc_business_id() {
        return this.rtc_business_id;
    }

    public String getSkey() {
        return this.skey;
    }

    public String getStreamId() {
        MediaInfo mediaInfo = this.mMainScreen;
        if (mediaInfo == null) {
            return null;
        }
        return mediaInfo.stream_id;
    }

    public int getStream_height() {
        MediaInfo mediaInfo = this.mMainScreen;
        return mediaInfo != null ? mediaInfo.height : this.stream_height;
    }

    public int getStream_width() {
        MediaInfo mediaInfo = this.mMainScreen;
        return mediaInfo != null ? mediaInfo.width : this.stream_width;
    }

    public String getToken() {
        MediaInfo mediaInfo = this.mMainScreen;
        return mediaInfo != null ? mediaInfo.token : this.token;
    }

    public int getVideo_stream_profile_id() {
        MediaInfo mediaInfo = this.mMainScreen;
        return mediaInfo != null ? mediaInfo.video_stream_profile_id : this.video_stream_profile_id;
    }

    public void initMediaList(CloudConfig cloudConfig) {
        String key;
        HashMap map = new HashMap();
        List<MediaInfo> list = this.media_list;
        if (list == null || list.isEmpty()) {
            MediaInfo mediaInfoExtractMediaInfo = extractMediaInfo(cloudConfig.getUserId());
            map.put(mediaInfoExtractMediaInfo.pod_user_id, mediaInfoExtractMediaInfo);
            this.mMainScreen = mediaInfoExtractMediaInfo;
        } else {
            Map<String, VeDisplay> displays = cloudConfig.getDisplays();
            if (displays == null || displays.isEmpty()) {
                this.mMainScreen = this.media_list.get(0);
                for (MediaInfo mediaInfo : this.media_list) {
                    map.put(mediaInfo.pod_user_id, mediaInfo);
                }
            } else {
                Iterator<Map.Entry<String, VeDisplay>> it = displays.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        key = null;
                        break;
                    }
                    Map.Entry<String, VeDisplay> next = it.next();
                    if (next.getValue().isMainScreen()) {
                        key = next.getKey();
                        break;
                    }
                }
                for (MediaInfo mediaInfo2 : this.media_list) {
                    map.put(mediaInfo2.pod_user_id, mediaInfo2);
                    if (TextUtils.equals(key, mediaInfo2.stream_id)) {
                        this.mMainScreen = mediaInfo2;
                    }
                }
            }
        }
        HashMap map2 = new HashMap();
        for (MediaInfo mediaInfo3 : map.values()) {
            map2.put(mediaInfo3.stream_id, mediaInfo3);
        }
        this.mAvailableMediaMapUid = Collections.unmodifiableMap(map);
        this.mAvailableMediaMapStreamId = Collections.unmodifiableMap(map2);
    }

    public boolean isCloudNative() {
        List<MediaInfo> list = this.media_list;
        return (list == null || list.isEmpty()) ? false : true;
    }

    public boolean isMainScreenReady() {
        return this.mMainScreen != null;
    }

    public void setLmsIP(String str) {
        this.lms_ip = str;
    }

    public void setLmsPort(int i) {
        this.lms_port = i;
    }

    public String toString() {
        return "PodInfo{pod_id='" + this.pod_id + "', rtc_app_id='" + this.rtc_app_id + "', pod_user_id='" + this.pod_user_id + "', room_id='" + this.room_id + "', token='" + this.token + "', stream_width=" + this.stream_width + ", stream_height=" + this.stream_height + ", rotation=" + this.rotation + ", video_stream_profile_id=" + this.video_stream_profile_id + ", round_id='" + this.round_id + "', gameId='" + this.gameId + "', message_channel='" + this.message_channel + "', reservedId='" + this.reservedId + "', skey='" + this.skey + "', media_provider='" + this.media_provider + "', media_app_id='" + this.media_app_id + "', media_token='" + this.media_token + "', eip='" + this.eip + "', port=" + this.port + ", extraMap=" + this.extraMap + ", video_stream_profile_id=" + this.video_stream_profile_id + ", round_id='" + this.round_id + "', gameId='" + this.gameId + "', gameType='" + this.gameType + "', expected_rtc_idc='" + this.expected_rtc_idc + "', extraMap=" + this.extraMap + ", getExpected_rtc_idc=" + this.expected_rtc_idc + ", media_list=" + this.media_list + '}';
    }
}
