package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public final class LayoutVideoBinding implements ViewBinding {
    public final ImageView back;
    public final ImageView backTiny;
    public final ProgressBar bottomProgressbar;
    public final TextView current;
    public final ImageView fullscreen;
    public final ImageView ivVoice;
    public final LinearLayout layoutBottom;
    public final LinearLayout layoutTop;
    public final ProgressBar loading;
    public final ImageView lockScreen;
    public final SeekBar progress;
    private final RelativeLayout rootView;
    public final ImageView smallClose;
    public final ImageView start;
    public final RelativeLayout surfaceContainer;
    public final RelativeLayout thumb;
    public final TextView title;
    public final TextView total;

    private LayoutVideoBinding(RelativeLayout rootView, ImageView back, ImageView backTiny, ProgressBar bottomProgressbar, TextView current, ImageView fullscreen, ImageView ivVoice, LinearLayout layoutBottom, LinearLayout layoutTop, ProgressBar loading, ImageView lockScreen, SeekBar progress, ImageView smallClose, ImageView start, RelativeLayout surfaceContainer, RelativeLayout thumb, TextView title, TextView total) {
        this.rootView = rootView;
        this.back = back;
        this.backTiny = backTiny;
        this.bottomProgressbar = bottomProgressbar;
        this.current = current;
        this.fullscreen = fullscreen;
        this.ivVoice = ivVoice;
        this.layoutBottom = layoutBottom;
        this.layoutTop = layoutTop;
        this.loading = loading;
        this.lockScreen = lockScreen;
        this.progress = progress;
        this.smallClose = smallClose;
        this.start = start;
        this.surfaceContainer = surfaceContainer;
        this.thumb = thumb;
        this.title = title;
        this.total = total;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static LayoutVideoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutVideoBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_video, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutVideoBinding bind(View rootView) {
        int i = R.id.back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.back);
        if (imageView != null) {
            i = R.id.back_tiny;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.back_tiny);
            if (imageView2 != null) {
                i = R.id.bottom_progressbar;
                ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.bottom_progressbar);
                if (progressBar != null) {
                    i = R.id.current;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.current);
                    if (textView != null) {
                        i = R.id.fullscreen;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.fullscreen);
                        if (imageView3 != null) {
                            i = R.id.iv_voice;
                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_voice);
                            if (imageView4 != null) {
                                i = R.id.layout_bottom;
                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.layout_bottom);
                                if (linearLayout != null) {
                                    i = R.id.layout_top;
                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.layout_top);
                                    if (linearLayout2 != null) {
                                        i = R.id.loading;
                                        ProgressBar progressBar2 = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.loading);
                                        if (progressBar2 != null) {
                                            i = R.id.lock_screen;
                                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.lock_screen);
                                            if (imageView5 != null) {
                                                i = R.id.progress;
                                                SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(rootView, R.id.progress);
                                                if (seekBar != null) {
                                                    i = R.id.small_close;
                                                    ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.small_close);
                                                    if (imageView6 != null) {
                                                        i = R.id.start;
                                                        ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.start);
                                                        if (imageView7 != null) {
                                                            i = R.id.surface_container;
                                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.surface_container);
                                                            if (relativeLayout != null) {
                                                                i = R.id.thumb;
                                                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.thumb);
                                                                if (relativeLayout2 != null) {
                                                                    i = R.id.title;
                                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.title);
                                                                    if (textView2 != null) {
                                                                        i = R.id.total;
                                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.total);
                                                                        if (textView3 != null) {
                                                                            return new LayoutVideoBinding((RelativeLayout) rootView, imageView, imageView2, progressBar, textView, imageView3, imageView4, linearLayout, linearLayout2, progressBar2, imageView5, seekBar, imageView6, imageView7, relativeLayout, relativeLayout2, textView2, textView3);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
