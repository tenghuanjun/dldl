package com.sqwan.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.sq.tool.logger.SQLog;
import com.sq.tool.network.RequestIdOkInterceptor;
import com.sq.tool.network.SignInterceptor;
import com.sq.tool.network.SignV1Interceptor;
import com.sq.tool.network.SignV2Interceptor;
import com.sq.tool.network.SignV3Interceptor;
import com.sq.tools.Logger;
import com.sq.tools.event.EventCollection;
import com.sq.tools.event.EventPost;
import com.sq.tools.event.EventRequest;
import com.sq.tools.event.EventsTracker;
import com.sq.tools.proxy.Pair;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.request.MapParams;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackNetKey;
import com.sqwan.common.util.NetWorkUtils;
import java.util.Map;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public enum FakeActive {
    INSTANCE;

    private EventsTracker tracker;
    private final String SQ_PREFS = "sq_prefs";
    public final String M_FAKE_ACTIVE_KEY = "sq_m_fake_active_content";
    public final String S_FAKE_ACTIVE_KEY = "sq_s_fake_active_content";
    private final OkHttpClient mOkHttpClient = new OkHttpClient.Builder().addInterceptor(new RequestIdOkInterceptor()).build();

    FakeActive() {
    }

    public void saveContent(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("sq_prefs", 0).edit();
        editorEdit.putString(str2, str);
        editorEdit.apply();
    }

    public String requireContent(Context context, String str, String str2, Map<String, String> map, SignInterceptor.SignVersion signVersion) {
        String strSign = null;
        if (context == null || !NetWorkUtils.isNetworkAvailable(context) || NetWorkUtils.isWifiProxy()) {
            return null;
        }
        String content = getContent(context, str);
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        Logger.info("本地激活逻辑开始运行", new Object[0]);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.LOACL_ACTIVE);
        BuglessAction.reportCatchException(new Exception("Fake Active"), "本地激活: url:" + str2, 28);
        int i = AnonymousClass1.$SwitchMap$com$sq$tool$network$SignInterceptor$SignVersion[signVersion.ordinal()];
        if (i == 1) {
            strSign = SignV1Interceptor.sign(map);
        } else if (i == 2) {
            strSign = SignV2Interceptor.sign(map, null);
        } else if (i == 3) {
            strSign = SignV3Interceptor.sign(map);
        }
        if (strSign != null) {
            map.put(SqConstants.SIGN, strSign);
        }
        keepRequest(context, str2, map);
        return content;
    }

    /* JADX INFO: renamed from: com.sqwan.common.FakeActive$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sq$tool$network$SignInterceptor$SignVersion;

        static {
            int[] iArr = new int[SignInterceptor.SignVersion.values().length];
            $SwitchMap$com$sq$tool$network$SignInterceptor$SignVersion = iArr;
            try {
                iArr[SignInterceptor.SignVersion.V1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sq$tool$network$SignInterceptor$SignVersion[SignInterceptor.SignVersion.V2.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sq$tool$network$SignInterceptor$SignVersion[SignInterceptor.SignVersion.V3.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public String getContent(Context context, String str) {
        if (context == null) {
            return null;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("sq_prefs", 0);
        if (sharedPreferences.contains(str)) {
            return sharedPreferences.getString(str, "");
        }
        return null;
    }

    private void keepRequest(Context context, String str, Map<String, String> map) {
        if (this.tracker == null) {
            EventsTracker eventsTracker = new EventsTracker(context, new FakeActiveFiled(context), str);
            this.tracker = eventsTracker;
            eventsTracker.setPostAvailable(true);
            this.tracker.setEventPoster(new FakeActivePost(null));
            this.tracker.setStorageFileName("SQ_FAKE_ACTIVE.log");
            this.tracker.setFlushNum(1);
        }
        MapParams mapParams = new MapParams(map);
        this.tracker.log("fakeActive", new Pair(SqTrackNetKey.params, mapParams.toPostParam(context)), new Pair("url", str), new Pair("type", mapParams.contentType));
    }

    private static class FakeActiveFiled extends EventRequest {
        @Override // com.sq.tools.event.EventRequest
        protected void fresh(Context context) {
        }

        public FakeActiveFiled(Context context) {
            super(context);
        }
    }

    private static class FakeActivePost extends EventPost {
        private FakeActivePost() {
        }

        /* synthetic */ FakeActivePost(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // com.sq.tools.event.EventPost
        protected boolean sendToServer(Context context, EventRequest eventRequest, EventCollection eventCollection, String str) {
            SQLog.d("本地激活尝试进行补偿上报, 条数: %d", Integer.valueOf(eventCollection.size()));
            boolean z = true;
            for (int i = 0; i < eventCollection.size(); i++) {
                JSONObject jsonObject = eventCollection.get(i).toJsonObject();
                String strOptString = jsonObject.optString(SqTrackNetKey.params);
                String strOptString2 = jsonObject.optString("type");
                HttpUrl httpUrl = HttpUrl.parse(jsonObject.optString("url"));
                if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2) && httpUrl != null) {
                    Request requestBuild = new Request.Builder().post(RequestBody.create(MediaType.parse(strOptString2), strOptString)).url(httpUrl).build();
                    Response responseExecute = null;
                    try {
                        try {
                            responseExecute = FakeActive.INSTANCE.mOkHttpClient.newCall(requestBuild).execute();
                            if (responseExecute.isSuccessful()) {
                                SQLog.i("补偿请求[" + httpUrl + "]成功");
                            } else {
                                SQLog.w("补偿请求[" + httpUrl + "]失败, " + responseExecute.code());
                            }
                            z = z && responseExecute.isSuccessful();
                            if (responseExecute != null) {
                                responseExecute.close();
                            }
                        } catch (Exception e) {
                            SQLog.e("补偿请求[" + httpUrl + "]异常", e);
                            if (responseExecute != null) {
                                responseExecute.close();
                            }
                            z = false;
                        }
                    } catch (Throwable th) {
                        if (responseExecute != null) {
                            responseExecute.close();
                        }
                        throw th;
                    }
                }
            }
            SQLog.i("本次本地激活补偿上报结果: %s", Boolean.valueOf(z));
            return z;
        }
    }
}
