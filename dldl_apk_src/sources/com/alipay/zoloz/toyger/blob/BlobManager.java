package com.alipay.zoloz.toyger.blob;

import com.alipay.zoloz.image.ToygerImageUtil;
import com.alipay.zoloz.toyger.ToygerBiometricInfo;
import com.alipay.zoloz.toyger.algorithm.TGFrame;
import com.alipay.zoloz.toyger.algorithm.ToygerBlobConfig;
import com.alipay.zoloz.toyger.face.ToygerDepthInfo;
import com.alipay.zoloz.toyger.face.ToygerFaceAttr;
import com.alipay.zoloz.toyger.face.ToygerFaceCallback;
import java.util.List;
import java.util.Map;
import java2jni_do_not_delete_this.java2jni_do_not_delete_this_library_zkfv_1ts_1tj;
import toygerservice.j;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public abstract class BlobManager<Info extends ToygerBiometricInfo> {
    public static final String BLOB_ELEM_IMAGE_HASHCODE = "hash";
    public static final String BLOB_ELEM_IMAGE_RUNTIMEINFO = "runtimeInfo";
    public static final String BLOB_ELEM_IMAGE_TYPE = "ImageType";
    public static final String BLOB_ELEM_SUBTYPE_GYRO = "gyro";
    public static final String BLOB_ELEM_TYPE_DOC = "doc";
    public static final String BLOB_ELEM_TYPE_FACE = "face";
    public static final String BLOB_ELEM_TYPE_SENSOR = "sensor";
    public static final String BLOB_VERSION = "1.0";
    public static final int META_ALGRESULT_BAT = 3;
    public static final int META_ALGRESULT_DRAGONFLY = 2;
    public static final int META_ALGRESULT_VERIFY = 1;
    public static final String META_COLL_KEY_AUTH_INFO = "authInfo";
    public static final String META_COLL_KEY_IMAGE_SIG = "imageSig";
    public static final int META_SERIALIZER_JSON = 1;
    public static final int META_SERIALIZER_PB = 2;
    public static final String META_TYPE_DOC = "zdoc";
    public static final String META_TYPE_FACE = "zface";
    public static final String SUB_TYPE_DARK = "Dark";
    public static final String SUB_TYPE_DEPTH = "Depth";
    public static final String SUB_TYPE_DOC_IMAGE = "docimage";
    public static final String SUB_TYPE_IR = "SLIR";
    public static final String SUB_TYPE_NANO = "Nano";
    public static final String SUB_TYPE_PANO = "Pano";
    public static final String SUB_TYPE_SURVEILLANCE = "Surveillance";
    public static final String SUB_TYPE_VERSION = "1.0";
    public static final String TAG = "TOYGER_FLOW_BlobManager";
    public ToygerBlobConfig config;
    public j crypto;

    static {
        java2jni_do_not_delete_this_library_zkfv_1ts_1tj.loadLibrary();
    }

    public abstract byte[] generateBlob(List<Info> list, Map<String, Object> map);

    public abstract byte[] getKey();

    public abstract boolean isUTF8();

    public native byte[] processDepthInfo(ToygerDepthInfo toygerDepthInfo);

    public native byte[] processFrame(TGFrame tGFrame, ToygerFaceAttr toygerFaceAttr, int i, int i2, String str, boolean z, ToygerFaceCallback toygerFaceCallback);

    public native byte[] processFrame(TGFrame tGFrame, ToygerFaceAttr toygerFaceAttr, String str, boolean z, ToygerFaceCallback toygerFaceCallback);

    public native byte[] processFrame(byte[] bArr);

    public byte[] processIRFrameInfo(TGFrame tGFrame, boolean z) {
        byte[] bArrTgFrameToBlob;
        byte[] bArrA;
        int iIntValue = this.config.getDesiredWidth().intValue();
        int compressRate = (int) (this.config.getCompressRate() * 100.0f);
        if ((tGFrame == null || tGFrame.data == null) || (bArrTgFrameToBlob = ToygerImageUtil.tgFrameToBlob(tGFrame, iIntValue, compressRate, "jpeg", z)) == null || (bArrA = this.crypto.a(bArrTgFrameToBlob)) == null) {
            return null;
        }
        return bArrA;
    }

    public native byte[] processSensorData(String str);
}
