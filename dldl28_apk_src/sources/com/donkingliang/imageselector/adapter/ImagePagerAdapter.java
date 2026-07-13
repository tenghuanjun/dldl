package com.donkingliang.imageselector.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.donkingliang.imageselector.entry.Image;
import com.donkingliang.imageselector.utils.ImageUtil;
import com.donkingliang.imageselector.utils.VersionUtils;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ImagePagerAdapter extends PagerAdapter {
    private Context mContext;
    List<Image> mImgList;
    private OnItemClickListener mListener;
    private List<PhotoView> viewList = new ArrayList(4);
    private boolean isAndroidQ = VersionUtils.isAndroidQ();

    public interface OnItemClickListener {
        void onItemClick(int i, Image image);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public ImagePagerAdapter(Context context, List<Image> list) {
        this.mContext = context;
        createImageViews();
        this.mImgList = list;
    }

    private void createImageViews() {
        for (int i = 0; i < 4; i++) {
            PhotoView photoView = new PhotoView(this.mContext);
            photoView.setAdjustViewBounds(true);
            this.viewList.add(photoView);
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        List<Image> list = this.mImgList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (obj instanceof PhotoView) {
            PhotoView photoView = (PhotoView) obj;
            photoView.setImageDrawable(null);
            this.viewList.add(photoView);
            viewGroup.removeView(photoView);
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, final int i) {
        final PhotoView photoViewRemove = this.viewList.remove(0);
        final Image image = this.mImgList.get(i);
        viewGroup.addView(photoViewRemove);
        int i2 = 1080;
        int i3 = 720;
        if (image.isGif()) {
            photoViewRemove.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Glide.with(this.mContext).load(this.isAndroidQ ? image.getUri() : image.getPath()).apply((BaseRequestOptions<?>) new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE)).override(720, 1080).into(photoViewRemove);
        } else {
            Glide.with(this.mContext).asBitmap().apply((BaseRequestOptions<?>) new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE)).load(this.isAndroidQ ? image.getUri() : image.getPath()).into(new SimpleTarget<Bitmap>(i3, i2) { // from class: com.donkingliang.imageselector.adapter.ImagePagerAdapter.1
                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                }

                public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                    int width = bitmap.getWidth();
                    int height = bitmap.getHeight();
                    if (width <= 4096 && height <= 4096) {
                        ImagePagerAdapter.this.setBitmap(photoViewRemove, bitmap);
                    } else {
                        ImagePagerAdapter.this.setBitmap(photoViewRemove, ImageUtil.zoomBitmap(bitmap, 4096, 4096));
                    }
                }
            });
        }
        photoViewRemove.setOnClickListener(new View.OnClickListener() { // from class: com.donkingliang.imageselector.adapter.ImagePagerAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ImagePagerAdapter.this.mListener != null) {
                    ImagePagerAdapter.this.mListener.onItemClick(i, image);
                }
            }
        });
        return photoViewRemove;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBitmap(PhotoView photoView, Bitmap bitmap) {
        photoView.setImageBitmap(bitmap);
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int width2 = photoView.getWidth();
            int height2 = photoView.getHeight();
            if (width == 0 || height == 0 || width2 == 0 || height2 == 0) {
                return;
            }
            float f = height * 1.0f;
            float f2 = width;
            float f3 = height2;
            float f4 = width2;
            if (f / f2 > (1.0f * f3) / f4) {
                photoView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                adjustOffset(photoView, (((f * f4) / f2) - f3) / 2.0f);
            } else {
                photoView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mListener = onItemClickListener;
    }

    private void adjustOffset(PhotoView photoView, float f) {
        PhotoViewAttacher attacher = photoView.getAttacher();
        try {
            Field declaredField = PhotoViewAttacher.class.getDeclaredField("mBaseMatrix");
            declaredField.setAccessible(true);
            ((Matrix) declaredField.get(attacher)).postTranslate(0.0f, f);
            Method declaredMethod = PhotoViewAttacher.class.getDeclaredMethod("resetMatrix", null);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(attacher, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
