package com.alipay.zoloz.toyger;

import android.content.Context;
import android.graphics.PointF;
import com.alipay.zoloz.toyger.ToygerAttr;
import com.alipay.zoloz.toyger.ToygerBiometricInfo;
import com.alipay.zoloz.toyger.ToygerCallback;
import com.alipay.zoloz.toyger.algorithm.IToygerDelegate;
import com.alipay.zoloz.toyger.algorithm.TGDepthFrame;
import com.alipay.zoloz.toyger.algorithm.TGFrame;
import com.alipay.zoloz.toyger.face.ToygerFaceAlgorithmConfig;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import toygerservice.b;
import toygerservice.d;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public abstract class ToygerBaseService<C extends ToygerCallback, State extends d, Attr extends ToygerAttr, Info extends ToygerBiometricInfo, AlgorithmConfig extends b> implements IToygerDelegate<State, Attr, Info> {
    public static final String ASSET_DOC = "toyger.doc.dat";
    public static final String ASSET_FACE = "toyger.face.dat";
    public static final String ASSET_LICENSE = "toyger.license";
    public static final String KEY_ALGORITHM_CONFIG = "algorithmConfig";
    public static final String KEY_ASYNC_BLOB = "AsyncBlob";
    public static final String KEY_CAMERA_CONFIG = "camera_config";
    public static final String KEY_COMPLETE_BLOB = "CompleteBlob";
    public static final String KEY_DOC_FRAME_RECT = "docFrameRect";
    public static final String KEY_ENABLE_ENCUP = "enableEncUp";
    public static final String KEY_IFFA_MSG = "IFFAMsg";
    public static final String KEY_IS_MIRROR = "is_mirror";
    public static final String KEY_LOCAL_MATCHING_COMMAND = "LocalMatchingCommand";
    public static final String KEY_META_SERIALIZER = "meta_serializer";
    public static final String KEY_NINE_SHOT_BLOB = "NineShootBlob";
    public static final String KEY_PUBLIC_KEY = "pubkey";
    public static final String KEY_RES_9_CONTENT = "content";
    public static final String KEY_RES_9_IS_UTF8 = "isUTF8";
    public static final String KEY_RES_9_KEY = "key";
    public static final String KEY_ROTATE_TIMES = "rotateTimes";
    public static final String KEY_TOKEN = "TOKEN";
    public static final String KEY_UPLOAD_CONFIG = "uploadConfig";
    public static final String KEY_USER_ID = "USER_ID";
    public static final String P_TAG_ANDROID = "TOYGER_FLOW_ANDROID";
    public static final String P_TAG_NATIVE = "TOYGER_FLOW_NATIVE";
    public static final String TAG = "TOYGER_FLOW_ANDROID";
    public static final String TAG_NATIVE = "TOYGER_FLOW_NATIVE";
    public C mToygerCallback;

    public static boolean preLoad(Context context) {
        return false;
    }

    public abstract boolean config(Map<String, Object> map);

    @Override // com.alipay.zoloz.toyger.algorithm.IToygerDelegate
    public PointF handleAlignDepthPoint(PointF pointF) {
        return this.mToygerCallback.onAlignDepthPoint(pointF);
    }

    @Override // com.alipay.zoloz.toyger.algorithm.IToygerDelegate
    public abstract void handleCaptureCompleted(int i, List<Info> list, Map<String, Object> map);

    @Override // com.alipay.zoloz.toyger.algorithm.IToygerDelegate
    public void handleEventTriggered(int i, String str) {
        HashMap map;
        if (i == -16 || i == -15) {
            HashMap map2 = new HashMap();
            if (str == null) {
                str = "-1";
            }
            map2.put(ToygerFaceAlgorithmConfig.DRAGONFLY_LIVENESS, str);
            map = map2;
        } else {
            map = null;
        }
        this.mToygerCallback.onEvent(i, map);
    }

    @Override // com.alipay.zoloz.toyger.algorithm.IToygerDelegate
    public abstract void handleInfoReady(TGFrame tGFrame, Attr attr);

    @Override // com.alipay.zoloz.toyger.algorithm.IToygerDelegate
    public abstract void handleLog(int i, HashMap<String, Object> map);

    @Override // com.alipay.zoloz.toyger.algorithm.IToygerDelegate
    public abstract void handleScanCompleted(int i, List<Info> list, Map<String, Object> map);

    @Override // com.alipay.zoloz.toyger.algorithm.IToygerDelegate
    public abstract void handleStateUpdated(State state, Attr attr);

    public abstract boolean init(Context context, C c, String str, String str2, Map<String, Object> map);

    public abstract boolean init(Context context, boolean z, C c);

    public abstract boolean processImage(List<TGFrame> list, TGDepthFrame tGDepthFrame);

    public abstract void release();

    public abstract void reset();
}
