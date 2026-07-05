package com.alipay.deviceid.module.x;

import android.content.Context;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class br {
    public static String a = "";
    private static Context b;

    class a implements FileFilter {
        String a;

        public a(String str) {
            this.a = "";
            this.a = str;
        }

        @Override // java.io.FileFilter
        public final boolean accept(File file) {
            return file.getName().startsWith(this.a);
        }
    }

    public br(Context context) {
        b = context;
    }

    private void a(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    a(file2);
                }
                file.delete();
            }
        }
    }

    private void a(String str, String str2) {
        try {
            for (File file : new File(str).listFiles(new a(str2))) {
                a(file);
            }
        } catch (Exception e) {
            Log.e("SEProtect", e.toString());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v10, types: [java.io.BufferedOutputStream] */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v19 */
    /* JADX WARN: Type inference failed for: r8v21 */
    /* JADX WARN: Type inference failed for: r8v9 */
    private static boolean a(InputStream inputStream, File file) throws Throwable {
        BufferedInputStream bufferedInputStream;
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    bufferedInputStream = new BufferedInputStream(inputStream);
                    try {
                        FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                        try {
                            file = new BufferedOutputStream(fileOutputStream2);
                        } catch (FileNotFoundException e) {
                            fileOutputStream = fileOutputStream2;
                            e = e;
                            file = 0;
                        } catch (IOException e2) {
                            fileOutputStream = fileOutputStream2;
                            e = e2;
                            file = 0;
                        } catch (Throwable th) {
                            fileOutputStream = fileOutputStream2;
                            th = th;
                            file = 0;
                        }
                        try {
                            byte[] bArr = new byte[512];
                            while (true) {
                                int i = bufferedInputStream.read(bArr);
                                if (i == -1) {
                                    file.flush();
                                    fileOutputStream2.flush();
                                    fileOutputStream2.close();
                                    bufferedInputStream.close();
                                    file.close();
                                    return true;
                                }
                                file.write(bArr, 0, i);
                            }
                        } catch (FileNotFoundException e3) {
                            fileOutputStream = fileOutputStream2;
                            e = e3;
                            file = file;
                            Log.e("SEProtect", e.toString());
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                            }
                            if (file != 0) {
                                file.close();
                            }
                            return false;
                        } catch (IOException e4) {
                            fileOutputStream = fileOutputStream2;
                            e = e4;
                            file = file;
                            Log.e("SEProtect", e.toString());
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                            }
                            if (file != 0) {
                                file.close();
                            }
                            return false;
                        } catch (Throwable th2) {
                            fileOutputStream = fileOutputStream2;
                            th = th2;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e5) {
                                    Log.e("SEProtect", e5.toString());
                                    throw th;
                                }
                            }
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                            }
                            if (file != 0) {
                                file.close();
                            }
                            throw th;
                        }
                    } catch (FileNotFoundException e6) {
                        e = e6;
                        file = 0;
                    } catch (IOException e7) {
                        e = e7;
                        file = 0;
                    } catch (Throwable th3) {
                        th = th3;
                        file = 0;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            } catch (FileNotFoundException e8) {
                e = e8;
                file = 0;
                bufferedInputStream = null;
            } catch (IOException e9) {
                e = e9;
                file = 0;
                bufferedInputStream = null;
            } catch (Throwable th5) {
                th = th5;
                file = 0;
                bufferedInputStream = null;
            }
        } catch (IOException e10) {
            Log.e("SEProtect", e10.toString());
            return false;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:0|2|(1:4)(2:6|(1:8)(8:9|31|10|33|11|29|12|(2:22|(2:24|25)(2:26|35))(1:27)))|5|31|10|33|11|29|12|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x00a0, code lost:
    
        r2 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x00a2, code lost:
    
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x00a3, code lost:
    
        r3 = null;
        r2 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x00a7, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00a8, code lost:
    
        r3 = null;
        r2 = r1;
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00ab, code lost:
    
        r2.toString();
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00d6 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean a(java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.deviceid.module.x.br.a(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    private static boolean a(String str, String str2, String str3, File file) throws Throwable {
        InputStream resourceAsStream = br.class.getClassLoader().getResourceAsStream(str2);
        if (resourceAsStream == null) {
            StringBuilder sb = new StringBuilder("error: can't find ");
            sb.append(str3);
            sb.append(" in apk");
            return false;
        }
        if (str == null) {
            Log.e("SEProtect", "apse file cann't be null...");
        }
        boolean zA = a(resourceAsStream, file);
        try {
            resourceAsStream.close();
            return zA;
        } catch (IOException e) {
            Log.e("SEProtect", e.toString());
            return zA;
        }
    }

    public final boolean a(String str) {
        String str2 = str + "_BK";
        try {
            File filesDir = b.getFilesDir();
            if (!a(filesDir.toString(), str2, str)) {
                Log.e("SEProtect", String.format(Locale.ENGLISH, "error copy %1$s lib fail", str));
                return false;
            }
            File file = new File(filesDir.toString() + File.separator + (str2 + File.separator + ("lib" + str + "_" + a + ".so")));
            if (!file.exists()) {
                String.format(Locale.ENGLISH, "error can't find %1$s lib in plugins_lib", str);
                return false;
            }
            try {
                System.load(file.toString());
                return true;
            } catch (UnsatisfiedLinkError e) {
                Log.e("SEProtect", e.toString());
                return false;
            }
        } catch (FileNotFoundException e2) {
            Log.e("SEProtect", e2.toString());
            return false;
        }
    }
}
