package com.alipay.zoloz.toyger.face;

import android.graphics.Rect;
import android.graphics.RectF;
import com.alipay.zoloz.toyger.algorithm.TGFrame;
import com.alipay.zoloz.toyger.blob.BlobManager;
import java.util.List;
import java.util.Map;
import toygerservice.j;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public abstract class FaceBlobManager extends BlobManager<ToygerFaceInfo> {
    public static final int MONITOR_COMPRESS_RATE = 30;
    public static final int MONITOR_IMAGE_WIDTH = 160;
    public byte[] bestDepthImage;
    public byte[] bestIRImage;
    public byte[] bestLightImage;
    public String compressFormat;
    public float compressRate;
    public ToygerDepthInfo depthInfo;
    public int desireWidth;
    public TGFrame irFrame;
    public boolean isMirror;
    public boolean isNano;

    public FaceBlobManager() {
    }

    public FaceBlobManager(ToygerFaceBlobConfig toygerFaceBlobConfig, boolean z) {
        this.config = toygerFaceBlobConfig;
        this.desireWidth = toygerFaceBlobConfig.desiredWidth;
        this.crypto = new j(toygerFaceBlobConfig.pubkey, z);
    }

    public static Rect convertFaceRegion(RectF rectF, int i, int i2, int i3, boolean z) {
        if (z) {
            float f = i;
            float f2 = i2;
            return new Rect((int) ((1.0f - rectF.right) * f), (int) (rectF.top * f2), (int) ((1.0f - rectF.left) * f), (int) (rectF.bottom * f2));
        }
        float f3 = i;
        float f4 = i2;
        return new Rect((int) (rectF.left * f3), (int) (rectF.top * f4), (int) (rectF.right * f3), (int) (rectF.bottom * f4));
    }

    public abstract void addMonitorImage(TGFrame tGFrame);

    public abstract Map<String, Object> generateBlob(Map<String, Object> map);

    @Override // com.alipay.zoloz.toyger.blob.BlobManager
    public abstract byte[] generateBlob(List<ToygerFaceInfo> list, Map<String, Object> map);

    public abstract byte[] generateFaceBlob(TGFrame tGFrame, ToygerFaceAttr toygerFaceAttr);

    public abstract byte[] generateLocalMatchingBlob(String str, byte[] bArr, byte[] bArr2, String str2);

    public String getBlobElemType(ToygerFaceInfo toygerFaceInfo) {
        int i = toygerFaceInfo.frame.frameType;
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? "" : BlobManager.SUB_TYPE_NANO : BlobManager.SUB_TYPE_IR : BlobManager.SUB_TYPE_DEPTH : BlobManager.SUB_TYPE_DARK : BlobManager.SUB_TYPE_PANO;
    }

    public abstract byte[] getFileIdBlob(String str);

    @Override // com.alipay.zoloz.toyger.blob.BlobManager
    public byte[] getKey() {
        return this.crypto.b;
    }

    public abstract byte[] getMonitorBlob();

    @Override // com.alipay.zoloz.toyger.blob.BlobManager
    public abstract boolean isUTF8();
}
