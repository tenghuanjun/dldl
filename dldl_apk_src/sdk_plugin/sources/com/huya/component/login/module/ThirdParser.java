package com.huya.component.login.module;

import android.text.TextUtils;
import com.sqwan.common.constants.SqConstants;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface ThirdParser {
    String getAccessToken(Map<String, String> map);

    String getExpiration(Map<String, String> map);

    String getName(Map<String, String> map);

    String getPartnerId(Map<String, String> map);

    String getRefreshToken(Map<String, String> map);

    String getUnionId(Map<String, String> map);

    String getUrl(Map<String, String> map);

    public static abstract class ComnParser implements ThirdParser {
        @Override // com.huya.component.login.module.ThirdParser
        public String getAccessToken(Map<String, String> map) {
            String str = map.get("accessToken");
            if (TextUtils.isEmpty(str)) {
                str = map.get(SqConstants.ACCESS_TOKEN);
            }
            return str == null ? "" : str;
        }

        @Override // com.huya.component.login.module.ThirdParser
        public String getUnionId(Map<String, String> map) {
            String str = map.get("unionid");
            return str == null ? "" : str;
        }

        @Override // com.huya.component.login.module.ThirdParser
        public String getRefreshToken(Map<String, String> map) {
            String str = map.get("refreshToken");
            return str == null ? "" : str;
        }

        @Override // com.huya.component.login.module.ThirdParser
        public String getExpiration(Map<String, String> map) {
            String str = map.get("expiration");
            if (TextUtils.isEmpty(str)) {
                str = map.get("expires_in");
            }
            return str == null ? "" : str;
        }
    }
}
