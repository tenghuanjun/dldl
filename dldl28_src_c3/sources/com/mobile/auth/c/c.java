package com.mobile.auth.c;

import android.content.Context;
import android.text.TextUtils;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class c {
    public static String a(Context context) {
        InputStreamReader inputStreamReader;
        FileInputStream fileInputStream;
        BufferedReader bufferedReader;
        try {
            File fileC = c(context);
            StringBuilder sb = new StringBuilder();
            if (fileC == null || !fileC.exists()) {
                return "";
            }
            try {
                fileInputStream = new FileInputStream(fileC);
                try {
                    inputStreamReader = new InputStreamReader(fileInputStream);
                    try {
                        bufferedReader = new BufferedReader(inputStreamReader);
                        while (true) {
                            try {
                                String line = bufferedReader.readLine();
                                if (line != null) {
                                    sb.append(line);
                                } else {
                                    try {
                                        break;
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } catch (Throwable th) {
                                th = th;
                                try {
                                    th.printStackTrace();
                                    if (bufferedReader != null) {
                                        try {
                                            bufferedReader.close();
                                        } catch (Exception e2) {
                                            e2.printStackTrace();
                                        }
                                    }
                                    if (inputStreamReader != null) {
                                        try {
                                            inputStreamReader.close();
                                        } catch (Exception e3) {
                                            e3.printStackTrace();
                                        }
                                    }
                                    if (fileInputStream != null) {
                                        try {
                                            fileInputStream.close();
                                        } catch (Exception e4) {
                                            e = e4;
                                            e.printStackTrace();
                                        }
                                    }
                                } finally {
                                }
                            }
                        }
                        bufferedReader.close();
                        try {
                            inputStreamReader.close();
                        } catch (Exception e5) {
                            e5.printStackTrace();
                        }
                        try {
                            fileInputStream.close();
                        } catch (Exception e6) {
                            e = e6;
                            e.printStackTrace();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader = null;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    inputStreamReader = null;
                    bufferedReader = null;
                }
            } catch (Throwable th4) {
                th = th4;
                inputStreamReader = null;
                fileInputStream = null;
                bufferedReader = null;
            }
            return sb.toString();
        } catch (Throwable th5) {
            try {
                ExceptionProcessor.processException(th5);
                return null;
            } catch (Throwable th6) {
                ExceptionProcessor.processException(th6);
                return null;
            }
        }
    }

    public static void a(Context context, String str) {
        try {
            File fileC = c(context);
            if (fileC == null || !fileC.exists()) {
                a(b(context), str);
            } else {
                a(fileC, str);
            }
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.BufferedWriter] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.BufferedWriter] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v9 */
    private static void a(File file, String str) {
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;
        if (file != null) {
            try {
                if (file.exists()) {
                    ?? IsEmpty = 0;
                    IsEmpty = 0;
                    IsEmpty = 0;
                    IsEmpty = 0;
                    try {
                        try {
                            fileWriter = new FileWriter(file, false);
                            try {
                                bufferedWriter = new BufferedWriter(fileWriter);
                            } catch (Exception e) {
                                e = e;
                            }
                        } catch (Throwable th) {
                            th = th;
                        }
                        try {
                            IsEmpty = TextUtils.isEmpty(str);
                            if (IsEmpty != 0) {
                                str = "";
                            }
                            bufferedWriter.write(str);
                            bufferedWriter.flush();
                            try {
                                bufferedWriter.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            try {
                                fileWriter.close();
                            } catch (Exception e3) {
                                e = e3;
                                e.printStackTrace();
                            }
                        } catch (Exception e4) {
                            e = e4;
                            IsEmpty = bufferedWriter;
                            e.printStackTrace();
                            if (IsEmpty != 0) {
                                try {
                                    IsEmpty.close();
                                } catch (Exception e5) {
                                    e5.printStackTrace();
                                }
                            }
                            if (fileWriter != null) {
                                try {
                                    fileWriter.close();
                                } catch (Exception e6) {
                                    e = e6;
                                    e.printStackTrace();
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            IsEmpty = bufferedWriter;
                            if (IsEmpty != 0) {
                                try {
                                    IsEmpty.close();
                                } catch (Exception e7) {
                                    e7.printStackTrace();
                                }
                            }
                            if (fileWriter == null) {
                                throw th;
                            }
                            try {
                                fileWriter.close();
                                throw th;
                            } catch (Exception e8) {
                                e8.printStackTrace();
                                throw th;
                            }
                        }
                    } catch (Exception e9) {
                        e = e9;
                        fileWriter = null;
                    } catch (Throwable th3) {
                        th = th3;
                        fileWriter = null;
                    }
                }
            } catch (Throwable th4) {
                try {
                    ExceptionProcessor.processException(th4);
                } catch (Throwable th5) {
                    ExceptionProcessor.processException(th5);
                }
            }
        }
    }

    private static File b(Context context) {
        if (context != null) {
            try {
                try {
                    File file = new File(context.getFilesDir() + "/eAccount/Log/");
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File file2 = new File(file, "ipa_ol.ds");
                    if (file2.exists()) {
                        file2.delete();
                    }
                    file2.createNewFile();
                    return file2;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                    return null;
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                }
            }
        }
        return null;
    }

    private static File c(Context context) {
        try {
            if (context != null) {
                try {
                    File file = new File(context.getFilesDir() + "/eAccount/Log/");
                    if (!file.exists()) {
                        return null;
                    }
                    File file2 = new File(file, "ipa_ol.ds");
                    if (file2.exists()) {
                        return file2;
                    }
                    return null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }
}
