package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.BasePopupWindow;
import com.cy.yyjia.zhe28.base.FastDialog;
import com.cy.yyjia.zhe28.base.QuickDialog;
import com.cy.yyjia.zhe28.databinding.ActivityYunPlayBinding;
import com.cy.yyjia.zhe28.domain.AppInfo;
import com.cy.yyjia.zhe28.domain.DownloadResult;
import com.cy.yyjia.zhe28.domain.GameDetailBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.YunBean;
import com.cy.yyjia.zhe28.domain.YunIndexBean;
import com.cy.yyjia.zhe28.domain.YunMessage;
import com.cy.yyjia.zhe28.service.DownloadService;
import com.cy.yyjia.zhe28.ui.dialog.WaitDialog;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.OkDownload;
import com.lzy.okserver.download.DownloadListener;
import com.lzy.okserver.download.DownloadTask;
import com.lzy.okserver.task.XExecutor;
import com.volcengine.androidcloud.common.model.StreamStats;
import com.volcengine.cloudcore.common.mode.LocalStreamStats;
import com.volcengine.cloudcore.common.mode.QueueInfo;
import com.volcengine.cloudgame.GamePlayConfig;
import com.volcengine.cloudgame.VeGameEngine;
import com.volcengine.cloudphone.apiservice.GamePodControlService;
import com.volcengine.cloudphone.apiservice.IMessageChannel;
import com.volcengine.cloudphone.apiservice.StreamProfileChangeCallBack;
import com.volcengine.cloudphone.apiservice.StreamProfileManager;
import com.volcengine.cloudphone.apiservice.outinterface.ICloudCoreManagerStatusListener;
import com.volcengine.cloudphone.apiservice.outinterface.IGamePlayerListener;
import com.volcengine.cloudphone.apiservice.outinterface.IStreamListener;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: YunPlayActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010K\u001a\u00020LJ\u0006\u0010M\u001a\u00020LJ\u000e\u0010N\u001a\u00020L2\u0006\u0010O\u001a\u000205J\u0010\u0010P\u001a\u00020L2\u0006\u0010\u001d\u001a\u00020\u0012H\u0002J\u0006\u0010Q\u001a\u00020RJ\u0006\u0010S\u001a\u00020LJ\b\u0010T\u001a\u00020LH\u0016J\b\u0010U\u001a\u00020LH\u0002J\u0010\u0010V\u001a\u00020L2\u0006\u0010W\u001a\u00020XH\u0002J\u0010\u0010Y\u001a\u00020L2\u0006\u0010Z\u001a\u00020[H\u0016J\u0012\u0010\\\u001a\u00020L2\b\u0010]\u001a\u0004\u0018\u00010^H\u0014J\b\u0010_\u001a\u00020LH\u0014J\u0018\u0010`\u001a\u00020<2\u0006\u0010a\u001a\u00020\u00122\u0006\u0010b\u001a\u00020cH\u0016J\b\u0010d\u001a\u00020LH\u0014J\b\u0010e\u001a\u00020LH\u0014J\u0006\u0010f\u001a\u00020LR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001b\u0010\u001d\u001a\u00020\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001e\u0010\u0014R\u000e\u0010!\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\"\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001a\"\u0004\b$\u0010\u001cR\u000e\u0010%\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u00020)8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u001c\u0010*\u001a\u0004\u0018\u00010+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001b\u00100\u001a\u00020\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b2\u0010 \u001a\u0004\b1\u0010\u0014R\"\u00103\u001a\b\u0012\u0004\u0012\u00020504X\u0086\u000e¢\u0006\u0010\n\u0002\u0010:\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001a\u0010;\u001a\u00020<X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u001c\u0010A\u001a\u0004\u0018\u00010BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u001a\u0010G\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010\u0014\"\u0004\bI\u0010\u0016R\u000e\u0010J\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006g"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/YunPlayActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityYunPlayBinding;", "Landroid/view/View$OnClickListener;", "()V", "code", "", "getCode", "()[I", "setCode", "([I)V", "downloadTask", "Lcom/lzy/okserver/download/DownloadTask;", "getDownloadTask", "()Lcom/lzy/okserver/download/DownloadTask;", "setDownloadTask", "(Lcom/lzy/okserver/download/DownloadTask;)V", "duration", "", "getDuration", "()I", "setDuration", "(I)V", "exitTime", "", "getExitTime", "()J", "setExitTime", "(J)V", "gid", "getGid", "gid$delegate", "Lkotlin/Lazy;", "height", "lastTime", "getLastTime", "setLastTime", "lastX", "", "lastY", "listener", "Landroid/view/View$OnTouchListener;", "messageChannel", "Lcom/volcengine/cloudphone/apiservice/IMessageChannel;", "getMessageChannel", "()Lcom/volcengine/cloudphone/apiservice/IMessageChannel;", "setMessageChannel", "(Lcom/volcengine/cloudphone/apiservice/IMessageChannel;)V", "myDeviceId", "getMyDeviceId", "myDeviceId$delegate", "str", "", "", "getStr", "()[Ljava/lang/String;", "setStr", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "success", "", "getSuccess", "()Z", "setSuccess", "(Z)V", "targetDevice", "Lcom/cy/yyjia/zhe28/domain/YunIndexBean$Device;", "getTargetDevice", "()Lcom/cy/yyjia/zhe28/domain/YunIndexBean$Device;", "setTargetDevice", "(Lcom/cy/yyjia/zhe28/domain/YunIndexBean$Device;)V", "times", "getTimes", "setTimes", "width", "afk", "", "confirmExit", "download", "downloadUrl", "getData", "getDownloadListener", "Lcom/lzy/okserver/download/DownloadListener;", "getRemainingTime", "init", "initFloat", "initYun", "data", "Lcom/cy/yyjia/zhe28/domain/YunBean;", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onKeyDown", "keyCode", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "onPause", "onResume", "requestDownloadUrl", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class YunPlayActivity extends BaseActivity<ActivityYunPlayBinding> implements View.OnClickListener {
    public static final int $stable = 8;
    private int[] code;
    private DownloadTask downloadTask;
    private int duration;
    private long exitTime;

    /* JADX INFO: renamed from: gid$delegate, reason: from kotlin metadata */
    private final Lazy gid;
    private int height;
    private long lastTime;
    private double lastX;
    private double lastY;
    private final View.OnTouchListener listener;
    private IMessageChannel messageChannel;

    /* JADX INFO: renamed from: myDeviceId$delegate, reason: from kotlin metadata */
    private final Lazy myDeviceId;
    private String[] str;
    private boolean success;
    private YunIndexBean.Device targetDevice;
    private int times;
    private int width;

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initFloat$lambda$0(View view) {
        return true;
    }

    public YunPlayActivity() {
        super(R.layout.activity_yun_play, 2);
        this.code = new int[]{2, 6, 14, 16};
        this.str = new String[]{"流畅", "标清", "高清", "超清"};
        this.myDeviceId = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity$myDeviceId$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("myDeviceId", 0));
            }
        });
        this.gid = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity$gid$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("gid", 0));
            }
        });
        this.listener = new View.OnTouchListener() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return YunPlayActivity.listener$lambda$1(this.f$0, view, motionEvent);
            }
        };
    }

    public static final /* synthetic */ ActivityYunPlayBinding access$getMBinding(YunPlayActivity yunPlayActivity) {
        return yunPlayActivity.getMBinding();
    }

    public final int[] getCode() {
        return this.code;
    }

    public final void setCode(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.code = iArr;
    }

    public final String[] getStr() {
        return this.str;
    }

    public final void setStr(String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "<set-?>");
        this.str = strArr;
    }

    public final IMessageChannel getMessageChannel() {
        return this.messageChannel;
    }

    public final void setMessageChannel(IMessageChannel iMessageChannel) {
        this.messageChannel = iMessageChannel;
    }

    public final long getLastTime() {
        return this.lastTime;
    }

    public final void setLastTime(long j) {
        this.lastTime = j;
    }

    public final int getMyDeviceId() {
        return ((Number) this.myDeviceId.getValue()).intValue();
    }

    public final int getGid() {
        return ((Number) this.gid.getValue()).intValue();
    }

    public final int getDuration() {
        return this.duration;
    }

    public final void setDuration(int i) {
        this.duration = i;
    }

    public final DownloadTask getDownloadTask() {
        return this.downloadTask;
    }

    public final void setDownloadTask(DownloadTask downloadTask) {
        this.downloadTask = downloadTask;
    }

    public final YunIndexBean.Device getTargetDevice() {
        return this.targetDevice;
    }

    public final void setTargetDevice(YunIndexBean.Device device) {
        this.targetDevice = device;
    }

    public final boolean getSuccess() {
        return this.success;
    }

    public final void setSuccess(boolean z) {
        this.success = z;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(1);
        if (getIntent().getBooleanExtra("landscape", false)) {
            setRequestedOrientation(0);
        } else {
            setRequestedOrientation(-1);
        }
        getWindow().setFlags(1024, 1024);
        super.onCreate(savedInstanceState);
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        log("myDeviceId=" + getMyDeviceId());
        this.duration = getIntent().getIntExtra("duration", 0) * 60;
        getMBinding().setVisitor(getMyDeviceId() == 0);
        getMBinding().setShowMenu(getMBinding().getVisitor());
        getData(getGid());
        if (getIntent().getBooleanExtra("landscape", false)) {
            this.width = Util.getWidth(getMContext());
            this.height = Util.getHeight(getMContext());
        } else {
            this.width = Util.getHeight(getMContext());
            this.height = Util.getWidth(getMContext());
        }
        initFloat();
    }

    private final void getData(int gid) {
        Repository.INSTANCE.getYunData(getMyDeviceId(), gid, new Function1<YunBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(YunBean yunBean) {
                invoke2(yunBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(YunBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                YunPlayActivity.this.initYun(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity.getData.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
                YunPlayActivity.this.netFail(it);
            }
        });
        Repository.INSTANCE.getGameDetail(gid, new Function1<GameDetailBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity.getData.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GameDetailBean gameDetailBean) {
                invoke2(gameDetailBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GameDetailBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                YunPlayActivity.access$getMBinding(YunPlayActivity.this).setData(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity.getData.4
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
                YunPlayActivity yunPlayActivity = YunPlayActivity.this;
                String localizedMessage = it.getLocalizedMessage();
                Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                yunPlayActivity.log(localizedMessage);
            }
        });
        Repository.INSTANCE.getYunIndex(new Function1<YunIndexBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity.getData.5
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(YunIndexBean yunIndexBean) {
                invoke2(yunIndexBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(YunIndexBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Iterator<YunIndexBean.Device> it2 = it.getDeviceList().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    YunIndexBean.Device next = it2.next();
                    if (next.getId() == YunPlayActivity.this.getMyDeviceId()) {
                        next.setSelected(true);
                        break;
                    }
                }
                YunPlayActivity.access$getMBinding(YunPlayActivity.this).setIndex(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity.getData.6
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
                YunPlayActivity yunPlayActivity = YunPlayActivity.this;
                String localizedMessage = it.getLocalizedMessage();
                Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                yunPlayActivity.log(localizedMessage);
            }
        });
    }

    private final void initFloat() {
        getMBinding().llFloat.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return YunPlayActivity.initFloat$lambda$0(view);
            }
        });
        getMBinding().llFloat.setOnTouchListener(this.listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initYun(final YunBean data) {
        GamePlayConfig.Builder builder = new GamePlayConfig.Builder();
        builder.sessionMode(0).userId(String.valueOf(Constant.INSTANCE.getId())).ak(data.getToken().getAk()).sk(data.getToken().getSk()).reservedId(data.getReserverd_id()).token(data.getToken().getToken()).container(getMBinding().body).roundId("999").videoStreamProfileId(this.code[3]).gameId(data.getGameId()).enableAcceleratorSensor(false).enableGravitySensor(false).enableGyroscopeSensor(false).enableMagneticSensor(false).enableOrientationSensor(false).enableVibrator(false).enableLocationService(false).autoRecycleTime(this.duration).enableLocalKeyboard(true).streamListener(new IStreamListener() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity.initYun.1
            @Override // com.volcengine.cloudphone.apiservice.outinterface.IStreamListener
            public void onFirstAudioFrame(String s) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // com.volcengine.cloudphone.apiservice.outinterface.IStreamListener
            public void onFirstRemoteVideoFrame(String s) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // com.volcengine.cloudphone.apiservice.outinterface.IStreamListener
            public void onLocalStreamStats(LocalStreamStats localStreamStats) {
                Intrinsics.checkNotNullParameter(localStreamStats, "localStreamStats");
            }

            @Override // com.volcengine.cloudphone.apiservice.outinterface.IStreamListener
            public void onNetworkQuality(int i) {
            }

            @Override // com.volcengine.cloudphone.apiservice.outinterface.IStreamListener
            public void onPodExit(int i, String s) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // com.volcengine.cloudphone.apiservice.outinterface.IStreamListener
            public void onRotation(int i) {
            }

            @Override // com.volcengine.cloudphone.apiservice.outinterface.IStreamListener
            public void onStreamConnectionStateChanged(int i) {
            }

            @Override // com.volcengine.cloudphone.apiservice.outinterface.IStreamListener
            public void onStreamPaused() {
            }

            @Override // com.volcengine.cloudphone.apiservice.outinterface.IStreamListener
            public void onStreamResumed() {
            }

            @Override // com.volcengine.cloudphone.apiservice.outinterface.IStreamListener
            public void onStreamStarted() {
            }

            @Override // com.volcengine.cloudphone.apiservice.outinterface.IStreamListener
            public void onStreamStats(StreamStats streamStats) {
                Intrinsics.checkNotNullParameter(streamStats, "streamStats");
            }

            @Override // com.volcengine.cloudphone.apiservice.outinterface.IStreamListener
            public void onDetectDelay(long l) {
                YunPlayActivity.access$getMBinding(YunPlayActivity.this).setPin(l + "ms");
                if (YunPlayActivity.access$getMBinding(YunPlayActivity.this).getVisitor()) {
                    YunPlayActivity.this.getRemainingTime();
                }
            }
        });
        GamePlayConfig gamePlayConfigBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(gamePlayConfigBuild, "build(...)");
        this.messageChannel = VeGameEngine.getInstance().getMessageChannel();
        VeGameEngine.getInstance().start(gamePlayConfigBuild, new IGamePlayerListener() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity.initYun.2
            @Override // com.volcengine.cloudphone.apiservice.outinterface.IGamePlayerListener
            public void onQueueSuccessAndStart(int i) {
            }

            @Override // com.volcengine.cloudphone.apiservice.outinterface.IGamePlayerListener
            public void onQueueUpdate(List<? extends QueueInfo> list) {
                Intrinsics.checkNotNullParameter(list, "list");
            }

            @Override // com.volcengine.cloudphone.apiservice.outinterface.IGamePlayerListener
            public void onPlaySuccess(String s, int i, Map<String, String> map, String gameid, String reservedId) {
                Intrinsics.checkNotNullParameter(s, "s");
                Intrinsics.checkNotNullParameter(map, "map");
                Intrinsics.checkNotNullParameter(gameid, "gameid");
                Intrinsics.checkNotNullParameter(reservedId, "reservedId");
                YunPlayActivity.this.setSuccess(true);
                if (!YunPlayActivity.access$getMBinding(YunPlayActivity.this).getVisitor()) {
                    Repository repository = Repository.INSTANCE;
                    int myDeviceId = YunPlayActivity.this.getMyDeviceId();
                    String serviceDeviceId = VeGameEngine.getInstance().getServiceDeviceId();
                    Intrinsics.checkNotNullExpressionValue(serviceDeviceId, "getServiceDeviceId(...)");
                    final YunPlayActivity yunPlayActivity = YunPlayActivity.this;
                    Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity$initYun$2$onPlaySuccess$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Result result) {
                            invoke2(result);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Result it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            yunPlayActivity.log("reportYunId" + it.getMsg());
                        }
                    };
                    final YunPlayActivity yunPlayActivity2 = YunPlayActivity.this;
                    repository.reportYunId(myDeviceId, serviceDeviceId, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity$initYun$2$onPlaySuccess$2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                            invoke2(exc);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Exception it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            yunPlayActivity2.log("reportYunId" + it.getLocalizedMessage());
                        }
                    });
                }
                YunPlayActivity.this.log("onPlaySuccess: s-" + s + "\ni-" + i);
                String str = Constant.INSTANCE.getChannelUid() + "," + Constant.INSTANCE.getUsername() + "," + data.getSiteToken();
                IMessageChannel messageChannel = VeGameEngine.getInstance().getMessageChannel();
                Intrinsics.checkNotNull(messageChannel);
                messageChannel.sendMessage(str, true);
            }

            @Override // com.volcengine.cloudphone.apiservice.outinterface.IGamePlayerListener
            public void onError(int i, String s) {
                Intrinsics.checkNotNullParameter(s, "s");
                YunPlayActivity.this.toast(i + s);
                YunPlayActivity.this.log(i + s);
            }

            @Override // com.volcengine.cloudphone.apiservice.outinterface.IGamePlayerListener
            public void onWarning(int i, String s) {
                Intrinsics.checkNotNullParameter(s, "s");
                YunPlayActivity.this.log("onWarning: s-" + s + "\ni-" + i);
            }

            @Override // com.volcengine.cloudphone.apiservice.outinterface.IGamePlayerListener
            public void onNetworkChanged(int i) {
                YunPlayActivity.this.log("onNetworkChanged: i-" + i);
            }

            @Override // com.volcengine.cloudphone.apiservice.outinterface.IGamePlayerListener
            public void onServiceInit() {
                YunPlayActivity.this.log("onServiceInit");
            }
        });
        VeGameEngine.getInstance().addCloudCoreManagerListener(new ICloudCoreManagerStatusListener() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity.initYun.3
            @Override // com.volcengine.cloudphone.apiservice.outinterface.ICloudCoreManagerStatusListener
            public void onInitialed() {
            }

            @Override // com.volcengine.cloudphone.apiservice.outinterface.ICloudCoreManagerStatusListener
            public void onPrepared() {
            }
        });
        IMessageChannel iMessageChannel = this.messageChannel;
        Intrinsics.checkNotNull(iMessageChannel);
        iMessageChannel.setMessageListener(new IMessageChannel.IMessageReceiver() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity.initYun.4
            @Override // com.volcengine.cloudphone.apiservice.IMessageChannel.IMessageReceiver
            public void onReceiveBinaryMessage(IMessageChannel.IChannelBinaryMessage iChannelBinaryMessage) {
                Intrinsics.checkNotNullParameter(iChannelBinaryMessage, "iChannelBinaryMessage");
            }

            @Override // com.volcengine.cloudphone.apiservice.IMessageChannel.IMessageReceiver
            public void onRemoteOffline(String s) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // com.volcengine.cloudphone.apiservice.IMessageChannel.IMessageReceiver
            public void onRemoteOnline(String s) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // com.volcengine.cloudphone.apiservice.IMessageChannel.IMessageReceiver
            public void onSentResult(boolean b, String s) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // com.volcengine.cloudphone.apiservice.IMessageChannel.IMessageReceiver
            public void onReceiveMessage(IMessageChannel.IChannelMessage iChannelMessage) {
                Intrinsics.checkNotNullParameter(iChannelMessage, "iChannelMessage");
                YunPlayActivity yunPlayActivity = YunPlayActivity.this;
                String payload = iChannelMessage.getPayload();
                Intrinsics.checkNotNullExpressionValue(payload, "getPayload(...)");
                yunPlayActivity.log(payload);
                YunMessage yunMessage = (YunMessage) new Gson().fromJson(iChannelMessage.getPayload(), YunMessage.class);
                if (Intrinsics.areEqual("charge", yunMessage.getMessageID())) {
                    YunPlayActivity.this.log("看看支付数据：" + yunMessage.getPaydata());
                    Intent intent = new Intent(YunPlayActivity.this.getMContext(), (Class<?>) WebPayActivity.class);
                    intent.putExtra("otherScheme", yunMessage.getPaydata());
                    YunPlayActivity.this.startActivity(intent);
                }
            }

            @Override // com.volcengine.cloudphone.apiservice.IMessageChannel.IMessageReceiver
            public void ready() {
                String str = Constant.INSTANCE.getChannelUid() + "," + Constant.INSTANCE.getUsername() + "," + data.getSiteToken();
                IMessageChannel messageChannel = VeGameEngine.getInstance().getMessageChannel();
                Intrinsics.checkNotNull(messageChannel);
                messageChannel.sendMessage(str, true);
            }

            @Override // com.volcengine.cloudphone.apiservice.IMessageChannel.IMessageReceiver
            public void onError(int i, String s) {
                Intrinsics.checkNotNullParameter(s, "s");
                YunPlayActivity.this.log(i + StringUtils.SPACE + s);
            }
        });
        StreamProfileManager clarityService = VeGameEngine.getInstance().getClarityService();
        Intrinsics.checkNotNull(clarityService);
        clarityService.setStreamProfileChangeListener(new StreamProfileChangeCallBack() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity.initYun.5
            @Override // com.volcengine.cloudphone.apiservice.StreamProfileChangeCallBack
            public void onError(int i, String s) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // com.volcengine.cloudphone.apiservice.StreamProfileChangeCallBack
            public void onVideoStreamProfileChange(boolean b, int i, int i1) {
                YunPlayActivity.this.toast(b ? "切换清晰度成功" : "切换清晰度失败");
                int length = YunPlayActivity.this.getCode().length;
                for (int i2 = 0; i2 < length; i2++) {
                    if (YunPlayActivity.this.getCode()[i2] == i1) {
                        YunPlayActivity.access$getMBinding(YunPlayActivity.this).tvLevel.setText(YunPlayActivity.this.getStr()[i2]);
                    }
                }
            }
        });
    }

    public final long getExitTime() {
        return this.exitTime;
    }

    public final void setExitTime(long j) {
        this.exitTime = j;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (getMyDeviceId() != 0) {
            confirmExit();
            return true;
        }
        if (keyCode != 4 || event.getAction() != 0) {
            return super.onKeyDown(keyCode, event);
        }
        if (System.currentTimeMillis() - this.exitTime > 2000) {
            toast("再按一次退出游戏");
            this.exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean listener$lambda$1(YunPlayActivity this$0, View v, MotionEvent event) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(event, "event");
        double rawX = event.getRawX();
        double rawY = event.getRawY();
        int action = event.getAction();
        if (action == 0) {
            this$0.lastX = rawX;
            this$0.lastY = rawY;
            this$0.lastTime = System.currentTimeMillis();
            return false;
        }
        if (action == 1) {
            v.setTranslationX(0.0f);
            return false;
        }
        if (action != 2) {
            return false;
        }
        double d = rawX - this$0.lastX;
        double d2 = rawY - this$0.lastY;
        v.setTranslationX((float) (((double) v.getTranslationX()) + d));
        v.setTranslationY((float) (((double) v.getTranslationY()) + d2));
        this$0.lastX = rawX;
        this$0.lastY = rawY;
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.ll_float /* 2131362258 */:
                getMBinding().setShowMenu(!getMBinding().getShowMenu());
                v.setTranslationX(0.0f);
                break;
            case R.id.tv_back /* 2131362659 */:
                if (getMBinding().getVisitor()) {
                    VeGameEngine.getInstance().stop();
                    finish();
                } else {
                    confirmExit();
                }
                break;
            case R.id.tv_download /* 2131362692 */:
                requestDownloadUrl();
                break;
            case R.id.tv_fold /* 2131362702 */:
                getMBinding().setShowMenu(!getMBinding().getShowMenu());
                break;
            case R.id.tv_level /* 2131362719 */:
                BasePopupWindow.OnClickListener onClickListener = new BasePopupWindow.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity$$ExternalSyntheticLambda8
                    @Override // com.cy.yyjia.zhe28.base.BasePopupWindow.OnClickListener
                    public final void onClick(BasePopupWindow basePopupWindow, View view) {
                        YunPlayActivity.onClick$lambda$2(this.f$0, basePopupWindow, view);
                    }
                };
                new BasePopupWindow.Builder(this).setContentView(R.layout.pop_yun).setOnClickListener(R.id.tv1, onClickListener).setOnClickListener(R.id.tv2, onClickListener).setOnClickListener(R.id.tv3, onClickListener).setOnClickListener(R.id.tv4, onClickListener).setWidth(v.getWidth()).showAsDropDown(getMBinding().tvLevel);
                break;
            case R.id.tv_restart /* 2131362762 */:
                new FastDialog(this).setContentView(R.layout.dialog_yun_exit).setOnClickListener(R.id.tv_no, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity$$ExternalSyntheticLambda9
                    @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                    public final void onClick(BaseDialog baseDialog, View view) {
                        YunPlayActivity.onClick$lambda$3(baseDialog, view);
                    }
                }).setOnClickListener(R.id.tv_confirm, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity$$ExternalSyntheticLambda10
                    @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                    public final void onClick(BaseDialog baseDialog, View view) {
                        YunPlayActivity.onClick$lambda$4(baseDialog, view);
                    }
                }).show();
                break;
            case R.id.tv_service /* 2131362771 */:
                Util.toService(getMContext());
                break;
            case R.id.tv_yun_device /* 2131362816 */:
                final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_dialog_yun_device, null, 2, null);
                QuickDialog adapter = new QuickDialog(getMContext(), R.layout.dialog_yun_device).setAdapter(R.id.rv, baseAdapter);
                YunIndexBean index = getMBinding().getIndex();
                Intrinsics.checkNotNull(index);
                adapter.setData(index).setOnClickListener(R.id.tv_cancel, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity$$ExternalSyntheticLambda11
                    @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                    public final void onClick(BaseDialog baseDialog, View view) {
                        baseDialog.dismiss();
                    }
                }).setOnClickListener(R.id.tv_go, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity$$ExternalSyntheticLambda1
                    @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                    public final void onClick(BaseDialog baseDialog, View view) {
                        YunPlayActivity.onClick$lambda$6(baseAdapter, this, baseDialog, view);
                    }
                }).show();
                baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity$$ExternalSyntheticLambda2
                    @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                    public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                        YunPlayActivity.onClick$lambda$7(baseAdapter, baseQuickAdapter, view, i);
                    }
                });
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onClick$lambda$2(YunPlayActivity this$0, BasePopupWindow popupWindow, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(popupWindow, "popupWindow");
        Intrinsics.checkNotNullParameter(view, "view");
        popupWindow.dismiss();
        switch (view.getId()) {
            case R.id.tv1 /* 2131362645 */:
                StreamProfileManager clarityService = VeGameEngine.getInstance().getClarityService();
                Intrinsics.checkNotNull(clarityService);
                clarityService.switchVideoStreamProfileId(this$0.code[0]);
                break;
            case R.id.tv2 /* 2131362646 */:
                StreamProfileManager clarityService2 = VeGameEngine.getInstance().getClarityService();
                Intrinsics.checkNotNull(clarityService2);
                clarityService2.switchVideoStreamProfileId(this$0.code[1]);
                break;
            case R.id.tv3 /* 2131362647 */:
                StreamProfileManager clarityService3 = VeGameEngine.getInstance().getClarityService();
                Intrinsics.checkNotNull(clarityService3);
                clarityService3.switchVideoStreamProfileId(this$0.code[2]);
                break;
            case R.id.tv4 /* 2131362648 */:
                StreamProfileManager clarityService4 = VeGameEngine.getInstance().getClarityService();
                Intrinsics.checkNotNull(clarityService4);
                clarityService4.switchVideoStreamProfileId(this$0.code[3]);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onClick$lambda$3(BaseDialog baseDialog, View view) {
        view.setSelected(!view.isSelected());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onClick$lambda$4(BaseDialog baseDialog, View view) {
        baseDialog.dismiss();
        VeGameEngine.getInstance().restart();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onClick$lambda$6(BaseAdapter deviceAdapter, YunPlayActivity this$0, BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(deviceAdapter, "$deviceAdapter");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        baseDialog.dismiss();
        for (T t : deviceAdapter.getData()) {
            if (t.getChecked()) {
                this$0.targetDevice = t;
                if (t.getGameId() == 0) {
                    this$0.setResult(9852, new Intent().putExtra("did", t.getId()));
                }
                this$0.afk();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onClick$lambda$7(BaseAdapter deviceAdapter, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(deviceAdapter, "$deviceAdapter");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        Iterator it = deviceAdapter.getData().iterator();
        while (it.hasNext()) {
            ((YunIndexBean.Device) it.next()).setChecked(false);
        }
        ((YunIndexBean.Device) deviceAdapter.getItem(i)).setChecked(true);
        deviceAdapter.notifyDataSetChanged();
    }

    public final void confirmExit() {
        if (this.success) {
            new FastDialog(this).setContentView(R.layout.dialog_yun_exit).setOnClickListener(R.id.tv_no, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity$$ExternalSyntheticLambda4
                @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                public final void onClick(BaseDialog baseDialog, View view) {
                    YunPlayActivity.confirmExit$lambda$8(baseDialog, view);
                }
            }).setOnClickListener(R.id.tv_confirm, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity$$ExternalSyntheticLambda5
                @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                public final void onClick(BaseDialog baseDialog, View view) {
                    YunPlayActivity.confirmExit$lambda$9(this.f$0, baseDialog, view);
                }
            }).setOnClickListener(R.id.tv_cancel, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity$$ExternalSyntheticLambda6
                @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                public final void onClick(BaseDialog baseDialog, View view) {
                    baseDialog.dismiss();
                }
            }).show();
        } else {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void confirmExit$lambda$8(BaseDialog baseDialog, View view) {
        view.setSelected(!view.isSelected());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void confirmExit$lambda$9(YunPlayActivity this$0, BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.afk();
    }

    public final void afk() {
        GamePodControlService gamePodControlService = VeGameEngine.getInstance().getGamePodControlService();
        Intrinsics.checkNotNull(gamePodControlService);
        gamePodControlService.setSessionMode(1);
        toast("正在切换到挂机模式，请稍候");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new AnonymousClass1(null), 3, null);
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity$afk$1, reason: invalid class name */
    /* JADX INFO: compiled from: YunPlayActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.cy.yyjia.zhe28.ui.activity.YunPlayActivity$afk$1", f = "YunPlayActivity.kt", i = {}, l = {448}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return YunPlayActivity.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (DelayKt.delay(500L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            YunPlayActivity.this.finish();
            return Unit.INSTANCE;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        VeGameEngine.getInstance().pause();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        VeGameEngine.getInstance().resume();
        DownloadTask task = OkDownload.getInstance().getTask(String.valueOf(getGid()));
        this.downloadTask = task;
        if (task != null) {
            task.register(getDownloadListener());
            getMBinding().setProgress(task.progress);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        if (getMBinding().getVisitor()) {
            Repository.INSTANCE.finishYunPlay(getGid(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity.onDestroy.1
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Result it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Result result) {
                    invoke2(result);
                    return Unit.INSTANCE;
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity.onDestroy.2
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Exception it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                    invoke2(exc);
                    return Unit.INSTANCE;
                }
            });
        }
        VeGameEngine.getInstance().stop();
        YunIndexBean.Device device = this.targetDevice;
        if (device != null && device.getGameId() != 0) {
            Intent intent = new Intent(getMContext(), (Class<?>) YunPlayActivity.class);
            intent.putExtra("gid", device.getGameId());
            intent.putExtra("myDeviceId", device.getId());
            intent.putExtra("landscape", Intrinsics.areEqual(device.getScreen(), "horizontal"));
            startActivity(intent);
        }
        super.onDestroy();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void requestDownloadUrl() {
        final WaitDialog waitDialog = (WaitDialog) new WaitDialog(this).setText("正在获取文件，请耐心等待").setCancelable(false);
        waitDialog.show();
        Repository.INSTANCE.requestGameDownloadUrl(getGid(), false, new Function1<DownloadResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity.requestDownloadUrl.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DownloadResult downloadResult) {
                invoke2(downloadResult);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DownloadResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                waitDialog.hide();
                if (Intrinsics.areEqual(it.getType(), "url")) {
                    this.download(it.getContent());
                } else {
                    this.toast("正在分包中，请稍后再试");
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity.requestDownloadUrl.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
                waitDialog.hide();
                this.netFail(it);
            }
        });
    }

    public final void download(String downloadUrl) {
        Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
        GetRequest getRequest = OkGo.get(downloadUrl);
        GameDetailBean data = getMBinding().getData();
        Intrinsics.checkNotNull(data);
        AppInfo appInfo = data.getAppInfo();
        GameDetailBean data2 = getMBinding().getData();
        if (data2 != null) {
            if (appInfo != null) {
                appInfo.setIcon(data2.getIcon());
            }
            if (appInfo != null) {
                appInfo.setName(data2.getName());
            }
        }
        this.downloadTask = OkDownload.request(String.valueOf(getGid()), getRequest).extra1(appInfo).save().register(getDownloadListener());
        startService(new Intent(this, (Class<?>) DownloadService.class));
        DownloadTask downloadTask = this.downloadTask;
        Intrinsics.checkNotNull(downloadTask);
        downloadTask.start();
        OkDownload.getInstance().addOnAllTaskEndListener(new XExecutor.OnAllTaskEndListener() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity$$ExternalSyntheticLambda0
            @Override // com.lzy.okserver.task.XExecutor.OnAllTaskEndListener
            public final void onAllTaskEnd() {
                YunPlayActivity.download$lambda$14(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void download$lambda$14(YunPlayActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.stopService(new Intent(this$0, (Class<?>) DownloadService.class));
    }

    public final DownloadListener getDownloadListener() {
        return new DownloadListener(String.valueOf(getGid())) { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity.getDownloadListener.1
            @Override // com.lzy.okserver.ProgressListener
            public void onStart(Progress p) {
                Intrinsics.checkNotNullParameter(p, "p");
                YunPlayActivity.access$getMBinding(YunPlayActivity.this).setProgress(p);
            }

            @Override // com.lzy.okserver.ProgressListener
            public void onProgress(Progress p) {
                Intrinsics.checkNotNullParameter(p, "p");
                YunPlayActivity.access$getMBinding(YunPlayActivity.this).setProgress(p);
            }

            @Override // com.lzy.okserver.ProgressListener
            public void onError(Progress p) {
                Intrinsics.checkNotNullParameter(p, "p");
                YunPlayActivity.access$getMBinding(YunPlayActivity.this).setProgress(p);
                YunPlayActivity yunPlayActivity = YunPlayActivity.this;
                String localizedMessage = p.exception.getLocalizedMessage();
                Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                yunPlayActivity.log(localizedMessage);
            }

            @Override // com.lzy.okserver.ProgressListener
            public void onFinish(File apk, Progress p) {
                Intrinsics.checkNotNullParameter(apk, "apk");
                Intrinsics.checkNotNullParameter(p, "p");
                YunPlayActivity.access$getMBinding(YunPlayActivity.this).setProgress(p);
                Util.installApk(YunPlayActivity.this.getMContext(), apk, YunPlayActivity.this.getDownloadTask());
                YunPlayActivity.this.finish();
            }

            @Override // com.lzy.okserver.ProgressListener
            public void onRemove(Progress p) {
                Intrinsics.checkNotNullParameter(p, "p");
                YunPlayActivity.access$getMBinding(YunPlayActivity.this).setProgress(p);
            }
        };
    }

    public final int getTimes() {
        return this.times;
    }

    public final void setTimes(int i) {
        this.times = i;
    }

    public final void getRemainingTime() {
        this.times++;
        int i = this.duration - 2;
        this.duration = i;
        int i2 = i / 60;
        getMBinding().f445tv.setText("试玩剩余时长：" + i2 + ":" + (i - (i2 * 60)));
        if (this.times == 30) {
            this.times = 0;
            Repository.INSTANCE.reportYunTimes(getGid(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity.getRemainingTime.1
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Result it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Result result) {
                    invoke2(result);
                    return Unit.INSTANCE;
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunPlayActivity.getRemainingTime.2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                    invoke2(exc);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Exception it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    YunPlayActivity yunPlayActivity = YunPlayActivity.this;
                    String localizedMessage = it.getLocalizedMessage();
                    Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                    yunPlayActivity.log(localizedMessage);
                }
            });
        }
    }
}
