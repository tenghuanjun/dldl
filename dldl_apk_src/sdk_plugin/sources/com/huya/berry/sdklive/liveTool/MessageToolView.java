package com.huya.berry.sdklive.liveTool;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.duowan.live.common.framework.BasePresenter;
import com.duowan.live.common.framework.BaseViewContainer;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.gamesdk.utils.UIUtil;
import com.huya.berry.sdklive.living.messageboard.BaseHandlerApp;
import com.huya.berry.sdklive.living.messageboard.MessageToolContainer;
import com.huya.berry.sdklive.living.messageboard.ui.ChatSigTextView;
import com.sqwan.liveshow.huya.SqR;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class MessageToolView extends BaseViewContainer {
    public static final int TAB_MESSAGE = 0;
    public static final String TAG = "MessageToolView";
    private LinearLayout mChatLineView;
    private FrameLayout mChatNormalView;
    private MessagePagerAdpter mMessagePagerAdpter;
    private ChatSigTextView mSigTextView;
    private TextView mTipTv;
    private ViewPager mViewPager;
    public static final int VIEW_HEIGHT_EXPAND = (int) UIUtil.getDp(147.0f);
    public static final int VIEW_HEIGHT_MINI = (int) UIUtil.getDp(40.0f);
    public static final int VIEW_WIDTH = (int) UIUtil.getDp(305.0f);
    public static final int VIEW_WIDTH_LANDSCAPE = (int) UIUtil.getDp(314.0f);
    public static int NOW_VIEW_WIDTH = VIEW_WIDTH;

    @Override // com.duowan.live.common.framework.BaseViewContainer
    public BasePresenter createPresenter() {
        return null;
    }

    @Override // com.duowan.live.common.framework.BaseViewContainer
    protected void onDestroy() {
    }

    public MessageToolView(Context context) {
        super(context);
    }

    public MessageToolView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MessageToolView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public View getAnimatonView() {
        return this.mViewPager;
    }

    public void setOrientation(boolean z) {
        if (z) {
            NOW_VIEW_WIDTH = VIEW_WIDTH;
        } else {
            NOW_VIEW_WIDTH = VIEW_WIDTH_LANDSCAPE;
        }
    }

    @Override // com.duowan.live.common.framework.BaseViewContainer
    protected void init() {
        BaseHandlerApp.inflate(getContext(), ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_pub_tool_message_board), this, true);
        this.mTipTv = (TextView) findViewById(ResourceUtil.getIdResIDByName(SqR.id.tip_tv));
        this.mSigTextView = (ChatSigTextView) findViewById(ResourceUtil.getIdResIDByName(SqR.id.sig_text_view));
        this.mChatNormalView = (FrameLayout) findViewById(ResourceUtil.getIdResIDByName(SqR.id.chat_normal));
        this.mChatLineView = (LinearLayout) findViewById(ResourceUtil.getIdResIDByName(SqR.id.chat_line));
        this.mViewPager = (ViewPager) findViewById(ResourceUtil.getIdResIDByName(SqR.id.view_pager));
        MessagePagerAdpter messagePagerAdpter = new MessagePagerAdpter(getContext());
        this.mMessagePagerAdpter = messagePagerAdpter;
        this.mViewPager.setAdapter(messagePagerAdpter);
        switchPage(0);
    }

    @Override // com.duowan.live.common.framework.BaseViewContainer, com.duowan.live.common.framework.ILifeCycle
    public void onPause() {
        super.onPause();
        MessagePagerAdpter messagePagerAdpter = this.mMessagePagerAdpter;
        if (messagePagerAdpter != null) {
            messagePagerAdpter.onPause();
        }
    }

    public boolean isExpandMode() {
        return this.mChatNormalView.getVisibility() == 0;
    }

    public void setExpandMode(boolean z) {
        if (z) {
            this.mChatNormalView.setVisibility(0);
            this.mChatLineView.setVisibility(8);
        } else {
            this.mChatNormalView.setVisibility(8);
            this.mChatLineView.setVisibility(0);
        }
    }

    public void setTipText(boolean z, boolean z2, String str) {
        if (z) {
            this.mTipTv.setText("直播已暂停，观众看不到你的游戏画面");
            this.mTipTv.setTextColor(getResources().getColor(ResourceUtil.getColorResIDByName(SqR.color.hyberry_color_FF4949)));
        } else {
            if (z2) {
                this.mTipTv.setTextColor(getResources().getColor(ResourceUtil.getColorResIDByName(SqR.color.hyberry_color_ff8800)));
            } else {
                this.mTipTv.setTextColor(getResources().getColor(ResourceUtil.getColorResIDByName(SqR.color.hyberry_white)));
            }
            this.mTipTv.setText(str);
        }
    }

    public void setMicBan(boolean z) {
        if (z) {
            this.mTipTv.setCompoundDrawablePadding((int) UIUtil.getDp(5.0f));
            this.mTipTv.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_mic_ban_icon), null), (Drawable) null, (Drawable) null, (Drawable) null);
        } else {
            this.mTipTv.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        }
    }

    private void switchPage(int i) {
        this.mViewPager.setCurrentItem(i);
    }

    @Override // com.duowan.live.common.framework.BaseViewContainer, com.duowan.live.common.framework.ILifeCycle
    public void onResume() {
        super.onResume();
        MessagePagerAdpter messagePagerAdpter = this.mMessagePagerAdpter;
        if (messagePagerAdpter != null) {
            messagePagerAdpter.onResume();
        }
    }

    private class MessagePagerAdpter extends PagerAdapter {
        private int mPagerNum;
        private ArrayList<BaseViewContainer> mViewList = new ArrayList<>();

        @Override // android.support.v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public MessagePagerAdpter(Context context) {
            MessageToolContainer messageToolContainer = new MessageToolContainer(MessageToolView.this.getContext());
            messageToolContainer.setSigTextView(MessageToolView.this.mSigTextView);
            this.mViewList.add(messageToolContainer);
        }

        @Override // android.support.v4.view.PagerAdapter
        public int getCount() {
            return this.mViewList.size();
        }

        public void onResume() {
            Iterator<BaseViewContainer> it = this.mViewList.iterator();
            while (it.hasNext()) {
                it.next().onResume();
            }
        }

        public void onDestroy() {
            Iterator<BaseViewContainer> it = this.mViewList.iterator();
            while (it.hasNext()) {
                it.next().onStop();
            }
        }

        public void onPause() {
            Iterator<BaseViewContainer> it = this.mViewList.iterator();
            while (it.hasNext()) {
                it.next().onPause();
            }
        }

        @Override // android.support.v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            if (this.mViewList.get(i) != null) {
                ((ViewPager) viewGroup).removeView(this.mViewList.get(i));
            }
        }

        @Override // android.support.v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            try {
                try {
                    if (this.mViewList.get(i).getParent() == null) {
                        ((ViewPager) viewGroup).addView(this.mViewList.get(i), 0);
                    } else {
                        ((ViewGroup) this.mViewList.get(i).getParent()).removeView(this.mViewList.get(i));
                        ((ViewPager) viewGroup).addView(this.mViewList.get(i), 0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.mPagerNum = i;
                return this.mViewList.get(i);
            } catch (Throwable th) {
                this.mPagerNum = i;
                throw th;
            }
        }
    }
}
