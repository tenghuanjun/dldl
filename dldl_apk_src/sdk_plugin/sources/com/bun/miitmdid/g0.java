package com.bun.miitmdid;

import android.os.AsyncTask;
import com.bun.lib.MsaIdInterface;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class g0 extends AsyncTask<Void, Void, Boolean> {
    public i0 a;
    public MsaIdInterface b;

    public g0(MsaIdInterface msaIdInterface, i0 i0Var) {
        this.b = msaIdInterface;
        this.a = i0Var;
    }

    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public native Boolean doInBackground(Void... voidArr);

    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public native void onPostExecute(Boolean bool);
}
