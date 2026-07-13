package com.volcengine.cloudphone.base;

import android.view.ViewGroup;
import com.tencent.connect.common.Constants;
import com.volcengine.androidcloud.common.pod.Rotation;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class VeDisplay {
    private final Builder mBuilder;

    public static final class Builder {
        private String app_id;
        private int auto_recycle_time;
        private int client_height;
        private int client_width;
        private ViewGroup container;
        private String package_name;
        private Rotation rotation;
        private int video_stream_profile_id = -1;
        private boolean mainScreen = true;

        public Builder appId(String str) {
            this.app_id = str;
            return this;
        }

        public Builder autoRecycleTime(int i) {
            this.auto_recycle_time = i;
            return this;
        }

        public VeDisplay build() {
            Objects.requireNonNull(this.container, "container must not be null.");
            return new VeDisplay(this);
        }

        public Builder clientHeight(int i) {
            this.client_height = i;
            return this;
        }

        public Builder clientWidth(int i) {
            this.client_width = i;
            return this;
        }

        public Builder container(ViewGroup viewGroup) {
            this.container = viewGroup;
            return this;
        }

        public Builder mainScreen(boolean z) {
            this.mainScreen = z;
            return this;
        }

        public Builder packageName(String str) {
            this.package_name = str;
            return this;
        }

        public Builder rotation(Rotation rotation) {
            this.rotation = rotation;
            return this;
        }

        public Builder videoStreamProfileId(int i) {
            this.video_stream_profile_id = i;
            return this;
        }
    }

    private VeDisplay(Builder builder) {
        this.mBuilder = builder;
    }

    public String getAppId() {
        return this.mBuilder.app_id;
    }

    public int getClientHeight() {
        return this.mBuilder.client_height;
    }

    public int getClientWidth() {
        return this.mBuilder.client_width;
    }

    public ViewGroup getContainer() {
        return this.mBuilder.container;
    }

    public String getPackageName() {
        return this.mBuilder.package_name;
    }

    public Rotation getRotation() {
        return this.mBuilder.rotation;
    }

    public int getVideoStreamProfileId() {
        return this.mBuilder.video_stream_profile_id;
    }

    public boolean isMainScreen() {
        return this.mBuilder.mainScreen;
    }

    public void setMainScreen(boolean z) {
        this.mBuilder.mainScreen = z;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        if (this.mBuilder.client_width == 0 || this.mBuilder.client_height == 0) {
            Builder builder = this.mBuilder;
            builder.client_width = builder.container.getWidth();
            Builder builder2 = this.mBuilder;
            builder2.client_height = builder2.container.getHeight();
        }
        try {
            if (this.mBuilder.client_width > 0) {
                jSONObject.put("client_width", this.mBuilder.client_width);
            }
            if (this.mBuilder.client_height > 0) {
                jSONObject.put("client_height", this.mBuilder.client_height);
            }
            if (this.mBuilder.rotation != null) {
                jSONObject.put("rotation", this.mBuilder.rotation.toRotation());
            }
            jSONObject.put("video_stream_profile_id", this.mBuilder.video_stream_profile_id);
            jSONObject.put(Constants.JumpUrlConstants.URL_KEY_APPID, this.mBuilder.app_id);
            jSONObject.put("package_name", this.mBuilder.package_name);
            if (this.mBuilder.auto_recycle_time > 0) {
                jSONObject.put("auto_recycle_time", this.mBuilder.auto_recycle_time);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
