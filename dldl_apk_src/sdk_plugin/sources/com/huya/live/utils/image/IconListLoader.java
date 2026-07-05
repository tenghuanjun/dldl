package com.huya.live.utils.image;

import android.graphics.Bitmap;
import com.duowan.auk.ArkValue;
import com.huya.mtp.utils.FP;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class IconListLoader {
    public static final int MAX_SIZE = 80;
    private List<Bitmap> mBitmapList = new ArrayList();
    private Listener mListener;
    private List<String> mUrls;

    public interface Listener {
        void onEnd(List<Bitmap> list);
    }

    public IconListLoader(List<String> list, Listener listener) {
        this.mUrls = null;
        this.mListener = null;
        this.mUrls = list;
        this.mListener = listener;
    }

    public void loadNext(Bitmap bitmap) {
        if (bitmap != null) {
            this.mBitmapList.add(bitmap);
        }
        if (!FP.empty(this.mUrls) && this.mUrls.size() > 1) {
            this.mUrls.remove(0);
            load();
        } else {
            Listener listener = this.mListener;
            if (listener != null) {
                listener.onEnd(this.mBitmapList);
            }
        }
    }

    public void load() {
        if (FP.empty(this.mUrls)) {
            Listener listener = this.mListener;
            if (listener != null) {
                listener.onEnd(null);
                return;
            }
            return;
        }
        ImageLoaderProxy.getInstance().loadImage(ArkValue.gContext, this.mUrls.get(0), this);
    }
}
