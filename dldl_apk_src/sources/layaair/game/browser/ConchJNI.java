package layaair.game.browser;

import android.content.res.AssetManager;
import layaair.game.conch.LayaConch5;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class ConchJNI {
    public static boolean g_bInitialized;

    public static native void InitDLib(AssetManager assetManager, int i, String str, String str2, String str3, String str4);

    public static native void InitDownLoadManager(int i, String str, AssetManager assetManager, String str2);

    public static native void OnAppDestroy();

    public static native void OnAppPause();

    public static native void OnAppResume();

    public static native void OnGLReady(int i, int i2);

    public static native void ReleaseDLib();

    public static native void RunJS(String str);

    public static native void SetLocalStoragePath(String str);

    public static native void alertCallback();

    public static native void audioMusicPlayEnd();

    public static native void authorizeCallback(String str);

    public static native void callConchJSFunction(String str, String str2, String str3);

    public static native void captureScreenCallBack(int i, int i2, byte[] bArr);

    public static native void closeExternalWebView();

    public static native void configSetIsPlug(boolean z);

    public static native void configSetParamExt(String str);

    public static native void configSetURL(String str);

    public static native void editBoxOnInput(String str);

    public static native void exportObjectToC(String str, Object obj);

    public static native void exportStaticMethodToC(String str);

    public static native void handleDeviceMotionEvent(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10);

    public static native void handleDeviceOrientationEvent(float f, float f2, float f3);

    public static native void handleJoystickEvent(float f, float f2, float f3, float f4, float f5, float f6);

    public static native void handleKeyEvent(int i, int i2);

    public static native void handleTouch(int i, int i2, int i3, int i4);

    public static boolean initNativeLibrary(String str, boolean z) {
        try {
            if (z) {
                System.load(str);
            } else {
                System.loadLibrary(str);
            }
            setLocalizable(LayaConch5.GetInstance().localizable);
            g_bInitialized = true;
        } catch (Exception e) {
            e.printStackTrace();
            g_bInitialized = false;
        }
        return g_bInitialized;
    }

    public static native void inputChange(int i);

    public static native void loginCallback(String str);

    public static native void networkChanged(int i);

    public static native boolean onBackPressed();

    public static native void onBuyPropsCallback(String str);

    public static native void onDrawFrame();

    public static native void onEnterAccountMgrCallback(String str);

    public static native void onEnterBBSCallback(String str);

    public static native void onEnterFeedbackCallback(String str);

    public static native void onEnterPlatformCallback(String str);

    public static native void onGetAvailableLoginTypeCallback(String str);

    public static native void onGetGameFriends(String str);

    public static native void onGetUserInfoCallback(String str);

    public static native void onInviteCallback(String str);

    public static native void onLogout(String str);

    public static native void onMarketInit(String str);

    public static native void onRunCmd(int i, int i2, int i3);

    public static native void onSendMessageToPlatformCallback(String str);

    public static native void onSendToDesktop(String str);

    public static native void onSensorChanged(float f);

    public static native void onSetRechargeInfoCallback(String str);

    public static native void onShareAndFeed(String str);

    public static native void onSwitchUserCallback(String str);

    public static native void onTopicCircle(String str);

    public static native void onVSyncCallback(long j);

    public static native void postMsgToRuntime(String str, String str2);

    public static native void rechargeEvent(String str);

    public static native void refreshTokenCallback(String str);

    public static native void reloadJS();

    public static native void setLocalizable(boolean z);
}
