package com.cy.yyjia.zhe28.wxapi;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.wxapi.WXEntryActivity$receiver$2;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.open.SocialConstants;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: WXEntryActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000C\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\f\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0011H\u0014J\u0012\u0010\u0015\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\u0010\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001dH\u0016R#\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000e¨\u0006\u001e"}, d2 = {"Lcom/cy/yyjia/zhe28/wxapi/WXEntryActivity;", "Landroid/app/Activity;", "Lcom/tencent/mm/opensdk/openapi/IWXAPIEventHandler;", "()V", "api", "Lcom/tencent/mm/opensdk/openapi/IWXAPI;", "kotlin.jvm.PlatformType", "getApi", "()Lcom/tencent/mm/opensdk/openapi/IWXAPI;", "api$delegate", "Lkotlin/Lazy;", SocialConstants.PARAM_RECEIVER, "com/cy/yyjia/zhe28/wxapi/WXEntryActivity$receiver$2$1", "getReceiver", "()Lcom/cy/yyjia/zhe28/wxapi/WXEntryActivity$receiver$2$1;", "receiver$delegate", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onNewIntent", "intent", "Landroid/content/Intent;", "onReq", "baseReq", "Lcom/tencent/mm/opensdk/modelbase/BaseReq;", "onResp", "resp", "Lcom/tencent/mm/opensdk/modelbase/BaseResp;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: api$delegate, reason: from kotlin metadata */
    private final Lazy api = LazyKt.lazy(new Function0<IWXAPI>() { // from class: com.cy.yyjia.zhe28.wxapi.WXEntryActivity$api$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final IWXAPI invoke() {
            return WXAPIFactory.createWXAPI(this.this$0, Constant.INSTANCE.getWxAppId(), true);
        }
    });

    /* JADX INFO: renamed from: receiver$delegate, reason: from kotlin metadata */
    private final Lazy receiver = LazyKt.lazy(new Function0<WXEntryActivity$receiver$2.AnonymousClass1>() { // from class: com.cy.yyjia.zhe28.wxapi.WXEntryActivity$receiver$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Type inference failed for: r0v0, types: [com.cy.yyjia.zhe28.wxapi.WXEntryActivity$receiver$2$1] */
        @Override // kotlin.jvm.functions.Function0
        public final AnonymousClass1 invoke() {
            final WXEntryActivity wXEntryActivity = this.this$0;
            return new BroadcastReceiver() { // from class: com.cy.yyjia.zhe28.wxapi.WXEntryActivity$receiver$2.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    wXEntryActivity.getApi().registerApp(Constant.INSTANCE.getWxAppId());
                }
            };
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public final IWXAPI getApi() {
        return (IWXAPI) this.api.getValue();
    }

    private final WXEntryActivity$receiver$2.AnonymousClass1 getReceiver() {
        return (WXEntryActivity$receiver$2.AnonymousClass1) this.receiver.getValue();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApi().registerApp(Constant.INSTANCE.getWxAppId());
        Intent intent = getIntent();
        if (intent != null) {
            getApi().handleIntent(intent, this);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            registerReceiver(getReceiver(), new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP), 4);
        } else {
            registerReceiver(getReceiver(), new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        getApi().handleIntent(intent, this);
    }

    @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
    public void onReq(BaseReq baseReq) {
        Intrinsics.checkNotNullParameter(baseReq, "baseReq");
        Log.e("onReq: ", baseReq.toString());
        finish();
    }

    @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
    public void onResp(BaseResp resp) {
        Intrinsics.checkNotNullParameter(resp, "resp");
        Log.e("onResp: ", "type=" + resp.getType() + " errCode=" + resp.errCode + " errStr=" + resp.errStr + " openId=" + resp.openId + " transaction=" + resp.transaction + StringUtils.SPACE);
        if (resp.errCode == 0) {
            try {
                String str = ((SendAuth.Resp) resp).code;
                Constant constant = Constant.INSTANCE;
                Intrinsics.checkNotNull(str);
                constant.setWxLoginCode(str);
                Unit unit = Unit.INSTANCE;
            } catch (Exception e) {
                Integer.valueOf(Log.e("onResp: ", e.getLocalizedMessage()));
            }
        }
        finish();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(getReceiver());
    }
}
