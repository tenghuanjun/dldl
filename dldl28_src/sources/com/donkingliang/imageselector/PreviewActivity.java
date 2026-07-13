package com.donkingliang.imageselector;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.donkingliang.imageselector.adapter.ImagePagerAdapter;
import com.donkingliang.imageselector.entry.Image;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.VersionUtils;
import com.donkingliang.imageselector.view.MyViewPager;
import com.volcengine.androidcloud.common.pod.PodInfo;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class PreviewActivity extends AppCompatActivity {
    private static ArrayList<Image> tempImages;
    private static ArrayList<Image> tempSelectImages;
    private FrameLayout btnConfirm;
    private boolean isSingle;
    private ArrayList<Image> mImages;
    private int mMaxCount;
    private BitmapDrawable mSelectDrawable;
    private ArrayList<Image> mSelectImages;
    private BitmapDrawable mUnSelectDrawable;
    private RelativeLayout rlBottomBar;
    private RelativeLayout rlTopBar;
    private TextView tvConfirm;
    private TextView tvIndicator;
    private TextView tvSelect;
    private MyViewPager vpImage;
    private boolean isShowBar = true;
    private boolean isConfirm = false;

    public static void openActivity(Activity activity, ArrayList<Image> arrayList, ArrayList<Image> arrayList2, boolean z, int i, int i2) {
        tempImages = arrayList;
        tempSelectImages = arrayList2;
        Intent intent = new Intent(activity, (Class<?>) PreviewActivity.class);
        intent.putExtra(ImageSelector.MAX_SELECT_COUNT, i);
        intent.putExtra(ImageSelector.IS_SINGLE, z);
        intent.putExtra(ImageSelector.POSITION, i2);
        activity.startActivityForResult(intent, 18);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_preview);
        if (VersionUtils.isAndroidP()) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            getWindow().setAttributes(attributes);
        }
        setStatusBarVisible(true);
        this.mImages = tempImages;
        tempImages = null;
        this.mSelectImages = tempSelectImages;
        tempSelectImages = null;
        Intent intent = getIntent();
        this.mMaxCount = intent.getIntExtra(ImageSelector.MAX_SELECT_COUNT, 0);
        this.isSingle = intent.getBooleanExtra(ImageSelector.IS_SINGLE, false);
        Resources resources = getResources();
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(resources, R.drawable.icon_image_select);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(resources, bitmapDecodeResource);
        this.mSelectDrawable = bitmapDrawable;
        bitmapDrawable.setBounds(0, 0, bitmapDecodeResource.getWidth(), bitmapDecodeResource.getHeight());
        Bitmap bitmapDecodeResource2 = BitmapFactory.decodeResource(resources, R.drawable.icon_image_un_select);
        BitmapDrawable bitmapDrawable2 = new BitmapDrawable(resources, bitmapDecodeResource2);
        this.mUnSelectDrawable = bitmapDrawable2;
        bitmapDrawable2.setBounds(0, 0, bitmapDecodeResource2.getWidth(), bitmapDecodeResource2.getHeight());
        setStatusBarColor();
        initView();
        initListener();
        initViewPager();
        this.tvIndicator.setText("1/" + this.mImages.size());
        changeSelect(this.mImages.get(0));
        this.vpImage.setCurrentItem(intent.getIntExtra(ImageSelector.POSITION, 0));
    }

    private void initView() {
        this.vpImage = (MyViewPager) findViewById(R.id.vp_image);
        this.tvIndicator = (TextView) findViewById(R.id.tv_indicator);
        this.tvConfirm = (TextView) findViewById(R.id.tv_confirm);
        this.btnConfirm = (FrameLayout) findViewById(R.id.btn_confirm);
        this.tvSelect = (TextView) findViewById(R.id.tv_select);
        this.rlTopBar = (RelativeLayout) findViewById(R.id.rl_top_bar);
        this.rlBottomBar = (RelativeLayout) findViewById(R.id.rl_bottom_bar);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.rlTopBar.getLayoutParams();
        layoutParams.topMargin = getStatusBarHeight(this);
        this.rlTopBar.setLayoutParams(layoutParams);
    }

    private void initListener() {
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() { // from class: com.donkingliang.imageselector.PreviewActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PreviewActivity.this.finish();
            }
        });
        this.btnConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.donkingliang.imageselector.PreviewActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PreviewActivity.this.isConfirm = true;
                PreviewActivity.this.finish();
            }
        });
        this.tvSelect.setOnClickListener(new View.OnClickListener() { // from class: com.donkingliang.imageselector.PreviewActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PreviewActivity.this.clickSelect();
            }
        });
    }

    private void initViewPager() {
        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(this, this.mImages);
        this.vpImage.setAdapter(imagePagerAdapter);
        imagePagerAdapter.setOnItemClickListener(new ImagePagerAdapter.OnItemClickListener() { // from class: com.donkingliang.imageselector.PreviewActivity.4
            @Override // com.donkingliang.imageselector.adapter.ImagePagerAdapter.OnItemClickListener
            public void onItemClick(int i, Image image) {
                if (PreviewActivity.this.isShowBar) {
                    PreviewActivity.this.hideBar();
                } else {
                    PreviewActivity.this.showBar();
                }
            }
        });
        this.vpImage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.donkingliang.imageselector.PreviewActivity.5
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                PreviewActivity.this.tvIndicator.setText((i + 1) + "/" + PreviewActivity.this.mImages.size());
                PreviewActivity previewActivity = PreviewActivity.this;
                previewActivity.changeSelect((Image) previewActivity.mImages.get(i));
            }
        });
    }

    private void setStatusBarColor() {
        if (VersionUtils.isAndroidL()) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(Color.parseColor("#373c3d"));
        }
    }

    public static int getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", PodInfo.GAME_TYPE_ANDROID);
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStatusBarVisible(boolean z) {
        if (z) {
            getWindow().getDecorView().setSystemUiVisibility(1024);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(1028);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showBar() {
        this.isShowBar = true;
        setStatusBarVisible(true);
        this.rlTopBar.postDelayed(new Runnable() { // from class: com.donkingliang.imageselector.PreviewActivity.6
            @Override // java.lang.Runnable
            public void run() {
                if (PreviewActivity.this.rlTopBar != null) {
                    ObjectAnimator duration = ObjectAnimator.ofFloat(PreviewActivity.this.rlTopBar, "translationY", PreviewActivity.this.rlTopBar.getTranslationY(), 0.0f).setDuration(300L);
                    duration.addListener(new AnimatorListenerAdapter() { // from class: com.donkingliang.imageselector.PreviewActivity.6.1
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationStart(Animator animator) {
                            super.onAnimationStart(animator);
                            if (PreviewActivity.this.rlTopBar != null) {
                                PreviewActivity.this.rlTopBar.setVisibility(0);
                            }
                        }
                    });
                    duration.start();
                    ObjectAnimator.ofFloat(PreviewActivity.this.rlBottomBar, "translationY", PreviewActivity.this.rlBottomBar.getTranslationY(), 0.0f).setDuration(300L).start();
                }
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideBar() {
        this.isShowBar = false;
        ObjectAnimator duration = ObjectAnimator.ofFloat(this.rlTopBar, "translationY", 0.0f, -r1.getHeight()).setDuration(300L);
        duration.addListener(new AnimatorListenerAdapter() { // from class: com.donkingliang.imageselector.PreviewActivity.7
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (PreviewActivity.this.rlTopBar != null) {
                    PreviewActivity.this.rlTopBar.setVisibility(8);
                    PreviewActivity.this.rlTopBar.postDelayed(new Runnable() { // from class: com.donkingliang.imageselector.PreviewActivity.7.1
                        @Override // java.lang.Runnable
                        public void run() {
                            PreviewActivity.this.setStatusBarVisible(false);
                        }
                    }, 5L);
                }
            }
        });
        duration.start();
        ObjectAnimator.ofFloat(this.rlBottomBar, "translationY", 0.0f, r1.getHeight()).setDuration(300L).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickSelect() {
        int currentItem = this.vpImage.getCurrentItem();
        ArrayList<Image> arrayList = this.mImages;
        if (arrayList == null || arrayList.size() <= currentItem) {
            return;
        }
        Image image = this.mImages.get(currentItem);
        if (this.mSelectImages.contains(image)) {
            this.mSelectImages.remove(image);
        } else if (this.isSingle) {
            this.mSelectImages.clear();
            this.mSelectImages.add(image);
        } else if (this.mMaxCount <= 0 || this.mSelectImages.size() < this.mMaxCount) {
            this.mSelectImages.add(image);
        }
        changeSelect(image);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeSelect(Image image) {
        this.tvSelect.setCompoundDrawables(this.mSelectImages.contains(image) ? this.mSelectDrawable : this.mUnSelectDrawable, null, null, null);
        setSelectImageCount(this.mSelectImages.size());
    }

    private void setSelectImageCount(int i) {
        if (i == 0) {
            this.btnConfirm.setEnabled(false);
            this.tvConfirm.setText(R.string.selector_send);
            return;
        }
        this.btnConfirm.setEnabled(true);
        if (this.isSingle) {
            this.tvConfirm.setText(R.string.selector_send);
            return;
        }
        if (this.mMaxCount > 0) {
            this.tvConfirm.setText(getString(R.string.selector_send) + "(" + i + "/" + this.mMaxCount + ")");
            return;
        }
        this.tvConfirm.setText(getString(R.string.selector_send) + "(" + i + ")");
    }

    @Override // android.app.Activity
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra(ImageSelector.IS_CONFIRM, this.isConfirm);
        setResult(18, intent);
        super.finish();
    }
}
