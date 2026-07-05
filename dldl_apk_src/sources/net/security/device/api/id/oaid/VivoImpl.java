package net.security.device.api.id.oaid;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import java.util.Objects;
import net.security.device.api.id.IOAID;
import net.security.device.api.id.IOAIDGetter;
import net.security.device.api.id.SystemUtils;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class VivoImpl implements IOAID {
    private final Context context;

    public VivoImpl(Context context) {
        this.context = context;
    }

    @Override // net.security.device.api.id.IOAID
    public boolean supportOAID() {
        return SystemUtils.sysProperty("persist.sys.identifierid.supported", "0").equals("1");
    }

    @Override // net.security.device.api.id.IOAID
    public void doGet(IOAIDGetter iOAIDGetter) {
        ContentResolver contentResolver;
        Cursor cursorQuery;
        if (this.context == null) {
            iOAIDGetter.onOAIDGetError(new NullPointerException("OAID context is null"));
            return;
        }
        if (Build.VERSION.SDK_INT < 19) {
            iOAIDGetter.onOAIDGetError(new RuntimeException("OAID unsupported system"));
            return;
        }
        String string = null;
        try {
            Uri uri = Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/OAID");
            if (uri != null && (contentResolver = this.context.getContentResolver()) != null && (cursorQuery = contentResolver.query(uri, null, null, null, null)) != null) {
                ((Cursor) Objects.requireNonNull(cursorQuery)).moveToFirst();
                string = cursorQuery.getString(cursorQuery.getColumnIndex("value"));
            }
            if (string != null && string.length() > 0) {
                iOAIDGetter.onOAIDGetComplete(string);
                return;
            }
            throw new RuntimeException("OAID query failed");
        } catch (Exception e) {
            iOAIDGetter.onOAIDGetError(e);
        }
    }
}
