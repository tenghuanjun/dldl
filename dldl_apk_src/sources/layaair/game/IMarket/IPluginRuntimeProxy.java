package layaair.game.IMarket;

import android.os.Bundle;
import android.webkit.ValueCallback;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface IPluginRuntimeProxy {
    void GetFriendsList(JSONObject jSONObject, ValueCallback<JSONObject> valueCallback);

    void Login(JSONObject jSONObject, ValueCallback<JSONObject> valueCallback);

    void Logout(JSONObject jSONObject, ValueCallback<JSONObject> valueCallback);

    void OpenBBS(JSONObject jSONObject, ValueCallback<JSONObject> valueCallback);

    void Pay(JSONObject jSONObject, ValueCallback<JSONObject> valueCallback);

    void PushIcon(JSONObject jSONObject, ValueCallback<JSONObject> valueCallback);

    void SendMessageToPlatform(JSONObject jSONObject, ValueCallback<JSONObject> valueCallback);

    void Share(JSONObject jSONObject, ValueCallback<JSONObject> valueCallback);

    Object laya_get_value(String str);

    Object laya_invoke_Method(String str, Bundle bundle);

    boolean laya_set_value(String str, Object obj);

    void laya_stop_game_engine();
}
