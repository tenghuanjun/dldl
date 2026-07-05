package com.sy37sdk.account.floatview;

import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sq.tools.network.respond.JsonResponse;
import com.sq.tools.network.respond.ResponseTools;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class MenuConfig extends ResponseTools {
    public static final String WARNING_TYPE_RED_POINT = "1";
    public static final String WARNING_TYPE_SHAKE = "3";
    public static final String WARNING_TYPE_TEXT = "2";

    @JsonResponse({"desc"})
    public String desc;

    @JsonResponse({"icon_url"})
    public String iconUrl;

    @JsonResponse({SqTrackCommonKey.id})
    public String id;

    @JsonResponse({"need_red_dot"})
    public int needRedDot;

    @JsonResponse({"open_type"})
    public String openType;

    @JsonResponse({"url"})
    public String openUrl;

    @JsonResponse({"priority"})
    public int priority;

    @JsonResponse({"sdk_method_value"})
    public String sdk_method_value;

    @JsonResponse({"title"})
    public String title;

    @JsonResponse({"uuid"})
    public String uuid;

    @JsonResponse({"warning_type"})
    public String warningType = "";

    @JsonResponse({"warning_msg"})
    public String warningMsg = "";

    public MenuConfig(String str) {
        initSelfByString(str);
    }

    public MenuConfig(JSONObject jSONObject) {
        initSelfByJson(jSONObject);
    }

    public MenuConfig() {
    }

    public boolean needRedDot() {
        return this.needRedDot == 1;
    }

    public String toString() {
        return "MenuConfig{id='" + this.id + "', uuid='" + this.uuid + "', title='" + this.title + "', desc='" + this.desc + "', iconUrl='" + this.iconUrl + "', openType='" + this.openType + "', openUrl='" + this.openUrl + "', needRedDot=" + this.needRedDot + AbstractJsonLexerKt.END_OBJ;
    }
}
