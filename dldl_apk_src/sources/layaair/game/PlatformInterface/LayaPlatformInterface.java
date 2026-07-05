package layaair.game.PlatformInterface;

import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface LayaPlatformInterface {
    void LP_EnterPlatform(String str);

    void LP_Init(Context context);

    void LP_LeavePlatform(String str);

    void LP_Login(String str);

    void LP_Logout(String str);

    void LP_OnDestroy();

    void LP_Recharge(String str);

    void LP_RefreshToken(String str);

    void LP_SwitchUser(String str);

    void LP_authorize(String str);

    void LP_buyProps(String str);

    int LP_canSendToDesktop(String str);

    void LP_enterAccountMgr(String str);

    void LP_enterBBS(String str);

    void LP_enterFeedback(String str);

    void LP_enterInvite(String str);

    void LP_enterShareAndFeed(String str);

    void LP_getAvailableLoginType(String str);

    void LP_getGameFriends(String str);

    String LP_getMarketValue(String str);

    void LP_getUserInfo(String str);

    void LP_onCreate(Context context);

    Boolean LP_onExit(String str);

    void LP_onGameEvent(String str);

    void LP_onPause(String str);

    void LP_onRestart(Context context);

    void LP_onResume(Context context);

    void LP_onStop(Context context);

    void LP_openTopicCircle(String str);

    void LP_sendMessageToPlatform(String str);

    void LP_sendToDesktop(String str);

    void LP_setMarketValue(String str, String str2);

    void LP_setRechargeInfo(String str);

    void onActivityResult(int i, int i2, Intent intent);

    void onNewIntent(Intent intent);
}
