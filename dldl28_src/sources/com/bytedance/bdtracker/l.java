package com.bytedance.bdtracker;

import androidx.exifinterface.media.ExifInterface;
import com.bytedance.bdtracker.o;
import com.volcengine.common.contant.CommonConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003:\u0001\u001aB\u0007¢\u0006\u0004\b\u0004\u0010\u0005R\"\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR$\u0010\r\u001a\u0004\u0018\u00018\u00008\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R$\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\u001b"}, d2 = {"Lcom/bytedance/applog/alink/model/ApiResponse;", "Lcom/bytedance/applog/alink/model/BaseData;", ExifInterface.GPS_DIRECTION_TRUE, "", "<init>", "()V", "", "code", "I", "getCode", "()I", "setCode", "(I)V", "data", "Lcom/bytedance/applog/alink/model/BaseData;", "getData", "()Lcom/bytedance/applog/alink/model/BaseData;", "setData", "(Lcom/bytedance/applog/alink/model/BaseData;)V", "", CommonConstants.KEY_MESSAGE, "Ljava/lang/String;", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "Companion", "agent_liteChinaRelease"}, k = 1, mv = {1, 4, 0})
public final class l<T extends o> {
    public static final a b = new a(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public T f286a;

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
        }

        public final <T extends o> l<T> a(String str, Class<T> clazz) {
            Intrinsics.checkParameterIsNotNull(clazz, "clazz");
            JSONObject jSONObject = new JSONObject(str);
            l<T> lVar = new l<>();
            jSONObject.optInt("code");
            jSONObject.optString(CommonConstants.KEY_MESSAGE);
            lVar.f286a = (T) o.f299a.a(jSONObject.optJSONObject("data"), clazz);
            return lVar;
        }
    }

    public final T a() {
        return this.f286a;
    }
}
