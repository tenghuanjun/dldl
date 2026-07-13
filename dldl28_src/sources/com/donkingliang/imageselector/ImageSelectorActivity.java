package com.donkingliang.imageselector;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.os.EnvironmentCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import coil.util.Utils;
import com.donkingliang.imageselector.adapter.FolderAdapter;
import com.donkingliang.imageselector.adapter.ImageAdapter;
import com.donkingliang.imageselector.entry.Folder;
import com.donkingliang.imageselector.entry.Image;
import com.donkingliang.imageselector.entry.RequestConfig;
import com.donkingliang.imageselector.model.ImageModel;
import com.donkingliang.imageselector.utils.DateUtils;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageUtil;
import com.donkingliang.imageselector.utils.UriUtils;
import com.donkingliang.imageselector.utils.VersionUtils;
import com.hjq.permissions.Permission;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class ImageSelectorActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST_CODE = 16;
    private static final int PERMISSION_CAMERA_REQUEST_CODE = 18;
    private static final int PERMISSION_WRITE_EXTERNAL_REQUEST_CODE = 17;
    private FrameLayout btnConfirm;
    private FrameLayout btnPreview;
    private boolean isInitFolder;
    private boolean isOpenFolder;
    private boolean isShowTime;
    private boolean isSingle;
    private ImageAdapter mAdapter;
    private String mCameraImagePath;
    private Uri mCameraUri;
    private Folder mFolder;
    private ArrayList<Folder> mFolders;
    private GridLayoutManager mLayoutManager;
    private int mMaxCount;
    private ArrayList<String> mSelectedImages;
    private long mTakeTime;
    private View masking;
    private RecyclerView rvFolder;
    private RecyclerView rvImage;
    private TextView tvConfirm;
    private TextView tvFolderName;
    private TextView tvPreview;
    private TextView tvTime;
    private boolean applyLoadImage = false;
    private boolean applyCamera = false;
    private boolean canPreview = true;
    private boolean useCamera = true;
    private boolean onlyTakePhoto = false;
    private Handler mHideHandler = new Handler();
    private Runnable mHide = new Runnable() { // from class: com.donkingliang.imageselector.ImageSelectorActivity.1
        @Override // java.lang.Runnable
        public void run() {
            ImageSelectorActivity.this.hideTime();
        }
    };

    public static void openActivity(Activity activity, int i, RequestConfig requestConfig) {
        Intent intent = new Intent(activity, (Class<?>) ImageSelectorActivity.class);
        intent.putExtra(ImageSelector.KEY_CONFIG, requestConfig);
        activity.startActivityForResult(intent, i);
    }

    public static void openActivity(Fragment fragment, int i, RequestConfig requestConfig) {
        Intent intent = new Intent(fragment.getActivity(), (Class<?>) ImageSelectorActivity.class);
        intent.putExtra(ImageSelector.KEY_CONFIG, requestConfig);
        fragment.startActivityForResult(intent, i);
    }

    public static void openActivity(android.app.Fragment fragment, int i, RequestConfig requestConfig) {
        Intent intent = new Intent(fragment.getActivity(), (Class<?>) ImageSelectorActivity.class);
        intent.putExtra(ImageSelector.KEY_CONFIG, requestConfig);
        fragment.startActivityForResult(intent, i);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        RequestConfig requestConfig = (RequestConfig) getIntent().getParcelableExtra(ImageSelector.KEY_CONFIG);
        this.mMaxCount = requestConfig.maxSelectCount;
        this.isSingle = requestConfig.isSingle;
        this.canPreview = requestConfig.canPreview;
        this.useCamera = requestConfig.useCamera;
        this.mSelectedImages = requestConfig.selected;
        boolean z = requestConfig.onlyTakePhoto;
        this.onlyTakePhoto = z;
        if (z) {
            return;
        }
        setContentView(R.layout.activity_image_select);
        setStatusBarColor();
        initView();
        initListener();
        initImageList();
        loadImageForSDCard();
        hideFolderList();
        setSelectImageCount(0);
    }

    private void setStatusBarColor() {
        if (VersionUtils.isAndroidL()) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(Color.parseColor("#373c3d"));
        }
    }

    private void initView() {
        this.rvImage = (RecyclerView) findViewById(R.id.rv_image);
        this.rvFolder = (RecyclerView) findViewById(R.id.rv_folder);
        this.tvConfirm = (TextView) findViewById(R.id.tv_confirm);
        this.tvPreview = (TextView) findViewById(R.id.tv_preview);
        this.btnConfirm = (FrameLayout) findViewById(R.id.btn_confirm);
        this.btnPreview = (FrameLayout) findViewById(R.id.btn_preview);
        this.tvFolderName = (TextView) findViewById(R.id.tv_folder_name);
        this.tvTime = (TextView) findViewById(R.id.tv_time);
        this.masking = findViewById(R.id.masking);
    }

    private void initListener() {
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() { // from class: com.donkingliang.imageselector.ImageSelectorActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ImageSelectorActivity.this.finish();
            }
        });
        this.btnPreview.setOnClickListener(new View.OnClickListener() { // from class: com.donkingliang.imageselector.ImageSelectorActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(ImageSelectorActivity.this.mAdapter.getSelectImages());
                ImageSelectorActivity.this.toPreviewActivity(arrayList, 0);
            }
        });
        this.btnConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.donkingliang.imageselector.ImageSelectorActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ImageSelectorActivity.this.confirm();
            }
        });
        findViewById(R.id.btn_folder).setOnClickListener(new View.OnClickListener() { // from class: com.donkingliang.imageselector.ImageSelectorActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ImageSelectorActivity.this.isInitFolder) {
                    if (ImageSelectorActivity.this.isOpenFolder) {
                        ImageSelectorActivity.this.closeFolder();
                    } else {
                        ImageSelectorActivity.this.openFolder();
                    }
                }
            }
        });
        this.masking.setOnClickListener(new View.OnClickListener() { // from class: com.donkingliang.imageselector.ImageSelectorActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ImageSelectorActivity.this.closeFolder();
            }
        });
        this.rvImage.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.donkingliang.imageselector.ImageSelectorActivity.7
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                ImageSelectorActivity.this.changeTime();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                ImageSelectorActivity.this.changeTime();
            }
        });
    }

    private void initImageList() {
        if (getResources().getConfiguration().orientation == 1) {
            this.mLayoutManager = new GridLayoutManager(this, 3);
        } else {
            this.mLayoutManager = new GridLayoutManager(this, 5);
        }
        this.rvImage.setLayoutManager(this.mLayoutManager);
        ImageAdapter imageAdapter = new ImageAdapter(this, this.mMaxCount, this.isSingle, this.canPreview);
        this.mAdapter = imageAdapter;
        this.rvImage.setAdapter(imageAdapter);
        ((SimpleItemAnimator) this.rvImage.getItemAnimator()).setSupportsChangeAnimations(false);
        ArrayList<Folder> arrayList = this.mFolders;
        if (arrayList != null && !arrayList.isEmpty()) {
            setFolder(this.mFolders.get(0));
        }
        this.mAdapter.setOnImageSelectListener(new ImageAdapter.OnImageSelectListener() { // from class: com.donkingliang.imageselector.ImageSelectorActivity.8
            @Override // com.donkingliang.imageselector.adapter.ImageAdapter.OnImageSelectListener
            public void OnImageSelect(Image image, boolean z, int i) {
                ImageSelectorActivity.this.setSelectImageCount(i);
            }
        });
        this.mAdapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() { // from class: com.donkingliang.imageselector.ImageSelectorActivity.9
            @Override // com.donkingliang.imageselector.adapter.ImageAdapter.OnItemClickListener
            public void OnCameraClick() {
            }

            @Override // com.donkingliang.imageselector.adapter.ImageAdapter.OnItemClickListener
            public void OnItemClick(Image image, int i) {
                ImageSelectorActivity imageSelectorActivity = ImageSelectorActivity.this;
                imageSelectorActivity.toPreviewActivity(imageSelectorActivity.mAdapter.getData(), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initFolderList() {
        ArrayList<Folder> arrayList = this.mFolders;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        this.isInitFolder = true;
        this.rvFolder.setLayoutManager(new LinearLayoutManager(this));
        FolderAdapter folderAdapter = new FolderAdapter(this, this.mFolders);
        folderAdapter.setOnFolderSelectListener(new FolderAdapter.OnFolderSelectListener() { // from class: com.donkingliang.imageselector.ImageSelectorActivity.10
            @Override // com.donkingliang.imageselector.adapter.FolderAdapter.OnFolderSelectListener
            public void OnFolderSelect(Folder folder) {
                ImageSelectorActivity.this.setFolder(folder);
                ImageSelectorActivity.this.closeFolder();
            }
        });
        this.rvFolder.setAdapter(folderAdapter);
    }

    private void hideFolderList() {
        this.rvFolder.post(new Runnable() { // from class: com.donkingliang.imageselector.ImageSelectorActivity.11
            @Override // java.lang.Runnable
            public void run() {
                ImageSelectorActivity.this.rvFolder.setTranslationY(ImageSelectorActivity.this.rvFolder.getHeight());
                ImageSelectorActivity.this.rvFolder.setVisibility(8);
                ImageSelectorActivity.this.rvFolder.setBackgroundColor(-1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFolder(Folder folder) {
        if (folder == null || this.mAdapter == null || folder.equals(this.mFolder)) {
            return;
        }
        this.mFolder = folder;
        this.tvFolderName.setText(folder.getName());
        this.rvImage.scrollToPosition(0);
        this.mAdapter.refresh(folder.getImages(), folder.isUseCamera());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelectImageCount(int i) {
        if (i == 0) {
            this.btnConfirm.setEnabled(false);
            this.btnPreview.setEnabled(false);
            this.tvConfirm.setText(R.string.selector_send);
            this.tvPreview.setText(R.string.selector_preview);
            return;
        }
        this.btnConfirm.setEnabled(true);
        this.btnPreview.setEnabled(true);
        this.tvPreview.setText(getString(R.string.selector_preview) + "(" + i + ")");
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

    /* JADX INFO: Access modifiers changed from: private */
    public void openFolder() {
        if (this.isOpenFolder) {
            return;
        }
        this.masking.setVisibility(0);
        ObjectAnimator duration = ObjectAnimator.ofFloat(this.rvFolder, "translationY", r0.getHeight(), 0.0f).setDuration(300L);
        duration.addListener(new AnimatorListenerAdapter() { // from class: com.donkingliang.imageselector.ImageSelectorActivity.12
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                ImageSelectorActivity.this.rvFolder.setVisibility(0);
            }
        });
        duration.start();
        this.isOpenFolder = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeFolder() {
        if (this.isOpenFolder) {
            this.masking.setVisibility(8);
            ObjectAnimator duration = ObjectAnimator.ofFloat(this.rvFolder, "translationY", 0.0f, r0.getHeight()).setDuration(300L);
            duration.addListener(new AnimatorListenerAdapter() { // from class: com.donkingliang.imageselector.ImageSelectorActivity.13
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    ImageSelectorActivity.this.rvFolder.setVisibility(8);
                }
            });
            duration.start();
            this.isOpenFolder = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideTime() {
        if (this.isShowTime) {
            ObjectAnimator.ofFloat(this.tvTime, "alpha", 1.0f, 0.0f).setDuration(300L).start();
            this.isShowTime = false;
        }
    }

    private void showTime() {
        if (this.isShowTime) {
            return;
        }
        ObjectAnimator.ofFloat(this.tvTime, "alpha", 0.0f, 1.0f).setDuration(300L).start();
        this.isShowTime = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeTime() {
        Image firstVisibleImage = this.mAdapter.getFirstVisibleImage(getFirstVisibleItem());
        if (firstVisibleImage != null) {
            this.tvTime.setText(DateUtils.getImageTime(this, firstVisibleImage.getTime()));
            showTime();
            this.mHideHandler.removeCallbacks(this.mHide);
            this.mHideHandler.postDelayed(this.mHide, 1500L);
        }
    }

    private int getFirstVisibleItem() {
        return this.mLayoutManager.findFirstVisibleItemPosition();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void confirm() {
        ImageAdapter imageAdapter = this.mAdapter;
        if (imageAdapter == null) {
            return;
        }
        ArrayList<Image> selectImages = imageAdapter.getSelectImages();
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<Image> it = selectImages.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getPath());
        }
        saveImageAndFinish(arrayList, false);
    }

    private void saveImageAndFinish(ArrayList<String> arrayList, boolean z) {
        setResult(arrayList, z);
        finish();
    }

    private void setResult(ArrayList<String> arrayList, boolean z) {
        Intent intent = new Intent();
        intent.putStringArrayListExtra(ImageSelector.SELECT_RESULT, arrayList);
        intent.putExtra(ImageSelector.IS_CAMERA_IMAGE, z);
        setResult(-1, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toPreviewActivity(ArrayList<Image> arrayList, int i) {
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        PreviewActivity.openActivity(this, arrayList, this.mAdapter.getSelectImages(), this.isSingle, this.mMaxCount, i);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        if (this.applyLoadImage) {
            this.applyLoadImage = false;
            loadImageForSDCard();
        }
        if (this.applyCamera) {
            this.applyCamera = false;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        Uri uriFromFile;
        super.onActivityResult(i, i2, intent);
        if (i == 18) {
            if (intent != null && intent.getBooleanExtra(ImageSelector.IS_CONFIRM, false)) {
                confirm();
                return;
            } else {
                this.mAdapter.notifyDataSetChanged();
                setSelectImageCount(this.mAdapter.getSelectImages().size());
                return;
            }
        }
        if (i == 16) {
            if (i2 == -1) {
                ArrayList<String> arrayList = new ArrayList<>();
                if (VersionUtils.isAndroidQ()) {
                    uriFromFile = this.mCameraUri;
                    arrayList.add(UriUtils.getPathForUri(this, uriFromFile));
                } else {
                    uriFromFile = Uri.fromFile(new File(this.mCameraImagePath));
                    arrayList.add(this.mCameraImagePath);
                }
                ImageUtil.savePicture(this, uriFromFile, this.mTakeTime);
                saveImageAndFinish(arrayList, true);
                return;
            }
            if (this.onlyTakePhoto) {
                finish();
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mLayoutManager == null || this.mAdapter == null) {
            return;
        }
        if (configuration.orientation == 1) {
            this.mLayoutManager.setSpanCount(3);
        } else if (configuration.orientation == 2) {
            this.mLayoutManager.setSpanCount(5);
        }
        this.mAdapter.notifyDataSetChanged();
    }

    private void checkPermissionAndLoadImages() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            if (ContextCompat.checkSelfPermission(this, Permission.WRITE_EXTERNAL_STORAGE) == 0) {
                loadImageForSDCard();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Permission.WRITE_EXTERNAL_STORAGE}, 17);
            }
        }
    }

    private void checkPermissionAndCamera() {
        int iCheckSelfPermission = ContextCompat.checkSelfPermission(this, Permission.CAMERA);
        int iCheckSelfPermission2 = ContextCompat.checkSelfPermission(this, Permission.WRITE_EXTERNAL_STORAGE);
        if (iCheckSelfPermission == 0 && iCheckSelfPermission2 == 0) {
            openCamera();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Permission.CAMERA, Permission.WRITE_EXTERNAL_STORAGE}, 18);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 17) {
            if (iArr.length > 0 && iArr[0] == 0) {
                loadImageForSDCard();
                return;
            } else {
                showExceptionDialog(true);
                return;
            }
        }
        if (i == 18) {
            if (iArr.length > 1 && iArr[0] == 0 && iArr[1] == 0) {
                openCamera();
            } else {
                showExceptionDialog(false);
            }
        }
    }

    private void showExceptionDialog(final boolean z) {
        new AlertDialog.Builder(this).setCancelable(false).setTitle(R.string.selector_hint).setMessage(R.string.selector_permissions_hint).setNegativeButton(R.string.selector_cancel, new DialogInterface.OnClickListener() { // from class: com.donkingliang.imageselector.ImageSelectorActivity.15
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                ImageSelectorActivity.this.finish();
            }
        }).setPositiveButton(R.string.selector_confirm, new DialogInterface.OnClickListener() { // from class: com.donkingliang.imageselector.ImageSelectorActivity.14
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                ImageSelectorActivity.this.startAppSettings();
                if (z) {
                    ImageSelectorActivity.this.applyLoadImage = true;
                } else {
                    ImageSelectorActivity.this.applyCamera = true;
                }
            }
        }).show();
    }

    private void loadImageForSDCard() {
        ImageModel.loadImageForSDCard(this, new ImageModel.DataCallback() { // from class: com.donkingliang.imageselector.ImageSelectorActivity.16
            @Override // com.donkingliang.imageselector.model.ImageModel.DataCallback
            public void onSuccess(ArrayList<Folder> arrayList) {
                ImageSelectorActivity.this.mFolders = arrayList;
                ImageSelectorActivity.this.runOnUiThread(new Runnable() { // from class: com.donkingliang.imageselector.ImageSelectorActivity.16.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (ImageSelectorActivity.this.mFolders == null || ImageSelectorActivity.this.mFolders.isEmpty()) {
                            return;
                        }
                        ImageSelectorActivity.this.initFolderList();
                        ((Folder) ImageSelectorActivity.this.mFolders.get(0)).setUseCamera(ImageSelectorActivity.this.useCamera);
                        ImageSelectorActivity.this.setFolder((Folder) ImageSelectorActivity.this.mFolders.get(0));
                        if (ImageSelectorActivity.this.mSelectedImages == null || ImageSelectorActivity.this.mAdapter == null) {
                            return;
                        }
                        ImageSelectorActivity.this.mAdapter.setSelectedImages(ImageSelectorActivity.this.mSelectedImages);
                        ImageSelectorActivity.this.mSelectedImages = null;
                        ImageSelectorActivity.this.setSelectImageCount(ImageSelectorActivity.this.mAdapter.getSelectImages().size());
                    }
                });
            }
        });
    }

    private void openCamera() {
        Uri uriFromFile;
        File fileCreateImageFile;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(getPackageManager()) != null) {
            if (VersionUtils.isAndroidQ()) {
                uriFromFile = createImagePathUri();
            } else {
                uriFromFile = null;
                try {
                    fileCreateImageFile = createImageFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    fileCreateImageFile = null;
                }
                if (fileCreateImageFile != null) {
                    this.mCameraImagePath = fileCreateImageFile.getAbsolutePath();
                    if (VersionUtils.isAndroidN()) {
                        uriFromFile = FileProvider.getUriForFile(this, getPackageName() + ".imageSelectorProvider", fileCreateImageFile);
                    } else {
                        uriFromFile = Uri.fromFile(fileCreateImageFile);
                    }
                }
            }
            this.mCameraUri = uriFromFile;
            if (uriFromFile != null) {
                intent.putExtra("output", uriFromFile);
                intent.addFlags(2);
                startActivityForResult(intent, 16);
                this.mTakeTime = System.currentTimeMillis();
            }
        }
    }

    public Uri createImagePathUri() {
        String externalStorageState = Environment.getExternalStorageState();
        String str = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
        ContentValues contentValues = new ContentValues(2);
        contentValues.put("_display_name", str);
        contentValues.put("mime_type", Utils.MIME_TYPE_JPEG);
        if (externalStorageState.equals("mounted")) {
            return getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        }
        return getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, contentValues);
    }

    private File createImageFile() throws IOException {
        String str = String.format("JPEG_%s.jpg", new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date()));
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!externalStoragePublicDirectory.exists()) {
            externalStoragePublicDirectory.mkdir();
        }
        File file = new File(externalStoragePublicDirectory, str);
        if ("mounted".equals(EnvironmentCompat.getStorageState(file))) {
            return file;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAppSettings() {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getAction() == 0 && this.isOpenFolder) {
            closeFolder();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }
}
