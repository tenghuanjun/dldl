package org.slf4j;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface IMarkerFactory {
    boolean detachMarker(String str);

    boolean exists(String str);

    Marker getDetachedMarker(String str);

    Marker getMarker(String str);
}
