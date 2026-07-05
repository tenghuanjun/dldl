package layaair.game.IMarket;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface IPlugin {
    void game_plugin_configonChanged(Configuration configuration);

    Object game_plugin_get_value(String str);

    View game_plugin_get_view();

    void game_plugin_init(int i);

    boolean game_plugin_intercept_key(int i);

    Object game_plugin_invoke_method(String str, Bundle bundle);

    void game_plugin_onDestory();

    void game_plugin_onPause();

    void game_plugin_onResume();

    void game_plugin_onStop();

    void game_plugin_set_option(String str, String str2);

    void game_plugin_set_runtime_proxy(IPluginRuntimeProxy iPluginRuntimeProxy);
}
