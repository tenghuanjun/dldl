package com.huya.berry.sdklive.living.messageboard.helper;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.alibaba.fastjson.asm.Opcodes;
import com.duowan.auk.ArkValue;
import com.duowan.kiwi.base.smile.DefaultSmile;
import com.duowan.live.one.module.report.Report;
import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.berry.gamesdk.utils.CommonUtil;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.gamesdk.utils.SystemUtil;
import com.huya.berry.gamesdk.utils.UIUtil;
import com.huya.berry.gamesdk.utils.ViewUtils;
import com.huya.berry.gamesdk.widgets.ComnTextView;
import com.huya.berry.gamesdk.widgets.MarqueeTextView;
import com.huya.berry.module.props.PropsMgr;
import com.huya.berry.module.props.prop.PropItem;
import com.huya.berry.sdklive.living.messageboard.entity.ViewerMessage;
import com.huya.live.common.api.DataConst;
import com.huya.live.utils.DensityUtil;
import com.huya.mtp.utils.BitmapUtils;
import com.huya.mtp.utils.FP;
import com.sqwan.liveshow.huya.SqR;
import com.youme.voiceengine.YouMeConst;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class MessageHelper {
    private static final int COLOR_TEXT = -1;
    private static final int COLOR_VIP_ENTER_TEXT = -3951105;
    private static final String ICON_PLACE_HOLDER = "icon";
    private static final String PROPOS_TO_PRESENTER = ArkValue.gContext.getString(ResourceUtil.getStringResIDByName(SqR.string.hyberry_props_to_presenter));
    private static final int COLOR_NAME = Color.argb(255, 255, YouMeConst.YouMeEvent.YOUME_EVENT_OTHER_PLAY_BACKGRAOUND_MUSIC, 93);
    private static final int COLOR_OWN_NAME = Color.argb(255, 255, 157, 31);
    private static final int COLOR_GIFT_TEXT = Color.argb(255, 255, Opcodes.IF_ICMPGT, Opcodes.IF_ACMPNE);
    private static final int COLOR_SYSTEM_TEXT = Color.argb(255, 128, 220, 255);
    private static final int COLOR_FANS_TEXT = Color.argb(255, 255, 152, 19);
    private static final int COLOR_COPY_BG = Color.argb(255, 255, 150, 0);
    private static final int GIFT_SIZE = ViewUtils.dip2px(ArkValue.gContext, 30.0f);

    public static void appendChatMessage(ViewerMessage.ChatMessage chatMessage, ComnTextView comnTextView) {
        if (comnTextView == null) {
            return;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String validNickName = MessageLoader.getValidNickName(chatMessage.mNickname);
        spannableStringBuilder.append((CharSequence) MessageLoader.getClickableNickSpanText(validNickName, chatMessage.mIsOwn ? COLOR_OWN_NAME : COLOR_NAME, 0, validNickName.length()));
        spannableStringBuilder.append((CharSequence) " ");
        if (!FP.empty(chatMessage.suffixBitmaps)) {
            for (int i = 0; i < chatMessage.suffixBitmaps.size(); i++) {
                Bitmap bitmap = chatMessage.suffixBitmaps.get(i);
                if (bitmap != null && !bitmap.isRecycled()) {
                    int iDip2px = DensityUtil.dip2px(ArkValue.gContext, 30.0f);
                    Bitmap bitmapScale = BitmapUtils.scale(bitmap, (bitmap.getWidth() * iDip2px) / bitmap.getHeight(), iDip2px);
                    String str = String.format(Locale.US, "[suffixIcon%d]", Integer.valueOf(i));
                    spannableStringBuilder.append((CharSequence) MessageLoader.getImageSpannele(str, bitmapScale, 0, str.length()));
                }
            }
        }
        SpannableString spannaleText = MessageLoader.getSpannaleText(-1, " " + chatMessage.mMessage);
        DefaultSmile.matchText(ArkValue.gContext, spannaleText);
        spannableStringBuilder.append((CharSequence) spannaleText);
        comnTextView.setShadowLayer(2.0f, 2.0f, 2.0f, -16777216);
        comnTextView.setComnText(spannableStringBuilder);
        comnTextView.setMovementMethod(LinkMovementMethod.getInstance());
        comnTextView.setLongClickable(false);
    }

    public static void appendPropMessage(ViewerMessage.PropMessage propMessage, ComnTextView comnTextView) {
        if (comnTextView == null) {
            return;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String validNickName = MessageLoader.getValidNickName(propMessage.mSenderName);
        spannableStringBuilder.append((CharSequence) MessageLoader.getClickableNickSpanText(validNickName, COLOR_NAME, 0, validNickName.length()));
        spannableStringBuilder.append((CharSequence) "  ");
        PropItem prop = PropsMgr.instance().getProp(propMessage.mItemType, true);
        if (prop != null) {
            spannableStringBuilder.append((CharSequence) MessageLoader.getSpannaleText(COLOR_GIFT_TEXT, PROPOS_TO_PRESENTER + prop.getName()));
        }
        spannableStringBuilder.append((CharSequence) " ");
        comnTextView.setShadowLayer(2.0f, 2.0f, 2.0f, -16777216);
        comnTextView.setComnText(spannableStringBuilder);
        comnTextView.append(" × " + propMessage.mCount);
        comnTextView.setMovementMethod(LinkMovementMethod.getInstance());
        comnTextView.setLongClickable(false);
    }

    public static void appVipEnterMessage(ViewerMessage.VipEnterMessage vipEnterMessage, ComnTextView comnTextView) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) MessageLoader.getClickableNickSpanText(MessageLoader.getValidNickName(vipEnterMessage.mNickName)));
        spannableStringBuilder.append((CharSequence) MessageLoader.getSpannaleText(COLOR_VIP_ENTER_TEXT, " " + ArkValue.gContext.getString(ResourceUtil.getStringResIDByName(SqR.string.hyberry_enter_live_room))));
        comnTextView.setComnText(spannableStringBuilder);
        comnTextView.setMovementMethod(LinkMovementMethod.getInstance());
        comnTextView.setLongClickable(false);
    }

    public static void appNormalEnterMessage(ViewerMessage.NormalEnterMessage normalEnterMessage, ComnTextView comnTextView) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) MessageLoader.getClickableNickSpanText(MessageLoader.getValidNickName(normalEnterMessage.mNickName)));
        spannableStringBuilder.append((CharSequence) MessageLoader.getSpannaleText(COLOR_VIP_ENTER_TEXT, " " + ArkValue.gContext.getString(ResourceUtil.getStringResIDByName(SqR.string.hyberry_enter_live_room))));
        comnTextView.setComnText(spannableStringBuilder);
        comnTextView.setMovementMethod(LinkMovementMethod.getInstance());
        comnTextView.setLongClickable(false);
    }

    public static void appShareEnterMessage(ViewerMessage.ShareEnterMessage shareEnterMessage, ComnTextView comnTextView) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) MessageLoader.getClickableNickSpanText(MessageLoader.getValidNickName(shareEnterMessage.mNickName)));
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        String string = ArkValue.gContext.getString(ResourceUtil.getStringResIDByName(SqR.string.hyberry_format_living_share_msg));
        Object[] objArr = new Object[2];
        objArr[0] = shareEnterMessage.mSharePlatform == null ? "" : shareEnterMessage.mSharePlatform;
        objArr[1] = shareEnterMessage.mExtraMsg;
        sb.append(String.format(string, objArr));
        spannableStringBuilder.append((CharSequence) MessageLoader.getSpannaleText(COLOR_VIP_ENTER_TEXT, sb.toString()));
        comnTextView.setComnText(spannableStringBuilder);
        comnTextView.setMovementMethod(LinkMovementMethod.getInstance());
        comnTextView.setLongClickable(false);
    }

    public static void appendSystemMessage(String str, String str2, ComnTextView comnTextView) {
        if (comnTextView == null) {
            return;
        }
        comnTextView.setMaxLines(100);
        comnTextView.setShadowLayer(2.0f, 2.0f, 2.0f, -16777216);
        comnTextView.setLineSpacing(TypedValue.applyDimension(1, 1.0f, ArkValue.gContext.getResources().getDisplayMetrics()), 1.0f);
        SpannableString spannaleText = MessageLoader.getSpannaleText(COLOR_SYSTEM_TEXT, str);
        if (!TextUtils.isEmpty(str2) && str.contains(str2)) {
            spannaleText.setSpan(new ForegroundColorSpan(COLOR_NAME), str.indexOf(str2), str2.length(), 18);
        }
        comnTextView.setComnText(spannaleText);
    }

    public static void appendShareMessage(final String str, ComnTextView comnTextView) {
        if (comnTextView == null) {
            return;
        }
        comnTextView.setMaxLines(100);
        comnTextView.setHighlightColor(0);
        comnTextView.setShadowLayer(2.0f, 2.0f, 2.0f, -16777216);
        comnTextView.setLineSpacing(TypedValue.applyDimension(1, 1.0f, ArkValue.gContext.getResources().getDisplayMetrics()), 1.0f);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        SpannableString spannaleText = MessageLoader.getSpannaleText(-1, "开播成功，虎牙直播房间号：");
        SpannableString spannaleText2 = MessageLoader.getSpannaleText(COLOR_NAME, str);
        SpannableString spannableString = new SpannableString("复制");
        spannableString.setSpan(new RoundBackgroundColorSpan(COLOR_COPY_BG, -1, "复制"), 0, spannableString.length(), 18);
        spannableString.setSpan(new ClickListenerSpan(new View.OnClickListener() { // from class: com.huya.berry.sdklive.living.messageboard.helper.MessageHelper.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MessageHelper.showCopyToast();
                CommonUtil.copyToClipboard(DataConst.URL_DEFAULT_SHARE + str);
                Report.event(SdkReportConst.CLICK_SMALLWINDOW_COPYURL);
            }
        }), 0, spannableString.length(), 18);
        spannableStringBuilder.append((CharSequence) spannaleText);
        spannableStringBuilder.append((CharSequence) spannaleText2).append((CharSequence) "  ");
        spannableStringBuilder.append((CharSequence) spannableString);
        comnTextView.setComnText(spannableStringBuilder);
        comnTextView.setMovementMethod(LinkMovementMethod.getInstance());
        if (comnTextView instanceof MarqueeTextView) {
            ((MarqueeTextView) comnTextView).stopScroll();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void showCopyToast() {
        Application application = ArkValue.gContext;
        final TextView textView = new TextView(application);
        textView.setPadding((int) UIUtil.getDp(10.0f), (int) UIUtil.getDp(5.0f), (int) UIUtil.getDp(10.0f), (int) UIUtil.getDp(5.0f));
        textView.setBackgroundColor(-16777216);
        textView.setTextColor(-1);
        textView.setText("已复制直播间地址");
        WindowManager.LayoutParams defaultSystemWindowParams = SystemUtil.getDefaultSystemWindowParams(-2, -2, false);
        defaultSystemWindowParams.gravity = 17;
        final WindowManager windowManager = (WindowManager) application.getSystemService("window");
        windowManager.addView(textView, defaultSystemWindowParams);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, "alpha", 1.0f, 0.0f);
        objectAnimatorOfFloat.setDuration(2000L);
        objectAnimatorOfFloat.addListener(new Animator.AnimatorListener() { // from class: com.huya.berry.sdklive.living.messageboard.helper.MessageHelper.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                windowManager.removeView(textView);
            }
        });
        objectAnimatorOfFloat.start();
    }
}
