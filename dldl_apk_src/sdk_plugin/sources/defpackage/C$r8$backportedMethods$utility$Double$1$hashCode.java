package defpackage;

/* JADX INFO: renamed from: $r8$backportedMethods$utility$Double$1$hashCode, reason: invalid class name */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public /* synthetic */ class C$r8$backportedMethods$utility$Double$1$hashCode {
    public static /* synthetic */ int hashCode(double d) {
        long jDoubleToLongBits = Double.doubleToLongBits(d);
        return (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
    }
}
