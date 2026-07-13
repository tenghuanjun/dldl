package com.volcengine.cloudphone.base;

import android.view.ViewGroup;
import com.volcengine.androidcloud.common.pod.PodInfo;
import com.volcengine.androidcloud.common.pod.Rotation;
import com.volcengine.cloudcore.common.mode.Role;
import com.volcengine.cloudcore.common.mode.StreamType;
import com.volcengine.cloudphone.apiservice.LocationService;
import com.volcengine.cloudphone.apiservice.outinterface.IStreamListener;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class CloudConfig {
    private final Builder mBuilder;

    public static final class Builder {
        private String ak;
        private int autoRecycleTime;
        private String configurationCode;
        private transient ViewGroup container;
        private String customGameId;
        private JSONObject debugConfig;
        private transient Map<String, VeDisplay> displays;
        private Map<String, String> extraMap;
        private String locationStrategy;
        private int maxPcu;
        private String phoneAppId;
        private String podId;
        private PodInfo podInfo;
        private boolean publishFallback;
        private int queuePriority;
        private LocationService.LocationInfo remoteLocationMock;
        private boolean reset;
        private Role roleType;
        private int roomType;
        private Rotation rotation;
        private String roundId;
        private String sk;
        private transient IStreamListener streamListener;
        private String token;
        private String userId;
        private List<String> userProfilePath;
        private String userTag;
        private String appId = "101";
        private boolean enableVibrator = true;
        private boolean enableAcceleratorMeterSensor = false;
        private boolean enableGyroscopeSensor = false;
        private boolean enableGravitySensor = false;
        private boolean enableOrientationSensor = false;
        private boolean enableMagneticSensor = false;
        private boolean enableLocationService = false;
        private boolean enableLocalKeyboard = false;
        private int sessionMode = 0;
        private String gameId = "";
        private String scenes = "";
        private int videoStreamProfile = -1;
        private String accountId = "";
        private String productId = "";
        private int serviceType = 1;
        private String reservedId = "";
        private boolean enableRemoteKeyBoard = true;
        private int remoteWindowWidth = -1;
        private int remoteWindowHeight = -1;
        private int videoRenderMode = 0;
        private int videoRotationMode = 0;
        private int renderViewType = 0;
        private StreamType streamType = StreamType.BOTH;
        private boolean isSubscribeStream = true;

        public Builder ak(String str) {
            this.ak = str;
            return this;
        }

        public Builder appId(String str) {
            this.phoneAppId = str;
            return this;
        }

        public Builder autoRecycleTime(int i) {
            this.autoRecycleTime = i;
            return this;
        }

        public CloudConfig build() {
            return new CloudConfig(this);
        }

        public Builder configurationCode(String str) {
            this.configurationCode = str;
            return this;
        }

        public Builder container(ViewGroup viewGroup) {
            this.container = viewGroup;
            return this;
        }

        public Builder customGameId(String str) {
            this.customGameId = str;
            return this;
        }

        public Builder debugConfig(JSONObject jSONObject) {
            this.debugConfig = jSONObject;
            return this;
        }

        public Builder displays(Map<String, VeDisplay> map) {
            this.displays = map;
            return this;
        }

        public Builder enableAccSensor(boolean z) {
            this.enableAcceleratorMeterSensor = z;
            return this;
        }

        public Builder enableGravitySensor(boolean z) {
            this.enableGravitySensor = z;
            return this;
        }

        public Builder enableGyroscopeSensor(boolean z) {
            this.enableGyroscopeSensor = z;
            return this;
        }

        public Builder enableLocalKeyboard(boolean z) {
            this.enableLocalKeyboard = z;
            return this;
        }

        public Builder enableLocationService(boolean z) {
            this.enableLocationService = z;
            return this;
        }

        public Builder enableMagneticSensor(boolean z) {
            this.enableMagneticSensor = z;
            return this;
        }

        public Builder enableOrientationSensor(boolean z) {
            this.enableOrientationSensor = z;
            return this;
        }

        public Builder enableRemoteKeyboard(boolean z) {
            this.enableRemoteKeyBoard = z;
            return this;
        }

        public Builder enableVibrator(boolean z) {
            this.enableVibrator = z;
            return this;
        }

        public Builder extra(Map<String, String> map) {
            this.extraMap = map;
            return this;
        }

        public Builder gameId(String str) {
            this.gameId = str;
            return this;
        }

        public Builder locationStrategy(String str) {
            this.locationStrategy = str;
            return this;
        }

        public Builder maxPcu(int i) {
            this.maxPcu = i;
            return this;
        }

        public Builder podId(String str) {
            this.podId = str;
            return this;
        }

        public Builder podInfo(PodInfo podInfo) {
            this.podInfo = podInfo;
            return this;
        }

        public Builder productId(String str) {
            this.productId = str;
            return this;
        }

        public Builder queuePriority(int i) {
            this.queuePriority = i;
            return this;
        }

        public Builder remoteLocationMock(LocationService.LocationInfo locationInfo) {
            this.remoteLocationMock = locationInfo;
            return this;
        }

        public Builder remoteWindowSize(int i, int i2) {
            this.remoteWindowWidth = i;
            this.remoteWindowHeight = i2;
            return this;
        }

        public Builder renderViewType(int i) {
            this.renderViewType = i;
            return this;
        }

        public Builder reservedId(String str) {
            this.reservedId = str;
            return this;
        }

        public Builder reset(boolean z) {
            this.reset = z;
            return this;
        }

        public Builder roleType(Role role) {
            this.roleType = role;
            return this;
        }

        public Builder roomType(int i) {
            this.roomType = i;
            return this;
        }

        public Builder rotation(Rotation rotation) {
            this.rotation = rotation;
            return this;
        }

        public Builder roundId(String str) {
            this.roundId = str;
            return this;
        }

        public Builder scenes(String str) {
            this.scenes = str;
            return this;
        }

        public Builder serviceType(int i) {
            this.serviceType = i;
            return this;
        }

        public void sessionMode(int i) {
            this.sessionMode = i;
        }

        public Builder sk(String str) {
            this.sk = str;
            return this;
        }

        public Builder streamListener(IStreamListener iStreamListener) {
            this.streamListener = iStreamListener;
            return this;
        }

        public Builder streamType(StreamType streamType) {
            this.streamType = streamType;
            return this;
        }

        public Builder subscribeStream(boolean z) {
            this.isSubscribeStream = z;
            return this;
        }

        public Builder token(String str) {
            this.token = str;
            return this;
        }

        public Builder userId(String str) {
            this.userId = str;
            return this;
        }

        public Builder userProfilePath(List<String> list) {
            this.userProfilePath = list;
            return this;
        }

        public Builder userTag(String str) {
            this.userTag = str;
            return this;
        }

        public Builder videoRenderMode(int i) {
            this.videoRenderMode = i;
            return this;
        }

        public Builder videoRotationMode(int i) {
            this.videoRotationMode = i;
            return this;
        }

        public Builder videoStreamProfileId(int i) {
            this.videoStreamProfile = i;
            return this;
        }
    }

    private CloudConfig(Builder builder) {
        this.mBuilder = builder;
    }

    public String getAccountId() {
        return this.mBuilder.accountId;
    }

    public String getAk() {
        return this.mBuilder.ak;
    }

    public String getAppId() {
        return this.mBuilder.appId;
    }

    public int getAutoRecycleTime() {
        return this.mBuilder.autoRecycleTime;
    }

    public Builder getBuilder() {
        return this.mBuilder;
    }

    public String getConfigurationCode() {
        return this.mBuilder.configurationCode;
    }

    public ViewGroup getContainer() {
        return this.mBuilder.container;
    }

    public String getCustomGameId() {
        return this.mBuilder.customGameId;
    }

    public JSONObject getDebugConfig() {
        return this.mBuilder.debugConfig;
    }

    public Map<String, VeDisplay> getDisplays() {
        return this.mBuilder.displays;
    }

    public Map<String, String> getExtraMap() {
        return this.mBuilder.extraMap;
    }

    public String getGameId() {
        return this.mBuilder.gameId;
    }

    public String getLocationStrategy() {
        return this.mBuilder.locationStrategy;
    }

    public int getMaxPcu() {
        return this.mBuilder.maxPcu;
    }

    public String getPhoneAppId() {
        return this.mBuilder.phoneAppId;
    }

    public String getPodId() {
        return this.mBuilder.podId;
    }

    public PodInfo getPodInfo() {
        return this.mBuilder.podInfo;
    }

    public String getProductId() {
        return this.mBuilder.productId;
    }

    public int getQueuePriority() {
        return this.mBuilder.queuePriority;
    }

    public LocationService.LocationInfo getRemoteLocationMock() {
        return this.mBuilder.remoteLocationMock;
    }

    public int getRemoteWindowHeight() {
        return this.mBuilder.remoteWindowHeight;
    }

    public int getRemoteWindowWidth() {
        return this.mBuilder.remoteWindowWidth;
    }

    public int getRenderViewType() {
        return this.mBuilder.renderViewType;
    }

    public String getReservedId() {
        return this.mBuilder.reservedId;
    }

    public Role getRoleType() {
        return this.mBuilder.roleType;
    }

    public int getRoomType() {
        return this.mBuilder.roomType;
    }

    public Rotation getRotation() {
        return this.mBuilder.rotation;
    }

    public String getRoundId() {
        return this.mBuilder.roundId;
    }

    public String getScenes() {
        return this.mBuilder.scenes;
    }

    public int getServiceType() {
        return this.mBuilder.serviceType;
    }

    public int getSessionMode() {
        return this.mBuilder.sessionMode;
    }

    public String getSk() {
        return this.mBuilder.sk;
    }

    public IStreamListener getStreamListener() {
        return this.mBuilder.streamListener;
    }

    public StreamType getStreamType() {
        return this.mBuilder.streamType;
    }

    public String getToken() {
        return this.mBuilder.token;
    }

    public String getUserId() {
        return this.mBuilder.userId;
    }

    public List<String> getUserProfilePath() {
        return this.mBuilder.userProfilePath;
    }

    public String getUserTag() {
        return this.mBuilder.userTag;
    }

    public int getVideoRenderMode() {
        return this.mBuilder.videoRenderMode;
    }

    public int getVideoRotationMode() {
        return this.mBuilder.videoRotationMode;
    }

    public int getVideoStreamProfile() {
        return this.mBuilder.videoStreamProfile;
    }

    public boolean isAddQueue() {
        return this.mBuilder.queuePriority >= 1;
    }

    public boolean isEnableAccelSensor() {
        return this.mBuilder.enableAcceleratorMeterSensor;
    }

    public boolean isEnableGravitySensor() {
        return this.mBuilder.enableGravitySensor;
    }

    public boolean isEnableGyroscopeSensor() {
        return this.mBuilder.enableGyroscopeSensor;
    }

    public boolean isEnableLocalKeyboard() {
        return this.mBuilder.enableLocalKeyboard;
    }

    public boolean isEnableLocationService() {
        return this.mBuilder.enableLocationService;
    }

    public boolean isEnableMagneticSensor() {
        return this.mBuilder.enableMagneticSensor;
    }

    public boolean isEnableOrientationSensor() {
        return this.mBuilder.enableOrientationSensor;
    }

    public boolean isEnableVibrator() {
        return this.mBuilder.enableVibrator;
    }

    public boolean isGame() {
        return this.mBuilder.serviceType == 1;
    }

    public boolean isKeyBoardEnable() {
        return this.mBuilder.enableRemoteKeyBoard;
    }

    public boolean isReset() {
        return this.mBuilder.reset;
    }

    public boolean isSubscribeStream() {
        return this.mBuilder.isSubscribeStream;
    }

    public void release() {
        this.mBuilder.streamListener(null);
        this.mBuilder.container(null);
    }

    public ViewGroup requireContainer() {
        if (this.mBuilder.container != null) {
            return this.mBuilder.container;
        }
        if (this.mBuilder.displays == null) {
            return null;
        }
        for (VeDisplay veDisplay : this.mBuilder.displays.values()) {
            if (veDisplay.isMainScreen()) {
                return veDisplay.getContainer();
            }
        }
        return null;
    }
}
