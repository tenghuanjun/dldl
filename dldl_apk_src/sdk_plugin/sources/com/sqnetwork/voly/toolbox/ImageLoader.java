package com.sqnetwork.voly.toolbox;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import com.sqnetwork.voly.Request;
import com.sqnetwork.voly.RequestQueue;
import com.sqnetwork.voly.Response;
import com.sqnetwork.voly.VolleyError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ImageLoader {
    private final ImageCache mCache;
    private final RequestQueue mRequestQueue;
    private Runnable mRunnable;
    private int mBatchResponseDelayMs = 100;
    private final HashMap<String, BatchedImageRequest> mInFlightRequests = new HashMap<>();
    private final HashMap<String, BatchedImageRequest> mBatchedResponses = new HashMap<>();
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public interface ImageCache {
        Bitmap getBitmap(String url);

        void putBitmap(String url, Bitmap bitmap);
    }

    public interface ImageListener extends Response.ErrorListener {
        void onResponse(ImageContainer response, boolean isImmediate);
    }

    public ImageLoader(RequestQueue queue, ImageCache imageCache) {
        this.mRequestQueue = queue;
        this.mCache = imageCache;
    }

    public static ImageListener getImageListener(final ImageView view, final int defaultImageResId, final int errorImageResId) {
        return new ImageListener() { // from class: com.sqnetwork.voly.toolbox.ImageLoader.1
            @Override // com.sqnetwork.voly.Response.ErrorListener
            public void onErrorResponse(VolleyError error) {
                int i = errorImageResId;
                if (i != 0) {
                    view.setImageResource(i);
                }
            }

            @Override // com.sqnetwork.voly.toolbox.ImageLoader.ImageListener
            public void onResponse(ImageContainer response, boolean isImmediate) {
                if (response.getBitmap() != null) {
                    view.setImageBitmap(response.getBitmap());
                    return;
                }
                int i = defaultImageResId;
                if (i != 0) {
                    view.setImageResource(i);
                }
            }
        };
    }

    public boolean isCached(String requestUrl, int maxWidth, int maxHeight) {
        return isCached(requestUrl, maxWidth, maxHeight, ImageView.ScaleType.CENTER_INSIDE);
    }

    public boolean isCached(String requestUrl, int maxWidth, int maxHeight, ImageView.ScaleType scaleType) {
        Threads.throwIfNotOnMainThread();
        return this.mCache.getBitmap(getCacheKey(requestUrl, maxWidth, maxHeight, scaleType)) != null;
    }

    public ImageContainer get(String requestUrl, final ImageListener listener) {
        return get(requestUrl, listener, 0, 0);
    }

    public ImageContainer get(String requestUrl, ImageListener imageListener, int maxWidth, int maxHeight) {
        return get(requestUrl, imageListener, maxWidth, maxHeight, ImageView.ScaleType.CENTER_INSIDE);
    }

    public ImageContainer get(String requestUrl, ImageListener imageListener, int maxWidth, int maxHeight, ImageView.ScaleType scaleType) {
        Threads.throwIfNotOnMainThread();
        String cacheKey = getCacheKey(requestUrl, maxWidth, maxHeight, scaleType);
        Bitmap bitmap = this.mCache.getBitmap(cacheKey);
        if (bitmap != null) {
            ImageContainer imageContainer = new ImageContainer(bitmap, requestUrl, null, null);
            imageListener.onResponse(imageContainer, true);
            return imageContainer;
        }
        ImageContainer imageContainer2 = new ImageContainer(null, requestUrl, cacheKey, imageListener);
        imageListener.onResponse(imageContainer2, true);
        BatchedImageRequest batchedImageRequest = this.mInFlightRequests.get(cacheKey);
        if (batchedImageRequest != null) {
            batchedImageRequest.addContainer(imageContainer2);
            return imageContainer2;
        }
        Request<Bitmap> requestMakeImageRequest = makeImageRequest(requestUrl, maxWidth, maxHeight, scaleType, cacheKey);
        this.mRequestQueue.add(requestMakeImageRequest);
        this.mInFlightRequests.put(cacheKey, new BatchedImageRequest(requestMakeImageRequest, imageContainer2));
        return imageContainer2;
    }

    protected Request<Bitmap> makeImageRequest(String requestUrl, int maxWidth, int maxHeight, ImageView.ScaleType scaleType, final String cacheKey) {
        return new ImageRequest(requestUrl, new Response.Listener<Bitmap>() { // from class: com.sqnetwork.voly.toolbox.ImageLoader.2
            @Override // com.sqnetwork.voly.Response.Listener
            public void onResponse(Response<Bitmap> raw, Bitmap response) {
                ImageLoader.this.onGetImageSuccess(cacheKey, response);
            }
        }, maxWidth, maxHeight, scaleType, Bitmap.Config.RGB_565, new Response.ErrorListener() { // from class: com.sqnetwork.voly.toolbox.ImageLoader.3
            @Override // com.sqnetwork.voly.Response.ErrorListener
            public void onErrorResponse(VolleyError error) {
                ImageLoader.this.onGetImageError(cacheKey, error);
            }
        });
    }

    public void setBatchedResponseDelay(int newBatchedResponseDelayMs) {
        this.mBatchResponseDelayMs = newBatchedResponseDelayMs;
    }

    protected void onGetImageSuccess(String cacheKey, Bitmap response) {
        this.mCache.putBitmap(cacheKey, response);
        BatchedImageRequest batchedImageRequestRemove = this.mInFlightRequests.remove(cacheKey);
        if (batchedImageRequestRemove != null) {
            batchedImageRequestRemove.mResponseBitmap = response;
            batchResponse(cacheKey, batchedImageRequestRemove);
        }
    }

    protected void onGetImageError(String cacheKey, VolleyError error) {
        BatchedImageRequest batchedImageRequestRemove = this.mInFlightRequests.remove(cacheKey);
        if (batchedImageRequestRemove != null) {
            batchedImageRequestRemove.setError(error);
            batchResponse(cacheKey, batchedImageRequestRemove);
        }
    }

    public class ImageContainer {
        private Bitmap mBitmap;
        private final String mCacheKey;
        private final ImageListener mListener;
        private final String mRequestUrl;

        public ImageContainer(Bitmap bitmap, String requestUrl, String cacheKey, ImageListener listener) {
            this.mBitmap = bitmap;
            this.mRequestUrl = requestUrl;
            this.mCacheKey = cacheKey;
            this.mListener = listener;
        }

        public void cancelRequest() {
            Threads.throwIfNotOnMainThread();
            if (this.mListener == null) {
                return;
            }
            BatchedImageRequest batchedImageRequest = (BatchedImageRequest) ImageLoader.this.mInFlightRequests.get(this.mCacheKey);
            if (batchedImageRequest == null) {
                BatchedImageRequest batchedImageRequest2 = (BatchedImageRequest) ImageLoader.this.mBatchedResponses.get(this.mCacheKey);
                if (batchedImageRequest2 != null) {
                    batchedImageRequest2.removeContainerAndCancelIfNecessary(this);
                    if (batchedImageRequest2.mContainers.size() == 0) {
                        ImageLoader.this.mBatchedResponses.remove(this.mCacheKey);
                        return;
                    }
                    return;
                }
                return;
            }
            if (batchedImageRequest.removeContainerAndCancelIfNecessary(this)) {
                ImageLoader.this.mInFlightRequests.remove(this.mCacheKey);
            }
        }

        public Bitmap getBitmap() {
            return this.mBitmap;
        }

        public String getRequestUrl() {
            return this.mRequestUrl;
        }
    }

    private static class BatchedImageRequest {
        private final List<ImageContainer> mContainers;
        private VolleyError mError;
        private final Request<?> mRequest;
        private Bitmap mResponseBitmap;

        public BatchedImageRequest(Request<?> request, ImageContainer container) {
            ArrayList arrayList = new ArrayList();
            this.mContainers = arrayList;
            this.mRequest = request;
            arrayList.add(container);
        }

        public void setError(VolleyError error) {
            this.mError = error;
        }

        public VolleyError getError() {
            return this.mError;
        }

        public void addContainer(ImageContainer container) {
            this.mContainers.add(container);
        }

        public boolean removeContainerAndCancelIfNecessary(ImageContainer container) {
            this.mContainers.remove(container);
            if (this.mContainers.size() != 0) {
                return false;
            }
            this.mRequest.cancel();
            return true;
        }
    }

    private void batchResponse(String cacheKey, BatchedImageRequest request) {
        this.mBatchedResponses.put(cacheKey, request);
        if (this.mRunnable == null) {
            Runnable runnable = new Runnable() { // from class: com.sqnetwork.voly.toolbox.ImageLoader.4
                @Override // java.lang.Runnable
                public void run() {
                    for (BatchedImageRequest batchedImageRequest : ImageLoader.this.mBatchedResponses.values()) {
                        for (ImageContainer imageContainer : batchedImageRequest.mContainers) {
                            if (imageContainer.mListener != null) {
                                if (batchedImageRequest.getError() == null) {
                                    imageContainer.mBitmap = batchedImageRequest.mResponseBitmap;
                                    imageContainer.mListener.onResponse(imageContainer, false);
                                } else {
                                    imageContainer.mListener.onErrorResponse(batchedImageRequest.getError());
                                }
                            }
                        }
                    }
                    ImageLoader.this.mBatchedResponses.clear();
                    ImageLoader.this.mRunnable = null;
                }
            };
            this.mRunnable = runnable;
            this.mHandler.postDelayed(runnable, this.mBatchResponseDelayMs);
        }
    }

    private static String getCacheKey(String url, int maxWidth, int maxHeight, ImageView.ScaleType scaleType) {
        StringBuilder sb = new StringBuilder(url.length() + 12);
        sb.append("#W");
        sb.append(maxWidth);
        sb.append("#H");
        sb.append(maxHeight);
        sb.append("#S");
        sb.append(scaleType.ordinal());
        sb.append(url);
        return sb.toString();
    }
}
