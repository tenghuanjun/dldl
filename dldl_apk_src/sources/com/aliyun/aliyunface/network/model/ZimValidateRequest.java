package com.aliyun.aliyunface.network.model;

import com.aliyun.aliyunface.utils.StringUtil;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ZimValidateRequest {
    public Map<String, String> bizData;
    public String zimData;
    public String zimId;

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("ZimValidateRequest{zimId='");
        sb.append(this.zimId);
        sb.append("'");
        sb.append(", data='");
        if (this.zimData == null) {
            str = "null";
        } else {
            str = "[length=" + this.zimData.length() + "]";
        }
        sb.append(str);
        sb.append("'");
        sb.append(", bizData='");
        sb.append(StringUtil.map2String(this.bizData));
        sb.append("'");
        sb.append('}');
        return sb.toString();
    }
}
