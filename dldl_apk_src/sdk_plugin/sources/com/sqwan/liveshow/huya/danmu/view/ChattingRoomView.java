package com.sqwan.liveshow.huya.danmu.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.ViewUtils;
import com.sqwan.liveshow.huya.SqR;
import com.sqwan.liveshow.huya.danmu.adpter.IMAdapter;
import com.sqwan.liveshow.huya.request.bean.danmu.http.FetchImRspBean;
import com.sqwan.liveshow.huya.request.bean.danmu.websocket.factory.AImMsg;
import com.sqwan.liveshow.huya.request.bean.danmu.websocket.factory.AImMsgFactory;
import com.sqwan.liveshow.huya.skin.SkinHelper;
import com.sqwan.liveshow.huya.skin.view.SkinImageView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ChattingRoomView extends LinearLayout {
    private static final String TAG = "ChattingRoomView";
    private IMAdapter imAdapter;
    private InputAndCountDownView inputView;
    private SkinImageView mIvChatTitle;
    private SkinImageView mIvCheckNewMessage;
    private float mRatio;
    private RecyclerView recyclerView;

    public ChattingRoomView(Context context) {
        this(context, null);
    }

    public ChattingRoomView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ChattingRoomView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ChattingRoomView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mRatio = 2.142857f;
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, SqResUtils.getLayoutId(context, SqR.layout.sy37_liveshow_chatting_view), this);
        this.recyclerView = (RecyclerView) findViewById(SqResUtils.getId(context, "rv_im_chat_message"));
        this.mIvCheckNewMessage = (SkinImageView) findViewById(SqResUtils.getId(context, SqR.id.iv_im_check_new_message));
        this.imAdapter = new IMAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(this.imAdapter);
        InputAndCountDownView inputAndCountDownView = (InputAndCountDownView) findViewById(SqResUtils.getId(context, SqR.id.ip_input_view));
        this.inputView = inputAndCountDownView;
        inputAndCountDownView.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.danmu.view.ChattingRoomView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        this.mIvCheckNewMessage.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.danmu.view.ChattingRoomView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChattingRoomView.this.recyclerView.scrollToPosition(ChattingRoomView.this.imAdapter.getItemCount() - 1);
                ViewUtils.gone(ChattingRoomView.this.mIvCheckNewMessage);
            }
        });
        this.inputView.getTvInputMessage().setTextColor(SkinHelper.getColorValue(context, SqR.color.sy37_item_roast_view_tv_input_message_text_color, Color.parseColor(getResources().getConfiguration().orientation == 1 ? "#80FFFFFF" : "#6E718B")));
        this.inputView.getTvCountDown().setTextColor(SkinHelper.getColorValue(context, SqR.color.sy37_item_roast_view_tv_countdown_text_color, Color.parseColor("#6E718B")));
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.sqwan.liveshow.huya.danmu.view.ChattingRoomView.3
            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
            }

            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                LogUtil.i(ChattingRoomView.TAG, "onScrolled");
                boolean zIsSlideToBottom = ChattingRoomView.this.isSlideToBottom(recyclerView);
                LogUtil.i(ChattingRoomView.TAG, "addChatMessage isVisBottom:" + zIsSlideToBottom);
                if (zIsSlideToBottom) {
                    ViewUtils.gone(ChattingRoomView.this.mIvCheckNewMessage);
                }
            }
        });
        SkinImageView skinImageView = (SkinImageView) findViewById(SqResUtils.getId(context, SqR.id.iv_im_chat_title));
        this.mIvChatTitle = skinImageView;
        if (skinImageView == null || getContext().getResources().getConfiguration().orientation != 1) {
            return;
        }
        this.mIvChatTitle.setImageDrawable(SkinHelper.getDrawable(context, SqR.drawable.sy37_liveshow_title_chat_room));
    }

    public void addChatMessage(FetchImRspBean.ImMsg imMsg) {
        AImMsg aImMsgConvert;
        if (imMsg == null || (aImMsgConvert = new AImMsgFactory().convert(imMsg)) == null) {
            return;
        }
        addChatMessage(aImMsgConvert.getUser(), aImMsgConvert.getContent());
    }

    public void addChatMessage(CharSequence charSequence, CharSequence charSequence2) {
        if (this.imAdapter == null) {
            return;
        }
        boolean zIsSlideToBottom = isSlideToBottom(this.recyclerView);
        LogUtil.i(TAG, "addChatMessage isVisBottom:" + zIsSlideToBottom);
        this.imAdapter.updateDataDirect(charSequence, charSequence2);
        if (zIsSlideToBottom) {
            this.recyclerView.scrollToPosition(this.imAdapter.getItemCount() - 1);
        } else {
            ViewUtils.show(this.mIvCheckNewMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSlideToBottom(RecyclerView recyclerView) {
        return recyclerView != null && recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange();
    }

    public void setCountDownViewText(String str) {
        this.inputView.getTvCountDown().setText(str);
    }

    public InputAndCountDownView getInputView() {
        return this.inputView;
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (getResources().getConfiguration().orientation == 2) {
            int size = View.MeasureSpec.getSize(i2);
            float f = this.mRatio;
            if (f != 0.0f) {
                i = View.MeasureSpec.makeMeasureSpec((int) (size / f), 1073741824);
            }
        }
        super.onMeasure(i, i2);
    }
}
