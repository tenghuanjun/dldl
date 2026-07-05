package com.sqwan.m;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.demo.base.ScreenCaptureUtils;
import com.demo.base.ShareAdapter;
import com.parameters.share.ShareImageInfo;
import com.parameters.share.ShareMessage;
import com.parameters.share.ShareTextInfo;
import com.parameters.share.ShareWebInfo;
import com.sqwan.common.dialog.PlatformAnnouncementActivity;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.account.IBindWxListener;
import com.sqwan.common.mod.download.IDownloadMod;
import com.sqwan.common.util.ApkInfoUtil;
import com.sqwan.common.util.AsyncImageLoader;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.StatusBarUtil;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.util.task.Task;
import com.sqwan.liveshow.common.LiveShowParamsKey;
import com.sqwan.msdk.BaseSQwanCore;
import com.sqwan.msdk.SQwanCore;
import com.sqwan.msdk.api.SQAppConfig;
import com.sqwan.msdk.api.SQPushTransmitMessageListener;
import com.sqwan.msdk.api.SQResultListener;
import com.sqwan.msdk.api.tool.IScreenshotListener;
import com.sy.yxjun.R;
import com.sy37sdk.account.alifast.FastLoginConstants;
import com.sy37sdk.account.floatview.FloatViewUtils;
import com.sy37sdk.account.util.SocialAccountUtil;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;
import com.tencent.bugly.Bugly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import notchtools.geek.com.notchtools.core.NotchProperty;
import notchtools.geek.com.notchtools.core.OnNotchCallBack;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class MainActivity extends Activity implements View.OnClickListener, OnNotchCallBack {
    public static final Handler HANDLER = new Handler(Looper.getMainLooper());
    private SQAppConfig config;
    private EditText etUrl;
    private List<String> imgs;
    private ImageView ivAgeAppropriate;
    private ListView lvShare;
    private OrientationEventListener mOrientationListener;
    private Bitmap mScreenCaptureBitmap;
    private CheckBox previewBox;
    float screenBrightness;
    private ShareAdapter shareAdapter;
    private EditText shareDesEdt;
    private EditText shareEdt;
    private EditText shareTitleEdt;
    private EditText trackEventEdt;
    private LinearLayout trackLayout;
    String TAG = getClass().getSimpleName();
    private String appkey = "";
    private List<EditText> keyEdts = new ArrayList();
    private List<EditText> valueEdts = new ArrayList();
    private Task screenBrightnessTask = Task.create();
    private Object mLock = new Object();
    private Handler mHandler = new Handler();
    private final SQResultListener mShareListener = new SQResultListener() { // from class: com.sqwan.m.MainActivity.32
        public void onSuccess(Bundle bundle) {
            Toast.makeText(MainActivity.this, "分享成功", 1).show();
            Log.i(MainActivity.this.TAG, "分享回调成功onSuccess: ");
            LogUtil.i("分享onSuccess");
        }

        public void onFailture(int i, String str) {
            Toast.makeText(MainActivity.this, "分享失败", 1).show();
            Log.i(MainActivity.this.TAG, "分享回调失败onFailture: " + str);
            LogUtil.i("分享onFailture");
        }
    };

    private void notchConfigOnCreate() {
    }

    private void notchConfigOnWindowFocusChanged(boolean z) {
    }

    @Override // android.app.Activity
    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        StatusBarUtil.hideSystemUI(getWindow());
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.sqwan.m.MainActivity.1
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public void onSystemUiVisibilityChange(int i) {
                StatusBarUtil.hideSystemUI(MainActivity.this.getWindow());
                LogUtil.i(MainActivity.this.TAG, "onSystemUiVisibilityChange isUiFlagHideNavigation:" + FloatViewUtils.isUiFlagHideNavigation(MainActivity.this));
            }
        });
        this.screenBrightness = getWindow().getAttributes().screenBrightness;
        Log.d(this.TAG, "screenBrightness: " + this.screenBrightness);
        handleScrrenBrightnessTask();
    }

    private void handleScrrenBrightnessTask() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.screenBrightness = this.screenBrightness;
        getWindow().setAttributes(attributes);
        this.screenBrightnessTask.oneShot(OAIDHelper.TIMEOUT, new Runnable() { // from class: com.sqwan.m.MainActivity.2
            @Override // java.lang.Runnable
            public void run() {
                WindowManager.LayoutParams attributes2 = MainActivity.this.getWindow().getAttributes();
                attributes2.screenBrightness = 0.1f;
                MainActivity.this.getWindow().setAttributes(attributes2);
            }
        });
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.appkey = AppkeyHelper.getAppkey(this);
        LogUtil.d("onCreate");
        String strChooseByX86andArm = ApkInfoUtil.chooseByX86andArm(this);
        LogUtil.i(this.TAG, "cpu_architect " + strChooseByX86andArm);
        super.onCreate(bundle);
        setContentView(2131296395);
        getWindow().getDecorView().setSystemUiVisibility(1028);
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            getWindow().setAttributes(attributes);
        }
        Uri data = getIntent().getData();
        if (data != null) {
            LogUtil.i("从浏览器启动收到的数据:" + data.getQuery());
        }
        findViewById(2131165655).setOnClickListener(this);
        findViewById(2131165309).setOnClickListener(this);
        findViewById(2131165718).setOnClickListener(this);
        findViewById(2131165719).setOnClickListener(this);
        findViewById(2131165664).setOnClickListener(this);
        findViewById(2131165867).setOnClickListener(this);
        findViewById(2131165341).setOnClickListener(this);
        findViewById(2131165342).setOnClickListener(this);
        findViewById(2131165903).setOnClickListener(this);
        findViewById(2131166177).setOnClickListener(this);
        findViewById(2131165904).setOnClickListener(this);
        findViewById(2131165441).setOnClickListener(this);
        findViewById(2131166227).setOnClickListener(this);
        findViewById(2131165870).setOnClickListener(this);
        findViewById(2131165869).setOnClickListener(this);
        findViewById(2131165289).setOnClickListener(this);
        findViewById(2131165852).setOnClickListener(this);
        findViewById(2131165286).setOnClickListener(this);
        findViewById(2131165287).setOnClickListener(this);
        findViewById(2131165288).setOnClickListener(this);
        findViewById(2131165856).setOnClickListener(this);
        findViewById(2131166228).setOnClickListener(this);
        findViewById(2131165218).setOnClickListener(this);
        findViewById(2131165219).setOnClickListener(this);
        findViewById(2131165256).setOnClickListener(this);
        findViewById(2131165257).setOnClickListener(this);
        findViewById(2131165335).setOnClickListener(this);
        findViewById(2131165339).setOnClickListener(this);
        findViewById(2131165272).setOnClickListener(this);
        findViewById(2131165872).setOnClickListener(this);
        findViewById(2131165829).setOnClickListener(this);
        findViewById(2131165876).setOnClickListener(this);
        findViewById(2131165873).setOnClickListener(this);
        findViewById(2131165877).setOnClickListener(this);
        findViewById(2131165875).setOnClickListener(this);
        findViewById(2131165440).setOnClickListener(this);
        findViewById(2131165874).setOnClickListener(this);
        findViewById(2131165971).setOnClickListener(this);
        this.etUrl = (EditText) findViewById(2131165382);
        this.trackLayout = (LinearLayout) findViewById(2131165338);
        this.trackEventEdt = (EditText) findViewById(2131165336);
        this.ivAgeAppropriate = (ImageView) findViewById(2131165480);
        this.shareEdt = (EditText) findViewById(2131165858);
        this.shareDesEdt = (EditText) findViewById(2131165857);
        this.shareTitleEdt = (EditText) findViewById(2131165859);
        this.previewBox = (CheckBox) findViewById(2131165853);
        this.lvShare = (ListView) findViewById(2131165668);
        ArrayList arrayList = new ArrayList();
        this.imgs = arrayList;
        arrayList.add("1608811521825");
        ShareAdapter shareAdapter = new ShareAdapter(this, this.imgs);
        this.shareAdapter = shareAdapter;
        this.lvShare.setAdapter((ListAdapter) shareAdapter);
        this.shareAdapter.setOnClickShareListener(new ShareAdapter.OnClickShareListener() { // from class: com.sqwan.m.MainActivity.3
            @Override // com.demo.base.ShareAdapter.OnClickShareListener
            public void clickShare(String str) {
                SQwanCore.getInstance().share("78278", str, MainActivity.this.mShareListener);
            }

            @Override // com.demo.base.ShareAdapter.OnClickShareListener
            public void clickDelete(int i) {
                MainActivity.this.imgs.remove(i);
                MainActivity.this.shareAdapter.setData(MainActivity.this.imgs);
            }
        });
        HANDLER.postDelayed(new Runnable() { // from class: com.sqwan.m.-$$Lambda$MainActivity$4RzIfo5Ugu7R6OTpm0bsQWmvlUg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.setSdkInit();
            }
        }, 1000L);
        SQwanCore.getInstance().setSwitchAccountListener(new SQResultListener() { // from class: com.sqwan.m.MainActivity.4
            public void onSuccess(Bundle bundle2) {
                Toast.makeText(MainActivity.this, "悬浮窗切换账号成功：" + bundle2, 1).show();
            }

            public void onFailture(int i, String str) {
                Toast.makeText(MainActivity.this, "悬浮窗切换账号失败:\n msg=" + str, 1).show();
            }
        });
        SQwanCore.getInstance().setBackToGameLoginListener(new SQResultListener() { // from class: com.sqwan.m.MainActivity.5
            public void onFailture(int i, String str) {
            }

            public void onSuccess(Bundle bundle2) {
                Toast.makeText(MainActivity.this, "重置游戏状态，回到游戏的登录界面，用户需要重新登录", 1).show();
            }
        });
        SQwanCore.getInstance().onJoinRoomListener(this, new SQResultListener() { // from class: com.sqwan.m.MainActivity.6
            public void onSuccess(Bundle bundle2) {
                Toast.makeText(MainActivity.this, "进入房间成功:\n roomname:" + bundle2.getString("roomname") + "\n memberid:" + bundle2.getString("memberid") + "\n anchorname:" + bundle2.getString("anchorname") + "\n timeout:" + bundle2.getString("timeout"), 1).show();
            }

            public void onFailture(int i, String str) {
                Toast.makeText(MainActivity.this, "进入房间失败:\n msg=" + str, 1).show();
            }
        });
        SQwanCore.getInstance().onQuitRoomListener(this, new SQResultListener() { // from class: com.sqwan.m.MainActivity.7
            public void onSuccess(Bundle bundle2) {
                Toast.makeText(MainActivity.this, "退出房间成功，cp在此进行相应处理-更新播放按钮ui", 1).show();
            }

            public void onFailture(int i, String str) {
                Toast.makeText(MainActivity.this, "退出房间失败:\n msg=" + str, 1).show();
            }
        });
        SQwanCore.getInstance().setScreenshotListener(new IScreenshotListener() { // from class: com.sqwan.m.MainActivity.8
            @Override // com.sqwan.msdk.api.tool.IScreenshotListener
            public Bitmap createScreenshot() {
                synchronized (MainActivity.this.mLock) {
                    MainActivity.this.testGameScreenCapture();
                    try {
                        MainActivity.this.mLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return MainActivity.this.mScreenCaptureBitmap;
            }
        });
        this.mOrientationListener = new OrientationEventListener(this, 3) { // from class: com.sqwan.m.MainActivity.9
            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int i) {
            }
        };
        _setAuthResultListener();
        notchConfigOnCreate();
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    /* JADX INFO: renamed from: com.sqwan.m.MainActivity$10, reason: invalid class name */
    class AnonymousClass10 implements SQResultListener {
        AnonymousClass10() {
        }

        public void onSuccess(Bundle bundle) {
            Toast.makeText(MainActivity.this, "初始化完成", 0).show();
            SQwanCore.getInstance().setSQPushTransmitMessageListener(new SQPushTransmitMessageListener() { // from class: com.sqwan.m.-$$Lambda$MainActivity$10$oFmmh0KLcjadGmbZfzKVMexQruY
                public final void onReceiveTransmitMessage(String str) {
                    this.f$0.lambda$onSuccess$0$MainActivity$10(str);
                }
            });
            if (bundle != null) {
                Log.i("MainActivity", "imei --> " + bundle.getString("imei") + ", isUpdate " + bundle.getBoolean("is_update") + ", updateType " + bundle.getString("update_type"));
            }
        }

        public /* synthetic */ void lambda$onSuccess$0$MainActivity$10(String str) {
            Toast.makeText(MainActivity.this, "收到推送: " + str, 1).show();
        }

        public void onFailture(int i, String str) {
            Toast.makeText(MainActivity.this, "初始化失败", 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSdkInit() {
        SQwanCore.getInstance().init(this, this.appkey, new AnonymousClass10());
    }

    public void _setAuthResultListener() {
        SQwanCore.getInstance().performFeature(this, "authResultCheck", (Object) null, new SQResultListener() { // from class: com.sqwan.m.MainActivity.11
            public void onSuccess(Bundle bundle) {
                LogUtil.i("AuthResult onSuccess");
                ToastUtil.showToast(MainActivity.this, "AuthResult onSuccess");
            }

            public void onFailture(int i, String str) {
                LogUtil.e("AuthResult onFailture");
                ToastUtil.showToast(MainActivity.this, "AuthResult onFailture");
            }
        });
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        SQwanCore.getInstance().onStart();
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        SQwanCore.getInstance().onRestart();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        SQwanCore.getInstance().onResume();
        this.mOrientationListener.enable();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        SQwanCore.getInstance().onPause();
        this.mOrientationListener.disable();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        SQwanCore.getInstance().onStop();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        SQwanCore.getInstance().onDestroy();
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.d("onActivityResult");
        SQwanCore.getInstance().onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        SQwanCore.getInstance().onNewIntent(intent);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == 2131165655) {
            SQwanCore.getInstance().login(this, new SQResultListener() { // from class: com.sqwan.m.MainActivity.12
                public void onSuccess(Bundle bundle) {
                    Log.i("sqsdk", "[Demo]登录成功: " + bundle);
                    Toast.makeText(MainActivity.this, "登录成功：" + bundle, 1).show();
                }

                public void onFailture(int i, String str) {
                    Toast.makeText(MainActivity.this, "登录失败回调：" + str, 1).show();
                }
            });
        } else if (id == 2131165309) {
            SQwanCore.getInstance().changeAccount(this, new SQResultListener() { // from class: com.sqwan.m.MainActivity.13
                public void onSuccess(Bundle bundle) {
                    Log.i("sqsdk", "[Demo]切换账号成功: " + bundle);
                    Toast.makeText(MainActivity.this, "主动切换账号成功：" + bundle, 1).show();
                }

                public void onFailture(int i, String str) {
                    Toast.makeText(MainActivity.this, str, 1).show();
                }
            });
        } else if (id == 2131165718) {
            EditText editText = (EditText) findViewById(2131165374);
            float f = TextUtils.isEmpty(editText.getText().toString()) ? 0.0f : Float.parseFloat(editText.getText().toString());
            SQwanCore.getInstance().pay(this, "A" + System.currentTimeMillis(), "一堆金币", "金币", "S001", "铁马金戈", "CP扩展字段", "RID0001", "路人甲", 1, f, 10, new SQResultListener() { // from class: com.sqwan.m.MainActivity.14
                public void onSuccess(Bundle bundle) {
                    Toast.makeText(MainActivity.this, "[Demo]成功发起充值请求(充值结果以服务端为准)", 1).show();
                }

                public void onFailture(int i, String str) {
                    Toast.makeText(MainActivity.this, "[Demo]" + str, 1).show();
                }
            });
        } else if (id == 2131165719) {
            SQwanCore.getInstance().pay(this, "A" + System.currentTimeMillis(), "一堆金币", "金币", "S001", "金戈铁马", "", "RID0001", "路人甲", 1, 0.0f, 10, new SQResultListener() { // from class: com.sqwan.m.MainActivity.15
                public void onSuccess(Bundle bundle) {
                    Toast.makeText(MainActivity.this, "成功发起充值请求(充值结果以服务端为准)", 1).show();
                }

                public void onFailture(int i, String str) {
                    Toast.makeText(MainActivity.this, str, 1).show();
                }
            });
        } else if (id == 2131165664) {
            Toast.makeText(this, "该接口已作废，不建议使用", 1).show();
        } else if (id == 2131165867) {
            SQwanCore.getInstance().showExitDailog(this, new SQResultListener() { // from class: com.sqwan.m.MainActivity.16
                public void onSuccess(Bundle bundle) {
                    MainActivity.this.leaveRadioRoom(null);
                    Toast.makeText(MainActivity.this, "登出完成，请处理游戏逻辑(例如清理资源、退出游戏等)", 1).show();
                    System.exit(0);
                }

                public void onFailture(int i, String str) {
                    Toast.makeText(MainActivity.this, "取消登出，则不做退出处理，继续游戏", 1).show();
                }
            });
        } else {
            if (id != 2131165341) {
                if (id == 2131165342) {
                    HashMap map = new HashMap();
                    map.put(BaseSQwanCore.INFO_SERVERID, "yourServerId");
                    map.put(BaseSQwanCore.INFO_SERVERNAME, "yourServerName");
                    map.put(BaseSQwanCore.INFO_ROLEID, (System.currentTimeMillis() / 1000) + "");
                    map.put(BaseSQwanCore.INFO_ROLENAME, "角色名");
                    map.put(BaseSQwanCore.INFO_ROLELEVEL, "yourRoleLevel");
                    map.put(BaseSQwanCore.INFO_BALANCE, "yourBalance");
                    map.put(BaseSQwanCore.INFO_PARTYNAME, "yourPartyName");
                    map.put(BaseSQwanCore.INFO_VIPLEVEL, "yourVipLevel");
                    map.put(BaseSQwanCore.INFO_ROLE_TIME_CREATE, "1458542706");
                    map.put(BaseSQwanCore.INFO_ROLE_TIME_LEVEL, "-1");
                    SQwanCore.getInstance().creatRoleInfo(map);
                    ToastUtil.showToast(map.toString());
                } else if (id == 2131165903) {
                    HashMap map2 = new HashMap();
                    map2.put(BaseSQwanCore.INFO_SERVERID, "99996");
                    map2.put(BaseSQwanCore.INFO_SERVERNAME, "yourServerName");
                    map2.put(BaseSQwanCore.INFO_SERVERTIME, (System.currentTimeMillis() / 1000) + "");
                    map2.put(BaseSQwanCore.INFO_ROLEID, (System.currentTimeMillis() / 1000) + "");
                    map2.put(BaseSQwanCore.INFO_ROLENAME, "夶夶夶" + System.currentTimeMillis());
                    map2.put(BaseSQwanCore.INFO_ROLELEVEL, "1");
                    map2.put(BaseSQwanCore.INFO_BALANCE, FastLoginConstants.Code.FAILURE_NOT_SUPPORT);
                    map2.put(BaseSQwanCore.INFO_PARTYNAME, "yourPartyName");
                    map2.put(BaseSQwanCore.INFO_VIPLEVEL, FastLoginConstants.Code.FAILURE_REQUEST_CONFIG);
                    map2.put(BaseSQwanCore.INFO_ROLE_TIME_CREATE, "1458542706");
                    map2.put(BaseSQwanCore.INFO_ROLE_TIME_LEVEL, "-1");
                    SQwanCore.getInstance().submitRoleInfo(map2);
                    Toast.makeText(getApplicationContext(), map2.toString(), 0).show();
                } else if (id == 2131166177) {
                    HashMap map3 = new HashMap();
                    map3.put(BaseSQwanCore.INFO_SERVERID, "yourServerId");
                    map3.put(BaseSQwanCore.INFO_SERVERNAME, "yourServerName");
                    map3.put(BaseSQwanCore.INFO_ROLEID, (System.currentTimeMillis() / 1000) + "");
                    map3.put(BaseSQwanCore.INFO_ROLENAME, "角色名");
                    map3.put(BaseSQwanCore.INFO_ROLELEVEL, "yourRoleLevel");
                    map3.put(BaseSQwanCore.INFO_BALANCE, "yourBalance");
                    map3.put(BaseSQwanCore.INFO_PARTYNAME, "yourPartyName");
                    map3.put(BaseSQwanCore.INFO_VIPLEVEL, "yourVipLevel");
                    map3.put(BaseSQwanCore.INFO_ROLE_TIME_CREATE, "1458542706");
                    map3.put(BaseSQwanCore.INFO_ROLE_TIME_LEVEL, "145345667");
                    SQwanCore.getInstance().upgradeRoleInfo(map3);
                    Toast.makeText(getApplicationContext(), map3.toString(), 0).show();
                } else {
                    if (id != 2131165904) {
                        if (id == 2131165441) {
                            SQAppConfig appConfig = SQwanCore.getInstance().getAppConfig();
                            this.config = appConfig;
                            Toast.makeText(this, "gid:" + appConfig.getGameid() + " \npid:" + this.config.getPartner() + "\nrefer:" + this.config.getRefer(), 1).show();
                            return;
                        }
                        if (id == 2131166227) {
                            Environment.getExternalStorageDirectory().getAbsolutePath();
                            SQwanCore.getInstance().shareToWX(this, "永恒纪元", "这里是介绍内容", "http://www.37.com.cn/dsf/", "http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=971de28cc1ef7609280691dc46b4c9b9/4a36acaf2edda3cce7305e310be93901203f92cf.jpg", 0, new SQResultListener() { // from class: com.sqwan.m.MainActivity.17
                                public void onSuccess(Bundle bundle) {
                                    System.out.println("  恭喜你分享成功 ");
                                }

                                public void onFailture(int i, String str) {
                                    System.out.println(str);
                                }
                            });
                            return;
                        }
                        if (id == 2131165870) {
                            SQwanCore.getInstance().showSQWebDialog("https://ai-agent-app.39on.com/voice");
                            return;
                        }
                        if (id == 2131165256) {
                            SQwanCore.getInstance().performFeature(this, "showWebDialog", "{ \"url\":\"http://37.com.cn/community/vertical/54/home?gid=1000000&pid=1&dev=c4c6f0a8e946b44a82f2b5e732a1d195&token=BASE64MTFkNmMxa0IwNDVRdG5LL1dudXhBNUc0bkpuaEJqWXV1SkZtR3FLL2cwMVVrZFYzMVJPNGJVK3I0TDdabVV4dCsvT2FjamF1T21JUTZZSmpJcGhQMndvVlcxUUoxcEd4WUFzU0prWjE1UDg3aHhKdEN5dXB6YTNHajBvQkh3Mkt3Y01YS1FXQ2dqNlpMSmpKd2t3YW1zdnZXcGRaRjd0Q1grdWhaYXA2TTdxMU1xVXhFSVFITFF5aDlDVjcySDJLWWFIelllaVNjVlFpbkExSDFFY2Uwb056S25J&sversion=3.7.5&refer=1_1000000_1234_1234&gwversion=1.5.4\",\"showToolBar\":true}", (SQResultListener) null);
                            return;
                        }
                        if (id == 2131165257) {
                            SQwanCore.getInstance().performFeature(this, "showWebDialog", "{ \"url\":\"http://37.com.cn/community/vertical/54/home?gid=1000000&pid=1&dev=c4c6f0a8e946b44a82f2b5e732a1d195&token=BASE64MTFkNmMxa0IwNDVRdG5LL1dudXhBNUc0bkpuaEJqWXV1SkZtR3FLL2cwMVVrZFYzMVJPNGJVK3I0TDdabVV4dCsvT2FjamF1T21JUTZZSmpJcGhQMndvVlcxUUoxcEd4WUFzU0prWjE1UDg3aHhKdEN5dXB6YTNHajBvQkh3Mkt3Y01YS1FXQ2dqNlpMSmpKd2t3YW1zdnZXcGRaRjd0Q1grdWhaYXA2TTdxMU1xVXhFSVFITFF5aDlDVjcySDJLWWFIelllaVNjVlFpbkExSDFFY2Uwb056S25J&sversion=3.7.5&refer=1_1000000_1234_1234&gwversion=1.5.6\",\"showToolBar\":false}", (SQResultListener) null);
                            return;
                        }
                        if (id == 2131165869) {
                            SQwanCore.getInstance().showSQPersonalDialog(this);
                            return;
                        }
                        if (id == 2131165852) {
                            ShareImageInfo shareImageInfo = new ShareImageInfo();
                            shareImageInfo.setBitmap(readBitmap(2131100341));
                            ShareMessage shareMessage = new ShareMessage();
                            shareMessage.setShareMessage(shareImageInfo);
                            shareMessage.setSkipPreview(!this.previewBox.isChecked());
                            shareMessage.setPlatform(1);
                            SQwanCore.getInstance().share(shareMessage, this.mShareListener);
                            return;
                        }
                        if (id == 2131165286) {
                            ShareImageInfo shareImageInfo2 = new ShareImageInfo();
                            shareImageInfo2.setBitmap(readBitmap(2131100340));
                            ShareMessage shareMessage2 = new ShareMessage();
                            shareMessage2.setShareMessage(shareImageInfo2);
                            shareMessage2.setSkipPreview(!this.previewBox.isChecked());
                            shareMessage2.setPlatform(4);
                            SQwanCore.getInstance().share(shareMessage2, this.mShareListener);
                            return;
                        }
                        if (id == 2131165287) {
                            ShareWebInfo shareWebInfo = new ShareWebInfo();
                            shareWebInfo.setThumbBmp(readBitmap(2131100340));
                            shareWebInfo.setTitle("测试标题");
                            shareWebInfo.setPageUrl("http://www.baidu.com");
                            ShareMessage shareMessage3 = new ShareMessage();
                            shareMessage3.setSkipPreview(true);
                            shareMessage3.setPlatform(4);
                            shareMessage3.setShareMessage(shareWebInfo);
                            SQwanCore.getInstance().share(shareMessage3, this.mShareListener);
                            return;
                        }
                        if (id == 2131165288) {
                            ShareTextInfo shareTextInfo = new ShareTextInfo();
                            shareTextInfo.setText("我是被分享的文本");
                            ShareMessage shareMessage4 = new ShareMessage();
                            shareMessage4.setSkipPreview(true);
                            shareMessage4.setPlatform(4);
                            shareMessage4.setShareMessage(shareTextInfo);
                            SQwanCore.getInstance().share(shareMessage4, this.mShareListener);
                            return;
                        }
                        if (id == 2131165856) {
                            String string = this.shareEdt.getText().toString();
                            if (TextUtils.isEmpty(string)) {
                                Toast.makeText(this, "请输入分享链接再点击分享", 0).show();
                                return;
                            }
                            ShareWebInfo shareWebInfo2 = new ShareWebInfo();
                            shareWebInfo2.setDesc(this.shareDesEdt.getText().toString());
                            shareWebInfo2.setPageUrl(string);
                            shareWebInfo2.setTitle(this.shareTitleEdt.getText().toString());
                            shareWebInfo2.setThumbBmp(readBitmap(2131100341));
                            ShareMessage shareMessage5 = new ShareMessage();
                            shareMessage5.setShareMessage(shareWebInfo2);
                            shareMessage5.setSkipPreview(!this.previewBox.isChecked());
                            shareMessage5.setPlatform(1);
                            SQwanCore.getInstance().share(shareMessage5, this.mShareListener);
                            return;
                        }
                        if (id == 2131165218) {
                            this.imgs.add("");
                            this.shareAdapter.setData(this.imgs);
                            setHeight();
                            return;
                        }
                        if (id == 2131165289) {
                            SQwanCore.getInstance().showUAgreement(this);
                            return;
                        }
                        if (id == 2131166228) {
                            SocialAccountUtil.socialBindAuthorize(this, new IBindWxListener() { // from class: com.sqwan.m.MainActivity.18
                                @Override // com.sqwan.common.mod.account.IBindWxListener
                                public void onSuccess(String str) {
                                    ToastUtil.showToast(MainActivity.this, "onSuccess " + str);
                                }

                                @Override // com.sqwan.common.mod.account.IBindWxListener
                                public void onFailure(int i, String str) {
                                    ToastUtil.showToast(MainActivity.this, "onFailure " + str);
                                }
                            });
                            return;
                        }
                        if (id == 2131165219) {
                            SQwanCore.getInstance().performFeature(this, "age_appropriate_icon", (Object) null, new SQResultListener() { // from class: com.sqwan.m.MainActivity.19
                                public void onSuccess(Bundle bundle) {
                                    String string2 = bundle.getString("age_appropriate_icon");
                                    LogUtil.i("适龄提醒url:" + string2);
                                    MainActivity.this.showAgeAppropriate(string2);
                                }

                                public void onFailture(int i, String str) {
                                    LogUtil.i("获取适龄提醒icon失败，msg: " + str);
                                    MainActivity.this.ivAgeAppropriate.setVisibility(8);
                                    ToastUtil.showToast(MainActivity.this, "获取适龄提醒图标url失败，" + str);
                                }
                            });
                            return;
                        }
                        if (id == 2131165335) {
                            addEdt();
                            return;
                        }
                        if (id == 2131165339) {
                            reportCpTrack();
                            return;
                        }
                        if (id == 2131165272) {
                            SQwanCore.getInstance().showSQWebDialog(this.etUrl.getText().toString());
                            return;
                        }
                        if (id == 2131165872) {
                            SQwanCore.getInstance().showAdReward(this, "{\n    \"advertise_id\": \"960079766\",\n    \"dsid\": \"服务器id\",\n    \"dsname\": \"服务器名\",\n    \"drid\": \"角色id\",\n    \"drname\": \"角色名\"\n}", new SQResultListener() { // from class: com.sqwan.m.MainActivity.20
                                public void onFailture(int i, String str) {
                                }

                                public void onSuccess(Bundle bundle) {
                                }
                            });
                            return;
                        }
                        if (id == 2131165829) {
                            SQwanCore.getInstance().performFeature(this, "scan_login", (Object) null, (SQResultListener) null);
                            return;
                        }
                        if (id == 2131165876) {
                            SQwanCore.getInstance().performFeature(this, "support_scan_login", (Object) null, new SQResultListener() { // from class: com.sqwan.m.MainActivity.21
                                public void onFailture(int i, String str) {
                                }

                                public void onSuccess(Bundle bundle) {
                                    ToastUtil.showToast("是否展示扫码按钮 : " + bundle.getBoolean("show_scan_login"));
                                }
                            });
                            return;
                        }
                        if (id == 2131165873) {
                            SQwanCore.getInstance().performFeature(this, "showGoodReview", (Object) null, new SQResultListener() { // from class: com.sqwan.m.MainActivity.22
                                public void onSuccess(Bundle bundle) {
                                }

                                public void onFailture(int i, String str) {
                                    ToastUtil.showToast("code:" + i + " ;msg:" + str);
                                }
                            });
                            return;
                        }
                        if (id == 2131165877) {
                            SQwanCore.getInstance().performFeature(this, "policy", "{ \"type\":\"show_third_list\" }", new SQResultListener() { // from class: com.sqwan.m.MainActivity.23
                                public void onSuccess(Bundle bundle) {
                                    ToastUtil.showToast("个人信息第三方共享清单打开成功");
                                }

                                public void onFailture(int i, String str) {
                                    ToastUtil.showToast("code:" + i + " ;msg:" + str);
                                }
                            });
                            return;
                        }
                        if (id == 2131165875) {
                            SQwanCore.getInstance().performFeature(this, "policy", "{ \"type\":\"show_personal_list\" }", new SQResultListener() { // from class: com.sqwan.m.MainActivity.24
                                public void onSuccess(Bundle bundle) {
                                    ToastUtil.showToast("个人信息收集与使用清单打开成功");
                                }

                                public void onFailture(int i, String str) {
                                    ToastUtil.showToast("code:" + i + " ;msg:" + str);
                                }
                            });
                            return;
                        }
                        if (id == 2131165440) {
                            SQwanCore.getInstance().performFeature(this, "policy", "{ \"type\":\"get_record_number\" }", new SQResultListener() { // from class: com.sqwan.m.MainActivity.25
                                public void onSuccess(Bundle bundle) {
                                    ToastUtil.showToast("获取备案号成功:" + bundle.getString("record_no"));
                                }

                                public void onFailture(int i, String str) {
                                    ToastUtil.showToast("code:" + i + " ;msg:" + str);
                                }
                            });
                            return;
                        } else if (id == 2131165874) {
                            SQwanCore.getInstance().performFeature(this, "policy", "{ \"type\":\"show_miit\" }", new SQResultListener() { // from class: com.sqwan.m.MainActivity.26
                                public void onSuccess(Bundle bundle) {
                                    ToastUtil.showToast("打开工信部url成功");
                                }

                                public void onFailture(int i, String str) {
                                    ToastUtil.showToast("code:" + i + " ;msg:" + str);
                                }
                            });
                            return;
                        } else {
                            if (id == 2131165971) {
                                ((IDownloadMod) ModHelper.get(IDownloadMod.class)).installApk(((EditText) findViewById(2131165970)).getText().toString(), new SQResultListener() { // from class: com.sqwan.m.MainActivity.27
                                    public void onSuccess(Bundle bundle) {
                                        ToastUtil.showToast(MainActivity.this, "模拟下载成功");
                                    }

                                    public void onFailture(int i, String str) {
                                        ToastUtil.showToast(MainActivity.this, "模拟下载失败: " + str);
                                    }
                                });
                                return;
                            }
                            return;
                        }
                    }
                    SQwanCore.getInstance().submitStatisticsInfo("consume", "特定数据格式");
                }
                return;
            }
            Intent intent = new Intent(this, (Class<?>) PlatformAnnouncementActivity.class);
            intent.putExtra("title", "AAAAAAA");
            intent.putExtra("content", "BBBBBB");
            startActivity(intent);
            overridePendingTransition(0, 0);
        }
    }

    private void addEdt() {
        View viewInflate = LayoutInflater.from(this).inflate(R.layout.sy37_demo_cp_filed, (ViewGroup) null);
        EditText editText = (EditText) viewInflate.findViewById(R.id.cp_track_key);
        EditText editText2 = (EditText) viewInflate.findViewById(R.id.cp_track_value);
        this.trackLayout.addView(viewInflate);
        this.keyEdts.add(editText);
        this.valueEdts.add(editText2);
    }

    private void reportCpTrack() {
        HashMap map = new HashMap();
        for (int i = 0; i < this.keyEdts.size(); i++) {
            map.put(this.keyEdts.get(i).getText().toString(), this.valueEdts.get(i).getText().toString());
        }
        SQwanCore.getInstance().track(this.trackEventEdt.getText().toString(), "Demo 测试 CP事件", map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAgeAppropriate(String str) {
        if (TextUtils.isEmpty(str)) {
            this.ivAgeAppropriate.setVisibility(8);
            ToastUtil.showToast(this, "适龄提醒图标url为空");
        } else {
            this.ivAgeAppropriate.setVisibility(0);
            new AsyncImageLoader(this).loadDrawable(str, this.ivAgeAppropriate, new AsyncImageLoader.ImageCallback() { // from class: com.sqwan.m.MainActivity.28
                @Override // com.sqwan.common.util.AsyncImageLoader.ImageCallback
                public void imageLoaded(Bitmap bitmap, ImageView imageView, String str2) {
                    imageView.setImageBitmap(bitmap);
                }
            });
            this.ivAgeAppropriate.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.m.MainActivity.29
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SQwanCore.getInstance().performFeature(MainActivity.this, "showAgeAppropriate", (Object) null, (SQResultListener) null);
                }
            });
        }
    }

    private Bitmap readBitmap(int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        return BitmapFactory.decodeStream(getResources().openRawResource(i), null, options);
    }

    public void setHeight() {
        int count = this.shareAdapter.getCount();
        int measuredHeight = 0;
        for (int i = 0; i < count; i++) {
            View view = this.shareAdapter.getView(i, null, this.lvShare);
            view.measure(0, 0);
            measuredHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams layoutParams = this.lvShare.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = measuredHeight;
        this.lvShare.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void testGameScreenCapture() {
        this.mHandler.postDelayed(new Runnable() { // from class: com.sqwan.m.MainActivity.30
            @Override // java.lang.Runnable
            public void run() {
                MainActivity mainActivity = MainActivity.this;
                mainActivity.mScreenCaptureBitmap = ScreenCaptureUtils.captureScreen(mainActivity);
                synchronized (MainActivity.this.mLock) {
                    MainActivity.this.mLock.notifyAll();
                }
            }
        }, 1000L);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            SQwanCore.getInstance().showExitDailog(this, new SQResultListener() { // from class: com.sqwan.m.MainActivity.31
                public void onSuccess(Bundle bundle) {
                    Toast.makeText(MainActivity.this, "登出完成，请处理游戏逻辑(例如清理资源、退出游戏等)", 1).show();
                    System.exit(0);
                }

                public void onFailture(int i2, String str) {
                    Toast.makeText(MainActivity.this, "取消登出，则不做退出处理，继续游戏", 1).show();
                }
            });
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        SQwanCore.getInstance().onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        StringBuilder sb = new StringBuilder();
        sb.append("onConfigurationChanged  isLandScape-->> ");
        sb.append(configuration.orientation == 2);
        Log.e("vivo_config", sb.toString());
        Log.e("vivo_config", "rotation is " + ((WindowManager) getSystemService("window")).getDefaultDisplay().getRotation());
        SQwanCore.getInstance().onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        notchConfigOnWindowFocusChanged(z);
        super.onWindowFocusChanged(z);
        SQwanCore.getInstance().onWindowFocusChanged(z);
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return SQwanCore.getInstance().getResources(super.getResources());
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return SQwanCore.getInstance().getAssets(super.getAssets());
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public ClassLoader getClassLoader() {
        return SQwanCore.getInstance().getClassLoader(super.getClassLoader());
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        SQwanCore.getInstance().startActivity(intent);
        super.startActivity(intent);
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i) {
        SQwanCore.getInstance().startActivityForResult(intent, i);
        super.startActivityForResult(intent, i);
    }

    public void url1(View view) {
        SQwanCore.getInstance().performFeature(this, "showWebDialog", "{ \"url\":\"http://37.com.cn/community/vertical/54/home\",\"showToolBar\":true}", (SQResultListener) null);
    }

    public void url2(View view) {
        startActivity(new Intent(this, (Class<?>) WebDemoActivity.class));
    }

    @Override // notchtools.geek.com.notchtools.core.OnNotchCallBack
    public void onNotchPropertyCallback(NotchProperty notchProperty) {
        notchProperty.getMarginTop();
    }

    public void test(View view) {
        setSdkInit();
    }

    public void leaveRoom(View view) {
        SQwanCore.getInstance().leaveLiveshowRoom((Map) null, new SQResultListener() { // from class: com.sqwan.m.MainActivity.33
            public void onFailture(int i, String str) {
            }

            public void onSuccess(Bundle bundle) {
                Log.i(MainActivity.this.TAG, "leaveLiveshowRoom onSuccess bundle " + bundle);
            }
        });
    }

    public void joinRoom(View view) {
        SQwanCore.getInstance().joinLiveshowRoom((Map) null, new SQResultListener() { // from class: com.sqwan.m.MainActivity.34
            public void onSuccess(Bundle bundle) {
                Log.i(MainActivity.this.TAG, "joinRoom onSuccess bundle " + bundle);
            }

            public void onFailture(int i, String str) {
                ToastUtil.showToast(str);
            }
        });
    }

    public void joinRadioRoom(View view) {
        SQwanCore.getInstance().joinLiveRadioRoom((Map) null, new SQResultListener() { // from class: com.sqwan.m.MainActivity.35
            public void onSuccess(Bundle bundle) {
                ToastUtil.showToast("进入电台成功");
                Log.i(MainActivity.this.TAG, "joinRadioRoom onSuccess bundle " + bundle);
            }

            public void onFailture(int i, String str) {
                ToastUtil.showToast(str);
            }
        });
        SQwanCore.getInstance().setLiveRadioDestroyCallback(new SQResultListener() { // from class: com.sqwan.m.MainActivity.36
            public void onSuccess(Bundle bundle) {
                ToastUtil.showToast("监听到电台关闭了");
                Log.i(MainActivity.this.TAG, "setLiveshowDestroyCallback onSuccess bundle " + bundle);
            }

            public void onFailture(int i, String str) {
                ToastUtil.showToast(str);
            }
        });
        SQwanCore.getInstance().setLiveRadioVoiceChangeCallback(new SQResultListener() { // from class: com.sqwan.m.MainActivity.37
            public void onSuccess(Bundle bundle) {
                ToastUtil.showToast("监听到电台声音变化了：" + bundle.getBoolean(LiveShowParamsKey.isResume));
                Log.i(MainActivity.this.TAG, "setLiveshowVoiceChangeCallback bundle " + bundle);
            }

            public void onFailture(int i, String str) {
                ToastUtil.showToast(str);
            }
        });
    }

    public void leaveRadioRoom(View view) {
        SQwanCore.getInstance().leaveLiveRadioRoom((Map) null, new SQResultListener() { // from class: com.sqwan.m.MainActivity.38
            public void onSuccess(Bundle bundle) {
                ToastUtil.showToast("离开电台成功");
                Log.i(MainActivity.this.TAG, "leaveLiveRadioRoom onSuccess bundle " + bundle);
            }

            public void onFailture(int i, String str) {
                ToastUtil.showToast(str);
            }
        });
    }

    private void handleAfterInited() {
        SQwanCore.getInstance().setLiveshowDestroyCallback(new SQResultListener() { // from class: com.sqwan.m.MainActivity.39
            public void onFailture(int i, String str) {
            }

            public void onSuccess(Bundle bundle) {
                Log.i(MainActivity.this.TAG, "setLiveshowDestroyCallback onSuccess bundle " + bundle);
            }
        });
        SQwanCore.getInstance().setLiveshowVoiceChangeCallback(new SQResultListener() { // from class: com.sqwan.m.MainActivity.40
            public void onFailture(int i, String str) {
            }

            public void onSuccess(Bundle bundle) {
                bundle.getBoolean(LiveShowParamsKey.isResume);
                Log.i(MainActivity.this.TAG, "setLiveshowVoiceChangeCallback bundle " + bundle);
            }
        });
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Log.d(this.TAG, "dispatchTouchEvent: ");
        if (motionEvent.getAction() == 1) {
            handleScrrenBrightnessTask();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void closeLiveshowVoice(View view) {
        HashMap map = new HashMap();
        map.put(LiveShowParamsKey.switchVoice, Bugly.SDK_IS_DEV);
        SQwanCore.getInstance().performLiveshowFeature(map, new SQResultListener() { // from class: com.sqwan.m.MainActivity.41
            public void onFailture(int i, String str) {
            }

            public void onSuccess(Bundle bundle) {
                LogUtil.i(MainActivity.this.TAG, "closeLiveshowVoice");
            }
        });
    }

    public void openLiveshowVoice(View view) {
        HashMap map = new HashMap();
        map.put(LiveShowParamsKey.switchVoice, "true");
        SQwanCore.getInstance().performLiveshowFeature(map, new SQResultListener() { // from class: com.sqwan.m.MainActivity.42
            public void onFailture(int i, String str) {
            }

            public void onSuccess(Bundle bundle) {
                LogUtil.i(MainActivity.this.TAG, "openLiveshowVoice");
            }
        });
    }

    public void onShareDemo(View view) {
        startActivity(new Intent(this, (Class<?>) ShareDemoActivity.class));
    }
}
