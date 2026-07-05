package com.nirvana.tools.crash;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.igexin.push.core.b;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
class FileUtils {
    FileUtils() {
    }

    private static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1048576];
        while (true) {
            int i = inputStream.read(bArr);
            if (i <= 0) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }

    public static void copyFile(File file, String str) throws IOException {
        File fileCreateFileIfUnExist = createFileIfUnExist(str);
        if (fileCreateFileIfUnExist == null) {
            return;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(fileCreateFileIfUnExist);
        try {
            byte[] bArr = new byte[1048576];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i <= 0) {
                    return;
                } else {
                    fileOutputStream.write(bArr, 0, i);
                }
            }
        } finally {
            fileInputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
        }
    }

    private static File createFileIfUnExist(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file;
        }
        File file2 = new File(file.getParent());
        if (!file2.exists()) {
            file2.mkdirs();
        }
        try {
            if (file.createNewFile()) {
                return file;
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteFile(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        file.delete();
    }

    private static String fillContent(Context context, String str, String str2, String str3, long j, SdkInfo sdkInfo, String str4, Map<String, String> map, Thread thread, Throwable th) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***\nBasic Information: 'pid: ");
            sb.append(Process.myPid());
            sb.append("/tid: ");
            sb.append(Process.myTid());
            sb.append("/time: ");
            sb.append(str);
            sb.append("'\nCpu Information: 'abi: ");
            sb.append(str2);
            sb.append("/processor: ");
            sb.append(CrashUploadUtils.getFieldFromCpuinfo("Processor"));
            sb.append("/hardware: ");
            sb.append(Build.HARDWARE);
            sb.append("'\nMobile Information: 'model: ");
            sb.append(Build.MODEL);
            sb.append("/version: ");
            sb.append(Build.VERSION.RELEASE);
            sb.append("/sdk: ");
            sb.append(Build.VERSION.SDK_INT);
            sb.append("'\nBuild fingerprint: '");
            sb.append(Build.FINGERPRINT);
            sb.append("'\nRuntime Information: 'start: ");
            sb.append(str3);
            sb.append("/maxheap: ");
            sb.append(j);
            sb.append("/primaryabi: ");
            sb.append(Build.CPU_ABI);
            sb.append("/ground: fg'\nApplication Information: 'version: ");
            sb.append(CrashUploadUtils.getVersion(context));
            sb.append("/subversion: release'\nCrashSDK Information: 'version:");
            sb.append(sdkInfo.getSdkVersion());
            sb.append("/arch: ");
            sb.append(Build.CPU_ABI);
            sb.append("/target: ");
            sb.append(CrashUploadUtils.apkInDebugRelease(context));
            sb.append("'\nReport Name: ");
            sb.append(str4);
            sb.append("\nLog Type: java\ncrash_shield_version: 2.0.3\ncustom_uc_upload: true");
            if (map != null && !map.isEmpty()) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    sb.append("\n");
                    sb.append(entry.getKey());
                    sb.append(":");
                    sb.append(entry.getValue());
                }
            }
            sb.append("\nActivity: (none)\n--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---\n--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---\nProcess Name: '");
            sb.append(CrashUploadUtils.getCurProcessName(context));
            sb.append("'\nThread Name: '");
            sb.append(thread.getName());
            sb.append("\nk_ac: ");
            sb.append(sdkInfo.getSdkName());
            sb.append("\nBack traces starts.\n");
            sb.append(Log.getStackTraceString(th));
            sb.append("\nBack traces ends.\n--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---\nmeminfo:\n");
            sb.append(CrashUploadUtils.getMeminfo());
            sb.append("\n--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---\n");
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getName(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.substring(str.lastIndexOf(File.separatorChar) + 1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0045 A[Catch: IOException -> 0x0056, PHI: r1 r4
  0x0045: PHI (r1v6 java.io.FileInputStream) = (r1v4 java.io.FileInputStream), (r1v7 java.io.FileInputStream) binds: [B:33:0x0043, B:41:0x0053] A[DONT_GENERATE, DONT_INLINE]
  0x0045: PHI (r4v7 ??) = (r4v5 ??), (r4v8 ??) binds: [B:33:0x0043, B:41:0x0053] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #1 {IOException -> 0x0056, blocks: (B:32:0x0040, B:34:0x0045, B:40:0x0050), top: B:55:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0061 A[Catch: IOException -> 0x0064, TRY_LEAVE, TryCatch #9 {IOException -> 0x0064, blocks: (B:48:0x005c, B:50:0x0061), top: B:60:0x005c }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x005c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.util.zip.GZIPOutputStream] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13, types: [java.io.OutputStream, java.util.zip.GZIPOutputStream] */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.util.zip.GZIPOutputStream] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean gzipCompress(java.io.File r4, java.lang.String r5) throws java.lang.Throwable {
        /*
            java.io.File r5 = createFileIfUnExist(r5)
            r0 = 0
            if (r5 != 0) goto L8
            return r0
        L8:
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L36 java.io.IOException -> L39 java.io.FileNotFoundException -> L49
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L36 java.io.IOException -> L39 java.io.FileNotFoundException -> L49
            java.util.zip.GZIPOutputStream r4 = new java.util.zip.GZIPOutputStream     // Catch: java.lang.Throwable -> L2c java.io.IOException -> L2e java.io.FileNotFoundException -> L32
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L2c java.io.IOException -> L2e java.io.FileNotFoundException -> L32
            r3.<init>(r5)     // Catch: java.lang.Throwable -> L2c java.io.IOException -> L2e java.io.FileNotFoundException -> L32
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L2c java.io.IOException -> L2e java.io.FileNotFoundException -> L32
            copy(r2, r4)     // Catch: java.lang.Throwable -> L26 java.io.IOException -> L28 java.io.FileNotFoundException -> L2a
            r4.flush()     // Catch: java.lang.Throwable -> L26 java.io.IOException -> L28 java.io.FileNotFoundException -> L2a
            r2.close()     // Catch: java.io.IOException -> L24
            r4.close()     // Catch: java.io.IOException -> L24
        L24:
            r4 = 1
            return r4
        L26:
            r5 = move-exception
            goto L59
        L28:
            r5 = move-exception
            goto L30
        L2a:
            r5 = move-exception
            goto L34
        L2c:
            r5 = move-exception
            goto L5a
        L2e:
            r5 = move-exception
            r4 = r1
        L30:
            r1 = r2
            goto L3b
        L32:
            r5 = move-exception
            r4 = r1
        L34:
            r1 = r2
            goto L4b
        L36:
            r5 = move-exception
            r2 = r1
            goto L5a
        L39:
            r5 = move-exception
            r4 = r1
        L3b:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L57
            if (r1 == 0) goto L43
            r1.close()     // Catch: java.io.IOException -> L56
        L43:
            if (r4 == 0) goto L56
        L45:
            r4.close()     // Catch: java.io.IOException -> L56
            goto L56
        L49:
            r5 = move-exception
            r4 = r1
        L4b:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L57
            if (r1 == 0) goto L53
            r1.close()     // Catch: java.io.IOException -> L56
        L53:
            if (r4 == 0) goto L56
            goto L45
        L56:
            return r0
        L57:
            r5 = move-exception
            r2 = r1
        L59:
            r1 = r4
        L5a:
            if (r2 == 0) goto L5f
            r2.close()     // Catch: java.io.IOException -> L64
        L5f:
            if (r1 == 0) goto L64
            r1.close()     // Catch: java.io.IOException -> L64
        L64:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nirvana.tools.crash.FileUtils.gzipCompress(java.io.File, java.lang.String):boolean");
    }

    public static String readStringFromFile(File file) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        BufferedReader bufferedReader3;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        FileInputStream fileInputStream3 = null;
        if (file == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            try {
                FileInputStream fileInputStream4 = new FileInputStream(file);
                try {
                    BufferedReader bufferedReader4 = new BufferedReader(new InputStreamReader(fileInputStream4, "UTF-8"));
                    while (true) {
                        try {
                            String line = bufferedReader4.readLine();
                            if (line != null) {
                                stringBuffer.append(line);
                            } else {
                                try {
                                    break;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (FileNotFoundException e2) {
                            fileInputStream2 = fileInputStream4;
                            bufferedReader3 = bufferedReader4;
                            e = e2;
                            fileInputStream3 = fileInputStream2;
                            e.printStackTrace();
                            if (fileInputStream3 != null) {
                                try {
                                    fileInputStream3.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                    return stringBuffer.toString();
                                }
                            }
                            if (bufferedReader3 != null) {
                                bufferedReader3.close();
                            }
                            return stringBuffer.toString();
                        } catch (IOException e4) {
                            fileInputStream = fileInputStream4;
                            bufferedReader2 = bufferedReader4;
                            e = e4;
                            fileInputStream3 = fileInputStream;
                            e.printStackTrace();
                            if (fileInputStream3 != null) {
                                try {
                                    fileInputStream3.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                    return stringBuffer.toString();
                                }
                            }
                            if (bufferedReader2 != null) {
                                bufferedReader2.close();
                            }
                            return stringBuffer.toString();
                        } catch (Throwable unused) {
                            fileInputStream3 = fileInputStream4;
                            bufferedReader = bufferedReader4;
                            if (fileInputStream3 != null) {
                                try {
                                    fileInputStream3.close();
                                } catch (IOException e6) {
                                    e6.printStackTrace();
                                    return stringBuffer.toString();
                                }
                            }
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            return stringBuffer.toString();
                        }
                    }
                    fileInputStream4.close();
                    bufferedReader4.close();
                    return stringBuffer.toString();
                } catch (FileNotFoundException e7) {
                    e = e7;
                    fileInputStream2 = fileInputStream4;
                    bufferedReader3 = null;
                } catch (IOException e8) {
                    e = e8;
                    fileInputStream = fileInputStream4;
                    bufferedReader2 = null;
                } catch (Throwable unused2) {
                    bufferedReader = null;
                    fileInputStream3 = fileInputStream4;
                }
            } catch (Throwable unused3) {
            }
        } catch (FileNotFoundException e9) {
            e = e9;
            bufferedReader3 = null;
        } catch (IOException e10) {
            e = e10;
            bufferedReader2 = null;
        } catch (Throwable unused4) {
            bufferedReader = null;
        }
    }

    public static void renameFile(String str, String str2) {
        new File(str).renameTo(new File(str2));
    }

    public static String writeSelfJavaLog(Context context, SdkInfo sdkInfo, Thread thread, Throwable th, String str, String str2, String str3, Map<String, String> map) {
        String absolutePath = new File(context.getCacheDir(), "crash/".concat(String.valueOf(str3))).getAbsolutePath();
        createFileIfUnExist(absolutePath);
        long jMaxMemory = Runtime.getRuntime().maxMemory();
        String strJoin = Build.VERSION.SDK_INT >= 21 ? TextUtils.join(b.aj, Build.SUPPORTED_ABIS) : Build.CPU_ABI;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(absolutePath);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            outputStreamWriter.append((CharSequence) fillContent(context, str, strJoin, str2, jMaxMemory, sdkInfo, str3, map, thread, th));
            outputStreamWriter.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return absolutePath;
    }
}
