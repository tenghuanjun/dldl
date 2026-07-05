package com.duowan.ark.helper;

import android.os.Environment;
import com.duowan.ark.ArkValue;
import com.duowan.ark.util.IOUtils;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class FileStorage {
    private File mCacheDirectory;
    private File mExtCacheDirectory;
    private File mExtFileDirectory;
    private File mFileDirectory;
    private File mSDCardDirectory;

    public enum Location {
        Cache,
        File,
        SDCard,
        ExtCache,
        ExtFile
    }

    /* synthetic */ FileStorage(AnonymousClass1 anonymousClass1) {
        this();
    }

    private static class Holder {
        private static FileStorage INSTANCE = new FileStorage(null);

        private Holder() {
        }
    }

    public static FileStorage instance() {
        return Holder.INSTANCE;
    }

    public static boolean isStoreExist(Location location) {
        return instance().getRoot(location) != null;
    }

    private FileStorage() {
        init();
    }

    public static File getRootDir(Location location) {
        return instance().getRoot(location);
    }

    /* JADX INFO: renamed from: com.duowan.ark.helper.FileStorage$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$duowan$ark$helper$FileStorage$Location;

        static {
            int[] iArr = new int[Location.values().length];
            $SwitchMap$com$duowan$ark$helper$FileStorage$Location = iArr;
            try {
                iArr[Location.Cache.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$duowan$ark$helper$FileStorage$Location[Location.File.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$duowan$ark$helper$FileStorage$Location[Location.SDCard.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$duowan$ark$helper$FileStorage$Location[Location.ExtCache.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$duowan$ark$helper$FileStorage$Location[Location.ExtFile.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private File getRoot(Location location) {
        int i = AnonymousClass1.$SwitchMap$com$duowan$ark$helper$FileStorage$Location[location.ordinal()];
        if (i == 1) {
            return this.mCacheDirectory;
        }
        if (i == 2) {
            return this.mFileDirectory;
        }
        if (i == 3) {
            return this.mSDCardDirectory;
        }
        if (i == 4) {
            return this.mExtCacheDirectory;
        }
        if (i != 5) {
            return null;
        }
        return this.mExtFileDirectory;
    }

    private void init() {
        this.mCacheDirectory = tryToGetCacheDir();
        this.mFileDirectory = tryToGetFileDir();
        this.mExtCacheDirectory = tryToGetExtCacheDir();
        this.mExtFileDirectory = tryToGetExtFileDir();
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

    private File tryToGetFileDir() {
        File filesDir = ArkValue.gContext.getFilesDir();
        if (testDirectoryAvailable(filesDir)) {
            return filesDir;
        }
        return null;
    }

    private File tryToGetExtCacheDir() {
        File externalCacheDir = ArkValue.gContext.getExternalCacheDir();
        if (testDirectoryAvailable(externalCacheDir)) {
            return externalCacheDir;
        }
        return null;
    }

    private File tryToGetExtFileDir() {
        File externalFilesDir = ArkValue.gContext.getExternalFilesDir(null);
        if (testDirectoryAvailable(externalFilesDir)) {
            return externalFilesDir;
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
        return IOUtils.createDirIfNoExist(file, ArkValue.gTag);
    }

    private boolean testDirectoryAvailable(File file) {
        return file != null && file.isDirectory() && file.canRead() && file.canWrite();
    }
}
