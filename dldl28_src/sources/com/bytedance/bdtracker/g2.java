package com.bytedance.bdtracker;

import com.bytedance.applog.aggregation.MetricsSQLiteCacheKt;
import com.bytedance.applog.log.LoggerImpl;
import com.bytedance.framwork.core.sdklib.DBHelper;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0001\u0010J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0002H&¢\u0006\u0004\b\f\u0010\u0004J\u000f\u0010\u000e\u001a\u00020\rH&¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/bytedance/applog/monitor/model/BaseTrace;", "Lcom/bytedance/applog/monitor/model/BaseAggregation;", "", "category", "()Ljava/lang/String;", "Lorg/json/JSONObject;", "getTraceParams", "()Lorg/json/JSONObject;", MetricsSQLiteCacheKt.METRICS_PARAMS, "", "loadParams", "(Lorg/json/JSONObject;)V", "name", "", DBHelper.COL_VALUE, "()Ljava/lang/Object;", "Companion", "agent_liteChinaRelease"}, k = 1, mv = {1, 4, 0})
public interface g2 extends f2 {

    public static final class a {
        public static JSONObject a(g2 g2Var) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("metrics_category", g2Var.e());
                jSONObject.put("metrics_name", g2Var.b());
                jSONObject.put("metrics_value", g2Var.g());
                g2Var.a(jSONObject);
            } catch (Throwable th) {
                LoggerImpl.global().error("JSON handle failed", th, new Object[0]);
            }
            return jSONObject;
        }

        public static void a(JSONObject params) {
            Intrinsics.checkParameterIsNotNull(params, "params");
        }

        public static List<Number> b(g2 g2Var) {
            return n0.d();
        }
    }

    void a(JSONObject jSONObject);

    String b();

    JSONObject d();

    String e();

    Object g();
}
