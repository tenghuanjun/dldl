package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.databinding.ActivityImageBinding;
import com.cy.yyjia.zhe28.databinding.FragmentImageBinding;
import com.cy.yyjia.zhe28.ui.activity.ImageActivity;
import com.cy.yyjia.zhe28.view.Navigation;
import com.donkingliang.imageselector.utils.ImageSelector;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImageActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0012\u001a\u00020\u0013H\u0016R+\u0010\u0004\u001a\u0012\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006\u0018\u00010\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/ImageActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityImageBinding;", "()V", "images", "Ljava/util/ArrayList;", "", "kotlin.jvm.PlatformType", "getImages", "()Ljava/util/ArrayList;", "images$delegate", "Lkotlin/Lazy;", ImageSelector.POSITION, "", "getPosition", "()I", "setPosition", "(I)V", "init", "", "ChildFragment", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ImageActivity extends BaseActivity<ActivityImageBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: images$delegate, reason: from kotlin metadata */
    private final Lazy images;
    private int position;

    public ImageActivity() {
        super(R.layout.activity_image, 2);
        this.images = LazyKt.lazy(new Function0<ArrayList<String>>() { // from class: com.cy.yyjia.zhe28.ui.activity.ImageActivity$images$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ArrayList<String> invoke() {
                return this.this$0.getIntent().getStringArrayListExtra("images");
            }
        });
    }

    public static final /* synthetic */ ActivityImageBinding access$getMBinding(ImageActivity imageActivity) {
        return imageActivity.getMBinding();
    }

    public final ArrayList<String> getImages() {
        return (ArrayList) this.images.getValue();
    }

    public final int getPosition() {
        return this.position;
    }

    public final void setPosition(int i) {
        this.position = i;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        this.position = getIntent().getIntExtra(ImageSelector.POSITION, 0);
        getMBinding().vp.setAdapter(new FragmentStateAdapter() { // from class: com.cy.yyjia.zhe28.ui.activity.ImageActivity.init.1
            {
                super(ImageActivity.this);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                ArrayList<String> images = ImageActivity.this.getImages();
                Intrinsics.checkNotNull(images);
                return images.size();
            }

            @Override // androidx.viewpager2.adapter.FragmentStateAdapter
            public Fragment createFragment(int position) {
                ArrayList<String> images = ImageActivity.this.getImages();
                Intrinsics.checkNotNull(images);
                String str = images.get(position);
                Intrinsics.checkNotNullExpressionValue(str, "get(...)");
                return new ChildFragment(str);
            }
        });
        getMBinding().vp.setCurrentItem(this.position, false);
        getMBinding().vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.cy.yyjia.zhe28.ui.activity.ImageActivity.init.2
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrollStateChanged(int state) {
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int position) {
                ArrayList<String> images = ImageActivity.this.getImages();
                Intrinsics.checkNotNull(images);
                if (images.size() > 1) {
                    Navigation navigation = ImageActivity.access$getMBinding(ImageActivity.this).navigation;
                    int i = position + 1;
                    ArrayList<String> images2 = ImageActivity.this.getImages();
                    navigation.setTitle(i + "/" + (images2 != null ? Integer.valueOf(images2.size()) : null));
                }
            }
        });
        ArrayList<String> images = getImages();
        Intrinsics.checkNotNull(images);
        if (images.size() > 1) {
            Navigation navigation = getMBinding().navigation;
            int i = this.position + 1;
            ArrayList<String> images2 = getImages();
            navigation.setTitle(i + "/" + (images2 != null ? Integer.valueOf(images2.size()) : null));
        }
        getMBinding().vp.setOffscreenPageLimit(3);
    }

    /* JADX INFO: compiled from: ImageActivity.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/ImageActivity$ChildFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentImageBinding;", "url", "", "(Ljava/lang/String;)V", "getUrl", "()Ljava/lang/String;", "init", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ChildFragment extends BaseFragment<FragmentImageBinding> {
        public static final int $stable = 0;
        private final String url;

        public final String getUrl() {
            return this.url;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ChildFragment(String url) {
            super(R.layout.fragment_image);
            Intrinsics.checkNotNullParameter(url, "url");
            this.url = url;
        }

        @Override // com.cy.yyjia.zhe28.base.BaseFragment
        public void init() {
            getMBinding().setData(this.url);
            getMBinding().pv.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ImageActivity$ChildFragment$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ImageActivity.ChildFragment.init$lambda$0(this.f$0, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$0(ChildFragment this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.getMContext().finish();
        }
    }
}
