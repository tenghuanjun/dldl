package com.sq.pluginex;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.pm.PackageParser;
import android.util.Log;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class LoadedPlugin {
    private static final String TAG = "LoadedPlugin";
    protected final PackageParser.Package mPackage;

    public LoadedPlugin(ClassLoader classLoader, Context context, File file) {
        PackageParser.Package r8 = PackageParserCompat.parsePackage(context, file, 4);
        this.mPackage = r8;
        for (PackageParser.Activity activity : r8.receivers) {
            Log.i(TAG, "receiver:" + activity.getClass().getName());
            BroadcastReceiver broadcastReceiver = null;
            try {
                Object objNewInstance = classLoader.loadClass(activity.getComponentName().getClassName()).newInstance();
                if (objNewInstance instanceof BroadcastReceiver) {
                    broadcastReceiver = (BroadcastReceiver) objNewInstance;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (broadcastReceiver != null) {
                for (PackageParser.ActivityIntentInfo activityIntentInfo : activity.intents) {
                    Log.i(TAG, "registerReceiver");
                    context.registerReceiver(broadcastReceiver, activityIntentInfo);
                }
            }
        }
    }
}
