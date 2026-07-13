package com.volcengine.cloudgame;

import android.text.TextUtils;
import android.view.ViewGroup;
import com.volcengine.androidcloud.common.pod.PodInfo;
import com.volcengine.cloudcore.common.mode.Role;
import com.volcengine.cloudcore.common.mode.StreamType;
import com.volcengine.cloudphone.apiservice.outinterface.IStreamListener;
import com.volcengine.cloudphone.base.CloudConfig;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class GamePlayConfig {
    private final Builder mBuilder;
    CloudConfig mCloudConfig;

    GamePlayConfig(Builder builder) {
        this.mBuilder = builder;
    }

    CloudConfig getCloudConfig() {
        if (this.mCloudConfig == null) {
            CloudConfig.Builder builder = new CloudConfig.Builder();
            builder.userId(this.mBuilder.userId);
            builder.enableVibrator(this.mBuilder.enableVibrator);
            builder.enableAccSensor(this.mBuilder.enableAcceleratorMeterSensor);
            builder.enableGravitySensor(this.mBuilder.enableGravitySensor);
            builder.enableGyroscopeSensor(this.mBuilder.enableGyroscopeSensor);
            builder.enableOrientationSensor(this.mBuilder.enableOrientationSensor);
            builder.enableMagneticSensor(this.mBuilder.enableMagneticSensor);
            builder.enableLocationService(this.mBuilder.enableLocationService);
            builder.container(this.mBuilder.container);
            builder.enableLocalKeyboard(this.mBuilder.enableLocalKeyboard);
            builder.podInfo(this.mBuilder.podInfo);
            builder.streamListener(this.mBuilder.mStreamListener);
            builder.ak(this.mBuilder.ak);
            builder.sk(this.mBuilder.sk);
            builder.token(this.mBuilder.token);
            builder.roundId(this.mBuilder.roundId);
            builder.videoStreamProfileId(this.mBuilder.videoStreamProfile);
            builder.streamType(this.mBuilder.streamType);
            builder.serviceType(1);
            builder.debugConfig(this.mBuilder.debugConfig);
            builder.gameId(this.mBuilder.gameId);
            builder.reservedId(this.mBuilder.reservedId);
            builder.customGameId(this.mBuilder.customGameId);
            builder.configurationCode(this.mBuilder.configurationCode);
            builder.userTag(this.mBuilder.userTag);
            builder.extra(this.mBuilder.extra);
            builder.autoRecycleTime(this.mBuilder.autoRecycleTime);
            builder.userProfilePath(this.mBuilder.userProfilePath);
            builder.maxPcu(this.mBuilder.maxPcu);
            builder.roleType(this.mBuilder.roleType);
            builder.roomType(this.mBuilder.roomType);
            builder.enableRemoteKeyboard(this.mBuilder.enableKeyBoard);
            builder.sessionMode(this.mBuilder.mSessionMode);
            builder.queuePriority(this.mBuilder.queuePriority);
            builder.subscribeStream(this.mBuilder.isSubscribeStream);
            builder.videoRenderMode(this.mBuilder.videoRenderMode);
            builder.remoteWindowSize(this.mBuilder.remoteWindowWidth, this.mBuilder.remoteWindowHeight);
            this.mCloudConfig = builder.build();
        }
        return this.mCloudConfig;
    }

    public static class Builder {
        private String ak;
        private ViewGroup container;
        private String customGameId;
        private JSONObject debugConfig;
        private IStreamListener mStreamListener;
        private PodInfo podInfo;
        private String reservedId;
        private String sk;
        private String token;
        private String userId;
        private List<String> userProfilePath;
        private boolean enableVibrator = true;
        private boolean enableAcceleratorMeterSensor = false;
        private boolean enableGyroscopeSensor = false;
        private boolean enableGravitySensor = false;
        private boolean enableOrientationSensor = false;
        private boolean enableMagneticSensor = false;
        private boolean enableLocationService = false;
        private boolean enableLocalKeyboard = false;
        private String gameId = "";
        private int videoStreamProfile = -1;
        private StreamType streamType = StreamType.BOTH;
        private String roundId = "";
        private String configurationCode = "";
        private String userTag = "";
        private Map<String, String> extra = new HashMap();
        private int autoRecycleTime = 0;
        private Role roleType = Role.VIEWER;
        private int roomType = 0;
        private int maxPcu = 1;
        private boolean enableKeyBoard = true;
        private int mSessionMode = 0;
        private int queuePriority = 0;
        private boolean isSubscribeStream = true;
        private int videoRenderMode = 0;
        private int remoteWindowWidth = -1;
        private int remoteWindowHeight = -1;

        public Builder userId(String str) {
            this.userId = str;
            return this;
        }

        public Builder videoRenderMode(int i) {
            if (i < 0 || i > 2) {
                throw new InvalidParameterException("renderMode " + i + " is invalid");
            }
            this.videoRenderMode = i;
            return this;
        }

        public Builder sessionMode(int i) {
            this.mSessionMode = i;
            return this;
        }

        public Builder streamListener(IStreamListener iStreamListener) {
            this.mStreamListener = iStreamListener;
            return this;
        }

        public Builder enableLocalKeyboard(boolean z) {
            this.enableLocalKeyboard = z;
            return this;
        }

        public Builder enableVibrator(boolean z) {
            this.enableVibrator = z;
            return this;
        }

        public Builder enableAcceleratorSensor(boolean z) {
            this.enableAcceleratorMeterSensor = z;
            return this;
        }

        public Builder enableGyroscopeSensor(boolean z) {
            this.enableGyroscopeSensor = z;
            return this;
        }

        public Builder enableGravitySensor(boolean z) {
            this.enableGravitySensor = z;
            return this;
        }

        public Builder enableOrientationSensor(boolean z) {
            this.enableOrientationSensor = z;
            return this;
        }

        public Builder enableMagneticSensor(boolean z) {
            this.enableMagneticSensor = z;
            return this;
        }

        public Builder enableLocationService(boolean z) {
            this.enableLocationService = z;
            return this;
        }

        public Builder container(ViewGroup viewGroup) {
            this.container = viewGroup;
            return this;
        }

        public Builder podInfo(PodInfo podInfo) {
            this.podInfo = podInfo;
            return this;
        }

        public Builder gameId(String str) {
            this.gameId = str;
            return this;
        }

        public Builder ak(String str) {
            this.ak = str;
            return this;
        }

        public Builder sk(String str) {
            this.sk = str;
            return this;
        }

        public Builder token(String str) {
            this.token = str;
            return this;
        }

        public Builder roundId(String str) {
            this.roundId = str;
            return this;
        }

        public Builder planId(String str) {
            this.configurationCode = str;
            return this;
        }

        public Builder userTag(String str) {
            this.userTag = str;
            return this;
        }

        public Builder extra(Map<String, String> map) {
            this.extra = map;
            return this;
        }

        public Builder debugConfig(String str) {
            try {
                this.debugConfig = new JSONObject(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }

        public Builder remoteWindowSize(int i, int i2) {
            if (i < 0 || i2 < 0) {
                throw new InvalidParameterException("width " + i + " and height " + i2 + " should not be less than 0");
            }
            this.remoteWindowWidth = i;
            this.remoteWindowHeight = i2;
            return this;
        }

        public Builder videoStreamProfileId(int i) {
            this.videoStreamProfile = i;
            return this;
        }

        public Builder streamType(StreamType streamType) {
            Objects.requireNonNull(streamType, "streamType is not allowed to be null");
            this.streamType = streamType;
            return this;
        }

        public Builder reservedId(String str) {
            this.reservedId = str;
            return this;
        }

        public Builder customGameId(String str) {
            this.customGameId = str;
            return this;
        }

        public Builder autoRecycleTime(int i) {
            this.autoRecycleTime = i;
            return this;
        }

        public Builder userProfilePath(List<String> list) {
            this.userProfilePath = list;
            return this;
        }

        public Builder roomType(int i) {
            this.roomType = i;
            return this;
        }

        public Builder role(Role role) {
            this.roleType = role;
            return this;
        }

        public Builder keyBoardEnable(boolean z) {
            this.enableKeyBoard = z;
            return this;
        }

        public Builder queuePriority(int i) {
            this.queuePriority = i;
            return this;
        }

        public Builder subscribeStream(boolean z) {
            this.isSubscribeStream = z;
            return this;
        }

        public GamePlayConfig build() {
            if (this.isSubscribeStream) {
                Objects.requireNonNull(this.container, "container required.");
            }
            Objects.requireNonNull(this.ak, "ak required.");
            Objects.requireNonNull(this.sk, "sk required.");
            Objects.requireNonNull(this.token, "token required.");
            if (TextUtils.isEmpty(this.gameId) && TextUtils.isEmpty(this.customGameId)) {
                throw new IllegalArgumentException("gameId or customGameId is required");
            }
            if (TextUtils.isEmpty(this.userId)) {
                throw new InvalidParameterException("userId required.");
            }
            if (TextUtils.isEmpty(this.roundId)) {
                throw new InvalidParameterException("roundId required.");
            }
            if (this.debugConfig == null) {
                this.debugConfig = new JSONObject();
            }
            int i = this.queuePriority;
            if (i < 0 || i > 100) {
                throw new IllegalArgumentException("The queue priority must fall within the range of 0 to 99.");
            }
            return new GamePlayConfig(this);
        }
    }
}
