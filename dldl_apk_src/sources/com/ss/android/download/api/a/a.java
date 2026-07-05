package com.ss.android.download.api.a;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.widget.Toast;
import com.ss.android.download.api.config.l;
import com.ss.android.download.api.download.DownloadModel;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class a implements l {
    @Override // com.ss.android.download.api.config.l
    public void a(int i, Context context, DownloadModel downloadModel, String str, Drawable drawable, int i2) {
        Toast.makeText(context, str, 0).show();
    }

    @Override // com.ss.android.download.api.config.l
    public Dialog b(com.ss.android.download.api.model.b bVar) {
        return a(bVar);
    }

    private static Dialog a(final com.ss.android.download.api.model.b bVar) {
        if (bVar == null) {
            return null;
        }
        AlertDialog alertDialogShow = new AlertDialog.Builder(bVar.a).setTitle(bVar.b).setMessage(bVar.c).setPositiveButton(bVar.d, new DialogInterface.OnClickListener() { // from class: com.ss.android.download.api.a.a.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (bVar.h != null) {
                    bVar.h.a(dialogInterface);
                }
            }
        }).setNegativeButton(bVar.e, new DialogInterface.OnClickListener() { // from class: com.ss.android.download.api.a.a.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (bVar.h != null) {
                    bVar.h.b(dialogInterface);
                }
            }
        }).show();
        alertDialogShow.setCanceledOnTouchOutside(bVar.f);
        alertDialogShow.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.ss.android.download.api.a.a.3
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                if (bVar.h != null) {
                    bVar.h.c(dialogInterface);
                }
            }
        });
        if (bVar.g != null) {
            alertDialogShow.setIcon(bVar.g);
        }
        return alertDialogShow;
    }
}
