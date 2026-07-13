package com.donkingliang.imageselector.utils;

import android.app.Activity;
import android.content.Context;
import androidx.fragment.app.Fragment;
import com.donkingliang.imageselector.ClipImageActivity;
import com.donkingliang.imageselector.ImageSelectorActivity;
import com.donkingliang.imageselector.entry.RequestConfig;
import com.donkingliang.imageselector.model.ImageModel;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class ImageSelector {
    public static final String IS_CAMERA_IMAGE = "is_camera_image";
    public static final String IS_CONFIRM = "is_confirm";
    public static final String IS_SINGLE = "is_single";
    public static final String KEY_CONFIG = "key_config";
    public static final String MAX_SELECT_COUNT = "max_select_count";
    public static final String POSITION = "position";
    public static final int RESULT_CODE = 18;
    public static final String SELECT_RESULT = "select_result";

    public static void preload(Context context) {
        ImageModel.preloadAndRegisterContentObserver(context);
    }

    public static void clearCache(Context context) {
        ImageModel.clearCache(context);
    }

    public static ImageSelectorBuilder builder() {
        return new ImageSelectorBuilder();
    }

    public static class ImageSelectorBuilder {
        private RequestConfig config;

        private ImageSelectorBuilder() {
            this.config = new RequestConfig();
        }

        public ImageSelectorBuilder setCrop(boolean z) {
            this.config.isCrop = z;
            return this;
        }

        public ImageSelectorBuilder setCropRatio(float f) {
            this.config.cropRatio = f;
            return this;
        }

        public ImageSelectorBuilder setSingle(boolean z) {
            this.config.isSingle = z;
            return this;
        }

        @Deprecated
        public ImageSelectorBuilder setViewImage(boolean z) {
            this.config.canPreview = z;
            return this;
        }

        public ImageSelectorBuilder canPreview(boolean z) {
            this.config.canPreview = z;
            return this;
        }

        public ImageSelectorBuilder useCamera(boolean z) {
            this.config.useCamera = z;
            return this;
        }

        public ImageSelectorBuilder onlyTakePhoto(boolean z) {
            this.config.onlyTakePhoto = z;
            return this;
        }

        public ImageSelectorBuilder setMaxSelectCount(int i) {
            this.config.maxSelectCount = i;
            return this;
        }

        public ImageSelectorBuilder setSelected(ArrayList<String> arrayList) {
            this.config.selected = arrayList;
            return this;
        }

        public void start(Activity activity, int i) {
            this.config.requestCode = i;
            if (this.config.onlyTakePhoto) {
                this.config.useCamera = true;
            }
            if (this.config.isCrop) {
                ClipImageActivity.openActivity(activity, i, this.config);
            } else {
                ImageSelectorActivity.openActivity(activity, i, this.config);
            }
        }

        public void start(Fragment fragment, int i) {
            this.config.requestCode = i;
            if (this.config.onlyTakePhoto) {
                this.config.useCamera = true;
            }
            if (this.config.isCrop) {
                ClipImageActivity.openActivity(fragment, i, this.config);
            } else {
                ImageSelectorActivity.openActivity(fragment, i, this.config);
            }
        }

        public void start(android.app.Fragment fragment, int i) {
            this.config.requestCode = i;
            if (this.config.onlyTakePhoto) {
                this.config.useCamera = true;
            }
            if (this.config.isCrop) {
                ClipImageActivity.openActivity(fragment, i, this.config);
            } else {
                ImageSelectorActivity.openActivity(fragment, i, this.config);
            }
        }
    }
}
