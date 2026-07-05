package com.huya.berry.module.Player;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class SMObject {

    public static class BitRateInfo {
        public int H264BitRate;
        public int H265BitRate;
        public String disPlayName = new String();
        public int height;
        public int width;
    }

    public static class BroadcastType {
        public static final int MEDIA_STOP_STREAM_NOTIFY = 10002;
        public static final int STREAM_CHANGE_NOTIFY = 10000;
    }

    public static final class EGetPushTarget {
        public static final int EUP_Anchor = 1;
        public static final int EUP_Client = 2;
    }

    public static final class EMediaCodecTool {
        public static final int EMediaCodecType_AMD_H264 = 7;
        public static final int EMediaCodecType_AMD_H265 = 8;
        public static final int EMediaCodecType_H264 = 0;
        public static final int EMediaCodecType_INTEL_H264 = 5;
        public static final int EMediaCodecType_INTEL_H265 = 4;
        public static final int EMediaCodecType_NV_H264 = 2;
        public static final int EMediaCodecType_NV_H265 = 1;
        public static final int EMediaCodecType_X265 = 6;
    }

    public static final class EMediaCompatibleFlag {
        public static final int ELiveCompatibleFlag_360 = 1;
        public static final int ELiveCompatibleFlag_3D = 2;
        public static final int ELiveCompatibleFlag_3D_TOPBOTTOM = 4;
        public static final int ELiveCompatibleFlag_BEGINTIMEBACK = 16;
        public static final int ELiveCompatibleFlag_FLAC_SUPPORT = 8;
    }

    public static final class EMediaLiveSourceType {
        public static final int ESourceType_GameLive = 0;
        public static final int ESourceType_HUYAOwnClient = 9;
        public static final int ESourceType_HuyaVideo = 3;
        public static final int ESourceType_MMS = 1;
        public static final int ESourceType_MatchLive = 12;
        public static final int ESourceType_MobileDirector = 7;
        public static final int ESourceType_MobileLive = 6;
        public static final int ESourceType_MobileStarShow = 11;
        public static final int ESourceType_MutilAudio = 10;
        public static final int ESourceType_ShangJing = 2;
        public static final int ESourceType_ShangJingRecord = 4;
        public static final int ESourceType_WSRtmp = 8;
        public static final int ESourceType_YYLive = 5;
    }

    public static final class EMediaPushMethod {
        public static final int ST_AL_RTMP = 3;
        public static final int ST_HUYA_RTMP = 15;
        public static final int ST_HUYA_UDP = 5;
        public static final int ST_JS_RTMP = 8;
        public static final int ST_SW_UDP = 20;
        public static final int ST_TX_RTMP = 6;
        public static final int ST_WS_RTMP = 1;
    }

    public static final class EMediaStreamType {
        public static final int STREAM_TYPE_FLAC_ONLY_IN_FLV = 4;
        public static final int STREAM_TYPE_FLAC_ONLY_IN_P2P = 6;
        public static final int STREAM_TYPE_FLV = 1;
        public static final int STREAM_TYPE_HUYA_PRIVATE = 3;
        public static final int STREAM_TYPE_LIVE_HLS = 7;
        public static final int STREAM_TYPE_P2P = 2;
        public static final int STREAM_TYPE_UNKNOWN = 0;
        public static final int STREAM_TYPE_VOD_HLS = 5;
    }

    public static final class EMediaUpConfirmRes {
        public static final int RES_DIFF_PUSH_METHOD = 1;
        public static final int RES_OK = 0;
    }

    public static final class EStreamVideoCodecType {
        public static final int E_VIDEO_CODEC_H264 = 0;
        public static final int E_VIDEO_CODEC_H265 = 1;
    }

    public static class GetAntiCodeRsp {
        public String antiCode;
        public String streamName;
    }

    public static class GetUpConfirmRsp {
        public int pushMethod;
        public int resCode;
    }

    public static final class GroupStatusChange {
        public int action;
        public int resCode;
        public String msg = new String();
        public Vector<String> groupIDs = new Vector<>();
    }

    public static class MediaEndLive {
        public String roomID = new String();
        public String reason = new String();
    }

    public static class MediaStopStream {
        public int iFlag;
        public int iReason;
        public long lSequnce;
        public String sStreamName = new String();
        public String sReason = new String();
    }

    public static class MsgType {
        public static final int GET_ANTICODE_RSP = 3;
        public static final int GET_LIVE_STREAM_INFO_RSP = 1;
        public static final int GET_UPCONFIRM_RSP = 4;
        public static final int GROUP_CHANGE_STATUS_RSP = 2;
        public static final int MEDIA_GET_PUSH_CONFIG_RSP = 1000;
        public static final int MEDIA_REQ_EXCEPTION = 2000;
        public static final int PLAYBACK_INFO = 3000;
        public static final int STREAMNAMES_LIST = 3001;
    }

    public static class PlayBackInfo {
        public String url = new String();
        public Vector<String> IPlist = new Vector<>();
    }

    public static class PushConfInfoRsp {
        public long sequence;
        public int streamType;
        public String upUrl = new String();
        public String streamName = new String();
        public String additionalParam = new String();
        public String micGroupId = new String();
    }

    public static class ReqException {
        public String errMsg = new String();
        public int errorType;
    }

    public static class LineInfo {
        int priorityRate;
        public Vector<BitRateInfo> bitRateInfoList = new Vector<>();
        public Vector<Integer> streamTypeList = new Vector<>();

        public int getPriorityRate() {
            return this.priorityRate;
        }

        public Vector<BitRateInfo> getBitRateList() {
            return this.bitRateInfoList;
        }

        public Vector<Integer> getStreamTypeList() {
            return this.streamTypeList;
        }
    }

    public static class SingleStreamInfo {
        public Map<Integer, LineInfo> singleInfo = new HashMap();
        public String streamGroupID;
        public long streamID;
        public int uProperty;
        public long uid;

        public long getStreamID() {
            return this.streamID;
        }

        public String getStreamGroupID() {
            return this.streamGroupID;
        }

        public Vector<Integer> getLineIndexs() {
            Vector<Integer> vector = new Vector<>();
            Iterator<Integer> it = this.singleInfo.keySet().iterator();
            while (it.hasNext()) {
                vector.add(it.next());
            }
            return vector;
        }

        public LineInfo getLineInfoByIndex(int i) {
            return this.singleInfo.get(Integer.valueOf(i));
        }
    }

    public static class StreamInfoList {
        public String roomID = new String();
        public String roomGroupID = new String();
        public Map<String, SingleStreamInfo> streamInfoList = new HashMap();

        public String getRoomID() {
            return this.roomID;
        }

        public String getRoomGroupID() {
            return this.roomGroupID;
        }

        public Vector<String> getStreamNames() {
            Vector<String> vector = new Vector<>();
            Iterator<String> it = this.streamInfoList.keySet().iterator();
            while (it.hasNext()) {
                vector.add(it.next());
            }
            return vector;
        }

        public SingleStreamInfo getSingleStreamByName(String str) {
            if (this.streamInfoList.containsKey(str)) {
                return this.streamInfoList.get(str);
            }
            return null;
        }

        public long getStreamIDByName(String str) {
            if (this.streamInfoList.containsKey(str)) {
                return this.streamInfoList.get(str).getStreamID();
            }
            return -1L;
        }

        public String getStreamGroupIDByName(String str) {
            return !this.streamInfoList.containsKey(str) ? "" : this.streamInfoList.get(str).getStreamGroupID();
        }

        public Vector<Integer> getLineIndexsByName(String str) {
            if (this.streamInfoList.containsKey(str)) {
                return this.streamInfoList.get(str).getLineIndexs();
            }
            return null;
        }

        public LineInfo getLineInfo(String str, int i) {
            if (this.streamInfoList.containsKey(str) && this.streamInfoList.get(str).singleInfo.containsKey(Integer.valueOf(i))) {
                return this.streamInfoList.get(str).singleInfo.get(Integer.valueOf(i));
            }
            return null;
        }

        public int getLinePriorityRate(String str, int i) {
            LineInfo lineInfo = getLineInfo(str, i);
            if (lineInfo == null) {
                return 0;
            }
            return lineInfo.getPriorityRate();
        }

        public Vector<BitRateInfo> getLineBitRateList(String str, int i) {
            LineInfo lineInfo = getLineInfo(str, i);
            if (lineInfo == null) {
                return null;
            }
            return lineInfo.getBitRateList();
        }

        public Vector<Integer> getLineStreamTypes(String str, int i) {
            LineInfo lineInfo = getLineInfo(str, i);
            if (lineInfo == null) {
                return null;
            }
            return lineInfo.getStreamTypeList();
        }
    }
}
