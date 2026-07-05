package com.huya.component.login.module;

import android.text.TextUtils;
import com.duowan.auk.ArkUtils;
import com.huya.component.login.module.ThirdParser;
import com.huya.mtp.utils.json.JsonUtils;
import com.sqwan.common.constants.SqConstants;
import com.umeng.socialize.bean.SHARE_MEDIA;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ParserFactory {

    /* JADX INFO: renamed from: com.huya.component.login.module.ParserFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$umeng$socialize$bean$SHARE_MEDIA;

        static {
            int[] iArr = new int[SHARE_MEDIA.values().length];
            $SwitchMap$com$umeng$socialize$bean$SHARE_MEDIA = iArr;
            try {
                iArr[SHARE_MEDIA.WEIXIN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$umeng$socialize$bean$SHARE_MEDIA[SHARE_MEDIA.QQ.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$umeng$socialize$bean$SHARE_MEDIA[SHARE_MEDIA.SINA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static ThirdParser getParser(SHARE_MEDIA share_media) {
        int i = AnonymousClass1.$SwitchMap$com$umeng$socialize$bean$SHARE_MEDIA[share_media.ordinal()];
        AnonymousClass1 anonymousClass1 = null;
        if (i == 1) {
            return new WX(anonymousClass1);
        }
        if (i == 2) {
            return new QQ(anonymousClass1);
        }
        if (i == 3) {
            return new Sina(anonymousClass1);
        }
        WX wx = new WX(anonymousClass1);
        ArkUtils.crashIfDebug("error type " + share_media.toString(), new Object[0]);
        return wx;
    }

    private static class WX extends ThirdParser.ComnParser {
        private WX() {
        }

        /* synthetic */ WX(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // com.huya.component.login.module.ThirdParser
        public String getName(Map<String, String> map) {
            String str = map.get("nickname");
            return str == null ? "" : str;
        }

        @Override // com.huya.component.login.module.ThirdParser
        public String getUrl(Map<String, String> map) {
            String str = map.get("headimgurl");
            return str == null ? "" : str;
        }

        @Override // com.huya.component.login.module.ThirdParser
        public String getPartnerId(Map<String, String> map) {
            String str = map.get(SqConstants.OPEN_ID);
            return str == null ? "" : str;
        }
    }

    private static class QQ extends ThirdParser.ComnParser {
        private QQ() {
        }

        /* synthetic */ QQ(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // com.huya.component.login.module.ThirdParser
        public String getName(Map<String, String> map) {
            String str = map.get("screen_name");
            if (TextUtils.isEmpty(str) || !TextUtils.isDigitsOnly(str) || str.length() < 5) {
                return str == null ? "" : str;
            }
            return str.substring(0, str.length() - 2) + "**";
        }

        @Override // com.huya.component.login.module.ThirdParser
        public String getUrl(Map<String, String> map) {
            String str = map.get("profile_image_url");
            return str == null ? "" : str;
        }

        @Override // com.huya.component.login.module.ThirdParser
        public String getPartnerId(Map<String, String> map) {
            String str = map.get(SqConstants.OPEN_ID);
            return str == null ? "" : str;
        }
    }

    private static class Sina extends ThirdParser.ComnParser {
        private Sina() {
        }

        /* synthetic */ Sina(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // com.huya.component.login.module.ThirdParser
        public String getName(Map<String, String> map) {
            Object json = JsonUtils.parseJson(map.get("result"), (Class<Object>) Object.class);
            String str = json instanceof Map ? (String) ((Map) json).get("screen_name") : null;
            return str == null ? "" : str;
        }

        @Override // com.huya.component.login.module.ThirdParser
        public String getUrl(Map<String, String> map) {
            Object json = JsonUtils.parseJson(map.get("result"), (Class<Object>) Object.class);
            String str = json instanceof Map ? (String) ((Map) json).get("profile_image_url") : null;
            return str == null ? "" : str;
        }

        @Override // com.huya.component.login.module.ThirdParser
        public String getPartnerId(Map<String, String> map) {
            String str = map.get("uid");
            return str == null ? "" : str;
        }
    }
}
