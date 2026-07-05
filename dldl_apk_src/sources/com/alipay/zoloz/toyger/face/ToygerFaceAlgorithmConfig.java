package com.alipay.zoloz.toyger.face;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alipay.android.phone.faceverify.BuildConfig;
import com.alipay.zoloz.toyger.algorithm.ToygerCameraConfig;
import com.alipay.zoloz.toyger.algorithm.ToygerConfig;
import com.alipay.zoloz.toyger.algorithm.ToygerLivenessConfig;
import com.alipay.zoloz.toyger.algorithm.ToygerQualityConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import toygerservice.b;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ToygerFaceAlgorithmConfig extends b {
    public static final String BAT_LIVENESS = "BatLiveness";
    public static final String DARK = "dark";
    public static final String DEPTH = "depth";
    public static final String DRAGONFLY_LIVENESS = "DragonflyLiveness";
    public static final String GEMINI_LIVENESS = "GeminiLiveness";
    public static final String NO_LIVENESS = "NoLiveness";
    public static final String PANO = "pano";
    public static final String ZFACE_BLINK_LIVENESS = "zfaceBlinkLiveness";

    @JSONField(name = "batLivenessThreshold")
    public float batLivenessThreshold;

    @JSONField(name = "blink_openness")
    public float blink_openness = 0.2f;

    @JSONField(name = "depth_cache_num")
    public int depth_cache_num;

    @JSONField(name = "dragonflyMax")
    public float dragonflyMax;

    @JSONField(name = "dragonflyMin")
    public float dragonflyMin;

    @JSONField(name = "eyeOcclusion")
    public float eyeOcclusion;

    @JSONField(name = "eye_openness")
    public float eye_openness;

    @JSONField(name = "geminiMax")
    public float geminiMax;

    @JSONField(name = "geminiMin")
    public float geminiMin;

    @JSONField(name = "isMirror")
    public boolean isMirror;

    @JSONField(name = "liveness_combination")
    public List<String> liveness_combination;

    @JSONField(name = "log_level")
    public int log_level;

    @JSONField(name = "max_iod")
    public float max_iod;

    @JSONField(name = "min_iod")
    public float min_iod;

    @JSONField(name = "pose_gaussian")
    public float pose_gaussian;

    @JSONField(name = "pose_integrity")
    public float pose_integrity;

    @JSONField(name = "pose_ligh")
    public float pose_light;

    @JSONField(name = "pose_motion")
    public float pose_motion;

    @JSONField(name = "pose_pitch")
    public float pose_pitch;

    @JSONField(name = "pose_pitchMin")
    public float pose_pitchMin;

    @JSONField(name = "pose_rectwidth")
    public float pose_rectwidth;

    @JSONField(name = "pose_yaw")
    public float pose_yaw;

    @JSONField(name = "pose_yawMin")
    public float pose_yawMin;

    @JSONField(name = "quality_depth_min_quality")
    public float quality_depth_min_quality;

    @JSONField(name = "quality_min_quality")
    public float quality_min_quality;

    @JSONField(name = "secProtocol")
    public String secProtocol;

    @JSONField(name = "stack_time")
    public float stack_time;

    @JSONField(name = "threshold")
    public Map<String, List<Float>> threshold;

    public ToygerFaceAlgorithmConfig() {
        ArrayList arrayList;
        this.stack_time = 2.0f;
        this.eye_openness = 0.25f;
        this.pose_pitch = 0.2f;
        this.pose_pitchMin = -0.2f;
        this.pose_yaw = 0.2f;
        this.pose_yawMin = -0.2f;
        this.pose_gaussian = 0.15f;
        this.pose_motion = 1.0f;
        this.pose_rectwidth = 0.25f;
        this.pose_integrity = 0.9f;
        this.pose_light = 0.3f;
        this.min_iod = 0.18f;
        this.max_iod = 0.45f;
        Float fValueOf = Float.valueOf(0.0f);
        this.dragonflyMax = 0.0f;
        this.dragonflyMin = 0.0f;
        this.geminiMin = 0.0f;
        this.geminiMax = 0.0f;
        this.quality_min_quality = 20.0f;
        this.eyeOcclusion = 0.8f;
        this.depth_cache_num = 2;
        this.quality_depth_min_quality = 0.5f;
        this.liveness_combination = new ArrayList();
        this.threshold = new HashMap();
        this.secProtocol = "";
        this.isMirror = false;
        if (TextUtils.equals(BuildConfig.PORTING_WALLET, "jrcloud")) {
            this.liveness_combination.add(NO_LIVENESS);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(fValueOf);
            this.stack_time = 2.0f;
            this.eye_openness = 0.2f;
            this.pose_pitch = 0.2f;
            this.pose_yaw = 0.2f;
            this.pose_gaussian = 0.15f;
            this.pose_motion = 1.0f;
            this.pose_pitchMin = -0.2f;
            this.pose_rectwidth = 0.25f;
            this.pose_integrity = 0.9f;
            this.pose_light = 0.3f;
            this.pose_yawMin = -0.2f;
            this.min_iod = 0.18f;
            this.max_iod = 0.45f;
            this.quality_min_quality = 20.0f;
            this.threshold.put(NO_LIVENESS, arrayList2);
            return;
        }
        if (!TextUtils.equals(BuildConfig.PORTING_VENDING, "jrcloud")) {
            if (!TextUtils.equals("jrcloud", "jrcloud") && TextUtils.equals(BuildConfig.PORTING_SUDIYI, "jrcloud")) {
                this.liveness_combination.add(NO_LIVENESS);
                arrayList = new ArrayList();
            } else {
                this.liveness_combination.add(NO_LIVENESS);
                arrayList = new ArrayList();
            }
            arrayList.add(fValueOf);
            this.threshold.put(NO_LIVENESS, arrayList);
            return;
        }
        this.liveness_combination.add(BAT_LIVENESS);
        this.batLivenessThreshold = 0.5f;
        this.stack_time = 1.0f;
        this.pose_gaussian = 1.0f;
        this.pose_rectwidth = 0.3f;
        this.pose_light = 0.0f;
        this.min_iod = 0.0f;
        this.max_iod = 1.0f;
        this.quality_depth_min_quality = 0.5f;
        this.depth_cache_num = 2;
        this.secProtocol = "";
    }

    public static ToygerFaceAlgorithmConfig from(String str) {
        ToygerFaceAlgorithmConfig toygerFaceAlgorithmConfig = (ToygerFaceAlgorithmConfig) JSON.parseObject(str, ToygerFaceAlgorithmConfig.class);
        if (toygerFaceAlgorithmConfig == null) {
            toygerFaceAlgorithmConfig = new ToygerFaceAlgorithmConfig();
        }
        if (toygerFaceAlgorithmConfig.depth_cache_num == 0) {
            toygerFaceAlgorithmConfig.depth_cache_num = 2;
        }
        if (toygerFaceAlgorithmConfig.quality_depth_min_quality == 0.0f) {
            toygerFaceAlgorithmConfig.quality_depth_min_quality = 0.5f;
        }
        return toygerFaceAlgorithmConfig;
    }

    public ToygerConfig toToygerConfig() {
        ToygerQualityConfig toygerQualityConfig = new ToygerQualityConfig(this.pose_light, this.pose_rectwidth, this.pose_integrity, this.pose_pitch, this.pose_yaw, this.pose_gaussian, this.pose_motion, this.quality_min_quality, this.stack_time, this.min_iod, this.max_iod, this.blink_openness, this.eye_openness, this.eyeOcclusion, this.pose_pitchMin, this.pose_yawMin, this.quality_depth_min_quality, this.depth_cache_num);
        String strConcat = "";
        if (this.liveness_combination != null) {
            for (int i = 0; i < this.liveness_combination.size(); i++) {
                strConcat = strConcat.length() > 0 ? strConcat.concat("#").concat(this.liveness_combination.get(i)) : this.liveness_combination.get(i);
            }
        }
        ToygerLivenessConfig toygerLivenessConfig = new ToygerLivenessConfig(strConcat, this.batLivenessThreshold, this.dragonflyMin, this.dragonflyMax, this.geminiMin, this.geminiMax, false);
        ToygerConfig toygerConfig = new ToygerConfig();
        toygerConfig.qualityConfig = toygerQualityConfig;
        toygerConfig.livenessConfig = toygerLivenessConfig;
        ToygerCameraConfig toygerCameraConfig = new ToygerCameraConfig();
        toygerConfig.cameraConfig = toygerCameraConfig;
        toygerCameraConfig.isMirror = this.isMirror;
        return toygerConfig;
    }
}
