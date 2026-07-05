package com.sqwan.bugless.util;

import android.os.Environment;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class FileUtil {
    public static final String FILE_EXTENSION_SEPARATOR = ".";
    public static final String SEP = File.separator;
    public static final String SDPATH = Environment.getExternalStorageDirectory() + File.separator;

    private FileUtil() {
        throw new Error("‾╊‾");
    }

    public static boolean hasSdcard() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static String readFile(String filePath) throws IOException {
        return readFile(filePath, "utf-8");
    }

    public static String readFile(String filePath, String charsetName) throws Throwable {
        BufferedReader bufferedReader = null;
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }
        if (TextUtils.isEmpty(charsetName)) {
            charsetName = "utf-8";
        }
        File file = new File(filePath);
        StringBuilder sb = new StringBuilder("");
        if (!file.isFile()) {
            return null;
        }
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file), charsetName));
            while (true) {
                try {
                    String line = bufferedReader2.readLine();
                    if (line == null) {
                        break;
                    }
                    if (!sb.toString().equals("")) {
                        sb.append("\r\n");
                    }
                    sb.append(line);
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
            String string = sb.toString();
            try {
                bufferedReader2.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return string;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static List<String> readFileToList(String filePath) throws IOException {
        return readFileToList(filePath, "utf-8");
    }

    public static List<String> readFileToList(String filePath, String charsetName) throws Throwable {
        BufferedReader bufferedReader = null;
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }
        if (TextUtils.isEmpty(charsetName)) {
            charsetName = "utf-8";
        }
        File file = new File(filePath);
        ArrayList arrayList = new ArrayList();
        if (!file.isFile()) {
            return null;
        }
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file), charsetName));
            while (true) {
                try {
                    String line = bufferedReader2.readLine();
                    if (line != null) {
                        arrayList.add(line);
                    } else {
                        try {
                            break;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
            bufferedReader2.close();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean writeFile(String filePath, String content, boolean append) throws Throwable {
        if (TextUtils.isEmpty(filePath) || TextUtils.isEmpty(content)) {
            return false;
        }
        FileWriter fileWriter = null;
        try {
            createFile(filePath);
            FileWriter fileWriter2 = new FileWriter(filePath, append);
            try {
                fileWriter2.write(content);
                fileWriter2.flush();
                try {
                    fileWriter2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            } catch (Throwable th) {
                th = th;
                fileWriter = fileWriter2;
                if (fileWriter != null) {
                    try {
                        fileWriter.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean writeFile(String filePath, InputStream stream) throws IOException {
        return writeFile(filePath, stream, false);
    }

    public static boolean writeFile(String filePath, InputStream stream, boolean append) throws IOException {
        if (TextUtils.isEmpty(filePath)) {
            throw new NullPointerException("filePath is Empty");
        }
        if (stream == null) {
            throw new NullPointerException("InputStream is null");
        }
        return writeFile(new File(filePath), stream, append);
    }

    public static boolean writeFile(File file, InputStream stream) throws IOException {
        return writeFile(file, stream, false);
    }

    public static boolean writeFile(File file, InputStream stream, boolean append) throws Throwable {
        FileOutputStream fileOutputStream;
        if (file == null) {
            throw new NullPointerException("file = null");
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            createFile(file.getAbsolutePath());
            fileOutputStream = new FileOutputStream(file, append);
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int i = stream.read(bArr);
                if (i == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, i);
            }
            fileOutputStream.flush();
            try {
                fileOutputStream.close();
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                    stream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static boolean copyFile(String sourceFilePath, String destFilePath) throws IOException {
        return writeFile(destFilePath, new FileInputStream(sourceFilePath));
    }

    public static List<String> getFileNameList(String dirPath, FilenameFilter fileFilter) {
        if (fileFilter == null) {
            return getFileNameList(dirPath);
        }
        if (TextUtils.isEmpty(dirPath)) {
            return Collections.emptyList();
        }
        File[] fileArrListFiles = new File(dirPath).listFiles(fileFilter);
        if (fileArrListFiles == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (File file : fileArrListFiles) {
            if (file.isFile()) {
                arrayList.add(file.getName());
            }
        }
        return arrayList;
    }

    public static List<String> getFileNameList(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return Collections.emptyList();
        }
        File[] fileArrListFiles = new File(dirPath).listFiles();
        if (fileArrListFiles == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (File file : fileArrListFiles) {
            if (file.isFile()) {
                arrayList.add(file.getName());
            }
        }
        return arrayList;
    }

    public static List<String> getFileNameList(String dirPath, final String extension) {
        if (TextUtils.isEmpty(dirPath)) {
            return Collections.emptyList();
        }
        File[] fileArrListFiles = new File(dirPath).listFiles(new FilenameFilter() { // from class: com.sqwan.bugless.util.FileUtil.1
            @Override // java.io.FilenameFilter
            public boolean accept(File dir, String filename) {
                StringBuilder sb = new StringBuilder();
                sb.append(FileUtil.FILE_EXTENSION_SEPARATOR);
                sb.append(extension);
                return filename.indexOf(sb.toString()) > 0;
            }
        });
        if (fileArrListFiles == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (File file : fileArrListFiles) {
            if (file.isFile()) {
                arrayList.add(file.getName());
            }
        }
        return arrayList;
    }

    public static String getFileExtension(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }
        int iLastIndexOf = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        return (iLastIndexOf != -1 && filePath.lastIndexOf(File.separator) < iLastIndexOf) ? filePath.substring(iLastIndexOf + 1) : "";
    }

    public static boolean createFile(String path) {
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        return createFile(new File(path));
    }

    public static boolean createFile(File file) {
        if (file != null && makeDirs(getFolderName(file.getAbsolutePath())) && !file.exists()) {
            try {
                return file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean makeDirs(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }
        File file = new File(filePath);
        if (file.exists() && file.isDirectory()) {
            return true;
        }
        return file.mkdirs();
    }

    public static boolean makeDirs(File dir) {
        if (dir == null) {
            return false;
        }
        if (dir.exists() && dir.isDirectory()) {
            return true;
        }
        return dir.mkdirs();
    }

    public static boolean isFileExist(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }

    public static String getFileNameWithoutExtension(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }
        int iLastIndexOf = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        int iLastIndexOf2 = filePath.lastIndexOf(File.separator);
        if (iLastIndexOf2 == -1) {
            return iLastIndexOf == -1 ? filePath : filePath.substring(0, iLastIndexOf);
        }
        if (iLastIndexOf == -1) {
            return filePath.substring(iLastIndexOf2 + 1);
        }
        if (iLastIndexOf2 < iLastIndexOf) {
            return filePath.substring(iLastIndexOf2 + 1, iLastIndexOf);
        }
        return filePath.substring(iLastIndexOf2 + 1);
    }

    public static String getFileName(String filePath) {
        int iLastIndexOf;
        return (TextUtils.isEmpty(filePath) || (iLastIndexOf = filePath.lastIndexOf(File.separator)) == -1) ? filePath : filePath.substring(iLastIndexOf + 1);
    }

    public static String getFolderName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }
        int iLastIndexOf = filePath.lastIndexOf(File.separator);
        return iLastIndexOf == -1 ? "" : filePath.substring(0, iLastIndexOf);
    }

    public static boolean isFolderExist(String directoryPath) {
        if (TextUtils.isEmpty(directoryPath)) {
            return false;
        }
        File file = new File(directoryPath);
        return file.exists() && file.isDirectory();
    }

    public static boolean deleteFile(String path) {
        if (TextUtils.isEmpty(path)) {
            return true;
        }
        return deleteFile(new File(path));
    }

    public static boolean deleteFile(File file) {
        if (file == null) {
            throw new NullPointerException("file is null");
        }
        if (!file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return true;
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isFile()) {
                file2.delete();
            } else if (file2.isDirectory()) {
                deleteFile(file2.getAbsolutePath());
            }
        }
        return file.delete();
    }

    public static void delete(String dir, FilenameFilter filter) {
        File[] fileArrListFiles;
        if (TextUtils.isEmpty(dir)) {
            return;
        }
        File file = new File(dir);
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            }
            if (file.isDirectory()) {
                if (filter != null) {
                    fileArrListFiles = file.listFiles(filter);
                } else {
                    fileArrListFiles = file.listFiles();
                }
                if (fileArrListFiles == null) {
                    return;
                }
                for (File file2 : fileArrListFiles) {
                    if (file2.isFile()) {
                        file2.delete();
                    }
                }
            }
        }
    }

    public static long getFileSize(String path) {
        if (TextUtils.isEmpty(path)) {
            return -1L;
        }
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            return file.length();
        }
        return -1L;
    }
}
