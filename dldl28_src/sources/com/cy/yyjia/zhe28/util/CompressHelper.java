package com.cy.yyjia.zhe28.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public class CompressHelper {
    private static volatile CompressHelper INSTANCE;
    private Bitmap.Config bitmapConfig;
    private Bitmap.CompressFormat compressFormat;
    private Context context;
    private String destinationDirectoryPath;
    private String fileName;
    private String fileNamePrefix;
    private float maxHeight;
    private float maxWidth;
    private int quality;

    public static CompressHelper getDefault(Context context) {
        if (INSTANCE == null) {
            synchronized (CompressHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CompressHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    private CompressHelper(Context context) {
        this.maxWidth = 720.0f;
        this.maxHeight = 960.0f;
        this.compressFormat = Bitmap.CompressFormat.JPEG;
        this.bitmapConfig = Bitmap.Config.ARGB_8888;
        this.quality = 80;
        this.context = context;
        this.destinationDirectoryPath = context.getCacheDir().getPath() + File.pathSeparator + FileUtil.FILES_PATH;
    }

    public File compressToFile(File file) {
        return BitmapUtil.compressImage(this.context, Uri.fromFile(file), this.maxWidth, this.maxHeight, this.compressFormat, this.bitmapConfig, this.quality, this.destinationDirectoryPath, this.fileNamePrefix, this.fileName);
    }

    public File compressToFile(Uri uri) {
        return BitmapUtil.compressImage(this.context, uri, this.maxWidth, this.maxHeight, this.compressFormat, this.bitmapConfig, this.quality, this.destinationDirectoryPath, this.fileNamePrefix, this.fileName);
    }

    public Bitmap compressToBitmap(File file) {
        return BitmapUtil.getScaledBitmap(this.context, Uri.fromFile(file), this.maxWidth, this.maxHeight, this.bitmapConfig);
    }

    public static class Builder {
        private CompressHelper mCompressHelper;

        public Builder(Context context) {
            this.mCompressHelper = new CompressHelper(context);
        }

        public Builder setMaxWidth(float maxWidth) {
            this.mCompressHelper.maxWidth = maxWidth;
            return this;
        }

        public Builder setMaxHeight(float maxHeight) {
            this.mCompressHelper.maxHeight = maxHeight;
            return this;
        }

        public Builder setCompressFormat(Bitmap.CompressFormat compressFormat) {
            this.mCompressHelper.compressFormat = compressFormat;
            return this;
        }

        public Builder setBitmapConfig(Bitmap.Config bitmapConfig) {
            this.mCompressHelper.bitmapConfig = bitmapConfig;
            return this;
        }

        public Builder setQuality(int quality) {
            this.mCompressHelper.quality = quality;
            return this;
        }

        public Builder setDestinationDirectoryPath(String destinationDirectoryPath) {
            this.mCompressHelper.destinationDirectoryPath = destinationDirectoryPath;
            return this;
        }

        public Builder setFileNamePrefix(String prefix) {
            this.mCompressHelper.fileNamePrefix = prefix;
            return this;
        }

        public Builder setFileName(String fileName) {
            this.mCompressHelper.fileName = fileName;
            return this;
        }

        public CompressHelper build() {
            return this.mCompressHelper;
        }
    }
}
