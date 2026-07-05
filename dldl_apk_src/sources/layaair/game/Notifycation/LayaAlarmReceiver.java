package layaair.game.Notifycation;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.tencent.open.SocialConstants;
import java.util.List;
import layaair.game.browser.ap;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class LayaAlarmReceiver extends BroadcastReceiver {
    private static boolean a(String str, Context context) {
        int i;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(TTDownloadField.TT_ACTIVITY)).getRunningAppProcesses();
        boolean z = false;
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.processName.equals(str) && ((i = runningAppProcessInfo.importance) == 200 || i == 100)) {
                    z = true;
                }
            }
        }
        return z;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.i("0", "========================LayaAlarmReceiver.onReceive pakeName=" + context.getPackageName());
        Bundle extras = intent.getExtras();
        if (extras == null || a(context.getPackageName(), context)) {
            return;
        }
        int i = extras.getInt("id");
        String string = extras.getString("title");
        String string2 = extras.getString("ticker");
        String string3 = extras.getString(SocialConstants.PARAM_APP_DESC);
        if (i != -1) {
            ap.a(context, i, string2, string, string3);
        }
    }
}
