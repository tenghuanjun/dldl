package kotlin;

import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import kotlin.internal.InlineOnly;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: HashCode.kt */
/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0000\n\u0000\u001a\u000f\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u0002H\u0087\b¨\u0006\u0003"}, d2 = {TTDownloadField.TT_HASHCODE, "", "", "kotlin-stdlib"}, k = 2, mv = {1, 1, 16})
public final class HashCodeKt {
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final int hashCode(@Nullable Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }
}
