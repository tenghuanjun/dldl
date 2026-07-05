package com.ss.android.socialbase.appdownloader.e;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.support.v4.app.NotificationManagerCompat;
import android.view.KeyEvent;
import com.ss.android.socialbase.appdownloader.c.n;
import com.ss.android.socialbase.appdownloader.i;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class d {
    private static final String a = d.class.getSimpleName();
    private static List<n> b = new ArrayList();
    private static com.ss.android.socialbase.appdownloader.view.a c;
    private static AlertDialog d;

    public static boolean a() {
        try {
            return NotificationManagerCompat.from(DownloadComponentManager.getAppContext()).areNotificationsEnabled();
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    public static synchronized void a(boolean z) {
        try {
            if (d != null) {
                d.cancel();
                d = null;
            }
            for (n nVar : b) {
                if (nVar != null) {
                    if (z) {
                        nVar.a();
                    } else {
                        nVar.b();
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static synchronized void a(final Activity activity, final n nVar) {
        if (nVar == null) {
            return;
        }
        if (activity != null) {
            try {
            } catch (Throwable th) {
                th.printStackTrace();
                a(false);
            }
            if (!activity.isFinishing()) {
                int iA = i.a(DownloadComponentManager.getAppContext(), "tt_appdownloader_notification_request_title");
                int iA2 = i.a(DownloadComponentManager.getAppContext(), "tt_appdownloader_notification_request_message");
                int iA3 = i.a(DownloadComponentManager.getAppContext(), "tt_appdownloader_notification_request_btn_yes");
                int iA4 = i.a(DownloadComponentManager.getAppContext(), "tt_appdownloader_notification_request_btn_no");
                b.add(nVar);
                if (d == null || !d.isShowing()) {
                    d = new AlertDialog.Builder(activity).setTitle(iA).setMessage(iA2).setPositiveButton(iA3, new DialogInterface.OnClickListener() { // from class: com.ss.android.socialbase.appdownloader.e.d.3
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            d.b(activity, nVar);
                            dialogInterface.cancel();
                            AlertDialog unused = d.d = null;
                        }
                    }).setNegativeButton(iA4, new DialogInterface.OnClickListener() { // from class: com.ss.android.socialbase.appdownloader.e.d.2
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            d.a(false);
                        }
                    }).setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.ss.android.socialbase.appdownloader.e.d.1
                        @Override // android.content.DialogInterface.OnKeyListener
                        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                            if (i != 4) {
                                return false;
                            }
                            if (keyEvent.getAction() == 1) {
                                d.a(false);
                            }
                            return true;
                        }
                    }).setCancelable(false).show();
                }
                return;
            }
        }
        nVar.b();
    }

    public static void b(Activity activity, n nVar) {
        if (activity != null) {
            try {
                if (!activity.isFinishing()) {
                    FragmentManager fragmentManager = activity.getFragmentManager();
                    com.ss.android.socialbase.appdownloader.view.a aVar = (com.ss.android.socialbase.appdownloader.view.a) fragmentManager.findFragmentByTag(a);
                    c = aVar;
                    if (aVar == null) {
                        c = new com.ss.android.socialbase.appdownloader.view.a();
                        fragmentManager.beginTransaction().add(c, a).commitAllowingStateLoss();
                        try {
                            fragmentManager.executePendingTransactions();
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                    c.a();
                    return;
                }
            } catch (Throwable th2) {
                try {
                    th2.printStackTrace();
                    nVar.a();
                    return;
                } catch (Throwable th3) {
                    th3.printStackTrace();
                    return;
                }
            }
        }
        nVar.a();
    }
}
