package com.cy.yyjia.zhe28.util;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.cy.yyjia.zhe28.domain.BaseResult;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.ui.activity.AuthActivity;
import com.cy.yyjia.zhe28.ui.activity.LoginActivity;
import com.cy.yyjia.zhe28.ui.activity.SafeActivity;
import com.cy.yyjia.zhe28.ui.activity.WebActivity2;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.utils.OkLogger;
import com.volcengine.cloudcore.common.mode.KeyBoardKey;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import org.json.JSONObject;

/* JADX INFO: compiled from: NetUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\bÇ\u0002\u0018\u00002\u00020\u0001:\u00014B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0082\u0001\u0010\u0007\u001a\u00020\b\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\u00012\u0006\u0010\n\u001a\u00020\u00042\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\f2#\b\u0004\u0010\r\u001a\u001d\u0012\u0013\u0012\u0011H\t¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00120\u000e2'\b\u0004\u0010\u0013\u001a!\u0012\u0017\u0012\u00150\u0014j\u0002`\u0015¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00120\u000eH\u0086\bø\u0001\u0000Jl\u0010\u0017\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00042\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\f2!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00120\u000e2%\u0010\u0013\u001a!\u0012\u0017\u0012\u00150\u0014j\u0002`\u0015¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00120\u000eJ\u008c\u0001\u0010\u0019\u001a\u00020\b\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\u00012\u0006\u0010\n\u001a\u00020\u00042\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\f2#\b\u0004\u0010\r\u001a\u001d\u0012\u0013\u0012\u0011H\t¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00120\u000e2'\b\u0004\u0010\u0013\u001a!\u0012\u0017\u0012\u00150\u0014j\u0002`\u0015¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00120\u000e2\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u0086\bø\u0001\u0000J\u000e\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020!J\u0082\u0001\u0010\"\u001a\u00020\b\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\u00012\u0006\u0010\n\u001a\u00020\u00042\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\f2#\b\u0004\u0010\r\u001a\u001d\u0012\u0013\u0012\u0011H\t¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00120\u000e2'\b\u0004\u0010\u0013\u001a!\u0012\u0017\u0012\u00150\u0014j\u0002`\u0015¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00120\u000eH\u0086\bø\u0001\u0000J\u008a\u0001\u0010#\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00042\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\f2!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00120\u000e2%\u0010\u0013\u001a!\u0012\u0017\u0012\u00150\u0014j\u0002`\u0015¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00120\u000e2\u001c\b\u0002\u0010$\u001a\u0016\u0012\u0004\u0012\u00020&\u0018\u00010%j\n\u0012\u0004\u0012\u00020&\u0018\u0001`'J|\u0010(\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010)\u001a\u00020&2\u0006\u0010*\u001a\u00020\u00042!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00120\u000e2%\u0010\u0013\u001a!\u0012\u0017\u0012\u00150\u0014j\u0002`\u0015¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00120\u000e2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\fJª\u0001\u0010+\u001a\u00020\b\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\u00012\u0006\u0010\n\u001a\u00020\u00042\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\f2#\b\u0004\u0010\r\u001a\u001d\u0012\u0013\u0012\u0011H\t¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00120\u000e2'\b\u0004\u0010\u0013\u001a!\u0012\u0017\u0012\u00150\u0014j\u0002`\u0015¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00120\u000e2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\u001c\b\u0002\u0010$\u001a\u0016\u0012\u0004\u0012\u00020&\u0018\u00010%j\n\u0012\u0004\u0012\u00020&\u0018\u0001`'H\u0086\bø\u0001\u0000JA\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\t0,\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\u00012\u0006\u0010\n\u001a\u00020\u00042\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\f2\b\b\u0002\u0010\u0007\u001a\u00020\u001bH\u0086\bJi\u0010-\u001a\b\u0012\u0004\u0012\u0002H\t0,\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\u00012\u0006\u0010\n\u001a\u00020\u00042\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\f2\b\b\u0002\u0010\u0007\u001a\u00020\u001b2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\u001c\b\u0002\u0010$\u001a\u0016\u0012\u0004\u0012\u00020&\u0018\u00010%j\n\u0012\u0004\u0012\u00020&\u0018\u0001`'H\u0086\bJP\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00180,2\u0006\u0010\n\u001a\u00020\u00042\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\f2\b\b\u0002\u0010\u0007\u001a\u00020\u001b2\u001c\b\u0002\u0010$\u001a\u0016\u0012\u0004\u0012\u00020&\u0018\u00010%j\n\u0012\u0004\u0012\u00020&\u0018\u0001`'J8\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00180,2\u0006\u0010\n\u001a\u00020\u00042\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\f2\u0006\u0010)\u001a\u00020&2\u0006\u0010*\u001a\u00020\u0004J\u0006\u00100\u001a\u00020\u0012J\u000e\u00101\u001a\u00020\u00122\u0006\u00102\u001a\u00020\u0004J\u000e\u00103\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u00065"}, d2 = {"Lcom/cy/yyjia/zhe28/util/NetUtil;", "", "()V", "BASE_URL1", "", "BASE_URL2", "BASE_URL3", "get", "Lkotlinx/coroutines/Job;", ExifInterface.GPS_DIRECTION_TRUE, "url", "param", "", "success", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "data", "", "fail", "Ljava/lang/Exception;", "Lkotlin/Exception;", "e", "get2", "Lcom/cy/yyjia/zhe28/domain/Result;", "getOther", "useToken", "", "getRequestParam", "request", "Lokhttp3/Request;", "isConnected", "mContext", "Landroid/content/Context;", "post", "post2", "fileList", "Ljava/util/ArrayList;", "Ljava/io/File;", "Lkotlin/collections/ArrayList;", "post3", "file", Progress.FILE_NAME, "postOther", "Lcom/cy/yyjia/zhe28/util/NetUtil$RequestResult;", "requestOther", "requestResult", "requestResult2", "toLogin", "toOther", "msg", "toUrl", "RequestResult", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class NetUtil {
    public static final int $stable = 0;
    public static final NetUtil INSTANCE = new NetUtil();
    public static final String BASE_URL1 = "https://mobile.28zhe.com/api/v1/";
    public static final String BASE_URL2 = "https://mobile.28zhe.com/api/v2/";
    public static final String BASE_URL3 = "https://www.28zhe.com/";

    private NetUtil() {
    }

    /* JADX INFO: compiled from: NetUtil.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0002\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/cy/yyjia/zhe28/util/NetUtil$RequestResult;", "R", "", "()V", "Error", "Success", "Lcom/cy/yyjia/zhe28/util/NetUtil$RequestResult$Error;", "Lcom/cy/yyjia/zhe28/util/NetUtil$RequestResult$Success;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class RequestResult<R> {
        public static final int $stable = 0;

        public /* synthetic */ RequestResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: compiled from: NetUtil.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\u0006J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u0001HÆ\u0001¢\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0013\u0010\u0003\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, d2 = {"Lcom/cy/yyjia/zhe28/util/NetUtil$RequestResult$Success;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/cy/yyjia/zhe28/util/NetUtil$RequestResult;", "data", "(Ljava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lcom/cy/yyjia/zhe28/util/NetUtil$RequestResult$Success;", "equals", "", "other", "", "hashCode", "", "toString", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Success<T> extends RequestResult<T> {
            public static final int $stable = 0;
            private final T data;

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Success copy$default(Success success, Object obj, int i, Object obj2) {
                if ((i & 1) != 0) {
                    obj = success.data;
                }
                return success.copy(obj);
            }

            public final T component1() {
                return this.data;
            }

            public final Success<T> copy(T data) {
                return new Success<>(data);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Success) && Intrinsics.areEqual(this.data, ((Success) other).data);
            }

            public int hashCode() {
                T t = this.data;
                if (t == null) {
                    return 0;
                }
                return t.hashCode();
            }

            public String toString() {
                return "Success(data=" + this.data + ")";
            }

            public Success(T t) {
                super(null);
                this.data = t;
            }

            public final T getData() {
                return this.data;
            }
        }

        private RequestResult() {
        }

        /* JADX INFO: compiled from: NetUtil.kt */
        @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\u0012\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005¢\u0006\u0002\u0010\u0006J\r\u0010\t\u001a\u00060\u0004j\u0002`\u0005HÆ\u0003J\u0017\u0010\n\u001a\u00020\u00002\f\b\u0002\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0015\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/cy/yyjia/zhe28/util/NetUtil$RequestResult$Error;", "Lcom/cy/yyjia/zhe28/util/NetUtil$RequestResult;", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Ljava/lang/Exception;)V", "getE", "()Ljava/lang/Exception;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Error extends RequestResult {
            public static final int $stable = 8;
            private final Exception e;

            public static /* synthetic */ Error copy$default(Error error, Exception exc, int i, Object obj) {
                if ((i & 1) != 0) {
                    exc = error.e;
                }
                return error.copy(exc);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final Exception getE() {
                return this.e;
            }

            public final Error copy(Exception e) {
                Intrinsics.checkNotNullParameter(e, "e");
                return new Error(e);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Error) && Intrinsics.areEqual(this.e, ((Error) other).e);
            }

            public int hashCode() {
                return this.e.hashCode();
            }

            public String toString() {
                return "Error(e=" + this.e + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Error(Exception e) {
                super(null);
                Intrinsics.checkNotNullParameter(e, "e");
                this.e = e;
            }

            public final Exception getE() {
                return this.e;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ RequestResult request$default(NetUtil netUtil, String url, Map param, boolean z, int i, Object obj) {
        Response responseExecute;
        if ((i & 4) != 0) {
            z = true;
        }
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(param, "param");
        if (!StringsKt.startsWith$default(url, "http", false, 2, (Object) null)) {
            url = BASE_URL1 + url;
        }
        String str = "";
        for (String str2 : param.keySet()) {
            str = str + "&" + str2 + "=" + param.get(str2);
        }
        try {
            String token = Constant.INSTANCE.getToken();
            if (z) {
                responseExecute = ((GetRequest) ((GetRequest) OkGo.get(url).headers("Authorization", "bearer " + token)).params(param, new boolean[0])).execute();
                Intrinsics.checkNotNull(responseExecute);
            } else {
                responseExecute = ((PostRequest) ((PostRequest) OkGo.post(url).headers("Authorization", "bearer " + token)).params((Map<String, String>) param, new boolean[0])).execute();
                Intrinsics.checkNotNull(responseExecute);
            }
            ResponseBody responseBodyBody = responseExecute.body();
            String strString = responseBodyBody != null ? responseBodyBody.string() : null;
            if (z) {
                Log.e("request: ", "请求接口" + responseExecute.request().url());
            } else {
                Log.e("request: ", "请求接口" + responseExecute.request().url() + "\n请求参数" + netUtil.getRequestParam(responseExecute.request()));
            }
            Log.e("request: ", "请求结果" + strString);
            if (strString != null) {
                if (responseExecute.isSuccessful()) {
                    JSONObject jSONObject = new JSONObject(strString);
                    int i2 = jSONObject.getInt("code");
                    String string = jSONObject.getString("msg");
                    if (i2 == 200) {
                        Intrinsics.needClassReification();
                        Object objFromJson = new Gson().fromJson(strString, new NetUtil$request$type$1().getType());
                        Intrinsics.checkNotNullExpressionValue(objFromJson, "fromJson(...)");
                        return new RequestResult.Success(((BaseResult) objFromJson).getData());
                    }
                    if (i2 == 403) {
                        netUtil.toLogin();
                        return new RequestResult.Error(new Exception(string));
                    }
                    if (i2 == 4004) {
                        String string2 = jSONObject.getString("data");
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                        netUtil.toUrl(string2);
                        return new RequestResult.Error(new Exception(string));
                    }
                    Intrinsics.checkNotNull(string);
                    netUtil.toOther(string);
                    return new RequestResult.Error(new Exception(string));
                }
                return new RequestResult.Error(new Exception("fail" + responseExecute.code()));
            }
            return new RequestResult.Error(new Exception("返回为空"));
        } catch (Exception e) {
            return new RequestResult.Error(e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ <T> RequestResult<T> request(String url, Map<String, String> param, boolean get) {
        Response responseExecute;
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(param, "param");
        if (!StringsKt.startsWith$default(url, "http", false, 2, (Object) null)) {
            url = BASE_URL1 + url;
        }
        String str = "";
        for (String str2 : param.keySet()) {
            str = str + "&" + str2 + "=" + ((Object) param.get(str2));
        }
        try {
            String token = Constant.INSTANCE.getToken();
            if (get) {
                responseExecute = ((GetRequest) ((GetRequest) OkGo.get(url).headers("Authorization", "bearer " + token)).params(param, new boolean[0])).execute();
                Intrinsics.checkNotNull(responseExecute);
            } else {
                responseExecute = ((PostRequest) ((PostRequest) OkGo.post(url).headers("Authorization", "bearer " + token)).params(param, new boolean[0])).execute();
                Intrinsics.checkNotNull(responseExecute);
            }
            ResponseBody responseBodyBody = responseExecute.body();
            String strString = responseBodyBody != null ? responseBodyBody.string() : null;
            if (get) {
                Log.e("request: ", "请求接口" + responseExecute.request().url());
            } else {
                Log.e("request: ", "请求接口" + responseExecute.request().url() + "\n请求参数" + getRequestParam(responseExecute.request()));
            }
            Log.e("request: ", "请求结果" + strString);
            if (strString != null) {
                if (responseExecute.isSuccessful()) {
                    JSONObject jSONObject = new JSONObject(strString);
                    int i = jSONObject.getInt("code");
                    String string = jSONObject.getString("msg");
                    if (i == 200) {
                        Intrinsics.needClassReification();
                        Object objFromJson = new Gson().fromJson(strString, new NetUtil$request$type$1().getType());
                        Intrinsics.checkNotNullExpressionValue(objFromJson, "fromJson(...)");
                        return new RequestResult.Success(((BaseResult) objFromJson).getData());
                    }
                    if (i == 403) {
                        toLogin();
                        return new RequestResult.Error(new Exception(string));
                    }
                    if (i == 4004) {
                        String string2 = jSONObject.getString("data");
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                        toUrl(string2);
                        return new RequestResult.Error(new Exception(string));
                    }
                    Intrinsics.checkNotNull(string);
                    toOther(string);
                    return new RequestResult.Error(new Exception(string));
                }
                return new RequestResult.Error(new Exception("fail" + responseExecute.code()));
            }
            return new RequestResult.Error(new Exception("返回为空"));
        } catch (Exception e) {
            return new RequestResult.Error(e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ RequestResult requestOther$default(NetUtil netUtil, String url, Map param, boolean z, boolean z2, ArrayList arrayList, int i, Object obj) {
        Response responseExecute;
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        if ((i & 16) != 0) {
            arrayList = new ArrayList();
        }
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(param, "param");
        if (!StringsKt.startsWith$default(url, "http", false, 2, (Object) null)) {
            url = BASE_URL1 + url;
        }
        String str = "";
        for (String str2 : param.keySet()) {
            str = str + "&" + str2 + "=" + param.get(str2);
        }
        try {
            String token = Constant.INSTANCE.getToken();
            if (z) {
                GetRequest getRequest = OkGo.get(url);
                if (z2) {
                    getRequest.headers("Authorization", "bearer " + token);
                }
                responseExecute = ((GetRequest) getRequest.params(param, new boolean[0])).execute();
                Intrinsics.checkNotNull(responseExecute);
            } else {
                PostRequest postRequestPost = OkGo.post(url);
                if (z2) {
                    postRequestPost.headers("Authorization", "bearer " + token);
                }
                Integer numValueOf = arrayList != null ? Integer.valueOf(arrayList.size()) : null;
                Intrinsics.checkNotNull(numValueOf);
                if (numValueOf.intValue() > 0) {
                    int size = arrayList.size();
                    int i2 = 0;
                    while (i2 < size) {
                        int i3 = i2 + 1;
                        postRequestPost.params("file" + i3, (File) arrayList.get(i2));
                        i2 = i3;
                    }
                }
                responseExecute = ((PostRequest) postRequestPost.params((Map<String, String>) param, new boolean[0])).execute();
                Intrinsics.checkNotNull(responseExecute);
            }
            ResponseBody responseBodyBody = responseExecute.body();
            String strString = responseBodyBody != null ? responseBodyBody.string() : null;
            if (z) {
                Log.e("request: ", "请求接口" + responseExecute.request().url());
            } else {
                Log.e("request: ", "请求接口" + responseExecute.request().url() + "\n请求参数" + netUtil.getRequestParam(responseExecute.request()));
            }
            Log.e("request: ", "请求结果" + strString);
            if (strString != null) {
                if (responseExecute.isSuccessful()) {
                    Intrinsics.needClassReification();
                    Object objFromJson = new Gson().fromJson(strString, new NetUtil$requestOther$type$1().getType());
                    Intrinsics.checkNotNullExpressionValue(objFromJson, "fromJson(...)");
                    return new RequestResult.Success(objFromJson);
                }
                return new RequestResult.Error(new Exception("fail" + responseExecute.code()));
            }
            return new RequestResult.Error(new Exception("返回为空"));
        } catch (Exception e) {
            return new RequestResult.Error(e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ <T> RequestResult<T> requestOther(String url, Map<String, String> param, boolean get, boolean useToken, ArrayList<File> fileList) {
        Response responseExecute;
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(param, "param");
        if (!StringsKt.startsWith$default(url, "http", false, 2, (Object) null)) {
            url = BASE_URL1 + url;
        }
        String str = "";
        for (String str2 : param.keySet()) {
            str = str + "&" + str2 + "=" + ((Object) param.get(str2));
        }
        try {
            String token = Constant.INSTANCE.getToken();
            if (get) {
                GetRequest getRequest = OkGo.get(url);
                if (useToken) {
                    getRequest.headers("Authorization", "bearer " + token);
                }
                responseExecute = ((GetRequest) getRequest.params(param, new boolean[0])).execute();
                Intrinsics.checkNotNull(responseExecute);
            } else {
                PostRequest postRequestPost = OkGo.post(url);
                if (useToken) {
                    postRequestPost.headers("Authorization", "bearer " + token);
                }
                Integer numValueOf = fileList != null ? Integer.valueOf(fileList.size()) : null;
                Intrinsics.checkNotNull(numValueOf);
                if (numValueOf.intValue() > 0) {
                    int size = fileList.size();
                    int i = 0;
                    while (i < size) {
                        int i2 = i + 1;
                        postRequestPost.params("file" + i2, fileList.get(i));
                        i = i2;
                    }
                }
                responseExecute = ((PostRequest) postRequestPost.params(param, new boolean[0])).execute();
                Intrinsics.checkNotNull(responseExecute);
            }
            ResponseBody responseBodyBody = responseExecute.body();
            String strString = responseBodyBody != null ? responseBodyBody.string() : null;
            if (get) {
                Log.e("request: ", "请求接口" + responseExecute.request().url());
            } else {
                Log.e("request: ", "请求接口" + responseExecute.request().url() + "\n请求参数" + getRequestParam(responseExecute.request()));
            }
            Log.e("request: ", "请求结果" + strString);
            if (strString != null) {
                if (responseExecute.isSuccessful()) {
                    Intrinsics.needClassReification();
                    Object objFromJson = new Gson().fromJson(strString, new NetUtil$requestOther$type$1().getType());
                    Intrinsics.checkNotNullExpressionValue(objFromJson, "fromJson(...)");
                    return new RequestResult.Success(objFromJson);
                }
                return new RequestResult.Error(new Exception("fail" + responseExecute.code()));
            }
            return new RequestResult.Error(new Exception("返回为空"));
        } catch (Exception e) {
            return new RequestResult.Error(e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ RequestResult requestResult$default(NetUtil netUtil, String str, Map map, boolean z, ArrayList arrayList, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            arrayList = new ArrayList();
        }
        return netUtil.requestResult(str, map, z, arrayList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final RequestResult<Result> requestResult(String url, Map<String, String> param, boolean get, ArrayList<File> fileList) {
        Response responseExecute;
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(param, "param");
        int i = 0;
        if (!StringsKt.startsWith$default(url, "http", false, 2, (Object) null)) {
            url = BASE_URL1 + url;
        }
        String str = "";
        for (String str2 : param.keySet()) {
            str = str + "&" + str2 + "=" + ((Object) param.get(str2));
        }
        try {
            if (get) {
                responseExecute = ((GetRequest) ((GetRequest) OkGo.get(url).headers("Authorization", "bearer " + Constant.INSTANCE.getToken())).params(param, new boolean[0])).execute();
                Intrinsics.checkNotNull(responseExecute);
            } else {
                PostRequest postRequest = (PostRequest) ((PostRequest) OkGo.post(url).headers("Authorization", "bearer " + Constant.INSTANCE.getToken())).params(param, new boolean[0]);
                Integer numValueOf = fileList != null ? Integer.valueOf(fileList.size()) : null;
                Intrinsics.checkNotNull(numValueOf);
                if (numValueOf.intValue() > 0) {
                    int size = fileList.size();
                    while (i < size) {
                        int i2 = i + 1;
                        postRequest.params("file" + i2, fileList.get(i));
                        i = i2;
                    }
                }
                responseExecute = postRequest.execute();
                Intrinsics.checkNotNull(responseExecute);
            }
            ResponseBody responseBodyBody = responseExecute.body();
            String strString = responseBodyBody != null ? responseBodyBody.string() : null;
            if (get) {
                Log.e("request: ", "请求接口" + responseExecute.request().url());
            } else {
                Log.e("request: ", "请求接口" + responseExecute.request().url() + "\n请求参数" + getRequestParam(responseExecute.request()));
            }
            Log.e("request: ", "请求结果" + strString);
            if (strString != null) {
                if (responseExecute.isSuccessful()) {
                    JSONObject jSONObject = new JSONObject(strString);
                    int i3 = jSONObject.getInt("code");
                    String string = jSONObject.getString("msg");
                    if (i3 == 200) {
                        Object objFromJson = new Gson().fromJson(strString, (Class<Object>) Result.class);
                        Intrinsics.checkNotNullExpressionValue(objFromJson, "fromJson(...)");
                        return new RequestResult.Success((Result) objFromJson);
                    }
                    if (i3 == 403) {
                        toLogin();
                        return new RequestResult.Error(new Exception(string));
                    }
                    if (i3 == 4004) {
                        String string2 = jSONObject.getString("data");
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                        toUrl(string2);
                        return new RequestResult.Error(new Exception(string));
                    }
                    Intrinsics.checkNotNull(string);
                    toOther(string);
                    return new RequestResult.Error(new Exception(string));
                }
                return new RequestResult.Error(new Exception("fail" + responseExecute.code()));
            }
            return new RequestResult.Error(new Exception("返回为空"));
        } catch (Exception e) {
            return new RequestResult.Error(e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final RequestResult<Result> requestResult2(String url, Map<String, String> param, File file, String fileName) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(param, "param");
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        if (!StringsKt.startsWith$default(url, "http", false, 2, (Object) null)) {
            url = BASE_URL1 + url;
        }
        String str = "";
        for (String str2 : param.keySet()) {
            str = str + "&" + str2 + "=" + ((Object) param.get(str2));
        }
        try {
            Response responseExecute = ((PostRequest) ((PostRequest) OkGo.post(url).headers("Authorization", "bearer " + Constant.INSTANCE.getToken())).params(param, new boolean[0])).params(fileName, file).execute();
            Intrinsics.checkNotNullExpressionValue(responseExecute, "execute(...)");
            ResponseBody responseBodyBody = responseExecute.body();
            String strString = responseBodyBody != null ? responseBodyBody.string() : null;
            Log.e("request: ", "请求接口" + responseExecute.request().url());
            Log.e("request: ", "请求结果" + strString);
            if (strString != null) {
                if (responseExecute.isSuccessful()) {
                    JSONObject jSONObject = new JSONObject(strString);
                    int i = jSONObject.getInt("code");
                    String string = jSONObject.getString("msg");
                    if (i == 200) {
                        Object objFromJson = new Gson().fromJson(strString, (Class<Object>) Result.class);
                        Intrinsics.checkNotNullExpressionValue(objFromJson, "fromJson(...)");
                        return new RequestResult.Success((Result) objFromJson);
                    }
                    if (i == 403) {
                        toLogin();
                        return new RequestResult.Error(new Exception(string));
                    }
                    if (i == 4004) {
                        String string2 = jSONObject.getString("data");
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                        toUrl(string2);
                        return new RequestResult.Error(new Exception(string));
                    }
                    Intrinsics.checkNotNull(string);
                    toOther(string);
                    return new RequestResult.Error(new Exception(string));
                }
                return new RequestResult.Error(new Exception("fail" + responseExecute.code()));
            }
            return new RequestResult.Error(new Exception("返回为空"));
        } catch (Exception e) {
            return new RequestResult.Error(e);
        }
    }

    public final void toOther(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        String str = msg;
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "绑定手机", false, 2, (Object) null)) {
            Context context = OkGo.getInstance().getContext();
            Intent intent = new Intent(context, (Class<?>) SafeActivity.class);
            intent.setFlags(268435456);
            context.startActivity(intent);
            return;
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "实名", false, 2, (Object) null)) {
            Context context2 = OkGo.getInstance().getContext();
            Intent intent2 = new Intent(context2, (Class<?>) AuthActivity.class);
            intent2.setFlags(268435456);
            context2.startActivity(intent2);
        }
    }

    public final void toLogin() {
        Context context = OkGo.getInstance().getContext();
        Intent intent = new Intent(context, (Class<?>) LoginActivity.class);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public final void toUrl(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        Context context = OkGo.getInstance().getContext();
        Intent intent = new Intent(context, (Class<?>) WebActivity2.class);
        intent.putExtra("url", url);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public final String getRequestParam(Request request) {
        Intrinsics.checkNotNullParameter(request, "request");
        try {
            RequestBody requestBodyBody = request.newBuilder().build().body();
            if (requestBodyBody == null) {
                return "";
            }
            Buffer buffer = new Buffer();
            requestBodyBody.writeTo(buffer);
            Charset UTF_8 = StandardCharsets.UTF_8;
            Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
            return buffer.readString(UTF_8);
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
            return "";
        }
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.util.NetUtil$get$1, reason: invalid class name */
    /* JADX INFO: compiled from: NetUtil.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = KeyBoardKey.KeyboardKeyMediaNextTrack)
    @DebugMetadata(c = "com.cy.yyjia.zhe28.util.NetUtil$get$1", f = "NetUtil.kt", i = {}, l = {382}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Exception, Unit> $fail;
        final /* synthetic */ Map<String, String> $param;
        final /* synthetic */ Function1<T, Unit> $success;
        final /* synthetic */ String $url;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Function1<? super T, Unit> function1, Function1<? super Exception, Unit> function12, String str, Map<String, String> map, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$success = function1;
            this.$fail = function12;
            this.$url = str;
            this.$param = map;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$success, this.$fail, this.$url, this.$param, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io2 = Dispatchers.getIO();
                Intrinsics.needClassReification();
                this.label = 1;
                obj = BuildersKt.withContext(io2, new NetUtil$get$1$result$1(this.$url, this.$param, null), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            RequestResult requestResult = (RequestResult) obj;
            if (requestResult instanceof RequestResult.Success) {
                this.$success.invoke((T) ((RequestResult.Success) requestResult).getData());
            } else if (requestResult instanceof RequestResult.Error) {
                this.$fail.invoke(((RequestResult.Error) requestResult).getE());
            }
            return Unit.INSTANCE;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public final Object invokeSuspend$$forInline(Object obj) throws Throwable {
            CoroutineDispatcher io2 = Dispatchers.getIO();
            Intrinsics.needClassReification();
            InlineMarker.mark(0);
            Object objWithContext = BuildersKt.withContext(io2, new NetUtil$get$1$result$1(this.$url, this.$param, null), this);
            InlineMarker.mark(1);
            RequestResult requestResult = (RequestResult) objWithContext;
            if (requestResult instanceof RequestResult.Success) {
                this.$success.invoke((T) ((RequestResult.Success) requestResult).getData());
            } else if (requestResult instanceof RequestResult.Error) {
                this.$fail.invoke(((RequestResult.Error) requestResult).getE());
            }
            return Unit.INSTANCE;
        }
    }

    public final /* synthetic */ <T> Job get(String url, Map<String, String> param, Function1<? super T, Unit> success, Function1<? super Exception, Unit> fail) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(param, "param");
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(fail, "fail");
        CoroutineScope coroutineScopeMainScope = CoroutineScopeKt.MainScope();
        Intrinsics.needClassReification();
        return BuildersKt__Builders_commonKt.launch$default(coroutineScopeMainScope, null, null, new AnonymousClass1(success, fail, url, param, null), 3, null);
    }

    public static /* synthetic */ Job getOther$default(NetUtil netUtil, String url, Map param, Function1 success, Function1 fail, boolean z, int i, Object obj) {
        boolean z2 = (i & 16) != 0 ? false : z;
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(param, "param");
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(fail, "fail");
        CoroutineScope coroutineScopeMainScope = CoroutineScopeKt.MainScope();
        Intrinsics.needClassReification();
        return BuildersKt__Builders_commonKt.launch$default(coroutineScopeMainScope, null, null, new C11941(success, fail, url, param, z2, null), 3, null);
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.util.NetUtil$getOther$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: NetUtil.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = KeyBoardKey.KeyboardKeyMediaNextTrack)
    @DebugMetadata(c = "com.cy.yyjia.zhe28.util.NetUtil$getOther$1", f = "NetUtil.kt", i = {}, l = {398}, m = "invokeSuspend", n = {}, s = {})
    public static final class C11941 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Exception, Unit> $fail;
        final /* synthetic */ Map<String, String> $param;
        final /* synthetic */ Function1<T, Unit> $success;
        final /* synthetic */ String $url;
        final /* synthetic */ boolean $useToken;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C11941(Function1<? super T, Unit> function1, Function1<? super Exception, Unit> function12, String str, Map<String, String> map, boolean z, Continuation<? super C11941> continuation) {
            super(2, continuation);
            this.$success = function1;
            this.$fail = function12;
            this.$url = str;
            this.$param = map;
            this.$useToken = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C11941(this.$success, this.$fail, this.$url, this.$param, this.$useToken, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C11941) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io2 = Dispatchers.getIO();
                Intrinsics.needClassReification();
                this.label = 1;
                obj = BuildersKt.withContext(io2, new NetUtil$getOther$1$result$1(this.$url, this.$param, this.$useToken, null), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            RequestResult requestResult = (RequestResult) obj;
            if (requestResult instanceof RequestResult.Success) {
                this.$success.invoke((T) ((RequestResult.Success) requestResult).getData());
            } else if (requestResult instanceof RequestResult.Error) {
                this.$fail.invoke(((RequestResult.Error) requestResult).getE());
            }
            return Unit.INSTANCE;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public final Object invokeSuspend$$forInline(Object obj) throws Throwable {
            CoroutineDispatcher io2 = Dispatchers.getIO();
            Intrinsics.needClassReification();
            InlineMarker.mark(0);
            Object objWithContext = BuildersKt.withContext(io2, new NetUtil$getOther$1$result$1(this.$url, this.$param, this.$useToken, null), this);
            InlineMarker.mark(1);
            RequestResult requestResult = (RequestResult) objWithContext;
            if (requestResult instanceof RequestResult.Success) {
                this.$success.invoke((T) ((RequestResult.Success) requestResult).getData());
            } else if (requestResult instanceof RequestResult.Error) {
                this.$fail.invoke(((RequestResult.Error) requestResult).getE());
            }
            return Unit.INSTANCE;
        }
    }

    public final /* synthetic */ <T> Job getOther(String url, Map<String, String> param, Function1<? super T, Unit> success, Function1<? super Exception, Unit> fail, boolean useToken) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(param, "param");
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(fail, "fail");
        CoroutineScope coroutineScopeMainScope = CoroutineScopeKt.MainScope();
        Intrinsics.needClassReification();
        return BuildersKt__Builders_commonKt.launch$default(coroutineScopeMainScope, null, null, new C11941(success, fail, url, param, useToken, null), 3, null);
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.util.NetUtil$get2$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: NetUtil.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.cy.yyjia.zhe28.util.NetUtil$get2$1", f = "NetUtil.kt", i = {}, l = {413}, m = "invokeSuspend", n = {}, s = {})
    static final class C11931 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Exception, Unit> $fail;
        final /* synthetic */ Map<String, String> $param;
        final /* synthetic */ Function1<Result, Unit> $success;
        final /* synthetic */ String $url;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C11931(Function1<? super Result, Unit> function1, Function1<? super Exception, Unit> function12, String str, Map<String, String> map, Continuation<? super C11931> continuation) {
            super(2, continuation);
            this.$success = function1;
            this.$fail = function12;
            this.$url = str;
            this.$param = map;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C11931(this.$success, this.$fail, this.$url, this.$param, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C11931) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = BuildersKt.withContext(Dispatchers.getIO(), new NetUtil$get2$1$result$1(this.$url, this.$param, null), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            RequestResult requestResult = (RequestResult) obj;
            if (requestResult instanceof RequestResult.Success) {
                this.$success.invoke((Result) ((RequestResult.Success) requestResult).getData());
            } else if (requestResult instanceof RequestResult.Error) {
                this.$fail.invoke(((RequestResult.Error) requestResult).getE());
            }
            return Unit.INSTANCE;
        }
    }

    public final Job get2(String url, Map<String, String> param, Function1<? super Result, Unit> success, Function1<? super Exception, Unit> fail) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(param, "param");
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(fail, "fail");
        return BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new C11931(success, fail, url, param, null), 3, null);
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.util.NetUtil$post$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: NetUtil.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = KeyBoardKey.KeyboardKeyMediaNextTrack)
    @DebugMetadata(c = "com.cy.yyjia.zhe28.util.NetUtil$post$1", f = "NetUtil.kt", i = {}, l = {431}, m = "invokeSuspend", n = {}, s = {})
    public static final class C11951 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Exception, Unit> $fail;
        final /* synthetic */ Map<String, String> $param;
        final /* synthetic */ Function1<T, Unit> $success;
        final /* synthetic */ String $url;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C11951(Function1<? super T, Unit> function1, Function1<? super Exception, Unit> function12, String str, Map<String, String> map, Continuation<? super C11951> continuation) {
            super(2, continuation);
            this.$success = function1;
            this.$fail = function12;
            this.$url = str;
            this.$param = map;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C11951(this.$success, this.$fail, this.$url, this.$param, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C11951) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io2 = Dispatchers.getIO();
                Intrinsics.needClassReification();
                this.label = 1;
                obj = BuildersKt.withContext(io2, new NetUtil$post$1$result$1(this.$url, this.$param, null), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            RequestResult requestResult = (RequestResult) obj;
            if (requestResult instanceof RequestResult.Success) {
                this.$success.invoke((T) ((RequestResult.Success) requestResult).getData());
            } else if (requestResult instanceof RequestResult.Error) {
                this.$fail.invoke(((RequestResult.Error) requestResult).getE());
            }
            return Unit.INSTANCE;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public final Object invokeSuspend$$forInline(Object obj) throws Throwable {
            CoroutineDispatcher io2 = Dispatchers.getIO();
            Intrinsics.needClassReification();
            InlineMarker.mark(0);
            Object objWithContext = BuildersKt.withContext(io2, new NetUtil$post$1$result$1(this.$url, this.$param, null), this);
            InlineMarker.mark(1);
            RequestResult requestResult = (RequestResult) objWithContext;
            if (requestResult instanceof RequestResult.Success) {
                this.$success.invoke((T) ((RequestResult.Success) requestResult).getData());
            } else if (requestResult instanceof RequestResult.Error) {
                this.$fail.invoke(((RequestResult.Error) requestResult).getE());
            }
            return Unit.INSTANCE;
        }
    }

    public final /* synthetic */ <T> Job post(String url, Map<String, String> param, Function1<? super T, Unit> success, Function1<? super Exception, Unit> fail) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(param, "param");
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(fail, "fail");
        CoroutineScope coroutineScopeMainScope = CoroutineScopeKt.MainScope();
        Intrinsics.needClassReification();
        return BuildersKt__Builders_commonKt.launch$default(coroutineScopeMainScope, null, null, new C11951(success, fail, url, param, null), 3, null);
    }

    public static /* synthetic */ Job postOther$default(NetUtil netUtil, String url, Map param, Function1 success, Function1 fail, boolean z, ArrayList arrayList, int i, Object obj) {
        boolean z2 = (i & 16) != 0 ? false : z;
        ArrayList arrayList2 = (i & 32) != 0 ? new ArrayList() : arrayList;
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(param, "param");
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(fail, "fail");
        CoroutineScope coroutineScopeMainScope = CoroutineScopeKt.MainScope();
        Intrinsics.needClassReification();
        return BuildersKt__Builders_commonKt.launch$default(coroutineScopeMainScope, null, null, new C11981(success, fail, url, param, z2, arrayList2, null), 3, null);
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.util.NetUtil$postOther$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: NetUtil.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = KeyBoardKey.KeyboardKeyMediaNextTrack)
    @DebugMetadata(c = "com.cy.yyjia.zhe28.util.NetUtil$postOther$1", f = "NetUtil.kt", i = {}, l = {448}, m = "invokeSuspend", n = {}, s = {})
    public static final class C11981 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Exception, Unit> $fail;
        final /* synthetic */ ArrayList<File> $fileList;
        final /* synthetic */ Map<String, String> $param;
        final /* synthetic */ Function1<T, Unit> $success;
        final /* synthetic */ String $url;
        final /* synthetic */ boolean $useToken;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C11981(Function1<? super T, Unit> function1, Function1<? super Exception, Unit> function12, String str, Map<String, String> map, boolean z, ArrayList<File> arrayList, Continuation<? super C11981> continuation) {
            super(2, continuation);
            this.$success = function1;
            this.$fail = function12;
            this.$url = str;
            this.$param = map;
            this.$useToken = z;
            this.$fileList = arrayList;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C11981(this.$success, this.$fail, this.$url, this.$param, this.$useToken, this.$fileList, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C11981) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io2 = Dispatchers.getIO();
                Intrinsics.needClassReification();
                this.label = 1;
                obj = BuildersKt.withContext(io2, new NetUtil$postOther$1$result$1(this.$url, this.$param, this.$useToken, this.$fileList, null), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            RequestResult requestResult = (RequestResult) obj;
            if (requestResult instanceof RequestResult.Success) {
                this.$success.invoke((T) ((RequestResult.Success) requestResult).getData());
            } else if (requestResult instanceof RequestResult.Error) {
                this.$fail.invoke(((RequestResult.Error) requestResult).getE());
            }
            return Unit.INSTANCE;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public final Object invokeSuspend$$forInline(Object obj) throws Throwable {
            CoroutineDispatcher io2 = Dispatchers.getIO();
            Intrinsics.needClassReification();
            InlineMarker.mark(0);
            Object objWithContext = BuildersKt.withContext(io2, new NetUtil$postOther$1$result$1(this.$url, this.$param, this.$useToken, this.$fileList, null), this);
            InlineMarker.mark(1);
            RequestResult requestResult = (RequestResult) objWithContext;
            if (requestResult instanceof RequestResult.Success) {
                this.$success.invoke((T) ((RequestResult.Success) requestResult).getData());
            } else if (requestResult instanceof RequestResult.Error) {
                this.$fail.invoke(((RequestResult.Error) requestResult).getE());
            }
            return Unit.INSTANCE;
        }
    }

    public final /* synthetic */ <T> Job postOther(String url, Map<String, String> param, Function1<? super T, Unit> success, Function1<? super Exception, Unit> fail, boolean useToken, ArrayList<File> fileList) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(param, "param");
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(fail, "fail");
        CoroutineScope coroutineScopeMainScope = CoroutineScopeKt.MainScope();
        Intrinsics.needClassReification();
        return BuildersKt__Builders_commonKt.launch$default(coroutineScopeMainScope, null, null, new C11981(success, fail, url, param, useToken, fileList, null), 3, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Job post2$default(NetUtil netUtil, String str, Map map, Function1 function1, Function1 function12, ArrayList arrayList, int i, Object obj) {
        if ((i & 16) != 0) {
            arrayList = new ArrayList();
        }
        return netUtil.post2(str, map, function1, function12, arrayList);
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.util.NetUtil$post2$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: NetUtil.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.cy.yyjia.zhe28.util.NetUtil$post2$1", f = "NetUtil.kt", i = {}, l = {467}, m = "invokeSuspend", n = {}, s = {})
    static final class C11961 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Exception, Unit> $fail;
        final /* synthetic */ ArrayList<File> $fileList;
        final /* synthetic */ Map<String, String> $param;
        final /* synthetic */ Function1<Result, Unit> $success;
        final /* synthetic */ String $url;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C11961(Function1<? super Result, Unit> function1, Function1<? super Exception, Unit> function12, String str, Map<String, String> map, ArrayList<File> arrayList, Continuation<? super C11961> continuation) {
            super(2, continuation);
            this.$success = function1;
            this.$fail = function12;
            this.$url = str;
            this.$param = map;
            this.$fileList = arrayList;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C11961(this.$success, this.$fail, this.$url, this.$param, this.$fileList, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C11961) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = BuildersKt.withContext(Dispatchers.getIO(), new NetUtil$post2$1$result$1(this.$url, this.$param, this.$fileList, null), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            RequestResult requestResult = (RequestResult) obj;
            if (requestResult instanceof RequestResult.Success) {
                this.$success.invoke((Result) ((RequestResult.Success) requestResult).getData());
            } else if (requestResult instanceof RequestResult.Error) {
                this.$fail.invoke(((RequestResult.Error) requestResult).getE());
            }
            return Unit.INSTANCE;
        }
    }

    public final Job post2(String url, Map<String, String> param, Function1<? super Result, Unit> success, Function1<? super Exception, Unit> fail, ArrayList<File> fileList) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(param, "param");
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(fail, "fail");
        return BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new C11961(success, fail, url, param, fileList, null), 3, null);
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.util.NetUtil$post3$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: NetUtil.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.cy.yyjia.zhe28.util.NetUtil$post3$1", f = "NetUtil.kt", i = {}, l = {487}, m = "invokeSuspend", n = {}, s = {})
    static final class C11971 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Exception, Unit> $fail;
        final /* synthetic */ File $file;
        final /* synthetic */ String $fileName;
        final /* synthetic */ Map<String, String> $param;
        final /* synthetic */ Function1<Result, Unit> $success;
        final /* synthetic */ String $url;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C11971(Function1<? super Result, Unit> function1, Function1<? super Exception, Unit> function12, String str, Map<String, String> map, File file, String str2, Continuation<? super C11971> continuation) {
            super(2, continuation);
            this.$success = function1;
            this.$fail = function12;
            this.$url = str;
            this.$param = map;
            this.$file = file;
            this.$fileName = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C11971(this.$success, this.$fail, this.$url, this.$param, this.$file, this.$fileName, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C11971) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = BuildersKt.withContext(Dispatchers.getIO(), new NetUtil$post3$1$result$1(this.$url, this.$param, this.$file, this.$fileName, null), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            RequestResult requestResult = (RequestResult) obj;
            if (requestResult instanceof RequestResult.Success) {
                this.$success.invoke((Result) ((RequestResult.Success) requestResult).getData());
            } else if (requestResult instanceof RequestResult.Error) {
                this.$fail.invoke(((RequestResult.Error) requestResult).getE());
            }
            return Unit.INSTANCE;
        }
    }

    public final Job post3(String url, File file, String fileName, Function1<? super Result, Unit> success, Function1<? super Exception, Unit> fail, Map<String, String> param) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(fail, "fail");
        Intrinsics.checkNotNullParameter(param, "param");
        return BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new C11971(success, fail, url, param, file, fileName, null), 3, null);
    }

    public final boolean isConnected(Context mContext) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Object systemService = mContext.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.isConnected();
        }
        return false;
    }
}
