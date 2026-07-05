package layaair.game.PlatformInterface;

import layaair.game.browser.ConchJNI;
import layaair.game.conch.LayaConch5;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class LayaPlatformCallback {
    private static LayaPlatformCallback sLPCallback;

    public static LayaPlatformCallback GetInstance() {
        if (sLPCallback == null) {
            sLPCallback = new LayaPlatformCallback();
        }
        return sLPCallback;
    }

    public void LP_BuyPropsCallback(String str) {
        ConchJNI.onBuyPropsCallback(str);
    }

    public void LP_EnterAccountMgrCallback(String str) {
        ConchJNI.onEnterAccountMgrCallback(str);
    }

    public void LP_EnterBBSCallback(String str) {
        ConchJNI.onEnterBBSCallback(str);
    }

    public void LP_EnterFeedbackCallback(String str) {
        ConchJNI.onEnterFeedbackCallback(str);
    }

    public void LP_EnterPlatformCallback(String str) {
        ConchJNI.onEnterPlatformCallback(str);
    }

    public void LP_GetAvailableLoginTypeCallback(String str) {
        ConchJNI.onGetAvailableLoginTypeCallback(str);
    }

    public void LP_GetUserInfoCallback(String str) {
        ConchJNI.onGetUserInfoCallback(str);
    }

    public void LP_InitCallback(int i) {
        LayaConch5.GetInstance().PlatformInitOK(i);
    }

    public void LP_InviteCallback(String str) {
        ConchJNI.onInviteCallback(str);
    }

    public void LP_LoginCallback(String str) {
        ConchJNI.loginCallback(str);
    }

    public void LP_SendMessageToPlatformCallback(String str) {
        ConchJNI.onSendMessageToPlatformCallback(str);
    }

    public void LP_SetRechargeInfoCallback(String str) {
        ConchJNI.onSetRechargeInfoCallback(str);
    }

    public void LP_ShareAndFeedCallback(String str) {
        ConchJNI.onShareAndFeed(str);
    }

    public void LP_SwitchUserCallback(String str) {
        ConchJNI.onSwitchUserCallback(str);
    }

    public void LP_onAuthorizeCallback(String str) {
        ConchJNI.authorizeCallback(str);
    }

    public void LP_onGetGameFriendsCallback(String str) {
        ConchJNI.onGetGameFriends(str);
    }

    public void LP_onLogoutCallback(String str) {
        ConchJNI.onLogout(str);
    }

    public void LP_onMarketInitCallback(String str) {
        ConchJNI.onMarketInit(str);
    }

    public void LP_onRechargeCallback(String str) {
        ConchJNI.rechargeEvent(str);
    }

    public void LP_onRefreshTokenCallback(String str) {
        ConchJNI.refreshTokenCallback(str);
    }

    public void LP_onSendToDesktopCallback(String str) {
        ConchJNI.onSendToDesktop(str);
    }

    public void LP_onTopicCircleCallback(String str) {
        ConchJNI.onTopicCircle(str);
    }
}
