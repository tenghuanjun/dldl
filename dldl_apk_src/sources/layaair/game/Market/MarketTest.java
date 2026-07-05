package layaair.game.Market;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.webkit.ValueCallback;
import com.mobile.auth.gatewayauth.Constant;
import com.tencent.open.SocialConstants;
import layaair.game.PlatformInterface.LayaPlatformCallback;
import layaair.game.PlatformInterface.LayaPlatformGlue;
import layaair.game.PlatformInterface.LayaPlatformInterface;
import layaair.game.conch.LayaConch5;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class MarketTest implements LayaPlatformInterface {
    private Context context;
    private final String TAG = "MarketTest";
    public Handler m_Handler = new Handler();
    public String mUserId = "";

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_EnterPlatform(String str) {
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_LeavePlatform(String str) {
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_OnDestroy() {
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_RefreshToken(String str) {
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_SwitchUser(String str) {
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_authorize(String str) {
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_buyProps(String str) {
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public int LP_canSendToDesktop(String str) {
        return 0;
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_enterAccountMgr(String str) {
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_enterFeedback(String str) {
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_enterInvite(String str) {
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_getAvailableLoginType(String str) {
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public String LP_getMarketValue(String str) {
        return null;
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_getUserInfo(String str) {
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_onCreate(Context context) {
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public Boolean LP_onExit(String str) {
        return null;
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_onGameEvent(String str) {
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_onPause(String str) {
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_onRestart(Context context) {
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_onResume(Context context) {
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_onStop(Context context) {
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_openTopicCircle(String str) {
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_setMarketValue(String str, String str2) {
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_setRechargeInfo(String str) {
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void onNewIntent(Intent intent) {
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_Init(Context context) {
        Log.d("MarketTest", ">>>>>>>>>>>>>>>>MarketLaya init ok");
        this.context = context;
        LayaPlatformCallback.GetInstance().LP_InitCallback(0);
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_Login(String str) {
        Log.d("MarketTest", ">>>>>>>>>>>>> plugin LP_Login param = " + str);
        GameEngine.getInstance().getRuntimeProxy().Login(new JSONObject(), new ValueCallback<JSONObject>() { // from class: layaair.game.Market.MarketTest.1
            @Override // android.webkit.ValueCallback
            public void onReceiveValue(JSONObject jSONObject) {
                Log.d("MarketTest", ">>>>>>>>>>>>>>> plugin LP_Login onReceiveValue = " + jSONObject.toString());
                JSONObject jSONObject2 = new JSONObject();
                if (jSONObject.has("unionUserID")) {
                    try {
                        int i = jSONObject.getInt("status");
                        String string = jSONObject.getString("unionUserID");
                        String string2 = jSONObject.getString("nickName");
                        String string3 = jSONObject.getString("photo");
                        String string4 = jSONObject.getString("sptoken");
                        String string5 = jSONObject.getString("msg");
                        String marketName = LayaPlatformGlue.GetInstance().getMarketName();
                        jSONObject2.put("nickName", string2);
                        jSONObject2.put("refreshToken", string4);
                        jSONObject2.put("unionUserId", string);
                        jSONObject2.put("sessionKey", string4);
                        jSONObject2.put("avtorUrl", string3);
                        jSONObject2.put(LayaConch5.MARKET_MARKETNAME, marketName);
                        jSONObject2.put("result", i);
                        jSONObject2.put(SocialConstants.PARAM_APP_DESC, string5);
                        LayaPlatformCallback.GetInstance().LP_LoginCallback(jSONObject2.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_Logout(String str) {
        try {
            Log.d("MarketTest", ">>>>>LP_Logout jsonParam= " + str);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("userId", this.mUserId);
            Log.d("MarketTest", ">>>>>>>>>LP_Logout obj=" + jSONObject.toString());
            GameEngine.getInstance().getRuntimeProxy().Logout(jSONObject, new ValueCallback<JSONObject>() { // from class: layaair.game.Market.MarketTest.2
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(JSONObject jSONObject2) {
                    try {
                        String string = jSONObject2.getString("msg");
                        int i = Integer.parseInt(jSONObject2.getString("status"));
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("result", i);
                        jSONObject3.put(SocialConstants.PARAM_APP_DESC, string);
                        Log.d("MarketTest", ">>>>>>>>>LP_Logout callback" + jSONObject3.toString());
                        LayaPlatformCallback.GetInstance().LP_onLogoutCallback(jSONObject3.toString());
                    } catch (JSONException e) {
                        Log.d("MarketTest", ">>>>>>LP_Logout callback error" + e);
                        JSONObject jSONObject4 = new JSONObject();
                        try {
                            jSONObject4.put("result", -1);
                            jSONObject4.put(SocialConstants.PARAM_APP_DESC, "");
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                        LayaPlatformCallback.GetInstance().LP_onLogoutCallback(jSONObject4.toString());
                    }
                }
            });
        } catch (JSONException e) {
            Log.d("MarketTest", ">>>>>+++LP_Logout: " + e.toString());
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("result", -1);
                jSONObject2.put(SocialConstants.PARAM_APP_DESC, "");
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            LayaPlatformCallback.GetInstance().LP_onLogoutCallback(jSONObject2.toString());
        }
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_Recharge(String str) {
        Log.d("MarketTest", ">>>>>>>>>>>>> plugin LP_Recharge param = " + str);
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject(str);
            jSONObject.put("orderId", jSONObject2.get("orderId"));
            jSONObject.put("payAmount", "" + jSONObject2.get("amount"));
            jSONObject.put("goodsName", jSONObject2.get("goodName"));
            jSONObject.put("userId", jSONObject2.get("customer_name"));
            jSONObject.put("gameId", jSONObject2.get("appid"));
            JSONObject jSONObject3 = new JSONObject(jSONObject2.getString("other"));
            String string = jSONObject3.getString("goodsId");
            jSONObject.put("callbackUrl", jSONObject3.getString("notifyUrl"));
            jSONObject.put("goodId", string);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("MarketTest", ">>>>>>>>>>>>> plugin LP_Recharge param = " + jSONObject.toString());
        GameEngine.getInstance().getRuntimeProxy().Pay(jSONObject, new ValueCallback<JSONObject>() { // from class: layaair.game.Market.MarketTest.3
            @Override // android.webkit.ValueCallback
            public void onReceiveValue(JSONObject jSONObject4) {
                Log.d("MarketTest", ">>>>>>>>>>>>> plugin LP_Recharge onReceiveValue = " + jSONObject4.toString());
                try {
                    int i = jSONObject4.getInt("status");
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put("result", i);
                    jSONObject5.put(SocialConstants.PARAM_APP_DESC, jSONObject4.getString("msg"));
                    LayaPlatformCallback.GetInstance().LP_onRechargeCallback(jSONObject5.toString());
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_enterBBS(String str) {
        Log.d("MarketTest", ">>>>>>>LP_enterBBS param =" + str);
        try {
            GameEngine.getInstance().getRuntimeProxy().OpenBBS(new JSONObject(str), new ValueCallback<JSONObject>() { // from class: layaair.game.Market.MarketTest.4
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(JSONObject jSONObject) {
                    Log.d("MarketTest", ">>>>>>>LP_enterBBS onReceiveValue value=" + jSONObject.toString());
                    try {
                        int i = Integer.parseInt(jSONObject.getString("status"));
                        String string = jSONObject.getString("msg");
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("result", i);
                        jSONObject2.put(SocialConstants.PARAM_APP_DESC, string);
                        LayaPlatformCallback.GetInstance().LP_EnterBBSCallback(jSONObject2.toString());
                    } catch (JSONException e) {
                        Log.d("MarketTest", ">>>>>>>LP_enterBBS error=" + e);
                        JSONObject jSONObject3 = new JSONObject();
                        try {
                            jSONObject3.put("result", -1);
                            jSONObject3.put(SocialConstants.PARAM_APP_DESC, e.toString());
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                        LayaPlatformCallback.GetInstance().LP_EnterBBSCallback(jSONObject3.toString());
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_enterShareAndFeed(String str) {
        Log.d("MarketTest", "LP_enterShareAndFeed jsonParam = " + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("gameUrl", jSONObject.getString("link"));
            jSONObject2.put("gameName", jSONObject.getString("title"));
            jSONObject2.put("gameIcon", jSONObject.getString("imgsrc"));
            jSONObject2.put("isFullScreen", true);
            jSONObject2.put(SocialConstants.PARAM_APP_DESC, jSONObject.getString(SocialConstants.PARAM_APP_DESC));
            GameEngine.getInstance().getRuntimeProxy().Share(jSONObject2, new ValueCallback<JSONObject>() { // from class: layaair.game.Market.MarketTest.5
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(JSONObject jSONObject3) {
                    Log.d("MarketTest", "LP_enterShareAndFeed receiveValue = " + jSONObject3);
                    try {
                        int i = Integer.parseInt(jSONObject3.getString("status"));
                        String string = jSONObject3.getString("msg");
                        JSONObject jSONObject4 = new JSONObject();
                        jSONObject4.put("code", i);
                        jSONObject4.put(SocialConstants.PARAM_APP_DESC, string);
                        LayaPlatformCallback.GetInstance().LP_ShareAndFeedCallback(jSONObject4.toString());
                    } catch (JSONException e) {
                        Log.d("MarketTest", ">>>>>>>LP_enterShareAndFeed error=" + e);
                        JSONObject jSONObject5 = new JSONObject();
                        try {
                            jSONObject5.put("result", -1);
                            jSONObject5.put(SocialConstants.PARAM_APP_DESC, e.toString());
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                        LayaPlatformCallback.GetInstance().LP_ShareAndFeedCallback(jSONObject5.toString());
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_getGameFriends(String str) {
        Log.d("MarketTest", ">>>>>>>>LP_getGameFriends = " + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("gameId", jSONObject.getString("appid"));
            jSONObject2.put("userId", this.mUserId);
            jSONObject2.put("accessToken", "");
            GameEngine.getInstance().getRuntimeProxy().GetFriendsList(jSONObject2, new ValueCallback<JSONObject>() { // from class: layaair.game.Market.MarketTest.6
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(JSONObject jSONObject3) {
                    try {
                        int i = Integer.parseInt(jSONObject3.getString("status"));
                        String string = jSONObject3.getString("msg");
                        String string2 = jSONObject3.getJSONArray("friends").toString();
                        Log.d("MarketTest", ">>>>>>>>LP_getGameFriends callback msg=" + string + ",result=" + i + ",sFriendsList=" + string2);
                        JSONObject jSONObject4 = new JSONObject();
                        jSONObject4.put("result", i);
                        jSONObject4.put(SocialConstants.PARAM_APP_DESC, string);
                        jSONObject4.put("friendslist", string2);
                        LayaPlatformCallback.GetInstance().LP_onGetGameFriendsCallback(jSONObject4.toString());
                    } catch (JSONException e) {
                        Log.d("MarketTest", ">>>>LP_getGameFriends error =" + e);
                        JSONObject jSONObject5 = new JSONObject();
                        try {
                            jSONObject5.put("result", -1);
                            jSONObject5.put(SocialConstants.PARAM_APP_DESC, e.toString());
                            jSONObject5.put("friendslist", "");
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                        LayaPlatformCallback.GetInstance().LP_onGetGameFriendsCallback(jSONObject5.toString());
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_sendMessageToPlatform(String str) {
        try {
            GameEngine.getInstance().getRuntimeProxy().SendMessageToPlatform(new JSONObject(str), new ValueCallback<JSONObject>() { // from class: layaair.game.Market.MarketTest.7
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(JSONObject jSONObject) {
                    LayaPlatformCallback.GetInstance().LP_SendMessageToPlatformCallback(jSONObject.toString());
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // layaair.game.PlatformInterface.LayaPlatformInterface
    public void LP_sendToDesktop(String str) {
        Log.d("MarketTest", "LP_sendToDesktop jsonParam = " + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("gameUrl", jSONObject.getString("pageUrl"));
            jSONObject2.put("gameName", jSONObject.getString("title"));
            jSONObject2.put("gameIcon", jSONObject.getString("imageUrl"));
            jSONObject2.put("isFullScreen", true);
            jSONObject2.put(Constant.PROTOCOL_WEB_VIEW_ORIENTATION, jSONObject.has(Constant.PROTOCOL_WEB_VIEW_ORIENTATION) ? jSONObject.getString(Constant.PROTOCOL_WEB_VIEW_ORIENTATION) : "landscape");
            GameEngine.getInstance().getRuntimeProxy().PushIcon(jSONObject2, new ValueCallback<JSONObject>() { // from class: layaair.game.Market.MarketTest.8
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(JSONObject jSONObject3) {
                    Log.d("MarketTest", "LP_sendToDesktop receiveValue = " + jSONObject3);
                    try {
                        int i = Integer.parseInt(jSONObject3.getString("status"));
                        String string = jSONObject3.getString("msg");
                        Log.d("MarketTest", ">>>>>>>LP_sendToDesktop callback msg" + string + ",result=" + i);
                        JSONObject jSONObject4 = new JSONObject();
                        jSONObject4.put("result", i);
                        jSONObject4.put(SocialConstants.PARAM_APP_DESC, string);
                        LayaPlatformCallback.GetInstance().LP_onSendToDesktopCallback(jSONObject4.toString());
                    } catch (JSONException e) {
                        Log.d("MarketTest", ">>>>LP_sendToDesktop error =" + e);
                        JSONObject jSONObject5 = new JSONObject();
                        try {
                            jSONObject5.put("result", -1);
                            jSONObject5.put(SocialConstants.PARAM_APP_DESC, e.toString());
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                        LayaPlatformCallback.GetInstance().LP_onSendToDesktopCallback(jSONObject5.toString());
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
