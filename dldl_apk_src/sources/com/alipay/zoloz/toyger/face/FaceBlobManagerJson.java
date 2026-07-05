package com.alipay.zoloz.toyger.face;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.asm.Opcodes;
import com.alipay.zoloz.toyger.algorithm.TGFrame;
import com.alipay.zoloz.toyger.blob.BlobManager;
import com.igexin.push.core.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java2jni_do_not_delete_this.java2jni_do_not_delete_this_library_zkfv_1ts_1tj;
import toygerservice.g;
import toygerservice.h;
import toygerservice.i;
import toygerservice.l;
import toygerservice.m;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class FaceBlobManagerJson extends FaceBlobManager {
    private List<h> mMonitorBlobElems;
    private ToygerFaceCallback toygerFaceCallback;

    static {
        java2jni_do_not_delete_this_library_zkfv_1ts_1tj.loadLibrary();
    }

    public FaceBlobManagerJson() {
    }

    public FaceBlobManagerJson(ToygerFaceBlobConfig toygerFaceBlobConfig, boolean z, ToygerFaceCallback toygerFaceCallback) {
        super(toygerFaceBlobConfig, z);
        this.toygerFaceCallback = toygerFaceCallback;
    }

    private i deSerializerByteArray(String str) {
        if (str != null) {
            return (i) JSON.parseObject(str, i.class);
        }
        return null;
    }

    private m generateMeta(List<ToygerFaceInfo> list, Map<String, Object> map, String str) {
        m mVar = new m();
        HashMap map2 = new HashMap();
        map2.put(BlobManager.BLOB_ELEM_IMAGE_TYPE, "jpeg");
        HashMap map3 = new HashMap();
        ToygerFaceCallback toygerFaceCallback = this.toygerFaceCallback;
        if (toygerFaceCallback != null) {
            toygerFaceCallback.onFaceBlobGenerate(str);
        }
        map3.put(BlobManager.BLOB_ELEM_IMAGE_HASHCODE, str);
        map2.put(BlobManager.BLOB_ELEM_IMAGE_RUNTIMEINFO, map3);
        return mVar;
    }

    private h generateMonitorBlob(TGFrame tGFrame) {
        h hVar = new h();
        byte[] bArrProcessFrame = processFrame(tGFrame, null, 160, 30, "jpeg", !this.isMirror, this.toygerFaceCallback);
        hVar.a = bArrProcessFrame;
        if (bArrProcessFrame == null) {
            return null;
        }
        return hVar;
    }

    @Override // com.alipay.zoloz.toyger.face.FaceBlobManager
    public void addMonitorImage(TGFrame tGFrame) {
        h hVarGenerateMonitorBlob = generateMonitorBlob(tGFrame);
        if (this.mMonitorBlobElems == null) {
            this.mMonitorBlobElems = new ArrayList();
        }
        if (hVarGenerateMonitorBlob != null) {
            synchronized (this) {
                this.mMonitorBlobElems.size();
                this.mMonitorBlobElems.add(hVarGenerateMonitorBlob);
            }
        }
    }

    @Override // com.alipay.zoloz.toyger.face.FaceBlobManager
    public native Map<String, Object> generateBlob(Map<String, Object> map);

    @Override // com.alipay.zoloz.toyger.face.FaceBlobManager, com.alipay.zoloz.toyger.blob.BlobManager
    public native byte[] generateBlob(List<ToygerFaceInfo> list, Map<String, Object> map);

    @Override // com.alipay.zoloz.toyger.face.FaceBlobManager
    public byte[] generateFaceBlob(TGFrame tGFrame, ToygerFaceAttr toygerFaceAttr) {
        return null;
    }

    public l generateFaceInfo(ToygerFaceInfo toygerFaceInfo, boolean z) {
        l lVar = new l();
        TGFrame tGFrame = toygerFaceInfo.frame;
        int i = tGFrame.rotation % Opcodes.GETFIELD == 0 ? tGFrame.width : tGFrame.height;
        int i2 = tGFrame.width;
        if (i == i2) {
            i2 = tGFrame.height;
        }
        int iIntValue = (i <= this.config.getDesiredWidth().intValue() || this.config.getDesiredWidth().intValue() <= 0) ? i : this.config.getDesiredWidth().intValue();
        lVar.a = FaceBlobManager.convertFaceRegion(((ToygerFaceAttr) toygerFaceInfo.attr).region(), iIntValue, (int) ((iIntValue / i) * i2), toygerFaceInfo.frame.rotation, z);
        ((ToygerFaceAttr) toygerFaceInfo.attr).quality();
        return lVar;
    }

    @Override // com.alipay.zoloz.toyger.face.FaceBlobManager
    public byte[] generateLocalMatchingBlob(String str, byte[] bArr, byte[] bArr2, String str2) {
        String[] strArrSplit;
        m mVar = new m();
        HashMap map = new HashMap(2);
        if (bArr2 != null) {
            map.put(BlobManager.META_COLL_KEY_IMAGE_SIG, bArr2);
        }
        if (str != null) {
            map.put(BlobManager.META_COLL_KEY_AUTH_INFO, str);
        }
        map.put(BlobManager.BLOB_ELEM_IMAGE_TYPE, "jpeg");
        ArrayList arrayList = new ArrayList();
        g gVar = new g();
        if (bArr != null) {
            l lVar = new l();
            if (str2 != null && (strArrSplit = str2.split(b.aj)) != null && strArrSplit.length != 4) {
                lVar.a.left = Integer.parseInt(strArrSplit[0]);
                lVar.a.top = Integer.parseInt(strArrSplit[1]);
                lVar.a.right = Integer.parseInt(strArrSplit[2]);
                lVar.a.bottom = Integer.parseInt(strArrSplit[3]);
            }
            h hVar = new h();
            hVar.a = bArr;
            ArrayList arrayList2 = new ArrayList();
            hVar.b = arrayList2;
            arrayList2.add(lVar);
            arrayList.add(hVar);
        }
        gVar.a = arrayList;
        i iVar = new i();
        iVar.b = gVar;
        iVar.a = mVar;
        return JSON.toJSONString(iVar).getBytes();
    }

    @Override // com.alipay.zoloz.toyger.face.FaceBlobManager
    public byte[] getFileIdBlob(String str) {
        HashMap map = new HashMap();
        map.put("fileid", str);
        m mVarGenerateMeta = generateMeta(null, map, "getFileIdBlob");
        i iVar = new i();
        iVar.a = mVarGenerateMeta;
        iVar.b = new g();
        return JSON.toJSONString(iVar).getBytes();
    }

    @Override // com.alipay.zoloz.toyger.face.FaceBlobManager
    public byte[] getMonitorBlob() {
        List<h> list = this.mMonitorBlobElems;
        byte[] bytes = null;
        if (list != null && !list.isEmpty()) {
            m mVarGenerateMeta = generateMeta(null, null, "monitorPNG");
            g gVar = new g();
            gVar.a = this.mMonitorBlobElems;
            i iVar = new i();
            iVar.b = gVar;
            iVar.a = mVarGenerateMeta;
            synchronized (this) {
                bytes = JSON.toJSONString(iVar).getBytes();
            }
        }
        return bytes;
    }

    @Override // com.alipay.zoloz.toyger.face.FaceBlobManager, com.alipay.zoloz.toyger.blob.BlobManager
    public boolean isUTF8() {
        return true;
    }
}
