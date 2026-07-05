package layaair.game.Market;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.aliyun.aliyunface.api.ZIMFacade;
import layaair.game.IMarket.IPlugin;
import layaair.game.IMarket.IPluginRuntimeProxy;
import layaair.game.conch.ILayaEventListener;
import layaair.game.conch.ILayaGameEgine;
import layaair.game.conch.LayaConch5;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class GameEngine implements IPlugin {
    private static final String TAG = "LayaGameEngine";
    public static GameEngine _instance;
    private Context mContext;
    public ILayaGameEgine mLayaGameEngine;
    private String mGameUrl = "";
    private IPluginRuntimeProxy mGameEngineProxy = null;

    @Override // layaair.game.IMarket.IPlugin
    public void game_plugin_configonChanged(Configuration configuration) {
    }

    @Override // layaair.game.IMarket.IPlugin
    public Object game_plugin_get_value(String str) {
        return null;
    }

    @Override // layaair.game.IMarket.IPlugin
    public boolean game_plugin_intercept_key(int i) {
        return false;
    }

    @Override // layaair.game.IMarket.IPlugin
    public Object game_plugin_invoke_method(String str, Bundle bundle) {
        return null;
    }

    public GameEngine(Context context) {
        this.mLayaGameEngine = null;
        this.mContext = null;
        this.mContext = context;
        initMarket();
        this.mLayaGameEngine = new LayaConch5(context);
        _instance = this;
    }

    public static GameEngine getInstance() {
        return _instance;
    }

    private void initMarket() {
        Log.d("", ">>>>>>>>>>>>>>>>>>>>>>>>");
        Bundle bundle = new Bundle();
        bundle.putString(LayaConch5.MARKET_MARKETNAME, "MarketTest");
        bundle.putInt(LayaConch5.MARKET_WAITSCREENBKCOLOR, 0);
        bundle.putInt(LayaConch5.MARKET_ENTERPLATFORMTYPE, 0);
        bundle.putString(LayaConch5.MARKET_EXITSHOWWEBURL, "");
        bundle.putString(LayaConch5.MARKET_SERVERNAME, "");
        bundle.putInt(LayaConch5.MARKET_PAYTYPE, 0);
        bundle.putInt(LayaConch5.MARKET_LOGINTYPE, 1);
        bundle.putInt(LayaConch5.MARKET_CHARGETYPE, 0);
        LayaConch5.setMarketBundle(bundle);
    }

    @Override // layaair.game.IMarket.IPlugin
    public View game_plugin_get_view() {
        Log.e(TAG, "game_plugin_get_view");
        return this.mLayaGameEngine.getAbsLayout();
    }

    @Override // layaair.game.IMarket.IPlugin
    public void game_plugin_init(int i) {
        Log.d(TAG, "game_plugin_init url =" + this.mGameUrl);
        String str = this.mGameUrl;
        if (str == null || str.length() < 2) {
            Log.e("", "引擎初始化失败，没有游戏地址 gameUrl = " + this.mGameUrl);
            return;
        }
        String str2 = this.mGameUrl;
        this.mLayaGameEngine.setIsPlugin(false);
        this.mLayaGameEngine.setGameUrl(str2);
        Log.d(TAG, "url=" + str2);
        String str3 = (String) this.mGameEngineProxy.laya_get_value("CacheDirInSdcard");
        if (str3 == null) {
            str3 = (String) this.mGameEngineProxy.laya_get_value("CacheDir");
        }
        this.mLayaGameEngine.setDownloadThreadNum(i);
        this.mLayaGameEngine.setAppCacheDir(str3);
        this.mLayaGameEngine.setExpansionZipDir((String) this.mGameEngineProxy.laya_get_value("ExpansionMainPath"), (String) this.mGameEngineProxy.laya_get_value("ExpansionPatchPath"));
        this.mLayaGameEngine.setAssetInfo(this.mContext.getAssets());
        layaGameListener layagamelistener = new layaGameListener();
        layagamelistener.activity = (Activity) this.mContext;
        this.mLayaGameEngine.setLayaEventListener(layagamelistener);
        this.mLayaGameEngine.setInterceptKey(true);
        this.mLayaGameEngine.onCreate();
        LayaConch5 layaConch5 = (LayaConch5) this.mLayaGameEngine;
        Log.e(TAG, "game_plugin_init soPath=" + layaConch5.getSoPath() + " jarfile=" + layaConch5.getJarFile() + " appcache=" + layaConch5.getAppCacheDir());
    }

    @Override // layaair.game.IMarket.IPlugin
    public void game_plugin_onPause() {
        this.mLayaGameEngine.onPause();
    }

    @Override // layaair.game.IMarket.IPlugin
    public void game_plugin_onResume() {
        this.mLayaGameEngine.onResume();
    }

    @Override // layaair.game.IMarket.IPlugin
    public void game_plugin_onStop() {
        this.mLayaGameEngine.onStop();
    }

    @Override // layaair.game.IMarket.IPlugin
    public void game_plugin_onDestory() {
        this.mLayaGameEngine.onDestroy();
    }

    @Override // layaair.game.IMarket.IPlugin
    public void game_plugin_set_option(String str, String str2) {
        Log.e(TAG, "game_plugin_set_option key=" + str + " value=" + str2);
        if (str.equalsIgnoreCase("gameUrl")) {
            this.mGameUrl = str2;
        } else if (str.equalsIgnoreCase("localize")) {
            this.mLayaGameEngine.setLocalizable(str2.equalsIgnoreCase(ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_TRUE));
        }
    }

    @Override // layaair.game.IMarket.IPlugin
    public void game_plugin_set_runtime_proxy(IPluginRuntimeProxy iPluginRuntimeProxy) {
        this.mGameEngineProxy = iPluginRuntimeProxy;
    }

    public IPluginRuntimeProxy getRuntimeProxy() {
        return this.mGameEngineProxy;
    }

    static class layaGameListener implements ILayaEventListener {
        public Activity activity;

        @Override // layaair.game.conch.ILayaEventListener
        public void destory() {
        }

        @Override // layaair.game.conch.ILayaEventListener
        public void showAssistantTouch(boolean z) {
        }

        layaGameListener() {
        }

        @Override // layaair.game.conch.ILayaEventListener
        public void ExitGame() {
            Log.i("=======", "======exit");
            this.activity.finish();
            this.activity = null;
            System.exit(0);
        }
    }
}
