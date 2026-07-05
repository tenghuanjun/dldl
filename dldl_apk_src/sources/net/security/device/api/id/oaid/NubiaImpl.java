package net.security.device.api.id.oaid;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import com.igexin.push.core.b;
import net.security.device.api.id.IOAID;
import net.security.device.api.id.IOAIDGetter;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class NubiaImpl implements IOAID {
    private final Context context;

    @Override // net.security.device.api.id.IOAID
    public boolean supportOAID() {
        return true;
    }

    public NubiaImpl(Context context) {
        this.context = context;
    }

    @Override // net.security.device.api.id.IOAID
    public void doGet(IOAIDGetter iOAIDGetter) {
        Bundle bundleCall;
        ContentProviderClient contentProviderClientAcquireContentProviderClient;
        if (this.context == null) {
            iOAIDGetter.onOAIDGetError(new NullPointerException("OAID context is null"));
            return;
        }
        try {
            Uri uri = Uri.parse("content://cn.nubia.identity/identity");
            String string = null;
            if (uri != null) {
                ContentResolver contentResolver = this.context.getContentResolver();
                if (contentResolver != null) {
                    if (Build.VERSION.SDK_INT <= 17 || (contentProviderClientAcquireContentProviderClient = contentResolver.acquireContentProviderClient(uri)) == null) {
                        bundleCall = null;
                    } else {
                        bundleCall = contentProviderClientAcquireContentProviderClient.call("getOAID", null, null);
                        if (Build.VERSION.SDK_INT >= 24) {
                            contentProviderClientAcquireContentProviderClient.close();
                        } else {
                            contentProviderClientAcquireContentProviderClient.release();
                        }
                    }
                } else {
                    bundleCall = contentResolver.call(uri, "getOAID", (String) null, (Bundle) null);
                }
                if (bundleCall == null) {
                    throw new RuntimeException("getOAID call failed");
                }
                if (bundleCall.getInt("code", -1) == 0) {
                    string = bundleCall.getString("id");
                }
            } else {
                bundleCall = null;
            }
            if (string != null && string.length() > 0) {
                iOAIDGetter.onOAIDGetComplete(string);
                return;
            }
            throw new RuntimeException(bundleCall != null ? bundleCall.getString(b.Z) : "OAID unsupported");
        } catch (Exception e) {
            iOAIDGetter.onOAIDGetError(e);
        }
    }
}
