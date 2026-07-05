package layaair.game.PlatformInterface;

import android.util.Log;
import layaair.game.conch.LayaConch5;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class LayaPlatformGlue {
    private static LayaPlatformGlue m_spLayaPlatformGlue = null;
    private static String sNotSupport = "{\"result\":-50,\"desc\":\"not support\"}";
    private LayaPlatformInterface m_pPlatform = null;

    public static void DelInstance() {
        m_spLayaPlatformGlue = null;
    }

    public static LayaPlatformGlue GetInstance() {
        if (m_spLayaPlatformGlue == null) {
            m_spLayaPlatformGlue = new LayaPlatformGlue();
        }
        return m_spLayaPlatformGlue;
    }

    public void Init(LayaPlatformInterface layaPlatformInterface) {
        this.m_pPlatform = layaPlatformInterface;
    }

    public void authorize(String str) {
        Log.i("0", ">>>>>authorize = " + str);
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_authorize(str);
        } else {
            LayaPlatformCallback.GetInstance().LP_onAuthorizeCallback(sNotSupport);
        }
    }

    public void buyProps(String str) {
        Log.i("0", ">>>>>buyProps = " + str);
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_buyProps(str);
        }
    }

    public int canSendToDesktop(String str) {
        Log.i("0", ">>>>>canSendToDesktop");
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            return layaPlatformInterface.LP_canSendToDesktop(str);
        }
        return 1;
    }

    public void enterAccountMgr(String str) {
        Log.i("0", ">>>>>enterAccountMgr");
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_enterAccountMgr(str);
        }
    }

    public void enterBBS(String str) {
        Log.i("0", ">>>>>enterBBS");
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_enterBBS(str);
        } else {
            LayaPlatformCallback.GetInstance().LP_EnterBBSCallback(sNotSupport);
        }
    }

    public void enterFeedback(String str) {
        Log.i("0", ">>>>>enterFeedback");
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_enterFeedback(str);
        }
    }

    public void enterPlatform(String str) {
        Log.i("0", ">>>>>enterPlatform");
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_EnterPlatform(str);
        } else {
            LayaPlatformCallback.GetInstance().LP_EnterPlatformCallback(sNotSupport);
        }
    }

    public void getAvailableLoginType(String str) {
        Log.i("0", ">>>>>getAvailableLoginType p_sParam=" + str);
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_getAvailableLoginType(str);
        } else {
            LayaPlatformCallback.GetInstance().LP_GetAvailableLoginTypeCallback(sNotSupport);
        }
    }

    public int getChargeType() {
        Log.i("0", ">>>>>getChargeType");
        return LayaConch5.getMarketBundle().getInt(LayaConch5.MARKET_CHARGETYPE);
    }

    public int getEnterPlatformType() {
        Log.i("0", ">>>>>getEnterPlatformType");
        return LayaConch5.getMarketBundle().getInt(LayaConch5.MARKET_ENTERPLATFORMTYPE);
    }

    public void getGameFriends(String str) {
        Log.i("0", ">>>>>getGameFriends = " + str);
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_getGameFriends(str);
        } else {
            LayaPlatformCallback.GetInstance().LP_onGetGameFriendsCallback(sNotSupport);
        }
    }

    public int getLoginType() {
        Log.i("0", ">>>>>getLoginType");
        return LayaConch5.getMarketBundle().getInt(LayaConch5.MARKET_LOGINTYPE);
    }

    public String getMarketName() {
        Log.i("0", ">>>>>getMarketName");
        String string = LayaConch5.getMarketBundle().getString(LayaConch5.MARKET_MARKETNAME);
        return string == null ? "" : string;
    }

    public String getMarketValue(String str) {
        Log.i("0", ">>>>>getMarketValue key=" + str);
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        return layaPlatformInterface != null ? layaPlatformInterface.LP_getMarketValue(str) : "";
    }

    public int getPayType() {
        Log.i("0", ">>>>>getPayType");
        return LayaConch5.getMarketBundle().getInt(LayaConch5.MARKET_PAYTYPE);
    }

    public String getServerName() {
        Log.i("0", ">>>>>getServerName");
        String string = LayaConch5.getMarketBundle().getString(LayaConch5.MARKET_SERVERNAME);
        return string == null ? "" : string;
    }

    public void getUserInfo(String str) {
        Log.i("0", ">>>>>getUserInfo p_sParam=" + str);
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_getUserInfo(str);
        } else {
            LayaPlatformCallback.GetInstance().LP_GetUserInfoCallback(sNotSupport);
        }
    }

    public void invite(String str) {
        Log.i("0", ">>>>>invite = " + str);
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_enterInvite(str);
        }
    }

    public void login(String str) {
        Log.i("0", ">>>>>login");
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_Login(str);
        } else {
            LayaPlatformCallback.GetInstance().LP_LoginCallback(sNotSupport);
        }
    }

    public void logout(String str) {
        Log.i("0", ">>>>>logout");
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_Logout(str);
        } else {
            LayaPlatformCallback.GetInstance().LP_onLogoutCallback(sNotSupport);
        }
    }

    public void onGameEvent(String str) {
        Log.i("0", ">>>>>onGameEvent");
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_onGameEvent(str);
        }
    }

    public void openTopicCircle(String str) {
        Log.i("0", ">>>>>openTopicCircle p_sParam=" + str);
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_openTopicCircle(str);
        } else {
            LayaPlatformCallback.GetInstance().LP_onTopicCircleCallback(sNotSupport);
        }
    }

    public void recharge(String str) {
        Log.i("0", ">>>>>uniPayForCoin");
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_Recharge(str);
        } else {
            LayaPlatformCallback.GetInstance().LP_onRechargeCallback(sNotSupport);
        }
    }

    public void refreshToken(String str) {
        Log.i("0", ">>>>>refreshToken = " + str);
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_RefreshToken(str);
        } else {
            LayaPlatformCallback.GetInstance().LP_onRefreshTokenCallback(sNotSupport);
        }
    }

    public void sendMessageToPlatform(String str) {
        Log.i("0", ">>>>>sendMessageToPlatform p_sParam=" + str);
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_sendMessageToPlatform(str);
        }
    }

    public void sendToDesktop(String str) {
        Log.i("0", ">>>>>sendToDesktop extinfo=" + str);
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_sendToDesktop(str);
        } else {
            LayaPlatformCallback.GetInstance().LP_onSendToDesktopCallback(sNotSupport);
        }
    }

    public void setMarketValue(String str, String str2) {
        Log.i("0", ">>>>>setMarketValue key=" + str + "value=" + str2);
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_setMarketValue(str, str2);
        }
    }

    public void setRechargeInfo(String str) {
        Log.i("0", ">>>>>setRechargeInfo param=" + str);
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_setRechargeInfo(str);
        }
    }

    public void shareAndFeed(String str) {
        Log.i("0", ">>>>>shareAndFeed param = " + str);
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_enterShareAndFeed(str);
        } else {
            LayaPlatformCallback.GetInstance().LP_ShareAndFeedCallback(sNotSupport);
        }
    }

    public void switchUser(String str) {
        Log.i("0", ">>>>>switchUser");
        LayaPlatformInterface layaPlatformInterface = this.m_pPlatform;
        if (layaPlatformInterface != null) {
            layaPlatformInterface.LP_SwitchUser(str);
        } else {
            LayaPlatformCallback.GetInstance().LP_SwitchUserCallback(sNotSupport);
        }
    }
}
