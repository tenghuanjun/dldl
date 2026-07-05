package com.aliyun.aliyunface.config;

import com.alibaba.fastjson.asm.Opcodes;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class Coll {
    private int retry = 3;
    private float minangle = -0.6f;
    private float maxangle = 0.6f;
    private float near = 0.5f;
    private float far = 0.28f;
    private int minlight = 50;
    private int time = 20;
    private int light = Opcodes.GETSTATIC;
    private int imageIndex = 1;
    private int mineDscore = 1;
    private int mineVideo = 5;
    private String topText = "";
    private String bottomText = "";
    private String topText_noface = "";
    private String topText_light = "";
    private String topText_rectwidth = "";
    private String topText_integrity = "";
    private String topText_angle = "";
    private String topText_blur = "";
    private String topText_quality = "";
    private String topText_blink = "";
    private String topText_stay = "";
    private String topText_max_rectwidth = "";
    private int uploadMonitorPic = 0;
    private boolean uploadLivePic = false;
    private boolean progressbar = false;
    private boolean uploadBestPic = true;
    private boolean uploadPoseOkPic = false;
    private boolean uploadBigPic = true;
    private boolean uploadDepthData = false;
    private boolean authorization = false;
    private String[] actionMode = {"7"};
    private boolean asyncUp = true;
    private boolean encUp = true;

    public void setRetry(int i) {
        this.retry = i;
    }

    public int getRetry() {
        return this.retry;
    }

    public void setMinangle(float f) {
        this.minangle = f;
    }

    public float getMinangle() {
        return this.minangle;
    }

    public void setMaxangle(float f) {
        this.maxangle = f;
    }

    public float getMaxangle() {
        return this.maxangle;
    }

    public void setNear(float f) {
        this.near = f;
    }

    public float getNear() {
        return this.near;
    }

    public void setFar(float f) {
        this.far = f;
    }

    public float getFar() {
        return this.far;
    }

    public void setMinlight(int i) {
        this.minlight = i;
    }

    public int getMinlight() {
        return this.minlight;
    }

    public void setTime(int i) {
        this.time = i;
    }

    public int getTime() {
        return this.time;
    }

    public void setLight(int i) {
        this.light = i;
    }

    public int getLight() {
        return this.light;
    }

    public void setImageIndex(int i) {
        this.imageIndex = i;
    }

    public int getImageIndex() {
        return this.imageIndex;
    }

    public void setMineDscore(int i) {
        this.mineDscore = i;
    }

    public int getMineDscore() {
        return this.mineDscore;
    }

    public void setMineVideo(int i) {
        this.mineVideo = i;
    }

    public int getMineVideo() {
        return this.mineVideo;
    }

    public void setTopText(String str) {
        this.topText = str;
    }

    public String getTopText() {
        return this.topText;
    }

    public void setBottomText(String str) {
        this.bottomText = str;
    }

    public String getBottomText() {
        return this.bottomText;
    }

    public void setUploadMonitorPic(int i) {
        this.uploadMonitorPic = i;
    }

    public int getUploadMonitorPic() {
        return this.uploadMonitorPic;
    }

    public void setUploadLivePic(boolean z) {
        this.uploadLivePic = z;
    }

    public boolean isUploadLivePic() {
        return this.uploadLivePic;
    }

    public void setProgressbar(boolean z) {
        this.progressbar = z;
    }

    public boolean isProgressbar() {
        return this.progressbar;
    }

    public void setUploadBestPic(boolean z) {
        this.uploadBestPic = z;
    }

    public boolean isUploadBestPic() {
        return this.uploadBestPic;
    }

    public void setUploadPoseOkPic(boolean z) {
        this.uploadPoseOkPic = z;
    }

    public boolean isUploadPoseOkPic() {
        return this.uploadPoseOkPic;
    }

    public void setUploadBigPic(boolean z) {
        this.uploadBigPic = z;
    }

    public boolean isUploadBigPic() {
        return this.uploadBigPic;
    }

    public boolean isUploadDepthData() {
        return this.uploadDepthData;
    }

    public void setUploadDepthData(boolean z) {
        this.uploadDepthData = z;
    }

    public void setActionMode(String[] strArr) {
        this.actionMode = strArr;
    }

    public String[] getActionMode() {
        return this.actionMode;
    }

    public String getTopText_noface() {
        return this.topText_noface;
    }

    public void setTopText_noface(String str) {
        this.topText_noface = str;
    }

    public String getTopText_light() {
        return this.topText_light;
    }

    public void setTopText_light(String str) {
        this.topText_light = str;
    }

    public String getTopText_rectwidth() {
        return this.topText_rectwidth;
    }

    public void setTopText_rectwidth(String str) {
        this.topText_rectwidth = str;
    }

    public String getTopText_integrity() {
        return this.topText_integrity;
    }

    public void setTopText_integrity(String str) {
        this.topText_integrity = str;
    }

    public String getTopText_angle() {
        return this.topText_angle;
    }

    public void setTopText_angle(String str) {
        this.topText_angle = str;
    }

    public String getTopText_blur() {
        return this.topText_blur;
    }

    public void setTopText_blur(String str) {
        this.topText_blur = str;
    }

    public String getTopText_quality() {
        return this.topText_quality;
    }

    public void setTopText_quality(String str) {
        this.topText_quality = str;
    }

    public String getTopText_blink() {
        return this.topText_blink;
    }

    public void setTopText_blink(String str) {
        this.topText_blink = str;
    }

    public String getTopText_stay() {
        return this.topText_stay;
    }

    public void setTopText_stay(String str) {
        this.topText_stay = str;
    }

    public String getTopText_max_rectwidth() {
        return this.topText_max_rectwidth;
    }

    public void setTopText_max_rectwidth(String str) {
        this.topText_max_rectwidth = str;
    }

    public String toString() {
        return "Coll{retry=" + this.retry + ", minangle=" + this.minangle + ", maxangle=" + this.maxangle + ", near=" + this.near + ", far=" + this.far + ", minlight=" + this.minlight + ", time=" + this.time + ", light=" + this.light + ", imageIndex=" + this.imageIndex + ", mineDscore=" + this.mineDscore + ", mineVideo=" + this.mineVideo + ", topText='" + this.topText + "', bottomText='" + this.bottomText + "', topText_noface='" + this.topText_noface + "', topText_light='" + this.topText_light + "', topText_rectwidth='" + this.topText_rectwidth + "', topText_integrity='" + this.topText_integrity + "', topText_angle='" + this.topText_angle + "', topText_blur='" + this.topText_blur + "', topText_quality='" + this.topText_quality + "', topText_blink='" + this.topText_blink + "', topText_stay='" + this.topText_stay + "', topText_max_rectwidth='" + this.topText_max_rectwidth + "', uploadMonitorPic=" + this.uploadMonitorPic + ", uploadLivePic=" + this.uploadLivePic + ", progressbar=" + this.progressbar + ", uploadBestPic=" + this.uploadBestPic + ", uploadPoseOkPic=" + this.uploadPoseOkPic + ", uploadBigPic=" + this.uploadBigPic + ", uploadDepthData=" + this.uploadDepthData + ", actionMode=" + Arrays.toString(this.actionMode) + ", asyncUp=" + this.asyncUp + ", encUp=" + this.encUp + '}';
    }

    public boolean isAuthorization() {
        return this.authorization;
    }

    public void setAuthorization(boolean z) {
        this.authorization = z;
    }

    public boolean isAsyncUp() {
        return this.asyncUp;
    }

    public void setAsyncUp(boolean z) {
        this.asyncUp = z;
    }

    public boolean isEncUp() {
        return this.encUp;
    }

    public void setEncUp(boolean z) {
        this.encUp = z;
    }
}
