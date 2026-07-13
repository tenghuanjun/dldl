package com.shuyu.gsyvideoplayer.model;

import java.io.File;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class GSYModel {
    boolean isCache;
    boolean looping;
    File mCachePath;
    Map<String, String> mapHeadData;
    String overrideExtension;
    float speed;
    String url;

    public GSYModel(String str, Map<String, String> map, boolean z, float f, boolean z2, File file, String str2) {
        this.url = str;
        this.mapHeadData = map;
        this.looping = z;
        this.speed = f;
        this.isCache = z2;
        this.mCachePath = file;
        this.overrideExtension = str2;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public Map<String, String> getMapHeadData() {
        return this.mapHeadData;
    }

    public void setMapHeadData(Map<String, String> map) {
        this.mapHeadData = map;
    }

    public boolean isLooping() {
        return this.looping;
    }

    public void setLooping(boolean z) {
        this.looping = z;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float f) {
        this.speed = f;
    }

    public boolean isCache() {
        return this.isCache;
    }

    public void setCache(boolean z) {
        this.isCache = z;
    }

    public File getCachePath() {
        return this.mCachePath;
    }

    public void setCachePath(File file) {
        this.mCachePath = file;
    }

    public String getOverrideExtension() {
        return this.overrideExtension;
    }

    public void setOverrideExtension(String str) {
        this.overrideExtension = str;
    }
}
