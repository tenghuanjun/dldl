package com.cy.yyjia.zhe28.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cy.yyjia.zhe28.databinding.LayoutGameTag2Binding;
import com.cy.yyjia.zhe28.databinding.LayoutGameTag3Binding;
import com.cy.yyjia.zhe28.databinding.LayoutGameTagBinding;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.view.BannerHolder;
import com.cy.yyjia.zhe28.view.RecyclerViewScrollBar;
import com.cy.yyjia.zhe28.view.WancmsStandardPlayer;
import com.hjq.shape.builder.ShapeDrawableBuilder;
import com.hjq.shape.view.ShapeTextView;
import com.lzy.okgo.model.Priority;
import com.mobile.auth.R;
import com.volcengine.androidcloud.common.pod.PodInfo;
import java.util.List;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class DataBindingHelper {
    public static void setGif(ImageView imageView, Drawable id) {
        Glide.with(imageView).load(id).into(imageView);
    }

    public static void setImg(ImageView imageView, String url, Drawable placeholder) {
        RequestOptions requestOptionsOverride = new RequestOptions().override(Priority.BG_LOW, Priority.BG_LOW);
        if (placeholder != null) {
            requestOptionsOverride = requestOptionsOverride.error(placeholder).placeholder(placeholder);
        }
        Glide.with(imageView.getContext()).load(url).apply(requestOptionsOverride).into(imageView);
    }

    public static void setImageResource(ImageView iv, int id) {
        iv.setImageResource(id);
    }

    public static void setGameIcon(ImageView imageView, String gameIcon) {
        Glide.with(imageView).load(gameIcon).apply(new RequestOptions().placeholder(2131624260).error(2131624260)).override(imageView.getWidth(), imageView.getHeight()).into(imageView);
    }

    public static void setUserIcon(ImageView imageView, String userIcon) {
        Glide.with(imageView).load(userIcon).apply(new RequestOptions().circleCrop().placeholder(2131624446).error(2131624446)).into(imageView);
    }

    public static void setDealIcon(ImageView imageView, String dealIcon) {
        Glide.with(imageView).load(dealIcon).apply(new RequestOptions().centerCrop().placeholder(2131624163).error(2131624163)).thumbnail(0.1f).into(imageView);
    }

    public static void selectPic(ImageView imageView, String selectPic) {
        Glide.with(imageView).load(selectPic).apply(new RequestOptions().centerCrop().placeholder(2131624290).error(2131624290)).thumbnail(0.1f).into(imageView);
    }

    public static void cli(View v, boolean flag) {
        v.setClipToOutline(flag);
    }

    public static void setViewGone(View v, boolean gone) {
        v.setVisibility(gone ? 8 : 0);
    }

    public static void setSelected(View v, boolean selected) {
        v.setSelected(selected);
    }

    public static void setBold(TextView v, boolean bold) {
        if (bold) {
            v.setTypeface(Typeface.DEFAULT_BOLD);
        } else {
            v.setTypeface(Typeface.DEFAULT);
        }
    }

    public static void setSelectedSize(TextView v, int textSize) {
        v.setTextSize(1, textSize);
    }

    public static void setRvData(RecyclerView rv, List rvData) {
        BaseQuickAdapter adapter = rv.getAdapter();
        if (adapter != null) {
            adapter.setNewInstance(rvData);
        }
    }

    public static void setBanner(ConvenientBanner banner, List data) {
        banner.setPages(new CBViewHolderCreator() { // from class: com.cy.yyjia.zhe28.util.DataBindingHelper$$ExternalSyntheticLambda0
            public final Object createHolder() {
                return new BannerHolder();
            }
        }, data).setPointViewVisible(true).setPageIndicator(new int[]{2131624240, 2131624241}).startTurning(2000L);
    }

    public static void attachToRv(RecyclerViewScrollBar sb, RecyclerView rv) {
        sb.attachRecyclerView(rv);
    }

    public static void setVideo(WancmsStandardPlayer player, String videoUrl, String videoPic) {
        player.setUp(videoUrl, videoPic);
    }

    public static void setDealStatus(ShapeTextView stv, String status) {
        ShapeDrawableBuilder shapeDrawableBuilder;
        String str;
        if (status == null) {
            return;
        }
        shapeDrawableBuilder = stv.getShapeDrawableBuilder();
        shapeDrawableBuilder.setSolidColor(Color.parseColor("#999999"));
        status.hashCode();
        switch (status) {
            case "beback":
                shapeDrawableBuilder.setSolidColor(Color.parseColor("#FF3A3A"));
                str = "驳回";
                break;
            case "outline":
                str = "已下架";
                break;
            case "selled":
                shapeDrawableBuilder.setSolidColor(Color.parseColor("#FF9900"));
                str = "已出售";
                break;
            case "pending":
                shapeDrawableBuilder.setSolidColor(Color.parseColor("#138B35"));
                str = "待审核";
                break;
            case "got":
                shapeDrawableBuilder.setSolidColor(Color.parseColor("#FF9900"));
                str = "已购买";
                break;
            case "fail":
                str = "撤销";
                break;
            case "sell":
                shapeDrawableBuilder.setSolidColor(Color.parseColor("#FF9900"));
                str = "出售中";
                break;
            case "confirm":
                shapeDrawableBuilder.setSolidColor(Color.parseColor("#FF9900"));
                str = "待确认收货";
                break;
            default:
                str = "";
                break;
        }
        shapeDrawableBuilder.intoBackground();
        stv.setText(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x007f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void setDownloadText(android.widget.TextView r5, com.lzy.okgo.model.Progress r6) {
        /*
            if (r6 == 0) goto L7f
            int r0 = r6.status
            java.lang.String r1 = "继续下载"
            if (r0 == 0) goto L81
            r2 = 1
            if (r0 == r2) goto L7c
            r2 = 2
            if (r0 == r2) goto L4d
            r2 = 3
            if (r0 == r2) goto L81
            r2 = 4
            if (r0 == r2) goto L28
            r1 = 5
            if (r0 == r1) goto L18
            goto L7f
        L18:
            java.io.File r0 = new java.io.File
            java.lang.String r6 = r6.filePath
            r0.<init>(r6)
            boolean r6 = r0.exists()
            if (r6 == 0) goto L7f
            java.lang.String r1 = "安装"
            goto L81
        L28:
            java.lang.Throwable r0 = r6.exception
            if (r0 == 0) goto L37
            java.lang.Throwable r0 = r6.exception
            java.lang.String r0 = r0.getLocalizedMessage()
            java.lang.String r2 = "setDownloadText: "
            android.util.Log.e(r2, r0)
        L37:
            java.lang.Throwable r0 = r6.exception
            if (r0 == 0) goto L4a
            java.lang.Throwable r6 = r6.exception
            java.lang.String r6 = r6.getLocalizedMessage()
            java.lang.String r0 = "Software caused connection abort"
            boolean r6 = r0.equals(r6)
            if (r6 == 0) goto L4a
            goto L81
        L4a:
            java.lang.String r1 = "点击重新下载"
            goto L81
        L4d:
            java.math.BigDecimal r0 = new java.math.BigDecimal
            float r6 = r6.fraction
            double r3 = (double) r6
            r0.<init>(r3)
            java.math.BigDecimal r6 = new java.math.BigDecimal
            java.lang.String r1 = "100"
            r6.<init>(r1)
            java.math.BigDecimal r6 = r0.multiply(r6)
            java.math.RoundingMode r0 = java.math.RoundingMode.HALF_UP
            java.math.BigDecimal r6 = r6.setScale(r2, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r6 = r6.toString()
            r0.append(r6)
            java.lang.String r6 = "%"
            r0.append(r6)
            java.lang.String r1 = r0.toString()
            goto L81
        L7c:
            java.lang.String r1 = "等待中"
            goto L81
        L7f:
            java.lang.String r1 = "下载"
        L81:
            r5.setText(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.util.DataBindingHelper.setDownloadText(android.widget.TextView, com.lzy.okgo.model.Progress):void");
    }

    public static void setDeleteLine(TextView tv, boolean deleteLine) {
        if (deleteLine) {
            tv.setPaintFlags(tv.getPaintFlags() | 16);
        }
    }

    public static void setTags(LinearLayout linearLayout, List<GameBean.Tag> tags) {
        Context context = linearLayout.getContext();
        linearLayout.removeAllViews();
        if (tags == null || tags.isEmpty()) {
            return;
        }
        LayoutGameTagBinding layoutGameTagBindingInflate = DataBindingUtil.inflate(LayoutInflater.from(context), 2131558860, (ViewGroup) null, false);
        layoutGameTagBindingInflate.setData(tags);
        linearLayout.addView(layoutGameTagBindingInflate.getRoot());
    }

    public static void setTags2(LinearLayout linearLayout, List<String> tags) {
        Context context = linearLayout.getContext();
        linearLayout.removeAllViews();
        if (tags == null || tags.isEmpty()) {
            return;
        }
        LayoutGameTag2Binding layoutGameTag2BindingInflate = DataBindingUtil.inflate(LayoutInflater.from(context), 2131558861, (ViewGroup) null, false);
        layoutGameTag2BindingInflate.setData(tags);
        linearLayout.addView(layoutGameTag2BindingInflate.getRoot());
    }

    public static void setTags3(LinearLayout linearLayout, List<GameBean.Tag> tags) {
        Context context = linearLayout.getContext();
        linearLayout.removeAllViews();
        if (tags == null || tags.isEmpty()) {
            return;
        }
        LayoutGameTag3Binding layoutGameTag3BindingInflate = DataBindingUtil.inflate(LayoutInflater.from(context), 2131558862, (ViewGroup) null, false);
        layoutGameTag3BindingInflate.setData(tags);
        linearLayout.addView(layoutGameTag3BindingInflate.getRoot());
    }

    public static void setFitWindow(View view, boolean fit) {
        int identifier = view.getContext().getResources().getIdentifier("status_bar_height", "dimen", PodInfo.GAME_TYPE_ANDROID);
        int dimensionPixelSize = identifier > 0 ? view.getContext().getResources().getDimensionPixelSize(identifier) : 0;
        view.getParent();
        ConstraintLayout.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            ((LinearLayout.LayoutParams) layoutParams).topMargin += dimensionPixelSize;
        } else if (layoutParams instanceof RelativeLayout.LayoutParams) {
            ((RelativeLayout.LayoutParams) layoutParams).topMargin += dimensionPixelSize;
        } else if (layoutParams instanceof FrameLayout.LayoutParams) {
            ((FrameLayout.LayoutParams) layoutParams).topMargin += dimensionPixelSize;
        } else if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            layoutParams.topMargin += dimensionPixelSize;
        }
        view.setLayoutParams(layoutParams);
    }

    public static void setLogColor(TextView tv, int logColor) {
        if (logColor == -1) {
            tv.setTextColor(-65536);
            return;
        }
        if (logColor == 11) {
            tv.setTextColor(tv.getResources().getColor(R.color.colorPrimary));
        } else if (logColor == 200) {
            tv.setTextColor(Color.parseColor("#58BE6B"));
        } else {
            tv.setTextColor(tv.getResources().getColor(2131099708));
        }
    }
}
