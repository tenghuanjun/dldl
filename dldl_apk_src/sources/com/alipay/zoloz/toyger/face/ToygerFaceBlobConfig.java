package com.alipay.zoloz.toyger.face;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alipay.zoloz.toyger.algorithm.ToygerBlobConfig;
import com.alipay.zoloz.toyger.blob.BlobManager;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ToygerFaceBlobConfig extends ToygerBlobConfig {

    @JSONField(name = "collection")
    public List<String> collection;

    @JSONField(name = "desiredWidth")
    public int desiredWidth;

    @JSONField(name = "uploadImageType")
    public String uploadImageType;

    @JSONField(name = "upload_compress_rate")
    public float upload_compress_rate;

    public ToygerFaceBlobConfig() {
        this.upload_compress_rate = 0.7f;
        this.desiredWidth = 480;
        ArrayList arrayList = new ArrayList();
        this.collection = arrayList;
        arrayList.add(BlobManager.SUB_TYPE_PANO);
        this.uploadImageType = "jpeg";
    }

    public ToygerFaceBlobConfig(float f, int i, String str, String str2) {
        this.upload_compress_rate = f;
        this.desiredWidth = i;
        this.pubkey = str;
        this.uploadImageType = str2;
    }

    public static ToygerFaceBlobConfig from(String str, String str2) {
        if (str == null || str2 == null) {
            return new ToygerFaceBlobConfig();
        }
        ToygerFaceBlobConfig toygerFaceBlobConfig = (ToygerFaceBlobConfig) JSON.parseObject(str, ToygerFaceBlobConfig.class);
        if (toygerFaceBlobConfig.desiredWidth <= 0) {
            toygerFaceBlobConfig.desiredWidth = 1280;
        }
        if (toygerFaceBlobConfig.collection == null) {
            toygerFaceBlobConfig.collection = new ArrayList();
        }
        toygerFaceBlobConfig.pubkey = str2;
        if (toygerFaceBlobConfig.uploadImageType == null) {
            toygerFaceBlobConfig.uploadImageType = "jpeg";
        }
        return toygerFaceBlobConfig;
    }

    @Override // com.alipay.zoloz.toyger.algorithm.ToygerBlobConfig
    public float getCompressRate() {
        return this.upload_compress_rate;
    }

    @Override // com.alipay.zoloz.toyger.algorithm.ToygerBlobConfig
    public Integer getDesiredWidth() {
        return Integer.valueOf(this.desiredWidth);
    }

    public int getMinWidth(int i) {
        int i2 = this.desiredWidth;
        return i2 > i ? i : i2;
    }

    public String toString() {
        return "ToygerFaceBlobConfig{upload_compress_rate=" + this.upload_compress_rate + ", desiredWidth=" + this.desiredWidth + ", collection=" + this.collection + ", uploadImageType='" + this.uploadImageType + "'}";
    }
}
