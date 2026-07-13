package com.donkingliang.imageselector.model;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.core.content.ContextCompat;
import com.donkingliang.imageselector.R;
import com.donkingliang.imageselector.entry.Folder;
import com.donkingliang.imageselector.entry.Image;
import com.donkingliang.imageselector.utils.ImageUtil;
import com.donkingliang.imageselector.utils.StringUtils;
import com.donkingliang.imageselector.utils.UriUtils;
import com.hjq.permissions.Permission;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class ImageModel {
    private static ArrayList<Folder> cacheImageList = null;
    private static boolean isNeedCache = false;
    private static PhotoContentObserver observer;

    public interface DataCallback {
        void onSuccess(ArrayList<Folder> arrayList);
    }

    public static void preloadAndRegisterContentObserver(Context context) {
        isNeedCache = true;
        if (observer == null) {
            observer = new PhotoContentObserver(context.getApplicationContext());
            context.getApplicationContext().getContentResolver().registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, observer);
        }
        preload(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void preload(Context context) {
        if (ContextCompat.checkSelfPermission(context, Permission.WRITE_EXTERNAL_STORAGE) == 0) {
            loadImageForSDCard(context, true, null);
        }
    }

    public static void clearCache(Context context) {
        isNeedCache = false;
        if (observer != null) {
            context.getApplicationContext().getContentResolver().unregisterContentObserver(observer);
            observer = null;
        }
        new Thread(new Runnable() { // from class: com.donkingliang.imageselector.model.ImageModel.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (ImageModel.class) {
                    if (ImageModel.cacheImageList != null) {
                        ImageModel.cacheImageList.clear();
                        ArrayList unused = ImageModel.cacheImageList = null;
                    }
                }
            }
        }).start();
    }

    public static void loadImageForSDCard(Context context, DataCallback dataCallback) {
        loadImageForSDCard(context, false, dataCallback);
    }

    private static void loadImageForSDCard(final Context context, final boolean z, final DataCallback dataCallback) {
        new Thread(new Runnable() { // from class: com.donkingliang.imageselector.model.ImageModel.2
            @Override // java.lang.Runnable
            public void run() {
                ArrayList<Folder> arrayListSplitFolder;
                synchronized (ImageModel.class) {
                    String imageCacheDir = ImageUtil.getImageCacheDir(context);
                    if (ImageModel.cacheImageList == null || z) {
                        ArrayList<Image> arrayListLoadImage = ImageModel.loadImage(context);
                        Collections.sort(arrayListLoadImage, new Comparator<Image>() { // from class: com.donkingliang.imageselector.model.ImageModel.2.1
                            @Override // java.util.Comparator
                            public int compare(Image image, Image image2) {
                                if (image.getTime() > image2.getTime()) {
                                    return 1;
                                }
                                return image.getTime() < image2.getTime() ? -1 : 0;
                            }
                        });
                        ArrayList arrayList = new ArrayList();
                        for (Image image : arrayListLoadImage) {
                            boolean z2 = !"downloading".equals(ImageModel.getExtensionName(image.getPath())) && ImageModel.checkImgExists(image.getPath());
                            if (!ImageUtil.isCutImage(imageCacheDir, image.getPath()) && z2) {
                                arrayList.add(image);
                            }
                        }
                        Collections.reverse(arrayList);
                        arrayListSplitFolder = ImageModel.splitFolder(context, arrayList);
                        if (ImageModel.isNeedCache) {
                            ArrayList unused = ImageModel.cacheImageList = arrayListSplitFolder;
                        }
                    } else {
                        arrayListSplitFolder = ImageModel.cacheImageList;
                    }
                    DataCallback dataCallback2 = dataCallback;
                    if (dataCallback2 != null) {
                        dataCallback2.onSuccess(arrayListSplitFolder);
                    }
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized ArrayList<Image> loadImage(Context context) {
        ArrayList<Image> arrayList;
        Cursor cursorQuery = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "_display_name", "date_added", "_id", "mime_type", "_size"}, "_size>0", null, "date_added DESC");
        arrayList = new ArrayList<>();
        if (cursorQuery != null) {
            while (cursorQuery.moveToNext()) {
                long j = cursorQuery.getLong(cursorQuery.getColumnIndex("_id"));
                String string = cursorQuery.getString(cursorQuery.getColumnIndex("_data"));
                String string2 = cursorQuery.getString(cursorQuery.getColumnIndex("_display_name"));
                long j2 = cursorQuery.getLong(cursorQuery.getColumnIndex("date_added"));
                if (String.valueOf(j2).length() < 13) {
                    j2 *= 1000;
                }
                arrayList.add(new Image(string, j2, string2, cursorQuery.getString(cursorQuery.getColumnIndex("mime_type")), MediaStore.Images.Media.EXTERNAL_CONTENT_URI.buildUpon().appendPath(String.valueOf(j)).build()));
            }
            cursorQuery.close();
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean checkImgExists(String str) {
        return new File(str).exists();
    }

    private static String getPathForAndroidQ(Context context, long j) {
        return UriUtils.getPathForUri(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI.buildUpon().appendPath(String.valueOf(j)).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ArrayList<Folder> splitFolder(Context context, ArrayList<Image> arrayList) {
        ArrayList<Folder> arrayList2 = new ArrayList<>();
        arrayList2.add(new Folder(context.getString(R.string.selector_all_image), arrayList));
        if (arrayList != null && !arrayList.isEmpty()) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                String folderName = getFolderName(arrayList.get(i).getPath());
                if (StringUtils.isNotEmptyString(folderName)) {
                    getFolder(folderName, arrayList2).addImage(arrayList.get(i));
                }
            }
        }
        return arrayList2;
    }

    public static String getExtensionName(String str) {
        int iLastIndexOf;
        if (str != null && str.length() > 0 && (iLastIndexOf = str.lastIndexOf(46)) > -1 && iLastIndexOf < str.length() - 1) {
            return str.substring(iLastIndexOf + 1);
        }
        return "";
    }

    private static String getFolderName(String str) {
        if (StringUtils.isNotEmptyString(str)) {
            String[] strArrSplit = str.split(File.separator);
            if (strArrSplit.length >= 2) {
                return strArrSplit[strArrSplit.length - 2];
            }
            return "";
        }
        return "";
    }

    private static Folder getFolder(String str, List<Folder> list) {
        if (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Folder folder = list.get(i);
                if (str.equals(folder.getName())) {
                    return folder;
                }
            }
        }
        Folder folder2 = new Folder(str);
        list.add(folder2);
        return folder2;
    }

    private static class PhotoContentObserver extends ContentObserver {
        private Context context;

        public PhotoContentObserver(Context context) {
            super(null);
            this.context = context;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            ImageModel.preload(this.context);
        }
    }
}
