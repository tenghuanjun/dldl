package com.sy37sdk.account.floatview;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorEvent;
import android.text.TextUtils;
import android.view.View;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tools.Logger;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.UrlUtils;
import com.sqwan.common.util.ViewUtils;
import com.sqwan.common.util.task.Task;
import com.sqwan.msdk.config.ConfigManager;
import com.sy37sdk.account.AccountRequestManager;
import com.sy37sdk.account.floatview.CommonFloatMenuLayout;
import com.sy37sdk.account.floatview.FloatViewManager;
import com.sy37sdk.account.floatview.FloatWindow;
import com.sy37sdk.account.floatview.ScreenOrientationHelper;
import com.sy37sdk.account.floatview.ShakeSensorHelper;
import com.sy37sdk.account.floatview.SqBaseFloatView;
import com.sy37sdk.account.floatview.data.RedDot;
import com.sy37sdk.account.floatview.redpacket.RedPacketInfo;
import com.sy37sdk.account.floatview.ui.MenuUIConfig;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SqFloatViewManager implements FloatWindow.DragBottom2DeleteCallback, ShakeSensorHelper.SensorChangedCallback {
    private static SqFloatViewManager sInstance;
    public CommonFloatConfig config;
    public SqBaseFloatView floatView;
    private boolean isShowFloatView;
    private CommonFloatMenuLayout layout;
    private Task taskShowFloatView = Task.create();
    private Task taskSensorChanged = Task.create();
    private String TAG = "SqFloatViewManager";

    private SqFloatViewManager() {
    }

    public static SqFloatViewManager getInstance() {
        if (sInstance == null) {
            sInstance = new SqFloatViewManager();
        }
        return sInstance;
    }

    public void showFloatView(final Activity activity) {
        CommonFloatConfig commonFloatConfig;
        Logger.info("activity的类名为: %s", activity.getClass().getName());
        ScreenOrientationHelper.initOrAdd(activity, new ScreenOrientationHelper.ScreenOrientationChangeListener() { // from class: com.sy37sdk.account.floatview.SqFloatViewManager.1
            @Override // com.sy37sdk.account.floatview.ScreenOrientationHelper.ScreenOrientationChangeListener
            public void onChange(final int i) {
                if (SqFloatViewManager.this.floatView != null) {
                    SqFloatViewManager.this.taskShowFloatView.oneShot(500L, new Task.TaskFunc() { // from class: com.sy37sdk.account.floatview.SqFloatViewManager.1.1
                        @Override // com.sqwan.common.util.task.Task.TaskFunc
                        public Task.Result exec() {
                            LogUtil.i(SqFloatViewManager.this.TAG, "onChange orientation " + i);
                            FloatViewUtils.resetFloatViewPos(activity, SqFloatViewManager.this.floatView);
                            if (SqFloatViewManager.this.floatView == null) {
                                return null;
                            }
                            SqFloatViewManager.this.floatView.resetView(true);
                            return null;
                        }
                    });
                }
            }
        });
        if (this.isShowFloatView || (commonFloatConfig = this.config) == null || !commonFloatConfig.isShow) {
            return;
        }
        this.isShowFloatView = true;
        HashMap map = new HashMap();
        map.put(SqTrackKey.ball_id, this.config.id);
        map.put(SqTrackKey.ball_name, this.config.name);
        map.put(SqTrackKey.ball_url, this.config.iconUrl);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.ball_show, map);
        SqBaseFloatView.Builder builder = new SqBaseFloatView.Builder(activity);
        Logger.info("显示普通悬浮窗", new Object[0]);
        if (this.layout == null) {
            this.layout = new CommonFloatMenuLayout(activity);
        }
        this.layout.setConfigs(this.config.menuConfigs);
        this.layout.setUserCenter(this.config.user_center);
        this.layout.setUIConfig(new MenuUIConfig(this.config.background_url, this.config.nickname_color, this.config.user_center_color, this.config.title_color));
        this.layout.setOnMenuItemClickListener(new CommonFloatMenuLayout.OnMenuItemClickListener() { // from class: com.sy37sdk.account.floatview.SqFloatViewManager.2
            @Override // com.sy37sdk.account.floatview.CommonFloatMenuLayout.OnMenuItemClickListener
            public void onMenuItemClick(MenuConfig menuConfig) {
                SqFloatViewManager.this.floatView.handleMenuConfigClick(menuConfig);
            }
        });
        this.layout.setUpdateRedCallback(new CommonFloatMenuLayout.UpdateRedDotCallback() { // from class: com.sy37sdk.account.floatview.SqFloatViewManager.3
            @Override // com.sy37sdk.account.floatview.CommonFloatMenuLayout.UpdateRedDotCallback
            public void onUpdateRedDotRemind() {
                SqFloatViewManager sqFloatViewManager = SqFloatViewManager.this;
                sqFloatViewManager.requestFloatRemind(sqFloatViewManager.config.menuConfigs);
            }
        });
        SqBaseFloatView sqBaseFloatViewBuild = builder.setMenuLayout(this.layout).setFloatIconUrl(this.config.iconUrl).setRedPacketInfo(null).setOnDragBottom2DeleteCallback(this).build();
        this.floatView = sqBaseFloatViewBuild;
        sqBaseFloatViewBuild.setClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.floatview.SqFloatViewManager.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ConfigManager.getInstance(activity).isSqSDK()) {
                    FloatViewDataManager.getInstance().requestPtUserInfo(new SqHttpCallback.SimpleSqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.floatview.SqFloatViewManager.4.1
                        @Override // com.sq.tool.network.SqHttpCallback
                        public void onSuccess(JSONObject jSONObject) {
                            SqFloatViewManager.this.layout.setPersonInfo();
                        }
                    });
                }
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.ball_access, new HashMap<String, String>() { // from class: com.sy37sdk.account.floatview.SqFloatViewManager.4.2
                    {
                        put(SqTrackKey.ball_id, SqFloatViewManager.this.config.id);
                        put(SqTrackKey.ball_name, SqFloatViewManager.this.config.name);
                        put(SqTrackKey.ball_url, SqFloatViewManager.this.config.iconUrl);
                        put(SqTrackKey.ball_red_dot, SqFloatViewManager.this.floatViewNeedRedDot() + "");
                    }
                });
            }
        });
        this.floatView.setMenuListener(new SqBaseFloatView.MenuListener() { // from class: com.sy37sdk.account.floatview.SqFloatViewManager.5
            @Override // com.sy37sdk.account.floatview.SqBaseFloatView.MenuListener
            public void onMenuShow() {
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.ball_entry_show, new HashMap<String, String>() { // from class: com.sy37sdk.account.floatview.SqFloatViewManager.5.1
                    {
                        put(SqTrackKey.ball_id, SqFloatViewManager.this.config.id);
                    }
                });
                List<MenuConfig> list = SqFloatViewManager.this.config.menuConfigs;
                if (list == null || list.size() <= 0) {
                    return;
                }
                for (MenuConfig menuConfig : list) {
                    HashMap map2 = new HashMap();
                    map2.put(SqTrackKey.ball_id, menuConfig.id);
                    map2.put(SqTrackKey.ball_name, menuConfig.title);
                    map2.put(SqTrackKey.ball_url, menuConfig.openUrl);
                    SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.ball_btn_show, map2);
                }
            }
        });
        this.floatView.setDismissListener(new SqBaseFloatView.DismissListener() { // from class: com.sy37sdk.account.floatview.SqFloatViewManager.6
            @Override // com.sy37sdk.account.floatview.SqBaseFloatView.DismissListener
            public void onDismiss() {
            }
        });
        this.floatView.show(false);
        onPauseShakeSensorHelper();
        if (this.config.needRedDot()) {
            requestFloatWindowRedPoint(this.config.menuConfigs, activity);
        }
    }

    public void showFloatMenu() {
        SqBaseFloatView sqBaseFloatView = this.floatView;
        if (sqBaseFloatView != null) {
            sqBaseFloatView.expandMenu();
        }
    }

    public void dismissFloatView() {
        if (this.isShowFloatView) {
            SqBaseFloatView sqBaseFloatView = this.floatView;
            if (sqBaseFloatView != null) {
                sqBaseFloatView.dismiss();
            }
            this.isShowFloatView = false;
        }
        this.taskSensorChanged.stop();
        this.taskShowFloatView.stop();
        onPauseShakeSensorHelper();
    }

    public void requestFloatWindowConfig(final Context context) {
        dismissFloatView();
        new AccountRequestManager(context).floatWindow(new SqHttpCallback.SimpleSqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.floatview.SqFloatViewManager.7
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                SqFloatViewManager.this.config = new CommonFloatConfig(jSONObject);
                if (SqFloatViewManager.this.config.isShow) {
                    SqFloatViewManager.this.showFloatView((Activity) context);
                }
            }
        });
    }

    /* JADX INFO: renamed from: com.sy37sdk.account.floatview.SqFloatViewManager$8, reason: invalid class name */
    class AnonymousClass8 implements FloatViewManager.OnRecInfListener {
        AnonymousClass8() {
        }

        @Override // com.sy37sdk.account.floatview.FloatViewManager.OnRecInfListener
        public void onMsgInf(final MenuConfig menuConfig) {
            SqFloatViewManager.this.showFloatItemRedDot();
            if (SqFloatViewManager.this.floatView == null || !(SqFloatViewManager.this.floatView.getContext() instanceof Activity)) {
                return;
            }
            ((Activity) SqFloatViewManager.this.floatView.getContext()).runOnUiThread(new Runnable() { // from class: com.sy37sdk.account.floatview.-$$Lambda$SqFloatViewManager$8$JcdrCvcXIVpvDI0p64ZlEP2Ir-4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onMsgInf$0$SqFloatViewManager$8(menuConfig);
                }
            });
        }

        public /* synthetic */ void lambda$onMsgInf$0$SqFloatViewManager$8(MenuConfig menuConfig) {
            SqFloatViewManager.this.handleFloatViewMsgRemind(menuConfig);
        }
    }

    public void bindRedDot() {
        FloatViewManager.getInstance().bindFloatSocket(new AnonymousClass8());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleFloatViewMsgRemind(MenuConfig menuConfig) {
        SqBaseFloatView sqBaseFloatView = this.floatView;
        if (sqBaseFloatView != null) {
            MenuConfig showingConfig = sqBaseFloatView.getShowingConfig();
            if (showingConfig != null) {
                if (menuConfig.priority > showingConfig.priority) {
                    showFloatViewRemind(menuConfig);
                    return;
                }
                return;
            }
            showFloatViewRemind(menuConfig);
        }
    }

    public void requestFloatWindowRedPoint(final List<MenuConfig> list, Context context) {
        StringBuilder sb = new StringBuilder();
        String valueFromUrlStrByParamName = "";
        if (list != null && list.size() > 0) {
            for (MenuConfig menuConfig : list) {
                if (menuConfig.needRedDot()) {
                    sb.append(menuConfig.title);
                    sb.append(",");
                    if (TextUtils.isEmpty(valueFromUrlStrByParamName)) {
                        valueFromUrlStrByParamName = UrlUtils.readValueFromUrlStrByParamName(menuConfig.openUrl, "page_uuid");
                    }
                }
            }
        }
        if (sb.length() <= 0) {
            Logger.info("不需要显示红点", new Object[0]);
        } else {
            new AccountRequestManager(context).floatRedPoint(valueFromUrlStrByParamName, sb.substring(0, sb.length() - 1), new SqHttpCallback.SimpleSqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.floatview.SqFloatViewManager.9
                @Override // com.sq.tool.network.SqHttpCallback
                public void onSuccess(JSONObject jSONObject) {
                    try {
                        FloatViewDataManager.getInstance().saveRedDot(RedDot.getRedDots(jSONObject.optString("res_data")));
                        SqFloatViewManager.this.showFloatItemRedDot();
                        SqFloatViewManager.this.requestFloatRemind(list);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void showFloatItemRedDot() {
        CommonFloatMenuLayout commonFloatMenuLayout = this.layout;
        if (commonFloatMenuLayout != null) {
            commonFloatMenuLayout.refreshRedDot(FloatViewDataManager.getInstance().getRedDotCache());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestFloatRemind(List<MenuConfig> list) {
        StringBuilder sb = new StringBuilder();
        String valueFromUrlStrByParamName = "";
        if (list != null && list.size() > 0) {
            for (MenuConfig menuConfig : list) {
                if (menuConfig.needRedDot()) {
                    sb.append(menuConfig.title);
                    sb.append(",");
                    if (TextUtils.isEmpty(valueFromUrlStrByParamName)) {
                        valueFromUrlStrByParamName = UrlUtils.readValueFromUrlStrByParamName(menuConfig.openUrl, "page_uuid");
                    }
                }
            }
        }
        if (sb.length() <= 0) {
            Logger.info("不需要显示红点", new Object[0]);
        } else {
            if (this.floatView == null || !floatViewNeedRedDot()) {
                return;
            }
            new AccountRequestManager(this.floatView.getContext()).getFloatWarning(valueFromUrlStrByParamName, sb.substring(0, sb.length() - 1), new SqHttpCallback.SimpleSqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.floatview.SqFloatViewManager.10
                @Override // com.sq.tool.network.SqHttpCallback
                public void onSuccess(JSONObject jSONObject) {
                    SqFloatViewManager.this.showFloatViewRemind(new MenuConfig(jSONObject));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFloatViewRemind(MenuConfig menuConfig) {
        MenuConfig showingConfig = this.floatView.getShowingConfig();
        if (showingConfig != null && TextUtils.equals(showingConfig.warningMsg, menuConfig.warningMsg) && TextUtils.equals(menuConfig.warningType, showingConfig.warningType) && menuConfig.priority == showingConfig.priority) {
            return;
        }
        this.floatView.setShowingConfig(menuConfig);
        if (menuConfig != null) {
            if (!TextUtils.isEmpty(menuConfig.warningType)) {
                if (!menuConfig.warningType.contains("1")) {
                    this.floatView.showRedDot(false);
                    this.floatView.showRemindAnim(menuConfig);
                } else {
                    this.floatView.removeBubble();
                    this.floatView.refreshRedDot();
                    this.floatView.showRedDot(true);
                }
                this.floatView.checkShake(menuConfig);
                return;
            }
            this.floatView.showRedDot(false);
            this.floatView.removeBubble();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean floatViewNeedRedDot() {
        Iterator<RedDot> it = FloatViewDataManager.getInstance().getRedDotCache().iterator();
        while (it.hasNext()) {
            if (it.next().getNum() > 0) {
                return true;
            }
        }
        return false;
    }

    public void redDotCalled(String str, String str2, Context context) {
        new AccountRequestManager(context).floatRedCalled(str, str2, null);
    }

    public boolean isShowFloat() {
        CommonFloatConfig commonFloatConfig = this.config;
        return commonFloatConfig != null && commonFloatConfig.isShow;
    }

    public void showRedPacketFloat(Activity activity, RedPacketInfo redPacketInfo, boolean z) {
        LogUtil.d(this.TAG, "showRedPacketFloat:" + redPacketInfo);
        dismissFloatView();
        if (this.isShowFloatView) {
            return;
        }
        this.isShowFloatView = true;
        SqBaseFloatView sqBaseFloatViewBuild = new SqBaseFloatView.Builder(activity).setRedPacketInfo(redPacketInfo).setOnDragBottom2DeleteCallback(this).build();
        this.floatView = sqBaseFloatViewBuild;
        sqBaseFloatViewBuild.show(!z);
        onPauseShakeSensorHelper();
    }

    public void showRedPacktFloatView() {
        SqBaseFloatView sqBaseFloatView = this.floatView;
        if (sqBaseFloatView != null) {
            ViewUtils.show(sqBaseFloatView);
        }
    }

    public void onResume() {
        ScreenOrientationHelper.register();
        onResumeShakeSensorHelper();
    }

    public void onPause() {
        ScreenOrientationHelper.unRegister();
        onPauseShakeSensorHelper();
    }

    private void onPauseShakeSensorHelper() {
        SqBaseFloatView sqBaseFloatView = this.floatView;
        if (sqBaseFloatView != null) {
            ShakeSensorHelper.getInstance(sqBaseFloatView.getContext()).onPause();
        }
    }

    private void onResumeShakeSensorHelper() {
        SqBaseFloatView sqBaseFloatView = this.floatView;
        if (sqBaseFloatView == null || sqBaseFloatView.getWindowVisibility() != 8) {
            return;
        }
        Context context = this.floatView.getContext();
        ShakeSensorHelper.getInstance(context).onResume();
        ShakeSensorHelper.getInstance(context).addSensorChangedCallback(this);
    }

    @Override // com.sy37sdk.account.floatview.FloatWindow.DragBottom2DeleteCallback
    public void onDragBottom2Delete(boolean z, boolean z2, boolean z3) {
        LogUtil.d("onDragBottom2Delete toDelete=" + z + " isShowBottomView" + z2 + " higilight" + z3);
        if (z) {
            SqBaseFloatView sqBaseFloatView = this.floatView;
            if (sqBaseFloatView == null || sqBaseFloatView.mBottomDeleteView == null) {
                return;
            }
            this.floatView.setWindowVisibility(8);
            this.floatView.dismissMenu();
            ViewUtils.gone(this.floatView.mBottomDeleteView);
            onResumeShakeSensorHelper();
            Context context = this.floatView.getContext();
            if (FloatViewUtils.isShakeTips(context)) {
                new Shake2FloatTipsDialog(context).show();
            }
            HashMap map = new HashMap();
            map.put(SqTrackKey.ball_id, this.config.id);
            map.put(SqTrackKey.ball_name, this.config.name);
            map.put(SqTrackKey.ball_url, this.config.iconUrl);
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.FLOAT_BALL_HIDE, map);
            return;
        }
        SqBaseFloatView sqBaseFloatView2 = this.floatView;
        if (sqBaseFloatView2 == null || sqBaseFloatView2.mBottomDeleteView == null) {
            return;
        }
        if (z2) {
            this.floatView.mBottomDeleteView.showBottomDeleteView();
        } else {
            this.floatView.mBottomDeleteView.hideBottomDeleteView();
        }
        this.floatView.mBottomDeleteView.setStatus(z3);
    }

    @Override // com.sy37sdk.account.floatview.ShakeSensorHelper.SensorChangedCallback
    public void onSensorChanged(Context context, SensorEvent sensorEvent) {
        LogUtil.d("onSensorChanged");
        SqBaseFloatView sqBaseFloatView = this.floatView;
        if (sqBaseFloatView != null && sqBaseFloatView.mBottomDeleteView != null) {
            this.floatView.setWindowVisibility(0);
            this.floatView.resetView(false);
            HashMap map = new HashMap();
            map.put(SqTrackKey.ball_id, this.config.id);
            map.put(SqTrackKey.ball_name, this.config.name);
            map.put(SqTrackKey.ball_url, this.config.iconUrl);
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.ball_show, map);
        }
        onPauseShakeSensorHelper();
    }
}
