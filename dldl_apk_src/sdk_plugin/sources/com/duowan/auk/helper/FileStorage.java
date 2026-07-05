package com.duowan.auk.helper;

import android.os.Environment;
import com.duowan.auk.ArkValue;
import com.huya.mtp.utils.ArrayUtils;
import com.huya.mtp.utils.IOUtils;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class FileStorage {
    private static FileStorage ourInstance = new FileStorage();
    private File mCacheDirectory;
    private File mSDCardDirectory;

    public enum Location {
        Cache,
        SDCard
    }

    public static FileStorage getInstance() {
        return ourInstance;
    }

    public static boolean isStoreExist(Location location) {
        return getInstance().getRootDir(location) != null;
    }

    private FileStorage() {
        init();
    }

    /* JADX INFO: renamed from: com.duowan.auk.helper.FileStorage$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$duowan$auk$helper$FileStorage$Location;

        static {
            int[] iArr = new int[Location.values().length];
            $SwitchMap$com$duowan$auk$helper$FileStorage$Location = iArr;
            try {
                iArr[Location.Cache.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$duowan$auk$helper$FileStorage$Location[Location.SDCard.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public File getRootDir(Location location) {
        int i = AnonymousClass1.$SwitchMap$com$duowan$auk$helper$FileStorage$Location[location.ordinal()];
        if (i == 1) {
            return this.mCacheDirectory;
        }
        if (i != 2) {
            return null;
        }
        return this.mSDCardDirectory;
    }

    public long getSize(Location location) {
        return IOUtils.getFileSize(getRootDir(location));
    }

    public boolean clear(Location location) {
        return IOUtils.removeFile(getRootDir(location), false);
    }

    public boolean exist(Location location, String str) {
        PathInfo path = parsePath(str);
        return exist(location, path.fileName, path.dirs);
    }

    public byte[] readBytes(Location location, String str) {
        PathInfo path = parsePath(str);
        return readBytes(location, path.fileName, path.dirs);
    }

    public boolean writeBytes(Location location, String str, byte[] bArr) {
        PathInfo path = parsePath(str);
        return writeBytes(location, path.fileName, bArr, path.dirs);
    }

    public boolean exist(Location location, String str, String... strArr) {
        return getFile(location, str, strArr) != null;
    }

    public File getFile(Location location, String str, String... strArr) {
        return IOUtils.getFile(getRootDir(location), str, strArr);
    }

    public byte[] readBytes(Location location, String str, String... strArr) {
        return IOUtils.readBytes(getRootDir(location), str, strArr);
    }

    public boolean writeBytes(Location location, String str, byte[] bArr, String... strArr) {
        return IOUtils.writeBytes(getRootDir(location), str, bArr, strArr);
    }

    private void init() {
        this.mCacheDirectory = tryToGetCacheDir();
        File fileTryToGetSDCardDir = tryToGetSDCardDir();
        this.mSDCardDirectory = fileTryToGetSDCardDir;
        if (this.mCacheDirectory == null && fileTryToGetSDCardDir != null) {
            this.mCacheDirectory = IOUtils.createDirIfNoExist(fileTryToGetSDCardDir, "cache");
            return;
        }
        File file = this.mCacheDirectory;
        if (file == null || this.mSDCardDirectory != null) {
            return;
        }
        this.mSDCardDirectory = createOwnSDCardDirIfNoExist(file);
    }

    private File tryToGetCacheDir() {
        File cacheDir = ArkValue.gContext.getCacheDir();
        if (testDirectoryAvailable(cacheDir)) {
            return cacheDir;
        }
        return null;
    }

    private File tryToGetSDCardDir() {
        File externalStorageDirectory;
        File fileCreateOwnSDCardDirIfNoExist;
        try {
            externalStorageDirectory = Environment.getExternalStorageDirectory();
            fileCreateOwnSDCardDirIfNoExist = createOwnSDCardDirIfNoExist(externalStorageDirectory);
        } catch (Exception unused) {
        }
        if (testDirectoryAvailable(fileCreateOwnSDCardDirIfNoExist)) {
            return fileCreateOwnSDCardDirIfNoExist;
        }
        File fileCreateOwnSDCardDirIfNoExist2 = createOwnSDCardDirIfNoExist(new File(externalStorageDirectory.getAbsolutePath().replace("0", "1")));
        if (testDirectoryAvailable(fileCreateOwnSDCardDirIfNoExist2)) {
            return fileCreateOwnSDCardDirIfNoExist2;
        }
        return null;
    }

    private File createOwnSDCardDirIfNoExist(File file) {
        return IOUtils.createDirIfNoExist(file, ArkValue.sPackageName);
    }

    private boolean testDirectoryAvailable(File file) {
        return file != null && file.isDirectory() && file.canRead() && file.canWrite();
    }

    private static PathInfo parsePath(String str) {
        if (str.startsWith(File.separator)) {
            str = str.substring(1);
        }
        if (str.length() == 0) {
            throw new IllegalArgumentException("relativePath is empty");
        }
        String[] strArrSplit = str.split(File.separator);
        int length = strArrSplit.length;
        PathInfo pathInfo = new PathInfo(null);
        if (length == 1) {
            pathInfo.fileName = str;
            pathInfo.dirs = new String[0];
        } else {
            int i = length - 1;
            String[] strArr = new String[i];
            ArrayUtils.remove(strArrSplit, strArr, strArrSplit.length - 1);
            pathInfo.fileName = strArrSplit[i];
            pathInfo.dirs = strArr;
        }
        return pathInfo;
    }

    private static class PathInfo {
        public String[] dirs;
        public String fileName;

        private PathInfo() {
        }

        /* synthetic */ PathInfo(AnonymousClass1 anonymousClass1) {
            this();
        }
    }
}
