package com.cy.yyjia.zhe28.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.compose.material3.TextFieldImplKt;
import androidx.core.content.FileProvider;
import com.cy.yyjia.zhe28.ui.activity.CardActivity;
import com.cy.yyjia.zhe28.ui.activity.DailyTaskActivity;
import com.cy.yyjia.zhe28.ui.activity.GameDetailActivity;
import com.cy.yyjia.zhe28.ui.activity.InviteActivity;
import com.cy.yyjia.zhe28.ui.activity.LoginActivity;
import com.cy.yyjia.zhe28.ui.activity.LotteryActivity;
import com.cy.yyjia.zhe28.ui.activity.MonthCardActivity;
import com.cy.yyjia.zhe28.ui.activity.NoviceWelfareActivity;
import com.cy.yyjia.zhe28.ui.activity.QiandaoActivity;
import com.cy.yyjia.zhe28.ui.activity.SanbaoActivity;
import com.cy.yyjia.zhe28.ui.activity.VipActivity;
import com.cy.yyjia.zhe28.ui.activity.WebActivity;
import com.cy.yyjia.zhe28.ui.activity.WebActivity2;
import com.cy.yyjia.zhe28.ui.dialog.ConfirmDialog;
import com.google.gson.Gson;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.lzy.okgo.model.Progress;
import com.lzy.okserver.download.DownloadTask;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes3.dex */
public class Util {
    public static void skipWithLogin(Context context, Class<?> cls) {
        Log.e("skipWithLogin: ", cls.getName());
        if (Constant.INSTANCE.getLogged()) {
            context.startActivity(new Intent(context, cls));
        } else {
            context.startActivity(new Intent(context, (Class<?>) LoginActivity.class).putExtra("next", cls.getName()));
        }
    }

    public static void skip(Context context, Class<?> cls) {
        Log.e("skip: ", cls.getName());
        context.startActivity(new Intent(context, cls));
    }

    public static String getAssetsData(Context context, String fileName) {
        InputStream inputStreamOpen;
        String str;
        String str2 = null;
        try {
            inputStreamOpen = context.getResources().getAssets().open(fileName);
            byte[] bArr = new byte[inputStreamOpen.available()];
            inputStreamOpen.read(bArr);
            str = new String(bArr);
        } catch (IOException e) {
            e = e;
        }
        try {
            inputStreamOpen.close();
            return str;
        } catch (IOException e2) {
            e = e2;
            str2 = str;
            e.printStackTrace();
            return str2;
        }
    }

    public static <T> T getAssetsData(Context context, String str, Class<T> cls) {
        T t = null;
        try {
            InputStream inputStreamOpen = context.getResources().getAssets().open(str);
            byte[] bArr = new byte[inputStreamOpen.available()];
            inputStreamOpen.read(bArr);
            t = (T) new Gson().fromJson(new String(bArr), (Class) cls);
            inputStreamOpen.close();
            return t;
        } catch (IOException e) {
            e.printStackTrace();
            return t;
        }
    }

    public static int getWidth(Context ctx) {
        return ctx.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getHeight(Context ctx) {
        return ctx.getResources().getDisplayMetrics().heightPixels;
    }

    public static int dpToPx(Context context, float dp) {
        return (int) ((dp * ((float) (((double) context.getResources().getDisplayMetrics().density) * (2.875d / ((double) context.getResources().getDisplayMetrics().density))))) + 0.5f);
    }

    public static int pxToDp(Context context, float px) {
        return (int) ((px / ((float) (((double) context.getResources().getDisplayMetrics().density) * (2.875d / ((double) context.getResources().getDisplayMetrics().density))))) + 0.5f);
    }

    public static void openWeb(Context context, String title, String url) {
        openWeb(context, title, url, true);
    }

    public static void openWebWithLogin(Context context, String title, String url) {
        Log.e("openWebWithLogin: ", url);
        if (Constant.INSTANCE.getLogged()) {
            if (url.contains("blind-box")) {
                context.startActivity(new Intent(context, (Class<?>) LotteryActivity.class));
                return;
            }
            if (url.contains("my-card")) {
                context.startActivity(new Intent(context, (Class<?>) MonthCardActivity.class));
                return;
            }
            if (url.contains("daily-sign")) {
                context.startActivity(new Intent(context, (Class<?>) QiandaoActivity.class));
                return;
            }
            if (url.contains("coupon-648")) {
                context.startActivity(new Intent(context, (Class<?>) SanbaoActivity.class));
                return;
            }
            if (url.contains("coupon-new-user")) {
                context.startActivity(new Intent(context, (Class<?>) NoviceWelfareActivity.class));
                return;
            }
            if (url.contains("my-money-card")) {
                context.startActivity(new Intent(context, (Class<?>) CardActivity.class));
                return;
            }
            if (url.contains("mission-hall") && !url.contains("mission-hall-rule")) {
                skip(context, DailyTaskActivity.class);
                return;
            }
            if (url.contains("new-vip")) {
                context.startActivity(new Intent(context, (Class<?>) VipActivity.class));
                return;
            }
            if (title.contains("三宝")) {
                context.startActivity(new Intent(context, (Class<?>) SanbaoActivity.class));
                return;
            } else if (url.contains("game-detail")) {
                gotoGame(context, Integer.parseInt(url.substring(15)));
                return;
            } else {
                openWeb(context, title, url, false);
                return;
            }
        }
        if (url.contains("video")) {
            openWeb(context, title, url, false);
            return;
        }
        if (url.contains("my-card")) {
            context.startActivity(new Intent(context, (Class<?>) MonthCardActivity.class));
            return;
        }
        if (url.contains("blind-box")) {
            context.startActivity(new Intent(context, (Class<?>) LotteryActivity.class));
            return;
        }
        if (url.contains("daily-sign")) {
            context.startActivity(new Intent(context, (Class<?>) QiandaoActivity.class));
            return;
        }
        if (url.contains("coupon-new-user")) {
            context.startActivity(new Intent(context, (Class<?>) NoviceWelfareActivity.class));
            return;
        }
        if (url.contains("invite")) {
            context.startActivity(new Intent(context, (Class<?>) InviteActivity.class));
            return;
        }
        if (url.contains("mission-hall") && !url.contains("mission-hall-rule")) {
            skip(context, DailyTaskActivity.class);
            return;
        }
        if (url.contains("my-money-card")) {
            context.startActivity(new Intent(context, (Class<?>) CardActivity.class));
            return;
        }
        if (url.contains("coupon-648")) {
            context.startActivity(new Intent(context, (Class<?>) SanbaoActivity.class));
            return;
        }
        if (url.contains("new-vip")) {
            context.startActivity(new Intent(context, (Class<?>) VipActivity.class));
            return;
        }
        if (title.contains("三宝")) {
            context.startActivity(new Intent(context, (Class<?>) SanbaoActivity.class));
            return;
        }
        if (url.contains("siteaccount")) {
            openWeb(context, title, url, false);
            return;
        }
        if (url.contains("transfer-game")) {
            openWeb(context, title, url, false);
            return;
        }
        if (url.contains("ac=lottery")) {
            openWeb(context, title, url, false);
            return;
        }
        if (url.contains("free-lottery")) {
            openWeb(context, title, url, false);
            return;
        }
        if (url.contains("customer-service")) {
            openWeb(context, title, url, false);
            return;
        }
        if (url.contains("award-trial-play")) {
            openWeb(context, title, url, false);
            return;
        }
        Intent intent = new Intent(context, (Class<?>) LoginActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    public static void openWeb(Context context, String title, String url, boolean showTitle) {
        Intent intent;
        if (showTitle) {
            intent = new Intent(context, (Class<?>) WebActivity.class);
        } else {
            intent = new Intent(context, (Class<?>) WebActivity2.class);
        }
        intent.putExtra("name", title);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    public static void openWebGame(Context context, String url) {
        Intent intent = new Intent(context, (Class<?>) WebActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("game", true);
        context.startActivity(intent);
    }

    public static void openProtocol(Context context, String title, String name) {
        Intent intent = new Intent(context, (Class<?>) WebActivity.class);
        intent.putExtra("name", title);
        intent.putExtra("module", name);
        context.startActivity(intent);
    }

    public static void gotoGame(Context context, int gid) {
        if (gid == 0) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) GameDetailActivity.class);
        intent.putExtra("gid", gid);
        context.startActivity(intent);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00b3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String savePicToDCIM(android.content.Context r5, android.graphics.Bitmap r6, java.lang.String r7, int r8) throws java.lang.Throwable {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = android.os.Environment.DIRECTORY_DCIM
            r0.append(r1)
            java.lang.String r1 = java.io.File.separator
            r0.append(r1)
            java.lang.String r1 = "邀请"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "文件夹目录 >>> "
            r1.<init>(r2)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "TAG"
            android.util.Log.e(r2, r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "文件名字 >>> "
            r1.<init>(r3)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            android.util.Log.e(r2, r1)
            android.content.ContentValues r1 = new android.content.ContentValues
            r1.<init>()
            java.lang.String r2 = "title"
            r1.put(r2, r7)
            java.lang.String r2 = "_display_name"
            r1.put(r2, r7)
            java.lang.String r2 = "mime_type"
            java.lang.String r3 = "image/jpeg"
            r1.put(r2, r3)
            java.lang.String r2 = "relative_path"
            r1.put(r2, r0)
            android.content.ContentResolver r5 = r5.getContentResolver()
            r2 = 0
            android.net.Uri r3 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            android.net.Uri r1 = r5.insert(r3, r1)     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            java.io.OutputStream r3 = r5.openOutputStream(r1)     // Catch: java.lang.Exception -> L92 java.lang.Throwable -> L95
            android.graphics.Bitmap$CompressFormat r4 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Laf
            r6.compress(r4, r8, r3)     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Laf
            r3.flush()     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Laf
            r3.close()     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Laf
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Laf
            r6.<init>()     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Laf
            r6.append(r0)     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Laf
            java.lang.String r8 = "/"
            r6.append(r8)     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Laf
            r6.append(r7)     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Laf
            java.lang.String r5 = r6.toString()     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Laf
            if (r3 == 0) goto Lae
            r3.close()     // Catch: java.io.IOException -> L8b
            goto Lae
        L8b:
            r6 = move-exception
            r6.printStackTrace()
            goto Lae
        L90:
            r6 = move-exception
            goto L9a
        L92:
            r6 = move-exception
            r3 = r2
            goto L9a
        L95:
            r5 = move-exception
            goto Lb1
        L97:
            r6 = move-exception
            r1 = r2
            r3 = r1
        L9a:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> Laf
            if (r1 == 0) goto La2
            r5.delete(r1, r2, r2)     // Catch: java.lang.Throwable -> Laf
        La2:
            if (r3 == 0) goto Lac
            r3.close()     // Catch: java.io.IOException -> La8
            goto Lac
        La8:
            r5 = move-exception
            r5.printStackTrace()
        Lac:
            java.lang.String r5 = ""
        Lae:
            return r5
        Laf:
            r5 = move-exception
            r2 = r3
        Lb1:
            if (r2 == 0) goto Lbb
            r2.close()     // Catch: java.io.IOException -> Lb7
            goto Lbb
        Lb7:
            r6 = move-exception
            r6.printStackTrace()
        Lbb:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.util.Util.savePicToDCIM(android.content.Context, android.graphics.Bitmap, java.lang.String, int):java.lang.String");
    }

    public static void copy(Context context, String text) {
        if (TextUtils.isEmpty(text)) {
            Toast.makeText(context, "复制内容为空", 0).show();
        } else {
            ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(TextFieldImplKt.LabelId, text));
            Toast.makeText(context, "已复制到剪切板", 0).show();
        }
    }

    public static byte[] bmpToByteArray(String url) {
        Bitmap bitmapNetPicToBmp = netPicToBmp(url);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapNetPicToBmp.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        bitmapNetPicToBmp.recycle();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArray;
    }

    public static Bitmap netPicToBmp(String src) {
        try {
            Log.d("FileUtil", src);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(src).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            int width = bitmapDecodeStream.getWidth();
            int height = bitmapDecodeStream.getHeight();
            Matrix matrix = new Matrix();
            matrix.postScale(150.0f / width, 150.0f / height);
            return Bitmap.createBitmap(bitmapDecodeStream, 0, 0, width, height, matrix, true);
        } catch (IOException unused) {
            return null;
        }
    }

    public static String formatName(String s) {
        if (s == null) {
            return "";
        }
        if (s.contains("（")) {
            if (s.contains("）")) {
                return s.substring(0, s.indexOf("（")) + s.substring(s.indexOf("）") + 1);
            }
            return s.substring(0, s.indexOf("（"));
        }
        if (s.contains("(")) {
            if (s.contains(")")) {
                return s.substring(0, s.indexOf("(")) + s.substring(s.indexOf(")") + 1);
            }
            return s.substring(0, s.indexOf("("));
        }
        if (!s.contains("【")) {
            return s;
        }
        if (s.contains("】")) {
            return s.substring(0, s.indexOf("【")) + s.substring(s.indexOf("】") + 1);
        }
        return s.substring(0, s.indexOf("【"));
    }

    public static String getFix(String s) {
        if (s == null) {
            return "";
        }
        if (s.contains("（")) {
            if (s.contains("）")) {
                return s.substring(s.indexOf("（") + 1, s.indexOf("）"));
            }
            return s.substring(s.indexOf("（") + 1);
        }
        if (s.contains("(")) {
            if (s.contains(")")) {
                return s.substring(s.indexOf("(") + 1, s.indexOf(")"));
            }
            return s.substring(s.indexOf("(") + 1);
        }
        if (!s.contains("【")) {
            return "";
        }
        if (s.contains("】")) {
            return s.substring(s.indexOf("【") + 1, s.indexOf("】"));
        }
        return s.substring(s.indexOf("【") + 1);
    }

    public static String getTotalCacheSize(Context context) throws Exception {
        long folderSize = getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals("mounted")) {
            folderSize += getFolderSize(context.getExternalCacheDir());
        }
        return getFormatSize(folderSize);
    }

    public static long getFolderSize(File file) throws Exception {
        long length;
        long j = 0;
        try {
            File[] fileArrListFiles = file.listFiles();
            for (int i = 0; i < fileArrListFiles.length; i++) {
                if (fileArrListFiles[i].isDirectory()) {
                    length = getFolderSize(fileArrListFiles[i]);
                } else {
                    length = fileArrListFiles[i].length();
                }
                j += length;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }

    public static void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals("mounted")) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            for (String str : dir.list()) {
                if (!deleteDir(new File(dir, str))) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public static String getFormatSize(double size) {
        double d = size / 1024.0d;
        if (d < 1.0d) {
            return "0K";
        }
        double d2 = d / 1024.0d;
        if (d2 < 1.0d) {
            return new BigDecimal(Double.toString(d)).setScale(2, 4).toPlainString() + "KB";
        }
        double d3 = d2 / 1024.0d;
        if (d3 < 1.0d) {
            return new BigDecimal(Double.toString(d2)).setScale(2, 4).toPlainString() + "MB";
        }
        double d4 = d3 / 1024.0d;
        if (d4 < 1.0d) {
            return new BigDecimal(Double.toString(d3)).setScale(2, 4).toPlainString() + "GB";
        }
        return new BigDecimal(d4).setScale(2, 4).toPlainString() + "TB";
    }

    public static void installApk(final Context context, File apk, final DownloadTask task) {
        if (!apk.getPath().endsWith(".apk") || apk.length() < 1048576) {
            new ConfirmDialog(context).setTitle("下载失败").setTip("检测到下载apk失败，请重新下载").setOnConfirm(new Function0() { // from class: com.cy.yyjia.zhe28.util.Util$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Util.lambda$installApk$0(task);
                }
            }).show();
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        Log.e("installApk: ", apk.getPath());
        Uri uriForFile = FileProvider.getUriForFile(context, "com.cy.yyjia.zhe28.fileProvider", apk);
        Log.e("installApk: ", uriForFile.toString());
        Log.e("installApk: ", uriForFile.getPath());
        intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
        intent.setFlags(268435456);
        intent.addFlags(1);
        context.startActivity(intent);
    }

    static /* synthetic */ Unit lambda$installApk$0(DownloadTask downloadTask) {
        downloadTask.restart();
        return null;
    }

    public static void installApk(final Context context, Uri uri) {
        Intent intent = new Intent("android.intent.action.VIEW");
        Log.e("installApk: ", uri.toString());
        Log.e("installApk: ", uri.getPath());
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        intent.setFlags(268435456);
        intent.addFlags(1);
        context.startActivity(intent);
    }

    public static Uri getFileUri(Context context, File file) {
        return FileProvider.getUriForFile(context, "com.cy.yyjia.zhe28.fileProvider", file);
    }

    public static boolean isAPPInstalled(Context context, String appName) {
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        if (installedPackages != null) {
            for (int i = 0; i < installedPackages.size(); i++) {
                if (installedPackages.get(i).packageName.equalsIgnoreCase(appName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int f2i(Progress progress) {
        if (progress != null) {
            return (int) (progress.fraction * 100.0f);
        }
        return 100;
    }

    public static void openOtherApp(Context context, String packageName) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (launchIntentForPackage != null) {
            context.startActivity(launchIntentForPackage);
        }
    }

    public static void toService(Context mContext) {
        openWebWithLogin(mContext, "客服中心", NetUtil.BASE_URL3 + "dist/customer-service");
    }

    public static void checkReadPermission(Context context, OnPermissionCallback callback) {
        String[] strArr = {Permission.READ_MEDIA_IMAGES};
        if (XXPermissions.isGranted(context, strArr)) {
            callback.onGranted(Arrays.asList(strArr), true);
        } else {
            XXPermissions.with(context).permission(strArr).request(callback);
        }
    }

    public static void setGray(View v, boolean gray) {
        Paint paint = new Paint();
        if (gray) {
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(0.0f);
            paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            v.setLayerType(2, paint);
            return;
        }
        v.setLayerType(2, paint);
    }
}
