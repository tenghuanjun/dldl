package com.kwai.monitor.log;

import a.a.a.b.c;
import a.a.a.b.e;
import a.a.a.d.a;
import a.a.a.g.b;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class TurboAgent {
    public static final String TAG = "TurboAgent";

    public static void init(TurboConfig turboConfig) {
        e.c.b.a.a(turboConfig);
    }

    public static void on14dayStay() {
        e.c.b.a.a(new a("EVENT_14_DAY_STAY"));
    }

    public static void on24hStay() {
        e.c.b.a.a(new a("EVENT_24h_STAY"));
    }

    public static void on2dayStay() {
        e.c.b.a.a(new a("EVENT_2_DAY_STAY"));
    }

    public static void on30dayStay() {
        e.c.b.a.a(new a("EVENT_30_DAY_STAY"));
    }

    public static void on3dayStay() {
        e.c.b.a.a(new a("EVENT_3_DAY_STAY"));
    }

    public static void on4dayStay() {
        e.c.b.a.a(new a("EVENT_4_DAY_STAY"));
    }

    public static void on5dayStay() {
        e.c.b.a.a(new a("EVENT_5_DAY_STAY"));
    }

    public static void on6dayStay() {
        e.c.b.a.a(new a("EVENT_6_DAY_STAY"));
    }

    public static void onAddShoppingCart(double d) {
        a aVar = new a("EVENT_ADD_SHOPPINGCART");
        aVar.b = d;
        e.c.b.a.a(aVar);
    }

    public static void onAffairFinish() {
        e.c.b.a.a(new a("EVENT_JINJIAN"));
    }

    public static void onAppActive() {
        e.c.b.a.a(new a("EVENT_ACTIVE"));
    }

    public static void onCreditGrant() {
        e.c.b.a.a(new a("EVENT_CREDIT_GRANT"));
    }

    public static void onFormSubmit() {
        e.c.b.a.a(new a("EVENT_FORM_SUBMIT"));
    }

    public static void onGameConsumption(double d) {
        a aVar = new a("EVENT_GAME_CONSUMPTION");
        aVar.b = d;
        e.c.b.a.a(aVar);
    }

    public static void onGameCreateRole(String str) {
        a aVar = new a("EVENT_GAME_CREATE_ROLE");
        aVar.d = str;
        e.c.b.a.a(aVar);
    }

    public static void onGameUpgradeRole(int i) {
        a aVar = new a("EVENT_GAME_UPGRADE_ROLE");
        aVar.e = i;
        e.c.b.a.a(aVar);
    }

    public static void onGameWatchRewardVideo() {
        e.c.b.a.a(new a("EVENT_GAME_WATCH_REWARD_VIDEO"));
    }

    public static void onGoodsView(double d) {
        a aVar = new a("EVENT_GOODS_VIEW");
        aVar.b = d;
        e.c.b.a.a(aVar);
    }

    public static void onNextDayStay() {
        e.c.b.a.a(new a("EVENT_NEXTDAY_STAY"));
    }

    public static void onOrderPayed(double d) {
        a aVar = new a("EVENT_ORDER_PAIED");
        aVar.b = d;
        e.c.b.a.a(aVar);
    }

    public static void onOrderSubmit(double d) {
        a aVar = new a("EVENT_ORDER_SUBMIT");
        aVar.b = d;
        e.c.b.a.a(aVar);
    }

    public static void onPagePause() {
        if (c.b <= 0) {
            b.a("GameDurationHelper", "GAME_RESUME_LAST_TIME <=0");
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - c.b;
        if (jCurrentTimeMillis < 3000) {
            b.a("GameDurationHelper", "ignore this time while duration is less than 3s");
            return;
        }
        a aVar = new a("EVENT_GAME_REPORT_DURATION");
        aVar.c = jCurrentTimeMillis;
        e.c.b.a.a(aVar);
    }

    public static void onPageResume(Activity activity) {
        c.b(activity);
    }

    public static void onPassKeyGameCard() {
        e.c.b.a.a(new a("EVENT_PASS_KEY_GAME_CARD"));
    }

    public static void onPay(double d) {
        a aVar = new a("EVENT_PAY");
        aVar.b = d;
        e.c.b.a.a(aVar);
    }

    public static void onRegister() {
        e.c.b.a.a(new a("EVENT_REGISTER"));
    }

    public static void onVipLevelUp(int i) {
        a aVar = new a("EVENT_VIP_LEVEL_UP");
        aVar.f = i;
        e.c.b.a.a(aVar);
    }

    public static void onWatchAppAd() {
        e.c.b.a.a(new a("EVENT_WATCH_APP_AD"));
    }

    public static void onWeekStay() {
        e.c.b.a.a(new a("EVENT_WEEK_STAY"));
    }

    public static void registerOAIDListener(Context context, OAIDListener oAIDListener) {
        if (context == null || oAIDListener == null) {
            b.b(TAG, "registerOAIDListener context or oaidListener is null");
            return;
        }
        Context applicationContext = context.getApplicationContext();
        a.a.a.f.a.b = oAIDListener;
        if (TextUtils.isEmpty(a.a.a.f.a.a)) {
            a.a.a.f.a.b(applicationContext);
            return;
        }
        try {
            if (a.a.a.f.a.b != null) {
                a.a.a.f.a.b.OnOAIDValid(a.a.a.f.a.a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void reportGameServer(ServerType serverType) {
        a aVar = new a("EVENT_SERVER_CONNECTION");
        if (serverType == ServerType.DedicatedServer) {
            aVar.h = 1;
        } else if (serverType == ServerType.MixtureServer) {
            aVar.h = 2;
        }
        e.c.b.a.a(aVar);
    }

    public static void reportInsufficientGoldCoins() {
        e.c.b.a.a(new a("EVENT_INSUFFICIENT_GOLD_COINS"));
    }

    public static void reportKeyPathOptimization(String str) {
        a aVar = new a("EVENT_KEY_PATH_OPTIMIZATION");
        aVar.i = str;
        e.c.b.a.a(aVar);
    }

    public static void reportMainCityUpgrade(int i) {
        a aVar = new a("EVENT_MAIN_CITY_UPGRADE");
        aVar.e = i;
        e.c.b.a.a(aVar);
    }

    public static void reportUsersIndependentNaming() {
        e.c.b.a.a(new a("EVENT_USERS_INDEPENDENT_NAMING"));
    }

    public static void reportUsersReceiveGiftBags() {
        e.c.b.a.a(new a("EVENT_USERS_RECEIVE_GIFT_BAGS"));
    }

    public static void reportUsersReceiveRedEnvelopes() {
        e.c.b.a.a(new a("EVENT_USERS_RECEIVE_RED_ENVELOPES"));
    }

    public static void unRegisterOAIDListener() {
        a.a.a.f.a.b = null;
    }

    public static void onPageResume() {
        c.a();
    }

    public static void onPagePause(Activity activity) {
        c.a(activity);
    }
}
