package com.sqwan.liveshow.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import com.sqwan.common.util.LogUtil;
import com.sqwan.liveshow.R;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class RoundedImageView extends ImageView {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final float DEFAULT_BORDER_WIDTH = 0.0f;
    public static final float DEFAULT_RADIUS = 0.0f;
    public static final int GET_DATA_SUCCESS = 1;
    public static final int NETWORK_ERROR = 2;
    private static final ImageView.ScaleType[] SCALE_TYPES = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};
    public static final int SERVER_ERROR = 3;
    public static final String TAG = "hc.RoundedImageView";
    private ColorStateList borderColor;
    private float borderWidth;
    private float cornerRadius;
    private Handler handler;
    private boolean isOval;
    private boolean isSquare;
    private Drawable mBackgroundDrawable;
    private Drawable mDrawable;
    private int mResource;
    private ImageView.ScaleType mScaleType;
    private boolean mutateBackground;

    public RoundedImageView(Context context) {
        super(context);
        this.cornerRadius = 0.0f;
        this.borderWidth = 0.0f;
        this.borderColor = ColorStateList.valueOf(-16777216);
        this.isOval = false;
        this.mutateBackground = false;
        this.handler = new Handler() { // from class: com.sqwan.liveshow.ui.RoundedImageView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    RoundedImageView.this.setImageBitmap((Bitmap) message.obj);
                } else if (i == 2) {
                    LogUtil.d("网络连接失败");
                } else {
                    if (i != 3) {
                        return;
                    }
                    LogUtil.d("服务器发生错误");
                }
            }
        };
        this.isSquare = false;
    }

    public RoundedImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundedImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.cornerRadius = 0.0f;
        this.borderWidth = 0.0f;
        this.borderColor = ColorStateList.valueOf(-16777216);
        this.isOval = false;
        this.mutateBackground = false;
        this.handler = new Handler() { // from class: com.sqwan.liveshow.ui.RoundedImageView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i2 = message.what;
                if (i2 == 1) {
                    RoundedImageView.this.setImageBitmap((Bitmap) message.obj);
                } else if (i2 == 2) {
                    LogUtil.d("网络连接失败");
                } else {
                    if (i2 != 3) {
                        return;
                    }
                    LogUtil.d("服务器发生错误");
                }
            }
        };
        this.isSquare = false;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundedImageView, i, 0);
        int i2 = typedArrayObtainStyledAttributes.getInt(R.styleable.RoundedImageView_android_scaleType, -1);
        if (i2 >= 0) {
            setScaleType(SCALE_TYPES[i2]);
        } else {
            setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
        this.cornerRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_corner_radius, -1);
        this.borderWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_border_width, -1);
        if (this.cornerRadius < 0.0f) {
            this.cornerRadius = 0.0f;
        }
        if (this.borderWidth < 0.0f) {
            this.borderWidth = 0.0f;
        }
        ColorStateList colorStateList = typedArrayObtainStyledAttributes.getColorStateList(R.styleable.RoundedImageView_border_color);
        this.borderColor = colorStateList;
        if (colorStateList == null) {
            this.borderColor = ColorStateList.valueOf(-16777216);
        }
        this.mutateBackground = typedArrayObtainStyledAttributes.getBoolean(R.styleable.RoundedImageView_mutate_background, false);
        this.isOval = typedArrayObtainStyledAttributes.getBoolean(R.styleable.RoundedImageView_oval, false);
        updateDrawableAttrs();
        updateBackgroundDrawableAttrs(true);
        typedArrayObtainStyledAttributes.recycle();
        this.isSquare = false;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.sqwan.liveshow.ui.RoundedImageView$2] */
    public void setImageUrl(final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        new Thread() { // from class: com.sqwan.liveshow.ui.RoundedImageView.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(10000);
                    if (httpURLConnection.getResponseCode() != 200) {
                        RoundedImageView.this.handler.sendEmptyMessage(3);
                    } else {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStream);
                        Message messageObtain = Message.obtain();
                        messageObtain.obj = bitmapDecodeStream;
                        messageObtain.what = 1;
                        RoundedImageView.this.handler.sendMessage(messageObtain);
                        inputStream.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    RoundedImageView.this.handler.sendEmptyMessage(2);
                }
            }
        }.start();
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (this.mScaleType != scaleType) {
            this.mScaleType = scaleType;
            switch (AnonymousClass3.$SwitchMap$android$widget$ImageView$ScaleType[scaleType.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    super.setScaleType(ImageView.ScaleType.FIT_XY);
                    break;
                default:
                    super.setScaleType(scaleType);
                    break;
            }
            updateDrawableAttrs();
            updateBackgroundDrawableAttrs(false);
            invalidate();
        }
    }

    /* JADX INFO: renamed from: com.sqwan.liveshow.ui.RoundedImageView$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType;

        static {
            int[] iArr = new int[ImageView.ScaleType.values().length];
            $SwitchMap$android$widget$ImageView$ScaleType = iArr;
            try {
                iArr[ImageView.ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_START.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_END.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_XY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        this.mResource = 0;
        this.mDrawable = RoundedDrawable.fromDrawable(drawable);
        updateDrawableAttrs();
        super.setImageDrawable(this.mDrawable);
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        this.mResource = 0;
        this.mDrawable = RoundedDrawable.fromBitmap(bitmap);
        updateDrawableAttrs();
        super.setImageDrawable(this.mDrawable);
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        if (this.mResource != i) {
            this.mResource = i;
            this.mDrawable = resolveResource();
            updateDrawableAttrs();
            super.setImageDrawable(this.mDrawable);
        }
    }

    public void setImageResourceUnRound(int i) {
        super.setImageResource(i);
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        setImageDrawable(getDrawable());
    }

    private Drawable resolveResource() {
        Resources resources = getResources();
        Drawable drawable = null;
        if (resources == null) {
            return null;
        }
        int i = this.mResource;
        if (i != 0) {
            try {
                drawable = resources.getDrawable(i);
            } catch (Exception e) {
                Log.w(TAG, "Unable to find resource: " + this.mResource, e);
                this.mResource = 0;
            }
        }
        return RoundedDrawable.fromDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    private void updateDrawableAttrs() {
        updateAttrs(this.mDrawable);
    }

    private void updateBackgroundDrawableAttrs(boolean z) {
        if (this.mutateBackground) {
            if (z) {
                this.mBackgroundDrawable = RoundedDrawable.fromDrawable(this.mBackgroundDrawable);
            }
            updateAttrs(this.mBackgroundDrawable);
        }
    }

    private void updateAttrs(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        if (drawable instanceof RoundedDrawable) {
            ((RoundedDrawable) drawable).setScaleType(this.mScaleType).setCornerRadius(this.cornerRadius).setBorderWidth(this.borderWidth).setBorderColor(this.borderColor).setOval(this.isOval);
            return;
        }
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            for (int i = 0; i < numberOfLayers; i++) {
                updateAttrs(layerDrawable.getDrawable(i));
            }
        }
    }

    @Override // android.view.View
    @Deprecated
    public void setBackgroundDrawable(Drawable drawable) {
        this.mBackgroundDrawable = drawable;
        updateBackgroundDrawableAttrs(true);
        super.setBackgroundDrawable(this.mBackgroundDrawable);
    }

    public float getCornerRadius() {
        return this.cornerRadius;
    }

    public void setCornerRadius(int i) {
        setCornerRadius(getResources().getDimension(i));
    }

    public void setCornerRadius(float f) {
        if (this.cornerRadius == f) {
            return;
        }
        this.cornerRadius = f;
        updateDrawableAttrs();
        updateBackgroundDrawableAttrs(false);
    }

    public float getBorderWidth() {
        return this.borderWidth;
    }

    public void setBorderWidth(int i) {
        setBorderWidth(getResources().getDimension(i));
    }

    public void setBorderWidth(float f) {
        if (this.borderWidth == f) {
            return;
        }
        this.borderWidth = f;
        updateDrawableAttrs();
        updateBackgroundDrawableAttrs(false);
        invalidate();
    }

    public int getBorderColor() {
        return this.borderColor.getDefaultColor();
    }

    public void setBorderColor(int i) {
        setBorderColor(ColorStateList.valueOf(i));
    }

    public ColorStateList getBorderColors() {
        return this.borderColor;
    }

    public void setBorderColor(ColorStateList colorStateList) {
        if (this.borderColor.equals(colorStateList)) {
            return;
        }
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(-16777216);
        }
        this.borderColor = colorStateList;
        updateDrawableAttrs();
        updateBackgroundDrawableAttrs(false);
        if (this.borderWidth > 0.0f) {
            invalidate();
        }
    }

    public boolean isOval() {
        return this.isOval;
    }

    public void setOval(boolean z) {
        this.isOval = z;
        updateDrawableAttrs();
        updateBackgroundDrawableAttrs(false);
        invalidate();
    }

    public boolean isMutateBackground() {
        return this.mutateBackground;
    }

    public void setMutateBackground(boolean z) {
        if (this.mutateBackground == z) {
            return;
        }
        this.mutateBackground = z;
        updateBackgroundDrawableAttrs(true);
        invalidate();
    }

    public void setSquare(boolean z) {
        this.isSquare = z;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        if (this.isSquare) {
            setMeasuredDimension(measuredWidth, measuredWidth);
        } else {
            setMeasuredDimension(measuredWidth, getMeasuredHeight());
        }
    }
}
