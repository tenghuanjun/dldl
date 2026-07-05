package layaair.game.conch;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.alipay.zoloz.toyger.blob.BlobManager;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.igexin.sdk.PushConsts;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;
import javax.microedition.khronos.opengles.GL10;
import layaair.game.PlatformInterface.LayaPlatformFactory;
import layaair.game.PlatformInterface.LayaPlatformGlue;
import layaair.game.PlatformInterface.LayaPlatformInterface;
import layaair.game.browser.ConchJNI;
import layaair.game.browser.ExportJavaFunction;
import layaair.game.browser.am;
import layaair.game.browser.ap;
import layaair.game.config.config;
import layaair.game.device.DevID;
import layaair.game.network.NetworkReceiver;
import layaair.game.utility.LayaAudioMusic;
import layaair.game.utility.ProcessInfo;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class LayaConch5 implements View.OnKeyListener, ILayaGameEgine {
    public static final String MARKET_CHARGETYPE = "chargetype";
    public static final String MARKET_ENTERPLATFORMTYPE = "enterplatformtype";
    public static final String MARKET_EXITSHOWWEBURL = "exitshowweburle";
    public static final String MARKET_LOGINTYPE = "logintype";
    public static final String MARKET_MARKETNAME = "marketName";
    public static final String MARKET_PAYTYPE = "paytype";
    public static final String MARKET_SERVERNAME = "servername";
    public static final String MARKET_WAITSCREENBKCOLOR = "waitscreenbkcolor";
    private static final String TAG = "LayaConch5";
    static Bundle m_marketBundle = null;
    public static String m_strJarFile = "";
    public static String m_strSoFile = "/liblayaair.so";
    public static String m_strSoPath = "";
    public static LayaConch5 ms_layaConche;
    public boolean m_bHorizontalScreen;
    private NetworkReceiver m_pNetWorkReveiver;
    private float x;
    private float y;
    private float z;
    public boolean localizable = false;
    public AbsoluteLayout m_pAbsLayout = null;
    public am m_pLayaWebView = null;
    public layaair.game.browser.b m_pEditBox$37880073 = null;
    private AbsoluteLayout m_pEditBoxLayout = null;
    public LayaPlatformInterface m_pPlatform = null;
    public layaair.game.browser.a m_pCavans = null;
    public DevID m_pDevID = null;
    private ILayaEventListener m_layaEventListener = null;
    private boolean m_interceptKey = false;
    public AssetManager m_AM = null;
    public Context mCtx = null;
    public boolean m_bIsPlug = true;
    public String m_strUrl = "";
    public String m_strExt = "";
    private long m_nBackPressTime = 0;
    protected int m_nDownloadThreadNum = 3;
    protected String m_strCachePath = "";
    protected String m_strExpansionMainPath = "";
    protected String m_strExpansionPatchPath = "";
    private SensorManager mSensorManager = null;
    private Sensor mSensor = null;
    private Sensor orientationSensor = null;
    private boolean mBIsSensor = false;
    private int canvas_res_w = -1;
    private int canvas_res_h = -1;
    private boolean m_bEnableOnLayout = false;
    SensorEventListener lsn = new a(this);
    int m_iScreenWidth = 0;
    int m_iScreenHeight = 0;

    public LayaConch5() {
        ms_layaConche = this;
    }

    public LayaConch5(Context context) {
        setContext(context);
        Configuration configuration = context.getResources().getConfiguration();
        try {
            this.m_bHorizontalScreen = configuration.screenWidthDp > configuration.screenHeightDp;
        } catch (NoSuchFieldError unused) {
            WindowManager windowManager = (WindowManager) this.mCtx.getSystemService("window");
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            this.m_bHorizontalScreen = displayMetrics.widthPixels > displayMetrics.heightPixels;
        }
        this.m_pNetWorkReveiver = new NetworkReceiver();
        ms_layaConche = this;
    }

    public static LayaConch5 GetInstance() {
        if (ms_layaConche == null) {
            ms_layaConche = new LayaConch5();
        }
        return ms_layaConche;
    }

    public static boolean checkDeviceHasNavigationBar(Context context) {
        try {
            Resources resources = context.getResources();
            int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
            boolean z = identifier > 0 ? resources.getBoolean(identifier) : false;
            try {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                String str = (String) cls.getMethod(MonitorConstants.CONNECT_TYPE_GET, String.class).invoke(cls, "qemu.hw.mainkeys");
                if ("1".equals(str)) {
                    return false;
                }
                if ("0".equals(str)) {
                    return true;
                }
            } catch (Exception unused) {
            }
            return z;
        } catch (Exception unused2) {
            return false;
        }
    }

    public static void deletePath(File file) {
        Log.e("2jni", "cacheMgr delete dir: " + file.toString());
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null || fileArrListFiles.length == 0) {
                file.delete();
                return;
            }
            for (File file2 : fileArrListFiles) {
                deletePath(file2);
            }
            file.delete();
        }
    }

    @SuppressLint({"NewApi"})
    @TargetApi(11)
    private void destory() {
        AbsoluteLayout absoluteLayout = this.m_pAbsLayout;
        if (absoluteLayout != null) {
            absoluteLayout.removeAllViews();
        }
        this.m_pAbsLayout.setOnKeyListener(null);
        if (Build.VERSION.SDK_INT >= 11) {
            this.m_pAbsLayout.setOnGenericMotionListener(null);
        }
        this.m_pAbsLayout = null;
        layaair.game.browser.a aVar = this.m_pCavans;
        if (aVar != null) {
            aVar.a = null;
            this.m_pCavans = null;
        }
        ms_layaConche = null;
        m_marketBundle = null;
        am amVar = this.m_pLayaWebView;
        if (amVar != null) {
            amVar.a();
            this.m_pLayaWebView = null;
        }
        AbsoluteLayout absoluteLayout2 = this.m_pEditBoxLayout;
        if (absoluteLayout2 != null) {
            absoluteLayout2.removeAllViews();
            this.m_pEditBoxLayout = null;
        }
        layaair.game.browser.b bVar = this.m_pEditBox$37880073;
        if (bVar != null) {
            bVar.a();
            this.m_pEditBox$37880073 = null;
        }
        this.mCtx = null;
        this.m_layaEventListener = null;
    }

    public static Vector getCachedApp(String str) {
        Vector vector = new Vector();
        for (File file : new File(str).listFiles()) {
            e eVar = new e();
            if (file.isDirectory()) {
                String name = file.getName();
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append("/");
                sb.append(name);
                if (name.compareTo("sessionFiles") != 0) {
                    String str2 = str + "/" + name + "/sourceid/appid";
                    if (new File(str2).exists()) {
                        try {
                            Integer.parseInt(new BufferedReader(new FileReader(str2)).readLine());
                        } catch (Exception unused) {
                        }
                    }
                }
            }
            vector.add(eVar);
        }
        return vector;
    }

    public static Bundle getMarketBundle() {
        Bundle bundle = m_marketBundle;
        if (bundle != null) {
            return bundle;
        }
        Bundle bundle2 = new Bundle();
        m_marketBundle = bundle2;
        return bundle2;
    }

    private boolean isFinishing(Activity activity) {
        return activity == null || activity.isFinishing();
    }

    public static boolean isHUAWEI() {
        return Build.MANUFACTURER.equals("HUAWEI");
    }

    private void registerSensor() {
        if (this.mSensorManager == null) {
            this.mSensorManager = (SensorManager) this.mCtx.getSystemService(BlobManager.BLOB_ELEM_TYPE_SENSOR);
            this.mSensor = this.mSensorManager.getDefaultSensor(1);
            this.orientationSensor = this.mSensorManager.getDefaultSensor(3);
        }
        this.mSensorManager.registerListener(this.lsn, this.orientationSensor, 3);
        this.mSensorManager.registerListener(this.lsn, this.mSensor, 3);
    }

    public static void setMarketBundle(Bundle bundle) {
        m_marketBundle = bundle;
    }

    private void unRegisterSensor() {
        SensorManager sensorManager = this.mSensorManager;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this.lsn);
        }
    }

    public void EngineStart() {
        String str = getAppCacheDir() + "/LayaCache";
        File file = new File(str);
        if (!file.exists()) {
            file.mkdir();
        }
        Log.e(TAG, "plugin-----------------EngineStart() = " + str + "/localstorage");
        File file2 = new File(str + "/localstorage");
        if (!file2.exists() && !file2.mkdirs()) {
            Log.e("", "创建localStorage目录失败！");
            ExportJavaFunction.alert("创建游戏目录失败，请清理空间或重启应用再试");
            return;
        }
        ConchJNI.SetLocalStoragePath(str + "/localstorage");
        InitView();
        String str2 = getAppCacheDir() + "/LayaCache";
        AssetManager assetManager = this.m_AM;
        if (assetManager != null) {
            int downloadThreadNum = getDownloadThreadNum();
            String str3 = this.m_strExpansionMainPath;
            if (str3 == null) {
                str3 = "";
            }
            String str4 = str3;
            String str5 = this.m_strExpansionPatchPath;
            if (str5 == null) {
                str5 = "";
            }
            ConchJNI.InitDLib(assetManager, downloadThreadNum, "cache", str2, str4, str5);
            return;
        }
        int downloadThreadNum2 = getDownloadThreadNum();
        String jarFile = getJarFile();
        String str6 = this.m_strExpansionMainPath;
        if (str6 == null) {
            str6 = "";
        }
        String str7 = str6;
        String str8 = this.m_strExpansionPatchPath;
        if (str8 == null) {
            str8 = "";
        }
        ConchJNI.InitDLib(null, downloadThreadNum2, jarFile, str2, str7, str8);
    }

    public int GetScreenHeight() {
        WindowManager windowManager = (WindowManager) this.mCtx.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        this.m_iScreenHeight = displayMetrics.heightPixels;
        return this.m_iScreenHeight;
    }

    public int GetScreenWidth() {
        WindowManager windowManager = (WindowManager) this.mCtx.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        this.m_iScreenWidth = displayMetrics.widthPixels;
        return this.m_iScreenWidth;
    }

    @SuppressLint({"NewApi"})
    @TargetApi(11)
    void InitView() {
        String str;
        StringBuilder sb;
        String string;
        this.m_pCavans = new layaair.game.browser.a(this.mCtx);
        if (this.m_pAbsLayout == null) {
            this.m_pAbsLayout = this.m_bEnableOnLayout ? new b(this, this.mCtx) : new AbsoluteLayout(this.mCtx);
            this.m_pAbsLayout.setBackgroundColor(16777215);
            if (Build.VERSION.SDK_INT >= 11) {
                this.m_pAbsLayout.setOnGenericMotionListener(new c(this));
            }
            this.m_pAbsLayout.setOnKeyListener(this);
        }
        if ((Build.VERSION.SDK_INT >= 11 ? (char) 4 : (char) 0) != 0) {
            this.m_pAbsLayout.setSystemUiVisibility(4);
        }
        if (this.m_pEditBox$37880073 == null) {
            this.m_pEditBoxLayout = new AbsoluteLayout(this.mCtx);
            this.m_pEditBoxLayout.setBackgroundColor(0);
            this.m_pEditBox$37880073 = new layaair.game.browser.b(this.mCtx);
        }
        this.m_pCavans.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        WindowManager windowManager = (WindowManager) this.mCtx.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        if ((this.m_bHorizontalScreen && displayMetrics.heightPixels > displayMetrics.widthPixels) || (!this.m_bHorizontalScreen && displayMetrics.widthPixels > displayMetrics.heightPixels)) {
            int i = displayMetrics.heightPixels;
            displayMetrics.heightPixels = displayMetrics.widthPixels;
            displayMetrics.widthPixels = i;
        }
        this.m_pCavans.b = displayMetrics.widthPixels;
        this.m_pCavans.c = displayMetrics.heightPixels;
        if (this.canvas_res_w > 0 || this.canvas_res_h > 0) {
            this.m_pCavans.a(this.canvas_res_w, this.canvas_res_h);
            this.canvas_res_w = -1;
            this.canvas_res_h = -1;
        }
        this.m_pAbsLayout.addView(this.m_pCavans);
        if (this.m_pLayaWebView == null) {
            try {
                this.m_pLayaWebView = new am(this.mCtx, this);
                if (config.GetInstance().m_sWebviewUrl != null) {
                    this.m_pLayaWebView.a(config.GetInstance().m_sWebviewUrl, 0, 0, GetScreenWidth(), GetScreenHeight());
                }
            } catch (Exception e) {
                str = "LayaBoxwebView";
                sb = new StringBuilder(">>>>>>>>>>>>>>Exception");
                string = e.toString();
                sb.append(string);
                Log.e(str, sb.toString());
            } catch (Throwable th) {
                str = "LayaBoxwebView";
                sb = new StringBuilder(">>>>>>>>>>>>>>throwable");
                string = th.toString();
                sb.append(string);
                Log.e(str, sb.toString());
            }
        }
        layaair.game.browser.b bVar = this.m_pEditBox$37880073;
        if (bVar != null) {
            bVar.d();
            this.m_pEditBoxLayout.addView(this.m_pEditBox$37880073.a, new AbsoluteLayout.LayoutParams(GetScreenWidth(), GetScreenHeight(), 0, 0));
            this.m_pAbsLayout.addView(this.m_pEditBoxLayout, new ViewGroup.LayoutParams(-2, -2));
        }
        if (this.m_interceptKey) {
            Log.i("layaConch5", "m_interceptKey OK!");
            this.m_pAbsLayout.setFocusable(true);
            this.m_pAbsLayout.setFocusableInTouchMode(true);
            this.m_pAbsLayout.requestFocus();
        }
    }

    public void MarketOnCreate() {
        String string = getMarketBundle().getString(MARKET_MARKETNAME);
        if (string == null) {
            string = "";
        }
        this.m_pPlatform = LayaPlatformFactory.GetInstance().CreateInterface("layaair.game.Market." + string);
        Log.e("", "m_pPlatform = " + this.m_pPlatform);
        LayaPlatformGlue.GetInstance().Init(this.m_pPlatform);
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_onCreate(this.mCtx);
        }
    }

    public void PlatformInitOK(int i) {
        Log.e("0", "==============Java流程 InitMainCanvas()");
        EngineStart();
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public void _enableOnLayout(boolean z) {
        this.m_bEnableOnLayout = z;
    }

    public void alertJS(String str, String str2, int i) {
        Activity activity = (Activity) this.mCtx;
        if (isFinishing(activity)) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(str);
        builder.setMessage(str2);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new d(this, i));
        AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.show();
    }

    public void delInstance() {
        ExportJavaFunction.DelInstance();
        LayaPlatformGlue.DelInstance();
        config.DelInstance();
        LayaPlatformFactory.DelInstance();
        ILayaEventListener iLayaEventListener = this.m_layaEventListener;
        if (iLayaEventListener != null) {
            iLayaEventListener.destory();
        }
    }

    public void dispatchGL(GL10 gl10, int i, int i2) {
        ConchJNI.captureScreenCallBack(i, i2, ap.a(gl10, i, i2));
    }

    public void game_conch3_SetIsPlug(boolean z) {
        this.m_bIsPlug = z;
    }

    public View game_conch3_get_view() {
        return this.m_pAbsLayout;
    }

    public void game_conch3_init() {
        onCreate();
    }

    public void game_conch3_onPause() {
        onPause();
    }

    public void game_conch3_onResume() {
        onResume();
    }

    public void game_conch3_onStop() {
        onStop();
    }

    public void game_conch3_setAppWorkPath(String str) {
        this.m_strCachePath = str;
    }

    public void game_conch3_setAssetInfo(AssetManager assetManager) {
        this.m_AM = assetManager;
    }

    public void game_plugin_exitGame() {
        ILayaEventListener iLayaEventListener = this.m_layaEventListener;
        if (iLayaEventListener != null) {
            iLayaEventListener.ExitGame();
        }
    }

    public int game_plugin_getTouchMovRange() {
        return 10;
    }

    public void game_plugin_onUrlBack() {
        ConchJNI.onRunCmd(4461, -1, 0);
    }

    public void game_plugin_onUrlRefresh() {
        ConchJNI.onRunCmd(4459, 0, 0);
    }

    public void game_showAssistantTouch(boolean z) {
        ILayaEventListener iLayaEventListener = this.m_layaEventListener;
        if (iLayaEventListener != null) {
            iLayaEventListener.showAssistantTouch(z);
        }
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public AbsoluteLayout getAbsLayout() {
        return this.m_pAbsLayout;
    }

    public String getAppCacheDir() {
        return this.m_strCachePath;
    }

    public layaair.game.browser.a getCanvas() {
        return this.m_pCavans;
    }

    public DevID getDevID() {
        return this.m_pDevID;
    }

    public int getDownloadThreadNum() {
        return this.m_nDownloadThreadNum;
    }

    public layaair.game.browser.b getEditBox$6b0f844e() {
        return this.m_pEditBox$37880073;
    }

    public Context getGameContext() {
        return this.mCtx;
    }

    public boolean getHorizontalScreen() {
        return this.m_bHorizontalScreen;
    }

    public boolean getInterceptKey() {
        return this.m_interceptKey;
    }

    public boolean getIsPlug() {
        return this.m_bIsPlug;
    }

    public String getJarFile() {
        return m_strJarFile;
    }

    public NetworkReceiver getNetworkReceiver() {
        return this.m_pNetWorkReveiver;
    }

    public String getSoPath() {
        return m_strSoPath;
    }

    public am getWebView() {
        return this.m_pLayaWebView;
    }

    public boolean isOpenNetwork() {
        if (!config.GetInstance().m_bCheckNetwork) {
            return true;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) this.mCtx.getSystemService("connectivity");
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isAvailable() && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public void onActivityResult(int i, int i2, Intent intent) {
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.onActivityResult(i, i2, intent);
        }
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public void onCreate() {
        LayaPlatformInterface layaPlatformInterface;
        Log.e(TAG, ">>>>>>>conchjar android-2.0.8");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PushConsts.ACTION_BROADCAST_NETWORK_CHANGE);
        try {
            this.mCtx.registerReceiver(this.m_pNetWorkReveiver, intentFilter);
        } catch (Exception unused) {
            Log.i("0", "registerReceiver error m_pNetWorkReveiver=" + this.m_pNetWorkReveiver);
        }
        Log.e(TAG, "plugin-----------------onCreate() ");
        ProcessInfo.init((ActivityManager) this.mCtx.getSystemService(TTDownloadField.TT_ACTIVITY));
        config.GetInstance().init(getClass().getResourceAsStream("/assets/config.ini"));
        boolean z = this.m_bIsPlug;
        MarketOnCreate();
        if (this.m_bIsPlug) {
            if (!ConchJNI.initNativeLibrary(getSoPath() + m_strSoFile, true)) {
                throw new RuntimeException("Failed to load native runtime library");
            }
        } else if (m_strSoPath.length() > 0) {
            if (!ConchJNI.initNativeLibrary(getSoPath() + m_strSoFile, true)) {
                throw new RuntimeException("Failed to load native runtime library");
            }
        } else if (!ConchJNI.initNativeLibrary("layaair", false)) {
            throw new RuntimeException("Failed to load native runtime library");
        }
        ConchJNI.configSetIsPlug(this.m_bIsPlug);
        if (this.m_strUrl.length() > 0) {
            ConchJNI.configSetURL(this.m_strUrl);
        }
        ExportJavaFunction.m_nState = 0;
        ExportJavaFunction exportJavaFunctionGetInstance = ExportJavaFunction.GetInstance();
        exportJavaFunctionGetInstance.m_pEngine = this;
        exportJavaFunctionGetInstance.Init(this.mCtx);
        String string = getMarketBundle().getString(MARKET_MARKETNAME);
        if (string != null && string.length() > 1 && (layaPlatformInterface = this.m_pPlatform) != null) {
            layaPlatformInterface.LP_Init(this.mCtx);
        } else {
            Log.e("0", "==============Java流程 没有第三方平台直接调用 PlatformInitOK");
            PlatformInitOK(0);
        }
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public void onDestroy() {
        ProcessInfo.uninit();
        try {
            this.mCtx.unregisterReceiver(this.m_pNetWorkReveiver);
        } catch (Exception unused) {
            Log.i("0", "unregisterReceiver error m_pNetWorkReveiver=" + this.m_pNetWorkReveiver);
        }
        delInstance();
        ConchJNI.ReleaseDLib();
        layaair.game.browser.a aVar = this.m_pCavans;
        if (aVar != null) {
            aVar.onPause();
        } else {
            Log.e("Canvas", ">>>>>onDestroy m_pCavans is null");
        }
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_OnDestroy();
        }
        LayaAudioMusic.uninit();
        destory();
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        Log.e("input", ">>>>>>>>>>>>>>>>" + i);
        if (keyEvent.getAction() == 0) {
            ConchJNI.handleKeyEvent(i, 0);
        } else if (keyEvent.getAction() == 1) {
            ConchJNI.handleKeyEvent(i, 1);
        }
        ExportJavaFunction exportJavaFunctionGetInstance = ExportJavaFunction.GetInstance();
        if (exportJavaFunctionGetInstance != null && exportJavaFunctionGetInstance.m_pEngine.getIsPlug()) {
            return false;
        }
        Log.e("", "exp is null");
        if (i == 4 && keyEvent.getAction() == 0) {
            Log.e("", "onKey = " + i);
            if (!ConchJNI.onBackPressed()) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                long j = this.m_nBackPressTime;
                if (j == 0 || (j > 0 && jCurrentTimeMillis - j > 3500)) {
                    this.m_nBackPressTime = System.currentTimeMillis();
                    if (isFinishing((Activity) this.mCtx)) {
                        return true;
                    }
                    Toast.makeText(this.mCtx, "再次点击后退键退出游戏!", 1).show();
                } else {
                    game_plugin_exitGame();
                }
                return false;
            }
        }
        return i == 4 && keyEvent.getAction() == 1;
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public void onKeyEvent(String str, int i) {
        if ("onKeyUp".equals(str)) {
            ConchJNI.handleKeyEvent(i, 1);
        } else if ("onKeyDown".equals(str)) {
            ConchJNI.handleKeyEvent(i, 0);
        }
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public void onNewIntent(Intent intent) {
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.onNewIntent(intent);
        }
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public void onPause() {
        if (this.mBIsSensor) {
            unRegisterSensor();
        }
        layaair.game.browser.a aVar = this.m_pCavans;
        if (aVar != null) {
            aVar.onPause();
        } else {
            Log.e("Canvas", ">>>>>onPause m_pCavans is null");
        }
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_onPause("");
        }
        ConchJNI.OnAppPause();
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public void onRestart() {
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_onRestart(this.mCtx);
        }
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public void onResume() {
        if (this.mBIsSensor) {
            registerSensor();
        }
        layaair.game.browser.a aVar = this.m_pCavans;
        if (aVar != null) {
            aVar.onResume();
        } else {
            Log.e("Canvas", ">>>>>onResume m_pCavans is null");
        }
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_onResume(this.mCtx);
        }
        ConchJNI.OnAppResume();
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public void onStop() {
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_onStop(this.mCtx);
        }
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public void setAppCacheDir(String str) {
        this.m_strCachePath = str;
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public void setAssetInfo(AssetManager assetManager) {
        game_conch3_setAssetInfo(assetManager);
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public void setContext(Context context) {
        if (context == null) {
            return;
        }
        this.mCtx = context;
        this.m_pDevID = new DevID(context);
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public void setDownloadThreadNum(int i) {
        this.m_nDownloadThreadNum = i;
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public void setExpansionZipDir(String str, String str2) {
        this.m_strExpansionMainPath = str;
        this.m_strExpansionPatchPath = str2;
    }

    public void setGameFocus() {
        if (this.m_interceptKey) {
            Log.i("layaConch3", "m_interceptKey OK!");
            AbsoluteLayout absoluteLayout = this.m_pAbsLayout;
            if (absoluteLayout == null) {
                return;
            }
            absoluteLayout.setFocusable(true);
            this.m_pAbsLayout.setFocusableInTouchMode(true);
            this.m_pAbsLayout.requestFocus();
            this.m_pAbsLayout.setOnKeyListener(this);
        }
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public void setGameUrl(String str) {
        this.m_strUrl = str;
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public void setInterceptKey(boolean z) {
        this.m_interceptKey = z;
        setGameFocus();
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public void setIsPlugin(boolean z) {
        game_conch3_SetIsPlug(z);
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public void setJarFile(String str) {
        m_strJarFile = str;
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public void setLayaEventListener(ILayaEventListener iLayaEventListener) {
        this.m_layaEventListener = iLayaEventListener;
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public void setLocalizable(boolean z) {
        this.localizable = z;
    }

    public void setParamExt(String str) {
        this.m_strExt = str;
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public void setResolution(int i, int i2) {
        Log.i("fix", "setResolution " + i + com.igexin.push.core.b.aj + i2);
        layaair.game.browser.a aVar = this.m_pCavans;
        if (aVar != null) {
            aVar.a(i, i2);
        } else {
            this.canvas_res_w = i;
            this.canvas_res_h = i2;
        }
    }

    public void setRuntimeExt() {
        if (this.m_strExt.length() > 0) {
            ConchJNI.configSetParamExt(this.m_strExt);
        }
    }

    public void setScreenOrientation(int i) {
    }

    public void setScreenWakeLock(boolean z) {
        try {
            Log.i("0", ">>>>>>screenWakeLock wake=" + z);
            Activity activity = (Activity) this.mCtx;
            if (activity == null) {
                return;
            }
            if (z) {
                Log.i("0", ">>>>>>screenWakeLock ok" + z);
                activity.getWindow().addFlags(128);
                return;
            }
            Log.i("0", ">>>>>>screenWakeLock ok" + z);
            activity.getWindow().clearFlags(128);
        } catch (Exception unused) {
            Log.i("0", ">>>>>>screenWakeLock error");
        }
    }

    public void setSensorAble(boolean z) {
        if (this.mBIsSensor != z) {
            this.mBIsSensor = z;
            if (this.mBIsSensor) {
                registerSensor();
            } else {
                unRegisterSensor();
            }
        }
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public void setSoFile(String str) {
        m_strSoFile = str;
    }

    @Override // layaair.game.conch.ILayaGameEgine
    public void setSoPath(String str) {
        m_strSoPath = str;
    }

    public void showMessage(String str) {
        Activity activity = (Activity) this.mCtx;
        if (isFinishing(activity)) {
            return;
        }
        Toast.makeText(activity, str, 1).show();
    }
}
