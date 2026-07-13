package com.cy.yyjia.zhe28.util;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.ShareInfo;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.DefaultUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: ShareTool.kt */
/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u001e\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r¨\u0006\u000e"}, d2 = {"Lcom/cy/yyjia/zhe28/util/ShareTool;", "", "()V", "shareToQQ", "", "context", "Landroid/app/Activity;", "shareInfo", "Lcom/cy/yyjia/zhe28/domain/ShareInfo;", "shareToQzone", "shareToWechat", "Landroid/content/Context;", "mTargetScene", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ShareTool {
    public static final int $stable = 0;
    public static final ShareTool INSTANCE = new ShareTool();

    private ShareTool() {
    }

    public final void shareToWechat(Context context, ShareInfo shareInfo, int mTargetScene) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(shareInfo, "shareInfo");
        if (TextUtils.isEmpty(shareInfo.getImgUrl())) {
            Toast.makeText(context, "分享图标未设置！", 0).show();
        } else {
            BuildersKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), (CoroutineStart) null, new C02191(context, shareInfo, mTargetScene, null), 2, (Object) null);
        }
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.util.ShareTool$shareToWechat$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ShareTool.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.cy.yyjia.zhe28.util.ShareTool$shareToWechat$1", f = "ShareTool.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02191 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ int $mTargetScene;
        final /* synthetic */ ShareInfo $shareInfo;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02191(Context context, ShareInfo shareInfo, int i, Continuation<? super C02191> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$shareInfo = shareInfo;
            this.$mTargetScene = i;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C02191(this.$context, this.$shareInfo, this.$mTargetScene, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            String wxAppId = Constant.INSTANCE.getWxAppId();
            IWXAPI iwxapiCreateWXAPI = WXAPIFactory.createWXAPI(this.$context, wxAppId);
            iwxapiCreateWXAPI.registerApp(wxAppId);
            WXWebpageObject wXWebpageObject = new WXWebpageObject();
            wXWebpageObject.webpageUrl = this.$shareInfo.getUrl();
            WXMediaMessage wXMediaMessage = new WXMediaMessage(wXWebpageObject);
            wXMediaMessage.title = this.$shareInfo.getTitle();
            wXMediaMessage.description = this.$shareInfo.getDescribe();
            wXMediaMessage.thumbData = Util.bmpToByteArray(this.$shareInfo.getImgUrl());
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.message = wXMediaMessage;
            req.scene = this.$mTargetScene;
            iwxapiCreateWXAPI.sendReq(req);
            NetUtil.post2$default(NetUtil.INSTANCE, "task/shareTask", new LinkedHashMap(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.util.ShareTool.shareToWechat.1.1
                public final void invoke(Result result) {
                    Intrinsics.checkNotNullParameter(result, "it");
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    invoke((Result) obj2);
                    return Unit.INSTANCE;
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.util.ShareTool.shareToWechat.1.2
                public final void invoke(Exception exc) {
                    Intrinsics.checkNotNullParameter(exc, "it");
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    invoke((Exception) obj2);
                    return Unit.INSTANCE;
                }
            }, null, 16, null);
            return Unit.INSTANCE;
        }
    }

    public final void shareToQQ(Activity context, ShareInfo shareInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(shareInfo, "shareInfo");
        Tencent.setIsPermissionGranted(true);
        Tencent tencentCreateInstance = Tencent.createInstance(Constant.INSTANCE.getQqAppId(), context.getApplicationContext());
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 1);
        bundle.putString("title", shareInfo.getTitle());
        bundle.putString("summary", shareInfo.getDescribe());
        bundle.putString("targetUrl", shareInfo.getUrl());
        bundle.putString("imageUrl", shareInfo.getImgUrl());
        bundle.putString("appName", context.getResources().getString(2131755037));
        tencentCreateInstance.shareToQQ(context, bundle, new DefaultUiListener() { // from class: com.cy.yyjia.zhe28.util.ShareTool.shareToQQ.1
            @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
            public void onComplete(Object o) {
                Intrinsics.checkNotNullParameter(o, "o");
                super.onComplete(o);
            }

            @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
            public void onError(UiError uiError) {
                Intrinsics.checkNotNullParameter(uiError, "uiError");
                super.onError(uiError);
                Log.e("onError: ", uiError.toString());
            }

            @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
            public void onCancel() {
                super.onCancel();
            }

            @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
            public void onWarning(int i) {
                super.onWarning(i);
            }
        });
    }

    public final void shareToQzone(Activity context, ShareInfo shareInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(shareInfo, "shareInfo");
        Tencent tencentCreateInstance = Tencent.createInstance(Constant.INSTANCE.getQqAppId(), context.getApplicationContext());
        Bundle bundle = new Bundle();
        ArrayList<String> arrayList = new ArrayList<>();
        String imgUrl = shareInfo.getImgUrl();
        Intrinsics.checkNotNull(imgUrl);
        arrayList.add(imgUrl);
        bundle.putInt("req_type", 1);
        bundle.putString("title", shareInfo.getTitle());
        bundle.putString("summary", shareInfo.getDescribe());
        bundle.putString("targetUrl", shareInfo.getUrl());
        bundle.putStringArrayList("imageUrl", arrayList);
        bundle.putString("appName", context.getResources().getString(2131755037));
        tencentCreateInstance.shareToQzone(context, bundle, new DefaultUiListener() { // from class: com.cy.yyjia.zhe28.util.ShareTool.shareToQzone.1
            @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
            public void onComplete(Object o) {
                Intrinsics.checkNotNullParameter(o, "o");
                super.onComplete(o);
            }

            @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
            public void onError(UiError uiError) {
                Intrinsics.checkNotNullParameter(uiError, "uiError");
                super.onError(uiError);
                Log.e("onError: ", uiError.toString());
            }

            @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
            public void onCancel() {
                super.onCancel();
            }

            @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
            public void onWarning(int i) {
                super.onWarning(i);
            }
        });
    }
}
