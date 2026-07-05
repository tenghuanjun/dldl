package com.huya.berry.gamesdk.resolutions;

import com.huya.berry.gamesdk.SdkProperties;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LivingParams implements Cloneable, Comparable<LivingParams> {
    private int maxVideoBitrate;
    private int minVideoBitrate;
    private String name;
    private int paramsVideoHeight;
    private int paramsVideoWidth;
    private int resolution;
    private String tips;
    private int videoBitrate;
    private int videoFrameRate;
    private int videoHeight;
    private int videoWidth;

    private LivingParams(Builder builder) {
        this.videoWidth = builder.videoWidth;
        this.videoHeight = builder.videoHeight;
        this.videoBitrate = builder.videoBitrate;
        this.maxVideoBitrate = builder.maxVideoBitrate;
        this.minVideoBitrate = builder.minVideoBitrate;
        this.videoFrameRate = builder.videoFrameRate;
        this.resolution = builder.resolution;
        this.name = builder.name;
        this.tips = builder.tips;
        this.paramsVideoWidth = builder.paramsVideoWidth;
        this.paramsVideoHeight = builder.paramsVideoHeight;
    }

    public int getParamsVideoWidth() {
        if (SdkProperties.isLandscape.get().booleanValue()) {
            return Math.max(this.paramsVideoWidth, this.paramsVideoHeight);
        }
        return Math.min(this.paramsVideoWidth, this.paramsVideoHeight);
    }

    public void setParamsVideoWidth(int i) {
        this.paramsVideoWidth = i;
    }

    public int getParamsVideoHeight() {
        if (SdkProperties.isLandscape.get().booleanValue()) {
            return Math.min(this.paramsVideoWidth, this.paramsVideoHeight);
        }
        return Math.max(this.paramsVideoWidth, this.paramsVideoHeight);
    }

    public void setParamsVideoHeight(int i) {
        this.paramsVideoHeight = i;
    }

    public int getVideoWidth() {
        return this.videoWidth;
    }

    public void setVideoWidth(int i) {
        this.videoWidth = i;
    }

    public int getVideoHeight() {
        return this.videoHeight;
    }

    public int getVideoBitrate() {
        return this.videoBitrate;
    }

    public void setVideoHeight(int i) {
        this.videoHeight = i;
    }

    public void setVideoBitrate(int i) {
        this.videoBitrate = i;
    }

    public int getMaxVideoBitrate() {
        return this.maxVideoBitrate;
    }

    public void setMaxVideoBitrate(int i) {
        this.maxVideoBitrate = i;
    }

    public int getMinVideoBitrate() {
        return this.minVideoBitrate;
    }

    public void setMinVideoBitrate(int i) {
        this.minVideoBitrate = i;
    }

    public void setVideoFrameRate(int i) {
        this.videoFrameRate = i;
    }

    public int getVideoFrameRate() {
        return this.videoFrameRate;
    }

    public int getResolution() {
        return this.resolution;
    }

    public void setResolution(int i) {
        this.resolution = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getTips() {
        return this.tips;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public LivingParams m17clone() {
        try {
            return (LivingParams) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toString() {
        return String.format(Locale.CHINA, "name %s,resolution %d,w-h:%d-%d,bitrate:%d,fps:%d", this.name, Integer.valueOf(this.resolution), Integer.valueOf(this.videoWidth), Integer.valueOf(this.videoHeight), Integer.valueOf(this.videoBitrate), Integer.valueOf(this.videoFrameRate));
    }

    public static class Builder {
        private int maxVideoBitrate;
        private int minVideoBitrate;
        private String name;
        private int paramsVideoHeight;
        private int paramsVideoWidth;
        private int resolution;
        private String tips;
        private int videoBitrate;
        private int videoFrameRate;
        private int videoHeight;
        private int videoWidth;

        public Builder paramsVideoWidth(int i) {
            this.paramsVideoWidth = i;
            return this;
        }

        public Builder paramsVideoHeight(int i) {
            this.paramsVideoHeight = i;
            return this;
        }

        public Builder videoWidth(int i) {
            this.videoWidth = i;
            return this;
        }

        public Builder videoHeight(int i) {
            this.videoHeight = i;
            return this;
        }

        public Builder videoBitrate(int i) {
            this.videoBitrate = i;
            return this;
        }

        public Builder maxVideoBitrate(int i) {
            this.maxVideoBitrate = i;
            return this;
        }

        public Builder minVideoBitrate(int i) {
            this.minVideoBitrate = i;
            return this;
        }

        public Builder videoFrameRate(int i) {
            this.videoFrameRate = i;
            return this;
        }

        public Builder resolution(int i) {
            this.resolution = i;
            return this;
        }

        public Builder name(String str) {
            this.name = str;
            return this;
        }

        public Builder tips(String str) {
            this.tips = str;
            return this;
        }

        public LivingParams build() {
            return new LivingParams(this);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(LivingParams livingParams) {
        return this.resolution - livingParams.resolution;
    }
}
