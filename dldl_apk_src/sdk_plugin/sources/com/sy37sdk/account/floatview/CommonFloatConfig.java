package com.sy37sdk.account.floatview;

import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sq.tools.network.respond.JsonResponse;
import com.sq.tools.network.respond.ResponseTools;
import com.sqwan.common.constants.SqConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class CommonFloatConfig extends ResponseTools {

    @JsonResponse({"float_window", "background_url"})
    public String background_url;

    @JsonResponse({"float_window", "icon_url"})
    public String iconUrl;

    @JsonResponse({"float_window", SqTrackCommonKey.id})
    public String id;

    @JsonResponse({"float_window", "is_show"})
    public boolean isShow;

    @JsonResponse({"float_window", "menu"})
    private JSONArray menuArray;
    public List<MenuConfig> menuConfigs;

    @JsonResponse({"float_window", "name"})
    public String name;

    @JsonResponse({"float_window", "nickname_color"})
    public String nickname_color;

    @JsonResponse({"float_window", SqConstants.SIGN})
    public String sign;

    @JsonResponse({"float_window", "title_color"})
    public String title_color;

    @JsonResponse({"float_window", "user_center"})
    public String user_center;

    @JsonResponse({"float_window", "user_center_color"})
    public String user_center_color;

    @JsonResponse({"float_window", "warning_type"})
    public String warning_type;

    public CommonFloatConfig(String str) {
        initSelfByString(str);
        initMenuConfigs();
    }

    public CommonFloatConfig(JSONObject jSONObject) {
        initSelfByJson(jSONObject);
        initMenuConfigs();
    }

    private void initMenuConfigs() {
        JSONArray jSONArray = this.menuArray;
        if (jSONArray == null || jSONArray.length() <= 0) {
            return;
        }
        this.menuConfigs = new ArrayList();
        for (int i = 0; i < this.menuArray.length(); i++) {
            this.menuConfigs.add(new MenuConfig(this.menuArray.optJSONObject(i)));
        }
    }

    public List<MenuConfig> getMenuConfigs() {
        return this.menuConfigs;
    }

    public String toString() {
        return "CommonFloatConfig{id='" + this.id + "', sign='" + this.sign + "', name='" + this.name + "', iconUrl='" + this.iconUrl + "', isShow=" + this.isShow + ", menuArray=" + this.menuArray + ", user_center='" + this.user_center + "', menuConfigs=" + this.menuConfigs + AbstractJsonLexerKt.END_OBJ;
    }

    public boolean needRedDot() {
        List<MenuConfig> list = this.menuConfigs;
        if (list != null && list.size() >= 1) {
            Iterator<MenuConfig> it = this.menuConfigs.iterator();
            while (it.hasNext()) {
                if (it.next().needRedDot()) {
                    return true;
                }
            }
        }
        return false;
    }
}
